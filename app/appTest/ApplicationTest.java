package appTest;

import connection.DBConnection;

import java.sql.Connection;

public class ApplicationTest {

    public static void main (String args []){
        Connection conn ;

        DBConnection dbcon = new DBConnection();
        conn = dbcon.getCon();
        System.out.println(conn);
    }
}
