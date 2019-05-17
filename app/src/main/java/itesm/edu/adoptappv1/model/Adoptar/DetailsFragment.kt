package itesm.edu.adoptappv1.model.Adoptar

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.squareup.picasso.Picasso

import itesm.edu.adoptappv1.R
import kotlinx.android.synthetic.main.adoptar_details.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DetailsFragment : Fragment() {
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
        return inflater.inflate(R.layout.adoptar_details, container, false)
    }

    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }



    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments

        val nombre= bundle!!.getString("nombre")
        val edad= bundle!!.getString("edad")
        val talla= bundle!!.getString("talla")
        val contacto= bundle!!.getString("contacto")
        val descripcion= bundle!!.getString("descripcion")

        val fotoUrl= bundle!!.getString("photo_url")

        nombre_perrito_Text.setText(nombre)
        edad_perrito.setText(edad)
        talla_perrito.setText(talla)
        contacto_perrito.setText(contacto)
        descripcion_perrito.setText(descripcion)
        Picasso.get().load(fotoUrl).into(foto_perrito_Image)


        button30.setOnClickListener {
            AlertDialog.Builder(context!!)
                .setMessage("Recuerda que un perrito es un amigo para toda la vida y una gran responsabilidad, " +
                        "¿Prometes cuidar de tu familia?")
                .setPositiveButton("¡Lo prometo!", object : DialogInterface.OnClickListener {

                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        AlertDialog.Builder(context!!)
                            .setMessage("Acepta para enviar un correo para solicitar la adopción.")
                            .setPositiveButton("Aceptar.",object : DialogInterface.OnClickListener {
                            override fun onClick(dialog: DialogInterface?, which: Int) {
                                val recipient = "adogtapp.services@gmail.com".trim()
                                val subject = "Solicitud de Adopción de $nombre".trim()
                                val message = ("Hola, estoy interesad@ en adoptar a $nombre.\n " +
                                        "Agrega un poco más acerca de ti y de por qué quieres adoptar a $nombre.").trim()


                                fun sendEmail(recipient: String, subject: String, message: String) {
                                    /*ACTION_SEND action to launch an email client installed on your Android device.*/
                                    val mIntent = Intent(Intent.ACTION_SEND)
                                    /*To send an email you need to specify mailto: as URI using setData() method
                                    and data type will be to text/plain using setType() method*/
                                    mIntent.data = Uri.parse("mailto:")
                                    mIntent.type = "text/plain"
                                    // put recipient email in intent
                                    /* recipient is put as array because you may wanna send email to multiple emails
                                       so enter comma(,) separated emails, it will be stored in array*/
                                    mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
                                    //put the Subject in the intent
                                    mIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
                                    //put the message in the intent
                                    mIntent.putExtra(Intent.EXTRA_TEXT, message)


                                    try {
                                        //start email intent
                                        startActivity(Intent.createChooser(mIntent, "Selecciona la app de tu correo..."))
                                    } catch (e: Exception) {
                                        //if any thing goes wrong for example no email client application or any exception
                                        //get and show exception message
                                        Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
                                    }
                                }
                                //method call for email intent with these inputs as parameters
                                sendEmail(recipient, subject, message)
                            }
                        }).setNegativeButton("Cancelar.", object : DialogInterface.OnClickListener {
                                override fun onClick(dialog: DialogInterface?, which: Int) {

                                }
                            }).setCancelable(false)

                            .create()
                            .show()


                    }

                })

                .create()
                .show()
        }
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
