package android.itesm.edu.adoptappv1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : NavigationView.OnNavigationItemSelectedListener, AppCompatActivity() {
    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when (p0.getItemId()) {
            R.id.layout_adoptar -> {
                drawer.closeDrawers()
                val adoptarFragment = Adoptar_Fragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, adoptarFragment).commit();
                Toast.makeText(this, "Adopta", Toast.LENGTH_SHORT).show()

            }
            R.id.layout_tienda -> {
                drawer.closeDrawers()
                val tiendaFragment = Tienda_Fragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, tiendaFragment).commit();
                Toast.makeText(this, "Tienda", Toast.LENGTH_SHORT).show()

            }
            R.id.layout_informacion -> {
                drawer.closeDrawers()
                val informacionFragment = Informacion_Fragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, informacionFragment).commit();
                Toast.makeText(this, "Contacto", Toast.LENGTH_SHORT).show()

            }
            R.id.layout_paseador -> {
                drawer.closeDrawers()
                //val adoptarFragment = Adoptar_Fragment.newInstance()
                //supportFragmentManager.beginTransaction().replace(R.id.fragment_container, adoptarFragment).commit();
                Toast.makeText(this, "Paseador", Toast.LENGTH_SHORT).show()

            }
            R.id.layout_login -> {
                drawer.closeDrawers()
                //val adoptarFragment = Adoptar_Fragment.newInstance()
                //supportFragmentManager.beginTransaction().replace(R.id.fragment_container, adoptarFragment).commit();
                Toast.makeText(this, "Login", Toast.LENGTH_SHORT).show()
            }
        }
        return true
    }

    private lateinit var drawer: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Para el menu
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        var navigationView : NavigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this)
        val toggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.drawer_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }





}
