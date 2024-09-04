package app.ativa_recife.model

import java.util.Date
import com.google.android.gms.maps.model.LatLng

data class Event (
    var address: Address,
    var data: Date,
    var startTime: Date,
    var manager: String,
    var route: LatLng,
    var sizeRoute: String,


)