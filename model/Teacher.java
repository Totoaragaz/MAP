package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Teacher class
 */
public class Teacher extends Person{
    private long teacherId;
    private List<Course> courses;

    public Teacher(String firstName, String lastName, long teacherID, List<Course> courses) {
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

    public void addCourse(Course course){
        courses.add(course);
    }

    public void removeCourse(Course course){
        courses.remove(course);
    }

    public List<Course> getCourses() {
        return courses;
    }

    public List<String> getCourseNames() {
        List<String> courseNames=new ArrayList<>();
        for (Course i: courses)
            courseNames.add(i.getName());
        return courseNames;
    }

    public void setCourses(List<Course> courses) {
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
        return "Teacher { Name:"+ getFirstName()+"/"+getLastName()+", ID: " + teacherId+", Courses: " +getCourseNames() +'}';
    }
}
