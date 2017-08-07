package com.example.hsarkisla.itenvantermobilapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by pc on 26.07.2017.
 */

public class Personel implements Parcelable, Cloneable {


    private int PersonelId;
    private String PersonelAdi;
    private String PersonelSoyadi;
    private String PersonelSubeAdi;

    private String SicilNo;
    public String getPersonelSoyadi() {
        return PersonelSoyadi;
    }

    public void setPersonelSoyadi(String personelSoyadi) {
        PersonelSoyadi = personelSoyadi;
    }

    public String getPersonelSubeAdi() {
        return PersonelSubeAdi;
    }

    public void setPersonelSubeAdi(String personelSubeAdi) {
        PersonelSubeAdi = personelSubeAdi;
    }



    public int getPersonelId() {
        return PersonelId;
    }

    public void setPersonelId(int personelId) {
        PersonelId = personelId;
    }


    public Personel getmInfo() {
        return mInfo;
    }

    public void setmInfo(Personel mInfo) {
        this.mInfo = mInfo;
    }


    public String getPersonelAdi() {
        return PersonelAdi;
    }

    public void setPersonelAdi(String personelAdi) {
        PersonelAdi = personelAdi;
    }

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

        dest.writeInt(PersonelId);
        dest.writeString(SicilNo);
        dest.writeString(PersonelAdi);
        dest.writeString(PersonelSoyadi);
        dest.writeString(PersonelSubeAdi);


    }

    private Personel(Parcel in) {

        PersonelId = in.readInt();
        SicilNo=in.readString();
        PersonelAdi = in.readString();
        PersonelSoyadi = in.readString();
        PersonelSubeAdi = in.readString();


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

    public String getSicilNo() {
        return SicilNo;
    }

    public void setSicilNo(String sicilNo) {
        SicilNo = sicilNo;
    }
}
