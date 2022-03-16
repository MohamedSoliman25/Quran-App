package com.example.quranapp.repository;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.quranapp.pojo.learningquranmodel.QuranResponse;
import com.example.quranapp.pojo.learningquranmodel.SurahDetailsResponse;
import com.example.quranapp.network.LearningQuranApiInterface;
import com.example.quranapp.network.LearningQuranClients;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LearningQuranRepo {

    private LearningQuranApiInterface learningQuranApiInterface;
    private static final String TAG = "LearningQuranRepo";
    private static LearningQuranRepo instance;
    private MutableLiveData<QuranResponse> quranResponseMutableLiveData;

    private MutableLiveData<SurahDetailsResponse> surahDetailsResponseMutableLiveData;

    public LearningQuranRepo() {
        learningQuranApiInterface = LearningQuranClients.getSurahsINSTANCE().create(LearningQuranApiInterface.class);
    }

    public static LearningQuranRepo getInstance() {
        if (instance==null){
            instance = new LearningQuranRepo();
        }
        return instance;
    }

    public LiveData<QuranResponse>getAllSurahs(){
        quranResponseMutableLiveData = new MutableLiveData<>();
        learningQuranApiInterface.getAllSurahs().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<QuranResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull QuranResponse quranResponse) {
                        quranResponseMutableLiveData.setValue(quranResponse);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                        Log.d(TAG, "onError: "+e.getMessage());
                    }
                });
        return quranResponseMutableLiveData;
    }

    @SuppressLint("CheckResult")
    public LiveData<SurahDetailsResponse> getSurahDetails(Integer surahNumber){
        surahDetailsResponseMutableLiveData = new MutableLiveData<>();
        learningQuranApiInterface.getSurahDetails(surahNumber).subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s->surahDetailsResponseMutableLiveData.setValue(s),e-> Log.d(TAG, "getSurahDetails: "+e.getMessage()));
        Log.d(TAG, "getSurahDetails surahNumber : "+surahNumber);
        return surahDetailsResponseMutableLiveData;
    }

}
