package com.example.quranapp.adapters;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quranapp.R;
import com.example.quranapp.pojo.azkar.ZekrType;
import com.example.quranapp.pojo.prayertimes.City;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.ViewHolder> implements Filterable {


    private List<City> cities = new ArrayList<>();
    private final OnClick onClick;
     private List<City> filteredList = new ArrayList<>();


    public CitiesAdapter( OnClick onClick) {
        this.onClick = onClick;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
        filteredList.clear();
        filteredList.addAll(cities);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_city, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(cities.get(position));
    }

    @Override
    public int getItemCount() {
        return cities == null ? 0 : cities.size();
    }



    // another way for search using collection
//    public void filter(String s) {
//        if (s == null || s.isEmpty()) {
//            setCities(originalCities);
//        } else {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                setCities(originalCities
//                        .stream()
//                        .filter(city -> city.getCountry().contains(s) || city.getName().contains(s))
//                        .collect(Collectors.toCollection(ArrayList::new)));
//            }
//        }
//    }

    //this method for adding or assigning filteredList to original list for displaying data which user searched about it
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<City> filterListTemp = new ArrayList<>();

                constraint = constraint.toString().trim();
                cities.clear();
                if (constraint==null ||constraint.length()==0){
                    filterListTemp.addAll(filteredList);
                }
                else {
                    for (City city : filteredList){
                        if (city.getArabicName().contains(constraint)){
                            filterListTemp.add(city);
                        }
                    }
                }
                FilterResults results = new FilterResults();
                results.values = filterListTemp;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                cities.clear();
                cities.addAll((Collection<? extends City>) results.values);
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView cityArabicText;
       // private TextView countryCodeText;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cityArabicText = itemView.findViewById(R.id.city);
            //countryCodeText = itemView.findViewById(R.id.country_code);
        }

        public void bind(City city) {
            cityArabicText.setText(city.getArabicName());
          //  countryCodeText.setText(city.getCountry());
            itemView.setOnClickListener((v) -> onClick.onClick(city));
        }
    }

    public interface OnClick {
        void onClick(City city);
    }

}
