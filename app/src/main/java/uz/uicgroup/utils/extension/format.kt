package uz.uicgroup.utils.extension

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.text.Spannable
import android.text.SpannableString
import android.text.style.BackgroundColorSpan
import android.text.style.ReplacementSpan
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import dagger.hilt.android.qualifiers.ApplicationContext
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

@SuppressLint("ResourceAsColor")
class BorderedSpan(context: Context) : ReplacementSpan() {
    private val mPaintBorder: Paint = Paint()
    private val mPaintBackground: Paint
    var mWidth = 0
    var r: Resources
    private var mTextColor: Int

    init {
        mPaintBorder.style = Paint.Style.STROKE
        mPaintBorder.strokeCap = Paint.Cap.ROUND
        mPaintBorder.strokeJoin = Paint.Join.MITER
        mPaintBorder.isAntiAlias = true
        mPaintBackground = Paint()
        mPaintBackground.style = Paint.Style.FILL
        mPaintBackground.isAntiAlias = true
        r = context.resources
        mPaintBackground.color = ContextCompat.getColor(context, R.color.span_bg_color)
        mTextColor = ContextCompat.getColor(context, R.color.text_color)
    }

    override fun getSize(
        paint: Paint,
        text: CharSequence?,
        start: Int,
        end: Int,
        fm: Paint.FontMetricsInt?
    ): Int {
        //return text with relative to the Paint
        mWidth = paint.measureText(text, start, end).toInt()
        return mWidth
    }

    override fun draw(
        canvas: Canvas,
        text: CharSequence?,
        start: Int,
        end: Int,
        x: Float,
        top: Int,
        y: Int,
        bottom: Int,
        paint: Paint
    ) {
        canvas.drawRect(x, top.toFloat(), x + mWidth, bottom.toFloat(), mPaintBackground)
        canvas.drawRect(x, top.toFloat(), x + mWidth, bottom.toFloat(), mPaintBorder)
        paint.color = mTextColor //use the default text paint to preserve font size/style
        canvas.drawText(text!!, start, end, x, y.toFloat(), paint)
    }
}


fun String.paintResultSearch(query: String, context: Context): Spannable {
    var start = this.indexOf(query)
    var end = start + query.length

    val spannable = SpannableString(this)

    if (this.equals(query, true)) {
        start = 0
        end = this.length
    }
    if (start >= 0) {
         spannable.setSpan(
             BackgroundColorSpan(ContextCompat.getColor(context, R.color.span_bg_color)),
             start,
             end,
             Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
         )

        /*spannable.setSpan(
            BorderedSpan(context),
            start,
            end,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )*/
    }

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