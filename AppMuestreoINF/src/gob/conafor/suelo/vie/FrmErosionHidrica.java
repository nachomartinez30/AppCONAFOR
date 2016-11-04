package gob.conafor.suelo.vie;

import gob.conafor.sitio.mod.CESitio;
import gob.conafor.suelo.mod.CDErosionHidrica;
import gob.conafor.suelo.mod.CDSuelo;
import gob.conafor.suelo.mod.CEErosionHidricaCanalillos;
import gob.conafor.suelo.mod.CEErosionHidricaCarcavas;
import gob.conafor.suelo.mod.CELongitudCanalillos;
import gob.conafor.suelo.mod.CELongitudCarcavas;
import gob.conafor.suelo.mod.CESuelo;
import gob.conafor.sys.mod.CDSecuencia;
import gob.conafor.sys.mod.CDSeguimientoUPM;
import gob.conafor.sys.mod.CESeccionesCapturadas;
import gob.conafor.upm.vie.UPMForms;
import gob.conafor.utils.Datos;
import gob.conafor.utils.FuncionesComunes;
import gob.conafor.utils.Tablas;
import gob.conafor.utils.ValidacionesComunes;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class FrmErosionHidrica extends javax.swing.JInternalFrame {

    private int upmID;
    private int sitioID;
    private int sitio;
    private static final int FORMATO_ID = 11;
    private int erosionHidrica;
    private int deformacionViento;
    private CESitio ceSitio = new CESitio();
    private CDErosionHidrica cdErosion = new CDErosionHidrica();
    private Integer medicionCanalillos;
    private Float profundidadCanalillos;
    private Float anchoCanalillos;
    private Float distanciaCanalillos;
    private Integer azimutCanalillos;
    private Integer medicionCarcava;
    private Float profundidadCarcava;
    private Float anchoCarcava;
    private Float distanciaCarcava;
    private Integer azimutCarcava;
    private Integer campoCanalillo;
    private Float longitudCanalillo;
    private Integer campoCarcava;
    private Float longitudCarcava;
    private Integer numeroCanalillos;
    private Float profundidadPromedioCanalillos;
    private Float anchoPromedioCanalillos;
    private Float longitudCanalillos;
    private Float volumenCanalillos;
    private Integer numeroCarcavas;
    private Float profundidadPromedioCarcavas;
    private Float anchoPromedioCarcavas;
    private Float volumenCarcavas;
    private Float promedioLongitudCanalillo;
    private Float promedioLongitudCarcava;
    private CEErosionHidricaCanalillos ceCanalillos = new CEErosionHidricaCanalillos();
    private CEErosionHidricaCarcavas ceCarcavas = new CEErosionHidricaCarcavas();
    private CELongitudCanalillos ceLongitudCanalillos = new CELongitudCanalillos();
    private CELongitudCarcavas ceLongitudCarcavas = new CELongitudCarcavas();
    private CESuelo ceSuelo = new CESuelo();
    private CDSuelo cdSuelo = new CDSuelo();
    private ValidacionesComunes validacion = new ValidacionesComunes();
    private FuncionesComunes combo = new FuncionesComunes();
    private CESeccionesCapturadas seccionActual = new CESeccionesCapturadas();
    private CESeccionesCapturadas seccionSiguiente = new CESeccionesCapturadas();
    private CDSeguimientoUPM seguimiento = new CDSeguimientoUPM();
    private Datos numeros = new Datos();
    private CDSecuencia cdSecuencia = new CDSecuencia();
    private FuncionesComunes funciones = new FuncionesComunes();
    private int revisar;

    public FrmErosionHidrica() {
        initComponents();
        /*fillCmbMedionesCanalillos();
        fillCmbLongitudCanalillo();
        fillCmbMedionesCarcava();
        fillCmbLongitudCarcavas();*/
        combo.reiniciarComboModel(cmbMedicionCanalillos);
        combo.reiniciarComboModel(cmbMedicionCarcavas);
        combo.reiniciarComboModel(cmbLongitudCanalillos);
        combo.reiniciarComboModel(cmbLongitudCarcava);
        this.erosionHidrica = 21;
        this.deformacionViento = 22;
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
        //cdSecuencia.insertFormatoCapturado(this.ceSitio, FORMATO_ID);
        llenarTablaErosionCanalillos();
        llenarTablaLongitudCanalillos();
        llenarTablaErosionCarcavas();
        llenarTablaLongitudCarcavas();
        fillCmbMedionesCanalillos();
        fillCmbMedionesCarcava();
        fillCmbLongitudCanalillo();
        fillCmbLongitudCarcavas();
        calcularSueloCanalillos();
        calcularSueloCarcavas();
        funciones.manipularBotonesMenuPrincipal(true);
        revisar = 0;
        limpiarCamposCalculadosCanalillos();
        limpiarCamposCalculadosCarcavas();
        limpiarControlesCanalillo();
        limpiarControlesCarcava();
        limpiarControlesLongitudCanalillo();
        limpiarControlesLongitudCarcava();
    }

    public void revisarErosionHidrica(CESitio ceSitio) {
        this.upmID = ceSitio.getUpmID();
        this.sitioID = ceSitio.getSitioID();
        this.sitio = ceSitio.getSitio();
        this.txtUPM.setText(String.valueOf(this.upmID));
        this.txtSitio.setText(String.valueOf(this.sitio));
        this.ceSitio.setSitioID(this.sitioID);
        this.ceSitio.setUpmID(this.upmID);
        this.ceSitio.setSitio(this.sitio);
        this.ceSitio=ceSitio;
        //System.out.println("Erosion hidrica "+this.ceSitio.getSecuencia());
        llenarTablaErosionCanalillos();
        llenarTablaLongitudCanalillos();
        llenarTablaErosionCarcavas();
        llenarTablaLongitudCarcavas();
        fillCmbMedionesCanalillos();
        fillCmbMedionesCarcava();
        fillCmbLongitudCanalillo();
        fillCmbLongitudCarcavas();
        calcularSueloCanalillos();
        calcularSueloCarcavas();
        funciones.manipularBotonesMenuPrincipal(true);
        //System.out.println("Linea 140 FRMEROSIONHIDRICA= "+funciones.habilitarCheckBox("SUELO_Canalillo", this.sitioID));
        chkCanalillos.setSelected(funciones.habilitarCheckBox("SUELO_ErosionHidricaCanalillo", this.sitioID));
        chkCarcavas.setSelected(funciones.habilitarCheckBox("SUELO_ErosionHidricaCarcava", this.sitioID));
        this.revisar = 1;
        limpiarControlesCanalillo();
        limpiarControlesCarcava();
        limpiarControlesLongitudCanalillo();
        limpiarControlesLongitudCarcava();
    }

    private void fillCmbMedionesCanalillos() {
        List<Integer> listMedionesCanalillos = new ArrayList<>();
        listMedionesCanalillos = this.cdErosion.getNoMedicionCanalillo(this.sitioID);
        if (listMedionesCanalillos != null) {
            int size = listMedionesCanalillos.size();
            for (int i = 0; i < size; i++) {
                cmbMedicionCanalillos.addItem(listMedionesCanalillos.get(i));
            }
        }
    }

    private void fillCmbLongitudCanalillo() {
        List<Integer> listLongitud = new ArrayList<>();
        listLongitud = this.cdErosion.getCampoLongitudCanalillo(this.sitioID);
        if (listLongitud != null) {
            int size = listLongitud.size();
            for (int i = 0; i < size; i++) {
                cmbLongitudCanalillos.addItem(listLongitud.get(i));
            }
        }
    }

    private void fillCmbMedionesCarcava() {
        List<Integer> listMedionesCarcava = new ArrayList<>();
        listMedionesCarcava = this.cdErosion.getNoMedicionCarcava(this.sitioID);
        if (listMedionesCarcava != null) {
            int size = listMedionesCarcava.size();
            for (int i = 0; i < size; i++) {
                cmbMedicionCarcavas.addItem(listMedionesCarcava.get(i));
            }
        }
    }

    private void fillCmbLongitudCarcavas() {
        List<Integer> listLongitud = new ArrayList<>();
        listLongitud = this.cdErosion.getCampoLongitudCarcava(this.sitioID);
        if (listLongitud != null) {
            int size = listLongitud.size();
            for (int i = 0; i < size; i++) {
                cmbLongitudCarcava.addItem(listLongitud.get(i));
            }
        }
    }

    private void llenarTablaErosionCanalillos() {
        grdCanalillos.setModel(this.cdErosion.getTablaErosionCanalillo(this.sitioID));
        grdCanalillos.getColumnModel().getColumn(2).setPreferredWidth(70);
        grdCanalillos.getColumnModel().getColumn(3).setPreferredWidth(70);
        grdCanalillos.getColumnModel().getColumn(4).setPreferredWidth(70);
        grdCanalillos.getColumnModel().getColumn(5).setPreferredWidth(70);
        grdCanalillos.getColumnModel().getColumn(6).setPreferredWidth(70);
        Tablas tabla = new Tablas();
        int[] columna_0 = {0};
        int[] column_1 = {1};
        //Ocultar campo ID
        tabla.hideColumnTable(grdCanalillos, columna_0);
        tabla.hideColumnTable(grdCanalillos, column_1);
    }

    private void llenarTablaLongitudCanalillos() {
        grdLongitudCanalillos.setModel(this.cdErosion.getTablaLongitudCanalillo(this.sitioID));
        grdLongitudCanalillos.getColumnModel().getColumn(2).setPreferredWidth(100);
        grdLongitudCanalillos.getColumnModel().getColumn(3).setPreferredWidth(100);
        Tablas tabla = new Tablas();
        int[] columna_0 = {0};
        int[] column_1 = {1};
        //Ocultar campo ID
        tabla.hideColumnTable(grdLongitudCanalillos, columna_0);
        tabla.hideColumnTable(grdLongitudCanalillos, column_1);
    }

    private void llenarTablaErosionCarcavas() {
        grdCarcavas.setModel(this.cdErosion.getTablaErosionCarcava(this.sitioID));
        grdCarcavas.getColumnModel().getColumn(2).setPreferredWidth(70);
        grdCarcavas.getColumnModel().getColumn(3).setPreferredWidth(70);
        grdCarcavas.getColumnModel().getColumn(4).setPreferredWidth(70);
        grdCarcavas.getColumnModel().getColumn(5).setPreferredWidth(70);
        grdCarcavas.getColumnModel().getColumn(6).setPreferredWidth(70);
        Tablas tabla = new Tablas();
        int[] columna_0 = {0};
        int[] column_1 = {1};
        //Ocultar campo ID
        tabla.hideColumnTable(grdCarcavas, columna_0);
        tabla.hideColumnTable(grdCarcavas, column_1);
    }

    private void llenarTablaLongitudCarcavas() {
        grdLongitudCarcavas.setModel(this.cdErosion.getTablaLongitudCarcava(this.sitioID));
        grdLongitudCarcavas.getColumnModel().getColumn(2).setPreferredWidth(100);
        grdLongitudCarcavas.getColumnModel().getColumn(3).setPreferredWidth(100);
        Tablas tabla = new Tablas();
        int[] columna_0 = {0};
        int[] column_1 = {1};
        //Ocultar campo ID
        tabla.hideColumnTable(grdLongitudCarcavas, columna_0);
        tabla.hideColumnTable(grdLongitudCarcavas, column_1);
    }

    private void fijarDatosCanalillos() {
        try {
            this.profundidadCanalillos = Float.valueOf(txtProfundidadCanalillos.getText());
        } catch (NumberFormatException e) {
            this.profundidadCanalillos = null;
        }

        try {
            this.anchoCanalillos = Float.valueOf(txtAnchoCanalillo.getText());
        } catch (NumberFormatException e) {
            this.anchoCanalillos = null;
        }

        try {
            this.distanciaCanalillos = Float.valueOf(txtDistanciaCanalillos.getText());
        } catch (NumberFormatException e) {
            this.distanciaCanalillos = null;
        }

        try {
            this.azimutCanalillos = Integer.valueOf(txtAzimutCanalillos.getText());
        } catch (NumberFormatException e) {
            this.azimutCanalillos = null;
        }
    }
    

    private void fijarDatosCarcavas() {
        try {
            this.profundidadCarcava = Float.valueOf(txtProfundidadCarcavas.getText());
        } catch (NumberFormatException e) {
            this.profundidadCarcava = null;
        }
        try {
            this.anchoCarcava = Float.valueOf(txtAnchoCarcavas.getText());
        } catch (NumberFormatException e) {
            this.anchoCarcava = null;
        }
        try {
            this.distanciaCarcava = Float.valueOf(txtDistanciaCarcavas.getText());
        } catch (NumberFormatException e) {
            this.distanciaCarcava = null;
        }
        try {
            this.azimutCarcava = Integer.valueOf(txtAzimutCarcavas.getText());
        } catch (NumberFormatException e) {
            this.azimutCarcava = null;
        }
    }

    private void fijarDatosLontudesCanalillos() {
        try {
            this.longitudCanalillo = Float.parseFloat(txtLongitudCanalillos.getText());
        } catch (NumberFormatException e) {
            this.longitudCanalillo = null;
        }
    }
    
    private void fijarDatosLongitudesCarcavas() {
        try {
            this.longitudCarcava = Float.valueOf(txtLongitudCarcavas.getText());
        } catch (NumberFormatException e) {
            this.longitudCarcava = null;
        }
    }

    private void calcularSueloCanalillos(){
        txtNoCanalillos.setText(String.valueOf(this.cdErosion.getNumeroRegistros("SUELO_LongitudCanalillo", this.sitioID)));
        txtProfundidadPromedioCanalillos.setText(String.valueOf(this.cdErosion.getPromedioCampo("SUELO_ErosionHidricaCanalillo", "Profundidad", this.sitioID)));
        txtAnchoPromedioCanalillos.setText(String.valueOf(this.cdErosion.getPromedioCampo("SUELO_ErosionHidricaCanalillo", "Ancho", this.sitioID)));
        txtPromedioLongitudCanalillos.setText(String.valueOf(this.cdErosion.getPromedioCampo("SUELO_LongitudCanalillo", "Longitud", this.sitioID)));
        Float volumen = (Float.parseFloat(txtProfundidadPromedioCanalillos.getText()) * Float.parseFloat(txtAnchoPromedioCanalillos.getText())/2)
                         * Float.parseFloat(txtPromedioLongitudCanalillos.getText());
        txtVolumenCanalillos.setText(String.valueOf(volumen));
    }
    
    private void calcularSueloCarcavas(){
        txtNoCarcavas.setText(String.valueOf(this.cdErosion.getNumeroRegistros("SUELO_LongitudCarcava", this.sitioID)));
        txtProfundidadPromedioCarcavas.setText(String.valueOf(this.cdErosion.getPromedioCampo("SUELO_ErosionHidricaCarcava", "Profundidad", this.sitioID)));
        txtAnchoPromedioCarcavas.setText(String.valueOf(this.cdErosion.getPromedioCampo("SUELO_ErosionHidricaCarcava", "Ancho", this.sitioID)));
        txtPromedioLongitudCarcavas.setText(String.valueOf(this.cdErosion.getPromedioCampo("SUELO_LongitudCarcava", "Longitud", this.sitioID)));
        Float volumen = (Float.parseFloat(txtProfundidadPromedioCarcavas.getText()) * Float.parseFloat(txtAnchoPromedioCarcavas.getText())/2)
                         * Float.parseFloat(txtPromedioLongitudCarcavas.getText());
        txtVolumenCarcavas.setText(String.valueOf(volumen));
    }
    
    private void fijarDatosSuelos() {
        try {
            this.numeroCanalillos = Integer.valueOf(txtNoCanalillos.getText());
            if (this.numeroCanalillos == 0) {
                this.numeroCanalillos = null;
            }
        } catch (NumberFormatException e) {
            this.numeroCanalillos = null;
        }
        try {
            this.profundidadPromedioCanalillos = Float.valueOf(txtProfundidadPromedioCanalillos.getText());
            if (this.profundidadPromedioCanalillos == (float) 0.0) {
                this.profundidadPromedioCanalillos = null;
            }
        } catch (NumberFormatException e) {
            this.profundidadPromedioCanalillos = null;
        }
        try {
            this.anchoPromedioCanalillos = Float.valueOf(txtAnchoPromedioCanalillos.getText());
            if (this.anchoPromedioCanalillos == (float) 0.0) {
                this.anchoPromedioCanalillos = null;
            }
        } catch (NumberFormatException e) {
            this.anchoPromedioCanalillos = null;
        }
        try {
            this.promedioLongitudCanalillo = Float.valueOf(txtPromedioLongitudCanalillos.getText());
            if (this.promedioLongitudCanalillo == (float) 0.0) {
                this.promedioLongitudCanalillo = null;
            }
        } catch (NumberFormatException e) {
            this.promedioLongitudCanalillo = null;
        }
        try {
            this.volumenCanalillos = Float.valueOf(txtVolumenCanalillos.getText());
            if (this.volumenCanalillos == (float) 0.0) {
                this.volumenCanalillos = null;
            }
        } catch (NumberFormatException e) {
            this.volumenCanalillos = null;
        }
        try {
            this.numeroCarcavas = Integer.valueOf(txtNoCarcavas.getText());
            if (this.numeroCarcavas == 0) {
                this.numeroCarcavas = null;
            }
        } catch (NumberFormatException e) {
            this.numeroCarcavas = null;
        }
        try {
            this.profundidadPromedioCarcavas = Float.valueOf(txtProfundidadPromedioCarcavas.getText());
            if (this.profundidadPromedioCarcavas == (float) 0.0) {
                this.profundidadPromedioCarcavas = null;
            }
        } catch (NumberFormatException e) {
            this.profundidadPromedioCarcavas = null;
        }
        try {
            this.anchoPromedioCarcavas = Float.valueOf(txtAnchoPromedioCarcavas.getText());
            if (this.anchoPromedioCarcavas == (float) 0.0) {
                this.anchoPromedioCarcavas = null;
            }
        } catch (NumberFormatException e) {
            this.anchoPromedioCarcavas = null;
        }
        try {
            this.promedioLongitudCarcava = Float.valueOf(txtPromedioLongitudCarcavas.getText());
            if (this.promedioLongitudCarcava == (float) 0.0) {
                this.promedioLongitudCarcava = null;
            }
        } catch (NumberFormatException e) {
            this.promedioLongitudCarcava = null;
        }
        try {
            this.volumenCarcavas = Float.valueOf(txtVolumenCarcavas.getText());
            if (this.volumenCarcavas == (float) 0.0) {
                this.volumenCarcavas = null;
            }
        } catch (NumberFormatException e) {
            this.volumenCarcavas = null;
        }
    }
    
    private void agregarDatosSuelo(){
        this.ceSuelo.setSitioID(this.sitioID);
        this.ceSuelo.setNumeroCanalillos(this.numeroCanalillos);
        this.ceSuelo.setProfundidadPromedioCanalillos(this.profundidadPromedioCanalillos);
        this.ceSuelo.setAnchoPromedioCanalillos(this.anchoPromedioCanalillos);
        this.ceSuelo.setLongitudCanalillos(this.promedioLongitudCanalillo);
        this.ceSuelo.setVolumenCanalillos(this.volumenCanalillos);
        this.ceSuelo.setNumeroCarcavas(this.numeroCarcavas);
        this.ceSuelo.setProfundidadPromedioCarcavas(this.profundidadPromedioCarcavas);
        this.ceSuelo.setAnchoPromedioCarcavas(this.anchoPromedioCarcavas);
        this.ceSuelo.setLongitudCarcavas(this.promedioLongitudCarcava);
        this.ceSuelo.setVolumenCarcavas(this.volumenCarcavas);
        
        this.cdSuelo.agregarErosionHidricaSuelo(ceSuelo);
    }
    
    private boolean validarCanalillosObligatorios(){
        if(cmbMedicionCanalillos.getSelectedItem() == null){
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar un número de medición ",
                    "Erosion hidrica", JOptionPane.INFORMATION_MESSAGE);
            cmbMedicionCanalillos.requestFocus();
            return false;
        } else if(txtProfundidadCanalillos.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar profundidad ",
                    "Erosion hidrica", JOptionPane.INFORMATION_MESSAGE);
            txtProfundidadCanalillos.requestFocus();
            return false;
        } else if(txtAnchoCanalillo.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar ancho ",
                    "Erosion hidrica", JOptionPane.INFORMATION_MESSAGE);
            txtAnchoCanalillo.requestFocus();
            return false;
        } else if(txtDistanciaCanalillos.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar distancia ",
                    "Erosion hidrica", JOptionPane.INFORMATION_MESSAGE);
            txtDistanciaCanalillos.requestFocus();
            return false;
        } else if(txtAzimutCanalillos.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar azimut ",
                    "Erosion hidrica", JOptionPane.INFORMATION_MESSAGE);
            txtAzimutCanalillos.requestFocus();
            return false;
        } else {
            return true;
        }
    }
    
    private boolean validarCarcavasObligatorios() {
        if (cmbMedicionCarcavas.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar un número de medición ",
                    "Erosion hidrica", JOptionPane.INFORMATION_MESSAGE);
            cmbMedicionCarcavas.requestFocus();
            return false;
        } else if (txtProfundidadCarcavas.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar profundidad ",
                    "Erosion hidrica", JOptionPane.INFORMATION_MESSAGE);
            txtProfundidadCarcavas.requestFocus();
            return false;
        } else if (txtAnchoCarcavas.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar ancho ",
                    "Erosion hidrica", JOptionPane.INFORMATION_MESSAGE);
            txtAnchoCarcavas.requestFocus();
            return false;
        } else if (txtDistanciaCarcavas.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar distancia ",
                    "Erosion hidrica", JOptionPane.INFORMATION_MESSAGE);
            txtDistanciaCarcavas.requestFocus();
            return false;
        } else if (txtAzimutCarcavas.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar azimut ",
                    "Erosion hidrica", JOptionPane.INFORMATION_MESSAGE);
            txtAzimutCarcavas.requestFocus();
            return false;
        } else {
            return true;
        }
    }
    
    private boolean validarLongitudCanalillosObligatorios() {
        if (cmbLongitudCanalillos.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar un número de campo de longitud ",
                    "Erosion hidrica", JOptionPane.INFORMATION_MESSAGE);
            cmbLongitudCanalillos.requestFocus();
            return false;
        } else if (txtLongitudCanalillos.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar longitud ",
                    "Erosion hidrica", JOptionPane.INFORMATION_MESSAGE);
            txtLongitudCanalillos.requestFocus();
            return false;
        } else {
            return true;
        }
    }
    
    private boolean validarLongitudCarcavasObligatorios() {
        if (cmbLongitudCarcava.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar un número de campo de longitud ",
                    "Erosion hidrica", JOptionPane.INFORMATION_MESSAGE);
            cmbLongitudCarcava.requestFocus();
            return false;
        } else if (txtLongitudCarcavas.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar longitud ",
                    "Erosion hidrica", JOptionPane.INFORMATION_MESSAGE);
            txtLongitudCarcavas.requestFocus();
            return false;
        } else {
            return true;
        }
    }
    
    private boolean validarDatosCanalillos() {
        if (validacion.esMayorCero(this.profundidadCanalillos)) {
            txtProfundidadCanalillos.requestFocus();
            return false;
        } else if (validacion.esMayorCero(this.anchoCanalillos)) {
            txtAnchoCanalillo.requestFocus();
            return false;
        } else if (validacion.esDistancia(this.distanciaCanalillos, "Erosion hidrica")) {
            txtDistanciaCanalillos.requestFocus();
            return false;
        } else if (validacion.esAzimut(this.azimutCanalillos, "Erosion hidrica")) {
            txtAzimutCanalillos.requestFocus();
            return false;
        } else {
            return true;
        }
    }
    
    private boolean validarDatosCarcavas() {
        if (validacion.esMayorCero(this.profundidadCarcava)) {
            txtProfundidadCarcavas.requestFocus();
            return false;
        } else if (validacion.esMayorCero(this.anchoCarcava)) {
            txtAnchoCarcavas.requestFocus();
            return false;
        } else if (validacion.esDistancia(this.distanciaCarcava, "Erosion hidrica")) {
            txtDistanciaCarcavas.requestFocus();
            return false;
        } else if (validacion.esAzimut(this.azimutCarcava, "Erosion hidrica")) {
            txtAzimutCarcavas.requestFocus();
            return false;
        } else {
            return true;
        }
    }
    
    private boolean validarDatosLongitudCanalillos() {
        if (validacion.esMayorCero(this.longitudCanalillo)) {
            txtLongitudCanalillos.requestFocus();
            return false;
        } else {
            return true;
        }
    }
    
    private boolean validarDatosLongitudCarcavas() {
        if (validacion.esMayorCero(this.longitudCarcava)) {
            txtLongitudCarcavas.requestFocus();
            return false;
        } else {
            return true;
        }
    }
    
    private void crearCanalillos(){
        Integer medicion = (Integer) cmbMedicionCanalillos.getSelectedItem();
        
        this.ceCanalillos.setSitioID(this.sitioID);
        this.ceCanalillos.setMedicion(medicion);
        this.ceCanalillos.setProfundidad(this.profundidadCanalillos);
        this.ceCanalillos.setAncho(this.anchoCanalillos);
        this.ceCanalillos.setDistancia(this.distanciaCanalillos);
        this.ceCanalillos.setAzimut(this.azimutCanalillos);
        
        this.cdErosion.insertErosionCanalillo(ceCanalillos);
    }
    
    private void crearCarcavas(){
        Integer medicion = (Integer) cmbMedicionCarcavas.getSelectedItem();
        
        this.ceCarcavas.setSitioID(this.sitioID);
        this.ceCarcavas.setMedicion(medicion);
        this.ceCarcavas.setProfundidad(this.profundidadCarcava);
        this.ceCarcavas.setAncho(this.anchoCarcava);
        this.ceCarcavas.setDistancia(this.distanciaCarcava);
        this.ceCarcavas.setAzimut(this.azimutCarcava);
        
        this.cdErosion.insertErosionCarcava(ceCarcavas);
    }
    
    private void crearLongitudCanalillo(){
        Integer campoLongitud = (Integer) cmbLongitudCanalillos.getSelectedItem();
        
        this.ceLongitudCanalillos.setSitioID(this.sitioID);
        this.ceLongitudCanalillos.setCampoLongitud(campoLongitud);
        this.ceLongitudCanalillos.setLongitud(this.longitudCanalillo);
        
        this.cdErosion.insertLongitudCanalillo(ceLongitudCanalillos);
    }
    
    private void crearLongitudCarcava(){
        Integer campoLongitud = (Integer) cmbLongitudCarcava.getSelectedItem();
        
        this.ceLongitudCarcavas.setSitioID(this.sitioID);
        this.ceLongitudCarcavas.setCampoLongitud(campoLongitud);
        this.ceLongitudCarcavas.setLongitud(this.longitudCarcava);
        
        this.cdErosion.insertLongitudCarcava(ceLongitudCarcavas);
    }
    
    /*private void enviarInformacionSuelo(){
        this.ceSuelo.setSitioID(sitioID);
        this.ceSuelo.setNumeroCanalillos(this.numeroCanalillos);
        this.ceSuelo.setProfundidadPromedioCanalillos(this.profundidadPromedioCanalillos);
        this.ceSuelo.setAnchoPromedioCanalillos(this.anchoPromedioCanalillos);
        this.ceSuelo.setLongitudCanalillos(this.longitudCanalillos);
        this.ceSuelo.setVolumenCanalillos(this.volumenCanalillos);
        
        this.ceSuelo.setNumeroCarcavas(this.numeroCarcavas);
        this.ceSuelo.setProfundidadPromedioCarcavas(this.profundidadPromedioCarcavas);
        this.ceSuelo.setAnchoPromedioCarcavas(this.anchoPromedioCarcavas);
        this.ceSuelo.setLongitudCarcavas(this.longitudCarcava);
        this.ceSuelo.setVolumenCarcavas(this.volumenCarcavas);
        
        this.cdSuelo.agregarErosionHidricaSuelo(ceSuelo);
    }*/
    
    private void modificarCanalillos() {
        try {
            int fila = grdCanalillos.getSelectedRow();
            String registro = grdCanalillos.getValueAt(fila, 0).toString();

            this.ceCanalillos.setErosionCanalillosID(Integer.parseInt(registro));
            this.ceCanalillos.setProfundidad(this.profundidadCanalillos);
            this.ceCanalillos.setAncho(this.anchoCanalillos);
            this.ceCanalillos.setDistancia(this.distanciaCanalillos);
            this.ceCanalillos.setAzimut(this.azimutCanalillos);

            this.cdErosion.updateErosionCanalillo(ceCanalillos);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla de canales o canalillos "
                    + e.getClass().getName() + " : " + e.getMessage(), "Erosion hidrica", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void modificarCarcavas() {
        try {
            int fila = grdCarcavas.getSelectedRow();
            String registro = grdCarcavas.getValueAt(fila, 0).toString();
            this.ceCarcavas.setErosionCarcavasID(Integer.parseInt(registro));
            this.ceCarcavas.setProfundidad(this.profundidadCarcava);
            this.ceCarcavas.setAncho(this.anchoCarcava);
            this.ceCarcavas.setDistancia(this.distanciaCarcava);
            this.ceCarcavas.setAzimut(this.azimutCarcava);

            this.cdErosion.updateErosionCarcava(ceCarcavas);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla de carcavas "
                    + e.getClass().getName() + " : " + e.getMessage(), "Erosion hidrica", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void modificarlongitudCanalillos() {
        try {
            int fila = grdLongitudCanalillos.getSelectedRow();
            String registro = grdLongitudCanalillos.getValueAt(fila, 0).toString();
            this.ceLongitudCanalillos.setLongitudCanalillosID(Integer.parseInt(registro));
            this.ceLongitudCanalillos.setLongitud(this.longitudCanalillo);

            this.cdErosion.updateLongitudCanalillo(ceLongitudCanalillos);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla de longitud de canalillos "
                    + e.getClass().getName() + " : " + e.getMessage(), "Erosion hidrica", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void modificarLongitudCarcavas() {
        try {
            int fila = grdLongitudCarcavas.getSelectedRow();
            String registro = grdLongitudCarcavas.getValueAt(fila, 0).toString();
            this.ceLongitudCarcavas.setLongitudCarcavasID(Integer.parseInt(registro));
            this.ceLongitudCarcavas.setLongitud(this.longitudCarcava);

            this.cdErosion.updateLongitudCarcava(ceLongitudCarcavas);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla de longitud de carcavas "
                    + e.getClass().getName() + " : " + e.getMessage(), "Erosion hidrica", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void eliminarCanalillo() {
        try {
            int fila = grdCanalillos.getSelectedRow();
            String registro = grdCanalillos.getValueAt(fila, 0).toString();
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Esta seguro de borrar el registro de canalillos?",
                    "Erosion hidrica", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (respuesta == JOptionPane.YES_OPTION) {
                this.cdErosion.deleteErosionCanalillo(Integer.parseInt(registro));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla de canalillos"
                    + "", "Erosion hidrica", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarCarcava() {
        try {
            int fila = grdCarcavas.getSelectedRow();
            String registro = grdCarcavas.getValueAt(fila, 0).toString();
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Esta seguro de borrar el registro de carcava?",
                    "Erosion hidrica", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (respuesta == JOptionPane.YES_OPTION) {
                this.cdErosion.deleteErosionCarcava(Integer.parseInt(registro));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla de carcava"
                    + "", "Erosion hidrica", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void eliminarLongitudCanalillo() {
        try {
            int fila = grdLongitudCanalillos.getSelectedRow();
            String registro = grdLongitudCanalillos.getValueAt(fila, 0).toString();
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Esta seguro de borrar el registro de longitud de canalillo?",
                    "Erosion hidrica", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (respuesta == JOptionPane.YES_OPTION) {
                this.cdErosion.deleteLongitudCanalillo(Integer.parseInt(registro));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla de longitud de canalillo"
                    + "", "Erosion hidrica", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void eliminarLongitudCarcava() {
        try {
            int fila = grdLongitudCarcavas.getSelectedRow();
            String registro = grdLongitudCarcavas.getValueAt(fila, 0).toString();
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Esta seguro de borrar el registro de longitud de carcavas?",
                    "Erosion hidrica", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (respuesta == JOptionPane.YES_OPTION) {
                this.cdErosion.deleteLongitudCarcava(Integer.parseInt(registro));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla de longitud de carcavas"
                    + "", "Erosion hidrica", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void limpiarControlesCanalillo(){
        cmbMedicionCanalillos.setSelectedItem(null);
        txtProfundidadCanalillos.setText("");
        txtAnchoCanalillo.setText("");
        txtDistanciaCanalillos.setText("");
        txtAzimutCanalillos.setText("");
        cmbMedicionCanalillos.requestFocus();
    }
    
    private void limpiarControlesCarcava(){
        cmbMedicionCarcavas.setSelectedItem(null);
        txtProfundidadCarcavas.setText("");
        txtAnchoCarcavas.setText("");
        txtDistanciaCarcavas.setText("");
        txtAzimutCarcavas.setText("");
        cmbMedicionCarcavas.requestFocus();
    }
    
    private void limpiarControlesLongitudCanalillo(){
        cmbLongitudCanalillos.setSelectedItem(null);
        txtLongitudCanalillos.setText("");
        cmbLongitudCanalillos.requestFocus();
    }
    
    private void limpiarControlesLongitudCarcava(){
        cmbLongitudCarcava.setSelectedItem(null);
        txtLongitudCarcavas.setText("");
        cmbLongitudCarcava.requestFocus();
    }
  
    private void manipularControlesCanalillos(boolean abilitar) {
        if (abilitar == true) {
            cmbMedicionCanalillos.setEnabled(true);
            txtProfundidadCanalillos.setEnabled(true);
            txtAnchoCanalillo.setEnabled(true);
            txtDistanciaCanalillos.setEnabled(true);
            txtAzimutCanalillos.setEnabled(true);
            btnAgregarCanalillos.setEnabled(true);
            btnModificarCanalillos.setEnabled(true);
            btnEliminarCanalillos.setEnabled(true);
            cmbLongitudCanalillos.setEnabled(true);
            txtLongitudCanalillos.setEnabled(true);
            btnAgregarLongitudCanalillos.setEnabled(true);
            btnModificarLongitudCanalillos.setEnabled(true);
            btnEliminarLongitudCanalillos.setEnabled(true);
        } else {
            cmbMedicionCanalillos.setEnabled(false);
            cmbMedicionCanalillos.setSelectedItem(null);
            txtProfundidadCanalillos.setEnabled(false);
            txtAnchoCanalillo.setEnabled(false);
            txtDistanciaCanalillos.setEnabled(false);
            txtAzimutCanalillos.setEnabled(false);
            btnAgregarCanalillos.setEnabled(false);
            btnModificarCanalillos.setEnabled(false);
            btnEliminarCanalillos.setEnabled(false);
            cmbLongitudCanalillos.setEnabled(false);
            cmbLongitudCanalillos.setSelectedItem(null);
            txtLongitudCanalillos.setEnabled(false);
            btnAgregarLongitudCanalillos.setEnabled(false);
            btnModificarLongitudCanalillos.setEnabled(false);
            btnEliminarLongitudCanalillos.setEnabled(false);
        }
    }

    private void manipularControlesCarcavas(boolean abilitar) {
        if (abilitar == true) {
            cmbMedicionCarcavas.setEnabled(true);
            txtProfundidadCarcavas.setEnabled(true);
            txtAnchoCarcavas.setEnabled(true);
            txtDistanciaCarcavas.setEnabled(true);
            txtAzimutCarcavas.setEnabled(true);
            btnAgregarCarcava.setEnabled(true);
            btnModificarCarcava.setEnabled(true);
            btnEliminarCarcava.setEnabled(true);
            cmbLongitudCarcava.setEnabled(true);
            txtLongitudCarcavas.setEnabled(true);
            btnAgregarLongitudCarcava.setEnabled(true);
            btnEliminarLongitudCarcava.setEnabled(true);
            btnModificarLongitudCarcava.setEnabled(true);
        } else {
            cmbMedicionCarcavas.setEnabled(false);
            cmbMedicionCarcavas.setSelectedItem(null);
            txtProfundidadCarcavas.setEnabled(false);
            txtAnchoCarcavas.setEnabled(false);
            txtDistanciaCarcavas.setEnabled(false);
            txtAzimutCarcavas.setEnabled(false);
            btnAgregarCarcava.setEnabled(false);
            btnModificarCarcava.setEnabled(false);
            btnEliminarCarcava.setEnabled(false);
            cmbLongitudCarcava.setEnabled(false);
            cmbLongitudCarcava.setSelectedItem(null);
            txtLongitudCarcavas.setEnabled(false);
            btnAgregarLongitudCarcava.setEnabled(false);
            btnEliminarLongitudCarcava.setEnabled(false);
            btnModificarLongitudCarcava.setEnabled(false);
        }
    }
    
    private void limpiarCamposCalculadosCanalillos(){
        txtNoCanalillos.setText("");
        txtNoCanalillos.setValue(null);
        txtProfundidadPromedioCanalillos.setText("");
        txtProfundidadPromedioCanalillos.setValue(null);
        txtAnchoPromedioCanalillos.setText("");
        txtAnchoPromedioCanalillos.setValue(null);
        txtPromedioLongitudCanalillos.setText("");
        txtPromedioLongitudCanalillos.setValue(null);
        txtVolumenCanalillos.setText("");
        txtVolumenCanalillos.setValue(null);
    }
    
    private void limpiarCamposCalculadosCarcavas(){
        txtNoCarcavas.setText("");
        txtNoCarcavas.setValue(null);
        txtProfundidadPromedioCarcavas.setText("");
        txtProfundidadPromedioCarcavas.setValue(null);
        txtAnchoPromedioCarcavas.setText("");
        txtAnchoPromedioCarcavas.setValue(null);
        txtLongitudCarcavas.setText("");
        txtLongitudCarcavas.setValue(null);
        txtVolumenCarcavas.setText("");
        txtVolumenCarcavas.setValue(null);
        txtPromedioLongitudCarcavas.setText("");
        txtPromedioLongitudCarcavas.setValue(null);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblErosionHidrica = new javax.swing.JLabel();
        lblUPM = new javax.swing.JLabel();
        txtUPM = new javax.swing.JTextField();
        lblSitio = new javax.swing.JLabel();
        txtSitio = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lblMedicionCanales = new javax.swing.JLabel();
        cmbMedicionCanalillos = new javax.swing.JComboBox();
        lblProfundidadCanales = new javax.swing.JLabel();
        lblAnchoCanales = new javax.swing.JLabel();
        lblDistanciaCanales = new javax.swing.JLabel();
        lblAzimtCanales = new javax.swing.JLabel();
        txtProfundidadCanalillos = new javax.swing.JFormattedTextField();
        txtAnchoCanalillo = new javax.swing.JFormattedTextField();
        txtDistanciaCanalillos = new javax.swing.JFormattedTextField();
        txtAzimutCanalillos = new javax.swing.JFormattedTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        grdCanalillos = new javax.swing.JTable();
        btnAgregarCanalillos = new javax.swing.JButton();
        btnModificarCanalillos = new javax.swing.JButton();
        btnEliminarCanalillos = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        lblCampoCanales = new javax.swing.JLabel();
        cmbLongitudCanalillos = new javax.swing.JComboBox();
        lblLongitudCanales = new javax.swing.JLabel();
        txtLongitudCanalillos = new javax.swing.JFormattedTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        grdLongitudCanalillos = new javax.swing.JTable();
        btnAgregarLongitudCanalillos = new javax.swing.JButton();
        btnModificarLongitudCanalillos = new javax.swing.JButton();
        btnEliminarLongitudCanalillos = new javax.swing.JButton();
        lblNoCanalillos = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        lblProfundidadPromedioCanalillos = new javax.swing.JLabel();
        txtProfundidadPromedioCanalillos = new javax.swing.JFormattedTextField();
        lblProfundidadPromedioCanalillos1 = new javax.swing.JLabel();
        txtAnchoPromedioCanalillos = new javax.swing.JFormattedTextField();
        lblProfundidadPromedioCanalillos2 = new javax.swing.JLabel();
        txtPromedioLongitudCanalillos = new javax.swing.JFormattedTextField();
        lblProfundidadPromedioCanalillos3 = new javax.swing.JLabel();
        txtVolumenCanalillos = new javax.swing.JFormattedTextField();
        txtNoCanalillos = new javax.swing.JFormattedTextField();
        chkCanalillos = new javax.swing.JCheckBox();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        lblMedicionCarcavas = new javax.swing.JLabel();
        cmbMedicionCarcavas = new javax.swing.JComboBox();
        lblProfundidadCarcavas = new javax.swing.JLabel();
        lblAnchoCarcavas = new javax.swing.JLabel();
        lblDistancia = new javax.swing.JLabel();
        lblAzimut = new javax.swing.JLabel();
        txtProfundidadCarcavas = new javax.swing.JFormattedTextField();
        txtAnchoCarcavas = new javax.swing.JFormattedTextField();
        txtDistanciaCarcavas = new javax.swing.JFormattedTextField();
        txtAzimutCarcavas = new javax.swing.JFormattedTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        grdCarcavas = new javax.swing.JTable();
        btnAgregarCarcava = new javax.swing.JButton();
        btnModificarCarcava = new javax.swing.JButton();
        btnEliminarCarcava = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        cmbLongitudCarcava = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        txtLongitudCarcavas = new javax.swing.JFormattedTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        grdLongitudCarcavas = new javax.swing.JTable();
        btnAgregarLongitudCarcava = new javax.swing.JButton();
        btnModificarLongitudCarcava = new javax.swing.JButton();
        btnEliminarLongitudCarcava = new javax.swing.JButton();
        lblNoCarcava = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        lblProfundidadPromedioCanalillos4 = new javax.swing.JLabel();
        txtProfundidadPromedioCarcavas = new javax.swing.JFormattedTextField();
        lblProfundidadPromedioCanalillos5 = new javax.swing.JLabel();
        txtAnchoPromedioCarcavas = new javax.swing.JFormattedTextField();
        lblProfundidadPromedioCanalillos6 = new javax.swing.JLabel();
        txtPromedioLongitudCarcavas = new javax.swing.JFormattedTextField();
        lblProfundidadPromedioCanalillos7 = new javax.swing.JLabel();
        txtVolumenCarcavas = new javax.swing.JFormattedTextField();
        txtNoCarcavas = new javax.swing.JFormattedTextField();
        chkCarcavas = new javax.swing.JCheckBox();
        btnContinuar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setTitle("Erosión hídrica con deformación del terreno");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/gob/conafor/utils/logo_conafor.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(940, 650));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        lblErosionHidrica.setBackground(new java.awt.Color(153, 153, 153));
        lblErosionHidrica.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblErosionHidrica.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblErosionHidrica.setText("Erosión hídrica con deformación del terreno");
        lblErosionHidrica.setOpaque(true);

        lblUPM.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblUPM.setText("UPMID:");

        txtUPM.setEditable(false);
        txtUPM.setEnabled(false);

        lblSitio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblSitio.setText("Sitio:");

        txtSitio.setEditable(false);
        txtSitio.setEnabled(false);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Registro de canalillos/canales"));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblMedicionCanales.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMedicionCanales.setText("Medición");

        lblProfundidadCanales.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProfundidadCanales.setText("Profundidad (cm)");

        lblAnchoCanales.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAnchoCanales.setText("Ancho (cm)");

        lblDistanciaCanales.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDistanciaCanales.setText("Distancia (m)");

        lblAzimtCanales.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAzimtCanales.setText("Azimut");

        txtProfundidadCanalillos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtProfundidadCanalillos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtProfundidadCanalillosFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtProfundidadCanalillosFocusLost(evt);
            }
        });
        txtProfundidadCanalillos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtProfundidadCanalillosKeyTyped(evt);
            }
        });

        txtAnchoCanalillo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtAnchoCanalillo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtAnchoCanalilloFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtAnchoCanalilloFocusLost(evt);
            }
        });
        txtAnchoCanalillo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAnchoCanalilloKeyTyped(evt);
            }
        });

        txtDistanciaCanalillos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtDistanciaCanalillos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDistanciaCanalillosFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDistanciaCanalillosFocusLost(evt);
            }
        });
        txtDistanciaCanalillos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDistanciaCanalillosKeyTyped(evt);
            }
        });

        txtAzimutCanalillos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtAzimutCanalillos.setNextFocusableComponent(btnAgregarCanalillos);
        txtAzimutCanalillos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtAzimutCanalillosFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtAzimutCanalillosFocusLost(evt);
            }
        });
        txtAzimutCanalillos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAzimutCanalillosKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblMedicionCanales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbMedicionCanalillos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblProfundidadCanales, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProfundidadCanalillos, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAnchoCanales, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAnchoCanalillo, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDistanciaCanales, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDistanciaCanalillos, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAzimtCanales, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAzimutCanalillos, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblProfundidadCanales)
                    .addComponent(lblAnchoCanales)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(lblAzimtCanales)
                            .addGap(26, 26, 26))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(lblDistanciaCanales)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtDistanciaCanalillos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtAzimutCanalillos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(lblMedicionCanales)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbMedicionCanalillos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtProfundidadCanalillos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAnchoCanalillo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 460, -1));

        grdCanalillos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        grdCanalillos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grdCanalillosMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(grdCanalillos);

        jPanel2.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 460, 90));

        btnAgregarCanalillos.setText("Agregar");
        btnAgregarCanalillos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarCanalillosActionPerformed(evt);
            }
        });
        jPanel2.add(btnAgregarCanalillos, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, -1, -1));

        btnModificarCanalillos.setText("Modificar");
        btnModificarCanalillos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarCanalillosActionPerformed(evt);
            }
        });
        jPanel2.add(btnModificarCanalillos, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, -1, -1));

        btnEliminarCanalillos.setText("Eliminar");
        btnEliminarCanalillos.setNextFocusableComponent(cmbMedicionCanalillos);
        btnEliminarCanalillos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarCanalillosActionPerformed(evt);
            }
        });
        jPanel2.add(btnEliminarCanalillos, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 120, -1, -1));

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblCampoCanales.setText("Campo longitud");

        lblLongitudCanales.setText("Longitud (cm)");

        txtLongitudCanalillos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtLongitudCanalillos.setNextFocusableComponent(btnAgregarLongitudCanalillos);
        txtLongitudCanalillos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtLongitudCanalillosFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtLongitudCanalillosFocusLost(evt);
            }
        });
        txtLongitudCanalillos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLongitudCanalillosKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblCampoCanales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbLongitudCanalillos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLongitudCanales, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLongitudCanalillos, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCampoCanales)
                    .addComponent(lblLongitudCanales))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbLongitudCanalillos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLongitudCanalillos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 50, -1, -1));

        grdLongitudCanalillos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        grdLongitudCanalillos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grdLongitudCanalillosMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(grdLongitudCanalillos);

        jPanel2.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 150, 244, 90));

        btnAgregarLongitudCanalillos.setText("Agregar");
        btnAgregarLongitudCanalillos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarLongitudCanalillosActionPerformed(evt);
            }
        });
        jPanel2.add(btnAgregarLongitudCanalillos, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 120, -1, -1));

        btnModificarLongitudCanalillos.setText("Modificar");
        btnModificarLongitudCanalillos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarLongitudCanalillosActionPerformed(evt);
            }
        });
        jPanel2.add(btnModificarLongitudCanalillos, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 120, -1, -1));

        btnEliminarLongitudCanalillos.setText("Eliminar");
        btnEliminarLongitudCanalillos.setNextFocusableComponent(cmbLongitudCanalillos);
        btnEliminarLongitudCanalillos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarLongitudCanalillosActionPerformed(evt);
            }
        });
        jPanel2.add(btnEliminarLongitudCanalillos, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 120, -1, -1));

        lblNoCanalillos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNoCanalillos.setText("No de canalillos");
        jPanel2.add(lblNoCanalillos, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 20, 107, -1));

        jPanel8.setBackground(new java.awt.Color(204, 204, 204));

        lblProfundidadPromedioCanalillos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProfundidadPromedioCanalillos.setText("Profundidad promedio");

        txtProfundidadPromedioCanalillos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("##0.###"))));
        txtProfundidadPromedioCanalillos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtProfundidadPromedioCanalillos.setEnabled(false);
        txtProfundidadPromedioCanalillos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtProfundidadPromedioCanalillos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtProfundidadPromedioCanalillosFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtProfundidadPromedioCanalillosFocusLost(evt);
            }
        });

        lblProfundidadPromedioCanalillos1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProfundidadPromedioCanalillos1.setText("Ancho promedio");

        txtAnchoPromedioCanalillos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("##0.###"))));
        txtAnchoPromedioCanalillos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAnchoPromedioCanalillos.setEnabled(false);
        txtAnchoPromedioCanalillos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtAnchoPromedioCanalillos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtAnchoPromedioCanalillosFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtAnchoPromedioCanalillosFocusLost(evt);
            }
        });

        lblProfundidadPromedioCanalillos2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProfundidadPromedioCanalillos2.setText("Longitud (cm)");

        txtPromedioLongitudCanalillos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("##0.###"))));
        txtPromedioLongitudCanalillos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPromedioLongitudCanalillos.setEnabled(false);
        txtPromedioLongitudCanalillos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtPromedioLongitudCanalillos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPromedioLongitudCanalillosFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPromedioLongitudCanalillosFocusLost(evt);
            }
        });

        lblProfundidadPromedioCanalillos3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProfundidadPromedioCanalillos3.setText("Volumen cm3");

        txtVolumenCanalillos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("##0.###"))));
        txtVolumenCanalillos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtVolumenCanalillos.setEnabled(false);
        txtVolumenCanalillos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtVolumenCanalillos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtVolumenCanalillosFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtVolumenCanalillosFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblProfundidadPromedioCanalillos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtProfundidadPromedioCanalillos)
                    .addComponent(lblProfundidadPromedioCanalillos1, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                    .addComponent(txtAnchoPromedioCanalillos)
                    .addComponent(lblProfundidadPromedioCanalillos2, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                    .addComponent(txtPromedioLongitudCanalillos)
                    .addComponent(lblProfundidadPromedioCanalillos3, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                    .addComponent(txtVolumenCanalillos))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(lblProfundidadPromedioCanalillos)
                .addGap(1, 1, 1)
                .addComponent(txtProfundidadPromedioCanalillos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(lblProfundidadPromedioCanalillos1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAnchoPromedioCanalillos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(lblProfundidadPromedioCanalillos2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPromedioLongitudCanalillos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblProfundidadPromedioCanalillos3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtVolumenCanalillos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 70, 130, 170));

        txtNoCanalillos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("##0"))));
        txtNoCanalillos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNoCanalillos.setEnabled(false);
        txtNoCanalillos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtNoCanalillos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNoCanalillosFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNoCanalillosFocusLost(evt);
            }
        });
        jPanel2.add(txtNoCanalillos, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 40, 110, -1));

        chkCanalillos.setBackground(new java.awt.Color(204, 204, 204));
        chkCanalillos.setSelected(true);
        chkCanalillos.setText("Registro de canalillos/ canales");
        chkCanalillos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCanalillosActionPerformed(evt);
            }
        });
        jPanel2.add(chkCanalillos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Registro de cárcavas"));

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblMedicionCarcavas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMedicionCarcavas.setText("Medición");

        cmbMedicionCarcavas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMedicionCarcavasActionPerformed(evt);
            }
        });

        lblProfundidadCarcavas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProfundidadCarcavas.setText("Profundidad (cm)");

        lblAnchoCarcavas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAnchoCarcavas.setText("Ancho (cm)");

        lblDistancia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDistancia.setText("Distancia (m)");

        lblAzimut.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAzimut.setText("Azimut");

        txtProfundidadCarcavas.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtProfundidadCarcavas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtProfundidadCarcavasFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtProfundidadCarcavasFocusLost(evt);
            }
        });
        txtProfundidadCarcavas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtProfundidadCarcavasKeyTyped(evt);
            }
        });

        txtAnchoCarcavas.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtAnchoCarcavas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtAnchoCarcavasFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtAnchoCarcavasFocusLost(evt);
            }
        });
        txtAnchoCarcavas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAnchoCarcavasKeyTyped(evt);
            }
        });

        txtDistanciaCarcavas.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtDistanciaCarcavas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDistanciaCarcavasFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDistanciaCarcavasFocusLost(evt);
            }
        });
        txtDistanciaCarcavas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDistanciaCarcavasKeyTyped(evt);
            }
        });

        txtAzimutCarcavas.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtAzimutCarcavas.setNextFocusableComponent(btnAgregarCarcava);
        txtAzimutCarcavas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtAzimutCarcavasFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtAzimutCarcavasFocusLost(evt);
            }
        });
        txtAzimutCarcavas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAzimutCarcavasKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblMedicionCarcavas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbMedicionCarcavas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblProfundidadCarcavas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtProfundidadCarcavas, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lblAnchoCarcavas, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblDistancia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(txtAnchoCarcavas, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDistanciaCarcavas, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblAzimut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtAzimutCarcavas, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblMedicionCarcavas)
                        .addComponent(lblProfundidadCarcavas))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblAzimut, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblDistancia)
                        .addComponent(lblAnchoCarcavas)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cmbMedicionCarcavas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAnchoCarcavas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDistanciaCarcavas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAzimutCarcavas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txtProfundidadCarcavas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        grdCarcavas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        grdCarcavas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grdCarcavasMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(grdCarcavas);

        btnAgregarCarcava.setText("Agregar");
        btnAgregarCarcava.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarCarcavaActionPerformed(evt);
            }
        });

        btnModificarCarcava.setText("Modificar");
        btnModificarCarcava.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarCarcavaActionPerformed(evt);
            }
        });

        btnEliminarCarcava.setText("Eliminar");
        btnEliminarCarcava.setNextFocusableComponent(cmbMedicionCarcavas);
        btnEliminarCarcava.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarCarcavaActionPerformed(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(204, 204, 204));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel14.setText("Campo longitud");

        jLabel15.setText("Longitud (cm)");

        txtLongitudCarcavas.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtLongitudCarcavas.setNextFocusableComponent(btnAgregarLongitudCarcava);
        txtLongitudCarcavas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtLongitudCarcavasFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtLongitudCarcavasFocusLost(evt);
            }
        });
        txtLongitudCarcavas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLongitudCarcavasKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbLongitudCarcava, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLongitudCarcavas, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbLongitudCarcava, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLongitudCarcavas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        grdLongitudCarcavas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        grdLongitudCarcavas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grdLongitudCarcavasMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(grdLongitudCarcavas);

        btnAgregarLongitudCarcava.setText("Agregar");
        btnAgregarLongitudCarcava.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarLongitudCarcavaActionPerformed(evt);
            }
        });

        btnModificarLongitudCarcava.setText("Modificar");
        btnModificarLongitudCarcava.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarLongitudCarcavaActionPerformed(evt);
            }
        });

        btnEliminarLongitudCarcava.setText("Eliminar");
        btnEliminarLongitudCarcava.setNextFocusableComponent(cmbLongitudCarcava);
        btnEliminarLongitudCarcava.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarLongitudCarcavaActionPerformed(evt);
            }
        });

        lblNoCarcava.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNoCarcava.setText("No de carcavas");

        jPanel9.setBackground(new java.awt.Color(204, 204, 204));

        lblProfundidadPromedioCanalillos4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProfundidadPromedioCanalillos4.setText("Profundidad promedio");

        txtProfundidadPromedioCarcavas.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("##0.###"))));
        txtProfundidadPromedioCarcavas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtProfundidadPromedioCarcavas.setEnabled(false);
        txtProfundidadPromedioCarcavas.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtProfundidadPromedioCarcavas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtProfundidadPromedioCarcavasFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtProfundidadPromedioCarcavasFocusLost(evt);
            }
        });

        lblProfundidadPromedioCanalillos5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProfundidadPromedioCanalillos5.setText("Ancho promedio");

        txtAnchoPromedioCarcavas.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("##0.###"))));
        txtAnchoPromedioCarcavas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAnchoPromedioCarcavas.setEnabled(false);
        txtAnchoPromedioCarcavas.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtAnchoPromedioCarcavas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtAnchoPromedioCarcavasFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtAnchoPromedioCarcavasFocusLost(evt);
            }
        });

        lblProfundidadPromedioCanalillos6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProfundidadPromedioCanalillos6.setText("Longitud (cm)");

        txtPromedioLongitudCarcavas.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("##0.###"))));
        txtPromedioLongitudCarcavas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPromedioLongitudCarcavas.setEnabled(false);
        txtPromedioLongitudCarcavas.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtPromedioLongitudCarcavas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPromedioLongitudCarcavasFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPromedioLongitudCarcavasFocusLost(evt);
            }
        });

        lblProfundidadPromedioCanalillos7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProfundidadPromedioCanalillos7.setText("Volumen cm3");

        txtVolumenCarcavas.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("##0.###"))));
        txtVolumenCarcavas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtVolumenCarcavas.setEnabled(false);
        txtVolumenCarcavas.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtVolumenCarcavas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtVolumenCarcavasFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtVolumenCarcavasFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblProfundidadPromedioCanalillos4, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                    .addComponent(txtProfundidadPromedioCarcavas)
                    .addComponent(lblProfundidadPromedioCanalillos5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtAnchoPromedioCarcavas)
                    .addComponent(lblProfundidadPromedioCanalillos6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPromedioLongitudCarcavas)
                    .addComponent(lblProfundidadPromedioCanalillos7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtVolumenCarcavas))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(lblProfundidadPromedioCanalillos4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtProfundidadPromedioCarcavas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(lblProfundidadPromedioCanalillos5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAnchoPromedioCarcavas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(lblProfundidadPromedioCanalillos6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPromedioLongitudCarcavas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblProfundidadPromedioCanalillos7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtVolumenCarcavas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 14, Short.MAX_VALUE))
        );

        txtNoCarcavas.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("##0"))));
        txtNoCarcavas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNoCarcavas.setEnabled(false);
        txtNoCarcavas.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtNoCarcavas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNoCarcavasFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNoCarcavasFocusLost(evt);
            }
        });

        chkCarcavas.setBackground(new java.awt.Color(204, 204, 204));
        chkCarcavas.setSelected(true);
        chkCarcavas.setText("Registro de cárcavas");
        chkCarcavas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCarcavasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(98, 98, 98)
                                .addComponent(btnAgregarCarcava)
                                .addGap(18, 18, 18)
                                .addComponent(btnModificarCarcava)
                                .addGap(18, 18, 18)
                                .addComponent(btnEliminarCarcava))
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(btnAgregarLongitudCarcava)
                                .addGap(14, 14, 14)
                                .addComponent(btnModificarLongitudCarcava)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnEliminarLongitudCarcava)
                                .addGap(2, 2, 2))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(chkCarcavas))
                        .addGap(0, 1, Short.MAX_VALUE)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtNoCarcavas, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNoCarcava, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblNoCarcava))
                    .addComponent(chkCarcavas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnAgregarCarcava)
                                .addComponent(btnModificarCarcava)
                                .addComponent(btnEliminarCarcava))
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnAgregarLongitudCarcava)
                                .addComponent(btnModificarLongitudCarcava)
                                .addComponent(btnEliminarLongitudCarcava)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(11, 11, 11))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(txtNoCarcavas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(lblUPM)
                .addGap(10, 10, 10)
                .addComponent(txtUPM, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblSitio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSitio, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblErosionHidrica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(364, 364, 364)
                .addComponent(btnContinuar)
                .addGap(18, 18, 18)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(lblUPM))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtUPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtSitio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSitio)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblErosionHidrica)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnContinuar)
                    .addComponent(btnSalir))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtProfundidadCanalillosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtProfundidadCanalillosFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtProfundidadCanalillos.selectAll();
            }
        });
    }//GEN-LAST:event_txtProfundidadCanalillosFocusGained

    private void txtAnchoCanalilloFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAnchoCanalilloFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtAnchoCanalillo.selectAll();
            }
        });
    }//GEN-LAST:event_txtAnchoCanalilloFocusGained

    private void txtDistanciaCanalillosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDistanciaCanalillosFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtDistanciaCanalillos.selectAll();
            }
        });
    }//GEN-LAST:event_txtDistanciaCanalillosFocusGained

    private void txtAzimutCanalillosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAzimutCanalillosFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtAnchoCanalillo.selectAll();
            }
        });
    }//GEN-LAST:event_txtAzimutCanalillosFocusGained

    private void txtProfundidadCarcavasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtProfundidadCarcavasFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtProfundidadCarcavas.selectAll();
            }
        });
    }//GEN-LAST:event_txtProfundidadCarcavasFocusGained

    private void txtAnchoCarcavasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAnchoCarcavasFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtAnchoCarcavas.selectAll();
            }
        });
    }//GEN-LAST:event_txtAnchoCarcavasFocusGained

    private void txtDistanciaCarcavasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDistanciaCarcavasFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtDistanciaCarcavas.selectAll();
            }
        });
    }//GEN-LAST:event_txtDistanciaCarcavasFocusGained

    private void txtAzimutCarcavasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAzimutCarcavasFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtAzimutCarcavas.selectAll();
            }
        });
    }//GEN-LAST:event_txtAzimutCarcavasFocusGained

    private void txtLongitudCanalillosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLongitudCanalillosFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtLongitudCanalillos.selectAll();
            }
        });
    }//GEN-LAST:event_txtLongitudCanalillosFocusGained

    private void txtLongitudCarcavasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLongitudCarcavasFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtLongitudCarcavas.selectAll();
            }
        });
    }//GEN-LAST:event_txtLongitudCarcavasFocusGained

    private void txtNoCanalillosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNoCanalillosFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtNoCanalillos.selectAll();
            }
        });
    }//GEN-LAST:event_txtNoCanalillosFocusGained

    private void txtProfundidadPromedioCanalillosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtProfundidadPromedioCanalillosFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtProfundidadPromedioCanalillos.selectAll();
            }
        });
    }//GEN-LAST:event_txtProfundidadPromedioCanalillosFocusGained

    private void txtAnchoPromedioCanalillosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAnchoPromedioCanalillosFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtAnchoPromedioCanalillos.selectAll();
            }
        });
    }//GEN-LAST:event_txtAnchoPromedioCanalillosFocusGained

    private void txtPromedioLongitudCanalillosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPromedioLongitudCanalillosFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtPromedioLongitudCanalillos.selectAll();
            }
        });
    }//GEN-LAST:event_txtPromedioLongitudCanalillosFocusGained

    private void txtVolumenCanalillosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtVolumenCanalillosFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtVolumenCanalillos.selectAll();
            }
        });
    }//GEN-LAST:event_txtVolumenCanalillosFocusGained

    private void txtNoCarcavasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNoCarcavasFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtNoCarcavas.selectAll();
            }
        });
    }//GEN-LAST:event_txtNoCarcavasFocusGained

    private void txtProfundidadPromedioCarcavasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtProfundidadPromedioCarcavasFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtProfundidadPromedioCarcavas.selectAll();
            }
        });
    }//GEN-LAST:event_txtProfundidadPromedioCarcavasFocusGained

    private void txtAnchoPromedioCarcavasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAnchoPromedioCarcavasFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtAnchoPromedioCarcavas.selectAll();
            }
        });
    }//GEN-LAST:event_txtAnchoPromedioCarcavasFocusGained

    private void txtPromedioLongitudCarcavasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPromedioLongitudCarcavasFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtPromedioLongitudCarcavas.selectAll();
            }
        });
    }//GEN-LAST:event_txtPromedioLongitudCarcavasFocusGained

    private void txtVolumenCarcavasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtVolumenCarcavasFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtVolumenCarcavas.selectAll();
            }
        });
    }//GEN-LAST:event_txtVolumenCarcavasFocusGained

    private void txtProfundidadCanalillosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtProfundidadCanalillosFocusLost
        if (txtProfundidadCanalillos.getText().isEmpty()) {
            txtProfundidadCanalillos.setValue(null);
        }
    }//GEN-LAST:event_txtProfundidadCanalillosFocusLost

    private void txtAnchoCanalilloFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAnchoCanalilloFocusLost
        if (txtAnchoCanalillo.getText().isEmpty()) {
            txtAnchoCanalillo.setValue(null);
        }
    }//GEN-LAST:event_txtAnchoCanalilloFocusLost

    private void txtDistanciaCanalillosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDistanciaCanalillosFocusLost
        if (txtDistanciaCanalillos.getText().isEmpty()) {
            txtDistanciaCanalillos.setValue(null);
        }
    }//GEN-LAST:event_txtDistanciaCanalillosFocusLost

    private void txtAzimutCanalillosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAzimutCanalillosFocusLost
        if (txtAzimutCanalillos.getText().isEmpty()) {
            txtAzimutCanalillos.setValue(null);
        }
    }//GEN-LAST:event_txtAzimutCanalillosFocusLost

    private void txtProfundidadCarcavasFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtProfundidadCarcavasFocusLost
        if (txtProfundidadCarcavas.getText().isEmpty()) {
            txtProfundidadCarcavas.setValue(null);
        }
    }//GEN-LAST:event_txtProfundidadCarcavasFocusLost

    private void txtAnchoCarcavasFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAnchoCarcavasFocusLost
        if (txtAnchoCarcavas.getText().isEmpty()) {
            txtAnchoCarcavas.setValue(null);
        }
    }//GEN-LAST:event_txtAnchoCarcavasFocusLost

    private void txtDistanciaCarcavasFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDistanciaCarcavasFocusLost
        if (txtDistanciaCarcavas.getText().isEmpty()) {
            txtDistanciaCarcavas.setValue(null);
        }
    }//GEN-LAST:event_txtDistanciaCarcavasFocusLost

    private void txtAzimutCarcavasFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAzimutCarcavasFocusLost
        if (txtAzimutCarcavas.getText().isEmpty()) {
            txtAzimutCarcavas.setValue(null);
        }
    }//GEN-LAST:event_txtAzimutCarcavasFocusLost

    private void txtLongitudCanalillosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLongitudCanalillosFocusLost
        if (txtLongitudCanalillos.getText().isEmpty()) {
            txtLongitudCanalillos.setValue(null);
        }
    }//GEN-LAST:event_txtLongitudCanalillosFocusLost

    private void txtLongitudCarcavasFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLongitudCarcavasFocusLost
        if (txtLongitudCarcavas.getText().isEmpty()) {
            txtLongitudCarcavas.setValue(null);
        }
    }//GEN-LAST:event_txtLongitudCarcavasFocusLost

    private void txtNoCanalillosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNoCanalillosFocusLost
        if (txtNoCanalillos.getText().isEmpty()) {
            txtNoCanalillos.setValue(null);
        }
    }//GEN-LAST:event_txtNoCanalillosFocusLost

    private void txtProfundidadPromedioCanalillosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtProfundidadPromedioCanalillosFocusLost
        if (txtProfundidadCanalillos.getText().isEmpty()) {
            txtProfundidadCanalillos.setValue(null);
        }
    }//GEN-LAST:event_txtProfundidadPromedioCanalillosFocusLost

    private void txtAnchoPromedioCanalillosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAnchoPromedioCanalillosFocusLost
        if (txtAnchoPromedioCanalillos.getText().isEmpty()) {
            txtAnchoPromedioCanalillos.setValue(null);
        }
    }//GEN-LAST:event_txtAnchoPromedioCanalillosFocusLost

    private void txtPromedioLongitudCanalillosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPromedioLongitudCanalillosFocusLost
        if (txtPromedioLongitudCanalillos.getText().isEmpty()) {
            txtPromedioLongitudCanalillos.setValue(null);
        }
    }//GEN-LAST:event_txtPromedioLongitudCanalillosFocusLost

    private void txtVolumenCanalillosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtVolumenCanalillosFocusLost
        if (txtVolumenCanalillos.getText().isEmpty()) {
            txtVolumenCanalillos.setValue(null);
        }
    }//GEN-LAST:event_txtVolumenCanalillosFocusLost

    private void txtNoCarcavasFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNoCarcavasFocusLost
        if (txtNoCarcavas.getText().isEmpty()) {
            txtNoCarcavas.setValue(null);
        }
    }//GEN-LAST:event_txtNoCarcavasFocusLost

    private void txtProfundidadPromedioCarcavasFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtProfundidadPromedioCarcavasFocusLost
        if (txtProfundidadPromedioCarcavas.getText().isEmpty()) {
            txtProfundidadPromedioCarcavas.setValue(null);
        }
    }//GEN-LAST:event_txtProfundidadPromedioCarcavasFocusLost

    private void txtAnchoPromedioCarcavasFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAnchoPromedioCarcavasFocusLost
        if (txtAnchoPromedioCarcavas.getText().isEmpty()) {
            txtAnchoPromedioCarcavas.setValue(null);
        }
    }//GEN-LAST:event_txtAnchoPromedioCarcavasFocusLost

    private void txtPromedioLongitudCarcavasFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPromedioLongitudCarcavasFocusLost
        if (txtPromedioLongitudCarcavas.getText().isEmpty()) {
            txtPromedioLongitudCarcavas.setValue(null);
        }
    }//GEN-LAST:event_txtPromedioLongitudCarcavasFocusLost

    private void txtVolumenCarcavasFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtVolumenCarcavasFocusLost
        if (txtVolumenCarcavas.getText().isEmpty()) {
            txtVolumenCarcavas.setValue(null);
        }
    }//GEN-LAST:event_txtVolumenCarcavasFocusLost

    private void btnAgregarCanalillosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarCanalillosActionPerformed
        fijarDatosCanalillos();
        if (validarCanalillosObligatorios() && validarDatosCanalillos()) {
            crearCanalillos();
            this.cdErosion.reenumerarErosionCanalillo(this.sitioID);
            llenarTablaErosionCanalillos();
            this.combo.reiniciarComboModel(cmbMedicionCanalillos);
            fillCmbMedionesCanalillos();
            limpiarControlesCanalillo();
            calcularSueloCanalillos();
        }
    }//GEN-LAST:event_btnAgregarCanalillosActionPerformed

    private void btnModificarCanalillosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarCanalillosActionPerformed
        fijarDatosCanalillos();
        if (validarDatosCanalillos()) {
            modificarCanalillos();
            llenarTablaErosionCanalillos();
            limpiarControlesCanalillo();
            calcularSueloCanalillos();
        }
    }//GEN-LAST:event_btnModificarCanalillosActionPerformed

    private void btnEliminarCanalillosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarCanalillosActionPerformed
        eliminarCanalillo();
        this.cdErosion.reenumerarErosionCanalillo(this.sitioID);
        llenarTablaErosionCanalillos();
        this.combo.reiniciarComboModel(cmbMedicionCanalillos);
        fillCmbMedionesCanalillos();
        limpiarControlesCanalillo();
        calcularSueloCanalillos();
    }//GEN-LAST:event_btnEliminarCanalillosActionPerformed

    private void btnAgregarCarcavaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarCarcavaActionPerformed
        fijarDatosCarcavas();
        if (validarCarcavasObligatorios() && validarDatosCarcavas()) {
            crearCarcavas();
            this.cdErosion.reenumerarErosionCarcava(this.sitioID);
            llenarTablaErosionCarcavas();
            this.combo.reiniciarComboModel(cmbMedicionCarcavas);
            fillCmbMedionesCarcava();
            limpiarControlesCarcava();
            calcularSueloCarcavas();
        }
    }//GEN-LAST:event_btnAgregarCarcavaActionPerformed

    private void btnModificarCarcavaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarCarcavaActionPerformed
        fijarDatosCarcavas();
        if (validarDatosCarcavas()) {
            modificarCarcavas();
            llenarTablaErosionCarcavas();
            limpiarControlesCarcava();
            calcularSueloCarcavas();
        }
    }//GEN-LAST:event_btnModificarCarcavaActionPerformed

    private void btnEliminarCarcavaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarCarcavaActionPerformed
        eliminarCarcava();
        this.cdErosion.reenumerarErosionCarcava(this.sitioID);
        llenarTablaErosionCarcavas();
        this.combo.reiniciarComboModel(cmbMedicionCarcavas);
        fillCmbMedionesCarcava();
        limpiarControlesCarcava();
        calcularSueloCarcavas();
    }//GEN-LAST:event_btnEliminarCarcavaActionPerformed

    private void btnAgregarLongitudCanalillosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarLongitudCanalillosActionPerformed
        fijarDatosLontudesCanalillos();
        if(validarLongitudCanalillosObligatorios() && validarDatosLongitudCanalillos()){
            crearLongitudCanalillo();
            this.cdErosion.reenumerarLongitudCanalillo(this.sitioID);
            llenarTablaLongitudCanalillos();
            this.combo.reiniciarComboModel(cmbLongitudCanalillos);
            fillCmbLongitudCanalillo();
            limpiarControlesLongitudCanalillo();
            calcularSueloCanalillos();
        }
    }//GEN-LAST:event_btnAgregarLongitudCanalillosActionPerformed

    private void btnModificarLongitudCanalillosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarLongitudCanalillosActionPerformed
        fijarDatosLontudesCanalillos();
        if(validarDatosLongitudCanalillos()){
            modificarlongitudCanalillos();
            llenarTablaLongitudCanalillos();
            limpiarControlesLongitudCanalillo();
            calcularSueloCanalillos();
        }
    }//GEN-LAST:event_btnModificarLongitudCanalillosActionPerformed

    private void btnEliminarLongitudCanalillosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarLongitudCanalillosActionPerformed
        eliminarLongitudCanalillo();
         this.cdErosion.reenumerarLongitudCanalillo(this.sitioID);
        llenarTablaLongitudCanalillos();
        this.combo.reiniciarComboModel(cmbLongitudCanalillos);
        fillCmbLongitudCanalillo();
        limpiarControlesLongitudCanalillo();
        calcularSueloCanalillos();
    }//GEN-LAST:event_btnEliminarLongitudCanalillosActionPerformed

    private void btnAgregarLongitudCarcavaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarLongitudCarcavaActionPerformed
        fijarDatosLongitudesCarcavas();
        if(validarLongitudCarcavasObligatorios() && validarDatosLongitudCarcavas()){
            crearLongitudCarcava();
            this.cdErosion.reenumerarLongitudCarcava(this.sitioID);
            llenarTablaLongitudCarcavas();
            this.combo.reiniciarComboModel(cmbLongitudCarcava);
            fillCmbLongitudCarcavas();
            limpiarControlesLongitudCarcava();
            calcularSueloCarcavas();
        }
    }//GEN-LAST:event_btnAgregarLongitudCarcavaActionPerformed

    private void btnModificarLongitudCarcavaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarLongitudCarcavaActionPerformed
        fijarDatosLongitudesCarcavas();
        if(validarDatosLongitudCarcavas()){
            modificarLongitudCarcavas();
            llenarTablaLongitudCarcavas();
            limpiarControlesLongitudCarcava();
            calcularSueloCarcavas();
        }
    }//GEN-LAST:event_btnModificarLongitudCarcavaActionPerformed

    private void btnEliminarLongitudCarcavaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarLongitudCarcavaActionPerformed
        eliminarLongitudCarcava();
        this.cdErosion.reenumerarLongitudCarcava(this.sitioID);
        llenarTablaLongitudCarcavas();
        this.combo.reiniciarComboModel(cmbLongitudCarcava);
        fillCmbLongitudCarcavas();
        limpiarControlesLongitudCarcava();
        calcularSueloCarcavas();
    }//GEN-LAST:event_btnEliminarLongitudCarcavaActionPerformed

    private void btnContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuarActionPerformed
        fijarDatosSuelos();
        agregarDatosSuelo();
        if (chkCanalillos.isSelected() && this.cdErosion.validarTablaErosionCanalillos(this.sitioID)) {
            JOptionPane.showMessageDialog(null, "Si selecciona canalillos/canales , se deben capturar"
                    + "", "Suelo", JOptionPane.INFORMATION_MESSAGE);
            cmbMedicionCanalillos.requestFocus();
        } else if (chkCarcavas.isSelected() && this.cdErosion.validarTablaErosionCarcava(this.sitioID)) {
            JOptionPane.showMessageDialog(null, "Si selecciona carcavas , se deben capturar"
                    + "", "Suelo", JOptionPane.INFORMATION_MESSAGE);
            cmbMedicionCarcavas.requestFocus();
        } else if (revisar == 0) {
            
            this.hide();
            UPMForms.deformacionTerreno.setDatosiniciales(this.ceSitio);
            UPMForms.deformacionTerreno.setVisible(true);
            this.cdSecuencia.updateSecuencia(this.ceSitio, FORMATO_ID, 1);
        } else {
            this.hide();
            //System.out.println("entró en modo de revision LINEA 2434 FRMEROSIONHIDRICA");
            UPMForms.deformacionTerreno.revisarDeformacionViento(this.ceSitio);
            UPMForms.deformacionTerreno.setVisible(true);
        }
        
    }//GEN-LAST:event_btnContinuarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.hide();
        funciones.manipularBotonesMenuPrincipal(false);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void grdCanalillosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grdCanalillosMouseClicked
        if (evt.getButton() == 1) {
            int fila = grdCanalillos.getSelectedRow();
            String registro = grdCanalillos.getValueAt(fila, 0).toString();
            this.ceCanalillos = this.cdErosion.getCanalillo(Integer.parseInt(registro));

            Integer medicion = this.ceCanalillos.getMedicion();
            cmbMedicionCanalillos.setSelectedItem(medicion);

            txtProfundidadCanalillos.setText(String.valueOf(this.ceCanalillos.getProfundidad()));
            txtAnchoCanalillo.setText(String.valueOf(this.ceCanalillos.getAncho()));
            txtDistanciaCanalillos.setText(String.valueOf(this.ceCanalillos.getDistancia()));
            txtAzimutCanalillos.setText(String.valueOf(this.ceCanalillos.getAzimut()));
        }
    }//GEN-LAST:event_grdCanalillosMouseClicked

    private void grdCarcavasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grdCarcavasMouseClicked
        if (evt.getButton() == 1) {
            int fila = grdCarcavas.getSelectedRow();
            String registro = grdCarcavas.getValueAt(fila, 0).toString();
            this.ceCarcavas = this.cdErosion.getCarcava(Integer.parseInt(registro));

            Integer medicion = this.ceCarcavas.getMedicion();
            cmbMedicionCarcavas.setSelectedItem(medicion);

            txtProfundidadCarcavas.setText(String.valueOf(this.ceCarcavas.getProfundidad()));
            txtAnchoCarcavas.setText(String.valueOf(this.ceCarcavas.getAncho()));
            txtDistanciaCarcavas.setText(String.valueOf(this.ceCarcavas.getDistancia()));
            txtAzimutCarcavas.setText(String.valueOf(this.ceCarcavas.getAzimut()));
        }
    }//GEN-LAST:event_grdCarcavasMouseClicked

    private void grdLongitudCanalillosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grdLongitudCanalillosMouseClicked
        if (evt.getButton() == 1) {
            int fila = grdLongitudCanalillos.getSelectedRow();
            String registro = grdLongitudCanalillos.getValueAt(fila, 0).toString();
            this.ceLongitudCanalillos = this.cdErosion.getLongitudCanalillos(Integer.parseInt(registro));

            Integer campo = this.ceLongitudCanalillos.getCampoLongitud();
            cmbLongitudCanalillos.setSelectedItem(campo);

            txtLongitudCanalillos.setText(String.valueOf(this.ceLongitudCanalillos.getLongitud()));
        }
    }//GEN-LAST:event_grdLongitudCanalillosMouseClicked

    private void grdLongitudCarcavasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grdLongitudCarcavasMouseClicked
        if (evt.getButton() == 1) {
            int fila = grdLongitudCarcavas.getSelectedRow();
            String registro = grdLongitudCarcavas.getValueAt(fila, 0).toString();
            this.ceLongitudCarcavas = this.cdErosion.getLongitudCarcava(Integer.parseInt(registro));

            Integer campo = this.ceLongitudCarcavas.getCampoLongitud();
            cmbLongitudCarcava.setSelectedItem(campo);

            txtLongitudCarcavas.setText(String.valueOf(this.ceLongitudCarcavas.getLongitud()));
        }
    }//GEN-LAST:event_grdLongitudCarcavasMouseClicked

    private void txtProfundidadCanalillosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProfundidadCanalillosKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtProfundidadCanalillosKeyTyped

    private void txtAnchoCanalilloKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAnchoCanalilloKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtAnchoCanalilloKeyTyped

    private void txtDistanciaCanalillosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDistanciaCanalillosKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtDistanciaCanalillosKeyTyped

    private void txtAzimutCanalillosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAzimutCanalillosKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtAzimutCanalillosKeyTyped

    private void txtLongitudCanalillosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLongitudCanalillosKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtLongitudCanalillosKeyTyped

    private void txtProfundidadCarcavasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProfundidadCarcavasKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtProfundidadCarcavasKeyTyped

    private void txtAnchoCarcavasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAnchoCarcavasKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtAnchoCarcavasKeyTyped

    private void txtDistanciaCarcavasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDistanciaCarcavasKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtDistanciaCarcavasKeyTyped

    private void txtAzimutCarcavasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAzimutCarcavasKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtAzimutCarcavasKeyTyped

    private void txtLongitudCarcavasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLongitudCarcavasKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtLongitudCarcavasKeyTyped

    private void chkCanalillosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCanalillosActionPerformed
        if (chkCanalillos.isSelected()) {
            manipularControlesCanalillos(true);
        } else {
            Object[] opciones = {"Si", "No"};
            int respuesta = JOptionPane.showOptionDialog(null, "Si capturo, se borrarán todos los datos de canales, canalillos y longitudes, ¿Esta seguro?",
                    "Suelo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[1]);
            if (respuesta == JOptionPane.YES_OPTION) {
                this.cdErosion.deleteErosionCanalilloSitio(this.sitioID);
                this.funciones.reiniciarTabla(grdCarcavas);
                llenarTablaErosionCanalillos();
                manipularControlesCanalillos(false);
                this.cdErosion.deleteLongitudCanalilloSitio(this.sitioID);
                this.funciones.reiniciarComboModel(cmbMedicionCanalillos);
                fillCmbMedionesCanalillos();
                this.funciones.reiniciarTabla(grdLongitudCanalillos);
                llenarTablaLongitudCanalillos();
                this.funciones.reiniciarComboModel(cmbLongitudCanalillos);
                fillCmbLongitudCanalillo();
                limpiarControlesCanalillo();
                limpiarCamposCalculadosCanalillos();
                limpiarControlesLongitudCanalillo();
            } else {
                chkCanalillos.setSelected(true);
                chkCanalillos.requestFocus();
            }
        }
    }//GEN-LAST:event_chkCanalillosActionPerformed

    private void chkCarcavasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCarcavasActionPerformed
       if(chkCarcavas.isSelected()){
           manipularControlesCarcavas(true);
       } else {
           Object[] opciones = {"Si", "No"};
            int respuesta = JOptionPane.showOptionDialog(null, "Si capturo, se borrarán todos los datos de carcavas y longitudes, ¿Esta seguro?",
                    "Suelo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[1]);
            if (respuesta == JOptionPane.YES_OPTION) {
                this.cdErosion.deleteErosionCarcavaSitio(this.sitioID);
                this.funciones.reiniciarTabla(grdCarcavas);
                llenarTablaErosionCarcavas();
                manipularControlesCarcavas(false);
                this.cdErosion.deleteLongitudCarcavaSitio(this.sitioID);
                this.funciones.reiniciarComboModel(cmbMedicionCarcavas);
                fillCmbMedionesCarcava();
                this.funciones.reiniciarComboModel(cmbLongitudCarcava);
                fillCmbLongitudCarcavas();
                limpiarControlesCarcava();
                limpiarControlesLongitudCarcava();
                limpiarCamposCalculadosCarcavas();
            } else {
                chkCarcavas.setSelected(true);
                chkCarcavas.requestFocus();
            }
       }
    }//GEN-LAST:event_chkCarcavasActionPerformed

    private void cmbMedicionCarcavasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMedicionCarcavasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbMedicionCarcavasActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarCanalillos;
    private javax.swing.JButton btnAgregarCarcava;
    private javax.swing.JButton btnAgregarLongitudCanalillos;
    private javax.swing.JButton btnAgregarLongitudCarcava;
    private javax.swing.JButton btnContinuar;
    private javax.swing.JButton btnEliminarCanalillos;
    private javax.swing.JButton btnEliminarCarcava;
    private javax.swing.JButton btnEliminarLongitudCanalillos;
    private javax.swing.JButton btnEliminarLongitudCarcava;
    private javax.swing.JButton btnModificarCanalillos;
    private javax.swing.JButton btnModificarCarcava;
    private javax.swing.JButton btnModificarLongitudCanalillos;
    private javax.swing.JButton btnModificarLongitudCarcava;
    private javax.swing.JButton btnSalir;
    private javax.swing.JCheckBox chkCanalillos;
    private javax.swing.JCheckBox chkCarcavas;
    private javax.swing.JComboBox cmbLongitudCanalillos;
    private javax.swing.JComboBox cmbLongitudCarcava;
    private javax.swing.JComboBox cmbMedicionCanalillos;
    private javax.swing.JComboBox cmbMedicionCarcavas;
    private javax.swing.JTable grdCanalillos;
    private javax.swing.JTable grdCarcavas;
    private javax.swing.JTable grdLongitudCanalillos;
    private javax.swing.JTable grdLongitudCarcavas;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JLabel lblAnchoCanales;
    private javax.swing.JLabel lblAnchoCarcavas;
    private javax.swing.JLabel lblAzimtCanales;
    private javax.swing.JLabel lblAzimut;
    private javax.swing.JLabel lblCampoCanales;
    private javax.swing.JLabel lblDistancia;
    private javax.swing.JLabel lblDistanciaCanales;
    private javax.swing.JLabel lblErosionHidrica;
    private javax.swing.JLabel lblLongitudCanales;
    private javax.swing.JLabel lblMedicionCanales;
    private javax.swing.JLabel lblMedicionCarcavas;
    private javax.swing.JLabel lblNoCanalillos;
    private javax.swing.JLabel lblNoCarcava;
    private javax.swing.JLabel lblProfundidadCanales;
    private javax.swing.JLabel lblProfundidadCarcavas;
    private javax.swing.JLabel lblProfundidadPromedioCanalillos;
    private javax.swing.JLabel lblProfundidadPromedioCanalillos1;
    private javax.swing.JLabel lblProfundidadPromedioCanalillos2;
    private javax.swing.JLabel lblProfundidadPromedioCanalillos3;
    private javax.swing.JLabel lblProfundidadPromedioCanalillos4;
    private javax.swing.JLabel lblProfundidadPromedioCanalillos5;
    private javax.swing.JLabel lblProfundidadPromedioCanalillos6;
    private javax.swing.JLabel lblProfundidadPromedioCanalillos7;
    private javax.swing.JLabel lblSitio;
    private javax.swing.JLabel lblUPM;
    private javax.swing.JFormattedTextField txtAnchoCanalillo;
    private javax.swing.JFormattedTextField txtAnchoCarcavas;
    private javax.swing.JFormattedTextField txtAnchoPromedioCanalillos;
    private javax.swing.JFormattedTextField txtAnchoPromedioCarcavas;
    private javax.swing.JFormattedTextField txtAzimutCanalillos;
    private javax.swing.JFormattedTextField txtAzimutCarcavas;
    private javax.swing.JFormattedTextField txtDistanciaCanalillos;
    private javax.swing.JFormattedTextField txtDistanciaCarcavas;
    private javax.swing.JFormattedTextField txtLongitudCanalillos;
    private javax.swing.JFormattedTextField txtLongitudCarcavas;
    private javax.swing.JFormattedTextField txtNoCanalillos;
    private javax.swing.JFormattedTextField txtNoCarcavas;
    private javax.swing.JFormattedTextField txtProfundidadCanalillos;
    private javax.swing.JFormattedTextField txtProfundidadCarcavas;
    private javax.swing.JFormattedTextField txtProfundidadPromedioCanalillos;
    private javax.swing.JFormattedTextField txtProfundidadPromedioCarcavas;
    private javax.swing.JFormattedTextField txtPromedioLongitudCanalillos;
    private javax.swing.JFormattedTextField txtPromedioLongitudCarcavas;
    private javax.swing.JTextField txtSitio;
    private javax.swing.JTextField txtUPM;
    private javax.swing.JFormattedTextField txtVolumenCanalillos;
    private javax.swing.JFormattedTextField txtVolumenCarcavas;
    // End of variables declaration//GEN-END:variables
}
