package app.storemanagement.model.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//import java.util.Properties;

public class DBConnection {

    private static final String URL = "jdbc:sqlserver://127.0.0.1;databaseName=StoreManagementDb";
    private static final String USERNAME = System.getenv("DB_USER1");
    private static final String PASSWORD = System.getenv("DB_PASSWORD1");

    public static Connection getConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(URL + ";user=" + USERNAME + ";password=" + PASSWORD + ";integratedSecurity=true;encrypt=true;trustServerCertificate=true");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}

/*public class DBConnection {

    private static final String DATABASE_URL = "jdbc:sqlserver://localhost";
    private static final String DATABASE_NAME = "StoreManagementDb";
 

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Properties connectionProperties = new Properties();
            connectionProperties.setProperty("integratedSecurity", "true");
            connectionProperties.setProperty("encrypt", "true");
            connectionProperties.setProperty("trustServerCertificate", "true");

            connection = DriverManager.getConnection(DATABASE_URL + ";databaseName=" + DATABASE_NAME, connectionProperties);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }
}*/