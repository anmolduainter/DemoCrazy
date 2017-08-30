package com.example.anmol.democrazy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.anmol.democrazy.fragments.BillsLaid;
import com.example.anmol.democrazy.fragments.BillsPassed;
import com.example.anmol.democrazy.fragments.Ordiances;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anmol on 27/8/17.
 */

public class Bills extends AppCompatActivity {


    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bills_main);

        toolbar = (Toolbar) findViewById(R.id.toolbarBills);

        toolbar.setTitle("Bills");

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpagerBills);
        viewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {


                final float normalizedposition = Math.abs(Math.abs(position) - 1);
                page.setScaleX(normalizedposition);
                //  page.setRotationX(position * -100);
                page.setRotationY(position * -90);

            }
        });
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabsBills);
        tabLayout.setupWithViewPager(viewPager);


    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new BillsLaid(Bills.this), "Bills Laid");
        adapter.addFragment(new BillsPassed(Bills.this), "Bills Passed");
        adapter.addFragment(new Ordiances(Bills.this), "Ordiances");
        viewPager.setAdapter(adapter);
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }


    }
}