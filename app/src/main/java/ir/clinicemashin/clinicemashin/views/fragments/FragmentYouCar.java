package ir.clinicemashin.clinicemashin.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ir.clinicemashin.clinicemashin.R;
import ir.clinicemashin.clinicemashin.databases.DataBaseCars;
import ir.clinicemashin.clinicemashin.databinding.FragmentYouCarBinding;
import ir.clinicemashin.clinicemashin.viewmodels.FragmentYouCarViewModel;
import ir.clinicemashin.clinicemashin.views.activitys.MainActivity;
import ir.clinicemashin.clinicemashin.views.adabters.AdabterYouCar;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;

public class FragmentYouCar extends Fragment {

    private AdabterYouCar adabterYouCar;
    private Context context;
    private RealmResults<DataBaseCars> dataBaseCars;
    private FragmentYouCarViewModel fragmentYouCarViewModel;
    private NavController navController;
    private View view;

    @BindView(R.id.FragmentYouCarEmpty)
    LinearLayout FragmentYouCarEmpty;
    @BindView(R.id.FragmentYouCarRecyclerList)
    RecyclerView FragmentYouCarRecyclerList;


    public FragmentYouCar() {//_____________________________________________________________________ Start FragmentYouCar

    }//_____________________________________________________________________________________________ End FragmentYouCar


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = getContext();
        FragmentYouCarBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_you_car, container, false);
        fragmentYouCarViewModel = new FragmentYouCarViewModel(context);
        binding.setYoucar(fragmentYouCarViewModel);
        view = binding.getRoot();
        ButterKnife.bind(this, view);
        return view;
    }//_____________________________________________________________________________________________ End onCreateView


    public void onStart() {//_______________________________________________________________________ Start onStart
        super.onStart();
        navController = Navigation.findNavController(view);
        GetCarFromDB();
        MainActivity.ChooseCar.onNext(-1);
    }//_____________________________________________________________________________________________ End onStart



    private void GetCarFromDB() {//_________________________________________________________________ Start GetCarFromDB
        dataBaseCars = fragmentYouCarViewModel.GetCars();
        if (dataBaseCars.size() == 0) {
            FragmentYouCarEmpty.setVisibility(View.VISIBLE);
            return;
        }
        FragmentYouCarEmpty.setVisibility(View.GONE);
        SetAdabter();
    }//_____________________________________________________________________________________________ End GetCarFromDB



    private void SetAdabter() {//___________________________________________________________________ Start SetAdabter
        adabterYouCar = new AdabterYouCar(dataBaseCars, this);
        FragmentYouCarRecyclerList.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
        FragmentYouCarRecyclerList.setAdapter(adabterYouCar);
    }//_____________________________________________________________________________________________ End SetAdabter



    public void ItemClick(int position) {//_________________________________________________________ Start ItemClick
        MainActivity.CarId = (dataBaseCars.get(position)).getID();
        MainActivity.ChooseCar.onNext((dataBaseCars.get(position)).getCarBrand());
        navController.navigate(R.id.action_fragmentYouCar_to_fragmentCarEvent);
    }//_____________________________________________________________________________________________ End ItemClick


}
