package com.bangtiray.core.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.Gravity
import android.view.WindowManager
import android.widget.Toast
import com.bangtiray.core.ui.activity.auth.R
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener

fun Context.toast(msg: String?) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
fun Context.longToast(msg: String?) = Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
fun logi(msg: String?) = Log.i("bangtiray_log", msg.toString())

fun generateIdRoom(vararg ids: String) = ids.toList().sorted().toString()
    .replace("[", "")
    .replace("]", "")
    .replace(", ", "")
    .trim()
    .replace(" ", "")

fun generateIdStory(userId: String) = "story-of-$userId"
fun generateIdImageBB(userId: String) = "image-bb-of-$userId-${System.currentTimeMillis()}"

fun Activity.withPermissions(listPermission: List<String>, action: (grantedList: List<String>, deniedList: List<String>) -> Unit) {
    Dexter.withActivity(this)
        .withPermissions(listPermission)
        .withListener(object : MultiplePermissionsListener {
            override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                if (report.areAllPermissionsGranted()) {
                    action.invoke(
                        report.grantedPermissionResponses.map { it.permissionName },
                        report.deniedPermissionResponses.map { it.permissionName })
                } else {
                    toast("Permission denied")
                }
            }

            override fun onPermissionRationaleShouldBeShown(permissions: MutableList<PermissionRequest>?, token: PermissionToken?) {
                token?.continuePermissionRequest()
            }
        })
        .check()
}

fun Context.slideNetworkStatus(slide : Dialog){
    var slideDialog = slide
    slideDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    val window = slideDialog.window
    window?.setLayout(
        WindowManager.LayoutParams.MATCH_PARENT,
        WindowManager.LayoutParams.MATCH_PARENT
    )
    slideDialog.setContentView(R.layout.popup_not_connect)
    val layoutParams = WindowManager.LayoutParams()
    slideDialog.window?.attributes?.windowAnimations = R.style.CustomDialogAnimation
    layoutParams.copyFrom(slideDialog.window?.attributes)
    val heightSpan = (this.resources.displayMetrics.heightPixels * 0.250).toInt()

    layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT
    layoutParams.height = heightSpan
    layoutParams.gravity = Gravity.BOTTOM

    slideDialog.window?.attributes = layoutParams
    slideDialog.setCancelable(false)
    slideDialog.setCanceledOnTouchOutside(false)
    slideDialog.show()
}