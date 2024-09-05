package app.ativa_recife.viewmodel


import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import app.ativa_recife.model.Event
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class MainViewModel : ViewModelBase() {
    private val _events = mutableStateListOf<Event>()
    val events : List<Event>
        get() = _events
}