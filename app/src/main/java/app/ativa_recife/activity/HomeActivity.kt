package app.ativa_recife.activity

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import app.ativa_recife.db.fb.FBDatabase
import app.ativa_recife.ui.theme.AtivarecifeTheme
import app.ativa_recife.utils.components.nav.BottomNavBar
import app.ativa_recife.utils.components.nav.BottomNavItem
import app.ativa_recife.utils.components.nav.MainNavHost
import app.ativa_recife.utils.modules.RegistrationEventDialog
import app.ativa_recife.viewmodel.MainViewModel

class HomeActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val context = LocalContext.current
            val launcher = rememberLauncherForActivityResult(contract =
            ActivityResultContracts.RequestPermission(), onResult = {})
            val viewModel : MainViewModel by viewModels()
            var showDialog by remember { mutableStateOf(false) }
            val currentRoute = navController.currentBackStackEntryAsState()
            val fbDB = remember { FBDatabase (viewModel) }
            val showButton = currentRoute.value?.destination?.route != BottomNavItem.SearchEvent.route

            if (!viewModel.loggedIn) {
                this.finish()
            }

            AtivarecifeTheme {
                if (showDialog) RegistrationEventDialog(
                    onDismiss = { showDialog = false },
                    onConfirm = { event ->
                        fbDB.registerEvent(event)
                        showDialog = false
                    },
                    manager = viewModel.user
                )
                Scaffold(
                    bottomBar = {
                        BottomNavBar(navController = navController)
                    },
                    floatingActionButton = {
                        if(showButton) {
                            FloatingActionButton(onClick = {showDialog = true}) {
                                Icon(Icons.Default.Add, contentDescription = "Adicionar")
                            }
                        }
                    }
                    ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                        MainNavHost(navController = navController, viewModel, context, fbDB)
                    }
                }


            }
        }
    }
}

