package org.atex.app.model

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import org.bson.types.ObjectId
import java.util.*

open class Event(
    @PrimaryKey var _id: ObjectId? = null,
    var __v: Long? = null,
    var atex_key: String = "",
    var credit: Long? = null,
    var date_time: Date? = null,
    var detail: String? = null,
    var eventname: String? = null,
    var host: RealmList<event_host> = RealmList(),
    var location: event_location? = null,
    var rating: String? = null,
    var thumbnailUrl: String? = null,
    var title: String? = null
): RealmObject() {}


