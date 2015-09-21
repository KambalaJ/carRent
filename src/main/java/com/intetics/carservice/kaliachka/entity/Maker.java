package com.intetics.carservice.kaliachka.entity;

public class Maker {
    private int makerId;
    private String name;

    public int getMakerId() {
        return makerId;
    }

    public void setMakerId(int makerId) {
        this.makerId = makerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Maker{" +
                "makerId=" + makerId +
                ", name='" + name + '\'' +
                '}';
    }
}
