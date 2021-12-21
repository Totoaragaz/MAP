package repository;

import model.Teacher;

/**
 * Teacher repository
 */
public class TeacherRepository extends InMemoryRepository<Teacher>{

    public TeacherRepository() {
        super();
    }

    @Override
    public Teacher update(Teacher obj) {
        Teacher teacherToUpdate = this.repoList.stream()
                .filter(teacher -> teacher.getTeacherId()==obj.getTeacherId())
                .findFirst()
                .orElseThrow();

        teacherToUpdate.setFirstName(obj.getFirstName());
        teacherToUpdate.setLastName(obj.getLastName());
        teacherToUpdate.setCourses(obj.getCourses());

        return teacherToUpdate;
    }
}
