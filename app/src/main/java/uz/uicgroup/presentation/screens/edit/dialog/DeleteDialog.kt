package uz.uicgroup.presentation.screens.edit.dialog

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.uicgroup.R
import uz.uicgroup.databinding.DeleteDialogBinding
import uz.uicgroup.utils.extension.myApply

class DeleteDialog :
    DialogFragment(R.layout.delete_dialog) {
    private var okListener: (() -> Unit)? = null
    private val binding by viewBinding(DeleteDialogBinding::bind)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.DialogTheme_transparent2)
    }

    override fun getTheme(): Int {
        return R.style.DialogTheme_transparent2
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) = binding.myApply {
        btnTryAgain2.setOnClickListener {
            okListener!!.invoke()
            dismiss()
        }

        btnTryAgain1.setOnClickListener {
            dismiss()
        }
    }

    fun setOkListener(block: () -> Unit) {
        okListener = block
    }
}