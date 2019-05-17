package itesm.edu.adoptappv1.model.Extras

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
import kotlinx.android.synthetic.main.extras_details.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var mContext: Context

class ExtrasDetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
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
        return inflater.inflate(R.layout.extras_details, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        val nombre= bundle!!.getString("nombre")
        val marca= bundle!!.getString("marca")
        val precio= bundle!!.getString("precio")
        val descripcion= bundle!!.getString("descripcion")
        val fotoUrl= bundle!!.getString("photo_url")
        val cantidad = bundle!!.getString("cantidad")
        val nombreCarrito=nombre
        val precioCarrito=precio
        val fotoUrlCarrito=fotoUrl
        var temp = "$$precio"
        nombre_extra_E.setText(nombre)
        marca_extra_E.setText(marca)
        precio_extra_E.setText(temp)
        temp = "Cantidad: $cantidad"
        cantidad_extra_E.text=temp
        descripcion_extra_E.setText(descripcion)
        Picasso.get().load(fotoUrl).into(foto_extra_E)


        Extra_Button_D.setOnClickListener {
            AlertDialog.Builder(context!!)
                .setMessage("Deseas agregar este producto al carrito?")
                .setPositiveButton("Agregar al carrito", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        val bundle = Bundle()
                        bundle.putString("nombre_palcarrito", nombreCarrito)
                        bundle.putString("precio_palcarrito", precioCarrito)
                        bundle.putString("photo_url_palcarrito", fotoUrlCarrito)
                        //startActivity(intent)
                        val donarFragment = CarritoFragment.newInstance()
                        donarFragment.arguments = bundle
                        (mContext as FragmentActivity).supportFragmentManager.beginTransaction()
                            .replace(itesm.edu.adoptappv1.R.id.fragment_container, donarFragment)
                            .addToBackStack(null)
                            .commit()
                    }
                })

                .create()
                .show()
        }

    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ExtrasDetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            ExtrasDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
