package com.example.quranapp.repository;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.quranapp.database.QuranDatabase;
import com.example.quranapp.pojo.readingquranmodel.Aya;
import com.example.quranapp.pojo.readingquranmodel.Jozz;
import com.example.quranapp.pojo.readingquranmodel.Sora;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

public class ReadingQuranRepo {
    private static final String TAG = "ReadingQuranRepo";
    private static ReadingQuranRepo readingQuranRepoInstance;
    private static QuranDatabase quranDatabase;
    private MutableLiveData<List<Aya>> getSoraListLiveData;
    private MutableLiveData<List<Aya>>getSoraDetailsLiveData;
    private MutableLiveData<List<Aya>>getAyaBySubTextLiveData;
    private MutableLiveData<Jozz>getAllAjzaaLiveData;
  //  private MutableLiveData<List<Integer>>getAllPagesLiveData;


    public static ReadingQuranRepo getReadingQuranRepoInstance(Application application) {

        quranDatabase = QuranDatabase.getInstance(application);
        if (readingQuranRepoInstance==null){
            readingQuranRepoInstance = new ReadingQuranRepo();
        }
        return readingQuranRepoInstance;
    }

//        public List<Sora> getAllSorasFromDb() {
//            List<Sora> soras = new ArrayList<>();
////            Observable<List<Sora>>listObservable = null;
//        for (int i = 1; i <= 114; i++) {
//            soras.add(quranDatabase.quranDao().getSoraByNumber(i));
//        }
////         Observable.fromArray(soras);
//        return soras;
//    }



    @SuppressLint("CheckResult")
    public LiveData<List<Aya>> getAllSoras() {
        getSoraListLiveData = new MutableLiveData<>();
        quranDatabase.quranDao().getAllSoras()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(ayas -> getSoraListLiveData.setValue(ayas));
        return getSoraListLiveData;
    }

    @SuppressLint("CheckResult")
    public LiveData<List<Aya>> getAllSoraDetails(int soraNumber) {
        getSoraDetailsLiveData = new MutableLiveData<>();
        quranDatabase.quranDao().getAllSorasDetails(soraNumber)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(ayas -> getSoraDetailsLiveData.setValue(ayas));
        return getSoraDetailsLiveData;
    }

    @SuppressLint("CheckResult")
    public LiveData<List<Aya>> getAyaBySubText(String keyword){
        getAyaBySubTextLiveData = new MutableLiveData<>();
        quranDatabase.quranDao().getAyaBySubText(keyword)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subAyas->getAyaBySubTextLiveData.setValue(subAyas));
        return getAyaBySubTextLiveData;
    }

//    @SuppressLint("CheckResult")
//    public Observable<List<Sora>> getObservableSoraDetails(){
//        Observable<List<Sora>>  soraListObservable=  Observable.create(new ObservableOnSubscribe<List<Sora>>() {
//
//            @Override
//            public void subscribe(@NonNull ObservableEmitter<List<Sora>> emitter) throws Exception {
//                for (int i=0; i<114;i++){
//                    emitter.onNext(quranDatabase.quranDao().getAllSoraDetails(i));
//                }
//            }
//        });
//        soraListObservable.subscribe(soras -> Log.d(TAG, "getObservableSoraDetails: "+soraListObservable));
//        return soraListObservable;
//    }
    //right code but there is simple delay
//    @SuppressLint("CheckResult")
//    public MutableLiveData<List<Sora>> getAllSoras() {
//        getSoraListLiveData = new MutableLiveData<>();
//        Observable.create(new ObservableOnSubscribe<List<Sora>>() {
//
//                              @Override
//                              public void subscribe(@NonNull ObservableEmitter<List<Sora>> emitter) throws Exception {
//                                  List<Sora> soraList = new ArrayList<>();
//                                  for (int i = 0; i < 114; i++) {
////                    emitter.onNext(quranDatabase.quranDao().getSoraByNumber(i));
//                                      soraList.add(quranDatabase.quranDao().getSoraByNumber(i+1));
//                                  }
//                                  emitter.onNext(soraList);
//                              }
//                          }).doOnNext(soras ->Log.d(TAG, "upStreamgetAllSoras: "+soras.get(0).getArabicName()))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(soras->{getSoraListLiveData.setValue(soras);Log.d(TAG, "getAllSoras: "+soras.get(113).getArabicName());});
//
//        return getSoraListLiveData;
   // }




    public ArrayList<Jozz> getAllAjzaaFromDb() {
        ArrayList<Jozz> ajzaa = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            ajzaa.add(quranDatabase.quranDao().getJozzByNumber(i));
        }
        return ajzaa;
    }


