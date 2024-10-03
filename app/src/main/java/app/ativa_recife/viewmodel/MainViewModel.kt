package app.ativa_recife.viewmodel


import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import app.ativa_recife.db.fb.FBDatabase
import app.ativa_recife.model.Address
import app.ativa_recife.model.Event
import app.ativa_recife.model.User
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class MainViewModel : ViewModelBase(), FBDatabase.Listener{
    private val _events = mutableStateListOf<Event>()
    val events : List<Event>
        get() = _events

    private val _user = mutableStateOf (User(name = ""))
    val user : User
        get() = _user.value

    override fun onUserLoaded(user: User) {
        _user.value = user
    }

    override fun onEventsLoaded(events: List<Event>) {
        _events.addAll(events)
    }

    override fun onEventCreated(event: Event) {
        _events.add(event)
    }

}