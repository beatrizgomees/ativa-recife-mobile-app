package app.ativa_recife.db.fb

import android.content.ContentValues.TAG
import android.util.Log
import app.ativa_recife.model.Address
import app.ativa_recife.model.Event
import app.ativa_recife.model.User
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import java.util.Date
import kotlin.random.Random

class FBDatabase(private val listener: Listener? = null) {
    private val auth = Firebase.auth
    private val db = Firebase.firestore

    interface Listener {
        fun onUserLoaded(user: User)
        fun onEventsLoaded(events: List<Event>)
//        fun onEventSubscribed(event: Event)
        fun onEventCreated(event: Event)
//        fun onEventUnsubscribed(event: Event)
    }

    init {
        auth.addAuthStateListener { auth ->
            if (auth.currentUser == null) {
//                labelled return, retorna a execução apenas do callback do addAuthStateListener,
//                encerrando o código que seria executado dentro desse listener, mas não retorna da
//                função principal em que esse código está rodando
                return@addAuthStateListener
            }
//
            val refCurrUser = db.collection("users")
                .document(auth.currentUser!!.uid)
            refCurrUser.get().addOnSuccessListener {
                it.toObject(FBUser::class.java)?.let { user ->
                    listener?.onUserLoaded(user.toUser())
                }
            }
//
            val refEvents = db.collection("events")
            refEvents.get()
                .addOnSuccessListener { result ->
//                    // Converte os documentos em uma lista de eventos
                    val eventList = result.documents.mapNotNull { document ->
//                      // Converte o documento para o objeto Event
                        val fbevent = document.toObject(FBEvent::class.java)
                        val event = fbevent?.toEvent()
                        event
                    }
//
//                    // Adiciona um log para a lista completa de eventos
//                    Log.d(TAG, "Lista de eventos recuperados: $eventList")
//
//                    // Chama o método onEventsLoaded da interface Listener
                    listener?.onEventsLoaded(eventList)
                }
//                .addOnFailureListener { exception ->
//                    // Trata falhas na obtenção dos eventos
//                    Log.w(TAG, "Erro ao buscar eventos: ", exception)
//                }
//
        }
//
    }
    fun register(user: User) {
        if (auth.currentUser == null) {
            throw RuntimeException("User not logged in!")
        }
        val uid = auth.currentUser!!.uid

        val fbUser = FBUser(
            name = user.name,
            address = FBAddress(),
            registredEvents = emptyList(),
            publicizedEvents = emptyList()
        )

        db.collection("users").document(uid).set(fbUser)
    }

    fun registerEvent(event: Event) {
        if (auth.currentUser == null) {
            throw RuntimeException("User not logged in!")
        }
        val uid = auth.currentUser!!.uid

        val fbEvent = FBEvent(event)

        db.collection("events").add(fbEvent)
        db.collection("users").document(uid).update("publicizedEvents", FieldValue.arrayUnion(fbEvent))
        listener?.onEventCreated(event)
    }
}