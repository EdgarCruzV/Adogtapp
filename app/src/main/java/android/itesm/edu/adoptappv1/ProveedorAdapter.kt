package android.itesm.edu.adoptappv1

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import kotlinx.android.synthetic.main.perrito_row.view.*
import kotlinx.android.synthetic.main.proveedor_row.view.*
import java.text.FieldPosition

class ProveedorAdapter(private val proveedor: List<String>) : RecyclerView.Adapter<ProveedorAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent:ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.proveedor_row, parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount()= proveedor.size

    override fun onBindViewHolder(holder: ViewHolder, position : Int) {
    holder.proveedor_TextView.text=proveedor[position]
    }

    class ViewHolder (view: View): RecyclerView.ViewHolder(view){
        val proveedor_TextView= view.proveedor_TextView
    }

}
