package uz.uicgroup.presentation.screen.edit.pager

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.uicgroup.R
import uz.uicgroup.data.remote.request.LatinRequest
import uz.uicgroup.databinding.PagerEditorBinding
import uz.uicgroup.presentation.viewModel.EditorViewModel
import uz.uicgroup.presentation.viewModel.impl.EditorViewModelImpl
import uz.uicgroup.utils.extension.values

@AndroidEntryPoint
class EditorPage : Fragment(R.layout.pager_editor) {
    private val viewBinding by viewBinding(PagerEditorBinding::bind)
    private val viewModel: EditorViewModel by viewModels<EditorViewModelImpl>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(viewBinding) {
        super.onViewCreated(view, savedInstanceState)
        btn1.setOnClickListener {
            viewModel.getLatin(LatinRequest(etMessageBox.values()))
        }
        btn2.setOnClickListener {
            viewModel.getCyrillic(LatinRequest(etMessageBox.values()))
        }

        viewModel._text.onEach {
            etMessageBox.setText(it)
        }.launchIn(lifecycleScope)

        return@with
    }
}