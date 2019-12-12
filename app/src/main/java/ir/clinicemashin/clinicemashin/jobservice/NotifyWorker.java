package ir.clinicemashin.clinicemashin.jobservice;

import android.content.Context;

import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class NotifyWorker extends Worker {
    private Integer notId;

    public NotifyWorker(Context context, WorkerParameters workerParams) {
        super(context, workerParams);
    }

    public Result doWork() {
//        SimpleDateFormat dfd = new SimpleDateFormat("hh:mm:ss");
//        String m = dfd.format(new Date());
//        long when = System.currentTimeMillis();
//        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService("notification");
//        String str = "mehrdad";
//        SharedPreferences prefs = getApplicationContext().getSharedPreferences(str, 0);
//        String str2 = "noti";
//        if (prefs == null) {
//            this.notId = Integer.valueOf(1);
//        } else {
//            this.notId = Integer.valueOf(prefs.getInt(str2, 1));
//        }
//        Uri alarmSound = RingtoneManager.getDefaultUri(2);
//        Builder smallIcon = new Builder(getApplicationContext()).setSmallIcon(R.drawable.add_car);
//        StringBuilder sb = new StringBuilder();
//        sb.append(m);
//        sb.append(" notif : ");
//        sb.append(this.notId);
//        notificationManager
//                .notify(this.notId.intValue(),
//                        smallIcon.setContentTitle(sb.toString())
//                                .setContentText("Events to be Performed")
//                                .setSound(alarmSound).setAutoCancel(true)
//                                .setWhen(when)
//                                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000}).build());
//
//        this.notId = Integer.valueOf(this.notId.intValue() + 1);
//        Editor editor = getApplicationContext().getSharedPreferences(str, 0).edit();
//        editor.putInt(str2, this.notId.intValue());
//        editor.apply();
//        Calendar currentDate = Calendar.getInstance();
//        Calendar dueDate = Calendar.getInstance();
//        dueDate.set(11, 20);
//        dueDate.set(12, 0);
//        dueDate.set(13, 0);
//        if (dueDate.before(currentDate)) {
//            dueDate.add(11, 24);
//        }
//        Long inter = Long.valueOf(dueDate.getTimeInMillis() - currentDate.getTimeInMillis());
//        if (inter.longValue() == 0) {
//            inter = Long.valueOf(86399990);
//        }
//        SimpleDateFormat simpleDateFormat = dfd;
//        WorkManager.getInstance(getApplicationContext()).enqueue((WorkRequest) (OneTimeWorkRequest) ((OneTimeWorkRequest.Builder) new OneTimeWorkRequest.Builder(NotifyWorker.class).setInitialDelay(inter.longValue(), TimeUnit.MILLISECONDS)).build());
//        StringBuilder sb2 = new StringBuilder();
//        sb2.append("Service Start : ");
//        sb2.append(m);
//        sb2.append(" *** notId : ");
//        sb2.append(this.notId);
//        Log.i("meri", sb2.toString());
        return Result.success();
    }
}
