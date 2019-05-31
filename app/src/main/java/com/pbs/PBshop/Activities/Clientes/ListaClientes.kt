package com.pbs.PBshop.Activities.Clientes

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.pbs.PBshop.R
import retrofit2.Call
import com.pbs.PBshop.`interface`.ClienteService
import com.pbs.PBshop.`interface`.ServiceBuilder
import com.pbs.PBshop.adapters.ClienteAdapter
import kotlinx.android.synthetic.main.activity_lista_clientes.*
import retrofit2.Callback
import retrofit2.Response

class ListaClientes : AppCompatActivity() {
    var origen:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_clientes)

        val bundle = intent.extras
        origen= bundle.get("origen") as String
        Toast.makeText(this,bundle.get("origen").toString() , Toast.LENGTH_SHORT).show()


        cargarClientes()
    }
    override fun onResume(){
        super.onResume()
        cargarClientes()
    }


    private fun cargarClientes(){
        val clienteService =
            ServiceBuilder.buildService(ClienteService::class.java)

        val requestCall=clienteService.getListaClientes()
        requestCall.enqueue(object: Callback<List<Cliente>>{

            override fun onResponse(call: Call<List<Cliente>>, response: Response<List<Cliente>>) {
                if(response.isSuccessful){
                    val clientesLista=response.body()!!
                    destiny_recycler_view.adapter= ClienteAdapter(clientesLista,origen)

                }

            }
            override fun onFailure(call: Call<List<Cliente>>, t: Throwable) {

            }

        })
    }





}
