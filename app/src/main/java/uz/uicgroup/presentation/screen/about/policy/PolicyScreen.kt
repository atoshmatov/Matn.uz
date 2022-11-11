package uz.uicgroup.presentation.screen.about.policy

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PolicyScreen : Fragment() {
    private val viewModel: PolicyViewModel by viewModels<PolicyViewModelImpl>()
}