package uz.uicgroup.presentation.screen.edit.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.uicgroup.R
import uz.uicgroup.databinding.CardItemBottomBinding
import uz.uicgroup.domain.model.WordData
import uz.uicgroup.utils.extension.myApply

@AndroidEntryPoint
class BottomDialog(private val _word: WordData) : DialogFragment(R.layout.card_item_bottom) {
    private val binding by viewBinding(CardItemBottomBinding::bind)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.DialogTheme_transparent)
    }

    override fun getTheme(): Int {
        return R.style.DialogTheme_transparent
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) = binding.myApply {
        super.onViewCreated(view, savedInstanceState)
        word.text = _word.word
        syllables.text = _word.syllable
        isCancelable = true
    }
}