package app.ativa_recife.model

import com.google.android.gms.maps.model.LatLng
import java.util.Date

data class Event (
    var address: Address,
    var data: Date,
    var startTime: Date,
    var manager: User,
    var sizeRoute: String,
    var startingLocation: LatLng //Latitude e longitude do endereço
)