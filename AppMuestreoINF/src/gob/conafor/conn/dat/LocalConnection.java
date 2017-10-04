package gob.conafor.conn.dat;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class LocalConnection {

    private static String driver = "org.sqlite.JDBC";
    private static Connection connect;
    
    public static Connection getConnection() {
        try {
            Class.forName(driver);
            connect = DriverManager.getConnection(getURL());
            //System.out.println(getURL());
            connect.setAutoCommit(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error, No hay base de datos local disponible" + e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return connect;
    }
    
    

    public static void closeConnection() {
        try {
            connect.close();
            JOptionPane.showConfirmDialog(null, "Desconectado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error, No hay base de datos local disponible" + e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public static String getURL() {
        Path currentPath = Paths.get("");
        String path = currentPath.toAbsolutePath().toString();
        
        return "jdbc:sqlite:" + path + "/MuestreoINF_2015.oct"; //Para distribuir
        //return "jdbc:sqlite:" + path + "/src/db/MuestreoINF_2015.oct"; //En producción.
    }
   
}










