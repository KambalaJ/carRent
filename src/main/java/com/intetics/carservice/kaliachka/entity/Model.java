package com.intetics.carservice.kaliachka.entity;

public class Model {
    private int modelId;
    private String name;
    private Maker maker;

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Maker getMaker() {
        return maker;
    }

    public void setMaker(Maker maker) {
        this.maker = maker;
    }

    @Override
    public String toString() {
        return "Model{" +
                "modelId=" + modelId +
                ", name='" + name + '\'' +
                ", maker=" + maker +
                '}';
    }
}
