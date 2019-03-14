package com.example.ipc.aidl;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable{
    private String name;
    private int id;

    public Book() {

    }

    public Book(String name, int id) {
        this.name=name;
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id=id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
    }

    public static final Creator<Book> CREATOR = new Parcelable.Creator<Book>(){

        @Override
        public Book createFromParcel(Parcel parcel) {
           return new Book(parcel);
        }

        @Override
        public Book[] newArray(int i) {
            return new Book[i];
        }
    };

    private Book(Parcel source) {
        id = source.readInt();
        name = source.readString();
    }

    public void readFromParcel(Parcel reply) {
        id = reply.readInt();
        name=reply.readString();
    }

}