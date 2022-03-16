package com.example.quranapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quranapp.OnItemSurahNumber;
import com.example.quranapp.R;
import com.example.quranapp.pojo.learningquranmodel.Quran;

import java.util.ArrayList;
import java.util.List;

public class LearningQuranAdapter extends RecyclerView.Adapter<LearningQuranAdapter.QuranViewHolder> {

    private List<Quran> quranList = new ArrayList<>();
    Context context;
    OnItemSurahNumber onItemSurahNumber;

    public LearningQuranAdapter(Context context, OnItemSurahNumber onItemSurahNumber) {
        this.context = context;
        this.onItemSurahNumber = onItemSurahNumber;
    }

    public void setQuranList(List<Quran> quranList) {
        this.quranList = quranList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public QuranViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new QuranViewHolder(LayoutInflater.from(context).inflate(R.layout.learning_all_surahs_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull QuranViewHolder holder, int position) {

        holder.tvNumber.setText(String.valueOf(quranList.get(position).getNumber()));
        holder.tvName.setText(quranList.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemSurahNumber.getItemSurahNumber(quranList.get(position).getNumber());
            }
        });
    }

    @Override
    public int getItemCount() {
        return quranList.size();
    }

    public class QuranViewHolder extends RecyclerView.ViewHolder {
        TextView tvNumber,tvName;
        public QuranViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNumber = itemView.findViewById(R.id.tv_surahs_number);
            tvName  = itemView.findViewById(R.id.tv_surahs_name);
        }
    }
}
