package com.revature.ce;

public class Car {
    private String model;
    private double price;
    private Engine engine;

    public Car (String model, double price, Engine engine){
        this.model = model;
        this.price = price;
        this.engine = engine;
    }

    @Override
    public String toString(){
        return "Car{" +
                "model='" + model + '\'' +
                ", price=" + price +
                ", engine=" + engine +
                '}';
    }
}
