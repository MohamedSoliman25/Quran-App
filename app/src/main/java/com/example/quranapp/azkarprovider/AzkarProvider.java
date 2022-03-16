package com.example.quranapp.azkarprovider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;

import com.example.quranapp.pojo.azkar.Zekr;
import com.example.quranapp.pojo.azkar.ZekrType;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class AzkarProvider {

    private static ArrayList<Zekr> getAllAzkar(Context context) {
        try {
            InputStream azkarFile = context.getAssets().open("azkar/azkar.json");
            int size = azkarFile.available();
            byte[] bytes = new byte[size];
            azkarFile.read(bytes);
            azkarFile.close();
            String azkarString = new String(bytes, StandardCharsets.UTF_8);
            Gson gson = new Gson();
            ArrayList<Zekr> azkar = gson.fromJson(azkarString, new TypeToken<List<Zekr>>() {
            }.getType());
            return azkar;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public  static  ArrayList<Zekr> getAzkarByType( ArrayList<Zekr> zekrList,@NonNull String zekrType) {
        ArrayList<Zekr>azkarByType = new ArrayList<>();
        for (Zekr zekr:zekrList){
            if (zekr.getCategory().equals(zekrType)){
               azkarByType.add(zekr);
            }
        }
        return azkarByType;

    }
    // this method takes zekrType(category or zekrName) and return zekr details about it
        public  static  ArrayList<Zekr> getAzkarByType(Context context, @NonNull String zekrType){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return  getAllAzkar(context)
                    .stream()
                    .filter(zekr -> zekrType.equals(zekr.getCategory()))
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        else
            return getAzkarByType(getAllAzkar(context),zekrType);
    }

    // i use HashSet for prevent duplicated items
    private static HashSet<ZekrType>getAllAzkarTypes(ArrayList<Zekr> zekrList){
        HashSet<ZekrType> allZekrTypeList = new HashSet<>();
        for (Zekr zekr:zekrList){
            allZekrTypeList.add(new ZekrType(zekr.getCategory()));
        }
        return allZekrTypeList;

    }
    //for getting all azkar names for AzkarHomeFragment
    // i use map operator in arraylist for getting only azkar name and assigns it to HashSet by Collection , note : (i can use rxjava instead of this code) later
    // i use HashSet for prevent duplicated items
    public static HashSet<ZekrType> getAzkarTypes(Context context){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return getAllAzkar(context)
                      .stream()
                      .map(zekr -> new ZekrType(zekr.getCategory()))
                      .collect(Collectors.toCollection(HashSet::new));
        }
        else
            return getAllAzkarTypes(getAllAzkar(context));
    }
}