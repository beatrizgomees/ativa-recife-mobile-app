package app.ativa_recife.viewmodel


import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import app.ativa_recife.db.fb.FBDatabase
import app.ativa_recife.model.Event
import app.ativa_recife.model.User

class MainViewModel : ViewModelBase(), FBDatabase.Listener{
    private val _eventsNear = mutableStateListOf<Event>()
    val eventsNear : List<Event>
        get() = _eventsNear

    private val _user = mutableStateOf (User(name = ""))
    val user : User
        get() = _user.value

    private val _eventsSubscribed = mutableStateListOf<Event>()
    val eventsSubscribed : List<Event>
        get() = _eventsSubscribed

    private val _events = mutableStateListOf<Event>()
    val events : List<Event>
        get() = _events

    override fun onUserLoaded(user: User) {
        _user.value = user
    }

    override fun onEventsLoaded(events: List<Event>) {
        _events.addAll(events)
        _eventsNear.addAll(events)
    }

    override fun onEventSubscribed(event: Event) {
        _eventsSubscribed.add(event)
        filterEventsSubscribed()
    }

    override fun onEventCreated(event: Event) {
        _eventsNear.add(event)
        _events.add(event)
    }

    override fun onEventUnsubscribed(event: Event) {
        _eventsSubscribed.remove(event)
        _eventsNear.add(event)
    }

    override fun onEventUpdated(event: Event) {
        _eventsNear.remove(event)
        _eventsNear.add(event)
        _events.remove(event)
        _events.add(event)

    }

    fun filterEventsSubscribed() {
        _eventsNear.removeAll { eventSubscribedOnNearList ->
            _eventsSubscribed.any { eventSubscribed ->
                eventSubscribed.id == eventSubscribedOnNearList.id
            }
        }
    }

}