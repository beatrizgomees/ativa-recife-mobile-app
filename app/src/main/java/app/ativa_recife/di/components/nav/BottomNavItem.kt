package app.ativa_recife.di.components.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed  class BottomNavItem(var title: String, var icon: ImageVector, var route: String) {

    object HomePage : BottomNavItem("Inicio", Icons.Default.Home, "home")
    object EventRegistrationPage : BottomNavItem("Inscrições", Icons.Default.DateRange, "eventRegistration")
    object SearchEvent : BottomNavItem("Eventos", Icons.Default.Search, "search")
    object FavoritesPage : BottomNavItem("Favoritos", Icons.Default.Favorite, "favorite")
    object SettingsPage : BottomNavItem("settings", Icons.Default.Settings, "settings")

}