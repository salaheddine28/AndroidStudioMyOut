package com.example.myout

import android.content.Intent
import android.os.Bundle
import android.os.storage.StorageManager
import androidx.appcompat.app.AppCompatActivity
import com.example.myout.databinding.ActivityProfileBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.fragment_setup.*


class Profile : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var uid: String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)


        auth = FirebaseAuth.getInstance()
        uid = auth.currentUser?.uid.toString()

        databaseReference = FirebaseDatabase.getInstance().getReference("users")
        if (uid.isNotEmpty()){
            loadProfile()
        }



        btn_Save.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this@Profile, MainActivity::class.java))
            finish()
        }

        btn_Settings.setOnClickListener {
            val intent = Intent(this, SettingsProfile::class.java)
            startActivity(intent)
            finish()
        }

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.setSelectedItemId(R.id.ic_profile)
        bottomNavigation.setOnNavigationItemSelectedListener(navigationBar)


    }

    private fun loadProfile() {
        databaseReference.child(uid).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot){

                binding.Firstname.setText(snapshot.child("Firstname").getValue().toString())
            }

            override fun onCancelled(error: DatabaseError){
                binding.Firstname.setText("Error")
            }
        })


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
            R.id.ic_social -> {
                val intent = Intent(this, Social::class.java)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }

        }
        false

    }
}
