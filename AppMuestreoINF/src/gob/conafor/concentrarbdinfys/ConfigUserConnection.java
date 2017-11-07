package gob.conafor.concentrarbdinfys;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class ConfigUserConnection {
	
	private static String driver = "org.sqlite.JDBC";
	private static Connection connect;

	public static Connection getConnection(String ruta) {
		try {
			Class.forName(driver);
			//connect = DriverManager.getConnection(getURL(ruta));
			//connect.setAutoCommit(false);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error, El archivo que intenta conectar, no es una base de datos valida"
					+ e.getClass().getName() + ": " + e.getMessage());

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

	private static String getURL(String ruta) {
		Path currentPath = Paths.get("");
        String path = currentPath.toAbsolutePath().toString();
        
         return "jdbc:sqlite:" + path + "/ConfigUser.ab"; //Para distribuir

       //return "jdbc:sqlite:" + path + "/src/Databas/ConfigUser.db"; //En producci�n.
	}
}
