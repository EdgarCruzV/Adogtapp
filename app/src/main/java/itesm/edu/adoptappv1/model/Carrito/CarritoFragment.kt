package itesm.edu.adoptappv1.model.Carrito

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.paypal.android.sdk.payments.PayPalConfiguration
import com.paypal.android.sdk.payments.PayPalPayment
import com.paypal.android.sdk.payments.PayPalService
import com.paypal.android.sdk.payments.PaymentActivity
import itesm.edu.adoptappv1.MainActivity
import itesm.edu.adoptappv1.R

import itesm.edu.adoptappv1.model.Extras.Carrito
import kotlinx.android.synthetic.main.activity_carrito.*
import java.math.BigDecimal



// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private lateinit var mContext: Context

val carrito = arrayListOf<Carrito>()
val names = arrayListOf<String>()
val prices= arrayListOf<String>()
class CarritoFragment : Fragment() {

    lateinit var _db: DatabaseReference
    lateinit var uid: String


    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    var config: PayPalConfiguration?=null
    var amount:Double=0.0

    var userNameGlobal : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        val nombre = bundle!!.getString("nombre_palcarrito")
        val precio = bundle!!.getString("precio_palcarrito")
        val fotoUrl = bundle!!.getString("photo_url_palcarrito")
        var suma=0
        val r = recycler_carrito.adapter


        if (nombre==null) {
            }else{
            carrito.add(
                Carrito(nombre, precio, fotoUrl)
            )
            names.add(nombre)
            prices.add(precio)
            }

        recycler_carrito.layoutManager = GridLayoutManager(context, 1)
        recycler_carrito.adapter = CarritoAdapter(carrito)


        carrito!!.forEach { event ->
            val suma1=event.precio_carrito.toInt()
            suma=suma+suma1
        }

        total_compra.setText("$suma MXN")

        HacerOrden_Button.setOnClickListener {
            var suma=0
            carrito!!.forEach { event ->
                val suma1=event.precio_carrito.toInt()
                suma=suma+suma1
            }
        }

        config = PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_PRODUCTION)//Cambiar a production para sacarlo
            .clientId("AcSv-Zug7TMoVsQuf_dqboM9LTCCwDsCSEQwOF7idQ2GG0hC6Ozb4zM-L43zkn_pAzFrW2TVUlU0gBf7")//Sacado de la página de developer de PayPal

        var i= Intent(context, PayPalService::class.java)
        i.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,config)
        activity!!.startService(i)

        HacerOrden_Button.setOnClickListener {

            if(carrito.isEmpty()){

            }else{
                amount = suma.toDouble()

                var payment= PayPalPayment(BigDecimal.valueOf(amount), "MXN"
                    , "Adogtapp", PayPalPayment.PAYMENT_INTENT_SALE)
                var intent = Intent(context, PaymentActivity::class.java)
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
                Toast.makeText(context, "Gracias por comprar en Adogtapp, pronto recibirá su pedido"
                    , Toast.LENGTH_SHORT).show()
                //NUEVO fragmento

                val donarFragment = envio.newInstance()
                (mContext as FragmentActivity).supportFragmentManager.beginTransaction()
                    .replace(itesm.edu.adoptappv1.R.id.fragment_container, donarFragment)
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mContext = container!!.getContext()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_carrito, container, false)
    }

    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
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
                    val pedidoAFirebase = Pedido(
                        uid,
                        names,
                        prices, suma)

                    val uid = FirebaseAuth.getInstance().uid ?: ""
                    val ref = FirebaseDatabase.getInstance()
                        .getReference("/pedidos/$userNameGlobal")



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
    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            CarritoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
data class Pedido(val uid : String, val articulos : MutableList<String>, val precios : MutableList<String>, val total : Int)