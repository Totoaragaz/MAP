import model.Student;
import org.junit.Test;
import repository.StudentJDBCRepository;

import static org.junit.Assert.*;

public class StudentJDBCRepositoryTest {
    private final StudentJDBCRepository studentJDBCRepository= new StudentJDBCRepository();

    @Test
    public void testCreate (){
        assertNotNull(studentJDBCRepository.create(new Student("a","a",111111,1)));
        studentJDBCRepository.delete(new Student("a","a",111111,1));
    }

    @Test
    public void testGetAll (){
        studentJDBCRepository.create(new Student("a","a",111111,1));
        assertNotNull(studentJDBCRepository.getAll());
        studentJDBCRepository.delete(new Student("a","a",111111,1));
    }

    @Test
    public void testUpdate(){
        studentJDBCRepository.create(new Student("a","a",111111,1));
        studentJDBCRepository.update(new Student("b","b",111111,5));
        for (Student student:studentJDBCRepository.getAll()){
            if (student.getStudentId()==111111){
                assertEquals(student.getTotalCredits(),5);
            }
        }
        studentJDBCRepository.delete(new Student("b","b",111111,5));
    }

    @Test
    public void testDelete(){
        studentJDBCRepository.create(new Student("a","a",111111,1));
        int oldsize=studentJDBCRepository.getAll().size();
        studentJDBCRepository.delete(new Student("a","a",111111,1));
        assertEquals(studentJDBCRepository.getAll().size(),oldsize-1);
    }
}
