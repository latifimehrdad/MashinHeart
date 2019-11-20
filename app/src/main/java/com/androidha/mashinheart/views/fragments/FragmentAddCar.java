package com.androidha.mashinheart.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.androidha.mashinheart.R;
import com.androidha.mashinheart.databinding.FragmentAddCarBinding;
import com.androidha.mashinheart.viewmodels.FragmentAddCarViewModel;
import com.androidha.mashinheart.views.activitys.MainActivity;
import com.androidha.mashinheart.views.application.MachinHeartApplication;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import ir.hamsaa.persiandatepicker.Listener;
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;
import ir.hamsaa.persiandatepicker.util.PersianCalendar;

public class FragmentAddCar extends Fragment {


    private int BrandSelected;
    private Integer CarBodyInsurance;
    private Integer CarPersonInsurance;
    private Boolean EditCat;
    private Boolean GearBox;
    private Context context;
    private FragmentAddCarViewModel fragmentAddCarViewModel;

    @BindView(R.id.FragmentAddCarAuto)
    AppCompatCheckBox FragmentAddCarAuto;
    @BindView(R.id.FragmentAddCarAverage)
    EditText FragmentAddCarAverage;
    @BindView(R.id.FragmentAddCarBerand)
    Spinner FragmentAddCarBerand;
    @BindView(R.id.FragmentAddCarBody)
    Button FragmentAddCarBody;
    @BindView(R.id.FragmentAddCarBodyCheck)
    AppCompatCheckBox FragmentAddCarBodyCheck;
    @BindView(R.id.FragmentAddCarChassis)
    EditText FragmentAddCarChassis;
    @BindView(R.id.FragmentAddCarColor)
    EditText FragmentAddCarColor;
    @BindView(R.id.FragmentAddCarHandly)
    AppCompatCheckBox FragmentAddCarHandly;
    @BindView(R.id.FragmentAddCarKm)
    EditText FragmentAddCarKm;
    @BindView(R.id.FragmentAddCarName)
    EditText FragmentAddCarName;
    @BindView(R.id.FragmentAddCarPelakAlphabet)
    EditText FragmentAddCarPelakAlphabet;
    @BindView(R.id.FragmentAddCarPelakIran)
    EditText FragmentAddCarPelakIran;
    @BindView(R.id.FragmentAddCarPelakLeft)
    EditText FragmentAddCarPelakLeft;
    @BindView(R.id.FragmentAddCarPelakRight)
    EditText FragmentAddCarPelakRight;
    @BindView(R.id.FragmentAddCarPerson)
    Button FragmentAddCarPerson;
    @BindView(R.id.FragmentAddCarPersonCheck)
    AppCompatCheckBox FragmentAddCarPersonCheck;
    @BindView(R.id.FragmentAddCarSave)
    Button FragmentAddCarSave;
    @BindView(R.id.FragmentAddCarYear)
    EditText FragmentAddCarYear;




