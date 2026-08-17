package com.rev.sp;

public class Student {

    // field of the student
    private String name;
    private int age;
    private int id;

    // a default no args constructor for set injection
    public Student(){

    }

    // a constructor for full parameters for constructor injections
    public Student(String name, int age, int id){
        this.name = name;
        this.age = age;
        this.id = id;
    }

    // set and get method to set and retrive the info


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // a method to return everything to string
    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "', age=" + age + "}";
    }
}
