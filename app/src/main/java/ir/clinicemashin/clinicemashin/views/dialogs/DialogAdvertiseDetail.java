package ir.clinicemashin.clinicemashin.views.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import ir.clinicemashin.clinicemashin.R;
import ir.clinicemashin.clinicemashin.databinding.DialogDetailAdvertiseBinding;
import ir.clinicemashin.clinicemashin.models.ModelAdvertiseList;

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
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                if(modelAdvertiseList.getAppStore() == null)
                    callIntent.setData(Uri.parse("tel:" + modelAdvertiseList.getMobileNumber()));
                else
                    callIntent.setData(Uri.parse("tel:" + modelAdvertiseList.getAppStore().getMobileNumber()));
                context.startActivity(callIntent);
            }
        });

    }//_____________________________________________________________________________________________ End SEtClick



}
