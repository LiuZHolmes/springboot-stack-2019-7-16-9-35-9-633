package com.tw.apistackbase.Class;

public class Employee {
    private long employeeID;
    private String name;
    private int age;
    private String gender;
    private int salary;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public long getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(long employeeID) {
        this.employeeID = employeeID;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
