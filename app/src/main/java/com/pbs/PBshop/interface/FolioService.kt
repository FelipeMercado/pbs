package com.pbs.PBshop.`interface`

import com.pbs.PBshop.Activities.Folios.Folio
import retrofit2.Call
import retrofit2.http.*

interface FolioService {



    // @GET("/api/folios")  final
    //prueba
    @GET("/api/workshops")
    fun getListaFolio(): Call<List<Folio>>

    @GET("/api/workshops/id")
    fun getFolio(@Path("id")id:Int): Call<Folio>

    @POST("workshop/create")
    fun addFolio(@Body newFolio: Folio): Call<Folio>

    @FormUrlEncoded
    @PUT("workshop/{id}")
    fun updateFolio(

    ): Call<Folio>


    @DELETE("folio/{id}")
    fun deleteFolio(@Path("id")id:Int): Call<Unit>


}