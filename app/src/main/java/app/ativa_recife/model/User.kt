package app.ativa_recife.model

data class User (
    var name: String,
    var address: Address = Address(), //Vai ser utilizado depois,
    var registredEvents: List<Event> ? = null,
    var publicizedEvents: List<Event> ? = null
)