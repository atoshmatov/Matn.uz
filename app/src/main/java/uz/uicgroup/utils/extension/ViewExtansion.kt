package uz.uicgroup.utils.extension

import android.app.Activity
import android.view.View
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.skydoves.balloon.*
import uz.uicgroup.R


fun View.visible() {
    visibility = View.VISIBLE
}

fun View.inVisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun Fragment.showToast(message: String, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(requireContext(), message, duration).show()
}

fun Activity.showToast(message: String, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, message, duration).show()
}

fun <T : ViewBinding> T.myApply(block: T.() -> Unit) {
    block(this)
}

fun View.showTooltipTop(
    message: String,
    @ColorRes color: Int = R.color.tool_tip_color,
    arrowPosition: Float = 0.5f,
    @ColorRes textColor: Int = R.color.tool_tip_text_color
) {
    val tooltip = createBalloon(context) {
        setWidth(BalloonSizeSpec.WRAP)
        setHeight(BalloonSizeSpec.WRAP)
        setArrowPosition(arrowPosition)
        setArrowOrientation(ArrowOrientation.TOP)
        setCornerRadius(6f)
        setMargin(8)
        setPadding(8)
        setTextSize(12f)
        setText(message)
        setTextColorResource(textColor)
        setBackgroundColorResource(color)
        setBalloonAnimation(BalloonAnimation.CIRCULAR)
        setAutoDismissDuration(2_000)
        setLifecycleOwner(lifecycleOwner)
    }
    tooltip.showAlignTop(this)
}

fun View.showTooltipStart(
    message: String,
    @ColorRes color: Int = R.color.tool_tip_color,
    arrowPosition: Float = 0.5f,
    @ColorRes textColor: Int = R.color.tool_tip_text_color
) {
    val tooltip = createBalloon(context) {
        setWidth(BalloonSizeSpec.WRAP)
        setHeight(BalloonSizeSpec.WRAP)
        setArrowPosition(arrowPosition)
        setArrowOrientation(ArrowOrientation.END)
        setCornerRadius(6f)
        setMargin(8)
        setPadding(8)
        setTextSize(12f)
        setText(message)
        setTextColorResource(textColor)
        setBackgroundColorResource(color)
        setBalloonAnimation(BalloonAnimation.CIRCULAR)
        setAutoDismissDuration(2_000)
        setLifecycleOwner(lifecycleOwner)
    }
    tooltip.showAlignLeft(this)
}









