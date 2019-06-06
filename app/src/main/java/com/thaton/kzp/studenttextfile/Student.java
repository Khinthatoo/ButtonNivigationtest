package com.thaton.kzp.studenttextfile;

public class Student {
    String name,year,uniName;

    public Student(){}

    public Student(String name, String year, String uniName) {
        this.name = name;
        this.year = year;
        this.uniName = uniName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getUniName() {
        return uniName;
    }

    public void setUniName(String uniName) {
        this.uniName = uniName;
    }
}
