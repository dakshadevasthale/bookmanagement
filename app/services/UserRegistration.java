package services;

import beans.User;
import connection.DBConnection;
import security.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRegistration {

    public int createUser(User user) throws SQLException {

        int count = 0;

        PreparedStatement pstmt = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        try{
            String userId = user.getUserId();
            String query = "SELECT userId FROM user WHERE userId=?";

            Connection con = DBConnection.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,userId);
            ResultSet rs = pstmt.executeQuery();
            if(rs != null)
            {
                return count;
            }
            else{
                String insertQueryUser = "INSERT INTO user(userId, password, role, email, status, securityQ, securityAns) VALUES (?,?,'Student',?,1,?,?)";
                String insertQueryContactDetails = "INSERT INTO contactDetails(userId, lname, fname, dateOfBirth, address, contactNo) VALUES (?,?,?,?,?,?)";


                String password = user.getPassword();

                //call for password encryption
                BCrypt b = new BCrypt();
                String encryptedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

                pstmt1 = con.prepareStatement(insertQueryUser);
                pstmt1.setString(1,userId);
                pstmt1.setString(2, encryptedPassword);
                pstmt1.setString(3,user.getEmail());
                pstmt1.setString(4,user.getSecurityQ());
                pstmt1.setString(5,user.getSecurityA());

                count = pstmt1.executeUpdate();

                System.out.println("Data updated in User Table");
            }

        } finally {
            if (pstmt != null) { pstmt.close(); }
        }
        return count;
    }
}
