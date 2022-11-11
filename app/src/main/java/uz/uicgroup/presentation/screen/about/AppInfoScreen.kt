package uz.uicgroup.presentation.screen.about

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.uicgroup.R
import uz.uicgroup.databinding.ScreenAppinfoBinding
import uz.uicgroup.presentation.screen.about.viewModel.AppInfoViewModel
import uz.uicgroup.presentation.screen.about.viewModel.impl.AppInfoViewModelImpl

@AndroidEntryPoint
class AppInfoScreen : Fragment(R.layout.screen_appinfo) {
    private val viewBinding by viewBinding(ScreenAppinfoBinding::bind)
    private val viewModel: AppInfoViewModel by viewModels<AppInfoViewModelImpl>()
}