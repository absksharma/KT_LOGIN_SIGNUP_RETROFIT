package com.example.kt_loginsignup_retrofit.api

import com.example.kt_loginsignup_retrofit.models.DefaultResponce
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiInterface {

    @FormUrlEncoded
    @POST("newuser.php")
    suspend fun createUser(
        @Field("username") username: String,
        @Field("name") name: String,
        @Field("password") password: String,
        @Field("email") email: String,
        @Field("phone") phone: String,
    ): Call<DefaultResponce>


    @POST("login.php")
    suspend fun logIn(email: String, password: String): Call<DefaultResponce>

}