package itesm.edu.adoptappv1.model.Extras

import android.content.Context
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import itesm.edu.adoptappv1.R
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class ExtrasAdapter(private val extras: List<Extra>) : RecyclerView.Adapter<ExtrasAdapter.ViewHolder>() {
    private lateinit var mContext: Context
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val temp = "$${extras[position].precio_extra}"
        Picasso.get().load(extras[position].extra_fotoUrl).into(holder.image)
        holder.nombre.text = extras[position].nombre_extra
        holder.marca.text=extras[position].marca_extra
        holder.precio.text=temp
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.getContext()
        val view = LayoutInflater.from(parent.context).inflate(R.layout.extras_row, parent ,false)
        val holder = ViewHolder(view)

        view.setOnClickListener{
            val bundle = Bundle()
            bundle.putString("nombre", extras[holder.adapterPosition].nombre_extra)
            bundle.putString("precio", extras[holder.adapterPosition].precio_extra)
            bundle.putString("marca", extras[holder.adapterPosition].marca_extra)
            bundle.putString("descripcion", extras[holder.adapterPosition].descripcion_extra)
            bundle.putString("photo_url", extras[holder.adapterPosition].extra_fotoUrl)
            bundle.putString("cantidad",extras[holder.adapterPosition].cantidad_extra)
            val adoptarFragment = ExtrasDetailsFragment.newInstance()
            adoptarFragment.arguments = bundle
            (mContext as FragmentActivity).supportFragmentManager.beginTransaction()
                .replace(itesm.edu.adoptappv1.R.id.fragment_container, adoptarFragment)
                .addToBackStack(null)
                .commit()
        }
        return holder
    }

    override fun getItemCount()= extras.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.foto_extra)
        val nombre: TextView = itemView.findViewById(R.id.nombre_extra)
        val marca: TextView = itemView.findViewById(R.id.marca_extra)
        val precio: TextView = itemView.findViewById(R.id.precio_extra)

    }


}