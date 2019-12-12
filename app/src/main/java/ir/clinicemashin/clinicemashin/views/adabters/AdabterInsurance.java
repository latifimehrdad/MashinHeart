package ir.clinicemashin.clinicemashin.views.adabters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import ir.clinicemashin.clinicemashin.R;
import ir.clinicemashin.clinicemashin.databases.DataBaseInsurance;
import ir.clinicemashin.clinicemashin.databinding.ItemInsuranceBinding;
import ir.clinicemashin.clinicemashin.views.fragments.FragmentInsurance;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;

public class AdabterInsurance extends RecyclerView.Adapter<AdabterInsurance.CustomHolder> {

    private RealmResults<DataBaseInsurance> dataBaseInsurancesArray;
    private FragmentInsurance fragmentInsurance;
    private LayoutInflater layoutInflater;

    public AdabterInsurance(FragmentInsurance fragmentInsurance, RealmResults<DataBaseInsurance> dataBaseInsurancesArray) {
        this.dataBaseInsurancesArray = dataBaseInsurancesArray;
        this.fragmentInsurance = fragmentInsurance;
    }

    public CustomHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        return new CustomHolder(DataBindingUtil.inflate(layoutInflater, R.layout.item_insurance, parent, false));
    }

    public void onBindViewHolder(CustomHolder holder, int position) {
        holder.bind(dataBaseInsurancesArray.get(position), position);
    }

    public int getItemCount() {
        return dataBaseInsurancesArray.size();
    }


    public class CustomHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ItemRepairDelete)
        ImageView ItemRepairDelete;
        @BindView(R.id.ItemRepairEdit)
        ImageView ItemRepairEdit;
        ItemInsuranceBinding itemInsuranceBinding;

        public CustomHolder(ItemInsuranceBinding itemInsuranceBinding2) {
            super(itemInsuranceBinding2.getRoot());
            this.itemInsuranceBinding = itemInsuranceBinding2;
            ButterKnife.bind(this, this.itemInsuranceBinding.getRoot());
        }

        public void bind(DataBaseInsurance dataBaseInsurance, final int Position) {
            itemInsuranceBinding.setInsurance(dataBaseInsurance);
            itemInsuranceBinding.getRoot().setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    fragmentInsurance.ItemClick(Position);
                }
            });
            this.ItemRepairEdit.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    fragmentInsurance.ItemClickEdit(Position);
                }
            });
            this.ItemRepairDelete.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    fragmentInsurance.ItemClickDelete(Position);
                }
            });
            itemInsuranceBinding.executePendingBindings();
        }
    }
}
