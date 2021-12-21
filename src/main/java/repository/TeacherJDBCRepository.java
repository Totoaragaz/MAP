package repository;

import model.Teacher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TeacherJDBCRepository extends JDBCRepository<Teacher>{

    public TeacherJDBCRepository() {
        super();
    }

    /**
     * adds a teacher to the repository
     * @param teacher the created teacher
     * @return the created teacher
     */
    @Override
    public Teacher create (Teacher teacher){
        Connection connection=openConnection();
        String query="insert into teacher values ('"+ teacher.getFirstName()+"','"+
                teacher.getLastName()+"',"+teacher.getTeacherId()+")";
        try {
            Statement statement=connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(connection);
        return teacher;
    }


    /**
     * returns all teachers in the repository
     * @return a list of all teachers
     */
    @Override
    public List<Teacher> getAll (){
        List<Teacher> teachers = new ArrayList<>();
        Connection connection=openConnection();
        String query="select * from teacher";
        try {
            Statement teacherStatement=connection.createStatement();
            ResultSet teacherResultSet=teacherStatement.executeQuery(query);
            while (teacherResultSet.next()){
                List<Long> teacherCourses=new ArrayList<>();
                query="select * from course_teachers where teacherId='"+teacherResultSet.getLong("teacherId")+"'";
                Statement courseStatement=connection.createStatement();
                ResultSet courseResultSet=courseStatement.executeQuery(query);
                while (courseResultSet.next()){
                    teacherCourses.add(courseResultSet.getLong("courseId"));
                }
                teachers.add(new Teacher(teacherResultSet.getString("firstName"),teacherResultSet.getString("lastName"), teacherResultSet.getLong("teacherId"),teacherCourses));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(connection);
        return teachers;
    }

    /**
     * updates the teacher with the same Id
     * @param teacher the new teacher
     * @return the new teacher
     */
    @Override
    public Teacher update(Teacher teacher){
        Connection connection=openConnection();
        String query="update teacher set firstName='"+teacher.getFirstName()+"',lastName='"+teacher.getLastName()+
                "',teacherId="+teacher.getTeacherId()+" where teacherId="+ teacher.getTeacherId();
        try {
            Statement statement=connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(connection);
        return teacher;
    }

    /**
     * deletes a teacher from the repository
     * @param teacher teacher we want to delete
     */
    @Override
    public void delete(Teacher teacher){
        Connection connection=openConnection();
        String query="delete from course_teachers where teacherId="+ teacher.getTeacherId();
        try {
            Statement statement=connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        query="delete from course where teacherId="+ teacher.getTeacherId();
        try {
            Statement statement=connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        query="delete from teacher where teacherId="+ teacher.getTeacherId();
        try {
            Statement statement=connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(connection);
    }
}
