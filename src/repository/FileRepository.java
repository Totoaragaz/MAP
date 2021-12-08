package repository;

import model.Course;
import model.Student;
import model.Teacher;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * File Repository for all repositories
 */
public class FileRepository {

    public FileRepository(){
    }

    /**
     * Reads all Students from Students.txt
     * @return StudentRepository with all students from the file
     */
    public StudentRepository getAllStudents(){
        StudentRepository studentRepository=new StudentRepository();
        CourseRepository courseRepository=getAllCourses();
        TeacherRepository teacherRepository=getAllTeachers();
        for (Course course: courseRepository.repoList){
            for (Teacher teacher: teacherRepository.repoList){
                if (teacher.getCourses().contains(course)){
                    course.setTeacher(teacher);
                }
            }
            courseRepository.update(course);
        }
        try {
            String firstName="";
            String lastName="";
            long studentId=0;
            int credits=0;
            List<Course> allCourses = courseRepository.getAll();
            File file = new File("Students.txt");
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                List<Course> studentCourses=new ArrayList<>();
                String data = myReader.nextLine();
                String word= "";
                int counter=0;
                for (int i=0;i<data.length();i++){
                    if (data.charAt(i)=='.'){
                        if (counter==0) firstName=word;
                        else if (counter==1) lastName=word;
                        else if (counter==2) studentId=Long.parseLong(word);
                        else if (counter==3) credits=Integer.parseInt(word);
                        else{
                            for (Course course : allCourses) {
                                if (course.getName().equals(word)) studentCourses.add(course);
                            }
                        }
                        word="";
                        counter+=1;
                    }
                    else word+=data.charAt(i);
                }
                studentRepository.create(new Student(firstName,lastName,studentId,credits,studentCourses));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return studentRepository;
    }

    /**
     * Reads all Teachers from Teachers.txt
     * @return TeacherRepository with all teachers from the file
     */
    public TeacherRepository getAllTeachers(){
        TeacherRepository teacherRepository=new TeacherRepository();
        CourseRepository courseRepository=getAllCourses();
        try {
            String firstName="";
            String lastName="";
            long teacherId=0;

            List<Course> allCourses=courseRepository.getAll();
            File file = new File("Teachers.txt");
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                List<Course> teacherCourses =new ArrayList<>();
                String data = myReader.nextLine();
                String word= "";
                int counter=0;
                for (int i=0;i<data.length();i++) {

                    if (data.charAt(i) == '.') {
                        if (counter == 0) firstName = word;
                        else if (counter == 1) lastName = word;
                        else if (counter == 2) teacherId = Long.parseLong(word);
                        else {
                            for (Course course : allCourses) {
                                if (course.getName().equals(word)) teacherCourses.add(course);
                            }
                        }
                        word = "";
                        counter += 1;
                    } else word += data.charAt(i);
                }
                teacherRepository.create(new Teacher(firstName,lastName,teacherId,teacherCourses));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return teacherRepository;
    }

    /**
     * Reads all Courses from Courses.txt
     * @return CourseRepository with all Courses from the file
     */
    public CourseRepository getAllCourses(){
        CourseRepository courseRepository=new CourseRepository();
        try {
            String name="";
            int maxEnrollment=0;
            int credits=0;
            File file = new File("Courses.txt");
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String word= "";
                int counter=0;
                for (int i=0;i<data.length();i++){
                    if (data.charAt(i)=='.'){
                        if (counter==0) name=word;
                        else if (counter==1) maxEnrollment=Integer.parseInt(word);
                        else credits=Integer.parseInt(word);
                        word="";
                        counter+=1;
                    }
                    else word+=data.charAt(i);
                }
                courseRepository.create(new Course(name,maxEnrollment,credits));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return courseRepository;
    }

    /**
     * Rewrites the Students.txt file with updated data
     * @param studentRepository Student Repository after running the program
     */
    public void writeStudents(StudentRepository studentRepository) {
        try {
            FileWriter writer = new FileWriter("Students.txt");
            for (Student student: studentRepository.repoList){
                writer.write(student.getFirstName()+'.'+student.getLastName()+'.'+student.getStudentId()+'.'+student.getTotalCredits()+'.');
                for (Course course: student.getEnrolledCourses()){
                    writer.write(course.getName()+'.');
                }
                writer.write('\n');
            }
            writer.close();
        } catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Rewrites the Teachers.txt file with updated data
     * @param teacherRepository Teacher Repository after running the program
     */
    public void writeTeachers(TeacherRepository teacherRepository){
        try {
            FileWriter writer = new FileWriter("Teachers.txt");
            for (Teacher teacher: teacherRepository.repoList){
                writer.write(teacher.getFirstName()+'.'+teacher.getLastName()+'.'+teacher.getTeacherId()+'.');
                for (Course course: teacher.getCourses()){
                    writer.write(course.getName()+'.');
                }
                writer.write('\n');
            }
            writer.close();
        } catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Rewrites the Courses.txt file with updated data
     * @param courseRepository Course Repository after running the program
     */
    public void writeCourses(CourseRepository courseRepository){
        try {
            FileWriter writer = new FileWriter("Courses.txt");
            for (Course course: courseRepository.repoList){
                writer.write(course.getName()+'.'+course.getMaxEnrollment()+'.'+course.getCredits()+".\n");
            }
            writer.close();
        } catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}
