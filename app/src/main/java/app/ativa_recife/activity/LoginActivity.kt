package app.ativa_recife.activity

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import app.ativa_recife.activity.ui.theme.AtivarecifeTheme
import app.ativa_recife.pages.LoginPage
import app.ativa_recife.viewmodel.ViewModelBase


class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewModelBase = ViewModelBase()
        val activity = this
        setContent {
            if (viewModelBase.loggedIn) {
                Toast.makeText(activity, "LoginToast!", Toast.LENGTH_SHORT).show()
                activity.startActivity(
                    Intent(activity, HomeActivity::class.java).setFlags(
                        FLAG_ACTIVITY_SINGLE_TOP
                    )
                )
            }
            AtivarecifeTheme {
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    LoginPage()
            }
            }
        }
    }
}
