package uz.uicgroup.presentation.screen.edit

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import uz.uicgroup.presentation.viewModel.SuggestionViewModel
import uz.uicgroup.presentation.viewModel.impl.SuggestionViewModelImpl

@AndroidEntryPoint
class SuggestionScreen : Fragment() {
    private val viewModel: SuggestionViewModel by viewModels<SuggestionViewModelImpl>()
}