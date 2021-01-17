package org.atex.app.model

import io.realm.RealmObject

open class User : RealmObject() {
    var _id = 0
    var name: String? = null
    var username: String? = null
    var password: String? = null
    var email: String? = null
}
