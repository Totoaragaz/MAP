package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Course class
 */
public class Course {
    private String name;
    private long teacher;
    private long courseId;
    private int maxEnrollment;
    private List<Long> studentsEnrolled;
    private int credits;

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public Course(String name, long courseId, long teacher, int maxEnrollment, int credits) {
        this.name = name;
        this.courseId=courseId;
        this.teacher = teacher;
        this.maxEnrollment = maxEnrollment;
        this.studentsEnrolled=new ArrayList<>();
        this.credits=credits;
    }

    public Course(String name,long courseId, int maxEnrollment, int credits) {
        this.name = name;
        this.courseId=courseId;
        this.maxEnrollment = maxEnrollment;
        this.credits=credits;
        this.studentsEnrolled=new ArrayList<>();
    }

    public Course(String name,long courseId, long teacher, int maxEnrollment, int credits, List<Long> studentsEnrolled) {
        this.name = name;
        this.courseId=courseId;
        this.teacher = teacher;
        this.maxEnrollment = maxEnrollment;
        this.studentsEnrolled = studentsEnrolled;
        this.credits = credits;
    }

    public Course() {
    }

    public void addStudent(long student){
        studentsEnrolled.add(student);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTeacher() {
        return teacher;
    }

    public void setTeacher(long teacher) {
        this.teacher = teacher;
    }

    public int getMaxEnrollment() {
        return maxEnrollment;
    }

    public void setMaxEnrollment(int maxEnrollment) {
        this.maxEnrollment = maxEnrollment;
    }

    public List<Long> getStudentsEnrolled() {
        return studentsEnrolled;
    }

    public void setStudentsEnrolled(List<Long> studentsEnrolled) {
        this.studentsEnrolled = studentsEnrolled;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    @Override
    public boolean equals(Object o){
        if (this==o) return true;
        if (o==null || getClass()!=o.getClass()) return false;
        Course course=(Course) o;
        return Objects.equals(name, course.name) && credits==course.credits && Objects.equals(teacher,course.teacher) &&
                studentsEnrolled==course.studentsEnrolled && maxEnrollment==course.maxEnrollment;
    }


    @Override
    public String toString(){
        return "Course { Name: "+ name+ ", Id: " + courseId +", Teacher: " + teacher +", Credits: " + credits+ ", Max Enrollment:" + maxEnrollment+'}';
    }
}
