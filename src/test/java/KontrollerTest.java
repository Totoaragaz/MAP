import kontroller.Kontroller;
import org.junit.Test;
import static org.junit.Assert.*;

public class KontrollerTest {

    private final Kontroller kontroller = new Kontroller();

    @Test
    public void testAddStudent() {
        assertTrue(kontroller.addStudent("a", "a", 111111, 1));
        kontroller.deleteStudent(111111);
    }

    @Test
    public void testAddTeacher() {
        assertTrue(kontroller.addTeacher("b", "b", 222222));
        kontroller.deleteTeacher(222222);

    }

    @Test
    public void testAddCourse() {
        assertTrue(kontroller.addCourse("c", 333333, 2, 1, 1));
        kontroller.deleteCourse(333333);

    }

    @Test
    public void testRegisterStudent(){
        kontroller.addStudent("a", "a", 111111, 1);
        kontroller.addTeacher("b", "b", 222222);
        kontroller.addCourse("c", 333333, 2, 1, 1);
        assertTrue(kontroller.registerStudent(111111,333333)==1);
        kontroller.deleteStudent(111111);
        kontroller.deleteTeacher(222222);
        kontroller.deleteCourse(333333);
    }
}