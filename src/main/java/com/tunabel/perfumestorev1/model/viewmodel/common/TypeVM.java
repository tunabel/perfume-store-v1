package com.tunabel.perfumestorev1.model.viewmodel.common;

public class TypeVM {

    private int id;
    private String name;

    public TypeVM(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public TypeVM() {
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
