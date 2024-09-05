package app.ativa_recife.db.fb

import app.ativa_recife.model.Address
import app.ativa_recife.model.Event
import app.ativa_recife.model.User
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties
import java.util.Date

@IgnoreExtraProperties
data class FBEvent(
    var address: FBAddress = FBAddress(),
    var data: Date = Date(),
    var startTime: Date = Date(),
    var managerName: String = "",
    var managerAddress: FBAddress = FBAddress(), // Adiciona endereço do gerente
    var managerRegistredEvents: List<FBEvent> = emptyList(), // Adiciona eventos registrados do gerente
    var managerPublicizedEvents: List<FBEvent> = emptyList(), // Adiciona eventos publicizados do gerente
    var sizeRoute: String = "",
    var startingLocation: FBLatLng = FBLatLng(),
    var title: String = ""
) {
    // Construtor para converter Event para FBEvent
    constructor(event: Event) : this(
        address = FBAddress(event.address),
        data = event.data,
        startTime = event.startTime,
        managerName = event.manager.name, // Usa o nome do gerente
        managerAddress = FBAddress(event.manager.address), // Usa o endereço do gerente
        managerRegistredEvents = event.manager.registredEvents?.map { FBEvent(it) } ?: emptyList(), // Usa os eventos registrados do gerente
        managerPublicizedEvents = event.manager.publicizedEvents?.map { FBEvent(it) } ?: emptyList(), // Usa os eventos publicizados do gerente
        sizeRoute = event.sizeRoute,
        startingLocation = FBLatLng(event.startingLocation)
    )

    // Método para converter FBEvent de volta para Event
    @Exclude
    fun toEvent(): Event {
        return Event(
            address = address.toAddress(),
            data = data,
            startTime = startTime,
            manager = User(
                name = managerName,
                address = managerAddress.toAddress(),
                registredEvents = managerRegistredEvents.map { it.toEvent() },
                publicizedEvents = managerPublicizedEvents.map { it.toEvent() }
            ),
            sizeRoute = sizeRoute,
            startingLocation = startingLocation.toLatLng(),
            title = title
        )
    }
}




// Classe LatLng para Firestore
data class FBLatLng(
    var latitude: Double = 0.0,
    var longitude: Double = 0.0
) {
    constructor(latLng: LatLng) : this(
        latitude = latLng.latitude,
        longitude = latLng.longitude
    )

    @Exclude
    fun toLatLng(): LatLng {
        return LatLng(latitude, longitude)
    }
}