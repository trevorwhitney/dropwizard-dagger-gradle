package com.trevorwhitney.helloworld.helloworld;

public class UserBean {

    private String name;
    private String address;
    public int randomId;

    public UserBean(){
        randomId = (int)(Math.random() *100);
    }

    public String getName() {
        return "Bob Builder";
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return "123 Fake Street";
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
