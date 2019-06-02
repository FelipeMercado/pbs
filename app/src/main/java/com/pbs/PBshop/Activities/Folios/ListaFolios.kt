package com.pbs.PBshop.Activities.Folios

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.pbs.PBshop.R
import com.pbs.PBshop.`interface`.FolioService
import com.pbs.PBshop.`interface`.ServiceBuilder
import com.pbs.PBshop.adapters.ClienteAdapter
import com.pbs.PBshop.adapters.FolioAdapter
import kotlinx.android.synthetic.main.activity_lista_clientes.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListaFolios : AppCompatActivity() {
    var origen:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_folios)
        val bundle=intent.extras
        val origen:String=bundle.get("origen").toString()
        Toast.makeText(this@ListaFolios, "xxx", Toast.LENGTH_SHORT).show()
        cargarFolios(origen)


    }



    private fun cargarFolios(origen:String){
        val folioService =
            ServiceBuilder.buildService(FolioService::class.java)
        val requestCall=folioService.getListaFolio()
        requestCall.enqueue(object: Callback<List<Folio>> {
            override fun onFailure(call: Call<List<Folio>>, t: Throwable) {
                Toast.makeText(this@ListaFolios, "ha fallado", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Folio>>, response: Response<List<Folio>>) {
                val listaFolios=response.body()!!

                destiny_recycler_view.adapter= FolioAdapter(listaFolios,origen)
            }


        })
    }




    override fun onResume(){
        super.onResume()
        cargarFolios(origen)
    }

}
