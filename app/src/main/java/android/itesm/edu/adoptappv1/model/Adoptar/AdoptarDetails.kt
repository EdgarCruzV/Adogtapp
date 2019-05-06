package android.itesm.edu.adoptappv1.model.Adoptar

import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adoptar_details.*
import android.content.Intent
import android.net.Uri
import android.widget.Toast


class AdoptarDetails (): AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(android.itesm.edu.adoptappv1.R.layout.adoptar_details)
        val nombre= intent.getStringExtra("nombre")
        val edad= intent.getStringExtra("edad")
        val talla= intent.getStringExtra("talla")
        val contacto= intent.getStringExtra("contacto")
        val descripcion= intent.getStringExtra("descripcion")

        val fotoUrl= intent.getStringExtra("photo_url")

        nombre_perrito_Text.setText(nombre)
        edad_perrito.setText(edad)
        talla_perrito.setText(talla)
        contacto_perrito.setText(contacto)
        descripcion_perrito.setText(descripcion)
        Picasso.get().load(fotoUrl).into(foto_perrito_Image)


        button30.setOnClickListener {
            AlertDialog.Builder(this)
                .setMessage("Recuerda que un perrito es un amigo para toda la vida y una gran responsabilidad, ¿prometes cuidar de tu familia?")
                .setPositiveButton("¡Lo prometo!", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        val recipient = "prueba@gmail.mx".trim()
                        val subject = "pueba de correo".trim()
                        val message = "Hola estoy probando mi correo".trim()


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
                                startActivity(Intent.createChooser(mIntent, "Choose Email Client..."))
                            } catch (e: Exception) {
                                //if any thing goes wrong for example no email client application or any exception
                                //get and show exception message
                                Toast.makeText(applicationContext, e.message, Toast.LENGTH_LONG).show()
                            }
                        }
                        //method call for email intent with these inputs as parameters
                        sendEmail(recipient, subject, message)

                    }

                })

                .create()
                .show()
        }
    }
}