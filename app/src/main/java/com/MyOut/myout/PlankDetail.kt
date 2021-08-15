package com.MyOut.myout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_plank.*
import kotlinx.android.synthetic.main.activity_plank_detail.*
import kotlinx.android.synthetic.main.instructions_plank.*

class PlankDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plank_detail)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        //bottomNavigation.setSelectedItemId(R.id.home)
        bottomNavigation.setOnNavigationItemSelectedListener(navigationBar)

        btn_InstructionsPlank.setOnClickListener{
            val intent = Intent(this, InstructionsPlank::class.java)
            startActivity(intent)
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
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
            R.id.ic_profile -> {
                return@OnNavigationItemSelectedListener true
            }

        }
        false
    }

    fun StartPlank(view: View) {

        var time = SetTimerTextPlank.text.toString()

        var intent = Intent(this, Plank::class.java)
        intent.putExtra("key", time)

        startActivity(intent)
    }
}