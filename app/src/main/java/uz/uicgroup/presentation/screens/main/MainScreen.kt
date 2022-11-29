package uz.uicgroup.presentation.screens.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import uz.uicgroup.R
import uz.uicgroup.data.local.SharedPref
import uz.uicgroup.databinding.ScreenMainBinding
import uz.uicgroup.presentation.screens.main.viewmodel.MainViewModel
import uz.uicgroup.presentation.screens.main.viewmodel.impl.MainViewModelImpl
import uz.uicgroup.utils.Open
import uz.uicgroup.utils.extension.gone
import uz.uicgroup.utils.extension.myApply
import uz.uicgroup.utils.extension.visible


@AndroidEntryPoint
class MainScreen : Fragment(R.layout.screen_main) {

    lateinit var sharedPref: SharedPref
    private val viewBinding by viewBinding(ScreenMainBinding::bind)
    private val viewModel: MainViewModel by viewModels<MainViewModelImpl>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Open.notInternet.observe(this@MainScreen, notInternetObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = viewBinding.myApply {
        super.onViewCreated(view, savedInstanceState)
        sharedPref = SharedPref(requireContext())

        if (sharedPref.stateBtn) {
            spStartBt.gone()
        } else {
            spStartBt.visible()
        }
        spStartBt.setOnClickListener {
            viewModel.openEditorScreen()
        }
        lifecycleScope.launch {
            viewModel.openNextScreenFLow.collect {
                findNavController().navigate(R.id.action_mainScreen_to_editorScreen)
            }
        }

        lifecycleScope.launch {
            viewModel.openNoErrorScreenFLow.collect {
                findNavController().navigate(R.id.action_mainScreen_to_networkErrorScreen)
            }
        }
    }

    private val notInternetObserver = Observer<Unit> {
        findNavController().navigate(R.id.action_mainScreen_to_networkErrorScreen)
    }
}