//        @SuppressLint("CheckResult")
//    public List<Sora>getSoras(){
//            List<Sora> soraList = new ArrayList<>();
//                    Observable.fromIterable(getAllSorasFromDb())
//                            .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(sora -> {soraList.add(sora);Log.d(TAG, "getSoras: "+sora.getArabicName());});
//        return soraList;
//    }

//     Observable.create({ aSubscriber ->
//        for (int i=0; i<50; i++) {
//            if (false == aSubscriber.isUnsubscribed()) {
//                aSubscriber.onNext("value_" + i);
//            };
//        }

//@SuppressLint("CheckResult")
//public void getSoras(){
//        List<Sora> soraList = new ArrayList<>();
//         Observable.create(new ObservableOnSubscribe<Object>() {
//                               @Override
//                               public void subscribe(@NonNull ObservableEmitter<Object> emitter) throws Exception {
//                                   for (int i = 0; i < 114; i++) {
//
//
//                                   }
//
//                               }
//                           }
//}





//    public List<Sora>getAllSoras(){
//        List<Sora> soraList = new ArrayList<>();
//        for (int i=0;i<114;i++){
//            soraList.add(getSora(i+1).getValue());
//            Log.d(TAG, "getAllSoras: "+getSora(i+1).getValue());
//        }
//        return soraList;
//    }

//    @SuppressLint("CheckResult")
//    public List<Sora>getSora(){
//        List<Sora> soraList = new ArrayList<>();
//        for (int i=0;i<114;i++) {
//            quranDatabase.quranDao().getSoraByNumber((i+1))
//                    .subscribeOn(Schedulers.newThread())
//                    .observeOn(Schedulers.newThread())
//                    .subscribe(new Consumer<Sora>() {
//                        @Override
//                        public void accept(Sora sora) throws Exception {
//                            soraList.add(sora);
//                        }
//                    });
//        }
//
//        return soraList;
//
//    }

//    @SuppressLint("CheckResult")
//    public List<Jozz>getAllAjzaa(){
//        List<Jozz> ajzaaList  = new ArrayList<>();
//
//        for (int i=0; i<30;i++){
//            quranDatabase.quranDao().getJozzByNumber((i+1))
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(jozz ->{ajzaaList.add(jozz);Log.d(TAG, "getJozz: "+jozz.getStartPage());});
//        }
//        return ajzaaList;
//    }

    public List<Integer> getPages() {
        List<Integer> pageLists = new ArrayList<>();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            return IntStream.range(1, 604).boxed().collect(Collectors.toList());
//        }
        for (int i=0;i<604;i++){
            pageLists.add(i+1);
        }
        return pageLists;
    }

//    public LiveData<?> provideIndexList(@androidx.annotation.NonNull IndexTabsUtils.QuranTabs currentTab) {
////        getAllSurasLiveData = new MutableLiveData<>();
////        getAllAjzaaLiveData = new MutableLiveData<>();
////        getAllPagesLiveData = new MutableLiveData<>();
//        switch (currentTab) {
//            case SORA:
//                getAllSurasLiveData.setValue(getAllSoras());
//                return getAllSurasLiveData;
//
//            case JOZZ:
//                getAllAjzaaLiveData.setValue(getAllAjzaa());
//                return getAllAjzaaLiveData;
//
//                case PAGE:
//                    getAllPagesLiveData.setValue(getPages());
//                return getAllPagesLiveData;
//            default:
//                return null;
//        }
//    }

}
