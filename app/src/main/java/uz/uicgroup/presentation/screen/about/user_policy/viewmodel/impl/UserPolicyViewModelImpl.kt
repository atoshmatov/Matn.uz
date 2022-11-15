package uz.uicgroup.presentation.screen.about.user_policy.viewmodel.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.uicgroup.presentation.screen.about.user_policy.viewmodel.UserPolicyViewModel
import javax.inject.Inject

@HiltViewModel
class UserPolicyViewModelImpl @Inject constructor() : ViewModel(), UserPolicyViewModel {
    override val backLiveData = MutableLiveData<Unit>()

    override fun backScreen() {
        backLiveData.value = Unit
    }
}