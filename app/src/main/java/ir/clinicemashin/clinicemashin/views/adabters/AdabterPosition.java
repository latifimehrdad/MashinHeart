package ir.clinicemashin.clinicemashin.views.adabters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import io.realm.RealmResults;
import ir.clinicemashin.clinicemashin.R;
import ir.clinicemashin.clinicemashin.databases.DataBasePositions;
import ir.clinicemashin.clinicemashin.databinding.ItemPositionBinding;
import ir.clinicemashin.clinicemashin.views.fragments.FragmentPosition;

public class AdabterPosition extends RecyclerView.Adapter<AdabterPosition.CustomHolder> {

    private RealmResults<DataBasePositions> positions;
    private FragmentPosition fragmentPosition;
    private LayoutInflater layoutInflater;



    public AdabterPosition(RealmResults<DataBasePositions> positions, FragmentPosition fragmentPosition) {
        this.positions = positions;
        this.fragmentPosition = fragmentPosition;
    }

    public CustomHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        return new CustomHolder(DataBindingUtil.inflate(layoutInflater, R.layout.item_position, parent, false));
    }

    public void onBindViewHolder(CustomHolder holder, int position) {
        holder.bind(positions.get(position), position);
    }

    public int getItemCount() {
        return positions.size();
    }


    public class CustomHolder extends RecyclerView.ViewHolder {
        ItemPositionBinding itemPositionBinding;

        public CustomHolder(ItemPositionBinding itemPositionBinding) {
            super(itemPositionBinding.getRoot());
            this.itemPositionBinding = itemPositionBinding;
        }

        public void bind(DataBasePositions dataBasePositions, final int positon) {
            itemPositionBinding.setPosition(dataBasePositions);
            itemPositionBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    fragmentPosition.ItemClick(positon);
                }
            });
            itemPositionBinding.executePendingBindings();
        }
    }
}
