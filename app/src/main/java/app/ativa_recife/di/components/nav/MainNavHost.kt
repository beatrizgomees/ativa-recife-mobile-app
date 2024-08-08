package app.ativa_recife.di.components.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import app.ativa_recife.pages.EventRegistrationPage
import app.ativa_recife.pages.HomePage
import app.ativa_recife.pages.SearchPage
import app.ativa_recife.pages.SettingsPage
import app.ativa_recife.viewmodel.MainViewModel

@Composable
fun MainNavHost(navController: NavHostController, viewModel: MainViewModel) {

    NavHost(navController, startDestination = BottomNavItem.HomePage.route ){
        composable(route = BottomNavItem.HomePage.route){
            HomePage()
        }
        composable(route = BottomNavItem.EventRegistrationPage.route){
            EventRegistrationPage()
        }
        composable(route = BottomNavItem.SearchEvent.route){
            SearchPage()
        }
        composable(route = BottomNavItem.SettingsPage.route){
            SettingsPage()
        }
    }
}