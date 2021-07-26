package com.example.myout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_choose_plank_wall_sit.*
import kotlinx.android.synthetic.main.activity_index.*

class ChoosePlankWallSit : AppCompatActivity() {

    private val name = arrayOf("Plank", "Wall-sit")
    private val image = arrayOf(R.drawable.planks, R.drawable.wallsit)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_plank_wall_sit)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        //bottomNavigation.setSelectedItemId(R.id.home)
        bottomNavigation.setOnNavigationItemSelectedListener(navigationBar)


        list_view_Plank_WallSit.adapter = ListAdapter(this, image, name)
        list_view_Plank_WallSit.setOnItemClickListener { parent, view, position, id ->
            if (position==0){
                val intent = Intent(this, Plank::class.java)
                startActivity(intent)
                finish()
            }

            if (position==1){
                val intent = Intent(this, WallSit::class.java)
                startActivity(intent)
                finish()
            }

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
            R.id.ic_social -> {
                return@OnNavigationItemSelectedListener true
            }

        }
        false

    }
}