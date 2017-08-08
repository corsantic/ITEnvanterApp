package com.example.hsarkisla.itenvantermobilapp.Fragment;


import android.app.ProgressDialog;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.hsarkisla.itenvantermobilapp.Activity.PersonelDetayActivity;
import com.example.hsarkisla.itenvantermobilapp.Activity.ScanActivity;
import com.example.hsarkisla.itenvantermobilapp.Activity.UrunDetaylariActivity;
import com.example.hsarkisla.itenvantermobilapp.Adapter.PersonelGetAdapter;
import com.example.hsarkisla.itenvantermobilapp.Adapter.UrunAraAdapter;
import com.example.hsarkisla.itenvantermobilapp.Model.Lokasyon;
import com.example.hsarkisla.itenvantermobilapp.Model.Personel;
import com.example.hsarkisla.itenvantermobilapp.Model.Urun;
import com.example.hsarkisla.itenvantermobilapp.Other.ApiUtils;
import com.example.hsarkisla.itenvantermobilapp.Other.RecyclerTouchListener;
import com.example.hsarkisla.itenvantermobilapp.R;
import com.example.hsarkisla.itenvantermobilapp.Services.APIService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class UrunTeslimFragment extends Fragment {


    private EditText etGive,etLocation;
    private Button btGive,btLocation;
    private Switch swResimNo;
    private int girilenNo;

    private List<Urun> resimNoListesi;
    private List<Urun> list;
    private List<Personel> listPerson;
    private List<Urun> geciciList;
    private List<Personel> geciciPersList;
    private RecyclerView urunListesi;
    private RecyclerView personelList;


    private UrunAraAdapter mUrunNoAdapter;
    private PersonelGetAdapter mPersonelGetAdapter;
    private Urun envanter;
    private Personel personel;
    private ImageButton aztecScan;
    private Spinner spLocation;


    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;


    private OnFragmentInteractionListener mListener;
    private String barcode;
    private APIService service;
private List<Lokasyon> getLocationList;
    private int girilenId;
    private List<Lokasyon> locationList;

    public UrunTeslimFragment() {
        // Required empty public constructor
    }


    public static UrunTeslimFragment newInstance(String param1, String param2) {
        UrunTeslimFragment fragment = new UrunTeslimFragment();
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

        View view = inflater.inflate(R.layout.fragment_urun_teslim, container, false);

        etGive = (EditText) view.findViewById(R.id.etGive);
        btGive = (Button) view.findViewById(R.id.btGive);
        urunListesi = (RecyclerView) view.findViewById(R.id.recUrunGive);
        personelList=(RecyclerView) view.findViewById(R.id.recPersonel) ;
        aztecScan = (ImageButton) view.findViewById(R.id.btAztecGive);
        spLocation=(Spinner) view.findViewById(R.id.spLocation);
        etLocation=(EditText) view.findViewById(R.id.etLocation);
        btLocation=(Button) view.findViewById(R.id.btLocation);

       FillLocation();//Lokasyonu dolduruyor

        if (this.getArguments() != null) {
            barcode = this.getArguments().getString("BARCODE", "");

            // burada deger elinde oluyor artik dene bakalim
            etGive.setText(barcode);
        }


        aztecScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), ScanActivity.class);
                getActivity().startActivity(myIntent);//burda activityi baslatıyor

            }


        });

        btLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                {
                    PersonelListesi();
                    personelList.addOnItemTouchListener(new RecyclerTouchListener(getActivity().getApplicationContext(), personelList, new RecyclerTouchListener.ClickListener() {
                        @Override
                        public void onClick(View view, int position) {


                            try {
                                mPersonelGetAdapter.setSelected(position);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onLongClick(View view, int position) {
                            if (personelList != null) {
                                personel = geciciPersList.get(position);
                                Intent intent = new Intent(getActivity(), PersonelDetayActivity.class);
                                intent.putExtra("personelSorgusu", personel);

                                startActivity(intent);

                            } else {

                                Toast.makeText(getContext(), "BÖYLE BİR KAYIT BULUNAMADI", Toast.LENGTH_LONG);
                            }
                        }
                    }));
                }

            }
        });

        btGive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                {
                    UrunListesi();
                    urunListesi.addOnItemTouchListener(new RecyclerTouchListener(getActivity().getApplicationContext(), urunListesi, new RecyclerTouchListener.ClickListener() {
                        @Override
                        public void onClick(View view, int position) {

                            try {
                                mUrunNoAdapter.setSelected(position);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onLongClick(View view, int position) {
                            if (geciciList != null) {
                                envanter = geciciList.get(position);
                                Intent intent = new Intent(getActivity(), UrunDetaylariActivity.class);
                                intent.putExtra("urunSorgusu", envanter);

                                startActivity(intent);

                            } else {

                                Toast.makeText(getContext(), "BÖYLE BİR KAYIT BULUNAMADI", Toast.LENGTH_LONG);
                            }

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

    private void showListinSpinner(){
        //String array to store all the book names
        String[] items = new String[locationList.size()];

        //Traversing through the whole list to get all the names
        for(int i=0; i<locationList.size(); i++){
            //Storing names to string array
            items[i] = locationList.get(i).getLokasyonAdi();
        }

        //Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, items);
        //setting adapter to spinner
        spLocation.setAdapter(adapter);
        //Creating an array adapter for list view

    }


    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }


    /** Ürün*/
    public void UrunListesi() {

        girilenNo = Integer.parseInt(etGive.getText().toString());

        service = ApiUtils.getAPIService();

        service.getUrun(girilenNo).enqueue(new Callback<List<Urun>>() {

            @Override
            public void onResponse(Call<List<Urun>> call, Response<List<Urun>> response) {

                int statusCode = response.code();
                Log.e("Status Code", "" + statusCode);
                Log.d("TAG", "response" + response.body());
                list = response.body();
                geciciList = list;
                if (geciciList != null) {
                    mUrunNoAdapter = new UrunAraAdapter(geciciList,getContext());
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


    /**Lokayon*/
    public List<Lokasyon> FillLocation()
    {
        service = ApiUtils.getAPIService();


        {
            service.getAllLocation().enqueue(new Callback<List<Lokasyon>>() {
                @Override
                public void onResponse(Call<List<Lokasyon>> call, Response<List<Lokasyon>> response) {

                    if (response.isSuccessful()) {
                        locationList=response.body();
                        Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                        showListinSpinner();
                    }
                }

                @Override
                public void onFailure(Call<List<Lokasyon>> call, Throwable t) {
                    Toast.makeText(getContext(), "HATA", Toast.LENGTH_SHORT).show();
                }
            });
        }

return locationList;



    }


/**Personel*/
    public void PersonelListesi() {

        girilenId = Integer.parseInt (etLocation.getText().toString());

        service = ApiUtils.getAPIService();

        service.getPersonel(girilenId).enqueue(new Callback<List<Personel>>() {
            @Override
            public void onResponse(Call<List<Personel>> call, Response<List<Personel>> response) {
                int statusCode = response.code();
                Log.e("Status Code", "" + statusCode);
                Log.d("TAG", "response" + response.body());
                listPerson = response.body();
                geciciPersList = listPerson;
                if (geciciPersList != null) {
                    mPersonelGetAdapter = new PersonelGetAdapter(geciciPersList,getContext());
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
                    personelList.setLayoutManager(mLayoutManager);
                    personelList.setItemAnimator(new DefaultItemAnimator());
                    personelList.setAdapter(mPersonelGetAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Personel>> call, Throwable t) {
                Log.e("HATA", "onFailure: " + t.getMessage());
            }
        });
    }
}