package repository;

import model.Course;
import model.Teacher;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * course repository test
 */
class CourseRepositoryTest {
    /**
     * create test
     */
    @Test
    void testCreate(){
        CourseRepository courseRepository=new CourseRepository();
        assertEquals(0,courseRepository.repoList.size(),0);
        Teacher teacher=new Teacher("Gigel","Ciocanel",7811);

        courseRepository.create(new Course("MAP",teacher,80,6));
        assertEquals(1,courseRepository.repoList.size(),0);
        assertTrue(courseRepository.repoList.contains(new Course("MAP",teacher,80,6)));

        courseRepository.create(new Course("Igiena Personala",teacher,60,8));
        assertEquals(2,courseRepository.repoList.size(),0);
        assertTrue(courseRepository.repoList.contains(new Course("Igiena Personala",teacher,60,8)));
    }

    /**
     * getAll test
     */
    @Test
    void testGetAll(){
        CourseRepository courseRepository=new CourseRepository();
        Teacher teacher=new Teacher("Gigel","Ciocanel",7811);
        List<Course> courseList=new ArrayList<>();
        assertEquals(courseRepository.getAll(),courseList);

        courseRepository.create(new Course("MAP",teacher,80,6));
        courseList.add(new Course("MAP",teacher,80,6));
        assertEquals(courseRepository.getAll(),courseList);

        courseRepository.create(new Course("Igiena Personala",teacher,60,8));
        courseList.add(new Course("Igiena Personala",teacher,60,8));
        assertEquals(courseRepository.getAll(),courseList);
    }

    /**
     * delete test
     */
    @Test
    void testDelete(){
        CourseRepository courseRepository=new CourseRepository();
        assertEquals(0,courseRepository.repoList.size(),0);
        Teacher teacher=new Teacher("Gigel","Ciocanel",7811);

        courseRepository.create(new Course("MAP",teacher,80,6));
        courseRepository.create(new Course("Igiena Personala",teacher,60,8));

        assertEquals(2,courseRepository.repoList.size(),0);
        assertTrue(courseRepository.repoList.contains(new Course("MAP",teacher,80,6)));
        assertTrue(courseRepository.repoList.contains(new Course("Igiena Personala",teacher,60,8)));

        courseRepository.delete(new Course("MAP",teacher,80,6));
        assertEquals(1,courseRepository.repoList.size(),0);
        assertFalse(courseRepository.repoList.contains(new Course("MAP",teacher,80,6)));
        assertTrue(courseRepository.repoList.contains(new Course("Igiena Personala",teacher,60,8)));

        courseRepository.delete(new Course("Igiena Personala",teacher,60,8));
        assertEquals(0,courseRepository.repoList.size(),0);
        assertFalse(courseRepository.repoList.contains(new Course("Igiena Personala",teacher,60,8)));
    }

    /**
     * update test
     */
    @Test
    void testUpdate() {
        CourseRepository courseRepository=new CourseRepository();
        Teacher teacher=new Teacher("Gigel","Ciocanel",7811);

        courseRepository.create(new Course("MAP",teacher,80,6));
        assertTrue(courseRepository.repoList.get(0).equals(new Course("MAP",teacher,80,6)));
        assertFalse(courseRepository.repoList.get(0).equals(new Course("MAP",teacher,90,7)));

        courseRepository.update(new Course("MAP",teacher,90,7));
        assertFalse(courseRepository.repoList.get(0).equals(new Course("MAP",teacher,80,6)));
        assertTrue(courseRepository.repoList.get(0).equals(new Course("MAP",teacher,90,7)));
    }
}