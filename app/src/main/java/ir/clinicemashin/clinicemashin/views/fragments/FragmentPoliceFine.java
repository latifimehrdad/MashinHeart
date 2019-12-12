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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ir.clinicemashin.clinicemashin.R;
import ir.clinicemashin.clinicemashin.databinding.FragmentPoliceFineBinding;
import ir.clinicemashin.clinicemashin.models.ModelPoliceFine;
import ir.clinicemashin.clinicemashin.viewmodels.FragmentPoliceFineViewModel;
import ir.clinicemashin.clinicemashin.views.adabters.AdabterPoliceFine;
import ir.clinicemashin.clinicemashin.views.application.MachinHeartApplication;
import ir.clinicemashin.clinicemashin.views.dialogs.DialogProgress;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import net.cachapa.expandablelayout.ExpandableLayout;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class FragmentPoliceFine extends Fragment {

    private Context context;
    private FragmentPoliceFineViewModel fragmentPoliceFineViewModel;
    private ArrayList<ModelPoliceFine> modelPoliceFinesArray;
    private DialogProgress progress;

    @BindView(R.id.FragmentPoliceFineBarcode)
    EditText FragmentPoliceFineBarcode;
    @BindView(R.id.FragmentPoliceFineCaptcha)
    ImageView FragmentPoliceFineCaptcha;
    @BindView(R.id.FragmentPoliceFineCaptchaTxt)
    EditText FragmentPoliceFineCaptchaTxt;
    @BindView(R.id.FragmentPoliceFineExpandable)
    ExpandableLayout FragmentPoliceFineExpandable;
    @BindView(R.id.FragmentPoliceFineIgnor)
    Button FragmentPoliceFineIgnor;
    @BindView(R.id.FragmentPoliceFineLayout)
    LinearLayout FragmentPoliceFineLayout;
    @BindView(R.id.FragmentPoliceFineNew)
    LinearLayout FragmentPoliceFineNew;
    @BindView(R.id.FragmentPoliceFinePela)
    TextView FragmentPoliceFinePela;
    @BindView(R.id.FragmentPoliceFinePelak)
    LinearLayout FragmentPoliceFinePelak;
    @BindView(R.id.FragmentPoliceFinePelakIran)
    TextView FragmentPoliceFinePelakIran;
    @BindView(R.id.FragmentPoliceFinePrice)
    TextView FragmentPoliceFinePrice;
    @BindView(R.id.FragmentPoliceFineRefresh)
    ImageView FragmentPoliceFineRefresh;
    @BindView(R.id.FragmentPoliceFineSend)
    Button FragmentPoliceFineSend;
    @BindView(R.id.FragmentPoliceFines)
    RecyclerView FragmentPoliceFines;


    public FragmentPoliceFine(Context context2) {//_____________________ Start FragmentPoliceFine
        this.context = context2;
    }//_____________________________________________________________________________________________ End FragmentPoliceFine


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentPoliceFineViewModel = new FragmentPoliceFineViewModel(context);
        FragmentPoliceFineBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_police_fine, container, false);
        binding.setPoliceFine(fragmentPoliceFineViewModel);
        View view = binding.getRoot();
        ButterKnife.bind(this, view);
        return view;
    }//_____________________________________________________________________________________________ End onCreateView


    public void onStart() {//_______________________________________________________________________ Start onStart
        super.onStart();
        FragmentPoliceFineExpandable.collapse();
        String str = "";
        FragmentPoliceFinePela.setText(str);
        FragmentPoliceFinePelakIran.setText(str);
        FragmentPoliceFinePelak.setVisibility(View.GONE);
        FragmentPoliceFinePrice.setVisibility(View.GONE);
        TextView textView = FragmentPoliceFinePrice;
        StringBuilder sb = new StringBuilder();
        sb.append(this.context.getResources().getString(R.string.TotalPoliceFine));
        sb.append(" ");
        sb.append(String.valueOf(0));
        sb.append(" ");
        sb.append(this.context.getResources().getString(R.string.Rial));
        textView.setText(sb.toString());
        SetClick();
        MessageControler();
    }//_____________________________________________________________________________________________ End onStart


    private void SetClick() {//_____________________________________________________________________ Start SetClick

        FragmentPoliceFineLayout.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
            }
        });

        FragmentPoliceFineRefresh.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ShowDialog();
                fragmentPoliceFineViewModel.GetImageCaptcha(false);
            }
        });


        FragmentPoliceFineNew.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                FragmentPoliceFineExpandable.collapse();
                String str = "";
                FragmentPoliceFineBarcode.setText(str);
                FragmentPoliceFineCaptchaTxt.setText(str);
                FragmentPoliceFinePelak.setVisibility(View.GONE);
                FragmentPoliceFinePrice.setVisibility(View.GONE);
                FragmentPoliceFines.setAdapter(null);
                FragmentPoliceFinePela.setText(str);
                FragmentPoliceFinePelakIran.setText(str);
                StringBuilder sb = new StringBuilder();
                sb.append(FragmentPoliceFine.this.context.getResources().getString(R.string.TotalPoliceFine));
                sb.append(" ");
                sb.append(String.valueOf(0));
                sb.append(" ");
                sb.append(FragmentPoliceFine.this.context.getResources().getString(R.string.Rial));
                FragmentPoliceFinePrice.setText(sb.toString());
                ShowDialog();
                fragmentPoliceFineViewModel.GetImageCaptcha(false);
            }
        });

        FragmentPoliceFineIgnor.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                FragmentPoliceFineExpandable.collapse();
            }
        });


        FragmentPoliceFineSend.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (CheckEmptyItems()) {
                    ShowDialog();
                    fragmentPoliceFineViewModel.CheckCaptcha(FragmentPoliceFineBarcode.getText().toString(), FragmentPoliceFineCaptchaTxt.getText().toString());
                }
            }
        });

    }//_____________________________________________________________________________________________ End SetClick


    private void MessageControler() {//_____________________________________________________________ Start MessageControler

        fragmentPoliceFineViewModel
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
                                            case "CancelByUser":
                                                FragmentPoliceFineExpandable.collapse();
                                                if (progress != null) {
                                                    progress.dismiss();
                                                }
                                                break;
                                            case "ارتباط با پلیس راهور برقرار نشد":
                                                if (progress != null) {
                                                    progress.dismiss();
                                                }
                                                FragmentPoliceFineExpandable.collapse();
                                                ShowToast(s);
                                                break;
                                            case "CaptchaOk":
                                                if (progress != null) {
                                                    progress.dismiss();
                                                }
                                                FragmentPoliceFineCaptcha.setImageBitmap(fragmentPoliceFineViewModel.getCaptchaBitmap());
                                                FragmentPoliceFineExpandable.expand();
                                                break;
                                            case "متن امنیتی را اشتباه وارد کرده اید":
                                                if (progress != null) {
                                                    progress.dismiss();
                                                }
                                                ShowToast(s);
                                                FragmentPoliceFineCaptcha.setImageBitmap(fragmentPoliceFineViewModel.getCaptchaBitmap());
                                                FragmentPoliceFineExpandable.expand();
                                                break;
                                            case "PoliceFineOk":
                                                FragmentPoliceFineExpandable.collapse();
                                                ShowPoliceFine(fragmentPoliceFineViewModel.getHtml());
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


    private void ShowDialog() {//___________________________________________________________________ Start ShowDialog
        progress = new DialogProgress(context, fragmentPoliceFineViewModel, null,null, context.getResources().getString(R.string.ConnetToPolice));
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


    public Boolean CheckEmptyItems() {//____________________________________________________________ Start CheckEmptyItems

        if (FragmentPoliceFineBarcode.getText().toString().trim().length() < 8) {
            ShowToast(this.context.getString(R.string.EmptyPoliceFineBarcode));
            FragmentPoliceFineBarcode.requestFocus();
            return false;
        } else if (FragmentPoliceFineCaptchaTxt.getText().toString().trim().length() != 0) {
            return true;
        } else {
            ShowToast(this.context.getString(R.string.EmptyPoliceFineCaptcha));
            FragmentPoliceFineCaptchaTxt.requestFocus();
            return false;
        }
    }//_____________________________________________________________________________________________ End CheckEmptyItems


    public void ShowPoliceFine(String html) {//_____________________________________________________ Start ShowPoliceFine

        long price = 0;
        try {
            Document document = Jsoup.parse(html);
            String pelak_p1 = document.select("div[id=p1]").first().text().replaceAll("ــ", "");
            FragmentPoliceFinePela.setText("ا" + "  " + document.select("div[id=p2]").first().text() + "  " + "ا");
            FragmentPoliceFinePelakIran.setText(pelak_p1);
            Elements elems = document.select("table[id=t1] tbody").select("tr");
            if (elems.size() > 0) {
                modelPoliceFinesArray = new ArrayList<>();
                int counter = 0;
                while (counter < elems.size()) {
                    Elements tdelem = ((Element) elems.get(counter)).select("td[data-value=78025368997]");
                    ModelPoliceFine modelPoliceFine = new ModelPoliceFine(
                            (tdelem.get(0)).text(),
                            (tdelem.get(1)).text(),
                            (tdelem.get(2)).text(),
                            Integer.valueOf((tdelem.get(3)).text()),
                            (tdelem.get(4)).text(),
                            (tdelem.get(5)).text(),
                            (tdelem.get(6)).text(),
                            (tdelem.get(7)).text(),
                            (tdelem.get(10)).text(),
                            (tdelem.get(11)).text());

                    modelPoliceFinesArray.add(modelPoliceFine);
                    price += Long.parseLong((tdelem.get(3)).text());
                    counter++;
                }

                NumberFormat formatter = new DecimalFormat("#,###");
                StringBuilder sb = new StringBuilder();
                sb.append(this.context.getResources().getString(R.string.TotalPoliceFine));
                sb.append(" ");
                sb.append(formatter.format(price));
                sb.append(" ");
                sb.append(this.context.getResources().getString(R.string.Rial));
                FragmentPoliceFinePrice.setText(sb.toString());
                FragmentPoliceFinePelak.setVisibility(View.VISIBLE);
                FragmentPoliceFinePrice.setVisibility(View.VISIBLE);
                if (modelPoliceFinesArray.size() > 0) {
                    SetAdabter(modelPoliceFinesArray);
                }
            }
        } catch (Exception e) {
            ShowToast(this.context.getResources().getString(R.string.ErrorGetPoliceFine));
            FragmentPoliceFinePela.setText("");
            FragmentPoliceFinePelakIran.setText("");
            FragmentPoliceFinePelak.setVisibility(View.GONE);
            FragmentPoliceFinePrice.setVisibility(View.GONE);
            StringBuilder sb2 = new StringBuilder();
            sb2.append(this.context.getResources().getString(R.string.TotalPoliceFine));
            sb2.append(" ");
            sb2.append("0");
            sb2.append(" ");
            sb2.append(this.context.getResources().getString(R.string.Rial));
            FragmentPoliceFinePrice.setText(sb2.toString());
        }

        if (progress != null) {
            progress.dismiss();
        }
    }//_____________________________________________________________________________________________ End ShowPoliceFine


    private void SetAdabter(ArrayList<ModelPoliceFine> arrayList) {//_______________________________ Start SetAdabter
        AdabterPoliceFine adabterPoliceFine = new AdabterPoliceFine(modelPoliceFinesArray, context);
        FragmentPoliceFines.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
        FragmentPoliceFines.setAdapter(adabterPoliceFine);
    }//_____________________________________________________________________________________________ End SetAdabter


    public Context getContext() {
        return this.context;
    }

    public void setContext(Context context2) {
        this.context = context2;
    }
}
