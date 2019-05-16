package itesm.edu.adoptappv1.model.Carrito

import itesm.edu.adoptappv1.R
import itesm.edu.adoptappv1.model.Extras.Carrito
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso


class CarritoAdapter(private val carrito: List<Carrito>) : RecyclerView.Adapter<CarritoAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(carrito[position].carrito_fotoUrl).into(holder.image)
        holder.nombre.text = carrito[position].nombre_carrito
        holder.precio.text=carrito[position].precio_carrito
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.carrito_row, parent ,false)
        val holder = ViewHolder(view)

        return holder
    }

    override fun getItemCount()= carrito.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.fotoCarrito)
        val nombre: TextView = itemView.findViewById(R.id.nombre_carrito)
        val precio: TextView = itemView.findViewById(R.id.precio_carrito)
    }


}