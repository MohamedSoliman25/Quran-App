package com.example.quranapp.network;

import com.example.quranapp.pojo.learningquranmodel.QuranResponse;
import com.example.quranapp.pojo.learningquranmodel.SurahDetailsResponse;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface LearningQuranApiInterface {

    @GET("surah")
    public Single<QuranResponse> getAllSurahs();

    @GET("surah/{surahNumber}/ar.alafasy")
    public Observable<SurahDetailsResponse> getSurahDetails(@Path("surahNumber") Integer surahNumber);

}
