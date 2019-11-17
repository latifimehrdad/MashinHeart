package com.androidha.mashinheart.views.adabters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.androidha.mashinheart.R;
import com.androidha.mashinheart.databinding.ItemAdvertiseBinding;
import com.androidha.mashinheart.models.ModelAdvertiseList;

import java.util.ArrayList;

public class AdabterAdvertise extends RecyclerView.Adapter<AdabterAdvertise.CustomHolder> {

    private Context context;
    private ArrayList<ModelAdvertiseList> modelAdvertiseLists;
    private LayoutInflater layoutInflater;

    public AdabterAdvertise(Context context, ArrayList<ModelAdvertiseList> modelAdvertiseLists) {
        this.context = context;
        this.modelAdvertiseLists = modelAdvertiseLists;
    }

    @NonNull
    @Override
    public CustomHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());

        ItemAdvertiseBinding binding = DataBindingUtil.inflate(
           layoutInflater, R.layout.item_advertise,parent,false
        );
        return new CustomHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomHolder holder, int position) {
        holder.bind(modelAdvertiseLists.get(position));
    }

    @Override
    public int getItemCount() {
        return modelAdvertiseLists.size();
    }

    public class CustomHolder extends RecyclerView.ViewHolder{

        ItemAdvertiseBinding itemAdvertiseBinding;
        public CustomHolder(ItemAdvertiseBinding itemAdvertiseBinding) {
            super(itemAdvertiseBinding.getRoot());
            this.itemAdvertiseBinding = itemAdvertiseBinding;
        }

        public void bind(ModelAdvertiseList list){
            itemAdvertiseBinding.setAdvertise(list);
            itemAdvertiseBinding.executePendingBindings();
        }
    }
}
