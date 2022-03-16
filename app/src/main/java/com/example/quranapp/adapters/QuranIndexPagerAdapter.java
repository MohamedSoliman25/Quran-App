package com.example.quranapp.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.quranapp.fragments.IndexListFragment;
import com.example.quranapp.utils.IndexTabsUtils;


public class QuranIndexPagerAdapter extends FragmentStateAdapter {
    public static final int PAGES_COUNT = 3;

    public QuranIndexPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new IndexListFragment(IndexTabsUtils.QuranTabs.SORA);

            case 1:
                return new IndexListFragment(IndexTabsUtils.QuranTabs.JOZZ);

            case 2:
                return new IndexListFragment(IndexTabsUtils.QuranTabs.PAGE);
            default:
                return null;
        }

    }

    @Override
    public int getItemCount() {
        return PAGES_COUNT;
    }
}
