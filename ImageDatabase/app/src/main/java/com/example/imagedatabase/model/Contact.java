package com.example.imagedatabase.model;

public class Contact {

    private int id;
    private String name;
    private String branch;
    private String home;
    private String date;
    private String about;

    public byte[] getImage() {
        return Image;
    }

    public void setImage(byte[] image) {
        Image = image;
    }

    private byte[] Image;


    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Contact(String name) {
        this.name = name;
    }

    public Contact(int id, String name, String branch) {
        this.id = id;
        this.name = name;
        this.branch = branch;
    }

    public Contact(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
