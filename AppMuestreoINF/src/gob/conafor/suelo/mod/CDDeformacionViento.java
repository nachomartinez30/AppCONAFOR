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

public class CDDeformacionViento {

    private String query;
    private CEDeformacionViento ceDeformacion = new CEDeformacionViento();
    private CELongitudMonticulos ceLongitudMonticulos = new CELongitudMonticulos();

    public List<Integer> getMedicionMonticulo(int sitioID) {
        List<Integer> listMedicionMonticulo = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            listMedicionMonticulo.add(i);
        }
        query = "SELECT SitioID, Medicion FROM SUELO_DeformacionViento WHERE SitioID= " + sitioID;
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Integer index = rs.getInt(2);
                listMedicionMonticulo.remove(index);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los numeros de medicion de monticulos ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en los numeros de medicion de monticulo ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        listMedicionMonticulo.add(0, null);
        return listMedicionMonticulo;
    }

    public List<Integer> getListMonticuloID(int sitioID) {
        List<Integer> listMonticuloID = new ArrayList<>();
        query = "SELECT DeformacionVientoID, SitioID FROM SUELO_DeformacionViento WHERE SitioID= " + sitioID + " ORDER BY DeformacionVientoID ASC";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                listMonticuloID.add(rs.getInt("DeformacionVientoID"));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los datos de deformacion por viento id ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en lista de deformacion por viento id", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return listMonticuloID;
    }

    public void reenumerarMonticulos(int sitioID) {
        List<Integer> listDeformacionViento = getListMonticuloID(sitioID);
        Connection conn = LocalConnection.getConnection();

        if (listDeformacionViento != null) {
            int size = listDeformacionViento.size();
            int consecutivo = 1;

            try {
                for (int i = 0; i < size; i++) {
                    Statement st = conn.createStatement();
                    query = "UPDATE SUELO_DeformacionViento SET Medicion= " + consecutivo + "  WHERE DeformacionVientoID= " + listDeformacionViento.get(i);
                    st.executeUpdate(query);
                    conn.commit();
                    consecutivo++;
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,
                        "Error! al reenumerar las mediciones de deformacion por viento ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al reenumerar las mediciones de deformacion por viento ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    public CEDeformacionViento getMonticulo(int deformacionID){
         query = "SELECT DeformacionVientoID, Medicion, Altura, Diametro, Longitud, Distancia, Azimut FROM SUELO_DeformacionViento "
                + " WHERE DeformacionVientoID= " + deformacionID;
        Connection conn = LocalConnection.getConnection();
        
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                this.ceDeformacion.setMedicionMonticulos(rs.getInt("Medicion"));
                this.ceDeformacion.setAlturaMonticulos(rs.getFloat("Altura"));
                this.ceDeformacion.setDiametroMonticulos(rs.getFloat("Diametro"));
                this.ceDeformacion.setLongitudMonticulos(rs.getFloat("Longitud"));
                this.ceDeformacion.setDistanciaMonticulos(rs.getFloat("Distancia"));
                this.ceDeformacion.setAzimutMonticulos(rs.getInt("Azimut"));         
            }
            rs.close();
            st.close();
            return this.ceDeformacion;
        }catch(SQLException e){
          JOptionPane.showMessageDialog(null, "Error! al obtener los datos de deformacion por viento ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener la deformacion por viento ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void insertMonticulos(CEDeformacionViento deformacion) {
        query = "INSERT INTO SUELO_DeformacionViento(SitioID, Medicion, Altura, Diametro, Longitud, Distancia, Azimut)VALUES("
                + deformacion.getSitioID() + ", " + deformacion.getMedicionMonticulos() + ", " + deformacion.getAlturaMonticulos() + ", " + deformacion.getDiametroMonticulos()
                + ", " + deformacion.getLongitudMonticulos() + ", " + deformacion.getDistanciaMonticulos() + ", " + deformacion.getAzimutMonticulos() + ")";
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la informacion en deformacion por viento ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al insertar datos de deformacion por viento ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void updateMonticulos(CEDeformacionViento deformacion) {
        query = "UPDATE SUELO_DeformacionViento SET Altura= " + deformacion.getAlturaMonticulos() + ", Diametro= " + deformacion.getDiametroMonticulos()
                + ", Longitud= " + deformacion.getLongitudMonticulos() + ", Distancia= " + deformacion.getDistanciaMonticulos() + ", Azimut= "
                + deformacion.getAzimutMonticulos() + " WHERE DeformacionVientoID= " + deformacion.getDeformacionVientoID();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo modificar la información de deformacion por viento ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la modificación de deformacion por viento ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void deleteMonticulo(int deformacionID) {
        query = "DELETE FROM SUELO_DeformacionViento WHERE DeformacionVientoID= " + deformacionID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información de deformacion por viento ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  de deformacion por viento ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void deleteMonticuloSitio(int sitioID){
        query = "DELETE FROM SUELO_DeformacionViento WHERE SitioID= " + sitioID;
         Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información de deformacion por viento por sitio", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  de deformacion por viento por sitio",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
     public DefaultTableModel getTablaDeformacionViento(int sitioID) {
        query = "SELECT DeformacionVientoID, SitioID, Medicion, Altura, Diametro, Longitud, Distancia, Azimut "
                + "FROM SUELO_DeformacionViento WHERE SitioID= " + sitioID;
        String[] encabezados = {"DeformacionVientoID", "SitioID", "Medicion", "Altura", "Diametro", "Longitud", 
            "Distancia", "Azimut"};
        DefaultTableModel deformacionVientoModel = new DefaultTableModel(null, encabezados);
        Object[] datosDeformacionViento = new Object[8];
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                datosDeformacionViento[0] = rs.getInt("DeformacionVientoID");
                datosDeformacionViento[1] = rs.getInt("SitioID");
                datosDeformacionViento[2] = rs.getInt("Medicion");
                datosDeformacionViento[3] = rs.getFloat("Altura");
                datosDeformacionViento[4] = rs.getFloat("Diametro");
                datosDeformacionViento[5] = rs.getFloat("Longitud");
                datosDeformacionViento[6] = rs.getFloat("Distancia");
                datosDeformacionViento[7] = rs.getInt("Azimut");

                deformacionVientoModel.addRow(datosDeformacionViento);
            }
            st.close();
            rs.close();
            return deformacionVientoModel;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de la vista de deformacion por viento ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en para la vista de deformacion por viento ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public List<Integer> getCampoLongitudMonticulo(int sitioID) {
        List<Integer> listLongitudCanalillo = new ArrayList<>();
        for (int i = 1; i < 14; i++) {
            listLongitudCanalillo.add(i);
        }
        query = "SELECT SitioID, CampoLongitud FROM SUELO_LongitudMonticulo WHERE SitioID= " + sitioID;
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Integer index = rs.getInt(2);
                listLongitudCanalillo.remove(index);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los campos de  longitud de monticulos",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en los campos de longitud de monticulos ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        listLongitudCanalillo.add(0, null);
        return listLongitudCanalillo;
    }

    private List<Integer> getListLongitudMonticuloID(int sitioID) {
        List<Integer> listLongitudCanalillo = new ArrayList<>();
        query = "SELECT LongitudMonticuloID, SitioID FROM SUELO_LongitudMonticulo WHERE SitioID= " + sitioID + " ORDER BY LongitudMonticuloID ASC";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                listLongitudCanalillo.add(rs.getInt("LongitudMonticuloID"));
            }
            rs.close();
            st.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los datos de longitud monticulo id ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en lista de longitud monticulo id", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return listLongitudCanalillo;
    }

    public void reenumerarLongitudMonticulo(int sitioID) {
        List<Integer> listCampoLongitud = getListLongitudMonticuloID(sitioID);
        Connection conn = LocalConnection.getConnection();

        if (listCampoLongitud != null) {
            int size = listCampoLongitud.size();
            int consecutivo = 1;

            try {
                for (int i = 0; i < size; i++) {
                    Statement st = conn.createStatement();
                    query = "UPDATE SUELO_LongitudMonticulo SET CampoLongitud= " + consecutivo + "  WHERE LongitudMonticuloID= " + listCampoLongitud.get(i);
                    st.executeUpdate(query);
                    conn.commit();
                    consecutivo++;
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,
                        "Error! al reenumerar el campo longitud monticulo ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al reenumerar el campo longitud monticulo ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
     
    public CELongitudMonticulos getLongitudMonticulo(int longitudMonticulosID) {
        query = "SELECT LongitudMonticuloID, Longitud FROM SUELO_LongitudMonticulo "
                + " WHERE LongitudMonticuloID= " + longitudMonticulosID;
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                this.ceLongitudMonticulos.setLongitud(rs.getFloat("Longitud"));
            }
            rs.close();
            st.close();
            return this.ceLongitudMonticulos;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de longitud de monticulos ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener la longitud de monticulos ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
     public void insertLongitudMonticulo(CELongitudMonticulos ceLongitudMonticulo) {
        query = "INSERT INTO SUELO_LongitudMonticulo(SitioID, CampoLongitud, Longitud)VALUES("
                + ceLongitudMonticulo.getSitioID() + ", " + ceLongitudMonticulo.getCampoLongitud() + ", " + ceLongitudMonticulo.getLongitud() + ")";
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la informacion en longitud de monticulos ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al insertar datos de longitud de monticulos ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void updateLongitudMonticulo(CELongitudMonticulos ceLongitudMonticulo) {
        query = "UPDATE SUELO_LongitudMonticulo SET Longitud= " + ceLongitudMonticulo.getLongitud() + " WHERE LongitudMonticuloID= " + 
                ceLongitudMonticulo.getLongitudMonticulosID();
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo modificar la información de longitud de monticulos ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la modificación de longitud de monticulos ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void deleteLongitudMonticulo(int longitudMonticuloID) {
        query = "DELETE FROM SUELO_LongitudMonticulo WHERE LongitudMonticuloID= " + longitudMonticuloID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información de longitud de monticulo ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  de longitud de monticulo ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void deleteLongitudMonticuloSitio(int sitioID) {
        query = "DELETE FROM SUELO_LongitudMonticulo WHERE SitioID= " + sitioID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información de longitud de monticulo por sitio", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  de longitud de monticulo por sitio",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public DefaultTableModel getTablaLongitudMonticulo(int sitioID) {
        query = "SELECT LongitudMonticuloID, SitioID, CampoLongitud, Longitud "
                + "FROM SUELO_LongitudMonticulo WHERE SitioID= " + sitioID;
        String[] encabezados = {"LongitudMonticuloID", "SitioID", "Campo de longitud", "Longitud"};
        DefaultTableModel longitudMonticuloModel = new DefaultTableModel(null, encabezados);
        Object[] datosLongitudMonticulo = new Object[4];
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                datosLongitudMonticulo[0] = rs.getInt("LongitudMonticuloID");
                datosLongitudMonticulo[1] = rs.getInt("SitioID");
                datosLongitudMonticulo[2] = rs.getInt("CampoLongitud");
                datosLongitudMonticulo[3] = rs.getFloat("Longitud");

                longitudMonticuloModel.addRow(datosLongitudMonticulo);
            }
            st.close();
            rs.close();
            return longitudMonticuloModel;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de la vista de erosion de monticulo ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en para la vista de erosion de monticulo ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
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
    
    public Float getPromedioCampo(String tabla, String campo, int sitioID) {
        query = "SELECT AVG(" + campo + ") AS Promedio FROM " + tabla + " WHERE SitioID= " + sitioID;
        Float promedio = null;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                promedio = rs.getFloat("Promedio");
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
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener el el promedio del campo: " + campo + " de la tabla: " + tabla
                       , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public boolean validarTablaErosionViento(int sitioID) {
        query = "SELECT * FROM SUELO_DeformacionViento WHERE SitioID= " + sitioID;
        boolean vacio = true;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                vacio = false;
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al validar la presencia de datos en la tabla erosión por viento", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la tabla de erosión por viento", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return vacio;
    }
}
