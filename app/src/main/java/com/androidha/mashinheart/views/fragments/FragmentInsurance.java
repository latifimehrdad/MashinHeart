package com.androidha.mashinheart.views.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidha.mashinheart.R;
import com.androidha.mashinheart.databases.DataBaseInsurance;
import com.androidha.mashinheart.databinding.FragmentInsuranceBinding;
import com.androidha.mashinheart.viewmodels.FragmentInsuranceViewModel;
import com.androidha.mashinheart.views.activitys.MainActivity;
import com.androidha.mashinheart.views.adabters.AdabterInsurance;
import com.androidha.mashinheart.views.application.MachinHeartApplication;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.realm.RealmResults;
import ir.hamsaa.persiandatepicker.Listener;
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;
import ir.hamsaa.persiandatepicker.util.PersianCalendar;
import net.cachapa.expandablelayout.ExpandableLayout;

public class FragmentInsurance extends Fragment {


    private RealmResults<DataBaseInsurance> CarInsurance;
    private Integer InsuranceDate;
    private int InsuranceType;
    private AdabterInsurance adabterInsurance;
    public Context context;
    private Integer editable;
    private FragmentInsuranceViewModel fragmentInsuranceViewModel;
    private FragmentCarEvent fragmentCarEvent;
    private View view;


    @BindView(R.id.FragmentInsuranceAddClick)
    LinearLayout FragmentInsuranceAddClick;
    @BindView(R.id.FragmentInsuranceBrand)
    EditText FragmentInsuranceBrand;
    @BindView(R.id.FragmentInsuranceDate)
    Button FragmentInsuranceDate;
    @BindView(R.id.FragmentInsuranceExpandable)
    ExpandableLayout FragmentInsuranceExpandable;
    @BindView(R.id.FragmentInsuranceIgnor)
    Button FragmentInsuranceIgnor;
    @BindView(R.id.FragmentInsuranceLayout)
    LinearLayout FragmentInsuranceLayout;
    @BindView(R.id.FragmentInsuranceMoney)
    EditText FragmentInsuranceMoney;
    @BindView(R.id.FragmentInsuranceSave)
    Button FragmentInsuranceSave;
    @BindView(R.id.FragmentInsuranceType)
    Spinner FragmentInsuranceType;
    @BindView(R.id.FragmentInsurances)
    RecyclerView FragmentInsurances;


