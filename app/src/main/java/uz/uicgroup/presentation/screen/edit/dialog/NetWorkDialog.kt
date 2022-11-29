package uz.uicgroup.presentation.screen.edit.dialog

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.uicgroup.R
import uz.uicgroup.databinding.InternetDialogBinding
import uz.uicgroup.utils.extension.myApply
import uz.uicgroup.utils.extension.visible

class NetWorkDialog(
    private val isNetwork: Boolean
) :
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
        /* if (isNetwork) {
             networkImage.setImageDrawable(
                 ContextCompat.getDrawable(
                     requireContext(),
                     R.drawable.wifi_off
                 )
             )
         } else {
             networkText.visible()
             networkText1.visible()
             btnTryAgain.text = "Tushunarli"
             networkImage.setImageDrawable(
                 ContextCompat.getDrawable(
                     requireContext(),
                     R.drawable.wifi
                 )
             )
         }*/
    }
}