package android.itesm.edu.adoptappv1.model.Adoptar

import android.itesm.edu.adoptappv1.R
import android.itesm.edu.adoptappv1.model.Extras.Carrito
import android.itesm.edu.adoptappv1.model.Paseadores.Paseadores
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        for (i in 0..5) {
            perritos.add(
                Perrito(
                    "Calceta",
                    true,
                    "3 meses",
                    "mediana",
                    "Huellitas de acero",
                    "soy calceta adoptame soy muy juguetona",
                    "https://firebasestorage.googleapis.com/v0/b/adogtapp-4fe6a.appspot.com/o/Perritos%2FP1.jpeg?alt=media&token=e8487ff2-12f4-4b7a-b962-d58e576743c9"
                )
            )
            perritos.add(
                Perrito(
                    "Choco",
                    false,
                    "9 meses",
                    "grande",
                    "Huellitas de acero",
                    "soy Choco adoptame soy muy jugueton",
                    "https://firebasestorage.googleapis.com/v0/b/adogtapp-4fe6a.appspot.com/o/Perritos%2FP3.jpeg?alt=media&token=1bcda912-7a5e-4ff9-b557-bc7df3f4f585"
                )
            )
            perritos.add(
                Perrito(
                    "lily",
                    true,
                    "4 meses",
                    "mediana",
                    "Huellitas de acero",
                    "soy Lily adoptame soy muy juguetona",
                    "https://firebasestorage.googleapis.com/v0/b/adogtapp-4fe6a.appspot.com/o/Perritos%2FP2.jpeg?alt=media&token=d0f53b43-1e96-4526-bdba-ff642d2047fb"
                )
            )
        }

        val ProductosCarrito = arrayListOf<Carrito>()

            recycler_adoptar.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
            recycler_adoptar.adapter= PerritosAdapter(perritos)


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
