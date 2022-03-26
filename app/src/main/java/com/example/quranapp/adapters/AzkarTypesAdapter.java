package com.example.quranapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quranapp.R;
import com.example.quranapp.fragments.AzkarHomeFragmentDirections;
import com.example.quranapp.pojo.azkar.ZekrType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class AzkarTypesAdapter extends RecyclerView.Adapter<AzkarTypesAdapter.ViewHolder> implements Filterable {

   private ArrayList<ZekrType> azkarTypes;
   private Fragment fragment;
    List<ZekrType> filteredList = new ArrayList<>();


    public AzkarTypesAdapter(Fragment fragment) {
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_azkar_type, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(azkarTypes.get(position));
    }

    @Override
    public int getItemCount() {
        return azkarTypes == null ? 0 : azkarTypes.size();
    }

    public void setAzkarTypes(HashSet<ZekrType> azkarTypes) {
        this.azkarTypes = new ArrayList<>(azkarTypes);
        filteredList.clear();
        filteredList.addAll(azkarTypes);
        notifyDataSetChanged();
    }

    //this method for adding or assigning filteredList to original list for displaying data which user searched about it
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<ZekrType> filterListTemp = new ArrayList<>();

                constraint = constraint.toString().trim();
                azkarTypes.clear();
                if (constraint==null ||constraint.length()==0){
                    filterListTemp.addAll(filteredList);
                }
                else {
                    for (ZekrType zekrType : filteredList){
                        if (zekrType.getZekrName().contains(constraint)){
                            filterListTemp.add(zekrType);
                        }
                    }
                }
                FilterResults results = new FilterResults();
                results.values = filterListTemp;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                azkarTypes.clear();
                azkarTypes.addAll((Collection<? extends ZekrType>) results.values);
                notifyDataSetChanged();
            }
        };
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {

        TextView zekrName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            zekrName = itemView.findViewById(R.id.zekr_name);
        }

        public void bind(ZekrType zekrType) {
            zekrName.setText(zekrType.getZekrName());
            // when user select zekr i will pass zekr name to AzkarListFragment using safeArgs for opening zekr details
            itemView.setOnClickListener(v-> NavHostFragment.findNavController(fragment).navigate(AzkarHomeFragmentDirections.actionAzkarHomeFragmentToAzkarListFragment2(zekrType.getZekrName())));

        }
    }

}
