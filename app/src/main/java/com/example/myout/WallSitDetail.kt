package com.example.myout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView

class WallSitDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wall_sit_detail)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        //bottomNavigation.setSelectedItemId(R.id.home)
        bottomNavigation.setOnNavigationItemSelectedListener(navigationBar)
    }


    private val navigationBar = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.ic_home -> {
                val intent = Intent(this, IndexActivity::class.java)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
            R.id.ic_bar -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
            R.id.ic_profile -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.ic_social -> {
                return@OnNavigationItemSelectedListener true
            }

        }
        false
    }
}