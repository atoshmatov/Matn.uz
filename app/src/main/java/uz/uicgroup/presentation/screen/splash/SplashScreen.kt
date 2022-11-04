package uz.uicgroup.presentation.screen.splash

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.uicgroup.R
import uz.uicgroup.databinding.ScreenSplaashBinding
import uz.uicgroup.presentation.viewModel.SplashViewModel
import uz.uicgroup.presentation.viewModel.impl.SplashViewModelImpl
import uz.uicgroup.utils.Open


@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
class SplashScreen : Fragment(R.layout.screen_splaash) {
    private val viewBinding by viewBinding(ScreenSplaashBinding::bind)
    private val viewModel: SplashViewModel by viewModels<SplashViewModelImpl>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Open.notInternet.observe(viewLifecycleOwner, notInternetObserver)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.openNextScreenFLow.onEach {
            findNavController().navigate(R.id.action_splashScreen_to_editorScreen)
        }.launchIn(lifecycleScope)
    }


    private val notInternetObserver = Observer<Boolean> {
    }


}