package concentrarbdinfys;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class H2Conexion {

    //private static final String DRIVER = "org.h2.Driver";
    private static final String JDBC_URL = "jdbc:h2:C:\\Users\\ignacio.martinez\\Desktop\\MuestreoINF_2016";
    //user=admin;password=3ls3n10r3sm1p4st0r
    private static final String USER = "admin";
    private static final String PASSWORD = "1gn4c10";
    private static String DRIVER = "org.h2.Driver";
    private static Connection conn,connect;

    public static Connection getConnection() {
        try {
        	   Class.forName("org.h2.Driver");
              conn= DriverManager.getConnection("jdbc:h2:C:/Users/ignacio.martinez/Desktop/MuestreoINF_2016", "admin", "1gn4c10");
               System.out.println("se conecto");
               // add application code here
               
		} catch (Exception e) {
			         System.err.println("NO se CONECTO "+e);
		}
        return conn;
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
