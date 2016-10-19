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

public class CDErosionHidrica {

    private String query;
    private CEErosionHidricaCanalillos ceCanalillos = new CEErosionHidricaCanalillos();
    private CEErosionHidricaCarcavas ceCarcava = new CEErosionHidricaCarcavas();
    private CELongitudCanalillos ceLongitudCanalillo = new CELongitudCanalillos();
    private CELongitudCarcavas ceLongitudCarcava = new CELongitudCarcavas();
    public List<Integer> getNoMedicionCanalillo(int sitioID) {
        List<Integer> listMedicionCanalillo = new ArrayList<>();
        for (int i = 1; i < 16; i++) {
            listMedicionCanalillo.add(i);
        }
        query = "SELECT SitioID, Medicion FROM SUELO_ErosionHidricaCanalillo WHERE SitioID= " + sitioID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Integer index = rs.getInt(2);
                listMedicionCanalillo.remove(index);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los numeros de medicion de canalillo" + e.getClass().getName() + " : " + e.getMessage(),
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en los numeros de medicion de canalillo "
                        + e.getClass().getName() + " : " + e.getMessage(), "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        listMedicionCanalillo.add(0, null);
        return listMedicionCanalillo;
    }
    
    private List<Integer> getListErosionCanalillosID(int sitioID) {
        List<Integer> listCanalillos = new ArrayList<>();
        query = "SELECT ErosionCanalilloID, SitioID FROM SUELO_ErosionHidricaCanalillo WHERE SitioID= " + sitioID + " ORDER BY ErosionCanalilloID ASC";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                listCanalillos.add(rs.getInt("ErosionCanalilloID"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los datos de erosion canalillo id ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en lista de erosion canalillo id", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return listCanalillos;
    }
    
    public void reenumerarErosionCanalillo(int sitioID) {
        List<Integer> listErosionCanalillo = getListErosionCanalillosID(sitioID);
        Connection conn = LocalConnection.getConnection();

        if (listErosionCanalillo != null) {
            int size = listErosionCanalillo.size();
            int consecutivo = 1;

            try {
                for (int i = 0; i < size; i++) {
                    Statement st = conn.createStatement();
                    query = "UPDATE SUELO_ErosionHidricaCanalillo SET Medicion= " + consecutivo + "  WHERE ErosionCanalilloID= " + listErosionCanalillo.get(i);
                    st.executeUpdate(query);
                    conn.commit();
                    consecutivo++;
                }
            } catch (SQLException e) {
                 JOptionPane.showMessageDialog(null,
                        "Error! al reenumerar las mediciones de erosion de canalillos ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al reenumerar las mediciones de erosion de canalillos ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public CEErosionHidricaCanalillos getCanalillo(int canalilloID) {
        query = "SELECT ErosionCanalilloID, Medicion, Profundidad, Ancho, Distancia, Azimut FROM SUELO_ErosionHidricaCanalillo "
                + " WHERE ErosionCanalilloID= " + canalilloID;
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                this.ceCanalillos.setMedicion(rs.getInt("Medicion"));
                this.ceCanalillos.setProfundidad(rs.getFloat("Profundidad"));
                this.ceCanalillos.setAncho(rs.getFloat("Ancho"));
                this.ceCanalillos.setDistancia(rs.getFloat("Distancia"));
                this.ceCanalillos.setAzimut(rs.getInt("Azimut"));
            }
            return this.ceCanalillos;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de erosion hidrica de canalillos ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener la erosion hidrica de canalillos ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void insertErosionCanalillo(CEErosionHidricaCanalillos ceCanalillo) {
        query = "INSERT INTO SUELO_ErosionHidricaCanalillo(SitioID, Medicion, Profundidad, Ancho, Distancia, Azimut)VALUES("
                + ceCanalillo.getSitioID() + ", " + ceCanalillo.getMedicion() + ", " + ceCanalillo.getProfundidad() + ", "
                + ceCanalillo.getAncho() + ", " + ceCanalillo.getDistancia() + ", " + ceCanalillo.getAzimut() + ")";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la informacion en erosion de canalillos ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al insertar datos de erosion de canalillos ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void updateErosionCanalillo(CEErosionHidricaCanalillos ceCanalillo) {
        query = "UPDATE SUELO_ErosionHidricaCanalillo SET Profundidad= " + ceCanalillo.getProfundidad() + ", Ancho= "
                + ceCanalillo.getAncho() + ", Distancia= " + ceCanalillo.getDistancia() + ", Azimut= " + ceCanalillo.getAzimut() + " WHERE ErosionCanalilloID= "
                + ceCanalillo.getErosionCanalillosID();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo modificar la información de erosion de canalillos ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la modificación de erosion de canalillos ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void deleteErosionCanalillo(int erosionCanalilloID) {
        query = "DELETE FROM SUELO_ErosionHidricaCanalillo WHERE ErosionCanalilloID= " + erosionCanalilloID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información de erosion de canalillo ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  de erosion de canalillo",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void deleteErosionCanalilloSitio(int sitioID){
        query = "DELETE FROM SUELO_ErosionHidricaCanalillo WHERE SitioID= " + sitioID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información de erosion de canalillo por sitio", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  de erosion de canalillo por sitio",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public DefaultTableModel getTablaErosionCanalillo(int sitioID) {
        query = "SELECT ErosionCanalilloID, SitioID, Medicion, Profundidad, Ancho, Distancia, Azimut "
                + "FROM SUELO_ErosionHidricaCanalillo WHERE SitioID= " + sitioID;
        String[] encabezados = {"ErosionHidricaCanalilloID", "SitioID", "Medicion", "Profundidad", "Ancho",
            "Distancia", "Azimut"};
        DefaultTableModel erosionCanalilloModel = new DefaultTableModel(null, encabezados);
        Object[] datosErosionCanalillo = new Object[7];
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                datosErosionCanalillo[0] = rs.getInt("ErosionCanalilloID");
                datosErosionCanalillo[1] = rs.getInt("SitioID");
                datosErosionCanalillo[2] = rs.getInt("Medicion");
                datosErosionCanalillo[3] = rs.getFloat("Profundidad");
                datosErosionCanalillo[4] = rs.getFloat("Ancho");
                datosErosionCanalillo[5] = rs.getFloat("Distancia");
                datosErosionCanalillo[6] = rs.getInt("Azimut");

                erosionCanalilloModel.addRow(datosErosionCanalillo);
            }
            st.close();
            rs.close();
            return erosionCanalilloModel;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de la vista de erosion de canalillo ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en para la vista de erosion de canalillo ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public List<Integer> getCampoLongitudCanalillo(int sitioID) {
        List<Integer> listLongitudCanalillo = new ArrayList<>();
        for (int i = 1; i < 16; i++) {
            listLongitudCanalillo.add(i);
        }
        query = "SELECT SitioID, CampoLongitud FROM SUELO_LongitudCanalillo WHERE SitioID= " + sitioID;
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Integer index = rs.getInt(2);
                listLongitudCanalillo.remove(index);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los campos de  longitud canalillo",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en los campos de longitud de canalillo ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        listLongitudCanalillo.add(0, null);
        return listLongitudCanalillo;
    }

    private List<Integer> getListLongitudCanalilloID(int sitioID) {
        List<Integer> listLongitudCanalillo = new ArrayList<>();
        query = "SELECT LongitudCanalilloID, SitioID FROM SUELO_LongitudCanalillo WHERE SitioID= " + sitioID + " ORDER BY LongitudCanalilloID ASC";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                listLongitudCanalillo.add(rs.getInt("LongitudCanalilloID"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los datos de longitud canalillo id ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en lista de longitud canalillo id", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return listLongitudCanalillo;
    }

    public void reenumerarLongitudCanalillo(int sitioID) {
        List<Integer> listCampoLongitud = getListLongitudCanalilloID(sitioID);
        Connection conn = LocalConnection.getConnection();

        if (listCampoLongitud != null) {
            int size = listCampoLongitud.size();
            int consecutivo = 1;

            try {
                for (int i = 0; i < size; i++) {
                    Statement st = conn.createStatement();
                    query = "UPDATE SUELO_LongitudCanalillo SET CampoLongitud= " + consecutivo + "  WHERE LongitudCanalilloID= " + listCampoLongitud.get(i);
                    st.executeUpdate(query);
                    conn.commit();
                    consecutivo++;
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,
                        "Error! al reenumerar el campo longitud canalillo ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al reenumerar el campo longitud canalillo", "Conexion BD", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    public CELongitudCanalillos getLongitudCanalillos(int longitudCanalilloID) {
        query = "SELECT LongitudCanalilloID, Longitud FROM SUELO_LongitudCanalillo "
                + " WHERE LongitudCanalilloID= " + longitudCanalilloID;
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                this.ceLongitudCanalillo.setLongitud(rs.getFloat("Longitud"));
            }
            return this.ceLongitudCanalillo;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de longitud de canalillos ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener la longitud de canalillos ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void insertLongitudCanalillo(CELongitudCanalillos ceLongitudCanalillo) {
        query = "INSERT INTO SUELO_LongitudCanalillo(SitioID, CampoLongitud, Longitud)VALUES("
                + ceLongitudCanalillo.getSitioID() + ", " + ceLongitudCanalillo.getCampoLongitud() + ", " + ceLongitudCanalillo.getLongitud() + ")";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la informacion en longitud de canalillos ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al insertar datos de longitud de canalillos ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void updateLongitudCanalillo(CELongitudCanalillos ceLongitudCanalillo) {
        query = "UPDATE SUELO_LongitudCanalillo SET Longitud= " + ceLongitudCanalillo.getLongitud() + " WHERE LongitudCanalilloID= " + 
                ceLongitudCanalillo.getLongitudCanalillosID();
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo modificar la información de longitud de canalillos ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la modificación de longitud de canalillos ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void deleteLongitudCanalillo(int longitudCanalilloID) {
        query = "DELETE FROM SUELO_LongitudCanalillo WHERE LongitudCanalilloID= " + longitudCanalilloID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información de longitud de canalillo ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  de longitud de canalillo",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void deleteLongitudCanalilloSitio(int sitioID) {
        query = "DELETE FROM SUELO_LongitudCanalillo WHERE SitioID= " + sitioID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información de longitud de canalillo por sitio", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  de longitud de canalillo por sitio",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
  
    public DefaultTableModel getTablaLongitudCanalillo(int sitioID) {
        query = "SELECT LongitudCanalilloID, SitioID, CampoLongitud, Longitud "
                + "FROM SUELO_LongitudCanalillo WHERE SitioID= " + sitioID;
        String[] encabezados = {"LongitudCanalilloID", "SitioID", "Campo de longitud", "Longitud"};
        DefaultTableModel longitudCanalilloModel = new DefaultTableModel(null, encabezados);
        Object[] datosLongitudCanalillo = new Object[4];
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                datosLongitudCanalillo[0] = rs.getInt("LongitudCanalilloID");
                datosLongitudCanalillo[1] = rs.getInt("SitioID");
                datosLongitudCanalillo[2] = rs.getInt("CampoLongitud");
                datosLongitudCanalillo[3] = rs.getFloat("Longitud");

                longitudCanalilloModel.addRow(datosLongitudCanalillo);
            }
            st.close();
            rs.close();
            return longitudCanalilloModel;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de la vista de erosion de canalillo ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en para la vista de erosion de canalillo ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public List<Integer> getNoMedicionCarcava(int sitioID) {
        List<Integer> listMedicionCarcava = new ArrayList<>();
        for (int i = 1; i < 16; i++) {
            listMedicionCarcava.add(i);
        }
        query = "SELECT SitioID, Medicion FROM SUELO_ErosionHidricaCarcava WHERE SitioID= " + sitioID;
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Integer index = rs.getInt(2);
                listMedicionCarcava.remove(index);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los numeros de medicion de carcava ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en los numeros de medicion de carcava ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        listMedicionCarcava.add(0, null);
        return listMedicionCarcava;
    }

    
    private List<Integer> getListErosionCarcavaID(int sitioID) {
        List<Integer> listCarcava = new ArrayList<>();
        query = "SELECT ErosionCarcavaID, SitioID FROM SUELO_ErosionHidricaCarcava WHERE SitioID= " + sitioID + " ORDER BY ErosionCarcavaID ASC";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                listCarcava.add(rs.getInt("ErosionCarcavaID"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los datos de erosion carcava id ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en lista de erosion carcava id"
                  , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return listCarcava;
    }

    public void reenumerarErosionCarcava(int sitioID) {
        List<Integer> listErosionCarcava = getListErosionCarcavaID(sitioID);
        Connection conn = LocalConnection.getConnection();

        if (listErosionCarcava != null) {
            int size = listErosionCarcava.size();
            int consecutivo = 1;

            try {
                for (int i = 0; i < size; i++) {
                    Statement st = conn.createStatement();
                    query = "UPDATE SUELO_ErosionHidricaCarcava SET Medicion= " + consecutivo + "  WHERE ErosionCarcavaID= " + listErosionCarcava.get(i);
                    st.executeUpdate(query);
                    conn.commit();
                    consecutivo++;
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,
                        "Error! al reenumerar las mediciones de erosion de carcava ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al reenumerar las mediciones de erosion de carcava ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    public CEErosionHidricaCarcavas getCarcava(int carcavaID) {
        query = "SELECT ErosionCarcavaID, Medicion, Profundidad, Ancho, Distancia, Azimut FROM SUELO_ErosionHidricaCarcava "
                + " WHERE ErosionCarcavaID= " + carcavaID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                this.ceCarcava.setMedicion(rs.getInt("Medicion"));
                this.ceCarcava.setProfundidad(rs.getFloat("Profundidad"));
                this.ceCarcava.setAncho(rs.getFloat("Ancho"));
                this.ceCarcava.setDistancia(rs.getFloat("Distancia"));
                this.ceCarcava.setAzimut(rs.getInt("Azimut"));
            }
            return this.ceCarcava;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de erosion hidrica de carcava ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener la erosion hidrica de carcava ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void insertErosionCarcava(CEErosionHidricaCarcavas ceCarcava) {
        query = "INSERT INTO SUELO_ErosionHidricaCarcava(SitioID, Medicion, Profundidad, Ancho, Distancia, Azimut)VALUES("
                + ceCarcava.getSitioID() + ", " + ceCarcava.getMedicion() + ", " + ceCarcava.getProfundidad() + ", "
                + ceCarcava.getAncho() + ", " + ceCarcava.getDistancia() + ", " + ceCarcava.getAzimut() + ")";
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la informacion en erosion de carcava ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al insertar datos de erosion de carcava ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void updateErosionCarcava(CEErosionHidricaCarcavas ceCarcava) {
        query = "UPDATE SUELO_ErosionHidricaCarcava SET Profundidad= " + ceCarcava.getProfundidad() + ", Ancho= "
                + ceCarcava.getAncho() + ", Distancia= " + ceCarcava.getDistancia() + ", Azimut= " + ceCarcava.getAzimut() + " WHERE ErosionCarcavaID= "
                + ceCarcava.getErosionCarcavasID();
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo modificar la información de erosion de carcava ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la modificación de erosion de carcava ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void deleteErosionCarcava(int erosionCarcavaID) {
        query = "DELETE FROM SUELO_ErosionHidricaCarcava WHERE ErosionCarcavaID= " + erosionCarcavaID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información de erosion de carcava ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  de erosion de carcava ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void deleteErosionCarcavaSitio(int sitioID){
        query  = "DELETE FROM SUELO_ErosionHidricaCarcava WHERE SitioID= " + sitioID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información de erosion de carcava por sitio", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  de erosion de carcava por sitio",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public DefaultTableModel getTablaErosionCarcava(int sitioID) {
        query = "SELECT ErosionCarcavaID, SitioID, Medicion, Profundidad, Ancho, Distancia, Azimut "
                + "FROM SUELO_ErosionHidricaCarcava WHERE SitioID= " + sitioID;
        String[] encabezados = {"ErosionCarcavaID", "SitioID", "Medicion", "Profundidad", "Ancho",
            "Distancia", "Azimut"};
        DefaultTableModel erosionCarcavaModel = new DefaultTableModel(null, encabezados);
        Object[] datosErosionCarcava = new Object[7];
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                datosErosionCarcava[0] = rs.getInt("ErosionCarcavaID");
                datosErosionCarcava[1] = rs.getInt("SitioID");
                datosErosionCarcava[2] = rs.getInt("Medicion");
                datosErosionCarcava[3] = rs.getFloat("Profundidad");
                datosErosionCarcava[4] = rs.getFloat("Ancho");
                datosErosionCarcava[5] = rs.getFloat("Distancia");
                datosErosionCarcava[6] = rs.getInt("Azimut");

                erosionCarcavaModel.addRow(datosErosionCarcava);
            }
            st.close();
            rs.close();
            return erosionCarcavaModel;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de la vista de erosion de carcava ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en para la vista de erosion de carcava ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public List<Integer> getCampoLongitudCarcava(int sitioID) {
        List<Integer> listLongitudCarcava = new ArrayList<>();
        for (int i = 1; i < 16; i++) {
            listLongitudCarcava.add(i);
        }
        query = "SELECT SitioID, CampoLongitud FROM SUELO_LongitudCarcava WHERE SitioID= " + sitioID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Integer index = rs.getInt(2);
                listLongitudCarcava.remove(index);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los campos de  longitud carcava ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en los campos de longitud de carcava ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        listLongitudCarcava.add(0, null);
        return listLongitudCarcava;
    }
    
    private List<Integer> getListLongitudCarcavaID(int sitioID) {
        List<Integer> listLongitudCarcava = new ArrayList<>();
        query = "SELECT LongitudCarcavaID, SitioID FROM SUELO_LongitudCarcava WHERE SitioID= " + sitioID + " ORDER BY LongitudCarcavaID ASC";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                listLongitudCarcava.add(rs.getInt("LongitudCarcavaID"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los datos de longitud carcava id ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en lista de longitud carcava id", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return listLongitudCarcava;
    }

    public void reenumerarLongitudCarcava(int sitioID) {
        List<Integer> listCampoLongitud = getListLongitudCarcavaID(sitioID);
        Connection conn = LocalConnection.getConnection();

        if (listCampoLongitud != null) {
            int size = listCampoLongitud.size();
            int consecutivo = 1;

            try {
                for (int i = 0; i < size; i++) {
                    Statement st = conn.createStatement();
                    query = "UPDATE SUELO_LongitudCarcava SET CampoLongitud= " + consecutivo + "  WHERE LongitudCarcavaID= " + listCampoLongitud.get(i);
                    st.executeUpdate(query);
                    conn.commit();
                    consecutivo++;
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,
                        "Error! al reenumerar el campo longitud carcava " + e.getClass().getName() + " : " + e.getMessage(),
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al reenumerar el campo longitud carcava ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public CELongitudCarcavas getLongitudCarcava(int longitudCarcavaID) {
        query = "SELECT LongitudCarcavaID, Longitud FROM SUELO_LongitudCarcava"
                + " WHERE LongitudCarcavaID= " + longitudCarcavaID;
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                this.ceLongitudCarcava.setLongitud(rs.getFloat("Longitud"));
            }
            return this.ceLongitudCarcava;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de longitud de carcava ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener la longitud de carcava ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void insertLongitudCarcava(CELongitudCarcavas ceLongitudCarcava) {
        query = "INSERT INTO SUELO_LongitudCarcava(SitioID, CampoLongitud, Longitud)VALUES("
                + ceLongitudCarcava.getSitioID() + ", " + ceLongitudCarcava.getCampoLongitud() + ", " + ceLongitudCarcava.getLongitud() + ")";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(this.query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la informacion en longitud de carcava ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al insertar datos de longitud de carcava ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void updateLongitudCarcava(CELongitudCarcavas ceLongitudCarcava) {
        query = "UPDATE SUELO_LongitudCarcava SET Longitud= " + ceLongitudCarcava.getLongitud() + " WHERE LongitudCarcavaID= " + ceLongitudCarcava.getLongitudCarcavasID();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(this.query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo modificar la información de longitud de carcava ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la modificación de longitud de carcava ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void deleteLongitudCarcava(int longitudCarcavaID) {
        this.query = "DELETE FROM SUELO_LongitudCarcava WHERE LongitudCarcavaID= " + longitudCarcavaID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(this.query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información de longitud de carcava ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  de longitud de carcava ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void deleteLongitudCarcavaSitio(int sitioID) {
        this.query = "DELETE FROM SUELO_LongitudCarcava WHERE SitioID= " + sitioID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(this.query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información de longitud de carcava por sitio"
                    + e.getClass().getName() + " : " + e.getMessage(), "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  de longitud de carcava por sitio",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public DefaultTableModel getTablaLongitudCarcava(int sitioID) {
        query = "SELECT LongitudCarcavaID, SitioID, CampoLongitud, Longitud "
                + "FROM SUELO_LongitudCarcava WHERE SitioID= " + sitioID;
        String[] encabezados = {"LongitudCarcavaID", "SitioID", "Campo de longitud", "Longitud"};
        DefaultTableModel longitudCanalilloModel = new DefaultTableModel(null, encabezados);
        Object[] datosLongitudCanalillo = new Object[4];
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                datosLongitudCanalillo[0] = rs.getInt("LongitudCarcavaID");
                datosLongitudCanalillo[1] = rs.getInt("SitioID");
                datosLongitudCanalillo[2] = rs.getInt("CampoLongitud");
                datosLongitudCanalillo[3] = rs.getFloat("Longitud");
                longitudCanalilloModel.addRow(datosLongitudCanalillo);
            }
            st.close();
            rs.close();
            return longitudCanalilloModel;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de la vista de erosion de canalillo ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en para la vista de erosion de canalillo ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public Integer getNumeroRegistros(String tabla, int sitioID) {
        query = "SELECT COUNT(*) AS Registros FROM " + tabla +" WHERE SitioID= " + sitioID;
        Integer registros = null;
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
               registros  = rs.getInt("Registros");
            }
            st.close();
            rs.close();
            return registros;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener el numero de registros de " + tabla, "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener el numero de registros de " + tabla, "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public Float getPromedioCampo(String tabla, String campo, int sitioID){
        query = "SELECT AVG(" + campo + ") AS Promedio FROM " + tabla + " WHERE SitioID= " + sitioID;
        Float promedio = null;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                promedio= rs.getFloat("Promedio");
            }
            st.close();
            rs.close();
            return promedio;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener el promedio del campo: " + campo + " de la tabla: " + tabla, "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener el el promedio del campo: " + campo + " de la tabla: " + tabla, "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public boolean validarTablaErosionCanalillos(int sitioID) {
        query = "SELECT * FROM SUELO_ErosionHidricaCanalillo WHERE SitioID= " + sitioID;
        boolean vacio = true;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                vacio = false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al validar la presencia de datos en la tabla erosión canalillos", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la tabla de erosión canalillos", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return vacio;
    }
    
    public boolean validarTablaErosionCarcava(int sitioID) {
        query = "SELECT * FROM SUELO_ErosionHidricaCarcava WHERE SitioID= " + sitioID;
        boolean vacio = true;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                vacio = false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al validar la presencia de datos en la tabla erosión carcava", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la tabla de erosión carcava", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return vacio;
    }

}

