package itesm.edu.adoptappv1.model.Comida

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

class ComidaAdapter(private val comidas: List<Comida>) : RecyclerView.Adapter<ComidaAdapter.ViewHolder>() {
    private lateinit var mContext: Context
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(comidas[position].comida_fotoUrl).into(holder.image)
        holder.nombre.text = comidas[position].comida_nombre
        holder.marca.text=comidas[position].marca_comida
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.getContext()
        val view = LayoutInflater.from(parent.context).inflate(R.layout.comida_row, parent ,false)
        val holder = ViewHolder(view)

        view.setOnClickListener{

            val bundle = Bundle()
            bundle.putString("nombre", comidas[holder.adapterPosition].comida_nombre)
            bundle.putString("marca", comidas[holder.adapterPosition].marca_comida)
            bundle.putString("precio", comidas[holder.adapterPosition].precio_comida)
            bundle.putString("cantidad", comidas[holder.adapterPosition].cantidad_comida)
            bundle.putString("descripcion", comidas[holder.adapterPosition].descripcion_comida)
            bundle.putString("photo_url", comidas[holder.adapterPosition].comida_fotoUrl)
            val adoptarFragment = ComidaDetailsFragment.newInstance()
            adoptarFragment.arguments = bundle
            (mContext as FragmentActivity).supportFragmentManager.beginTransaction()
                .replace(itesm.edu.adoptappv1.R.id.fragment_container, adoptarFragment)
                .addToBackStack(null)
                .commit()
        }


        return holder
    }

    override fun getItemCount()= comidas.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.fotoComida)
        val nombre: TextView = itemView.findViewById(R.id.nombre_comida)
        val marca: TextView = itemView.findViewById(R.id.marca_comida)

    }


}