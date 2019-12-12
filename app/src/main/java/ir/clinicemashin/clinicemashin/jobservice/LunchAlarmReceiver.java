package ir.clinicemashin.clinicemashin.jobservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class LunchAlarmReceiver extends BroadcastReceiver {

    private Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        SetAlarmNotification();
    }

    private void SetAlarmNotification() {//__________________________________________________________ Start SetAlarmNotification

        context.startService(new Intent(context, EventService.class));

//        MachinHeartApplication.getMachinHeartApplication(context)
//                .getApplicationUtilityComponent()
//                .getApplicationUtility().CustomToastShow(context,"Alarm");
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.HOUR_OF_DAY, 21);
//        calendar.set(Calendar.MINUTE, 40);
//        calendar.set(Calendar.SECOND, 0);
//
//        Calendar now = Calendar.getInstance();
//        if (!now.before(calendar))
//            calendar.add(Calendar.DATE, 1);
//        Intent intent1 = new Intent(context, AlarmReceiver.class);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
//        AlarmManager am = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
//        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);


    }//_____________________________________________________________________________________________ End SetAlarmNotification


}
