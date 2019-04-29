package android.itesm.edu.adoptappv1.model

import android.itesm.edu.adoptappv1.R
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.paseadores_details.*


class PaseadoresDetails(): AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.paseadores_details)
val nombre= intent.getStringExtra("nombre")
        nombre_paseador.setText(nombre)
    }
}