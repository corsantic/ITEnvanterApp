package com.example.hsarkisla.itenvantermobilapp.Activity;

/**
 * Created by pc on 26.07.2017.
 */

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.hsarkisla.itenvantermobilapp.Fragment.PersonelBilgileriFragment;
import com.example.hsarkisla.itenvantermobilapp.Fragment.UrunBilgileriFragment;
import com.example.hsarkisla.itenvantermobilapp.Model.Kategori;
import com.example.hsarkisla.itenvantermobilapp.Model.Personel;
import com.example.hsarkisla.itenvantermobilapp.Model.Urun;
import com.example.hsarkisla.itenvantermobilapp.R;

import java.util.ArrayList;




import java.util.List;

public class PersonelDetayActivity extends AppCompatActivity {


    private TabLayout tabLayout;
    private ViewPager viewPager;

    private Personel gelenPersonel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personel_detaylari);


        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager) {

        gelenPersonel =  getIntent().getParcelableExtra("personelSorgusu");

        PersonelBilgileriFragment personelBilgileriFragment = new PersonelBilgileriFragment();

        Bundle bundlePersonel = new Bundle();


        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        bundlePersonel.putParcelable("PersonelDetaylari", gelenPersonel);
        personelBilgileriFragment.setArguments(bundlePersonel);


        adapter.addFragment(personelBilgileriFragment, "Personel Bilgileri");


        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }
    }
}
