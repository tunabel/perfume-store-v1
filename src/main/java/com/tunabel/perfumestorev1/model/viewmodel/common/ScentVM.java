package com.tunabel.perfumestorev1.model.viewmodel.common;

public class ScentVM {

    private int id;
    private String name;

    public ScentVM(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public ScentVM() {
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
