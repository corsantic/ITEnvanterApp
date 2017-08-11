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
import com.example.hsarkisla.itenvantermobilapp.Model.Urun;
import com.example.hsarkisla.itenvantermobilapp.R;
import com.google.android.gms.vision.text.Text;


/**
 * Created by Ebim-ST on 20.06.2017.
 */

public class UrunBilgileriFragment extends Fragment {

    private TextView tvUrunAdi;

    private TextView tvUrunModel,tvMarka,tvKategori,tvCreate;
    private Urun gelenUrunler;




    public static UrunBilgileriFragment newInstance(Urun urunler) {
        UrunBilgileriFragment fragment = new UrunBilgileriFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable("UrunNoDetaylari", urunler);

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

        tvUrunAdi = (TextView) view.findViewById(R.id.tvUrunAdiNo);
     tvUrunModel=(TextView) view.findViewById(R.id.tvModelNo);
        tvCreate=(TextView) view.findViewById(R.id.tvCreateDate);
        tvMarka=(TextView) view.findViewById(R.id.tvMarkaNo);
        tvKategori=(TextView) view.findViewById(R.id.tvKategoriNo);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            gelenUrunler = bundle.getParcelable("UrunNoDetaylari");
            if (gelenUrunler.getUrunAciklama() != null)
                tvUrunAdi.setText(gelenUrunler.getUrunAciklama() + "");
            if (gelenUrunler.getModel() != null)
                tvUrunModel.setText(gelenUrunler.getModel() + "");
            if (gelenUrunler.getMarka() != null)
                tvMarka.setText(gelenUrunler.getMarka() + "");
            if (gelenUrunler.getKategoriAdi() != null)
                tvKategori.setText(gelenUrunler.getKategoriAdi() + "");
            if (gelenUrunler.getCreateDate() != null)
                tvCreate.setText(gelenUrunler.getCreateDate() + "");
        }

        return view;
    }

}
