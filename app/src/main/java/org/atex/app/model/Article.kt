package org.atex.app.model

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey
import java.util.Date;
import org.bson.types.ObjectId;

open class article(
    @PrimaryKey var _id: ObjectId? = null,
    var atex_key: String = "",
    var date: Date? = null,
    var description: String? = null,
    var detail: String? = null,
    var thumbnailUrl: String? = null,
    var title: String? = null
) : RealmObject() {}