package app.ativa_recife.pages

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import app.ativa_recife.model.Event
import app.ativa_recife.ui.theme.Orange50
import app.ativa_recife.ui.theme.focusedTextFiled
import app.ativa_recife.ui.theme.labelMediumBlack
import app.ativa_recife.ui.theme.textFieldContainer
import app.ativa_recife.ui.theme.unfocusedTextField
import app.ativa_recife.viewmodel.MainViewModel
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchPage(modifier: Modifier = Modifier,
               viewModel: MainViewModel,
               context: Context
) {
    val activity = LocalContext.current as? Activity
    val camPosState = rememberCameraPositionState()
    val hasLocationPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED
        )
    }

    var currentEvents = viewModel.eventsNear
    var search by remember { mutableStateOf("") }
    var selectedFilter by remember { mutableStateOf("") }
    var filteredEvents by remember { mutableStateOf(currentEvents) }

    // Função para aplicar o filtro
    fun applyFilter(filter: String, query: String): List<Event> {
        return when (filter) {
            "title" -> filterEventsByTitle(currentEvents, query)
            "size" -> filterEventsBySizeRoute(currentEvents, query)
            "manager" -> filterEventsByManager(currentEvents, query)
            else -> currentEvents
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Orange50),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = camPosState,
                properties = MapProperties(isMyLocationEnabled = hasLocationPermission),
                uiSettings = MapUiSettings(myLocationButtonEnabled = true)
            ) {
                // Atualiza os marcadores com base nos eventos filtrados
                (if (filteredEvents.isNotEmpty()) filteredEvents else currentEvents).forEach {
                    it.startingLocation?.let { location ->
                        Marker(
                            state = MarkerState(position = location),
                            title = it.title,
                            snippet = "Organizador: ${it.manager.name} | Horário : ${it.data} "
                        )
                    }
                }
            }

            Column(modifier = Modifier.fillMaxSize()) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp),
                    value = search,
                    onValueChange = { search = it },
                    label = { Text("Search") },
                    trailingIcon = {
                        IconButton(onClick = {
                            // Aplica o filtro ao clicar no botão de pesquisa
                            if (selectedFilter.isNotEmpty() && search.isNotEmpty()) {
                                filteredEvents = applyFilter(selectedFilter, search)
                            }
                        }) {
                            Icon(imageVector = Icons.Filled.Search, contentDescription = "Search icon")
                        }
                    },
                    leadingIcon = {
                        IconButton(onClick = {
                            Toast.makeText(activity, "Abrindo os filtros", Toast.LENGTH_SHORT).show()
                        }) {
                            Icon(imageVector = Icons.Filled.List, contentDescription = "Filter icon")
                        }
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedPlaceholderColor = MaterialTheme.colorScheme.unfocusedTextField,
                        focusedPlaceholderColor = MaterialTheme.colorScheme.focusedTextFiled,
                        unfocusedContainerColor = MaterialTheme.colorScheme.textFieldContainer,
                        focusedContainerColor = MaterialTheme.colorScheme.textFieldContainer
                    )
                )

                Row(
                    modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Chip para filtro por título
                    FilterChip(
                        selected = selectedFilter == "title",
                        onClick = {
                            selectedFilter = if (selectedFilter == "title") "" else "title"
                            if (selectedFilter.isEmpty()) {
                                filteredEvents = currentEvents // Reseta os eventos para a lista original
                            }
                        },
                        label = { Text("Filter by Title") }
                    )

                    // Chip para filtro por tamanho da rota
                    FilterChip(
                        selected = selectedFilter == "size",
                        onClick = {
                            selectedFilter = if (selectedFilter == "size") "" else "size"
                            if (selectedFilter.isEmpty()) {
                                filteredEvents = currentEvents // Reseta os eventos para a lista original
                            }
                        },
                        label = { Text("Filter by Size of Route") }
                    )

                    // Chip para filtro por gerente
                    FilterChip(
                        selected = selectedFilter == "manager",
                        onClick = {
                            selectedFilter = if (selectedFilter == "manager") "" else "manager"
                            if (selectedFilter.isEmpty()) {
                                filteredEvents = currentEvents // Reseta os eventos para a lista original
                            }
                        },
                        label = { Text("Filter by Manager") }
                    )
                }
            }
        }
    }
}

// Funções de filtro
fun filterEventsByTitle(events: List<Event>, query: String): List<Event> {
    return events.filter { it.title.contains(query, ignoreCase = true) }
}

fun filterEventsBySizeRoute(events: List<Event>, routeSize: String): List<Event> {
    return events.filter { it.sizeRoute == routeSize }
}

fun filterEventsByManager(events: List<Event>, managerName: String): List<Event> {
    return events.filter { it.manager.name.contains(managerName, ignoreCase = true) }
}
