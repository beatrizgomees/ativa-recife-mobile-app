package app.ativa_recife.utils.components.nav

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import app.ativa_recife.db.fb.FBDatabase
import app.ativa_recife.pages.EventRegistrationPage
import app.ativa_recife.pages.HomePage
import app.ativa_recife.pages.SearchPage
import app.ativa_recife.pages.SettingsPage
import app.ativa_recife.viewmodel.MainViewModel

@Composable
fun MainNavHost(navController: NavHostController, viewModel: MainViewModel, context: Context, fbDatabase: FBDatabase) {

    NavHost(navController, startDestination = BottomNavItem.HomePage.route ){
        composable(route = BottomNavItem.HomePage.route){
            HomePage(viewModel = viewModel, fbDatabase = fbDatabase)
        }
        composable(route = BottomNavItem.EventRegistrationPage.route){
            EventRegistrationPage(viewModel = viewModel, fbDatabase = fbDatabase)
        }
        composable(route = BottomNavItem.SearchEvent.route){
            SearchPage(viewModel = viewModel, context = context)
        }
        composable(route = BottomNavItem.SettingsPage.route){
            SettingsPage()
        }
    }
}