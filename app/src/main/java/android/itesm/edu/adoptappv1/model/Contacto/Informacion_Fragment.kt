package android.itesm.edu.adoptappv1.model.Contacto

import android.content.Intent
import android.itesm.edu.adoptappv1.R
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.informacion_fragment.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [Informacion_Fragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [Informacion_Fragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class Informacion_Fragment : Fragment() {
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        facebook_imageButton.setOnClickListener {
            val i= Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/Adogtapp-456828638476933/?modal=admin_todo_tour&notif_id=1556900686703830&notif_t=page_invite"))
            startActivity(i)
        }

        instagram_imageButton.setOnClickListener {
            val i= Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/pinkpigletpuppy/?hl=es-la"))
            startActivity(i)
        }

        instagram_imageButton_huellitas.setOnClickListener {
            val i= Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/huellitas_de_acero/?hl=es-la"))
            startActivity(i)
        }

        facebook_imageButton_huellitas.setOnClickListener {
            val i= Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/HuellitasDeAcero/"))
            startActivity(i)
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.informacion_fragment, container, false)
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
         * @return A new instance of fragment Informacion_Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            Informacion_Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
