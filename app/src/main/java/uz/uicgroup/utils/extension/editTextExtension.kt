package uz.uicgroup.utils.extension

import android.widget.EditText
import androidx.core.widget.addTextChangedListener

fun EditText.values(): String {
    return this.text.toString().trim()
}

fun EditText.addListener(block: (String) -> Unit) {
    this.addTextChangedListener {
        it?.let {
            block.invoke(it.toString().trim())
        }
    }
}