package com.MyOut.myout

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.MyOut.myout.util.NotificationUtil
import com.MyOut.myout.util.PrefUtil

class TimerExpiredReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        NotificationUtil.showTimerExpired(context)
        PrefUtil.setTimerState(Plank.TimerEnum.Stopped, context)
        PrefUtil.setAlarmSetTime(0, context)
    }
}