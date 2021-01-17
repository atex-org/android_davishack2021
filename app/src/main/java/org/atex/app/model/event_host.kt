package org.atex.app.model

import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass(embedded = true)
open class event_host(
    var id: String? = null,
    var name: String? = null,
    var username: String? = null
): RealmObject() {}