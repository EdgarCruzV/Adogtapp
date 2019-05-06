package android.itesm.edu.adoptappv1

import android.itesm.edu.adoptappv1.R
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.itesm.edu.adoptappv1.model.Adoptar.Perrito
import android.itesm.edu.adoptappv1.model.Adoptar.PerritosAdapter
import android.itesm.edu.adoptappv1.model.Extras.Carrito
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.paypal.android.sdk.payments.PayPalConfiguration
import com.paypal.android.sdk.payments.PayPalPayment
import com.paypal.android.sdk.payments.PayPalService
import com.paypal.android.sdk.payments.PaymentActivity
import kotlinx.android.synthetic.main.adoptar_fragment.*
import kotlinx.android.synthetic.main.fragment_donar.*
import kotlinx.android.synthetic.main.fragment_donar.view.*
import org.jetbrains.anko.appcompat.v7._ButtonBarLayout
import java.math.BigDecimal


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [Donar.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [Donar.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class Donar : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    var config: PayPalConfiguration?=null
    var amount:Double=0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        config = PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_PRODUCTION)//Cambiar a production para sacarlo
            .clientId("AcSv-Zug7TMoVsQuf_dqboM9LTCCwDsCSEQwOF7idQ2GG0hC6Ozb4zM-L43zkn_pAzFrW2TVUlU0gBf7")//Sacado de la página de developer de PayPal

        var i= Intent(context, PayPalService::class.java)
        i.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,config)
        context?.startService(i)


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 223) {
            if (resultCode == Activity.RESULT_OK) {
                //Sí compró
                //vaciar carrito
                //enviar pedido a base de datos
                Toast.makeText(context, "Gracias por donar a Adogtapp", Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_donar, container, false)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
    }


    override fun onDestroy() {
        context?.stopService(Intent(context,PayPalService::class.java))
        super.onDestroy()
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }



    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        donar_button.setOnClickListener {
            amount = cantidad_text.text.toString().toDouble()
            var payment= PayPalPayment(BigDecimal.valueOf(amount), "MXN", "Adogtapp", PayPalPayment.PAYMENT_INTENT_SALE)
            var intent = Intent(context, PaymentActivity::class.java)
            intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,config)
            intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment)
            startActivityForResult(intent, 223)
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Donar.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            Donar().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
