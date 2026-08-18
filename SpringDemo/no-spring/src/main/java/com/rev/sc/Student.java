package com.rev.sc;

public class Student {
    private String name;
    private int id;
    private Course course;

    public Student(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return "Student{ " + name + "Course: " + course + "id: " + id+"}";
    }
}


