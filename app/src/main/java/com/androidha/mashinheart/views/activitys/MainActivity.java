package com.androidha.mashinheart.views.activitys;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import butterknife.BindView;
import butterknife.ButterKnife;
import android.content.SharedPreferences.Editor;

import com.androidha.mashinheart.R;
import com.androidha.mashinheart.dagger.persianpicker.PersianPickerModul;
import com.androidha.mashinheart.databinding.ActivityMainBinding;
import com.androidha.mashinheart.jobservice.AlarmReceiver;
import com.androidha.mashinheart.viewmodels.ActivityMainViewModel;
import com.androidha.mashinheart.views.application.MachinHeartApplication;
import com.androidha.mashinheart.views.dialogs.DialogNewAdvertise;
import com.androidha.mashinheart.views.fragments.FragmentAddCar;
import com.androidha.mashinheart.views.fragments.FragmentAdvertise;
import com.androidha.mashinheart.views.fragments.FragmentCarEvent;
import com.androidha.mashinheart.views.fragments.FragmentNegativeGrade;
import com.androidha.mashinheart.views.fragments.FragmentPoliceFine;
import com.androidha.mashinheart.views.fragments.FragmentYouCar;

import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

import java.util.Calendar;
import java.util.Date;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

    public static Integer CarId = null;
    public static PublishSubject<Integer> ChooseCar = null;
    public static PublishSubject<String> FragmentMessage = null;
    public static final String workTag = "notificationWork";
    private DialogNewAdvertise dialogNewAdvertise;

    @BindView(R.id.MainAddCar)
    LinearLayout MainAddCar;
    @BindView(R.id.MainAddCarDownShadow)
    LinearLayout MainAddCarDownShadow;
    @BindView(R.id.MainAddCarLeft)
    LinearLayout MainAddCarLeft;
    @BindView(R.id.MainAddCarLine)
    LinearLayout MainAddCarLine;
    @BindView(R.id.MainAddCarUpShadow)
    LinearLayout MainAddCarUpShadow;
    @BindView(R.id.MainAgaring)
    LinearLayout MainAgaring;
    @BindView(R.id.MainAgaringLeft)
    LinearLayout MainAgaringLeft;
    @BindView(R.id.MainAgaringUpShadow)
    LinearLayout MainAgaringUpShadow;
    @BindView(R.id.MainCarSale)
    LinearLayout MainCarSale;
    @BindView(R.id.MainCarSaleDownShadow)
    LinearLayout MainCarSaleDownShadow;
    @BindView(R.id.MainCarSaleLeft)
    LinearLayout MainCarSaleLeft;
    @BindView(R.id.MainCarSaleLine)
    LinearLayout MainCarSaleLine;
    @BindView(R.id.MainCarSaleUpShadow)
    LinearLayout MainCarSaleUpShadow;
    @BindView(R.id.MainChangeCar)
    LinearLayout MainChangeCar;
    @BindView(R.id.MainChangeCarDownShadow)
    LinearLayout MainChangeCarDownShadow;
    @BindView(R.id.MainChangeCarLeft)
    LinearLayout MainChangeCarLeft;
    @BindView(R.id.MainChangeCarLine)
    LinearLayout MainChangeCarLine;
    @BindView(R.id.MainChangeCarUpShadow)
    LinearLayout MainChangeCarUpShadow;
    @BindView(R.id.MainImgCircleAdvertise)
    CircleImageView MainImgCircleAdvertise;
    @BindView(R.id.MainNegativeGrade)
    LinearLayout MainNegativeGrade;
    @BindView(R.id.MainNegativeGradeDownShadow)
    LinearLayout MainNegativeGradeDownShadow;
    @BindView(R.id.MainNegativeGradeLeft)
    LinearLayout MainNegativeGradeLeft;
    @BindView(R.id.MainNegativeGradeLine)
    LinearLayout MainNegativeGradeLine;
    @BindView(R.id.MainNegativeGradeUpShadow)
    LinearLayout MainNegativeGradeUpShadow;
    @BindView(R.id.MainAdvertise)
    LinearLayout MainAdvertise;
    @BindView(R.id.MainAdvertiseDownShadow)
    LinearLayout MainAdvertiseDownShadow;
    @BindView(R.id.MainAdvertiseLeft)
    LinearLayout MainAdvertiseLeft;
    @BindView(R.id.MainAdvertiseLine)
    LinearLayout MainAdvertiseLine;
    @BindView(R.id.MainAdvertiseUpShadow)
    LinearLayout MainAdvertiseUpShadow;
    @BindView(R.id.MainPoliceFine)
    LinearLayout MainPoliceFine;
    @BindView(R.id.MainPoliceFineDownShadow)
    LinearLayout MainPoliceFineDownShadow;
    @BindView(R.id.MainPoliceFineLeft)
    LinearLayout MainPoliceFineLeft;
    @BindView(R.id.MainPoliceFineLine)
    LinearLayout MainPoliceFineLine;
    @BindView(R.id.MainPoliceFineUpShadow)
    LinearLayout MainPoliceFineUpShadow;
    @BindView(R.id.MainUpdateInfo)
    LinearLayout MainUpdateInfo;
    @BindView(R.id.MainUpdateInfoDownShadow)
    LinearLayout MainUpdateInfoDownShadow;
    @BindView(R.id.MainUpdateInfoLeft)
    LinearLayout MainUpdateInfoLeft;
    @BindView(R.id.MainUpdateInfoLine)
    LinearLayout MainUpdateInfoLine;
    @BindView(R.id.MainUpdateInfoUpShadow)
    LinearLayout MainUpdateInfoUpShadow;
    @BindView(R.id.MainYouCar)
    LinearLayout MainYouCar;
    @BindView(R.id.MainYouCarDownShadow)
    LinearLayout MainYouCarDownShadow;
    @BindView(R.id.MainYouCarImg)
    ImageView MainYouCarImg;
    @BindView(R.id.MainYouCarLeft)
    LinearLayout MainYouCarLeft;
    @BindView(R.id.MainYouCarLine)
    LinearLayout MainYouCarLine;
    @BindView(R.id.MainYouCarText)
    TextView MainYouCarText;
    @BindView(R.id.MainYouCarUpShadow)
    LinearLayout MainYouCarUpShadow;
    @BindView(R.id.MainProfileName)
    TextView MainProfileName;
    @BindView(R.id.MainProfileTel)
    TextView MainProfileTel;

    FragmentManager fm;
    FragmentTransaction ft;
    ActivityMainViewModel viewModel;

    public void onCreate(Bundle savedInstanceState) {//_____________________________________________ Start onCreate
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        this.viewModel = new ActivityMainViewModel(this);
        activityMainBinding.setMain(this.viewModel);
        PersianPickerModul.context = this;
        ButterKnife.bind(this);
        FragmentMessage = PublishSubject.create();
        ChooseCar = PublishSubject.create();
        ResetRightMenu();
        ClickItems();
        SetAnimationViews();
        ShowFragmentYouCar();
        FragmentObserver();

//        Integer notId = 0;
//        SharedPreferences prefs = getSharedPreferences("mehrdad", 0);
//        if (prefs == null) {
//            notId = Integer.valueOf(1);
//        } else {
//            notId = Integer.valueOf(prefs.getInt("noti", 1));
//        }
//
//        notId++;
//        Editor editor = getApplicationContext().getSharedPreferences("mehrdad", 0).edit();
//        editor.putInt("noti", notId);
//        editor.apply();

        SetProfile();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent("ir.MachinHeart.Lunch");
                sendBroadcast(i);
            }
        }, 1000);
        //SetAlarmNotification();


    }//_____________________________________________________________________________________________ End onCreate




    private void SetProfile(){//____________________________________________________________________ Start SetProfile
        SharedPreferences prefs = getSharedPreferences("mehrdad", 0);
        if (prefs != null) {
            MainProfileName.setText(prefs.getString("profname",this.getResources().getString(R.string.ProfileName)));
            MainProfileTel.setText(prefs.getString("proftel",this.getResources().getString(R.string.ProfilePhone)));
        }
    }//_____________________________________________________________________________________________ End SetProfile


    private void SetAlarmNotification() {//__________________________________________________________ Start SetAlarmNotification

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 15);
        calendar.set(Calendar.MINUTE, 13);
        calendar.set(Calendar.SECOND, 0);

        Calendar now = Calendar.getInstance();
        if (!now.before(calendar))
            calendar.add(Calendar.DATE, 1);
        Intent intent1 = new Intent(MainActivity.this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) MainActivity.this.getSystemService(MainActivity.this.ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);


    }//_____________________________________________________________________________________________ End SetAlarmNotification


    private void ClickItems() {//_______________________________________________________________ Start ClickItems

        MainYouCar.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ShowFragmentYouCar();
            }
        });

        MainAddCar.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ResetRightMenu();
                MainAddCar.setBackgroundColor(MainActivity.this.getResources().getColor(R.color.MainFragment));
                MainAddCarLeft.setVisibility(View.GONE);
                MainAddCarUpShadow.setVisibility(View.VISIBLE);
                MainAddCarDownShadow.setVisibility(View.VISIBLE);
                MainAddCarLine.setVisibility(View.GONE);
                MainYouCarLine.setVisibility(View.GONE);
                fm = null;
                ft = null;
                fm = getSupportFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.MainFragment, new FragmentAddCar(MainActivity.this, false));
                ft.commit();
            }
        });

        MainPoliceFine.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ResetRightMenu();
                MainPoliceFine.setBackgroundColor(MainActivity.this.getResources().getColor(R.color.MainFragment));
                MainPoliceFineLeft.setVisibility(View.GONE);
                MainPoliceFineUpShadow.setVisibility(View.VISIBLE);
                MainPoliceFineDownShadow.setVisibility(View.VISIBLE);
                MainPoliceFineLine.setVisibility(View.GONE);
                MainAddCarLine.setVisibility(View.GONE);
                fm = null;
                ft = null;
                fm = getSupportFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.MainFragment, new FragmentPoliceFine(MainActivity.this));
                ft.commit();
            }
        });


        MainNegativeGrade.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ResetRightMenu();
                MainNegativeGrade.setBackgroundColor(MainActivity.this.getResources().getColor(R.color.MainFragment));
                MainNegativeGradeLeft.setVisibility(View.GONE);
                MainNegativeGradeUpShadow.setVisibility(View.VISIBLE);
                MainNegativeGradeDownShadow.setVisibility(View.VISIBLE);
                MainNegativeGradeLine.setVisibility(View.GONE);
                MainPoliceFineLine.setVisibility(View.GONE);
                fm = null;
                ft = null;
                fm = getSupportFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.MainFragment, new FragmentNegativeGrade(MainActivity.this));
                ft.commit();
            }
        });


        MainAdvertise.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ResetRightMenu();
                MainAdvertise.setBackgroundColor(MainActivity.this.getResources().getColor(R.color.MainFragment));
                MainAdvertiseLeft.setVisibility(View.GONE);
                MainAdvertiseUpShadow.setVisibility(View.VISIBLE);
                MainAdvertiseDownShadow.setVisibility(View.VISIBLE);
                MainAdvertiseLine.setVisibility(View.GONE);
                MainNegativeGradeLine.setVisibility(View.GONE);
                fm = null;
                ft = null;
                fm = getSupportFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.MainFragment, new FragmentAdvertise(MainActivity.this));
                ft.commit();

            }
        });


        MainChangeCar.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ResetRightMenu();
                MainChangeCar.setBackgroundColor(MainActivity.this.getResources().getColor(R.color.MainFragment));
                MainChangeCarLeft.setVisibility(View.GONE);
                MainChangeCarUpShadow.setVisibility(View.VISIBLE);
                MainChangeCarDownShadow.setVisibility(View.VISIBLE);
                MainChangeCarLine.setVisibility(View.GONE);
                MainAdvertiseLine.setVisibility(View.GONE);
            }
        });

        MainCarSale.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ResetRightMenu();
                MainCarSale.setBackgroundColor(MainActivity.this.getResources().getColor(R.color.MainFragment));
                MainCarSaleLeft.setVisibility(View.GONE);
                MainCarSaleUpShadow.setVisibility(View.VISIBLE);
                MainCarSaleDownShadow.setVisibility(View.VISIBLE);
                MainCarSaleLine.setVisibility(View.GONE);
                MainChangeCarLine.setVisibility(View.GONE);
            }
        });


        MainUpdateInfo.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ResetRightMenu();
                MainUpdateInfo.setBackgroundColor(MainActivity.this.getResources().getColor(R.color.MainFragment));
                MainUpdateInfoLeft.setVisibility(View.GONE);
                MainUpdateInfoUpShadow.setVisibility(View.VISIBLE);
                MainUpdateInfoDownShadow.setVisibility(View.VISIBLE);
                MainUpdateInfoLine.setVisibility(View.GONE);
                MainCarSaleLine.setVisibility(View.GONE);
            }
        });

        MainAgaring.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ResetRightMenu();
                MainAgaring.setBackgroundColor(MainActivity.this.getResources().getColor(R.color.MainFragment));
                MainAgaringLeft.setVisibility(View.GONE);
                MainAgaringUpShadow.setVisibility(View.VISIBLE);
                MainUpdateInfoLine.setVisibility(View.GONE);
            }
        });

        MainImgCircleAdvertise.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialogNew();
            }
        });

    }//_____________________________________________________________________________________________ End ClickItems


    private void ResetRightMenu() {//_______________________________________________________________ Start ResetRightMenu

        MainYouCar.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.MainRight));
        MainYouCarLeft.setVisibility(View.VISIBLE);
        MainYouCarUpShadow.setVisibility(View.GONE);
        MainYouCarDownShadow.setVisibility(View.GONE);
        MainYouCarLine.setVisibility(View.VISIBLE);
        MainYouCarImg.setImageResource(R.drawable.you_car);
        MainYouCarText.setText(getResources().getString(R.string.YouCar));
        MainAddCar.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.MainRight));
        MainAddCarLeft.setVisibility(View.VISIBLE);
        MainAddCarUpShadow.setVisibility(View.GONE);
        MainAddCarDownShadow.setVisibility(View.GONE);
        MainAddCarLine.setVisibility(View.VISIBLE);
        MainPoliceFine.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.MainRight));
        MainPoliceFineLeft.setVisibility(View.VISIBLE);
        MainPoliceFineUpShadow.setVisibility(View.GONE);
        MainPoliceFineDownShadow.setVisibility(View.GONE);
        MainPoliceFineLine.setVisibility(View.VISIBLE);
        MainNegativeGrade.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.MainRight));
        MainNegativeGradeLeft.setVisibility(View.VISIBLE);
        MainNegativeGradeUpShadow.setVisibility(View.GONE);
        MainNegativeGradeDownShadow.setVisibility(View.GONE);
        MainNegativeGradeLine.setVisibility(View.VISIBLE);
        MainAdvertise.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.MainRight));
        MainAdvertiseLeft.setVisibility(View.VISIBLE);
        MainAdvertiseUpShadow.setVisibility(View.GONE);
        MainAdvertiseDownShadow.setVisibility(View.GONE);
        MainAdvertiseLine.setVisibility(View.VISIBLE);
        MainChangeCar.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.MainRight));
        MainChangeCarLeft.setVisibility(View.VISIBLE);
        MainChangeCarUpShadow.setVisibility(View.GONE);
        MainChangeCarDownShadow.setVisibility(View.GONE);
        MainChangeCarLine.setVisibility(View.VISIBLE);
        MainCarSale.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.MainRight));
        MainCarSaleLeft.setVisibility(View.VISIBLE);
        MainCarSaleUpShadow.setVisibility(View.GONE);
        MainCarSaleDownShadow.setVisibility(View.GONE);
        MainCarSaleLine.setVisibility(View.VISIBLE);
        MainUpdateInfo.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.MainRight));
        MainUpdateInfoLeft.setVisibility(View.VISIBLE);
        MainUpdateInfoUpShadow.setVisibility(View.GONE);
        MainUpdateInfoDownShadow.setVisibility(View.GONE);
        MainUpdateInfoLine.setVisibility(View.VISIBLE);
        MainAgaring.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.MainRight));
        MainAgaringLeft.setVisibility(View.VISIBLE);
        MainAgaringUpShadow.setVisibility(View.GONE);
    }//_____________________________________________________________________________________________ End ClickRightMenu


    private void ShowFragmentYouCar() {//___________________________________________________________ Start ShowFragmentYouCar

        ResetRightMenu();
        MainYouCar.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.MainFragment));
        MainYouCarLeft.setVisibility(View.GONE);
        MainYouCarUpShadow.setVisibility(View.VISIBLE);
        MainYouCarDownShadow.setVisibility(View.VISIBLE);
        MainYouCarLine.setVisibility(View.GONE);
        fm = null;
        ft = null;
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        FragmentYouCar fragmentYouCar = new FragmentYouCar(this);
        ft.replace(R.id.MainFragment, fragmentYouCar);
        ft.commit();

    }//_____________________________________________________________________________________________ End ShowFragmentYouCar


    private void ShowFragmentCarEvent(Integer Brand) {//___________________________________________________________ Start ShowFragmentCarEvent

        MainYouCarImg.setImageResource(getResources().obtainTypedArray(R.array.CarLogo).getResourceId(Brand, R.drawable.logo));
        MainYouCarText.setText(getResources().getStringArray(R.array.CarBrand)[Brand]);
        fm = null;
        ft = null;
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        FragmentCarEvent fragmentCarEvent = new FragmentCarEvent(CarId, this);
        ft.replace(R.id.MainFragment, fragmentCarEvent);
        ft.commit();
        SetAnimationYouCar();
    }//_____________________________________________________________________________________________ End ShowFragmentCarEvent


    private void FragmentObserver() {//_____________________________________________________________ Start FragmentObserver

        FragmentMessage
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (s.equalsIgnoreCase("CommitAdd")) {
                                    MainActivity.this.ShowFragmentYouCar();
                                }
                            }
                        });
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        ChooseCar
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Integer>() {
                    @Override
                    public void onNext(Integer brand) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                MainActivity.this.ShowFragmentCarEvent(brand);
                            }
                        });
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }//_____________________________________________________________________________________________ End FragmentObserver


    private void SetAnimationViews() {//____________________________________________________________ Start SetAnimationViews

        MainImgCircleAdvertise.startAnimation(AnimationUtils.loadAnimation(getBaseContext(), R.anim.bounce));
    }//_____________________________________________________________________________________________ End SetAnimationViews


    private void SetAnimationFrame() {//___________________________________________ Start SetAnimationFrame
        FrameLayout fragment = (FrameLayout) findViewById(R.id.MainFragment);
        fragment.clearAnimation();
        Animation BottomToTop = AnimationUtils.loadAnimation(this, R.anim.bottom_to_top);
        fragment.setVisibility(View.VISIBLE);
        fragment.setAnimation(BottomToTop);
    }//_____________________________________________________________________________________________ End SetAnimationFrame


    public void attachBaseContext(Context newBase) {//______________________________________________ Start attachBaseContext
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }//_____________________________________________________________________________________________ End attachBaseContext


    private void SetAnimationYouCar() {//___________________________________________________________ Start SetAnimationYouCar
        Animation LefttoRight = AnimationUtils.loadAnimation(this, R.anim.left_to_right);
        MainYouCarImg.setAnimation(LefttoRight);
        MainYouCarText.setAnimation(LefttoRight);
    }//_____________________________________________________________________________________________ End SetAnimationYouCar



    private void ShowDialogNew() {//________________________________________________________________ Start ShowDialogNew
        dialogNewAdvertise = new DialogNewAdvertise(this);
        dialogNewAdvertise.show(getSupportFragmentManager(),NotificationCompat.CATEGORY_PROGRESS);
    }//_____________________________________________________________________________________________ End ShowDialogNew


}
