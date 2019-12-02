package security;

import connection.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SecureLogin {

    public boolean userSecureLogin(String userName, String password) throws SQLException {
        boolean result = false;

        PreparedStatement pstmt = null;
        String query = "SELECT userId FROM user WHERE userId = ? and password = ?";
        Connection con = DBConnection.getCon();
        pstmt = con.prepareStatement(query);
        pstmt.setString(1,userName);

        return result;
    }

}
