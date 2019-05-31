package com.pbs.PBshop.`interface`

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    private const val URL="http://138.68.242.208"

    //Crear cliente okHTTP
    private val okHttp =OkHttpClient.Builder()

    //builder de retrofit
    private val  builder =Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).client(
        okHttp.build())


    //instancia de retrofit
    private val retrofit=builder.build()

    fun <T> buildService(serviceType: Class<T>): T{
        return retrofit.create(serviceType)
    }
}