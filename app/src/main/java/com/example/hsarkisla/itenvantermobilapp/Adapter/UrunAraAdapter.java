package com.example.hsarkisla.itenvantermobilapp.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hsarkisla.itenvantermobilapp.Model.Urun;
import com.example.hsarkisla.itenvantermobilapp.R;
import com.google.android.gms.vision.text.Text;

import java.util.List;


/**
 * Created by Ebim-ST on 19.06.2017.
 */

public class UrunAraAdapter extends RecyclerView.Adapter<UrunAraAdapter.MyViewHolder> {

    private List<Urun> list;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView twNumara;
        public TextView twNumaraAciklama;



        public MyViewHolder(View itemView) {
            super(itemView);
            twNumara = (TextView) itemView.findViewById(R.id.twNumara);
            twNumaraAciklama = (TextView) itemView.findViewById(R.id.twNumaraAciklama);

        }

    }

    public UrunAraAdapter(List<Urun> list) {
        this.list = list;
    }

    @Override
    public UrunAraAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.urun_bilgileri_list_row, parent, false);

        return new UrunAraAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UrunAraAdapter.MyViewHolder holder, int position) {

        Urun urunler = list.get(position);
        holder.twNumara.setText(urunler.getBarcodeNo());
        holder.twNumaraAciklama.setText(urunler.getUrunAdi());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
