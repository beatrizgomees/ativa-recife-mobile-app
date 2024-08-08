package app.ativa_recife.di.components.nav

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavBar(navController: NavHostController) {

    val items = listOf(
        BottomNavItem.HomePage,
        BottomNavItem.EventRegistrationPage,
        BottomNavItem.SearchEvent,
        BottomNavItem.FavoritesPage,
        BottomNavItem.SettingsPage
    )

    NavigationBar(contentColor = Color.Black) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach{ item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                icon = {Icon(imageVector = item.icon, contentDescription = item.title)},
                label = {Text(text = item.title, fontSize = 12.sp)},
                alwaysShowLabel = true,
                onClick = {
                    navController.navigate(item.route) {
                        // Volta pilha de navegação até HomePage (startDest).
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it) {
                                saveState = true
                            }
                            restoreState = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}