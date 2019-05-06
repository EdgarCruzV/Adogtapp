package android.itesm.edu.adoptappv1.model.Carrito

import android.app.Activity
import android.content.Intent
import android.itesm.edu.adoptappv1.R
import android.itesm.edu.adoptappv1.model.Extras.Carrito
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.widget.Toast
import com.paypal.android.sdk.payments.PayPalConfiguration
import com.paypal.android.sdk.payments.PayPalPayment
import com.paypal.android.sdk.payments.PayPalService
import com.paypal.android.sdk.payments.PaymentActivity
import kotlinx.android.synthetic.main.activity_carrito.*
import kotlinx.android.synthetic.main.comida_details.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.startActivityForResult
import java.math.BigDecimal

val carrito = arrayListOf<Carrito>()

class CarritoActivity(): AppCompatActivity() {

    var config:PayPalConfiguration?=null
    var amount:Double=0.0

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

        config = PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_PRODUCTION)//Cambiar a production para sacarlo
            .clientId("AcSv-Zug7TMoVsQuf_dqboM9LTCCwDsCSEQwOF7idQ2GG0hC6Ozb4zM-L43zkn_pAzFrW2TVUlU0gBf7")//Sacado de la página de developer de PayPal

        var i= Intent(this,PayPalService::class.java)
        i.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,config)
        startService(i)

        HacerOrden_Button.setOnClickListener {
            amount = total_compra.text.toString().toDouble()
            var payment=PayPalPayment(BigDecimal.valueOf(amount), "MXN", "Adogtapp", PayPalPayment.PAYMENT_INTENT_SALE)
            var intent = Intent(this, PaymentActivity::class.java)
            intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,config)
            intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment)
            startActivityForResult(intent, 123)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 123) {
            if (resultCode == Activity.RESULT_OK) {
                //Sí compró
                //vaciar carrito
                //enviar pedido a base de datos
                Toast.makeText(this, "Gracias por comprar en Adogtapp, pronto recibirá su pedido", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        stopService(Intent(this,PayPalService::class.java))
        super.onDestroy()
    }


}