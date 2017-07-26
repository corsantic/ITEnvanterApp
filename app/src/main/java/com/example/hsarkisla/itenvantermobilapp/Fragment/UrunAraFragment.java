package com.example.hsarkisla.itenvantermobilapp.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.hsarkisla.itenvantermobilapp.Activity.UrunDetaylariActivity;
import com.example.hsarkisla.itenvantermobilapp.Adapter.UrunAraAdapter;
import com.example.hsarkisla.itenvantermobilapp.Model.Urun;
import com.example.hsarkisla.itenvantermobilapp.Other.RecyclerTouchListener;
import com.example.hsarkisla.itenvantermobilapp.R;
import com.example.hsarkisla.itenvantermobilapp.Services.APIService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class UrunAraFragment extends Fragment {


    private EditText etParcaAra;
    private Button btBul;
    private Switch swResimNo;
    private int girilenNo;
    private List<Urun> resimNoListesi;
    private List<Urun> list;
    private List<Urun> geciciList;
    private RecyclerView urunListesi;

    private UrunAraAdapter mUrunNoAdapter;
    private Urun envanter;
    private ImageButton aztecScan;


    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;


    private OnFragmentInteractionListener mListener;
    private String barcode;

    public UrunAraFragment() {
        // Required empty public constructor
    }


    public static UrunAraFragment newInstance(String param1, String param2) {
        UrunAraFragment fragment = new UrunAraFragment();
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

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_urun_ara, container, false);

        etParcaAra = (EditText) view.findViewById(R.id.etParcaAra);
        btBul = (Button) view.findViewById(R.id.btBul);
        urunListesi = (RecyclerView) view.findViewById(R.id.recParcaAra);
        aztecScan = (ImageButton) view.findViewById(R.id.btAztec);

//// TODO: 26.07.2017
//        if (this.getArguments() != null) {
//            barcode = this.getArguments().getString("BARCODE", "");
//
//            // burada deger elinde oluyor artik dene bakalim
//            etParcaAra.setText(barcode);
//        }

//
//        aztecScan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent myIntent = new Intent(getActivity(), ScanActivity.class);
//                getActivity().startActivity(myIntent);//burda activityi baslatıyor
//
//            }
//
//
//        });


        btBul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                {
                    UrunListesi();
                    urunListesi.addOnItemTouchListener(new RecyclerTouchListener(getActivity().getApplicationContext(), urunListesi, new RecyclerTouchListener.ClickListener() {
                        @Override
                        public void onClick(View view, int position) {

                            if (geciciList != null) {
                                envanter = geciciList.get(position);
                                Intent intent = new Intent(getActivity(), UrunDetaylariActivity.class);
                                intent.putExtra("urunSorgusu", envanter);
                                startActivity(intent);
                            } else {

                                Toast.makeText(getContext(), "BÖYLE BİR KAYIT BULUNAMADI", Toast.LENGTH_LONG);
                            }

                        }

                        @Override
                        public void onLongClick(View view, int position) {

                        }
                    }));
                }

            }
        });

        return view;
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

    public void UrunListesi() {

        girilenNo = Integer.parseInt(etParcaAra.getText().toString());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://193.1.1.5/itenvanterapi/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);
        Call<List<Urun>> call = service.getUrun(girilenNo);

        call.enqueue(new Callback<List<Urun>>() {

            @Override
            public void onResponse(Call<List<Urun>> call, Response<List<Urun>> response) {

                    int statusCode = response.code();
                    Log.e("Status Code", "" + statusCode);
                    Log.d("TAG", "response" + response.body());
                    list = response.body();
                    geciciList = list;
                    if (geciciList != null) {
                        mUrunNoAdapter = new UrunAraAdapter(geciciList);
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
                        urunListesi.setLayoutManager(mLayoutManager);
                        urunListesi.setItemAnimator(new DefaultItemAnimator());
                        urunListesi.setAdapter(mUrunNoAdapter);
                    }



            }

            @Override
            public void onFailure(Call<List<Urun>> call, Throwable t) {
                Log.e("HATA", "onFailure: " + t.getMessage());
            }
        });
    }
}