package com.example.hsarkisla.itenvantermobilapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by pc on 26.07.2017.
 */

public class Urun implements Parcelable,Cloneable{

    private int UrunId;
    private String UrunAdi ;
    private String Model;
    private String BarcodeNo ;
    private String CreateDate ;
    private String Marka;
    private String KategoriAdi;

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

    private int KategoriId;

    private Urun mInfo;

    public Urun() {

    }

    @Override
    public Object clone() {
        Parcel parcel = Parcel.obtain();
        this.writeToParcel(parcel, 0);
        byte[] bytes = parcel.marshall();

        Parcel parcel2 = Parcel.obtain();
        parcel2.unmarshall(bytes, 0, bytes.length);
        parcel2.setDataPosition(0);
        return Urun.CREATOR.createFromParcel(parcel2);
    }

    @Override
    public int describeContents() {
        return this.hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(UrunAdi);
        dest.writeInt(UrunId);
        dest.writeString(KategoriAdi);
        dest.writeString(Marka);
        dest.writeString(Model);
        dest.writeString(BarcodeNo);
        dest.writeString(CreateDate);

    }

    private Urun(Parcel in) {

        UrunAdi = in.readString();
        UrunId = in.readInt();
        KategoriAdi=in.readString();
        Marka = in.readString();
        Model=in.readString();
        BarcodeNo = in.readString();
        CreateDate = in.readString();

        mInfo = in.readParcelable(Urun.class.getClassLoader());
    }

    public static final Parcelable.Creator<Urun> CREATOR
            = new Parcelable.Creator<Urun>() {
        @Override
        public Urun createFromParcel(Parcel source) {
            return new Urun(source);
        }

        @Override
        public Urun[] newArray(int size) {
            return new Urun[0];
        }
    };


    public void setUrunKategoriAdi(String UrunKategoriAdi) {
        UrunKategoriAdi = UrunKategoriAdi;
    }

    public int getUrunId() {
        return UrunId;
    }

    public void setUrunId(int urunId) {
        UrunId = urunId;
    }

    public String getUrunAdi() {
        return UrunAdi;
    }

    public void setUrunAdi(String urunAdi) {
        UrunAdi = urunAdi;
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
}
