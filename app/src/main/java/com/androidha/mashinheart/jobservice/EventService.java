package com.androidha.mashinheart.jobservice;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.androidha.mashinheart.views.application.MachinHeartApplication;

import java.util.Calendar;

public class EventService extends Service {


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        MachinHeartApplication.getMachinHeartApplication(getApplicationContext())
                .getApplicationUtilityComponent()
                .getApplicationUtility().CustomToastShow(getApplicationContext(),"Alarm");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 22);
        calendar.set(Calendar.MINUTE, 30);
        calendar.set(Calendar.SECOND, 0);

        Calendar now = Calendar.getInstance();
        if (!now.before(calendar))
            calendar.add(Calendar.DATE, 1);
        Intent intent1 = new Intent(getApplicationContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) getApplicationContext().getSystemService(getApplicationContext().ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        return Service.START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
