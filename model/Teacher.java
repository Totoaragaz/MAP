package model;

import java.util.List;

/**
 * Teacher class
 */
public class Teacher extends Person{
    private long teacherId;
    private List<Course> courses;

    public Teacher(String firstName, String lastName, long teacherID, List<Course> courses) {
        super(firstName, lastName);
        this.teacherId = teacherId;
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

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public boolean equals(Object o){
        if (this==o) return true;
        if (o==null || getClass()!=o.getClass()) return false;
        Teacher teacher=(Teacher) o;
        return teacherId==teacher.teacherId && getFirstName()==teacher.getFirstName() &&
                getLastName()==teacher.getLastName() && courses==teacher.getCourses();
    }

    @Override
    public String toString(){
        return "Teacher { Name:"+ getFirstName()+"/"+getLastName()+", ID: " + teacherId+", Courses: " +getCourses() +'}';
    }
}
