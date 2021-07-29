package com.example.myout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView

class PushUp : AppCompatActivity() {


    val SetArray = arrayOf<Int>(15, 23, 51, 22, 13)

    var noOfSets = 1;

    var started1 = false;
    var started2 = false;
    var started3 = false;
    var started4 = false;
    var started5 = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_push_up)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        //bottomNavigation.setSelectedItemId(R.id.home)
        bottomNavigation.setOnNavigationItemSelectedListener(navigationBar)


        var btn = findViewById<Button>(R.id.PushUpButton)
        btn.text = SetArray[0].toString()

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
                val intent = Intent(this, Profile::class.java)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
            R.id.ic_social -> {
                val intent = Intent(this, Social::class.java)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }

        }
        false

    }

    fun onTap(view: View) {
        val btn = findViewById<Button>(R.id.PushUpButton)

        if (SetArray[0].toInt() != 1 )
        {
            SetArray[0] --

            btn.text = SetArray[0].toString()
        }
        else if (SetArray[0].toInt() == 1 && SetArray[1].toInt() != 0)
        {
            if (noOfSets == 1) {

                btn.text = "You Finished"
                btn.isEnabled = false;
            }
            else {
                btn.text = SetArray[1].toString()
                SetArray[1] --
            }


        }
        else if (SetArray[1].toInt() == 0 && SetArray[2] != 0)
        {
            if (noOfSets == 2) {
                btn.text = "You Finished"
                btn.isEnabled = false;
            }
            else {
                btn.text = SetArray[2].toString()
                SetArray[2] --
            }


        }
        else if (SetArray[2].toInt() == 0 && SetArray[3] != 0)
        {
            if (noOfSets == 3) {
                btn.text = "You Finished"
                btn.isEnabled = false;
            }
            else {
                btn.text = SetArray[3].toString()
                SetArray[3] --
            }

        }

        else if (SetArray[3].toInt() == 0 && SetArray[4] != 0)
        {
            if (noOfSets == 4) {
                btn.text = "You Finished"
                btn.isEnabled = false;
            }
            else {
                btn.text = SetArray[4].toString()
                SetArray[4] --
            }

        }

        else if (SetArray[4].toInt() == 0 && SetArray[5] != 0)
        {
            if (noOfSets == 5) {
                btn.text = "You Finished"
                btn.isEnabled = false;
            }


        }



    }
}