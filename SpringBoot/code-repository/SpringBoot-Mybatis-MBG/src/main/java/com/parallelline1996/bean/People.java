package com.parallelline1996.bean;

public class People {
    private Integer id;

    private Integer age;

    private Double weight;

    private Double height;

    private String name;

    public People(Integer age, Double weight, Double height, String name) {
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.name = name;
    }

    public People(Integer id, Integer age, Double weight, Double height, String name) {
        this.id = id;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.name = name;
    }

    public People() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}