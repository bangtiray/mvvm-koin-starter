package com.bangtiray.core.network.news

import android.app.Activity
import android.content.ComponentCallbacks
import com.bangtiray.core.database.entity.LocalNews
import com.bangtiray.core.utils.logi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsNetwork(componentCallbacks: ComponentCallbacks) {
    private val newsInstanceNetwork: NewsInstanceNetwork by componentCallbacks.inject()
    fun getNews(activity: Activity, newsNetworkCallback: NewsNetworkCallback) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                logi("Getting News...")
                val response = newsInstanceNetwork.showNews()
                response.enqueue(object :Callback<LatestNewsListDto>{
                    override fun onFailure(call: Call<LatestNewsListDto>, t: Throwable) {

                    }

                    override fun onResponse(
                        call: Call<LatestNewsListDto>,
                        response: Response<LatestNewsListDto>
                    ) {
                        if(response.isSuccessful){
                            activity.runOnUiThread {
                                newsNetworkCallback.onSuccess(response.body()!!.results)
                            }
                        }else{
                            activity.runOnUiThread {
                                logi("retrieve failed")
                            }
                        }
                    }

                })
            }catch (e:Throwable){
                activity.runOnUiThread {
                    logi(e.localizedMessage)
                    newsNetworkCallback.onFailed(e.localizedMessage)
                }
            }
        }
    }

    interface NewsNetworkCallback {
        fun onSuccess(localNews: List<LocalNews>)
        fun onFailed(message: String?)
    }
}