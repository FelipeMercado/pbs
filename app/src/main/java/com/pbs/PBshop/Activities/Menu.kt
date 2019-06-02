package com.pbs.PBshop.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.pbs.PBshop.Activities.Clientes.ListaClientes
import com.pbs.PBshop.Activities.Clientes.verCliente
import com.pbs.PBshop.Activities.Folios.ListaFolios
import com.pbs.PBshop.Activities.Folios.verFolio
import com.pbs.PBshop.R
import kotlinx.android.synthetic.main.activity_menu.*

class Menu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        nc.setOnClickListener {
            val intent = Intent(this, verCliente::class.java)
            intent.putExtra("iden","-1")
            intent.putExtra("name","")
            intent.putExtra("lastName","")
            intent.putExtra("phone","")
            intent.putExtra("email","")

            startActivity(intent)
        }


        tc.setOnClickListener {
            val intent = Intent(this, ListaClientes::class.java)
            intent.putExtra("origen","menu")
            startActivity(intent)
        }






        nf.setOnClickListener {
            val intent = Intent(this, verFolio::class.java)
            intent.putExtra("iden","-1")
            startActivity(intent)
        }


        tf.setOnClickListener {
            val intent = Intent(this, ListaFolios::class.java)
            intent.putExtra("origen","todos")
            startActivity(intent)
        }
        espera.setOnClickListener {
            val intent = Intent(this, ListaFolios::class.java)
            intent.putExtra("origen","espera")
            startActivity(intent)
        }
        proceso.setOnClickListener {
            val intent = Intent(this, ListaFolios::class.java)
            intent.putExtra("origen","proceso")
            startActivity(intent)
        }
        terminados.setOnClickListener {
            val intent = Intent(this, ListaFolios::class.java)
            intent.putExtra("origen","terminados")
            startActivity(intent)
        }



    }
     public fun delivery(){
        val intent = Intent(this, verCliente::class.java)

        startActivity(intent)

    }
}
