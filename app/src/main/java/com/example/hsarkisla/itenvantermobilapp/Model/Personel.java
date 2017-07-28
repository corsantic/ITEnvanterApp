package com.example.hsarkisla.itenvantermobilapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by pc on 26.07.2017.
 */

public class Personel implements Parcelable,Cloneable{

    public int getPersonelId() {
        return PersonelId;
    }

    public void setPersonelId(int personelId) {
        PersonelId = personelId;
    }

    private int PersonelId;
    private String PersonelAdi ;

    public int getKategoriId() {
        return KategoriId;
    }

    public void setKategoriId(int kategoriId) {
        KategoriId = kategoriId;
    }

    public String getKategoriAdi() {
        return KategoriAdi;
    }

    public void setKategoriAdi(String kategoriAdi) {
        KategoriAdi = kategoriAdi;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getBarcodeNo() {
        return BarcodeNo;
    }

    public void setBarcodeNo(String barcodeNo) {
        BarcodeNo = barcodeNo;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String createDate) {
        CreateDate = createDate;
    }

    public String getMarka() {
        return Marka;
    }

    public void setMarka(String marka) {
        Marka = marka;
    }

    public Personel getmInfo() {
        return mInfo;
    }

    public void setmInfo(Personel mInfo) {
        this.mInfo = mInfo;
    }

    private int KategoriId ;
    private String KategoriAdi ;
    private String Model;
    private String BarcodeNo ;
    private String CreateDate ;
    private String Marka;

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
