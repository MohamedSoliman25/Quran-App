package com.example.quranapp.viewmodels;

import androidx.lifecycle.ViewModel;

import com.example.quranapp.utils.IndexTabsUtils;


public class QuranIndexViewModel extends ViewModel {

    private int[] tabsList;

    public QuranIndexViewModel() {
        tabsList = IndexTabsUtils.QURAN_INDEX_TABS;
    }

    public int getTabAt(int position) {
        return tabsList[position];
    }


}
