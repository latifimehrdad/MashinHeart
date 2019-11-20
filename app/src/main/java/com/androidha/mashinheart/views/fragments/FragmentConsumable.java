package com.androidha.mashinheart.views.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidha.mashinheart.R;
import com.androidha.mashinheart.databases.DataBaseConsumable;
import com.androidha.mashinheart.databinding.FragmentConsumablesBinding;
import com.androidha.mashinheart.viewmodels.FragmentConsumablesViewModel;
import com.androidha.mashinheart.views.activitys.MainActivity;
import com.androidha.mashinheart.views.adabters.AdabterConsumable;
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
import java.util.ArrayList;
import net.cachapa.expandablelayout.ExpandableLayout;

public class FragmentConsumable extends Fragment {

    private RealmResults<DataBaseConsumable> CarConsumable;
    private Integer ChangeDate;
    private AdabterConsumable adabterConsumable;
    public Context context;
    private Integer editable;
    private FragmentConsumablesViewModel fragmentConsumablesViewModel;
    private FragmentCarEvent fragmentCarEvent;

    @BindView(R.id.FragmentConsumableAddClick)
    LinearLayout FragmentConsumableAddClick;
    @BindView(R.id.FragmentConsumableBrand)
    EditText FragmentConsumableBrand;
    @BindView(R.id.FragmentConsumableDate)
    Button FragmentConsumableDate;
    @BindView(R.id.FragmentConsumableIgnor)
    Button FragmentConsumableIgnor;
    @BindView(R.id.FragmentConsumableKm)
    EditText FragmentConsumableKm;
    @BindView(R.id.FragmentConsumableLayout)
    LinearLayout FragmentConsumableLayout;
    @BindView(R.id.FragmentConsumableMoney)
    EditText FragmentConsumableMoney;
    @BindView(R.id.FragmentConsumableNextKm)
    EditText FragmentConsumableNextKm;
    @BindView(R.id.FragmentConsumableSave)
    Button FragmentConsumableSave;
    @BindView(R.id.FragmentConsumableSuggestion)
    ListView FragmentConsumableSuggestion;
    @BindView(R.id.FragmentConsumableTitle)
    EditText FragmentConsumableTitle;
    @BindView(R.id.FragmentConsumableexpandable)
    ExpandableLayout FragmentConsumableexpandable;
    @BindView(R.id.FragmentConsumables)
    RecyclerView FragmentConsumables;


