package uz.uicgroup.presentation.screen.about.pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.uicgroup.R
import uz.uicgroup.databinding.PagerInfoBinding
import uz.uicgroup.presentation.screen.about.pager.viewModel.InfoViewModel
import uz.uicgroup.presentation.screen.about.pager.viewModel.impl.InfoViewModelImpl

@AndroidEntryPoint
class InfoPager : Fragment(R.layout.pager_info) {
    private val viewModel: InfoViewModel by viewModels<InfoViewModelImpl>()
    private val binding by viewBinding(PagerInfoBinding::bind)
}