package com.bangtiray.core.network.news

import com.bangtiray.core.data.ConstantValue
import com.bangtiray.core.database.entity.LocalNews
import com.google.gson.GsonBuilder
import retrofit2.Call
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


import java.util.concurrent.TimeUnit

interface NewsInstanceNetwork {
    @GET("dashboard/blog/category/news")
    fun showNews(): Call<LatestNewsListDto>

    companion object {
        private val gsonBuilder=GsonBuilder()
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



        fun create(): NewsInstanceNetwork = retrofit.create(
            NewsInstanceNetwork::class.java
        )
    }
}

data class LatestNewsListDto(

    val results: List<LocalNews>
)
