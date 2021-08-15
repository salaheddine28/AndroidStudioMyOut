package com.MyOut.myout

import android.app.*
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {
    lateinit var signInEmail: String
    lateinit var signInPassword: String
    lateinit var signInInputsArray: Array<EditText>

    private val CHANNEL_ID = "channel_id_example_01"
    private val notificationId = 101

    lateinit var sharedPreferences: SharedPreferences
    var isRemembered = false

/*    comm*/



    val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotificationChannel()


        sharedPreferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)

        isRemembered = sharedPreferences.getBoolean("CHECKBOX", false)

        if (isRemembered) {

            startActivity(Intent(this, IndexActivity::class.java))
        }

        btn_SignUp.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
            finish()

        }
        btn_SignIn.setOnClickListener{
            doLogin()

            val username: String = et_username.text.toString()
            val password: String = et_password.text.toString()

            val checked: Boolean = checkboxRemember.isChecked
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("NAME", username)
            editor.putString("PASSWORD", password)
            editor.putBoolean("CHECKBOX", checked)
            editor.apply()

            Toast.makeText(this, "Information saved!", Toast.LENGTH_LONG).show()

            startActivity(Intent(this, IndexActivity::class.java))
            finish()

        }

         val alarmManager = this.getSystemService(ALARM_SERVICE) as AlarmManager
         val alarmPendingIntent by lazy {
            val intent = Intent(this, AlarmReceiver::class.java)
            PendingIntent.getBroadcast(this, 0, intent, 0)
        }
         val HOUR_TO_SHOW_PUSH = 19

        fun schedulePushNotifications() {
            val calendar = GregorianCalendar.getInstance().apply {
                if (get(Calendar.HOUR_OF_DAY) >= HOUR_TO_SHOW_PUSH) {
                    add(Calendar.DAY_OF_MONTH, 1)
                }

                set(Calendar.HOUR_OF_DAY, HOUR_TO_SHOW_PUSH)
                set(Calendar.MINUTE, 0)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }

            alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                alarmPendingIntent
            )
        }
    }
    private fun doLogin(){
        if (et_username.text.toString().isEmpty()){
            et_username.error = "please enter Email"
            et_username.requestFocus()
            return
        }


        if (et_password.text.toString().isEmpty()){
            et_password.error = "please enter Password"
            et_password.requestFocus()
            return
        }

        signInEmail = et_username.text.toString().trim()
        signInPassword = et_password.text.toString().trim()

            if (notEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(signInEmail, signInPassword)
                        .addOnCompleteListener { signIn ->
                            if (signIn.isSuccessful) {
                                startActivity(Intent(this, IndexActivity::class.java))
                                Toast.makeText(
                                        baseContext, "signed in successfully",
                                        Toast.LENGTH_SHORT
                                ).show()
                                finish()
                            } else {
                                Toast.makeText(
                                        baseContext, "Sign in failed. Try again",
                                        Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
            } else {
                signInInputsArray.forEach { input ->
                    if (input.text.toString().trim().isEmpty()) {
                        input.error = "${input.hint} is required"
                    }
                }
            }



    }

    private fun notEmpty(): Boolean = signInEmail.isNotEmpty() && signInPassword.isNotEmpty()


    private fun createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = "Notification Title"
            val descriptionText = "Notification Description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

    }

     fun sendNotification() {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = TaskStackBuilder.create(this).run {
            addNextIntentWithParentStack(intent)
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }


        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notif)
            .setContentTitle("Dont forget your daily push ups!")
            .setContentText("Train insane or remain the same.")
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this))
        {
            notify(notificationId, builder.build())
        }
    }






}