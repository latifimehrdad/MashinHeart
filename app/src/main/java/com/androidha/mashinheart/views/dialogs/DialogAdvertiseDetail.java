package com.androidha.mashinheart.views.dialogs;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.androidha.mashinheart.R;
import com.androidha.mashinheart.databinding.DialogDetailAdvertiseBinding;
import com.androidha.mashinheart.models.ModelAdvertiseList;
import com.androidha.mashinheart.views.application.MachinHeartApplication;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DialogAdvertiseDetail extends DialogFragment {

    private Context context;
    private ModelAdvertiseList modelAdvertiseList;

    @BindView(R.id.DialogDetailIgnor)
    Button DialogDetailIgnor;

    @BindView(R.id.DialogDetailCall)
    Button DialogDetailCall;




    public DialogAdvertiseDetail(Context context, ModelAdvertiseList modelAdvertiseList) {//________ Start DialogAdvertiseDetail
        this.context = context;
        this.modelAdvertiseList = modelAdvertiseList;
    }//_____________________________________________________________________________________________ End DialogAdvertiseDetail




    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {//___________________________ Start onCreate
        DialogDetailAdvertiseBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(this.context), R.layout.dialog_detail_advertise
                , null, false
        );
        binding.setAdvertise(modelAdvertiseList);
        View view = binding.getRoot();
        ButterKnife.bind(this, view);
        SetClick();
        return new AlertDialog.Builder(context).setView(view).create();
    }//_____________________________________________________________________________________________ End onCreate



    private void SetClick() {//_____________________________________________________________________ Start SEtClick

        DialogDetailIgnor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogAdvertiseDetail.this.dismiss();
            }
        });


        DialogDetailCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }//_____________________________________________________________________________________________ End SEtClick



}
