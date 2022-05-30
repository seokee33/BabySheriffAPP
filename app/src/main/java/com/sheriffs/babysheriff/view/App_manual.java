package com.sheriffs.babysheriff.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.sheriffs.babysheriff.R;

import me.relex.circleindicator.CircleIndicator;

public class App_manual extends AppCompatActivity {

    private  ViewPager mViewPager;
    SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_manual);
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mViewPager);

    }

    public void setupViewPager(ViewPager viewPager) {
        adapter.addFragment(new App_manualFrag1(), "1");
        adapter.addFragment(new App_manualFrag2(), "2");
        adapter.addFragment(new App_manualFrag3(), "3");
        adapter.addFragment(new App_manualFrag4(), "4");
        adapter.addFragment(new App_manualFrag5(), "5");
        viewPager.setAdapter(adapter);
    }

}