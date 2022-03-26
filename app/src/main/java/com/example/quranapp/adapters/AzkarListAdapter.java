package com.example.quranapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.quranapp.R;
import com.example.quranapp.pojo.azkar.Zekr;

import java.util.ArrayList;

public class AzkarListAdapter extends RecyclerView.Adapter<AzkarListAdapter.ViewHolder>{
    ArrayList<Zekr> azkar;
    Context context;

    public AzkarListAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_zekr_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(azkar.get(position));
    }

    @Override
    public int getItemCount() {
        return azkar == null ? 0 : azkar.size();
    }

    public void setAzkar(ArrayList<Zekr> azkar) {
        this.azkar = azkar;
        notifyDataSetChanged();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView zekrName,zekrDescription,zekrArabicCount;
        ImageView imgShare,imgCopy;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            zekrName = itemView.findViewById(R.id.zekr_item_name);
            zekrDescription = itemView.findViewById(R.id.zekr_item_description);
            zekrArabicCount = itemView.findViewById(R.id.zekr_item_count);
            imgShare = itemView.findViewById(R.id.zekr_item_share);
            imgCopy = itemView.findViewById(R.id.zekr_item_copy);
        }


        public void bind(Zekr azkar) {
            zekrName.setText(azkar.getZekr());
            zekrDescription.setText(azkar.getDescription());
            zekrArabicCount.setText(getArabicZekrCount(Integer.parseInt(azkar.getCount())));
            imgShare.setOnClickListener(v->shareContent(azkar.getZekr()));
            imgCopy.setOnClickListener(v->copyToClipboard(azkar.getZekr()));
        }

        public String getArabicZekrCount(int count){
            String arabic_count = "";
            switch (count){
                case 1:
                    arabic_count = "مرة واحده";
                    break;
                case 2:
                    arabic_count = "مرتان";
                    break;
                case 3:
                    arabic_count = "ثلاث مرات";
                    break;
                case 4:
                    arabic_count = "اربع مرات";
                    break;
                case 5:
                    arabic_count = "خمس مرات";
                    break;
                case 6:
                    arabic_count = "ست مرات";
                    break;
                case 7:
                    arabic_count = "سبع مرات";
                    break;
                case 8:
                    arabic_count = "ثمان مرات";
                    break;
                case 9:
                    arabic_count = "تسع مرات";
                    break;
                case 10:
                    arabic_count = "عشر مرات";
                    break;
                case 100:
                    arabic_count = "مائة مرة";
                    break;
            }
            return arabic_count;

        }

        public void shareContent(String zekr){
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, zekr);
            sendIntent.setType("text/plain");
            Intent shareIntent = Intent.createChooser(sendIntent, null);
            context.startActivity(shareIntent);
        }

        public void copyToClipboard(String copyText) {
            int sdk = android.os.Build.VERSION.SDK_INT;
            if (sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
                android.text.ClipboardManager clipboard = (android.text.ClipboardManager)
                        context.getSystemService(Context.CLIPBOARD_SERVICE);
                clipboard.setText(copyText);
            } else {
                android.content.ClipboardManager clipboard = (android.content.ClipboardManager)
                        context.getSystemService(Context.CLIPBOARD_SERVICE);
                android.content.ClipData clip = android.content.ClipData
                        .newPlainText("Your OTP", copyText);
                clipboard.setPrimaryClip(clip);
            }
//            Toast toast = Toast.makeText(context,
//                    "تم نسخ الذكر", Toast.LENGTH_SHORT);
//            toast.setGravity(Gravity.TOP| Gravity.RIGHT, 50, 50);
         //   toast.show();
            Toast.makeText(context, "تم نسخ الذكر", Toast.LENGTH_SHORT).show();
        }
    }

}
