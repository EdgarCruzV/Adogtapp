package itesm.edu.adoptappv1.model.Extras

import android.icu.text.StringSearch
import itesm.edu.adoptappv1.ProveedorAdapter
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.paseadores_fragment.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL
import java.util.*
import java.util.stream.IntStream
import android.R
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import itesm.edu.adoptappv1.model.Comida.Comida
import itesm.edu.adoptappv1.model.Comida.ComidaAdapter
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.comida_fragment.*
import kotlinx.android.synthetic.main.extras_fragment.*
import kotlinx.android.synthetic.main.paseadores_details.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [Paseadores_Fragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ Paseadores Fragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class Extras_Fragment : Fragment() {
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
        // Inflate the layout for this fragment


        return inflater.inflate(itesm.edu.adoptappv1.R.layout.extras_fragment, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        doAsync {
            val json= URL("https://api.myjson.com/bins/eac3s").readText()

            uiThread {
                d("alex", "json: $json")
                //val extras=Gson().fromJson(json,Array<Extra>::class.java).toString().toList()
                //Gson gson=new Gson();


            }
        }


        val extras = arrayListOf<Extra>()
        val db = FirebaseDatabase.getInstance().getReference()

        db.addValueEventListener(

            object : ValueEventListener {

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (d in dataSnapshot.child("extras").children) {
                        val articulo = d.child("articulo").value!!.toString()
                        val cantidad = d.child("cantidad").value!!.toString()
                        val foto = d.child("foto").value!!.toString()
                        val descripcion = d.child("descripcion").value!!.toString()
                        val marca = d.child("marca").value!!.toString()
                        val precio = d.child("precio").value!!.toString()

                        extras.add(Extra(articulo,marca,precio,descripcion, foto, cantidad))

                    }
                    try{

                        recycler_extra.layoutManager=GridLayoutManager(context,1)
                        recycler_extra.adapter= ExtrasAdapter(extras)
                    }catch(e:Exception){

                    }

                }

                override fun onCancelled(databaseError: DatabaseError) {

                }
            }
        )



//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Search_button_extra.setOnClickListener {

            val lookforExtras = Search_text_extra.text.toString()
            val extrasBuscados = arrayListOf<Extra>()
            extras!!.forEach { event ->
                if (event.nombre_extra.contains(lookforExtras)) {
                    val e = event
                    Log.d("Sam", "existe una paseadora Sam :) ,$event")
                    extrasBuscados.add(event)
                } else {
                    Log.d("Sam", "NO existe una paseadora Sam :) ")
                }
            }
            recycler_extra.layoutManager=GridLayoutManager(context,1)
            recycler_extra.adapter= ExtrasAdapter(extrasBuscados)
        }

        recycler_proveedor_extra.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)

        progressBar_extra.visibility= View.GONE
    }


    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
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
         * @return A new instance of fragment Tienda_Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            Extras_Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
