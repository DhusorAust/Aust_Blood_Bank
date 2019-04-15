package com.hub.aus.life;


public class Info {

private String name,password,batch,mobile,address;
private String string1,string2;


    public Info(String name, String string1, String string2, String batch, String mobile, String address) {
        this.name = name;
        //this.password = password;
        this.string1 = string1;
        this.string2 = string2;
        this.batch = batch;
        this.mobile = mobile;
        this.address = address;
    }

    public String getName() {
        return name;
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

    public String getString1() {
        return string1;
    }

    public String getString2() {
        return string2;
    }
}

