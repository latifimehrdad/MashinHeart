package com.androidha.mashinheart.jobservice;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.androidha.mashinheart.R;
import com.androidha.mashinheart.databases.DataBaseCars;
import com.androidha.mashinheart.databases.DataBaseConsumable;
import com.androidha.mashinheart.databases.DataBaseInsurance;
import com.androidha.mashinheart.views.application.MachinHeartApplication;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmResults;


public class AlarmReceiver extends BroadcastReceiver {
    private Context context;
    private String notifis;
    private Integer notId = 0;
    NotificationManager notifManager;
    String CHANNEL_ONE_NAME = "New Event";
    String CHANNEL_ONE_ID = "com.androidha.mashinheart";

    public void onReceive(Context context, Intent intent) {//_______________________________________ Start onReceive

        this.context = context;
        notifis = "";
        GetNotiId();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CreateChannels();
            ShowNotificationNew(notifis);
        } else {
            ShowNotificationOld(notifis);
        }
        //CheckItems();
        //ShowNotification(notifis);

    }//_____________________________________________________________________________________________ End onReceive


    private void CheckItems() {//____________________________________________________________________ Start CheckItems

        Realm realm = MachinHeartApplication
                .getMachinHeartApplication(context)
                .getRealmComponent()
                .getRealm();

        String CurrentDate = MachinHeartApplication
                .getMachinHeartApplication(context)
                .getApplicationUtilityComponent()
                .getApplicationUtility()
                .MiladiToJalali(Calendar.getInstance().getTime(), "FullJalaliNumber");

        if (CurrentDate != null)
            CurrentDate = CurrentDate.replaceAll("/", "");
        else
            return;

        RealmResults<DataBaseCars> Cars = realm
                .where(DataBaseCars.class)
                .findAll();

        for (DataBaseCars car : Cars) {
            RealmResults<DataBaseConsumable> consumables = realm
                    .where(DataBaseConsumable.class)
                    .notEqualTo("notification", 2)
                    .and()
                    .equalTo("CarId", car.getID())
                    .findAll();

            for (DataBaseConsumable item : consumables) {
                int dayBetween = MachinHeartApplication
                        .getMachinHeartApplication(context)
                        .getApplicationUtilityComponent()
                        .getApplicationUtility()
                        .JalaliDatBetween(null, null, item.getChangeDate(), Integer.valueOf(CurrentDate));

                Long currentKm = Long.valueOf(dayBetween * car.getCarUseAverage()) + car.getCarKm();
                Long LastKm = currentKm + (7 * car.getCarUseAverage());

                if ((currentKm > item.getChangeNextKm()) || ((currentKm <= item.getChangeNextKm()) && (LastKm >= item.getChangeNextKm()))) {
                    AddNotificationItem(
                            context.getResources().getStringArray(R.array.CarBrand)[car.getCarBrand()]
                            , car.getCarColor()
                            , item.getTitle()
                            , true);
                    realm.beginTransaction();
                    item.setNotification(item.getNotification() + 1);
                    realm.commitTransaction();
                }

            }

            CurrentDate = MachinHeartApplication
                    .getMachinHeartApplication(context)
                    .getApplicationUtilityComponent()
                    .getApplicationUtility()
                    .JalaliReduceDay(null, Integer.valueOf(CurrentDate), 365);

            if (CurrentDate != null)
                CurrentDate = CurrentDate.replaceAll("/", "");
            else
                return;

            String date = MachinHeartApplication
                    .getMachinHeartApplication(context)
                    .getApplicationUtilityComponent()
                    .getApplicationUtility()
                    .JalaliAddDay(null, Integer.valueOf(CurrentDate), 7);

            if (date != null)
                date = date.replaceAll("/", "");
            else
                return;

            RealmResults<DataBaseInsurance> insurances = realm
                    .where(DataBaseInsurance.class)
                    .notEqualTo("notification", 2)
                    .and()
                    .equalTo("CarId", car.getID())
                    .and()
                    .lessThanOrEqualTo("InsuranceDate", Integer.valueOf(date))
                    .and()
                    .greaterThanOrEqualTo("InsuranceDate", Integer.valueOf(CurrentDate))
                    .findAll();

            for (DataBaseInsurance item : insurances) {
                AddNotificationItem(
                        context.getResources().getStringArray(R.array.CarBrand)[car.getCarBrand()]
                        , car.getCarColor()
                        , context.getResources().getStringArray(R.array.InsuranceType)[item.getInsuranceType()]
                        , false);
            }

        }

        if (!notifis.equalsIgnoreCase("")) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                CreateChannels();
                ShowNotificationNew(notifis);
            } else {
                ShowNotificationOld(notifis);
            }
        }


    }//_____________________________________________________________________________________________ End CheckItems


    private void AddNotificationItem(String Brand, String Color, String Title, Boolean Consumable) {// Start AddNotificationItem

        if (!notifis.equalsIgnoreCase(""))
            notifis = notifis + "\n";
        if (Consumable)
            notifis = notifis + Brand + " " + Color + " : " + context.getResources().getString(R.string.Change) + " " + Title;
        else
            notifis = notifis + Brand + " " + Color + " : " + context.getResources().getString(R.string.Get) + " " + Title;

    }//_____________________________________________________________________________________________ End AddNotificationItem


    private void ShowNotificationOld(String Text) {//_______________________________________________ Start ShowNotificationOld

        Text = "Notification : " + notId;
        long when = System.currentTimeMillis();
        NotificationCompat.Builder mNotifyBuilder = new NotificationCompat.Builder(
                context)
                .setSmallIcon(R.drawable.logo)
                .setContentTitle(context.getResources().getString(R.string.app_name))
                .setStyle(new NotificationCompat.BigTextStyle().bigText(Text))
                .setContentText(Text)
                .setSound(getSound())
                .setAutoCancel(true)
                .setWhen(when)
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});
        getManager().notify(GetNotiId(), mNotifyBuilder.build());

    }//_____________________________________________________________________________________________ End ShowNotificationOld


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void ShowNotificationNew(String Text) {//________________________________________________ Start ShowNotificationNew
        Text = "Notification : " + notId;
        Notification.Builder builder = new Notification.Builder(context, CHANNEL_ONE_ID)
                .setContentText(Text)
                .setSound(getSound())
                .setSmallIcon(R.drawable.logo)
                .setAutoCancel(true);
        getManager().notify(notId, builder.build());
    }//_____________________________________________________________________________________________ End ShowNotificationNew


    public Uri getSound() {//________________________________________________________________________ Start getSound

        return RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

    }//_____________________________________________________________________________________________ End getSound


    private Integer GetNotiId() {//__________________________________________________________________ Start GetNotiId

        Integer notIdOld = 0;
        SharedPreferences prefs = context.getSharedPreferences("mehrdad", 0);
        if (prefs == null) {
            notId = Integer.valueOf(1);
        } else {
            notId = Integer.valueOf(prefs.getInt("noti", 1));
        }

        notIdOld = notId++;
        SharedPreferences.Editor editor = context.getApplicationContext().getSharedPreferences("mehrdad", 0).edit();
        editor.putInt("noti", notIdOld);
        editor.apply();
        return notId;

    }//_____________________________________________________________________________________________ End GetNotiId


    private NotificationManager getManager() {//____________________________________________________ Start getManager
        if (notifManager == null) {
            notifManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return notifManager;
    }//_____________________________________________________________________________________________ End getManager


    @TargetApi(Build.VERSION_CODES.O)
    public void CreateChannels() {//________________________________________________________________ Start createChannels
        NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ONE_ID,
                CHANNEL_ONE_NAME, notifManager.IMPORTANCE_HIGH);
        notificationChannel.enableLights(true);
        notificationChannel.setLightColor(Color.RED);
        notificationChannel.setShowBadge(true);
        notificationChannel.enableVibration(true);
        notificationChannel.setVibrationPattern(new long[]{1000, 1000, 1000, 1000, 1000});
        notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        getManager().createNotificationChannel(notificationChannel);
    }//_____________________________________________________________________________________________ End createChannels


}
