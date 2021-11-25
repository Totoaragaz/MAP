package repository;

import model.Course;
import model.Student;
import model.Teacher;

import java.io.File;
import java.util.*;
import java.util.List;

/**
 * Controller class
 */
public class Kontroller {
    private CourseRepository courseRepository;
    private StudentRepository studentRepository;
    private TeacherRepository teacherRepository;

    public Kontroller(){
    }

    public CourseRepository getCourseRepository() {
        return courseRepository;
    }

    public void setCourseRepository(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public StudentRepository getStudentRepository() {
        return studentRepository;
    }

    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public TeacherRepository getTeacherRepository() {
        return teacherRepository;
    }

    public void setTeacherRepository(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    /**
     * gets the teacher data from the file repository
     * @param fileRepository file repository
     */

    public void getTeacherData(FileRepository fileRepository){
        this.teacherRepository= fileRepository.getAllTeachers();
    }

    /**
     * gets the course data from the file repository
     * @param fileRepository file repository
     */
    public void getCourseData(FileRepository fileRepository){
        this.courseRepository= fileRepository.getAllCourses();

    }

    /**
     * gets the student data from the file repository
     * @param fileRepository file repository
     */
    public void getStudentData(FileRepository fileRepository){
        this.studentRepository=fileRepository.getAllStudents();

    }

    /**
     * gets data from the file repository
     */
    public void getData(){
        FileRepository fileRepository= new FileRepository();
        getTeacherData(fileRepository);
        getCourseData(fileRepository);
        for (Course course: courseRepository.repoList){
            for (Teacher teacher: teacherRepository.repoList){
                if (teacher.getCourseNames().contains(course.getName())){
                    course.setTeacher(teacher);
                }
            }
            courseRepository.update(course);
        }
        getStudentData(fileRepository);
        for (Course course: courseRepository.repoList){
            for (Student student: studentRepository.repoList){
                if (student.getCourseNames().contains(course.getName())){
                    course.addStudent(student);
                }
            }
            courseRepository.update(course);
        }
    }

    /**
     * rewrites the file repository files with updated data
     */
    public void saveChanges(){
        FileRepository fileRepository= new FileRepository();
        fileRepository.writeCourses(courseRepository);
        fileRepository.writeStudents(studentRepository);
        fileRepository.writeTeachers(teacherRepository);
    }

    /**
     * displays all students
     */
    public void printAllStudents(){
        for (Student student: studentRepository.repoList){
            System.out.println(student);
        }
    }

    /**
     * displays all teachers
     */
    public void printAllTeachers(){
        for (Teacher teacher: teacherRepository.repoList){
            System.out.println(teacher);
        }
    }

    /**
     * displays all courses
     */
    public void printAllCourses(){
        for (Course course: courseRepository.repoList) {
            System.out.println(course);
        }
    }

    /**
     * adds a Student to studentRepository
     */
    public void addStudent(){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Vorname:");
        String vorname= keyboard.nextLine();
        System.out.println("Nachname:");
        String nachname= keyboard.nextLine();
        System.out.println("StudentId:");
        long id= keyboard.nextLong();
        System.out.println("Kredite:");
        int credits= keyboard.nextInt();
        System.out.println("Kursen:");
        keyboard.nextLine();
        String kursListe= keyboard.nextLine();
        List<Course> courses=new ArrayList<>();
        for (Course course:courseRepository.repoList){
            if (kursListe.contains(course.getName())){
                courses.add(course);
            }
        }
        studentRepository.create(new Student(vorname,nachname,id,credits,courses));
    }

    /**
     * adds a teacher to teacherRepository
     */
    public void addTeacher(){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Vorname:");
        String vorname= keyboard.nextLine();
        System.out.println("Nachname:");
        String nachname= keyboard.nextLine();
        System.out.println("TeacherId:");
        long id= keyboard.nextLong();
        System.out.println("Kursen:");
        keyboard.nextLine();
        String kursListe= keyboard.nextLine();
        List<Course> courses=new ArrayList<>();
        for (Course course:courseRepository.repoList){
            if (kursListe.contains(course.getName())){
                courses.add(course);
            }
        }
        teacherRepository.create(new Teacher(vorname,nachname,id,courses));
    }

    /**
     * adds a course to courseRepository
     */
    public void addCourse(){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Name:");
        String name= keyboard.nextLine();
        System.out.println("Teacher (ID):");
        long teacherid=keyboard.nextLong();
        Teacher courseTeacher=new Teacher();
        for (Teacher teacher: teacherRepository.repoList){
            if (teacher.getTeacherId()==teacherid){
                courseTeacher=teacher;
            }
        }
        if (courseTeacher.getFirstName()==null) {
            System.out.println("Invalid TeacherID.");
            return;
        }
        System.out.println("Max Enrollment:");
        int maxEnrollment= keyboard.nextInt();
        System.out.println("Kredite:");
        int credits= keyboard.nextInt();
        System.out.println("Enrolled Students (ID):");
        keyboard.nextLine();
        String studentListe= keyboard.nextLine();
        List<Student> students=new ArrayList<>();
        for (Student student: studentRepository.repoList){
            if (studentListe.contains(String.valueOf(student.getStudentId()))){
                students.add(student);
            }
        }
        courseRepository.create(new Course(name,courseTeacher,maxEnrollment,students,credits));
        courseTeacher.addCourse(new Course(name,courseTeacher,maxEnrollment,students,credits));
        teacherRepository.update(courseTeacher);
        for (Student student: students){
            student.addCourse(new Course(name,courseTeacher,maxEnrollment,students,credits));
            studentRepository.update(student);
        }
    }

    /**
     * deletes a student from studentRepository
     */
    public void deleteStudent(){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("StudentId:");
        long id= keyboard.nextLong();
        for (Student student: studentRepository.repoList){
            if (student.getStudentId()==id){
                studentRepository.delete(student);
                System.out.println("Student wurde erfolgreich geloscht.");
                return;
            }
        }
        System.out.println("Student wurde NICHT geloscht.");
    }

    /**
     * deletes a teacher from teacherRepository
     */
    public void deleteTeacher(){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("TeacherId:");
        long id= keyboard.nextLong();
        for (Teacher teacher: teacherRepository.repoList){
            if (teacher.getTeacherId()==id){
                for (Course course: courseRepository.repoList){
                    if (course.getTeacher().equals(teacher)){
                        course.setTeacher(null);
                        courseRepository.update(course);
                    }
                }
                teacherRepository.delete(teacher);
                System.out.println("Lehrer wurde erfolgreich geloscht.");
                return;
            }
        }
        System.out.println("Lehrer wurde NICHT geloscht.");
    }

    /**
     * deletes a course from courseRepository
     */
    public void deleteCourse(){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Kurs Name:");
        String name= keyboard.nextLine();
        for (Course course: courseRepository.repoList){
            if (course.getName().equals(name)){
                for (Teacher teacher: teacherRepository.repoList){
                    if (teacher.getCourses().contains(course)){
                        teacher.removeCourse(course);
                        teacherRepository.update(teacher);
                    }
                }
                for (Student student: studentRepository.repoList){
                    if(student.getEnrolledCourses().contains(course)){
                        student.removeCourse(course);
                        studentRepository.update(student);
                    }
                }
                courseRepository.delete(course);
                System.out.println("Kurs wurde erfolgreich geloscht.");
                return;
            }
        }
        System.out.println("Kurs wurde NICHT geloscht.");
    }

    /**
     * displays courses with free places
     */
    public void coursesWithFreePlaces(){
        for (Course course: courseRepository.repoList){
            if (course.getStudentsEnrolled().size()<course.getMaxEnrollment())
                System.out.println(course);
        }
    }

    /**
     * registers a student for a course
     */
    public void registerStudent(){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Kurs Name:");
        String kursName= keyboard.nextLine();
        System.out.println("StudentId:");
        long id= keyboard.nextLong();
        for (Course course: courseRepository.repoList){
            if (course.getName().equals(kursName)) {
                for (Student student: studentRepository.repoList) {
                    if (student.getStudentId()==id) {
                        if (course.getStudentsEnrolled().size() < course.getMaxEnrollment() && student.getTotalCredits()+course.getCredits()<=30) {
                            course.addStudent(student);
                            courseRepository.update(course);
                            student.addCourse(course);
                            studentRepository.update(student);
                            System.out.println("Student wurde erfolgreich angemeldet");
                            return;
                        }
                    }
                }
            }
        }
        System.out.println("Student wurde NICHT angemeldet");
    }

    /**
     * displays the students that are enrolled for a course
     */
    public void courseStudents(){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Kurs Name:");
        String kursName= keyboard.nextLine();
        for (Course course:courseRepository.repoList){
            if (course.getName().equals(kursName)){
                for (Student student: course.getStudentsEnrolled()){
                    System.out.println(student);
                }
            }
        }
    }

    /**
     * sorts Students by number of credits (descending)
     */
    public void sortStudents(){
        List<Student> studentList=studentRepository.repoList;
        Collections.sort(studentList, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                if (o1.getTotalCredits()<o2.getTotalCredits()) return 1;
                else if (o1.getTotalCredits()==o2.getTotalCredits()) return 0;
                return -1;
            }
        });
        for (Student student:studentRepository.repoList){
            System.out.println(student);
        }
    }

    /**
     * sorts Courses by number of credits (descending)
     */
    public void sortCourses(){
        List<Course> courseList=courseRepository.repoList;
        Collections.sort(courseList, new Comparator<Course>() {
            @Override
            public int compare(Course o1, Course o2) {
                if (o1.getCredits()<o2.getCredits()) return 1;
                else if (o1.getCredits()==o2.getCredits()) return 0;
                return -1;
            }
        });
        for (Course course: courseRepository.repoList){
            System.out.println(course);
        }
    }

    /**
     * only displays students that have at least 20 credits
     */
    public void filterStudents(){
        for (Student student: studentRepository.repoList){
            if (student.getTotalCredits()>=20){
                System.out.println(student);
            }
        }
    }

    /**
     * only displays courses that give at least 6 credits
     */
    public void filterCourses(){
        for (Course course: courseRepository.repoList){
            if (course.getCredits()>=6){
                System.out.println(course);
            }
        }
    }
}
