package com.example.bat_main;

public class retrive {

    private String id;
    private String fname;
    private String lname;
    private String whend;
    private String whered;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getWhend() {
        return whend;
    }

    public void setWhend(String whend) {
        this.whend = whend;
    }

    public String getWhered() {
        return whered;
    }

    public void setWhered(String whered) {
        this.whered = whered;
    }

    public retrive(String id, String fname, String lname, String whend, String whered) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.whend = whend;
        this.whered = whered;
    }


}
