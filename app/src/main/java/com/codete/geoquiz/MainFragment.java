package com.codete.geoquiz;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    private static final int SCREENS_COUNT = 3;
    public static final String TAG = "MainFragment";

    private PagerAdapter pagerAdapter;
    private ActionBar mActionBar;
    @InjectView(R.id.pager)
    ViewPager mPager;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.e(TAG, "onCreateView");
        View contentView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.inject(this, contentView);
        if (pagerAdapter == null) {
            pagerAdapter = new PagerAdapter(getChildFragmentManager(), getActivity());
            mPager.setAdapter(pagerAdapter);
        }

        mActionBar = ((ActionBarActivity) getActivity()).getSupportActionBar();
        initiateActionBar();
        mPager.setOnPageChangeListener(onPageChangeListener);

        return contentView;
    }


    private void initiateActionBar() {
        if (mActionBar != null) {
            ColorDrawable background = new ColorDrawable(getActivity().getResources
                    ().getColor(R.color.green));
            mActionBar.setBackgroundDrawable(background);
            mActionBar.setStackedBackgroundDrawable(background);
            mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
            mActionBar.removeAllTabs();

            TypedArray tabs = getResources().obtainTypedArray(R.array.tabs);
            if (tabs != null)
                for (int i = 0; i < SCREENS_COUNT; i++) {
                    mActionBar.addTab(mActionBar.newTab().setIcon(tabs.getResourceId(i, -1))
                            .setTabListener(tabListener));
                }
        }
    }

    private ActionBar.TabListener tabListener = new ActionBar.TabListener() {
        @Override
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
            mPager.setCurrentItem(tab.getPosition());
        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

        }
    };

    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager
            .OnPageChangeListener() {


        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            mActionBar.setSelectedNavigationItem(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };


    public class PagerAdapter extends FragmentPagerAdapter {

        protected Context context;

        public PagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return new FirstScreenFragment();
                case 1:
                    return new SecondScreenFragment();
                case 2:
                    return new ThirdScreenFragment();

            }
            return null;
        }

        @Override
        public int getCount() {
            return SCREENS_COUNT;
        }
    }

}
