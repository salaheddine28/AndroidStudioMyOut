package com.example.myout

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.myout.util.NotificationUtil
import com.example.myout.util.PrefUtil

class TimerExpiredReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        NotificationUtil.showTimerExpired(context)
        PrefUtil.setTimerState(Plank.TimerEnum.Stopped, context)
        PrefUtil.setAlarmSetTime(0, context)
    }
}