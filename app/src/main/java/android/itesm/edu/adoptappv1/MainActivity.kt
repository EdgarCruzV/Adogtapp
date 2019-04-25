package android.itesm.edu.adoptappv1

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_header.*
import kotlinx.android.synthetic.main.nav_header.view.*
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseReference



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

    lateinit var _db: DatabaseReference
    lateinit var uid: String
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
        checkUserLogged()
        loadUserInformation()
        val fragment = Adoptar_Fragment.newInstance();
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.log_out ->{
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(this, RegisterActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)

            }
        }
        return super.onOptionsItemSelected(item)
    }



    private fun checkUserLogged() {
        val uid = FirebaseAuth.getInstance().uid
        if (uid == null) {
            val intent = Intent(this, RegisterActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }

    private fun loadUserInformation() {
        val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
        var user : FirebaseUser? = mAuth.getCurrentUser()
        _db = FirebaseDatabase.getInstance().getReference("/users")


        if(user != null){
            if(user!!.uid != null){
                uid = user!!.uid
            }
            if(user!!.email != null){
                //var displayName : String = user!!.getDisplayName()!!
                nav_view.getHeaderView(0).mail_text.text = user!!.email
            }
            val ref = FirebaseDatabase.getInstance().reference
            ref.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // Code
                    val userName  = dataSnapshot.child("users").child(uid).child("username").value.toString()
                    if(userName != "null"){
                        nav_view.getHeaderView(0).name_text.text = userName
                    }

                    val userPhotoUrl  = dataSnapshot.child("users").child(uid).child("profileImageUrl").value.toString()
                    if(userPhotoUrl != "null"){
                        Glide.with(applicationContext)
                            .load(userPhotoUrl)
                            .into(nav_view.getHeaderView(0).profile_pic)
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Code
                }
            })
        }

    }

}

data class User(
    var uid: String = "",
    val username: String = "",
    val image: String = ""
)