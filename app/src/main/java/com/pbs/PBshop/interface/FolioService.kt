package com.pbs.PBshop.`interface`

import com.pbs.PBshop.Activities.Folios.Folio
import retrofit2.Call
import retrofit2.http.*

interface FolioService {



    // @GET("/api/folios")  final
    //prueba
    @GET("/get/workshops")
    fun getListaFolio(): Call<List<Folio>>

    @GET("/get/workshops/id")
    fun getFolio(@Path("id")id:Int): Call<Folio>

    @POST("folios")
    fun addFolio(@Body newFolio: Folio): Call<Folio>

    @FormUrlEncoded
    @PUT("folios/{id}")
    fun updateFolio(

    ): Call<Folio>


    @DELETE("folio/{id}")
    fun deleteFolio(@Path("id")id:Int): Call<Unit>


}