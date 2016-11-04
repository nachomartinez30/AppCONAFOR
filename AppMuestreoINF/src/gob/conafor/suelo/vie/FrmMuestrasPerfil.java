package gob.conafor.suelo.vie;

import gob.conafor.sitio.mod.CESitio;
import gob.conafor.suelo.mod.CDMuestrasPerfil;
import gob.conafor.suelo.mod.CEMuestras;
import gob.conafor.suelo.mod.CEMuestrasPerfil;
import gob.conafor.suelo.mod.CatEProfundidadMuestras;
import gob.conafor.sys.mod.CDSecuencia;
import gob.conafor.upm.vie.UPMForms;
import gob.conafor.utils.Datos;
import gob.conafor.utils.FuncionesComunes;
import gob.conafor.utils.Tablas;
import gob.conafor.utils.ValidacionesComunes;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class FrmMuestrasPerfil extends javax.swing.JInternalFrame {

    private int upmID;
    private int sitioID;
    private int sitio;
    private static final int FORMATO_ID = 16;
    private CEMuestrasPerfil cePerfil = new CEMuestrasPerfil();
    private CDMuestrasPerfil cdPerfil = new CDMuestrasPerfil();
    private CESitio ceSitio = new CESitio();
    private Datos numeros = new Datos();
    private CDSecuencia cdSecuencia = new CDSecuencia();
    private int modificar;
    private FuncionesComunes funciones = new FuncionesComunes();
    private Integer gradosLatitud;
    private Integer minutosLatitud;
    private Float segundosLatitud;
    private Integer gradosLongitud;
    private Integer minutosLongitud;
    private Float segundosLongitud;
    private Float elevacion;
    private Float diametroInterno;
    private Float diametroExterno;
    private Float altura;
    private String observaciones;
    private Integer profundidadID;
    private Float pesoMuestra;
    private Float lectura1;
    private Float lectura2;
    private Float lectura3;
    private Float lectura4;
    private Float promedio;
    private String claveColecta;
    private ValidacionesComunes validacion = new ValidacionesComunes();
    private CEMuestras ceMuestras = new CEMuestras();
    
    public FrmMuestrasPerfil() {
        initComponents();
    }
    
    public void setDatosiniciales(CESitio ceSitio) {
        this.upmID = ceSitio.getUpmID();
        this.sitioID = ceSitio.getSitioID();
        this.sitio = ceSitio.getSitio();
        this.txtUPM.setText(String.valueOf(this.upmID));
        this.txtSitio.setText(String.valueOf(this.sitio));
        this.ceSitio.setSitioID(this.sitioID);
        this.ceSitio.setUpmID(this.upmID);
        this.ceSitio.setSitio(this.sitio);
        this.ceSitio = ceSitio;
        this.modificar = 0;
        reiniciarControlesMuestras();
        funciones.reiniciarTabla(grdMuestras);
        llenarTablaMuestras();
        funciones.reiniciarComboModel(this.cmbProfundidad);
        fillCmbProfundidad();
        funciones.manipularBotonesMenuPrincipal(true);
        txtGradosLatitud.requestFocus();
        reiniciarControlesMuestras();
        limpiarMuestrasPerfil();
    }

    public void revisarMuestrasPerfil(CESitio ceSitio) {
        this.upmID = ceSitio.getUpmID();
        this.sitioID = ceSitio.getSitioID();
        
        this.sitio = ceSitio.getSitio();
        this.txtUPM.setText(String.valueOf(this.upmID));
        this.txtSitio.setText(String.valueOf(this.sitio));
        this.ceSitio.setSitioID(this.sitioID);
        this.ceSitio.setUpmID(this.upmID);
        this.ceSitio.setSitio(this.sitio);
        this.cePerfil = cdPerfil.getDatosMuestrasPerfil(this.sitioID);
        txtGradosLatitud.setText(String.valueOf(cePerfil.getGradosLatitud()));
        txtMinutosLatitud.setText(String.valueOf(cePerfil.getMinutosLatitud()));
        txtSegundosLatitud.setText(String.valueOf(cePerfil.getSegundosLatitud()));
        txtGradosLongitud.setText(String.valueOf(cePerfil.getGradosLongitud()));
        txtMinutosLongitud.setText(String.valueOf(cePerfil.getMinutosLongitud()));
        txtSegundosLongitud.setText(String.valueOf(cePerfil.getSegundosLongitud()));
        txtElevacion.setText(String.valueOf(cePerfil.getElevacion()));
        txtDiametroInterno.setText(String.valueOf(cePerfil.getDiametroInterno()));
        txtDiametroExterno.setText(String.valueOf(cePerfil.getDiametroExterno()));
        txtAltura.setText(String.valueOf(cePerfil.getAltura()));
        this.txtObservaciones.setText(this.cePerfil.getObservaciones());
        this.modificar = 1;
        cmbProfundidad.setSelectedItem(null);
        funciones.reiniciarTabla(grdMuestras);
        llenarTablaMuestras();
        funciones.reiniciarComboModel(this.cmbProfundidad);
        fillCmbProfundidad();
        funciones.manipularBotonesMenuPrincipal(true);
        reiniciarControlesMuestras();
        txtGradosLatitud.requestFocus();
    }
    
    private void fillCmbProfundidad() {
        List<CatEProfundidadMuestras> listProfundidad = new ArrayList<>();
        listProfundidad = this.cdPerfil.getProfundidadMuestrasCapturadas(this.sitioID);
        if (listProfundidad != null) {
            int size = listProfundidad.size();
            for (int i = 0; i < size; i++) {
                cmbProfundidad.addItem(listProfundidad.get(i));
            }
        }
    }
    
    public void llenarTablaMuestras() {
        this.grdMuestras.setModel(this.cdPerfil.getTablaMuestras(this.sitioID));
        this.grdMuestras.getColumnModel().getColumn(2).setPreferredWidth(80);
        this.grdMuestras.getColumnModel().getColumn(3).setPreferredWidth(90);
        this.grdMuestras.getColumnModel().getColumn(4).setPreferredWidth(100);
        this.grdMuestras.getColumnModel().getColumn(5).setPreferredWidth(90);
        this.grdMuestras.getColumnModel().getColumn(6).setPreferredWidth(90);
        this.grdMuestras.getColumnModel().getColumn(7).setPreferredWidth(90);
        this.grdMuestras.getColumnModel().getColumn(8).setPreferredWidth(90);
        this.grdMuestras.getColumnModel().getColumn(9).setPreferredWidth(90);
        this.grdMuestras.getColumnModel().getColumn(10).setPreferredWidth(100);
        Tablas tabla = new Tablas();
        int[] column_0 = {0};
        int[] column_1 = {1};
        tabla.hideColumnTable(this.grdMuestras, column_0);
        tabla.hideColumnTable(this.grdMuestras, column_1);
    }
    
    private void fijarDatosMuestraPerfil() {
        try {
            this.gradosLatitud = Integer.valueOf(txtGradosLatitud.getText());
        } catch (Exception e) {
            this.gradosLatitud = null;
        }
        try {
            this.minutosLatitud = Integer.valueOf(txtMinutosLatitud.getText());
        } catch (Exception e) {
            this.minutosLatitud = null;
        }
        try {
            this.segundosLatitud = Float.valueOf(txtSegundosLatitud.getText());
        } catch (Exception e) {
            this.segundosLatitud = null;
        }
        try {
            this.gradosLongitud = Integer.valueOf(txtGradosLongitud.getText());
        } catch (Exception e) {
            this.gradosLatitud = null;
        }
        try {
            this.minutosLongitud = Integer.valueOf(txtMinutosLongitud.getText());
        } catch (Exception e) {
            this.minutosLongitud = null;
        }
        try {
            this.segundosLongitud = Float.valueOf(txtSegundosLongitud.getText());
        } catch (Exception e) {
            this.segundosLongitud = null;
        }
        try {
            this.elevacion = Float.valueOf(txtElevacion.getText());
        } catch (Exception e) {
            this.elevacion = null;
        }
        try {
            this.diametroInterno = Float.valueOf(txtDiametroInterno.getText());
        } catch (Exception e) {
            this.diametroInterno = null;
        }
        try {
            this.diametroExterno = Float.valueOf(txtDiametroInterno.getText());
        } catch (Exception e) {
            this.diametroExterno = null;
        }
        try {
            this.altura = Float.valueOf(txtAltura.getText());
        } catch (Exception e) {
            this.altura = null;
        }
        this.observaciones = txtObservaciones.getText();
    }

    private void fijarDatosMuestras() {
        try {
            this.pesoMuestra = Float.valueOf(txtPesoMuestra.getText());
        } catch (Exception e) {
            this.pesoMuestra = null;
        }
        try {
            this.lectura1 = Float.valueOf(txtLectura1.getText());
        } catch (Exception e) {
            this.lectura1 = null;
        }
        try {
            this.lectura2 = Float.valueOf(txtLectura2.getText());
        } catch (Exception e) {
            this.lectura2 = null;
        }
        try {
            this.lectura3 = Float.valueOf(txtLectura3.getText());
        } catch (Exception e) {
            this.lectura3 = null;
        }
        try {
            this.lectura4 = Float.valueOf(txtLectura4.getText());
        } catch (Exception e) {
            this.lectura4 = null;
        }
        try {
            this.promedio = Float.valueOf(txtPromedio.getText());
        } catch (Exception e) {
            this.promedio = null;
        }
        this.claveColecta = txtClaveColecta.getText();
    }

    private boolean validarDatosobligatoriosMuestrasPerfil() {
        if (txtGradosLatitud.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Error! debe capturar grados latitud ",
                    "Muestras del perfil", JOptionPane.INFORMATION_MESSAGE);
            txtGradosLatitud.requestFocus();
            return false;
        } else if (txtMinutosLatitud.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Error! debe capturar minutos latitud ",
                    "Muestras del perfil", JOptionPane.INFORMATION_MESSAGE);
            txtMinutosLatitud.requestFocus();
            return false;
        } else if (txtSegundosLatitud.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Error! debe capturar segundos latitud ",
                    "Muestras del perfil", JOptionPane.INFORMATION_MESSAGE);
            txtSegundosLatitud.requestFocus();
            return false;
        } else if (txtGradosLongitud.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Error! debe capturar grados longitud",
                    "Muestras del perfil", JOptionPane.INFORMATION_MESSAGE);
            txtGradosLongitud.requestFocus();
            return false;
        } else if (txtMinutosLongitud.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Error! debe capturar minutos longitud",
                    "Muestras del perfil", JOptionPane.INFORMATION_MESSAGE);
            txtMinutosLongitud.requestFocus();
            return false;
        } else if (txtSegundosLongitud.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Error! debe capturar segundos longitud",
                    "Muestras del perfil", JOptionPane.INFORMATION_MESSAGE);
            txtSegundosLongitud.requestFocus();
            return false;
        } else if (txtElevacion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Error! debe capturar una elevación",
                    "Muestras del perfil", JOptionPane.INFORMATION_MESSAGE);
            txtElevacion.requestFocus();
            return false;
        } else if (txtDiametroInterno.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Error! debe capturar un diámetro interno",
                    "Muestras del perfil", JOptionPane.INFORMATION_MESSAGE);
            txtDiametroInterno.requestFocus();
            return false;
        } else if (txtDiametroExterno.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Error! debe capturar un diámetro externo",
                    "Muestras del perfil", JOptionPane.INFORMATION_MESSAGE);
            txtDiametroExterno.requestFocus();
            return false;
        } else if (txtAltura.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Error! debe capturar una altura",
                    "Muestras del perfil", JOptionPane.INFORMATION_MESSAGE);
            txtAltura.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean validarDatosMuestrasPerfil() {
        if (validacion.sonGradosLatitud(this.gradosLatitud, "Muestras del perfil")) {
            txtGradosLatitud.requestFocus();
            txtGradosLatitud.setText("");
            return false;
        } else if (validacion.sonMinutos(this.minutosLatitud, "Muestras del perfil")) {
            txtMinutosLatitud.requestFocus();
            txtMinutosLatitud.setText("");
            return false;
        } else if (validacion.sonSegundos(this.segundosLatitud, "Muestras del perfil")) {
            txtSegundosLatitud.requestFocus();
            txtSegundosLatitud.setText("");
            return false;
        } else if (validacion.sonGradosLongitud(this.gradosLongitud, "Muestras del perfil")) {
            txtGradosLongitud.requestFocus();
            txtGradosLongitud.setText("");
            return false;
        } else if (validacion.sonMinutos(this.minutosLongitud, "Muestras del perfil")) {
            txtMinutosLongitud.requestFocus();
            txtMinutosLongitud.setText("");
            return false;
        } else if (validacion.sonSegundos(this.segundosLongitud, "Muestras del perfil")) {
            txtSegundosLongitud.requestFocus();
            txtSegundosLongitud.setText("");
            return false;
        } else if (this.elevacion < 0) {
            JOptionPane.showMessageDialog(null,
                    "Error! debe capturar una elevación mayor a cero ",
                    "Muestras del perfil", JOptionPane.INFORMATION_MESSAGE);
            txtElevacion.requestFocus();
            txtElevacion.setText("");
            return false;
        } else if (this.diametroInterno < 0) {
            JOptionPane.showMessageDialog(null,
                    "Error! debe capturar un diámetro interno mayor a cero ",
                    "Muestras del perfil", JOptionPane.INFORMATION_MESSAGE);
            txtDiametroInterno.requestFocus();
            txtDiametroInterno.setText("");
            return false;
        } else if (this.diametroExterno < 0) {
            JOptionPane.showMessageDialog(null,
                    "Error! debe capturar un diámetro externo mayor a cero ",
                    "Muestras del perfil", JOptionPane.INFORMATION_MESSAGE);
            txtDiametroExterno.requestFocus();
            txtDiametroExterno.setText("");
            return false;
        } else if (this.altura < 0) {
            JOptionPane.showMessageDialog(null,
                    "Error! debe capturar una altura mayor a cero ",
                    "Muestras del perfil", JOptionPane.INFORMATION_MESSAGE);
            txtAltura.requestFocus();
            txtAltura.setText("");
            return false;
        } else {
            return true;
        }
    }
    
    private void crearMuestrasPerfil() {
        this.cePerfil.setSitioID(this.sitioID);
        this.cePerfil.setGradosLatitud(this.gradosLatitud);
        this.cePerfil.setMinutosLatitud(this.minutosLatitud);
        this.cePerfil.setSegundosLatitud(this.segundosLatitud);
        this.cePerfil.setGradosLongitud(this.gradosLongitud);
        this.cePerfil.setMinutosLongitud(this.minutosLongitud);
        this.cePerfil.setSegundosLongitud(this.segundosLongitud);
        this.cePerfil.setElevacion(this.elevacion);
        this.cePerfil.setDiametroInterno(this.diametroInterno);
        this.cePerfil.setDiametroExterno(this.diametroExterno);
        this.cePerfil.setAltura(this.altura);
        this.cePerfil.setObservaciones(this.observaciones);
        this.cdPerfil.insertMuestrasPerfil(this.cePerfil);
    }
    
    private void actualizarMuestraPerfil() {
        this.cePerfil.setSitioID(this.sitioID);
        this.cePerfil.setGradosLatitud(this.gradosLatitud);
        this.cePerfil.setMinutosLatitud(this.minutosLatitud);
        this.cePerfil.setSegundosLatitud(this.segundosLatitud);
        this.cePerfil.setGradosLongitud(this.gradosLongitud);
        this.cePerfil.setMinutosLongitud(this.minutosLongitud);
        this.cePerfil.setSegundosLongitud(this.segundosLongitud);
        this.cePerfil.setElevacion(this.elevacion);
        this.cePerfil.setDiametroInterno(this.diametroInterno);
        this.cePerfil.setDiametroExterno(this.diametroExterno);
        this.cePerfil.setAltura(this.altura);
        this.cePerfil.setObservaciones(this.observaciones);
        this.cdPerfil.updateMuestrasPerfil(this.cePerfil);
    }

    private void eliminarMuestrasPerfilSitio(int sitioID) {
        this.cdPerfil.deleteMuetrasPerfil(sitioID);
        this.cdPerfil.deleteMuestrasSitio(sitioID);
    }
    
    private void crearMuestras() {
        this.ceMuestras.setSitioID(this.sitioID);
        CatEProfundidadMuestras profundidad = (CatEProfundidadMuestras) cmbProfundidad.getSelectedItem();
        this.ceMuestras.setProfundidadID(profundidad.getProfundidadMuestraID());
        this.ceMuestras.setPesoMuestra(this.pesoMuestra);
        this.ceMuestras.setLectura1(this.lectura1);
        this.ceMuestras.setLectura2(this.lectura2);
        this.ceMuestras.setLectura3(this.lectura3);
        this.ceMuestras.setLectura4(this.lectura4);
        this.ceMuestras.setPromedio(this.promedio);
        if (null != profundidad.getProfundidadMuestraID()) {
            switch (profundidad.getProfundidadMuestraID()) {
                case 1:
                    this.claveColecta = crearClave(this.upmID, this.sitio, "C1");
                    this.ceMuestras.setClaveColecta(this.claveColecta);
                    break;
                case 2:
                    this.claveColecta = crearClave(this.upmID, this.sitio, "C1R");
                    this.ceMuestras.setClaveColecta(this.claveColecta);
                    break;
                case 3:
                    this.claveColecta = crearClave(this.upmID, this.sitio, "C2");
                    this.ceMuestras.setClaveColecta(this.claveColecta);
                    break;
                case 4:
                    this.claveColecta = crearClave(this.upmID, this.sitio, "C3");
                    this.ceMuestras.setClaveColecta(this.claveColecta);
                    break;
                case 5:
                    this.claveColecta = crearClave(this.upmID, this.sitio, "C4");
                    this.ceMuestras.setClaveColecta(this.claveColecta);
                    break;
                case 6:
                    this.claveColecta = crearClave(this.upmID, this.sitio, "C5");
                    this.ceMuestras.setClaveColecta(this.claveColecta);
                    break;
                case 7:
                    this.claveColecta = crearClave(this.upmID, this.sitio, "C6");
                    this.ceMuestras.setClaveColecta(this.claveColecta);
                    break;
            }
        }
        this.cdPerfil.insertMuestras(this.ceMuestras);
    }
    
     private String crearClave(int upmID, int sitio, String seccion){
        String clave = null;
        String upm = crearUPM(upmID);
        String noSitio = crearSitio(sitio);
        clave = upm + "-" + noSitio + "-9-" + seccion;
        return clave;
    }
    
    private String crearUPM(int upmID) {
        String upm = null;
        if (upmID < 10) {
            upm = "00000" + upmID;
        } else if (upmID >= 10 && upmID < 100) {
            upm = "0000" + upmID;
        } else if (upmID >= 100 && upmID < 1000) {
            upm = "000" + upmID;
        } else if (upmID >= 1000 && upmID < 10000) {
            upm = "00" + upmID;
        } else if (upmID >= 10000 && upmID < 100000) {
            upm = "0" + upmID;
        } else {
            upm = String.valueOf(upmID);
        }
        return upm;
    }
    
    private String crearSitio(int sitio){
        return "S" + sitio;
    }
    
    private void limpiarMuestrasPerfil(){
        txtGradosLatitud.setValue(null);
        txtGradosLatitud.setText("");
        txtMinutosLatitud.setValue(null);
        txtMinutosLatitud.setText("");
        txtSegundosLatitud.setValue(null);
        txtSegundosLatitud.setText("");
        txtGradosLongitud.setValue(null);
        txtGradosLongitud.setText("");
        txtMinutosLongitud.setValue(null);
        txtMinutosLongitud.setText("");
        txtSegundosLongitud.setValue(null);
        txtSegundosLongitud.setText("");
        txtElevacion.setValue(null);
        txtElevacion.setText("");
        txtDiametroInterno.setValue(null);
        txtDiametroInterno.setText("");
        txtDiametroExterno.setValue(null);
        txtDiametroExterno.setText("");
        txtAltura.setValue(null);
        txtAltura.setText("");
        txtObservaciones.setText("");
    }
    
    private void actualizarMuestras() {
        try {
            int fila = grdMuestras.getSelectedRow();
            String registro = grdMuestras.getValueAt(fila, 0).toString();
            this.ceMuestras.setMuestrasID(Integer.parseInt(registro));
            this.ceMuestras.setPesoMuestra(this.pesoMuestra);
            this.ceMuestras.setLectura1(this.lectura1);
            this.ceMuestras.setLectura2(this.lectura2);
            this.ceMuestras.setLectura3(this.lectura3);
            this.ceMuestras.setLectura4(this.lectura4);
            this.ceMuestras.setPromedio(this.promedio);
            this.cdPerfil.updateMuestras(this.ceMuestras);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla de muestras "
                    + e.getClass().getName() + " : " + e.getMessage(), "Muestras de perfil", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private boolean validarCamposObligatoriosMuestras() {
        if (cmbProfundidad.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null,
                    "Error! Debe de seleccionar un nivel de profundidad",
                    "Muestras del perfil", JOptionPane.INFORMATION_MESSAGE);
            cmbProfundidad.requestFocus();
            return false;
        } else if (txtPesoMuestra.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Error! Debe de introducir un dato para peso muestra",
                    "Muestras del perfil", JOptionPane.INFORMATION_MESSAGE);
            txtPesoMuestra.requestFocus();
            return false;
        } else if (txtLectura1.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Error! Debe de introducir un valor para lactura 1",
                    "Muestras del perfil", JOptionPane.INFORMATION_MESSAGE);
            txtLectura1.requestFocus();
            return false;
        } else if (txtLectura2.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Error! Debe de introducir un valor para lactura 2",
                    "Muestras del perfil", JOptionPane.INFORMATION_MESSAGE);
            txtLectura2.requestFocus();
            return false;
        } else if (txtLectura3.isEnabled() && txtLectura3.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Error! Debe de introducir un valor para lactura 3",
                    "Muestras del perfil", JOptionPane.INFORMATION_MESSAGE);
            txtLectura3.requestFocus();
            return false;
        } else if (txtLectura4.isEnabled() && txtLectura4.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Error! Debe de introducir un valor para lactura 4",
                    "Muestras del perfil", JOptionPane.INFORMATION_MESSAGE);
            txtLectura4.requestFocus();
            return false;
        } else if (txtPromedio.isEnabled() && txtPromedio.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Error! Debe de introducir un valor para promedio",
                    "Muestras del perfil", JOptionPane.INFORMATION_MESSAGE);
            txtPromedio.requestFocus();
            return false;
        } else {
            return true;
        }
    }
    
    private boolean validarCamposMuestras() {
        if (this.pesoMuestra < 0) {
            JOptionPane.showMessageDialog(null,
                    "Error! Debe de introducir un valor mayor a cero para peso muestra",
                    "Muestras del perfil", JOptionPane.INFORMATION_MESSAGE);
            txtPesoMuestra.requestFocus();
            return false;
        } else if (this.lectura1 != null && this.lectura1 < 0) {
            JOptionPane.showMessageDialog(null,
                    "Error! Debe de introducir un valor mayor a cero para lactura 1",
                    "Muestras del perfil", JOptionPane.INFORMATION_MESSAGE);
            txtLectura1.requestFocus();
            return false;
        } else if (this.lectura2 != null && this.lectura2 < 0) {
            JOptionPane.showMessageDialog(null,
                    "Error! Debe de introducir un valor mayor a cero para lactura 2",
                    "Muestras del perfil", JOptionPane.INFORMATION_MESSAGE);
            txtLectura2.requestFocus();
            return false;
        } else if (this.lectura3 != null && this.lectura3 < 0) {
            JOptionPane.showMessageDialog(null,
                    "Error! Debe de introducir un valor mayor a cero para lactura 3",
                    "Muestras del perfil", JOptionPane.INFORMATION_MESSAGE);
            txtLectura3.requestFocus();
            return false;
        } else if (this.lectura4 != null && this.lectura4 < 0) {
            JOptionPane.showMessageDialog(null,
                    "Error! Debe de introducir un valor mayor a cero para lactura 4",
                    "Muestras del perfil", JOptionPane.INFORMATION_MESSAGE);
            txtLectura4.requestFocus();
            return false;
        } else if (this.promedio != null && this.promedio < 0) {
            JOptionPane.showMessageDialog(null,
                    "Error! Debe de introducir un valor mayor a cero para promedio",
                    "Muestras del perfil", JOptionPane.INFORMATION_MESSAGE);
            txtPromedio.requestFocus();
            return false;
        } else {
            return true;
        }
    }
    
    private void seleccionarSiguienteFormulario(CESitio ceSitio) {
        Integer secuenciaID = ceSitio.getSecuencia();
        if (secuenciaID != null) {
            switch (secuenciaID) {
                case 1: //Módulo A
                    /* UPMForms.carbono.setDatosIniciales(ceSitio);
                    UPMForms.carbono.setVisible(true);
                    this.hide();*/
                    break;
                case 2: //Módulos A y C
                    /* UPMForms.carbono.setDatosIniciales(ceSitio);
                    UPMForms.carbono.setVisible(true);*/
                    break;
                case 3: //Modulos A, C, E y G
                    UPMForms.parametrosFQ.setDatosiniciales(ceSitio);
                    UPMForms.parametrosFQ.setVisible(true);
                    break;
                case 4: //Modulos A y E
                    /*UPMForms.hojarascaProfundidad.setDatosiniciales(ceSitio);
                    UPMForms.hojarascaProfundidad.setVisible(true);*/
                    this.hide();
                    this.funciones.manipularBotonesMenuPrincipal(false);
                    this.cdSecuencia.insertSecuenciaTerminada(ceSitio);
                    break;
                case 5: //Modulos A, C, D y F
                    /*UPMForms.carbono.setDatosIniciales(ceSitio);
                    UPMForms.carbono.setVisible(true);*/
                    break;
                case 6://Modulos A, C y D
                    /*UPMForms.suelo.setDatosiniciales(ceSitio);
                    UPMForms.suelo.setVisible(true);*/
                    break;
                case 7://Modulos A, C, D y E
                    /* UPMForms.carbono.setDatosIniciales(ceSitio);
                    UPMForms.carbono.setVisible(true);*/
                    this.hide();
                    this.funciones.manipularBotonesMenuPrincipal(false);
                    this.cdSecuencia.insertSecuenciaTerminada(ceSitio);
                    break;
                case 8://Modulos A, C, D, E y F
                    UPMForms.fotoHemisferica.setDatosiniciales(ceSitio);
                    UPMForms.fotoHemisferica.setVisible(true);
                    break;
                case 9://Modulos A, C y E
                    /*UPMForms.carbono.setDatosIniciales(ceSitio);
                    UPMForms.carbono.setVisible(true);*/
                    this.hide();
                    this.funciones.manipularBotonesMenuPrincipal(false);
                    this.cdSecuencia.insertSecuenciaTerminada(ceSitio);
                    break;
                case 10://Modulos A, C, H
                    /*UPMForms.suelo.setDatosiniciales(ceSitio);
                    UPMForms.suelo.setVisible(true);*/
                    break;
                case 11://Modulos A y H
                    /* UPMForms.carbono.setDatosIniciales(ceSitio);
                    UPMForms.carbono.setVisible(true);*/
                    break;
                case 12://Modulos A, E y H
                    UPMForms.vegetacionMenor.setDatosIniciales(ceSitio);
                    UPMForms.vegetacionMenor.setVisible(true);
                    break;
                case 13://Modulos A, C, E y H
                    UPMForms.vegetacionMenor.setDatosIniciales(ceSitio);
                    UPMForms.vegetacionMenor.setVisible(true);
                    break;
                case 14://Modulos A, E y G
                    UPMForms.parametrosFQ.setDatosiniciales(ceSitio);
                    UPMForms.parametrosFQ.setVisible(true);
                    break;
                case 15://A y G
                    /* UPMForms.repobladoVM.setDatosIniciales(ceSitio);
                    UPMForms.repobladoVM.setVisible(true);*/
                    break;
            }
        }
    }
   
    private void revisarSiguienteFormulario(CESitio ceSitio) {
        Integer secuenciaID = ceSitio.getSecuencia();
        //System.out.println("FrmMuestrasDePerfil Linea 679 "+secuenciaID);
        if (secuenciaID != null) {
            //System.out.println("FrmMuestrasDePerfil Linea 680 "+secuenciaID);
            switch (secuenciaID) {
                case 1: //Módulo A
                    /* UPMForms.carbono.setDatosIniciales(ceSitio);
                    UPMForms.carbono.setVisible(true);
                    this.hide();*/
                    break;
                case 2: //Módulos A y C
                    /* UPMForms.carbono.setDatosIniciales(ceSitio);
                    UPMForms.carbono.setVisible(true);*/
                    break;
                case 3: //Modulos A, C, E y G
                    UPMForms.parametrosFQ.continuarParametrosFQ(ceSitio);
                    UPMForms.parametrosFQ.setVisible(true);
                    break;
                case 4: //Modulos A y E
                    /*UPMForms.hojarascaProfundidad.setDatosiniciales(ceSitio);
                    UPMForms.hojarascaProfundidad.setVisible(true);*/
                    this.hide();
                    this.funciones.manipularBotonesMenuPrincipal(false);
                    break;
                case 5: //Modulos A, C, D y F
                    /*UPMForms.carbono.setDatosIniciales(ceSitio);
                    UPMForms.carbono.setVisible(true);*/
                    break;
                case 6://Modulos A, C y D
                    /*UPMForms.suelo.setDatosiniciales(ceSitio);
                    UPMForms.suelo.setVisible(true);*/
                    break;
                case 7://Modulos A, C, D y E
                    /* UPMForms.carbono.setDatosIniciales(ceSitio);
                    UPMForms.carbono.setVisible(true);*/
                    this.hide();
                    this.funciones.manipularBotonesMenuPrincipal(false);
                    break;
                case 8://Modulos A, C, D, E y F
                    UPMForms.fotoHemisferica.revisarFotoHemisferica(ceSitio);
                    UPMForms.fotoHemisferica.setVisible(true);
                    break;
                case 9://Modulos A, C y E
                    /*UPMForms.carbono.setDatosIniciales(ceSitio);
                    UPMForms.carbono.setVisible(true);*/
                    this.hide();
                    this.funciones.manipularBotonesMenuPrincipal(false);
                    break;
                case 10://Modulos A, C, H
                    /*UPMForms.suelo.setDatosiniciales(ceSitio);
                    UPMForms.suelo.setVisible(true);*/
                    break;
                case 11://Modulos A y H
                    /* UPMForms.carbono.setDatosIniciales(ceSitio);
                    UPMForms.carbono.setVisible(true);*/
                    break;
                case 12://Modulos A, E y H
                    UPMForms.vegetacionMenor.revisarVegetacionMenor(ceSitio);
                    UPMForms.vegetacionMenor.setVisible(true);
                    break;
                case 13://Modulos A, C, E y H
                    UPMForms.vegetacionMenor.revisarVegetacionMenor(ceSitio);
                    UPMForms.vegetacionMenor.setVisible(true);
                    break;
                case 14://Modulos A, E y G
                    UPMForms.parametrosFQ.continuarParametrosFQ(ceSitio);
                    UPMForms.parametrosFQ.setVisible(true);
                    break;
                case 15://A y G
                    /* UPMForms.repobladoVM.setDatosIniciales(ceSitio);
                    UPMForms.repobladoVM.setVisible(true);*/
                    break;
            }
        }else{
        //this.hide();
                    this.funciones.manipularBotonesMenuPrincipal(false);
        }
    }

    public void reiniciarControlesMuestras() {
        this.cmbProfundidad.setSelectedItem(null);
        this.txtPesoMuestra.setText("");
        this.txtPesoMuestra.setValue(null);
        this.txtLectura1.setText("");
        this.txtLectura1.setValue(null);
        this.txtLectura2.setText("");
        this.txtLectura2.setValue(null);
        this.txtLectura3.setText("");
        this.txtLectura3.setValue(null);
        this.txtLectura4.setText("");
        this.txtLectura4.setValue(null);
        this.txtPromedio.setText("");
        this.txtPromedio.setValue(null);
        this.cmbProfundidad.requestFocus();
        this.pesoMuestra = null;
        this.lectura1 = null;
        this.lectura2 = null;
        this.lectura3 = null;
        this.lectura4 = null;
        this.promedio = null;
        this.txtClaveColecta.setText("");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblUPM = new javax.swing.JLabel();
        txtUPM = new javax.swing.JTextField();
        lblMuestrasPerfil = new javax.swing.JLabel();
        lblSitio = new javax.swing.JLabel();
        txtSitio = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        lblObservaciones = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObservaciones = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        btnContinuar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        PnlCoordenadasInterior = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        LblLatitud = new javax.swing.JLabel();
        LblLongitud = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        LblGradosLatitud = new javax.swing.JLabel();
        LblMinutosLatitud = new javax.swing.JLabel();
        LblSegundosLatitud = new javax.swing.JLabel();
        txtGradosLatitud = new javax.swing.JFormattedTextField();
        txtMinutosLatitud = new javax.swing.JFormattedTextField();
        txtSegundosLatitud = new javax.swing.JFormattedTextField();
        jPanel10 = new javax.swing.JPanel();
        LblGradosLongitud = new javax.swing.JLabel();
        LblMinutosLongitud = new javax.swing.JLabel();
        LblSegundosLongitud = new javax.swing.JLabel();
        txtGradosLongitud = new javax.swing.JFormattedTextField();
        txtMinutosLongitud = new javax.swing.JFormattedTextField();
        txtSegundosLongitud = new javax.swing.JFormattedTextField();
        jPanel11 = new javax.swing.JPanel();
        txtElevacion = new javax.swing.JFormattedTextField();
        lblMetrosSobreNivelMar = new javax.swing.JLabel();
        lblElevacion = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblDiametroInterno = new javax.swing.JLabel();
        lblDiametroExterno = new javax.swing.JLabel();
        lblAltura = new javax.swing.JLabel();
        txtDiametroInterno = new javax.swing.JFormattedTextField();
        txtDiametroExterno = new javax.swing.JFormattedTextField();
        txtAltura = new javax.swing.JFormattedTextField();
        jPanel5 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        lblProfundidad = new javax.swing.JLabel();
        cmbProfundidad = new javax.swing.JComboBox();
        lblPesoMuestra = new javax.swing.JLabel();
        txtPesoMuestra = new javax.swing.JFormattedTextField();
        lblLectura1 = new javax.swing.JLabel();
        lblLectura2 = new javax.swing.JLabel();
        lblLectura4 = new javax.swing.JLabel();
        lblLectura3 = new javax.swing.JLabel();
        lblPromedio = new javax.swing.JLabel();
        txtPromedio = new javax.swing.JFormattedTextField();
        txtLectura1 = new javax.swing.JFormattedTextField();
        txtLectura3 = new javax.swing.JFormattedTextField();
        txtLectura2 = new javax.swing.JFormattedTextField();
        txtLectura4 = new javax.swing.JFormattedTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        grdMuestras = new javax.swing.JTable();
        txtClaveColecta = new javax.swing.JTextField();
        lblClaveColecta = new javax.swing.JLabel();
        lblLecturaVernier = new javax.swing.JLabel();
        btnGuardarMuestra = new javax.swing.JButton();
        btnModificarMuestra = new javax.swing.JButton();
        btnEliminarMuestra = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Muestras del perfil, módulo E");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/gob/conafor/utils/logo_conafor.png"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setPreferredSize(new java.awt.Dimension(940, 650));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblUPM.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblUPM.setText("UPMID:");
        jPanel1.add(lblUPM, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 15, -1, -1));

        txtUPM.setEditable(false);
        txtUPM.setEnabled(false);
        jPanel1.add(txtUPM, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 13, 97, -1));

        lblMuestrasPerfil.setBackground(new java.awt.Color(153, 153, 153));
        lblMuestrasPerfil.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblMuestrasPerfil.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMuestrasPerfil.setText("Muestras del perfil");
        lblMuestrasPerfil.setOpaque(true);
        jPanel1.add(lblMuestrasPerfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 39, 900, -1));

        lblSitio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblSitio.setText("Sitio:");
        jPanel1.add(lblSitio, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 10, -1, -1));

        txtSitio.setEditable(false);
        txtSitio.setEnabled(false);
        jPanel1.add(txtSitio, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 10, 100, -1));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblObservaciones.setBackground(new java.awt.Color(153, 153, 153));
        lblObservaciones.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblObservaciones.setText("Observaciones");
        lblObservaciones.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblObservaciones.setOpaque(true);

        txtObservaciones.setColumns(20);
        txtObservaciones.setRows(5);
        txtObservaciones.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtObservacionesFocusGained(evt);
            }
        });
        txtObservaciones.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtObservacionesKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(txtObservaciones);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblObservaciones, javax.swing.GroupLayout.DEFAULT_SIZE, 876, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblObservaciones)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, 900, 100));

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnContinuar.setText("Continuar");
        btnContinuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinuarActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(357, 357, 357)
                .addComponent(btnContinuar)
                .addGap(18, 18, 18)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(364, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalir)
                    .addComponent(btnContinuar))
                .addContainerGap())
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 570, 900, 50));

        PnlCoordenadasInterior.setBackground(new java.awt.Color(204, 204, 204));
        PnlCoordenadasInterior.setBorder(javax.swing.BorderFactory.createTitledBorder("Ubicación geográfica"));

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));

        LblLatitud.setBackground(new java.awt.Color(0, 0, 0));
        LblLatitud.setForeground(new java.awt.Color(255, 255, 255));
        LblLatitud.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblLatitud.setText("Latitud");
        LblLatitud.setOpaque(true);

        LblLongitud.setBackground(new java.awt.Color(0, 0, 0));
        LblLongitud.setForeground(new java.awt.Color(255, 255, 255));
        LblLongitud.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblLongitud.setText("Longitud");
        LblLongitud.setOpaque(true);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(LblLatitud, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(LblLongitud, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(LblLatitud)
                .addComponent(LblLongitud))
        );

        jPanel7.setBackground(new java.awt.Color(204, 204, 204));

        LblGradosLatitud.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblGradosLatitud.setText("Grados");
        LblGradosLatitud.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        LblMinutosLatitud.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblMinutosLatitud.setText("Minutos");
        LblMinutosLatitud.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        LblMinutosLatitud.setPreferredSize(new java.awt.Dimension(36, 16));

        LblSegundosLatitud.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblSegundosLatitud.setText("Segundos");
        LblSegundosLatitud.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        LblSegundosLatitud.setPreferredSize(new java.awt.Dimension(36, 16));

        txtGradosLatitud.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtGradosLatitud.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGradosLatitud.setToolTipText("");
        txtGradosLatitud.setNextFocusableComponent(txtMinutosLatitud);
        txtGradosLatitud.setPreferredSize(new java.awt.Dimension(70, 20));
        txtGradosLatitud.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtGradosLatitudFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtGradosLatitudFocusLost(evt);
            }
        });
        txtGradosLatitud.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtGradosLatitudKeyTyped(evt);
            }
        });

        txtMinutosLatitud.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtMinutosLatitud.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMinutosLatitud.setNextFocusableComponent(txtSegundosLatitud);
        txtMinutosLatitud.setPreferredSize(new java.awt.Dimension(70, 20));
        txtMinutosLatitud.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtMinutosLatitudFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtMinutosLatitudFocusLost(evt);
            }
        });
        txtMinutosLatitud.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMinutosLatitudKeyTyped(evt);
            }
        });

        txtSegundosLatitud.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtSegundosLatitud.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSegundosLatitud.setNextFocusableComponent(txtGradosLongitud);
        txtSegundosLatitud.setPreferredSize(new java.awt.Dimension(70, 20));
        txtSegundosLatitud.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSegundosLatitudFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSegundosLatitudFocusLost(evt);
            }
        });
        txtSegundosLatitud.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSegundosLatitudKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(LblGradosLatitud, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(LblMinutosLatitud, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LblSegundosLatitud, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(txtGradosLatitud, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(txtMinutosLatitud, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSegundosLatitud, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LblGradosLatitud)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(LblSegundosLatitud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LblMinutosLatitud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(4, 4, 4)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtGradosLatitud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSegundosLatitud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtMinutosLatitud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jPanel10.setBackground(new java.awt.Color(204, 204, 204));

        LblGradosLongitud.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblGradosLongitud.setText("Grados");
        LblGradosLongitud.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        LblMinutosLongitud.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblMinutosLongitud.setText("Minutos");
        LblMinutosLongitud.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        LblMinutosLongitud.setPreferredSize(new java.awt.Dimension(36, 16));

        LblSegundosLongitud.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblSegundosLongitud.setText("Segundos");
        LblSegundosLongitud.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        LblSegundosLongitud.setPreferredSize(new java.awt.Dimension(36, 16));

        txtGradosLongitud.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtGradosLongitud.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGradosLongitud.setNextFocusableComponent(txtMinutosLongitud);
        txtGradosLongitud.setPreferredSize(new java.awt.Dimension(70, 20));
        txtGradosLongitud.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtGradosLongitudFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtGradosLongitudFocusLost(evt);
            }
        });
        txtGradosLongitud.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtGradosLongitudKeyTyped(evt);
            }
        });

        txtMinutosLongitud.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtMinutosLongitud.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMinutosLongitud.setNextFocusableComponent(txtSegundosLongitud);
        txtMinutosLongitud.setPreferredSize(new java.awt.Dimension(70, 20));
        txtMinutosLongitud.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtMinutosLongitudFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtMinutosLongitudFocusLost(evt);
            }
        });
        txtMinutosLongitud.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMinutosLongitudKeyTyped(evt);
            }
        });

        txtSegundosLongitud.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtSegundosLongitud.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSegundosLongitud.setFocusCycleRoot(true);
        txtSegundosLongitud.setNextFocusableComponent(txtElevacion);
        txtSegundosLongitud.setPreferredSize(new java.awt.Dimension(70, 20));
        txtSegundosLongitud.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSegundosLongitudFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSegundosLongitudFocusLost(evt);
            }
        });
        txtSegundosLongitud.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSegundosLongitudKeyTyped(evt);
            }
        });

        jPanel11.setBackground(new java.awt.Color(204, 204, 204));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtElevacion.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtElevacion.setNextFocusableComponent(txtDiametroInterno);
        txtElevacion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtElevacionFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtElevacionFocusLost(evt);
            }
        });
        txtElevacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtElevacionKeyTyped(evt);
            }
        });

        lblMetrosSobreNivelMar.setText("m.s.n.m");

        lblElevacion.setBackground(new java.awt.Color(204, 204, 204));
        lblElevacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblElevacion.setText("Elevación");
        lblElevacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblElevacion.setOpaque(true);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(LblGradosLongitud, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(LblMinutosLongitud, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(txtGradosLongitud, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMinutosLongitud, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(4, 4, 4)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(LblSegundosLongitud, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSegundosLongitud, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(lblElevacion, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(txtElevacion, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMetrosSobreNivelMar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LblGradosLongitud)
                            .addComponent(LblMinutosLongitud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(LblSegundosLongitud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblElevacion)))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtGradosLongitud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtMinutosLongitud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtSegundosLongitud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtElevacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMetrosSobreNivelMar))))
                .addContainerGap())
        );

        javax.swing.GroupLayout PnlCoordenadasInteriorLayout = new javax.swing.GroupLayout(PnlCoordenadasInterior);
        PnlCoordenadasInterior.setLayout(PnlCoordenadasInteriorLayout);
        PnlCoordenadasInteriorLayout.setHorizontalGroup(
            PnlCoordenadasInteriorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlCoordenadasInteriorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnlCoordenadasInteriorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PnlCoordenadasInteriorLayout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PnlCoordenadasInteriorLayout.setVerticalGroup(
            PnlCoordenadasInteriorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnlCoordenadasInteriorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PnlCoordenadasInteriorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(PnlCoordenadasInteriorLayout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel1.add(PnlCoordenadasInterior, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 900, 100));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Dimensiones del cilindro"));

        lblDiametroInterno.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDiametroInterno.setText("Diámetro interno");

        lblDiametroExterno.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDiametroExterno.setText("Diámetro externo");

        lblAltura.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAltura.setText("Altura");

        txtDiametroInterno.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtDiametroInterno.setNextFocusableComponent(txtDiametroExterno);
        txtDiametroInterno.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDiametroInternoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDiametroInternoFocusLost(evt);
            }
        });
        txtDiametroInterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDiametroInternoKeyTyped(evt);
            }
        });

        txtDiametroExterno.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtDiametroExterno.setNextFocusableComponent(txtAltura);
        txtDiametroExterno.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDiametroExternoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDiametroExternoFocusLost(evt);
            }
        });
        txtDiametroExterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDiametroExternoKeyTyped(evt);
            }
        });

        txtAltura.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtAltura.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtAlturaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtAlturaFocusLost(evt);
            }
        });
        txtAltura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAlturaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDiametroInterno)
                    .addComponent(lblDiametroInterno, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 251, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDiametroExterno)
                    .addComponent(lblDiametroExterno, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(207, 207, 207)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtAltura)
                    .addComponent(lblAltura, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDiametroInterno)
                    .addComponent(lblDiametroExterno)
                    .addComponent(lblAltura))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDiametroInterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiametroExterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAltura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 900, 70));

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel8.setBackground(new java.awt.Color(204, 204, 204));

        lblProfundidad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProfundidad.setText("Profundidad");

        cmbProfundidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbProfundidadActionPerformed(evt);
            }
        });

        lblPesoMuestra.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPesoMuestra.setText("Peso de la muestra");

        txtPesoMuestra.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtPesoMuestra.setNextFocusableComponent(txtLectura1);
        txtPesoMuestra.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPesoMuestraFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPesoMuestraFocusLost(evt);
            }
        });
        txtPesoMuestra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPesoMuestraKeyTyped(evt);
            }
        });

        lblLectura1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLectura1.setText("1");
        lblLectura1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblLectura2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLectura2.setText("2");
        lblLectura2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblLectura4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLectura4.setText("4");
        lblLectura4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblLectura3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLectura3.setText("3");
        lblLectura3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblPromedio.setBackground(new java.awt.Color(204, 204, 204));
        lblPromedio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPromedio.setText("Promedio");
        lblPromedio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblPromedio.setOpaque(true);

        txtPromedio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtPromedio.setNextFocusableComponent(btnGuardarMuestra);
        txtPromedio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPromedioFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPromedioFocusLost(evt);
            }
        });
        txtPromedio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPromedioKeyTyped(evt);
            }
        });

        txtLectura1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtLectura1.setNextFocusableComponent(txtLectura2);
        txtLectura1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtLectura1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtLectura1FocusLost(evt);
            }
        });
        txtLectura1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLectura1KeyTyped(evt);
            }
        });

        txtLectura3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtLectura3.setNextFocusableComponent(txtLectura4);
        txtLectura3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtLectura3FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtLectura3FocusLost(evt);
            }
        });
        txtLectura3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLectura3KeyTyped(evt);
            }
        });

        txtLectura2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtLectura2.setNextFocusableComponent(txtLectura3);
        txtLectura2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtLectura2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtLectura2FocusLost(evt);
            }
        });
        txtLectura2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLectura2KeyTyped(evt);
            }
        });

        txtLectura4.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtLectura4.setNextFocusableComponent(txtPromedio);
        txtLectura4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtLectura4FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtLectura4FocusLost(evt);
            }
        });
        txtLectura4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLectura4KeyTyped(evt);
            }
        });

        grdMuestras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        grdMuestras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grdMuestrasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(grdMuestras);

        txtClaveColecta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtClaveColecta.setEnabled(false);

        lblClaveColecta.setBackground(new java.awt.Color(204, 204, 204));
        lblClaveColecta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblClaveColecta.setText("Clave de colecta");
        lblClaveColecta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblClaveColecta.setOpaque(true);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblProfundidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbProfundidad, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblPesoMuestra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPesoMuestra, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblLectura1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtLectura1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblLectura2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtLectura2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(lblLectura3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblLectura4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(txtLectura3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtLectura4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblPromedio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPromedio, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblClaveColecta, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                    .addComponent(txtClaveColecta)))
            .addComponent(jScrollPane2)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(lblProfundidad)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbProfundidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addComponent(lblPesoMuestra)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPesoMuestra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblLectura1)
                                .addComponent(lblLectura2))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtLectura1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtLectura2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblLectura4)
                                .addComponent(lblLectura3))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtLectura3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtLectura4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                            .addComponent(lblPromedio)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtPromedio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(lblClaveColecta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtClaveColecta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        lblLecturaVernier.setBackground(new java.awt.Color(0, 0, 0));
        lblLecturaVernier.setForeground(new java.awt.Color(255, 255, 255));
        lblLecturaVernier.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLecturaVernier.setText("Lectura del vernier");
        lblLecturaVernier.setOpaque(true);

        btnGuardarMuestra.setText("Guardar");
        btnGuardarMuestra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarMuestraActionPerformed(evt);
            }
        });

        btnModificarMuestra.setText("Modificar");
        btnModificarMuestra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarMuestraActionPerformed(evt);
            }
        });

        btnEliminarMuestra.setText("Eliminar");
        btnEliminarMuestra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarMuestraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(333, 333, 333)
                        .addComponent(btnGuardarMuestra)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificarMuestra)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminarMuestra, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(186, 186, 186)
                        .addComponent(lblLecturaVernier, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLecturaVernier)
                .addGap(1, 1, 1)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarMuestra)
                    .addComponent(btnModificarMuestra)
                    .addComponent(btnEliminarMuestra))
                .addContainerGap())
        );

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 900, 200));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 924, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 627, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuarActionPerformed
        fijarDatosMuestraPerfil();
        if (this.modificar == 0) {
            if (validarDatosobligatoriosMuestrasPerfil() && validarDatosMuestrasPerfil()) {
                crearMuestrasPerfil();
                this.hide();
                this.cdSecuencia.updateSecuencia(this.ceSitio, FORMATO_ID, 1);
                seleccionarSiguienteFormulario(this.ceSitio);
            }
        } else if (this.modificar == 1) {
            if (validarDatosobligatoriosMuestrasPerfil() && validarDatosMuestrasPerfil()) {
                actualizarMuestraPerfil();
                this.hide();
                revisarSiguienteFormulario(this.ceSitio);
                
            }
        }
    }//GEN-LAST:event_btnContinuarActionPerformed

    private void txtObservacionesFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtObservacionesFocusGained
        txtObservaciones.selectAll();
    }//GEN-LAST:event_txtObservacionesFocusGained

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.hide();
        //System.out.print("Sigue manipular Botones");
        funciones.manipularBotonesMenuPrincipal(false);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtGradosLatitudFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGradosLatitudFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtGradosLatitud.selectAll();
            }
        });
    }//GEN-LAST:event_txtGradosLatitudFocusGained

    private void txtGradosLatitudFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGradosLatitudFocusLost
        if (txtGradosLatitud.getText().isEmpty()) {
            txtGradosLatitud.setValue(null);
        }
    }//GEN-LAST:event_txtGradosLatitudFocusLost

    private void txtGradosLatitudKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGradosLatitudKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtGradosLatitudKeyTyped

    private void txtMinutosLatitudFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMinutosLatitudFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtMinutosLatitud.selectAll();
            }
        });
    }//GEN-LAST:event_txtMinutosLatitudFocusGained

    private void txtMinutosLatitudFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMinutosLatitudFocusLost
        if (txtMinutosLatitud.getText().isEmpty()) {
            txtMinutosLatitud.setValue(null);
        }
    }//GEN-LAST:event_txtMinutosLatitudFocusLost

    private void txtMinutosLatitudKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMinutosLatitudKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtMinutosLatitudKeyTyped

    private void txtSegundosLatitudFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSegundosLatitudFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtSegundosLatitud.selectAll();
            }
        });
    }//GEN-LAST:event_txtSegundosLatitudFocusGained

    private void txtSegundosLatitudFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSegundosLatitudFocusLost
        if (txtSegundosLatitud.getText().isEmpty()) {
            txtSegundosLatitud.setValue(null);
        }
    }//GEN-LAST:event_txtSegundosLatitudFocusLost

    private void txtSegundosLatitudKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSegundosLatitudKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtSegundosLatitudKeyTyped

    private void txtGradosLongitudFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGradosLongitudFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtGradosLongitud.selectAll();
            }
        });
    }//GEN-LAST:event_txtGradosLongitudFocusGained

    private void txtGradosLongitudFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGradosLongitudFocusLost
        String gradosLongitud;
        if (txtGradosLongitud.getText().isEmpty()) {
            txtGradosLongitud.setValue(null);
        } else {
            gradosLongitud = txtGradosLongitud.getText();
            gradosLongitud = "-" + gradosLongitud;
            txtGradosLongitud.setText(gradosLongitud);
        }
    }//GEN-LAST:event_txtGradosLongitudFocusLost

    private void txtGradosLongitudKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGradosLongitudKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtGradosLongitudKeyTyped

    private void txtMinutosLongitudFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMinutosLongitudFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtMinutosLongitud.selectAll();
            }
        });
    }//GEN-LAST:event_txtMinutosLongitudFocusGained

    private void txtMinutosLongitudFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMinutosLongitudFocusLost
        if (txtMinutosLongitud.getText().isEmpty()) {
            txtMinutosLongitud.setValue(null);
        }
    }//GEN-LAST:event_txtMinutosLongitudFocusLost

    private void txtMinutosLongitudKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMinutosLongitudKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtMinutosLongitudKeyTyped

    private void txtSegundosLongitudFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSegundosLongitudFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtSegundosLongitud.selectAll();
            }
        });
    }//GEN-LAST:event_txtSegundosLongitudFocusGained

    private void txtSegundosLongitudFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSegundosLongitudFocusLost
        if (txtSegundosLongitud.getText().isEmpty()) {
            txtSegundosLongitud.setValue(null);
        }
    }//GEN-LAST:event_txtSegundosLongitudFocusLost

    private void txtSegundosLongitudKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSegundosLongitudKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtSegundosLongitudKeyTyped

    private void txtElevacionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtElevacionFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtElevacion.selectAll();
            }
        });
    }//GEN-LAST:event_txtElevacionFocusGained

    private void txtElevacionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtElevacionFocusLost
        if (txtElevacion.getText().isEmpty()) {
            txtElevacion.setValue(null);
        }
    }//GEN-LAST:event_txtElevacionFocusLost

    private void txtElevacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtElevacionKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtElevacionKeyTyped

    private void btnGuardarMuestraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarMuestraActionPerformed
        fijarDatosMuestras();
        if(validarCamposObligatoriosMuestras() && validarCamposMuestras()){
            crearMuestras();
            funciones.reiniciarTabla(this.grdMuestras);
            llenarTablaMuestras();
            funciones.reiniciarComboModel(this.cmbProfundidad);
            fillCmbProfundidad();
            reiniciarControlesMuestras();
        }
    }//GEN-LAST:event_btnGuardarMuestraActionPerformed

    private void btnModificarMuestraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarMuestraActionPerformed
        fijarDatosMuestras();
        if (validarCamposMuestras()) {
            actualizarMuestras();
            this.funciones.reiniciarTabla(this.grdMuestras);
            llenarTablaMuestras();
            this.funciones.reiniciarComboModel(this.cmbProfundidad);
            fillCmbProfundidad();
            reiniciarControlesMuestras();
        }
    }//GEN-LAST:event_btnModificarMuestraActionPerformed

    private void btnEliminarMuestraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarMuestraActionPerformed
        try {
            int fila = grdMuestras.getSelectedRow();
            String registro = grdMuestras.getValueAt(fila, 0).toString();
            Object[] opciones = {"Si", "No"};
            int respuesta = JOptionPane.showOptionDialog(null, "Se borrará el registro de muestra ¿Esta seguro?",
                    "Suelo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[1]);
            if (respuesta == JOptionPane.YES_OPTION) {
                this.cdPerfil.deleteMuestras(Integer.parseInt(registro));
                this.funciones.reiniciarTabla(this.grdMuestras);
                llenarTablaMuestras();
                this.funciones.reiniciarComboModel(this.cmbProfundidad);
                fillCmbProfundidad();
                reiniciarControlesMuestras();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla de muestras"
                    + "", "Muestras de perfil", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarMuestraActionPerformed

    private void txtDiametroInternoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiametroInternoKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtDiametroInternoKeyTyped

    private void txtDiametroExternoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiametroExternoKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtDiametroExternoKeyTyped

    private void txtAlturaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAlturaKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtAlturaKeyTyped

    private void txtPesoMuestraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesoMuestraKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtPesoMuestraKeyTyped

    private void txtLectura1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLectura1KeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtLectura1KeyTyped

    private void txtLectura2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLectura2KeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtLectura2KeyTyped

    private void txtLectura3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLectura3KeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtLectura3KeyTyped

    private void txtLectura4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLectura4KeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtLectura4KeyTyped

    private void txtPromedioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPromedioKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtPromedioKeyTyped

    private void grdMuestrasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grdMuestrasMouseClicked
        if (evt.getButton() == 1) {
            int fila = grdMuestras.getSelectedRow();
            String id = grdMuestras.getValueAt(fila, 0).toString();
            this.ceMuestras = this.cdPerfil.getDatosMuestras(Integer.parseInt(id));
            CatEProfundidadMuestras muestras = new CatEProfundidadMuestras();

            muestras.setProfundidadMuestraID(this.ceMuestras.getProfundidadID());
            cmbProfundidad.setSelectedItem(muestras);
            if (this.ceMuestras.getPesoMuestra() != null) {
                txtPesoMuestra.setEnabled(true);
                txtPesoMuestra.setText(String.valueOf(this.ceMuestras.getPesoMuestra()));
            }

            if (this.ceMuestras.getLectura1() != null) {
                txtLectura1.setEnabled(true);
                txtLectura1.setText(String.valueOf(this.ceMuestras.getLectura1()));
            }

            if (this.ceMuestras.getLectura2() != null) {
                txtLectura2.setEnabled(true);
                txtLectura2.setText(String.valueOf(this.ceMuestras.getLectura2()));
            }
            if (this.ceMuestras.getLectura3() != null) {
                txtLectura3.setEnabled(true);
                txtLectura3.setText(String.valueOf(this.ceMuestras.getLectura3()));
            } else {
                txtLectura3.setEnabled(false);
                txtLectura3.setText("");
            }
            if (this.ceMuestras.getLectura4() != null) {
                txtLectura4.setEnabled(true);
                txtLectura4.setText(String.valueOf(this.ceMuestras.getLectura4()));
            } else {
                txtLectura4.setEnabled(false);
                txtLectura4.setText("");
            }
            if (this.ceMuestras.getPromedio() != null) {
                txtPromedio.setEnabled(true);
                txtPromedio.setText(String.valueOf(this.ceMuestras.getPromedio()));
            } else {
                txtPromedio.setEnabled(false);
                txtPromedio.setText("");
            }
            txtClaveColecta.setText(ceMuestras.getClaveColecta());
        }
    }//GEN-LAST:event_grdMuestrasMouseClicked

    private void txtDiametroInternoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDiametroInternoFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtDiametroInterno.selectAll();
            }
        });
    }//GEN-LAST:event_txtDiametroInternoFocusGained

    private void txtDiametroExternoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDiametroExternoFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtDiametroExterno.selectAll();
            }
        });
    }//GEN-LAST:event_txtDiametroExternoFocusGained

    private void txtAlturaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAlturaFocusGained
       SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtAltura.selectAll();
            }
        });
    }//GEN-LAST:event_txtAlturaFocusGained

    private void txtPesoMuestraFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPesoMuestraFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtPesoMuestra.selectAll();
            }
        });
    }//GEN-LAST:event_txtPesoMuestraFocusGained

    private void txtLectura1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLectura1FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtLectura1.selectAll();
            }
        });
    }//GEN-LAST:event_txtLectura1FocusGained

    private void txtLectura2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLectura2FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtLectura2.selectAll();
            }
        });
    }//GEN-LAST:event_txtLectura2FocusGained

    private void txtLectura3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLectura3FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtLectura3.selectAll();
            }
        });
    }//GEN-LAST:event_txtLectura3FocusGained

    private void txtLectura4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLectura4FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtLectura4.selectAll();
            }
        });
    }//GEN-LAST:event_txtLectura4FocusGained

    private void txtPromedioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPromedioFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtPromedio.selectAll();
            }
        });
    }//GEN-LAST:event_txtPromedioFocusGained

    private void txtDiametroInternoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDiametroInternoFocusLost
        if (txtDiametroInterno.getText().isEmpty()) {
            txtDiametroInterno.setValue(null);
        }
    }//GEN-LAST:event_txtDiametroInternoFocusLost

    private void txtDiametroExternoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDiametroExternoFocusLost
        if (txtDiametroExterno.getText().isEmpty()) {
            txtDiametroExterno.setValue(null);
        }
    }//GEN-LAST:event_txtDiametroExternoFocusLost

    private void txtAlturaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAlturaFocusLost
         if (txtAltura.getText().isEmpty()) {
            txtAltura.setValue(null);
        }
    }//GEN-LAST:event_txtAlturaFocusLost

    private void txtPesoMuestraFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPesoMuestraFocusLost
        if (txtPesoMuestra.getText().isEmpty()) {
            txtPesoMuestra.setValue(null);
        }
    }//GEN-LAST:event_txtPesoMuestraFocusLost

    private void txtLectura1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLectura1FocusLost
         if (txtLectura1.getText().isEmpty()) {
            txtLectura1.setValue(null);
        }
    }//GEN-LAST:event_txtLectura1FocusLost

    private void txtLectura2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLectura2FocusLost
        if (txtLectura2.getText().isEmpty()) {
            txtLectura2.setValue(null);
        }
    }//GEN-LAST:event_txtLectura2FocusLost

    private void txtLectura3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLectura3FocusLost
         if (txtLectura3.getText().isEmpty()) {
            txtLectura3.setValue(null);
        }
    }//GEN-LAST:event_txtLectura3FocusLost

    private void txtLectura4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLectura4FocusLost
         if (txtLectura4.getText().isEmpty()) {
            txtLectura4.setValue(null);
        }
    }//GEN-LAST:event_txtLectura4FocusLost

    private void txtPromedioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPromedioFocusLost
        if (txtPromedio.getText().isEmpty()) {
            txtPromedio.setValue(null);
        }
    }//GEN-LAST:event_txtPromedioFocusLost

    private void cmbProfundidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProfundidadActionPerformed
        CatEProfundidadMuestras profundidad = (CatEProfundidadMuestras) cmbProfundidad.getSelectedItem();
        if (cmbProfundidad.getSelectedItem() == null) {
            txtPesoMuestra.setText("");
            txtPesoMuestra.setValue(null);
            txtPesoMuestra.setEnabled(false);
            txtLectura1.setText("");
            txtLectura1.setValue(null);
            txtLectura1.setEnabled(false);
            txtLectura2.setText("");
            txtLectura2.setValue(null);
            txtLectura2.setEnabled(false);
            txtLectura3.setText("");
            txtLectura3.setValue(null);
            txtLectura3.setEnabled(false);
            txtLectura4.setText("");
            txtLectura4.setValue(null);
            txtLectura4.setEnabled(false);
            txtPromedio.setText("");
            txtPromedio.setValue(null);
            txtPromedio.setEnabled(false);
        } else if (profundidad.getProfundidadMuestraID() > 3) {
            txtPesoMuestra.setEnabled(true);
            txtLectura1.setEnabled(true);
            txtLectura2.setEnabled(true);
            txtLectura3.setText("");
            txtLectura3.setValue(null);
            txtLectura3.setEnabled(false);
            txtLectura4.setText("");
            txtLectura4.setValue(null);
            txtLectura4.setEnabled(false);
            txtPromedio.setText("");
            txtPromedio.setValue(null);
            txtPromedio.setEnabled(false);
        } else {
            txtPesoMuestra.setEnabled(true);
            txtLectura1.setEnabled(true);
            txtLectura2.setEnabled(true);
            txtLectura3.setEnabled(true);
            txtLectura4.setEnabled(true);
            txtPromedio.setEnabled(true);
        }
    }//GEN-LAST:event_cmbProfundidadActionPerformed

    private void txtObservacionesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtObservacionesKeyPressed
         if(evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB){
            evt.consume();
        }
    }//GEN-LAST:event_txtObservacionesKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LblGradosLatitud;
    private javax.swing.JLabel LblGradosLongitud;
    private javax.swing.JLabel LblLatitud;
    private javax.swing.JLabel LblLongitud;
    private javax.swing.JLabel LblMinutosLatitud;
    private javax.swing.JLabel LblMinutosLongitud;
    private javax.swing.JLabel LblSegundosLatitud;
    private javax.swing.JLabel LblSegundosLongitud;
    private javax.swing.JPanel PnlCoordenadasInterior;
    private javax.swing.JButton btnContinuar;
    private javax.swing.JButton btnEliminarMuestra;
    private javax.swing.JButton btnGuardarMuestra;
    private javax.swing.JButton btnModificarMuestra;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cmbProfundidad;
    private javax.swing.JTable grdMuestras;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAltura;
    private javax.swing.JLabel lblClaveColecta;
    private javax.swing.JLabel lblDiametroExterno;
    private javax.swing.JLabel lblDiametroInterno;
    private javax.swing.JLabel lblElevacion;
    private javax.swing.JLabel lblLectura1;
    private javax.swing.JLabel lblLectura2;
    private javax.swing.JLabel lblLectura3;
    private javax.swing.JLabel lblLectura4;
    private javax.swing.JLabel lblLecturaVernier;
    private javax.swing.JLabel lblMetrosSobreNivelMar;
    private javax.swing.JLabel lblMuestrasPerfil;
    private javax.swing.JLabel lblObservaciones;
    private javax.swing.JLabel lblPesoMuestra;
    private javax.swing.JLabel lblProfundidad;
    private javax.swing.JLabel lblPromedio;
    private javax.swing.JLabel lblSitio;
    private javax.swing.JLabel lblUPM;
    private javax.swing.JFormattedTextField txtAltura;
    public javax.swing.JTextField txtClaveColecta;
    private javax.swing.JFormattedTextField txtDiametroExterno;
    private javax.swing.JFormattedTextField txtDiametroInterno;
    private javax.swing.JFormattedTextField txtElevacion;
    private javax.swing.JFormattedTextField txtGradosLatitud;
    private javax.swing.JFormattedTextField txtGradosLongitud;
    private javax.swing.JFormattedTextField txtLectura1;
    private javax.swing.JFormattedTextField txtLectura2;
    private javax.swing.JFormattedTextField txtLectura3;
    private javax.swing.JFormattedTextField txtLectura4;
    private javax.swing.JFormattedTextField txtMinutosLatitud;
    private javax.swing.JFormattedTextField txtMinutosLongitud;
    private javax.swing.JTextArea txtObservaciones;
    private javax.swing.JFormattedTextField txtPesoMuestra;
    private javax.swing.JFormattedTextField txtPromedio;
    private javax.swing.JFormattedTextField txtSegundosLatitud;
    private javax.swing.JFormattedTextField txtSegundosLongitud;
    private javax.swing.JTextField txtSitio;
    private javax.swing.JTextField txtUPM;
    // End of variables declaration//GEN-END:variables
   

}
