package com.example.hsarkisla.itenvantermobilapp.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hsarkisla.itenvantermobilapp.Activity.ScanActivity;
import com.example.hsarkisla.itenvantermobilapp.Model.Urun;
import com.example.hsarkisla.itenvantermobilapp.Other.ApiUtils;
import com.example.hsarkisla.itenvantermobilapp.R;
import com.example.hsarkisla.itenvantermobilapp.Services.APIService;
import com.google.android.gms.vision.text.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class YeniUrunFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Urun urun1;

    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private RecyclerView recyclerView;
    private String mParam1;
    private EditText edtUrunAdi, edtUrunModel, edtUrunMarka, edtUrunKategoriId, edtUrunDesc;
    private Button btEkle;
    private TextView txCreateDate, txBarcode;
    private ImageButton btAztecAdd;

    private String mParam2;
    private String barcode;

    private OnFragmentInteractionListener mListener;
    private APIService service;
    private String TAG = "TAG_RESPONSE";

    public YeniUrunFragment() {
        // Required empty public constructor
    }

    public static YeniUrunFragment newInstance(String param1, String param2) {
        YeniUrunFragment fragment = new YeniUrunFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public void UrunEkle() {
        Urun urun = new Urun();
        Date date = new Date();
        setUrun(urun, date);//urunu doldur
        service = ApiUtils.getAPIService();

        {
            service.addUrun(urun).enqueue(new Callback<Urun>() {
                @Override
                public void onResponse(Call<Urun> call, Response<Urun> response) {

                    if (response.isSuccessful()) {
                        Toast.makeText(getContext(), "EKLENDİ", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Urun> call, Throwable t) {
                    Toast.makeText(getContext(), "HATA", Toast.LENGTH_SHORT).show();
                }
            });
        }

//        Call<Urun> call = service.addWitParametres(urun.getKategoriId(), urun.getMarka(), urun.getModel(), urun.getBarcodeNo(), urun.getUrunAciklama()
//                , urun.getCreateId());


    }

    private void setUrun(Urun urun, Date date) {
        urun.setUrunAciklama(edtUrunAdi.getText().toString());
        urun.setKategoriId(Integer.valueOf(edtUrunKategoriId.getText().toString()));
        urun.setMarka(edtUrunMarka.getText().toString());
        urun.setModel(edtUrunModel.getText().toString());
        urun.setBarcodeNo(txBarcode.getText().toString());
        urun.setCreateId(1);
        urun.setCreateDate(dateFormat.format(date));
        txCreateDate.setText(urun.getCreateDate());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_yeni_urun, container, false);
        setView(view);
        setBarcode();

        btAztecAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), ScanActivity.class);
                getActivity().startActivity(myIntent);

            }
        });
        btEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UrunEkle();
            }
        });

        return view;


    }

    private void setBarcode() {
        if (this.getArguments() != null) {
            barcode = this.getArguments().getString("BARCODE", "");
            // burada deger elinde oluyor
            txBarcode.setText(barcode);
        }
    }

    private void setView(View view) {
        edtUrunAdi = (EditText) view.findViewById(R.id.urunAdiEdt);
        edtUrunKategoriId = (EditText) view.findViewById(R.id.urunKategoriEdt);
        edtUrunMarka = (EditText) view.findViewById(R.id.urunMarkaEdt);
        edtUrunModel = (EditText) view.findViewById(R.id.urunModelEdt);
        txBarcode = (TextView) view.findViewById(R.id.barcodeText);
        txCreateDate = (TextView) view.findViewById(R.id.tvCreateDateTx);
        btEkle = (Button) view.findViewById(R.id.btAdd);
        btAztecAdd = (ImageButton) view.findViewById(R.id.btAztecAdd);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}
