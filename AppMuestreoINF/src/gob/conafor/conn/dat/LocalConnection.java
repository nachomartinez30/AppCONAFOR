package gob.conafor.conn.dat;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class LocalConnection {

    private static String driver = "org.h2.Driver";
	private static Connection connect;


	public static Connection getConnection() {
		try {
                    Path currentPath = Paths.get("");
                    String path = currentPath.toAbsolutePath().toString(),conexion;
                    //conexion="jdbc:h2:"+path+"/src/db/MuestreoINF_2017"; //desarrollo
                    conexion="jdbc:h2:"+path+"/MuestreoINF_2017"; //distribucion
			Class.forName(driver);
			connect = DriverManager.getConnection(conexion,"usuario_java","211372848");
						connect.setAutoCommit(false);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Error, No hay base de datos local disponible" + e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();
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
	}/**/
}










