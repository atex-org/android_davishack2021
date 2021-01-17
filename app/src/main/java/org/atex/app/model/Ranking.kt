package org.atex.app.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import org.bson.types.ObjectId


open class Ranking(
    @PrimaryKey var _id: ObjectId? = null,
    var __v: Long? = null,
    var atex_key: String = "",
    var name: String? = null,
    var photoUrl: String? = null,
    var point: String? = null,
    var user_id: String? = null
): RealmObject() {}