package com.androidha.mashinheart.jobservice;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.androidha.mashinheart.views.activitys.MainActivity;
import com.androidha.mashinheart.views.application.MachinHeartApplication;

import java.util.Calendar;

public class LunchAlarmReceiver extends BroadcastReceiver {

    private Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
//        MachinHeartApplication
//                .getMachinHeartApplication(context)
//                .getApplicationUtilityComponent()
//                .getApplicationUtility()
//                .CustomToastShow(context, "AlarmOn");
        SetAlarmNotification();
    }

    private void SetAlarmNotification() {//__________________________________________________________ Start SetAlarmNotification

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 17);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 0);

        Calendar now = Calendar.getInstance();
        if (!now.before(calendar))
            calendar.add(Calendar.DATE, 1);
        Intent intent1 = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);


    }//_____________________________________________________________________________________________ End SetAlarmNotification


}
