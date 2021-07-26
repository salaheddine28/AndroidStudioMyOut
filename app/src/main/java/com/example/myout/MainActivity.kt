package com.example.myout

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_SignIn.setOnClickListener{

            if (et_username.text.toString().isEmpty()){
                Toast.makeText(this,
                    "This username and password combination is invalid", Toast.LENGTH_SHORT).show()
            }else{
                val intent = Intent(this, IndexActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        btn_SignUp.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
            finish()
        }
    }
}