package uz.uicgroup.presentation.screens.edit

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.uicgroup.R
import uz.uicgroup.databinding.ScreenSuggestionBinding
import uz.uicgroup.presentation.screens.edit.viewModel.SuggestionViewModel
import uz.uicgroup.presentation.screens.edit.viewModel.impl.SuggestionViewModelImpl

@AndroidEntryPoint
class SuggestionScreen : Fragment(R.layout.screen_suggestion) {
    private val binding by viewBinding(ScreenSuggestionBinding::bind)
    private val viewModel: SuggestionViewModel by viewModels<SuggestionViewModelImpl>()
}