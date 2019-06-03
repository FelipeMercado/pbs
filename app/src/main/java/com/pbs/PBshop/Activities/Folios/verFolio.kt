package com.pbs.PBshop.Activities.Folios

import android.app.Activity
import android.content.ClipDescription
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.annotation.RequiresApi
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.widget.*
import com.pbs.PBshop.Activities.Clientes.ListaClientes
import kotlinx.android.synthetic.main.activity_ver_folio.*
import com.pbs.PBshop.R
import kotlinx.android.synthetic.main.nueva_tarea.*
import java.net.URI
import android.graphics.Color.WHITE as E


class verFolio : AppCompatActivity() {
    var idtv=0
    private var IMAGE_PICK_CODE = 0
    val nuevaTarea = R.layout.nueva_tarea

    var inflater: LayoutInflater? = null
    var layoutTarea: LinearLayout? = null
    var imagen: ImageView? = null
    var editTex: EditText?= null
    var boton: CardView?= null

    var uri: Uri? = null
    val arrayListTarea = ArrayList<targetaTarea>()

    @RequiresApi(Build.VERSION_CODES.M)
     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_ver_folio)
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
            nuevaTarea()


            // creating TextView programmatically
//            val tv_dynamic = EditText(this)
//            tv_dynamic.textSize = 20f
//            tv_dynamic.id=idtv
//
//            var image = ImageView(this)
//            val width = 60
//            val height = 60
//            val parms = LinearLayout.LayoutParams(width, height)
//            image.setLayoutParams(parms)
//
//            val delete = Button(this)
//            delete.textSize = 40f
//            delete.id=idtv
//
//            //delete.setBackgroundColor(RED);
//            delete.setText("Aleoa")
//            delete.setTextColor(resources.getColor(R.color.colorAccent))
//
//            idtv++
//
//            // add TextView to LinearLayout
//
//            tasks.addView(tv_dynamic)
//           // tasks.addView(image)
//            tasks.addView(delete)
        }


    }

    private fun nuevaTarea(){

        inflater = LayoutInflater.from(applicationContext)
        layoutTarea = inflater!!.inflate(nuevaTarea, null, false) as LinearLayout
        imagen = layoutTarea!!.findViewById(R.id.content_img) as ImageView
        editTex = layoutTarea!!.findViewById(R.id.edit_text_tarea) as EditText
        boton = layoutTarea!!.findViewById(R.id.buttonFoto) as CardView

        var targeta = targetaTarea(idtv, imagen!!, editTex!!)

        boton!!.id = idtv
        val id = boton!!.id
        boton!!.setOnClickListener {
            editTex!!.id = id
            imagen!!.id = id
            pickImageFromGallery(id)
        }

        arrayListTarea.add(targeta)
        tasks.addView(layoutTarea)
        idtv++
    }

    private fun pickImageFromGallery(imageCode: Int) {
        IMAGE_PICK_CODE = imageCode
        //Intent to pick image
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, imageCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            arrayListTarea.get(IMAGE_PICK_CODE).imagen.setImageURI(data?.data)
        }
    }

}

data class targetaTarea(var id: Int, var imagen: ImageView, var description: EditText)

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

