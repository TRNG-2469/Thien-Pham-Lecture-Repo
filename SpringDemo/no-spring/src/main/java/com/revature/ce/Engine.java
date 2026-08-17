package com.revature.ce;

public class Engine {
    private String type;
    private int horsePower;


    public Engine (String type, int horsePower){
        this.type =type;
        this.horsePower = horsePower;
    }

    @Override
    public String toString(){
        return "Engine{" +
                "type='" + type + '\'' +
                ", horsePower=" + horsePower +
                '}';
    }
}