    public FragmentAddCar(Context context, Boolean EditCat) {//_____________________________________ Start FragmentAddCar
        this.context = context;
        this.EditCat = EditCat;
    }//_____________________________________________________________________________________________ End FragmentAddCar



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentAddCarBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_car, container, false);
        fragmentAddCarViewModel = new FragmentAddCarViewModel(context);
        binding.setAddcar(fragmentAddCarViewModel);
        View view = binding.getRoot();
        ButterKnife.bind(this, view);
        return view;
    }//_____________________________________________________________________________________________ End onCreateView



    public void onStart() {//_______________________________________________________________________ Start onStart
        super.onStart();
        BrandSelected = 0;
        ItemClick();
        MessageControler();
        SetTextChange();
    }//_____________________________________________________________________________________________ End onStart



    private void MessageControler() {//_____________________________________________________________ Start MessageControler
        fragmentAddCarViewModel
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
                                        FragmentAddCar.this.ShowToast(s);
                                        MainActivity.FragmentMessage.onNext("CommitAdd");
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



    private void ItemClick() {//____________________________________________________________________ Start ItemClick

        FragmentAddCarHandly.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                FragmentAddCarAuto.setChecked(false);
                GearBox = false;
            }
        });

        FragmentAddCarAuto.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                FragmentAddCarHandly.setChecked(false);
                GearBox = true;
            }
        });

        FragmentAddCarSave.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (CheckEmptyItems()) {
                    SaveToDataBase();
                }
            }
        });

        FragmentAddCarBerand.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                BrandSelected = i;
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        FragmentAddCarPerson.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                PersianDatePickerDialog persianCalendar = MachinHeartApplication
                        .getMachinHeartApplication(FragmentAddCar.this.context)
                        .getPersianPickerComponent()
                        .getPersianDatePickerDialog();

                persianCalendar.setListener(new Listener() {
                    public void onDateSelected(PersianCalendar persianCalendar) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(persianCalendar.getPersianYear());
                        sb.append(String.format("%02d",persianCalendar.getPersianMonth()));
                        sb.append(String.format("%02d",persianCalendar.getPersianDay()));
                        CarPersonInsurance = Integer.valueOf(sb.toString());
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(persianCalendar.getPersianYear());
                        sb2.append("/");
                        sb2.append(String.format("%02d",persianCalendar.getPersianMonth()));
                        sb2.append("/");
                        sb2.append(String.format("%02d",persianCalendar.getPersianDay()));
                        FragmentAddCarPerson.setText(sb2.toString());
                    }

                    public void onDismissed() {
                    }
                });
                persianCalendar.show();
            }
        });


        FragmentAddCarBody.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {

                PersianDatePickerDialog persianCalendar = MachinHeartApplication
                        .getMachinHeartApplication(FragmentAddCar.this.context)
                        .getPersianPickerComponent()
                        .getPersianDatePickerDialog();

                persianCalendar.setListener(new Listener() {
                    public void onDateSelected(PersianCalendar persianCalendar) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(persianCalendar.getPersianYear());
                        sb.append(String.format("%02d",persianCalendar.getPersianMonth()));
                        sb.append(String.format("%02d",persianCalendar.getPersianDay()));
                        CarBodyInsurance = Integer.valueOf(sb.toString());
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(persianCalendar.getPersianYear());
                        sb2.append("/");
                        sb2.append(String.format("%02d",persianCalendar.getPersianMonth()));
                        sb2.append("/");
                        sb2.append(String.format("%02d",persianCalendar.getPersianDay()));
                        FragmentAddCarBody.setText(sb2.toString());
                    }

                    public void onDismissed() {
                    }
                });
                persianCalendar.show();
            }
        });
    }//_____________________________________________________________________________________________ End ItemClick




    private void SaveToDataBase() {//_______________________________________________________________ Start SaveToDataBase

        fragmentAddCarViewModel.setCarBrand(BrandSelected);
        fragmentAddCarViewModel.setCarType(FragmentAddCarName.getText().toString());
        fragmentAddCarViewModel.setCarYearCreated(Integer.valueOf(FragmentAddCarYear.getText().toString()));
        fragmentAddCarViewModel.setCarColor(FragmentAddCarColor.getText().toString());
        fragmentAddCarViewModel.setCarPersonInsurance(CarPersonInsurance);
        fragmentAddCarViewModel.setCarBodyInsurance(CarBodyInsurance);
        fragmentAddCarViewModel.setGearbox(GearBox);
        fragmentAddCarViewModel.setCarChassisNumber(FragmentAddCarChassis.getText().toString());
        if (FragmentAddCarPelakLeft.getText().toString().length() > 0) {
            fragmentAddCarViewModel.setCarTagLeft(Integer.valueOf(FragmentAddCarPelakLeft.getText().toString()));
        } else {
            fragmentAddCarViewModel.setCarTagLeft(0);
        }
        if (FragmentAddCarPelakRight.getText().toString().length() > 0) {
            fragmentAddCarViewModel.setCarTagRight(Integer.valueOf(FragmentAddCarPelakRight.getText().toString()));
        } else {
            fragmentAddCarViewModel.setCarTagRight(0);
        }
        if (FragmentAddCarPelakIran.getText().toString().length() > 0) {
            fragmentAddCarViewModel.setCarTagIran(Integer.valueOf(FragmentAddCarPelakIran.getText().toString()));
        } else {
            fragmentAddCarViewModel.setCarTagIran(0);
        }
        fragmentAddCarViewModel.setCarTagLetter(FragmentAddCarPelakAlphabet.getText().toString());
        fragmentAddCarViewModel
                .setCarKm(Integer.valueOf(FragmentAddCarKm.getText().toString().replaceAll(",", "").replaceAll("٬","")));
        fragmentAddCarViewModel.setCarUseAverage(Integer.valueOf(FragmentAddCarAverage.getText().toString().replaceAll(",", "").replaceAll("٬","")));
        fragmentAddCarViewModel.SaveToDataBase();
    }//_____________________________________________________________________________________________ End SaveToDataBase



    private Boolean CheckEmptyItems() {//_______________________________________________________________ Start CheckEmptyItems

        try{
            int i = Integer.valueOf(FragmentAddCarAverage.getText().toString().replaceAll(",", "").replaceAll("٬",""));
        }
        catch (Exception ex){
            ShowToast(this.context.getString(R.string.JustNumber));
            FragmentAddCarAverage.requestFocus();
            FragmentAddCarAverage.setText("");
            return false;
        }
        if (BrandSelected == 0) {
            ShowToast(this.context.getString(R.string.EmptyCarBrand));
            return false;
        } else if (FragmentAddCarName.getText().toString().trim().length() == 0) {
            ShowToast(this.context.getString(R.string.EmptyCarName));
            FragmentAddCarName.requestFocus();
            return false;
        } else if (FragmentAddCarYear.getText().toString().trim().length() == 0) {
            ShowToast(this.context.getString(R.string.EmptyCarYear));
            FragmentAddCarYear.requestFocus();
            return false;
        } else if (FragmentAddCarKm.getText().toString().trim().length() == 0) {
            ShowToast(this.context.getString(R.string.EmptyCarKM));
            FragmentAddCarKm.requestFocus();
            return false;
        }
        else if (FragmentAddCarAverage.getText().toString().trim().length() == 0){
            ShowToast(this.context.getString(R.string.EmptyCarAverage));
            return false;
        } else if (FragmentAddCarColor.getText().toString().trim().length() == 0) {
            ShowToast(this.context.getString(R.string.EmptyCarColor));
            FragmentAddCarColor.requestFocus();
            return false;
        } else if (FragmentAddCarPersonCheck.isChecked() && FragmentAddCarPerson.getText().toString().trim().length() == 0) {
            ShowToast(this.context.getString(R.string.EmptyCarPerson));
            return false;
        } else if (FragmentAddCarBodyCheck.isChecked() && FragmentAddCarBody.getText().toString().trim().length() == 0) {
            ShowToast(this.context.getString(R.string.EmptyCarBody));
            return false;
        } else if (!FragmentAddCarHandly.isChecked() && !FragmentAddCarAuto.isChecked()) {
            ShowToast(this.context.getString(R.string.EmptyCarGearBox));
            return false;
        } else if (FragmentAddCarChassis.getText().toString().trim().length() == 0 && (FragmentAddCarPelakLeft.getText().toString().trim().length() > 0 || FragmentAddCarPelakAlphabet.getText().toString().trim().length() > 0 || FragmentAddCarPelakRight.getText().toString().trim().length() > 0 || FragmentAddCarPelakIran.getText().toString().trim().length() > 0)) {
            ShowToast(this.context.getString(R.string.EmptyCarChassis));
            return false;
        } else if (FragmentAddCarChassis.getText().toString().trim().length() <= 0 || (FragmentAddCarPelakLeft.getText().toString().trim().length() != 0 && FragmentAddCarPelakAlphabet.getText().toString().trim().length() != 0 && FragmentAddCarPelakRight.getText().toString().trim().length() != 0 && FragmentAddCarPelakIran.getText().toString().trim().length() != 0)) {
            return true;
        } else {
            ShowToast(this.context.getString(R.string.EmptyCarPelak));
            return false;
        }
    }//_____________________________________________________________________________________________ End CheckEmptyItems



    private void ShowToast(String Message) {//______________________________________________________ Start ShowToast
        MachinHeartApplication
                .getMachinHeartApplication(context)
                .getApplicationUtilityComponent()
                .getApplicationUtility()
                .CustomToastShow(context, Message);
    }//_____________________________________________________________________________________________ End ShowToast



    private void SetTextChange() {//______________________________________________________ Start SetTextChange

        FragmentAddCarKm
                .addTextChangedListener(MachinHeartApplication
                        .getMachinHeartApplication(context)
                        .getApplicationUtilityComponent()
                        .getApplicationUtility()
                        .SetTextWatcherSplitting(FragmentAddCarKm));

    }//_____________________________________________________________________________________________ End SetTextChange

}
