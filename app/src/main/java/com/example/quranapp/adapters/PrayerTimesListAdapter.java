package com.example.quranapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.quranapp.R;
import com.example.quranapp.pojo.prayertimes.PrayerTiming;

import java.util.ArrayList;

public class PrayerTimesListAdapter extends RecyclerView.Adapter<PrayerTimesListAdapter.ViewHolder> {


    private ArrayList<PrayerTiming> timings;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_prayer, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(timings.get(position));
    }

    @Override
    public int getItemCount() {
        return timings == null ? 0 : timings.size();
    }

    public void setTimings(ArrayList<PrayerTiming> timings) {
        this.timings = timings;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView prayerName, prayerTime;
        ImageView prayerImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            prayerName = itemView.findViewById(R.id.prayer_name);
            prayerTime = itemView.findViewById(R.id.prayer_time);
            prayerImage = itemView.findViewById(R.id.img_prayer);
        }
        public void bind(PrayerTiming timing) {
            prayerName.setText(timing.getPrayerName());
            // the time format is show as 4:30 (EET) and i will cut or split (EET) for only displaying 4:30
            prayerTime.setText(timing.getPrayerTime().split(" ")[0]);
            prayerImage.setImageResource(timing.getPrayerImage());
        }
    }
}