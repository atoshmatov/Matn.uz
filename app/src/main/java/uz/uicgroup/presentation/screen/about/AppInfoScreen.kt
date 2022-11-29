package uz.uicgroup.presentation.screen.about

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import uz.uicgroup.R
import uz.uicgroup.databinding.ScreenAppinfoBinding
import uz.uicgroup.presentation.screen.about.viewModel.AppInfoViewModel
import uz.uicgroup.presentation.screen.about.viewModel.impl.AppInfoViewModelImpl
import uz.uicgroup.presentation.screen.edit.adapter.PagerAdapter
import uz.uicgroup.utils.Open
import uz.uicgroup.utils.extension.myApply

@AndroidEntryPoint
class AppInfoScreen : Fragment(R.layout.screen_appinfo) {
    private val viewBinding by viewBinding(ScreenAppinfoBinding::bind)
    private val viewModel: AppInfoViewModel by viewModels<AppInfoViewModelImpl>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.backLiveData.observe(this@AppInfoScreen, backLiveDataObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = viewBinding.myApply {
        super.onViewCreated(view, savedInstanceState)
        val mainAdapter = PagerAboutAdapter(childFragmentManager, lifecycle)
        viewPagerScreen.adapter = mainAdapter
        TabLayoutMediator(tabLayout, viewPagerScreen) { tab, position ->
            when (position) {
                0 -> {
                    tab.setText(R.string.loyiha_haqida)
                }
                else -> {
                    tab.setText(R.string.loyiha_ishtirokchilari)
                }
            }
        }.attach()

        backBtn.setOnClickListener {
            viewModel.backScreen()
        }
    }

    private val backLiveDataObserver = Observer<Unit> {
        findNavController().navigateUp()
    }
}