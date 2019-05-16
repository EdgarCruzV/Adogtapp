package itesm.edu.adoptappv1.model.Adoptar

import android.content.Intent
import itesm.edu.adoptappv1.R
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class PerritosAdapter(private val perrito: List<Perrito>) : RecyclerView.Adapter<PerritosAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(perrito[position].perrito_fotoUrl).into(holder.image)
        holder.nombre.text = perrito[position].perrito_nombre
        if(perrito[position].hembra){
            holder.hembra.visibility=View.VISIBLE
            holder.macho.visibility=View.GONE
        }else{
            holder.macho.visibility=View.VISIBLE
            holder.hembra.visibility=View.GONE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.perrito_row, parent ,false)
        val holder = ViewHolder(view)
        view.setOnClickListener{
            val intent =Intent(parent.context, AdoptarDetails::class.java)
            intent.putExtra("nombre", perrito[holder.adapterPosition].perrito_nombre)
            intent.putExtra("edad", perrito[holder.adapterPosition].edad)
            intent.putExtra("hembra", perrito[holder.adapterPosition].hembra)
            intent.putExtra("talla", perrito[holder.adapterPosition].talla)
            intent.putExtra("contacto", perrito[holder.adapterPosition].perrito_contacto)
            intent.putExtra("descripcion", perrito[holder.adapterPosition].descripcion)
            intent.putExtra("photo_url", perrito[holder.adapterPosition].perrito_fotoUrl)
            parent.context.startActivity(intent)
        }

        return holder
    }

    override fun getItemCount()= perrito.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.foto_perrito)
        val nombre: TextView = itemView.findViewById(R.id.nombre_perrito)
        val hembra: ImageView=itemView.findViewById(R.id.hembra_true)
        val macho: ImageView=itemView.findViewById(R.id.hembra_false)

    }


}