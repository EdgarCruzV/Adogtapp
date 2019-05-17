package itesm.edu.adoptappv1.model.Adoptar

import android.content.Intent
import android.os.Bundle
import itesm.edu.adoptappv1.R
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import android.R.attr.fragment
import android.content.Context
import android.support.v4.app.FragmentActivity
import android.text.TextUtils.replace


class PerritosAdapter(private val perrito: List<Perrito>)
    : RecyclerView.Adapter<PerritosAdapter.ViewHolder>()  {
    private lateinit var mContext: Context

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
        mContext = parent.getContext()
        val view = LayoutInflater.from(parent.context).inflate(itesm.edu.adoptappv1.R.layout.perrito_row, parent ,false)
        val holder = ViewHolder(view)
        view.setOnClickListener{
            val bundle = Bundle()
            bundle.putString("nombre", perrito[holder.adapterPosition].perrito_nombre)
            bundle.putString("edad", perrito[holder.adapterPosition].edad)
            bundle.putString("hembra", perrito[holder.adapterPosition].hembra.toString())
            bundle.putString("talla", perrito[holder.adapterPosition].talla)
            bundle.putString("contacto", perrito[holder.adapterPosition].perrito_contacto)
            bundle.putString("descripcion", perrito[holder.adapterPosition].descripcion)
            bundle.putString("photo_url", perrito[holder.adapterPosition].perrito_fotoUrl)
            val adoptarFragment = DetailsFragment.newInstance()
            adoptarFragment.arguments = bundle
            //supportFragmentManager.beginTransaction().replace(itesm.edu.adoptappv1.R.id.fragment_container, adoptarFragment).commit();
            (mContext as FragmentActivity).supportFragmentManager.beginTransaction()
                .replace(itesm.edu.adoptappv1.R.id.fragment_container, adoptarFragment)
                .addToBackStack(null)
                .commit()
        }

        return holder
    }

    override fun getItemCount()= perrito.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(itesm.edu.adoptappv1.R.id.foto_perrito)
        val nombre: TextView = itemView.findViewById(itesm.edu.adoptappv1.R.id.nombre_perrito)
        val hembra: ImageView=itemView.findViewById(itesm.edu.adoptappv1.R.id.hembra_true)
        val macho: ImageView=itemView.findViewById(itesm.edu.adoptappv1.R.id.hembra_false)

    }


}