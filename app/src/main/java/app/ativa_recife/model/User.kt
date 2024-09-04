package app.ativa_recife.model

data class User (
    var name: String,
    var email: String,
    var password: String,
    var address: Address? = null,
    var registredEvents: List<Event>? = null,
    var publicizedEvents: List<Event>? = null

)