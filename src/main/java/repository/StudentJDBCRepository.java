package repository;

import model.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentJDBCRepository extends JDBCRepository<Student>{

    public StudentJDBCRepository() {
        super();
    }


    /**
     * adds a student to the repository
     * @param student the created student
     * @return the created student
     */
    @Override
    public Student create (Student student){
        Connection connection=openConnection();
        String query="insert into student values ('"+ student.getFirstName()+"','"+
                student.getLastName()+"',"+student.getStudentId()+","+student.getTotalCredits()+")";
        try {
            Statement statement=connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(connection);

        return student;
    }

    /**
     * returns all students in the repository
     * @return a list of all students
     */
    @Override
    public List<Student> getAll (){
        List<Student> students = new ArrayList<>();
        Connection connection=openConnection();
        String query="select * from student";
        try {
            Statement studentStatement=connection.createStatement();
            ResultSet studentResultSet=studentStatement.executeQuery(query);
            while (studentResultSet.next()){
                List<Long> enrolledCourses=new ArrayList<>();
                query="select * from enrolledstudents where studentId='"+studentResultSet.getLong("studentId")+"'";
                Statement courseStatement=connection.createStatement();
                ResultSet courseResultSet=courseStatement.executeQuery(query);
                while (courseResultSet.next()){
                    enrolledCourses.add(courseResultSet.getLong("courseId"));
                }
                students.add(new Student(studentResultSet.getString("firstName"),studentResultSet.getString("lastName"), studentResultSet.getLong("studentId"),studentResultSet.getInt("credits"),enrolledCourses));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(connection);
        return students;
    }

    /**
     * updates the student with the same Id
     * @param student the new student
     * @return the new student
     */
    @Override
    public Student update(Student student){
        Connection connection=openConnection();
        String query="update student set firstName='"+student.getFirstName()+"',lastName='"+student.getLastName()+
                "',studentId="+student.getStudentId()+",credits="+student.getTotalCredits()+ " where studentId="+ student.getStudentId();
        try {
            Statement statement=connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(connection);
        return student;
    }

    /**
     * deletes a student from the repository
     * @param student the student we want to delete
     */
    @Override
    public void delete(Student student){
        Connection connection=openConnection();
        String query="delete from enrolledstudents where studentId="+ student.getStudentId();
        try {
            Statement statement=connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        query="delete from student where studentId="+ student.getStudentId();
        try {
            Statement statement=connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(connection);
    }
}
