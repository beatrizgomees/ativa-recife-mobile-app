package app.ativa_recife.model

data class Address(
    var street: String = "",
    var district: String = "",
    var number: Int = 0,
    var city: String = "",
    var state: String = ""
)
