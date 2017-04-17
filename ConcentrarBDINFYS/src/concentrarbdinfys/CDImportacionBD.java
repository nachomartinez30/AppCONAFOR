package concentrarbdinfys;

import concentrarbdinfys.ExternalConnection;
import concentrarbdinfys.LocalConnection;
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
        System.out.println("validando repetidos...");
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
                        if(respuesta == JOptionPane.NO_OPTION) {
                            System.out.println("opcion no");
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
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer UPMID = rs.getInt("UPMID");
                String fechaInicio = rs.getString("FechaInicio");
                String fechaFin = rs.getString("FechaFin");
                Integer tipoUpmID = rs.getInt("TipoUPMID");
                Float altitud = rs.getFloat("Altitud");
                Integer pendiente = rs.getInt("PendienteRepresentativa");
                Integer fisiografiaID = rs.getInt("FisiografiaID");
                Integer exposicionID = rs.getInt("ExposicionID");
                String predio = rs.getString("Predio");
                String paraje = rs.getString("Paraje");
                Integer tipoTenenciaID = rs.getInt("TipoTenenciaID");
                Integer accesible = rs.getInt("Accesible");
                Integer gradosLatitud = rs.getInt("GradosLatitud");
                Integer minutosLatitud = rs.getInt("MinutosLatitud");
                Float segundosLatitud = rs.getFloat("SegundosLatitud");
                Integer gradosLongitud = rs.getInt("GradosLongitud");
                Integer minutosLongitud = rs.getInt("MinutosLongitud");
                Float segundosLongitud = rs.getFloat("SegundosLongitud");
                String datum = rs.getString("Datum");
                Integer errorPresicion = rs.getInt("ErrorPresicion");
                Integer azimut = rs.getInt("Azimut");
                Float distancia = rs.getFloat("Distancia");
                if (rs.getObject("TipoInaccesibilidadID") != null) {
                    tipoInaccesibilidadID = rs.getInt("TipoInaccesibilidadID");
                }
                String otroTipoInaccesibilidad = rs.getString("OtroTipoInaccesibilidad");
                String explicacionInaccesibilidad = rs.getString("ExplicacionInaccesibilidad");
                Integer informacionContacto = rs.getInt("InformacionContacto");
                ps.executeUpdate("INSERT INTO UPM_UPM(UPMID, FechaInicio, FechaFin, TipoUPMID, Altitud, PendienteRepresentativa, "
                        + "FisiografiaID, ExposicionID, 'Predio', 'Paraje', TipoTenenciaID, Accesible, GradosLatitud, MinutosLatitud, "
                        + "SegundosLatitud, GradosLongitud, MinutosLongitud, SegundosLongitud, Datum, ErrorPresicion, "
                        + "Azimut, Distancia, TipoInaccesibilidadID, OtroTipoInaccesibilidad, ExplicacionInaccesibilidad, "
                        + "InformacionContacto)VALUES(" + UPMID + ", '" + fechaInicio + "', '" + fechaFin + "', " + tipoUpmID + ", " + altitud + ", " + pendiente + " , " + fisiografiaID + ", " + exposicionID
                        + ", '" + predio + "', '" + paraje + "', " + tipoTenenciaID + ", " + accesible + ", " + gradosLatitud + ", " + minutosLatitud + ", " + segundosLatitud + ", " + gradosLongitud + ", " + minutosLongitud + ", " + segundosLongitud + " , '"
                        + datum + "', " + errorPresicion + ", " + azimut + ", " + distancia + ", " + tipoInaccesibilidadID + ", '" + otroTipoInaccesibilidad + "', '" + explicacionInaccesibilidad + "', " + informacionContacto + ")");
                tipoInaccesibilidadID = null;
                this.baseDatosLocal.commit();
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
        this.querySelect = "SELECT PuntoControlID, UPMID, Descripcion, Paraje, GradosLatitud, MinutosLatitud, SegundosLatitud, GradosLongitud, "
                + "MinutosLongitud, SegundosLongitud, ErrorPresicion, Datum, Azimut, Distancia FROM PC_PuntoControl";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer puntoControlID = rs.getInt("PuntoControlID");
                Integer upmID = rs.getInt("UPMID");
                String descripcion = rs.getString("Descripcion");
                String paraje = rs.getString("Paraje");
                Integer gradosLatitud = rs.getInt("GradosLatitud");
                Integer minutosLatitud = rs.getInt("MinutosLatitud");
                Float segundosLatitud = rs.getFloat("SegundosLatitud");
                Integer gradosLongitud = rs.getInt("GradosLongitud");
                Integer minutosLongitud = rs.getInt("MinutosLongitud");
                Float segundosLongitud = rs.getFloat("SegundosLongitud");
                Integer errorPresicion = rs.getInt("ErrorPresicion");
                String datum = rs.getString("Datum");
                Integer azimut = rs.getInt("Azimut");
                Float distancia = rs.getFloat("Distancia");
                ps.executeUpdate("INSERT INTO PC_PuntoControl(PuntoControlID,UPMID, Descripcion, Paraje, GradosLatitud, MinutosLatitud, SegundosLatitud, GradosLongitud, "
                        + "MinutosLongitud, SegundosLongitud, ErrorPresicion, Datum, Azimut, Distancia)VALUES(" +puntoControlID+","+ upmID + ", '" + descripcion + "', '" + paraje + "', " + gradosLatitud
                        + ", " + minutosLatitud + ", " + segundosLatitud + ", " + gradosLongitud + ", " + minutosLongitud + ", " + segundosLongitud + ", " + errorPresicion + ", '"
                        + datum + "', " + azimut + ", " + distancia + ")");
                this.baseDatosLocal.commit();
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
        this.querySelect = "SELECT AccesibilidadID ,UPMID, MedioTransporteID, ViaAccesibilidadID, Distancia, CondicionAccesibilidadID FROM PC_Accesibilidad";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer accesibilidadID  = rs.getInt("AccesibilidadID");
                Integer upmID = rs.getInt("UPMID");
                Integer medioTransporteID = rs.getInt("MedioTransporteID");
                Integer viaAccesibilidadID = rs.getInt("ViaAccesibilidadID");
                Float distancia = rs.getFloat("Distancia");
                Integer condicionAccesibilidadID = rs.getInt("CondicionAccesibilidadID");
                ps.executeUpdate("INSERT INTO PC_Accesibilidad(AccesibilidadID, UPMID, MedioTransporteID, ViaAccesibilidadID, Distancia, CondicionAccesibilidadID)"
                        + "VALUES("+accesibilidadID+"," + upmID + ", " + medioTransporteID + ", " + viaAccesibilidadID + ", " + distancia + ", " + condicionAccesibilidadID + ")");
                this.baseDatosLocal.commit();
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
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer upmID = rs.getInt("UPMID");
                Integer sitioID = rs.getInt("SitioID");
                Integer sitio = rs.getInt("Sitio");
                Integer senialGPS = rs.getInt("SenialGPS");
                Integer gradosLatitud = rs.getInt("GradosLatitud");
                Integer minutosLatitud = rs.getInt("MinutosLatitud");
                Float segundosLatitud = rs.getFloat("SegundosLatitud");
                Integer gradosLongitud = rs.getInt("GradosLongitud");
                Integer minutosLongitud = rs.getInt("MinutosLongitud");
                Float segundosLongitud = rs.getFloat("SegundosLongitud");
                Integer errorPresicion = rs.getInt("ErrorPresicion");
                if (rs.getObject("EvidenciaMuestreo") != null) {
                    evidenciaMuestreo = rs.getInt("EvidenciaMuestreo");
                }
                String datum = rs.getString("Datum");
                Integer azimut = rs.getInt("Azimut");
                Float distancia = rs.getFloat("Distancia");
                Integer sitioAccesible = rs.getInt("SitioAccesible");
                Integer tipoInaccesibilidad = rs.getInt("TipoInaccesibilidad");
                String explicacionInaccesibilidad = rs.getString("ExplicacionInaccesibilidad");
                Integer coberturaForestal = rs.getInt("CoberturaForestal");
                if (rs.getObject("Condicion") != null) {
                    condicion = rs.getInt("Condicion");
                }
                Integer claveSerieV = rs.getInt("ClaveSerieV");
                if (rs.getObject("FaseSucecional") != null) {
                    faseSucecional = rs.getInt("FaseSucecional");
                }
                Integer arbolFuera = rs.getInt("ArbolFuera");
                Integer ecotono = rs.getInt("Ecotono");
                String condicionPresente = rs.getString("CondicionPresenteCampo");
                String condicionEcotono = rs.getString("CondicionEcotono");
                Integer repobladoFuera = rs.getInt("RepobladoFuera");
                Integer porcentajeRepoblado = rs.getInt("PorcentajeRepoblado");
                Integer sotoBosqueFuera = rs.getInt("SotoBosqueFuera");
                Integer porcentajeSotoBosque = rs.getInt("PorcentajeSotoBosqueFuera");
                String observaciones = rs.getString("Observaciones");
                Integer hipsometroBrujula = rs.getInt("HipsometroBrujula");
                Integer cintaClinometroBrujula = rs.getInt("CintaClinometroBrujula");
                Integer cuadrante1 = rs.getInt("Cuadrante1");
                Integer cuadrante2 = rs.getInt("Cuadrante2");
                Integer cuadrante3 = rs.getInt("Cuadrante3");
                Integer cuadrante4 = rs.getInt("Cuadrante4");
                Float distancia1 = rs.getFloat("Distancia1");
                Float distancia2 = rs.getFloat("Distancia2");
                Float distancia3 = rs.getFloat("Distancia3");
                Float distancia4 = rs.getFloat("Distancia4");
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
                evidenciaMuestreo = null;
                condicion = null;
                faseSucecional = null;
                this.baseDatosLocal.commit();
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
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer coberturaID = rs.getInt("CoberturaID");
                Integer sitioID = rs.getInt("SitioID");
                Integer upmid = extraerUPM(sitioID, ruta);
                Integer gramineas = rs.getInt("Gramineas");
                Integer helechos = rs.getInt("Helechos");
                Integer musgos = rs.getInt("Musgos");
                Integer liquenes = rs.getInt("Liquenes");
                Integer hierbas = rs.getInt("Hierbas");
                Integer rocas = rs.getInt("Roca");
                Integer sueloDesnudo = rs.getInt("SueloDesnudo");
                Integer hojarasca = rs.getInt("Hojarasca");
                Integer grava = rs.getInt("Grava");
                Integer otros = rs.getInt("Otros");
                ps.executeUpdate("INSERT INTO SITIOS_CoberturaSuelo(SitioID,UPMID,CoberturaID,  Gramineas, Helechos, Musgos, Liquenes, Hierbas, Roca, SueloDesnudo, Hojarasca, Grava, Otros)"
                        + "VALUES(" + sitioID + ", " + upmid + "," + coberturaID + ", " + gramineas + ", " + helechos + ", " + musgos + ", " + liquenes + ", " + hierbas + ", " + rocas
                        + ", " + sueloDesnudo + ", " + hojarasca + ", " + grava + ", " + otros + ")");
                this.baseDatosLocal.commit();
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
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);

            while (rs.next()) {
                Integer fotografiaHemisfericaID = rs.getInt("FotografiaHemisfericaID");
                Integer sitioID = rs.getInt("SitioID");
                Integer upmid = extraerUPM(sitioID, ruta);
                Integer coberturaArborea = rs.getInt("CoberturaArborea");
                Integer tomaFotografia = rs.getInt("TomaFotografia");
                String hora = rs.getString("Hora");
                Integer declinacionMagnetica = rs.getInt("DeclinacionMagnetica");
                ps.executeUpdate("INSERT INTO SITIOS_FotografiaHemisferica(SitioID,UPMID,FotografiaHemisfericaID, CoberturaArborea, TomaFotografia, Hora, DeclinacionMagnetica)"
                        + "VALUES(" + sitioID + ", " + upmid + "," + fotografiaHemisfericaID + ", " + coberturaArborea + ", " + tomaFotografia + ", '" + hora + "', " + declinacionMagnetica + ")");
                this.baseDatosLocal.commit();
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
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {

                Integer transponderID = rs.getInt("TransponderID");
                Integer sitioID = rs.getInt("SitioID");
                Integer upmid = extraerUPM(sitioID, ruta);
                Integer tipoColocacionID = rs.getInt("TipoColocacionID");
                String especifique = rs.getString("Especifique");
                String observaciones = rs.getString("Observaciones");
                ps.executeUpdate("INSERT INTO SITIOS_Transponder(SitioID,UPMID,TransponderID, TipoColocacionID, Especifique, Observaciones)"
                        + "VALUES(" + sitioID + ", " + upmid + "," + transponderID + ", " + tipoColocacionID + ", '" + especifique + "', '" + observaciones + "')");
                this.baseDatosLocal.commit();
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
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer parametrosFQID = rs.getInt("ParametrosFQID");
                Integer sitioID = rs.getInt("SitioID");
                Integer upmid = extraerUPM(sitioID, ruta);
                Integer tipoAgua = rs.getInt("TipoAgua");
                Float salinidad = rs.getFloat("Salinidad");
                Float temperatura = rs.getFloat("Temperatura");
                Float conductividadElectrica = rs.getFloat("ConductividadElectrica");
                Float ph = rs.getFloat("Ph");
                Float potencialRedox = rs.getFloat("PotencialRedox");
                Float profundidad = rs.getFloat("Profundidad");
                String observaciones = rs.getString("Observaciones");
                ps.executeUpdate("INSERT INTO SITIOS_ParametrosFisicoQuimicos(SitioID,UPMID,ParametrosFQID,  TipoAgua, Salinidad, Temperatura, ConductividadElectrica, Ph, PotencialRedox, "
                        + "Profundidad, Observaciones)VALUES(" + sitioID + ", " + upmid + "," + parametrosFQID + ", " + tipoAgua + ", " + salinidad + ", " + temperatura + ", " + conductividadElectrica + ", " + ph
                        + ", " + potencialRedox + ", " + profundidad + ", '" + observaciones + "')");
                this.baseDatosLocal.commit();
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
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer canalilloID = rs.getInt("CanalilloID");
                Integer sitioID = rs.getInt("SitioID");
                Integer upmid = extraerUPM(sitioID, ruta);
                Integer numero = rs.getInt("Numero");
                Float ancho = rs.getFloat("Ancho");
                Float profundidad = rs.getFloat("Profundidad");
                ps.executeUpdate("INSERT INTO SUELO_Canalillo(SitioID,UPMID,CanalilloID,  Numero, Ancho, Profundidad)VALUES(" + sitioID + ", " + upmid + "," + canalilloID + ", " + numero + ", " + ancho + ", " + profundidad + ")");
                this.baseDatosLocal.commit();
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
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer carcavaID = rs.getInt("CarcavaID");
                Integer sitioID = rs.getInt("SitioID");
                Integer upmid = extraerUPM(sitioID, ruta);
                Integer numero = rs.getInt("Numero");
                Float ancho = rs.getFloat("Ancho");
                Float profundidad = rs.getFloat("Profundidad");
                ps.executeUpdate("INSERT INTO SUELO_Carcava(SitioID,UPMID,CarcavaID,  Numero, Ancho, Profundidad)VALUES(" + sitioID + ", " + upmid + "," + carcavaID + ", " + numero + ", " + ancho + ", " + profundidad + ")");
                this.baseDatosLocal.commit();
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
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer coberturaSueloID = rs.getInt("CoberturaSueloID");
                Integer sitioID = rs.getInt("SitioID");
                Integer upmid = extraerUPM(sitioID, ruta);
                Integer transecto = rs.getInt("Transecto");
                Integer pendiente = rs.getInt("Pendiente");
                ps.executeUpdate("INSERT INTO SUELO_CoberturaSuelo(SitioID,UPMID,CoberturaSueloID,  Transecto, Pendiente)VALUES(" + sitioID + ", " + upmid + "," + coberturaSueloID + ", " + transecto + ", " + pendiente + ")");
                this.baseDatosLocal.commit();
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
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer costrasID = rs.getInt("CostrasID");
                Integer sitioID = rs.getInt("SitioID");
                Integer upmid = extraerUPM(sitioID, ruta);
                Integer numero = rs.getInt("Numero");
                Float diametro = rs.getFloat("Diametro");
                ps.executeUpdate("INSERT INTO SUELO_Costras(SitioID,UPMID,CostrasID,  Numero, Diametro)VALUES(" + sitioID + ", " + upmid + "," + costrasID + ", " + numero + ", " + diametro + ")");
                this.baseDatosLocal.commit();
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
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer deformacionVientoID = rs.getInt("DeformacionVientoID");
                Integer sitioID = rs.getInt("SitioID");
                Integer upmid = extraerUPM(sitioID, ruta);
                Integer medicion = rs.getInt("Medicion");
                Float altura = rs.getFloat("Altura");
                Float diametro = rs.getFloat("Diametro");
                Float longitud = rs.getFloat("Longitud");
                Float distancia = rs.getFloat("Distancia");
                Integer azimut = rs.getInt("Azimut");
                ps.executeUpdate("INSERT INTO SUELO_DeformacionViento(SitioID,UPMID,DeformacionVientoID, Medicion, Altura, Diametro, Longitud, Distancia, Azimut)VALUES(" + sitioID + ", " + upmid + "," + deformacionVientoID + ", " + medicion + ", " + altura + ", " + diametro + ", " + longitud + ", " + distancia + ", " + azimut + ")");
                this.baseDatosLocal.commit();
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
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer erosionCanalilloID = rs.getInt("ErosionCanalilloID");
                Integer sitioID = rs.getInt("SitioID");
                Integer upmid = extraerUPM(sitioID, ruta);
                Integer medicion = rs.getInt("Medicion");
                Float profundidad = rs.getFloat("Profundidad");
                Float ancho = rs.getFloat("Ancho");
                Float distancia = rs.getFloat("Distancia");
                Integer azimut = rs.getInt("Azimut");
                ps.executeUpdate("INSERT INTO SUELO_ErosionHidricaCanalillo(SitioID,UPMID,ErosionCanalilloID,Medicion, Profundidad, Ancho, Distancia, Azimut)"
                        + "VALUES(" + sitioID + ", " + upmid + ", " + erosionCanalilloID + ", " + medicion + ", " + profundidad + ", " + ancho + ", " + distancia + ", " + azimut + ")");
                this.baseDatosLocal.commit();
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
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer erosionCarcavaID = rs.getInt("ErosionCarcavaID");
                Integer sitioID = rs.getInt("SitioID");
                Integer upmid = extraerUPM(sitioID, ruta);
                Integer medicion = rs.getInt("Medicion");
                Float profundidad = rs.getFloat("Profundidad");
                Float ancho = rs.getFloat("Ancho");
                Float distancia = rs.getFloat("Distancia");
                Integer azimut = rs.getInt("Azimut");
                ps.executeUpdate("INSERT INTO SUELO_ErosionHidricaCarcava(SitioID,UPMID,ErosionCarcavaID, Medicion, Profundidad, Ancho, Distancia, Azimut)"
                        + "VALUES(" + sitioID + ", " + upmid + ", " + erosionCarcavaID + ", " + medicion + ", " + profundidad + ", " + ancho + ", " + distancia + ", " + azimut + ")");
                this.baseDatosLocal.commit();
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
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer erosionLaminarID = rs.getInt("ErosionLaminarID");
                Integer sitioID = rs.getInt("SitioID");
                Integer upmid = extraerUPM(sitioID, ruta);
                Integer numero = rs.getInt("Numero");
                Float ancho = rs.getFloat("Ancho");
                Float largo = rs.getFloat("Largo");
                ps.executeUpdate("INSERT INTO SUELO_ErosionLaminar(SitioID,UPMID,ErosionLaminarID,  Numero, Ancho, Largo)VALUES(" + sitioID + ", " + upmid + ", " + erosionLaminarID + ", " + numero + ", " + ancho + ", " + largo + ")");
                this.baseDatosLocal.commit();
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

    public int extraerSitioIDEvidenciaErosion(int coberturaSuelioID,String ruta){
        int sitioID=0;
        String query = "SELECT SitioID FROM SUELO_CoberturaSuelo WHERE CoberturaSueloID=" + coberturaSuelioID;
        //System.out.println(query);
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            ResultSet rs = sqlExterno.executeQuery(query);
            while (rs.next()) {
                sitioID = rs.getInt("SitioID");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error! no se encontó sitioID en SUELO_CoberturaSuelo", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return sitioID;
    }
    
    //16
    public void importarSueloEvidenciaErosion(String ruta) {
        
        this.querySelect = "SELECT EvidenciaErosionID, CoberturaSueloID, Punto, Dosel, LecturaTierraID FROM SUELO_EvidenciaErosion";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer evidenciaErosionID = rs.getInt("EvidenciaErosionID");
                Integer coberturaSueloID = rs.getInt("CoberturaSueloID");
                
                Integer sitioID= extraerSitioIDEvidenciaErosion(coberturaSueloID,ruta);
                Integer upmid = extraerUPM(sitioID, ruta);
                
                Integer punto = rs.getInt("Punto");
                Integer dosel = rs.getInt("Dosel");
                Integer lecturaTierraID = rs.getInt("LecturaTierraID");
             
                ps.executeUpdate("INSERT INTO SUELO_EvidenciaErosion(UPMID,SitioID,EvidenciaErosionID, CoberturaSueloID, Punto, Dosel, LecturaTierraID)VALUES(" +upmid +", "+sitioID+", "+ evidenciaErosionID + ", " + coberturaSueloID + ", " + punto + ", " + dosel + ", " + lecturaTierraID + ")");
                this.baseDatosLocal.commit();
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
        this.querySelect = "SELECT HojarascaID, SitioID, Punto, TipoHojarascaID, EspesorHO, EspesorF, PesoTotalHO, PesoTotalF, PesoMuestraHO, PesoMuestraF, "
                + "Observaciones FROM SUELO_Hojarasca";
        Float espesorF = null;
        Float pesoTotalF = null;
        Float pesoMuestraF = null;
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer hojarascaID = rs.getInt("HojarascaID");
                Integer sitioID = rs.getInt("SitioID");
                Integer upmid = extraerUPM(sitioID, ruta);
                Integer punto = rs.getInt("Punto");
                Integer tipoHojarascaID = rs.getInt("TipoHojarascaID");
                Float espesorHO = rs.getFloat("EspesorHO");
                if (rs.getObject("EspesorF") != null) {
                    espesorF = rs.getFloat("EspesorF");
                }
                Float pesoTotalHO = rs.getFloat("PesoTotalHO");
                if (rs.getObject("PesoTotalF") != null) {
                    pesoTotalF = rs.getFloat("PesoTotalF");
                }
                Float pesoMuestraHO = rs.getFloat("PesoMuestraHO");
                if (rs.getObject("PesoMuestraF") != null) {
                    pesoMuestraF = rs.getFloat("PesoMuestraF");
                }
                String observaciones = rs.getString("Observaciones");
                ps.executeUpdate("INSERT INTO SUELO_Hojarasca(SitioID,UPMID,HojarascaID,  Punto, TipoHojarascaID, EspesorHO, EspesorF, PesoTotalHO, PesoTotalF, PesoMuestraHO, PesoMuestraF, "
                        + "Observaciones)VALUES(" + sitioID + ", " + upmid + ", " + hojarascaID + ", " + punto + ", " + tipoHojarascaID + ", " + espesorHO + ", " + espesorF + ", " + pesoTotalHO + ", " + pesoTotalF + ", " + pesoMuestraHO + ", " + pesoMuestraF + ", '" + observaciones + "')");
                espesorF = null;
                pesoTotalF = null;
                pesoMuestraF = null;
                this.baseDatosLocal.commit();
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
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer longitudCanalilloID = rs.getInt("LongitudCanalilloID");
                Integer sitioID = rs.getInt("SitioID");
                Integer upmid = extraerUPM(sitioID, ruta);
                Integer campoLongitud = rs.getInt("CampoLongitud");
                Float longitud = rs.getFloat("Longitud");
                ps.executeUpdate("INSERT INTO SUELO_LongitudCanalillo(SitioID,UPMID,LongitudCanalilloID, CampoLongitud, Longitud)"
                        + "VALUES(" + sitioID + ", " + upmid + ", " + longitudCanalilloID + ", " + campoLongitud + ", " + longitud + ")");
                this.baseDatosLocal.commit();
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
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer longitudCarcavaID = rs.getInt("LongitudCarcavaID");
                Integer sitioID = rs.getInt("SitioID");
                Integer upmid = extraerUPM(sitioID, ruta);
                Integer campoLongitud = rs.getInt("CampoLongitud");
                Float longitud = rs.getFloat("Longitud");
                ps.executeUpdate("INSERT INTO SUELO_LongitudCarcava(SitioID,UPMID,LongitudCarcavaID, CampoLongitud, Longitud)VALUES(" + sitioID + ", " + upmid + ", " + longitudCarcavaID + ", " + campoLongitud + ", " + longitud + ")");
                this.baseDatosLocal.commit();
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
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer longitudMonticuloID = rs.getInt("LongitudMonticuloID");
                Integer sitioID = rs.getInt("SitioID");
                Integer upmid = extraerUPM(sitioID, ruta);
                Integer campoLongitud = rs.getInt("CampoLongitud");
                Float longitud = rs.getFloat("Longitud");
                ps.executeUpdate("INSERT INTO SUELO_LongitudMonticulo(SitioID,UPMID,LongitudMonticuloID,  CampoLongitud, Longitud)VALUES(" + sitioID + ", " + upmid + ", " + longitudMonticuloID + ", " + campoLongitud + ", " + longitud + ")");
                this.baseDatosLocal.commit();
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
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer medicionCanalillosID = rs.getInt("MedicionCanalillosID");
                Integer sitioID = rs.getInt("SitioID");
                Integer upmid = extraerUPM(sitioID, ruta);
                Integer numeroCanalillos = rs.getInt("NumeroCanalillos");
                Float profundidadPromedio = rs.getFloat("ProfundidadPromedio");
                Float longitud = rs.getFloat("Longitud");
                Float volumen = rs.getFloat("Volumen");
                ps.executeUpdate("INSERT INTO SUELO_MedicionCanalillos(SitioID,UPMID,MedicionCanalillosID,  NumeroCanalillos, ProfundidadPromedio, Longitud, Volumen)"
                        + "VALUES(" + sitioID + ", " + upmid + ", " + medicionCanalillosID + ", " + numeroCanalillos + ", " + profundidadPromedio + ", " + longitud + ", " + volumen + ")");
                this.baseDatosLocal.commit();
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
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer medicionCarcavasID = rs.getInt("MedicionCarcavasID");
                Integer sitioID = rs.getInt("SitioID");
                Integer upmid = extraerUPM(sitioID, ruta);
                Integer numeroCarcavas = rs.getInt("NumeroCarcavas");
                Float profundidadPromedio = rs.getFloat("ProfundidadPromedio");
                Float anchoPromedio = rs.getFloat("AnchoPromedio");
                Float longitud = rs.getFloat("Longitud");
                Float volumen = rs.getFloat("Volumen");
                ps.executeUpdate("INSERT INTO SUELO_MedicionCanalillos(SitioID,UPMID,MedicionCarcavasID,  NumeroCarcavas, ProfundidadPromedio, AnchoPromedio, Longitud, Volumen)"
                        + "VALUES(" + sitioID + ", " + upmid + ", " + medicionCarcavasID + ", " + numeroCarcavas + ", " + profundidadPromedio + ", " + anchoPromedio + ", " + longitud + ", " + volumen + ")");
                this.baseDatosLocal.commit();
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
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer medicionDunas = rs.getInt("MedicionDunas");
                Integer sitioID = rs.getInt("SitioID");
                Integer upmid = extraerUPM(sitioID, ruta);
                Integer numeroDunas = rs.getInt("NumeroDunas");
                Float alturaPromedio = rs.getFloat("AlturaPromedio");
                Float anchoPromedio = rs.getFloat("AnchoPromedio");
                Float longitud = rs.getFloat("Longitud");
                Float volumen = rs.getFloat("Volumen");
                ps.executeUpdate("INSERT INTO SUELO_MedicionCanalillos(SitioID,UPMID,MedicionDunas, NumeroCarcavas, ProfundidadPromedio, AnchoPromedio, Longitud, Volumen)"
                        + "VALUES(" + sitioID + ", " + upmid + ", " + medicionDunas + ", " + numeroDunas + ", " + alturaPromedio + ", " + anchoPromedio + ", " + longitud + ", " + volumen + ")");
                this.baseDatosLocal.commit();
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
        this.querySelect = "SELECT MuestrasID, SitioID, ProfundidadID, PesoMuestra, Lectura1, Lectura2, Lectura3, Lectura4, "
                + "Promedio, ClaveColecta FROM SUELO_Muestras";
        Float lectura1 = null;
        Float lectura2 = null;
        Float lectura3 = null;
        Float lectura4 = null;
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer muestrasID = rs.getInt("MuestrasID");
                Integer sitioID = rs.getInt("SitioID");
                Integer upmid = extraerUPM(sitioID, ruta);
                Integer profundidadID = rs.getInt("ProfundidadID");
                Float pesoMuestra = rs.getFloat("PesoMuestra");
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
                Float promedio = rs.getFloat("Promedio");
                String claveColecta = rs.getString("ClaveColecta");
                ps.executeUpdate("INSERT INTO SUELO_Muestras(SitioID,UPMID,MuestrasID,  ProfundidadID, PesoMuestra, Lectura1, Lectura2, Lectura3, Lectura4, "
                        + "Promedio, ClaveColecta)VALUES(" + sitioID + ", " + upmid + ", " + muestrasID + ", " + profundidadID + ", " + pesoMuestra + ", " + lectura1
                        + ", " + lectura2 + ", " + lectura3 + ", " + lectura4 + ", " + promedio + ", '" + claveColecta + "')");
                lectura1 = null;
                lectura2 = null;
                lectura3 = null;
                lectura4 = null;
                this.baseDatosLocal.commit();
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
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer muestrasPerfilID = rs.getInt("MuestrasPerfilID");
                Integer sitioID = rs.getInt("SitioID");
                Integer upmid = extraerUPM(sitioID, ruta);
                Integer gradosLatitud = rs.getInt("GradosLatitud");
                Integer minutosLatitud = rs.getInt("MinutosLatitud");
                Float segundosLatitud = rs.getFloat("SegundosLatitud");
                Integer gradosLongitud = rs.getInt("GradosLongitud");
                Integer minutosLongitud = rs.getInt("MinutosLongitud");
                Float segundosLongitud = rs.getFloat("SegundosLongitud");
                Float elevacion = rs.getFloat("Elevacion");
                Float diametroInterno = rs.getFloat("DiametroInterno");
                Float diametroExterno = rs.getFloat("DiametroExterno");
                Float altura = rs.getFloat("Altura");
                String observaciones = rs.getString("Observaciones");
                ps.executeUpdate("INSERT INTO SUELO_MuestrasPerfil(SitioID,UPMID,MuestrasPerfilID, GradosLatitud, MinutosLatitud, SegundosLatitud, GradosLongitud, MinutosLongitud, SegundosLongitud, Elevacion, "
                        + "DiametroInterno, DiametroExterno, Altura, Observaciones)VALUES(" + sitioID + ", " + upmid + ", " + muestrasPerfilID + ", " + gradosLatitud + ", " + minutosLatitud + ", " + segundosLatitud + ", " + gradosLongitud
                        + ", " + minutosLongitud + ", " + segundosLongitud + ", " + elevacion + ", " + diametroInterno + ", " + diametroExterno + ", " + altura + ", '" + observaciones + "')");
                this.baseDatosLocal.commit();
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
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer pavimentoErosionID = rs.getInt("PavimentoErosionID");
                Integer sitioID = rs.getInt("SitioID");
                Integer upmid = extraerUPM(sitioID, ruta);
                Integer numero = rs.getInt("Numero");
                Float diametro = rs.getFloat("Diametro");
                ps.executeUpdate("INSERT INTO SUELO_PavimentoErosion(SitioID,UPMID,PavimentoErosionID,  Numero, Diametro)VALUES(" + sitioID + ", " + upmid + ", " + pavimentoErosionID + ", " + numero + ", " + diametro + ")");
                this.baseDatosLocal.commit();
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
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer pedestalID = rs.getInt("PedestalID");
                Integer sitioID = rs.getInt("SitioID");
                Integer upmid = extraerUPM(sitioID, ruta);
                Integer numero = rs.getInt("Numero");
                Float altura = rs.getFloat("Altura");
                ps.executeUpdate("INSERT INTO SUELO_Pedestal(SitioID,UPMID,PedestalID, Numero, Altura)VALUES(" + sitioID + ", " + upmid + ", " + pedestalID + ", " + numero + ", " + altura + ")");
                this.baseDatosLocal.commit();
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
        this.querySelect = "SELECT ProfundidadSueloID, SitioID, Punto, Profundidad030, Profundidad3060, PesoTotal030, PesoTotal3060, Equipo030, Equipo3060, "
                + "Observaciones FROM SUELO_Profundidad";
        Float profundidad3060 = null;
        Float pesoTotal3060 = null;
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer profundidadSueloID = rs.getInt("ProfundidadSueloID");
                Integer sitioID = rs.getInt("SitioID");
                Integer upmid = extraerUPM(sitioID, ruta);
                Integer punto = rs.getInt("Punto");
                Float profundidad030 = rs.getFloat("Profundidad030");
                if (rs.getObject("Profundidad3060") != null) {
                    profundidad3060 = rs.getFloat("Profundidad3060");
                }
                Float pesoTotal030 = rs.getFloat("PesoTotal030");
                if (rs.getObject("PesoTotal3060") != null) {
                    pesoTotal3060 = rs.getFloat("PesoTotal3060");
                }
                Float equipo030 = rs.getFloat("Equipo030");
                Float equipo3060 = rs.getFloat("Equipo3060");
                String observaciones = rs.getString("Observaciones");
                ps.executeUpdate("INSERT INTO SUELO_Profundidad(SitioID,UPMID,ProfundidadSueloID,  Punto, Profundidad030, Profundidad3060, PesoTotal030, PesoTotal3060, Equipo030, Equipo3060, "
                        + "Observaciones)VALUES(" + sitioID + ", " + upmid + ", " + profundidadSueloID + ", " + punto + ", " + profundidad030 + ", " + profundidad3060 + ", " + pesoTotal030 + ", " + pesoTotal3060 + ", '" + equipo030 + "', '" + equipo3060 + "', '" + observaciones + "')");
                profundidad3060 = null;
                pesoTotal3060 = null;
                this.baseDatosLocal.commit();
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
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer sueloID = rs.getInt("SueloID");
                Integer sitioID = rs.getInt("SitioID");
                Integer upmid = extraerUPM(sitioID, ruta);
                Integer usoSueloID = rs.getInt("UsoSueloID");
                String otroUsoSuelo = rs.getString("OtroUsoSuelo");
                Float espesor = rs.getFloat("Espesor");
                Integer pendienteDominante = rs.getInt("PendienteDominante");
                String observaciones = rs.getString("Observaciones");
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
                ps.executeUpdate("INSERT INTO SUELO_Suelo(SitioID,UPMID,SueloID, UsoSueloID, OtroUsoSuelo, Espesor, PendienteDominante, Observaciones, NumeroCanalillos, ProfundidadPromedioCanalillos, "
                        + "AnchoPromedioCanalillos, LongitudCanalillos, VolumenCanalillos, NumeroCarcavas, ProfundidadPromedioCarcavas, AnchoPromedioCarcavas, LongitudCarcavas, VolumenCarcavas, "
                        + "NumeroMonticulos, AlturaPromedioMonticulos, AnchoPromedioMonticulos, LongitudPromedioMonticulos, VolumenMonticulos)"
                        + "VALUES(" + sitioID + ", " + upmid + ", " + sueloID + ", " + usoSueloID + ", '" + otroUsoSuelo + "', " + espesor + ", " + pendienteDominante + ", '" + observaciones + "', " + numeroCanalillos
                        + ", " + profundidadPromedioCanalillos + ", " + anchoPromedioCanalillos + ", " + longitudCanalillos + ", " + volumenCanalillos + ", " + numeroCarcavas + ", " + profundidadPromedioCarcavas
                        + ", " + anchoPromedioCarcavas + ", " + longitudPromedioCarcavas + ", " + volumenCarcavas + ", " + numeroMonticulos + ", " + alturaPromedioMonticulos + ", " + anchoPromedioMoticulos
                        + ", " + longitudPromedioMonticulos + ", " + volumenMonticulos + ")");
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
                this.baseDatosLocal.commit();
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
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer varillaID = rs.getInt("VarillaID");
                Integer sitioID = rs.getInt("SitioID");
                Integer upmid = extraerUPM(sitioID, ruta);
                Integer noVarilla = rs.getInt("NoVarilla");
                Integer azimut = rs.getInt("Azimut");
                Float distancia = rs.getFloat("Distancia");
                Float profundidad = rs.getFloat("Profundidad");
                ps.executeUpdate("INSERT INTO SUELO_VarillaErosion(SitioID,UPMID,VarillaID, NoVarilla, Azimut, Distancia, Profundidad)"
                        + "VALUES(" + sitioID + ", " + upmid + ", " + varillaID + ", " + noVarilla + ", " + azimut + ", " + distancia + ", " + profundidad + ")");
                this.baseDatosLocal.commit();
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
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer coberturaDoselID = rs.getInt("CoberturaDoselID");
                Integer sitioID = rs.getInt("SitioID");
                Integer upmid = extraerUPM(sitioID, ruta);
                Integer transecto = rs.getInt("Transecto");
                Integer punto1 = rs.getInt("Punto1");
                Integer punto2 = rs.getInt("Punto2");
                Integer punto3 = rs.getInt("Punto3");
                Integer punto4 = rs.getInt("Punto4");
                Integer punto5 = rs.getInt("Punto5");
                Integer punto6 = rs.getInt("Punto6");
                Integer punto7 = rs.getInt("Punto7");
                Integer punto8 = rs.getInt("Punto8");
                Integer punto9 = rs.getInt("Punto9");
                Integer punto10 = rs.getInt("Punto10");
                ps.executeUpdate("INSERT INTO CARBONO_CoberturaDosel(SitioID,UPMID,CoberturaDoselID, Transecto, Punto1, Punto2, Punto3, Punto4, Punto5, Punto6, Punto7, "
                        + "Punto8, Punto9, Punto10 )VALUES(" + sitioID + ", " + upmid + ", " + coberturaDoselID + ", " + transecto + ", " + punto1 + ", " + punto2 + ", " + punto3 + ", " + punto4
                        + ", " + punto5 + ", " + punto6 + ", " + punto7 + ", " + punto8 + ", " + punto9 + ", " + punto10 + ")");
                this.baseDatosLocal.commit();
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
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer cubiertaVegetalID = rs.getInt("CubiertaVegetalID");
                Integer sitioID = rs.getInt("SitioID");
                Integer upmid = extraerUPM(sitioID, ruta);
                Integer transecto = rs.getInt("Transecto");
                Integer componenteID = rs.getInt("ComponenteID");
                Float altura5 = rs.getFloat("Altura5");
                Float altura10 = rs.getFloat("Altura10");
                ps.executeUpdate("INSERT INTO CARBONO_CubiertaVegetal(SitioID,UPMID,CubiertaVegetalID,  Transecto, ComponenteID, Altura5, Altura10)"
                        + "VALUES(" + sitioID + ", " + upmid + ", " + cubiertaVegetalID + ", " + transecto + ", " + componenteID + ", " + altura5 + ", " + altura10 + ")");
                this.baseDatosLocal.commit();
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
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer longitudComponenteID = rs.getInt("LongitudComponenteID");
                Integer sitioID = rs.getInt("SitioID");
                Integer upmid = extraerUPM(sitioID, ruta);
                Integer consecutivo = rs.getInt("Consecutivo");
                Integer transecto = rs.getInt("Transecto");
                Integer componenteID = rs.getInt("ComponenteID");
                Integer familiaID = rs.getInt("FamiliaID");
                Integer generoID = rs.getInt("GeneroID");
                Integer especieID = rs.getInt("EspecieID");
                Integer infraespecieID = rs.getInt("InfraespecieID");
                String nombreComun = rs.getString("NombreComun");
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
                Integer total = rs.getInt("Total");
                String claveColecta = rs.getString("ClaveColecta");
                ps.executeUpdate("INSERT INTO CARBONO_LongitudComponente(SitioID,UPMID,LongitudComponenteID,  Consecutivo, Transecto, ComponenteID, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, Segmento1, Segmento2, Segmento3, Segmento4, "
                        + "Segmento5, Segmento6, Segmento7, Segmento8, Segmento9, Segmento10, Total, ClaveColecta)VALUES(" + sitioID + ", " + upmid + ", " + longitudComponenteID + ", " + consecutivo + ", " + transecto + ", " + componenteID + ", " + familiaID + ", " + generoID
                        + ", " + especieID + ", " + infraespecieID + ", '" + nombreComun + "', " + segmento1 + ", " + segmento2 + ", " + segmento3 + ", " + segmento4 + ", " + segmento5 + ", " + segmento6 + ", " + segmento7 + ", " + segmento8
                        + ", " + segmento9 + ", " + segmento10 + ", " + total + ", '" + claveColecta + "')");
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
                this.baseDatosLocal.commit();
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
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer materialLenioso100ID = rs.getInt("MaterialLenioso100ID");
                Integer sitioID = rs.getInt("SitioID");
                Integer upmid = extraerUPM(sitioID, ruta);
                Integer transecto = rs.getInt("Transecto");
                Integer pendiente = rs.getInt("Pendiente");
                Integer unaHora = rs.getInt("UnaHora");
                Integer diezhoras = rs.getInt("DiezHoras");
                Integer cienHoras = rs.getInt("CienHoras");
                ps.executeUpdate("INSERT INTO CARBONO_MaterialLenioso100(SitioID,UPMID,MaterialLenioso100ID, Transecto, Pendiente, UnaHora, DiezHoras, CienHoras)VALUES"
                        + "(" + sitioID + ", " + upmid + ", " + materialLenioso100ID + ", " + transecto + ", " + pendiente + ", " + unaHora + ", " + diezhoras + ", " + cienHoras + ")");
                this.baseDatosLocal.commit();
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
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer materialLenioso1000ID = rs.getInt("MaterialLenioso1000ID");
                Integer sitioID = rs.getInt("SitioID");
                Integer upmid = extraerUPM(sitioID, ruta);
                Integer transecto = rs.getInt("Transecto");
                Float diametro = rs.getFloat("Diametro");
                Integer grados = rs.getInt("Grado");
                ps.executeUpdate("INSERT INTO CARBONO_MaterialLenioso1000(SitioID,UPMID,MaterialLenioso1000ID, Transecto, Diametro, Grado)VALUES(" + sitioID + ", " + upmid + ", " + materialLenioso1000ID + ", " + transecto + ", " + diametro + ", " + grados + ")");
                this.baseDatosLocal.commit();
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
        this.querySelect = "SELECT ArboladoID, SitioID, Consecutivo, NoIndividuo, NoRama, Azimut, Distancia, FamiliaID, GeneroID, EspecieID, InfraespecieID, "
                + "NombreComun, EsSubmuestra, FormaVidaID, FormaFusteID, CondicionID, MuertoPieID, GradoPutrefaccionID, TipoToconID, DiametroNormal, "
                + "DiametroBasal, AlturaTotal, AnguloInclinacion, AlturaFusteLimpio, AlturaComercial, DiametroCopaNS, DiametroCopaEO, ProporcionCopaVivaID, ExposicionCopaID, "
                + "PosicionCopaID, DensidadCopaID, MuerteRegresivaID, TransparenciaFollajeID, VigorID, NivelVigorID, ClaveColecta FROM TAXONOMIA_Arbolado";
        Float diametroNormal = null;
        Integer diametroBasal = null;
        Float alturaTotal = null;
        Float anguloInclinacion = null;
        Float alturaFusteLimpio = null;
        Float alturaComercial = null;
        Float diametroCopaNS = null;
        Float diametroCopaEO = null;
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer arboladoID = rs.getInt("ArboladoID");
                Integer sitioID = rs.getInt("SitioID");
                Integer upmid = extraerUPM(sitioID, ruta);
                Integer consecutivo = rs.getInt("Consecutivo");
                Integer noIndividuo = rs.getInt("NoIndividuo");
                Integer noRama = rs.getInt("NoRama");
                Integer azimut = rs.getInt("Azimut");
                Float distancia = rs.getFloat("Distancia");
                Integer familiaID = rs.getInt("FamiliaID");
                Integer generoID = rs.getInt("GeneroID");
                Integer especieID = rs.getInt("EspecieID");
                Integer infraespecieID = rs.getInt("InfraespecieID");
                String nombreComun = rs.getString("NombreComun");
                Integer esSubmuestra = rs.getInt("EsSubmuestra");
                Integer formaVidaID = rs.getInt("FormaVidaID");
                Integer formaFusteID = rs.getInt("FormaFusteID");
                Integer condicionID = rs.getInt("CondicionID");
                Integer muertoPieID = rs.getInt("MuertoPieID");
                Integer gradoPutrefaccionID = rs.getInt("GradoPutrefaccionID");
                Integer tipoToconID = rs.getInt("TipoToconID");
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
                Integer proporcionCopaVivaID = rs.getInt("ProporcionCopaVivaID");
                Integer exposicionCopaID = rs.getInt("ExposicionCopaID");
                Integer posicionCopaID = rs.getInt("PosicionCopaID");
                Integer densidadCopaID = rs.getInt("DensidadCopaID");
                Integer muerteRegresivaID = rs.getInt("MuerteRegresivaID");
                Integer transparenciaFollajeID = rs.getInt("TransparenciaFollajeID");
                Integer vigorID = rs.getInt("VigorID");
                Integer nivelVigorID=rs.getInt("NivelVigorID");
                System.out.println("\t\t\tNivelVigorID===="+nivelVigorID);
                String claveColecta = rs.getString("ClaveColecta");
                ps.executeUpdate("INSERT INTO TAXONOMIA_Arbolado(SitioID,UPMID,ArboladoID, Consecutivo, NoIndividuo, NoRama, Azimut, Distancia, FamiliaID, GeneroID, EspecieID, InfraespecieID, "
                        + "NombreComun, EsSubmuestra, FormaVidaID, FormaFusteID, CondicionID, MuertoPieID, GradoPutrefaccionID, TipoToconID, DiametroNormal, "
                        + "DiametroBasal, AlturaTotal, AnguloInclinacion, AlturaFusteLimpio, AlturaComercial, DiametroCopaNS, DiametroCopaEO, ProporcionCopaVivaID, ExposicionCopaID, "
                        + "PosicionCopaID, DensidadCopaID, MuerteRegresivaID, TransparenciaFollajeID, VigorID, NivelVigorID, ClaveColecta)VALUES(" + sitioID + ", " + upmid + ", " + arboladoID + ", " + consecutivo + ", " + noIndividuo
                        + ", " + noRama + ", " + azimut + ", " + distancia + ", " + familiaID + ", " + generoID + ", " + especieID + ", " + infraespecieID + ", '" + nombreComun + "'"
                        + ", " + esSubmuestra + ", " + formaVidaID + ", " + formaFusteID + ", " + condicionID + ", " + muertoPieID + ", " + gradoPutrefaccionID + ", " + tipoToconID
                        + ", " + diametroNormal + ", " + diametroBasal + ", " + alturaTotal + ", " + anguloInclinacion + ", " + alturaFusteLimpio + ", " + alturaComercial + ", " + diametroCopaNS
                        + ", " + diametroCopaEO + ", " + proporcionCopaVivaID + ", " + exposicionCopaID + ", " + posicionCopaID + ", " + densidadCopaID + ", " + muerteRegresivaID + ", " + transparenciaFollajeID
                        + ", " + vigorID +", "+nivelVigorID+ ", '" + claveColecta + "')");
                diametroNormal = null;
                diametroBasal = null;
                alturaTotal = null;
                anguloInclinacion = null;
                alturaFusteLimpio = null;
                alturaComercial = null;
                diametroCopaNS = null;
                diametroCopaEO = null;
                this.baseDatosLocal.commit();
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
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer submuestraID = rs.getInt("SubmuestraID");
                Integer sitioID = rs.getInt("SitioID");
                Integer upmid = extraerUPM(sitioID, ruta);
                Integer arboladoID = rs.getInt("ArboladoID");
                Float diametroBasal = rs.getFloat("DiametroBasal");
                Integer edad = rs.getInt("Edad");
                Integer numeroAnillos25 = rs.getInt("NumeroAnillos25");
                Float longitudAnillos10 = rs.getFloat("LongitudAnillos10");
                Float grozorCorteza = rs.getFloat("GrozorCorteza");
                ps.executeUpdate("INSERT INTO ARBOLADO_Submuestra(SitioID,UPMID,SubmuestraID,  ArboladoID, DiametroBasal, Edad, NumeroAnillos25, LongitudAnillos10, GrozorCorteza)VALUES(" + sitioID + ", " + upmid + ", " + submuestraID + ", " + arboladoID + ", " + diametroBasal + ", " + edad + ", " + numeroAnillos25 + ", " + longitudAnillos10 + ", " + grozorCorteza + ")");
                this.baseDatosLocal.commit();
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
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer trozaID = rs.getInt("TrozaID");
                Integer submuestraID = rs.getInt("SubmuestraID");
                Integer sitioID=extraerSitioARBOLADOSubmuestra(submuestraID, ruta);
                Integer upmID=extraerUPM(sitioID, ruta);
                Integer noTroza = rs.getInt("NoTroza");
                Integer tipoTrozaID = rs.getInt("TipoTrozaID");
                ps.executeUpdate("INSERT INTO ARBOLADO_Troza(SitioID,UPMID,TrozaID, SubmuestraID, NoTroza, TipoTrozaID)VALUES("+sitioID+ ", " +upmID+ ", " + trozaID + ", " + submuestraID + ", " + noTroza + ", " + tipoTrozaID + ")");
                this.baseDatosLocal.commit();
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
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer submuestraObservacionesID = rs.getInt("SubmuestraObservacionesI");
                Integer sitioID = rs.getInt("SitioID");
                Integer upmid = extraerUPM(sitioID, ruta);
                String observaciones = rs.getString("Observaciones");
                ps.executeUpdate("INSERT INTO SUBMUESTRA_Observaciones(SitioID,UPMID,SubmuestraObservacionesID,  Observaciones)VALUES(" + sitioID + ", " + upmid + ", " + submuestraObservacionesID + ", '" + observaciones + ")");
                this.baseDatosLocal.commit();
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
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer danioSeveridadID = rs.getInt("DanioSeveridadID");
                Integer arbolID = rs.getInt("ArboladoID");
                Integer sitioID=extraerSitioARBOLADODanioSeveridad(arbolID, ruta);
                Integer upmID=extraerUPM(sitioID, ruta);
                Integer noDanio = rs.getInt("NumeroDanio");
                Integer agenteDanioID = rs.getInt("AgenteDanioID");
                Integer severidadID = rs.getInt("SeveridadID");
                ps.executeUpdate("INSERT INTO ARBOLADO_DanioSeveridad(SitioID,UPMID,DanioSeveridadID, ArboladoID, NumeroDanio, AgenteDanioID, SeveridadID)VALUES("+sitioID+ ", " +upmID+ ", " + danioSeveridadID + ", " + arbolID + ", "
                        + noDanio + ", " + agenteDanioID + ", " + severidadID + ")");
                this.baseDatosLocal.commit();
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
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer colectaBotanicaID = rs.getInt("ColectaBotanicaID");
                Integer UPMID = rs.getInt("UPMID");
                Integer familiaID = rs.getInt("FamiliaID");
                Integer generoID = rs.getInt("GeneroID");
                Integer especieID = rs.getInt("EspecieID");
                String infraespecieID = rs.getString("InfraespecieID");
                String nombreComun = rs.getString("NombreComun");
                Integer sitio = rs.getInt("Sitio");
                Integer seccionID = rs.getInt("SeccionID");
                Integer consecutivo = rs.getInt("Consecutivo");
                String claveColecta = rs.getString("ClaveColecta");
                Integer contraFuertes = rs.getInt("ContraFuertes");
                Integer exudado = rs.getInt("Exudado");
                String indicarExudado = rs.getString("IndicarExudado");
                Integer color = rs.getInt("Color");
                String indicarColor = rs.getString("IndicarColor");
                Integer cambioColor = rs.getInt("CambioColor");
                Integer aceitesVolatiles = rs.getInt("AceitesVolatiles");
                Integer colorFlor = rs.getInt("ColorFlor");
                String indicarColorFlor = rs.getString("IndicarColorFlor");
                Integer hojasTejidoVivo = rs.getInt("HojasTejidoVivo");
                Integer fotoFlor = rs.getInt("FotoFlor");
                Integer fotoFruto = rs.getInt("FotoFruto");
                Integer fotoHojasArriba = rs.getInt("FotoHojasArriba");
                Integer fotoHojasAbajo = rs.getInt("FotoHojasAbajo");
                Integer fotoArbolCompleto = rs.getInt("FotoArbolCompleto");
                Integer fotoCorteza = rs.getInt("FotoCorteza");
                Integer virutaIncluida = rs.getInt("VirutaIncluida");
                Integer cortezaIncluida = rs.getInt("CortezaIncluida");
                Integer maderaIncluida = rs.getInt("MaderaIncluida");
                String observaciones = rs.getString("Observaciones");
                ps.executeUpdate("INSERT INTO TAXONOMIA_ColectaBotanica(ColectaBotanicaID, UPMID, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, Sitio, SeccionID, Consecutivo, ClaveColecta, ContraFuertes, Exudado, IndicarExudado, Color, IndicarColor, CambioColor, AceitesVolatiles, ColorFlor, IndicarColorFlor, HojasTejidoVivo, FotoFlor, FotoFruto, FotoHojasArriba, FotoHojasAbajo, FotoArbolCompleto, FotoCorteza, "
                        + "VirutaIncluida, CortezaIncluida, MaderaIncluida, Observaciones)VALUES(" + colectaBotanicaID + ", " + UPMID + ", " + familiaID + ", " + generoID + ", " + especieID + ", " + infraespecieID + ", '" + nombreComun + "'," + sitio + ", " + seccionID + ", " + consecutivo + ", '" + claveColecta + "', " + contraFuertes + ", " + exudado + ", '" + indicarExudado + "', " + color + ", '" + indicarColor + "'"
                        + ", " + cambioColor + ", " + aceitesVolatiles + ", " + colorFlor + ", '" + indicarColorFlor + "', " + hojasTejidoVivo + ", " + fotoFlor + ", " + fotoFruto + ", " + fotoHojasArriba + ", " + fotoHojasAbajo + ", " + fotoArbolCompleto + ", " + fotoCorteza + ", " + virutaIncluida + ", " + cortezaIncluida + ", " + maderaIncluida
                        + ", '" + observaciones + "')");
                this.baseDatosLocal.commit();
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
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer repobladoID = rs.getInt("RepobladoID");
                Integer sitioID = rs.getInt("SitioID");
                Integer upmid = extraerUPM(sitioID, ruta);
                Integer consecutivo = rs.getInt("Consecutivo");
                Integer familiaID = rs.getInt("FamiliaID");
                Integer generoID = rs.getInt("GeneroID");
                Integer especieID = rs.getInt("EspecieID");
                Integer infraespecieID = rs.getInt("InfraespecieID");
                String nombreComun = rs.getString("NombreComun");
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
                Integer vigorID = rs.getInt("VigorID");
                Integer danioID = rs.getInt("DanioID");
                if (rs.getObject("PorcentajeDanio") != null) {
                    porcentajeDanio = rs.getInt("PorcentajeDanio");
                }
                String claveColecta = rs.getString("ClaveColecta");
                ps.executeUpdate("INSERT INTO TAXONOMIA_Repoblado(SitioID,UPMID,RepobladoID,  Consecutivo, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, Frecuencia025150, Edad025150, Frecuencia151275, Edad151275, "
                        + "Frecuencia275, Edad275, VigorID, DanioID, PorcentajeDanio, ClaveColecta)VALUES(" + sitioID + ", " + upmid + ", " + repobladoID + ", " + consecutivo + ", " + familiaID + ", " + generoID + ", " + especieID + ", " + infraespecieID + ""
                        + ", '" + nombreComun + "', " + frecuencia025150 + ", " + edad025150 + ", " + frecuencia151275 + ", " + edad151275 + ", " + frecuencia275 + ", " + edad275 + ", " + vigorID + ", " + danioID
                        + ", " + porcentajeDanio + ", '" + claveColecta + "')");
                frecuencia025150 = null;
                edad025150 = null;
                frecuencia151275 = null;
                edad151275 = null;
                frecuencia275 = null;
                edad275 = null;
                porcentajeDanio = null;
                this.baseDatosLocal.commit();
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
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer repobladoVMID = rs.getInt("RepobladoVMID");
                Integer sitioID = rs.getInt("SitioID");
                Integer upmid = extraerUPM(sitioID, ruta);
                Integer consecutivo = rs.getInt("Consecutivo");
                Integer formaVidaID = rs.getInt("FormaVidaID");
                Integer familiaID = rs.getInt("FamiliaID");
                Integer generoID = rs.getInt("GeneroID");
                Integer especieID = rs.getInt("EspecieID");
                Integer infraespecieID = rs.getInt("InfraespecieID");
                String nombreComun = rs.getString("NombreComun");
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
                String claveColecta = rs.getString("ClaveColecta");
                ps.executeUpdate("INSERT INTO TAXONOMIA_RepobladoVM(SitioID,UPMID,RepobladoVMID,  Consecutivo, FormaVidaID, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, Frecuencia50, PorcentajeCobertura50, Frecuencia51200, PorcentajeCobertura51200, "
                        + "Frecuencia200, PorcentajeCobertura200, VigorID, ClaveColecta)VALUES(" + sitioID + ", " + upmid + ", " + repobladoVMID + ", " + consecutivo + ", " + formaVidaID + ", " + familiaID + ", " + generoID + ", " + especieID + ", " + infraespecieID + ", '" + nombreComun + "'"
                        + ", " + frecuencia50 + ", " + porcentajeCobertura50 + ", " + frecuencia51200 + ", " + porcentajeCobertura51200 + ", " + frecuencia200 + ", " + porcentajeCobertura200 + ", " + vigorID + ", '" + claveColecta + "')");
                frecuencia50 = null;
                porcentajeCobertura50 = null;
                frecuencia51200 = null;
                porcentajeCobertura51200 = null;
                frecuencia200 = null;
                porcentajeCobertura200 = null;
                this.baseDatosLocal.commit();
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
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer sotoBosqueID = rs.getInt("SotoBosqueID");
                Integer sitioID = rs.getInt("SitioID");
                Integer upmid = extraerUPM(sitioID, ruta);
                Integer consecutivo = rs.getInt("Consecutivo");
                Integer familiaID = rs.getInt("FamiliaID");
                Integer generoID = rs.getInt("GeneroID");
                Integer especieID = rs.getInt("EspecieID");
                Integer infraespecieID = rs.getInt("InfraespecieID");
                String nombreComun = rs.getString("NombreComun");
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
                Integer vigorID = rs.getInt("VigorID");
                Integer danioID = rs.getInt("DanioID");
                if (rs.getObject("PorcentajeDanio") != null) {
                    porcentajeDanio = rs.getInt("PorcentajeDanio");
                }
                String claveColecta = rs.getString("ClaveColecta");
                ps.executeUpdate("INSERT INTO TAXONOMIA_SotoBosque(SitioID,UPMID,SotoBosqueID, Consecutivo, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, Frecuencia025150, Cobertura025150, Frecuencia151275, Cobertura151275, Frecuencia275, Cobertura275, VigorID, DanioID, "
                        + "PorcentajeDanio, ClaveColecta)VALUES(" + sitioID + ", " + upmid + ", " + sotoBosqueID + ", " + consecutivo + ", " + familiaID + ", " + generoID + ", " + especieID + ", " + infraespecieID + ", '" + nombreComun + "', " + frecuencia025150 + ", " + cobertura025150 + ", " + frecuencia151275
                        + ", " + cobertura151275 + ", " + frecuencia275 + ", " + cobertura275 + ", " + vigorID + ", " + danioID + ", " + porcentajeDanio + ", '" + claveColecta + "')");
                frecuencia025150 = null;
                cobertura025150 = null;
                frecuencia151275 = null;
                cobertura151275 = null;
                frecuencia275 = null;
                cobertura275 = null;
                porcentajeDanio = null;
                this.baseDatosLocal.commit();
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
        Integer formaVidaID = null;
        Integer condicionID = null;
        Integer familiaID = null;
        Integer generoID = null;
        Integer especieID = null;
        Integer infraespecieID = null;
        Integer formaCrecimientoID = null;
        Integer densidadColoniaID = null;
        this.querySelect = "SELECT VegetacionMayorID, SitioID, Consecutivo, NoIndividuo, FormaVidaID, CondicionID, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, FormaCrecimientoID, DensidadColoniaID, AlturaTotalMaxima, AlturaTotalMedia, AlturaTotalMinima, DiametroCoberturaMayor, "
                + "DiametroCoberturaMenor, VigorID, ClaveColecta FROM TAXONOMIA_VegetacionMayorGregarios";
        Float alturaTotalMaxima = null;
        Float alturaTotalMedia = null;
        Float alturaTotalMinima = null;
        Float diametroCoberturaMayor = null;
        Float diametroCoberturaMenor = null;
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer vegetacionMayorID = rs.getInt("VegetacionMayorID");
                Integer sitioID = rs.getInt("SitioID");
                Integer upmid = extraerUPM(sitioID, ruta);
                Integer consecutivo = rs.getInt("Consecutivo");
                Integer noIndividuo = rs.getInt("NoIndividuo");
                if (rs.getObject("FormaVidaID") != null) {
                    formaVidaID = rs.getInt("FormaVidaID");
                }
                if (rs.getObject("CondicionID") != null) {
                    condicionID = rs.getInt("CondicionID");
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
                String nombreComun = rs.getString("NombreComun");
                if (rs.getObject("FormaCrecimientoID") != null) {
                    formaCrecimientoID = rs.getInt("FormaCrecimientoID");
                }
                if (rs.getObject("DensidadColoniaID") != null) {
                    densidadColoniaID = rs.getInt("DensidadColoniaID");
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
                String claveColecta = rs.getString("ClaveColecta");
                ps.executeUpdate("INSERT INTO TAXONOMIA_VegetacionMayorGregarios(SitioID,UPMID,VegetacionMayorID, Consecutivo, NoIndividuo, FormaVidaID, CondicionID, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, FormaCrecimientoID, DensidadColoniaID, AlturaTotalMaxima, AlturaTotalMedia, AlturaTotalMinima, DiametroCoberturaMayor, "
                        + "DiametroCoberturaMenor, VigorID, ClaveColecta)VALUES(" + sitioID + ", " + upmid + ", " + vegetacionMayorID + ", " + consecutivo + ", " + noIndividuo + ", " + formaVidaID + ", " + condicionID + ", " + familiaID + ", " + generoID + ", " + especieID + ", " + infraespecieID + ", '" + nombreComun + "', " + formaCrecimientoID + ", " + densidadColoniaID
                        + ", " + alturaTotalMaxima + ", " + alturaTotalMedia + ", " + alturaTotalMinima + ", " + diametroCoberturaMayor + ", " + diametroCoberturaMenor + ", " + vigorID + ", '" + claveColecta + "')");
                formaVidaID = null;
                condicionID = null;
                familiaID = null;
                generoID = null;
                especieID = null;
                infraespecieID = null;
                formaCrecimientoID = null;
                densidadColoniaID = null;
                alturaTotalMaxima = null;
                alturaTotalMedia = null;
                alturaTotalMinima = null;
                diametroCoberturaMayor = null;
                diametroCoberturaMenor = null;
                this.baseDatosLocal.commit();
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
        Integer numeroDanio = null;
        Integer agenteDanioID = null;
        Integer severidadID = null;
        this.querySelect = "SELECT DanioSeveridadID, VegetacionMayorID, NumeroDanio, AgenteDanioID, SeveridadID FROM VEGETACIONMAYORG_DanioSeveridad";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {

                Integer vegetacionMayorGregariosID = rs.getInt("VegetacionMayorID");
                Integer sitioID = extraerSitioIDVegetacionMayorGregarios(vegetacionMayorGregariosID, ruta);
                Integer upmID = extraerUPM(sitioID, ruta);
                Integer danioSeveridadID = rs.getInt("DanioSeveridadID");
                if (rs.getObject("NumeroDanio") != null) {
                    numeroDanio = rs.getInt("NumeroDanio");
                }
                if (rs.getObject("AgenteDanioID") != null) {
                    agenteDanioID = rs.getInt("AgenteDanioID");
                }
                if (rs.getObject("SeveridadID") != null) {
                    severidadID = rs.getInt("SeveridadID");
                }
                ps.executeUpdate("INSERT INTO VEGETACIONMAYORG_DanioSeveridad(SitioID,UPMID, DanioSeveridadID, VegetacionMayorID, NumeroDanio, AgenteDanioID, SeveridadID)VALUES(" + sitioID + "," + upmID + "," + danioSeveridadID + ", "
                        + vegetacionMayorGregariosID + ", " + numeroDanio + ", " + agenteDanioID + ", " + severidadID + ")");
                numeroDanio = null;
                agenteDanioID = null;
                severidadID = null;
                this.baseDatosLocal.commit();
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
        Integer formaVidaID = null;
        Integer condicionID = null;
        Integer familiaID = null;
        Integer generoID = null;
        Integer especieID = null;
        Integer formaGeometricaID = null;
        Integer densidadFollajeID = null;
        this.querySelect = "SELECT VegetacionMayorID, SitioID, Consecutivo, NoIndividuo, FormaVidaID, CondicionID, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, FormaGeometricaID, DensidadFollajeID, DiametroBase, AlturaTotal, DiametroCoberturaMayor, DiametroCoberturaMenor, VigorID, ClaveColecta FROM TAXONOMIA_VegetacionMayorIndividual";
        Float diametroBase = null;
        Float alturaTotal = null;
        Float diametroCoberturaMayor = null;
        Float diametroCoberturaMenor = null;
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer vegetacionMayorID = rs.getInt("VegetacionMayorID");
                Integer sitioID = rs.getInt("SitioID");
                Integer upmid = extraerUPM(sitioID, ruta);
                Integer consecutivo = rs.getInt("Consecutivo");
                Integer noIndividuo = rs.getInt("NoIndividuo");
                if (rs.getObject("FormaVidaID") != null) {
                    formaVidaID = rs.getInt("FormaVidaID");
                }
                if (rs.getObject("CondicionID") != null) {
                    condicionID = rs.getInt("CondicionID");
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
                String infraespecieID = rs.getString("InfraespecieID");
                String nombreComun = rs.getString("NombreComun");
                if (rs.getObject("FormaGeometricaID") != null) {
                    formaGeometricaID = rs.getInt("FormaGeometricaID");
                }
                if (rs.getObject("DensidadFollajeID") != null) {
                    densidadFollajeID = rs.getInt("DensidadFollajeID");
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
                Integer vigorID = rs.getInt("VigorID");
                String claveColecta = rs.getString("ClaveColecta");
                ps.executeUpdate("INSERT INTO TAXONOMIA_VegetacionMayorIndividual(SitioID,UPMID,VegetacionMayorID, Consecutivo, NoIndividuo, FormaVidaID, CondicionID, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, FormaGeometricaID, DensidadFollajeID, DiametroBase, AlturaTotal, DiametroCoberturaMayor, DiametroCoberturaMenor, VigorID, ClaveColecta)"
                        + "VALUES(" + sitioID + ", " + upmid + ", " + vegetacionMayorID + ", " + consecutivo + ", " + noIndividuo + ", " + formaVidaID + ", " + condicionID + ", " + familiaID + ", " + generoID + ", " + especieID + ", " + infraespecieID + ", '" + nombreComun + "', " + formaGeometricaID + ", " + densidadFollajeID + ", " + diametroBase + ",  " + alturaTotal
                        + ", " + diametroCoberturaMayor + ", " + diametroCoberturaMenor + ", " + vigorID + ", '" + claveColecta + "')");
                formaVidaID = null;
                condicionID = null;
                familiaID = null;
                generoID = null;
                especieID = null;
                formaGeometricaID = null;
                densidadFollajeID = null;
                diametroBase = null;
                alturaTotal = null;
                diametroCoberturaMayor = null;
                diametroCoberturaMenor = null;
                this.baseDatosLocal.commit();
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
        Integer numeroDanio = null;
        Integer agenteDanio = null;
        Integer severidadID = null;
        this.querySelect = "SELECT DanioSeveridadID, VegetacionMayorID, NumeroDanio, AgenteDanioID, SeveridadID FROM VEGETACIONMAYORI_DanioSeveridad";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer danioSeveridadID = rs.getInt("DanioSeveridadID");
                Integer vegetacionMayorIndividualID = rs.getInt("VegetacionMayorID");
                Integer sitioID = extraerSitioIDVegetacionMayorIndividual(vegetacionMayorIndividualID, ruta);
                Integer upmID = extraerUPM(sitioID, ruta);
                if (rs.getObject("NumeroDanio") != null) {
                    numeroDanio = rs.getInt("NumeroDanio");
                }
                if (rs.getObject("AgenteDanioID") != null) {
                    agenteDanio = rs.getInt("AgenteDanioID");
                }
                if (rs.getObject("SeveridadID") != null) {
                    severidadID = rs.getInt("SeveridadID");
                }
                ps.executeUpdate("INSERT INTO VEGETACIONMAYORI_DanioSeveridad(SitioID,UPMID,DanioSeveridadID, VegetacionMayorID, NumeroDanio, AgenteDanioID, SeveridadID)VALUES(" + sitioID + "," + upmID + "," + danioSeveridadID + ", "
                        + vegetacionMayorIndividualID + ", " + numeroDanio + ", " + agenteDanio + ", " + severidadID + ")");
                numeroDanio = null;
                agenteDanio = null;
                severidadID = null;
                this.baseDatosLocal.commit();
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
        Integer familiaID = null;
        Integer generoID = null;
        Integer especieID = null;
        Integer infraespecieID = null;
        Integer formaVidaID = null;
        Integer condicionID = null;
        Integer numero0110 = null;
        Integer numero1125 = null;
        Integer numero5175 = null;
        Integer numero76100 = null;
        Integer numero101125 = null;
        Integer numero126150 = null;
        Integer numero150 = null;
        Integer porcentajeCobertura = null;
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer vegetacionMenorID = rs.getInt("VegetacionMenorID");
                Integer sitioID = rs.getInt("SitioID");
                Integer upmid = extraerUPM(sitioID, ruta);
                Integer consecutivo = rs.getInt("Consecutivo");
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
                String nombreComun = rs.getString("NombreComun");
                if (rs.getObject("FormaVidaID") != null) {
                    formaVidaID = rs.getInt("FormaVidaID");
                }
                if (rs.getObject("CondicionID") != null) {
                    condicionID = rs.getInt("CondicionID");
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
                Integer vigorID = rs.getInt("VigorID");
                String claveColecta = rs.getString("ClaveColecta");
                ps.executeUpdate("INSERT INTO TAXONOMIA_VegetacionMenor(SitioID,UPMID,VegetacionMenorID,  Consecutivo, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, FormaVidaID, CondicionID, Numero0110, Numero1125, Numero5175, Numero76100, Numero101125, Numero126150, Numero150, PorcentajeCobertura, VigorID, ClaveColecta)VALUES(" + sitioID + ", " + upmid + ", " + vegetacionMenorID
                        + ", " + consecutivo + ", " + familiaID + ", " + generoID + ", " + especieID + ", " + infraespecieID + ", '" + nombreComun + "', " + formaVidaID + ", " + condicionID + ", " + numero0110 + ", " + numero1125 + ", " + numero5175 + ", " + numero76100 + ", " + numero101125
                        + ", " + numero126150 + ", " + numero150 + ", " + porcentajeCobertura + ", " + vigorID + ", '" + claveColecta + "')");
                familiaID = null;
                generoID = null;
                especieID = null;
                infraespecieID = null;
                formaVidaID = null;
                condicionID = null;
                numero0110 = null;
                numero1125 = null;
                numero5175 = null;
                numero76100 = null;
                numero101125 = null;
                numero126150 = null;
                numero150 = null;
                porcentajeCobertura = null;
                this.baseDatosLocal.commit();
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
        Integer numeroDanio = null;
        Integer agenteDanioID = null;
        Integer severidadID = null;
        this.querySelect = "SELECT DanioSeveridadVMID, VegetacionMenorID, NumeroDanio, AgenteDanioID, SeveridadID FROM VEGETACIONMENOR_DanioSeveridad";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer danioSeveridadVMID = rs.getInt("DanioSeveridadVMID");
                Integer vegetacionMenorID = rs.getInt("VegetacionMenorID");
                Integer sitioID = extraerSitioIDVegetacionMenor(vegetacionMenorID, ruta);
                Integer upmID = extraerUPM(sitioID, ruta);
                if (rs.getObject("NumeroDanio") != null) {
                    numeroDanio = rs.getInt("NumeroDanio");
                }
                if (rs.getObject("AgenteDanioID") != null) {
                    agenteDanioID = rs.getInt("AgenteDanioID");
                }
                if (rs.getObject("SeveridadID") != null) {
                    severidadID = rs.getInt("SeveridadID");
                }
                ps.executeUpdate("INSERT INTO VEGETACIONMENOR_DanioSeveridad(SitioID,UPMID,DanioSeveridadVMID, VegetacionMenorID, NumeroDanio, AgenteDanioID, SeveridadID)"
                        + "VALUES(" + sitioID + "," + upmID + "," + danioSeveridadVMID + ", " + vegetacionMenorID + ", " + numeroDanio + ", " + agenteDanioID + ", " + severidadID + ")");
                numeroDanio = null;
                agenteDanioID = null;
                severidadID = null;
                this.baseDatosLocal.commit();
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
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer repobladoDanioID = rs.getInt("RepobladoDanioID");
                Integer repobladoVMID = rs.getInt("RepobladoVMID");
                Integer numeroDanio = rs.getInt("NumeroDanio");
                Integer agenteDanioID = rs.getInt("AgenteDanioID");
                Integer severidadID = rs.getInt("SeveridadID");
                ps.executeUpdate("INSERT INTO REPOBLADO_AgenteDanio(RepobladoDanioID, RepobladoVMID, NumeroDanio, AgenteDanioID, SeveridadID)"
                        + "VALUES(" + repobladoDanioID + ", " + repobladoVMID + ", " + numeroDanio + ", " + agenteDanioID + ", " + severidadID + ")");
                this.baseDatosLocal.commit();
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
                + "TieneRadio, Canal, Frecuencia FROM UPM_Contacto";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer contactoID = rs.getInt("ContactoID");
                Integer upmID = rs.getInt("UPMID");
                Integer tipoContacto = rs.getInt("TipoContacto");
                String nombre = rs.getString("Nombre");
                String direccion = rs.getString("Direccion");
                Integer tipoTelefono = rs.getInt("TipoTelefono");
                String numeroTelefono = rs.getString("NumeroTelefono");
                Integer tieneCorreo = rs.getInt("TieneCorreo");
                String direccionCorreo = rs.getString("DireccionCorreo");
                Integer tieneRadio = rs.getInt("TieneRadio");
                String canal = rs.getString("Canal");
                String frecuencia = rs.getString("Frecuencia");

                ps.executeUpdate("INSERT INTO UPM_Contacto(ContactoID, UPMID, TipoContacto, Nombre, Direccion, TipoTelefono, NumeroTelefono, TieneCorreo, DireccionCorreo, "
                        + "TieneRadio, Canal, Frecuencia)VALUES(" + contactoID + ", " + upmID + ", " + tipoContacto + " , '" + nombre + "', '" + direccion + "', " + tipoTelefono
                        + ", '" + numeroTelefono + "', " + tieneCorreo + ", '" + direccionCorreo + "' , " + tieneRadio + ", '" + canal + "', '" + frecuencia + "')");
                this.baseDatosLocal.commit();
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
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer epifitaID = rs.getInt("EpifitaID");
                Integer upmID = rs.getInt("UPMID");
                Integer claseTipoID = rs.getInt("ClaseTipoID");
                Integer presenciaTroncosID = rs.getInt("PresenciaTroncosID");
                Integer presenciaRamasID = rs.getInt("PresenciaRamasID");
                ps.executeUpdate("INSERT INTO UPM_Epifita(EpifitaID, UPMID, ClaseTipoID, PresenciaTroncosID, PresenciaRamasID)VALUES(" + epifitaID + ", " + upmID + ", " + claseTipoID
                        + ", " + presenciaTroncosID + ", " + presenciaRamasID + ")");
                this.baseDatosLocal.commit();
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
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer secuenciaCapturaID = rs.getInt("SecuenciaCapturaID");
                Integer secuenciaID = rs.getInt("SecuenciaID");
                Integer UPMID = rs.getInt("UPMID");
                Integer sitio = rs.getInt("Sitio");
                Integer formatoID = rs.getInt("FormatoID");
                Integer estatus = rs.getInt("Estatus");
                ps.executeUpdate("INSERT INTO SYS_SecuenciaCaptura(SecuenciaCapturaID, SecuenciaID, UPMID, Sitio, FormatoID, Estatus)"
                        + "VALUES(" + secuenciaCapturaID + ", " + secuenciaID + ", " + UPMID + ", " + sitio + ", " + formatoID + ", " + estatus + ")");
                this.baseDatosLocal.commit();
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

    public void importarUPMRevision(String ruta) {
        this.querySelect = "SELECT RevisionID, UPMID, SitioID, Sitio, SecuenciaID FROM SYS_UPM_Revision";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer revisionID = rs.getInt("RevisionID");
                Integer UPMID = rs.getInt("UPMID");
                Integer sitioID = rs.getInt("SitioID");
                Integer sitio = rs.getInt("Sitio");
                Integer secuenciaID = rs.getInt("SecuenciaID");
                ps.executeUpdate("INSERT INTO SYS_UPM_Revision(RevisionID, UPMID, SitioID, Sitio, SecuenciaID)"
                        + "VALUES(" + revisionID + ", " + UPMID + ", " + sitioID + ", " + sitio + ", " + secuenciaID + ")");
                this.baseDatosLocal.commit();
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

    public int extraerSitioIDVegetacionMayorIndividual(int vegetacioMayorIID, String ruta) {
        int sitioID = 0;
        String query = "Select SitioID From TAXONOMIA_VegetacionMayorIndividual WHERE VegetacionMayorID=" + vegetacioMayorIID;
        //System.out.println(query);
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            ResultSet rs = sqlExterno.executeQuery(query);
            while (rs.next()) {
                sitioID = rs.getInt("SitioID");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error! no se encontó Sitio TAXONOMIA_VegetacionMayorIndividual", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return sitioID;
    }

    public int extraerSitioIDVegetacionMenor(int vegetacioMenorID, String ruta) {
        int sitioID = 0;
        String query = "Select SitioID From TAXONOMIA_VegetacionMenor WHERE VegetacionMenorID=" + vegetacioMenorID;
        //System.out.println(query);
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            ResultSet rs = sqlExterno.executeQuery(query);
            while (rs.next()) {
                sitioID = rs.getInt("SitioID");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error! no se encontó Sitio TAXONOMIA_VegetacionMenor", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return sitioID;
    }

    public int extraerSitioIDVegetacionMayorGregarios(int vegetacioMayorID, String ruta) {
        int sitioID = 0;
        String query = "Select SitioID From TAXONOMIA_VegetacionMayorGregarios WHERE VegetacionMayorID=" + vegetacioMayorID;
        //System.out.println(query);
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            ResultSet rs = sqlExterno.executeQuery(query);
            while (rs.next()) {
                sitioID = rs.getInt("SitioID");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error! no se encontó Sitio TAXONOMIA_VegetacionMayorGregarios", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return sitioID;
    }

    public int extraerSitioARBOLADODanioSeveridad(int arboladoID, String ruta) {
        int sitio = 0;
        String query = "Select SitioID From TAXONOMIA_Arbolado WHERE ArboladoID=" + arboladoID;
        //System.out.println(query);
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            ResultSet rs = sqlExterno.executeQuery(query);
            while (rs.next()) {
                sitio = rs.getInt("SitioID");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error! no se encontó SitioID De ARBOLADO_DanioSeveridad", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        return sitio;
    }
    
    public int extraerSitioARBOLADOSubmuestra(int submuestraID, String ruta) {
        int sitio = 0;
        String query = "Select SitioID From ARBOLADO_Submuestra WHERE SubmuestraID=" + submuestraID;
        //System.out.println(query);
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            ResultSet rs = sqlExterno.executeQuery(query);
            while (rs.next()) {
                sitio = rs.getInt("SitioID");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error! no se encontó SitioID De ARBOLADO_DanioSeveridad", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        return sitio;
    }

    public int extraerUPM(int sitioID, String ruta) {
        int upm = 0;
        String query = "Select UPMID From SITIOS_Sitio WHERE SitioID=" + sitioID;
        //System.out.println(query);
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            ResultSet rs = sqlExterno.executeQuery(query);
            while (rs.next()) {
                upm = rs.getInt("UPMID");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error! no se encontó UPM", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        return upm;
    }

    //54
    public void importarBrigadas(String ruta) {
        this.querySelect = "SELECT BrigadaID, UPMID, BrigadistaID, PuestoID, EmpresaID FROM UPM_Brigada";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer brigadaID = rs.getInt("BrigadaID");
                Integer UPMID = rs.getInt("UPMID");
                Integer brigadistaID = rs.getInt("BrigadistaID");
                Integer puestoID = rs.getInt("PuestoID");
                Integer empresaID = rs.getInt("EmpresaID");
                ps.executeUpdate("INSERT INTO UPM_Brigada(BrigadaID, UPMID, BrigadistaID, PuestoID, EmpresaID)"
                        + "VALUES(" + brigadaID + ", " + UPMID + ", " + brigadistaID + ", " + puestoID + ", " + empresaID + ")");
                this.baseDatosLocal.commit();
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
