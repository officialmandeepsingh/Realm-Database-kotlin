package com.mandeep.realmlearn.data.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId


/**
 * App Name: Realm learn
 * Package: com.mandeep.realmlearn
 * By: Mandeep Singh
 * Email Id: officialmandeepsp@gmail.com
 * Date: Mon 20 Feb, 2023
 **/
class Item() : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var isComplete: Boolean = false
    var summary: String = ""
    var owner_id: String = ""

    constructor(ownerId: String = "") : this() {
        owner_id = ownerId
    }

    override fun toString(): String {
        return super.toString()
    }
}

