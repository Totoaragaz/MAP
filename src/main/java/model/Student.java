package model;

import java.util.List;
import java.util.Objects;

/**
 * Student class
 */
public class Student extends Person{
    private long studentId;
    private int totalCredits;
    private List<Long> enrolledCourses;

    public Student(String firstName, String lastName, long studentId) {
        super(firstName, lastName);
        this.studentId = studentId;
    }

    public Student(String firstName, String lastName, long studentId, int totalCredits, List<Long> enrolledCourses) {
        super(firstName, lastName);
        this.studentId = studentId;
        this.totalCredits = totalCredits;
        this.enrolledCourses = enrolledCourses;
    }

    public Student(String firstName, String lastName, long studentId, int totalCredits) {
        super(firstName, lastName);
        this.studentId = studentId;
        this.totalCredits = totalCredits;
    }

    public Student() {
    }

    public void addCourse(long course){
        this.enrolledCourses.add(course);
    }

    public void removeCourse(long course){
        this.enrolledCourses.remove(course);
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public void setTotalCredits(int totalCredits) {
        this.totalCredits = totalCredits;
    }

    public void setEnrolledCourses(List<Long> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

    public long getStudentId() {
        return studentId;
    }

    public int getTotalCredits() {
        return totalCredits;
    }

    public List<Long> getEnrolledCourses() {
        return enrolledCourses;
    }

    @Override
    public boolean equals(Object o){
        if (this==o) return true;
        if (o==null || getClass()!=o.getClass()) return false;
        Student student=(Student) o;
        return studentId==student.studentId && Objects.equals(getFirstName(), student.getFirstName()) && Objects.equals(getLastName(), student.getLastName())
                && totalCredits==student.getTotalCredits() && enrolledCourses==student.getEnrolledCourses();
    }

    @Override
    public String toString(){
        return "Student { Name:"+ getFirstName()+"/"+getLastName()+", ID: " + studentId+", Kredite: "+ totalCredits+", Courses: "+enrolledCourses+'}';
    }
}
