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

public class CDMuestrasPerfil {

    private String query;
    
    public List<CatEProfundidadMuestras> getProfundidadMuestras() {
        List listProfundidad = new ArrayList<>();
        CatEProfundidadMuestras profundidad = new CatEProfundidadMuestras();
        query = "SELECT ProfundidadMuestraID, Descripcion FROM CAT_ProfundidadMuestras";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                profundidad.setProfundidadMuestraID(rs.getInt("ProfundidadMuestraID"));
                profundidad.setDescripcion(rs.getString("Descripcion"));
                listProfundidad.add(profundidad);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los tipos de profundidades de perfil ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en los tipos de profundidades de perfil", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        listProfundidad.add(0, null);
        return listProfundidad;
    }
    
    public List<CatEProfundidadMuestras> getProfundidadMuestrasCapturadas(int sitioID) {
        List listProfundidad = new ArrayList<>();
        CatEProfundidadMuestras profundidad2 = new CatEProfundidadMuestras();
        String query1 = "SELECT ProfundidadMuestraID, Descripcion FROM CAT_ProfundidadMuestras";
        String query2 = "SELECT SitioID, ProfundidadID FROM SUELO_Muestras WHERE SitioID= " + sitioID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs1 = st.executeQuery(query1);
            while (rs1.next()) {
                CatEProfundidadMuestras profundidad1 = new CatEProfundidadMuestras();
                profundidad1.setProfundidadMuestraID(rs1.getInt("ProfundidadMuestraID"));
                profundidad1.setDescripcion(rs1.getString("Descripcion"));
                listProfundidad.add(profundidad1);
            }
            ResultSet rs2 = st.executeQuery(query2);
            while (rs2.next()) {
                profundidad2.setProfundidadMuestraID(rs2.getInt("ProfundidadID"));
                listProfundidad.remove(profundidad2);
            }
            rs1.close();
            rs2.close();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los tipos de profundidades de perfil ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en los tipos de profundidades de perfil", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        listProfundidad.add(0, null);
        return listProfundidad;
    }
    
