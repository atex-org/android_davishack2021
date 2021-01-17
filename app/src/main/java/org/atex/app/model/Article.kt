package org.atex.app.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import org.bson.types.ObjectId


open class Article(
    @PrimaryKey var _id: ObjectId? = null,
    var atex_key: String = "",
    var description: String? = null,
    var detail: String? = null,
    var thumbnailUrl: String? = null,
    var title: String? = null
) : RealmObject() {}