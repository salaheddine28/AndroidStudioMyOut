package com.example.myout

import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_push_up_detail.*
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*

class PushUp : AppCompatActivity() {



    private var mediaPlayer: MediaPlayer? = null
    private var mediaPlayerFinish: MediaPlayer? = null



    private var SetArray = arrayOf<Int>(1,1,1,1,1,1)

    var noOfSets = 0


    var started1 = false;
    var started2 = false;
    var started3 = false;
    var started4 = false;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_push_up)
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        //bottomNavigation.setSelectedItemId(R.id.home)
        bottomNavigation.setOnNavigationItemSelectedListener(navigationBar)

        mediaPlayer = MediaPlayer.create(this, R.raw.pushup_sound)
        mediaPlayerFinish = MediaPlayer.create(this, R.raw.finish)



        noOfSets = intent.getStringExtra("noOfSets").toString().toInt()

        var btn = findViewById<Button>(R.id.PushUpButton)


        var numberList = intent .getStringArrayListExtra( "key" )



        var s1 = numberList?.get(0).toString()
        var s2 = numberList?.get(1).toString()
        var s3 = numberList?.get(2).toString()
        var s4 = numberList?.get(3).toString()
        var s5 = numberList?.get(4).toString()



        if (noOfSets == 1)
        {
            SetArray[0] = s1.toInt()
        }
        if (noOfSets == 2)
        {
            SetArray[0] = s1.toInt()
            SetArray[1] = s2.toInt()
        }
        if (noOfSets == 3)
        {
            SetArray[0] = s1.toInt()
            SetArray[1] = s2.toInt()
            SetArray[2] = s3.toInt()
        }
        if (noOfSets == 4)
        {
            SetArray[0] = s1.toInt()
            SetArray[1] = s2.toInt()
            SetArray[2] = s3.toInt()
            SetArray[3] = s4.toInt()
        }
        if (noOfSets == 5)
        {
            SetArray[0] = s1.toInt()
            SetArray[1] = s2.toInt()
            SetArray[2] = s3.toInt()
            SetArray[3] = s4.toInt()
            SetArray[4] = s5.toInt()
        }


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
            mediaPlayer?.start()
            SetArray[0] --


            btn.text = SetArray[0].toString()
        }
        else if (SetArray[0].toInt() == 1 && SetArray[1].toInt() != 0)
        {
            mediaPlayer?.start()
            if (noOfSets == 1) {
                btn.text = "You Finished"
                btn.isEnabled = false;
                mediaPlayer?.stop()
                mediaPlayerFinish?.start()

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
                    mediaPlayer?.start()
                }


            }


        }
        else if (SetArray[1].toInt() == 0 && SetArray[2] != 0)
        {
            mediaPlayer?.start()
            if (noOfSets == 2) {
                btn.text = "You Finished"
                btn.isEnabled = false;
                mediaPlayer?.stop()
                mediaPlayerFinish?.start()
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
                    mediaPlayer?.start()
                }

            }


        }
        else if (SetArray[2].toInt() == 0 && SetArray[3] != 0)
        {
            mediaPlayer?.start()
            if (noOfSets == 3) {
                btn.text = "You Finished"
                btn.isEnabled = false;
                mediaPlayer?.stop()
                mediaPlayerFinish?.start()
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
                    mediaPlayer?.start()
                }


            }

        }

        else if (SetArray[3].toInt() == 0 && SetArray[4] != 0)
        {
            mediaPlayer?.start()
            if (noOfSets == 4) {
                btn.text = "You Finished"
                btn.isEnabled = false;
                mediaPlayer?.stop()
                mediaPlayerFinish?.start()
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
                    mediaPlayer?.start()
                }


            }

        }

        else if (SetArray[4].toInt() == 0 && SetArray[5] != 0)
        {
            mediaPlayer?.start()
            if (noOfSets == 5) {
                btn.text = "You Finished"
                btn.isEnabled = false;
                mediaPlayer?.stop()
                mediaPlayerFinish?.start()
            }


        }



    }
}