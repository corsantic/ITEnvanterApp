package com.example.hsarkisla.itenvantermobilapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by pc on 26.07.2017.
 */

public class Personel implements Parcelable,Cloneable{

    private int PersonelId;
    private String PersonelAdi ;
    private int KategoriId ;
    private String KategoriAdi ;
    private String Model;
    private String BarcodeNo ;
    private String CreateDate ;
    private String Marka;

    private Personel mInfo;

    public Personel() {

    }

    @Override
    public Object clone() {
        Parcel parcel = Parcel.obtain();
        this.writeToParcel(parcel, 0);
        byte[] bytes = parcel.marshall();

        Parcel parcel2 = Parcel.obtain();
        parcel2.unmarshall(bytes, 0, bytes.length);
        parcel2.setDataPosition(0);
        return Personel.CREATOR.createFromParcel(parcel2);
    }

    @Override
    public int describeContents() {
        return this.hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(PersonelAdi);
        dest.writeString(KategoriAdi);
        dest.writeString(Model);
        dest.writeString(BarcodeNo);
        dest.writeString(Marka);
        dest.writeString(CreateDate);
        dest.writeInt(KategoriId);
        dest.writeInt(PersonelId);

    }

    private Personel(Parcel in) {

        PersonelAdi = in.readString();
        KategoriAdi = in.readString();
        Marka = in.readString();
        BarcodeNo = in.readString();
        CreateDate = in.readString();
        KategoriId = in.readInt();
        PersonelId = in.readInt();



        mInfo = in.readParcelable(Personel.class.getClassLoader());
    }

    public static final Parcelable.Creator<Personel> CREATOR
            = new Parcelable.Creator<Personel>() {
        @Override
        public Personel createFromParcel(Parcel source) {
            return new Personel(source);
        }

        @Override
        public Personel[] newArray(int size) {
            return new Personel[0];
        }
    };

}
