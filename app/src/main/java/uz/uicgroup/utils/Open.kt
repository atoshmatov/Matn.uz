package uz.uicgroup.utils

import androidx.lifecycle.MutableLiveData

object Open {
    val openScreen = MutableLiveData<Unit>()
    val openNoConnectionScreen = MutableLiveData<Unit>()
    val notInternet = MutableLiveData<Boolean>()
}