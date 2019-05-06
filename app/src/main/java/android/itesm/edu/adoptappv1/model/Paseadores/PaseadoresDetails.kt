package android.itesm.edu.adoptappv1.model.Paseadores

import android.content.DialogInterface
import android.itesm.edu.adoptappv1.R
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.paseadores_details.*


class PaseadoresDetails(): AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.paseadores_details)
        val nombre= intent.getStringExtra("nombre")
        val colonia= intent.getStringExtra("colonia")
        val telefono= intent.getStringExtra("telefono")
        val horario1= intent.getStringExtra("horario1")
        val horario2= intent.getStringExtra("horario2")
        val descripcion= intent.getStringExtra("descripcion")
        val fotoUrl= intent.getStringExtra("photo_url")
        nombre_paseador.setText(nombre)
        colonia_paseador.setText(colonia)
        telefono_paseador.setText(telefono)
        descripcion_paseador.setText(descripcion)
        horario1_paseador.setText(horario1)
        horario2_paseador.setText(horario2)
        Picasso.get().load(fotoUrl).into(foto_paseador)


        button3.setOnClickListener {
            AlertDialog.Builder(this)
                .setMessage("Hola amiiguitos")
                .setPositiveButton("Ok", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {


                    }

                })

                .create()
                .show()
        }
    }

}