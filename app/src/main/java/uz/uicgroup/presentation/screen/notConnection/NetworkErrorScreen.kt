package uz.uicgroup.presentation.screen.notConnection

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.uicgroup.R
import uz.uicgroup.databinding.ScreenNetworkErrorBinding

@AndroidEntryPoint
class NetworkErrorScreen : Fragment(R.layout.screen_network_error) {
    private val binding by viewBinding(ScreenNetworkErrorBinding::bind)
    private val viewModel: NetWorkErrorViewModelImpl by viewModels<NetWorkErrorViewModelImpl>()
}