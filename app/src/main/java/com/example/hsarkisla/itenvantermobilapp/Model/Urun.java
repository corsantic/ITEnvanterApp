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
    @SerializedName("UrunAdi")
    @Expose
    private String UrunAciklama ;
    @SerializedName("Model")
    @Expose
    private String Model;
    @SerializedName("BarkodNo")
    @Expose
    private String BarcodeNo ;
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
    private boolean selected;
    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }


    public  Urun(int UrunId,String UrunAciklama,String Model,String BarcodeNo,String CreateDate,String Marka,String KategoriAdi,int KategoriId,int CreateId)
    {
        this.UrunId=UrunId;
        this.UrunAciklama=UrunAciklama;
        this.Model=Model;
        this.Marka=Marka;
        this.BarcodeNo=BarcodeNo;
        this.CreateDate=CreateDate;
        this.KategoriAdi=KategoriAdi;
        this.KategoriId=KategoriId;
        this.CreateId=CreateId;

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
        dest.writeString(UrunAciklama);
        dest.writeInt(UrunId);
        dest.writeString(KategoriAdi);
        dest.writeString(Marka);
        dest.writeString(Model);
        dest.writeString(BarcodeNo);
        dest.writeString(CreateDate);
        dest.writeInt(CreateId);

    }

    private Urun(Parcel in) {

        UrunAciklama = in.readString();
        UrunId = in.readInt();
        KategoriAdi=in.readString();
        Marka = in.readString();
        Model=in.readString();
        BarcodeNo = in.readString();
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

    public int getCreateId() {
        return CreateId;
    }

    public void setCreateId(int createId) {
        CreateId = createId;
    }
}
