package com.usend.extensions

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Build
import android.text.*
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.annotation.VisibleForTesting
import androidx.appcompat.widget.AppCompatEditText
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import com.usend.BuildConfig
import com.usend.R
import com.usend.utils.JLog
import org.threeten.bp.LocalDate
import java.util.regex.Pattern


/*
* Execute block into try...catch
* */
inline fun <T> justTry(block: () -> T) = try {
    block()
} catch (e: Throwable) {
    debugMode {
        e.printStackTrace()
    }
}

/*
* Run Block of function on debug mode
* */
inline fun debugMode(block: () -> Unit) {
    if (BuildConfig.DEBUG) {
        block()
    }
}

/*
* Run Block of function on release mode
* */





/**
 *   Convert string to model class
 *
 */

inline fun <reified T> String.toObject(): T? {
    justTry {
        return Gson().fromJson(this, T::class.java)
    }
    return null
}

/**
 *  Convert Model to String
 */


inline fun <reified T : Any> newIntent(context: Context): Intent = Intent(context, T::class.java)

/**
 *  Set drawable end listener
 */


@SuppressLint("ClickableViewAccessibility")
fun EditText.addDrawableEndListener(callback: () -> Unit) {
    setOnTouchListener(OnTouchListener { _, event ->
        val DRAWABLE_RIGHT = 2
        if (event.action == MotionEvent.ACTION_UP) {
            if (event.rawX >= this.right - this.compoundDrawables[DRAWABLE_RIGHT].bounds.width()) {

                callback.invoke()
                return@OnTouchListener true
            }
        }
        false
    })
}


fun Any.getClassName(): String = if (this::class.java.simpleName.isEmpty()) {
    "Class"
} else this::class.java.simpleName



@VisibleForTesting
fun String.isValidEmail(): Boolean {
    //val EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$"
    return Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$").matcher(this)
        .matches()
}

@VisibleForTesting
fun String.isValidPhoneNumber(): Boolean {
    return Pattern.compile("^\\+(?:[0-9] ?){6,14}[0-9]\$").matcher(this)
        .matches()
}




fun View.isVisible() = visibility == View.VISIBLE

fun String?.nullSafe(defaultValue: String = ""): String = this ?: defaultValue
fun Int?.nullSafe(defaultValue: Int = 0): Int = this ?: defaultValue
fun Long?.nullSafe(defaultValue: Long = 0L): Long = this ?: defaultValue
fun Boolean?.nullSafe(defaultValue: Boolean = false): Boolean = this ?: defaultValue
fun Float?.nullSafe(defaultValue: Float = 0f): Float = this ?: defaultValue
fun Double?.nullSafe(defaultValue: Double = 0.0): Double = this ?: defaultValue




fun EditText.getTrimText(): String = text.toString().trim()

fun SpannableString.setClickableSpan(
    start: Int,
    end: Int, @ColorInt color: Int,
    typeFace : Typeface,
    isUnderLine : Boolean = false,
    underLineColor : Int? = null,
    block: (view: View?) -> Unit
) {

    setSpan(object : ClickableSpan() {
        override fun onClick(p0: View) {
            block(p0)
        }

        override fun updateDrawState(ds: TextPaint) {
            ds.isUnderlineText = false // set to false to remove underline
            ds.typeface = typeFace

            @RequiresApi(Build.VERSION_CODES.Q)
            if (isUnderLine) {
                ds.isUnderlineText = true
                ds.underlineColor = underLineColor!!
            }
        }

    }, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    // Set Color Span
    setSpan(ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
}

inline fun EditText.afterTextChanged(crossinline listener: (String) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable) {

            if (editable.isNotEmpty()) {
                listener(editable.toString())
            }
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }
    })
}



fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.makeVisible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

@SuppressLint("CheckResult")
fun ImageView.loadImageUrl(context: Context, imageUrl: String, cornerRadius : Int = 1, @DrawableRes placeholder: Int? = R.drawable.ic_package_place_holder,
                           @DrawableRes errorPlaceholder: Int? = R.drawable.ic_package_place_holder, callback: (Int) -> Unit) {

    val requestOptions = RequestOptions()
    requestOptions.transform(RoundedCorners(cornerRadius))

    placeholder?.let {
        requestOptions.placeholder(placeholder!!)
    }
    errorPlaceholder?.let {
        requestOptions.error(errorPlaceholder!!)
    }

    Glide.with(context)
        .load(imageUrl)
        .apply(requestOptions).listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(e: GlideException?, model: Any?, target: com.bumptech.glide.request.target.Target<Drawable>?, isFirstResource: Boolean): Boolean {
                callback(0)
                return false
            }

            override fun onResourceReady(resource: Drawable?, model: Any?, target: com.bumptech.glide.request.target.Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                return false
            }

        })
        .into(this)
}

@SuppressLint("CheckResult")
fun ImageView.loadImageUrl(context: Context, imageUrl: Int, cornerRadius : Int = 1, @DrawableRes placeholder: Int? = R.drawable.ic_package_place_holder,
                           @DrawableRes errorPlaceholder: Int? = R.drawable.ic_package_place_holder, callback: (Int) -> Unit) {

    val requestOptions = RequestOptions()
    requestOptions.transform(RoundedCorners(cornerRadius))

    placeholder?.let {
        requestOptions.placeholder(placeholder!!)
    }
    errorPlaceholder?.let {
        requestOptions.error(errorPlaceholder!!)
    }

    Glide.with(context)
        .load(imageUrl)
        .apply(requestOptions).listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(e: GlideException?, model: Any?, target: com.bumptech.glide.request.target.Target<Drawable>?, isFirstResource: Boolean): Boolean {
                callback(0)
                return false
            }

            override fun onResourceReady(resource: Drawable?, model: Any?, target: com.bumptech.glide.request.target.Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                return false
            }

        })
        .into(this)
}

/**
 * Log.i(className,"methodName() Line : this")
 */
fun <T : Any> T.info(): T {
    val res = this
    Throwable().stackTrace[1].apply {
        JLog.i(generateTag(), generateMessage(res))
    }
    return this
}

/**
 * Log.i(className,"methodName() Line : msg")
 */
fun <T : Any> T.info(msg: () -> Any): T {
    Throwable().stackTrace[1].apply {
        JLog.i(generateTag(), generateMessage(msg()))
    }
    return this
}

/**
 * Log.i(className,"methodName() Line : this")
 */
fun <T : Any> T.error(): T {
    val res = this
    Throwable().stackTrace[1].apply {
        JLog.e(generateTag(), generateMessage(res))
    }
    return this
}

/**
 * Log.i(className,"methodName() Line : msg")
 */
fun <T : Any> T.error(msg: () -> Any): T {
    Throwable().stackTrace[1].apply {
        JLog.e(generateTag(), generateMessage(msg()))
    }
    return this
}
fun showToast(context: Context, message: String, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(context, message, duration).apply { show() }

fun StackTraceElement.generateTag() = className.substringAfterLast(".")

fun StackTraceElement.generateMessage(msg: Any = "") = "$methodName() Line $lineNumber: $msg"

fun showAppToast(context: Application, message: String, duration: Int = Toast.LENGTH_LONG) =
    Toast.makeText(context, message, duration).apply { show() }!!