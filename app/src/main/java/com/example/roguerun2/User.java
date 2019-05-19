package com.example.roguerun2;

public class User {

    private String ID;
    private double age;
    private double height;
    private double bmi;
    private double weight;
    private double bodyFat;
    private Boolean workOut1, workOut2, workOut3, workOut4;


    public User(String ID, double age, double height, double bmi, double weight, double bodyFat, Boolean workOut1, Boolean workOut2, Boolean workOut3, Boolean workOut4) {
        this.ID = ID;
        this.age = age;
        this.height = height;
        this.bmi = bmi;
        this.weight = weight;
        this.bodyFat = bodyFat;
        this.workOut1 = false;
        this.workOut2 = workOut2;
        this.workOut3 = workOut3;
        this.workOut4 = workOut4;
    }



    public User(){}

    public User(String ID, Boolean workOut1, Boolean workOut2, Boolean workOut3, Boolean workOut4) {
        this.ID = ID;
        this.workOut1 = workOut1;
        this.workOut2 = workOut2;
        this.workOut3 = workOut3;
        this.workOut4 = workOut4;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Double getBodyFat() {
        return bodyFat;
    }

    public void setBodyFat(double bodyFat) {
        this.bodyFat = bodyFat;
    }

    public Boolean getWorkOut1() {
        return workOut1;
    }

    public void setWorkOut1(Boolean workOut1) {
        this.workOut1 = workOut1;
    }

    public Boolean getWorkOut2() {
        return workOut2;
    }

    public void setWorkOut2(Boolean workOut2) {
        this.workOut2 = workOut2;
    }

    public Boolean getWorkOut3() {
        return workOut3;
    }

    public void setWorkOut3(Boolean workOut3) {
        this.workOut3 = workOut3;
    }

    public Boolean getWorkOut4() {
        return workOut4;
    }

    public void setWorkOut4(Boolean workOut4) {
        this.workOut4 = workOut4;
    }

    public double getAge() {
        return age;
    }

    public double getHeight() {
        return height;
    }

    public double getBmi() {
        return bmi;
    }

    public double getWeight() {
        return weight;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {

        this.ID = ID;
    }


}