    public FragmentInsurance(Context context, FragmentCarEvent fragmentCarEvent) {//__________________________________________________ Star FragmentInsurance
        this.context = context;
        this.fragmentCarEvent = fragmentCarEvent;
    }//_____________________________________________________________________________________________ End FragmentInsurance




    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentInsuranceBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_insurance, container, false);
        fragmentInsuranceViewModel = new FragmentInsuranceViewModel(context);
        binding.setInsurance(fragmentInsuranceViewModel);
        view = binding.getRoot();
        ButterKnife.bind( this, view);
        return view;
    }//_____________________________________________________________________________________________ End onCreateView



    public void onStart() {//________________________________________________________________________ Star onStart
        super.onStart();
        SetClick();
        BackClick();
        SetTextChange();
        FragmentInsuranceExpandable.collapse();
        SetCurrentDate();
        MessageControler();
        GetCarInsuranceFromDB();
    }//_____________________________________________________________________________________________ End onStart




    private void BackClick() {//____________________________________________________________________ Start BackClick
        view.setFocusableInTouchMode(true);
        view.requestFocus();

        view.setOnKeyListener(SetKey(view));
        FragmentInsuranceSave.setOnKeyListener(SetKey(FragmentInsuranceSave));
        FragmentInsuranceAddClick.setOnKeyListener(SetKey(FragmentInsuranceAddClick));
        FragmentInsuranceType.setOnKeyListener(SetKey(FragmentInsuranceType));
        FragmentInsuranceBrand.setOnKeyListener(SetKey(FragmentInsuranceBrand));
        FragmentInsuranceMoney.setOnKeyListener(SetKey(FragmentInsuranceMoney));
        FragmentInsuranceDate.setOnKeyListener(SetKey(FragmentInsuranceDate));
        FragmentInsuranceIgnor.setOnKeyListener(SetKey(FragmentInsuranceIgnor));
    }//_____________________________________________________________________________________________ End BackClick




    private View.OnKeyListener SetKey(View view){//_________________________________________________ Start SetKey
        return new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode != 4) {
                    return true;
                }
                keyCode = 0;
                event = null;
                fragmentCarEvent.MessageType.onNext("close");
                return true;
            }
        };
    }//_____________________________________________________________________________________________ End SetKey



    private void SetCurrentDate() {//_______________________________________________________________ Star SetCurrentDate
        editable = Integer.valueOf(-1);
        FragmentInsuranceDate.setText("");
    }//_____________________________________________________________________________________________ End SetCurrentDate



    private void GetCarInsuranceFromDB() {//________________________________________________________ Star GetCarInsuranceFromDB
        CarInsurance = fragmentInsuranceViewModel.GetCarInsurance(MainActivity.CarId);
        if (CarInsurance.size() > 0) {
            SetAdabter();
        } else {
            FragmentInsurances.setAdapter(null);
        }
    }//_____________________________________________________________________________________________ End GetCarInsuranceFromDB



    private void SetAdabter() {//___________________________________________________________________ Star SetAdabter
        adabterInsurance = new AdabterInsurance(this, CarInsurance);
        FragmentInsurances.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
        FragmentInsurances.setAdapter(adabterInsurance);
    }//_____________________________________________________________________________________________ End SetAdabter



    private void SetTextChange() {//________________________________________________________________ Star SetTextChange
        FragmentInsuranceMoney
                .addTextChangedListener(MachinHeartApplication
                        .getMachinHeartApplication(context)
                        .getApplicationUtilityComponent()
                        .getApplicationUtility()
                        .SetTextWatcherSplitting(FragmentInsuranceMoney));
    }//_____________________________________________________________________________________________ End SetTextChange



    private void SetClick() {//_____________________________________________________________________ Star SetClick

        FragmentInsuranceLayout.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
            }
        });

        FragmentInsuranceAddClick.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                FragmentInsuranceBrand.setText("");
                FragmentInsuranceMoney.setText("");
                FragmentInsuranceType.setSelection(0);
                if (FragmentInsuranceExpandable.isExpanded()) {
                    FragmentInsuranceExpandable.collapse();
                } else {
                    FragmentInsuranceExpandable.expand();
                }
            }
        });


        FragmentInsuranceIgnor.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                FragmentInsuranceExpandable.collapse();
                editable = -1;
            }
        });

        FragmentInsuranceSave.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (!CheckEmptyItems()) {
                    return;
                }
                if (editable == -1) {
                    SaveToDataBase();
                } else {
                    EditDataBase();
                }
            }
        });


        FragmentInsuranceType.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                InsuranceType = i;
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        FragmentInsuranceDate.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                PersianDatePickerDialog persianCalendar = MachinHeartApplication
                        .getMachinHeartApplication(context)
                        .getPersianPickerComponent()
                        .getPersianDatePickerDialog();

                persianCalendar.setListener(new Listener() {
                    public void onDateSelected(PersianCalendar persianCalendar) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(persianCalendar.getPersianYear());
                        sb.append(String.format("%02d",persianCalendar.getPersianMonth()));
                        sb.append(String.format("%02d",persianCalendar.getPersianDay()));
                        InsuranceDate = Integer.valueOf(sb.toString());
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(persianCalendar.getPersianYear());
                        sb2.append("/");
                        sb2.append(String.format("%02d",persianCalendar.getPersianMonth()));
                        sb2.append("/");
                        sb2.append(String.format("%02d",persianCalendar.getPersianDay()));
                        FragmentInsuranceDate.setText(sb2.toString());
                    }

                    public void onDismissed() {
                    }
                });
                persianCalendar.show();
            }
        });
    }//_____________________________________________________________________________________________ End SetClick


    private Boolean CheckEmptyItems() {//___________________________________________________________ Star CheckEmptyItems
        if (InsuranceType == 0) {
            ShowToast(this.context.getString(R.string.EmptyInsuranceType));
            return false;
        } else if (FragmentInsuranceBrand.getText().toString().trim().length() == 0) {
            ShowToast(this.context.getString(R.string.EmptyInsuranceBrand));
            FragmentInsuranceBrand.requestFocus();
            return false;
        } else if (FragmentInsuranceMoney.getText().toString().trim().length() == 0) {
            ShowToast(this.context.getString(R.string.EmptyChangeMoney));
            FragmentInsuranceMoney.requestFocus();
            return false;
        } else if (FragmentInsuranceDate.getText().toString().trim().length() != 0) {
            return true;
        } else {
            ShowToast(this.context.getString(R.string.EmptyChangeDate));
            return false;
        }
    }//_____________________________________________________________________________________________ End CheckEmptyItems



    public void ShowToast(String Message) {//_______________________________________________________ Star ShowToast
        MachinHeartApplication
                .getMachinHeartApplication(context)
                .getApplicationUtilityComponent()
                .getApplicationUtility()
                .CustomToastShow(context, Message);
    }//_____________________________________________________________________________________________ End ShowToast



    public void SaveToDataBase() {//________________________________________________________________ Star SaveToDataBase
        fragmentInsuranceViewModel.setTitle(FragmentInsuranceBrand.getText().toString());
        fragmentInsuranceViewModel.setCarId(MainActivity.CarId);
        fragmentInsuranceViewModel.setInsuranceDate(InsuranceDate);
        fragmentInsuranceViewModel.setMoney(Integer.valueOf(FragmentInsuranceMoney.getText().toString().replaceAll(",", "").replaceAll("٬","")));
        fragmentInsuranceViewModel.setInsuranceType(InsuranceType);
        fragmentInsuranceViewModel.SaveToDataBase();
    }//_____________________________________________________________________________________________ End SaveToDataBase



    public void EditDataBase() {//__________________________________________________________________ Star EditDataBase
        fragmentInsuranceViewModel.setTitle(FragmentInsuranceBrand.getText().toString());
        fragmentInsuranceViewModel.setCarId(MainActivity.CarId);
        fragmentInsuranceViewModel.setInsuranceDate(InsuranceDate);
        fragmentInsuranceViewModel.setMoney(Integer.valueOf(FragmentInsuranceMoney.getText().toString().replaceAll(",", "").replaceAll("٬","")));
        fragmentInsuranceViewModel.setInsuranceType(InsuranceType);
        fragmentInsuranceViewModel.EditDataBase(CarInsurance.get(editable));
    }//_____________________________________________________________________________________________ End EditDataBase



    private void MessageControler() {//_____________________________________________________________ Star MessageControler
        fragmentInsuranceViewModel
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
                                        ShowToast(s);
                                        SetCurrentDate();
                                        FragmentInsuranceExpandable.collapse();
                                        GetCarInsuranceFromDB();
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



    public void ItemClick(int position) {
    }


    public void ItemClickEdit(int position) {//_____________________________________________________ Star ItemClickEdit

        FragmentInsuranceBrand.setText(CarInsurance.get(position).getTitle());
        FragmentInsuranceMoney.setText(String.valueOf(CarInsurance.get(position).getMoney()));
        String temp = String.valueOf(CarInsurance.get(position).getInsuranceDate());
        StringBuilder sb = new StringBuilder();
        sb.append(temp.substring(0, 4));
        sb.append("/");
        sb.append(temp.substring(4, 6));
        sb.append("/");
        sb.append(temp.substring(6, 8));
        FragmentInsuranceDate.setText(sb.toString());
        editable = Integer.valueOf(position);
        InsuranceDate = CarInsurance.get(position).getInsuranceDate();
        FragmentInsuranceType.setSelection(CarInsurance.get(position).getInsuranceType());
        FragmentInsuranceExpandable.expand();
    }//_____________________________________________________________________________________________ End MessageControler



    public void ItemClickDelete(final int position) {//_____________________________________________ Star ItemClickDelete

        new Builder(context)
                .setMessage(this.context.getResources().getString(R.string.AreYouSure))
                .setPositiveButton( this.context.getResources().getString(R.string.Yes), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (editable.intValue() == -1) {
                    fragmentInsuranceViewModel.DeleteDataBase(CarInsurance.get(position));
                    return;
                }
                ShowToast(context.getResources().getString(R.string.ErrorDeleteWhenEdit));
            }
        }).setNegativeButton(this.context.getResources().getString(R.string.No), null).show();

    }//_____________________________________________________________________________________________ End ItemClickDelete


}
