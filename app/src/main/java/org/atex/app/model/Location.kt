package org.atex.app.model


open class Location() {
    var latitude: Double = 0.0
    var longitude: Double = 0.0
    var address: String = ""
    constructor(latitude:Double,longitude:Double,address:String) : this() {
        this.latitude=latitude
        this.longitude=longitude
        this.address=address
    }
}