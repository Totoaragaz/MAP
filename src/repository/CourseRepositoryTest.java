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
        Teacher teacher=new Teacher("Gigel","Ciocanel",7811);
        courseRepository.create(new Course("MAP",teacher,80,6));
        assertEquals(1,courseRepository.repoList.size(),0);
    }

    /**
     * getAll test
     */
    @Test
    void testGetAll(){
        CourseRepository courseRepository=new CourseRepository();
        Teacher teacher=new Teacher("Gigel","Ciocanel",7811);
        List<Course> courseList=new ArrayList<>();
        Course course=new Course("MAP",teacher,80,6);
        courseRepository.create(course);
        courseList.add(course);
        assertEquals(courseRepository.getAll(),courseList);
    }

    /**
     * delete test
     */
    @Test
    void testDelete(){
        CourseRepository courseRepository=new CourseRepository();
        Teacher teacher=new Teacher("Gigel","Ciocanel",7811);
        Course course=new Course("MAP",teacher,80,6);
        courseRepository.create(course);
        courseRepository.delete(course);
        assertEquals(0,courseRepository.repoList.size(),0);
    }

    /**
     * update test
     */
    @Test
    void testUpdate() {
        CourseRepository courseRepository=new CourseRepository();
        Teacher teacher=new Teacher("Gigel","Ciocanel",7811);
        Course course=new Course("MAP",teacher,80,6);
        Course newCourse=new Course("MAP",teacher,90,7);
        courseRepository.create(course);
        courseRepository.update(newCourse);
        assertEquals(courseRepository.repoList.get(0), newCourse);
    }
}