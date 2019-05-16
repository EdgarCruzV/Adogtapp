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

        for (i in 0..1) {
            perritos.add(
                Perrito(
                    "Bigotes",
                    true,
                    "3 años",
                    "mediana",
                    "Huellitas de acero",
                    " Hola mi nombre es Bigotes y tengo 3 años de edad, soy tímida, tranquila y obediente me gusta a salir a pasear en las mañanas.",
                    "https://firebasestorage.googleapis.com/v0/b/adogtapp-4fe6a.appspot.com/o/Perritos%2FWhatsApp%20Image%202019-04-29%20at%2011.23.18%20AM(2).jpeg?alt=media&token=cdfe77df-2d53-4288-a2a1-8e29a35341c3"
                )
            )
            perritos.add(
                Perrito(
                    "Lacky",
                    true,
                    "2 años",
                    "mediana",
                    "Huellitas de acero",
                    " Hola mi nombre es Lacky y tengo 2 años de edad, soy tranquila y obediente me gusta salir a pasear en las mañanas.",
                    "https://firebasestorage.googleapis.com/v0/b/adogtapp-4fe6a.appspot.com/o/Perritos%2FWhatsApp%20Image%202019-04-29%20at%2011.23.19%20AM.jpeg?alt=media&token=9984b2b3-bd38-4ce5-88ed-98ca59b3f4a5"
                )
            )
            perritos.add(
                Perrito(
                    "Mami",
                    true,
                    "3 años",
                    "mediana",
                    "Huellitas de acero",
                    " Hola mi nombre es Mami y tengo 3 años de edad soy muy tímida con mis compañeros, pero cariñosa con las personas y obediente, me gustaría formar parte de una familia.",
            "https://firebasestorage.googleapis.com/v0/b/adogtapp-4fe6a.appspot.com/o/Perritos%2FWhatsApp%20Image%202019-04-29%20at%2011.23.18%20AM.jpeg?alt=media&token=77537798-d2d2-4da5-9721-311f75bbab98"
            )
            )
            perritos.add(
                Perrito(
                    "Rayita",
                    true,
                    "3 años",
                    "mediana",
                    "Huellitas de acero",
                    " Hola mi nombre es Rayita y tengo 3 años de edad soy muy juguetona, cariñosa y aunque no tengo parte de mi patita trasera derecha me gusta nadar en el mar y salir a caminar por las mañanas.",
            "https://firebasestorage.googleapis.com/v0/b/adogtapp-4fe6a.appspot.com/o/Perritos%2FWhatsApp%20Image%202019-04-29%20at%2011.23.19%20AM(2).jpeg?alt=media&token=7dee37f5-dc2c-4cb6-86b6-ef191320648f"
            )
            )
            perritos.add(
                Perrito(
                    "Chaparra",
                    true,
                    "2 años",
                    "mediana",
                    "Huellitas de acero",
                    " Hola mi nombre es Chaparra y tengo 2 años de edad soy juguetona con mis compañeros y tímida con las personas, deseo que me adopte una familia que me dé mucho cariño.",
            "https://firebasestorage.googleapis.com/v0/b/adogtapp-4fe6a.appspot.com/o/Perritos%2FWhatsApp%20Image%202019-04-29%20at%2011.23.21%20AM(4).jpeg?alt=media&token=2bd5dfb2-bc02-489b-a9e7-e7dcc8c04d7f"
            )
            )
            perritos.add(
                Perrito(
                    "Tina",
                    true,
                    "1 años",
                    "mediana",
                    "Huellitas de acero",
                    " Hola mi nombre es Tina y tengo 1 año de edad, ya casi cumplo los dos, soy muy juguetona y amigable, me gusta salir a pasear por las mañanas.",
            "https://firebasestorage.googleapis.com/v0/b/adogtapp-4fe6a.appspot.com/o/Perritos%2FWhatsApp%20Image%202019-04-29%20at%2011.23.20%20AM.jpeg?alt=media&token=0af02fca-7bb3-43b8-b72f-f3e2738b4e1b"
            )
            )
            perritos.add(
                Perrito(
                    "Kira",
                    true,
                    "4 meses",
                    "mediana",
                    "Huellitas de acero",
                    " Hola mi nombre es Kira y tengo 4 meses de edad, soy amigable y cariñosa, no veo con mi ojito izquierdo, deseo que me adopte una familia que me dé mucho cariño.",
            "https://firebasestorage.googleapis.com/v0/b/adogtapp-4fe6a.appspot.com/o/Perritos%2FWhatsApp%20Image%202019-04-29%20at%2011.23.20%20AM(2).jpeg?alt=media&token=321e68b1-ab2c-47be-aebc-e3cebd14864d"
            )
            )

            perritos.add(
                Perrito(
                    "Canelita",
                    true,
                    "2 años",
                    "mediana",
                    "Huellitas de acero",
                    " Hola mi nombre es Canelita y tengo 2 años de edad, soy muy tímida con las personas, pero juguetona con mis compañeros, deseo que me adopte una familia que me de cariño.",
            "https://firebasestorage.googleapis.com/v0/b/adogtapp-4fe6a.appspot.com/o/Perritos%2FWhatsApp%20Image%202019-04-29%20at%2011.23.20%20AM(3).jpeg?alt=media&token=d36ab3fe-8e01-456c-b608-ce90820bce8b"
            )
            )
            perritos.add(
                Perrito(
                    "Chucho",
                    false,
                    "3 años",
                    "mediano",
                    "Huellitas de acero",
                    " Hola mi nombre es Chucho y tengo 3 años de edad, soy muy juguetón y amigable con las personas y mis compañeros, me gusta salir a pasear.",
            "https://firebasestorage.googleapis.com/v0/b/adogtapp-4fe6a.appspot.com/o/Perritos%2FWhatsApp%20Image%202019-04-29%20at%2011.22.33%20AM.jpeg?alt=media&token=0e27279a-c62b-4ede-bc65-0b65f50b1154"
            )
            )
            perritos.add(
                Perrito(
                    "Bell",
                    false,
                    "3 meses",
                    "mediana",
                    "Huellitas de acero",
                    "Hola mi nombre es Bella y tengo 3 meses de edad, soy muy juguetona y obediente, deseo formar parte de una familia que me de cariño.",
            "https://firebasestorage.googleapis.com/v0/b/adogtapp-4fe6a.appspot.com/o/Perritos%2FWhatsApp%20Image%202019-04-29%20at%2011.23.21%20AM(2).jpeg?alt=media&token=65e5cfcd-04ce-42fa-b6e3-f70a01f1b093"
            )
            )
            perritos.add(
                Perrito(
                    "Luna",
                    true,
                    "2 años",
                    "mediana",
                    "Huellitas de acero",
                    "Hola mi nombre es Luna y tengo 2 años de edad, soy tranquila y obediente, me gusta salir a pasear todos los días.",
            "https://firebasestorage.googleapis.com/v0/b/adogtapp-4fe6a.appspot.com/o/Perritos%2FWhatsApp%20Image%202019-04-29%20at%2011.23.19%20AM(1).jpeg?alt=media&token=67cbbaee-ce6d-4012-9c8c-d26a75430397"
            )
            )
            perritos.add(
                Perrito(
                    "Dolby",
                    false,
                    "1 años",
                    "mediano",
                    "Huellitas de acero",
                    "Hola mi nombre es Dolby y tengo apenas 1 año de edad, soy muy juguetón con las personas y mis compañeros, también soy cariñoso; me gusta salir a pasear por las mañanas con mi hermana Toña.",
            "https://firebasestorage.googleapis.com/v0/b/adogtapp-4fe6a.appspot.com/o/Perritos%2FWhatsApp%20Image%202019-04-29%20at%2011.23.21%20AM(3).jpeg?alt=media&token=68e4cffd-f0af-4eaf-bc69-e80a66c9f3ac"
            )
            )
            perritos.add(
                Perrito(
                    "Toña",
                    true,
                    "1 años",
                    "mediana",
                    "Huellitas de acero",
                    "Hola mi nombre es Toña y tengo apenas 1 año de edad, me gusta jugar con mis compañeros, además soy muy cariñosa, me gusta pasear en las mañanas con mi hermano Dolby.",
            "https://firebasestorage.googleapis.com/v0/b/adogtapp-4fe6a.appspot.com/o/Perritos%2FWhatsApp%20Image%202019-04-29%20at%2011.23.21%20AM(1).jpeg?alt=media&token=67f75546-ba37-4a36-8a2a-607933d270d4"
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
