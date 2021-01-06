package school.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {

    //Database driver name
    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3308/school_system";

    //Credintails
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    private static Connection connection = null;

    public DAO() {

    }

    public static Connection getConnection() {
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            return connection;
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found exception occured");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("SQl exception occured");
            e.printStackTrace();
        }
        return null;


    }

}


