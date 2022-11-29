package uz.uicgroup.presentation.screen.notConnection

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.uicgroup.R
import uz.uicgroup.databinding.ScreenNetworkErrorBinding
import uz.uicgroup.presentation.screen.edit.dialog.NetWorkDialog
import uz.uicgroup.utils.Open
import uz.uicgroup.utils.extension.showToast
import uz.uicgroup.utils.internetConnection.isConnected

@AndroidEntryPoint
class NetworkErrorScreen : Fragment(R.layout.screen_network_error) {
    private val binding by viewBinding(ScreenNetworkErrorBinding::bind)
    private val viewModel: NetWorkErrorViewModelImpl by viewModels<NetWorkErrorViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.spStartBt.setOnClickListener {
            if (isConnected()){
                findNavController().navigate(R.id.action_networkErrorScreen_to_editorScreen)
            }else{
                showDialog(true)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Open.openScreen.observe(this@NetworkErrorScreen, openScreenObserver)
    }

    private val openScreenObserver = Observer<Unit> {
        findNavController().navigate(R.id.action_networkErrorScreen_to_editorScreen)
    }

    private fun showDialog(isNetwork: Boolean) {
        val dialog = NetWorkDialog(isNetwork)
        dialog.show(childFragmentManager, "")
    }
}