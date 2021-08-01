package com.example.myout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.core.view.isVisible
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_push_up_detail.*

class PushUpDetail : AppCompatActivity() {

    lateinit var option : Spinner

    var noOfSets = "0"
    var s1 = "0"
    var s2 = "0"
    var s3 = "0"
    var s4 = "0"
    var s5 = "0"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_push_up_detail)

        val options = arrayOf("1 set", "2 sets", "3 sets", "4 sets", "5 sets")

        option = findViewById<Spinner>(R.id.sp_option)

        option.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,options)

        option.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?)
            {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {


                if (options[p2] == "1 set")
                { set1.isVisible = true
                    set1tv.isVisible = true
                    set2.isVisible = false
                    set2tv.isVisible = false
                    set3.isVisible = false
                    set3tv.isVisible = false
                    set4.isVisible = false
                    set4tv.isVisible = false
                    set5.isVisible = false
                    set5tv.isVisible = false
                    noOfSets = "1"
                }
                if (options[p2] == "2 sets")
                {
                    set1.isVisible = true
                    set1tv.isVisible = true
                    set2.isVisible = true
                    set2tv.isVisible = true
                    set3.isVisible = false
                    set3tv.isVisible = false
                    set4.isVisible = false
                    set4tv.isVisible = false
                    set5.isVisible = false
                    set5tv.isVisible = false
                    noOfSets = "2"
                }
                if (options[p2] == "3 sets")
                {
                    set1.isVisible = true
                    set1tv.isVisible = true
                    set2.isVisible = true
                    set2tv.isVisible = true
                    set3.isVisible = true
                    set3tv.isVisible = true
                    set4.isVisible = false
                    set4tv.isVisible = false
                    set5.isVisible = false
                    set5tv.isVisible = false
                    noOfSets = "3"
                }
                if (options[p2] == "4 sets")
                {
                    set1.isVisible = true
                    set1tv.isVisible = true
                    set2.isVisible = true
                    set2tv.isVisible = true
                    set3.isVisible = true
                    set3tv.isVisible = true
                    set4.isVisible = true
                    set4tv.isVisible = true
                    set5.isVisible = false
                    set5tv.isVisible = false
                    noOfSets = "4"
                }
                if (options[p2] == "5 sets")
                {
                    set1.isVisible = true
                    set1tv.isVisible = true
                    set2.isVisible = true
                    set2tv.isVisible = true
                    set3.isVisible = true
                    set3tv.isVisible = true
                    set4.isVisible = true
                    set4tv.isVisible = true
                    set5.isVisible = true
                    set5tv.isVisible = true
                    noOfSets = "5"
                }

            }
        }





        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        //bottomNavigation.setSelectedItemId(R.id.home)
        bottomNavigation.setOnNavigationItemSelectedListener(navigationBar)

        btn_Start.setOnClickListener{

            val intent = Intent(this, PushUp::class.java)
            intent.putExtra("noOfSets", noOfSets)

            var setsArray: ArrayList<String> = ArrayList()

            setsArray.add(set1.text.toString())
            setsArray.add(set2.text.toString())
            setsArray.add(set3.text.toString())
            setsArray.add(set4.text.toString())
            setsArray.add(set5.text.toString())

            intent.putExtra("key", setsArray)
            startActivity(intent)
            finish()
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
}