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

    private Integer CarId;
    private Byte ClickLayout;
    private Context context;
    private FragmentManager fm;
    private FragmentCarEventViewModel fragmentCarEventViewModel;
    private FragmentTransaction ft;
    public PublishSubject<String> MessageType = PublishSubject.create();
    private View view;

    @BindView(R.id.FragmentCarEventConsumables)
    LinearLayout FragmentCarEventConsumables;
    @BindView(R.id.FragmentCarEventFragment)
    FrameLayout FragmentCarEventFragment;
    @BindView(R.id.FragmentCarEventInsurance)
    LinearLayout FragmentCarEventInsurance;
    @BindView(R.id.FragmentCarEventRepair)
    LinearLayout FragmentCarEventRepair;
    @BindView(R.id.FragmentCarEventSale)
    LinearLayout FragmentCarEventSale;


    public FragmentCarEvent(Integer carId, Context context) {//_____________________________________ Start FragmentCarEvent
        this.CarId = carId;
        this.context = context;
    }//_____________________________________________________________________________________________ End FragmentCarEvent


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentCarEventBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_car_event, container, false);
        fragmentCarEventViewModel = new FragmentCarEventViewModel(context);
        binding.setEvent(fragmentCarEventViewModel);
        view = binding.getRoot();
        ButterKnife.bind(this, view);
        ClickLayout = 0;
        FragmentClickControler();
        return view;
    }//_____________________________________________________________________________________________ End onCreateView


    public void onStart() {//_______________________________________________________________________ Start onStart
        super.onStart();
        SetAnimation();
        MessageControler();
        FragmentCarEventFragment.setVisibility(View.INVISIBLE);
    }//_____________________________________________________________________________________________ End onStart


    private void FragmentClickControler() {//_____________________________________ Start FragmentCarEvent

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyCode != 4) {
                    return true;
                }
                if (ClickLayout == 0) {
                    MainActivity.FragmentMessage.onNext("CommitAdd");
                } else if (ClickLayout == 2) {
                    MessageType.onNext("close");
                } else {
                    ClickLayout = 0;
                }
                return true;
            }
        });

        FragmentCarEventConsumables.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                fm = null;
                ft = null;
                fm = getFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.FragmentCarEventFragment, new FragmentConsumable(context, FragmentCarEvent.this));
                ft.commit();
                ClickLayout = 2;
                SetAnimationFrame();
            }
        });


        FragmentCarEventRepair.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                fm = null;
                ft = null;
                fm = getFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.FragmentCarEventFragment, new FragmentRepair(context,FragmentCarEvent.this));
                ft.commit();
                ClickLayout = 2;
                SetAnimationFrame();
            }
        });


        FragmentCarEventInsurance.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                fm = null;
                ft = null;
                fm = getFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.FragmentCarEventFragment, new FragmentInsurance(context,FragmentCarEvent.this));
                ft.commit();
                ClickLayout = 2;
                SetAnimationFrame();
            }
        });
    }//_____________________________________________________________________________________________ End onCreateView


    private void SetAnimation() {//_________________________________________________________________ Start SetAnimation

        Animation LefttoRight = AnimationUtils.loadAnimation(context, R.anim.left_to_right);
        Animation RighttoLeft = AnimationUtils.loadAnimation(context, R.anim.right_to_left);
        FragmentCarEventConsumables.setAnimation(LefttoRight);
        FragmentCarEventSale.setAnimation(LefttoRight);
        FragmentCarEventRepair.setAnimation(RighttoLeft);
        FragmentCarEventInsurance.setAnimation(RighttoLeft);
    }//_____________________________________________________________________________________________ End SetAnimation


    private void SetAnimationFrame() {//____________________________________________________________ Start SetAnimationFrame
        FragmentCarEventFragment.clearAnimation();
        Animation BottomToTop = AnimationUtils.loadAnimation(context, R.anim.bottom_to_top);
        FragmentCarEventFragment.setVisibility(View.VISIBLE);
        FragmentCarEventFragment.setAnimation(BottomToTop);
    }//_____________________________________________________________________________________________ End SetAnimationFrame


    private void FrameClose() {//___________________________________________________________________ Start FrameClose
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        FragmentCarEventFragment.clearAnimation();
        FragmentCarEventFragment.setVisibility(View.INVISIBLE);
        ClickLayout = 1;
    }//_____________________________________________________________________________________________ End FrameClose


    public void onDestroyView() {//___________________________________________________________________ Start onDestroyView
        super.onDestroyView();
        try {
            if (FragmentCarEventFragment.getChildCount() > 0) {
                getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.FragmentCarEventFragment)).commit();
            }
        } catch (Exception e) {
        }
    }//_____________________________________________________________________________________________ End onDestroyView



    private void MessageControler() {//_____________________________________________________________ Start MessageControler

                MessageType
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        getActivity()
                                .runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        FrameClose();
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


}
