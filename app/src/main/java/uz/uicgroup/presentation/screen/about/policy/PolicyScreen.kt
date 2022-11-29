package uz.uicgroup.presentation.screen.about.policy

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.uicgroup.R
import uz.uicgroup.databinding.ScreenPolicyBinding
import uz.uicgroup.presentation.screen.about.policy.viewmodel.PolicyViewModel
import uz.uicgroup.presentation.screen.about.policy.viewmodel.impl.PolicyViewModelImpl
import uz.uicgroup.utils.Open
import uz.uicgroup.utils.extension.myApply

@AndroidEntryPoint
class PolicyScreen : Fragment(R.layout.screen_policy) {
    private val viewModel: PolicyViewModel by viewModels<PolicyViewModelImpl>()
    private val binding by viewBinding(ScreenPolicyBinding::bind)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.backLiveData.observe(this@PolicyScreen, backLiveDataObserver)
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