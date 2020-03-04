package ir.clinicemashin.clinicemashin.views.activitys;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import ir.clinicemashin.clinicemashin.R;
import ir.clinicemashin.clinicemashin.dagger.persianpicker.PersianPickerModul;
import ir.clinicemashin.clinicemashin.databinding.ActivityMainBinding;
import ir.clinicemashin.clinicemashin.jobservice.LunchAlarmReceiver;
import ir.clinicemashin.clinicemashin.viewmodels.ActivityMainViewModel;
import ir.clinicemashin.clinicemashin.views.application.MachinHeartApplication;
import ir.clinicemashin.clinicemashin.views.dialogs.DialogNewAdvertise;


import de.hdodenhof.circleimageview.CircleImageView;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;


public class MainActivity extends AppCompatActivity {

    public static Integer CarId = null;
    public static PublishSubject<Integer> ChooseCar = null;
    public static PublishSubject<String> FragmentMessage = null;
    public static final String workTag = "notificationWork";
    private DialogNewAdvertise dialogNewAdvertise;
    private NavController navController;

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
    @BindView(R.id.MainAbout)
    LinearLayout MainAbout;
    @BindView(R.id.MainAboutLeft)
    LinearLayout MainAboutLeft;
    @BindView(R.id.MainAboutUpShadow)
    LinearLayout MainAboutUpShadow;
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
    @BindView(R.id.MainPositionCar)
    LinearLayout MainPositionCar;
    @BindView(R.id.MainPositionCarDownShadow)
    LinearLayout MainPositionCarDownShadow;
    @BindView(R.id.MainPositionCarLeft)
    LinearLayout MainPositionCarLeft;
    @BindView(R.id.MainPositionCarLine)
    LinearLayout MainPositionCarLine;
    @BindView(R.id.MainPositionCarUpShadow)
    LinearLayout MainPositionCarUpShadow;
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
    @BindView(R.id.MainDialogProfileName)
    EditText MainDialogProfileName;
    @BindView(R.id.MainDialogProfileTel)
    EditText MainDialogProfileTel;
    @BindView(R.id.MainDialogProfileIgnor)
    Button MainDialogProfileIgnor;
    @BindView(R.id.MainDialogProfileSave)
    Button MainDialogProfileSave;
    @BindView(R.id.MainDialogProfile)
    LinearLayout MainDialogProfile;
    @BindView(R.id.MainProfileEdit)
    LinearLayout MainProfileEdit;

    ActivityMainViewModel viewModel;



