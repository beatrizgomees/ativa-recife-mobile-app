package app.ativa_recife.viewmodel


import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import app.ativa_recife.model.Event

class MainViewModel : ViewModel() {
    private val _events = mutableStateListOf<Event>()
    val events : List<Event>
        get() = _events
}