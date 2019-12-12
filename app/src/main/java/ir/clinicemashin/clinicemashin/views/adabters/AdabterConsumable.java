package ir.clinicemashin.clinicemashin.views.adabters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import ir.clinicemashin.clinicemashin.R;
import ir.clinicemashin.clinicemashin.databases.DataBaseConsumable;
import ir.clinicemashin.clinicemashin.databinding.ItemConsumableBinding;
import ir.clinicemashin.clinicemashin.views.fragments.FragmentConsumable;

import butterknife.BindView;
import butterknife.ButterKnife;

import io.realm.RealmResults;

public class AdabterConsumable extends RecyclerView.Adapter<AdabterConsumable.CustomHolder> {

    private RealmResults<DataBaseConsumable> dataBaseConsumablesArray;
    private FragmentConsumable fragmentConsumable;
    private LayoutInflater layoutInflater;

    public AdabterConsumable(FragmentConsumable fragmentConsumable, RealmResults<DataBaseConsumable> dataBaseConsumablesArray) {
        this.dataBaseConsumablesArray = dataBaseConsumablesArray;
        this.fragmentConsumable = fragmentConsumable;
    }


    public CustomHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        return new CustomHolder(DataBindingUtil.inflate(layoutInflater, R.layout.item_consumable, parent, false));
    }

    public void onBindViewHolder(CustomHolder holder, int position) {
        holder.bind(dataBaseConsumablesArray.get(position), position);
    }

    public int getItemCount() {
        return dataBaseConsumablesArray.size();
    }



    public class CustomHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ItemConsumableDelete)
        ImageView ItemConsumableDelete;
        @BindView(R.id.ItemConsumableEdit)
        ImageView ItemConsumableEdit;
        ItemConsumableBinding itemConsumableBinding;

        public CustomHolder(ItemConsumableBinding itemConsumableBinding2) {
            super(itemConsumableBinding2.getRoot());
            this.itemConsumableBinding = itemConsumableBinding2;
            ButterKnife.bind(this, this.itemConsumableBinding.getRoot());
        }

        public void bind(DataBaseConsumable dataBaseConsumable, final int Position) {

            itemConsumableBinding.setConsumable(dataBaseConsumable);

            itemConsumableBinding.getRoot().setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    fragmentConsumable.ItemClick(Position);
                }
            });

            this.ItemConsumableEdit.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    fragmentConsumable.ItemClickEdit(Position);
                }
            });

            this.ItemConsumableDelete.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    fragmentConsumable.ItemClickDelete(Position);
                }
            });
            itemConsumableBinding.executePendingBindings();
        }
    }
}
