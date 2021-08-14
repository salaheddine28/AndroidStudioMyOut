package com.MyOut.myout

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.MyOut.myout.util.NotificationUtil
import com.MyOut.myout.util.PrefUtil


class TimerNotificationActionReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action){
            AppConstants.ACTION_STOP -> {
                Plank.removeAlarm(context)
                PrefUtil.setTimerState(Plank.TimerEnum.Stopped, context)
                NotificationUtil.hideTimerNotification(context)
            }
            AppConstants.ACTION_PAUSE -> {
                var secondsRemaining = PrefUtil.getSecondsRemaining(context)
                val alarmSetTime = PrefUtil.getAlarmSetTime(context)
                val nowSeconds = Plank.nowSeconds

                secondsRemaining -= nowSeconds - alarmSetTime
                PrefUtil.setSecondsRemaining(secondsRemaining, context)

                Plank.removeAlarm(context)
                PrefUtil.setTimerState(Plank.TimerEnum.Paused, context)
                NotificationUtil.showTimerPaused(context)
            }
            AppConstants.ACTION_RESUME -> {
                val secondsRemaining = PrefUtil.getSecondsRemaining(context)
                val wakeUpTime = Plank.setAlarm(context, Plank.nowSeconds, secondsRemaining)
                PrefUtil.setTimerState(Plank.TimerEnum.Started, context)
                NotificationUtil.showTimerRunning(context, wakeUpTime)
            }
            AppConstants.ACTION_START -> {
                val minutesRemaining = PrefUtil.getTimerLength(context)
                val secondsRemaining = minutesRemaining * 60L
                val wakeUpTime = Plank.setAlarm(context, Plank.nowSeconds, secondsRemaining)
                PrefUtil.setTimerState(Plank.TimerEnum.Started, context)
                PrefUtil.setSecondsRemaining(secondsRemaining, context)
                NotificationUtil.showTimerRunning(context, wakeUpTime)
            }
        }
    }
}