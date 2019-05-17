package itesm.edu.adoptappv1.model.Adoptar

import itesm.edu.adoptappv1.model.Extras.Carrito
import itesm.edu.adoptappv1.model.Paseadores.Paseadores
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import itesm.edu.adoptappv1.R
import kotlinx.android.synthetic.main.adoptar_fragment.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [Adoptar_Fragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [Adoptar_Fragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class Adoptar_Fragment : Fragment() {
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

        return inflater.inflate(R.layout.adoptar_fragment, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val perritos = arrayListOf<Perrito>()
        //DataBase
        val db = FirebaseDatabase.getInstance().getReference()

        db.addValueEventListener(

            object : ValueEventListener {

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (d in dataSnapshot.child("perritos").children) {
                        val foto = d.child("foto").value!!.toString()
                        val edad = d.child("edad").value!!.toString()
                        val talla = d.child("talla").value!!.toString()
                        val descripcion = d.child("descripcion").value!!.toString()
                        val nombre = d.child("nombre").value!!.toString()
                        val contacto = d.child("contacto").value!!.toString()
                        val genero = d.child("genero").value!!.toString()
                        var hembra = false
                        if (genero == "Hembra"){
                            hembra = true
                        }
                        perritos.add(Perrito(nombre,hembra,edad,talla,contacto,descripcion, foto))

                    }
                    try{
                        recycler_adoptar!!.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL
                            , false)
                        recycler_adoptar!!.adapter= PerritosAdapter(perritos)
                    }catch(e:Exception){

                    }

                }

                override fun onCancelled(databaseError: DatabaseError) {

                }
            }
        )

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
         * @return A new instance of fragment Adoptar_Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            Adoptar_Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}
