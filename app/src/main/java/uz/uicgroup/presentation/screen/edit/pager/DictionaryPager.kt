package uz.uicgroup.presentation.screen.edit.pager

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.uicgroup.R
import uz.uicgroup.databinding.PagerDictionaryBinding
import uz.uicgroup.presentation.screen.edit.adapter.DictionaryAdapter
import uz.uicgroup.presentation.screen.edit.pager.viewmodel.DictionaryViewModel
import uz.uicgroup.presentation.screen.edit.pager.viewmodel.impl.DictionaryViewModelImpl
import uz.uicgroup.utils.extension.hideKeyboard
import uz.uicgroup.utils.extension.myApply

@AndroidEntryPoint
class DictionaryPager : Fragment(R.layout.pager_dictionary), SearchView.OnQueryTextListener {

    private val binding by viewBinding(PagerDictionaryBinding::bind)
    private val viewModel: DictionaryViewModel by viewModels<DictionaryViewModelImpl>()
    private val adapter: DictionaryAdapter by lazy(mode = LazyThreadSafetyMode.NONE) {
        DictionaryAdapter(
            requireContext()
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.myApply {
        super.onViewCreated(view, savedInstanceState)

        listDic.adapter = adapter
        listDic.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)


        searchView.setOnQueryTextListener(this@DictionaryPager)

        searchView.setOnCloseListener {
            hideKeyboard()
            return@setOnCloseListener true
        }

        viewModel.searchList.onEach {
            adapter.submitList(it.data)
        }.launchIn(lifecycleScope)

        viewModel.searchQueryFlow.onEach {
            it.let { adapter.query = it }
        }.launchIn(lifecycleScope)

        viewModel.showLoadingFlow.onEach {
            listEmpty.isVisible = it.data!!
        }.launchIn(lifecycleScope)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        searchWord(query)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        searchWord(newText)
        return true
    }

    private fun searchWord(text: String?) {
        if (text != null) {
            viewModel.getSearchWord(text)
            viewModel.onSearch(text)
        }
    }
}