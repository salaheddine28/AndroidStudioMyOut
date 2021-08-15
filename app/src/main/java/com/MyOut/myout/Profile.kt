package com.MyOut.myout

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.fragment_setup.*


class Profile : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        auth = FirebaseAuth.getInstance()

        loadProfile()


        btn_save.setOnClickListener {
            UpdateProfile()
        }

        btn_ProfileSettings.setOnClickListener {
            startActivity(Intent(this, Settings::class.java))
        }

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.setSelectedItemId(R.id.ic_profile)
        bottomNavigation.setOnNavigationItemSelectedListener(navigationBar)


    }
    private fun UpdateProfile(){

        val db = FirebaseFirestore.getInstance()

        val firstName = firstname.text.toString()
        val lastName = lastname.text.toString()
        val ht = height.text.toString()
        val wt = weight.text.toString()

        val user = hashMapOf(
            "Firstname" to firstName,
            "Lastname" to lastName,
            "height" to ht,
            "weight" to wt
        )
        db.collection("users").document(auth.currentUser!!.uid).set(user)
    }

    private fun loadProfile() {


        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("users").document(auth.currentUser!!.uid)

        val firstname = findViewById(R.id.firstname) as TextView
        val lastname = findViewById(R.id.lastname) as TextView
        val height = findViewById(R.id.height) as TextView
        val weight = findViewById(R.id.weight) as TextView

        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null){
                    Log.d("Exist", "DocumentSnapchot data: ${document.data}")

                    firstname.hint = document.getString("Firstname")
                    lastname.hint = document.getString("Lastname")
                    height.hint = document.getString("height")
                    weight.hint = document.getString("weight")
                }else {
                    Log.d("noexist", "No such document")
                }
            }
            .addOnFailureListener{
                Log.d("errordb", "get failed with")
            }


    }


    private val navigationBar = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.ic_home -> {
                val intent = Intent(this, IndexActivity::class.java)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
            R.id.ic_bar -> {
                val intent = Intent(this, Charts::class.java)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
            R.id.ic_profile -> {
                return@OnNavigationItemSelectedListener false
            }

        }
        false

    }
}
