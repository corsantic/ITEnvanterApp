package com.example.hsarkisla.itenvantermobilapp.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    private SharedPreferences mPref;
    private SharedPreferences.Editor mEditor;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView twNumara;
        public TextView twNumaraAciklama;



        public MyViewHolder(View itemView) {
            super(itemView);
            twNumara = (TextView) itemView.findViewById(R.id.twLokasyon);
            twNumaraAciklama = (TextView) itemView.findViewById(R.id.twLokasyonDesc);



        }

    }

    public void setSelected(int pos) {
        try {
            if (list.size() > 1) {
                list.get(mPref.getInt("position", 0)).setSelected(false);
                mEditor.putInt("position", pos);
                mEditor.commit();
            }
            list.get(pos).setSelected(true);
            notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PersonelGetAdapter(List<Personel> list,Context context) {
        this.list = list;

        mPref = context.getSharedPreferences("person", Context.MODE_PRIVATE);
        mEditor = mPref.edit();
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
        holder.twNumara.setText(personel.getPersonelAdi());
        holder.twNumaraAciklama.setText(personel.getPersonelSoyadi());
        Log.e("selection", "" + list.get(position).isSelected());
        if (list.get(position).isSelected()) {
            holder.itemView.setBackgroundColor(Color.BLUE);
        } else {
            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
