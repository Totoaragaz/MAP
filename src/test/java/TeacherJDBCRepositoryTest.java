import model.Teacher;
import org.junit.Test;
import repository.TeacherJDBCRepository;

import static org.junit.Assert.*;

public class TeacherJDBCRepositoryTest {
    private final TeacherJDBCRepository teacherJDBCRepository=new TeacherJDBCRepository();

    @Test
    public void testCreate (){
        assertNotNull(teacherJDBCRepository.create(new Teacher("b","b",222222)));
        teacherJDBCRepository.delete(new Teacher("b","b",222222));
    }

    @Test
    public void testGetAll (){
        teacherJDBCRepository.create(new Teacher("b","b",222222));
        assertNotNull(teacherJDBCRepository.getAll());
        teacherJDBCRepository.delete(new Teacher("b","b",222222));
    }

    @Test
    public void testUpdate(){
        teacherJDBCRepository.create(new Teacher("b","b",222222));
        teacherJDBCRepository.update(new Teacher("d","d",222222));
        for (Teacher teacher: teacherJDBCRepository.getAll()){
            if (teacher.getTeacherId()==111111){
                assertEquals(teacher.getFirstName(),"d");
            }
        }
        teacherJDBCRepository.delete(new Teacher("d","d",222222));
    }

    @Test
    public void testDelete(){
        teacherJDBCRepository.create(new Teacher("b","b",222222));
        int oldsize=teacherJDBCRepository.getAll().size();
        teacherJDBCRepository.delete(new Teacher("b","b",222222));
        assertEquals(teacherJDBCRepository.getAll().size(),oldsize-1);
    }
}
