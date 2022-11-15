package uz.uicgroup.presentation.screen.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.uicgroup.R
import uz.uicgroup.databinding.ScreenMainBinding
import uz.uicgroup.presentation.screen.main.viewmodel.MainViewModel
import uz.uicgroup.presentation.screen.main.viewmodel.impl.MainViewModelImpl


@AndroidEntryPoint
class MainScreen : Fragment(R.layout.screen_main) {
    private val viewBinding by viewBinding(ScreenMainBinding::bind)
    private val viewModel: MainViewModel by viewModels<MainViewModelImpl>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(viewBinding) {
        super.onViewCreated(view, savedInstanceState)
        spStartBt.setOnClickListener {
            viewModel.openEditorScreen()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.openNextScreenFLow.onEach {
            findNavController().navigate(R.id.action_mainScreen_to_editorScreen)
        }.launchIn(lifecycleScope)

        viewModel.openNoErrorScreenFLow.onEach {
            findNavController().navigate(R.id.action_mainScreen_to_networkErrorScreen)
        }.launchIn(lifecycleScope)
    }
}