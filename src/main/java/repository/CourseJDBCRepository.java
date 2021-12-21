package repository;

import model.Course;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CourseJDBCRepository extends JDBCRepository<Course>{

    public CourseJDBCRepository(){
        super();
    }

    /**
     * adds a course to the repository
     * @param course the created course
     * @return the created course
     */
    @Override
    public Course create (Course course){
        Connection connection=openConnection();
        String query="insert into course values ('"+ course.getName()+"','"+ course.getCourseId()+"',"
                +course.getTeacher()+","+course.getMaxEnrollment()+","+course.getCredits()+")";
        try {
            Statement statement=connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(connection);

        return course;
    }

    /**
     * returns all courses in the repository
     * @return a list of all courses in the repository
     */
    @Override
    public List<Course> getAll () {
        List<Course> courses = new ArrayList<>();
        Connection connection = openConnection();
        String query = "select * from course";
        try {
            Statement courseStatement = connection.createStatement();
            ResultSet courseResultSet = courseStatement.executeQuery(query);
            while (courseResultSet.next()) {
                List<Long> enrolledStudents = new ArrayList<>();
                query = "select * from enrolledstudents where courseId=" + courseResultSet.getLong("courseId");
                Statement studentStatement = connection.createStatement();
                ResultSet studentResultSet = studentStatement.executeQuery(query);
                while (studentResultSet.next()) {
                    enrolledStudents.add(studentResultSet.getLong("studentId"));
                }
                courses.add(new Course(courseResultSet.getString("courseName"), courseResultSet.getLong("courseId"), courseResultSet.getLong("teacherId"), courseResultSet.getInt("maxEnrollment"), courseResultSet.getInt("credits"), enrolledStudents));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(connection);
        return courses;
    }

    /**
     * updates the course with the same Id
     * @param course the new course
     * @return the new course
     */
    @Override
    public Course update(Course course){
        Connection connection=openConnection();
        String query="update course set courseName='"+course.getName()+"',teacherId="+course.getTeacher()+
                ",maxEnrollment="+course.getMaxEnrollment()+",credits="+course.getCredits()+ " where courseId="+ course.getCourseId();
        try {
            Statement statement=connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(connection);
        return course;
    }

    /**
     * deletes a course from the repository
     * @param course the course we want to delete
     */
    @Override
    public void delete(Course course){
        Connection connection=openConnection();
        String query="delete from course_teachers where courseId="+ course.getCourseId();
        try {
            Statement statement=connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        query="delete from enrolledstudents where courseId="+ course.getCourseId();
        try {
            Statement statement=connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        query="delete from course where courseId="+ course.getCourseId();
        try {
            Statement statement=connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(connection);
    }
}
