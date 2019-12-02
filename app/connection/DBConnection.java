package connection;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection getCon() {
        String databaseURL = "jdbc:mysql://localhost:3306/bookmanagement";
        String user = "root";
        String password = "Hrygny@4evr";

        Connection conn = null;
        try {
           Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(databaseURL, user, password);
            if (conn != null) {
                System.out.println("Connected to the database");
            }


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }




        return conn;
    }
}
