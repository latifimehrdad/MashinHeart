package com.androidha.mashinheart.views.adabters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.androidha.mashinheart.R;
import com.androidha.mashinheart.databases.DataBaseCars;
import com.androidha.mashinheart.databinding.ItemsYouCarBinding;
import com.androidha.mashinheart.views.fragments.FragmentYouCar;

import io.realm.RealmResults;

public class AdabterYouCar extends RecyclerView.Adapter<AdabterYouCar.CustomHolder> {

    private RealmResults<DataBaseCars> dataBaseCarsArray;
    private FragmentYouCar fragmentYouCar;
    private LayoutInflater layoutInflater;



    public AdabterYouCar(RealmResults<DataBaseCars> dataBaseCarsArray2, FragmentYouCar fragmentYouCar2) {
        this.dataBaseCarsArray = dataBaseCarsArray2;
        this.fragmentYouCar = fragmentYouCar2;
    }

    public CustomHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        return new CustomHolder(DataBindingUtil.inflate(layoutInflater, R.layout.items_you_car, parent, false));
    }

    public void onBindViewHolder(CustomHolder holder, int position) {
        holder.bind(dataBaseCarsArray.get(position), position);
    }

    public int getItemCount() {
        return dataBaseCarsArray.size();
    }


    public class CustomHolder extends RecyclerView.ViewHolder {
        ItemsYouCarBinding itemsYouCarBinding;

        public CustomHolder(ItemsYouCarBinding itemsYouCarBinding2) {
            super(itemsYouCarBinding2.getRoot());
            this.itemsYouCarBinding = itemsYouCarBinding2;
        }

        public void bind(DataBaseCars dataBaseCars, final int positon) {
            itemsYouCarBinding.setCars(dataBaseCars);
            itemsYouCarBinding.getRoot().setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    fragmentYouCar.ItemClick(positon);
                }
            });
            itemsYouCarBinding.executePendingBindings();
        }
    }
}
