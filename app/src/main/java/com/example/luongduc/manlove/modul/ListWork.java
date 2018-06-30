package com.example.luongduc.manlove.modul;

public class ListWork {
    public int imgUser;
    public String nameUser;
    public String ageUser;
    public String workUser;
    public byte[] byteImgArr;


    public ListWork(int imgUser, String nameUser, String ageUser, String workUser) {
        this.imgUser = imgUser;
        this.nameUser = nameUser;
        this.ageUser = ageUser;
        this.workUser = workUser;
    }

    public ListWork(byte[] byteImgArr, String nameUser, String ageUser, String workUser) {
        this.byteImgArr = byteImgArr;
        this.nameUser = nameUser;
        this.ageUser = ageUser;
        this.workUser = workUser;
    }

    public byte[] getByteImgArr() {
        return byteImgArr;
    }

    public void setByteImgArr(byte[] byteImgArr) {
        this.byteImgArr = byteImgArr;
    }

    public int getImgUser() {
        return imgUser;
    }

    public void setImgUser(int imgUser) {
        this.imgUser = imgUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getAgeUser() {
        return ageUser;
    }

    public void setAgeUser(String ageUser) {
        this.ageUser = ageUser;
    }

    public String getWorkUser() {
        return workUser;
    }

    public void setWorkUser(String workUser) {
        this.workUser = workUser;
    }
}
