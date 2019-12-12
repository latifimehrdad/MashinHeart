package ir.clinicemashin.clinicemashin.views.fragments;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import ir.clinicemashin.clinicemashin.R;
import ir.clinicemashin.clinicemashin.databinding.FragmentAboutBinding;
import ir.clinicemashin.clinicemashin.viewmodels.FragmentAboutViewModel;
import ir.clinicemashin.clinicemashin.views.application.MachinHeartApplication;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentAbout extends Fragment {

    private Context context;
    private FragmentAboutViewModel fragmentAboutViewModel;

    @BindView(R.id.FragmentAboutVersion)
    TextView FragmentAboutVersion;

    @BindView(R.id.FragmentAboutTelegram)
    LinearLayout FragmentAboutTelegram;

    @BindView(R.id.FragmentAboutInstagram)
    LinearLayout FragmentAboutInstagram;

    @BindView(R.id.FragmentAboutWhatsApp)
    LinearLayout FragmentAboutWhatsApp;

    @BindView(R.id.FragmentAboutWeb)
    TextView FragmentAboutWeb;

    public FragmentAbout(Context context) {//_______________________________________________________ Start FragmentAbout
        this.context = context;
    }//_____________________________________________________________________________________________ End FragmentAbout


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentAboutBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_about,container,false
        );
        fragmentAboutViewModel = new FragmentAboutViewModel(context);
        binding.setAbout(fragmentAboutViewModel);
        View view = binding.getRoot();
        ButterKnife.bind(this, view);
        return view;
    }//_____________________________________________________________________________________________ End onCreateView


    @Override
    public void onStart() {//_______________________________________________________________________ Start onStart
        super.onStart();
        SetClick();
        PackageInfo pInfo = null;
        try {
            pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            FragmentAboutVersion.setText(
                    context.getResources().getString(R.string.Version) + " : " +
                            pInfo.versionName
            );
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();FragmentAboutVersion.setText(
                    context.getResources().getString(R.string.Version)
            );
        }

    }//_____________________________________________________________________________________________ End onStart



    private void SetClick() {//_____________________________________________________________________ Start SetClick


        FragmentAboutTelegram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenTelegram();
            }
        });

        FragmentAboutInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenInstagram();
            }
        });

        FragmentAboutWhatsApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenWhatsApp();
            }
        });

        FragmentAboutWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenWeb();
            }
        });



    }//_____________________________________________________________________________________________ End SetClick




    private void OpenTelegram() {//__________________________________________________________________ Start OpenTelegram
        final String appName = "org.telegram.messenger";
        if (isAppAvailable(getContext(), appName)) {
            Intent telegram = new Intent(Intent.ACTION_VIEW);
            telegram.setData(Uri.parse(context.getResources().getString(R.string.TelegramLink)));
            telegram.setPackage("org.telegram.messenger");
            startActivity(Intent.createChooser(telegram, ""));
        } else {
            MachinHeartApplication
                    .getMachinHeartApplication(getContext())
                    .getApplicationUtilityComponent()
                    .getApplicationUtility()
                    .CustomToastShow(getContext(), getResources().getString(R.string.NotInstallTelegram));
        }
    }//_____________________________________________________________________________________________ End OpenTelegram


    private void OpenInstagram() {//_________________________________________________________________ Start OpenInstagram
        Uri uri = Uri.parse("http://instagram.com/_u/"+context.getResources().getString(R.string.InstagramLink));
        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
        likeIng.setPackage("com.instagram.android");
        try {
            startActivity(likeIng);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://instagram.com/"+context.getResources().getString(R.string.InstagramLink))));

        }
    }//_____________________________________________________________________________________________ End OpenInstagram


    private void OpenWhatsApp() {//__________________________________________________________________ Start OpenWhatsApp
        String url = "https://api.whatsapp.com/send?phone="+"+989039124662";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }//_____________________________________________________________________________________________ End OpenWhatsApp



    private void OpenWeb() {//______________________________________________________________________ Start OpenWeb
        startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse(context.getResources().getString(R.string.WebSiteLink))));
    }//_____________________________________________________________________________________________ End OpenWeb



    public static boolean isAppAvailable(Context context, String appName) {//_______________________ Start isAppAvailable
        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo(appName, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }//_____________________________________________________________________________________________ End isAppAvailable





}
