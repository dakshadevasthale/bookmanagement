package services;

import beans.User;
import connection.DBConnection;
import security.BCrypt;

import java.sql.*;

public class UserRegistration {

    public int createUser(User user) throws SQLException {
        System.out.println(" inside service function");
        int count = 0;

        PreparedStatement pstmt = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        try{
            String userId = user.getUserId();
            System.out.println(userId);
            String query = "SELECT userId FROM user WHERE userId = ?";
            Connection con = DBConnection.getCon();
            System.out.println(con);
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,userId);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
            {
                System.out.println("Inside if");
                count = 0;
            }
            else{

                System.out.println("in else");

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

                pstmt2 = con.prepareStatement(insertQueryContactDetails);
                pstmt2.setString(1,userId);
                pstmt2.setString(2, user.getlName());
                pstmt2.setString(3, user.getfName());
                pstmt2.setDate(4, new java.sql.Date(user.getDateOfBirth().getTime()));
                pstmt2.setString(5, user.getAddress());
                pstmt2.setString(6, user.getContactNo());

                count = pstmt2.executeUpdate();

                System.out.println("Data updated in Contact Details Table");
            }

        } finally {
            if (pstmt != null) { pstmt.close(); }
        }
        return count;
    }
}
