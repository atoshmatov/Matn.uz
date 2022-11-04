package uz.uicgroup.presentation.screen.edit

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.uicgroup.R
import uz.uicgroup.databinding.ScreenEditorBinding
import uz.uicgroup.presentation.viewModel.EditorViewModel
import uz.uicgroup.presentation.viewModel.impl.EditorViewModelImpl

@AndroidEntryPoint
class EditorScreen : Fragment(R.layout.screen_editor) {
    private val viewBinding by viewBinding(ScreenEditorBinding::bind)
    private val viewModel: EditorViewModel by viewModels<EditorViewModelImpl>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}