    public void onCreate(Bundle savedInstanceState) {//_____________________________________________ Start onCreate
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        this.viewModel = new ActivityMainViewModel(this);
        activityMainBinding.setMain(this.viewModel);
        PersianPickerModul.context = this;
        ButterKnife.bind(this);
        MainDialogProfile.setVisibility(View.GONE);
        FragmentMessage = PublishSubject.create();
        ChooseCar = PublishSubject.create();
        navController = Navigation.findNavController(this, R.id.MainFragment);
        ResetRightMenu();
        ClickItems();
        SetAnimationViews();
        MainYouCar.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.MainFragment));
        MainYouCarLeft.setVisibility(View.GONE);
        MainYouCarUpShadow.setVisibility(View.VISIBLE);
        MainYouCarDownShadow.setVisibility(View.VISIBLE);
        MainYouCarLine.setVisibility(View.GONE);
        FragmentObserver();
        CheckPermissions();
        SetProfile();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    sendBroadcast(new Intent(MainActivity.this, LunchAlarmReceiver.class).setAction("ir.MachinHeart.Lunch"));
                } else {
                    Intent i = new Intent("ir.MachinHeart.Lunch");
                    sendBroadcast(i);
                }


            }
        }, 1000);

    }//_____________________________________________________________________________________________ End onCreate




    private void CheckPermissions() {//_____________________________________________________________ Start CheckPermissions

        int permissionLocation = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        List<String> listPermissionsNeeded = new ArrayList<>();

        if (permissionLocation != PackageManager.PERMISSION_GRANTED)
            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);

        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this,
                    listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),
                    0);
        }

    }//_____________________________________________________________________________________________ End CheckPermissions




    private void SetProfile() {//____________________________________________________________________ Start SetProfile
        SharedPreferences prefs = getSharedPreferences("mehrdad", 0);
        if (prefs != null) {
            String name = prefs.getString("profname", this.getResources().getString(R.string.ProfileName));
            String Tel = prefs.getString("proftel", this.getResources().getString(R.string.ProfilePhone));
            if ((name == null) || (name.length() == 0))
                MainProfileName.setText(this.getResources().getString(R.string.ProfileName));
            else
                MainProfileName.setText(prefs.getString("profname", this.getResources().getString(R.string.ProfileName)));

            if ((Tel == null) || (Tel.length() == 0))
                MainProfileTel.setText(this.getResources().getString(R.string.ProfilePhone));
            else
                MainProfileTel.setText(prefs.getString("proftel", this.getResources().getString(R.string.ProfilePhone)));
        }
    }//_____________________________________________________________________________________________ End SetProfile


    private void ClickItems() {//_______________________________________________________________ Start ClickItems


        MainProfileEdit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainDialogProfile.getVisibility() == View.GONE)
                    MainDialogProfile.setVisibility(View.VISIBLE);
                else
                    MainDialogProfile.setVisibility(View.GONE);
            }
        });

        MainDialogProfileIgnor.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                MainDialogProfile.setVisibility(View.GONE);
            }
        });
        MainDialogProfile.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                MainDialogProfile.setVisibility(View.GONE);
            }
        });

        MainDialogProfileSave.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainDialogProfileName.getText().toString().trim().length() == 0) {
                    MachinHeartApplication
                            .getMachinHeartApplication(MainActivity.this)
                            .getApplicationUtilityComponent()
                            .getApplicationUtility()
                            .CustomToastShow(MainActivity.this, getResources().getString(R.string.EmptyProfileName));
                    MainDialogProfileName.requestFocus();
                    return;
                }

                SharedPreferences.Editor editor = MainActivity.this
                        .getApplicationContext()
                        .getSharedPreferences("mehrdad", 0)
                        .edit();
                editor.putString("profname", MainDialogProfileName.getText().toString());
                editor.putString("proftel", MainDialogProfileTel.getText().toString());
                editor.apply();
                SetProfile();
                MainDialogProfile.setVisibility(View.GONE);

            }
        });


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
                NavDestination navDestination = navController.getCurrentDestination();
                String fragment = navDestination.getLabel().toString();
                if (fragment.equalsIgnoreCase("fragment_add_car")) {
                    return;
                } else {
                    navController.navigate(R.id.fragmentAddCar);
                }
            }
        });


        MainPositionCar.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ResetRightMenu();
                MainPositionCar.setBackgroundColor(MainActivity.this.getResources().getColor(R.color.MainFragment));
                MainPositionCarLeft.setVisibility(View.GONE);
                MainPositionCarUpShadow.setVisibility(View.VISIBLE);
                MainPositionCarDownShadow.setVisibility(View.VISIBLE);
                MainPositionCarLine.setVisibility(View.GONE);
                MainAddCarLine.setVisibility(View.GONE);
                NavDestination navDestination = navController.getCurrentDestination();
                String fragment = navDestination.getLabel().toString();
                if (fragment.equalsIgnoreCase("fragment_position") ||
                        fragment.equalsIgnoreCase("fragment_map") ||
                        fragment.equalsIgnoreCase("fragment_find_location")) {
                    return;
                } else {
                    navController.navigate(R.id.fragmentPosition);
                }
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
                MainPositionCarLine.setVisibility(View.GONE);
                NavDestination navDestination = navController.getCurrentDestination();
                String fragment = navDestination.getLabel().toString();
                if (fragment.equalsIgnoreCase("fragment_police_fine")) {
                    return;
                } else {
                    navController.navigate(R.id.fragmentPoliceFine);
                }
//                fm = null;
//                ft = null;
//                fm = getSupportFragmentManager();
//                ft = fm.beginTransaction();
//                ft.replace(R.id.MainFragment, new FragmentPoliceFine(MainActivity.this));
//                ft.commit();
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
                NavDestination navDestination = navController.getCurrentDestination();
                String fragment = navDestination.getLabel().toString();
                if (fragment.equalsIgnoreCase("fragment_negative_grade")) {
                    return;
                } else {
                    navController.navigate(R.id.fragmentNegativeGrade);
                }
