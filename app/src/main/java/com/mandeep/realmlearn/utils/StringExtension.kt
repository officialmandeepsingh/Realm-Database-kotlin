package com.mandeep.realmlearn.utils

import android.text.Editable

/**
 * App Name: Realm learn
 * Package: com.mandeep.realmlearn
 * By: Mandeep Singh
 * Email Id: officialmandeepsp@gmail.com
 * Date: Tue 21 Feb, 2023
 **/

fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)