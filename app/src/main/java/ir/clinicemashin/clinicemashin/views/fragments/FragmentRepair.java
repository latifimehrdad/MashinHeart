package ir.clinicemashin.clinicemashin.views.fragments;

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

import ir.clinicemashin.clinicemashin.R;
import ir.clinicemashin.clinicemashin.databases.DataBaseRepair;
import ir.clinicemashin.clinicemashin.databinding.FragmentRepairBinding;
import ir.clinicemashin.clinicemashin.viewmodels.FragmentRepairViewModel;
import ir.clinicemashin.clinicemashin.views.activitys.MainActivity;
import ir.clinicemashin.clinicemashin.views.adabters.AdabterRepair;
import ir.clinicemashin.clinicemashin.views.application.MachinHeartApplication;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.realm.RealmResults;
import ir.hamsaa.persiandatepicker.Listener;
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;
import ir.hamsaa.persiandatepicker.util.PersianCalendar;

import java.util.ArrayList;

import net.cachapa.expandablelayout.ExpandableLayout;

public class FragmentRepair extends Fragment {

    private RealmResults<DataBaseRepair> CarRepair;
    private Integer RepairDate;
    private AdabterRepair adabterRepair;
    public Context context;
    private Integer editable;
    private FragmentRepairViewModel fragmentRepairViewModel;
    private FragmentCarEvent fragmentCarEvent;
    private View view;

    @BindView(R.id.FragmentRepairAddClick)
    LinearLayout FragmentRepairAddClick;
    @BindView(R.id.FragmentRepairBrand)
    EditText FragmentRepairBrand;
    @BindView(R.id.FragmentRepairDate)
    Button FragmentRepairDate;
    @BindView(R.id.FragmentRepairExpandable)
    ExpandableLayout FragmentRepairExpandable;
    @BindView(R.id.FragmentRepairIgnor)
    Button FragmentRepairIgnor;
    @BindView(R.id.FragmentRepairKm)
    EditText FragmentRepairKm;
    @BindView(R.id.FragmentRepairLayout)
    LinearLayout FragmentRepairLayout;
    @BindView(R.id.FragmentRepairMoney)
    EditText FragmentRepairMoney;
    @BindView(R.id.FragmentRepairSave)
    Button FragmentRepairSave;
    @BindView(R.id.FragmentRepairSuggestion)
    ListView FragmentRepairSuggestion;
    @BindView(R.id.FragmentRepairTitle)
    EditText FragmentRepairTitle;
    @BindView(R.id.FragmentRepairWhy)
    EditText FragmentRepairWhy;
    @BindView(R.id.FragmentRepairs)
    RecyclerView FragmentRepairs;


