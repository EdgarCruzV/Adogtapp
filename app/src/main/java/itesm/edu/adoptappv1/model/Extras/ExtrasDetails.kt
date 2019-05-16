package itesm.edu.adoptappv1.model.Extras

import android.content.DialogInterface
import android.content.Intent
import itesm.edu.adoptappv1.R
import itesm.edu.adoptappv1.model.Carrito.CarritoActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.extras_details.*
import kotlinx.android.synthetic.main.paseadores_details.*


class ExtrasDetails(): AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.extras_details)
        val nombre= intent.getStringExtra("nombre")
        val marca= intent.getStringExtra("marca")
        val precio= intent.getStringExtra("precio")
        val descripcion= intent.getStringExtra("descripcion")
        val fotoUrl= intent.getStringExtra("photo_url")
        val nombreCarrito=nombre
        val precioCarrito=precio
        val fotoUrlCarrito=fotoUrl
        nombre_extra_D.setText(nombre)
        marca_extra_D.setText(marca)
        precio_extra_D.setText(precio)
        descripcion_extra_D.setText(descripcion)
        Picasso.get().load(fotoUrl).into(foto_extra_D)


        Extra_Button_D.setOnClickListener {
            AlertDialog.Builder(this)
                .setMessage("Deseas agregar este producto al carrito?")
                .setPositiveButton("Agregar al carrito", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        val intent = Intent(applicationContext, CarritoActivity::class.java)
                        intent.putExtra("nombre_palcarrito", nombreCarrito)
                        intent.putExtra("precio_palcarrito", precioCarrito)
                        intent.putExtra("photo_url_palcarrito", fotoUrlCarrito)
                        startActivity(intent)
                    }
                })

                .create()
                .show()
        }
    }

}