package uz.uicgroup.presentation.screen.edit.pager

import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.uicgroup.R
import uz.uicgroup.databinding.PagerEditorBinding

@AndroidEntryPoint
class EditPage:Fragment(R.layout.pager_editor) {
    private val viewBinding by viewBinding(PagerEditorBinding::bind)
}