/**
 * @Author: parallelline1996
 * @Email: chenyu1996a@163.com
 * @Date: 2020/2/25 0:32
 */

package com.paralleline1996.request.entity;

public class Person {

    private Integer age;
    private String name;
    private Double weight;

    public Person() {

    }

    public Person(Integer age, String name, Double weight) {
        this.age = age;
        this.name = name;
        this.weight = weight;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                '}';
    }
}
