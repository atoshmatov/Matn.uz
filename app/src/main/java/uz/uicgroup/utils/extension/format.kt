package uz.uicgroup.utils.extension

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import uz.uicgroup.R

fun usingJavaStringFormat(input: Double, scale: Int) = String.format("%.${scale}f", input)

fun View.onClick(action: (View) -> Unit) {
    setOnClickListener {
        it?.let(action)
    }
}

@SuppressLint("ResourceAsColor")
fun String.paintResult(query: String): Spannable {
    val spannable: Spannable = SpannableString(query)
    spannable.setSpan(
        BackgroundColorSpan(R.color.tool_tip_danger_text_color),
        0,
        query.length,
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