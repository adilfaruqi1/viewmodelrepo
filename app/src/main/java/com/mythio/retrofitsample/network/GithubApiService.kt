package com.mythio.retrofitsample.network

import com.mythio.retrofitsample.responsemodel.VehicleMake
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit
//private const val BASE_URL = "https://api.github.com/"
private const val BASE_URL = "https://interface.billt.online/"
val interceptor = HttpLoggingInterceptor()

private val client =
    OkHttpClient.Builder()
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addInterceptor { chain ->
            val rew = chain.request().newBuilder()
                .addHeader("Content-Type", "application/json")
                .build()
            chain.proceed(rew)
        }
        .addInterceptor(interceptor.setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()
private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL).client(client)
        .build()

interface GithubApiService {
    @GET("/users/{profile}")
    fun getUserData(@Path("profile") profile: String):
            Call<GithubUser>

    @GET("api/open/vehicle/makes")
    fun getVehicleMakes(): Call<VehicleMake>

}

object GithubApi {
    val retrofitService: GithubApiService by lazy {
        retrofit.create(GithubApiService::class.java)
    }
}