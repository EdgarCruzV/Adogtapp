package itesm.edu.adoptappv1.model.Carrito

import android.content.Intent
import itesm.edu.adoptappv1.MainActivity
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import itesm.edu.adoptappv1.R
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_envio.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [envio.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [envio.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class envio : Fragment() {
    lateinit var _db:DatabaseReference
    lateinit var uid : String
    var userNameGlobal : String = ""
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
        return inflater.inflate(R.layout.fragment_envio, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        enviar_button.setOnClickListener {
            val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
            var user: FirebaseUser? = mAuth.getCurrentUser()

            _db = FirebaseDatabase.getInstance().getReference("/users")
            if (user != null) {
                if (user!!.uid != null) {
                    uid = user!!.uid
                }
                val ref = FirebaseDatabase.getInstance().reference
                ref.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        // Code
                        val userName = dataSnapshot.child("users")
                            .child(uid).child("username").value.toString()
                        if (userName != "null") {
                            userNameGlobal = userName
                            //Toast.makeText(applicationContext, "$userNameGlobal", Toast.LENGTH_SHORT).show()
                        }
                        val direccionAFirebase = DireccionesDeEnvio(uid, editText.text.toString(), editText2.text.toString(), editText3.text.toString(), editText4.text.toString(), editText5.text.toString(), editText8.text.toString(), editText7.text.toString())

                        val uid = FirebaseAuth.getInstance().uid ?: ""
                        val ref = FirebaseDatabase.getInstance().getReference("/DireccionesDeEnvio/$userNameGlobal")



                        ref.setValue(direccionAFirebase)
                            .addOnSuccessListener {
                                //Log.d("AdogtApp", "Cliente uploaded")
                            }
                            .addOnFailureListener {
                                //Log.d("AdogtApp", "FAILED: ${it.message}")
                            }

                    }
                    override fun onCancelled(databaseError: DatabaseError) {
                        // Code
                    }
                })
            }
            Toast.makeText(context, "Información Enviada", Toast.LENGTH_SHORT).show()
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)

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
         * @return A new instance of fragment envio.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            envio().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
data class DireccionesDeEnvio(val uid : String, val nombre : String, val direccion : String, val pais : String, val estado : String, val ciudad : String, val codigoPostal : String, val telefono : String)