    public FragmentRepair(Context context, FragmentCarEvent fragmentCarEvent) {//_____________________________________________________ Start FragmentRepair
        this.context = context;
        this.fragmentCarEvent = fragmentCarEvent;
    }//_____________________________________________________________________________________________ End FragmentRepair


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentRepairBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_repair, container, false);
        fragmentRepairViewModel = new FragmentRepairViewModel(context);
        binding.setRepair(fragmentRepairViewModel);
        view = binding.getRoot();
        ButterKnife.bind(this, view);
        return view;
    }//_____________________________________________________________________________________________ End onCreateView


    public void onStart() {//_____________________________________________________ Start FragmentRepair
        super.onStart();
        SetClick();
        BackClick();
        SetTextChange();
        FragmentRepairExpandable.collapse();
        FragmentRepairSuggestion.setVisibility(View.GONE);
        SetCurrentDate();
        MessageControler();
        GetCarRepairFromDB();
    }//_____________________________________________________________________________________________ End onCreateView




    private void BackClick() {//____________________________________________________________________ Start BackClick
        view.setFocusableInTouchMode(true);
        view.requestFocus();

        view.setOnKeyListener(SetKey(view));
        FragmentRepairSave.setOnKeyListener(SetKey(FragmentRepairSave));
        FragmentRepairAddClick.setOnKeyListener(SetKey(FragmentRepairAddClick));
        FragmentRepairTitle.setOnKeyListener(SetKey(FragmentRepairTitle));
        FragmentRepairKm.setOnKeyListener(SetKey(FragmentRepairKm));
        FragmentRepairWhy.setOnKeyListener(SetKey(FragmentRepairWhy));
        FragmentRepairBrand.setOnKeyListener(SetKey(FragmentRepairBrand));
        FragmentRepairMoney.setOnKeyListener(SetKey(FragmentRepairMoney));
        FragmentRepairDate.setOnKeyListener(SetKey(FragmentRepairDate));
        FragmentRepairIgnor.setOnKeyListener(SetKey(FragmentRepairIgnor));
    }//_____________________________________________________________________________________________ End BackClick




    private View.OnKeyListener SetKey(View view){//_________________________________________________ Start SetKey
        return new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() != KeyEvent.ACTION_DOWN)
                    return true;

                if (keyCode != 4) {
                    return false;
                }
                keyCode = 0;
                event = null;
                fragmentCarEvent.MessageType.onNext("close");
                return true;
            }
        };
    }//_____________________________________________________________________________________________ End SetKey





    private void SetClick() {//_____________________________________________________________________ Start FragmentRepair

        FragmentRepairLayout.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
            }
        });

        FragmentRepairAddClick.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                FragmentRepairTitle.setText("");
                FragmentRepairKm.setText("");
                FragmentRepairWhy.setText("");
                FragmentRepairBrand.setText("");
                FragmentRepairMoney.setText("");
                if (FragmentRepairExpandable.isExpanded()) {
                    FragmentRepairExpandable.collapse();
                } else {
                    FragmentRepairExpandable.expand();
                }
            }
        });

        FragmentRepairIgnor.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                FragmentRepairExpandable.collapse();
                editable = -1;
            }
        });

        FragmentRepairSave.setOnClickListener(new OnClickListener() {
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

        FragmentRepairDate.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                PersianDatePickerDialog persianCalendar = MachinHeartApplication
                        .getMachinHeartApplication(context)
                        .getPersianPickerComponent()
                        .getPersianDatePickerDialog();

                persianCalendar.setListener(new Listener() {
                    public void onDateSelected(PersianCalendar persianCalendar) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(persianCalendar.getPersianYear());
                        sb.append(String.format("%02d", persianCalendar.getPersianMonth()));
                        sb.append(String.format("%02d", persianCalendar.getPersianDay()));
                        RepairDate = Integer.valueOf(sb.toString());
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(persianCalendar.getPersianYear());
                        sb2.append("/");
                        sb2.append(String.format("%02d", persianCalendar.getPersianMonth()));
                        sb2.append("/");
                        sb2.append(String.format("%02d", persianCalendar.getPersianDay()));
                        FragmentRepairDate.setText(sb2.toString());
                    }

                    public void onDismissed() {
                    }
                });
                persianCalendar.show();
            }
        });
    }//_____________________________________________________________________________________________ End onCreateView


    private void SetTextChange() {//________________________________________________________________ Start SetTextChange

        FragmentRepairKm.addTextChangedListener(MachinHeartApplication
                .getMachinHeartApplication(context)
                .getApplicationUtilityComponent()
                .getApplicationUtility()
                .SetTextWatcherSplitting(FragmentRepairKm));

        FragmentRepairMoney.addTextChangedListener(MachinHeartApplication
                .getMachinHeartApplication(context)
                .getApplicationUtilityComponent()
                .getApplicationUtility()
                .SetTextWatcherSplitting(FragmentRepairMoney));

        FragmentRepairTitle.addTextChangedListener(SetTextWatcherSuggestion(FragmentRepairTitle));

    }//_____________________________________________________________________________________________ End SetTextChange


    private TextWatcher SetTextWatcherSuggestion(final EditText editText) {//_______________________ Start SetTextWatcherSuggestion
        return new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String n = editText.getText().toString();
                if (n.equalsIgnoreCase("")) {
                    FragmentRepairSuggestion.setVisibility(View.GONE);
                    return;
                }
                ArrayList<String> list = fragmentRepairViewModel.GetSuggestionTitle(n);
                if (list.size() == 0) {
                    FragmentRepairSuggestion.setVisibility(View.GONE);
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

        FragmentRepairSuggestion.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, list));
        FragmentRepairSuggestion.setVisibility(View.VISIBLE);
        FragmentRepairSuggestion.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FragmentRepairTitle.setText((CharSequence) list.get(i));
                FragmentRepairSuggestion.setVisibility(View.GONE);
            }
        });

    }//_____________________________________________________________________________________________ End ShowSuggestionList


    private void SetCurrentDate() {//_______________________________________________________________ Start SetCurrentDate

        editable = Integer.valueOf(-1);
        FragmentRepairDate.setText("");

    }//_____________________________________________________________________________________________ End SetCurrentDate


    private void MessageControler() {//_______________________________________________________________ Start MessageControler

        fragmentRepairViewModel
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
                                        FragmentRepairExpandable.collapse();
                                        GetCarRepairFromDB();
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


    private void GetCarRepairFromDB() {//___________________________________________________________ Start GetCarRepairFromDB
        CarRepair = fragmentRepairViewModel.GetCarRepair(MainActivity.CarId);
        if (CarRepair.size() > 0) {
            SetAdabter();
        } else {
            FragmentRepairs.setAdapter(null);
        }
    }//_____________________________________________________________________________________________ End GetCarRepairFromDB


    private void SetAdabter() {//___________________________________________________________________ Start SetAdabter
        adabterRepair = new AdabterRepair(this, CarRepair);
        FragmentRepairs.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
        FragmentRepairs.setAdapter(adabterRepair);
    }//_____________________________________________________________________________________________ End SetAdabter


    public Boolean CheckEmptyItems() {//____________________________________________________________ Start CheckEmptyItems

        if (FragmentRepairTitle.getText().toString().trim().length() == 0) {
            ShowToast(this.context.getString(R.string.EmptyRepairTitle));
            FragmentRepairTitle.requestFocus();
            return false;
        } else if (FragmentRepairKm.getText().toString().trim().length() == 0) {
            ShowToast(this.context.getString(R.string.EmptyRepairKM));
            FragmentRepairKm.requestFocus();
            return false;
        } else if (FragmentRepairMoney.getText().toString().trim().length() == 0) {
            ShowToast(this.context.getString(R.string.EmptyChangeMoney));
            FragmentRepairMoney.requestFocus();
            return false;
        } else if (FragmentRepairDate.getText().toString().trim().length() != 0) {
            return true;
        } else {
            ShowToast(this.context.getString(R.string.EmptyChangeDate));
            return false;
        }
    }//_____________________________________________________________________________________________ End CheckEmptyItems


    public void ShowToast(String Message) {//_______________________________________________________ Start ShowToast

        MachinHeartApplication
                .getMachinHeartApplication(context)
                .getApplicationUtilityComponent()
                .getApplicationUtility()
                .CustomToastShow(context, Message);

    }//_____________________________________________________________________________________________ End ShowToast


    public void SaveToDataBase() {//________________________________________________________________ Start SaveToDataBase
        fragmentRepairViewModel.setBrand(FragmentRepairBrand.getText().toString());
        fragmentRepairViewModel.setCarId(MainActivity.CarId);
        fragmentRepairViewModel.setRepairDate(RepairDate);
        fragmentRepairViewModel.setRepairKm(Integer.valueOf(FragmentRepairKm.getText().toString().replaceAll(",", "").replaceAll("٬","")));
        fragmentRepairViewModel.setRepairWhy(FragmentRepairWhy.getText().toString().replaceAll(",", "").replaceAll("٬",""));
        fragmentRepairViewModel.setMoney(Integer.valueOf(FragmentRepairMoney.getText().toString().replaceAll(",", "").replaceAll("٬","")));
        fragmentRepairViewModel.setTitle(FragmentRepairTitle.getText().toString());
        fragmentRepairViewModel.SaveToDataBase();
    }//_____________________________________________________________________________________________ End SaveToDataBase


    public void EditDataBase() {//__________________________________________________________________ Start EditDataBase

        fragmentRepairViewModel.setBrand(FragmentRepairBrand.getText().toString());
        fragmentRepairViewModel.setRepairKm(Integer.valueOf(FragmentRepairKm.getText().toString().replaceAll(",", "").replaceAll("٬","")));
        fragmentRepairViewModel.setRepairWhy(FragmentRepairWhy.getText().toString().replaceAll(",", "").replaceAll("٬",""));
        fragmentRepairViewModel.setMoney(Integer.valueOf(FragmentRepairMoney.getText().toString().replaceAll(",", "").replaceAll("٬","")));
        fragmentRepairViewModel.setTitle(FragmentRepairTitle.getText().toString());
        fragmentRepairViewModel.setRepairDate(RepairDate);
        fragmentRepairViewModel.EditDataBase(CarRepair.get(editable));
    }//_____________________________________________________________________________________________ End EditDataBase


    public void ItemClick(int position) {
    }


    public void ItemClickEdit(int position) {//_____________________________________________________ Start ItemClickEdit

        FragmentRepairTitle.setText((CarRepair.get(position)).getTitle());
        FragmentRepairKm.setText(String.valueOf((CarRepair.get(position)).getRepairKm()));
        FragmentRepairWhy.setText(String.valueOf((CarRepair.get(position)).getRepairWhy()));
        FragmentRepairBrand.setText((CarRepair.get(position)).getBrand());
        FragmentRepairMoney.setText(String.valueOf((CarRepair.get(position)).getMoney()));
        String temp = String.valueOf((CarRepair.get(position)).getRepairDate());
        StringBuilder sb = new StringBuilder();
        sb.append(temp.substring(0, 4));
        sb.append("/");
        sb.append(temp.substring(4, 6));
        sb.append("/");
        sb.append(temp.substring(6, 8));
        FragmentRepairDate.setText(sb.toString());
        editable = Integer.valueOf(position);
        RepairDate = (CarRepair.get(position)).getRepairDate();
        FragmentRepairSuggestion.setVisibility(View.GONE);
        FragmentRepairExpandable.expand();
    }//_____________________________________________________________________________________________ End ItemClickEdit


    public void ItemClickDelete(final int position) {//_____________________________________________ Start ItemClickDelete

        new Builder(context)
                .setMessage(this.context.getResources().getString(R.string.AreYouSure))
                .setPositiveButton(this.context.getResources().getString(R.string.Yes), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (editable.intValue() == -1) {
                            fragmentRepairViewModel.DeleteDataBase(CarRepair.get(position));
                            return;
                        }
                        ShowToast(context.getResources().getString(R.string.ErrorDeleteWhenEdit));
                    }
                }).setNegativeButton(this.context.getResources().getString(R.string.No), null).show();
    }//_____________________________________________________________________________________________ End ItemClickDelete


}
