package com.MyOut.myout

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
import kotlinx.android.synthetic.main.activity_plank_detail.*
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

        btn_instructionsPushUps.setOnClickListener{
            val intent = Intent(this, InstructionsPushUps::class.java)
            startActivity(intent)
        }

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

                    btn_Start.isEnabled = false
                    val editTexts = listOf(set1)
                    for (editText in editTexts) {
                        editText.addTextChangedListener(object : TextWatcher {
                            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                                var et1 = set1.text.toString().trim()
                                btn_Start.isEnabled = et1.isNotEmpty()
                            }

                            override fun beforeTextChanged(
                                s: CharSequence, start: Int, count: Int, after: Int) {
                            }

                            override fun afterTextChanged(
                                s: Editable
                            ) {
                            }
                        })
                    }


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

                    btn_Start.isEnabled = false
                    val editTexts = listOf(set1, set2)
                    for (editText in editTexts) {
                        editText.addTextChangedListener(object : TextWatcher {
                            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                                var et1 = set1.text.toString().trim()
                                var et2 = set2.text.toString().trim()


                                btn_Start.isEnabled = et1.isNotEmpty()
                                        && et2.isNotEmpty()
                            }

                            override fun beforeTextChanged(
                                s: CharSequence, start: Int, count: Int, after: Int) {
                            }

                            override fun afterTextChanged(
                                s: Editable) {
                            }
                        })
                    }

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

                    btn_Start.isEnabled = false
                    val editTexts = listOf(set1, set2, set3)
                    for (editText in editTexts) {
                        editText.addTextChangedListener(object : TextWatcher {
                            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                                var et1 = set1.text.toString().trim()
                                var et2 = set2.text.toString().trim()
                                var et3 = set3.text.toString().trim()


                                btn_Start.isEnabled = et1.isNotEmpty()
                                        && et2.isNotEmpty()
                                        && et3.isNotEmpty()
                            }

                            override fun beforeTextChanged(
                                s: CharSequence, start: Int, count: Int, after: Int) {
                            }

                            override fun afterTextChanged(
                                s: Editable) {
                            }
                        })
                    }
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

                    btn_Start.isEnabled = false
                    val editTexts = listOf(set1, set2, set3, set4)
                    for (editText in editTexts) {
                        editText.addTextChangedListener(object : TextWatcher {
                            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                                var et1 = set1.text.toString().trim()
                                var et2 = set2.text.toString().trim()
                                var et3 = set3.text.toString().trim()
                                var et4 = set4.text.toString().trim()


                                btn_Start.isEnabled = et1.isNotEmpty()
                                        && et2.isNotEmpty()
                                        && et3.isNotEmpty()
                                        && et4.isNotEmpty()
                            }

                            override fun beforeTextChanged(
                                s: CharSequence, start: Int, count: Int, after: Int) {
                            }

                            override fun afterTextChanged(
                                s: Editable) {
                            }
                        })
                    }
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

                    btn_Start.isEnabled = false
                    val editTexts = listOf(set1, set2, set3, set4, set5)
                    for (editText in editTexts) {
                        editText.addTextChangedListener(object : TextWatcher {
                            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                                var et1 = set1.text.toString().trim()
                                var et2 = set2.text.toString().trim()
                                var et3 = set3.text.toString().trim()
                                var et4 = set4.text.toString().trim()
                                var et5 = set5.text.toString().trim()


                                btn_Start.isEnabled = et1.isNotEmpty()
                                        && et2.isNotEmpty()
                                        && et3.isNotEmpty()
                                        && et4.isNotEmpty()
                                        && et5.isNotEmpty()
                            }

                            override fun beforeTextChanged(
                                s: CharSequence, start: Int, count: Int, after: Int) {
                            }

                            override fun afterTextChanged(
                                s: Editable) {
                            }
                        })
                    }
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

        }
        false

    }
}