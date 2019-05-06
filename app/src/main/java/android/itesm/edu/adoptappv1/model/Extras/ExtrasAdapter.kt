package android.itesm.edu.adoptappv1.model.Extras

import android.content.Intent
import android.itesm.edu.adoptappv1.R
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.extras_details.view.*
import kotlinx.android.synthetic.main.extras_row.view.*

class ExtrasAdapter(private val extras: List<Extra>) : RecyclerView.Adapter<ExtrasAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(extras[position].extra_fotoUrl).into(holder.image)
        holder.nombre.text = extras[position].nombre_extra
        holder.marca.text=extras[position].marca_extra
        holder.precio.text=extras[position].precio_extra
        if (extras[position].oferta_extra){
        holder.oferta.visibility=View.VISIBLE
        }else{
            holder.oferta.visibility=View.GONE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.extras_row, parent ,false)
        val holder = ViewHolder(view)

        view.setOnClickListener{
            val intent =Intent(parent.context, ExtrasDetails::class.java)
            intent.putExtra("nombre", extras[holder.adapterPosition].nombre_extra)
            intent.putExtra("precio", extras[holder.adapterPosition].precio_extra)
            intent.putExtra("marca", extras[holder.adapterPosition].marca_extra)
            intent.putExtra("descripcion", extras[holder.adapterPosition].descripcion_extra)
            intent.putExtra("photo_url", extras[holder.adapterPosition].extra_fotoUrl)
            parent.context.startActivity(intent)
        }
        return holder
    }

    override fun getItemCount()= extras.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.foto_extra)
        val nombre: TextView = itemView.findViewById(R.id.nombre_extra)
        val marca: TextView = itemView.findViewById(R.id.marca_extra)
        val precio: TextView = itemView.findViewById(R.id.precio_extra)
        val oferta: ImageView=itemView.findViewById(R.id.oferta_extra)

    }


}