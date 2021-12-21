package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class JDBCRepository<T> implements ICrudRepository<T>{

    public JDBCRepository() {
    }

    public Connection openConnection(){
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/Lab5?autoReconnect=true&useSSL=false", "root", "ciumpalac3");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void closeConnection(Connection con){
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
