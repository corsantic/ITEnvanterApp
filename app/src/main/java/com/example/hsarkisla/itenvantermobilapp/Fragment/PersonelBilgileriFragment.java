package com.example.hsarkisla.itenvantermobilapp.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hsarkisla.itenvantermobilapp.Model.Kategori;
import com.example.hsarkisla.itenvantermobilapp.Model.Personel;
import com.example.hsarkisla.itenvantermobilapp.Model.Urun;
import com.example.hsarkisla.itenvantermobilapp.R;
import com.google.android.gms.vision.text.Text;


/**
 * Created by Ebim-ST on 20.06.2017.
 */

public class PersonelBilgileriFragment extends Fragment {

    private TextView tvPersonelAdi;

    private TextView tvPersonelSoyadi,tvPersonelSube,tvPersonelSicilNo;
    private Personel gelenPersonel;




    public static PersonelBilgileriFragment newInstance(Personel personeller) {
        PersonelBilgileriFragment fragment = new PersonelBilgileriFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable("PersonelDetaylari", personeller);

        fragment.setArguments(bundle);

        return fragment;
    }

    public PersonelBilgileriFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_personel_bilgileri, container, false);

        tvPersonelAdi = (TextView) view.findViewById(R.id.tvPersonel);
    tvPersonelSicilNo=(TextView) view.findViewById(R.id.tvPersonelSicilNo);
        tvPersonelSoyadi=(TextView) view.findViewById(R.id.tvPersonelSoyadi);
        tvPersonelSube=(TextView) view.findViewById(R.id.tvSubeAdi);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            gelenPersonel = bundle.getParcelable("PersonelDetaylari");
            if (gelenPersonel.getPersonelAdi() != null)
                tvPersonelAdi.setText(gelenPersonel.getPersonelAdi() + "");
            if (gelenPersonel.getPersonelSoyadi() != null)
                tvPersonelSoyadi.setText(gelenPersonel.getPersonelSoyadi() + "");
            if (gelenPersonel.getPersonelSubeAdi() != null)
                tvPersonelSube.setText(gelenPersonel.getPersonelSubeAdi() + "");
            if (gelenPersonel.getSicilNo() != null)
                tvPersonelSicilNo.setText(gelenPersonel.getSicilNo() + "");



        }

        return view;
    }

}
