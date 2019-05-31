package com.pbs.PBshop.Activities.Folios

import com.pbs.PBshop.Activities.Clientes.Cliente
import java.time.Year

data class Folio (
    val id: Int,
    val client_id:Int,
    val Workshop_record_sate_id:Int,
    val workshop_record_sate:Workshop_record_sate,
    val client:Cliente,
    val vehicle:Vehicle
    )


data class Workshop_record_sate(
    val id:Int,
    val state:String
)

data class Vehicle(
    val id:Int,
    val serial:String,
    val model:String,
    val brand:String,
    val year:Int

)