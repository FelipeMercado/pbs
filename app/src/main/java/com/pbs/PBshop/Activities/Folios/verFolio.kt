package com.pbs.PBshop.Activities.Folios

import android.annotation.SuppressLint
import android.app.PendingIntent.getActivity
import android.content.Intent
import android.graphics.Color
import android.graphics.Color.*
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.pbs.PBshop.Activities.Clientes.ListaClientes
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_ver_folio.*
import kotlinx.android.synthetic.main.activity_ver_folio.view.*
import android.widget.LinearLayout
import com.pbs.PBshop.R
import kotlinx.android.synthetic.main.activity_ver_cliente.*
import android.graphics.Color.WHITE as E


class verFolio : AppCompatActivity() {
    var idtv=0

     @RequiresApi(Build.VERSION_CODES.M)
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.pbs.PBshop.R.layout.activity_ver_folio)
        println("aqui entra a la version de crear")

        val bundle = intent.extras
        print(bundle.get("iden"))
        if (bundle.get("iden")== "-1"){
            seleccionCliente.text = "Seleccionar el cliente"
            sa.text = "Nombre del cliente"
        }
        else {
            seleccionCliente.text = "Cambiar de cliente"
            sa.text=sa.text.toString()+": "+bundle.get("name")+" "+bundle.get("lastName")
         }


        seleccionCliente.setOnClickListener {
            val intent= Intent(this, ListaClientes::class.java)
            intent.putExtra("origen","folio")
            startActivity(intent)



        }

        addTask.setOnClickListener{

            // creating TextView programmatically
            val tv_dynamic = EditText(this)
            tv_dynamic.textSize = 20f
            tv_dynamic.id=idtv

            var image = ImageView(this)
            val width = 60
            val height = 60
            val parms = LinearLayout.LayoutParams(width, height)
            image.setLayoutParams(parms)


            val delete = Button(this)
            delete.textSize = 40f
            delete.id=idtv


            //delete.setBackgroundColor(RED);
            delete.setText("Aleoa")
            delete.setTextColor(0);





            idtv++



            // add TextView to LinearLayout

            tasks.addView(tv_dynamic)
           // tasks.addView(image)
            tasks.addView(delete)

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

