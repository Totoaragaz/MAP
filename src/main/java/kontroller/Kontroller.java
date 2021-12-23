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
     * adds a student to the database
     * @return true if the student was added, false otherwise
     */
    public boolean addStudent(String vorname, String nachname,long id,int credits){
        if (studentRepository.create(new Student(vorname,nachname,id,credits))!=null){
            System.out.println("Student wurde erfolgreich eingefügt");
            return true;
        }
        System.out.println("Student wurde nicht eingefügt");
        return false;
    }

    /**
     * adds a teacher to the database
     * @return true if the teacher was added, false otherwise
     */
    public boolean addTeacher(String vorname, String nachname,long id){
        if (teacherRepository.create(new Teacher(vorname,nachname,id))!=null){
            System.out.println("Lehrer wurde erfolgreich eingefügt");
            return true;
        }
        System.out.println("Lehrer wurde nicht eingefügt");
        return false;
    }

    /**
     * finds the teacher corresponding to an Id
     * @param teacherId Id of the teacher
     * @return the teacher if found
     */

    public Teacher findTeacher (long teacherId){
        for (Teacher teacher: teacherRepository.getAll()){
            if (teacher.getTeacherId()==teacherId){
                return teacher;
            }
        }
        throw new InvalidValue("Invalid TeacherID.");
    }

    /**
     * finds the student corresponding to an Id
     * @param studentId Id of the student
     * @return the student if found
     */

    public Student findStudent (long studentId){
        for (Student student: studentRepository.getAll()){
            if (student.getStudentId()==studentId){
                return student;
            }
        }
        throw new InvalidValue("Invalid StudentID.");
    }

    /**
     * finds the course corresponding to an Id
     * @param courseId Id of the course
     * @return the course if found
     */

    public Course findCourse (long courseId){
        for (Course course: courseRepository.getAll()){
            if (course.getCourseId()==courseId){
                return course;
            }
        }
        throw new InvalidValue("Invalid CourseID.");
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
        Teacher courseTeacher=findTeacher(teacherId);
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
        Student student=findStudent(id);
        studentRepository.delete(student);
        System.out.println("Student wurde erfolgreich gelöscht.");
    }

    /**
     * deletes a teacher from the database
     */
    public void deleteTeacher(long id){
        Teacher teacher=findTeacher(id);
        teacherRepository.delete(teacher);
        System.out.println("Lehrer wurde erfolgreich gelöscht.");
    }

    /**
     * deletes a course from the database
     */
    public void deleteCourse(long id){
        Course course=findCourse(id);
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
     * @return true if student was registered, false otherwise
     */
    public boolean registerStudent(long studentId, long courseId){
        if (findCourse(courseId).getMaxEnrollment()==findCourse(courseId).getStudentsEnrolled().size()) throw new FullCourse("Kurs hat keine freie Plätze.");
        if (findStudent(studentId).getTotalCredits()+findCourse(courseId).getCredits()>30) throw new CreditLimit("Student hat zu viele Kredite");
        Connection connection=studentRepository.openConnection();
        String query="insert into enrolledstudents (studentId,courseId) values ("+ studentId +","+ courseId+")";
        try {
            Statement statement=connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        studentRepository.closeConnection(connection);
        Student new_student =findStudent(studentId);
        new_student.setTotalCredits(findStudent(studentId).getTotalCredits()+findCourse(courseId).getCredits());
        studentRepository.update(new_student);
        System.out.println("Student wurde erfolgreich angemeldet");
        return true;
    }

    /**
     * displays the students that are enrolled for a course
     */
    public void courseStudents(long courseId){
        Connection connection=studentRepository.openConnection();
        String query="select * from enrolledstudents where courseId=" +courseId;
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(query);
            while (resultSet.next()){
                System.out.print(resultSet.getLong("studentId"));
            }
            System.out.println();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        studentRepository.closeConnection(connection);

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
}
