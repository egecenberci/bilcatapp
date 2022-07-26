package com.beyza.bilcat;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;

public class CommentData {

    private String txtComment, userID, userName;
    private CatData catData;
    private FirebaseAuth fAuth;
    private int currentCat;
    private LatLng pingLocation;
    private String locationToString;

    public CommentData(){}

    public CommentData(String txtComment, CatData catData, int currentCat, LatLng pingLocation) {
        this.txtComment = txtComment;
        this.catData = catData;
        this.currentCat = currentCat;
        this.pingLocation = pingLocation;
        this.locationToString = pingLocation.toString();
        fAuth = FirebaseAuth.getInstance();
        userID = fAuth.getCurrentUser().getUid();
        userName = fAuth.getCurrentUser().getEmail();

    }

    public String getTxtComment() {
        return txtComment;
    }

    public void setTxtComment(String txtComment) {
        this.txtComment = txtComment;
    }

    public CatData getCatData() {
        return catData;
    }

    public void setCatData(CatData catData) {
        this.catData = catData;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getCurrentCat() {
        return currentCat;
    }

    public void setCurrentCat(int currentCat) {
        this.currentCat = currentCat;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LatLng getPingLocation(){
        return this.pingLocation;
    }
}
