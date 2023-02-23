package com.mandeep.realmlearn.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mandeep.realmlearn.data.client.RealmeClient
import com.mandeep.realmlearn.data.model.Item
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * App Name: Realm learn
 * Package: com.mandeep.realmlearn
 * By: Mandeep Singh
 * Email Id: officialmandeepsp@gmail.com
 * Date: Mon 20 Feb, 2023
 **/
class RealViewModel : ViewModel() {

    private var _items: MutableLiveData<RealmResults<Item>> = MutableLiveData()
    val items: MutableLiveData<RealmResults<Item>>
        get() = _items

    private var _item: MutableLiveData<Item> = MutableLiveData()
    val item: MutableLiveData<Item>
        get() = _item

    fun addItem(payload: Item) {
        viewModelScope.launch(Dispatchers.IO) {
            RealmeClient.realm.writeBlocking {
                Log.d(
                    "TAG",
                    "addItem called ID : ${payload._id}, isComplete: ${payload.isComplete}, Summary: ${payload.summary} and Owner Id: ${payload.owner_id}"
                )
                _item?.postValue(copyToRealm(payload))
            }
        }
    }

    fun getAllItem() {
        viewModelScope.launch(Dispatchers.IO) {
            val resultItems: RealmResults<Item> = RealmeClient.realm.query<Item>().find()
            _items?.postValue(resultItems)
        }
    }

    fun deleteItem(item: RealmResults<Item>) {
        viewModelScope.launch(Dispatchers.IO) {
            RealmeClient.realm.writeBlocking {
                val frozenObj = query<Item>().find()
                findLatest(item[0]).let { it?.let { it1 -> delete(it1) } }
                delete(item.first())
            }
        }
    }

    fun updateItem(item: Item) {
        viewModelScope.launch(Dispatchers.IO) {
            RealmeClient.realm.writeBlocking {
                findLatest(item)?.isComplete = true
            }
        }
    }

}