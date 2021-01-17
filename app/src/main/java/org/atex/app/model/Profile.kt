package org.atex.app.model

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey
import org.bson.types.ObjectId;

open class Profile(
    @PrimaryKey var _id: ObjectId? = null,
    var address: String? = null,
    var atex_key: String = "",
    var email: String? = null,
    var name: String? = null,
    var photoUrl: String? = null
) : RealmObject() {}