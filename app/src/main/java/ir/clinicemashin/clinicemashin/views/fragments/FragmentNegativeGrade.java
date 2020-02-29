package ir.clinicemashin.clinicemashin.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import ir.clinicemashin.clinicemashin.R;
import ir.clinicemashin.clinicemashin.databinding.FragmentNegativeGradeBinding;
import ir.clinicemashin.clinicemashin.viewmodels.FragmentNegativeGradeViewModel;
import ir.clinicemashin.clinicemashin.views.application.MachinHeartApplication;
import ir.clinicemashin.clinicemashin.views.dialogs.DialogProgress;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import java.text.DecimalFormat;
import java.util.ArrayList;

import net.cachapa.expandablelayout.ExpandableLayout;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class FragmentNegativeGrade extends Fragment {

    private Context context;
    private FragmentNegativeGradeViewModel fragmentNegativeGradeViewModel;
    private DialogProgress progress;
    private DisposableObserver<String> observer;


    @BindView(R.id.FragmentNegativeGradeBarcode)
    EditText FragmentNegativeGradeBarcode;
    @BindView(R.id.FragmentNegativeGradeCaptcha)
    ImageView FragmentNegativeGradeCaptcha;
    @BindView(R.id.FragmentNegativeGradeCaptchaTxt)
    EditText FragmentNegativeGradeCaptchaTxt;
    @BindView(R.id.FragmentNegativeGradeCount)
    TextView FragmentNegativeGradeCount;
    @BindView(R.id.FragmentNegativeGradeExpandable)
    ExpandableLayout FragmentNegativeGradeExpandable;
    @BindView(R.id.FragmentNegativeGradeGrade)
    TextView FragmentNegativeGradeGrade;
    @BindView(R.id.FragmentNegativeGradeIgnor)
    Button FragmentNegativeGradeIgnor;
    @BindView(R.id.FragmentNegativeGradeLayout)
    LinearLayout FragmentNegativeGradeLayout;
    @BindView(R.id.FragmentNegativeGradeNew)
    LinearLayout FragmentNegativeGradeNew;
    @BindView(R.id.FragmentNegativeGradeRefresh)
    ImageView FragmentNegativeGradeRefresh;
    @BindView(R.id.FragmentNegativeGradeSend)
    Button FragmentNegativeGradeSend;



    public FragmentNegativeGrade() {//______________________________________________________________ Start FragmentNegativeGrade

    }//_____________________________________________________________________________________________ End FragmentNegativeGrade



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context =getActivity();
        FragmentNegativeGradeBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_negative_grade, container, false);
        fragmentNegativeGradeViewModel = new FragmentNegativeGradeViewModel(context);
        binding.setNegative(fragmentNegativeGradeViewModel);
        View view = binding.getRoot();
        ButterKnife.bind( this, view);
        return view;
    }//_____________________________________________________________________________________________ End onCreateView



    public void onStart() {//_______________________________________________________________________ Start onStart
        super.onStart();
        SetClick();
        if(observer != null)
            observer.dispose();
        observer = null;
        MessageControler();
        FragmentNegativeGradeExpandable.collapse();
        FragmentNegativeGradeBarcode.setText("");
        FragmentNegativeGradeCaptchaTxt.setText("");
        FragmentNegativeGradeGrade.setText("");
        FragmentNegativeGradeCount.setText("");
        ShowDialog();
        fragmentNegativeGradeViewModel.GetImageCaptcha(false, false);
    }//_____________________________________________________________________________________________ End onStart



    private void SetClick() {//_____________________________________________________________________ Start SetClick

        FragmentNegativeGradeLayout.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
            }
        });

        FragmentNegativeGradeRefresh.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ShowDialog();
                fragmentNegativeGradeViewModel.GetImageCaptcha(false, true);
            }
        });


        FragmentNegativeGradeNew.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                FragmentNegativeGradeExpandable.collapse();
                FragmentNegativeGradeBarcode.setText("");
                FragmentNegativeGradeCaptchaTxt.setText("");
                FragmentNegativeGradeGrade.setText("");
                FragmentNegativeGradeCount.setText("");
                ShowDialog();
                fragmentNegativeGradeViewModel.GetImageCaptcha(false, false);
            }
        });


        FragmentNegativeGradeIgnor.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                FragmentNegativeGradeExpandable.collapse();
            }
        });


        FragmentNegativeGradeSend.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (CheckEmptyItems()) {
                    ShowDialog();
                    fragmentNegativeGradeViewModel.CheckCaptcha(FragmentNegativeGradeBarcode.getText().toString(), FragmentNegativeGradeCaptchaTxt.getText().toString());
                }
            }
        });

    }//_____________________________________________________________________________________________ End SetClick




    private void MessageControler() {//_____________________________________________________________ Start MessageControler

        observer = new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                getActivity()
                        .runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                switch (s) {
                                    case "CancelByUser":
                                        FragmentNegativeGradeExpandable.collapse();
                                        if (progress != null) {
                                            progress.dismiss();
                                        }
                                        break;
                                    case "ارتباط با پلیس راهور برقرار نشد":
                                        if (progress != null) {
                                            progress.dismiss();
                                        }
                                        FragmentNegativeGradeExpandable.collapse();
                                        ShowToast(s);
                                        break;
                                    case "CaptchaOk":
                                        if (progress != null) {
                                            progress.dismiss();
                                        }
                                        FragmentNegativeGradeCaptcha.setImageBitmap(fragmentNegativeGradeViewModel.getCaptchaBitmap());
                                        FragmentNegativeGradeExpandable.expand();
                                        break;
                                    case "متن امنیتی را اشتباه وارد کرده اید":
                                        if (progress != null) {
                                            progress.dismiss();
                                        }
                                        ShowToast("ارتباط با پلیس راهور برقرار نشد");
                                        FragmentNegativeGradeCaptcha.setImageBitmap(fragmentNegativeGradeViewModel.getCaptchaBitmap());
                                        FragmentNegativeGradeExpandable.expand();
                                        break;
                                    case "PoliceFineOk":
                                        FragmentNegativeGradeExpandable.collapse();
                                        ShowPoliceFine(fragmentNegativeGradeViewModel.getHtml());
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
        };

        fragmentNegativeGradeViewModel
                .getMessageType()
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }//_____________________________________________________________________________________________ End MessageControler



    private void ShowDialog() {//___________________________________________________________________ Start ShowDialog
        progress = new DialogProgress(context, null, fragmentNegativeGradeViewModel,null, context.getResources().getString(R.string.ConnetToPolice));
        progress.setCancelable(false);
        progress.show(getFragmentManager(), NotificationCompat.CATEGORY_PROGRESS);
    }//_____________________________________________________________________________________________ End ShowDialog




    private void ShowToast(String Message) {//______________________________________________________ Start ShowToast
        MachinHeartApplication
                .getMachinHeartApplication(context)
                .getApplicationUtilityComponent()
                .getApplicationUtility()
                .CustomToastShow(context, Message);
    }//_____________________________________________________________________________________________ End ShowToast




    private Boolean CheckEmptyItems() {//____________________________________________________________ Start CheckEmptyItems
        if (FragmentNegativeGradeBarcode.getText().toString().trim().length() < 11) {
            ShowToast(this.context.getString(R.string.EmptyNegativeGradeBarcode));
            FragmentNegativeGradeBarcode.requestFocus();
            return false;
        } else if (FragmentNegativeGradeCaptchaTxt.getText().toString().trim().length() != 0) {
            return true;
        } else {
            ShowToast(this.context.getString(R.string.EmptyPoliceFineCaptcha));
            FragmentNegativeGradeCaptchaTxt.requestFocus();
            return false;
        }
    }//_____________________________________________________________________________________________ End CheckEmptyItems



    private void ShowPoliceFine(String html) {//____________________________________________________ Start ShowPoliceFine
        try {
            Document document = Jsoup.parse(html);
            new ArrayList();
            Elements elem_list = document.select("saveditems");
            if (elem_list.size() > 0) {
                String m1 = elem_list.select("div[id=itemDrop10658]").text();
                String m2 = elem_list.select("div[id=itemDrop10657]").text();
                new DecimalFormat("#,###");
                StringBuilder sb = new StringBuilder();
                sb.append(context.getResources().getString(R.string.NegativeGrade));
                sb.append(" : ");
                sb.append(m1);
                FragmentNegativeGradeGrade.setText(sb.toString());
                StringBuilder sb2 = new StringBuilder();
                sb2.append(context.getResources().getString(R.string.TotalPoliceFine));
                sb2.append(" : ");
                sb2.append(m2);
                FragmentNegativeGradeCount.setText(sb2.toString());
                FragmentNegativeGradeBarcode.setText("");
                FragmentNegativeGradeBarcode.setText("");
            } else if (elem_list.size() <= 0) {
                ShowToast(this.context.getResources().getString(R.string.ErrorGetNegativeGrade));
                FragmentNegativeGradeBarcode.setText("");
                FragmentNegativeGradeBarcode.setText("");
                StringBuilder sb3 = new StringBuilder();
                sb3.append(context.getResources().getString(R.string.NegativeGrade));
                sb3.append(" ");
                sb3.append("0");
                FragmentNegativeGradeCount.setText(sb3.toString());
                StringBuilder sb4 = new StringBuilder();
                sb4.append(this.context.getResources().getString(R.string.TotalPoliceFine));
                sb4.append(" : ");
                sb4.append(0);
                FragmentNegativeGradeGrade.setText(sb4.toString());
            } else {
                ShowToast(this.context.getResources().getString(R.string.ErrorGetNegativeGrade));
                FragmentNegativeGradeBarcode.setText("");
                FragmentNegativeGradeBarcode.setText("");
                StringBuilder sb3 = new StringBuilder();
                sb3.append(context.getResources().getString(R.string.NegativeGrade));
                sb3.append(" ");
                sb3.append("0");
                FragmentNegativeGradeCount.setText(sb3.toString());
                StringBuilder sb4 = new StringBuilder();
                sb4.append(this.context.getResources().getString(R.string.TotalPoliceFine));
                sb4.append(" : ");
                sb4.append(0);
                FragmentNegativeGradeGrade.setText(sb4.toString());
            }
        } catch (Exception e) {
            ShowToast(this.context.getResources().getString(R.string.ErrorGetNegativeGrade));
            FragmentNegativeGradeBarcode.setText("");
            FragmentNegativeGradeBarcode.setText("");
            StringBuilder sb3 = new StringBuilder();
            sb3.append(context.getResources().getString(R.string.NegativeGrade));
            sb3.append(" ");
            sb3.append("0");
            FragmentNegativeGradeCount.setText(sb3.toString());
            StringBuilder sb4 = new StringBuilder();
            sb4.append(this.context.getResources().getString(R.string.TotalPoliceFine));
            sb4.append(" : ");
            sb4.append(0);
            FragmentNegativeGradeGrade.setText(sb4.toString());
        }
        if (progress != null) {
            progress.dismiss();
        }
    }//_____________________________________________________________________________________________ End ShowPoliceFine


    @Override
    public void onDestroy() {//_____________________________________________________________________ Start onDestroy
        super.onDestroy();
        observer.dispose();
    }//_____________________________________________________________________________________________ End onDestroy


}
