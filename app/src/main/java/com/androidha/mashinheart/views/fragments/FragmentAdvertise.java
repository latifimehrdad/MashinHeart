package com.androidha.mashinheart.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidha.mashinheart.R;
import com.androidha.mashinheart.databinding.FragmentAdvertiseBinding;
import com.androidha.mashinheart.models.ModelAdvertiseList;
import com.androidha.mashinheart.viewmodels.FragmentAdvertiseViewModel;
import com.androidha.mashinheart.views.activitys.MainActivity;
import com.androidha.mashinheart.views.adabters.AdabterAdvertise;
import com.androidha.mashinheart.views.application.MachinHeartApplication;
import com.androidha.mashinheart.views.dialogs.DialogAdvertiseDetail;
import com.androidha.mashinheart.views.dialogs.DialogNewAdvertise;
import com.androidha.mashinheart.views.dialogs.DialogProgress;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class FragmentAdvertise extends Fragment {

    private Context context;
    private FragmentAdvertiseViewModel fragmentAdvertiseViewModel;
    private int CityId;
    private DialogProgress progress;
    private AdabterAdvertise adabterAdvertise;
    private ArrayList<ModelAdvertiseList> modelAdvertiseLists;

    @BindView(R.id.FragmentAdvertiseCity)
    Spinner FragmentAdvertiseCity;

//    @BindView(R.id.FragmentAdvertiseExpandable)
//    ExpandableLayout FragmentAdvertiseExpandable;

    @BindView(R.id.FragmentAdvertiseSearch)
    ImageView FragmentAdvertiseSearch;

    @BindView(R.id.FragmentAdvertiseBtn)
    Button FragmentAdvertiseBtn;

    @BindView(R.id.FragmentAdvertiseText)
    EditText FragmentAdvertiseText;

    @BindView(R.id.FragmentAdvertises)
    RecyclerView FragmentAdvertises;

    public FragmentAdvertise(Context context) {//___________________________________________________ Start FragmentAdvertise
        this.context = context;
    }//_____________________________________________________________________________________________ End FragmentAdvertise


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentAdvertiseBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_advertise, container, false
        );
        fragmentAdvertiseViewModel = new FragmentAdvertiseViewModel(context);
        binding.setAdvertise(fragmentAdvertiseViewModel);
        View view = binding.getRoot();
        ButterKnife.bind(this, view);

        return view;
    }//_____________________________________________________________________________________________ End onCreateView


    @Override
    public void onStart() {//_______________________________________________________________________ Start onStart
        super.onStart();
        SpinnerAdabter();
        SetClick();
        MessageControler();
        //FragmentAdvertiseExpandable.collapse();
    }//_____________________________________________________________________________________________ End onStart


    private void SpinnerAdabter() {//_______________________________________________________________ Start SpinnerAdabter
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                context, R.layout.spineer_item,
                MachinHeartApplication
                        .getMachinHeartApplication(context)
                        .getApplicationUtilityComponent()
                        .getApplicationUtility()
                        .getName());
        FragmentAdvertiseCity.setAdapter(spinnerArrayAdapter);
    }//_____________________________________________________________________________________________ End SpinnerAdabter


    private void SetClick() {//_____________________________________________________________________ Start SetClick


        FragmentAdvertiseSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CityId == 0) {
                    ShowToast(context.getResources().getString(R.string.AdvertiseCityEmpty));
                    return;
                } else {
                    //FragmentAdvertiseExpandable.expand();
                }
            }
        });

        FragmentAdvertiseCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                CityId = i;
                if (CityId == 0) {
//                    FragmentAdvertiseExpandable.collapse();
                    return;
                }
//                FragmentAdvertiseExpandable.expand();
                double[] latlong = MachinHeartApplication
                        .getMachinHeartApplication(context)
                        .getApplicationUtilityComponent()
                        .getApplicationUtility()
                        .getLatLong(CityId);
                ShowProgressDialog();
                fragmentAdvertiseViewModel.setCancel(false);
                fragmentAdvertiseViewModel
                        .GetAdvertise(
                                latlong[0],
                                latlong[1],
                                FragmentAdvertiseText.getText().toString());
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        FragmentAdvertiseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if(FragmentAdvertiseText.getText().length() < 3){
//                    ShowToast(context.getResources().getString(R.string.SearchEmpty));
//                    return;
//                }
                double[] latlong = MachinHeartApplication
                        .getMachinHeartApplication(context)
                        .getApplicationUtilityComponent()
                        .getApplicationUtility()
                        .getLatLong(CityId);
                ShowProgressDialog();
                fragmentAdvertiseViewModel.setCancel(false);
                fragmentAdvertiseViewModel
                        .GetAdvertise(
                                latlong[0],
                                latlong[1],
                                FragmentAdvertiseText.getText().toString());

            }
        });

    }//_____________________________________________________________________________________________ End SetClick


    public void ShowToast(String Message) {//_______________________________________________________ Start ShowToast

        MachinHeartApplication
                .getMachinHeartApplication(context)
                .getApplicationUtilityComponent()
                .getApplicationUtility()
                .CustomToastShow(context, Message);

    }//_____________________________________________________________________________________________ End ShowToast


    private void ShowProgressDialog() {//___________________________________________________________ Start ShowProgressDialog
        progress = new DialogProgress(context,
                null,
                null,
                fragmentAdvertiseViewModel,
                context.getResources().getString(R.string.GetDataProgress));

        progress.setCancelable(false);
        progress.show(getFragmentManager(), NotificationCompat.CATEGORY_PROGRESS);
    }//_____________________________________________________________________________________________ End ShowProgressDialog


    private void MessageControler() {//_____________________________________________________________ Start MessageControler

        fragmentAdvertiseViewModel
                .MessageType
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        getActivity()
                                .runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        switch (s) {
                                            case "GetOk":
                                                if (progress != null) {
                                                    progress.dismiss();
                                                }
                                                //FragmentAdvertiseExpandable.collapse();
                                                FragmentAdvertiseCity.setSelection(0);
                                                SetAdabter();
                                                break;
                                            case "CancelByUser":
                                                if (progress != null) {
                                                    progress.dismiss();
                                                }
                                                fragmentAdvertiseViewModel.setCancel(true);
                                                break;
                                            default:
                                                if (progress != null) {
                                                    progress.dismiss();
                                                }
                                                ShowToast(s);
                                                break;
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
    }//_____________________________________________________________________________________________ End MessageControler


    private void SetAdabter() {//____________________________________________________________________ Start SetAdabter
        modelAdvertiseLists = fragmentAdvertiseViewModel.getModelAdvertiseLists();
        adabterAdvertise = new AdabterAdvertise(context, modelAdvertiseLists, FragmentAdvertise.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        FragmentAdvertises.setLayoutManager(layoutManager);
        FragmentAdvertises.setAdapter(adabterAdvertise);
    }//_____________________________________________________________________________________________ End SetAdabter


    @Override
    public void onDestroy() {//_____________________________________________________________________ Start onDestroy

        super.onDestroy();
        fragmentAdvertiseViewModel.setCancel(true);

    }//_____________________________________________________________________________________________ End onDestroy


    public void ItemClick(int position) {//_________________________________________________________ Start ItemClick
        DialogAdvertiseDetail dialogAdvertiseDetail = new DialogAdvertiseDetail(
                context,
                fragmentAdvertiseViewModel.getModelAdvertiseLists().get(position)
        );
        dialogAdvertiseDetail.show(getFragmentManager(), "DialogAdvertiseDetail");

    }//_____________________________________________________________________________________________ End ItemClick


}
