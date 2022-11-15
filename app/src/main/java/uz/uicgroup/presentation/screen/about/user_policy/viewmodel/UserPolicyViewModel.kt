package uz.uicgroup.presentation.screen.about.user_policy.viewmodel

import androidx.lifecycle.LiveData

interface UserPolicyViewModel {
    val backLiveData:LiveData<Unit>
    fun backScreen()
}