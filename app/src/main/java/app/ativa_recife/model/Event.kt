package app.ativa_recife.model

import com.google.android.gms.maps.model.LatLng
import java.util.Date
import app.ativa_recife.model.User

data class Event(
    var title : String,
    var address: Address,
    var data: Date,
    var startTime: Date, //corrigir esses dois dates
    var manager: User,
    var sizeRoute: String,
    var startingLocation: LatLng //Latitude e longitude do endereço
)