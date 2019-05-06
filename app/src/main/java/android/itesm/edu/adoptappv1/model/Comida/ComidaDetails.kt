package android.itesm.edu.adoptappv1.model.Comida

import android.content.DialogInterface
import android.content.Intent
import android.itesm.edu.adoptappv1.model.Carrito.CarritoActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.comida_details.*
import org.jetbrains.anko.startActivity


class ComidaDetails(): AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(android.itesm.edu.adoptappv1.R.layout.comida_details)
        val nombre= intent.getStringExtra("nombre")
        val marca= intent.getStringExtra("marca")
        val precio= intent.getStringExtra("precio")
        val fotoUrl= intent.getStringExtra("photo_url")
        val nombreCarrito=nombre
        val precioCarrito=precio
        val fotoUrlCarrito=fotoUrl
        nombre_comida_D.setText(nombre)
        marca_comida_D.setText(marca)
        precio_comida_D.setText(precio)
        Picasso.get().load(fotoUrl).into(foto_comida_D)


        buttonComida.setOnClickListener {
            AlertDialog.Builder(this)
                .setMessage("Deseas agregar este producto al carrito?")
                .setPositiveButton("Agregar al carrito", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        val intent =Intent(applicationContext, CarritoActivity::class.java)
                        intent.putExtra("nombre_palcarrito", nombreCarrito)
                        intent.putExtra("precio_palcarrito", precioCarrito)
                        intent.putExtra("photo_url_palcarrito", fotoUrlCarrito)
                        startActivity(intent)
                    }

                }

                )

                .create()
                .show()
        }
    }

}