package ir.clinicemashin.clinicemashin.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;
import ir.clinicemashin.clinicemashin.R;
import ir.clinicemashin.clinicemashin.databases.DataBasePositions;
import ir.clinicemashin.clinicemashin.databinding.FragmentPositionBinding;
import ir.clinicemashin.clinicemashin.viewmodels.FragmentPositionViewModel;
import ir.clinicemashin.clinicemashin.views.adabters.AdabterPosition;


public class FragmentPosition extends Fragment {

    private Context context;
    private FragmentPositionViewModel fragmentPositionViewModel;
    private View view;
    private RealmResults<DataBasePositions> positions;
    private AdabterPosition adabterPosition;
    private NavController navController;


    @BindView(R.id.FragmentPositionRecyclerList)
    RecyclerView FragmentPositionRecyclerList;

    @BindView(R.id.FragmentAddLocation)
    Button FragmentAddLocation;


    public FragmentPosition() {//___________________________________________________________________ Start FragmentPosition
    }//_____________________________________________________________________________________________ End FragmentPosition



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = getContext();
        FragmentPositionBinding binding =
                DataBindingUtil.inflate(
                        inflater,
                        R.layout.fragment_position,
                        container,
                        false);
        fragmentPositionViewModel = new FragmentPositionViewModel(context);
        binding.setPosition(fragmentPositionViewModel);
        view = binding.getRoot();
        ButterKnife.bind(this, view);
        return view;
    }//_____________________________________________________________________________________________ End onCreateView


    public void onStart() {//_______________________________________________________________________ Start onStart
        super.onStart();
        navController = Navigation.findNavController(view);
        GetPositionFromDB();

        FragmentAddLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_fragmentPosition_to_mapFragment);
            }
        });


    }//_____________________________________________________________________________________________ End onStart



    private void GetPositionFromDB() {//____________________________________________________________ Start GetPositionFromDB
        positions = fragmentPositionViewModel.GetPositions();
        if (positions.size() == 0) {
            return;
        }
        SetAdabter();
    }//_____________________________________________________________________________________________ End GetPositionFromDB



    private void SetAdabter() {//___________________________________________________________________ Start SetAdabter
        adabterPosition = new AdabterPosition(positions, this);
        FragmentPositionRecyclerList.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
        FragmentPositionRecyclerList.setAdapter(adabterPosition);
    }//_____________________________________________________________________________________________ End SetAdabter



    public void ItemClick(int position) {//_________________________________________________________ Start ItemClick

        Bundle bundle = new Bundle();
        bundle.putDouble("FindLat", positions.get(position).getPositonLat());
        bundle.putDouble("FindLong", positions.get(position).getPositionLong());
        navController.navigate(
                R.id.action_fragmentPosition_to_fragmentFindPosition,
                bundle);
    }//_____________________________________________________________________________________________ End ItemClick


}
