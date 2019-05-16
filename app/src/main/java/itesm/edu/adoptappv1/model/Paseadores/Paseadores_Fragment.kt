package itesm.edu.adoptappv1.model.Paseadores

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
import kotlinx.android.synthetic.main.activity_register.*
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
class Paseadores_Fragment : Fragment() {
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


        return inflater.inflate(itesm.edu.adoptappv1.R.layout.paseadores_fragment, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        doAsync {
            val json= URL("https://api.myjson.com/bins/eac3s").readText()

            uiThread {
                d("alex", "json: $json")
                //val paseadores=Gson().fromJson(json,Array<Paseadores>::class.java).toString().toList()
                //Gson gson=new Gson();


            }
        }


        //  ESTE CODIGO VA A MANITA PERO SIRVE
        val paseadores = arrayListOf<Paseadores>()


        //AQUI ESTA LA LISTA
        //for (i in 0..2) {
            paseadores.add(
                Paseadores(
                    "Alex", "5570477258", "Col.Paseos de Taxqueña ", "Mar 9:00-11:00", "Vie 9:00-11:00", false,
                    "Soy Alex, estudiante de universidad y salgo con Simba, mi perrito en las mañanas a paser , podemos hacer un paseo mas largo y que tu perrito nos acompañe :) ",
                    "https://firebasestorage.googleapis.com/v0/b/adogtapp-4fe6a.appspot.com/o/Paseadores%2Falex.jpeg?alt=media&token=cbc9b9df-971e-411b-b766-480fa2c35513"
                )
            )
            paseadores.add(
                Paseadores(
                    "Eddy", "5528490744", "Col.Presidentes Ejiales", "Dom 14:00-17:00", "", false,
                    "Hola, soy Eddy. Me gusta mucho sacar a pasear a mi perrita, sobre todo los fines de semana. Me encantaría poder salir a pasear con tu perro. ",
                    "https://firebasestorage.googleapis.com/v0/b/adogtapp-4fe6a.appspot.com/o/Paseadores%2Feddy.jpeg?alt=media&token=fba8c308-4a39-4115-816b-e490d1abe87b"
                )
            )
            paseadores.add(
                Paseadores(
                    "Sam", "5521907188", "Col. Los Olivos", "Dom 14:00-17:00", "", true,
                    "Soy Sam tengo 23 años y estudio ingeniería en telecom y electrónica. Tengo una perrita Yorkie, y me gustan las mascotas.",
                    "https://firebasestorage.googleapis.com/v0/b/adogtapp-4fe6a.appspot.com/o/Paseadores%2FSamy.jpeg?alt=media&token=07df5747-0b3a-4f03-b204-2dca6bd843f2"
                )
            )


       // }

////////////////////////////////////////////////
        recycler_paseadores.layoutManager=GridLayoutManager(context,2)
        recycler_paseadores.adapter= PaseadoresAdapter(paseadores)

        Search_button.setOnClickListener {

            val lookfor = Search_text.text.toString()
            val paseadoresBuscados = arrayListOf<Paseadores>()
            paseadores!!.forEach { event ->
                if (event.colonia.contains(lookfor)) {
                    val e = event
                    Log.d("Sam", "existe una paseadora Sam :) ,$event")
                    paseadoresBuscados.add(event)
                } else {
                    Log.d("Sam", "NO existe una paseadora Sam :) ")
                }
            }
            recycler_paseadores.layoutManager=GridLayoutManager(context,2)
            recycler_paseadores.adapter= PaseadoresAdapter(paseadoresBuscados)
        }


        progressBar.visibility= View.GONE
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
            Paseadores_Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
