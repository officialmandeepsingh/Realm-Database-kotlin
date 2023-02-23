package com.mandeep.realmlearn.data.client

import com.mandeep.realmlearn.data.model.Item
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

/**
 * App Name: Realm learn
 * Package: com.mandeep.realmlearn
 * By: Mandeep Singh
 * Email Id: officialmandeepsp@gmail.com
 * Date: Mon 20 Feb, 2023
 **/
object RealmeClient {
    val config = RealmConfiguration.create(schema = setOf(Item::class))
    val realm: Realm = Realm.open(config)
}