package gob.conafor.upm.mod;

import gob.conafor.conn.dat.LocalConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class CDContacto {

    private String query;

    public CEContacto getDatosContacto(int upm) {
        query = "SELECT UPMID, TipoContacto, Nombre, Direccion, TipoTelefono, NumeroTelefono, TieneCorreo, "
                + "DireccionCorreo, TieneRadio, Canal, Frecuencia, Observaciones  FROM UPM_Contacto WHERE UPMID= " + upm;
        CEContacto contacto = new CEContacto();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                contacto.setTipoContacto(rs.getInt("TipoContacto"));
                contacto.setNombre(rs.getString("Nombre"));
                contacto.setDireccion(rs.getString("Direccion"));
                contacto.setTipoTelefono(rs.getInt("TipoTelefono"));
                contacto.setNumeroTelefono(rs.getString("NumeroTelefono"));
                contacto.setTieneCorreoElectronico(rs.getInt("TieneCorreo"));
                contacto.setTieneRadio(rs.getInt("TieneRadio"));
                if (contacto.getTieneCorreoElectronico() == 1) {
                    contacto.setCorreoElectronico(rs.getString("DireccionCorreo"));
                }
                contacto.setTieneRadio(rs.getInt("TieneRadio"));
                if (contacto.getTieneRadio() == 1) {
                    contacto.setCanal(rs.getString("Canal"));
                    contacto.setFrecuencia(rs.getString("Frecuencia"));
                }
                contacto.setObservaciones("Observaciones");
            }
            return contacto;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los datos del contacto ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en datos del contacto ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void insertContacto(CEContacto contacto) {
        query = "INSERT INTO UPM_Contacto (UPMID, TipoContacto, Nombre, Direccion, TipoTelefono, NumeroTelefono, "
                + "TieneCorreo, DireccionCorreo, TieneRadio, Canal, Frecuencia, Observaciones)VALUES(" + contacto.getUpmID()
                + ", " + contacto.getTipoContacto() + ", '" + contacto.getNombre() + "', '" + contacto.getDireccion() + "', "
                + contacto.getTipoTelefono() + ",'" + contacto.getNumeroTelefono() + "', " + contacto.getTieneCorreoElectronico() + ", '"
                + contacto.getCorreoElectronico() + "', " + contacto.getTieneRadio() + ", '" + contacto.getCanal() + "', '"
                + contacto.getFrecuencia() + "', '" + contacto.getObservaciones() + "')";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la información del contacto ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al guardar la información del contacto "
                        + e.getClass().getName() + " : " + e.getMessage(), "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void updateDatosContacto(CEContacto contacto) {
        query = "UPDATE UPM_Contacto SET TipoContacto= " + contacto.getTipoContacto() + ", Nombre= '" + contacto.getNombre() + "', Direccion='" + contacto.getDireccion()
                + "', TipoTelefono= " + contacto.getTipoTelefono() + ", NumeroTelefono= '" + contacto.getNumeroTelefono() + "', TieneCorreo= " + contacto.getTieneCorreoElectronico()
                + ", DireccionCorreo= '" + contacto.getCorreoElectronico() + "', TieneRadio= " + contacto.getTieneRadio() + ", Canal= '" + contacto.getCanal() + "', Frecuencia= '" + contacto.getFrecuencia()
                + "', Observaciones= '" + contacto.getObservaciones() + "' WHERE UPMID= " + contacto.getUpmID();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo actualizar la información del contacto ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al actualizar la información del contacto ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void eliminarContacto(CEContacto contacto) {
        query = "DELETE FROM UPM_Contacto WHERE UPMID= " + contacto.getUpmID();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información del contacto ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar al contacto ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
