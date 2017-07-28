package com.example.hsarkisla.itenvantermobilapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pc on 26.07.2017.
 */

public class Kategori implements Parcelable,Cloneable{

    private int KategoriId;
    private String KategoriAdi ;
    private String KategoriKodu;


    private Kategori mInfo;

    public Kategori() {

    }

    @Override
    public Object clone() {
        Parcel parcel = Parcel.obtain();
        this.writeToParcel(parcel, 0);
        byte[] bytes = parcel.marshall();

        Parcel parcel2 = Parcel.obtain();
        parcel2.unmarshall(bytes, 0, bytes.length);
        parcel2.setDataPosition(0);
        return Kategori.CREATOR.createFromParcel(parcel2);
    }

    @Override
    public int describeContents() {
        return this.hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(KategoriId);
        dest.writeString(KategoriAdi);
        dest.writeString(KategoriKodu);


    }

    private Kategori(Parcel in) {

        KategoriId = in.readInt();
        KategoriAdi = in.readString();
        KategoriKodu=in.readString();

        mInfo = in.readParcelable(Kategori.class.getClassLoader());
    }

    public static final Parcelable.Creator<Kategori> CREATOR
            = new Parcelable.Creator<Kategori>() {
        @Override
        public Kategori createFromParcel(Parcel source) {
            return new Kategori(source);
        }

        @Override
        public Kategori[] newArray(int size) {
            return new Kategori[0];
        }
    };

    public String getKategoriAdi() {
        return KategoriAdi;
    }

    public void setKategoriAdi(String kategoriAdi) {
        KategoriAdi = kategoriAdi;
    }

    public int getKategoriId() {
        return KategoriId;
    }

    public void setKategoriId(int kategoriId) {
        KategoriId = kategoriId;
    }

    public String getKategoriKodu() {
        return KategoriKodu;
    }

    public void setKategoriKodu(String kategoriKodu) {
        KategoriKodu = kategoriKodu;
    }


}
