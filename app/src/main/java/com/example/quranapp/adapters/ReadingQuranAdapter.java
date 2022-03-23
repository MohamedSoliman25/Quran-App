package com.example.quranapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


import com.example.quranapp.R;
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
        TextView soraNumber, soraName, from, to, wordTo,wordFrom;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            soraNumber = itemView.findViewById(R.id.sora_number);
            soraName = itemView.findViewById(R.id.sora_name);
            from = itemView.findViewById(R.id.sora_start);
            to = itemView.findViewById(R.id.sora_end);
            wordFrom = itemView.findViewById(R.id.word_from);
            wordTo = itemView.findViewById(R.id.word_to);
        }

        public void bind(Aya aya) {
            NumberFormat nf= NumberFormat.getInstance(new Locale("ar","EG"));

            wordTo.setVisibility(View.VISIBLE);

            soraNumber.setText((nf.format(aya.getSora())+"-"));
            soraName.setText(aya.getSora_name_ar());
//            from.setText(nf.format(sora.getStartPage()));
//            to.setText(nf.format(sora.getEndPage()));
//            itemView.setOnClickListener(v -> NavHostFragment
//                    .findNavController(fragment)
//                    .navigate(QuranIndexFragmentDirections.actionQuranIndexToQuranFragment(sora.getStartPage())));
        }

        public void bind(Jozz jozz) {
            NumberFormat nf= NumberFormat.getInstance(new Locale("ar","EG"));

            soraName.setText((fragment.getString(R.string.jozz)+": "+(nf.format(jozz.getJozzNumber()))));
            wordTo.setVisibility(View.VISIBLE);

            soraNumber.setText("");
            from.setText(nf.format(jozz.getStartPage()));
            to.setText(nf.format(jozz.getEndPage()));

//            itemView.setOnClickListener(v -> NavHostFragment
//                    .findNavController(fragment)
//                    .navigate(QuranIndexFragmentDirections.actionQuranIndexToQuranFragment(jozz.getStartPage())));
        }

        public void bind(Integer page) {
            NumberFormat nf= NumberFormat.getInstance(new Locale("ar","EG"));

            soraName.setText((fragment.getString(R.string.page)+" : "+(nf.format(page))));
            from.setVisibility(View.GONE);
            to.setVisibility(View.GONE);
            wordTo.setVisibility(View.GONE);
            wordFrom.setVisibility(View.GONE);
//
//            soraNumber.setText("");
//
//            itemView.setOnClickListener(v -> NavHostFragment
//                    .findNavController(fragment)
//                    .navigate(QuranIndexFragmentDirections.actionQuranIndexToQuranFragment(page)));
        }

    }
}
