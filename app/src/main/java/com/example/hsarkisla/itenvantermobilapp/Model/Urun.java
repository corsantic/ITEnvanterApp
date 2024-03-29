package com.example.hsarkisla.itenvantermobilapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by pc on 26.07.2017.
 */

public class Urun implements Parcelable,Cloneable{


    @SerializedName("UrunId")
    @Expose
    private int UrunId;
    @SerializedName("UrunAciklama")
    @Expose
    private String UrunAciklama ;
    @SerializedName("Model")
    @Expose
    private String Model;
    @SerializedName("BarcodeNo")
    @Expose
    private String BarkodNo ;
    @SerializedName("CreateDate")
    @Expose
    private String CreateDate ;
    @SerializedName("Marka")
    @Expose
    private String Marka;
    @SerializedName("KategoriAdi")
    @Expose
    private String KategoriAdi;
    @SerializedName("KategoriId")
    @Expose
    private int KategoriId;
    @SerializedName("CreateId")
    @Expose
    private int CreateId;
    @SerializedName("UrunAdi")
    @Expose
    private String UrunAdi;


    private boolean selected;
    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }



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
        dest.writeString(UrunAciklama);
        dest.writeInt(UrunId);
        dest.writeString(KategoriAdi);
        dest.writeString(Marka);
        dest.writeString(Model);
        dest.writeString(BarkodNo);
        dest.writeString(CreateDate);
        dest.writeInt(CreateId);

    }

    private Urun(Parcel in) {
        UrunAdi=in.readString();
        UrunAciklama = in.readString();
        UrunId = in.readInt();
        KategoriAdi=in.readString();
        Marka = in.readString();
        Model=in.readString();
        BarkodNo = in.readString();
        CreateDate = in.readString();
        CreateId=in.readInt();

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

    public String getUrunAdi() {
        return UrunAdi;
    }

    public void setUrunAdi(String urunAdi) {
        UrunAdi = urunAdi;
    }


    public int getUrunId() {
        return UrunId;
    }

    public void setUrunId(int urunId) {
        UrunId = urunId;
    }

    public String getUrunAciklama() {
        return UrunAciklama;
    }

    public void setUrunAciklama(String urunAciklama) {
        UrunAciklama = urunAciklama;
    }



    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getBarkodNo() {
        return BarkodNo;
    }

    public void setBarkodNo(String barkodNo) {
        BarkodNo = barkodNo;
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

    public int getCreateId() {
        return CreateId;
    }

    public void setCreateId(int createId) {
        CreateId = createId;
    }
}
