package app.ativa_recife.viewmodel


import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import app.ativa_recife.db.FBDatabase
import app.ativa_recife.model.Address
import app.ativa_recife.model.Event
import app.ativa_recife.model.User

class MainViewModel : ViewModel(), FBDatabase.Listener {
    private val _user = mutableStateOf(User("", "", ""))
    val user : User
        get() = _user.value

    private val _events = mutableStateListOf<Event>()
    val events : List<Event>
        get() = _events

    override fun onUserLoaded(user: User) {
        _user.value = user
    }
    override fun onEventAdded(event: Event) {
        _events.add(event)
    }
    override fun onEventRemoved(event: Event) {
        _events.remove(event)
    }
}