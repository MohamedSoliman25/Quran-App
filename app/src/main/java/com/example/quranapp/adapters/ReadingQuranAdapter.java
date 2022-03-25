package com.example.quranapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;


import com.example.quranapp.R;
import com.example.quranapp.fragments.ReadingQuranFragmentDirections;
import com.example.quranapp.pojo.readingquranmodel.Jozz;
import com.example.quranapp.pojo.readingquranmodel.Aya;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ReadingQuranAdapter extends RecyclerView.Adapter<ReadingQuranAdapter.ViewHolder> {

    List<Aya> ayaList = new ArrayList<>() ;
    Fragment fragment;


    public ReadingQuranAdapter(Fragment fragment) {
        this.fragment = fragment;
    }

    public void setSoraList(List<Aya> ayaList) {
        this.ayaList = ayaList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_sora, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            holder.bind(ayaList.get(position));
    }

    @Override
    public int getItemCount() {
        return ayaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView soraNumber, soraName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            soraNumber = itemView.findViewById(R.id.reading_quran_sora_number);
            soraName = itemView.findViewById(R.id.reading_quran_sora_name);

        }

        public void bind(Aya aya) {
            NumberFormat nf= NumberFormat.getInstance(new Locale("ar","EG"));

            soraNumber.setText((nf.format(aya.getSora())));
            soraName.setText(aya.getSora_name_ar());
            itemView.setOnClickListener(v -> NavHostFragment
                    .findNavController(fragment)
                    .navigate(ReadingQuranFragmentDirections.actionReadingQuranFragmentToReadingQuranDetailsFragment(aya.getSora())));
        }



    }
}