    public CEMuestrasPerfil getDatosMuestrasPerfil(int sitioID) {
        query = "SELECT SitioID, GradosLatitud, MinutosLatitud, SegundosLatitud, "
                + "GradosLongitud, MinutosLongitud, SegundosLongitud, "
                + "Elevacion, DiametroInterno, DiametroExterno, Altura, Observaciones FROM SUELO_MuestrasPerfil "
                + " WHERE SitioID= " + sitioID;
        Connection conn = LocalConnection.getConnection();
        CEMuestrasPerfil perfil = new CEMuestrasPerfil();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                perfil.setGradosLatitud(rs.getInt("GradosLatitud"));
                perfil.setMinutosLatitud(rs.getInt("MinutosLatitud"));
                perfil.setSegundosLatitud(rs.getFloat("SegundosLatitud"));
                perfil.setGradosLongitud(rs.getInt("GradosLongitud"));
                perfil.setMinutosLongitud(rs.getInt("MinutosLongitud"));
                perfil.setSegundosLongitud(rs.getFloat("SegundosLongitud"));
                perfil.setElevacion(rs.getFloat("Elevacion"));
                perfil.setDiametroInterno(rs.getFloat("DiametroInterno"));
                perfil.setDiametroExterno(rs.getFloat("DiametroExterno"));
                perfil.setAltura(rs.getFloat("Altura"));
                perfil.setObservaciones(rs.getString("Observaciones"));
            }
            rs.close();
            st.close();
            return perfil;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los datos de muestras del perfil ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener los datos de muestras del perfil ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public CEMuestras getDatosMuestras(int muestrasID) {
        query = "SELECT MuestrasID, ProfundidadID, PesoMuestra, Muestras,"
                + "Lectura1, Lectura2, Lectura3, Lectura4, Promedio, ClaveColecta "
                + "FROM SUELO_Muestras"
                + " WHERE MuestrasID= " + muestrasID;
        Connection conn = LocalConnection.getConnection();
        CEMuestras muestras = new CEMuestras();
        Float lectura3;
        Float lectura4;
        Float promedio;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                muestras.setProfundidadID(rs.getInt("ProfundidadID"));
                muestras.setPesoMuestra(rs.getFloat("PesoMuestra"));
                muestras.setMuestra(rs.getInt("Muestras"));
                muestras.setLectura1(rs.getFloat("Lectura1"));
                muestras.setLectura2(rs.getFloat("Lectura2"));
                if (rs.getFloat("Lectura3") == 0.0) {
                    muestras.setLectura3(null);
                } else {
                    muestras.setLectura3(rs.getFloat("Lectura3"));
                }
                if (rs.getFloat("Lectura4") == 0.0) {
                    muestras.setLectura4(null);
                } else {
                    muestras.setLectura4(rs.getFloat("Lectura4"));
                }
                if (rs.getFloat("Promedio") == 0.0) {
                    muestras.setPromedio(null);
                } else {
                    muestras.setPromedio(rs.getFloat("Promedio"));
                }
                muestras.setClaveColecta(rs.getString("ClaveColecta"));
            }
            rs.close();
            st.close();
            return muestras;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los datos de muestras ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener los datos de muestras", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void insertMuestrasPerfil(CEMuestrasPerfil perfil) {
        this.query = "INSERT INTO SUELO_MuestrasPerfil(SitioID, GradosLatitud, MinutosLatitud, SegundosLatitud, "
                + "GradosLongitud, MinutosLongitud, SegundosLongitud, Elevacion, DiametroInterno, "
                + "DiametroExterno, Altura, Observaciones)VALUES(" + perfil.getSitioID() + ", " + perfil.getGradosLatitud()
                + ", " + perfil.getMinutosLatitud() + ", " + perfil.getSegundosLatitud() + ","
                + perfil.getGradosLongitud() + "," + perfil.getMinutosLongitud() + ", " + perfil.getSegundosLongitud()
                + ", " + perfil.getElevacion() + ", " + perfil.getDiametroInterno() + ", " + perfil.getDiametroExterno() + ", " + perfil.getAltura()
                + ", '" + perfil.getObservaciones() + "')";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la informacion de muestras perfil ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al insertar datos de muestras perfil",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void updateMuestrasPerfil(CEMuestrasPerfil perfil) {
        query = "UPDATE SUELO_MuestrasPerfil SET GradosLatitud= " + perfil.getGradosLatitud() + ", MinutosLatitud= " + perfil.getMinutosLatitud()
                + ", SegundosLatitud= " + perfil.getSegundosLatitud() + ", GradosLongitud= " + perfil.getGradosLongitud()
                + ", MinutosLongitud= " + perfil.getMinutosLongitud() + ", SegundosLongitud= " + perfil.getSegundosLongitud() + ", Elevacion= " + perfil.getElevacion()
                + ", DiametroInterno= " + perfil.getDiametroInterno() + ", DiametroExterno= " + perfil.getDiametroExterno() + ", Altura= " + perfil.getAltura()
                + ", Observaciones= '" + perfil.getObservaciones() + "' WHERE SitioID= " + perfil.getSitioID();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo modificar la información de muestras de perfil ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la modificación de muestras de perfil", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void deleteMuetrasPerfil(int sitioID) {
        query = "DELETE FROM SUELO_MuestrasPerfil WHERE SitioID= " + sitioID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información de muestras del perfil", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar la información de muestras del perfil",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public DefaultTableModel getTablaMuestras(int sitioID) {
        query = "SELECT MuestrasID, SitioID, Profundidad, Muestras, PesoMuestra, "
                + "Lectura1, Lectura2, Lectura3, Lectura4, Promedio, ClaveColecta FROM VW_Muestras"
                + " WHERE SitioID= " + sitioID;
        String[] encabezados = {"MuestrasID", "SitioID", "Profundidad", "Muestras", "Peso Muestra",
            "Lectura 1", "Lectura 2", "Lectura 3", "Lectura 4", "Promedio", "Clave de colecta"};
        DefaultTableModel muestraModel = new DefaultTableModel(null, encabezados);
        Object[] datosMuestras = new Object[11];
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                datosMuestras[0] = rs.getInt("MuestrasID");
                datosMuestras[1] = rs.getInt("SitioID");
                datosMuestras[2] = rs.getString("Profundidad");
                datosMuestras[3] = rs.getInt("Muestras");
                datosMuestras[4] = rs.getString("PesoMuestra");
                datosMuestras[5] = rs.getString("Lectura1");
                datosMuestras[6] = rs.getString("Lectura2");
                datosMuestras[7] = rs.getString("Lectura3");
                datosMuestras[8] = rs.getString("Lectura4");
                datosMuestras[9] = rs.getString("Promedio");
                datosMuestras[10] = rs.getString("ClaveColecta");
                muestraModel.addRow(datosMuestras);
            }
            st.close();
            rs.close();
            return muestraModel;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de la vista de muestras de perfil ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en para la vista de muestras de perfil", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void insertMuestras(CEMuestras ceMuestras) {
        query = "INSERT INTO SUELO_Muestras(SitioID, ProfundidadID, PesoMuestra,Muestras, Lectura1, Lectura2, "
                + "Lectura3, Lectura4, Promedio, ClaveColecta)VALUES(" + ceMuestras.getSitioID() + ", " + ceMuestras.getProfundidadID()
                +  ", " + ceMuestras.getMuestra() +  ", " + ceMuestras.getPesoMuestra() + ", " + ceMuestras.getLectura1()
                + ", " + ceMuestras.getLectura2() + ", " + ceMuestras.getLectura3() + ", " + ceMuestras.getLectura4()
                + ", " + ceMuestras.getPromedio() +  ", '" + ceMuestras.getClaveColecta() + "')";
        Connection conn = LocalConnection.getConnection();
        System.out.println(query);
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la informacion en muestras", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al insertar datos de muestras",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void updateMuestras(CEMuestras ceMuestras) {
        query = "UPDATE SUELO_Muestras SET PesoMuestra= " + ceMuestras.getPesoMuestra() +",Muestras="+ceMuestras.getMuestra()+ ", Lectura1= " + ceMuestras.getLectura1() + ", Lectura2= " + ceMuestras.getLectura2()
                + ", Lectura3= " + ceMuestras.getLectura3() + ", Lectura4= " + ceMuestras.getLectura4() + ", Promedio= " + ceMuestras.getPromedio()
                + " WHERE MuestrasID= " + ceMuestras.getMuestrasID();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo modificar la información de muestras", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la modificación de muestras", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void deleteMuestras(int muestrasID) {
        query = "DELETE FROM SUELO_Muestras WHERE MuestrasID= " + muestrasID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información de muestras", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  de muestras",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void deleteMuestrasSitio(int sitioID) {
        query = "DELETE FROM SUELO_Muestras WHERE SitioID= " + sitioID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información de muestras", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  de muestras",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public boolean validarTablaMuestras(int sitioID) {
        query = "SELECT * FROM SUELO_Muestras WHERE SitioID= " + sitioID;
        boolean vacio = true;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                vacio = false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al validar la presencia de datos en la tabla muestras de perfil", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la tabla de muestras de perfil", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return vacio;
    }
    
}
