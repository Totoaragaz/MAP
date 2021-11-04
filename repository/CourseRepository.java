package repository;

import model.Course;

/**
 * Course repository
 */
public class CourseRepository extends InMemoryRepository<Course>{

    public CourseRepository() {
        super();
    }

    @Override
    public Course update(Course obj) {
        Course courseToUpdate = this.repoList.stream()
                .filter(course -> course.getName() ==obj.getName())
                .findFirst()
                .orElseThrow();

        courseToUpdate.setTeacher(obj.getTeacher());
        courseToUpdate.setCredits(obj.getCredits());
        courseToUpdate.setMaxEnrollment(obj.getMaxEnrollment());
        courseToUpdate.setStudentsEnrolled(obj.getStudentsEnrolled());

        return courseToUpdate;
    }
}
