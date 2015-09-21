package com.intetics.carservice.kaliachka.entity;

import java.util.Date;
import java.util.List;
import java.util.UUID;


public class Car {
    private int vin;
    private Model model;
    private Date year;
    private List<Order> orders;


    public int getVin() {
        return vin;
    }

    public void setVin(int vin) {
        this.vin = vin;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Car{" +
                "vin=" + vin +
                ", model=" + model +
                ", year=" + year +
                ", orders=" + orders +
                '}';
    }
}
