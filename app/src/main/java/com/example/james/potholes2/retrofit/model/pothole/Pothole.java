package com.example.james.potholes2.retrofit.model.pothole;

/**
 * Created by James on 4/13/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Pothole extends RealmObject{

    @PrimaryKey
    @SerializedName("potholeID")
    @Expose
    private Integer potholeID;

    @SerializedName("userID")
    @Expose
    private Integer userID;

    @SerializedName("location")
    @Expose
    private Location location;

    @SerializedName("fixed")
    @Expose
    private Integer fixed;

    @SerializedName("modified")
    @Expose
    private Integer modified;



    public Integer getPotholeID() {
        return potholeID;
    }

    public void setPotholeID(Integer potholeID) {
        this.potholeID = potholeID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Integer getFixed() {
        return fixed;
    }

    public void setFixed(Integer fixed) {
        this.fixed = fixed;
    }

    public Integer getModified() {
        return modified;
    }

    public void setModified(Integer modified) {
        this.modified = modified;
    }

    public static void create(Realm realm, Pothole p)
    {
        //Parent parent = realm.where(Parent.class).findFirst();
        //RealmList<Pothole> potholes = parent.getPotholeList();
        realm.copyToRealmOrUpdate(p);
        //potholes.add(p);
    }
}
