package uz.uicgroup.presentation.screen.edit

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import uz.uicgroup.R
import uz.uicgroup.databinding.ScreenEditorBinding
import uz.uicgroup.presentation.screen.edit.adapter.PagerAdapter
import uz.uicgroup.presentation.screen.edit.pager.viewmodel.EditorViewModel
import uz.uicgroup.presentation.screen.edit.pager.viewmodel.impl.EditorViewModelImpl
import uz.uicgroup.utils.extension.hideKeyboard

@AndroidEntryPoint
class EditScreen : Fragment(R.layout.screen_editor) {
    private val viewBinding by viewBinding(ScreenEditorBinding::bind)
    private val viewModel: EditorViewModel by viewModels<EditorViewModelImpl>()

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(viewBinding) {
        super.onViewCreated(view, savedInstanceState)
        val mainAdapter = PagerAdapter(childFragmentManager, lifecycle)
        viewPagerScreen.adapter = mainAdapter
        TabLayoutMediator(tabLayout, viewPagerScreen) { tab, position ->
            when (position) {
                0 -> {
                    tab.setText(R.string.editor_tab_tx)
                }
                else -> {
                    tab.setText(R.string.dictionary_tab_tx)
                }
            }
        }.attach()

        appInfo.setOnClickListener {
            hideKeyboard()
            findNavController().navigate(R.id.action_editorScreen_to_aboutScreen)
        }
    }
}