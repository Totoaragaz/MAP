import model.Course;
import model.Teacher;
import org.junit.Test;
import repository.CourseJDBCRepository;
import repository.TeacherJDBCRepository;

import static org.junit.Assert.*;

public class CourseJDBCRepositoryTest {

    private final CourseJDBCRepository courseJDBCRepository=new CourseJDBCRepository();
    private final TeacherJDBCRepository teacherJDBCRepository=new TeacherJDBCRepository();

    @Test
    public void testCreate (){
        teacherJDBCRepository.create(new Teacher("b","b",222222));
        assertNotNull(courseJDBCRepository.create(new Course("a",333333,222222,1,1)));
        courseJDBCRepository.delete(new Course("a",333333,222222,1,1));
        teacherJDBCRepository.delete(new Teacher("b","b",222222));
    }

    @Test
    public void testGetAll (){
        teacherJDBCRepository.create(new Teacher("b","b",222222));
        courseJDBCRepository.create(new Course("a",333333,222222,1,1));
        assertNotNull(courseJDBCRepository.getAll());
        courseJDBCRepository.delete(new Course("a",333333,222222,1,1));
        teacherJDBCRepository.delete(new Teacher("b","b",222222));
    }

    @Test
    public void testUpdate(){
        teacherJDBCRepository.create(new Teacher("b","b",222222));
        courseJDBCRepository.create(new Course("a",333333,222222,1,1));
        courseJDBCRepository.update(new Course("c",333333,222222,3,3));
        for (Course course:courseJDBCRepository.getAll()){
            if (course.getCourseId()==333333){
                assertEquals(course.getCredits(),3);
            }
        }
        courseJDBCRepository.delete(new Course("c",333333,222222,3,3));
        teacherJDBCRepository.delete(new Teacher("b","b",222222));
    }

    @Test
    public void testDelete(){
        teacherJDBCRepository.create(new Teacher("b","b",222222));
        courseJDBCRepository.create(new Course("a",333333,222222,1,1));
        int oldsize=courseJDBCRepository.getAll().size();
        courseJDBCRepository.delete(new Course("a",333333,222222,1,1));
        assertEquals(courseJDBCRepository.getAll().size(),oldsize-1);
        teacherJDBCRepository.delete(new Teacher("b","b",222222));
    }

}
