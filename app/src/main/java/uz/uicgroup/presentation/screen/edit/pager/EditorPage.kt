package uz.uicgroup.presentation.screen.edit.pager

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import uz.uicgroup.R
import uz.uicgroup.databinding.PagerEditorBinding
import uz.uicgroup.presentation.viewModel.EditorViewModel
import uz.uicgroup.presentation.viewModel.impl.EditorViewModelImpl
import uz.uicgroup.utils.extension.*


@AndroidEntryPoint
class EditorPage : Fragment(R.layout.pager_editor) {
    private val viewBinding by viewBinding(PagerEditorBinding::bind)
    private val viewModel: EditorViewModel by viewModels<EditorViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(viewBinding) {
        super.onViewCreated(view, savedInstanceState)
        clickFun()
        onEachFlow()
        textLength(etMessageBox, currentCount)
    }

    private fun clickFun() = with(viewBinding) {
        btnLatin.setOnClickListener {
            viewModel.getLatin(etMessageBox.values())
        }
        btnCyrill.setOnClickListener {
            viewModel.getCyrillic(etMessageBox.values())
        }
        clearBtn.setOnClickListener {
            if (etMessageBox.text?.isEmpty() == true) {
                clearBtn.showTooltipTop(
                    getString(R.string.clear_text_info_1)
                )
            } else {
                etMessageBox.text?.clear()
                clearBtn.showTooltipTop(
                    getString(R.string.clear_text_info_2)
                )
            }
        }
        copyText.setOnClickListener {
            if (etMessageBox.text?.isEmpty() == true) {
                copyText.showTooltipStart(
                    getString(R.string.clear_text_info_1)
                )
            } else {
                copyText.showTooltipStart(
                    getString(R.string.copy_text_info_1)
                )
                etMessageBox.text?.let { it1 -> requireContext().copyToClipboard(it1) }
            }
        }
        checkButton.setOnClickListener {
            val charStr = etMessageBox.values().split(' ')
            viewModel.getCorrect(charStr)
        }
    }

    private fun onEachFlow() = with(viewBinding) {
        viewModel.words.onEach {
            etMessageBox.setText(it.data)
            viewModel.savePosition(etMessageBox)
        }.launchIn(lifecycleScope)
        viewModel.showMassageFlow.onEach {
            etMessageBox.showTooltipTop(
                it.message.toString()
            )
        }.launchIn(lifecycleScope)

        viewModel.errorFlow.onEach {
            showToast(it.data.toString())
        }.launchIn(lifecycleScope)

        viewModel.corrects.onEach {
            val charStr = etMessageBox.values().split(' ')
            etMessageBox.values().paintResult(etMessageBox.values())
            val responseCharts = it.data!!.data
            val str = java.lang.StringBuilder("")
            for (i in responseCharts.indices) {
                if (charStr[i] == responseCharts[i]) {
                }
            }
            etMessageBox.setText(etMessageBox.values().paintResult(etMessageBox.values()))
        }.launchIn(lifecycleScope)
    }

    private fun textLength(box: EditText, view: TextView) {
        box.addListener {
            val count = it.length
            view.text = count.toString()
            if (count != 1000) {
                view.text = count.toString()
                viewBinding
                    .currentCount
                    .setTextColor(Color.GRAY)
            } else {
                viewBinding
                    .currentCount
                    .setTextColor(Color.RED)
                viewBinding.textCountItem.showTooltipTop(
                    getString(R.string.count_text_info),
                    R.color.tool_tip_bg_danger_color,
                    0.3f,
                    R.color.tool_tip_danger_text_color
                )
                return@addListener
            }
        }
    }

    private fun Context.copyToClipboard(text: CharSequence) {
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("text", text)
        clipboard.setPrimaryClip(clip)
    }
}