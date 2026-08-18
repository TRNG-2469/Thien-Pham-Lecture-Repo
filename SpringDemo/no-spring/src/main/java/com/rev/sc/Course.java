package com.rev.sc;

public class Course {
    private String courseID;
    private String courseName;
    private String duration;


    public Course (){

    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString(){
        return "Course{ " + courseName + "Course: " + courseID + "id: " + duration+"}";
    }
}
