package com.example.quranapp.database;

import androidx.room.Dao;
import androidx.room.Query;


import com.example.quranapp.pojo.readingquranmodel.Aya;
import com.example.quranapp.pojo.readingquranmodel.Jozz;
import com.example.quranapp.pojo.readingquranmodel.Sora;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
public interface QuranDao {


    @Query("SELECT * FROM quran WHERE page = :page")
    List<Aya> getAyatByPage(int page);

    @Query("SELECT sora as soraNumber, MIN(page) as startPage ,MAX(page) as endPage ,sora_name_ar as arabicName,sora_name_en as englishName FROM quran WHERE sora = :soraNumber")
    Sora getSoraByNumber(int soraNumber);

    @Query("SELECT jozz as jozzNumber, MIN(page) as startPage ,MAX(page) as endPage FROM quran WHERE jozz = :jozzNumber")
    Jozz getJozzByNumber(int jozzNumber);


    // for searching about aya (original)
    @Query("SELECT * FROM quran WHERE aya_text_emlaey LIKE '%' || :keyword || '%'")
    Observable<List<Aya>> getAyaBySubText(String keyword);

    // for searching about sora name
//    @Query("SELECT * FROM quran WHERE sora_name_ar LIKE '%' || :keyword || '%'")
//    Observable<List<Aya>> getAyaBySubText(String keyword);

    // i use group by for preventing repeated items
    @Query("SELECT  *  FROM quran GROUP BY sora")
    Single<List<Aya>> getAllSoras();

//    @Query("SELECT sora as soraNumber, MIN(page) as startPage ,MAX(page) as endPage ,sora_name_ar as arabicName,sora_name_en as englishName,aya_text as ayaText,aya_text_emlaey as ayaTextEmlaey FROM quran WHERE sora = :soraNumber")
//    Observable<List<Sora>> getAllSoraDetails(int soraNumber);

    @Query("SELECT  *  FROM quran  WHERE sora = :soraNumber")
    Observable<List<Aya>> getAllSorasDetails(int soraNumber);



}
