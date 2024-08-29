package app.ativa_recife.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import app.ativa_recife.utils.components.nav.BottomNavBar
import app.ativa_recife.utils.components.nav.MainNavHost
import app.ativa_recife.ui.theme.AtivarecifeTheme
import app.ativa_recife.viewmodel.MainViewModel

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()
            val viewModel : MainViewModel by viewModels()

            AtivarecifeTheme {
                Scaffold(
                    bottomBar = {
                        BottomNavBar(navController = navController)
                    },

                    ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        MainNavHost(navController = navController, viewModel)
                    }
                }
            }
        }
    }
}

