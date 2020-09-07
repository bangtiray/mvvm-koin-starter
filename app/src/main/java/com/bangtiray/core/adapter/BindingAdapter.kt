package com.bangtiray.core.adapter


import android.util.Base64
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("showImage")
fun showImage(imgView: ImageView, url: String?) {
    imgView.loadImage(
        StringBuilder().append("https://jastan.co.id/storage/img/products/")
            .append(url).toString()
    )
}

@BindingAdapter("showBlogImage")
fun showBlogImage(imgView: ImageView, url: String?) {
    imgView.loadImage(
        StringBuilder().append("https://jastan.co.id/storage/img/blog/")
            .append(url).toString()
    )
}

//@BindingAdapter("premium_amount", "admin_service", "admin_services")
//fun moneyFormat(
//    textView: TextView,
//    premium_amount: String,
//    admin_service: String,
//    admin_services: String
//) {
//    val total = premium_amount.toDouble() + admin_service.toDouble() + admin_services.toDouble()
//    textView.text = CustomLibrary.priceWithoutDecimal(total)
//}
//
//@BindingAdapter("setGender")
//fun setGender(imgView: ImageView, setGender: String) {
//    var genderImg = ""
//    genderImg = when (setGender) {
//        Constants.MALE -> "boy.png"
//        else -> "girl.png"
//    }
//    imgView.loadImage(
//        StringBuilder().append(BuildConfig.GITHUB_REPO)
//            .append(genderImg).toString()
//    )
//}

//@BindingAdapter("setLinearBackground")
//fun setLinearBackground(linearLayout: LinearLayout, status: String) {
//    linearLayout.loadBackground(linearLayout, status)
//}

//@BindingAdapter("setShortDate")
//fun setShortDate(textView: TextView, setShortDate: String) {
//    textView.text = CustomLibrary.getShortDate(setShortDate)
//}
//
//@BindingAdapter("setDate", "setTime")
//fun setShortDateTime(textView: TextView, setDate: String, setTime: String) {
//    textView.text = "DOL : ${CustomLibrary.getShortDate(setDate)} $setTime"
//}
//
//@BindingAdapter("setCounter")
//fun setCounter(mCounter: Counter, value: String) {
//    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
//    format.timeZone = TimeZone.getTimeZone("UTC")
//    try {
//        val date = format.parse(value)
//        mCounter.setDate(date)
//    } catch (e: ParseException) {
//        e.printStackTrace()
//    }
//    mCounter.setListener(object : Counter.Listener {
//        override fun onTick(millisUntilFinished: Long) {
//            Log.d("onTick", "onTick: Counter - " + millisUntilFinished);
//        }
//
//        override fun onTick(days: Long, hours: Long, minutes: Long, seconds: Long) {
//            Log.d(
//                "onTick", "onTick: Counter - " + days + "d " +
//                        hours + "h " +
//                        minutes + "m " +
//                        seconds + "s "
//            );
//        }
//
//    })
//}

//private fun LinearLayout.loadBackground(
//    linearLayout: LinearLayout,
//    status: String
//) {
//    when (status) {
//        Constants.WAITING -> {
//            linearLayout.setBackgroundResource(R.drawable.drawable_amber_gradient)
//        }
//        Constants.INFORCE -> {
//            linearLayout.setBackgroundResource(R.drawable.drawable_green_gradient)
//        }
//        Constants.CLOSE -> {
//            linearLayout.setBackgroundResource(R.drawable.drawable_purple_gradient)
//        }
//        Constants.CANCEL -> {
//            linearLayout.setBackgroundResource(R.drawable.drawable_red_gradient)
//        }
//        Constants.REQUEST -> {
//            linearLayout.setBackgroundResource(R.drawable.drawable_amber_gradient)
//        }
//        Constants.APPROVED -> {
//            linearLayout.setBackgroundResource(R.drawable.drawable_green_gradient)
//        }
//
//        Constants.UNAPPROVED -> {
//            linearLayout.setBackgroundResource(R.drawable.drawable_red_gradient)
//        }
//    }
//
//}

fun ImageView.loadImage(source: String?, requestOptions: RequestOptions? = null) {
    val requestBuilder = Glide.with(this.context)

        .load(source ?: "")
    requestOptions?.let {
        requestBuilder.apply(requestOptions)
    }
    requestBuilder.into(this)
}

@BindingAdapter("showByteImage")
fun showByteImage(imgView: ImageView, byte: String?) {
    imgView.loadByteImage(
        StringBuilder().append("")
            .append(byte).toString()
    )
}

fun ImageView.loadByteImage(source: String?, requestOptions: RequestOptions? = null) {

    var requestBuilder = Glide.with(this.context).asBitmap()
        .load(Base64.decode(source, Base64.DEFAULT))
    requestOptions?.let {
        requestBuilder.apply(requestOptions)
    }
    requestBuilder.into(this)

}

@BindingAdapter("toVisibility")
fun toVisibility(editText: TextInputLayout, visible: String) {
    if (visible == "Visible") {
        editText.visibility = View.VISIBLE
    } else {
        editText.visibility = View.GONE
    }
}
//@BindingAdapter("imageBase64"){
//    fun imageBase64(string: String)
//}