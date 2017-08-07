package com.example.hsarkisla.itenvantermobilapp.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hsarkisla.itenvantermobilapp.Model.Personel;
import com.example.hsarkisla.itenvantermobilapp.Model.Urun;
import com.example.hsarkisla.itenvantermobilapp.R;
import com.google.android.gms.vision.text.Text;

import java.util.List;


/**
 * Created by Ebim-ST on 19.06.2017.
 */

public class PersonelGetAdapter extends RecyclerView.Adapter<PersonelGetAdapter.MyViewHolder> {

    private List<Personel> list;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView twNumara;
        public TextView twNumaraAciklama;



        public MyViewHolder(View itemView) {
            super(itemView);
            twNumara = (TextView) itemView.findViewById(R.id.twLokasyonNo);
            twNumaraAciklama = (TextView) itemView.findViewById(R.id.twLokasyonDesc);

        }

    }

    public PersonelGetAdapter(List<Personel> list) {
        this.list = list;
    }

    @Override
    public PersonelGetAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.personel_bilgileri_list_row, parent, false);

        return new PersonelGetAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PersonelGetAdapter.MyViewHolder holder, int position) {

        Personel personel = list.get(position);
        holder.twNumara.setText(personel.getPersonelId());
        holder.twNumaraAciklama.setText(personel.getPersonelAdi());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
