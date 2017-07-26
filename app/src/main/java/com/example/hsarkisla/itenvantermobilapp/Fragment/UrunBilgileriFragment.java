package com.example.hsarkisla.itenvantermobilapp.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hsarkisla.itenvantermobilapp.Model.Urun;
import com.example.hsarkisla.itenvantermobilapp.R;


/**
 * Created by Ebim-ST on 20.06.2017.
 */

public class UrunBilgileriFragment extends Fragment {

    private TextView tvDescriptionDeger, tvResimNoDetayPartNoDeger, tvResimNoDetayResimNoDeger,
            tvDimQualityDeger, tvUnitMeasDeger, tvTuvMeasInfoDeger, tvTuvTsDinDeger;

    private Urun gelenUrunler;

    private Bundle bundle;

    public static UrunBilgileriFragment newInstance(Urun parcalar) {
        UrunBilgileriFragment fragment = new UrunBilgileriFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable("Urun Detaylari", parcalar);

        fragment.setArguments(bundle);

        return fragment;
    }

    public UrunBilgileriFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_urun_bilgileri, container, false);

        tvDescriptionDeger = (TextView) view.findViewById(R.id.tvDescriptionDeger);


        Bundle bundle = this.getArguments();
        if (bundle != null) {
            gelenUrunler = bundle.getParcelable("UrunNoDetaylari");
            if (gelenUrunler.getUrunAdi() != null)
                tvDescriptionDeger.setText(gelenUrunler.getUrunAdi() + "");

        }

        return view;
    }

}
