package com.sevenpeakssoftware.khinthirisoe.data.db.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Unique;

@Entity(nameInDb = "content")
public class ArticleContent implements Parcelable {

    @Unique
    @Property(nameInDb = "id")
    private int id;

    @Property(nameInDb = "title")
    private String title;

    @Property(nameInDb = "datetime")
    private String datetime;

    @Property(nameInDb = "ingress")
    private String ingress;

    @Property(nameInDb = "image")
    private String url;

    @Generated(hash = 819763091)
    public ArticleContent(int id, String title, String datetime, String ingress, String url) {
        this.id = id;
        this.title = title;
        this.datetime = datetime;
        this.ingress = ingress;
        this.url = url;
    }

    protected ArticleContent(Parcel in) {
        id = in.readInt();
        title = in.readString();
        datetime = in.readString();
        ingress = in.readString();
        url = in.readString();
    }

    @Generated(hash = 949142728)
    public ArticleContent() {
    }

    public static final Creator<ArticleContent> CREATOR = new Creator<ArticleContent>() {
        @Override
        public ArticleContent createFromParcel(Parcel in) {
            return new ArticleContent(in);
        }

        @Override
        public ArticleContent[] newArray(int size) {
            return new ArticleContent[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(datetime);
        dest.writeString(ingress);
        dest.writeString(url);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int carId) {
        this.id = carId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDatetime() {
        return this.datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getIngress() {
        return this.ingress;
    }

    public void setIngress(String ingress) {
        this.ingress = ingress;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
