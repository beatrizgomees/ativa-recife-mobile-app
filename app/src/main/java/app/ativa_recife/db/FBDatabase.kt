package app.ativa_recife.db

import app.ativa_recife.model.Event
import app.ativa_recife.model.User
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.util.Listener

class FBDatabase(private val listener: Listener? = null) {
    private val auth = Firebase.auth
    private val db = Firebase.firestore
    private var eventListReg: ListenerRegistration? = null
    interface Listener {
        fun onUserLoaded(user: User)
        fun onEventAdded(event: Event)
        fun onEventRemoved(event: Event)
    }
    init {
        auth.addAuthStateListener { auth ->
            if (auth.currentUser == null) {
                eventListReg?.remove()
                return@addAuthStateListener
            }
            val refCurrUser = db.collection("users")
                .document(auth.currentUser!!.uid)
            refCurrUser.get().addOnSuccessListener {
                it.toObject(FBUser::class.java)?.let { user ->
                    listener?.onUserLoaded(user.toUser())
                }
            }
            eventListReg = refCurrUser.collection("cities")
                .addSnapshotListener { snapshots, ex ->
                    if (ex != null) return@addSnapshotListener
                    snapshots?.documentChanges?.forEach { change ->
                        val fbEvent = change.document.toObject(FBEvent::class.java)
                        if (change.type == DocumentChange.Type.ADDED) {
                            listener?.onEventAdded(fbEvent.toEvent())
                        } else if (change.type == DocumentChange.Type.REMOVED) {
                            listener?.onEventRemoved(fbEvent.toEvent())
                        }
                    }
                }
        }
    }
    fun register(user: User) {
        fun register(user: User) {
            if (auth.currentUser == null)
                throw RuntimeException("User not logged in!")
            val uid = auth.currentUser!!.uid
            db.collection("users").document(uid + "").set(user.toFBUser());
        }
    }
    fun add(event: Event) {
        fun add(event: Event) {
            if (auth.currentUser == null)
                throw RuntimeException("User not logged in!")
            val uid = auth.currentUser!!.uid
            db.collection("users").document(uid).collection("events")
                .document(event.name).set(event.toFBEvent())
        }
    }
    fun remove(event: Event) {
        if (auth.currentUser == null)
            throw RuntimeException("User not logged in!")
        val uid = auth.currentUser!!.uid
        db.collection("users").document(uid).collection("events")
            .document(event.name).delete()
    }
}