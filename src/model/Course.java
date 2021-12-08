package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Course class
 */
public class Course {
    private String name;
    private Person teacher;
    private int maxEnrollment;
    private List<Student> studentsEnrolled;
    private int credits;

    public Course(String name, Person teacher, int maxEnrollment, int credits) {
        this.name = name;
        this.teacher = teacher;
        this.maxEnrollment = maxEnrollment;
        this.studentsEnrolled=new ArrayList<>();
        this.credits=credits;
    }

    public Course(String name, int maxEnrollment, int credits) {
        this.name = name;
        this.maxEnrollment = maxEnrollment;
        this.credits=credits;
        this.studentsEnrolled=new ArrayList<>();
    }

    public Course(String name, Person teacher, int maxEnrollment, List<Student> studentsEnrolled, int credits) {
        this.name = name;
        this.teacher = teacher;
        this.maxEnrollment = maxEnrollment;
        this.studentsEnrolled = studentsEnrolled;
        this.credits = credits;
    }

    public Course() {
    }

    public void addStudent(Student student){
        studentsEnrolled.add(student);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getTeacher() {
        return teacher;
    }

    public String getTeacherName() {
        if (teacher!=null) return teacher.getFirstName()+'/'+teacher.getLastName();
        return "null";
    }

    public void setTeacher(Person teacher) {
        this.teacher = teacher;
    }

    public int getMaxEnrollment() {
        return maxEnrollment;
    }

    public void setMaxEnrollment(int maxEnrollment) {
        this.maxEnrollment = maxEnrollment;
    }

    public List<Student> getStudentsEnrolled() {
        return studentsEnrolled;
    }

    public void setStudentsEnrolled(List<Student> studentsEnrolled) {
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
        return "Course { Name:"+ name+", Teacher: "+getTeacherName()+", Credits: " + credits+ ", Max Enrollment:" + maxEnrollment+'}';
    }
}
