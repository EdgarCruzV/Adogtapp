package android.itesm.edu.adoptappv1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Para el menu
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.drawer_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.layout_adoptar -> {
                // Handle the camera action
                Toast.makeText(this,"holis",Toast.LENGTH_LONG).show()
            }
            R.id.layout_tienda -> {

            }
            R.id.layout_informacion -> {

            }
            R.id.layout_paseador -> {

            }
            R.id.layout_login -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }




}
