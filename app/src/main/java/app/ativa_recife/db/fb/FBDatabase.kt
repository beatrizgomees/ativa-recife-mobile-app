package app.ativa_recife.db.fb

import app.ativa_recife.model.Event
import app.ativa_recife.model.User
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject

class FBDatabase(private val listener: Listener? = null) {
    private val auth = Firebase.auth
    private val db = Firebase.firestore

    interface Listener {
        fun onUserLoaded(user: User)
        fun onEventsLoaded(events: List<Event>)
        fun onEventSubscribed(event: Event)
        fun onEventCreated(event: Event)
        fun onEventUnsubscribed(event: Event)
        fun onEventUpdated(event : Event)
    }

    init {
        auth.addAuthStateListener { auth ->
            val refEvents = db.collection("events")
            if (auth.currentUser == null) {
//                labelled return, retorna a execução apenas do callback do addAuthStateListener,
//                encerrando o código que seria executado dentro desse listener, mas não retorna da
//                função principal em que esse código está rodando
                return@addAuthStateListener
            }

            val refCurrentUser =  db.collection("users").document(auth.currentUser!!.uid)

            //Listener recuperando informações do usuário logado
            val refCurrUser = db.collection("users")
                .document(auth.currentUser!!.uid)
            refCurrUser.get().addOnSuccessListener {
                it.toObject(FBUser::class.java)?.let { user ->
                    listener?.onUserLoaded(user.toUser())
                }
            }

            refEvents.get().addOnSuccessListener { documents ->
                val listEvents = mutableListOf<Event>()
                documents.forEach { document ->
                    val currentfbevent = document.toObject(FBEvent::class.java)
                    val currentEvent = currentfbevent.toEvent()
                    listEvents.add(currentEvent)
                }
                listener?.onEventsLoaded(listEvents)
            }


            //Listener primeira vez carregando os eventos
//            refEvents.get()
//                .addOnSuccessListener { result ->
//                    val eventList = result.documents.mapNotNull { document ->
////                      // Converte o documento para o objeto Event
//                        val fbevent = document.toObject(FBEvent::class.java)
//                        val event = fbevent?.toEvent()
//                        event
//                    }
////
//                    listener?.onEventsLoaded(eventList)
//                }

            //Listener de criação de evento
            refEvents
                .addSnapshotListener{ snapshots, ex ->
                    if(ex != null) return@addSnapshotListener
                    snapshots?.documentChanges?.forEach { change ->
                        val newfbEvent = change.document.toObject(FBEvent::class.java)
                        val newEvent = newfbEvent.toEvent()

                        if(change.type == DocumentChange.Type.MODIFIED) {
                            listener?.onEventUpdated(newEvent)
                        }
                    }

                }

            //Listener de inscrição e desinscrição em eventos
            refCurrentUser.collection("registredEvents")
                .addSnapshotListener{snapshots, ex ->
                    if(ex != null) return@addSnapshotListener
                    snapshots?.documentChanges?.forEach { change ->
                        val subscribedfbEvent = change.document.toObject(FBEvent::class.java)
                        val subscribedOrUnsubscribedEvent = subscribedfbEvent.toEvent()
                        if(change.type == DocumentChange.Type.ADDED) {
                            listener?.onEventSubscribed(subscribedOrUnsubscribedEvent)
                        }
                        else if(change.type == DocumentChange.Type.REMOVED) {
                            listener?.onEventUnsubscribed(subscribedOrUnsubscribedEvent)
                        }

                    }
                }

        }
    }
    fun register(user: User) {
        if (auth.currentUser == null) {
            throw RuntimeException("User not logged in!")
        }
        val uid = auth.currentUser!!.uid

        val fbUser = FBUser(
            name = user.name,
            address = FBAddress()
            )

        db.collection("users").document(uid).set(fbUser)
    }

    fun registerEvent(event: Event) {
        if (auth.currentUser == null) {
            throw RuntimeException("User not logged in!")
        }
        val uid = auth.currentUser!!.uid

        val fbEvent = FBEvent(event)

        val documentID = db.collection("events").add(fbEvent)
            .addOnSuccessListener { documentReference ->
                val documentID = documentReference.id
                db.collection("events").document(documentID).update("id", documentID)
                fbEvent.id = documentID
                db.collection("users")
                    .document(uid).collection("publicizedEvents")
                    .document(documentID).set(fbEvent)
            }
    }

    fun subscribeEvent(event : Event) {
        if (auth.currentUser == null) {
            throw RuntimeException("User not logged in!")
        }
        val uid = auth.currentUser!!.uid

        val fbEvent = FBEvent(event)
        db.collection("users").document(uid).collection("registredEvents").document(fbEvent.id).set(fbEvent)

        db.collection("users")
            .document(uid).collection("registredEvents")
            .document(event.id).update("id", event.id)
    }

    fun unsubscribeEvent(event: Event) {
        if (auth.currentUser == null) {
            throw RuntimeException("User not logged in!")
        }
        val uid = auth.currentUser!!.uid

        val fbEvent = FBEvent(event) //só por padrão!

        db.collection("users").document(uid).collection("registredEvents").document(fbEvent.id).delete()
    }
}