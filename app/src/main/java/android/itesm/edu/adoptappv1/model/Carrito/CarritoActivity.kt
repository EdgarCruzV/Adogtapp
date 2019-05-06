package android.itesm.edu.adoptappv1.model.Carrito

import android.itesm.edu.adoptappv1.R
import android.itesm.edu.adoptappv1.model.Extras.Carrito
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import kotlinx.android.synthetic.main.activity_carrito.*
import kotlinx.android.synthetic.main.comida_details.*

val carrito = arrayListOf<Carrito>()

class CarritoActivity(): AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrito)
        val nombre = intent.getStringExtra("nombre_palcarrito")
        val precio = intent.getStringExtra("precio_palcarrito")
        val fotoUrl = intent.getStringExtra("photo_url_palcarrito")
        var suma=0




        if (nombre==null) {
            Log.d("ALex","tengo el valor de nombre es $carrito")
        }else{
            carrito.add(
                Carrito(nombre, precio, fotoUrl)
            )
            Log.d("ALex","tengo el valor de nombre es $carrito")
        }

        recycler_carrito.layoutManager = GridLayoutManager(this, 1)
        recycler_carrito.adapter = CarritoAdapter(carrito)

        carrito!!.forEach { event ->
            val suma1=event.precio_carrito.toInt()
            suma=suma+suma1
        }
        Log.d("alex","$suma")
        total_compra.setText(suma.toString())

        HacerOrden_Button.setOnClickListener {
            var suma=0
            carrito!!.forEach { event ->
                val suma1=event.precio_carrito.toInt()
                suma=suma+suma1
            }
            Log.d("alex","$suma")
        }
    }


}