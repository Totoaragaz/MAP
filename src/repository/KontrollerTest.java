package repository;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class KontrollerTest {

    private final Kontroller kontroller = new Kontroller();
    private final FileRepository fileRepository = new FileRepository();

    @Test
    void testGetTeacherData() {
        kontroller.getTeacherData(fileRepository);
        assertNotEquals(kontroller.getTeacherRepository(), null);
    }

    @Test
    void testGetCourseData() {
        kontroller.getCourseData(fileRepository);
        assertNotEquals(kontroller.getCourseRepository(), null);
    }

    @Test
    void testGetStudentData() {
        kontroller.getStudentData(fileRepository);
        assertNotEquals(kontroller.getStudentRepository(), null);
    }

    @Test
    void testAddStudent() {
        kontroller.getData();
        assertTrue(kontroller.addStudent("a", "a", 1, 1, ""));
    }

    @Test
    void testAddTeacher() {
        kontroller.getData();
        assertTrue(kontroller.addTeacher("b", "b", 2, ""));
    }

    @Test
    void testAddCourse() {
        kontroller.getData();
        kontroller.addTeacher("b", "b", 2, "");
        assertTrue(kontroller.addCourse("c", 2, 10, 1, ""));
    }

    @Test
    void testDeleteStudent() {
        kontroller.getData();
        kontroller.addStudent("a", "a", 1, 1, "");
        assertTrue(kontroller.deleteStudent(1));
    }

    @Test
    void testDeleteTeacher() {
        kontroller.getData();
        kontroller.addTeacher("b", "b", 2, "");
        assertTrue(kontroller.deleteTeacher(2));
    }

    @Test
    void testDeleteCourse() {
        kontroller.getData();
        kontroller.addTeacher("b", "b", 2, "");
        kontroller.addCourse("c", 2, 10, 1, "");
        assertTrue(kontroller.deleteCourse("c"));
    }

    @Test
    void testRegisterStudent(){
        kontroller.getData();
        kontroller.addStudent("a", "a", 1, 1, "");
        kontroller.addTeacher("b", "b", 2, "");
        kontroller.addCourse("c", 2, 10, 1, "");
        assertTrue(kontroller.registerStudent("c",1));
    }
}