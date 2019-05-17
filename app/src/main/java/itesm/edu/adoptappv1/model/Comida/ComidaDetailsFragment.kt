package itesm.edu.adoptappv1.model.Comida

import android.content.Context
import android.content.DialogInterface
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso

import itesm.edu.adoptappv1.R
import itesm.edu.adoptappv1.model.Carrito.CarritoFragment
import kotlinx.android.synthetic.main.comida_details.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var mContext: Context
class ComidaDetailsFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mContext = container!!.getContext()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.comida_details, container, false)
    }

    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        val nombre= bundle!!.getString("nombre")
        val marca= bundle!!.getString("marca")
        val precio= bundle!!.getString("precio")
        val fotoUrl= bundle!!.getString("photo_url")
        val cantidad = bundle!!.getString("cantidad")
        val descripcion = bundle!!.getString("descripcion")
        val nombreCarrito=nombre
        val precioCarrito=precio
        val fotoUrlCarrito=fotoUrl
        val temp = "$$precio"
        cantidad_comida_D.text=cantidad
        descripcion_comida_D.text=descripcion
        nombre_comida_D.setText(nombre)
        marca_comida_D.setText(marca)
        precio_comida_D.text=temp
        Picasso.get().load(fotoUrl).into(foto_comida_D)


        buttonComida.setOnClickListener {
            AlertDialog.Builder(context!!)
                .setMessage("Deseas agregar este producto al carrito?")
                .setPositiveButton("Agregar al carrito", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        val bundle = Bundle()

                        bundle.putString("nombre_palcarrito", nombreCarrito)
                        bundle.putString("precio_palcarrito", precioCarrito)
                        bundle.putString("photo_url_palcarrito", fotoUrlCarrito)
                        val donarFragment = CarritoFragment.newInstance()
                        donarFragment.arguments = bundle
                        (mContext as FragmentActivity).supportFragmentManager.beginTransaction()
                            .replace(itesm.edu.adoptappv1.R.id.fragment_container, donarFragment)
                            .addToBackStack(null)
                            .commit()
                        //supportFragmentManager.beginTransaction()
                            //.replace(R.id.fragment_container, donarFragment).commit();

                    }

                }

                )

                .create()
                .show()
        }
    }


    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            ComidaDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
