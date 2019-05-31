package com.pbs.PBshop.Activities.Folios

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.pbs.PBshop.Activities.Clientes.ListaClientes
import com.pbs.PBshop.R
import kotlinx.android.synthetic.main.activity_ver_folio.*



class verFolio : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_folio)
        println("aqui entra a la version de crear")

        val bundle=intent.extras
        if(bundle.get("id")=="")
            seleccionCliente.text="Seleccionar el cliente"
        else {
            seleccionCliente.text = "Cambiar de cliente"
            sa.text=sa.text.toString()+": "+bundle.get("name")+" "+bundle.get("lastName")
         }


        seleccionCliente.setOnClickListener {
            val intent= Intent(this, ListaClientes::class.java)
            intent.putExtra("origen","folio")
            startActivity(intent)



        }


    }







    }
    fun cargarimagenes() {
/*
            Picasso.get()
            .load("https://i.pinimg.com/1200x/a4/08/b8/a408b81d0d0dd12cbd155d1824a4fbaa.jpg")
            .into(image1)

            Picasso.get()
            .load("https://i.pinimg.com/564x/cc/0d/48/cc0d48c097232702ae5269d9bf4957b8.jpg")
            .into(image2)

            Picasso.get()
            .load("https://i.pinimg.com/564x/09/31/ce/0931cecace926c65576e63f9fc879fa9.jpg")
            .into(image3)
*/
    }

