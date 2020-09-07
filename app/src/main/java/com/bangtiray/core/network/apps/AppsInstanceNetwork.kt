package com.bangtiray.core.network.apps

import com.bangtiray.core.data.ConstantValue
import com.bangtiray.core.database.entity.LocalApps
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface AppsInstanceNetwork {
    @GET("dashboard/apps/online")
    fun showApps(): Call<AppsListDto>


    companion object {
        private val gsonBuilder= GsonBuilder()
            .setLenient()
            .setPrettyPrinting()
            .create()

        private val clientBuilder = OkHttpClient.Builder()
            .followRedirects(false)
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)

        private val retrofit= Retrofit.Builder()
            .client(clientBuilder.build())
            .baseUrl(ConstantValue.baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
            .build()



        fun create(): AppsInstanceNetwork = retrofit.create(
            AppsInstanceNetwork::class.java
        )
    }
}

data class AppsListDto(
    val results: List<LocalApps>
)