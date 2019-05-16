package itesm.edu.adoptappv1

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.design.widget.NavigationView
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.content.ContextCompat
import android.support.v4.content.ContextCompat.startActivity
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import itesm.edu.adoptappv1.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.nav_header.*
import java.util.*


class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        register_button.setOnClickListener {
            AlertDialog.Builder(this)
                .setMessage("AVISO DE PRIVACIDAD\n" +
                        "\n" +
                        "De conformidad con lo establecido en la Ley Federal de Protección de Datos Personales en Posesión de\n" +
                        "los Particulares, A Dog App pone a su disposición el siguiente aviso de privacidad\n" +
                        "\n" +
                        "Adogtapp. es responsable del uso y protección de sus datos personales, en este sentido y atendiendo las\n" +
                        "obligaciones legales establecidas en la Ley Federal de Protección de Datos Personales en Posesión de\n" +
                        "los Particulares, a través de este instrumento se informa a los titulares de los datos, la información que de\n" +
                        "ellos se recaba y los fines que se le darán a dicha información\n" +
                        "\n" +
                        "Además de lo anterior, informamos a usted que Adogtapp, tiene su domicilio ubicado en\n" +
                        "Instituto Tecnológico y de Estudios Superiores de Monterrey, Campus Ciudad de México\n" +
                        "Los datos personales que recabamos de usted seran utilizados para las siguientes finalidades, las cuales\n" +
                        "son necesarias para concretar nuestra relación con usted asi como atender los servicios y/o pedidos que\n" +
                        "solicite:\n" +
                        "\n" +
                        "Seguimiento de proceso de adopción, envio de pedidos, contacto\n" +
                        "\n" +
                        "Para llevar a cabo las finalidades descritas en el presente aviso de privacidad, utilizaremos los siguientes\n" +
                        "datos personales\n" +
                        "\n" +
                        "Nombre, Telefono, Domicilio, Correo electrónico\n" +
                        "Por otra parte, informamos a usted, que sus datos personales no serán compartidos con ninguna\n" +
                        "autoridad, empresa, organización o persona distintas a nosotros y serán utilizados exclusivamente para\n" +
                        "los fines señalados\n" +
                        "\n" +
                        "Usted tiene en todo momento el derecho a conocer que datos personales tenemos de usted, para que los\n" +
                        "utilizamos y las condiciones del uso que les damos (Acceso). Asimismo, es su derecho solicitar la\n" +
                        "corrección de su información personal en caso de que esta desactualizada, sea inexacta o incompleta\n" +
                        "(Rectificación), de igual manera, tiene derecho a que su información se elimine de\n" +
                        "bases de datos cuando considere que la misma no está siendo utilizada adecuadamente (Cancelación);\n" +
                        "así como también a oponerse al uso de sus datos personales para fines especificos (Oposición). Estos\n" +
                        "derechos se conocen como derechos ARCO\n" +
                        "\n" +
                        "Para el ejercicio de cualquiera de los derechos ARCO, se deberá presentar la solicitud respectiva a través\n" +
                        "del siguiente correo electrónico:\n" +
                        "\n" +
                        "Adogtapp.services@gmail.com\n" +
                        "\n" +
                        "Lo anterior también servirá para conocer el procedimiento y requisitos para el ejercicio de los derechos\n" +
                        "ARCO, no obstante, la solicitud de ejercicio de estos derechos debe contener la siguiente información:\n" +
                        "\n" +
                        "Nombre, domicilio, correo electronico usuario\n" +
                        "\n" +
                        "La respuesta a la solicitud se dará en 5 dias habiles y se comunicara de la siguiente manera;\n" +
                        "\n" +
                        "Correo electrónico\n" +
                        "Los datos de contacto de la persona o departamento de datos personales, que está a cargo de dar trámite\n" +
                        "a las  solicitudes de derechos ARCO, son los siguientes\n" +
                        "\n" +
                        "a) Nombre del responsable: Departamento de información\n" +
                        "b) Domicilio: Instituto Tecnológico y de Estudios Superiores de Monterrey, Campus Ciudad de México\n" +
                        "C) Teléfono: NA\n" +
                        "\n" +
                        "Cabe mencionar que en cualquier momento usted puede revocar su consentimiento para el uso de sus\n" +
                        "datos personales. Del mismo modo, usted puede revocar el consentimiento que, en su caso, nos haya\n" +
                        "otorgado para el tratamiento de sus datos personales\n" +
                        "\n" +
                        "Asimismo, usted deberá considerar que para ciertos fines, la revocación de su\n" +
                        "consentimiento implicará que no podamos seguir prestando el servicio que nos solicito, o la conclusión\n" +
                        "de su relación con nosotros.\n" +
                        "\n" +
                        "Para revocar el consentimiento que usted otorga en este acto o para limitar su divulgación, se deberá\n" +
                        "presentar la solicitud respectiva a través de los formatos que estarán a su disposición en:\n" +
                        "https://www.infoem.org.mx/src/htm/formatoSolicitudesArco.html\n" +
                        ": Del mismo modo, podrá solicitar la información para conocer el procedimiento y requisitos para la\n" +
                        "revocación del consentimiento, asi como limitar el uso y divulgación de su información personal.\n" +
                        "https://www.infoem.org.mx/src/htm/formatoSolicitudesArco.html\n" +
                        "En cualquier caso, la respuesta a las peticiones se dará a conocer en un plazo máximo de 20 dias hábiles y mínimo de 5 dias habiles.\n" +
                        "Actualizado al 7 mayo de 2019\n" +
                        "\n")
                .setPositiveButton("HE leido y acepto", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        doRegister()
                    }

                })

                .create()
                .show()
        }

        have_an_account.setOnClickListener {
            finish()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        photo_user_button.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 666)
        }

    }

    var selectedPhotoUri: Uri? = null
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==666 && resultCode == Activity.RESULT_OK && data != null){

            selectedPhotoUri = data.data
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhotoUri)
            photo_user_button.setImageBitmap(bitmap)
        }

    }
    private fun doRegister(){
        val email = email_edittext.text.toString()
        val password = password_edittext.text.toString()

        val username = username_edittext.text.toString()

        if (email.isEmpty() || password.isEmpty()|| username.isEmpty() ) {
            Toast.makeText(this, "Please fill out email/pw / user.", Toast.LENGTH_SHORT).show()
            return
        }
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                Toast.makeText(this, "Created ${it.result!!.user.uid}", Toast.LENGTH_SHORT).show()
                uploadImageToFirebaseStorage()
                finish()
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed!!!", Toast.LENGTH_SHORT).show()

            }
    }



    private fun uploadImageToFirebaseStorage() {
        if (selectedPhotoUri == null) {
            saveClienteToFirebaseDatabase("")
            return
        }

        val filename = email_edittext.text.toString()
        val ref = FirebaseStorage.getInstance().getReference("/images/$filename")

        ref.putFile(selectedPhotoUri!!)
            .addOnSuccessListener {
                Log.d("AdogtApp", "Successfully uploaded image: ${it.metadata?.path}")

                ref.downloadUrl.addOnSuccessListener {
                    Log.d("AdogtApp", "File Location: $it")

                    saveClienteToFirebaseDatabase(it.toString())
                }
            }
            .addOnFailureListener {
                Log.d("AdogtApp", "Failed to upload image to storage: ${it.message}")
            }
    }

    private fun saveClienteToFirebaseDatabase(profileImageUrl: String) {
        val uid = FirebaseAuth.getInstance().uid ?: ""
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")

        val user = Cliente(uid, username_edittext.text.toString(), profileImageUrl)

        ref.setValue(user)
            .addOnSuccessListener {
                Log.d("AdogtApp", "Cliente uploaded")
            }
            .addOnFailureListener {
                Log.d("AdogtApp", "FAILED: ${it.message}")
            }
    }
}


data class Cliente(val uid: String, val username: String, val profileImageUrl: String)











