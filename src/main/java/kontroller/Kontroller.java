package kontroller;

import exceptions.CreditLimit;
import exceptions.FullCourse;
import exceptions.InvalidValue;
import model.Course;
import model.Student;
import model.Teacher;
import repository.CourseJDBCRepository;
import repository.StudentJDBCRepository;
import repository.TeacherJDBCRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Controller class
 */
public class Kontroller {
    private CourseJDBCRepository courseRepository;
    private StudentJDBCRepository studentRepository;
    private TeacherJDBCRepository teacherRepository;

    public Kontroller(){
        courseRepository=new CourseJDBCRepository();
        studentRepository=new StudentJDBCRepository();
        teacherRepository=new TeacherJDBCRepository();
    }

    public CourseJDBCRepository getCourseRepository() {
        return courseRepository;
    }

    public void setCourseRepository(CourseJDBCRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public StudentJDBCRepository getStudentRepository() {
        return studentRepository;
    }

    public void setStudentRepository(StudentJDBCRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public TeacherJDBCRepository getTeacherRepository() {
        return teacherRepository;
    }

    public void setTeacherRepository(TeacherJDBCRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    /**
     * displays all students
     */
    public void printAllStudents(){
        for (Student student: studentRepository.getAll()){
            System.out.println(student);
        }
    }

    /**
     * displays all teachers
     */
    public void printAllTeachers(){
        for (Teacher teacher: teacherRepository.getAll()){
            System.out.println(teacher);
        }
    }

    /**
     * displays all courses
     */
    public void printAllCourses(){
        for (Course course: courseRepository.getAll()) {
            System.out.println(course);
        }
    }

    /**
     * returns all courses in a list
     * @return list of all courses
     */
    public List<Course> getAllCourses(){
        List<Course> courseList=new ArrayList<>();
        for (Course course: courseRepository.getAll()) {
            courseList.add(course);
        }
        return courseList;
    }

    /**
     * adds a student to the database
     * @return true if the student was added, false otherwise
     */
    public boolean addStudent(String vorname, String nachname,long id,int credits){
        if (studentRepository.create(new Student(vorname,nachname,id,credits))!=null){
            return true;
        }
        return false;
    }

    /**
     * adds a teacher to the database
     * @return true if the teacher was added, false otherwise
     */
    public boolean addTeacher(String vorname, String nachname,long id){
        if (teacherRepository.create(new Teacher(vorname,nachname,id))!=null){
            return true;
        }
        return false;
    }

    /**
     * finds the teacher corresponding to an Id
     * @param teacherId Id of the teacher
     * @return the teacher if found
     */

    public Teacher findTeacherById (long teacherId){
        for (Teacher teacher: teacherRepository.getAll()){
            if (teacher.getTeacherId()==teacherId){
                return teacher;
            }
        }
        return null;
    }

    /**
     * finds the teacher corresponding to a name
     * @param firstName first name of the teacher
     * @param lastName last name of the teacher
     * @return the teacher if found, null otherwise
     */

    public Teacher findTeacherByName (String firstName, String lastName){
        for (Teacher teacher: teacherRepository.getAll()){
            if (teacher.getFirstName().equals(firstName) && teacher.getLastName().equals(lastName)){
                return teacher;
            }
        }
        return null;
    }

    /**
     * finds the student corresponding to an Id
     * @param studentId Id of the student
     * @return the student if found
     */

    public Student findStudentById (long studentId){
        for (Student student: studentRepository.getAll()){
            if (student.getStudentId()==studentId){
                return student;
            }
        }
        return null;
    }

    /**
     * finds the student corresponding to a name
     * @param firstName first name of the student
     * @param lastName last name of the student
     * @return the student if found, null otherwise
     */

    public Student findStudentByName (String firstName, String lastName){
        for (Student student: studentRepository.getAll()){
            if (student.getFirstName().equals(firstName) && student.getLastName().equals(lastName)){
                return student;
            }
        }
        return null;
    }

    /**
     * finds the course corresponding to an Id
     * @param courseId Id of the course
     * @return the course if found
     */

    public Course findCourseById (long courseId){
        for (Course course: courseRepository.getAll()){
            if (course.getCourseId()==courseId){
                return course;
            }
        }
        return null;
    }

    /**
     * pairs a course to a teacher in the course_teachers table
     * @param teacherId id of the teacher
     * @param courseId id of the course
     * @return true if the pair was added to the table, false otherwise
     */


    public boolean addCourseToTeacher (long teacherId,long courseId){
        Connection connection=teacherRepository.openConnection();
        String query="insert into course_teachers (teacherId,courseId) values ("+ teacherId +","+ courseId+")";
        try {
            Statement statement=connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        teacherRepository.closeConnection(connection);
        return true;
    }

    /**
     * adds a course to the database
     * @return true if the course was added, false otherwise
     */
    public boolean addCourse(String name,long courseId,long teacherId,int maxEnrollment,int credits){
        Teacher courseTeacher=findTeacherById(teacherId);
        if (courseRepository.create(new Course(name,courseId,teacherId,maxEnrollment,credits))!=null) {
            addCourseToTeacher(teacherId,courseId);
            System.out.println("Kurs wurde erfolgreich eingefügt");
            return true;
        }
        System.out.println("Kurs wurde nicht eingefügt");
        return false;
    }

    /**
     * deletes a student from the database
     */
    public void deleteStudent(long id){
        Student student=findStudentById(id);
        studentRepository.delete(student);
        System.out.println("Student wurde erfolgreich gelöscht.");
    }

    /**
     * deletes a teacher from the database
     */
    public void deleteTeacher(long id){
        Teacher teacher=findTeacherById(id);
        teacherRepository.delete(teacher);
        System.out.println("Lehrer wurde erfolgreich gelöscht.");
    }

    /**
     * deletes a course from the database
     */
    public void deleteCourse(long id){
        Course course=findCourseById(id);
        courseRepository.delete(course);
        System.out.println("Kurs wurde erfolgreich gelöscht.");
    }

    /**
     * displays courses with free places
     */
    public void coursesWithFreePlaces(){
        for (Course course: courseRepository.getAll()){
            if (course.getStudentsEnrolled().size()<course.getMaxEnrollment())
                System.out.println(course);
        }
    }

    /**
     * registers a student for a course
     * @return 0 if student cannot be added, 1 if student was added, 2 if the course doesnt have any free places,
     * 3 if the student has too many credits, 4 if the student is already enrolled for the course
     */
    public int registerStudent(long studentId, long courseId){
        if (findCourseById(courseId).getMaxEnrollment()==findCourseById(courseId).getStudentsEnrolled().size()) return 2;
        if (findStudentById(studentId).getTotalCredits()+findCourseById(courseId).getCredits()>30) return 3;
        if (findCourseById(courseId).getStudentsEnrolled().contains(studentId)) return 4;
        Connection connection=studentRepository.openConnection();
        String query="insert into enrolledstudents (studentId,courseId) values ("+ studentId +","+ courseId+")";
        try {
            Statement statement=connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
        studentRepository.closeConnection(connection);
        Student new_student =findStudentById(studentId);
        new_student.setTotalCredits(findStudentById(studentId).getTotalCredits()+findCourseById(courseId).getCredits());
        studentRepository.update(new_student);
        return 1;
    }

    /**
     * displays the students that are enrolled for a course
     */
    public List<Student> courseStudents(long courseId){
        List<Student> studentList=new ArrayList<>();
        Connection connection=studentRepository.openConnection();
        String query="select * from enrolledstudents where courseId=" +courseId;
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(query);
            while (resultSet.next()){
                studentList.add(findStudentById(resultSet.getLong("studentId")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        studentRepository.closeConnection(connection);
        return studentList;
    }

    /**
     * sorts Students by number of credits (descending)
     */
    public void sortStudents(){
        List<Student> studentList=studentRepository.getAll();
        Collections.sort(studentList, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                if (o1.getTotalCredits()<o2.getTotalCredits()) return 1;
                else if (o1.getTotalCredits()==o2.getTotalCredits()) return 0;
                return -1;
            }
        });
        for (Student student: studentList){
            System.out.println(student);
        }
    }

    /**
     * sorts Courses by number of credits (descending)
     */
    public void sortCourses(){
        List<Course> courseList=courseRepository.getAll();
        Collections.sort(courseList, new Comparator<Course>() {
            @Override
            public int compare(Course o1, Course o2) {
                if (o1.getCredits()<o2.getCredits()) return 1;
                else if (o1.getCredits()==o2.getCredits()) return 0;
                return -1;
            }
        });
        for (Course course: courseList){
            System.out.println(course);
        }
    }

    /**
     * only displays students that have at least 20 credits
     */
    public void filterStudents(){
        for (Student student: studentRepository.getAll()){
            if (student.getTotalCredits()>=20){
                System.out.println(student);
            }
        }
    }

    /**
     * only displays courses that give at least 6 credits
     */
    public void filterCourses() {
        for (Course course : courseRepository.getAll()) {
            if (course.getCredits() >= 6) {
                System.out.println(course);
            }
        }
    }

    /**
     * finds the first free StudentId
     * @return first free StudentId
     */

    public long firstFreeStudentId(){
        for (int i=1;i<9999999;i++){
            if (findStudentById(i)==null) return i;
        }
        return -1;
    }
}
