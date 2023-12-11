package webAutomation.webUtilities;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbSetUp {

    public ResultSet ConnectAndQuerySQL(String sDBURL, String sUserName, String sPassword, String sQuery) {

        ResultSet result = null;
        try {

            String dbUrl = sDBURL;
            String username = sUserName;
            String password = sPassword;

            java.sql.Connection con = DriverManager.getConnection(dbUrl, username, password);
            Statement stat = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            result = stat.executeQuery(sQuery);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
