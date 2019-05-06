package android.itesm.edu.adoptappv1.model.Paseadores

import android.content.Intent
import android.itesm.edu.adoptappv1.R
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class PaseadoresAdapter(private val paseadores: List<Paseadores>) : RecyclerView.Adapter<PaseadoresAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(paseadores[position].fotoUrl).into(holder.image)
        holder.nombre.text = paseadores[position].nombre
        holder.colonia.text=paseadores[position].colonia
        if (paseadores[position].estrella){
        holder.estrella.visibility=View.VISIBLE
        }else{
            holder.estrella.visibility=View.GONE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.paseadores_row, parent ,false)
        val holder = ViewHolder(view)

        view.setOnClickListener{
            val intent =Intent(parent.context, PaseadoresDetails::class.java)
            intent.putExtra("nombre", paseadores[holder.adapterPosition].nombre)
            intent.putExtra("telefono", paseadores[holder.adapterPosition].telefono)
            intent.putExtra("colonia", paseadores[holder.adapterPosition].colonia)
            intent.putExtra("horario1", paseadores[holder.adapterPosition].horario1)
            intent.putExtra("horario2", paseadores[holder.adapterPosition].horario2)
            intent.putExtra("descripcion", paseadores[holder.adapterPosition].descripcion)
            intent.putExtra("photo_url", paseadores[holder.adapterPosition].fotoUrl)
            parent.context.startActivity(intent)
        }
        return holder
    }

    override fun getItemCount()= paseadores.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.foto)
        val nombre: TextView = itemView.findViewById(R.id.nombre)
        val colonia: TextView = itemView.findViewById(R.id.colonia)
        val estrella: ImageView=itemView.findViewById(R.id.estrella_paseador)
    }


}