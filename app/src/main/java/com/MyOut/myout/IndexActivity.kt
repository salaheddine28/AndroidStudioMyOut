package com.MyOut.myout

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.MyOut.myout.ui.Running
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_index.*
import kotlinx.android.synthetic.main.activity_main.*

class IndexActivity : AppCompatActivity() {

    private val name = arrayOf("Push-Up","Squat", "Plank / Wall-sit", "Running")

    lateinit var preferences: SharedPreferences

    private val image = arrayOf(R.drawable.pushup, R.drawable.squats, R.drawable.planks, R.drawable.running)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_index)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener(navigationBar)

        preferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)




        list_view.adapter = ListAdapter(this, image, name)
        list_view.setOnItemClickListener { parent, view, position, id ->
            if (position==0){
                val intent = Intent(this, PushUpDetail::class.java)
                startActivity(intent)
                finish()
            }

            if (position==1){
                val intent = Intent(this, SquatDetail::class.java)
                startActivity(intent)
                finish()
            }

            if (position==2){
                val intent = Intent(this, ChoosePlankWallSit::class.java)
                startActivity(intent)
                finish()
            }

            if (position==3){
                val intent = Intent(this, Running::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private val navigationBar = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.home -> {
                return@OnNavigationItemSelectedListener false
            }
            R.id.ic_bar -> {
                val intent = Intent(this, Charts::class.java)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
            R.id.ic_profile -> {
                val intent = Intent(this, Profile::class.java)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }

        }
        false

    }
}