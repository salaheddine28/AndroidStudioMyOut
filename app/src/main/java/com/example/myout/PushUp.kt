package com.example.myout

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.text.DecimalFormat;
import java.text.NumberFormat;

class PushUp : AppCompatActivity() {



    private val SetArray = arrayOf<Int>(15, 23, 51, 22, 13)

    var noOfSets = 3;

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

    private fun pause()
    {
        var btn = findViewById<Button>(R.id.PushUpButton)
        var txt = findViewById<TextView>(R.id.text)

        object : CountDownTimer(59000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

                txt.text = "Pause"


                val f: NumberFormat = DecimalFormat("00")
                val sec = millisUntilFinished / 1000 % 60
                btn.text = f.format(sec)
                btn.setTextColor(Color.parseColor("#a7fab0"))
                btn.isEnabled = false
            }

            // When the task is over it will print 00:00:00 there
            override fun onFinish() {
                btn.text = "Click to start"
                btn.isEnabled = true
                btn.setTextColor(Color.parseColor("White"))
                txt.text = "Push Up"
            }
        }.start()
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
                if (!started1)
                {
                    pause()
                    started1 = true
                }
                else
                {
                    btn.text = SetArray[1].toString()
                    SetArray[1] --
                }


            }


        }
        else if (SetArray[1].toInt() == 0 && SetArray[2] != 0)
        {
            if (noOfSets == 2) {
                btn.text = "You Finished"
                btn.isEnabled = false;
            }
            else {
                if (!started2)
                {
                    pause()
                    started2 = true
                }
                else
                {
                    btn.text = SetArray[2].toString()
                    SetArray[2] --
                }

            }


        }
        else if (SetArray[2].toInt() == 0 && SetArray[3] != 0)
        {
            if (noOfSets == 3) {
                btn.text = "You Finished"
                btn.isEnabled = false;
            }
            else {
                if (!started3)
                {
                    pause()
                    started3 = true
                }
                else
                {
                    btn.text = SetArray[3].toString()
                    SetArray[3] --
                }


            }

        }

        else if (SetArray[3].toInt() == 0 && SetArray[4] != 0)
        {
            if (noOfSets == 4) {
                btn.text = "You Finished"
                btn.isEnabled = false;
            }
            else {

                if (!started4)
                {
                    pause()
                    started4 = true
                }
                else
                {
                    btn.text = SetArray[4].toString()
                    SetArray[4] --
                }


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