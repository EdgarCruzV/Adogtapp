package itesm.edu.adoptappv1.model.Comida

import android.content.Intent
import itesm.edu.adoptappv1.model.Comida.ComidaDetails
import itesm.edu.adoptappv1.R
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import itesm.edu.adoptappv1.model.Comida.Comida

class ComidaAdapter(private val comidas: List<Comida>) : RecyclerView.Adapter<ComidaAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(comidas[position].comida_fotoUrl).into(holder.image)
        holder.nombre.text = comidas[position].comida_nombre
        holder.marca.text=comidas[position].marca_comida
        if (comidas[position].oferta_comida){
        holder.oferta.visibility=View.VISIBLE
        }else{
            holder.oferta.visibility=View.GONE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.comida_row, parent ,false)
        val holder = ViewHolder(view)

        view.setOnClickListener{

            val intent =Intent(parent.context, ComidaDetails::class.java)
            intent.putExtra("nombre", comidas[holder.adapterPosition].comida_nombre)
            intent.putExtra("marca", comidas[holder.adapterPosition].marca_comida)
            intent.putExtra("precio", comidas[holder.adapterPosition].precio_comida)
            intent.putExtra("photo_url", comidas[holder.adapterPosition].comida_fotoUrl)
            parent.context.startActivity(intent)
        }


        return holder
    }

    override fun getItemCount()= comidas.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.fotoComida)
        val nombre: TextView = itemView.findViewById(R.id.nombre_comida)
        val marca: TextView = itemView.findViewById(R.id.marca_comida)
        val oferta: ImageView=itemView.findViewById(R.id.oferta_comida)
    }


}