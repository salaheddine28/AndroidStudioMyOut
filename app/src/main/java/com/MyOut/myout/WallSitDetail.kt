package com.MyOut.myout

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_plank_detail.*
import kotlinx.android.synthetic.main.activity_wall_sit_detail.*

class WallSitDetail : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wall_sit_detail)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        //bottomNavigation.setSelectedItemId(R.id.home)
        bottomNavigation.setOnNavigationItemSelectedListener(navigationBar)

        btn_InstructionsWall.setOnClickListener{
            val intent = Intent(this, InstructionsWallSit::class.java)
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

    fun StarttTimerWall(view: View) {
        var time = SetTimerText.text.toString()

        var intent = Intent(this, WallSit::class.java)
        intent.putExtra("key", time)

        startActivity(intent)
    }
}