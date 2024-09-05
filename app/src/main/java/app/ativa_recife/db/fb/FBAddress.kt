package app.ativa_recife.db.fb

import app.ativa_recife.model.Address
import com.google.firebase.firestore.Exclude

data class FBAddress(
    var street: String = "",
    var district: String = "",
    var number: Int = 0,
    var city: String = "",
    var state: String = ""
) {
    constructor(address: Address) : this(
        street = address.street,
        district = address.district,
        number = address.number,
        city = address.city,
        state = address.state
    )

    @Exclude
    fun toAddress(): Address {
        return Address(
            street = street,
            district = district,
            number = number,
            city = city,
            state = state
        )
    }
}