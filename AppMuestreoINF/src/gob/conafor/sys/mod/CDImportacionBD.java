package gob.conafor.sys.mod;

import gob.conafor.conn.dat.ExternalConnection;
import gob.conafor.conn.dat.LocalConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class CDImportacionBD {

    private String querySelect;
    private String queryInsert;
    private String queryDelete;
    private Connection baseDatosLocal;
    private Connection baseDatosExterna;
    private Statement sqlExterno;
    private Statement sqlLocal;
    private int upmIDExterno;
    private int upmIDLocal;

    public void validarRepetidos(String ruta) {
        this.querySelect = "SELECT UPMID FROM UPM_UPM";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            this.sqlLocal = this.baseDatosLocal.createStatement();
            ResultSet rsExterno = sqlExterno.executeQuery(this.querySelect);
            ResultSet rsLocal = sqlLocal.executeQuery(this.querySelect);
            while (rsExterno.next()) {
                this.upmIDExterno = rsExterno.getInt("UPMID");
                while (rsLocal.next()) {
                    this.upmIDLocal = rsLocal.getInt("UPMID");
                    if (this.upmIDExterno == this.upmIDLocal) {
                        Object[] opciones = {"Si", "No"};
                        int respuesta = JOptionPane.showOptionDialog(null, "El UPMID: " + this.upmIDExterno + " ya se encuentra en la base de datos local, ¿desea reeplazarlo?",
                                "Importación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[1]);
                        if (respuesta == JOptionPane.YES_OPTION) {
                            eliminarRepetido(upmIDLocal);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo revisar la importacion de la tabla UPM_UPM", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al revisar la importación de la tabla UPM_UPM", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public boolean continuarSinRepetidos(String ruta, int upmID) {
        this.querySelect = "SELECT UPMID FROM UPM_UPM";
        this.baseDatosLocal = LocalConnection.getConnection();
        boolean hayRepetidos = false;
        try {
            this.sqlLocal = this.baseDatosLocal.createStatement();
            ResultSet rsLocal = sqlLocal.executeQuery(this.querySelect);
            while (rsLocal.next()) {
                this.upmIDLocal = rsLocal.getInt("UPMID");
                if (upmID == this.upmIDLocal) {
                    hayRepetidos = false;
                } else {
                    hayRepetidos = true;
                }
            }
            return hayRepetidos;
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo revisar la importacion de la tabla UPM_UPM", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            try {
                baseDatosLocal.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al revisar la importación de la tabla UPM_UPM", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void eliminarRepetido(int upmID) {
        this.queryDelete = "DELETE FROM UPM_UPM WHERE UPMID = " + upmID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(this.queryDelete);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información del UPMID repetido ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar el UPMID repetido",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //1
    public void importarUPM_UPM(String ruta) {
        this.querySelect = "SELECT UPMID, FechaInicio, FechaFin, TipoUPMID, Altitud, PendienteRepresentativa, "
                + "FisiografiaID, ExposicionID, Predio, Paraje, TipoTenenciaID, Accesible, GradosLatitud, MinutosLatitud, "
                + "SegundosLatitud, GradosLongitud, MinutosLongitud, SegundosLongitud, Datum, ErrorPresicion, "
                + "Azimut, Distancia, TipoInaccesibilidadID, OtroTipoInaccesibilidad, ExplicacionInaccesibilidad, "
                + "InformacionContacto FROM UPM_UPM";
        Integer tipoInaccesibilidadID = null;
        Integer UPMID = null;
        String fechaInicio = null;
        String fechaFin = null;
        Integer tipoUpmID = null;
        Float altitud = null;
        Integer pendiente = null;
        Integer fisiografiaID = null;
        Integer exposicionID = null;
        String predio = null;
        String paraje = null;
        Integer tipoTenenciaID = null;
        Integer accesible = null;
        Integer gradosLatitud = null;
        Integer minutosLatitud = null;
        Float segundosLatitud = null;
        Integer gradosLongitud = null;
        Integer minutosLongitud = null;
        Float segundosLongitud = null;
        String datum = null;
        Integer errorPresicion = null;
        Integer azimut = null;
        Float distancia = null;
        String otroTipoInaccesibilidad = null;
        String explicacionInaccesibilidad = null;
        Integer informacionContacto = null;
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                if (rs.getObject("UPMID") != null) {
                    UPMID = rs.getInt("UPMID");
                }
                if (rs.getObject("FechaInicio") != null) {
                    fechaInicio = rs.getString("FechaInicio");
                }
                if (rs.getObject("FechaFin") != null) {
                    fechaFin = rs.getString("FechaFin");
                }
                if (rs.getObject("TipoUPMID") != null) {
                    tipoUpmID = rs.getInt("TipoUPMID");
                }
                if (rs.getObject("Altitud") != null) {
                    altitud = rs.getFloat("Altitud");
                }
                if (rs.getObject("PendienteRepresentativa") != null) {
                    pendiente = rs.getInt("PendienteRepresentativa");
                }
                if (rs.getObject("FisiografiaID") != null) {
                    fisiografiaID = rs.getInt("FisiografiaID");
                }
                if (rs.getObject("ExposicionID") != null) {
                    exposicionID = rs.getInt("ExposicionID");
                }
                if (rs.getObject("Predio") != null) {
                    predio = rs.getString("Predio");
                }
                if (rs.getObject("Paraje") != null) {
                    paraje = rs.getString("Paraje");
                }
                if (rs.getObject("TipoTenenciaID") != null) {
                    tipoTenenciaID = rs.getInt("TipoTenenciaID");
                }
                if (rs.getObject("Accesible") != null) {
                    accesible = rs.getInt("Accesible");
                }
                if (rs.getObject("GradosLatitud") != null) {
                    gradosLatitud = rs.getInt("GradosLatitud");
                }
                if (rs.getObject("MinutosLatitud") != null) {
                    minutosLatitud = rs.getInt("MinutosLatitud");
                }
                if (rs.getObject("SegundosLatitud") != null) {
                    segundosLatitud = rs.getFloat("SegundosLatitud");
                }
                if (rs.getObject("GradosLongitud") != null) {
                    gradosLongitud = rs.getInt("GradosLongitud");
                }
                if (rs.getObject("MinutosLongitud") != null) {
                    minutosLongitud = rs.getInt("MinutosLongitud");
                }
                if (rs.getObject("SegundosLongitud") != null) {
                    segundosLongitud = rs.getFloat("SegundosLongitud");
                }
                if (rs.getObject("Datum") != null) {
                    datum = rs.getString("Datum");
                }
                if (rs.getObject("ErrorPresicion") != null) {
                    errorPresicion = rs.getInt("ErrorPresicion");
                }
                if (rs.getObject("Azimut") != null) {
                    azimut = rs.getInt("Azimut");
                }
                if (rs.getObject("Distancia") != null) {
                    distancia = rs.getFloat("Distancia");
                }
                if (rs.getObject("OtroTipoInaccesibilidad") != null) {
                    otroTipoInaccesibilidad = rs.getString("OtroTipoInaccesibilidad");
                }
                if (rs.getObject("ExplicacionInaccesibilidad") != null) {
                    explicacionInaccesibilidad = rs.getString("ExplicacionInaccesibilidad");
                }
                if (rs.getObject("InformacionContacto") != null) {
                    informacionContacto = rs.getInt("InformacionContacto");
                }
                if (rs.getObject("TipoInaccesibilidadID") != null) {
                    tipoInaccesibilidadID = rs.getInt("TipoInaccesibilidadID");
                }

                ps.executeUpdate("INSERT INTO UPM_UPM(UPMID, FechaInicio, FechaFin, TipoUPMID, Altitud, PendienteRepresentativa, "
                        + "FisiografiaID, ExposicionID, 'Predio', 'Paraje', TipoTenenciaID, Accesible, GradosLatitud, MinutosLatitud, "
                        + "SegundosLatitud, GradosLongitud, MinutosLongitud, SegundosLongitud, Datum, ErrorPresicion, "
                        + "Azimut, Distancia, TipoInaccesibilidadID, OtroTipoInaccesibilidad, ExplicacionInaccesibilidad, "
                        + "InformacionContacto)VALUES(" + UPMID + ", '" + fechaInicio + "', '" + fechaFin + "', " + tipoUpmID + ", " + altitud + ", " + pendiente + " , " + fisiografiaID + ", " + exposicionID
                        + ", '" + predio + "', '" + paraje + "', " + tipoTenenciaID + ", " + accesible + ", " + gradosLatitud + ", " + minutosLatitud + ", " + segundosLatitud + ", " + gradosLongitud + ", " + minutosLongitud + ", " + segundosLongitud + " , '"
                        + datum + "', " + errorPresicion + ", " + azimut + ", " + distancia + ", " + tipoInaccesibilidadID + ", '" + otroTipoInaccesibilidad + "', '" + explicacionInaccesibilidad + "', " + informacionContacto + ")");
                this.baseDatosLocal.commit();
                tipoInaccesibilidadID = null;
                UPMID = null;
                fechaInicio = null;
                fechaFin = null;
                tipoUpmID = null;
                altitud = null;
                pendiente = null;
                fisiografiaID = null;
                exposicionID = null;
                predio = null;
                paraje = null;
                tipoTenenciaID = null;
                accesible = null;
                gradosLatitud = null;
                minutosLatitud = null;
                segundosLatitud = null;
                gradosLongitud = null;
                minutosLongitud = null;
                segundosLongitud = null;
                datum = null;
                errorPresicion = null;
                azimut = null;
                distancia = null;
                otroTipoInaccesibilidad = null;
                explicacionInaccesibilidad = null;
                informacionContacto = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla UPM_UPM", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla UPM_UPM", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//2
    public void importarPC(String ruta) {
        this.querySelect = "SELECT UPMID, Descripcion, Paraje, GradosLatitud, MinutosLatitud, SegundosLatitud, GradosLongitud, "
                + "MinutosLongitud, SegundosLongitud, ErrorPresicion, Datum, Azimut, Distancia FROM PC_PuntoControl";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        Integer upmID = null;
        String descripcion = null;
        String paraje = null;
        Integer gradosLatitud = null;
        Integer minutosLatitud = null;
        Float segundosLatitud = null;
        Integer gradosLongitud = null;
        Integer minutosLongitud = null;
        Float segundosLongitud = null;
        Integer errorPresicion = null;
        String datum = null;
        Integer azimut = null;
        Float distancia = null;
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                if (rs.getObject("UPMID") != null) {
                    upmID = rs.getInt("UPMID");
                }
                if (rs.getObject("Descripcion") != null) {
                    descripcion = rs.getString("Descripcion");
                }
                if (rs.getObject("Paraje") != null) {
                    paraje = rs.getString("Paraje");
                }
                if (rs.getObject("GradosLatitud") != null) {
                    gradosLatitud = rs.getInt("GradosLatitud");
                }
                if (rs.getObject("MinutosLatitud") != null) {
                    minutosLatitud = rs.getInt("MinutosLatitud");
                }
                if (rs.getObject("SegundosLatitud") != null) {
                    segundosLatitud = rs.getFloat("SegundosLatitud");
                }
                if (rs.getObject("GradosLongitud") != null) {
                    gradosLongitud = rs.getInt("GradosLongitud");
                }
                if (rs.getObject("MinutosLongitud") != null) {
                    minutosLongitud = rs.getInt("MinutosLongitud");
                }
                if (rs.getObject("SegundosLongitud") != null) {
                    segundosLongitud = rs.getFloat("SegundosLongitud");
                }
                if (rs.getObject("ErrorPresicion") != null) {
                    errorPresicion = rs.getInt("ErrorPresicion");
                }
                if (rs.getObject("Datum") != null) {
                    datum = rs.getString("Datum");
                }
                if (rs.getObject("Azimut") != null) {
                    azimut = rs.getInt("Azimut");
                }
                if (rs.getObject("Distancia") != null) {
                    distancia = rs.getFloat("Distancia");
                }
                ps.executeUpdate("INSERT INTO PC_PuntoControl(UPMID, Descripcion, Paraje, GradosLatitud, MinutosLatitud, SegundosLatitud, GradosLongitud, "
                        + "MinutosLongitud, SegundosLongitud, ErrorPresicion, Datum, Azimut, Distancia)VALUES(" + upmID + ", '" + descripcion + "', '" + paraje + "', " + gradosLatitud
                        + ", " + minutosLatitud + ", " + segundosLatitud + ", " + gradosLongitud + ", " + minutosLongitud + ", " + segundosLongitud + ", " + errorPresicion + ", '"
                        + datum + "', " + azimut + ", " + distancia + ")");
                this.baseDatosLocal.commit();
                upmID = null;
                descripcion = null;
                paraje = null;
                gradosLatitud = null;
                minutosLatitud = null;
                segundosLatitud = null;
                gradosLongitud = null;
                minutosLongitud = null;
                segundosLongitud = null;
                errorPresicion = null;
                datum = null;
                azimut = null;
                distancia = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla PC_PuntoControl", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla PC_PuntoControl", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//3
    public void importarAccesibilidadPC(String ruta) {
        this.querySelect = "SELECT UPMID, MedioTransporteID, ViaAccesibilidadID, Distancia, CondicionAccesibilidadID FROM PC_Accesibilidad";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        Integer upmID = null;
        Integer medioTransporteID = null;
        Integer viaAccesibilidadID = null;
        Float distancia = null;
        Integer condicionAccesibilidadID = null;
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                if (rs.getObject("UPMID") != null) {
                    upmID = rs.getInt("UPMID");
                }
                if (rs.getObject("MedioTransporteID") != null) {
                    medioTransporteID = rs.getInt("MedioTransporteID");
                }
                if (rs.getObject("ViaAccesibilidadID") != null) {
                    viaAccesibilidadID = rs.getInt("ViaAccesibilidadID");
                }
                if (rs.getObject("Distancia") != null) {
                    distancia = rs.getFloat("Distancia");
                }
                if (rs.getObject("CondicionAccesibilidadID") != null) {
                    condicionAccesibilidadID = rs.getInt("CondicionAccesibilidadID");
                }

                ps.executeUpdate("INSERT INTO PC_Accesibilidad(UPMID, MedioTransporteID, ViaAccesibilidadID, Distancia, CondicionAccesibilidadID)"
                        + "VALUES(" + upmID + ", " + medioTransporteID + ", " + viaAccesibilidadID + ", " + distancia + ", " + condicionAccesibilidadID + ")");
                this.baseDatosLocal.commit();
                upmID = null;
                medioTransporteID = null;
                viaAccesibilidadID = null;
                distancia = null;
                condicionAccesibilidadID = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla PC_Accesibilidad", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla PC_Accesibilidad", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//4
    public void importarSitios(String ruta) {
        this.querySelect = "SELECT UPMID, SitioID, Sitio, SenialGPS, GradosLatitud, MinutosLatitud, SegundosLatitud, GradosLongitud, MinutosLongitud, "
                + "SegundosLongitud, ErrorPresicion, EvidenciaMuestreo, Datum, Azimut, Distancia, SitioAccesible, TipoInaccesibilidad, ExplicacionInaccesibilidad, CoberturaForestal, "
                + "Condicion, ClaveSerieV, FaseSucecional, ArbolFuera, Ecotono, CondicionPresenteCampo, CondicionEcotono, RepobladoFuera, PorcentajeRepoblado, "
                + "SotoBosqueFuera, PorcentajeSotoBosqueFuera, Observaciones, HipsometroBrujula, CintaClinometroBrujula, Cuadrante1, Cuadrante2, Cuadrante3, "
                + "Cuadrante4, Distancia1, Distancia2, Distancia3, Distancia4 FROM SITIOS_Sitio";
        Integer evidenciaMuestreo = null;
        Integer condicion = null;
        Integer faseSucecional = null;
        Integer azimut = null;
        Float distancia = null;
        Integer tipoInaccesibilidad = null;
        String explicacionInaccesibilidad = null;
        Integer porcentajeRepoblado = null;
        Integer porcentajeSotoBosque = null;
        Integer upmID = null;
        Integer sitioID = null;
        Integer sitio = null;
        Integer senialGPS = null;
        Integer gradosLatitud = null;
        Integer minutosLatitud = null;
        Float segundosLatitud = null;
        Integer gradosLongitud = null;
        Integer minutosLongitud = null;
        Float segundosLongitud = null;
        Integer errorPresicion = null;
        Integer arbolFuera = null;
        Integer ecotono = null;
        String condicionPresente = null;
        String condicionEcotono = null;
        Integer repobladoFuera = null;
        Integer sotoBosqueFuera = null;
        String observaciones = null;
        Integer hipsometroBrujula = null;
        Integer cintaClinometroBrujula = null;
        Integer cuadrante1 = null;
        Integer cuadrante2 = null;
        Integer cuadrante3 = null;
        Integer cuadrante4 = null;
        Float distancia1 = null;
        Float distancia2 = null;
        Float distancia3 = null;
        Float distancia4 = null;
        Integer claveSerieV = null;
        Integer coberturaForestal = null;
        String datum = null;
        Integer sitioAccesible = null;

        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                if (rs.getObject("UPMID") != null) {
                    upmID = rs.getInt("UPMID");
                }
                if (rs.getObject("SitioID") != null) {
                    sitioID = rs.getInt("SitioID");
                }
                if (rs.getObject("Sitio") != null) {
                    sitio = rs.getInt("Sitio");
                }
                if (rs.getObject("SenialGPS") != null) {
                    senialGPS = rs.getInt("SenialGPS");
                }
                if (rs.getObject("GradosLatitud") != null) {
                    gradosLatitud = rs.getInt("GradosLatitud");
                }
                if (rs.getObject("MinutosLatitud") != null) {
                    minutosLatitud = rs.getInt("MinutosLatitud");
                }
                if (rs.getObject("SegundosLatitud") != null) {
                    segundosLatitud = rs.getFloat("SegundosLatitud");
                }
                if (rs.getObject("GradosLongitud") != null) {
                    gradosLongitud = rs.getInt("GradosLongitud");
                }
                if (rs.getObject("MinutosLongitud") != null) {
                    minutosLongitud = rs.getInt("MinutosLongitud");
                }
                if (rs.getObject("SegundosLongitud") != null) {
                    segundosLongitud = rs.getFloat("SegundosLongitud");
                }
                if (rs.getObject("ErrorPresicion") != null) {
                    errorPresicion = rs.getInt("ErrorPresicion");
                }
                if (rs.getObject("ArbolFuera") != null) {
                    arbolFuera = rs.getInt("ArbolFuera");
                }
                if (rs.getObject("Ecotono") != null) {
                    ecotono = rs.getInt("Ecotono");
                }
                if (rs.getObject("CondicionPresenteCampo") != null) {
                    condicionPresente = rs.getString("CondicionPresenteCampo");
                }
                if (rs.getObject("CondicionEcotono") != null) {
                    condicionEcotono = rs.getString("CondicionEcotono");
                }
                if (rs.getObject("RepobladoFuera") != null) {
                    repobladoFuera = rs.getInt("RepobladoFuera");
                }
                if (rs.getObject("SotoBosqueFuera") != null) {
                    sotoBosqueFuera = rs.getInt("SotoBosqueFuera");
                }
                if (rs.getObject("Observaciones") != null) {
                    observaciones = rs.getString("Observaciones");
                }
                if (rs.getObject("HipsometroBrujula") != null) {
                    hipsometroBrujula = rs.getInt("HipsometroBrujula");
                }
                if (rs.getObject("CintaClinometroBrujula") != null) {
                    cintaClinometroBrujula = rs.getInt("CintaClinometroBrujula");
                }
                if (rs.getObject("Cuadrante1") != null) {
                    cuadrante1 = rs.getInt("Cuadrante1");
                }
                if (rs.getObject("Cuadrante2") != null) {
                    cuadrante2 = rs.getInt("Cuadrante2");
                }
                if (rs.getObject("Cuadrante3") != null) {
                    cuadrante3 = rs.getInt("Cuadrante3");
                }
                if (rs.getObject("Cuadrante4") != null) {
                    cuadrante4 = rs.getInt("Cuadrante4");
                }
                if (rs.getObject("Distancia1") != null) {
                    distancia1 = rs.getFloat("Distancia1");
                }
                if (rs.getObject("Distancia2") != null) {
                    distancia2 = rs.getFloat("Distancia2");
                }
                if (rs.getObject("Distancia3") != null) {
                    distancia3 = rs.getFloat("Distancia3");
                }
                if (rs.getObject("Distancia4") != null) {
                    distancia4 = rs.getFloat("Distancia4");
                }
                if (rs.getObject("EvidenciaMuestreo") != null) {
                    evidenciaMuestreo = rs.getInt("EvidenciaMuestreo");
                }
                if (rs.getObject("ClaveSerieV") != null) {
                    claveSerieV = rs.getInt("ClaveSerieV");
                }
                if (rs.getObject("CoberturaForestal") != null) {
                    coberturaForestal = rs.getInt("CoberturaForestal");
                }
                if (rs.getObject("Datum") != null) {
                    datum = rs.getString("Datum");
                }
                if (rs.getObject("SitioAccesible") != null) {
                    sitioAccesible = rs.getInt("SitioAccesible");
                }
                if (rs.getObject("Condicion") != null) {
                    condicion = rs.getInt("Condicion");
                }

                if (rs.getObject("FaseSucecional") != null) {
                    faseSucecional = rs.getInt("FaseSucecional");
                }
                if (rs.getObject("Azimut") != null) {
                    azimut = rs.getInt("Azimut");
                }
                if (rs.getObject("Distancia") != null) {
                    distancia = rs.getFloat("Distancia");
                }
                if (rs.getObject("TipoInaccesibilidad") != null) {
                    tipoInaccesibilidad = rs.getInt("TipoInaccesibilidad");
                }

                if (rs.getObject("ExplicacionInaccesibilidad") != null) {
                    explicacionInaccesibilidad = rs.getString("ExplicacionInaccesibilidad");
                }
                if (rs.getObject("PorcentajeRepoblado") != null) {
                    porcentajeRepoblado = rs.getInt("PorcentajeRepoblado");
                }
                if (rs.getObject("PorcentajeSotoBosqueFuera") != null) {
                    porcentajeSotoBosque = rs.getInt("PorcentajeSotoBosqueFuera");
                }

                ps.executeUpdate("INSERT INTO SITIOS_Sitio(UPMID, SitioID, Sitio, SenialGPS, GradosLatitud, MinutosLatitud, SegundosLatitud, GradosLongitud, MinutosLongitud, "
                        + "SegundosLongitud, ErrorPresicion, EvidenciaMuestreo, Datum, Azimut, Distancia, SitioAccesible, TipoInaccesibilidad, ExplicacionInaccesibilidad, CoberturaForestal, "
                        + "Condicion, ClaveSerieV, FaseSucecional, ArbolFuera, Ecotono, CondicionPresenteCampo, CondicionEcotono, RepobladoFuera, PorcentajeRepoblado, "
                        + "SotoBosqueFuera, PorcentajeSotoBosqueFuera, Observaciones, HipsometroBrujula, CintaClinometroBrujula, Cuadrante1, Cuadrante2, Cuadrante3, "
                        + "Cuadrante4, Distancia1, Distancia2, Distancia3, Distancia4)VALUES(" + upmID + ", " + sitioID + ", " + sitio + ", " + senialGPS + ", " + gradosLatitud + ", " + minutosLatitud + ", " + segundosLatitud
                        + ", " + gradosLongitud + ", " + minutosLongitud + ", " + segundosLongitud + ", " + errorPresicion + ", " + evidenciaMuestreo + ", '" + datum + "', " + azimut + ", " + distancia
                        + ", " + sitioAccesible + ", " + tipoInaccesibilidad + ", '" + explicacionInaccesibilidad + "', " + coberturaForestal + ", " + condicion + ", " + claveSerieV + ", " + faseSucecional + ", " + arbolFuera
                        + ", " + ecotono + ", '" + condicionPresente + "', '" + condicionEcotono + "', " + repobladoFuera + ", " + porcentajeRepoblado + ", " + sotoBosqueFuera + ", " + porcentajeSotoBosque
                        + ", '" + observaciones + "', " + hipsometroBrujula + ", " + cintaClinometroBrujula + ", " + cuadrante1 + ", " + cuadrante2 + ", " + cuadrante3 + ", " + cuadrante4 + ", " + distancia1
                        + ", " + distancia2 + ", " + distancia3 + ", " + distancia4 + ")");
                this.baseDatosLocal.commit();
                evidenciaMuestreo = null;
                condicion = null;
                faseSucecional = null;
                azimut = null;
                distancia = null;
                tipoInaccesibilidad = null;
                explicacionInaccesibilidad = null;
                porcentajeRepoblado = null;
                porcentajeSotoBosque = null;
                upmID = null;
                sitioID = null;
                sitio = null;
                senialGPS = null;
                gradosLatitud = null;
                minutosLatitud = null;
                segundosLatitud = null;
                gradosLongitud = null;
                minutosLongitud = null;
                segundosLongitud = null;
                errorPresicion = null;
                arbolFuera = null;
                ecotono = null;
                condicionPresente = null;
                condicionEcotono = null;
                repobladoFuera = null;
                sotoBosqueFuera = null;
                observaciones = null;
                hipsometroBrujula = null;
                cintaClinometroBrujula = null;
                cuadrante1 = null;
                cuadrante2 = null;
                cuadrante3 = null;
                cuadrante4 = null;
                distancia1 = null;
                distancia2 = null;
                distancia3 = null;
                distancia4 = null;
                claveSerieV = null;
                coberturaForestal = null;
                datum = null;
                sitioAccesible = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SITIOS_Sitio", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SITIOS_Sitio", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//5
    public void importarSitiosCoberturaSuelo(String ruta) {
        this.querySelect = "SELECT CoberturaID, SitioID, Gramineas, Helechos, Musgos, Liquenes, Hierbas, Roca, SueloDesnudo, Hojarasca, Grava, Otros FROM SITIOS_CoberturaSuelo";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        Integer coberturaID = null;
        Integer sitioID = null;
        Integer gramineas = null;
        Integer helechos = null;
        Integer musgos = null;
        Integer liquenes = null;
        Integer hierbas = null;
        Integer rocas = null;
        Integer sueloDesnudo = null;
        Integer hojarasca = null;
        Integer grava = null;
        Integer otros = null;
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                if (rs.getObject("CoberturaID") != null) {
                    coberturaID = rs.getInt("CoberturaID");
                }
                if (rs.getObject("SitioID") != null) {
                    sitioID = rs.getInt("SitioID");
                }
                if (rs.getObject("Gramineas") != null) {
                    gramineas = rs.getInt("Gramineas");
                }
                if (rs.getObject("Helechos") != null) {
                    helechos = rs.getInt("Helechos");
                }
                if (rs.getObject("Musgos") != null) {
                    musgos = rs.getInt("Musgos");
                }
                if (rs.getObject("Liquenes") != null) {
                    liquenes = rs.getInt("Liquenes");
                }
                if (rs.getObject("Hierbas") != null) {
                    hierbas = rs.getInt("Hierbas");
                }
                if (rs.getObject("Roca") != null) {
                    rocas = rs.getInt("Roca");
                }
                if (rs.getObject("SueloDesnudo") != null) {
                    sueloDesnudo = rs.getInt("SueloDesnudo");
                }
                if (rs.getObject("Hojarasca") != null) {
                    hojarasca = rs.getInt("Hojarasca");
                }
                if (rs.getObject("Grava") != null) {
                    grava = rs.getInt("Grava");
                }
                if (rs.getObject("Otros") != null) {
                    otros = rs.getInt("Otros");
                }

                ps.executeUpdate("INSERT INTO SITIOS_CoberturaSuelo(CoberturaID, SitioID, Gramineas, Helechos, Musgos, Liquenes, Hierbas, Roca, SueloDesnudo, Hojarasca, Grava, Otros)"
                        + "VALUES(" + coberturaID + ", " + sitioID + ", " + gramineas + ", " + helechos + ", " + musgos + ", " + liquenes + ", " + hierbas + ", " + rocas
                        + ", " + sueloDesnudo + ", " + hojarasca + ", " + grava + ", " + otros + ")");
                this.baseDatosLocal.commit();
                coberturaID = null;
                sitioID = null;
                gramineas = null;
                helechos = null;
                musgos = null;
                liquenes = null;
                hierbas = null;
                rocas = null;
                sueloDesnudo = null;
                hojarasca = null;
                grava = null;
                otros = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SITIOS_CoberturaSuelo", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SITIOS_CoberturaSuelo", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//5
    public void importarFotografiaHemisferica(String ruta) {
        this.querySelect = "SELECT FotografiaHemisfericaID, SitioID, CoberturaArborea, TomaFotografia, Hora, DeclinacionMagnetica FROM SITIOS_FotografiaHemisferica";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        Integer fotografiaHemisfericaID = null;
        Integer sitioID = null;
        Integer coberturaArborea = null;
        Integer tomaFotografia = null;
        String hora = null;
        Integer declinacionMagnetica = null;
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);

            while (rs.next()) {
                if (rs.getObject("FotografiaHemisfericaID") != null) {
                    fotografiaHemisfericaID = rs.getInt("FotografiaHemisfericaID");
                }
                if (rs.getObject("SitioID") != null) {
                    sitioID = rs.getInt("SitioID");
                }
                if (rs.getObject("CoberturaArborea") != null) {
                    coberturaArborea = rs.getInt("CoberturaArborea");
                }
                if (rs.getObject("TomaFotografia") != null) {
                    tomaFotografia = rs.getInt("TomaFotografia");
                }
                if (rs.getObject("Hora") != null) {
                    hora = rs.getString("Hora");
                }
                if (rs.getObject("DeclinacionMagnetica") != null) {
                    declinacionMagnetica = rs.getInt("DeclinacionMagnetica");
                }
                ps.executeUpdate("INSERT INTO SITIOS_FotografiaHemisferica(FotografiaHemisfericaID, SitioID, CoberturaArborea, TomaFotografia, Hora, DeclinacionMagnetica)"
                        + "VALUES(" + fotografiaHemisfericaID + ", " + sitioID + ", " + coberturaArborea + ", " + tomaFotografia + ", '" + hora + "', " + declinacionMagnetica + ")");
                this.baseDatosLocal.commit();
                fotografiaHemisfericaID = null;
                sitioID = null;
                coberturaArborea = null;
                tomaFotografia = null;
                hora = null;
                declinacionMagnetica = null;
                ps.close();
            }

            this.sqlExterno.close();
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SITIOS_FotografiaHemisferica", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SITIOS_FotografiaHemisferica", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //6
    public void importarTransponder(String ruta) {
        this.querySelect = "SELECT TransponderID, SitioID, TipoColocacionID, Especifique, Observaciones FROM SITIOS_Transponder";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        Integer transponderID = null;
        Integer sitioID = null;
        Integer tipoColocacionID = null;
        String especifique = null;
        String observaciones = null;
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {

                if (rs.getObject("TransponderID") != null) {
                    transponderID = rs.getInt("TransponderID");
                }
                if (rs.getObject("SitioID") != null) {
                    sitioID = rs.getInt("SitioID");
                }
                if (rs.getObject("TipoColocacionID") != null) {
                    tipoColocacionID = rs.getInt("TipoColocacionID");
                }
                if (rs.getObject("Especifique") != null) {
                    especifique = rs.getString("Especifique");
                }
                if (rs.getObject("Observaciones") != null) {
                    observaciones = rs.getString("Observaciones");
                }
                ps.executeUpdate("INSERT INTO SITIOS_Transponder(TransponderID,SitioID, TipoColocacionID, Especifique, Observaciones)"
                        + "VALUES(" + transponderID + ", " + sitioID + ", " + tipoColocacionID + ", '" + especifique + "', '" + observaciones + "')");
                this.baseDatosLocal.commit();
                transponderID = null;
                sitioID = null;
                tipoColocacionID = null;
                especifique = null;
                observaciones = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SITIOS_Transponder", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SITIOS_Transponder", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//7
    public void importarParametrosFisicoQuimicos(String ruta) {
        this.querySelect = "SELECT ParametrosFQID, SitioID, TipoAgua, Salinidad, Temperatura, ConductividadElectrica, Ph, PotencialRedox, Profundidad, Observaciones FROM "
                + "SITIOS_ParametrosFisicoQuimicos";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        Integer parametrosFQID = null;
        Integer sitioID = null;
        Integer tipoAgua = null;
        Float salinidad = null;
        Float temperatura = null;
        Float conductividadElectrica = null;
        Float ph = null;
        Float potencialRedox = null;
        Float profundidad = null;
        String observaciones = null;
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                if (rs.getObject("ParametrosFQID") != null) {
                    parametrosFQID = rs.getInt("ParametrosFQID");
                }
                if (rs.getObject("SitioID") != null) {
                    sitioID = rs.getInt("SitioID");
                }
                if (rs.getObject("TipoAgua") != null) {
                    tipoAgua = rs.getInt("TipoAgua");
                }
                if (rs.getObject("Salinidad") != null) {
                    salinidad = rs.getFloat("Salinidad");
                }
                if (rs.getObject("Temperatura") != null) {
                    temperatura = rs.getFloat("Temperatura");
                }
                if (rs.getObject("ConductividadElectrica") != null) {
                    conductividadElectrica = rs.getFloat("ConductividadElectrica");
                }
                if (rs.getObject("Ph") != null) {
                    ph = rs.getFloat("Ph");
                }
                if (rs.getObject("PotencialRedox") != null) {
                    potencialRedox = rs.getFloat("PotencialRedox");
                }
                if (rs.getObject("Profundidad") != null) {
                    profundidad = rs.getFloat("Profundidad");
                }
                if (rs.getObject("Observaciones") != null) {
                    observaciones = rs.getString("Observaciones");
                }
                ps.executeUpdate("INSERT INTO SITIOS_ParametrosFisicoQuimicos(ParametrosFQID, SitioID, TipoAgua, Salinidad, Temperatura, ConductividadElectrica, Ph, PotencialRedox, "
                        + "Profundidad, Observaciones)VALUES(" + parametrosFQID + ", " + sitioID + ", " + tipoAgua + ", " + salinidad + ", " + temperatura + ", " + conductividadElectrica + ", " + ph
                        + ", " + potencialRedox + ", " + profundidad + ", '" + observaciones + "')");
                this.baseDatosLocal.commit();
                parametrosFQID = null;
                sitioID = null;
                tipoAgua = null;
                salinidad = null;
                temperatura = null;
                conductividadElectrica = null;
                ph = null;
                potencialRedox = null;
                profundidad = null;
                observaciones = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SITIOS_ParametrosFisicoQuimicos", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SITIOS_ParametrosFisicoQuimicos", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //8
    public void importarSueloCanalillo(String ruta) {
        this.querySelect = "SELECT CanalilloID, SitioID, Numero, Ancho, Profundidad FROM SUELO_Canalillo";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        Integer canalilloID = null;
        Integer sitioID = null;
        Integer numero = null;
        Float ancho = null;
        Float profundidad = null;
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                if (rs.getObject("CanalilloID") != null) {
                    canalilloID = rs.getInt("CanalilloID");
                }
                if (rs.getObject("SitioID") != null) {
                    sitioID = rs.getInt("SitioID");
                }
                if (rs.getObject("Numero") != null) {
                    numero = rs.getInt("Numero");
                }
                if (rs.getObject("Ancho") != null) {
                    ancho = rs.getFloat("Ancho");
                }
                if (rs.getObject("Profundidad") != null) {
                    profundidad = rs.getFloat("Profundidad");
                }
                ps.executeUpdate("INSERT INTO SUELO_Canalillo(CanalilloID, SitioID, Numero, Ancho, Profundidad)VALUES(" + canalilloID + ", " + sitioID + ", " + numero + ", " + ancho + ", " + profundidad + ")");
                this.baseDatosLocal.commit();
                canalilloID = null;
                sitioID = null;
                numero = null;
                ancho = null;
                profundidad = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_Canalillo", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_Canalillo", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //9
    public void importarSueloCarcava(String ruta) {
        this.querySelect = "SELECT CarcavaID, SitioID, Numero, Ancho, Profundidad FROM SUELO_Carcava";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        Integer carcavaID = null;
        Integer sitioID = null;
        Integer numero = null;
        Float ancho = null;
        Float profundidad = null;
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                if (rs.getObject("CarcavaID") != null) {
                    carcavaID = rs.getInt("CarcavaID");
                }
                if (rs.getObject("SitioID") != null) {
                    sitioID = rs.getInt("SitioID");
                }
                if (rs.getObject("Numero") != null) {
                    numero = rs.getInt("Numero");
                }
                if (rs.getObject("Ancho") != null) {
                    ancho = rs.getFloat("Ancho");
                }
                if (rs.getObject("Profundidad") != null) {
                    profundidad = rs.getFloat("Profundidad");
                }
                ps.executeUpdate("INSERT INTO SUELO_Carcava(CarcavaID, SitioID, Numero, Ancho, Profundidad)VALUES(" + carcavaID + ", " + sitioID + ", " + numero + ", " + ancho + ", " + profundidad + ")");
                this.baseDatosLocal.commit();
                carcavaID = null;
                sitioID = null;
                numero = null;
                ancho = null;
                profundidad = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_Carcava", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_Carcava", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //10
    public void importarSueloCobertura(String ruta) {
        this.querySelect = "SELECT CoberturaSueloID, SitioID, Transecto, Pendiente FROM SUELO_CoberturaSuelo";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        Integer coberturaSueloID = null;
        Integer sitioID = null;
        Integer transecto = null;
        Integer pendiente = null;
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                if (rs.getObject("CoberturaSueloID") != null) {
                    coberturaSueloID = rs.getInt("CoberturaSueloID");
                }
                if (rs.getObject("SitioID") != null) {
                    sitioID = rs.getInt("SitioID");
                }
                if (rs.getObject("Transecto") != null) {
                    transecto = rs.getInt("Transecto");
                }
                if (rs.getObject("Pendiente") != null) {
                    pendiente = rs.getInt("Pendiente");
                }
                ps.executeUpdate("INSERT INTO SUELO_CoberturaSuelo(CoberturaSueloID, SitioID, Transecto, Pendiente)VALUES(" + coberturaSueloID + ", " + sitioID + ", " + transecto + ", " + pendiente + ")");
                this.baseDatosLocal.commit();
                coberturaSueloID = null;
                sitioID = null;
                transecto = null;
                pendiente = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_CoberturaSuelo", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_CoberturaSuelo", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //11
    public void importarSueloCostras(String ruta) {
        this.querySelect = "SELECT CostrasID, SitioID, Numero, Diametro FROM SUELO_Costras";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        Integer costrasID = null;
        Integer sitioID = null;
        Integer numero = null;
        Float diametro = null;
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                if (rs.getObject("CostrasID") != null) {
                    costrasID = rs.getInt("CostrasID");
                }
                if (rs.getObject("SitioID") != null) {
                    sitioID = rs.getInt("SitioID");
                }
                if (rs.getObject("Numero") != null) {
                    numero = rs.getInt("Numero");
                }
                if (rs.getObject("Diametro") != null) {
                    diametro = rs.getFloat("Diametro");
                }
                ps.executeUpdate("INSERT INTO SUELO_Costras(CostrasID, SitioID, Numero, Diametro)VALUES(" + costrasID + ", " + sitioID + ", " + numero + ", " + diametro + ")");
                this.baseDatosLocal.commit();
                costrasID = null;
                sitioID = null;
                numero = null;
                diametro = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_Costras", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_Costras", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //12
    public void importarSueloDeformacionViento(String ruta) {
        this.querySelect = "SELECT DeformacionVientoID, SitioID, Medicion, Altura, Diametro, Longitud, Distancia, Azimut FROM SUELO_DeformacionViento";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        Integer deformacionVientoID = null;
        Integer sitioID = null;
        Integer medicion = null;
        Float altura = null;
        Float diametro = null;
        Float longitud = null;
        Float distancia = null;
        Integer azimut = null;
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                if (rs.getObject("DeformacionVientoID") != null) {
                    deformacionVientoID = rs.getInt("DeformacionVientoID");
                }
                if (rs.getObject("SitioID") != null) {
                    sitioID = rs.getInt("SitioID");
                }
                if (rs.getObject("Medicion") != null) {
                    medicion = rs.getInt("Medicion");
                }
                if (rs.getObject("Altura") != null) {
                    altura = rs.getFloat("Altura");
                }
                if (rs.getObject("Diametro") != null) {
                    diametro = rs.getFloat("Diametro");
                }
                if (rs.getObject("Longitud") != null) {
                    longitud = rs.getFloat("Longitud");
                }
                if (rs.getObject("Distancia") != null) {
                    distancia = rs.getFloat("Distancia");
                }
                if (rs.getObject("Azimut") != null) {
                    azimut = rs.getInt("Azimut");
                }
                ps.executeUpdate("INSERT INTO SUELO_DeformacionViento(DeformacionVientoID, SitioID, Medicion, Altura, Diametro, Longitud, Distancia, Azimut)VALUES(" + deformacionVientoID + ", " + sitioID + ", " + medicion + ", " + altura + ", " + diametro + ", " + longitud + ", " + distancia + ", " + azimut + ")");
                this.baseDatosLocal.commit();
                deformacionVientoID = null;
                sitioID = null;
                medicion = null;
                altura = null;
                diametro = null;
                longitud = null;
                distancia = null;
                azimut = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_DeformacionViento", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_DeformacionViento", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //13
    public void importarSueloErosionHidricaCanalillo(String ruta) {
        this.querySelect = "SELECT ErosionCanalilloID, SitioID, Medicion, Profundidad, Ancho, Distancia, Azimut FROM SUELO_ErosionHidricaCanalillo";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        Integer erosionCanalilloID = null;
        Integer sitioID = null;
        Integer medicion = null;
        Float profundidad = null;
        Float ancho = null;
        Float distancia = null;
        Integer azimut = null;
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                if (rs.getObject("ErosionCanalilloID") != null) {
                    erosionCanalilloID = rs.getInt("ErosionCanalilloID");
                }
                if (rs.getObject("SitioID") != null) {
                    sitioID = rs.getInt("SitioID");
                }
                if (rs.getObject("Medicion") != null) {
                    medicion = rs.getInt("Medicion");
                }
                if (rs.getObject("Profundidad") != null) {
                    profundidad = rs.getFloat("Profundidad");
                }
                if (rs.getObject("Ancho") != null) {
                    ancho = rs.getFloat("Ancho");
                }
                if (rs.getObject("Distancia") != null) {
                    distancia = rs.getFloat("Distancia");
                }
                if (rs.getObject("Azimut") != null) {
                    azimut = rs.getInt("Azimut");
                }
                ps.executeUpdate("INSERT INTO SUELO_ErosionHidricaCanalillo(ErosionCanalilloID, SitioID, Medicion, Profundidad, Ancho, Distancia, Azimut)"
                        + "VALUES(" + erosionCanalilloID + ", " + sitioID + ", " + medicion + ", " + profundidad + ", " + ancho + ", " + distancia + ", " + azimut + ")");
                this.baseDatosLocal.commit();
                erosionCanalilloID = null;
                sitioID = null;
                medicion = null;
                profundidad = null;
                ancho = null;
                distancia = null;
                azimut = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_ErosionHidricaCanalillo", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_ErosionHidricaCanalillo", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //14
    public void importarSueloErosionHidricaCarcava(String ruta) {
        this.querySelect = "SELECT ErosionCarcavaID,SitioID, Medicion, Profundidad, Ancho, Distancia, Azimut FROM SUELO_ErosionHidricaCarcava";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        Integer erosionCarcavaID = null;
        Integer sitioID = null;
        Integer medicion = null;
        Float profundidad = null;
        Float ancho = null;
        Float distancia = null;
        Integer azimut = null;
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                if (rs.getObject("ErosionCarcavaID") != null) {
                    erosionCarcavaID = rs.getInt("ErosionCarcavaID");
                }
                if (rs.getObject("SitioID") != null) {
                    sitioID = rs.getInt("SitioID");
                }
                if (rs.getObject("Medicion") != null) {
                    medicion = rs.getInt("Medicion");
                }
                if (rs.getObject("Profundidad") != null) {
                    profundidad = rs.getFloat("Profundidad");
                }
                if (rs.getObject("Ancho") != null) {
                    ancho = rs.getFloat("Ancho");
                }
                if (rs.getObject("Distancia") != null) {
                    distancia = rs.getFloat("Distancia");
                }
                if (rs.getObject("Azimut") != null) {
                    azimut = rs.getInt("Azimut");
                }
                ps.executeUpdate("INSERT INTO SUELO_ErosionHidricaCarcava(ErosionCarcavaID,SitioID, Medicion, Profundidad, Ancho, Distancia, Azimut)"
                        + "VALUES(" + erosionCarcavaID + ", " + sitioID + ", " + medicion + ", " + profundidad + ", " + ancho + ", " + distancia + ", " + azimut + ")");
                this.baseDatosLocal.commit();
                erosionCarcavaID = null;
                sitioID = null;
                medicion = null;
                profundidad = null;
                ancho = null;
                distancia = null;
                azimut = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_ErosionHidricaCarcava", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_ErosionHidricaCarcava", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //15
    public void importarSueloErosionLaminar(String ruta) {
        this.querySelect = "SELECT ErosionLaminarID, SitioID, Numero, Ancho, Largo FROM SUELO_ErosionLaminar";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        Integer erosionLaminarID = null;
        Integer sitioID = null;
        Integer numero = null;
        Float ancho = null;
        Float largo = null;
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                if (rs.getObject("ErosionLaminarID") != null) {
                    erosionLaminarID = rs.getInt("ErosionLaminarID");
                }
                if (rs.getObject("SitioID") != null) {
                    sitioID = rs.getInt("SitioID");
                }
                if (rs.getObject("Numero") != null) {
                    numero = rs.getInt("Numero");
                }
                if (rs.getObject("Ancho") != null) {
                    ancho = rs.getFloat("Ancho");
                }
                if (rs.getObject("Largo") != null) {
                    largo = rs.getFloat("Largo");
                }
                ps.executeUpdate("INSERT INTO SUELO_ErosionLaminar(ErosionLaminarID, SitioID, Numero, Ancho, Largo)VALUES(" + erosionLaminarID + ", " + sitioID + ", " + numero + ", " + ancho + ", " + largo + ")");
                this.baseDatosLocal.commit();
                erosionLaminarID = null;
                erosionLaminarID = null;
                sitioID = null;
                numero = null;
                ancho = null;
                largo = null;
                sitioID = null;
                numero = null;
                ancho = null;
                largo = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_ErosionLaminar", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_ErosionLaminar", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //16
    public void importarSueloEvidenciaErosion(String ruta) {
        this.querySelect = "SELECT EvidenciaErosionID, CoberturaSueloID, Punto, Dosel, LecturaTierraID FROM SUELO_EvidenciaErosion";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        Integer evidenciaErosionID = null;
        Integer coberturaSueloID = null;
        Integer punto = null;
        Integer dosel = null;
        Integer lecturaTierraID = null;
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                if (rs.getObject("EvidenciaErosionID") != null) {
                    evidenciaErosionID = rs.getInt("EvidenciaErosionID");
                }
                if (rs.getObject("CoberturaSueloID") != null) {
                    coberturaSueloID = rs.getInt("CoberturaSueloID");
                }
                if (rs.getObject("Punto") != null) {
                    punto = rs.getInt("Punto");
                }
                if (rs.getObject("Dosel") != null) {
                    dosel = rs.getInt("Dosel");
                }
                if (rs.getObject("LecturaTierraID") != null) {
                    lecturaTierraID = rs.getInt("LecturaTierraID");
                }
                ps.executeUpdate("INSERT INTO SUELO_EvidenciaErosion(EvidenciaErosionID, CoberturaSueloID, Punto, Dosel, LecturaTierraID)VALUES(" + evidenciaErosionID + ", " + coberturaSueloID + ", " + punto + ", " + dosel + ", " + lecturaTierraID + ")");
                this.baseDatosLocal.commit();
                evidenciaErosionID = null;
                coberturaSueloID = null;
                punto = null;
                dosel = null;
                lecturaTierraID = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_EvidenciaErosion", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_EvidenciaErosion", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //17
    public void importarSueloHojarasca(String ruta) {
        this.querySelect = "SELECT HojarascaID, SitioID, Punto, TipoHojarascaID, EspesorHO, EspesorF, PesoTotalHO, PesoTotalF, PesoMuestraHO, PesoMuestraF,ClaveHO, ClaveF, "
                + "Observaciones FROM SUELO_Hojarasca";
        Float espesorF = null;
        Float pesoTotalF = null;
        Float pesoMuestraF = null;
        Integer hojarascaID = null;
        Integer sitioID = null;
        Integer punto = null;
        Integer tipoHojarascaID = null;
        Float espesorHO = null;
        Float pesoTotalHO = null;
        Float pesoMuestraHO = null;
        String claveHO = null;
        String claveF = null;
        String observaciones = null;
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                if (rs.getObject("HojarascaID") != null) {
                    hojarascaID = rs.getInt("HojarascaID");
                }
                if (rs.getObject("SitioID") != null) {
                    sitioID = rs.getInt("SitioID");
                }
                if (rs.getObject("Punto") != null) {
                    punto = rs.getInt("Punto");
                }
                if (rs.getObject("TipoHojarascaID") != null) {
                    tipoHojarascaID = rs.getInt("TipoHojarascaID");
                }
                if (rs.getObject("EspesorHO") != null) {
                    espesorHO = rs.getFloat("EspesorHO");
                }
                if (rs.getObject("EspesorF") != null) {
                    espesorF = rs.getFloat("EspesorF");
                }

                if (rs.getObject("PesoTotalF") != null) {
                    pesoTotalF = rs.getFloat("PesoTotalF");
                }

                if (rs.getObject("PesoMuestraF") != null) {
                    pesoMuestraF = rs.getFloat("PesoMuestraF");
                }
                if (rs.getObject("PesoTotalHO") != null) {
                    pesoTotalHO = rs.getFloat("PesoTotalHO");
                }
                if (rs.getObject("PesoMuestraHO") != null) {
                    pesoMuestraHO = rs.getFloat("PesoMuestraHO");
                }
                if (rs.getObject("ClaveHO") != null) {
                    claveHO = rs.getString("ClaveHO");
                }
                if (rs.getObject("ClaveF") != null) {
                    claveF = rs.getString("ClaveF");
                }
                if (rs.getObject("Observaciones") != null) {
                    observaciones = rs.getString("Observaciones");
                }
                ps.executeUpdate("INSERT INTO SUELO_Hojarasca(HojarascaID, SitioID, Punto, TipoHojarascaID, EspesorHO, EspesorF, PesoTotalHO, PesoTotalF, PesoMuestraHO, PesoMuestraF,ClaveHO,ClaveF "
                        + ",Observaciones)VALUES(" + hojarascaID + ", " + sitioID + ", " + punto + ", " + tipoHojarascaID + ", " + espesorHO + ", " + espesorF + ", " + pesoTotalHO + ", " + pesoTotalF + ", " + pesoMuestraHO + ", " + pesoMuestraF + ",'" + claveHO + "', '" + claveF + "', '" + observaciones + "')");
                this.baseDatosLocal.commit();
                espesorF = null;
                pesoTotalF = null;
                pesoMuestraF = null;
                hojarascaID = null;
                sitioID = null;
                punto = null;
                tipoHojarascaID = null;
                espesorHO = null;
                pesoTotalHO = null;
                pesoMuestraHO = null;
                claveHO = null;
                claveF = null;
                observaciones = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_Hojarasca"
                    + e.getClass().getName() + " : " + e.getMessage(), "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_Hojarasca", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //18
    public void importarSueloLongitudCanalillo(String ruta) {
        this.querySelect = "SELECT LongitudCanalilloID, SitioID, CampoLongitud, Longitud FROM SUELO_LongitudCanalillo";
        this.queryInsert = "INSERT INTO SUELO_LongitudCanalillo(LongitudCanalilloID, SitioID, CampoLongitud, Longitud)VALUES(?,?, ?, ?)";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        Integer longitudCanalilloID = null;
        Integer sitioID = null;
        Integer campoLongitud = null;
        Float longitud = null;
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                if (rs.getObject("LongitudCanalilloID") != null) {
                    longitudCanalilloID = rs.getInt("LongitudCanalilloID");
                }
                if (rs.getObject("SitioID") != null) {
                    sitioID = rs.getInt("SitioID");
                }
                if (rs.getObject("CampoLongitud") != null) {
                    campoLongitud = rs.getInt("CampoLongitud");
                }
                if (rs.getObject("Longitud") != null) {
                    longitud = rs.getFloat("Longitud");
                }
                ps.executeUpdate("INSERT INTO SUELO_LongitudCanalillo(LongitudCanalilloID, SitioID, CampoLongitud, Longitud)"
                        + "VALUES(" + longitudCanalilloID + ", " + sitioID + ", " + campoLongitud + ", " + longitud + ")");
                this.baseDatosLocal.commit();
                longitudCanalilloID = null;
                sitioID = null;
                campoLongitud = null;
                longitud = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_LongitudCanalillo", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_LongitudCanalillo", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //19
    public void importarSueloLongitudCarcava(String ruta) {
        this.querySelect = "SELECT LongitudCarcavaID, SitioID, CampoLongitud, Longitud FROM SUELO_LongitudCarcava";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        Integer longitudCarcavaID = null;
        Integer sitioID = null;
        Integer campoLongitud = null;
        Float longitud = null;
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                if (rs.getObject("LongitudCarcavaID") != null) {
                    longitudCarcavaID = rs.getInt("LongitudCarcavaID");
                }
                if (rs.getObject("SitioID") != null) {
                    sitioID = rs.getInt("SitioID");
                }
                if (rs.getObject("CampoLongitud") != null) {
                    campoLongitud = rs.getInt("CampoLongitud");
                }
                if (rs.getObject("Longitud") != null) {
                    longitud = rs.getFloat("Longitud");
                }
                ps.executeUpdate("INSERT INTO SUELO_LongitudCarcava(LongitudCarcavaID, SitioID, CampoLongitud, Longitud)VALUES(" + longitudCarcavaID + ", " + sitioID + ", " + campoLongitud + ", " + longitud + ")");
                this.baseDatosLocal.commit();
                longitudCarcavaID = null;
                sitioID = null;
                campoLongitud = null;
                longitud = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_LongitudCarcava", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_LongitudCarcava", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //20
    public void importarSueloLongitudMonticulo(String ruta) {
        this.querySelect = "SELECT LongitudMonticuloID, SitioID, CampoLongitud, Longitud FROM SUELO_LongitudMonticulo";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        Integer longitudMonticuloID = null;
        Integer sitioID = null;
        Integer campoLongitud = null;
        Float longitud = null;
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                if (rs.getObject("LongitudMonticuloID") != null) {
                    longitudMonticuloID = rs.getInt("LongitudMonticuloID");
                }

                if (rs.getObject("SitioID") != null) {
                    sitioID = rs.getInt("SitioID");
                }

                if (rs.getObject("CampoLongitud") != null) {
                    campoLongitud = rs.getInt("CampoLongitud");
                }

                if (rs.getObject("Longitud") != null) {
                    longitud = rs.getFloat("Longitud");
                }
                ps.executeUpdate("INSERT INTO SUELO_LongitudMonticulo(LongitudMonticuloID, SitioID, CampoLongitud, Longitud)VALUES(" + longitudMonticuloID + ", " + sitioID + ", " + campoLongitud + ", " + longitud + ")");
                this.baseDatosLocal.commit();
                longitudMonticuloID = null;
                sitioID = null;
                campoLongitud = null;
                longitud = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_LongitudMonticulo", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_LongitudMonticulo", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //21
    public void importarSueloMedicionCanalillos(String ruta) {
        this.querySelect = "SELECT MedicionCanalillosID, SitioID, NumeroCanalillos, ProfundidadPromedio, Longitud, Volumen FROM SUELO_MedicionCanalillos";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        Integer medicionCanalillosID = null;
        Integer sitioID = null;
        Integer numeroCanalillos = null;
        Float profundidadPromedio = null;
        Float longitud = null;
        Float volumen = null;
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                if (rs.getObject("MedicionCanalillosID") != null) {
                    medicionCanalillosID = rs.getInt("MedicionCanalillosID");
                }
                if (rs.getObject("SitioID") != null) {
                    sitioID = rs.getInt("SitioID");
                }
                if (rs.getObject("NumeroCanalillos") != null) {
                    numeroCanalillos = rs.getInt("NumeroCanalillos");
                }
                if (rs.getObject("ProfundidadPromedio") != null) {
                    profundidadPromedio = rs.getFloat("ProfundidadPromedio");
                }
                if (rs.getObject("Longitud") != null) {
                    longitud = rs.getFloat("Longitud");
                }
                if (rs.getObject("Volumen") != null) {
                    volumen = rs.getFloat("Volumen");
                }

                ps.executeUpdate("INSERT INTO SUELO_MedicionCanalillos(MedicionCanalillosID, SitioID, NumeroCanalillos, ProfundidadPromedio, Longitud, Volumen)"
                        + "VALUES(" + medicionCanalillosID + "," + sitioID + ", " + numeroCanalillos + ", " + profundidadPromedio + ", " + longitud + ", " + volumen + ")");
                this.baseDatosLocal.commit();
                medicionCanalillosID = null;
                sitioID = null;
                numeroCanalillos = null;
                profundidadPromedio = null;
                longitud = null;
                volumen = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_MedicionCanalillos", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SSUELO_MedicionCanalillos", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //22
    public void importarSueloMedicionCarcavas(String ruta) {
        this.querySelect = "SELECT MedicionCarcavasID, SitioID, NumeroCarcavas, ProfundidadPromedio, AnchoPromedio, Longitud, Volumen FROM SUELO_MedicionCarcavas";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        Integer medicionCarcavasID = null;
        Integer sitioID = null;
        Integer numeroCarcavas = null;
        Float profundidadPromedio = null;
        Float anchoPromedio = null;
        Float longitud = null;
        Float volumen = null;

        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                if (rs.getObject("MedicionCarcavasID") != null) {
                    medicionCarcavasID = rs.getInt("MedicionCarcavasID");
                }
                if (rs.getObject("SitioID") != null) {
                    sitioID = rs.getInt("SitioID");
                }
                if (rs.getObject("NumeroCarcavas") != null) {
                    numeroCarcavas = rs.getInt("NumeroCarcavas");
                }
                if (rs.getObject("ProfundidadPromedio") != null) {
                    profundidadPromedio = rs.getFloat("ProfundidadPromedio");
                }
                if (rs.getObject("AnchoPromedio") != null) {
                    anchoPromedio = rs.getFloat("AnchoPromedio");
                }
                if (rs.getObject("Longitud") != null) {
                    longitud = rs.getFloat("Longitud");
                }
                if (rs.getObject("Volumen") != null) {
                    volumen = rs.getFloat("Volumen");
                }
                ps.executeUpdate("INSERT INTO SUELO_MedicionCanalillos(MedicionCarcavasID, SitioID, NumeroCarcavas, ProfundidadPromedio, AnchoPromedio, Longitud, Volumen)"
                        + "VALUES(" + medicionCarcavasID + ", " + sitioID + ", " + numeroCarcavas + ", " + profundidadPromedio + ", " + anchoPromedio + ", " + longitud + ", " + volumen + ")");
                this.baseDatosLocal.commit();
                medicionCarcavasID = null;
                sitioID = null;
                numeroCarcavas = null;
                profundidadPromedio = null;
                anchoPromedio = null;
                longitud = null;
                volumen = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_MedicionCarcavas ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SSUELO_MedicionCarcavas ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //23
    public void importarSueloMedicionDunas(String ruta) {
        this.querySelect = "SELECT MedicionDunas, SitioID, NumeroDunas, AlturaPromedio, AnchoPromedio, Longitud, Volumen FROM SUELO_MedicionDunas";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        Integer medicionDunas = null;
        Integer sitioID = null;
        Integer numeroDunas = null;
        Float alturaPromedio = null;
        Float anchoPromedio = null;
        Float longitud = null;
        Float volumen = null;
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                if (rs.getObject("MedicionDunas") != null) {
                    medicionDunas = rs.getInt("MedicionDunas");
                }
                if (rs.getObject("SitioID") != null) {
                    sitioID = rs.getInt("SitioID");
                }
                if (rs.getObject("NumeroDunas") != null) {
                    numeroDunas = rs.getInt("NumeroDunas");
                }
                if (rs.getObject("AlturaPromedio") != null) {
                    alturaPromedio = rs.getFloat("AlturaPromedio");
                }
                if (rs.getObject("AnchoPromedio") != null) {
                    anchoPromedio = rs.getFloat("AnchoPromedio");
                }
                if (rs.getObject("Longitud") != null) {
                    longitud = rs.getFloat("Longitud");
                }
                if (rs.getObject("Volumen") != null) {
                    volumen = rs.getFloat("Volumen");
                }
                ps.executeUpdate("INSERT INTO SUELO_MedicionCanalillos(MedicionDunas, SitioID, NumeroCarcavas, ProfundidadPromedio, AnchoPromedio, Longitud, Volumen)"
                        + "VALUES(" + medicionDunas + ", " + sitioID + ", " + numeroDunas + ", " + alturaPromedio + ", " + anchoPromedio + ", " + longitud + ", " + volumen + ")");
                this.baseDatosLocal.commit();
                medicionDunas = null;
                sitioID = null;
                numeroDunas = null;
                alturaPromedio = null;
                anchoPromedio = null;
                longitud = null;
                volumen = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_MedicionCarcavas ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SSUELO_MedicionCarcavas ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //24
    public void importarSueloMuestras(String ruta) {
        this.querySelect = "SELECT MuestrasID, SitioID, ProfundidadID,Muestras, PesoMuestra, Lectura1, Lectura2, Lectura3, Lectura4, "
                + "Promedio, ClaveColecta FROM SUELO_Muestras";
        Float lectura1 = null;
        Float lectura2 = null;
        Float lectura3 = null;
        Float lectura4 = null;
        Integer muestrasID = null;
        Integer sitioID = null;
        Integer profundidadID = null;
        Integer muestras = null;
        Float pesoMuestra = null;
        Float promedio = null;
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                if (rs.getObject("MuestrasID") != null) {
                    muestrasID = rs.getInt("MuestrasID");
                }
                if (rs.getObject("SitioID") != null) {
                    sitioID = rs.getInt("SitioID");
                }
                if (rs.getObject("ProfundidadID") != null) {
                    profundidadID = rs.getInt("ProfundidadID");
                }
                if (rs.getObject("Muestras") != null) {
                    muestras = rs.getInt("Muestras");
                }
                if (rs.getObject("PesoMuestra") != null) {
                    pesoMuestra = rs.getFloat("PesoMuestra");
                }
                if (rs.getObject("Promedio") != null) {
                    promedio = rs.getFloat("Promedio");
                }
                if (rs.getObject("Lectura1") != null) {
                    lectura1 = rs.getFloat("Lectura1");
                }
                if (rs.getObject("Lectura2") != null) {
                    lectura2 = rs.getFloat("Lectura2");
                }
                if (rs.getObject("Lectura3") != null) {
                    lectura3 = rs.getFloat("Lectura3");
                }
                if (rs.getObject("Lectura4") != null) {
                    lectura4 = rs.getFloat("Lectura4");
                }

                String claveColecta = rs.getString("ClaveColecta");
                ps.executeUpdate("INSERT INTO SUELO_Muestras(MuestrasID, SitioID, ProfundidadID, Muestras, PesoMuestra, Lectura1, Lectura2, Lectura3, Lectura4, "
                        + "Promedio, ClaveColecta)VALUES(" + muestrasID + ", " + sitioID + ", " + profundidadID + ", " + muestras + ", " + pesoMuestra + ", " + lectura1
                        + ", " + lectura2 + ", " + lectura3 + ", " + lectura4 + ", " + promedio + ", '" + claveColecta + "')");
                this.baseDatosLocal.commit();
                lectura1 = null;
                lectura2 = null;
                lectura3 = null;
                lectura4 = null;
                muestrasID = null;
                sitioID = null;
                profundidadID = null;
                muestras = null;
                pesoMuestra = null;
                promedio = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_Muestras ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_Muestras ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //25
    public void importarSueloMuestrasPerfil(String ruta) {
        this.querySelect = "SELECT MuestrasPerfilID, SitioID, GradosLatitud, MinutosLatitud, SegundosLatitud, GradosLongitud, MinutosLongitud, SegundosLongitud, Elevacion, "
                + "DiametroInterno, DiametroExterno, Altura, Observaciones FROM SUELO_MuestrasPerfil";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        Integer muestrasPerfilID = null;
        Integer sitioID = null;
        Integer gradosLatitud = null;
        Integer minutosLatitud = null;
        Float segundosLatitud = null;
        Integer gradosLongitud = null;
        Integer minutosLongitud = null;
        Float segundosLongitud = null;
        Float elevacion = null;
        Float diametroInterno = null;
        Float diametroExterno = null;
        Float altura = null;
        String observaciones = null;
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                if (rs.getObject("MuestrasPerfilID") != null) {
                    muestrasPerfilID = rs.getInt("MuestrasPerfilID");
                }
                if (rs.getObject("SitioID") != null) {
                    sitioID = rs.getInt("SitioID");
                }
                if (rs.getObject("GradosLatitud") != null) {
                    gradosLatitud = rs.getInt("GradosLatitud");
                }
                if (rs.getObject("MinutosLatitud") != null) {
                    minutosLatitud = rs.getInt("MinutosLatitud");
                }
                if (rs.getObject("SegundosLatitud") != null) {
                    segundosLatitud = rs.getFloat("SegundosLatitud");
                }
                if (rs.getObject("GradosLongitud") != null) {
                    gradosLongitud = rs.getInt("GradosLongitud");
                }
                if (rs.getObject("MinutosLongitud") != null) {
                    minutosLongitud = rs.getInt("MinutosLongitud");
                }
                if (rs.getObject("SegundosLongitud") != null) {
                    segundosLongitud = rs.getFloat("SegundosLongitud");
                }
                if (rs.getObject("Elevacion") != null) {
                    elevacion = rs.getFloat("Elevacion");
                }
                if (rs.getObject("DiametroInterno") != null) {
                    diametroInterno = rs.getFloat("DiametroInterno");
                }
                if (rs.getObject("DiametroExterno") != null) {
                    diametroExterno = rs.getFloat("DiametroExterno");
                }
                if (rs.getObject("Altura") != null) {
                    altura = rs.getFloat("Altura");
                }
                if (rs.getObject("Observaciones") != null) {
                    observaciones = rs.getString("Observaciones");
                }
                ps.executeUpdate("INSERT INTO SUELO_MuestrasPerfil(MuestrasPerfilID, SitioID, GradosLatitud, MinutosLatitud, SegundosLatitud, GradosLongitud, MinutosLongitud, SegundosLongitud, Elevacion, "
                        + "DiametroInterno, DiametroExterno, Altura, Observaciones)VALUES(" + muestrasPerfilID + ", " + sitioID + ", " + gradosLatitud + ", " + minutosLatitud + ", " + segundosLatitud + ", " + gradosLongitud
                        + ", " + minutosLongitud + ", " + segundosLongitud + ", " + elevacion + ", " + diametroInterno + ", " + diametroExterno + ", " + altura + ", '" + observaciones + "')");
                this.baseDatosLocal.commit();
                muestrasPerfilID = null;
                sitioID = null;
                gradosLatitud = null;
                minutosLatitud = null;
                segundosLatitud = null;
                gradosLongitud = null;
                minutosLongitud = null;
                segundosLongitud = null;
                elevacion = null;
                diametroInterno = null;
                diametroExterno = null;
                altura = null;
                observaciones = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_MuestrasPerfil ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_MuestrasPerfil ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //26
    public void importarSueloPavimentos(String ruta) {
        this.querySelect = "SELECT PavimentoErosionID, SitioID, Numero, Diametro FROM SUELO_PavimentoErosion";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        Integer pavimentoErosionID = null;
        Integer sitioID = null;
        Integer numero = null;
        Float diametro = null;
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                if (rs.getObject("PavimentoErosionID") != null) {
                    pavimentoErosionID = rs.getInt("PavimentoErosionID");
                }
                if (rs.getObject("SitioID") != null) {
                    sitioID = rs.getInt("SitioID");
                }
                if (rs.getObject("Numero") != null) {
                    numero = rs.getInt("Numero");
                }
                if (rs.getObject("Diametro") != null) {
                    diametro = rs.getFloat("Diametro");
                }

                ps.executeUpdate("INSERT INTO SUELO_PavimentoErosion(PavimentoErosionID, SitioID, Numero, Diametro)VALUES(" + pavimentoErosionID + ", " + sitioID + ", " + numero + ", " + diametro + ")");
                this.baseDatosLocal.commit();
                pavimentoErosionID = null;
                sitioID = null;
                numero = null;
                diametro = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_PavimentosErosión", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_PavimentosErosión", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //27
    public void importarSueloPedestal(String ruta) {
        this.querySelect = "SELECT PedestalID, SitioID, Numero, Altura FROM SUELO_Pedestal";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        Integer pedestalID = null;
        Integer sitioID = null;
        Integer numero = null;
        Float altura = null;
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                if (rs.getObject("PedestalID") != null) {
                    pedestalID = rs.getInt("PedestalID");
                }
                if (rs.getObject("SitioID") != null) {
                    sitioID = rs.getInt("SitioID");
                }
                if (rs.getObject("Numero") != null) {
                    numero = rs.getInt("Numero");
                }
                if (rs.getObject("Altura") != null) {
                    altura = rs.getFloat("Altura");
                }
                ps.executeUpdate("INSERT INTO SUELO_Pedestal(PedestalID, SitioID, Numero, Altura)VALUES(" + pedestalID + ", " + sitioID + ", " + numero + ", " + altura + ")");
                this.baseDatosLocal.commit();
                pedestalID = null;
                sitioID = null;
                numero = null;
                altura = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_Pedestal", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_Pedestal", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //28
    public void importarSueloProfundidad(String ruta) {
        this.querySelect = "SELECT ProfundidadSueloID, SitioID, Punto, Profundidad030, Profundidad3060, PesoTotal030, PesoTotal3060, Equipo030, Equipo3060, Clave030 ,Clave3060,"
                + "Observaciones FROM SUELO_Profundidad";
        Float profundidad3060 = null;
        Float pesoTotal3060 = null;
        Integer profundidadSueloID = null;
        Integer sitioID = null;
        Integer punto = null;
        Float profundidad030 = null;
        Float pesoTotal030 = null;
        Float equipo030 = null;
        Float equipo3060 = null;
        String clave030 = null;
        String clave3060 = null;
        String observaciones = null;
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {

                if (rs.getObject("Profundidad3060") != null) {
                    profundidad3060 = rs.getFloat("Profundidad3060");
                }

                if (rs.getObject("PesoTotal3060") != null) {
                    pesoTotal3060 = rs.getFloat("PesoTotal3060");
                }
                if (rs.getObject("ProfundidadSueloID") != null) {
                    profundidadSueloID = rs.getInt("ProfundidadSueloID");
                }
                if (rs.getObject("SitioID") != null) {
                    sitioID = rs.getInt("SitioID");
                }
                if (rs.getObject("Punto") != null) {
                    punto = rs.getInt("Punto");
                }
                if (rs.getObject("Profundidad030") != null) {
                    profundidad030 = rs.getFloat("Profundidad030");
                }
                if (rs.getObject("PesoTotal030") != null) {
                    pesoTotal030 = rs.getFloat("PesoTotal030");
                }
                if (rs.getObject("Equipo030") != null) {
                    equipo030 = rs.getFloat("Equipo030");
                }
                if (rs.getObject("Equipo3060") != null) {
                    equipo3060 = rs.getFloat("Equipo3060");
                }
                if (rs.getObject("Clave030") != null) {
                    clave030 = rs.getString("Clave030");
                }
                if (rs.getObject("Clave3060") != null) {
                    clave3060 = rs.getString("Clave3060");
                }
                if (rs.getObject("Observaciones") != null) {
                    observaciones = rs.getString("Observaciones");
                }
                ps.executeUpdate("INSERT INTO SUELO_Profundidad(ProfundidadSueloID, SitioID, Punto, Profundidad030, Profundidad3060, PesoTotal030, PesoTotal3060, Equipo030, Equipo3060,Clave030,Clave3060, "
                        + "Observaciones)VALUES(" + profundidadSueloID + ", " + sitioID + ", " + punto + ", " + profundidad030 + ", " + profundidad3060 + ", " + pesoTotal030 + ", " + pesoTotal3060 + ", '" + equipo030 + "', '" + equipo3060 + "', '" + clave030 + "', '" + clave3060 + "', '" + observaciones + "')");
                this.baseDatosLocal.commit();
                profundidad3060 = null;
                pesoTotal3060 = null;
                profundidadSueloID = null;
                sitioID = null;
                punto = null;
                profundidad030 = null;
                pesoTotal030 = null;
                equipo030 = null;
                equipo3060 = null;
                clave030 = null;
                clave3060 = null;
                observaciones = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_Profundidad ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_Profundidad ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//29
    public void importarSueloInformacion(String ruta) {
        this.querySelect = "SELECT SueloID, SitioID, UsoSueloID, OtroUsoSuelo, Espesor, PendienteDominante, Observaciones, NumeroCanalillos, ProfundidadPromedioCanalillos, "
                + "AnchoPromedioCanalillos, LongitudCanalillos, VolumenCanalillos, NumeroCarcavas, ProfundidadPromedioCarcavas, AnchoPromedioCarcavas, LongitudCarcavas, VolumenCarcavas, "
                + "NumeroMonticulos, AlturaPromedioMonticulos, AnchoPromedioMonticulos, LongitudPromedioMonticulos, VolumenMonticulos FROM SUELO_Suelo";
        Integer numeroCanalillos = null;
        Float profundidadPromedioCanalillos = null;
        Float anchoPromedioCanalillos = null;
        Float longitudCanalillos = null;
        Float volumenCanalillos = null;
        Integer numeroCarcavas = null;
        Float profundidadPromedioCarcavas = null;
        Float anchoPromedioCarcavas = null;
        Float longitudPromedioCarcavas = null;
        Float volumenCarcavas = null;
        Integer numeroMonticulos = null;
        Float alturaPromedioMonticulos = null;
        Float anchoPromedioMoticulos = null;
        Float longitudPromedioMonticulos = null;
        Float volumenMonticulos = null;
        Integer sueloID = null;
        Integer sitioID = null;
        Integer usoSueloID = null;
        String otroUsoSuelo = null;
        Float espesor = null;
        Integer pendienteDominante = null;
        String observaciones = null;
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                if (rs.getObject("SueloID") != null) {
                    sueloID = rs.getInt("SueloID");
                }
                if (rs.getObject("SitioID") != null) {
                    sitioID = rs.getInt("SitioID");
                }
                if (rs.getObject("UsoSueloID") != null) {
                    usoSueloID = rs.getInt("UsoSueloID");
                }
                if (rs.getObject("OtroUsoSuelo") != null) {
                    otroUsoSuelo = rs.getString("OtroUsoSuelo");
                }
                if (rs.getObject("Espesor") != null) {
                    espesor = rs.getFloat("Espesor");
                }
                if (rs.getObject("PendienteDominante") != null) {
                    pendienteDominante = rs.getInt("PendienteDominante");
                }
                if (rs.getObject("Observaciones") != null) {
                    observaciones = rs.getString("Observaciones");
                }
                if (rs.getObject("NumeroCanalillos") != null) {
                    numeroCanalillos = rs.getInt("NumeroCanalillos");
                }
                if (rs.getObject("ProfundidadPromedioCanalillos") != null) {
                    profundidadPromedioCanalillos = rs.getFloat("ProfundidadPromedioCanalillos");
                }
                if (rs.getObject("AnchoPromedioCanalillos") != null) {
                    anchoPromedioCanalillos = rs.getFloat("AnchoPromedioCanalillos");
                }
                if (rs.getObject("LongitudCanalillos") != null) {
                    longitudCanalillos = rs.getFloat("LongitudCanalillos");
                }
                if (rs.getObject("VolumenCanalillos") != null) {
                    volumenCanalillos = rs.getFloat("VolumenCanalillos");
                }
                if (rs.getObject("NumeroCarcavas") != null) {
                    numeroCarcavas = rs.getInt("NumeroCarcavas");
                }
                if (rs.getObject("ProfundidadPromedioCarcavas") != null) {
                    profundidadPromedioCarcavas = rs.getFloat("ProfundidadPromedioCarcavas");
                }
                if (rs.getObject("AnchoPromedioCarcavas") != null) {
                    anchoPromedioCarcavas = rs.getFloat("AnchoPromedioCarcavas");
                }
                if (rs.getObject("LongitudCarcavas") != null) {
                    longitudPromedioCarcavas = rs.getFloat("LongitudCarcavas");
                }
                if (rs.getObject("VolumenCarcavas") != null) {
                    volumenCarcavas = rs.getFloat("VolumenCarcavas");
                }
                if (rs.getObject("NumeroMonticulos") != null) {
                    numeroMonticulos = rs.getInt("NumeroMonticulos");
                }
                if (rs.getObject("AlturaPromedioMonticulos") != null) {
                    alturaPromedioMonticulos = rs.getFloat("AlturaPromedioMonticulos");
                }
                if (rs.getObject("AnchoPromedioMonticulos") != null) {
                    anchoPromedioMoticulos = rs.getFloat("AnchoPromedioMonticulos");
                }
                if (rs.getObject("LongitudPromedioMonticulos") != null) {
                    longitudPromedioMonticulos = rs.getFloat("LongitudPromedioMonticulos");
                }
                if (rs.getObject("VolumenMonticulos") != null) {
                    volumenMonticulos = rs.getFloat("VolumenMonticulos");
                }
                ps.executeUpdate("INSERT INTO SUELO_Suelo(SueloID, SitioID, UsoSueloID, OtroUsoSuelo, Espesor, PendienteDominante, Observaciones, NumeroCanalillos, ProfundidadPromedioCanalillos, "
                        + "AnchoPromedioCanalillos, LongitudCanalillos, VolumenCanalillos, NumeroCarcavas, ProfundidadPromedioCarcavas, AnchoPromedioCarcavas, LongitudCarcavas, VolumenCarcavas, "
                        + "NumeroMonticulos, AlturaPromedioMonticulos, AnchoPromedioMonticulos, LongitudPromedioMonticulos, VolumenMonticulos)"
                        + "VALUES(" + sueloID + ", " + sitioID + ", " + usoSueloID + ", '" + otroUsoSuelo + "', " + espesor + ", " + pendienteDominante + ", '" + observaciones + "', " + numeroCanalillos
                        + ", " + profundidadPromedioCanalillos + ", " + anchoPromedioCanalillos + ", " + longitudCanalillos + ", " + volumenCanalillos + ", " + numeroCarcavas + ", " + profundidadPromedioCarcavas
                        + ", " + anchoPromedioCarcavas + ", " + longitudPromedioCarcavas + ", " + volumenCarcavas + ", " + numeroMonticulos + ", " + alturaPromedioMonticulos + ", " + anchoPromedioMoticulos
                        + ", " + longitudPromedioMonticulos + ", " + volumenMonticulos + ")");
                this.baseDatosLocal.commit();
                numeroCanalillos = null;
                profundidadPromedioCanalillos = null;
                anchoPromedioCanalillos = null;
                longitudCanalillos = null;
                volumenCanalillos = null;
                numeroCarcavas = null;
                profundidadPromedioCarcavas = null;
                anchoPromedioCarcavas = null;
                longitudPromedioCarcavas = null;
                volumenCarcavas = null;
                numeroMonticulos = null;
                alturaPromedioMonticulos = null;
                anchoPromedioMoticulos = null;
                longitudPromedioMonticulos = null;
                volumenMonticulos = null;
                sueloID = null;
                sitioID = null;
                usoSueloID = null;
                otroUsoSuelo = null;
                espesor = null;
                pendienteDominante = null;
                observaciones = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_Suelo", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_Suelo", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//30
    public void importarSueloVarillasErosion(String ruta) {
        this.querySelect = "SELECT VarillaID, SitioID, NoVarilla, Azimut, Distancia, Profundidad FROM SUELO_VarillaErosion";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        Integer azimut = null;
        Float distancia = null;
        Integer varillaID = null;
        Integer sitioID = null;
        Integer noVarilla = null;
        Float profundidad = null;
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {

                if (rs.getObject("Azimut") != null) {
                    azimut = rs.getInt("Azimut");
                }
                if (rs.getObject("Distancia") != null) {
                    distancia = rs.getFloat("Distancia");
                }
                if (rs.getObject("VarillaID") != null) {
                    varillaID = rs.getInt("VarillaID");
                }
                if (rs.getObject("SitioID") != null) {
                    sitioID = rs.getInt("SitioID");
                }
                if (rs.getObject("NoVarilla") != null) {
                    noVarilla = rs.getInt("NoVarilla");
                }
                if (rs.getObject("Profundidad") != null) {
                    profundidad = rs.getFloat("Profundidad");
                }

                ps.executeUpdate("INSERT INTO SUELO_VarillaErosion(VarillaID, SitioID, NoVarilla, Azimut, Distancia, Profundidad)"
                        + "VALUES(" + varillaID + ", " + sitioID + ", " + noVarilla + ", " + azimut + ", " + distancia + ", " + profundidad + ")");
                this.baseDatosLocal.commit();
                azimut = null;
                distancia = null;
                varillaID = null;
                sitioID = null;
                noVarilla = null;
                profundidad = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_VarillasErosion", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_VarillasErosion", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//31
    public void importarCarbonoCoberturaDosel(String ruta) {
        this.querySelect = "SELECT CoberturaDoselID, SitioID, Transecto, Punto1, Punto2, Punto3, Punto4, Punto5, Punto6, Punto7, "
                + "Punto8, Punto9, Punto10 FROM CARBONO_CoberturaDosel";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        Integer coberturaDoselID = null;
        Integer sitioID = null;
        Integer transecto = null;
        Integer punto1 = null;
        Integer punto2 = null;
        Integer punto3 = null;
        Integer punto4 = null;
        Integer punto5 = null;
        Integer punto6 = null;
        Integer punto7 = null;
        Integer punto8 = null;
        Integer punto9 = null;
        Integer punto10 = null;

        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                if (rs.getObject("CoberturaDoselID") != null) {
                    coberturaDoselID = rs.getInt("CoberturaDoselID");
                }
                if (rs.getObject("SitioID") != null) {
                    sitioID = rs.getInt("SitioID");
                }
                if (rs.getObject("Transecto") != null) {
                    transecto = rs.getInt("Transecto");
                }
                if (rs.getObject("Punto1") != null) {
                    punto1 = rs.getInt("Punto1");
                }
                if (rs.getObject("Punto2") != null) {
                    punto2 = rs.getInt("Punto2");
                }
                if (rs.getObject("Punto3") != null) {
                    punto3 = rs.getInt("Punto3");
                }
                if (rs.getObject("Punto4") != null) {
                    punto4 = rs.getInt("Punto4");
                }
                if (rs.getObject("Punto5") != null) {
                    punto5 = rs.getInt("Punto5");
                }
                if (rs.getObject("Punto6") != null) {
                    punto6 = rs.getInt("Punto6");
                }
                if (rs.getObject("Punto7") != null) {
                    punto7 = rs.getInt("Punto7");
                }
                if (rs.getObject("Punto8") != null) {
                    punto8 = rs.getInt("Punto8");
                }
                if (rs.getObject("Punto9") != null) {
                    punto9 = rs.getInt("Punto9");
                }
                if (rs.getObject("Punto10") != null) {
                    punto10 = rs.getInt("Punto10");
                }
                ps.executeUpdate("INSERT INTO CARBONO_CoberturaDosel(CoberturaDoselID, SitioID, Transecto, Punto1, Punto2, Punto3, Punto4, Punto5, Punto6, Punto7, "
                        + "Punto8, Punto9, Punto10 )VALUES(" + coberturaDoselID + ", " + sitioID + ", " + transecto + ", " + punto1 + ", " + punto2 + ", " + punto3 + ", " + punto4
                        + ", " + punto5 + ", " + punto6 + ", " + punto7 + ", " + punto8 + ", " + punto9 + ", " + punto10 + ")");
                this.baseDatosLocal.commit();
                coberturaDoselID = null;
                sitioID = null;
                transecto = null;
                punto1 = null;
                punto2 = null;
                punto3 = null;
                punto4 = null;
                punto5 = null;
                punto6 = null;
                punto7 = null;
                punto8 = null;
                punto9 = null;
                punto10 = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla CARBONO_CoberturaDosel", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla CARBONO_CoberturaDosel", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //32
    public void importarCarbonoCubiertaVegetal(String ruta) {
        this.querySelect = "SELECT CubiertaVegetalID, SitioID, Transecto, ComponenteID, Altura5, Altura10 FROM CARBONO_CubiertaVegetal";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        Integer cubiertaVegetalID = null;
        Integer sitioID = null;
        Integer transecto = null;
        Integer componenteID = null;
        Float altura5 = null;
        Float altura10 = null;
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                if (rs.getObject("CubiertaVegetalID") != null) {
                    cubiertaVegetalID = rs.getInt("CubiertaVegetalID");
                }
                if (rs.getObject("SitioID") != null) {
                    sitioID = rs.getInt("SitioID");
                }
                if (rs.getObject("Transecto") != null) {
                    transecto = rs.getInt("Transecto");
                }
                if (rs.getObject("ComponenteID") != null) {
                    componenteID = rs.getInt("ComponenteID");
                }
                if (rs.getObject("Altura5") != null) {
                    altura5 = rs.getFloat("Altura5");
                }
                if (rs.getObject("Altura10") != null) {
                    altura10 = rs.getFloat("Altura10");
                }
                ps.executeUpdate("INSERT INTO CARBONO_CubiertaVegetal(CubiertaVegetalID, SitioID, Transecto, ComponenteID, Altura5, Altura10)"
                        + "VALUES(" + cubiertaVegetalID + ", " + sitioID + ", " + transecto + ", " + componenteID + ", " + altura5 + ", " + altura10 + ")");
                this.baseDatosLocal.commit();
                cubiertaVegetalID = null;
                sitioID = null;
                transecto = null;
                componenteID = null;
                altura5 = null;
                altura10 = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla CARBONO_CubiertaVegetal", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla CARBONO_CubiertaVegetal", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//33
    public void importarCarbonoLongitudComponente(String ruta) {
        this.querySelect = "SELECT LongitudComponenteID, SitioID, Consecutivo, Transecto, ComponenteID, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, Segmento1, Segmento2, Segmento3, Segmento4, "
                + "Segmento5, Segmento6, Segmento7, Segmento8, Segmento9, Segmento10, Total, ClaveColecta FROM CARBONO_LongitudComponente";
        Integer segmento1 = null;
        Integer segmento2 = null;
        Integer segmento3 = null;
        Integer segmento4 = null;
        Integer segmento5 = null;
        Integer segmento6 = null;
        Integer segmento7 = null;
        Integer segmento8 = null;
        Integer segmento9 = null;
        Integer segmento10 = null;
        Integer familiaID = null;
        Integer generoID = null;
        Integer especieID = null;
        Integer infraespecieID = null;
        Integer longitudComponenteID = null;
        Integer sitioID = null;
        Integer consecutivo = null;
        Integer transecto = null;
        Integer componenteID = null;
        String nombreComun = null;
        Integer total = null;
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                if (rs.getObject("LongitudComponenteID") != null) {
                    longitudComponenteID = rs.getInt("LongitudComponenteID");
                }
                if (rs.getObject("SitioID") != null) {
                    sitioID = rs.getInt("SitioID");
                }
                if (rs.getObject("Consecutivo") != null) {
                    consecutivo = rs.getInt("Consecutivo");
                }
                if (rs.getObject("Transecto") != null) {
                    transecto = rs.getInt("Transecto");
                }
                if (rs.getObject("ComponenteID") != null) {
                    componenteID = rs.getInt("ComponenteID");
                }
                if (rs.getObject("NombreComun") != null) {
                    nombreComun = rs.getString("NombreComun");
                }
                if (rs.getObject("Total") != null) {
                    total = rs.getInt("Total");
                }
                if (rs.getObject("FamiliaID") != null) {
                    familiaID = rs.getInt("FamiliaID");
                }
                if (rs.getObject("GeneroID") != null) {
                    generoID = generoID = rs.getInt("GeneroID");
                }
                if (rs.getObject("EspecieID") != null) {
                    especieID = especieID = rs.getInt("EspecieID");
                }
                if (rs.getObject("InfraespecieID") != null) {
                    infraespecieID = infraespecieID = rs.getInt("InfraespecieID");
                }

                if (rs.getObject("Segmento1") != null) {
                    segmento1 = rs.getInt("Segmento1");
                }
                if (rs.getObject("Segmento2") != null) {
                    segmento2 = rs.getInt("Segmento2");
                }
                if (rs.getObject("Segmento3") != null) {
                    segmento3 = rs.getInt("Segmento3");
                }
                if (rs.getObject("Segmento4") != null) {
                    segmento4 = rs.getInt("Segmento4");
                }
                if (rs.getObject("Segmento5") != null) {
                    segmento5 = rs.getInt("Segmento5");
                }
                if (rs.getObject("Segmento6") != null) {
                    segmento6 = rs.getInt("Segmento6");
                }
                if (rs.getObject("Segmento7") != null) {
                    segmento7 = rs.getInt("Segmento7");
                }
                if (rs.getObject("Segmento8") != null) {
                    segmento8 = rs.getInt("Segmento8");
                }
                if (rs.getObject("Segmento9") != null) {
                    segmento9 = rs.getInt("Segmento9");
                }
                if (rs.getObject("Segmento10") != null) {
                    segmento10 = rs.getInt("Segmento10");
                }

                String claveColecta = rs.getString("ClaveColecta");
                ps.executeUpdate("INSERT INTO CARBONO_LongitudComponente(LongitudComponenteID, SitioID, Consecutivo, Transecto, ComponenteID, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, Segmento1, Segmento2, Segmento3, Segmento4, "
                        + "Segmento5, Segmento6, Segmento7, Segmento8, Segmento9, Segmento10, Total, ClaveColecta)VALUES(" + longitudComponenteID + ", " + sitioID + ", " + consecutivo + ", " + transecto + ", " + componenteID + ", " + familiaID + ", " + generoID
                        + ", " + especieID + ", " + infraespecieID + ", '" + nombreComun + "', " + segmento1 + ", " + segmento2 + ", " + segmento3 + ", " + segmento4 + ", " + segmento5 + ", " + segmento6 + ", " + segmento7 + ", " + segmento8
                        + ", " + segmento9 + ", " + segmento10 + ", " + total + ", '" + claveColecta + "')");
                this.baseDatosLocal.commit();
                segmento1 = null;
                segmento2 = null;
                segmento3 = null;
                segmento4 = null;
                segmento5 = null;
                segmento6 = null;
                segmento7 = null;
                segmento8 = null;
                segmento9 = null;
                segmento10 = null;
                familiaID = null;
                generoID = null;
                especieID = null;
                infraespecieID = null;
                longitudComponenteID = null;
                sitioID = null;
                consecutivo = null;
                transecto = null;
                componenteID = null;
                nombreComun = null;
                total = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla CARBONO_LongitudComponente", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla CARBONO_LongitudComponente"
                        + e.getClass().getName() + " : " + e.getMessage(), "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //34
    public void importarCarbonoMaterialLenioso100(String ruta) {
        this.querySelect = "SELECT MaterialLenioso100ID, SitioID, Transecto, Pendiente, UnaHora, DiezHoras, CienHoras FROM CARBONO_MaterialLenioso100";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        Integer materialLenioso100ID = null;
        Integer sitioID = null;
        Integer transecto = null;
        Integer pendiente = null;
        Integer unaHora = null;
        Integer diezhoras = null;
        Integer cienHoras = null;
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                if (rs.getObject("MaterialLenioso100ID") != null) {
                    materialLenioso100ID = rs.getInt("MaterialLenioso100ID");
                }
                if (rs.getObject("SitioID") != null) {
                    sitioID = rs.getInt("SitioID");
                }
                if (rs.getObject("Transecto") != null) {
                    transecto = rs.getInt("Transecto");
                }
                if (rs.getObject("Pendiente") != null) {
                    pendiente = rs.getInt("Pendiente");
                }
                if (rs.getObject("UnaHora") != null) {
                    unaHora = rs.getInt("UnaHora");
                }
                if (rs.getObject("DiezHoras") != null) {
                    diezhoras = rs.getInt("DiezHoras");
                }
                if (rs.getObject("CienHoras") != null) {
                    cienHoras = rs.getInt("CienHoras");
                }
                ps.executeUpdate("INSERT INTO CARBONO_MaterialLenioso100(MaterialLenioso100ID, SitioID, Transecto, Pendiente, UnaHora, DiezHoras, CienHoras)VALUES"
                        + "(" + materialLenioso100ID + ", " + sitioID + ", " + transecto + ", " + pendiente + ", " + unaHora + ", " + diezhoras + ", " + cienHoras + ")");
                this.baseDatosLocal.commit();
                materialLenioso100ID = null;
                sitioID = null;
                transecto = null;
                pendiente = null;
                unaHora = null;
                diezhoras = null;
                cienHoras = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla CARBONO_MaterialLenioso100", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla CARBONO_MaterialLenioso100", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//35
    public void importarCarbonoMaterialLenioso1000(String ruta) {
        this.querySelect = "SELECT MaterialLenioso1000ID, SitioID, Transecto, Diametro, Grado FROM CARBONO_MaterialLenioso1000";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        Integer materialLenioso1000ID = null;
        Integer sitioID = null;
        Integer transecto = null;
        Float diametro = null;
        Integer grados = null;
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                if (rs.getObject("MaterialLenioso1000ID") != null) {
                    materialLenioso1000ID = rs.getInt("MaterialLenioso1000ID");
                }
                if (rs.getObject("SitioID") != null) {
                    sitioID = rs.getInt("SitioID");
                }
                if (rs.getObject("Transecto") != null) {
                    transecto = rs.getInt("Transecto");
                }
                if (rs.getObject("Diametro") != null) {
                    diametro = rs.getFloat("Diametro");
                }
                if (rs.getObject("Grado") != null) {
                    grados = rs.getInt("Grado");
                }
                ps.executeUpdate("INSERT INTO CARBONO_MaterialLenioso1000(MaterialLenioso1000ID, SitioID, Transecto, Diametro, Grado)VALUES(" + materialLenioso1000ID + ", " + sitioID + ", " + transecto + ", " + diametro + ", " + grados + ")");
                this.baseDatosLocal.commit();
                materialLenioso1000ID = null;
                sitioID = null;
                transecto = null;
                diametro = null;
                grados = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla CARBONO_MaterialLenioso1000", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla CARBONO_MaterialLenioso1000", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//36
    public void importarTaxonomiaArbolado(String ruta) {
        this.querySelect = "SELECT SitioID ,ArboladoID ,Consecutivo ,NoIndividuo ,NoRama ,Azimut ,Distancia ,FamiliaID ,GeneroID ,EspecieID ,InfraespecieID ,NombreComun ,EsColecta ,EsSubmuestra ,FormaVidaID ,FormaFusteID ,CondicionID ,MuertoPieID ,GradoPutrefaccionID ,TipoToconID ,DiametroNormal ,DiametroBasal ,AlturaTotal ,AnguloInclinacion ,AlturaFusteLimpio ,AlturaComercial ,DiametroCopaNS ,DiametroCopaEO ,ProporcionCopaVivaID ,ExposicionCopaID ,PosicionCopaID ,DensidadCopaID ,MuerteRegresivaID ,TransparenciaFollajeID ,VigorID ,NivelVigorID ,ClaveColecta FROM TAXONOMIA_Arbolado ";
        Float diametroNormal = null;
        Integer diametroBasal = null;
        Float alturaTotal = null;
        Float anguloInclinacion = null;
        Float alturaFusteLimpio = null;
        Float alturaComercial = null;
        Float diametroCopaNS = null;
        Float diametroCopaEO = null;
        Integer familiaID = null;
        Integer generoID = null;
        Integer especieID = null;
        Integer infraespecieID = null;
        Integer muertoPieID = null;
        Integer gradoPutrefaccionID = null;
        Integer tipoToconID = null;
        Integer proporcionCopaVivaID = null;
        Integer exposicionCopaID = null;
        Integer posicionCopaID = null;
        Integer densidadCopaID = null;
        Integer muerteRegresivaID = null;
        Integer transparenciaFollajeID = null;
        Integer vigorID = null;
        Integer nivelVigor = null;
        String claveColecta = null;
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer arboladoID = rs.getInt("ArboladoID");
                Integer sitioID = rs.getInt("SitioID");
                Integer consecutivo = rs.getInt("Consecutivo");
                Integer noIndividuo = rs.getInt("NoIndividuo");
                Integer noRama = rs.getInt("NoRama");
                Integer azimut = rs.getInt("Azimut");
                Float distancia = rs.getFloat("Distancia");
                String nombreComun = rs.getString("NombreComun");
                Integer esSubmuestra = rs.getInt("EsSubmuestra");
                Integer formaVidaID = rs.getInt("FormaVidaID");
                Integer formaFusteID = rs.getInt("FormaFusteID");
                Integer condicionID = rs.getInt("CondicionID");

                if (rs.getObject("DiametroNormal") != null) {
                    diametroNormal = rs.getFloat("DiametroNormal");
                }
                if (rs.getObject("DiametroBasal") != null) {
                    diametroBasal = rs.getInt("DiametroBasal");
                }
                if (rs.getObject("AlturaTotal") != null) {
                    alturaTotal = rs.getFloat("AlturaTotal");
                }
                if (rs.getObject("AnguloInclinacion") != null) {
                    anguloInclinacion = rs.getFloat("AnguloInclinacion");
                }
                if (rs.getObject("AlturaFusteLimpio") != null) {
                    alturaFusteLimpio = rs.getFloat("AlturaFusteLimpio");
                }
                if (rs.getObject("AlturaComercial") != null) {
                    alturaComercial = rs.getFloat("AlturaComercial");
                }
                if (rs.getObject("DiametroCopaNS") != null) {
                    diametroCopaNS = rs.getFloat("DiametroCopaNS");
                }
                if (rs.getObject("DiametroCopaEO") != null) {
                    diametroCopaEO = rs.getFloat("DiametroCopaEO");
                }
                if (rs.getObject("FamiliaID") != null) {
                    familiaID = rs.getInt("FamiliaID");
                }
                if (rs.getObject("GeneroID") != null) {
                    generoID = generoID = rs.getInt("GeneroID");
                }
                if (rs.getObject("EspecieID") != null) {
                    especieID = especieID = rs.getInt("EspecieID");
                }
                if (rs.getObject("InfraespecieID") != null) {
                    infraespecieID = infraespecieID = rs.getInt("InfraespecieID");
                }
                if (rs.getObject("MuertoPieID") != null) {
                    muertoPieID = rs.getInt("MuertoPieID");
                }
                if (rs.getObject("GradoPutrefaccionID") != null) {
                    gradoPutrefaccionID = rs.getInt("GradoPutrefaccionID");
                }
                if (rs.getObject("TipoToconID") != null) {
                    tipoToconID = rs.getInt("TipoToconID");
                }
                if (rs.getObject("ProporcionCopaVivaID") != null) {
                    proporcionCopaVivaID = rs.getInt("ProporcionCopaVivaID");
                }
                if (rs.getObject("ExposicionCopaID") != null) {
                    exposicionCopaID = rs.getInt("ExposicionCopaID");
                }
                if (rs.getObject("PosicionCopaID") != null) {
                    posicionCopaID = rs.getInt("PosicionCopaID");
                }
                if (rs.getObject("DensidadCopaID") != null) {
                    densidadCopaID = rs.getInt("DensidadCopaID");
                }
                if (rs.getObject("MuerteRegresivaID") != null) {
                    muerteRegresivaID = rs.getInt("MuerteRegresivaID");
                }
                if (rs.getObject("TransparenciaFollajeID") != null) {
                    transparenciaFollajeID = rs.getInt("TransparenciaFollajeID");
                }
                if (rs.getObject("VigorID") != null) {
                    vigorID = rs.getInt("VigorID");
                }
                if (rs.getObject("NivelVigorID") != null) {
                    nivelVigor = rs.getInt("NivelVigorID");
                }
                if (rs.getObject("ClaveColecta") != null) {
                    claveColecta = rs.getString("ClaveColecta");
                }
                ps.executeUpdate("INSERT INTO TAXONOMIA_Arbolado(ArboladoID, SitioID, Consecutivo, NoIndividuo, NoRama, Azimut, Distancia, FamiliaID, GeneroID, EspecieID, InfraespecieID, "
                        + "NombreComun, EsSubmuestra, FormaVidaID, FormaFusteID, CondicionID, MuertoPieID, GradoPutrefaccionID, TipoToconID, DiametroNormal, "
                        + "DiametroBasal, AlturaTotal, AnguloInclinacion, AlturaFusteLimpio, AlturaComercial, DiametroCopaNS, DiametroCopaEO, ProporcionCopaVivaID, ExposicionCopaID, "
                        + "PosicionCopaID, DensidadCopaID, MuerteRegresivaID, TransparenciaFollajeID, VigorID,NivelVigorID , ClaveColecta)VALUES(" + arboladoID + ", " + sitioID + ", " + consecutivo + ", " + noIndividuo
                        + ", " + noRama + ", " + azimut + ", " + distancia + ", " + familiaID + ", " + generoID + ", " + especieID + ", " + infraespecieID + ", '" + nombreComun + "'"
                        + ", " + esSubmuestra + ", " + formaVidaID + ", " + formaFusteID + ", " + condicionID + ", " + muertoPieID + ", " + gradoPutrefaccionID + ", " + tipoToconID
                        + ", " + diametroNormal + ", " + diametroBasal + ", " + alturaTotal + ", " + anguloInclinacion + ", " + alturaFusteLimpio + ", " + alturaComercial + ", " + diametroCopaNS
                        + ", " + diametroCopaEO + ", " + proporcionCopaVivaID + ", " + exposicionCopaID + ", " + posicionCopaID + ", " + densidadCopaID + ", " + muerteRegresivaID + ", " + transparenciaFollajeID
                        + ", " + vigorID + ", " + nivelVigor + ", '" + claveColecta + "')");

                this.baseDatosLocal.commit();
                diametroNormal = null;
                diametroBasal = null;
                alturaTotal = null;
                anguloInclinacion = null;
                alturaFusteLimpio = null;
                alturaComercial = null;
                diametroCopaNS = null;
                diametroCopaEO = null;
                familiaID = null;
                generoID = null;
                especieID = null;
                infraespecieID = null;
                muertoPieID = null;
                gradoPutrefaccionID = null;
                tipoToconID = null;
                proporcionCopaVivaID = null;
                exposicionCopaID = null;
                posicionCopaID = null;
                densidadCopaID = null;
                muerteRegresivaID = null;
                transparenciaFollajeID = null;
                vigorID = null;
                nivelVigor = null;
                claveColecta = null;
                diametroNormal = null;
                diametroBasal = null;
                alturaTotal = null;
                anguloInclinacion = null;
                alturaFusteLimpio = null;
                alturaComercial = null;
                diametroCopaNS = null;
                diametroCopaEO = null;
                familiaID = null;
                generoID = null;
                especieID = null;
                infraespecieID = null;
                muertoPieID = null;
                gradoPutrefaccionID = null;
                tipoToconID = null;
                proporcionCopaVivaID = null;
                exposicionCopaID = null;
                posicionCopaID = null;
                densidadCopaID = null;
                muerteRegresivaID = null;
                transparenciaFollajeID = null;
                vigorID = null;
                nivelVigor = null;
                claveColecta = null;
                diametroNormal = null;
                diametroBasal = null;
                alturaTotal = null;
                anguloInclinacion = null;
                alturaFusteLimpio = null;
                alturaComercial = null;
                diametroCopaNS = null;
                diametroCopaEO = null;
                familiaID = null;
                generoID = null;
                especieID = null;
                infraespecieID = null;
                muertoPieID = null;
                gradoPutrefaccionID = null;
                tipoToconID = null;
                proporcionCopaVivaID = null;
                exposicionCopaID = null;
                posicionCopaID = null;
                densidadCopaID = null;
                muerteRegresivaID = null;
                transparenciaFollajeID = null;
                vigorID = null;
                nivelVigor = null;
                claveColecta = null;
                diametroNormal = null;
                diametroBasal = null;
                alturaTotal = null;
                anguloInclinacion = null;
                alturaFusteLimpio = null;
                alturaComercial = null;
                diametroCopaNS = null;
                diametroCopaEO = null;
                familiaID = null;
                generoID = null;
                especieID = null;
                infraespecieID = null;
                muertoPieID = null;
                gradoPutrefaccionID = null;
                tipoToconID = null;
                proporcionCopaVivaID = null;
                exposicionCopaID = null;
                posicionCopaID = null;
                densidadCopaID = null;
                muerteRegresivaID = null;
                transparenciaFollajeID = null;
                vigorID = null;
                nivelVigor = null;
                claveColecta = null;
                diametroNormal = null;
                diametroBasal = null;
                alturaTotal = null;
                anguloInclinacion = null;
                alturaFusteLimpio = null;
                alturaComercial = null;
                diametroCopaNS = null;
                diametroCopaEO = null;
                familiaID = null;
                generoID = null;
                especieID = null;
                infraespecieID = null;
                muertoPieID = null;
                gradoPutrefaccionID = null;
                tipoToconID = null;
                proporcionCopaVivaID = null;
                exposicionCopaID = null;
                posicionCopaID = null;
                densidadCopaID = null;
                muerteRegresivaID = null;
                transparenciaFollajeID = null;
                vigorID = null;
                nivelVigor = null;
                claveColecta = null;
                diametroNormal = null;
                diametroBasal = null;
                alturaTotal = null;
                anguloInclinacion = null;
                alturaFusteLimpio = null;
                alturaComercial = null;
                diametroCopaNS = null;
                diametroCopaEO = null;
                familiaID = null;
                generoID = null;
                especieID = null;
                infraespecieID = null;
                muertoPieID = null;
                gradoPutrefaccionID = null;
                tipoToconID = null;
                proporcionCopaVivaID = null;
                exposicionCopaID = null;
                posicionCopaID = null;
                densidadCopaID = null;
                muerteRegresivaID = null;
                transparenciaFollajeID = null;
                vigorID = null;
                nivelVigor = null;
                claveColecta = null;
                diametroNormal = null;
                diametroBasal = null;
                alturaTotal = null;
                anguloInclinacion = null;
                alturaFusteLimpio = null;
                alturaComercial = null;
                diametroCopaNS = null;
                diametroCopaEO = null;
                familiaID = null;
                generoID = null;
                especieID = null;
                infraespecieID = null;
                muertoPieID = null;
                gradoPutrefaccionID = null;
                tipoToconID = null;
                proporcionCopaVivaID = null;
                exposicionCopaID = null;
                posicionCopaID = null;
                densidadCopaID = null;
                muerteRegresivaID = null;
                transparenciaFollajeID = null;
                vigorID = null;
                nivelVigor = null;
                claveColecta = null;
                diametroNormal = null;
                diametroBasal = null;
                alturaTotal = null;
                anguloInclinacion = null;
                alturaFusteLimpio = null;
                alturaComercial = null;
                diametroCopaNS = null;
                diametroCopaEO = null;
                familiaID = null;
                generoID = null;
                especieID = null;
                infraespecieID = null;
                muertoPieID = null;
                gradoPutrefaccionID = null;
                tipoToconID = null;
                proporcionCopaVivaID = null;
                exposicionCopaID = null;
                posicionCopaID = null;
                densidadCopaID = null;
                muerteRegresivaID = null;
                transparenciaFollajeID = null;
                vigorID = null;
                nivelVigor = null;
                claveColecta = null;
                diametroNormal = null;
                diametroBasal = null;
                alturaTotal = null;
                anguloInclinacion = null;
                alturaFusteLimpio = null;
                alturaComercial = null;
                diametroCopaNS = null;
                diametroCopaEO = null;
                familiaID = null;
                generoID = null;
                especieID = null;
                infraespecieID = null;
                muertoPieID = null;
                gradoPutrefaccionID = null;
                tipoToconID = null;
                proporcionCopaVivaID = null;
                exposicionCopaID = null;
                posicionCopaID = null;
                densidadCopaID = null;
                muerteRegresivaID = null;
                transparenciaFollajeID = null;
                vigorID = null;
                nivelVigor = null;
                claveColecta = null;
                diametroNormal = null;
                diametroBasal = null;
                alturaTotal = null;
                anguloInclinacion = null;
                alturaFusteLimpio = null;
                alturaComercial = null;
                diametroCopaNS = null;
                diametroCopaEO = null;
                familiaID = null;
                generoID = null;
                especieID = null;
                infraespecieID = null;
                muertoPieID = null;
                gradoPutrefaccionID = null;
                tipoToconID = null;
                proporcionCopaVivaID = null;
                exposicionCopaID = null;
                posicionCopaID = null;
                densidadCopaID = null;
                muerteRegresivaID = null;
                transparenciaFollajeID = null;
                vigorID = null;
                nivelVigor = null;
                claveColecta = null;
                diametroNormal = null;
                diametroBasal = null;
                alturaTotal = null;
                anguloInclinacion = null;
                alturaFusteLimpio = null;
                alturaComercial = null;
                diametroCopaNS = null;
                diametroCopaEO = null;
                familiaID = null;
                generoID = null;
                especieID = null;
                infraespecieID = null;
                muertoPieID = null;
                gradoPutrefaccionID = null;
                tipoToconID = null;
                proporcionCopaVivaID = null;
                exposicionCopaID = null;
                posicionCopaID = null;
                densidadCopaID = null;
                muerteRegresivaID = null;
                transparenciaFollajeID = null;
                vigorID = null;
                nivelVigor = null;
                claveColecta = null;
                diametroNormal = null;
                diametroBasal = null;
                alturaTotal = null;
                anguloInclinacion = null;
                alturaFusteLimpio = null;
                alturaComercial = null;
                diametroCopaNS = null;
                diametroCopaEO = null;
                familiaID = null;
                generoID = null;
                especieID = null;
                infraespecieID = null;
                muertoPieID = null;
                gradoPutrefaccionID = null;
                tipoToconID = null;
                proporcionCopaVivaID = null;
                exposicionCopaID = null;
                posicionCopaID = null;
                densidadCopaID = null;
                muerteRegresivaID = null;
                transparenciaFollajeID = null;
                vigorID = null;
                nivelVigor = null;
                claveColecta = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla TAXONOMIA_Arbolado", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla TAXONOMIA_Arbolado", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//37
    public void importarSubmuestra(String ruta) {
        this.querySelect = "SELECT SubmuestraID, SitioID, ArboladoID, DiametroBasal, Edad, NumeroAnillos25, LongitudAnillos10, GrozorCorteza FROM ARBOLADO_Submuestra";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        Integer submuestraID = null;
        Integer sitioID = null;
        Integer arboladoID = null;
        Float diametroBasal = null;
        Integer edad = null;
        Integer numeroAnillos25 = null;
        Float longitudAnillos10 = null;
        Float grozorCorteza = null;
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                if (rs.getObject("SubmuestraID") != null) {
                    submuestraID = rs.getInt("SubmuestraID");
                }
                if (rs.getObject("SitioID") != null) {
                    sitioID = rs.getInt("SitioID");
                }
                if (rs.getObject("ArboladoID") != null) {
                    arboladoID = rs.getInt("ArboladoID");
                }
                if (rs.getObject("DiametroBasal") != null) {
                    diametroBasal = rs.getFloat("DiametroBasal");
                }
                if (rs.getObject("Edad") != null) {
                    edad = rs.getInt("Edad");
                }
                if (rs.getObject("NumeroAnillos25") != null) {
                    numeroAnillos25 = rs.getInt("NumeroAnillos25");
                }
                if (rs.getObject("LongitudAnillos10") != null) {
                    longitudAnillos10 = rs.getFloat("LongitudAnillos10");
                }
                if (rs.getObject("GrozorCorteza") != null) {
                    grozorCorteza = rs.getFloat("GrozorCorteza");
                }
                ps.executeUpdate("INSERT INTO ARBOLADO_Submuestra(SubmuestraID, SitioID, ArboladoID, DiametroBasal, Edad, NumeroAnillos25, LongitudAnillos10, GrozorCorteza)VALUES(" + submuestraID + ","
                        + sitioID + ", " + arboladoID + ", " + diametroBasal + ", " + edad + ", " + numeroAnillos25 + ", " + longitudAnillos10 + ", " + grozorCorteza + ")");
                this.baseDatosLocal.commit();
                submuestraID = null;
                sitioID = null;
                arboladoID = null;
                diametroBasal = null;
                edad = null;
                numeroAnillos25 = null;
                longitudAnillos10 = null;
                grozorCorteza = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla ARBOLADO_Submuestra", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla ARBOLADO_Submuestra"
                        + e.getClass().getName() + " : " + e.getMessage(), "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//38
    public void importarSubmuestraTroza(String ruta) {
        this.querySelect = "SELECT TrozaID, SubmuestraID, NoTroza, TipoTrozaID FROM ARBOLADO_Troza";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        Integer trozaID = null;
        Integer submuestraID = null;
        Integer noTroza = null;
        Integer tipoTrozaID = null;
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                if (rs.getObject("TrozaID") != null) {
                    trozaID = rs.getInt("TrozaID");
                }
                if (rs.getObject("SubmuestraID") != null) {
                    submuestraID = rs.getInt("SubmuestraID");
                }
                if (rs.getObject("NoTroza") != null) {
                    noTroza = rs.getInt("NoTroza");
                }
                if (rs.getObject("TipoTrozaID") != null) {
                    tipoTrozaID = rs.getInt("TipoTrozaID");
                }

                ps.executeUpdate("INSERT INTO ARBOLADO_Troza(TrozaID, SubmuestraID, NoTroza, TipoTrozaID)VALUES(" + trozaID + ", " + submuestraID + ", " + noTroza + ", " + tipoTrozaID + ")");
                this.baseDatosLocal.commit();
                trozaID = null;
                submuestraID = null;
                noTroza = null;
                tipoTrozaID = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla ARBOLADO_Troza", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla ARBOLADO_Troza", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void importarSubmuestraObservaciones(String ruta) {
        this.querySelect = "SELECT SubmuestraObservacionesID, SitioID, Observaciones FROM SUBMUESTRA_Observaciones";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        Integer submuestraObservacionesID = null;
        Integer sitioID = null;
        String observaciones = null;
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                if (rs.getObject("SubmuestraObservacionesI") != null) {
                    submuestraObservacionesID = rs.getInt("SubmuestraObservacionesI");
                }
                if (rs.getObject("SitioID") != null) {
                    sitioID = rs.getInt("SitioID");
                }
                if (rs.getObject("Observaciones") != null) {
                    observaciones = rs.getString("Observaciones");
                }
                ps.executeUpdate("INSERT INTO SUBMUESTRA_Observaciones(SubmuestraObservacionesID, SitioID, Observaciones)VALUES(" + submuestraObservacionesID + ", " + sitioID + ", '" + observaciones + ")");
                this.baseDatosLocal.commit();
                submuestraObservacionesID = null;
                sitioID = null;
                observaciones = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUBMUESTRA_Observaciones", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUBMUESTRA_Observaciones", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//39
    public void importarArboladoDanioSeveridad(String ruta) {
        this.querySelect = "SELECT DanioSeveridadID, ArboladoID, NumeroDanio, AgenteDanioID, SeveridadID FROM ARBOLADO_DanioSeveridad";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        Integer danioSeveridadID = null;
        Integer arbolID = null;
        Integer noDanio = null;
        Integer agenteDanioID = null;
        Integer severidadID = null;
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                if (rs.getObject("DanioSeveridadID") != null) {
                    danioSeveridadID = rs.getInt("DanioSeveridadID");
                }
                if (rs.getObject("ArboladoID") != null) {
                    arbolID = rs.getInt("ArboladoID");
                }
                if (rs.getObject("NumeroDanio") != null) {
                    noDanio = rs.getInt("NumeroDanio");
                }
                if (rs.getObject("AgenteDanioID") != null) {
                    agenteDanioID = rs.getInt("AgenteDanioID");
                }
                if (rs.getObject("SeveridadID") == null) {
                    severidadID = null;
                } else {
                    severidadID = rs.getInt("SeveridadID");
                }
                ps.executeUpdate("INSERT INTO ARBOLADO_DanioSeveridad(DanioSeveridadID, ArboladoID, NumeroDanio, AgenteDanioID, SeveridadID)VALUES(" + danioSeveridadID + ", " + arbolID + ", "
                        + noDanio + ", " + agenteDanioID + ", " + severidadID + ")");
                this.baseDatosLocal.commit();
                severidadID = null;
                danioSeveridadID = null;
                arbolID = null;
                noDanio = null;
                agenteDanioID = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla ARBOLADO_DanioSeveridad", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla ARBOLADO_DanioSeveridad", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //40
    public void importarTaxonomiaColectaBotanica(String ruta) {
        this.querySelect = "SELECT ColectaBotanicaID, UPMID, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, Sitio, SeccionID, Consecutivo, ClaveColecta, ContraFuertes, Exudado, IndicarExudado, Color, IndicarColor, CambioColor, AceitesVolatiles, ColorFlor, IndicarColorFlor, HojasTejidoVivo, FotoFlor, FotoFruto, FotoHojasArriba, FotoHojasAbajo, FotoArbolCompleto, FotoCorteza, VirutaIncluida, "
                + "CortezaIncluida, MaderaIncluida, Observaciones FROM TAXONOMIA_ColectaBotanica";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        Integer colectaBotanicaID = null;
        Integer UPMID = null;
        Integer familiaID = null;
        Integer generoID = null;
        Integer especieID = null;
        String infraespecieID = null;
        String nombreComun = null;
        Integer sitio = null;
        Integer seccionID = null;
        Integer consecutivo = null;
        String claveColecta = null;
        Integer contraFuertes = null;
        Integer exudado = null;
        String indicarExudado = null;
        Integer color = null;
        String indicarColor = null;
        Integer cambioColor = null;
        Integer aceitesVolatiles = null;
        Integer colorFlor = null;
        String indicarColorFlor = null;
        Integer hojasTejidoVivo = null;
        Integer fotoFlor = null;
        Integer fotoFruto = null;
        Integer fotoHojasArriba = null;
        Integer fotoHojasAbajo = null;
        Integer fotoArbolCompleto = null;
        Integer fotoCorteza = null;
        Integer virutaIncluida = null;
        Integer cortezaIncluida = null;
        Integer maderaIncluida = null;
        String observaciones = null;

        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                if (rs.getObject("ColectaBotanicaID") != null) {
                    colectaBotanicaID = rs.getInt("ColectaBotanicaID");
                }
                if (rs.getObject("UPMID") != null) {
                    UPMID = rs.getInt("UPMID");
                }
                if (rs.getObject("FamiliaID") != null) {
                    familiaID = rs.getInt("FamiliaID");
                }
                if (rs.getObject("GeneroID") != null) {
                    generoID = generoID = rs.getInt("GeneroID");
                }
                if (rs.getObject("EspecieID") != null) {
                    especieID = especieID = rs.getInt("EspecieID");
                }
                if (rs.getObject("InfraespecieID") != null) {
                    infraespecieID = infraespecieID = rs.getString("InfraespecieID");
                }
                if (rs.getObject("NombreComun") != null) {
                    nombreComun = rs.getString("NombreComun");
                }
                if (rs.getObject("Sitio") != null) {
                    sitio = rs.getInt("Sitio");
                }
                if (rs.getObject("SeccionID") != null) {
                    seccionID = rs.getInt("SeccionID");
                }
                if (rs.getObject("Consecutivo") != null) {
                    consecutivo = rs.getInt("Consecutivo");
                }
                if (rs.getObject("ClaveColecta") != null) {
                    claveColecta = rs.getString("ClaveColecta");
                }
                if (rs.getObject("ContraFuertes") != null) {
                    contraFuertes = rs.getInt("ContraFuertes");
                }
                if (rs.getObject("Exudado") != null) {
                    exudado = rs.getInt("Exudado");
                }
                if (rs.getObject("IndicarExudado") != null) {
                    indicarExudado = rs.getString("IndicarExudado");
                }
                if (rs.getObject("Color") != null) {
                    color = rs.getInt("Color");
                }
                if (rs.getObject("IndicarColor") != null) {
                    indicarColor = rs.getString("IndicarColor");
                }
                if (rs.getObject("CambioColor") != null) {
                    cambioColor = rs.getInt("CambioColor");
                }
                if (rs.getObject("AceitesVolatiles") != null) {
                    aceitesVolatiles = rs.getInt("AceitesVolatiles");
                }
                if (rs.getObject("ColorFlor") != null) {
                    colorFlor = rs.getInt("ColorFlor");
                }
                if (rs.getObject("IndicarColorFlor") != null) {
                    indicarColorFlor = rs.getString("IndicarColorFlor");
                }
                if (rs.getObject("HojasTejidoVivo") != null) {
                    hojasTejidoVivo = rs.getInt("HojasTejidoVivo");
                }
                if (rs.getObject("FotoFlor") != null) {
                    fotoFlor = rs.getInt("FotoFlor");
                }
                if (rs.getObject("FotoFruto") != null) {
                    fotoFruto = rs.getInt("FotoFruto");
                }
                if (rs.getObject("FotoHojasArriba") != null) {
                    fotoHojasArriba = rs.getInt("FotoHojasArriba");
                }
                if (rs.getObject("FotoHojasAbajo") != null) {
                    fotoHojasAbajo = rs.getInt("FotoHojasAbajo");
                }
                if (rs.getObject("FotoArbolCompleto") != null) {
                    fotoArbolCompleto = rs.getInt("FotoArbolCompleto");
                }
                if (rs.getObject("FotoCorteza") != null) {
                    fotoCorteza = rs.getInt("FotoCorteza");
                }
                if (rs.getObject("VirutaIncluida") != null) {
                    virutaIncluida = rs.getInt("VirutaIncluida");
                }
                if (rs.getObject("CortezaIncluida") != null) {
                    cortezaIncluida = rs.getInt("CortezaIncluida");
                }
                if (rs.getObject("MaderaIncluida") != null) {
                    maderaIncluida = rs.getInt("MaderaIncluida");
                }
                if (rs.getObject("Observaciones") != null) {
                    observaciones = rs.getString("Observaciones");
                }
                ps.executeUpdate("INSERT INTO TAXONOMIA_ColectaBotanica(ColectaBotanicaID, UPMID, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, Sitio, SeccionID, Consecutivo, ClaveColecta, ContraFuertes, Exudado, IndicarExudado, Color, IndicarColor, CambioColor, AceitesVolatiles, ColorFlor, IndicarColorFlor, HojasTejidoVivo, FotoFlor, FotoFruto, FotoHojasArriba, FotoHojasAbajo, FotoArbolCompleto, FotoCorteza, "
                        + "VirutaIncluida, CortezaIncluida, MaderaIncluida, Observaciones)VALUES(" + colectaBotanicaID + ", " + UPMID + ", " + familiaID + ", " + generoID + ", " + especieID + ", " + infraespecieID + ", '" + nombreComun + "'," + sitio + ", " + seccionID + ", " + consecutivo + ", '" + claveColecta + "', " + contraFuertes + ", " + exudado + ", '" + indicarExudado + "', " + color + ", '" + indicarColor + "'"
                        + ", " + cambioColor + ", " + aceitesVolatiles + ", " + colorFlor + ", '" + indicarColorFlor + "', " + hojasTejidoVivo + ", " + fotoFlor + ", " + fotoFruto + ", " + fotoHojasArriba + ", " + fotoHojasAbajo + ", " + fotoArbolCompleto + ", " + fotoCorteza + ", " + virutaIncluida + ", " + cortezaIncluida + ", " + maderaIncluida
                        + ", '" + observaciones + "')");
                this.baseDatosLocal.commit();
                colectaBotanicaID = null;
                UPMID = null;
                familiaID = null;
                generoID = null;
                especieID = null;
                infraespecieID = null;
                nombreComun = null;
                sitio = null;
                seccionID = null;
                consecutivo = null;
                claveColecta = null;
                contraFuertes = null;
                exudado = null;
                indicarExudado = null;
                color = null;
                indicarColor = null;
                cambioColor = null;
                aceitesVolatiles = null;
                colorFlor = null;
                indicarColorFlor = null;
                hojasTejidoVivo = null;
                fotoFlor = null;
                fotoFruto = null;
                fotoHojasArriba = null;
                fotoHojasAbajo = null;
                fotoArbolCompleto = null;
                fotoCorteza = null;
                virutaIncluida = null;
                cortezaIncluida = null;
                maderaIncluida = null;
                observaciones = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla TAXONOMIA_ColectaBotanica", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla TAXONOMIA_ColectaBotanica", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//41
    public void importarTaxonomiaRepoblado(String ruta) {
        this.querySelect = "SELECT RepobladoID, SitioID, Consecutivo, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, Frecuencia025150, Edad025150, Frecuencia151275, Edad151275, "
                + "Frecuencia275, Edad275, VigorID, DanioID, PorcentajeDanio, ClaveColecta FROM TAXONOMIA_Repoblado";
        Integer frecuencia025150 = null;
        Integer edad025150 = null;
        Integer frecuencia151275 = null;
        Integer edad151275 = null;
        Integer frecuencia275 = null;
        Integer edad275 = null;
        Integer porcentajeDanio = null;
        Integer familiaID = null;
        Integer generoID = null;
        Integer especieID = null;
        Integer infraespecieID = null;
        String claveColecta = null;
        Integer repobladoID = null;
        Integer sitioID = null;
        Integer consecutivo = null;
        String nombreComun = null;
        Integer vigorID = null;
        Integer danioID = null;
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                if (rs.getObject("RepobladoID") != null) {
                    repobladoID = rs.getInt("RepobladoID");
                }
                if (rs.getObject("SitioID") != null) {
                    sitioID = rs.getInt("SitioID");
                }
                if (rs.getObject("Consecutivo") != null) {
                    consecutivo = rs.getInt("Consecutivo");
                }
                if (rs.getObject("NombreComun") != null) {
                    nombreComun = rs.getString("NombreComun");
                }
                if (rs.getObject("VigorID") != null) {
                    vigorID = rs.getInt("VigorID");
                }
                if (rs.getObject("DanioID") != null) {
                    danioID = rs.getInt("DanioID");
                }
                if (rs.getObject("FamiliaID") != null) {
                    familiaID = rs.getInt("FamiliaID");
                }

                if (rs.getObject("GeneroID") != null) {
                    generoID = rs.getInt("GeneroID");
                }

                if (rs.getObject("EspecieID") != null) {
                    especieID = rs.getInt("EspecieID");
                }

                if (rs.getObject("InfraespecieID") != null) {
                    infraespecieID = rs.getInt("InfraespecieID");
                }
                if (rs.getObject("Frecuencia025150") != null) {
                    frecuencia025150 = rs.getInt("Frecuencia025150");
                }
                if (rs.getObject("Edad025150") != null) {
                    edad025150 = rs.getInt("Edad025150");
                }
                if (rs.getObject("Frecuencia151275") != null) {
                    frecuencia151275 = rs.getInt("Frecuencia151275");
                }
                if (rs.getObject("Edad151275") != null) {
                    edad151275 = rs.getInt("Edad151275");
                }
                if (rs.getObject("Frecuencia275") != null) {
                    frecuencia275 = rs.getInt("Frecuencia275");
                }
                if (rs.getObject("Edad275") != null) {
                    edad275 = rs.getInt("Edad275");
                }
                if (rs.getObject("PorcentajeDanio") != null) {
                    porcentajeDanio = rs.getInt("PorcentajeDanio");
                }
                if (rs.getObject("ClaveColecta") != null) {
                    claveColecta = rs.getString("ClaveColecta");
                }
                ps.executeUpdate("INSERT INTO TAXONOMIA_Repoblado(RepobladoID, SitioID, Consecutivo, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, Frecuencia025150, Edad025150, Frecuencia151275, Edad151275, "
                        + "Frecuencia275, Edad275, VigorID, DanioID, PorcentajeDanio, ClaveColecta)VALUES(" + repobladoID + ", " + sitioID + ", " + consecutivo + ", " + familiaID + ", " + generoID + ", " + especieID + ", " + infraespecieID + ""
                        + ", '" + nombreComun + "', " + frecuencia025150 + ", " + edad025150 + ", " + frecuencia151275 + ", " + edad151275 + ", " + frecuencia275 + ", " + edad275 + ", " + vigorID + ", " + danioID
                        + ", " + porcentajeDanio + ", '" + claveColecta + "')");
                this.baseDatosLocal.commit();
                frecuencia025150 = null;
                edad025150 = null;
                frecuencia151275 = null;
                edad151275 = null;
                frecuencia275 = null;
                edad275 = null;
                porcentajeDanio = null;
                familiaID = null;
                generoID = null;
                especieID = null;
                infraespecieID = null;
                claveColecta = null;
                repobladoID = null;
                sitioID = null;
                consecutivo = null;
                nombreComun = null;
                vigorID = null;
                danioID = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla TAXONOMIA_Repoblado", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla TAXONOMIA_Repoblado", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//42
    public void importarTaxonomiaRepobladoVM(String ruta) {
        this.querySelect = "SELECT RepobladoVMID, SitioID, Consecutivo, FormaVidaID, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, Frecuencia50, PorcentajeCobertura50, Frecuencia51200, PorcentajeCobertura51200,"
                + "Frecuencia200, PorcentajeCobertura200, VigorID, ClaveColecta FROM TAXONOMIA_RepobladoVM";
        Integer frecuencia50 = null;
        Integer porcentajeCobertura50 = null;
        Integer frecuencia51200 = null;
        Integer porcentajeCobertura51200 = null;
        Integer frecuencia200 = null;
        Integer porcentajeCobertura200 = null;
        Integer familiaID = null;
        Integer generoID = null;
        Integer especieID = null;
        Integer infraespecieID = null;
        String claveColecta = null;
        Integer repobladoVMID = null;
        Integer sitioID = null;
        Integer consecutivo = null;
        Integer formaVidaID = null;
        String nombreComun = null;
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                if (rs.getObject("RepobladoVMID") != null) {
                    repobladoVMID = rs.getInt("RepobladoVMID");
                }
                if (rs.getObject("SitioID") != null) {
                    sitioID = rs.getInt("SitioID");
                }
                if (rs.getObject("Consecutivo") != null) {
                    consecutivo = rs.getInt("Consecutivo");
                }
                if (rs.getObject("FormaVidaID") != null) {
                    formaVidaID = rs.getInt("FormaVidaID");
                }
                if (rs.getObject("NombreComun") != null) {
                    nombreComun = rs.getString("NombreComun");
                }
                if (rs.getObject("FamiliaID") != null) {
                    familiaID = rs.getInt("FamiliaID");
                }

                if (rs.getObject("GeneroID") != null) {
                    generoID = rs.getInt("GeneroID");
                }

                if (rs.getObject("EspecieID") != null) {
                    especieID = rs.getInt("EspecieID");
                }

                if (rs.getObject("InfraespecieID") != null) {
                    infraespecieID = rs.getInt("InfraespecieID");
                }

                if (rs.getObject("Frecuencia50") != null) {
                    frecuencia50 = rs.getInt("Frecuencia50");
                }
                if (rs.getObject("PorcentajeCobertura50") != null) {
                    porcentajeCobertura50 = rs.getInt("PorcentajeCobertura50");
                }
                if (rs.getObject("Frecuencia51200") != null) {
                    frecuencia51200 = rs.getInt("Frecuencia51200");
                }
                if (rs.getObject("PorcentajeCobertura51200") != null) {
                    porcentajeCobertura51200 = rs.getInt("PorcentajeCobertura51200");
                }
                if (rs.getObject("Frecuencia200") != null) {
                    frecuencia200 = rs.getInt("Frecuencia200");
                }
                if (rs.getObject("PorcentajeCobertura200") != null) {
                    porcentajeCobertura200 = rs.getInt("PorcentajeCobertura200");
                }
                Integer vigorID = rs.getInt("VigorID");
                if (rs.getObject("ClaveColecta") != null) {
                    claveColecta = rs.getString("ClaveColecta");
                }
                ps.executeUpdate("INSERT INTO TAXONOMIA_RepobladoVM(RepobladoVMID, SitioID, Consecutivo, FormaVidaID, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, Frecuencia50, PorcentajeCobertura50, Frecuencia51200, PorcentajeCobertura51200, "
                        + "Frecuencia200, PorcentajeCobertura200, VigorID, ClaveColecta)VALUES(" + repobladoVMID + ", " + sitioID + ", " + consecutivo + ", " + formaVidaID + ", " + familiaID + ", " + generoID + ", " + especieID + ", " + infraespecieID + ", '" + nombreComun + "'"
                        + ", " + frecuencia50 + ", " + porcentajeCobertura50 + ", " + frecuencia51200 + ", " + porcentajeCobertura51200 + ", " + frecuencia200 + ", " + porcentajeCobertura200 + ", " + vigorID + ", '" + claveColecta + "')");
                this.baseDatosLocal.commit();
                frecuencia50 = null;
                porcentajeCobertura50 = null;
                frecuencia51200 = null;
                porcentajeCobertura51200 = null;
                frecuencia200 = null;
                porcentajeCobertura200 = null;
                familiaID = null;
                generoID = null;
                especieID = null;
                infraespecieID = null;
                claveColecta = null;
                repobladoVMID = null;
                sitioID = null;
                consecutivo = null;
                formaVidaID = null;
                nombreComun = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla TAXONOMIA_RepobladoVM", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla TAXONOMIA_RepobladoVM", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//43
    public void importarTaxonomiaSotoBosque(String ruta) {
        this.querySelect = "SELECT SotoBosqueID, SitioID, Consecutivo, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, Frecuencia025150, Cobertura025150, Frecuencia151275, Cobertura151275, Frecuencia275, Cobertura275, VigorID, DanioID, "
                + "PorcentajeDanio, ClaveColecta FROM TAXONOMIA_SotoBosque";
        Integer frecuencia025150 = null;
        Integer cobertura025150 = null;
        Integer frecuencia151275 = null;
        Integer cobertura151275 = null;
        Integer frecuencia275 = null;
        Integer cobertura275 = null;
        Integer porcentajeDanio = null;
        Integer familiaID = null;
        Integer generoID = null;
        Integer especieID = null;
        Integer infraespecieID = null;
        String claveColecta = null;
        Integer sotoBosqueID = null;
        Integer sitioID = null;
        Integer consecutivo = null;
        String nombreComun = null;
        Integer vigorID = null;
        Integer danioID = null;
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                if (rs.getObject("SotoBosqueID") != null) {
                    sotoBosqueID = rs.getInt("SotoBosqueID");
                }
                if (rs.getObject("SitioID") != null) {
                    sitioID = rs.getInt("SitioID");
                }
                if (rs.getObject("Consecutivo") != null) {
                    consecutivo = rs.getInt("Consecutivo");
                }
                if (rs.getObject("NombreComun") != null) {
                    nombreComun = rs.getString("NombreComun");
                }
                if (rs.getObject("VigorID") != null) {
                    vigorID = rs.getInt("VigorID");
                }
                if (rs.getObject("DanioID") != null) {
                    danioID = rs.getInt("DanioID");
                }

                if (rs.getObject("FamiliaID") != null) {
                    familiaID = rs.getInt("FamiliaID");
                }

                if (rs.getObject("GeneroID") != null) {
                    generoID = rs.getInt("GeneroID");
                }

                if (rs.getObject("EspecieID") != null) {
                    especieID = rs.getInt("EspecieID");
                }

                if (rs.getObject("InfraespecieID") != null) {
                    infraespecieID = rs.getInt("InfraespecieID");
                }

                if (rs.getObject("Frecuencia025150") != null) {
                    frecuencia025150 = rs.getInt("Frecuencia025150");
                }
                if (rs.getObject("Cobertura025150") != null) {
                    cobertura025150 = rs.getInt("Cobertura025150");
                }
                if (rs.getObject("Frecuencia151275") != null) {
                    frecuencia151275 = rs.getInt("Frecuencia151275");
                }
                if (rs.getObject("Cobertura151275") != null) {
                    cobertura151275 = rs.getInt("Cobertura151275");
                }
                if (rs.getObject("Frecuencia275") != null) {
                    frecuencia275 = rs.getInt("Frecuencia275");
                }
                if (rs.getObject("Cobertura275") != null) {
                    cobertura275 = rs.getInt("Cobertura275");
                }
                if (rs.getObject("PorcentajeDanio") != null) {
                    porcentajeDanio = rs.getInt("PorcentajeDanio");
                }
                if (rs.getObject("ClaveColecta") != null) {
                    claveColecta = rs.getString("ClaveColecta");
                }
                ps.executeUpdate("INSERT INTO TAXONOMIA_SotoBosque(SotoBosqueID, SitioID, Consecutivo, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, Frecuencia025150, Cobertura025150, Frecuencia151275, Cobertura151275, Frecuencia275, Cobertura275, VigorID, DanioID, "
                        + "PorcentajeDanio, ClaveColecta)VALUES(" + sotoBosqueID + ", " + sitioID + ", " + consecutivo + ", " + familiaID + ", " + generoID + ", " + especieID + ", " + infraespecieID + ", '" + nombreComun + "', " + frecuencia025150 + ", " + cobertura025150 + ", " + frecuencia151275
                        + ", " + cobertura151275 + ", " + frecuencia275 + ", " + cobertura275 + ", " + vigorID + ", " + danioID + ", " + porcentajeDanio + ", '" + claveColecta + "')");
                this.baseDatosLocal.commit();
                frecuencia025150 = null;
                cobertura025150 = null;
                frecuencia151275 = null;
                cobertura151275 = null;
                frecuencia275 = null;
                cobertura275 = null;
                porcentajeDanio = null;
                familiaID = null;
                generoID = null;
                especieID = null;
                infraespecieID = null;
                claveColecta = null;
                sotoBosqueID = null;
                sitioID = null;
                consecutivo = null;
                nombreComun = null;
                vigorID = null;
                danioID = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla TAXONOMIA_SotoBosque", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla TAXONOMIA_SotoBosque", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//44
    public void importarTaxonomiaVegetacionMayorGregarios(String ruta) {
        this.querySelect = "SELECT VegetacionMayorID, SitioID, Consecutivo, NoIndividuo, FormaVidaID, CondicionID, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, FormaCrecimientoID, DensidadColoniaID, AlturaTotalMaxima, AlturaTotalMedia, AlturaTotalMinima, DiametroCoberturaMayor, "
                + "DiametroCoberturaMenor, VigorID, ClaveColecta FROM TAXONOMIA_VegetacionMayorGregarios";
        Float alturaTotalMaxima = null;
        Float alturaTotalMedia = null;
        Float alturaTotalMinima = null;
        Float diametroCoberturaMayor = null;
        Float diametroCoberturaMenor = null;
        Integer familiaID = null;
        Integer generoID = null;
        Integer especieID = null;
        Integer infraespecieID = null;
        String claveColecta = null;
        Integer vegetacionMayorID = null;
        Integer sitioID = null;
        Integer consecutivo = null;
        Integer noIndividuo = null;
        Integer formaVidaID = null;
        Integer condicionID = null;
        String nombreComun = null;
        Integer formaCrecimientoID = null;
        Integer densidadColoniaID = null;
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                if (rs.getObject("VegetacionMayorID") != null) {
                    vegetacionMayorID = rs.getInt("VegetacionMayorID");
                }
                if (rs.getObject("SitioID") != null) {
                    sitioID = rs.getInt("SitioID");
                }
                if (rs.getObject("Consecutivo") != null) {
                    consecutivo = rs.getInt("Consecutivo");
                }
                if (rs.getObject("NoIndividuo") != null) {
                    noIndividuo = rs.getInt("NoIndividuo");
                }
                if (rs.getObject("FormaVidaID") != null) {
                    formaVidaID = rs.getInt("FormaVidaID");
                }
                if (rs.getObject("CondicionID") != null) {
                    condicionID = rs.getInt("CondicionID");
                }
                if (rs.getObject("NombreComun") != null) {
                    nombreComun = rs.getString("NombreComun");
                }
                if (rs.getObject("FormaCrecimientoID") != null) {
                    formaCrecimientoID = rs.getInt("FormaCrecimientoID");
                }
                if (rs.getObject("DensidadColoniaID") != null) {
                    densidadColoniaID = rs.getInt("DensidadColoniaID");
                }
                if (rs.getObject("FamiliaID") != null) {
                    familiaID = rs.getInt("FamiliaID");
                }

                if (rs.getObject("GeneroID") != null) {
                    generoID = rs.getInt("GeneroID");
                }

                if (rs.getObject("EspecieID") != null) {
                    especieID = rs.getInt("EspecieID");
                }

                if (rs.getObject("InfraespecieID") != null) {
                    infraespecieID = rs.getInt("InfraespecieID");
                }

                if (rs.getObject("AlturaTotalMaxima") != null) {
                    alturaTotalMaxima = rs.getFloat("AlturaTotalMaxima");
                }
                if (rs.getObject("AlturaTotalMedia") != null) {
                    alturaTotalMedia = rs.getFloat("AlturaTotalMedia");
                }
                if (rs.getObject("AlturaTotalMinima") != null) {
                    alturaTotalMinima = rs.getFloat("AlturaTotalMinima");
                }
                if (rs.getObject("DiametroCoberturaMayor") != null) {
                    diametroCoberturaMayor = rs.getFloat("DiametroCoberturaMayor");
                }
                if (rs.getObject("DiametroCoberturaMenor") != null) {
                    diametroCoberturaMenor = rs.getFloat("DiametroCoberturaMenor");
                }
                Integer vigorID = rs.getInt("VigorID");
                if (rs.getObject("ClaveColecta") != null) {
                    claveColecta = rs.getString("ClaveColecta");
                }
                ps.executeUpdate("INSERT INTO TAXONOMIA_VegetacionMayorGregarios(VegetacionMayorID, SitioID, Consecutivo, NoIndividuo, FormaVidaID, CondicionID, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, FormaCrecimientoID, DensidadColoniaID, AlturaTotalMaxima, AlturaTotalMedia, AlturaTotalMinima, DiametroCoberturaMayor, "
                        + "DiametroCoberturaMenor, VigorID, ClaveColecta)VALUES(" + vegetacionMayorID + ", " + sitioID + ", " + consecutivo + ", " + noIndividuo + ", " + formaVidaID + ", " + condicionID + ", " + familiaID + ", " + generoID + ", " + especieID + ", " + infraespecieID + ", '" + nombreComun + "', " + formaCrecimientoID + ", " + densidadColoniaID
                        + ", " + alturaTotalMaxima + ", " + alturaTotalMedia + ", " + alturaTotalMinima + ", " + diametroCoberturaMayor + ", " + diametroCoberturaMenor + ", " + vigorID + ", '" + claveColecta + "')");
                this.baseDatosLocal.commit();
                alturaTotalMaxima = null;
                alturaTotalMedia = null;
                alturaTotalMinima = null;
                diametroCoberturaMayor = null;
                diametroCoberturaMenor = null;
                familiaID = null;
                generoID = null;
                especieID = null;
                infraespecieID = null;
                claveColecta = null;
                vegetacionMayorID = null;
                sitioID = null;
                consecutivo = null;
                noIndividuo = null;
                formaVidaID = null;
                condicionID = null;
                nombreComun = null;
                formaCrecimientoID = null;
                densidadColoniaID = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla TAXONOMIA_VegetacionMayorGregarios", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla TAXONOMIA_VegetacionMayorGregarios", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//45
    public void importarVegetacionMayorGDanioSeveridad(String ruta) {
        this.querySelect = "SELECT DanioSeveridadID, VegetacionMayorID, NumeroDanio, AgenteDanioID, SeveridadID FROM VEGETACIONMAYORG_DanioSeveridad";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        Integer severidadID = null;
        Integer danioSeveridadID = null;
        Integer vegetacionMayorID = null;
        Integer numeroDanio = null;
        Integer agenteDanioID = null;
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                if (rs.getObject("DanioSeveridadID") != null) {
                    danioSeveridadID = rs.getInt("DanioSeveridadID");
                }
                if (rs.getObject("VegetacionMayorID") != null) {
                    vegetacionMayorID = rs.getInt("VegetacionMayorID");
                }
                if (rs.getObject("NumeroDanio") != null) {
                    numeroDanio = rs.getInt("NumeroDanio");
                }
                if (rs.getObject("AgenteDanioID") != null) {
                    agenteDanioID = rs.getInt("AgenteDanioID");
                }
                if (rs.getObject("SeveridadID") != null) {
                    severidadID = rs.getInt("SeveridadID");
                }
                ps.executeUpdate("INSERT INTO VEGETACIONMAYORG_DanioSeveridad(DanioSeveridadID, VegetacionMayorID, NumeroDanio, AgenteDanioID, SeveridadID)VALUES(" + danioSeveridadID + ", "
                        + vegetacionMayorID + ", " + numeroDanio + ", " + agenteDanioID + ", " + severidadID + ")");
                this.baseDatosLocal.commit();
                severidadID = null;
                danioSeveridadID = null;
                vegetacionMayorID = null;
                numeroDanio = null;
                agenteDanioID = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla VEGETACIONMAYORG_DanioSeveridad", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla VEGETACIONMAYORG_DanioSeveridad", "Conexion BD", JOptionPane.ERROR_MESSAGE);

            }
        }
    }

//46
    public void importarTaxonomiaVegetacionMayorIndividual(String ruta) {
        this.querySelect = "SELECT VegetacionMayorID, SitioID, Consecutivo, NoIndividuo, FormaVidaID, CondicionID, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, FormaGeometricaID, DensidadFollajeID, DiametroBase, AlturaTotal, DiametroCoberturaMayor, DiametroCoberturaMenor, VigorID, ClaveColecta FROM TAXONOMIA_VegetacionMayorIndividual";
        Float diametroBase = null;
        Float alturaTotal = null;
        Float diametroCoberturaMayor = null;
        Float diametroCoberturaMenor = null;
        Integer familiaID = null;
        Integer generoID = null;
        Integer especieID = null;
        Integer infraespecieID = null;
        Integer vegetacionMayorID = null;
        Integer sitioID = null;
        Integer consecutivo = null;
        Integer noIndividuo = null;
        Integer formaVidaID = null;
        Integer condicionID = null;
        String nombreComun = null;
        Integer formaGeometricaID = null;
        Integer densidadFollajeID = null;
        Integer vigorID = null;
        String claveColecta = null;
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                if (rs.getObject("VegetacionMayorID") != null) {
                    vegetacionMayorID = rs.getInt("VegetacionMayorID");
                }
                if (rs.getObject("SitioID") != null) {
                    sitioID = rs.getInt("SitioID");
                }
                if (rs.getObject("Consecutivo") != null) {
                    consecutivo = rs.getInt("Consecutivo");
                }
                if (rs.getObject("NoIndividuo") != null) {
                    noIndividuo = rs.getInt("NoIndividuo");
                }
                if (rs.getObject("FormaVidaID") != null) {
                    formaVidaID = rs.getInt("FormaVidaID");
                }
                if (rs.getObject("CondicionID") != null) {
                    condicionID = rs.getInt("CondicionID");
                }
                if (rs.getObject("NombreComun") != null) {
                    nombreComun = rs.getString("NombreComun");
                }
                if (rs.getObject("FormaGeometricaID") != null) {
                    formaGeometricaID = rs.getInt("FormaGeometricaID");
                }
                if (rs.getObject("DensidadFollajeID") != null) {
                    densidadFollajeID = rs.getInt("DensidadFollajeID");
                }
                if (rs.getObject("VigorID") != null) {
                    vigorID = rs.getInt("VigorID");
                }
                if (rs.getObject("ClaveColecta") != null) {
                    claveColecta = rs.getString("ClaveColecta");
                }
                if (rs.getObject("FamiliaID") != null) {
                    familiaID = rs.getInt("FamiliaID");
                }
                if (rs.getObject("GeneroID") != null) {
                    generoID = generoID = rs.getInt("GeneroID");
                }
                if (rs.getObject("EspecieID") != null) {
                    especieID = especieID = rs.getInt("EspecieID");
                }
                if (rs.getObject("InfraespecieID") != null) {
                    infraespecieID = infraespecieID = rs.getInt("InfraespecieID");
                }

                if (rs.getObject("DiametroBase") != null) {
                    diametroBase = rs.getFloat("DiametroBase");
                }
                if (rs.getObject("AlturaTotal") != null) {
                    alturaTotal = rs.getFloat("AlturaTotal");
                }
                if (rs.getObject("DiametroCoberturaMayor") != null) {
                    diametroCoberturaMayor = rs.getFloat("DiametroCoberturaMayor");
                }
                if (rs.getObject("DiametroCoberturaMenor") != null) {
                    diametroCoberturaMenor = rs.getFloat("DiametroCoberturaMenor");
                }

                ps.executeUpdate("INSERT INTO TAXONOMIA_VegetacionMayorIndividual(VegetacionMayorID, SitioID, Consecutivo, NoIndividuo, FormaVidaID, CondicionID, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, FormaGeometricaID, DensidadFollajeID, DiametroBase, AlturaTotal, DiametroCoberturaMayor, DiametroCoberturaMenor, VigorID, ClaveColecta)"
                        + "VALUES(" + vegetacionMayorID + ", " + sitioID + ", " + consecutivo + ", " + noIndividuo + ", " + formaVidaID + ", " + condicionID + ", " + familiaID + ", " + generoID + ", " + especieID + ", " + infraespecieID + ", '" + nombreComun + "', " + formaGeometricaID + ", " + densidadFollajeID + ", " + diametroBase + ",  " + alturaTotal
                        + ", " + diametroCoberturaMayor + ", " + diametroCoberturaMenor + ", " + vigorID + ", '" + claveColecta + "')");
                this.baseDatosLocal.commit();
                diametroBase = null;
                alturaTotal = null;
                diametroCoberturaMayor = null;
                diametroCoberturaMenor = null;
                familiaID = null;
                generoID = null;
                especieID = null;
                infraespecieID = null;
                vegetacionMayorID = null;
                sitioID = null;
                consecutivo = null;
                noIndividuo = null;
                formaVidaID = null;
                condicionID = null;
                nombreComun = null;
                formaGeometricaID = null;
                densidadFollajeID = null;
                vigorID = null;
                claveColecta = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla TAXONOMIA_VegetacionMayorIndividual", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla TAXONOMIA_VegetacionMayorIndividual", "Conexion BD", JOptionPane.ERROR_MESSAGE);

            }
        }
    }

//47
    public void importarVegetacionMayorIDanioSeveridad(String ruta) {
        this.querySelect = "SELECT DanioSeveridadID, VegetacionMayorID, NumeroDanio, AgenteDanioID, SeveridadID FROM VEGETACIONMAYORI_DanioSeveridad";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        Integer danioSeveridadID = null;
        Integer vegetacionMayorID = null;
        Integer numeroDanio = null;
        Integer agenteDanio = null;
        Integer severidadID = null;
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                if (rs.getObject("DanioSeveridadID") != null) {
                    danioSeveridadID = rs.getInt("DanioSeveridadID");
                }
                if (rs.getObject("VegetacionMayorID") != null) {
                    vegetacionMayorID = rs.getInt("VegetacionMayorID");
                }
                if (rs.getObject("NumeroDanio") != null) {
                    numeroDanio = rs.getInt("NumeroDanio");
                }
                if (rs.getObject("AgenteDanioID") != null) {
                    agenteDanio = rs.getInt("AgenteDanioID");
                }

                if (rs.getObject("SeveridadID") != null) {
                    severidadID = rs.getInt("SeveridadID");
                }
                ps.executeUpdate("INSERT INTO VEGETACIONMAYORI_DanioSeveridad(DanioSeveridadID, VegetacionMayorID, NumeroDanio, AgenteDanioID, SeveridadID)VALUES(" + danioSeveridadID + ", "
                        + vegetacionMayorID + ", " + numeroDanio + ", " + agenteDanio + ", " + severidadID + ")");
                this.baseDatosLocal.commit();
                severidadID = null;
                danioSeveridadID = null;
                vegetacionMayorID = null;
                numeroDanio = null;
                agenteDanio = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla VEGETACIONMAYORI_DanioSeveridad", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla VEGETACIONMAYORI_DanioSeveridad", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//48
    public void importarTaxonomiaVegetacionMenor(String ruta) {
        this.querySelect = "SELECT VegetacionMenorID, SitioID, Consecutivo, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, FormaVidaID, CondicionID, Numero0110, Numero1125, Numero5175, Numero76100, Numero101125, Numero126150, Numero150, PorcentajeCobertura, VigorID, ClaveColecta FROM TAXONOMIA_VegetacionMenor";
        Integer numero0110 = null;
        Integer numero1125 = null;
        Integer numero5175 = null;
        Integer numero76100 = null;
        Integer numero101125 = null;
        Integer numero126150 = null;
        Integer numero150 = null;
        Integer porcentajeCobertura = null;
        Integer familiaID = null;
        Integer generoID = null;
        Integer especieID = null;
        Integer infraespecieID = null;
        String claveColecta = null;
        Integer vegetacionMenorID = null;
        Integer sitioID = null;
        Integer consecutivo = null;
        String nombreComun = null;
        Integer formaVidaID = null;
        Integer condicionID = null;
        Integer vigorID = null;
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                if (rs.getObject("VegetacionMenorID") != null) {
                    vegetacionMenorID = rs.getInt("VegetacionMenorID");
                }
                if (rs.getObject("SitioID") != null) {
                    sitioID = rs.getInt("SitioID");
                }
                if (rs.getObject("Consecutivo") != null) {
                    consecutivo = rs.getInt("Consecutivo");
                }
                if (rs.getObject("NombreComun") != null) {
                    nombreComun = rs.getString("NombreComun");
                }
                if (rs.getObject("FormaVidaID") != null) {
                    formaVidaID = rs.getInt("FormaVidaID");
                }
                if (rs.getObject("CondicionID") != null) {
                    condicionID = rs.getInt("CondicionID");
                }
                if (rs.getObject("VigorID") != null) {
                    vigorID = rs.getInt("VigorID");
                }
                if (rs.getObject("FamiliaID") != null) {
                    familiaID = rs.getInt("FamiliaID");
                }

                if (rs.getObject("GeneroID") != null) {
                    generoID = rs.getInt("GeneroID");
                }

                if (rs.getObject("EspecieID") != null) {
                    especieID = rs.getInt("EspecieID");
                }

                if (rs.getObject("InfraespecieID") != null) {
                    infraespecieID = rs.getInt("InfraespecieID");
                }
                if (rs.getObject("ClaveColecta") != null) {
                    claveColecta = rs.getString("ClaveColecta");
                }

                if (rs.getObject("Numero0110") != null) {
                    numero0110 = rs.getInt("Numero0110");
                }
                if (rs.getObject("Numero1125") != null) {
                    numero1125 = rs.getInt("Numero1125");
                }
                if (rs.getObject("Numero5175") != null) {
                    numero5175 = rs.getInt("Numero5175");
                }
                if (rs.getObject("Numero76100") != null) {
                    numero76100 = rs.getInt("Numero76100");
                }
                if (rs.getObject("Numero101125") != null) {
                    numero101125 = rs.getInt("Numero101125");
                }
                if (rs.getObject("Numero126150") != null) {
                    numero126150 = rs.getInt("Numero126150");
                }
                if (rs.getObject("Numero150") != null) {
                    numero150 = rs.getInt("Numero150");
                }
                if (rs.getObject("PorcentajeCobertura") != null) {
                    porcentajeCobertura = rs.getInt("PorcentajeCobertura");
                }

                ps.executeUpdate("INSERT INTO TAXONOMIA_VegetacionMenor(VegetacionMenorID, SitioID, Consecutivo, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, FormaVidaID, CondicionID, Numero0110, Numero1125, Numero5175, Numero76100, Numero101125, Numero126150, Numero150, PorcentajeCobertura, VigorID, ClaveColecta)VALUES(" + vegetacionMenorID
                        + ", " + sitioID + ", " + consecutivo + ", " + familiaID + ", " + generoID + ", " + especieID + ", " + infraespecieID + ", '" + nombreComun + "', " + formaVidaID + ", " + condicionID + ", " + numero0110 + ", " + numero1125 + ", " + numero5175 + ", " + numero76100 + ", " + numero101125
                        + ", " + numero126150 + ", " + numero150 + ", " + porcentajeCobertura + ", " + vigorID + ", '" + claveColecta + "')");
                this.baseDatosLocal.commit();
                vegetacionMenorID = null;
                sitioID = null;
                consecutivo = null;
                nombreComun = null;
                formaVidaID = null;
                condicionID = null;
                vigorID = null;
                numero0110 = null;
                numero1125 = null;
                numero5175 = null;
                numero76100 = null;
                numero101125 = null;
                numero126150 = null;
                numero150 = null;
                familiaID = null;
                generoID = null;
                especieID = null;
                infraespecieID = null;
                claveColecta = null;
                porcentajeCobertura = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla TAXONOMIA_VegetacionMenor", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla TAXONOMIA_VegetacionMenor", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//49
    public void importarVegetacionMenorDanioSeveridad(String ruta) {
        this.querySelect = "SELECT DanioSeveridadVMID, VegetacionMenorID, NumeroDanio, AgenteDanioID, SeveridadID FROM VEGETACIONMENOR_DanioSeveridad";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        Integer severidadID = null;
        Integer danioSeveridadVMID = null;
        Integer vegetacionMenorID = null;
        Integer numeroDanio = null;
        Integer agenteDanioID = null;
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                if (rs.getObject("DanioSeveridadVMID") != null) {
                    danioSeveridadVMID = rs.getInt("DanioSeveridadVMID");
                }
                if (rs.getObject("VegetacionMenorID") != null) {
                    vegetacionMenorID = rs.getInt("VegetacionMenorID");
                }
                if (rs.getObject("NumeroDanio") != null) {
                    numeroDanio = rs.getInt("NumeroDanio");
                }
                if (rs.getObject("AgenteDanioID") != null) {
                    agenteDanioID = rs.getInt("AgenteDanioID");
                }
                if (rs.getObject("SeveridadID") != null) {
                    severidadID = rs.getInt("SeveridadID");
                }
                ps.executeUpdate("INSERT INTO VEGETACIONMENOR_DanioSeveridad(DanioSeveridadVMID, VegetacionMenorID, NumeroDanio, AgenteDanioID, SeveridadID)"
                        + "VALUES(" + danioSeveridadVMID + ", " + vegetacionMenorID + ", " + numeroDanio + ", " + agenteDanioID + ", " + severidadID + ")");
                this.baseDatosLocal.commit();
                danioSeveridadVMID = null;
                vegetacionMenorID = null;
                numeroDanio = null;
                agenteDanioID = null;
                severidadID = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla VEGETACIONMENOR_DanioSeveridad", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla VEGETACIONMENOR_DanioSeveridad", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void importarRepobladoDanioSeveridad(String ruta) {
        this.querySelect = "SELECT RepobladoDanioID, RepobladoVMID, NumeroDanio, AgenteDanioID, SeveridadID FROM REPOBLADO_AgenteDanio";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        Integer severidadID = null;
        Integer repobladoDanioID = null;
        Integer repobladoVMID = null;
        Integer numeroDanio = null;
        Integer agenteDanioID = null;
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                if (rs.getObject("RepobladoDanioID") != null) {
                    repobladoDanioID = rs.getInt("RepobladoDanioID");
                }
                if (rs.getObject("RepobladoVMID") != null) {
                    repobladoVMID = rs.getInt("RepobladoVMID");
                }
                if (rs.getObject("NumeroDanio") != null) {
                    numeroDanio = rs.getInt("NumeroDanio");
                }
                if (rs.getObject("AgenteDanioID") != null) {
                    agenteDanioID = rs.getInt("AgenteDanioID");
                }
                if (rs.getObject("SeveridadID") != null) {
                    severidadID = rs.getInt("SeveridadID");
                }

                ps.executeUpdate("INSERT INTO REPOBLADO_AgenteDanio(RepobladoDanioID, RepobladoVMID, NumeroDanio, AgenteDanioID, SeveridadID)"
                        + "VALUES(" + repobladoDanioID + ", " + repobladoVMID + ", " + numeroDanio + ", " + agenteDanioID + ", " + severidadID + ")");
                this.baseDatosLocal.commit();
                severidadID = null;
                repobladoDanioID = null;
                repobladoVMID = null;
                numeroDanio = null;
                agenteDanioID = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla REPOBLADO_AgenteDanio", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla REPOBLADO_AgenteDanio", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //50
    public void importarUPMContacto(String ruta) {
        this.querySelect = "SELECT ContactoID, UPMID, TipoContacto, Nombre, Direccion, TipoTelefono, NumeroTelefono, TieneCorreo, DireccionCorreo, "
                + "TieneRadio, Canal, Frecuencia ,Observaciones FROM UPM_Contacto";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        Integer contactoID = null;
        Integer upmID = null;
        Integer tipoContacto = null;
        String nombre = null;
        String direccion = null;
        Integer tipoTelefono = null;
        String numeroTelefono = null;
        Integer tieneCorreo = null;
        String direccionCorreo = null;
        Integer tieneRadio = null;
        String canal = null;
        String frecuencia = null;
        String observaciones = null;
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                if (rs.getObject("ContactoID") != null) {
                    contactoID = rs.getInt("ContactoID");
                }
                if (rs.getObject("UPMID") != null) {
                    upmID = rs.getInt("UPMID");
                }
                if (rs.getObject("TipoContacto") != null) {
                    tipoContacto = rs.getInt("TipoContacto");
                }
                if (rs.getObject("Nombre") != null) {
                    nombre = rs.getString("Nombre");
                }
                if (rs.getObject("Direccion") != null) {
                    direccion = rs.getString("Direccion");
                }
                if (rs.getObject("TipoTelefono") != null) {
                    tipoTelefono = rs.getInt("TipoTelefono");
                }
                if (rs.getObject("NumeroTelefono") != null) {
                    numeroTelefono = rs.getString("NumeroTelefono");
                }
                if (rs.getObject("TieneCorreo") != null) {
                    tieneCorreo = rs.getInt("TieneCorreo");
                }
                if (rs.getObject("DireccionCorreo") != null) {
                    direccionCorreo = rs.getString("DireccionCorreo");
                }
                if (rs.getObject("TieneRadio") != null) {
                    tieneRadio = rs.getInt("TieneRadio");
                }
                if (rs.getObject("Canal") != null) {
                    canal = rs.getString("Canal");
                }
                if (rs.getObject("Frecuencia") != null) {
                    frecuencia = rs.getString("Frecuencia");
                }
                if (rs.getObject("Observaciones") != null) {
                    observaciones = rs.getString("Observaciones");
                }
                ps.executeUpdate("INSERT INTO UPM_Contacto(ContactoID, UPMID, TipoContacto, Nombre, Direccion, TipoTelefono, NumeroTelefono, TieneCorreo, DireccionCorreo, "
                        + "TieneRadio, Canal, Frecuencia, Observaciones)VALUES(" + contactoID + ", " + upmID + ", " + tipoContacto + " , '" + nombre + "', '" + direccion + "', " + tipoTelefono
                        + ", '" + numeroTelefono + "', " + tieneCorreo + ", '" + direccionCorreo + "' , " + tieneRadio + ", '" + canal + "', '" + frecuencia + "', '" + observaciones + "')");
                this.baseDatosLocal.commit();
                contactoID = null;
                upmID = null;
                tipoContacto = null;
                nombre = null;
                direccion = null;
                tipoTelefono = null;
                numeroTelefono = null;
                tieneCorreo = null;
                direccionCorreo = null;
                tieneRadio = null;
                canal = null;
                frecuencia = null;
                observaciones = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla UPM_Contacto", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla UPM_Contacto", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //51
    public void importarUPMEpifitas(String ruta) {
        this.querySelect = "SELECT EpifitaID, UPMID, ClaseTipoID, PresenciaTroncosID, PresenciaRamasID FROM UPM_Epifita";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        Integer epifitaID = null;
        Integer upmID = null;
        Integer claseTipoID = null;
        Integer presenciaTroncosID = null;
        Integer presenciaRamasID = null;
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                if (rs.getObject("EpifitaID") != null) {
                    epifitaID = rs.getInt("EpifitaID");
                }
                if (rs.getObject("UPMID") != null) {
                    upmID = rs.getInt("UPMID");
                }
                if (rs.getObject("ClaseTipoID") != null) {
                    claseTipoID = rs.getInt("ClaseTipoID");
                }
                if (rs.getObject("PresenciaTroncosID") != null) {
                    presenciaTroncosID = rs.getInt("PresenciaTroncosID");
                }
                if (rs.getObject("PresenciaRamasID") != null) {
                    presenciaRamasID = rs.getInt("PresenciaRamasID");
                }
                ps.executeUpdate("INSERT INTO UPM_Epifita(EpifitaID, UPMID, ClaseTipoID, PresenciaTroncosID, PresenciaRamasID)VALUES(" + epifitaID + ", " + upmID + ", " + claseTipoID
                        + ", " + presenciaTroncosID + ", " + presenciaRamasID + ")");
                this.baseDatosLocal.commit();
                epifitaID = null;
                upmID = null;
                claseTipoID = null;
                presenciaTroncosID = null;
                presenciaRamasID = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla UPM_Epifita", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla UPM_Epifita", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //53
    public void importarSecuencias(String ruta) {
        this.querySelect = "SELECT SecuenciaCapturaID, SecuenciaID, UPMID, Sitio, FormatoID, Estatus FROM SYS_SecuenciaCaptura";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        Integer secuenciaCapturaID = null;
        Integer secuenciaID = null;
        Integer UPMID = null;
        Integer sitio = null;
        Integer formatoID = null;
        Integer estatus = null;
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                if (rs.getObject("SecuenciaCapturaID") != null) {
                    secuenciaCapturaID = rs.getInt("SecuenciaCapturaID");
                }
                if (rs.getObject("SecuenciaID") != null) {
                    secuenciaID = rs.getInt("SecuenciaID");
                }
                if (rs.getObject("UPMID") != null) {
                    UPMID = rs.getInt("UPMID");
                }
                if (rs.getObject("Sitio") != null) {
                    sitio = rs.getInt("Sitio");
                }
                if (rs.getObject("FormatoID") != null) {
                    formatoID = rs.getInt("FormatoID");
                }
                if (rs.getObject("Estatus") != null) {
                    estatus = rs.getInt("Estatus");
                }
                ps.executeUpdate("INSERT INTO SYS_SecuenciaCaptura(SecuenciaCapturaID, SecuenciaID, UPMID, Sitio, FormatoID, Estatus)"
                        + "VALUES(" + secuenciaCapturaID + ", " + secuenciaID + ", " + UPMID + ", " + sitio + ", " + formatoID + ", " + estatus + ")");
                this.baseDatosLocal.commit();
                secuenciaCapturaID = null;
                secuenciaID = null;
                UPMID = null;
                sitio = null;
                formatoID = null;
                estatus = null;
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SYS_SecuenciaCaptura", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SYS_SecuenciaCaptura", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void importarObservacionesSitio(String ruta) {
        //state=" importarObservaciones Sitio";
        this.querySelect = "SELECT ObservacionesID ,SitioID ,FormatoID, Observaciones FROM  SITIOS_Observaciones ";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        Integer observacionesID = null;
        Integer sitioID = null;
        Integer formatoID = null;
        String observaciones = null;
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                if (rs.getObject("ObservacionesID") != null) {
                    observacionesID = rs.getInt("ObservacionesID");
                }
                if (rs.getObject("SitioID") != null) {
                    sitioID = rs.getInt("SitioID");
                }
                if (rs.getObject("FormatoID") != null) {
                    formatoID = rs.getInt("FormatoID");
                }
                if (rs.getObject("Observaciones") != null) {
                    observaciones = rs.getString("Observaciones");
                }
                ps.executeUpdate(
                        "INSERT INTO SITIOS_Observaciones(ObservacionesID,SitioID,FormatoID,Observaciones)VALUES("
                        + observacionesID + ", " + sitioID + ", " + formatoID + ",'" + observaciones + "')");
                this.baseDatosLocal.commit();
                observacionesID = null;
                sitioID = null;
                formatoID = null;
                observaciones = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null,
                        "Error! al cerrar la base de datos en la importación de la tabla ARBOLADO_Submuestra"
                        + e.getClass().getName() + " : " + e.getMessage(),
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void importarUPMRevision(String ruta) {
        this.querySelect = "SELECT RevisionID, UPMID, SitioID, Sitio, SecuenciaID FROM SYS_UPM_Revision";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        Integer revisionID = null;
        Integer UPMID = null;
        Integer sitioID = null;
        Integer sitio = null;
        Integer secuenciaID = null;
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                if (rs.getObject("RevisionID") != null) {
                    revisionID = rs.getInt("RevisionID");
                }
                if (rs.getObject("UPMID") != null) {
                    UPMID = rs.getInt("UPMID");
                }
                if (rs.getObject("SitioID") != null) {
                    sitioID = rs.getInt("SitioID");
                }
                if (rs.getObject("Sitio") != null) {
                    sitio = rs.getInt("Sitio");
                }
                if (rs.getObject("SecuenciaID") != null) {
                    secuenciaID = rs.getInt("SecuenciaID");
                }
                ps.executeUpdate("INSERT INTO SYS_UPM_Revision(RevisionID, UPMID, SitioID, Sitio, SecuenciaID)"
                        + "VALUES(" + revisionID + ", " + UPMID + ", " + sitioID + ", " + sitio + ", " + secuenciaID + ")");
                this.baseDatosLocal.commit();
                revisionID = null;
                UPMID = null;
                sitioID = null;
                sitio = null;
                secuenciaID = null;
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SYS_UPM_Revision", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SYS_UPM_Revision", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //54
    public void importarBrigadas(String ruta) {
        this.querySelect = "SELECT BrigadaID, UPMID, BrigadistaID, PuestoID, EmpresaID FROM UPM_Brigada";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        Integer brigadaID = null;
        Integer UPMID = null;
        Integer brigadistaID = null;
        Integer puestoID = null;
        Integer empresaID = null;
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                if (rs.getObject("BrigadaID") != null) {
                    brigadaID = rs.getInt("BrigadaID");
                }
                if (rs.getObject("UPMID") != null) {
                    UPMID = rs.getInt("UPMID");
                }
                if (rs.getObject("BrigadistaID") != null) {
                    brigadistaID = rs.getInt("BrigadistaID");
                }
                if (rs.getObject("PuestoID") != null) {
                    puestoID = rs.getInt("PuestoID");
                }
                if (rs.getObject("EmpresaID") != null) {
                    empresaID = rs.getInt("EmpresaID");
                }
                ps.executeUpdate("INSERT INTO UPM_Brigada(BrigadaID, UPMID, BrigadistaID, PuestoID, EmpresaID)"
                        + "VALUES(" + brigadaID + ", " + UPMID + ", " + brigadistaID + ", " + puestoID + ", " + empresaID + ")");
                this.baseDatosLocal.commit();
                brigadaID = null;
                UPMID = null;
                brigadistaID = null;
                puestoID = null;
                empresaID = null;
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla UPM_Brigada", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla UPM_Brigada", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void eliminarPorUPM(int upm) {
        String query = "DELETE FROM UPM_UPM WHERE UPMID =" + upm;
        //System.out.println(query);
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la informacion del upm seleccionado", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al eliminar los datos del upm seleccionado ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
