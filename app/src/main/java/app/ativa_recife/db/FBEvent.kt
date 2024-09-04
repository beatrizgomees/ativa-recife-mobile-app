package app.ativa_recife.db

import android.provider.Telephony.Mms.Addr
import app.ativa_recife.model.Address
import app.ativa_recife.model.Event
import com.google.android.gms.maps.model.LatLng
import java.util.Date

class FBEvent {
    var name : String? = null
    var lat : Double? = null
    var lng : Double? = null
    var date: Date? = null
    var startTime: Date? = null
    var manager: String? = null
    var sizeRoute: String? = null
    var address: Address? = null

    fun toEvent(): Event {
        val latlng = LatLng(lat ?: 0.0, lng ?: 0.0)
        return Event(
            address = address,
            name = name!!,
            route = latlng,
            date = date!!,
            startTime = startTime!!,
            manager = manager!!,
            sizeRoute = sizeRoute!!,
            )
    }
}
fun Event.toFBEvent() : FBEvent{
    val fbEvent = FBEvent()
    fbEvent.name = this.name
    fbEvent.date = this.date
    fbEvent.address = this.address
    fbEvent.manager = this.manager
    fbEvent.startTime = startTime
    fbEvent.lat = this.route.latitude ?: 0.0
    fbEvent.lng = this.route.longitude ?: 0.0
    return fbEvent
}