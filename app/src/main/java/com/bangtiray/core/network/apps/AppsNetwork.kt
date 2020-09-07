package com.bangtiray.core.network.apps

import android.app.Activity
import android.content.ComponentCallbacks
import com.bangtiray.core.database.entity.LocalApps
import com.bangtiray.core.utils.logi
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AppsNetwork(componentCallbacks: ComponentCallbacks) {
    private val appsInstanceNetwork: AppsInstanceNetwork by componentCallbacks.inject()
    fun getApps(activity: Activity, appsNetworkCallback: AppsNetworkCallback) {
        try {
            logi("get Apps")
            val response = appsInstanceNetwork.showApps()
            response.enqueue(object : Callback<AppsListDto> {
                override fun onFailure(call: Call<AppsListDto>, t: Throwable) {
                    appsNetworkCallback.onFailed(t.localizedMessage)
                }

                override fun onResponse(call: Call<AppsListDto>, response: Response<AppsListDto>) {
                    activity.runOnUiThread { appsNetworkCallback.onSuccess(response.body()!!.results) }
                }

            })
        } catch (e: Throwable) {
            activity.runOnUiThread {
                appsNetworkCallback.onFailed(e.localizedMessage)
            }
        }
    }

    interface AppsNetworkCallback {
        fun onSuccess(localApps: List<LocalApps>)
        fun onFailed(message: String?)
    }
}

