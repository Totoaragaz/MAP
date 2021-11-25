package repository;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class KontrollerTest {

    private Kontroller kontroller=new Kontroller();

    @Test
    void testGetTeacherData(){
        FileRepository fileRepository= new FileRepository();
        kontroller.getTeacherData(fileRepository);
        assertNotEquals(kontroller.getTeacherRepository(),null);
    }

    @Test
    void testGetCourseData(){
        FileRepository fileRepository= new FileRepository();
        kontroller.getCourseData(fileRepository);
        assertNotEquals(kontroller.getCourseRepository(),null);
    }

    @Test
    void testGetStudentData(){
        FileRepository fileRepository= new FileRepository();
        kontroller.getStudentData(fileRepository);
        assertNotEquals(kontroller.getStudentRepository(),null);
    }
}
