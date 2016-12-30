package gob.conafor.suelo.mod;

import gob.conafor.conn.dat.LocalConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CDProfundidadSuelo {

    private String query;
    private CEProfundidadSuelo ceProfundidad = new CEProfundidadSuelo();

    public List<Integer> getPuntosProfundidad(int sitioID) {
        List<Integer> listPuntosProfundidad = new ArrayList<>();
        for (int i = 1; i < 9; i++) {
            listPuntosProfundidad.add(i);
        }
        query = "SELECT SitioID, Punto FROM SUELO_Profundidad WHERE SitioID= " + sitioID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Integer index = rs.getInt(2);
                listPuntosProfundidad.remove(index);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los puntos de profundidad del suelo ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en los puntos de profundidad del suelo ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        listPuntosProfundidad.add(0, null);
        return listPuntosProfundidad;
    }

    public List<Integer> getListProfundidadID(int sitioID) {
        List<Integer> listHojarascaID = new ArrayList<>();
        query = "SELECT ProfundidadSueloID, SitioID FROM SUELO_Profundidad WHERE SitioID= " + sitioID + " ORDER BY ProfundidadSueloID ASC";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                listHojarascaID.add(rs.getInt("ProfundidadSueloID"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los datos de profundidad de suelo id ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en lista de profundidad de suelo id", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return listHojarascaID;
    }

    public void reenumerarProfundidad(int sitioID) {
        List<Integer> listProfundidadPuntos = getListProfundidadID(sitioID);
        Connection conn = LocalConnection.getConnection();
        if (listProfundidadPuntos != null) {
            int size = listProfundidadPuntos.size();
            int consecutivo = 1;
            try {
                for (int i = 0; i < size; i++) {
                    Statement st = conn.createStatement();
                    query = "UPDATE SUELO_Profundidad SET Punto= " + consecutivo + "  WHERE ProfundidadSueloID= " + listProfundidadPuntos.get(i);
                    st.executeUpdate(query);
                    conn.commit();
                    consecutivo++;
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,
                        "Error! al reenumerar los puntos de profundidad de suelo ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al reenumerar los puntos de profundidad de suelo ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public CEProfundidadSuelo getDatosProfundidad(int profundidadID) {
        query = "SELECT ProfundidadSueloID, Punto, Profundidad030, Profundidad3060, PesoTotal030, PesoTotal3060, Equipo030, "
                + "Equipo3060, Observaciones, Clave030, Clave3060 FROM SUELO_Profundidad WHERE ProfundidadSueloID= " + profundidadID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                this.ceProfundidad.setPunto(rs.getInt("Punto"));
                this.ceProfundidad.setProfundidad030(rs.getFloat("Profundidad030"));
                this.ceProfundidad.setProfundidad3060(rs.getFloat("Profundidad3060"));
                this.ceProfundidad.setPeso030(rs.getFloat("PesoTotal030"));
                this.ceProfundidad.setPeso3060(rs.getFloat("PesoTotal3060"));
                this.ceProfundidad.setEquipo030(rs.getString("Equipo030"));
                this.ceProfundidad.setEquipo3060(rs.getString("Equipo3060"));
                this.ceProfundidad.setObservaciones(rs.getString("Observaciones"));
                this.ceProfundidad.setClave030(rs.getString("Clave030"));
                this.ceProfundidad.setClave3060(rs.getString("Clave3060"));
            }
            return this.ceProfundidad;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de profundidad de suelo "
                    + e.getClass().getName() + " : " + e.getMessage(), "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener la información de profundidad de suelo ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public DefaultTableModel getTablaProfundidad(int sitioID) {
        query = "SELECT ProfundidadSueloID, SitioID, Punto, Profundidad030, Profundidad3060, PesoTotal030, "
                + "PesoTotal3060, Equipo030, Equipo3060, Observaciones, Clave030, Clave3060  FROM SUELO_Profundidad WHERE SitioID="+sitioID;
        String[] encabezados = {"ProfundidadID", "SitioID", "Punto", "Profundidad 0-30", "Profundidad 30-60",
            "Peso total 0-30", "Peso total 30-60", "Equipo 0-30", "Equipo 30-60", "Observaciones", "Clave 0-30", "Clave 30-60"};
        DefaultTableModel hojarascaModel = new DefaultTableModel(null, encabezados);
        Object[] datosHojarasca = new Object[12];
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                datosHojarasca[0] = rs.getInt("ProfundidadSueloID");
                datosHojarasca[1] = rs.getInt("SitioID");
                datosHojarasca[2] = rs.getInt("Punto");
                datosHojarasca[3] = rs.getString("Profundidad030");
                datosHojarasca[4] = rs.getString("Profundidad3060");
                datosHojarasca[5] = rs.getString("PesoTotal030");
                datosHojarasca[6] = rs.getString("PesoTotal3060");
                datosHojarasca[7] = rs.getString("Equipo030");
                datosHojarasca[8] = rs.getString("Equipo3060");
                datosHojarasca[9] = rs.getString("Observaciones");
                datosHojarasca[10] = rs.getString("Clave030");
                datosHojarasca[11] = rs.getString("Clave3060");
                hojarascaModel.addRow(datosHojarasca);
            }
            st.close();
            rs.close();
            return hojarascaModel;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de la vista de profundidad suelo ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en para la vista de profundidad suelo ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void insertProfundidad(CEProfundidadSuelo ceProfundidad) {
        query = "INSERT INTO SUELO_Profundidad(SitioID, Punto, Profundidad030, Profundidad3060, PesoTotal030, PesoTotal3060, "
                + "Equipo030, Equipo3060, Observaciones, Clave030, Clave3060)VALUES(" + ceProfundidad.getSitioID() + ", " + ceProfundidad.getPunto() + ", " + ceProfundidad.getProfundidad030() + ", " + ceProfundidad.getProfundidad3060()
                + ", " + ceProfundidad.getPeso030() + ", " + ceProfundidad.getPeso3060() + ", '" + ceProfundidad.getEquipo030() + "', '" + ceProfundidad.getEquipo3060() + "', '" + ceProfundidad.getObservaciones() + "', '" + ceProfundidad.getClave030()
                + "', '" + ceProfundidad.getClave3060() + "')";
        //System.out.println(query);
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la informacion en profundidad de suelo ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al insertar datos de profundidad de suelo ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public boolean validarCDPuntoProfundidad(CEProfundidadSuelo ceProfundidad) {
        query = "SELECT SitioID, Punto FROM SUELO_Profundidad WHERE SitioID= " + ceProfundidad.getSitioID() + " AND Punto= " + ceProfundidad.getPunto();
        boolean vacio = false;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                vacio = true;
            }
            //vacio = false;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al validar la duplicidad de puntos en la tabla de profundidad de suelo", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al validar la duplicidad de puntos en la tabla de profundidad de suelo", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return vacio;
    }

    public void updateProfundidad(CEProfundidadSuelo ceProfundidad) {
        query = "UPDATE SUELO_Profundidad SET Profundidad030= " + ceProfundidad.getProfundidad030()
                + ", Profundidad3060= " + ceProfundidad.getProfundidad3060() + ", PesoTotal030= " + ceProfundidad.getPeso030() + ", PesoTotal3060= " + ceProfundidad.getPeso3060()
                + ", Equipo030= '" + ceProfundidad.getEquipo030() + "', Equipo3060= '" + ceProfundidad.getEquipo3060() + "', Observaciones= '" + ceProfundidad.getObservaciones()
                + "' WHERE ProfundidadSueloID= " + ceProfundidad.getProfundidadSueloID();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo modificar la información de profundidad de suelo "
                    + e.getClass().getName() + " : " + e.getMessage(), "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la modificación de profundidad de suelo "
                        + e.getClass().getName() + " : " + e.getMessage(), "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void deleteProfundidad(int profundidadID) {
        query = "DELETE FROM SUELO_Profundidad WHERE ProfundidadSueloID= " + profundidadID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información de profundidad de suelo ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar profundidad de suelo ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void deleteProfundidadSitio(int sitioID) {
        query = "DELETE FROM SUELO_Profundidad WHERE SitioID= " + sitioID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información de profundidad de suelo por sitio", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar profundidad de suelo por sitio",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public boolean validarTablaProfundidad(int sitioID) {
        query = "SELECT * FROM SUELO_Profundidad WHERE SitioID= " + sitioID;
        boolean vacio = true;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                vacio = false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al validar la presencia de datos en la tabla de profundidad de suelo", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la tabla de profundidad de suelo", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return vacio;
    }

}
