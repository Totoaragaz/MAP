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
        assertEquals(0,studentRepository.repoList.size(),0);

        Student studentAlex=new Student("Alex","Arbaleta",1231);
        studentRepository.create(studentAlex);
        assertEquals(1,studentRepository.repoList.size(),0);
        assertTrue(studentRepository.repoList.contains(studentAlex));

        Student studentMaria=new Student("Maria","Mitraliera",5472);
        studentRepository.create(studentMaria);
        assertEquals(2,studentRepository.repoList.size(),0);
        assertTrue(studentRepository.repoList.contains(studentMaria));
    }

    /**
     * getAll test
     */
    @Test
    void testGetAll() {
        StudentRepository studentRepository=new StudentRepository();
        List<Student> studentList=new ArrayList<>();
        assertEquals(studentList,studentRepository.getAll());

        Student studentAlex=new Student("Alex","Arbaleta",1231);
        studentRepository.create(studentAlex);
        studentList.add(studentAlex);
        assertEquals(studentList,studentRepository.getAll());

        Student studentMaria=new Student("Maria","Mitraliera",5472);
        studentRepository.create(studentMaria);
        studentList.add(studentMaria);
        assertEquals(studentList,studentRepository.getAll());
    }

    /**
     * delete test
     */
    @Test
    void testDelete(){
        StudentRepository studentRepository=new StudentRepository();
        assertEquals(0,studentRepository.repoList.size(),0);

        Student studentAlex=new Student("Alex","Arbaleta",1231);
        studentRepository.create(studentAlex);
        Student studentMaria=new Student("Maria","Mitraliera",5472);
        studentRepository.create(studentMaria);
        assertEquals(2,studentRepository.repoList.size(),0);
        assertTrue(studentRepository.repoList.contains(studentAlex));
        assertTrue(studentRepository.repoList.contains(studentMaria));

        studentRepository.delete(studentAlex);
        assertEquals(1,studentRepository.repoList.size(),0);
        assertFalse(studentRepository.repoList.contains(studentAlex));
        assertTrue(studentRepository.repoList.contains(studentMaria));

        studentRepository.delete(studentMaria);
        assertEquals(studentRepository.repoList.size(),0,0);
        assertFalse(studentRepository.repoList.contains(studentMaria));
    }

    /**
     * update test
     */
    @Test
    void testUpdate(){
        StudentRepository studentRepository=new StudentRepository();

        studentRepository.create(new Student("Alex","Arbalta",1231));
        assertTrue(studentRepository.repoList.get(0).equals(new Student("Alex","Arbalta",1231)));
        assertFalse(studentRepository.repoList.get(0).equals(new Student("Alex","Arbaleta",1231)));

        studentRepository.update(new Student("Alex","Arbaleta",1231));
        assertFalse(studentRepository.repoList.get(0).equals(new Student("Alex","Arbalta",1231)));
        assertTrue(studentRepository.repoList.get(0).equals(new Student("Alex","Arbaleta",1231)));
    }
}