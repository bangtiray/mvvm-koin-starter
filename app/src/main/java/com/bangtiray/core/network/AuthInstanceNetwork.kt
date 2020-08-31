package com.bangtiray.core.network

import com.bangtiray.core.data.ConstantValue
import com.bangtiray.core.database.entity.LocalUser
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthInstanceNetwork{
   @FormUrlEncoded
    @POST("auth/login")
//    @Headers("No-Authentication: true")
   suspend fun postLogin(
       @Field("email") email: String,
       @Field("password") password: String,
       @Field("deviceToken") deviceToken: String
    ): LocalUser

    companion object{
        private val gsonBuilder=GsonBuilder()
            .setLenient()
            .setPrettyPrinting()
            .create()

        private val retrofit=Retrofit.Builder()
            .baseUrl(ConstantValue.baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
            .build()

        fun create():AuthInstanceNetwork= retrofit.create(AuthInstanceNetwork::class.java)
    }
}