package uz.uicgroup.presentation.screen.about.user_policy

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.uicgroup.R
import uz.uicgroup.databinding.ScreenUserPolicyBinding
import uz.uicgroup.presentation.screen.about.user_policy.viewmodel.UserPolicyViewModel
import uz.uicgroup.presentation.screen.about.user_policy.viewmodel.impl.UserPolicyViewModelImpl
import uz.uicgroup.utils.extension.myApply

@AndroidEntryPoint
class UserPolicyScreen : Fragment(R.layout.screen_user_policy) {
    private val binding by viewBinding(ScreenUserPolicyBinding::bind)
    private val viewModel: UserPolicyViewModel by viewModels<UserPolicyViewModelImpl>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.backLiveData.observe(this@UserPolicyScreen, backLiveDataObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.myApply {
        super.onViewCreated(view, savedInstanceState)
        backBtn.setOnClickListener {
            viewModel.backScreen()
        }
    }


    private val backLiveDataObserver = Observer<Unit> {
        findNavController().navigateUp()
    }
}