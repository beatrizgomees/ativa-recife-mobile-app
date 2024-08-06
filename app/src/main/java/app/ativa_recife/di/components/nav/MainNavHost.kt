package app.ativa_recife.di.components.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import app.ativa_recife.viewmodel.MainViewModel

@Composable
fun MainNavHost(navController: NavHostController, viewModel: MainViewModel) {

    NavHost(navController, startDestination = BottomNavItem.HomePage.route ){
        composable(route = BottomNavItem.HomePage.route){
            BottomNavItem.HomePage
        }
        composable(route = BottomNavItem.EventRegistrationPage.route){

        }
        composable(route = BottomNavItem.SearchEvent.route){

        }
    }
}