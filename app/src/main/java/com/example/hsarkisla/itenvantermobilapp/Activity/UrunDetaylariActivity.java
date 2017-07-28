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

import com.example.hsarkisla.itenvantermobilapp.Fragment.UrunBilgileriFragment;
import com.example.hsarkisla.itenvantermobilapp.Model.Kategori;
import com.example.hsarkisla.itenvantermobilapp.Model.Urun;
import com.example.hsarkisla.itenvantermobilapp.R;

import java.util.ArrayList;




import java.util.List;

public class UrunDetaylariActivity extends AppCompatActivity {


    private TabLayout tabLayout;
    private ViewPager viewPager;

    private Urun gelenUrunler;
    private Kategori gelenKategori;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urun_detaylari);


        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager) {

        gelenUrunler =  getIntent().getParcelableExtra("urunSorgusu");

        UrunBilgileriFragment urunBilgileriFragment = new UrunBilgileriFragment();

        Bundle bundleUrun = new Bundle();


        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        bundleUrun.putParcelable("UrunNoDetaylari", gelenUrunler);
        urunBilgileriFragment.setArguments(bundleUrun);


        adapter.addFragment(urunBilgileriFragment, "Ürün Bilgileri");


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
