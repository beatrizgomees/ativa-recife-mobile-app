package app.ativa_recife.model

data class User (
    var name: String,
    var email: String,
    var password: String,
    var address: Address,
    var registredEvents: List<Event>,
    var publicizedEvents: List<Event>

)