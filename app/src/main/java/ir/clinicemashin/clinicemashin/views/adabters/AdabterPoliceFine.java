package ir.clinicemashin.clinicemashin.views.adabters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import ir.clinicemashin.clinicemashin.R;
import ir.clinicemashin.clinicemashin.databinding.ItemPoliceFineBinding;
import ir.clinicemashin.clinicemashin.models.ModelPoliceFine;

import java.util.ArrayList;

public class AdabterPoliceFine extends RecyclerView.Adapter<AdabterPoliceFine.CustomViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<ModelPoliceFine> modelPoliceFineArrayList;


    public AdabterPoliceFine(ArrayList<ModelPoliceFine> modelPoliceFineArrayList2, Context context2) {
        this.modelPoliceFineArrayList = modelPoliceFineArrayList2;
        this.context = context2;
    }

    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        return new CustomViewHolder(DataBindingUtil.inflate(layoutInflater, R.layout.item_police_fine, parent, false));
    }

    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.bind(modelPoliceFineArrayList.get(position), position);
    }

    public int getItemCount() {
        return modelPoliceFineArrayList.size();
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {
        ItemPoliceFineBinding itemPoliceFineBinding;

        public CustomViewHolder(ItemPoliceFineBinding itemPoliceFineBinding) {
            super(itemPoliceFineBinding.getRoot());
            this.itemPoliceFineBinding = itemPoliceFineBinding;
        }

        public void bind(ModelPoliceFine modelPoliceFine, int Position) {
            itemPoliceFineBinding.setPoliceFines(modelPoliceFine);
            itemPoliceFineBinding.executePendingBindings();
        }
    }
}
