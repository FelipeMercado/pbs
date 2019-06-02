package com.pbs.PBshop.adapters
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.pbs.PBshop.Activities.Clientes.verCliente
import com.pbs.PBshop.Activities.Folios.Folio
import com.pbs.PBshop.Activities.Folios.verFolio
import com.pbs.PBshop.R

class FolioAdapter(private val listaFolio: List<Folio>,origen:String): RecyclerView.Adapter<FolioAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): FolioAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.folio_item, parent, false)
        return ViewHolder(view)


    }

    override fun getItemCount(): Int {
        return listaFolio.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            holder.Folio = listaFolio[position]
            holder.carOwner.setText(listaFolio[position].client.name + " " + listaFolio[position].client.lastName)
            holder.brand.setText(listaFolio[position].vehicle.brand)
            holder.carModel.setText(listaFolio[position].vehicle.model)
            holder.status.setText(listaFolio[position].workshop_record_sate.state)
            holder.identificador.setText(listaFolio[position].workshop_record_sate.state)






        holder.itemView.setOnClickListener { v ->
            val context = v.context
            val intent = Intent(context, verFolio::class.java)
            intent.putExtra("email", listaFolio[position].id)
            context.startActivity(intent)
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val carOwner: TextView = itemView.findViewById(R.id.clienteS)
        val brand: TextView = itemView.findViewById(R.id.carBrand)
        val carModel:TextView = itemView.findViewById(R.id.model)
        val status:TextView = itemView.findViewById(R.id.status)
        val identificador:TextView = itemView.findViewById(R.id.identificador)





        var Folio: Folio? = null


        override fun toString(): String {
            return """${super.toString()} '${carOwner.text}'"""
        }
    }
}


