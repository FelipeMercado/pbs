package com.pbs.PBshop.`interface`
import retrofit2.Call
import com.pbs.PBshop.Activities.Clientes.Cliente
import retrofit2.http.*

interface ClienteService {


   // @GET("/api/clients")  final
    //prueba
    @GET("/api/clients")
    fun getListaClientes():Call<List<Cliente>>

    @GET ("api/clients/id")
    fun getDestination(@Path("id")id:Int):Call<Cliente>

    @POST("/api/clients/create")
    fun addCliente(@Body newCliente: Cliente):Call<Cliente>

    @FormUrlEncoded
    @PUT("clients/{id}")
    fun updateCliente(
                @Path("id")id:Int,
                @Field("name")name:String,
                @Field("lastName")lastName:String,
                @Field("phone")LastName:String,
                @Field("email")email:String
                ):Call<Cliente>


    @DELETE("/api/clients/delete/{id}")
    fun deleteCliente(@Path("id")id:Int):Call<Unit>

}