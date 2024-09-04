package app.ativa_recife.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import app.ativa_recife.activity.ui.theme.AtivarecifeTheme
import app.ativa_recife.pages.CreateEventPage

class CreateEventActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AtivarecifeTheme {
                CreateEventPage()
            }
        }
    }
}
