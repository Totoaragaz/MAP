package repository;

import model.Teacher;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * teacher repository test
 */
class TeacherRepositoryTest {
    /**
     * create test
     */
    @Test
    void testCreate(){
        TeacherRepository teacherRepository=new TeacherRepository();
        assertEquals(0,teacherRepository.repoList.size(),0);

        teacherRepository.create(new Teacher("Gigel","Ciocanel",7811));
        assertEquals(1,teacherRepository.repoList.size(),0);
        assertTrue(teacherRepository.repoList.contains(new Teacher("Gigel","Ciocanel",7811)));

        teacherRepository.create(new Teacher("Maricel","Copacel",7235));
        assertEquals(2,teacherRepository.repoList.size(),0);
        assertTrue(teacherRepository.repoList.contains(new Teacher("Maricel","Copacel",7235)));
    }

    /**
     * getAll test
     */
    @Test
    void testGetAll(){
        TeacherRepository teacherRepository=new TeacherRepository();
        List<Teacher> teacherList=new ArrayList<>();
        assertEquals(teacherRepository.getAll(),teacherList);

        teacherRepository.create(new Teacher("Gigel","Ciocanel",7811));
        teacherList.add(new Teacher("Gigel","Ciocanel",7811));
        assertEquals(teacherRepository.getAll(),teacherList);

        teacherRepository.create(new Teacher("Maricel","Copacel",7235));
        teacherList.add(new Teacher("Maricel","Copacel",7235));
        assertEquals(teacherRepository.getAll(),teacherList);
    }

    /**
     * delete test
     */
    @Test
    void testDelete(){
        TeacherRepository teacherRepository=new TeacherRepository();
        assertEquals(0,teacherRepository.repoList.size(),0);

        teacherRepository.create(new Teacher("Gigel","Ciocanel",7811));
        teacherRepository.create(new Teacher("Maricel","Copacel",7235));

        assertEquals(2,teacherRepository.repoList.size(),0);
        assertTrue(teacherRepository.repoList.contains(new Teacher("Gigel","Ciocanel",7811)));
        assertTrue(teacherRepository.repoList.contains(new Teacher("Maricel","Copacel",7235)));

        teacherRepository.delete(new Teacher("Gigel","Ciocanel",7811));
        assertEquals(1,teacherRepository.repoList.size(),0);
        assertFalse(teacherRepository.repoList.contains(new Teacher("Gigel","Ciocanel",7811)));
        assertTrue(teacherRepository.repoList.contains(new Teacher("Maricel","Copacel",7235)));

        teacherRepository.delete(new Teacher("Maricel","Copacel",7235));
        assertEquals(0,teacherRepository.repoList.size(),0);
        assertFalse(teacherRepository.repoList.contains(new Teacher("Maricel","Copacel",7235)));
    }

    /**
     * update test
     */
    @Test
    void testUpdate() {
        TeacherRepository teacherRepository=new TeacherRepository();

        teacherRepository.create(new Teacher("Gigel","Ciocanel",7811));
        assertTrue(teacherRepository.repoList.get(0).equals(new Teacher("Gigel","Ciocanel",7811)));
        assertFalse(teacherRepository.repoList.get(0).equals(new Teacher("Dorel","Ciocanel",7811)));

        teacherRepository.update(new Teacher("Dorel","Ciocanel",7811));
        assertFalse(teacherRepository.repoList.get(0).equals(new Teacher("Gigel","Ciocanel",7811)));
        assertTrue(teacherRepository.repoList.get(0).equals(new Teacher("Dorel","Ciocanel",7811)));
    }
}