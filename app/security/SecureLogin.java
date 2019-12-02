package security;

import connection.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SecureLogin {

    public boolean userSecureLogin(String userName, String password) throws SQLException {
        boolean result = false;
        boolean authenticated = false;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt1 = null;
        String query = "SELECT userId FROM user WHERE userId = ? ";
        Connection con = DBConnection.getCon();
        pstmt = con.prepareStatement(query);
        pstmt.setString(1,userName);
        ResultSet rs = pstmt.executeQuery();
        if(rs.next()){
            System.out.println("user exists");
            String query2 = "SELECT password FROM user WHERE userId = ? ";

            pstmt1 = con.prepareStatement(query2);
            pstmt1.setString(1,userName);
            ResultSet rs1 = pstmt1.executeQuery();
            if(rs1.next()) {
                String dbPassword = rs1.getString(1);
                result = BCrypt.checkpw(password,dbPassword);
                System.out.println(result);
                if(result){
                    result = true;
                }
                 if (result == true){
                     authenticated = true;
                 }
                System.out.println(dbPassword);
            }
        }

        return authenticated;
    }

}
