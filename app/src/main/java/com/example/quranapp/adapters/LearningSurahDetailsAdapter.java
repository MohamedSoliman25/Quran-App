package com.example.quranapp.adapters;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.quranapp.R;
import com.example.quranapp.pojo.learningquranmodel.Ayah;

import java.util.ArrayList;
import java.util.List;

public class LearningSurahDetailsAdapter extends RecyclerView.Adapter<LearningSurahDetailsAdapter.SurahDetailsViewHolder> {

    private static final String TAG = "LearningSurahDetailsAdapter";
    private List<Ayah> ayahList = new ArrayList<>();
    Context context;
    public static MediaPlayer mediaPlayer;
    private int currentPlayingPosition;
    private SeekBarUpdater seekBarUpdater;
     SurahDetailsViewHolder surahDetailsViewHolder;


    public LearningSurahDetailsAdapter(Context context) {
        this.context = context;
        this.currentPlayingPosition = -1;
        seekBarUpdater = new SeekBarUpdater();
    }

    public void setAyahList(List<Ayah> ayahList) {
        this.ayahList = ayahList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SurahDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SurahDetailsViewHolder(LayoutInflater.from(context).inflate(R.layout.learning_surah_details_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SurahDetailsViewHolder holder, int position) {

        holder.tvAyahNumber.setText(String.valueOf(ayahList.get(position).getNumberInSurah()));
        holder.tvAyahBody.setText(ayahList.get(position).getText());

        if (position == currentPlayingPosition) {
            surahDetailsViewHolder = holder;

         updatePlayingView();
        } else {
          updateNonPlayingView(holder);
        }
    }

    public void updatePlayingView() {
        surahDetailsViewHolder.sbMyAudio.setMax(mediaPlayer.getDuration());
        surahDetailsViewHolder.sbMyAudio.setProgress(mediaPlayer.getCurrentPosition());
        surahDetailsViewHolder.sbMyAudio.setEnabled(true);
        if (mediaPlayer.isPlaying()) {
            surahDetailsViewHolder.sbMyAudio.postDelayed(seekBarUpdater, 100);
            surahDetailsViewHolder.imgPlayAudio.setImageResource(R.drawable.ic_baseline_pause_circle);
        } else {
            surahDetailsViewHolder.sbMyAudio.removeCallbacks(seekBarUpdater);
            surahDetailsViewHolder.imgPlayAudio.setImageResource(R.drawable.ic_baseline_play_circle);
        }
    }


        private void updateNonPlayingView(SurahDetailsViewHolder holder) {
            holder.sbMyAudio.removeCallbacks(seekBarUpdater);
            holder.sbMyAudio.setEnabled(false);
            holder.sbMyAudio.setProgress(0);
            holder.imgPlayAudio.setImageResource(R.drawable.ic_baseline_play_circle);
    }

    @Override
    public int getItemCount() {
        return ayahList.size();
    }

    public class SurahDetailsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

        TextView tvAyahNumber,tvAyahBody;
      public  SeekBar sbMyAudio;
      public  ImageView imgPlayAudio;
        public SurahDetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAyahNumber = itemView.findViewById(R.id.tv_aya_number);
            tvAyahBody = itemView.findViewById(R.id.tv_aya_body);

            imgPlayAudio =  itemView.findViewById(R.id.ivPlayPause);
            sbMyAudio = itemView.findViewById(R.id.sbProgress);

            imgPlayAudio.setOnClickListener(this);
            sbMyAudio.setOnSeekBarChangeListener(this);
        }

//        currentPlayingPosition = getAdapterPosition();
//          if (mediaPlayer != null) {
//            if (seekBarUpdater.playingHolder!=null) {
//                seekBarUpdater.playingHolder.sbMyAudio.removeCallbacks(seekBarUpdater);
//                seekBarUpdater.playingHolder.sbMyAudio.setProgress(0);
//                Log.d(TAG, "onClickss: ");
//            }
//            mediaPlayer.release();
//        }
//
//        seekBarUpdater.playingHolder = this;
//        startMediaPlayer();
//            sbMyAudio.setMax(mediaPlayer.getDuration());
//            sbMyAudio.post(seekBarUpdater);

        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.sbProgress:
                    break;

                case R.id.ivPlayPause: {
                    if (getAdapterPosition() == currentPlayingPosition) {
                        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                            mediaPlayer.pause();
                        } else {
                            if (mediaPlayer != null)
                                mediaPlayer.start();
                        }
                    } else {
                        currentPlayingPosition = getAdapterPosition();
                        if (mediaPlayer != null) {
                            if (surahDetailsViewHolder!=null) {
                                updateNonPlayingView(surahDetailsViewHolder);
                            }
                            mediaPlayer.release();
                        }
                        surahDetailsViewHolder = this;

                        PlaySound(ayahList.get(currentPlayingPosition).getAudio());
                    }
                    if (mediaPlayer != null)
                        updatePlayingView();
                }
                break;
            }



        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            if (fromUser) {
                mediaPlayer.seekTo(progress);
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }

    private void PlaySound(String  sound) {

        mediaPlayer = MediaPlayer.create(context, Uri.parse(sound));

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                releaseMediaPlayer();
            }
        });
        mediaPlayer.start();
    }

    private void releaseMediaPlayer() {
        if (surahDetailsViewHolder!=null) {
            updateNonPlayingView(surahDetailsViewHolder);
        }

        mediaPlayer.release();
        mediaPlayer = null;
        currentPlayingPosition = -1;
    }




    class SeekBarUpdater implements Runnable {
        @Override
        public void run() {
            if (surahDetailsViewHolder!=null && mediaPlayer!=null) {
                surahDetailsViewHolder.sbMyAudio.setProgress(mediaPlayer.getCurrentPosition());
                surahDetailsViewHolder.sbMyAudio.postDelayed(this, 100);
            }
        }
    }
    //for resuming
    public interface OnSurahDetailsViewHolder{
        void getSurahDetailsViewHolder(SurahDetailsViewHolder surahDetailsViewHolder);
    }
}
