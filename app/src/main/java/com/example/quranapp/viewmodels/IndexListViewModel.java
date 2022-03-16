package com.example.quranapp.viewmodels;

import android.app.Application;
import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;


import com.example.quranapp.database.QuranDao;
import com.example.quranapp.database.QuranDatabase;
import com.example.quranapp.readingquranmodel.Jozz;
import com.example.quranapp.readingquranmodel.Sora;
import com.example.quranapp.utils.IndexTabsUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IndexListViewModel extends AndroidViewModel {

    public IndexListViewModel(@NonNull Application application) {
        super(application);
    }

    public ArrayList<Sora> getAllSoras() {
        QuranDao dao = QuranDatabase.getInstance(getApplication()).quranDao();
        ArrayList<Sora> soras = new ArrayList<>();
        for (int i = 1; i <= 114; i++) {
            soras.add(dao.getSoraByNumber(i));
        }
        return soras;
    }

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

    public List<?> provideIndexList(@NonNull IndexTabsUtils.QuranTabs currentTab) {
        switch (currentTab) {
            case SORA:
                return getAllSoras();
            case JOZZ:
                return getAllAjzaa();
            case PAGE:
                return getPages();
            default:
                return null;
        }
    }
}
