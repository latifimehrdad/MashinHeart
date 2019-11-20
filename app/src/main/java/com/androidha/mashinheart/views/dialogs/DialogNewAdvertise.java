package com.androidha.mashinheart.views.dialogs;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.core.app.NotificationCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.androidha.mashinheart.R;
import com.androidha.mashinheart.databinding.DialogNewAdvertiseBinding;
import com.androidha.mashinheart.viewmodels.DialogNewAdvertiseViewModel;
import com.androidha.mashinheart.views.activitys.ImagePickerActivity;
import com.androidha.mashinheart.views.activitys.MainActivity;
import com.androidha.mashinheart.views.application.MachinHeartApplication;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DialogNewAdvertise extends DialogFragment {

    private Context context;
    private DialogNewAdvertiseViewModel dialogNewAdvertiseViewModel;
    private Boolean ChooseImage;
    private Uri uri;
    private int City;
    private double lat;
    private double lng;

    @BindView(R.id.DialogNewAdvertiseIgnor)
    Button DialogNewAdvertiseIgnor;
    @BindView(R.id.DialogNewAdvertiseSend)
    Button DialogNewAdvertiseSend;
    @BindView(R.id.DialogNewAdvertiseTitle)
    EditText DialogNewAdvertiseTitle;
    @BindView(R.id.DialogNewAdvertiseTel)
    EditText DialogNewAdvertiseTel;
    @BindView(R.id.DialogNewAdvertiseAddress)
    EditText DialogNewAdvertiseAddress;
    @BindView(R.id.DialogNewAdvertiseImage)
    ImageView DialogNewAdvertiseImage;
    @BindView(R.id.FragmentDialogCity)
    Spinner FragmentDialogCity;

    @BindView(R.id.DialogNewTelegram)
    LinearLayout DialogNewTelegram;

    @BindView(R.id.DialogNewInstagram)
    LinearLayout DialogNewInstagram;

    @BindView(R.id.DialogNewWhatsApp)
    LinearLayout DialogNewWhatsApp;


    public DialogNewAdvertise(Context context) {//__________________________________________________ Start DialogNewAdvertise
        this.context = context;
    }//_____________________________________________________________________________________________ End DialogNewAdvertise


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {//___________________________________ Start onCreate
        super.onCreate(savedInstanceState);

    }//_____________________________________________________________________________________________ End onCreate


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {//___________________________ Start onCreate

        DialogNewAdvertiseBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(this.context), R.layout.dialog_new_advertise
                , null, false
        );
        dialogNewAdvertiseViewModel = new DialogNewAdvertiseViewModel(context);
        binding.setNewAdvertise(dialogNewAdvertiseViewModel);
        View view = binding.getRoot();
        ButterKnife.bind(this, view);

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                context, R.layout.spineer_item,
                MachinHeartApplication
                        .getMachinHeartApplication(context)
                        .getApplicationUtilityComponent()
                        .getApplicationUtility()
                        .getName());
        FragmentDialogCity.setAdapter(spinnerArrayAdapter);
        ChooseImage = false;
        City = 0;
        SEtClick();

        return new Builder(context).setView(view).create();
    }//_____________________________________________________________________________________________ End onCreate


    private void SEtClick() {//_____________________________________________________________________ Start SEtClick


        DialogNewTelegram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenTelegram();
            }
        });

        DialogNewInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenInstagram();
            }
        });

        DialogNewWhatsApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenWhatsApp();
            }
        });

        FragmentDialogCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                City = i;
                double[] latlong = MachinHeartApplication
                        .getMachinHeartApplication(context)
                        .getApplicationUtilityComponent()
                        .getApplicationUtility()
                        .getLatLong(i);
                lat = latlong[0];
                lng = latlong[1];
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        DialogNewAdvertiseIgnor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogNewAdvertise.this.dismiss();
            }
        });


        DialogNewAdvertiseSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckEmpty()) {
                    DialogNewAdvertise.this.dismiss();
                }

            }
        });

        DialogNewAdvertiseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dexter.withActivity(getActivity())
                        .withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .withListener(new MultiplePermissionsListener() {
                            @Override
                            public void onPermissionsChecked(MultiplePermissionsReport report) {
                                if (report.areAllPermissionsGranted()) {
                                    showImagePickerOptions();
                                } else {
                                    // TODO - handle permission denied case
                                }
                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                                token.continuePermissionRequest();
                            }
                        }).check();
            }
        });


    }//_____________________________________________________________________________________________ End SEtClick


    private void OpenTelegram() {//__________________________________________________________________ Start OpenTelegram
        final String appName = "org.telegram.messenger";
        if (isAppAvailable(getContext(), appName)) {
            Intent telegram = new Intent(Intent.ACTION_VIEW);
            telegram.setData(Uri.parse("https://telegram.me/iranlandcruiser"));
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
        Uri uri = Uri.parse("http://instagram.com/_u/iranlandcruiser");
        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
        likeIng.setPackage("com.instagram.android");
        try {
            startActivity(likeIng);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://instagram.com/iranlandcruiser")));

        }
    }//_____________________________________________________________________________________________ End OpenInstagram


    private void OpenWhatsApp() {//__________________________________________________________________ Start OpenWhatsApp

    }//_____________________________________________________________________________________________ End OpenWhatsApp


    public static boolean isAppAvailable(Context context, String appName) {//_______________________ Start isAppAvailable
        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo(appName, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }//_____________________________________________________________________________________________ End isAppAvailable


    private void showImagePickerOptions() {//_______________________________________________________ Start showImagePickerOptions
        ImagePickerActivity.showImagePickerOptions(context, new ImagePickerActivity.PickerOptionListener() {
            @Override
            public void onTakeCameraSelected() {
                launchCameraIntent();
            }

            @Override
            public void onChooseGallerySelected() {
                launchGalleryIntent();
            }
        });
    }//_____________________________________________________________________________________________ End showImagePickerOptions


    private void launchCameraIntent() {//___________________________________________________________ Start launchCameraIntent
        Intent intent = new Intent(context, ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_IMAGE_CAPTURE);

        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);

        // setting maximum bitmap width and height
        intent.putExtra(ImagePickerActivity.INTENT_SET_BITMAP_MAX_WIDTH_HEIGHT, true);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_WIDTH, 1000);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_HEIGHT, 1000);

        startActivityForResult(intent, 7126);
    }//_____________________________________________________________________________________________ End launchCameraIntent


    private void launchGalleryIntent() {//__________________________________________________________ Start launchGalleryIntent
        Intent intent = new Intent(context, ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_GALLERY_IMAGE);

        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);
        startActivityForResult(intent, 7126);
    }//_____________________________________________________________________________________________ End launchGalleryIntent


    private void ShowToast(String Message) {//______________________________________________________ Start ShowToast
        MachinHeartApplication
                .getMachinHeartApplication(context)
                .getApplicationUtilityComponent()
                .getApplicationUtility()
                .CustomToastShow(context, Message);
    }//_____________________________________________________________________________________________ End ShowToast


    private Boolean CheckEmpty() {//____________________________________________________________________ Start CheckEmpty

        if (!ChooseImage) {
            ShowToast(context.getResources().getString(R.string.AdvertiseImageEmpty));
            return false;
        }

        if (City == 0) {
            ShowToast(context.getResources().getString(R.string.AdvertiseCityEmpty));
            return false;
        }

        if (DialogNewAdvertiseTitle.getText().toString().trim().length() == 0) {
            DialogNewAdvertiseTitle.setError(context.getResources().getString(R.string.AdvertiseTitleEmpty));
            DialogNewAdvertiseTitle.requestFocus();
            return false;
        }


        if (DialogNewAdvertiseTel.getText().toString().trim().length() == 0) {
            DialogNewAdvertiseTel.setError(context.getResources().getString(R.string.AdvertiseTelEmpty));
            DialogNewAdvertiseTel.requestFocus();
            return false;
        }


        if (DialogNewAdvertiseAddress.getText().toString().trim().length() == 0) {
            DialogNewAdvertiseAddress.setError(context.getResources().getString(R.string.AdvertiseAddressEmpty));
            DialogNewAdvertiseAddress.requestFocus();
            return false;
        }

        return true;
    }//_____________________________________________________________________________________________ End CheckEmpty


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {//__________________ Start onActivityResult
        if (requestCode == 7126) {
            if (resultCode == Activity.RESULT_OK) {
                uri = data.getParcelableExtra("path");
                try {
                    DialogNewAdvertiseImage.setImageURI(uri);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }//_____________________________________________________________________________________________ End onActivityResult


}
