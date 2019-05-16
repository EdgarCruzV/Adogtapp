package itesm.edu.adoptappv1.model.Carrito

import android.app.Activity
import android.content.Intent
import itesm.edu.adoptappv1.MainActivity
import itesm.edu.adoptappv1.R
import itesm.edu.adoptappv1.model.Extras.Carrito
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
import java.math.BigDecimal
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

lateinit var _db: DatabaseReference
lateinit var uid: String
val carrito = arrayListOf<Carrito>()
val names = arrayListOf<String>()
val prices= arrayListOf<String>()
class CarritoActivity(): AppCompatActivity() {

    var config:PayPalConfiguration?=null
    var amount:Double=0.0

    var userNameGlobal : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(itesm.edu.adoptappv1.R.layout.activity_carrito)
        val nombre = intent.getStringExtra("nombre_palcarrito")
        val precio = intent.getStringExtra("precio_palcarrito")
        val fotoUrl = intent.getStringExtra("photo_url_palcarrito")
        var suma=0
        val r = recycler_carrito.adapter


        if (nombre==null) {
            Log.d("ALex","tengo el valor de nombre es $carrito")
        }else{
            carrito.add(
                Carrito(nombre, precio, fotoUrl)
            )
            names.add(nombre)
            prices.add(precio)
            Log.d("ALex","tengo el valor de nombre es $carrito")
        }

        recycler_carrito.layoutManager = GridLayoutManager(this, 1)
        recycler_carrito.adapter = CarritoAdapter(carrito)


        carrito!!.forEach { event ->
            val suma1=event.precio_carrito.toInt()
            suma=suma+suma1
        }

        Log.d("alex","$suma")
        total_compra.setText("$suma MXN")

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

            if(carrito.isEmpty()){

            }else{
                amount = suma.toDouble()

                var payment=PayPalPayment(BigDecimal.valueOf(amount), "MXN", "Adogtapp", PayPalPayment.PAYMENT_INTENT_SALE)
                var intent = Intent(this, PaymentActivity::class.java)
                intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,config)
                intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment)
                startActivityForResult(intent, 123)
            }
        }
        delete_button.setOnClickListener {

            //vaciar carrito
            carrito.clear()
            recycler_carrito.adapter = CarritoAdapter(carrito)
            total_compra.text = "0 MXN"
            //QUITARLO

            ///QUITARLO
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 123) {
            if (resultCode == Activity.RESULT_OK) {
                //Sí compró

                //vaciar carrito
                carrito.clear()
                recycler_carrito.adapter = CarritoAdapter(carrito)
                total_compra.text = "0 MXN"
                //enviar pedido a base de datos
                savePedido(amount.toInt())
                Toast.makeText(this, "Gracias por comprar en Adogtapp, pronto recibirá su pedido", Toast.LENGTH_SHORT).show()
                //NUEVO fragmento

                intent = Intent(this, MainActivity::class.java)
                intent.putExtra("Pagado", "Pagado")
                startActivity(intent)
            }
        }
    }

    override fun onDestroy() {
        stopService(Intent(this,PayPalService::class.java))
        super.onDestroy()
    }

    private fun savePedido(suma: Int) {
        val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
        var user: FirebaseUser? = mAuth.getCurrentUser()

        _db = FirebaseDatabase.getInstance().getReference("/users")
        if (user != null) {
            if (user!!.uid != null) {
                uid = user!!.uid
            }
            val ref = FirebaseDatabase.getInstance().reference
            ref.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // Code
                    val userName = dataSnapshot.child("users")
                        .child(uid).child("username").value.toString()
                    if (userName != "null") {
                        userNameGlobal = userName
                        //Toast.makeText(applicationContext, "$userNameGlobal", Toast.LENGTH_SHORT).show()
                    }
                    val pedidoAFirebase = Pedido(uid, names, prices, suma)

                    val uid = FirebaseAuth.getInstance().uid ?: ""
                    val ref = FirebaseDatabase.getInstance().getReference("/pedidos/$userNameGlobal")



                    ref.setValue(pedidoAFirebase)
                        .addOnSuccessListener {
                            //Log.d("AdogtApp", "Cliente uploaded")
                        }
                        .addOnFailureListener {
                            //Log.d("AdogtApp", "FAILED: ${it.message}")
                        }

                }
                override fun onCancelled(databaseError: DatabaseError) {
                    // Code
                }
            })
        }

    }


}


data class Pedido(val uid : String, val articulos : MutableList<String>, val precios : MutableList<String>, val total : Int)