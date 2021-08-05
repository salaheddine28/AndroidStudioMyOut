package com.example.myout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.TextureView
import android.view.View
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView

class PlankDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plank_detail)

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

    fun StartPlank(view: View) {

        var time = findViewById<androidx.appcompat.widget.AppCompatEditText>(R.id.SetTimerTextPlank)

        val intent = Intent(this, Plank::class.java)
        intent.putExtra("key", time.toString())

        startActivity(intent)
    }
}