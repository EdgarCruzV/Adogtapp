package itesm.edu.adoptappv1.model.Contacto

import android.content.Intent
import itesm.edu.adoptappv1.R
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.informacion_fragment.*
import kotlinx.android.synthetic.main.informacion_fragment.view.*
import kotlinx.android.synthetic.main.nav_header.view.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

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

        Glide.with(context!!)
            .load(resources.getDrawable(R.drawable.facebook_logos))
            .into(facebook_imageButton)

        Glide.with(context!!)
            .load(resources.getDrawable(R.drawable.facebook_logos))
            .into(facebook_imageButton_huellitas)

        Glide.with(context!!)
            .load(resources.getDrawable(R.drawable.instagram_png12))
            .into(instagram_imageButton)

        Glide.with(context!!)
            .load(resources.getDrawable(R.drawable.instagram_png12))
            .into(instagram_imageButton_huellitas)
        Glide.with(context!!)
            .load(resources.getDrawable(R.drawable.huellitas))
            .into(huella)


        facebook_imageButton.setOnClickListener {
            val i= Intent(Intent.ACTION_VIEW, Uri
                .parse("https://www.facebook.com/Adogtapp-456828638476933/?modal=admin_todo_tour&notif_id=1556900686703830&notif_t=page_invite"))
            startActivity(i)
        }

        instagram_imageButton.setOnClickListener {
            val i= Intent(Intent.ACTION_VIEW, Uri
                .parse("https://www.instagram.com/pinkpigletpuppy/?hl=es-la"))
            startActivity(i)
        }

        instagram_imageButton_huellitas.setOnClickListener {
            val i= Intent(Intent.ACTION_VIEW, Uri
                .parse("https://www.instagram.com/huellitas_de_acero/?hl=es-la"))
            startActivity(i)
        }

        facebook_imageButton_huellitas.setOnClickListener {
            val i= Intent(Intent.ACTION_VIEW, Uri
                .parse("https://www.facebook.com/HuellitasDeAcero/"))
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

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
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
