import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class ServerConnection {

    private static String user = "Desarrollo";
    private static String pass = "D354R0110J4";
    private static String url = "jdbc:sqlserver://GEOMATICA:1433;databaseName=MuestreoINF_2015";
    private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static Connection connection;

    public static Connection getConnection() {
        try {
            Class.forName(driver).newInstance();
            connection = DriverManager.getConnection(url, user, pass);
            JOptionPane.showMessageDialog(null, "se conectó");
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null,
                    "Error, No hay un servidor de base de datos disponible" + e.getMessage());

        }

        return connection;
    }

    public static void closeConnection() {
        try {
            connection.close();
            JOptionPane.showMessageDialog(null, "cerrando");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error, No hay base de datos local disponible" + e.getClass().getName() + ": " + e.getMessage());
        }

    }
    
   
    

}
