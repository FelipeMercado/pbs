package com.pbs.PBshop.Activities.Clientes

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import retrofit2.Callback
import com.pbs.PBshop.R
import com.pbs.PBshop.`interface`.ClienteService
import com.pbs.PBshop.`interface`.ServiceBuilder
import kotlinx.android.synthetic.main.activity_ver_cliente.*
import retrofit2.Call
import retrofit2.Response

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class verCliente : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_ver_cliente)
        val bundle = intent.extras
        val ident:Int=bundle.get("iden").toString().toInt()

        if(ident==-1){
            guardar.text="Guardar"
            EliminarCliente.text="limpiar"

        }else {
            guardar.text="Actualizar"
            EliminarCliente.text="Eliminar"

        }

        nameE.setText(bundle.get("name").toString())
        lastNameE.setText(bundle.get("lastName").toString())
        phoneE.setText(bundle.get("phone").toString())
        emailE.setText(bundle.get("email").toString())




        EliminarCliente.setOnClickListener{
            if (EliminarCliente.text=="Limpiar") {

                nameE.setText("")
                lastNameE.setText("")
                phoneE.setText("")
                emailE.setText("")


            } else {
                val clienteService=
                    ServiceBuilder.buildService(ClienteService::class.java)


                val requestCall=clienteService.deleteCliente(ident)
                requestCall.enqueue(object:Callback<Unit> {

                    override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                           if(response.isSuccessful){
                               finish()
                               Toast.makeText(this@verCliente, "Se elimino correctamente", Toast.LENGTH_SHORT).show()
                               print(response)

                           }else
                           {
                               Toast.makeText(this@verCliente, "Fallo al eliminar", Toast.LENGTH_SHORT).show()
                               print(response)
                           }
                    }
                    override fun onFailure(call: Call<Unit>, t: Throwable) {
                        Toast.makeText(this@verCliente, "Fallo al eliminar", Toast.LENGTH_SHORT).show()
                        print(call.toString()+"------"+ident+"--------")

                    }

                })



            }



        }

        guardar.setOnClickListener {


            if (guardar.text == "Actualizar") {


            } else

                if (guardar.text=="Guardar"){


                if (nameE.text.isEmpty() || lastNameE.text.isEmpty() || phoneE.text.isEmpty() || emailE.text.isEmpty()) {
                    Toast.makeText(this, "Los campos no pueden estar vácios", Toast.LENGTH_SHORT).show()

                } else {
                    var cliente: Cliente = (Cliente(
                        ident,
                        nameE.text.toString(),
                        lastNameE.text.toString(),
                        phoneE.text.toString(),
                        emailE.text.toString()
                    ))
                    //almacenar
                    // Toast.makeText(this, "Se guardo", Toast.LENGTH_SHORT).show()
                    //listaCliente.add(cliente)

                    val clienteService = ServiceBuilder.buildService(ClienteService::class.java)
                    val requestCall = clienteService.addCliente(cliente)


                    requestCall.enqueue(object : Callback<Cliente> {
                        override fun onFailure(call: Call<Cliente>, t: Throwable) {
                            Toast.makeText(this@verCliente, "Fallo el registro", Toast.LENGTH_SHORT).show()

                        }

                        override fun onResponse(call: Call<Cliente>, response: Response<Cliente>) {
                            if (response.isSuccessful) {
                                finish()
                                var newlyClienteCreado = response.body()
                                Toast.makeText(this@verCliente, "Fue exitoso el registro", Toast.LENGTH_SHORT).show()
                                print(newlyClienteCreado.toString())
                            } else
                                Toast.makeText(this@verCliente, "Fallo el registro", Toast.LENGTH_SHORT).show()
                                print(response.toString())
                        }

                    })


                }
            }


        }
    }



}

