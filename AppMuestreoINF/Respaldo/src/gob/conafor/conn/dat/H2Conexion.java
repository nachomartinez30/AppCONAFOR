package gob.conafor.conn.dat;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class H2Conexion {

    //private static final String DRIVER = "org.h2.Driver";
    private static final String JDBC_URL = "jdbc:h2:C:/Users/ecalderon/Documents/java/AppMuestreoINF/Muestreo";
    //user=admin;password=3ls3n10r3sm1p4st0r
    private static final String USER = "sa";
    private static final String PASSWORD = "3ls3n10r3sm1p4st0r";
    private static String DRIVER = "org.h2.Driver";
    private static Connection connect;

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            connect = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            //connect.setAutoCommit(true);
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

   /* public static void main(String args[]) {
        Connection conn = H2Conexion.getConnection();
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(H2Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/

}
