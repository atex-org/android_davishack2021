package org.atex.app.model

import io.realm.RealmObject


open class Article  : RealmObject() {
    var _id = 0
    var title: String? = ""
    var description: String? = ""
    var thumbnailUrl: String? = ""
    var detail: String? = ""
}
