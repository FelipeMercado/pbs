package com.pbs.PBshop.adapters

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.widget.TextView

import com.pbs.PBshop.Activities.Clientes.Cliente
import com.pbs.PBshop.Activities.Clientes.verCliente
import com.pbs.PBshop.Activities.Folios.verFolio
import com.pbs.PBshop.R

class ClienteAdapter(private val listaCliente: List<Cliente>,val origen:String): RecyclerView.Adapter<ClienteAdapter.ViewHolder>() {
     override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ClienteAdapter.ViewHolder {
       val view =LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return ViewHolder(view)


    }

    override fun getItemCount(): Int {
       return listaCliente.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.cliente=listaCliente[position]
        holder.clienteSeleccion.text=listaCliente[position].name+" "+listaCliente[position].lastName

        holder.itemView.setOnClickListener{
            v->
            val context =v.context
            if(origen.equals("menu")) {
                val intent = Intent(context, verCliente::class.java)
                intent.putExtra("iden", listaCliente[position].id)
                intent.putExtra("name", listaCliente[position].name)
                intent.putExtra("lastName", listaCliente[position].lastName)
                intent.putExtra("phone", listaCliente[position].phone)
                intent.putExtra("email", listaCliente[position].email)
                context.startActivity(intent)
            }else
                if(origen.equals("folio")) {
                    val intent = Intent(context, verFolio::class.java)
                    intent.putExtra("iden", listaCliente[position].id)
                    intent.putExtra("name", listaCliente[position].name)
                    intent.putExtra("lastName", listaCliente[position].lastName)
                    intent.putExtra("phone", listaCliente[position].phone)
                    intent.putExtra("email", listaCliente[position].email)
                    context.startActivity(intent)
                }


        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val clienteSeleccion: TextView = itemView.findViewById(R.id.clienteSeleccion)
        var cliente: Cliente? = null

        override fun toString(): String {
            return """${super.toString()} '${clienteSeleccion.text}'"""
        }
    }


}