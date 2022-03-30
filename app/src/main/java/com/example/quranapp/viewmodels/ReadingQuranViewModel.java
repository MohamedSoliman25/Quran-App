package com.example.quranapp.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.example.quranapp.database.QuranDao;
import com.example.quranapp.database.QuranDatabase;
import com.example.quranapp.pojo.readingquranmodel.Aya;
import com.example.quranapp.pojo.readingquranmodel.Jozz;
import com.example.quranapp.repository.ReadingQuranRepo;

import java.util.ArrayList;
import java.util.List;

public class ReadingQuranViewModel extends AndroidViewModel {

    ReadingQuranRepo readingQuranRepo;

    public ReadingQuranViewModel(@NonNull Application application) {
        super(application);
        readingQuranRepo=  ReadingQuranRepo.getReadingQuranRepoInstance(application);
    }

//    public LiveData<Sora> getAllSoras(){
//        return readingQuranRepo.getAllSoras();
//    }

    public LiveData<List<Aya>> getSoraListMutableLiveData(){

        return readingQuranRepo.getAllSoras();
    }

//    public ArrayList<Sora> getAllSoras() {
//        QuranDao dao = QuranDatabase.getInstance(getApplication()).quranDao();
//        ArrayList<Sora> soras = new ArrayList<>();
//        for (int i = 1; i <= 114; i++) {
//            soras.add(dao.getSoraByNumber(i));
//        }
//        return soras;
//    }

    public ArrayList<Jozz> getAllAjzaa() {
        QuranDao dao = QuranDatabase.getInstance(getApplication()).quranDao();
        ArrayList<Jozz> ajzaa = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            ajzaa.add(dao.getJozzByNumber(i));
        }
        return ajzaa;
    }

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

//    public List<?> provideIndexList(@NonNull IndexTabsUtils.QuranTabs currentTab) {
//        switch (currentTab) {
//            case SORA:
//                return readingQuranRepo.getAllSoras();
//            case JOZZ:
//                return getAllAjzaa();
//            case PAGE:
//                return getPages();
//            default:
//                return null;
//        }
//    }
}
