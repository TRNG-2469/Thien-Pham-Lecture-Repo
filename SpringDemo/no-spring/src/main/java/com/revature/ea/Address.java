package com.revature.ea;

public class Address {
    private String city;
    private String state;
    private int zipCode;


    public Address(){

    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Address{" + "city=" + city + ", state=" + state + ", zipCode=" + zipCode + '}';
    }
}

