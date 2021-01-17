package org.atex.app.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import org.bson.types.ObjectId

open class User(
    @PrimaryKey var _id: ObjectId? = null,
    var __v: Long? = null,
    var atex_key: String = "",
    var email: String? = null,
    var fullName: String? = null,
    var fullname: String? = null,
    var password: String? = null
) : RealmObject() {}

