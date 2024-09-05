package app.ativa_recife.db.fb


import app.ativa_recife.model.User
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
class FBUser(
    var name: String = "",
    var address: FBAddress = FBAddress(),
    var registredEvents: List<FBEvent> = emptyList(),
    var publicizedEvents: List<FBEvent> = emptyList()
) {
    // Construtor para converter User para FBUser
    constructor(user: app.ativa_recife.model.User) : this(
        name = user.name,
        address = user.address?.let { FBAddress(it) } ?: FBAddress(),  // Usa valor padrão se address for null
        registredEvents = user.registredEvents?.map { FBEvent(it) } ?: emptyList(),  // Usa lista vazia se for null
        publicizedEvents = user.publicizedEvents?.map { FBEvent(it) } ?: emptyList()  // Usa lista vazia se for null
    )

    // Método para converter FBUser de volta para User
    @Exclude
    fun toUser(): User {
        return app.ativa_recife.model.User(
            name = name,
            address = address.toAddress(),
            registredEvents = registredEvents.map { it.toEvent() },
            publicizedEvents = publicizedEvents.map {it.toEvent() }
        )
    }
}
