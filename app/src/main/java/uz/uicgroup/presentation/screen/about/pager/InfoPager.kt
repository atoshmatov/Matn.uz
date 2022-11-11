package uz.uicgroup.presentation.screen.about.pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import uz.uicgroup.presentation.screen.about.pager.viewModel.InfoViewModel
import uz.uicgroup.presentation.screen.about.pager.viewModel.impl.InfoViewModelImpl

@AndroidEntryPoint
class InfoPager : Fragment() {
    private val viewModel: InfoViewModel by viewModels<InfoViewModelImpl>()
}