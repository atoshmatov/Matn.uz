package uz.uicgroup.utils.extension

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

fun usingJavaStringFormat(input: Double, scale: Int) = String.format("%.${scale}f", input)

fun View.onClick(action: (View) -> Unit) {
    setOnClickListener {
        it?.let(action)
    }
}

fun String.paintResult(query: String): Spannable {
    var start = this.indexOf(query)
    var end = start + query.length
    if (this.equals(query, true)) {
        start = 0
        end = this.length
    }
    val spannable = SpannableString(this)
    spannable.setSpan(
        ForegroundColorSpan(Color.RED),
        start, end,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    return spannable
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}


fun Context.showKeyboard(view: View) {}