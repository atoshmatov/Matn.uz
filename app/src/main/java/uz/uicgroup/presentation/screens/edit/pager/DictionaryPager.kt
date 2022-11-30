package uz.uicgroup.presentation.screens.edit.pager

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import uz.uicgroup.R
import uz.uicgroup.databinding.PagerDictionaryBinding
import uz.uicgroup.domain.models.WordData
import uz.uicgroup.presentation.screens.edit.adapter.DictionaryAdapter
import uz.uicgroup.presentation.screens.edit.dialog.BottomDialog
import uz.uicgroup.presentation.screens.edit.dialog.DeleteDialog
import uz.uicgroup.presentation.screens.edit.pager.viewmodel.DictionaryViewModel
import uz.uicgroup.presentation.screens.edit.pager.viewmodel.impl.DictionaryViewModelImpl
import uz.uicgroup.utils.extension.*


@AndroidEntryPoint
class DictionaryPager : Fragment(R.layout.pager_dictionary), SearchView.OnQueryTextListener {

    private val binding by viewBinding(PagerDictionaryBinding::bind)

    private val viewModel: DictionaryViewModel by viewModels<DictionaryViewModelImpl>()

    private val adapter: DictionaryAdapter by lazy(mode = LazyThreadSafetyMode.NONE) {
        DictionaryAdapter(requireContext(), lifecycleScope)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        setUpUi()

        viewModel.getHistory()

        searchView.setOnQueryTextListener(this@DictionaryPager)

        searchView.setOnCloseListener {
            hideKeyboard()
            return@setOnCloseListener true
        }

        historyImage2.setOnClickListener {
            val dialog = DeleteDialog()
            dialog.setOkListener {
                viewModel.delete()
                viewModel.getHistory()
            }
            dialog.show(childFragmentManager, "")
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel
                    .showLoadingFlow
                    .collect { isLoading ->
                        historyText.isVisible = !isLoading
                        historyImage2.isVisible = !isLoading
                    }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel
                    .searchList
                    .collect {
                        adapter.submitList(it.data)
                        adapter.click = viewModel.historyAndApiClickListener
                    }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel
                    .searchQueryFlow
                    .collect {
                        it.let { adapter.query = it }
                    }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel
                    .wordsFlow
                    .collect {
                        hideKeyboard()
                        val dialog = BottomDialog(it.data!!)
                        dialog.show(childFragmentManager, "")
                        viewModel
                            .addWordHistory(
                                WordData(
                                    it.data.id, it.data.word, it.data.syllable
                                )
                            )
                    }
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.showImageEmptyFlow
                    .collect {
                        imageEmpty.isVisible = it
                    }
            }

        }
        return@with
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
            viewModel.historyAndApiClickListener = false
            viewModel.getSearchWord(text)
            viewModel.onSearch(text)
        }
        if (text!!.isEmpty()) {
            viewModel.getHistory()
        }
    }

    private fun setUpUi() = binding.myApply {
        listDic.adapter = adapter
        listDic.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)

        adapter.setOnItemClickListener { itemById ->
            hideKeyboard()
            if (viewModel.historyAndApiClickListener) viewModel.getById(itemById)
            else viewModel.getWords(itemById)
        }
    }
}