package ir.clinicemashin.clinicemashin.views.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import ir.clinicemashin.clinicemashin.R;
import ir.clinicemashin.clinicemashin.databinding.DialogProgressBinding;
import ir.clinicemashin.clinicemashin.viewmodels.FragmentAdvertiseViewModel;
import ir.clinicemashin.clinicemashin.viewmodels.FragmentNegativeGradeViewModel;
import ir.clinicemashin.clinicemashin.viewmodels.FragmentPoliceFineViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DialogProgress extends DialogFragment {

    @BindView(R.id.DialogProgressIgnor)
    Button DialogProgressIgnor;
    @BindView(R.id.DialogProgressTitle)
    TextView DialogProgressTitle;

    private String Title;
    private Context context;
    private FragmentNegativeGradeViewModel fragmentNegativeGradeViewModel;
    private FragmentPoliceFineViewModel fragmentPoliceFineViewModel;
    private FragmentAdvertiseViewModel fragmentAdvertiseViewModel;

    public DialogProgress(Context context,
                          FragmentPoliceFineViewModel fragmentPoliceFineViewModel,
                          FragmentNegativeGradeViewModel fragmentNegativeGradeViewModel,
                          FragmentAdvertiseViewModel fragmentAdvertiseViewModel,
                          String title) {
        this.context = context;
        this.fragmentPoliceFineViewModel = fragmentPoliceFineViewModel;
        this.fragmentNegativeGradeViewModel = fragmentNegativeGradeViewModel;
        this.fragmentAdvertiseViewModel = fragmentAdvertiseViewModel;
        this.Title = title;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = null;
        if (fragmentPoliceFineViewModel != null) {
            DialogProgressBinding binding = DataBindingUtil.inflate(LayoutInflater.from(this.context), R.layout.dialog_progress, null, false);
            binding.setPoliceFine(this.fragmentPoliceFineViewModel);
            view = binding.getRoot();
        }
        if (fragmentNegativeGradeViewModel != null) {
            DialogProgressBinding binding = DataBindingUtil.inflate(LayoutInflater.from(this.context), R.layout.dialog_progress, null, false);
            binding.setNegativeGrade(this.fragmentNegativeGradeViewModel);
            view = binding.getRoot();
        }
        if(fragmentAdvertiseViewModel != null){
            DialogProgressBinding binding = DataBindingUtil.inflate(LayoutInflater.from(this.context), R.layout.dialog_progress, null, false);
            binding.setAdvertise(fragmentAdvertiseViewModel);
            view = binding.getRoot();
        }
        ButterKnife.bind(this, view);
        DialogProgressTitle.setText(Title);
        DialogProgressIgnor.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                String str = "CancelByUser";
                if (fragmentPoliceFineViewModel != null) {
                    fragmentPoliceFineViewModel.MessageType.onNext(str);
                }
                if (fragmentNegativeGradeViewModel != null) {
                    fragmentNegativeGradeViewModel.MessageType.onNext(str);
                }
                if (fragmentAdvertiseViewModel != null) {
                    fragmentAdvertiseViewModel.MessageType.onNext(str);
                }
            }
        });
        return new Builder(context).setView(view).create();
    }
}
