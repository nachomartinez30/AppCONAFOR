package gob.conafor.pc.mod;

import gob.conafor.conn.dat.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CDAccesibilidadPC {

    private String query;
    
    public boolean validarTablaAccesibilida(int upm) {
        query = "SELECT * FROM PC_Accesibilidad WHERE UPMID= " + upm;
        boolean vacio = true;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                vacio = false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de accesibilidad  al PC "
                    , "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en datos de validar tabla de accesibilidad"
                       , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return vacio;
    }

    public DefaultTableModel getDatosAccesibilidadPC(int upmID) {
        query = "SELECT AccesibilidadID, UPMID, Medio, Via, Distancia, Condicion FROM VW_ViaAccesibilidad "
                + "WHERE UPMID= " + upmID;
        String[] encabezados = {"AccesibilidadID", "UPMID", "Medio", "Via", "Distancia", "Condicion"};
        Object[] datosAccesibilidad = new Object[6];
        DefaultTableModel accesibilidadTableModel = new DefaultTableModel(null, encabezados);
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                datosAccesibilidad[0] = rs.getInt("AccesibilidadID");
                datosAccesibilidad[1] = rs.getInt("UPMID");
                datosAccesibilidad[2] = rs.getString("Medio");
                datosAccesibilidad[3] = rs.getString("Via");
                datosAccesibilidad[4] = rs.getFloat("Distancia");
                datosAccesibilidad[5] = rs.getString("Condicion");
                accesibilidadTableModel.addRow(datosAccesibilidad);
            }
            st.close();
            rs.close();
            return accesibilidadTableModel;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de accesibilidad  al PC "
                    , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en datos de accesibilidad al pc"
                        , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    public String[] getDatosComboAcesibilidad(int AccesibilidadID) {
        query = "SELECT AccesibilidadID, MedioTransporteID, ViaAccesibilidadID, Distancia, CondicionAccesibilidadID FROM PC_Accesibilidad "
                + "WHERE AccesibilidadID= " + AccesibilidadID;
        String[] datosAccesibilidad = new String[4];
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                datosAccesibilidad[0] = rs.getString("MedioTransporteID");
                datosAccesibilidad[1] = rs.getString("ViaAccesibilidadID");
                datosAccesibilidad[2] = rs.getString("Distancia");
                datosAccesibilidad[3] = rs.getString("CondicionAccesibilidadID");
            }
            st.close();
            rs.close();
            return datosAccesibilidad;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de accesibilidad  al PC "
                    , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en datos de accesibilidad al pc"
                       , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public List<CatEMedioTransporte> getMedioTransporte() {
        query = "SELECT MedioTransporteID, Medio, Descripcion FROM CAT_MedioTransporte";
        Connection conn = LocalConnectionCat.getConnection();
        List<CatEMedioTransporte> listaMedioTransporte = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                CatEMedioTransporte medioTransporte = new CatEMedioTransporte();
                medioTransporte.setMedioTransporteID(rs.getInt("MedioTransporteID"));
                medioTransporte.setMedio(rs.getString("Medio"));
                medioTransporte.setDescripcion(rs.getString("Descripcion"));
                listaMedioTransporte.add(medioTransporte);
            }
            listaMedioTransporte.add(0, null);
            return listaMedioTransporte;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en lista de medios de transporte"
                    , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public List<CatEViaAcceso> getViaAcceso(int medioTransporteID) {
        query = "SELECT vi.ViaAccesibilidadID AS Id, vi.Via AS via, vi.Descripcion AS descripcion FROM CAT_ViaAccesibilidad vi "
                + "LEFT JOIN CAT_TransporteAccesibilidad tra ON tra.ViaAccesibilidadID = vi.ViaAccesibilidadID "
                + "WHERE tra.MedioTransporteID = " + medioTransporteID;
        Connection conn = LocalConnectionCat.getConnection();
        List<CatEViaAcceso> listaMedioTransporte = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                CatEViaAcceso viaAcceso = new CatEViaAcceso();
                viaAcceso.setViaAccesibilidadID(rs.getInt("Id"));
                viaAcceso.setVia(rs.getString("via"));
                viaAcceso.setDescripcion(rs.getString("descripcion"));
                listaMedioTransporte.add(viaAcceso);
            }
            listaMedioTransporte.add(0, null);
            return listaMedioTransporte;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en lista de vias de acceso"
                   , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al guardad la via de acceso al pc"
                        , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public List<CatECondicionAccesibilidad> getCondicion() {
        query = "SELECT CondicionAccesibilidadID, Condicion, Descripcion FROM CAT_CondicionAccesibilidad";
        Connection conn = LocalConnectionCat.getConnection();
        List<CatECondicionAccesibilidad> listaCondicion = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                CatECondicionAccesibilidad viaAcceso = new CatECondicionAccesibilidad();
                viaAcceso.setCondicionAccesibilidadID(rs.getInt("CondicionAccesibilidadID"));
                viaAcceso.setCondicion(rs.getString("Condicion"));
                viaAcceso.setDescripcion(rs.getString("Descripcion"));
                listaCondicion.add(viaAcceso);
            }
            listaCondicion.add(0, null);
            return listaCondicion;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en lista de condición"
                    , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al guardad la condicion de accesibilidad al pc"
                        , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void insertDatosAccesibilidadPC(CEAccesibilidadPC accesibilidad) {
        query = "INSERT INTO PC_Accesibilidad(UPMID, MedioTransporteID, ViaAccesibilidadID, Distancia, CondicionAccesibilidadID)"
                + "VALUES(" + accesibilidad.getUpmID() + ", " + accesibilidad.getMedioTransporteID() + ", " + accesibilidad.getViaAccesoID() + ", "
                + accesibilidad.getDistancia() + ", " + accesibilidad.getCondicionAccesoID() + ")";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la información de accesibilidad del pc "
                    , "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {

                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al guardad la accesibilidad al pc"
                        , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void updateDatosAccesibilidadPC(CEAccesibilidadPC accesibilidad) {
        query = "UPDATE PC_Accesibilidad SET MedioTransporteID= " + accesibilidad.getMedioTransporteID() + ", ViaAccesibilidadID= " + accesibilidad.getViaAccesoID()
                + ", Distancia= " + accesibilidad.getDistancia() + ", CondicionAccesibilidadID= " + accesibilidad.getCondicionAccesoID() + " WHERE AccesibilidadID=" + accesibilidad.getAccesibilidadID();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo actualizar la información de la via de acceso "
                    , "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al modificar la accesibilidad al pc"
                        , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void deleteDatosAccesibilidadPC(CEAccesibilidadPC id) {
        query = "DELETE FROM PC_Accesibilidad WHERE AccesibilidadID= " + id.getAccesibilidadID();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error!  al eliminar datos de la accesibilidad al pc"
                   , "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al eliminar la accesibilidad al pc"
                       , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
