package webAutomation.webUtilities;

import java.sql.ResultSet;

public class DbConnection {


    public ResultSet ConnectDb(String Query) {
        DbSetUp connect = new DbSetUp();
        ResultSet Data;
        ResultSet FillData;

        String dbUrl = "jdbc:mysql://localhost:3306/nameofdb";
        String username = "root";
        String password = "password";

        Data = connect.ConnectAndQuerySQL(dbUrl, username, password, Query);

        return Data;
    }
}