//                fm = null;
//                ft = null;
//                fm = getSupportFragmentManager();
//                ft = fm.beginTransaction();
//                ft.replace(R.id.MainFragment, new FragmentNegativeGrade(MainActivity.this));
//                ft.commit();
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
                NavDestination navDestination = navController.getCurrentDestination();
                String fragment = navDestination.getLabel().toString();
                if (fragment.equalsIgnoreCase("fragment_advertise")) {
                    return;
                } else {
                    navController.navigate(R.id.fragmentAdvertise);
                }
//                fm = null;
//                ft = null;
//                fm = getSupportFragmentManager();
//                ft = fm.beginTransaction();
//                ft.replace(R.id.MainFragment, new FragmentAdvertise(MainActivity.this));
//                ft.commit();

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
                MainPositionCarLine.setVisibility(View.GONE);
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

        MainAbout.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ResetRightMenu();
                MainAbout.setBackgroundColor(MainActivity.this.getResources().getColor(R.color.MainFragment));
                MainAboutLeft.setVisibility(View.GONE);
                MainAboutUpShadow.setVisibility(View.VISIBLE);
                MainAdvertiseLine.setVisibility(View.GONE);
                NavDestination navDestination = navController.getCurrentDestination();
                String fragment = navDestination.getLabel().toString();
                if (fragment.equalsIgnoreCase("fragment_about")) {
                    return;
                } else {
                    navController.navigate(R.id.fragmentAbout);
                }
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
        MainPositionCar.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.MainRight));
        MainPositionCarLeft.setVisibility(View.VISIBLE);
        MainPositionCarUpShadow.setVisibility(View.GONE);
        MainPositionCarDownShadow.setVisibility(View.GONE);
        MainPositionCarLine.setVisibility(View.VISIBLE);
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
        MainAbout.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.MainRight));
        MainAboutLeft.setVisibility(View.VISIBLE);
        MainAboutUpShadow.setVisibility(View.GONE);
    }//_____________________________________________________________________________________________ End ClickRightMenu


    private void ShowFragmentYouCar() {//___________________________________________________________ Start ShowFragmentYouCar

        ResetRightMenu();
        MainYouCar.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.MainFragment));
        MainYouCarLeft.setVisibility(View.GONE);
        MainYouCarUpShadow.setVisibility(View.VISIBLE);
        MainYouCarDownShadow.setVisibility(View.VISIBLE);
        MainYouCarLine.setVisibility(View.GONE);
        navController.navigate(R.id.fragmentYouCar);


    }//_____________________________________________________________________________________________ End ShowFragmentYouCar


    private void ShowFragmentCarEvent(Integer Brand) {//___________________________________________________________ Start ShowFragmentCarEvent

        if(Brand > -1) {
            MainYouCarImg.setImageResource(getResources().obtainTypedArray(R.array.CarLogo).getResourceId(Brand, R.drawable.logo));
            MainYouCarText.setText(getResources().getStringArray(R.array.CarBrand)[Brand]);
        } else {
            MainYouCarImg.setImageResource(R.drawable.you_car);
            MainYouCarText.setText(getResources().getString(R.string.YouCar));
        }
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
                                    ShowFragmentYouCar();
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
                                ShowFragmentCarEvent(brand);
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
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }//_____________________________________________________________________________________________ End attachBaseContext


    private void SetAnimationYouCar() {//___________________________________________________________ Start SetAnimationYouCar
        Animation LefttoRight = AnimationUtils.loadAnimation(this, R.anim.left_to_right);
        MainYouCarImg.setAnimation(LefttoRight);
        MainYouCarText.setAnimation(LefttoRight);
    }//_____________________________________________________________________________________________ End SetAnimationYouCar


    private void ShowDialogNew() {//________________________________________________________________ Start ShowDialogNew
        dialogNewAdvertise = new DialogNewAdvertise(this);
        dialogNewAdvertise.show(getSupportFragmentManager(), NotificationCompat.CATEGORY_PROGRESS);
    }//_____________________________________________________________________________________________ End ShowDialogNew


}
