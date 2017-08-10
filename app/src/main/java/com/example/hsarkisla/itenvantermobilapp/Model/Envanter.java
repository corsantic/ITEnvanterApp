package com.example.hsarkisla.itenvantermobilapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by pc on 8.08.2017.
 */

public class Envanter implements Parcelable,Cloneable {
    private int UrunId;
    private int LokasyonId;
    private int PersonelId;
    private int CreateId;
    private int IslemId;
    private int IslemYonu;
    private String EnvanterKodu;
    private int Miktar;
Envanter mInfo;

    public Envanter() {
    }

    @Override
    public Object clone() {
        Parcel parcel = Parcel.obtain();
        this.writeToParcel(parcel, 0);
        byte[] bytes = parcel.marshall();

        Parcel parcel2 = Parcel.obtain();
        parcel2.unmarshall(bytes, 0, bytes.length);
        parcel2.setDataPosition(0);
        return Envanter.CREATOR.createFromParcel(parcel2);
    }

    @Override
    public int describeContents() {
        return this.hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(UrunId);
        dest.writeInt(LokasyonId);
        dest.writeInt(PersonelId);
        dest.writeInt(CreateId);
        dest.writeInt(IslemId);
        dest.writeInt(IslemYonu);
        dest.writeString(EnvanterKodu);
        dest.writeInt(Miktar);
    }
    private Envanter(Parcel in) {

        UrunId=in.readInt();
        LokasyonId = in.readInt();
        PersonelId =in.readInt();
        CreateId=in.readInt();
        IslemId=in.readInt();
        IslemYonu=in.readInt();
        EnvanterKodu=in.readString();
        Miktar=in.readInt();

        mInfo = in.readParcelable(Envanter.class.getClassLoader());
    }

    public static final Parcelable.Creator<Envanter> CREATOR
            = new Parcelable.Creator<Envanter>() {
        @Override
        public Envanter createFromParcel(Parcel source) {
            return new Envanter(source);
        }

        @Override
        public Envanter[] newArray(int size) {
            return new Envanter[0];
        }
    };
    public int getUrunId() {
        return UrunId;
    }

    public void setUrunId(int urunId) {
        UrunId = urunId;
    }

    public int getLokasyonId() {
        return LokasyonId;
    }

    public void setLokasyonId(int lokasyonId) {
        LokasyonId = lokasyonId;
    }

    public int getPersonelId() {
        return PersonelId;
    }

    public void setPersonelId(int personelId) {
        PersonelId = personelId;
    }

    public int getCreateId() {
        return CreateId;
    }

    public void setCreateId(int createId) {
        CreateId = createId;
    }

    public int getIslemId() {
        return IslemId;
    }

    public void setIslemId(int islemId) {
        IslemId = islemId;
    }

    public int getIslemYonu() {
        return IslemYonu;
    }

    public void setIslemYonu(int islemYonu) {
        IslemYonu = islemYonu;
    }

    public String getEnvanterKodu() {
        return EnvanterKodu;
    }

    public void setEnvanterKodu(String envanterKodu) {
        EnvanterKodu = envanterKodu;
    }

    public int getMiktar() {
        return Miktar;
    }

    public void setMiktar(int miktar) {
        Miktar = miktar;
    }






}
