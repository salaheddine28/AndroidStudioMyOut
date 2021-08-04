package com.example.myout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUp : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        auth = FirebaseAuth.getInstance()

        SignUp_btn_GoBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        SignUp_btn_SignUp.setOnClickListener {
            signUpUser()

        }
    }

    private fun signUpUser() {
        if (SignUp_FirstName.text.toString().isEmpty()){
            SignUp_FirstName.error = "please enter Firstname"
            SignUp_FirstName.requestFocus()
            return
        }
        if (SignUp_LastName.text.toString().isEmpty()){
            SignUp_LastName.error = "please enter Lastname"
            SignUp_LastName.requestFocus()
            return
        }
        if (SignUp_Email.text.toString().isEmpty()){
            SignUp_Email.error = "please enter Email"
            SignUp_Email.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(SignUp_Email.text.toString()).matches()){
            SignUp_Email.error = "please enter valid Email"
            SignUp_Email.requestFocus()
            return
        }

        if (SignUp_Password.text.toString().isEmpty()){
            SignUp_Password.error = "please enter Password"
            SignUp_Password.requestFocus()
            return
        }
        auth.createUserWithEmailAndPassword(SignUp_Email.text.toString(), SignUp_Password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user?.sendEmailVerification()
                        ?.addOnCompleteListener{ task ->
                            if (task.isSuccessful){
                                val intent = Intent(this, MainActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                        }
                } else {
                    Toast.makeText(
                        baseContext, "Sign Up failed. Try again",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        if (SignUp_Height.text.toString().isEmpty()){
            SignUp_Height.error = "please enter Height"
            SignUp_Height.requestFocus()
            return
        }
        if (SignUp_Weight.text.toString().isEmpty()){
            SignUp_Weight.error = "please enter weight"
            SignUp_Weight.requestFocus()
            return
        }
        if (SignUp_Gender.text.toString().isEmpty()){
            SignUp_Gender.error = "please select Gender"
            SignUp_Gender.requestFocus()
            return
        }
        if (Birthday.text.toString().isEmpty()){
            Birthday.error = "please enter birthday"
            Birthday.requestFocus()
            return
        }
    }
}