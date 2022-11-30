package uz.uicgroup.presentation.screens.edit.dialog

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.uicgroup.R
import uz.uicgroup.databinding.InternetDialogBinding
import uz.uicgroup.utils.extension.myApply

class NetWorkDialog :
    DialogFragment(R.layout.internet_dialog) {

    private val binding by viewBinding(InternetDialogBinding::bind)

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
        btnTryAgain.setOnClickListener {
            dismiss()
        }
    }
}