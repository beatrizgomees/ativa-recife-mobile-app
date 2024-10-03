package app.ativa_recife.model

data class User (
    var name: String,
    var address: Address = Address(), //Vai ser utilizado depois,
)