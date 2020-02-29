package ir.clinicemashin.clinicemashin.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import ir.clinicemashin.clinicemashin.R;
import ir.clinicemashin.clinicemashin.databinding.FragmentCarEventBinding;
import ir.clinicemashin.clinicemashin.viewmodels.FragmentCarEventViewModel;
import ir.clinicemashin.clinicemashin.views.activitys.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class FragmentCarEvent extends Fragment {

    private Context context;
    private FragmentCarEventViewModel fragmentCarEventViewModel;
    private View view;
    private NavController navController;

    @BindView(R.id.FragmentCarEventConsumables)
    LinearLayout FragmentCarEventConsumables;
    @BindView(R.id.FragmentCarEventInsurance)
    LinearLayout FragmentCarEventInsurance;
    @BindView(R.id.FragmentCarEventRepair)
    LinearLayout FragmentCarEventRepair;
    @BindView(R.id.FragmentCarEventSale)
    LinearLayout FragmentCarEventSale;


    public FragmentCarEvent() {//___________________________________________________________________ Start FragmentCarEvent

    }//_____________________________________________________________________________________________ End FragmentCarEvent


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = getActivity();
        FragmentCarEventBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_car_event, container, false);
        fragmentCarEventViewModel = new FragmentCarEventViewModel(context);
        binding.setEvent(fragmentCarEventViewModel);
        view = binding.getRoot();
        ButterKnife.bind(this, view);
        FragmentClickControler();
        return view;
    }//_____________________________________________________________________________________________ End onCreateView


    public void onStart() {//_______________________________________________________________________ Start onStart
        super.onStart();
        navController = Navigation.findNavController(view);
    }//_____________________________________________________________________________________________ End onStart


    private void FragmentClickControler() {//_____________________________________ Start FragmentCarEvent

        FragmentCarEventConsumables.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                navController.navigate(R.id.action_fragmentCarEvent_to_fragmentConsumable);
            }
        });


        FragmentCarEventRepair.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                navController.navigate(R.id.action_fragmentCarEvent_to_fragmentRepair);
            }
        });


        FragmentCarEventInsurance.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                navController.navigate(R.id.action_fragmentCarEvent_to_fragmentInsurance);
            }
        });
    }//_____________________________________________________________________________________________ End onCreateView

}

