package com.example.myout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_sign_up.*
import android.content.ContentValues.TAG

class SignUp : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    val db = Firebase.firestore

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

    fun saveData(firstname: String, lastname: String, email: String, height: String, weight: String, birthday: String){
        val user = hashMapOf(
            "Firstname" to firstname,
            "Lastname" to lastname,
            "email" to email,
            "height" to height,
            "weight" to weight,
            "birthday" to birthday
        )

        // Add a new document with a generated ID
        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }
    fun signUpUser(){
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
        if (Birthday.text.toString().isEmpty()){
            Birthday.error = "please enter birthday"
            Birthday.requestFocus()
            return
        }

        auth.createUserWithEmailAndPassword(SignUp_Email.text.toString(), SignUp_Password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user?.sendEmailVerification()
                        ?.addOnCompleteListener{ task ->
                            if (task.isSuccessful){
                                    val firstName = SignUp_FirstName.text.toString()
                                    val lastName = SignUp_LastName.text.toString()
                                    val Email = SignUp_Email.text.toString()
                                    val Height = SignUp_Height.text.toString()
                                    val Weight = SignUp_Weight.text.toString()
                                    val BirthDay = Birthday.text.toString()
                                    saveData(firstName, lastName, Email, Height, Weight, BirthDay)
                                    val intent = Intent(this, MainActivity::class.java)
                                    startActivity(intent)
                                    finish()
                                }

                                else{
                                    Toast.makeText(applicationContext,"All fields are required",Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                 else {
                    Toast.makeText(
                        baseContext, "Sign Up failed. Try again",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

    }

}