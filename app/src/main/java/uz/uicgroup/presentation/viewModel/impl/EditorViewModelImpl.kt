package uz.uicgroup.presentation.viewModel.impl

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.uicgroup.presentation.viewModel.EditorViewModel
import javax.inject.Inject

@HiltViewModel
class EditorViewModelImpl @Inject constructor() : ViewModel(), EditorViewModel {
}