package model;

import java.util.List;
import java.util.Objects;

/**
 * Teacher class
 */
public class Teacher extends Person{
    private long teacherId;
    private List<Long> courses;

    public Teacher(String firstName, String lastName, long teacherID, List<Long> courses) {
        super(firstName, lastName);
        this.teacherId = teacherID;
        this.courses = courses;
    }

    public Teacher(String firstName, String lastName, long teacherId) {
        super(firstName, lastName);
        this.teacherId = teacherId;
    }

    public long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }

    public Teacher() {
    }

    public void addCourse(long course){
        courses.add(course);
    }

    public void removeCourse(long course){
        courses.remove(course);
    }

    public List<Long> getCourses() {
        return courses;
    }

    public void setCourses(List<Long> courses) {
        this.courses = courses;
    }

    @Override
    public boolean equals(Object o){
        if (this==o) return true;
        if (o==null || getClass()!=o.getClass()) return false;
        Teacher teacher=(Teacher) o;
        return teacherId==teacher.teacherId && Objects.equals(getFirstName(), teacher.getFirstName()) &&
                Objects.equals(getLastName(), teacher.getLastName()) && courses==teacher.getCourses();
    }

    @Override
    public String toString(){
        return "Teacher { Name:"+ getFirstName()+"/"+getLastName()+", ID: " + teacherId+", Courses: " + courses +'}';
    }
}
