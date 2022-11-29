package uz.uicgroup.presentation.screens.about.user_policy.viewmodel

import androidx.lifecycle.LiveData

interface UserPolicyViewModel {
    val backLiveData:LiveData<Unit>
    fun backScreen()
}