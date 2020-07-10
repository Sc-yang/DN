package com.priv.singlevalue;

public class SingleValue {

    private String name;
    private int age;
    private double salary;
    private boolean isMan;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setMan(boolean isMan) {

        this.isMan = isMan;
    }


    @Override
    public String toString() {
        return "SingleValue{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", isMan=" + isMan +
                '}';
    }
}
