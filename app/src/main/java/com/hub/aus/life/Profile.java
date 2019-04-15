package com.hub.aus.life;

public class Profile {
    String name, string1, string2, batch, mobile, address;
    Profile(){

    }



    Profile(String name, String string1, String string2, String batch, String mobile, String address){
        this.name = name;
        this.string1 = string1;
        this.string2 = string2;
        this.batch = batch;
        this.mobile = mobile;
        this.address = address;
    }
    public String getName() {
        return name;
    }

    public String getString1() {
        return string1;
    }

    public String getString2() {
        return string2;
    }

    public String getBatch() {
        return batch;
    }

    public String getMobile() {
        return mobile;
    }

    public String getAddress() {
        return address;
    }
}
