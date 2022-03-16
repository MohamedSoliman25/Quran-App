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
import com.example.quranapp.fragments.QuranSearchFragmentDirections;
import com.example.quranapp.readingquranmodel.Aya;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class QuranSearchAdapter extends RecyclerView.Adapter<QuranSearchAdapter.ViewHolder> {

   private ArrayList<Aya> ayat;
   private Fragment fragment;

    public QuranSearchAdapter(Fragment fragment) {
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_quran_search,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.bind(ayat.get(position));
    }

    @Override
    public int getItemCount() {
        return ayat==null ? 0 : ayat.size();
    }

    public void setAyat(ArrayList<Aya> ayat) {
        this.ayat = ayat;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView ayaNo,soraNo,soraName,ayaContent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            soraNo = itemView.findViewById(R.id.sora_no);
            ayaNo = itemView.findViewById(R.id.aya_no);
            soraName = itemView.findViewById(R.id.sora_name);
            ayaContent = itemView.findViewById(R.id.aya_content);
        }

        public void bind(Aya aya) {
            NumberFormat nf= NumberFormat.getInstance(new Locale("ar","EG"));

            soraNo.setText(nf.format(aya.getSora()));
            ayaNo.setText(nf.format(aya.getAya_no()));
            soraName.setText(aya.getSora_name_ar());
            ayaContent.setText(aya.getAya_text());
            itemView.setOnClickListener(v->{
                NavHostFragment.findNavController(fragment).navigate(QuranSearchFragmentDirections.actionQuranSearchFragmentToQuranFragment(aya.getPage()));
            });
        }
    }
}
