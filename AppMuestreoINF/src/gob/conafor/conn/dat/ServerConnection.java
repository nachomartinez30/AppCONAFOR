package gob.conafor.conn.dat;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class ServerConnection {

    private static String user = "sa";
    private static String pass = "208184879";
    private static String url = "jdbc:sqlserver://ecalderon:1433;databaseName=Especies";
    private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static Connection connection;

    public static Connection getConnection() {
        try {
            Class.forName(driver).newInstance();
            connection = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error, No hay un servidor de base de datos disponible" + e.getMessage());
        }
        return connection;
    }

    public static void close() {
        try {
            connection.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error, No hay base de datos local disponible" + e.getClass().getName() + ": " + e.getMessage());
        }
    }
}
