package com.androidha.mashinheart.views.adabters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.androidha.mashinheart.R;
import com.androidha.mashinheart.databases.DataBaseRepair;
import com.androidha.mashinheart.databinding.ItemRepairBinding;
import com.androidha.mashinheart.views.fragments.FragmentRepair;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;

public class AdabterRepair extends RecyclerView.Adapter<AdabterRepair.CustomHolder> {

    private RealmResults<DataBaseRepair> dataBaseRepairsArray;
    private FragmentRepair fragmentRepair;
    private LayoutInflater layoutInflater;

    public AdabterRepair(FragmentRepair fragmentRepair, RealmResults<DataBaseRepair> dataBaseRepairsArray) {
        this.dataBaseRepairsArray = dataBaseRepairsArray;
        this.fragmentRepair = fragmentRepair;
    }



    public CustomHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        return new CustomHolder(DataBindingUtil.inflate(layoutInflater, R.layout.item_repair, parent, false));
    }

    public void onBindViewHolder(CustomHolder holder, int position) {
        holder.bind(dataBaseRepairsArray.get(position), position);
    }

    public int getItemCount() {
        return dataBaseRepairsArray.size();
    }


    public class CustomHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ItemRepairDelete)
        ImageView ItemRepairDelete;
        @BindView(R.id.ItemRepairEdit)
        ImageView ItemRepairEdit;
        ItemRepairBinding itemRepairBinding;

        public CustomHolder(ItemRepairBinding itemRepairBinding2) {
            super(itemRepairBinding2.getRoot());
            this.itemRepairBinding = itemRepairBinding2;
            ButterKnife.bind((Object) this, this.itemRepairBinding.getRoot());
        }

        public void bind(DataBaseRepair dataBaseRepair, final int Position) {
            itemRepairBinding.setRepair(dataBaseRepair);
            itemRepairBinding.getRoot().setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    fragmentRepair.ItemClick(Position);
                }
            });
            this.ItemRepairEdit.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    fragmentRepair.ItemClickEdit(Position);
                }
            });
            this.ItemRepairDelete.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    fragmentRepair.ItemClickDelete(Position);
                }
            });
            itemRepairBinding.executePendingBindings();
        }
    }
}
