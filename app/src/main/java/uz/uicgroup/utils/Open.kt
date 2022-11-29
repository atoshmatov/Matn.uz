package uz.uicgroup.utils

import androidx.lifecycle.MutableLiveData

object Open {
    var notInternet = MutableLiveData<Unit>()
    var openScreen = MutableLiveData<Unit>()
}