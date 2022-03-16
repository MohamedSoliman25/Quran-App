package com.example.quranapp.adapters;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quranapp.R;
import com.example.quranapp.pojo.prayertimes.City;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.ViewHolder> {


    private List<City> cities;
    private final List<City> originalCities;
    private final OnClick onClick;

    public CitiesAdapter(List<City> cities, OnClick onClick) {
        this.cities = cities;
        this.onClick = onClick;
        originalCities = new ArrayList<>(cities);
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

    public void setCities(List<City> cities) {
        this.cities = cities;
        notifyDataSetChanged();
    }

    // i added Build.VERSION_CODES.N condition
    public void filter(String s) {
        if (s == null || s.isEmpty()) {
            setCities(originalCities);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                setCities(originalCities
                        .stream()
                        .filter(city -> city.getCountry().contains(s) || city.getName().contains(s))
                        .collect(Collectors.toCollection(ArrayList::new)));
            }
        }
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
