package repository;

import model.Student;

/**
 * Student repository
 */
public class StudentRepository extends InMemoryRepository<Student>{

    public StudentRepository() {
        super();
    }

    @Override
    public Student update(Student obj) {
        Student studentToUpdate = this.repoList.stream()
                .filter(student -> student.getStudentId()==obj.getStudentId())
                .findFirst()
                .orElseThrow();

        studentToUpdate.setFirstName(obj.getFirstName());
        studentToUpdate.setLastName(obj.getLastName());
        studentToUpdate.setEnrolledCourses(obj.getEnrolledCourses());
        studentToUpdate.setTotalCredits(obj.getTotalCredits());

        return studentToUpdate;
    }
}
