package com.sheriffs.babysheriff.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SectionPageAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public void addFragment(Fragment fragment, String title){
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }
    public CharSequence getPageTitle(int pos) {
        return mFragmentTitleList.get(pos);
    }
    public SectionPageAdapter(FragmentManager fm){
        super(fm);
    }
    @Override
    public Fragment getItem(int pos) { return mFragmentList.get(pos); }

    @Override
    public int getCount() {
        return mFragmentList.size() ;
    }

}
