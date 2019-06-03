package com.pbs.PBshop.Activities.Folios

import android.app.Activity
import android.content.ClipDescription
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.annotation.RequiresApi
import android.support.v7.widget.CardView
import android.util.Log
import android.view.LayoutInflater
import android.widget.*
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.auth.CognitoCachingCredentialsProvider
import com.amazonaws.auth.CognitoCredentialsProvider
import com.amazonaws.mobile.client.AWSMobileClient
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility
import com.amazonaws.regions.Region
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.ObjectMetadata
import com.pbs.PBshop.Activities.Clientes.ListaClientes
import kotlinx.android.synthetic.main.activity_ver_folio.*
import com.pbs.PBshop.R
import kotlinx.android.synthetic.main.nueva_tarea.*
import java.io.File
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

    val KEY = "AKIAU3CTK5VEJMGCHXFI"
    val SECRET = "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"

    @RequiresApi(Build.VERSION_CODES.M)
     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_ver_folio)
         println("aqui entra a la version de crear")

        AWSMobileClient.getInstance().initialize(this) {
            Log.d("AWSMobileClient", "AWSMobileClient is instantiated and you are connected to AWS!")
        }.execute()

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
//            arrayListTarea.get(IMAGE_PICK_CODE).imagen.setImageURI(data?.data)
            var uri = data?.data
            val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
            var cursor: Cursor = contentResolver.query(uri,filePathColumn,null,null,null)
            cursor.moveToFirst()
            var columnIndex = cursor.getColumnIndex(filePathColumn[0])
            var realPath = cursor.getString(columnIndex)
            var file = File(realPath)

            uploadImagen(file)
        }
    }

    private fun uploadImagen(file: File){
        var credentials = BasicAWSCredentials(KEY,SECRET)
        var s3Client: AmazonS3 = AmazonS3Client(credentials)
        s3Client.setRegion(Region.getRegion(Regions.US_EAST_2))

        var imageName = file.name

//        var transferUtility = TransferUtility(s3Client,this)
        var transferUtility = TransferUtility.builder()
            .context(applicationContext)
            .awsConfiguration(AWSMobileClient.getInstance().configuration)
            .s3Client(s3Client)
            .build()

        var uploadObserver: TransferObserver = transferUtility.upload("pbshop","imagenes", file)
        uploadObserver.setTransferListener(object : TransferListener {

            override fun onStateChanged(id: Int, state: TransferState) {
                if (TransferState.COMPLETED === state) {
                    Log.d("COMPLETE", "true")
                }
            }

            override fun onProgressChanged(id: Int, bytesCurrent: Long, bytesTotal: Long) {
                val percentDonef = bytesCurrent.toFloat() / bytesTotal.toFloat() * 100
                val percentDone = percentDonef.toInt()
                Log.d("Progress", percentDone.toString())
            }

            override fun onError(id: Int, ex: Exception) {
                // Handle errors
                Log.e("TransferListener",ex.message.toString())
            }
        })

//        var downloadObserver: TransferObserver = transferUtility.download("jsaS3/$imageName", file)
//        downloadObserver.setTransferListener(object : TransferListener {
//
//            override fun onStateChanged(id: Int, state: TransferState) {
//                if (TransferState.COMPLETED === state) {
//                    // Handle a completed download.
//                }
//            }
//
//            override fun onProgressChanged(id: Int, bytesCurrent: Long, bytesTotal: Long) {
//                val percentDonef = bytesCurrent.toFloat() / bytesTotal.toFloat() * 100
//                val percentDone = percentDonef.toInt()
//            }
//
//            override fun onError(id: Int, ex: Exception) {
//                // Handle errors
//                Log.e("TransferListener",ex.message.toString())
//            }
//        })
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

