package app.ativa_recife.db

import app.ativa_recife.model.User

class FBUser {
    var name : String ? = null
    var email : String? = null
    var password: String? = null
    fun toUser() = User(name!!, email!!, password!!)
}
fun User.toFBUser() : FBUser {
    val fbUser = FBUser()
    fbUser.name = this.name
    fbUser.email = this.email
    return fbUser
}