    public FragmentConsumable(Context context, FragmentCarEvent fragmentCarEvent) {//_________________________________________________ Start FragmentConsumable
        this.context = context;
        this.fragmentCarEvent = fragmentCarEvent;
    }//_____________________________________________________________________________________________ End FragmentConsumable



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentConsumablesBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_consumables, container, false);
        fragmentConsumablesViewModel = new FragmentConsumablesViewModel(context);
        binding.setConsumables(fragmentConsumablesViewModel);
        View view = binding.getRoot();
        ButterKnife.bind(this, view);

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyCode != 4) {
                    return true;
                }
                keyCode = 0;
                keyEvent = null;
                fragmentCarEvent.MessageType.onNext("close");
                return true;
            }
        });

        return view;
    }//_____________________________________________________________________________________________ End onCreateView



    public void onStart() {//_______________________________________________________________________ Start onStart
        super.onStart();
        SetClick();
        SetTextChange();
        FragmentConsumableexpandable.collapse();
        FragmentConsumableSuggestion.setVisibility(View.GONE);
        SetCurrentDate();
        MessageControler();
        GetCarConsumableFromDB();
    }//_____________________________________________________________________________________________ End onStart




    private void SetCurrentDate() {//_______________________________________________________________ Start SetCurrentDate
        editable = Integer.valueOf(-1);
        FragmentConsumableDate.setText("");
    }//_____________________________________________________________________________________________ End SetCurrentDate




    private void GetCarConsumableFromDB() {//_______________________________________________________ Start GetCarConsumableFromDB
        CarConsumable = fragmentConsumablesViewModel.GetCarConsumable(MainActivity.CarId);
        if (CarConsumable.size() > 0) {
            SetAdabter();
        } else {
            FragmentConsumables.setAdapter(null);
        }
    }//_____________________________________________________________________________________________ End GetCarConsumableFromDB



    private void SetAdabter() {//___________________________________________________________________ Start SetAdabter
        adabterConsumable = new AdabterConsumable(this, CarConsumable);
        FragmentConsumables.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
        FragmentConsumables.setAdapter(adabterConsumable);
    }//_____________________________________________________________________________________________ End SetAdabter



    private void SetTextChange() {//________________________________________________________________ Start SetTextChange

        FragmentConsumableKm.addTextChangedListener(MachinHeartApplication
                .getMachinHeartApplication(context)
                .getApplicationUtilityComponent()
                .getApplicationUtility()
                .SetTextWatcherSplitting(FragmentConsumableKm));

        FragmentConsumableNextKm.addTextChangedListener(MachinHeartApplication
                .getMachinHeartApplication(context)
                .getApplicationUtilityComponent()
                .getApplicationUtility()
                .SetTextWatcherSplitting(FragmentConsumableNextKm));

        FragmentConsumableMoney.addTextChangedListener(MachinHeartApplication
                .getMachinHeartApplication(context)
                .getApplicationUtilityComponent()
                .getApplicationUtility()
                .SetTextWatcherSplitting(FragmentConsumableMoney));

        FragmentConsumableTitle.addTextChangedListener(SetTextWatcherSuggestion(FragmentConsumableTitle));

    }//_____________________________________________________________________________________________ End SetTextChange



    private TextWatcher SetTextWatcherSuggestion(final EditText editText) {//_______________________ Start SetTextWatcherSuggestion
        return new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String n = editText.getText().toString();
                if (n.equalsIgnoreCase("")) {
                    FragmentConsumableSuggestion.setVisibility(View.GONE);
                    return;
                }
                ArrayList<String> list = FragmentConsumable.this.fragmentConsumablesViewModel.GetSuggestionTitle(n);
                if (list.size() == 0) {
                    FragmentConsumableSuggestion.setVisibility(View.GONE);
                } else {
                    ShowSuggestionList(list);
                }
            }

            public void afterTextChanged(Editable editable) {
                editText.setSelection(editText.getText().length());
            }
        };
    }//_____________________________________________________________________________________________ End SetTextWatcherSuggestion



    private void ShowSuggestionList(final ArrayList<String> list) {//_______________________________ Start ShowSuggestionList

        FragmentConsumableSuggestion.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, list));
        FragmentConsumableSuggestion.setVisibility(View.VISIBLE);
        FragmentConsumableSuggestion.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FragmentConsumableTitle.setText((CharSequence) list.get(i));
                FragmentConsumableSuggestion.setVisibility(View.GONE);
            }
        });
    }//_____________________________________________________________________________________________ End SetTextWatcherSuggestion


    private void SetClick() {//_____________________________________________________________________ Start SetClick



        FragmentConsumableLayout.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
            }
        });

        FragmentConsumableAddClick.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                FragmentConsumableTitle.setText("");
                FragmentConsumableKm.setText("");
                FragmentConsumableNextKm.setText("");
                FragmentConsumableBrand.setText("");
                FragmentConsumableMoney.setText("");
                if (FragmentConsumableexpandable.isExpanded()) {
                    FragmentConsumableexpandable.collapse();
                } else {
                    FragmentConsumableexpandable.expand();
                }
            }
        });

        FragmentConsumableIgnor.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                FragmentConsumableexpandable.collapse();
                editable = -1;
            }
        });

        FragmentConsumableSave.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (!CheckEmptyItems()) {
                    return;
                }
                if (editable.intValue() == -1) {
                    SaveToDataBase();
                } else {
                    EditDataBase();
                }
            }
        });

        FragmentConsumableDate.setOnClickListener(new OnClickListener() {
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
                        ChangeDate = Integer.valueOf(sb.toString());
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(persianCalendar.getPersianYear());
                        sb2.append("/");
                        sb2.append(String.format("%02d",persianCalendar.getPersianMonth()));
                        sb2.append("/");
                        sb2.append(String.format("%02d",persianCalendar.getPersianDay()));
                        FragmentConsumableDate.setText(sb2.toString());
                    }

                    public void onDismissed() {
                    }
                });
                persianCalendar.show();
            }
        });
    }//_____________________________________________________________________________________________ End SetTextWatcherSuggestion




    private Boolean CheckEmptyItems() {//___________________________________________________________ Start CheckEmptyItems

        if (FragmentConsumableTitle.getText().toString().trim().length() == 0) {
            ShowToast(this.context.getString(R.string.EmptyCahngeTitle));
            FragmentConsumableTitle.requestFocus();
            return false;
        } else if (FragmentConsumableKm.getText().toString().trim().length() == 0) {
            ShowToast(this.context.getString(R.string.EmptyChangeKM));
            FragmentConsumableKm.requestFocus();
            return false;
        } else if (FragmentConsumableNextKm.getText().toString().trim().length() == 0) {
            ShowToast(this.context.getString(R.string.EmptyChangeNext));
            FragmentConsumableNextKm.requestFocus();
            return false;
        } else {
            if (Integer.valueOf(this.FragmentConsumableKm.getText().toString().replaceAll(",", "").replaceAll("٬","")).intValue() > Integer.valueOf(this.FragmentConsumableNextKm.getText().toString().replaceAll(",", "").replaceAll("٬","")).intValue()) {
                ShowToast(this.context.getString(R.string.EmptyChangeNextError));
                FragmentConsumableNextKm.requestFocus();
                return false;
            } else if (FragmentConsumableMoney.getText().toString().trim().length() == 0) {
                ShowToast(this.context.getString(R.string.EmptyChangeMoney));
                FragmentConsumableMoney.requestFocus();
                return false;
            } else if (FragmentConsumableDate.getText().toString().trim().length() != 0) {
                return true;
            } else {
                ShowToast(this.context.getString(R.string.EmptyChangeDate));
                return false;
            }
        }
    }//_____________________________________________________________________________________________ End CheckEmptyItems



    private void ShowToast(String Message) {//___________________________________________________________ Start ShowToast
        MachinHeartApplication
                .getMachinHeartApplication(context)
                .getApplicationUtilityComponent()
                .getApplicationUtility()
                .CustomToastShow(context, Message);
    }//_____________________________________________________________________________________________ End ShowToast



    private void SaveToDataBase() {//_______________________________________________________________ Start SaveToDataBase
        fragmentConsumablesViewModel.setBrand(FragmentConsumableBrand.getText().toString());
        fragmentConsumablesViewModel.setCarId(MainActivity.CarId);
        fragmentConsumablesViewModel.setChangeDate(ChangeDate);
        fragmentConsumablesViewModel.setChangeKm(Integer.valueOf(FragmentConsumableKm.getText().toString().replaceAll(",", "").replaceAll("٬","")));
        fragmentConsumablesViewModel.setChangeNextKm(Integer.valueOf(FragmentConsumableNextKm.getText().toString().replaceAll(",", "").replaceAll("٬","")));
        fragmentConsumablesViewModel.setMoney(Integer.valueOf(FragmentConsumableMoney.getText().toString().replaceAll(",", "").replaceAll("٬","")));
        fragmentConsumablesViewModel.setTitle(FragmentConsumableTitle.getText().toString());
        fragmentConsumablesViewModel.SaveToDataBase();
    }//_____________________________________________________________________________________________ End SaveToDataBase




    public void EditDataBase() {//__________________________________________________________________ Start EditDataBase
        fragmentConsumablesViewModel.setBrand(FragmentConsumableBrand.getText().toString());
        fragmentConsumablesViewModel.setChangeKm(Integer.valueOf(FragmentConsumableKm.getText().toString().replaceAll(",", "").replaceAll("٬","")));
        fragmentConsumablesViewModel.setChangeNextKm(Integer.valueOf(FragmentConsumableNextKm.getText().toString().replaceAll(",", "").replaceAll("٬","")));
        fragmentConsumablesViewModel.setMoney(Integer.valueOf(FragmentConsumableMoney.getText().toString().replaceAll(",", "").replaceAll("٬","")));
        fragmentConsumablesViewModel.setTitle(FragmentConsumableTitle.getText().toString());
        fragmentConsumablesViewModel.setChangeDate(ChangeDate);
        fragmentConsumablesViewModel.EditDataBase((DataBaseConsumable) this.CarConsumable.get(this.editable.intValue()));
    }//_____________________________________________________________________________________________ End EditDataBase



    private void MessageControler() {//_____________________________________________________________ Start MessageControler

        fragmentConsumablesViewModel
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
                                        FragmentConsumableexpandable.collapse();
                                        GetCarConsumableFromDB();
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

    public void ItemClickEdit(int position) {//_____________________________________________________ Start ItemClickEdit

        FragmentConsumableTitle.setText(CarConsumable.get(position).getTitle());
        FragmentConsumableKm.setText(String.valueOf(CarConsumable.get(position).getChangeKm()));
        FragmentConsumableNextKm.setText(String.valueOf(CarConsumable.get(position).getChangeNextKm()));
        FragmentConsumableBrand.setText(CarConsumable.get(position).getBrand());
        FragmentConsumableMoney.setText(String.valueOf(CarConsumable.get(position).getMoney()));
        String temp = String.valueOf(CarConsumable.get(position).getChangeDate());
        StringBuilder sb = new StringBuilder();
        sb.append(temp.substring(0, 4));
        sb.append("/");
        sb.append(temp.substring(4, 6));
        sb.append("/");
        sb.append(temp.substring(6, 8));
        FragmentConsumableDate.setText(sb.toString());
        editable = Integer.valueOf(position);
        ChangeDate = CarConsumable.get(position).getChangeDate();
        FragmentConsumableSuggestion.setVisibility(View.GONE);
        FragmentConsumableexpandable.expand();
    }//_____________________________________________________________________________________________ End ItemClickEdit



    public void ItemClickDelete(final int position) {//_____________________________________________ Start ItemClickDelete

        new Builder(context)
                .setMessage(this.context.getResources().getString(R.string.AreYouSure))
                .setPositiveButton(this.context.getResources().getString(R.string.Yes), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (editable == -1) {
                    fragmentConsumablesViewModel.DeleteDataBase(CarConsumable.get(position));
                    return;
                }
                ShowToast(context.getResources().getString(R.string.ErrorDeleteWhenEdit));
            }
        }).setNegativeButton( this.context.getResources().getString(R.string.No), null).show();

    }//_____________________________________________________________________________________________ End ItemClickDelete


}
