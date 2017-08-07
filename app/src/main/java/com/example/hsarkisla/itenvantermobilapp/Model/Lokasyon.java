package com.example.hsarkisla.itenvantermobilapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pc on 7.08.2017.
 */

public class Lokasyon implements Parcelable,Cloneable {


    private int LokasyonId;
    private String LokasyonAdi ;

    private Lokasyon mInfo;

    public Lokasyon() {

    }

    @Override
    public Object clone() {
        Parcel parcel = Parcel.obtain();
        this.writeToParcel(parcel, 0);
        byte[] bytes = parcel.marshall();

        Parcel parcel2 = Parcel.obtain();
        parcel2.unmarshall(bytes, 0, bytes.length);
        parcel2.setDataPosition(0);
        return Lokasyon.CREATOR.createFromParcel(parcel2);
    }

    @Override
    public int describeContents() {
        return this.hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(LokasyonId);
        dest.writeString(LokasyonAdi);
    }
    private Lokasyon(Parcel in) {

        LokasyonId = in.readInt();
        LokasyonAdi = in.readString();

        mInfo = in.readParcelable(Lokasyon.class.getClassLoader());
    }

    public static final Parcelable.Creator<Lokasyon> CREATOR
            = new Parcelable.Creator<Lokasyon>() {
        @Override
        public Lokasyon createFromParcel(Parcel source) {
            return new Lokasyon(source);
        }

        @Override
        public Lokasyon[] newArray(int size) {
            return new Lokasyon[0];
        }
    };

    public String getLokasyonAdi() {
        return LokasyonAdi;
    }

    public void setLokasyonAdi(String LokasyonAdi) {
        LokasyonAdi = LokasyonAdi;
    }

    public int getLokasyonId() {
        return LokasyonId;
    }

    public void setLokasyonId(int LokasyonId) {
        LokasyonId = LokasyonId;
    }


    }


