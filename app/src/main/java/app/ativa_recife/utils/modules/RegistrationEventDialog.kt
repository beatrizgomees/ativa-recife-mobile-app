package app.ativa_recife.utils.modules

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import app.ativa_recife.db.fb.FBDatabase
import app.ativa_recife.model.Address
import app.ativa_recife.model.Event
import app.ativa_recife.model.User
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun RegistrationEventDialog(onDismiss: () -> Unit, onConfirm: (event: Event) -> Unit, manager : User) {
    var currentPage by remember { mutableIntStateOf(0) } // Controle da página atual

    // Variáveis de estado para armazenar os dados do formulário
    var title by remember { mutableStateOf("") }
    var street by remember { mutableStateOf("") }
    var district by remember { mutableStateOf("") }
    var number by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var state by remember { mutableStateOf("") }
    var dateInput by remember { mutableStateOf("") }
    var startTimeInput by remember { mutableStateOf("") }
    var sizeRoute by remember { mutableStateOf("") }
    var startingLocation by remember { mutableStateOf(LatLng(0.0, 0.0)) }

    val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val timeFormatter = SimpleDateFormat("HH:mm", Locale.getDefault())
    var errorMessage by remember { mutableStateOf("") }

    Dialog(onDismissRequest = onDismiss,
        properties =
            DialogProperties(
                usePlatformDefaultWidth = false
            )
        ) {
        Surface( modifier = Modifier
            .fillMaxWidth()
        ) {
            Column(modifier = Modifier
                .fillMaxWidth()){
                if (currentPage == 0) {
                    // Página 1: Detalhes básicos do evento
                    PageOne(
                        title, { title = it },
                        street, { street = it },
                        district, { district = it },
                        number, { number = it },
                        city, { city = it },
                        state, { state = it },
                        dateInput, { dateInput = it },
                        errorMessage
                    )
                } else {
                    // Página 2: Detalhes adicionais
                    PageTwo(
                        startTimeInput, { startTimeInput = it },
                        sizeRoute, { sizeRoute = it },
                        startingLocation, { lat, lon -> startingLocation = LatLng(lat, lon) }
                    )
                }

                // Controles de navegação
                Row {
                    if (currentPage > 0) {
                        Button(onClick = { currentPage-- }) {
                            Text("Voltar")
                        }
                    }
                    Spacer(modifier = androidx.compose.ui.Modifier.weight(1f))
                    if (currentPage < 1) {
                        Button(onClick = { currentPage++ }) {
                            Text("Próximo")
                        }
                    }
                    Button(onClick = onDismiss) {
                        Text(text = "Fechar")
                    }
                }
                if (currentPage == 1) {
                    Spacer(modifier = androidx.compose.ui.Modifier.height(16.dp))
                    Button(onClick = {
                        // Validação e criação do evento
                        try {
                            val parsedDate = dateFormatter.parse(dateInput)
                            val parsedStartTime = timeFormatter.parse(startTimeInput)
                            errorMessage = "Evento cadastrado com sucesso!"
                            val event = Event(
                                title,
                                Address(street, district, number.toIntOrNull() ?: 0, city, state),
                                parsedDate,
                                parsedStartTime,
                                manager,
                                sizeRoute,
                                startingLocation
                            )
                            onConfirm(event)
                            onDismiss()
                        } catch (e: Exception) {
                            errorMessage = "Erro ao criar o evento: ${e.message}"
                        }
                    }) {
                        Text("Cadastrar Evento")
                    }
                    Text(errorMessage, color = if (errorMessage.contains("Erro")) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary)
                }
            }
        }
    }
}

@Composable
fun PageOne(
    title: String, onTitleChange: (String) -> Unit,
    street: String, onStreetChange: (String) -> Unit,
    district: String, onDistrictChange: (String) -> Unit,
    number: String, onNumberChange: (String) -> Unit,
    city: String, onCityChange: (String) -> Unit,
    state: String, onStateChange: (String) -> Unit,
    dateInput: String, onDateInputChange: (String) -> Unit,
    errorMessage: String
) {
    Column(modifier = Modifier
        .fillMaxWidth()) {
        TextField(value = title, onValueChange = onTitleChange, label = { Text("Título do Evento") }, modifier = Modifier.fillMaxWidth())
        TextField(value = street, onValueChange = onStreetChange, label = { Text("Rua")}, modifier = Modifier.fillMaxWidth())
        TextField(value = district, onValueChange = onDistrictChange, label = { Text("Bairro") }, modifier = Modifier.fillMaxWidth())
        TextField(value = number, onValueChange = onNumberChange, label = { Text("Número") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), modifier = Modifier.fillMaxWidth())
        TextField(value = city, onValueChange = onCityChange, label = { Text("Cidade") }, modifier = Modifier.fillMaxWidth())
        TextField(value = state, onValueChange = onStateChange, label = { Text("Estado") }, modifier = Modifier.fillMaxWidth())
        TextField(value = dateInput, onValueChange = onDateInputChange, label = { Text("Data (dd/MM/yyyy)") }, isError = errorMessage.contains("Data inválida"), modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun PageTwo(
    startTimeInput: String, onStartTimeChange: (String) -> Unit,
    sizeRoute: String, onSizeRouteChange: (String) -> Unit,
    startingLocation: LatLng, onLocationSelect: (Double, Double) -> Unit
) {

    Column(modifier = Modifier
        .fillMaxWidth()) {
        TextField(value = startTimeInput, onValueChange = onStartTimeChange, label = { Text("Hora de Início (HH:mm)") }, modifier = Modifier.fillMaxWidth())
        TextField(value = sizeRoute, onValueChange = onSizeRouteChange, label = { Text("Tamanho da Rota") }, modifier = Modifier.fillMaxWidth())

        GoogleMap(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f),
            onMapClick = { position -> onLocationSelect(position.latitude, position.longitude) }
        ) {
            Marker(state = MarkerState(position = startingLocation))
        }
        Text("Latitude: ${startingLocation.latitude}, Longitude: ${startingLocation.longitude}")
    }
}