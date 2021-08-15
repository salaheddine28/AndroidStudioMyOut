package com.MyOut.myout

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.MenuItem
import com.MyOut.myout.util.NotificationUtil
import com.MyOut.myout.util.PrefUtil
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_plank.*
import kotlinx.android.synthetic.main.activity_plank.progress_countdown
import kotlinx.android.synthetic.main.activity_plank.textView_counter
import kotlinx.android.synthetic.main.activity_wall_sit.*
import java.util.*

class WallSit : AppCompatActivity() {

    private var mediaPlayerFinish: MediaPlayer? = null
var time = 0
    companion object {
        fun setAlarm(context: Context, nowSeconds: Long, secondsRemaining: Long): Long{
            val wakeUpTime = (nowSeconds + secondsRemaining) * 1000
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(context, TimerExpiredReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, wakeUpTime, pendingIntent)
            PrefUtil.setAlarmSetTime(nowSeconds, context)
            return wakeUpTime
        }

        fun removeAlarm(context: Context){
            val intent = Intent(context, TimerExpiredReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManager.cancel(pendingIntent)
            PrefUtil.setAlarmSetTime(0, context)
        }
        val nowSeconds: Long
            get() = Calendar.getInstance().timeInMillis / 1000
    }




    enum class TimerEnum{
        Started, Paused, Stopped
    }

    private lateinit var timer: CountDownTimer
    private var timerLengthSeconds: Long = 0L
    private var timerState = TimerEnum.Stopped

    private var secondsRemaining = 0L

    override fun onResume() {
        super.onResume()

        initTimer()

        WallSit.removeAlarm(this)
        NotificationUtil.hideTimerNotification(this)
    }

    override fun onPause() {
        super.onPause()

        if (timerState == WallSit.TimerEnum.Started){
            timer.cancel()
            val wakeUpTime = WallSit.setAlarm(this, WallSit.nowSeconds, secondsRemaining)
            NotificationUtil.showTimerRunning(this, wakeUpTime)
        }
        else if (timerState == WallSit.TimerEnum.Paused){
            NotificationUtil.showTimerPaused(this)
        }
        PrefUtil.setPreviousTimerLengthSeconds(timerLengthSeconds, this)
        PrefUtil.setSecondsRemaining(secondsRemaining, this)
        PrefUtil.setTimerStateWall(timerState, this)
    }

    private fun initTimer(){
        timerState = PrefUtil.getTimerStateWall(this)

        //we don't want to change the length of the timer which is already running
        //if the length was changed in settings while it was backgrounded
        if (timerState == WallSit.TimerEnum.Stopped)
            setNewTimerLength()
        else
            setPreviousTimerLength()

        secondsRemaining = if (timerState == WallSit.TimerEnum.Started || timerState == WallSit.TimerEnum.Paused)
            PrefUtil.getSecondsRemaining(this)
        else
            timerLengthSeconds

        val alarmSetTime = PrefUtil.getAlarmSetTime(this)
        if (alarmSetTime > 0)
            secondsRemaining -= WallSit.nowSeconds - alarmSetTime

        if (secondsRemaining <= 0)
            onTimerFinished()
        else if (timerState == WallSit.TimerEnum.Started)
            startTimer()

        updateButtons()
        updateCountdownUI()

        mediaPlayerFinish = MediaPlayer.create(this, R.raw.finish)
    }

    private fun onTimerFinished(){
        timerState = TimerEnum.Stopped
        mediaPlayerFinish?.start()

        //set the length of the timer to be the one set in SettingsActivity
        //if the length was changed when the timer was running
        setNewTimerLength()

        progress_countdown.progress = 0

        PrefUtil.setSecondsRemaining(timerLengthSeconds, this)
        secondsRemaining = timerLengthSeconds

        updateButtons()
        updateCountdownUI()
    }

    private fun startTimer(){
        timerState = WallSit.TimerEnum.Started

        timer = object : CountDownTimer(secondsRemaining * 1000, 1000) {
            override fun onFinish() = onTimerFinished()

            override fun onTick(millisUntilFinished: Long) {
                secondsRemaining = millisUntilFinished / 1000
                updateCountdownUI()
            }
        }.start()
    }

    private fun setNewTimerLength(){

        timerLengthSeconds = (time * 60L)
        progress_countdown.max = timerLengthSeconds.toInt()
    }

    private fun setPreviousTimerLength(){
        timerLengthSeconds = PrefUtil.getPreviousTimerLengthSeconds(this)
        progress_countdown.max = timerLengthSeconds.toInt()
    }

    private fun updateCountdownUI(){
        val minutesUntilFinished = secondsRemaining / 60
        val secondsInMinuteUntilFinished = secondsRemaining - minutesUntilFinished * 60
        val secondsStr = secondsInMinuteUntilFinished.toString()
        textView_counter.text = "$minutesUntilFinished:${if (secondsStr.length == 2) secondsStr else "0" + secondsStr}"
        progress_countdown.progress = (timerLengthSeconds - secondsRemaining).toInt()
    }

    private fun updateButtons(){
        when (timerState) {
            WallSit.TimerEnum.Started ->{
                btn_StartWall.isEnabled = false
                btn_PauseWall.isEnabled = true
                btn_StopWall.isEnabled = true
            }
            WallSit.TimerEnum.Stopped -> {
                btn_StartWall.isEnabled = true
                btn_PauseWall.isEnabled = false
                btn_StopWall.isEnabled = false
            }
            WallSit.TimerEnum.Paused -> {
                btn_StartWall.isEnabled = true
                btn_PauseWall.isEnabled = false
                btn_StopWall.isEnabled = true
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wall_sit)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        //bottomNavigation.setSelectedItemId(R.id.home)
        bottomNavigation.setOnNavigationItemSelectedListener(navigationBar)


        btn_StartWall.setOnClickListener{v ->
            startTimer()
            timerState = WallSit.TimerEnum.Started
            updateButtons()
        }

        btn_PauseWall.setOnClickListener{v ->
            timer.cancel()
            timerState = WallSit.TimerEnum.Paused
            updateButtons()
        }
        btn_StopWall.setOnClickListener{v ->
            timer.cancel()
            timerState = WallSit.TimerEnum.Stopped
            onTimerFinished()
        }

        time = intent.getStringExtra("key").toString().toInt()
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