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
    var startingLocation: LatLng, //Latitude e longitude do endereço
    var id : String = ""
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true // Verifica se são o mesmo objeto na memória
        if (other !is Event) return false // Verifica se o tipo é diferente

        return this.id == other.id
    }

    // Sobrescreve o método hashCode para ser consistente com equals
    override fun hashCode(): Int {
        return id.hashCode() // Usa apenas o 'id' no hashCode
    }
}