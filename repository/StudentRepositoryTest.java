package repository;

import model.Student;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Student Repository Test
 */
class StudentRepositoryTest {
    /**
     * create test
     */
    @Test
    void testCreate() {
        StudentRepository studentRepository=new StudentRepository();
        Student studentAlex=new Student("Alex","Arbaleta",1231);
        studentRepository.create(studentAlex);
        assertEquals(1,studentRepository.repoList.size(),0);
    }

    /**
     * getAll test
     */
    @Test
    void testGetAll() {
        StudentRepository studentRepository=new StudentRepository();
        List<Student> studentList=new ArrayList<>();
        Student studentAlex=new Student("Alex","Arbaleta",1231);
        studentRepository.create(studentAlex);
        studentList.add(studentAlex);
        assertEquals(studentList,studentRepository.getAll());
    }

    /**
     * delete test
     */
    @Test
    void testDelete(){
        StudentRepository studentRepository=new StudentRepository();
        Student studentAlex=new Student("Alex","Arbaleta",1231);
        studentRepository.create(studentAlex);
        studentRepository.delete(studentAlex);
        assertEquals(0,studentRepository.repoList.size(),0);
    }

    /**
     * update test
     */
    @Test
    void testUpdate(){
        StudentRepository studentRepository=new StudentRepository();
        studentRepository.create(new Student("Alex","Arbalta",1231));
        studentRepository.update(new Student("Alex","Arbaleta",1231));
        assertEquals(studentRepository.repoList.get(0), new Student("Alex", "Arbaleta", 1231));
    }
}