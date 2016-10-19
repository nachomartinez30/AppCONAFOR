package gob.conafor.suelo.vie;

import gob.conafor.sitio.mod.CESitio;
import gob.conafor.suelo.mod.CDEvidenciaErosion;
import gob.conafor.suelo.mod.CDSuelo;
import gob.conafor.suelo.mod.CDVarillasErosion;
import gob.conafor.suelo.mod.CECoberturaSuelo;
import gob.conafor.suelo.mod.CEEvidenciaErosion;
import gob.conafor.suelo.mod.CESuelo;
import gob.conafor.suelo.mod.CEVarillaErosion;
import gob.conafor.suelo.mod.CatELecturaTierra;
import gob.conafor.suelo.mod.CatEUsoSuelo;
import gob.conafor.sys.mod.CDSecuencia;
import gob.conafor.sys.mod.CDSeguimientoUPM;
import gob.conafor.sys.mod.CESeccionesCapturadas;
import gob.conafor.upm.vie.UPMForms;
import gob.conafor.utils.Datos;
import gob.conafor.utils.FuncionesComunes;
import gob.conafor.utils.Tablas;
import gob.conafor.utils.ValidacionesComunes;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class FrmSuelo extends JInternalFrame {
    
    private int upmID;
    private int sitioID;
    private int sitio;
    private int sueloID;
    private int suelo;
    private static final int FORMATO_ID = 9;
    private int condicionDegradacion;
    private CESitio ceSitio = new CESitio();
    private CDSuelo cdSuelo = new CDSuelo();
    private CESuelo ceSuelo = new CESuelo();
    private CDEvidenciaErosion cdEvidencia = new CDEvidenciaErosion();
    private CDVarillasErosion cdVarillas = new CDVarillasErosion();
    private CECoberturaSuelo ceCobertura = new CECoberturaSuelo();
    private int coberturaID;
    private String otroUsoSuelo;
    private Float espesor;
    private Integer pendienteDominante;
    private String observaciones;
    private CatEUsoSuelo indexUsoSuelo;
    private Integer noVarilla1;
    private Integer azimut1;
    private Float distancia1;
    private Float profundidad1;
    private Integer noVarilla2;
    private Integer azimut2;
    private Float distancia2;
    private Float profundidad2;
    private Integer noVarilla3;
    private Integer azimut3;
    private Float distancia3;
    private Float profundidad3;
    private Integer noVarilla4;
    private Integer azimut4;
    private Float distancia4;
    private Float profundidad4;
    private Integer noVarilla5;
    private Integer azimut5;
    private Float distancia5;
    private Float profundidad5;
    private Float pendienteCobertura;
    private CatELecturaTierra lecturaTierra = new CatELecturaTierra();
    private CEEvidenciaErosion ceEvidencia = new CEEvidenciaErosion();
    private int dosel;
    private int evidenciaID;
    private CEVarillaErosion ceVarilla = new CEVarillaErosion();
    private CEVarillaErosion varilla1 = new CEVarillaErosion();
    private CEVarillaErosion varilla2 = new CEVarillaErosion();
    private CEVarillaErosion varilla3 = new CEVarillaErosion();
    private CEVarillaErosion varilla4 = new CEVarillaErosion();
    private CEVarillaErosion varilla5 = new CEVarillaErosion();
    private ValidacionesComunes validacion = new ValidacionesComunes();
    private CESeccionesCapturadas seccionActual = new CESeccionesCapturadas();
    private CESeccionesCapturadas seccionSiguiente = new CESeccionesCapturadas();
    private CDSeguimientoUPM seguimiento = new CDSeguimientoUPM();
    private Datos numeros = new Datos();
    private FuncionesComunes combo = new FuncionesComunes();
    private CDSecuencia cdSecuencia = new CDSecuencia();
    private int actualizar;
    private FuncionesComunes funciones = new FuncionesComunes();
    
    public FrmSuelo() {
        this.suelo = 19;
        this.condicionDegradacion = 20;
        initComponents();
     /*   fillCmbUsoSuelo();
        fillCmbTransectos();
        fillCmbLecturaTierra();*/
    }
    
    public void setDatosiniciales(CESitio ceSitio) {
        this.upmID = ceSitio.getUpmID();
        this.sitioID = ceSitio.getSitioID();
        this.sitio = ceSitio.getSitio();
        txtUPM.setText(String.valueOf(this.upmID));
        txtSitio.setText(String.valueOf(this.sitio));
        this.ceSitio.setSitioID(this.sitioID);
        this.ceSitio.setUpmID(this.upmID);
        this.ceSitio.setSitio(this.sitio);
        llenarTablaEvidenciaErosion();
        combo.reiniciarComboModel(cmbTransecto);
        combo.reiniciarComboModel(cmbUsoSuelo);
        this.ceSitio = ceSitio;
        fillCmbTransectos();
        fillCmbUsoSuelo();
        fillCmbLecturaTierra();
        this.actualizar = 0;
        funciones.manipularBotonesMenuPrincipal(true);
        limpiarSuelo();
    }

    public void revisarSuelo(CESitio ceSitio) {
        this.upmID = ceSitio.getUpmID();
        this.sitioID = ceSitio.getSitioID();
        this.sitio = ceSitio.getSitio();
        txtUPM.setText(String.valueOf(this.upmID));
        txtSitio.setText(String.valueOf(this.sitio));
        this.ceSitio.setSitioID(this.sitioID);
        this.ceSitio.setUpmID(this.upmID);
        this.ceSitio.setSitio(this.sitio);
        llenarTablaEvidenciaErosion();
        combo.reiniciarComboModel(cmbTransecto);
        combo.reiniciarComboModel(cmbUsoSuelo);
        fillCmbTransectos();
        fillCmbUsoSuelo();
        fillCmbLecturaTierra();
        ceSuelo = cdSuelo.getDatosSuelo(this.sitioID);
        CatEUsoSuelo ceUsoSuelo = new CatEUsoSuelo();
        ceUsoSuelo.setUsoSueloID(ceSuelo.getUsoSueloID());
        cmbUsoSuelo.setSelectedItem(ceUsoSuelo);
        if (ceUsoSuelo.getUsoSueloID() == 6) {
            txtOtroUsoSuelo.setText(ceSuelo.getOtroUsoSuelo());
            txtOtroUsoSuelo.setEnabled(true);
        }
        txtEspesor.setText(String.valueOf(ceSuelo.getEspesor()));
        //Categoria
        txtPendienteDominante.setText(String.valueOf(ceSuelo.getPendienteDominante()));
        txtObservaciones.setText(ceSuelo.getObservaciones());
        List<CEVarillaErosion> listVarillas = new ArrayList<>();
        listVarillas = this.cdVarillas.getDatosVarillas(this.sitioID);
        if (listVarillas != null) {
            int size = listVarillas.size();
            for (int i = 0; i < size; i++) {
                CEVarillaErosion ceVarillas = new CEVarillaErosion();
                ceVarillas = listVarillas.get(i);
                switch (i) {
                    case 0:
                        txtAzimut1.setText(String.valueOf(ceVarillas.getAzimut()));
                        txtDistancia1.setText(String.valueOf(ceVarillas.getDistancia()));
                        txtProfundidad1.setText(String.valueOf(ceVarillas.getProfundidad()));
                        break;
                    case 1:
                        txtAzimut2.setText(String.valueOf(ceVarillas.getAzimut()));
                        txtDistancia2.setText(String.valueOf(ceVarillas.getDistancia()));
                        txtProfundidad2.setText(String.valueOf(ceVarillas.getProfundidad()));
                        break;
                    case 2:
                        txtAzimut3.setText(String.valueOf(ceVarillas.getAzimut()));
                        txtDistancia3.setText(String.valueOf(ceVarillas.getDistancia()));
                        txtProfundidad3.setText(String.valueOf(ceVarillas.getProfundidad()));
                        break;
                    case 3:
                        txtAzimut4.setText(String.valueOf(ceVarillas.getAzimut()));
                        txtDistancia4.setText(String.valueOf(ceVarillas.getDistancia()));
                        txtProfundidad4.setText(String.valueOf(ceVarillas.getProfundidad()));
                        break;
                    case 4:
                        txtProfundidad5.setText(String.valueOf(ceVarillas.getProfundidad()));
                        break;
                }
            }
        }
        this.actualizar = 1;
        this.chkCoberturaSuelo.setEnabled(funciones.habilitarCheckBox("SUELO_CoberturaSuelo", this.sitioID));
        this.funciones.manipularBotonesMenuPrincipal(true);
    }
    
    private void fillCmbUsoSuelo(){
        List<CatEUsoSuelo> listUsoSuelo = new ArrayList<>();
        listUsoSuelo = cdSuelo.getUsoSuelo();
        if(listUsoSuelo != null){
            int size = listUsoSuelo.size();
            for(int i = 0; i < size; i++){
                cmbUsoSuelo.addItem(listUsoSuelo.get(i));
            }
        }
    }
    
    private void fillCmbTransectos() {
        List<Integer> listTransectos = new ArrayList<>();
        listTransectos = cdEvidencia.getTransecto();
        if (listTransectos != null) {
            int size = listTransectos.size();
            for (int i = 0; i < size; i++) {
                cmbTransecto.addItem(listTransectos.get(i));
            }
        }
    }
    
    private void fillCmbPuntos(int indexCobertura) {
        List<Integer> listPuntos = new ArrayList<>();
        listPuntos = cdEvidencia.getPuntos(indexCobertura);
        if(listPuntos != null){
            int size = listPuntos.size();
            for(int i = 0; i < size; i++){
                cmbPunto.addItem(listPuntos.get(i));
            }
        }
    }
    
    private void fillCmbLecturaTierra(){
        List<CatELecturaTierra> listLectura = new ArrayList<>();
        listLectura = this.cdEvidencia.getTipoLecturaTierra();
        if(listLectura != null){
            int size = listLectura.size();
            for(int i = 0; i < size; i++){
                cmbLecturaTierra.addItem(listLectura.get(i));
            }
        }
    }
    
    private void llenarTablaEvidenciaErosion(){
        grdEvidenciaErosion.setModel(cdEvidencia.getTableEvidenciaErosion(this.coberturaID));
        grdEvidenciaErosion.getColumnModel().getColumn(2).setPreferredWidth(70);
        grdEvidenciaErosion.getColumnModel().getColumn(3).setPreferredWidth(70);
        grdEvidenciaErosion.getColumnModel().getColumn(4).setPreferredWidth(166);
        Tablas tabla = new Tablas();
        int[] columna_0 = {0};
        int[] column_1 = {1};
        //Ocultar campo ID
        tabla.hideColumnTable(grdEvidenciaErosion, columna_0);
        tabla.hideColumnTable(grdEvidenciaErosion, column_1);
    }
    
    private boolean validarObligatoriosSuelo() {
        this.indexUsoSuelo = (CatEUsoSuelo) cmbUsoSuelo.getSelectedItem();
        if (indexUsoSuelo == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar un uso de suelo ",
                    "Suelo", JOptionPane.INFORMATION_MESSAGE);
            cmbUsoSuelo.requestFocus();
            return false;
        } else if (indexUsoSuelo.getUsoSueloID() == 6 && txtOtroUsoSuelo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Si selecciono otro uso de suelo, debe especificar ",
                    "Suelo", JOptionPane.INFORMATION_MESSAGE);
            txtOtroUsoSuelo.requestFocus();
            return false;
        } else if (txtEspesor.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar espesor ",
                    "Suelo", JOptionPane.INFORMATION_MESSAGE);
            txtEspesor.requestFocus();
            return false;
        } else if (txtPendienteDominante.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar la pendiente dominante ",
                    "Suelo", JOptionPane.INFORMATION_MESSAGE);
            txtPendienteDominante.requestFocus();
            return false;
        } else {
            return true;
        }
    }
    
    private boolean validarDatosSuelo() {
        if (this.espesor < 1 || this.espesor > 200) {
            JOptionPane.showMessageDialog(null, "Error! El espesor debe ser un valor entre 1 y 200 ",
                    "Suelo", JOptionPane.INFORMATION_MESSAGE);
            txtEspesor.requestFocus();
            return false;
        } else if (this.pendienteDominante < 0 || this.pendienteDominante > 100) {
            JOptionPane.showMessageDialog(null, "Error! La pendiente dominante debe ser un valor entre 0 y 100",
                    "Suelo", JOptionPane.INFORMATION_MESSAGE);
            txtPendienteDominante.requestFocus();
            return false;
        } else {
            return true;
        }
    }
    
    private void asignarDatosSuelo() {
        try {
            this.espesor = Float.valueOf(txtEspesor.getText());
        } catch (NumberFormatException e) {
            this.espesor = null;
        }

        try {
            this.pendienteDominante = Integer.valueOf(txtPendienteDominante.getText());
        } catch (NumberFormatException e) {
            this.pendienteDominante = null;
        }
        this.observaciones = txtObservaciones.getText();
    }
    
    private void crearSuelo() {
        CESuelo ceSuelo = new CESuelo();
        CatEUsoSuelo usoSueloID = (CatEUsoSuelo) cmbUsoSuelo.getSelectedItem();
        ceSuelo.setSitioID(this.sitioID);
        ceSuelo.setUsoSueloID(usoSueloID.getUsoSueloID());
        ceSuelo.setOtroUsoSuelo(this.otroUsoSuelo);
        ceSuelo.setEspesor(this.espesor);
        ceSuelo.setPendienteDominante(this.pendienteDominante);
        ceSuelo.setObservaciones(this.observaciones);
        this.cdSuelo.insertDatosSuelo(ceSuelo);
    }
    
    private void actualizarSuelo(){
        CatEUsoSuelo usoSueloID = (CatEUsoSuelo) cmbUsoSuelo.getSelectedItem();
        this.ceSuelo.setSitioID(this.sitioID);
        this.ceSuelo.setUsoSueloID(usoSueloID.getUsoSueloID());
        this.ceSuelo.setOtroUsoSuelo(this.otroUsoSuelo);
        this.ceSuelo.setEspesor(this.espesor);
        this.ceSuelo.setPendienteDominante(this.pendienteDominante);
        this.ceSuelo.setObservaciones(this.observaciones);
        this.cdSuelo.updateDatosSuelo(ceSuelo);
    }
    
    private void eliminarSuelo(){
        this.cdSuelo.deleteDatosSuelo(this.sitioID);
    }
    
    private boolean validarObligatoriosVarilla() {
        if (txtAzimut1.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar datos para el azimut 1",
                    "Suelo", JOptionPane.INFORMATION_MESSAGE);
            txtAzimut1.requestFocus();
            return false;
        } else if (txtDistancia1.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar datos para la distancia 1",
                    "Suelo", JOptionPane.INFORMATION_MESSAGE);
            txtDistancia1.requestFocus();
            return false;
        } else if (txtProfundidad1.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar datos para la profundidad 1",
                    "Suelo", JOptionPane.INFORMATION_MESSAGE);
            txtProfundidad1.requestFocus();
            return false;
        } else if (txtAzimut2.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar datos para el azimut 2",
                    "Suelo", JOptionPane.INFORMATION_MESSAGE);
            txtAzimut2.requestFocus();
            return false;
        } else if (txtDistancia2.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar datos para la distancia 2",
                    "Suelo", JOptionPane.INFORMATION_MESSAGE);
            txtDistancia2.requestFocus();
            return false;
        } else if (txtProfundidad2.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar datos para la profundidad 2",
                    "Suelo", JOptionPane.INFORMATION_MESSAGE);
            txtProfundidad2.requestFocus();
            return false;
        } else if (txtAzimut3.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar datos para el azimut 3",
                    "Suelo", JOptionPane.INFORMATION_MESSAGE);
            txtAzimut3.requestFocus();
            return false;
        } else if (txtDistancia3.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar datos para la distancia 3",
                    "Suelo", JOptionPane.INFORMATION_MESSAGE);
            txtDistancia3.requestFocus();
            return false;
        } else if (txtProfundidad3.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar datos para la profundidad 3",
                    "Suelo", JOptionPane.INFORMATION_MESSAGE);
            txtProfundidad3.requestFocus();
            return false;
        } else if (txtAzimut4.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar datos para el azimut 4",
                    "Suelo", JOptionPane.INFORMATION_MESSAGE);
            txtAzimut4.requestFocus();
            return false;
        } else if (txtDistancia4.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar datos para la distancia 4",
                    "Suelo", JOptionPane.INFORMATION_MESSAGE);
            txtDistancia4.requestFocus();
            return false;
        } else if (txtProfundidad4.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar datos para la profundidad 4",
                    "Suelo", JOptionPane.INFORMATION_MESSAGE);
            txtProfundidad4.requestFocus();
            return false;
        } else if (txtProfundidad5.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar datos para la profundidad 5",
                    "Suelo", JOptionPane.INFORMATION_MESSAGE);
            txtProfundidad5.requestFocus();
            return false;
        } else {
            return true;
        }
    }
    
    private void limpiarSuelo(){
        cmbUsoSuelo.setSelectedItem(null);
        txtOtroUsoSuelo.setText("");
        txtOtroUsoSuelo.setEnabled(false);
        txtEspesor.setValue(null);
        txtEspesor.setText("");
        txtPendienteCobertura.setValue(null);
        txtPendienteCobertura.setText("");
        cmbTransecto.setSelectedItem(null);
        txtAzimut1.setValue(null);
        txtAzimut1.setText("");
        txtAzimut2.setValue(null);
        txtAzimut2.setText("");
        txtAzimut3.setValue(null);
        txtAzimut3.setText("");
        txtAzimut4.setValue(null);
        txtAzimut4.setText("");
        txtAzimut5.setValue(null);
        txtAzimut5.setText("");
        txtAzimut5.setEnabled(false);
        txtDistancia1.setValue(null);
        txtDistancia1.setText("");
        txtDistancia2.setValue(null);
        txtDistancia2.setText("");
        txtDistancia3.setValue(null);
        txtDistancia3.setText("");
        txtDistancia4.setValue(null);
        txtDistancia4.setText("");
        txtDistancia5.setValue(null);
        txtDistancia5.setText("");
        txtDistancia5.setEnabled(false);
        txtProfundidad1.setValue(null);
        txtProfundidad1.setText("");
        txtProfundidad2.setValue(null);
        txtProfundidad2.setText("");
        txtProfundidad3.setValue(null);
        txtProfundidad3.setText("");
        txtProfundidad4.setValue(null);
        txtProfundidad4.setText("");
        txtProfundidad5.setValue(null);
        txtProfundidad5.setText("");
    }
    
    private void asignarDatosVarillas1() {
        this.noVarilla1 = 1;
        try {
            this.azimut1 = Integer.valueOf(txtAzimut1.getText());
        } catch (NumberFormatException e) {
            this.azimut1 = null;
        }
        try {
            this.distancia1 = Float.valueOf(txtDistancia1.getText());
        } catch (NumberFormatException e) {
            this.distancia1 = null;
        }
        try {
            this.profundidad1 = Float.valueOf(txtProfundidad1.getText());
        } catch (NumberFormatException e) {
            this.profundidad1 = null;
        }
    }
    
    private void asignarDatosVarillas2() {
        this.noVarilla2 = 2;
        try {
            this.azimut2 = Integer.valueOf(txtAzimut2.getText());
        } catch (NumberFormatException e) {
            this.azimut2 = null;
        }
        try {
            this.distancia2 = Float.valueOf(txtDistancia2.getText());
        } catch (NumberFormatException e) {
            this.distancia2 = null;
        }
        try {
            this.profundidad2 = Float.valueOf(txtProfundidad2.getText());
        } catch (NumberFormatException e) {
            this.profundidad2 = null;
        }
    }
    
    private void asignarDatosVarillas3() {
        this.noVarilla3 = 3;
        try {
            this.azimut3 = Integer.valueOf(txtAzimut3.getText());
        } catch (NumberFormatException e) {
            this.azimut3 = null;
        }
        try {
            this.distancia3 = Float.valueOf(txtDistancia3.getText());
        } catch (NumberFormatException e) {
            this.distancia3 = null;
        }
        try {
            this.profundidad3 = Float.valueOf(txtProfundidad3.getText());
        } catch (NumberFormatException e) {
            this.profundidad3 = null;
        }
    }

    private void asignarDatosVarillas4() {
        this.noVarilla4 = 4;
        try {
            this.azimut4 = Integer.valueOf(txtAzimut4.getText());
        } catch (NumberFormatException e) {
            this.azimut4 = null;
        }
        try {
            this.distancia4 = Float.valueOf(txtDistancia4.getText());
        } catch (NumberFormatException e) {
            this.distancia4 = null;
        }
        try {
            this.profundidad4 = Float.valueOf(txtProfundidad4.getText());
        } catch (NumberFormatException e) {
            this.profundidad4 = null;
        }
    }
    
    private void asignarDatosVarillas5(){
        this.noVarilla5 = 5;
        try{
            this.azimut5 = Integer.valueOf(txtAzimut5.getText());
        }catch(NumberFormatException e){
            this.azimut5 = null;
        }
        try{
            this.distancia5 = Float.valueOf(txtDistancia5.getText());
        }catch(NumberFormatException e){
            this.distancia5 = null;
        }
        try{
            this.profundidad5 = Float.valueOf(txtProfundidad5.getText());
        }catch(NumberFormatException e){
            this.profundidad5 = null;
        }
    }
    
    private void crearVarilla1(){
        this.varilla1.setSitioID(this.sitioID);
        this.varilla1.setNoVarilla(this.noVarilla1);
        this.varilla1.setAzimut(this.azimut1);
        this.varilla1.setDistancia(this.distancia1);
        this.varilla1.setProfundidad(this.profundidad1);
        
        this.cdVarillas.insertDatosVarillas(varilla1);
    }
    
    private void crearVarilla2(){
        this.varilla2.setSitioID(this.sitioID);
        this.varilla2.setNoVarilla(this.noVarilla2);
        this.varilla2.setAzimut(this.azimut2);
        this.varilla2.setDistancia(this.distancia2);
        this.varilla2.setProfundidad(this.profundidad2);
        
        this.cdVarillas.insertDatosVarillas(varilla2);
    }
    
    private void crearVarilla3(){
        this.varilla3.setSitioID(this.sitioID);
        this.varilla3.setNoVarilla(this.noVarilla3);
        this.varilla3.setAzimut(this.azimut3);
        this.varilla3.setDistancia(this.distancia3);
        this.varilla3.setProfundidad(this.profundidad3);
        
        this.cdVarillas.insertDatosVarillas(varilla3);
    }
    
    private void crearVarilla4(){
        this.varilla4.setSitioID(this.sitioID);
        this.varilla4.setNoVarilla(this.noVarilla4);
        this.varilla4.setAzimut(this.azimut4);
        this.varilla4.setDistancia(this.distancia4);
        this.varilla4.setProfundidad(this.profundidad4);
        
        this.cdVarillas.insertDatosVarillas(varilla4);
    }
    
    private void crearVarilla5(){
        this.varilla5.setSitioID(this.sitioID);
        this.varilla5.setNoVarilla(this.noVarilla5);
        this.varilla5.setAzimut(this.azimut5);
        this.varilla5.setDistancia(this.distancia5);
        this.varilla5.setProfundidad(this.profundidad5);
        this.cdVarillas.insertDatosVarillas(varilla5);
    }
    
    private void actualizarVarilla1(){
        this.varilla1.setSitioID(this.sitioID);
        this.varilla1.setNoVarilla(this.noVarilla1);
        this.varilla1.setAzimut(this.azimut1);
        this.varilla1.setDistancia(this.distancia1);
        this.varilla1.setProfundidad(this.profundidad1);
        this.cdVarillas.updateDatosVarillas(varilla1);
    }
    
    private void actualizarVarilla2(){
        this.varilla2.setSitioID(this.sitioID);
        this.varilla2.setNoVarilla(this.noVarilla2);
        this.varilla2.setAzimut(this.azimut2);
        this.varilla2.setDistancia(this.distancia2);
        this.varilla2.setProfundidad(this.profundidad2);
        this.cdVarillas.updateDatosVarillas(varilla2);
    }
    
    private void actualizarVarilla3(){
        this.varilla3.setSitioID(this.sitioID);
        this.varilla3.setNoVarilla(this.noVarilla3);
        this.varilla3.setAzimut(this.azimut3);
        this.varilla3.setDistancia(this.distancia3);
        this.varilla3.setProfundidad(this.profundidad3);
        this.cdVarillas.updateDatosVarillas(varilla3);
    }
    
    private void actualizarVarilla4(){
        this.varilla4.setSitioID(this.sitioID);
        this.varilla4.setNoVarilla(this.noVarilla4);
        this.varilla4.setAzimut(this.azimut4);
        this.varilla4.setDistancia(this.distancia4);
        this.varilla4.setProfundidad(this.profundidad4);
        this.cdVarillas.updateDatosVarillas(varilla4);
    }
    
    private void actualizarVarilla5(){
        this.varilla5.setSitioID(this.sitioID);
        this.varilla5.setNoVarilla(this.noVarilla5);
        this.varilla5.setAzimut(this.azimut5);
        this.varilla5.setDistancia(this.distancia5);
        this.varilla5.setProfundidad(this.profundidad5);
        this.cdVarillas.updateDatosVarillas(varilla5);
    }
  
    private void eliminarVarillas(){
        this.cdVarillas.deleteDatosVarillas(this.sitioID);
    }
    
    private boolean validarVarillas() {
        if (this.azimut1 != null && validacion.esAzimut(this.azimut1, "Suelo")) {
            txtAzimut1.requestFocus();
            return false;
        } else if (this.azimut2 != null && validacion.esAzimut(this.azimut2, "Suelo")) {
            txtAzimut2.requestFocus();
            return false;
        } else if (this.azimut3 != null && validacion.esAzimut(this.azimut3, "Suelo")) {
            txtAzimut3.requestFocus();
            return false;
        } else if (this.azimut4 != null && validacion.esAzimut(this.azimut4, "Suelo")) {
            txtAzimut4.requestFocus();
            return false;
        } else if (this.azimut5 != null && validacion.esAzimut(this.azimut5, "Suelo")) {
            txtAzimut5.requestFocus();
            return false;
        } else if (this.distancia1 != null && validacion.esDistancia(this.distancia1, "Suelo")) {
            txtDistancia1.requestFocus();
            return false;
        } else if (this.distancia2 != null && validacion.esDistancia(this.distancia2, "Suelo")) {
            txtDistancia2.requestFocus();
            return false;
        } else if (this.distancia3 != null && validacion.esDistancia(this.distancia3, "Suelo")) {
            txtDistancia3.requestFocus();
            return false;
        } else if (this.distancia4 != null && validacion.esDistancia(this.distancia4, "Suelo")) {
            txtDistancia4.requestFocus();
            return false;
        } else if (this.distancia5 != null && validacion.esDistancia(this.distancia5, "Suelo")) {
            txtDistancia5.requestFocus();
            return false;
        } else {
            return true;
        }
    }
    
    private boolean validarCobertura() {
        if (txtPendienteCobertura.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe proporcionar la pendiente de cobertura ",
                    "Suelo", JOptionPane.INFORMATION_MESSAGE);
            txtPendienteCobertura.requestFocus();
            return false;
        } else {
            return true;
        }
    }
    
   private void fijarDatosCobertura(){
        try{
            this.pendienteCobertura = Float.valueOf(txtPendienteCobertura.getText());
        }catch(NumberFormatException e){
            this.pendienteCobertura = null;
        }
    }
    
    private void crearCobertura(){
        Integer transecto = (Integer) cmbTransecto.getSelectedItem();
        
        this.ceCobertura.setSitioID(this.sitioID);
        this.ceCobertura.setTransecto(transecto);
        this.ceCobertura.setPendiente(this.pendienteCobertura);
        
        if(this.cdEvidencia.validarTransectoCobertura(ceCobertura)){
             JOptionPane.showMessageDialog(null, "Ya capturo cobertura para ese transecto, seleccione otro ",
                    "Suelo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            this.cdEvidencia.insertCoberturaSuelo(ceCobertura);
        }
    }
    
    private void actualizarCobertura(){
        Integer transecto = (Integer) cmbTransecto.getSelectedItem();
        this.ceCobertura.setSitioID(this.sitioID);
        this.ceCobertura.setTransecto(transecto);
        this.ceCobertura.setPendiente(this.pendienteCobertura);
        this.cdEvidencia.updateCoberturaSuelo(ceCobertura);
    }
    
    private void eliminarCobertura(){
         Integer transecto = (Integer) cmbTransecto.getSelectedItem();
         this.ceCobertura.setSitioID(this.sitioID);
         this.ceCobertura.setTransecto(transecto);
         this.cdEvidencia.deleteCoberturaSuelo(ceCobertura);
    }
    
    private boolean validarDatosEvidencia(){
        if(cmbLecturaTierra.getSelectedItem() == null){
             JOptionPane.showMessageDialog(null, "Debe seleccioner un tipo de lectura de tierra ",
                    "Suelo", JOptionPane.INFORMATION_MESSAGE);
            cmbLecturaTierra.requestFocus();
            return false;
        } else {
            return true;
        }
    }
    
   private void fijarDatosEvidencia(){
       if(chkDosel.isSelected()){
           this.dosel = 1;
       } else{
           this.dosel = 0;
       }
   }
   
   private void crearDatosEvidencia(){
      Integer indexPunto = (Integer) cmbPunto.getSelectedItem();
      CatELecturaTierra indexLectura = (CatELecturaTierra) cmbLecturaTierra.getSelectedItem();
      Integer transecto = (Integer) cmbTransecto.getSelectedItem();
      
      this.ceCobertura.setSitioID(this.sitioID);
      this.ceCobertura.setTransecto(transecto);
      Integer coberturaSueloID = this.cdEvidencia.getIdCoberturaActual(ceCobertura);
      
      this.ceEvidencia.setCoberturaSueloID(this.coberturaID);
      this.ceEvidencia.setPunto(indexPunto);
      this.ceEvidencia.setDosel(this.dosel);
      this.ceEvidencia.setLecturaTierraID(indexLectura.getLecturaTierraID());
      this.cdEvidencia.insertEvidenciaErosion(ceEvidencia);
   }
   
    private void actualizarDatosEvidencia() {
        try {
            int fila = grdEvidenciaErosion.getSelectedRow();
            String registro = grdEvidenciaErosion.getValueAt(fila, 0).toString();
            
            CatELecturaTierra indexLectura = (CatELecturaTierra) cmbLecturaTierra.getSelectedItem();
            Integer transecto = (Integer) cmbTransecto.getSelectedItem();

            this.ceEvidencia.setEvidenciaErosionID(Integer.parseInt(registro));
            this.ceEvidencia.setDosel(this.dosel);
            this.ceEvidencia.setLecturaTierraID(indexLectura.getLecturaTierraID());

            this.cdEvidencia.updateEvidenciaErosion(ceEvidencia);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla de evidencia de erosion "
                    + e.getClass().getName() + " : " + e.getMessage(), "Longitud de componentes", JOptionPane.INFORMATION_MESSAGE);
        }
    }
   
    private void eliminarDatosEvidencia() {
        try {
            int fila = grdEvidenciaErosion.getSelectedRow();
            String registro = grdEvidenciaErosion.getValueAt(fila, 0).toString();
            this.cdEvidencia.deleteEvidenciaErosion(Integer.parseInt(registro));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla de evidencia de erosion "
                    + e.getClass().getName() + " : " + e.getMessage(), "Longitud de componentes", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void manipularControlesCobertura(boolean habilitar) {
        if (habilitar == true) {
            cmbTransecto.setEnabled(true);
        } else {
            cmbTransecto.setSelectedItem(null);
            cmbTransecto.setEnabled(false);
        }
    }
   
    private boolean validarPorcentajePendiente(float pendiente){
        if(pendiente > 100.00 || pendiente < -100.00){
            JOptionPane.showMessageDialog(null, "El valor introducido debe estar entre 100.00 y -100.00 ",
                    "Suelo", JOptionPane.INFORMATION_MESSAGE);
            txtPendienteCobertura.requestFocus();
            txtPendienteCobertura.setText("");
            return false;
        } else {
            return true;
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblUPM = new javax.swing.JLabel();
        txtUPM = new javax.swing.JTextField();
        lblSitio = new javax.swing.JLabel();
        txtSitio = new javax.swing.JTextField();
        lblSuelo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblUsoActualSuelo = new javax.swing.JLabel();
        cmbUsoSuelo = new javax.swing.JComboBox();
        lblEspesor = new javax.swing.JLabel();
        lblCategoria = new javax.swing.JLabel();
        lblPendienteDominante = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtOtroUsoSuelo = new javax.swing.JTextField();
        txtEspesor = new javax.swing.JFormattedTextField();
        txtPendienteDominante = new javax.swing.JFormattedTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        lblTransecto = new javax.swing.JLabel();
        cmbTransecto = new javax.swing.JComboBox();
        lblPendiente = new javax.swing.JLabel();
        btnCrearTransecto = new javax.swing.JButton();
        btnModificarTransecto = new javax.swing.JButton();
        btnEliminarTransecto = new javax.swing.JButton();
        txtPendienteCobertura = new javax.swing.JFormattedTextField();
        lblPunto = new javax.swing.JLabel();
        cmbPunto = new javax.swing.JComboBox();
        lblLecturaTierra = new javax.swing.JLabel();
        cmbLecturaTierra = new javax.swing.JComboBox();
        btnModificarPunto = new javax.swing.JButton();
        btnEliminarPunto = new javax.swing.JButton();
        btnAgregarPunto = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        grdEvidenciaErosion = new javax.swing.JTable();
        chkDosel = new javax.swing.JCheckBox();
        chkCoberturaSuelo = new javax.swing.JCheckBox();
        lblCoberturaSuelo = new javax.swing.JLabel();
        lblVarillaErosion = new javax.swing.JLabel();
        lblObservaciones = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtObservaciones = new javax.swing.JTextArea();
        btnContinuar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        txtAzimut5 = new javax.swing.JFormattedTextField();
        txtAzimut4 = new javax.swing.JFormattedTextField();
        txtAzimut3 = new javax.swing.JFormattedTextField();
        txtAzimut2 = new javax.swing.JFormattedTextField();
        txtAzimut1 = new javax.swing.JFormattedTextField();
        lblAzimut = new javax.swing.JLabel();
        lblDistancia = new javax.swing.JLabel();
        txtDistancia1 = new javax.swing.JFormattedTextField();
        txtDistancia2 = new javax.swing.JFormattedTextField();
        txtDistancia3 = new javax.swing.JFormattedTextField();
        txtDistancia4 = new javax.swing.JFormattedTextField();
        txtDistancia5 = new javax.swing.JFormattedTextField();
        txtProfundidad5 = new javax.swing.JFormattedTextField();
        txtProfundidad4 = new javax.swing.JFormattedTextField();
        txtProfundidad3 = new javax.swing.JFormattedTextField();
        txtProfundidad2 = new javax.swing.JFormattedTextField();
        txtProfundidad1 = new javax.swing.JFormattedTextField();
        lblProfundidad = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        lblNumeroVarilla = new javax.swing.JLabel();
        lblVarilla1 = new javax.swing.JLabel();
        lblVarilla2 = new javax.swing.JLabel();
        lblVarilla3 = new javax.swing.JLabel();
        lblVarilla4 = new javax.swing.JLabel();
        lblVarilla5 = new javax.swing.JLabel();
        lblUsoSuelo = new javax.swing.JLabel();

        setTitle("Suelo, cobertura y evidencia de erosión");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/gob/conafor/utils/logo_conafor.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(940, 650));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblUPM.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblUPM.setText("UPMID:");
        jPanel1.add(lblUPM, new org.netbeans.lib.awtextra.AbsoluteConstraints(78, 13, -1, -1));

        txtUPM.setEditable(false);
        txtUPM.setEnabled(false);
        jPanel1.add(txtUPM, new org.netbeans.lib.awtextra.AbsoluteConstraints(127, 11, 97, -1));

        lblSitio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblSitio.setText("Sitio:");
        jPanel1.add(lblSitio, new org.netbeans.lib.awtextra.AbsoluteConstraints(709, 13, -1, -1));

        txtSitio.setEditable(false);
        txtSitio.setEnabled(false);
        jPanel1.add(txtSitio, new org.netbeans.lib.awtextra.AbsoluteConstraints(745, 11, 100, -1));

        lblSuelo.setBackground(new java.awt.Color(153, 153, 153));
        lblSuelo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblSuelo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSuelo.setText("Suelos");
        lblSuelo.setOpaque(true);
        jPanel1.add(lblSuelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 39, 899, -1));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblUsoActualSuelo.setText("Uso actual del suelo:");

        cmbUsoSuelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbUsoSueloActionPerformed(evt);
            }
        });

        lblEspesor.setText("Espesor:");

        lblCategoria.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCategoria.setText("Categoría");
        lblCategoria.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblPendienteDominante.setText("% Pendiente dominante:");

        jLabel1.setText("Otro uso:");

        txtOtroUsoSuelo.setEnabled(false);
        txtOtroUsoSuelo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtOtroUsoSueloFocusGained(evt);
            }
        });

        txtEspesor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat(""))));
        txtEspesor.setNextFocusableComponent(txtPendienteDominante);
        txtEspesor.setPreferredSize(new java.awt.Dimension(47, 20));
        txtEspesor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtEspesorFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtEspesorFocusLost(evt);
            }
        });
        txtEspesor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEspesorKeyTyped(evt);
            }
        });

        txtPendienteDominante.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat(""))));
        txtPendienteDominante.setPreferredSize(new java.awt.Dimension(47, 20));
        txtPendienteDominante.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPendienteDominanteFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPendienteDominanteFocusLost(evt);
            }
        });
        txtPendienteDominante.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPendienteDominanteKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblUsoActualSuelo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbUsoSuelo, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtOtroUsoSuelo, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblEspesor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEspesor, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPendienteDominante)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPendienteDominante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblUsoActualSuelo)
                            .addComponent(cmbUsoSuelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(txtOtroUsoSuelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPendienteDominante)
                            .addComponent(txtPendienteDominante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblEspesor)
                                .addComponent(txtEspesor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 900, -1));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTransecto.setText("Transecto:");

        cmbTransecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTransectoActionPerformed(evt);
            }
        });

        lblPendiente.setText("% Pendiente:");

        btnCrearTransecto.setText("Crear");
        btnCrearTransecto.setEnabled(false);
        btnCrearTransecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearTransectoActionPerformed(evt);
            }
        });

        btnModificarTransecto.setText("Modificar");
        btnModificarTransecto.setEnabled(false);
        btnModificarTransecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarTransectoActionPerformed(evt);
            }
        });

        btnEliminarTransecto.setText("Eliminar");
        btnEliminarTransecto.setEnabled(false);
        btnEliminarTransecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarTransectoActionPerformed(evt);
            }
        });

        txtPendienteCobertura.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtPendienteCobertura.setEnabled(false);
        txtPendienteCobertura.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPendienteCoberturaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPendienteCoberturaFocusLost(evt);
            }
        });
        txtPendienteCobertura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPendienteCoberturaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnCrearTransecto, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btnModificarTransecto)
                        .addGap(16, 16, 16)
                        .addComponent(btnEliminarTransecto))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lblTransecto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbTransecto, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPendiente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPendienteCobertura, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTransecto)
                    .addComponent(cmbTransecto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPendiente)
                    .addComponent(txtPendienteCobertura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificarTransecto)
                    .addComponent(btnEliminarTransecto)
                    .addComponent(btnCrearTransecto))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblPunto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPunto.setText("Punto:");

        cmbPunto.setEnabled(false);
        cmbPunto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPuntoActionPerformed(evt);
            }
        });

        lblLecturaTierra.setText("Suelo:");

        cmbLecturaTierra.setEnabled(false);

        btnModificarPunto.setText("Modificar");
        btnModificarPunto.setEnabled(false);
        btnModificarPunto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarPuntoActionPerformed(evt);
            }
        });

        btnEliminarPunto.setText("Eliminar");
        btnEliminarPunto.setEnabled(false);
        btnEliminarPunto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarPuntoActionPerformed(evt);
            }
        });

        btnAgregarPunto.setText("Agregar");
        btnAgregarPunto.setEnabled(false);
        btnAgregarPunto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarPuntoActionPerformed(evt);
            }
        });

        grdEvidenciaErosion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        grdEvidenciaErosion.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        grdEvidenciaErosion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grdEvidenciaErosionMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(grdEvidenciaErosion);

        chkDosel.setBackground(new java.awt.Color(204, 204, 204));
        chkDosel.setText("Dosel");
        chkDosel.setToolTipText("");
        chkDosel.setEnabled(false);
        chkDosel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        chkDosel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                chkDoselKeyPressed(evt);
            }
        });

        chkCoberturaSuelo.setBackground(new java.awt.Color(204, 204, 204));
        chkCoberturaSuelo.setSelected(true);
        chkCoberturaSuelo.setText("Cobertura del suelo y evidencia de erosión");
        chkCoberturaSuelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCoberturaSueloActionPerformed(evt);
            }
        });
        chkCoberturaSuelo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                chkCoberturaSueloKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkCoberturaSuelo)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnAgregarPunto, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(lblPunto, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbPunto, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(btnModificarPunto)
                                        .addGap(24, 24, 24)
                                        .addComponent(btnEliminarPunto, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(31, 31, 31)
                                        .addComponent(chkDosel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblLecturaTierra)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbLecturaTierra, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(chkCoberturaSuelo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPunto)
                    .addComponent(cmbPunto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLecturaTierra)
                    .addComponent(cmbLecturaTierra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkDosel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificarPunto)
                    .addComponent(btnEliminarPunto)
                    .addComponent(btnAgregarPunto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, 380));

        lblCoberturaSuelo.setBackground(new java.awt.Color(153, 153, 153));
        lblCoberturaSuelo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCoberturaSuelo.setText("Cobertura del suelo y evidencia de erosión del suelo");
        lblCoberturaSuelo.setOpaque(true);
        jPanel1.add(lblCoberturaSuelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 334, -1));

        lblVarillaErosion.setBackground(new java.awt.Color(153, 153, 153));
        lblVarillaErosion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVarillaErosion.setText("Varillas de erosión");
        lblVarillaErosion.setOpaque(true);
        jPanel1.add(lblVarillaErosion, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 140, 555, -1));

        lblObservaciones.setBackground(new java.awt.Color(153, 153, 153));
        lblObservaciones.setText("Observaciones:");
        lblObservaciones.setOpaque(true);
        jPanel1.add(lblObservaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 380, 555, -1));

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
        jScrollPane2.setViewportView(txtObservaciones);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 400, 555, 150));

        btnContinuar.setText("Continuar");
        btnContinuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinuarActionPerformed(evt);
            }
        });
        jPanel1.add(btnContinuar, new org.netbeans.lib.awtextra.AbsoluteConstraints(351, 576, -1, -1));

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel1.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(448, 576, 80, -1));

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtAzimut5.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat(""))));
        txtAzimut5.setEnabled(false);
        txtAzimut5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtAzimut5FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtAzimut5FocusLost(evt);
            }
        });
        txtAzimut5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAzimut5KeyTyped(evt);
            }
        });
        jPanel6.add(txtAzimut5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, 100, -1));

        txtAzimut4.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtAzimut4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtAzimut4FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtAzimut4FocusLost(evt);
            }
        });
        txtAzimut4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAzimut4KeyTyped(evt);
            }
        });
        jPanel6.add(txtAzimut4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, 100, -1));

        txtAzimut3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtAzimut3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtAzimut3FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtAzimut3FocusLost(evt);
            }
        });
        txtAzimut3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAzimut3KeyTyped(evt);
            }
        });
        jPanel6.add(txtAzimut3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 100, -1));

        txtAzimut2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtAzimut2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtAzimut2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtAzimut2FocusLost(evt);
            }
        });
        txtAzimut2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAzimut2KeyTyped(evt);
            }
        });
        jPanel6.add(txtAzimut2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 100, -1));

        txtAzimut1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtAzimut1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtAzimut1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtAzimut1FocusLost(evt);
            }
        });
        txtAzimut1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAzimut1KeyTyped(evt);
            }
        });
        jPanel6.add(txtAzimut1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 100, -1));

        lblAzimut.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAzimut.setText("Azimut");
        jPanel6.add(lblAzimut, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 102, -1));

        lblDistancia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDistancia.setText("Distancia(m)");
        jPanel6.add(lblDistancia, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 91, -1));

        txtDistancia1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtDistancia1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDistancia1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDistancia1FocusLost(evt);
            }
        });
        txtDistancia1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDistancia1KeyTyped(evt);
            }
        });
        jPanel6.add(txtDistancia1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, 91, -1));

        txtDistancia2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtDistancia2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDistancia2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDistancia2FocusLost(evt);
            }
        });
        txtDistancia2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDistancia2KeyTyped(evt);
            }
        });
        jPanel6.add(txtDistancia2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 70, 91, -1));

        txtDistancia3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtDistancia3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDistancia3FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDistancia3FocusLost(evt);
            }
        });
        txtDistancia3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDistancia3KeyTyped(evt);
            }
        });
        jPanel6.add(txtDistancia3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, 91, -1));

        txtDistancia4.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtDistancia4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDistancia4FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDistancia4FocusLost(evt);
            }
        });
        txtDistancia4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDistancia4KeyTyped(evt);
            }
        });
        jPanel6.add(txtDistancia4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 130, 91, -1));

        txtDistancia5.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtDistancia5.setEnabled(false);
        txtDistancia5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDistancia5FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDistancia5FocusLost(evt);
            }
        });
        txtDistancia5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDistancia5KeyTyped(evt);
            }
        });
        jPanel6.add(txtDistancia5, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 160, 91, -1));

        txtProfundidad5.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtProfundidad5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtProfundidad5FocusGained(evt);
            }
        });
        txtProfundidad5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtProfundidad5KeyTyped(evt);
            }
        });
        jPanel6.add(txtProfundidad5, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 160, 136, -1));

        txtProfundidad4.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtProfundidad4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtProfundidad4FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtProfundidad4FocusLost(evt);
            }
        });
        txtProfundidad4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtProfundidad4KeyTyped(evt);
            }
        });
        jPanel6.add(txtProfundidad4, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 130, 136, -1));

        txtProfundidad3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtProfundidad3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtProfundidad3FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtProfundidad3FocusLost(evt);
            }
        });
        txtProfundidad3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtProfundidad3KeyTyped(evt);
            }
        });
        jPanel6.add(txtProfundidad3, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 100, 136, -1));

        txtProfundidad2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtProfundidad2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtProfundidad2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtProfundidad2FocusLost(evt);
            }
        });
        txtProfundidad2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtProfundidad2KeyTyped(evt);
            }
        });
        jPanel6.add(txtProfundidad2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 70, 136, -1));

        txtProfundidad1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtProfundidad1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtProfundidad1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtProfundidad1FocusLost(evt);
            }
        });
        txtProfundidad1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtProfundidad1KeyTyped(evt);
            }
        });
        jPanel6.add(txtProfundidad1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 40, 136, -1));

        lblProfundidad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProfundidad.setText("Profundidad enterrada (cm)");
        jPanel6.add(lblProfundidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, 136, -1));

        jPanel9.setBackground(new java.awt.Color(204, 204, 204));

        lblNumeroVarilla.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNumeroVarilla.setText("Varilla");

        lblVarilla1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVarilla1.setText("1");

        lblVarilla2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVarilla2.setText("2");

        lblVarilla3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVarilla3.setText("3");

        lblVarilla4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVarilla4.setText("4");

        lblVarilla5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVarilla5.setText("5");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblNumeroVarilla, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lblVarilla5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                        .addComponent(lblVarilla4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblVarilla3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblVarilla2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblVarilla1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(lblNumeroVarilla)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(lblVarilla1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblVarilla2)
                .addGap(18, 18, 18)
                .addComponent(lblVarilla3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblVarilla4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblVarilla5)
                .addContainerGap())
        );

        jPanel6.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 50, 180));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 170, 555, 195));

        lblUsoSuelo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblUsoSuelo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsoSuelo.setToolTipText("Descricpcion del uso actual del suelo");
        jPanel1.add(lblUsoSuelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 900, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbUsoSueloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbUsoSueloActionPerformed
        CatEUsoSuelo usoSuelo = (CatEUsoSuelo) cmbUsoSuelo.getSelectedItem();
        if (usoSuelo != null) {
            lblUsoSuelo.setText(usoSuelo.getUso());
            if (usoSuelo.getUsoSueloID() == 6) {
                txtOtroUsoSuelo.setEnabled(true);
                txtOtroUsoSuelo.requestFocus();
            } else {
                txtOtroUsoSuelo.setEnabled(false);
                txtOtroUsoSuelo.setText("");
            }
        } else {
            lblUsoSuelo.setText("");
            txtOtroUsoSuelo.setEnabled(false);
            txtOtroUsoSuelo.setText("");
        }
    }//GEN-LAST:event_cmbUsoSueloActionPerformed

    private void cmbTransectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTransectoActionPerformed
        Integer transecto = (Integer) cmbTransecto.getSelectedItem();
        DefaultTableModel model = (DefaultTableModel) grdEvidenciaErosion.getModel();
        DefaultComboBoxModel dcm = (DefaultComboBoxModel) cmbPunto.getModel();
        
        //dcm.removeAllElements();
        if (transecto != null) {
            txtPendienteCobertura.setEnabled(true);
            btnCrearTransecto.setEnabled(true);
            btnEliminarTransecto.setEnabled(true);
            btnModificarTransecto.setEnabled(true);
            this.ceCobertura.setTransecto(transecto);
            this.ceCobertura.setSitioID(this.sitioID);
            this.pendienteCobertura = this.cdEvidencia.getCoberturaSuelo(ceCobertura);
           // dcm.removeAllElements();
            //fillCmbPuntos(this.coberturaID);
            if (this.pendienteCobertura == null) {
                txtPendienteCobertura.setText("");
                txtPendienteCobertura.setValue(null);
                cmbPunto.setEnabled(false);
                cmbLecturaTierra.setEnabled(false);
                cmbLecturaTierra.setSelectedItem(null);
                grdEvidenciaErosion.setModel(new DefaultTableModel());
                dcm.removeAllElements();
            } else {
                txtPendienteCobertura.setText(String.valueOf(this.pendienteCobertura));
                cmbPunto.setEnabled(true);
                chkDosel.setSelected(false);
                cmbLecturaTierra.setSelectedItem(null);
                this.ceCobertura.setTransecto(transecto);
                this.ceCobertura.setSitioID(this.sitioID);
                this.coberturaID = this.cdEvidencia.getIdCoberturaActual(this.ceCobertura);
                dcm.removeAllElements();
                fillCmbPuntos(this.coberturaID);
                llenarTablaEvidenciaErosion();
            }
        } else {
            txtPendienteCobertura.setEnabled(false);
            txtPendienteCobertura.setText("");
            btnCrearTransecto.setEnabled(false);
            btnEliminarTransecto.setEnabled(false);
            btnModificarTransecto.setEnabled(false);
           // dcm.removeAllElements();
            cmbPunto.setEnabled(false);
            cmbPunto.setSelectedItem(null);
            cmbLecturaTierra.setEnabled(false);
            cmbLecturaTierra.setSelectedItem(null);
            grdEvidenciaErosion.setModel(new DefaultTableModel());
        }
        //dcm.removeAllElements();
    }//GEN-LAST:event_cmbTransectoActionPerformed

    private void cmbPuntoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPuntoActionPerformed
        Integer punto = (Integer) cmbPunto.getSelectedItem();
        DefaultComboBoxModel dcm = (DefaultComboBoxModel) cmbPunto.getModel();
        
        if (punto == null) {
            chkDosel.setEnabled(false);
            cmbLecturaTierra.setEnabled(false);
            btnAgregarPunto.setEnabled(false);
            btnModificarPunto.setEnabled(false);
            btnEliminarPunto.setEnabled(false);
        } else {
            chkDosel.setEnabled(true);
            cmbLecturaTierra.setEnabled(true);
            btnAgregarPunto.setEnabled(true);
            btnModificarPunto.setEnabled(true);
            btnEliminarPunto.setEnabled(true);
        }
        //dcm.removeAllElements();
       // fillCmbPuntos(this.coberturaID);
    }//GEN-LAST:event_cmbPuntoActionPerformed

    private void txtOtroUsoSueloFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtOtroUsoSueloFocusGained
        txtOtroUsoSuelo.selectAll();
    }//GEN-LAST:event_txtOtroUsoSueloFocusGained

    private void txtEspesorFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEspesorFocusGained
         SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtEspesor.selectAll();
            }
        });
    }//GEN-LAST:event_txtEspesorFocusGained

    private void txtPendienteDominanteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPendienteDominanteFocusGained
       SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtPendienteDominante.selectAll();
            }
        });
    }//GEN-LAST:event_txtPendienteDominanteFocusGained

    private void txtPendienteCoberturaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPendienteCoberturaFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtPendienteCobertura.selectAll();
            }
        });
    }//GEN-LAST:event_txtPendienteCoberturaFocusGained

    private void txtAzimut1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAzimut1FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtAzimut1.selectAll();
            }
        });
    }//GEN-LAST:event_txtAzimut1FocusGained

    private void txtAzimut2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAzimut2FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtAzimut2.selectAll();
            }
        });
    }//GEN-LAST:event_txtAzimut2FocusGained

    private void txtAzimut3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAzimut3FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtAzimut3.selectAll();
            }
        });
    }//GEN-LAST:event_txtAzimut3FocusGained

    private void txtAzimut4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAzimut4FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtAzimut4.selectAll();
            }
        });
    }//GEN-LAST:event_txtAzimut4FocusGained

    private void txtAzimut5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAzimut5FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtAzimut5.selectAll();
            }
        });
    }//GEN-LAST:event_txtAzimut5FocusGained

    private void txtDistancia1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDistancia1FocusGained
       SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtDistancia1.selectAll();
            }
        });
    }//GEN-LAST:event_txtDistancia1FocusGained

    private void txtDistancia2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDistancia2FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtDistancia2.selectAll();
            }
        });
    }//GEN-LAST:event_txtDistancia2FocusGained

    private void txtDistancia3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDistancia3FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtDistancia3.selectAll();
            }
        });
    }//GEN-LAST:event_txtDistancia3FocusGained

    private void txtDistancia4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDistancia4FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtDistancia4.selectAll();
            }
        });
    }//GEN-LAST:event_txtDistancia4FocusGained

    private void txtDistancia5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDistancia5FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtDistancia5.selectAll();
            }
        });
    }//GEN-LAST:event_txtDistancia5FocusGained

    private void txtProfundidad1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtProfundidad1FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtProfundidad1.selectAll();
            }
        });
    }//GEN-LAST:event_txtProfundidad1FocusGained

    private void txtProfundidad2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtProfundidad2FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtProfundidad2.selectAll();
            }
        });
    }//GEN-LAST:event_txtProfundidad2FocusGained

    private void txtProfundidad3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtProfundidad3FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtProfundidad3.selectAll();
            }
        });
    }//GEN-LAST:event_txtProfundidad3FocusGained

    private void txtProfundidad4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtProfundidad4FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtProfundidad4.selectAll();
            }
        });
    }//GEN-LAST:event_txtProfundidad4FocusGained

    private void txtProfundidad5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtProfundidad5FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtProfundidad5.selectAll();
            }
        });
    }//GEN-LAST:event_txtProfundidad5FocusGained

    private void txtObservacionesFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtObservacionesFocusGained
        txtObservaciones.selectAll();
    }//GEN-LAST:event_txtObservacionesFocusGained

    private void txtEspesorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEspesorFocusLost
        if (txtEspesor.getText().isEmpty()) {
            txtEspesor.setValue(null);
            lblCategoria.setText("Categoría");
        } else {
            Integer espesor = Integer.valueOf(txtEspesor.getText());
            if (espesor >= 0 || espesor < 15) {
                lblCategoria.setText("Muy somero");
            } else if (espesor >= 15 || espesor <= 30) {
                lblCategoria.setText("Somero");
            } else if (espesor >= 30 || espesor <= 60) {
                lblCategoria.setText("Mediano");
            } else if (espesor >= 60 || espesor <= 90) {
                lblCategoria.setText("Profundo");
            } else if (espesor > 90) {
                lblCategoria.setText("Muy profundo");
            }            
        }
    }//GEN-LAST:event_txtEspesorFocusLost

    private void txtPendienteDominanteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPendienteDominanteFocusLost
        if(txtPendienteDominante.getText().isEmpty()){
            txtPendienteDominante.setValue(null);
        }
    }//GEN-LAST:event_txtPendienteDominanteFocusLost

    private void txtPendienteCoberturaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPendienteCoberturaFocusLost
       if(txtPendienteCobertura.getText().isEmpty()){
           txtPendienteCobertura.setValue(null);
       }
    }//GEN-LAST:event_txtPendienteCoberturaFocusLost

    private void txtAzimut1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAzimut1FocusLost
       if(txtAzimut1.getText().isEmpty()){
           txtAzimut1.setValue(null);
       }
    }//GEN-LAST:event_txtAzimut1FocusLost

    private void txtAzimut2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAzimut2FocusLost
        if(txtAzimut2.getText().isEmpty()){
           txtAzimut2.setValue(null);
       }
    }//GEN-LAST:event_txtAzimut2FocusLost

    private void txtAzimut3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAzimut3FocusLost
        if(txtAzimut3.getText().isEmpty()){
           txtAzimut3.setValue(null);
       }
    }//GEN-LAST:event_txtAzimut3FocusLost

    private void txtAzimut4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAzimut4FocusLost
        if(txtAzimut4.getText().isEmpty()){
           txtAzimut4.setValue(null);
       }
    }//GEN-LAST:event_txtAzimut4FocusLost

    private void txtAzimut5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAzimut5FocusLost
        if(txtAzimut5.getText().isEmpty()){
           txtAzimut5.setValue(null);
       }
    }//GEN-LAST:event_txtAzimut5FocusLost

    private void txtDistancia1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDistancia1FocusLost
        if(txtDistancia1.getText().isEmpty()){
           txtDistancia1.setValue(null);
       }
    }//GEN-LAST:event_txtDistancia1FocusLost

    private void txtDistancia2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDistancia2FocusLost
        if(txtDistancia2.getText().isEmpty()){
           txtDistancia2.setValue(null);
       }
    }//GEN-LAST:event_txtDistancia2FocusLost

    private void txtDistancia3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDistancia3FocusLost
        if(txtDistancia3.getText().isEmpty()){
           txtDistancia3.setValue(null);
       }
    }//GEN-LAST:event_txtDistancia3FocusLost

    private void txtDistancia4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDistancia4FocusLost
         if(txtDistancia4.getText().isEmpty()){
           txtDistancia4.setValue(null);
       }
    }//GEN-LAST:event_txtDistancia4FocusLost

    private void txtDistancia5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDistancia5FocusLost
         if(txtDistancia5.getText().isEmpty()){
           txtDistancia5.setValue(null);
       }
    }//GEN-LAST:event_txtDistancia5FocusLost

    private void txtProfundidad1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtProfundidad1FocusLost
        if(txtProfundidad1.getText().isEmpty()){
           txtProfundidad1.setValue(null);
       }
    }//GEN-LAST:event_txtProfundidad1FocusLost

    private void txtProfundidad2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtProfundidad2FocusLost
        if(txtProfundidad2.getText().isEmpty()){
           txtProfundidad2.setValue(null);
       }
    }//GEN-LAST:event_txtProfundidad2FocusLost

    private void txtProfundidad3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtProfundidad3FocusLost
        if(txtProfundidad3.getText().isEmpty()){
           txtProfundidad3.setValue(null);
       }
    }//GEN-LAST:event_txtProfundidad3FocusLost

    private void txtProfundidad4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtProfundidad4FocusLost
         if(txtProfundidad4.getText().isEmpty()){
           txtProfundidad4.setValue(null);
       }
    }//GEN-LAST:event_txtProfundidad4FocusLost

    private void btnContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuarActionPerformed
        asignarDatosSuelo();
        asignarDatosVarillas1();
        asignarDatosVarillas2();
        asignarDatosVarillas3();
        asignarDatosVarillas4();
        asignarDatosVarillas5();
        if (validarObligatoriosSuelo() && validarDatosSuelo() && validarObligatoriosVarilla()
                && validarVarillas()) {
            if (chkCoberturaSuelo.isSelected() && this.cdEvidencia.validarTablaCubiertaSuelo(this.sitioID)) {
                JOptionPane.showMessageDialog(null, "Si selecciona cobertura de suelo y evidencia de erosión, se deben capturar"
                        + "", "Suelo", JOptionPane.INFORMATION_MESSAGE);
                cmbTransecto.requestFocus();
            } else if (chkCoberturaSuelo.isSelected() && this.cdEvidencia.validarTablaEvidenciaErosion(this.sitioID)) {
                JOptionPane.showMessageDialog(null, "Si se selecciona cobertura de suelo se debe capturar evidencia de erosión"
                        + "", "Suelo", JOptionPane.INFORMATION_MESSAGE);
                cmbTransecto.requestFocus();
            } else if (this.actualizar == 0) {
                crearSuelo();
                crearVarilla1();
                crearVarilla2();
                crearVarilla3();
                crearVarilla4();
                crearVarilla5();
                this.hide();
                UPMForms.condicionDegradacion.setDatosiniciales(this.ceSitio);
                UPMForms.condicionDegradacion.setVisible(true);
                this.cdSecuencia.updateSecuencia(this.ceSitio, FORMATO_ID, 1);
            } else {
                 JOptionPane.showMessageDialog(null, "Actualizar");
                actualizarSuelo();
                actualizarVarilla1();
                actualizarVarilla2();
                actualizarVarilla3();
                actualizarVarilla4();
                actualizarVarilla5();
                this.hide();
                UPMForms.condicionDegradacion.revisarCondicionDegradacion(this.ceSitio);
                UPMForms.condicionDegradacion.setVisible(true);
            }
        }
    }//GEN-LAST:event_btnContinuarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
       this.hide();
       this.funciones.manipularBotonesMenuPrincipal(false);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnCrearTransectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearTransectoActionPerformed
        fijarDatosCobertura();
        if(validarCobertura() && validarPorcentajePendiente(this.pendienteCobertura)){
            crearCobertura();
             cmbTransecto.setSelectedItem(null);
             txtPendienteCobertura.setText("");
             txtPendienteCobertura.setValue(null);
             cmbTransecto.requestFocus();
        }
    }//GEN-LAST:event_btnCrearTransectoActionPerformed

    private void btnModificarTransectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarTransectoActionPerformed
        fijarDatosCobertura();
        if(validarCobertura() && validarPorcentajePendiente(this.pendienteCobertura)){
            actualizarCobertura();
             cmbTransecto.setSelectedItem(null);
             txtPendienteCobertura.setText("");
             txtPendienteCobertura.setValue(null);
             cmbTransecto.requestFocus();
        }
    }//GEN-LAST:event_btnModificarTransectoActionPerformed

    private void btnEliminarTransectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarTransectoActionPerformed
        fijarDatosCobertura();
        eliminarCobertura();
        cmbTransecto.setSelectedItem(null);
        txtPendienteCobertura.setText("");
        txtPendienteCobertura.setValue(null);
        cmbTransecto.requestFocus();
    }//GEN-LAST:event_btnEliminarTransectoActionPerformed

    private void grdEvidenciaErosionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grdEvidenciaErosionMouseClicked
        if (evt.getButton() == 1) {
            int fila = grdEvidenciaErosion.getSelectedRow();
            String id = grdEvidenciaErosion.getValueAt(fila, 0).toString();
            this.evidenciaID = Integer.parseInt(id);
            this.ceEvidencia = this.cdEvidencia.getEvidenciaErosionPunto(evidenciaID);
            chkDosel.setEnabled(true);
            cmbLecturaTierra.setEnabled(true);
           // btnAgregarPunto.setEnabled(true);
            btnModificarPunto.setEnabled(true);
            btnEliminarPunto.setEnabled(true);
            Integer indexPunto = this.ceEvidencia.getPunto();
            cmbPunto.setSelectedItem(indexPunto);

            this.dosel = this.ceEvidencia.getDosel();
            if (this.dosel == 1) {
                chkDosel.setSelected(true);
            } else {
                chkDosel.setSelected(false);
            }

            CatELecturaTierra indexLectura = new CatELecturaTierra();
            indexLectura.setLecturaTierraID(this.ceEvidencia.getLecturaTierraID());
            cmbLecturaTierra.setSelectedItem(indexLectura);
        }
    }//GEN-LAST:event_grdEvidenciaErosionMouseClicked

    private void btnAgregarPuntoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarPuntoActionPerformed
        DefaultComboBoxModel dcm = (DefaultComboBoxModel) cmbPunto.getModel();
        
        fijarDatosEvidencia();
        if (validarDatosEvidencia()) {
            crearDatosEvidencia();
            llenarTablaEvidenciaErosion();
            cmbLecturaTierra.setSelectedItem(null);
            cmbPunto.requestFocus();
        }
         dcm.removeAllElements();
         fillCmbPuntos(this.coberturaID);
    }//GEN-LAST:event_btnAgregarPuntoActionPerformed

    private void btnModificarPuntoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarPuntoActionPerformed
        fijarDatosEvidencia();
        if (validarDatosEvidencia()) {
            actualizarDatosEvidencia();
            cmbLecturaTierra.setSelectedItem(null);
            cmbLecturaTierra.setEnabled(false);
            chkDosel.setSelected(false);
            chkDosel.setEnabled(false);
            cmbPunto.requestFocus();
            llenarTablaEvidenciaErosion();
        }
    }//GEN-LAST:event_btnModificarPuntoActionPerformed

    private void btnEliminarPuntoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarPuntoActionPerformed
        DefaultComboBoxModel dcm = (DefaultComboBoxModel) cmbPunto.getModel();
        eliminarDatosEvidencia();
        cmbLecturaTierra.setSelectedItem(null);
        cmbLecturaTierra.setEnabled(false);
        chkDosel.setSelected(false);
        chkDosel.setEnabled(false);
        cmbPunto.requestFocus();
        dcm.removeAllElements();
        fillCmbPuntos(this.coberturaID);
        llenarTablaEvidenciaErosion();
    }//GEN-LAST:event_btnEliminarPuntoActionPerformed

    private void txtEspesorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEspesorKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtEspesorKeyTyped

    private void txtPendienteDominanteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPendienteDominanteKeyTyped
         numeros.keyTyped(evt);
    }//GEN-LAST:event_txtPendienteDominanteKeyTyped

    private void txtPendienteCoberturaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPendienteCoberturaKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtPendienteCoberturaKeyTyped

    private void txtAzimut1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAzimut1KeyTyped
         numeros.keyTyped(evt);
    }//GEN-LAST:event_txtAzimut1KeyTyped

    private void txtDistancia1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDistancia1KeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtDistancia1KeyTyped

    private void txtProfundidad1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProfundidad1KeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtProfundidad1KeyTyped

    private void txtAzimut2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAzimut2KeyTyped
         numeros.keyTyped(evt);
    }//GEN-LAST:event_txtAzimut2KeyTyped

    private void txtDistancia2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDistancia2KeyTyped
         numeros.keyTyped(evt);
    }//GEN-LAST:event_txtDistancia2KeyTyped

    private void txtProfundidad2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProfundidad2KeyTyped
         numeros.keyTyped(evt);
    }//GEN-LAST:event_txtProfundidad2KeyTyped

    private void txtAzimut3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAzimut3KeyTyped
         numeros.keyTyped(evt);
    }//GEN-LAST:event_txtAzimut3KeyTyped

    private void txtDistancia3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDistancia3KeyTyped
         numeros.keyTyped(evt);
    }//GEN-LAST:event_txtDistancia3KeyTyped

    private void txtProfundidad3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProfundidad3KeyTyped
         numeros.keyTyped(evt);
    }//GEN-LAST:event_txtProfundidad3KeyTyped

    private void txtAzimut4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAzimut4KeyTyped
         numeros.keyTyped(evt);
    }//GEN-LAST:event_txtAzimut4KeyTyped

    private void txtDistancia4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDistancia4KeyTyped
         numeros.keyTyped(evt);
    }//GEN-LAST:event_txtDistancia4KeyTyped

    private void txtProfundidad4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProfundidad4KeyTyped
         numeros.keyTyped(evt);
    }//GEN-LAST:event_txtProfundidad4KeyTyped

    private void txtProfundidad5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProfundidad5KeyTyped
         numeros.keyTyped(evt);
    }//GEN-LAST:event_txtProfundidad5KeyTyped

    private void txtAzimut5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAzimut5KeyTyped
         numeros.keyTyped(evt);
    }//GEN-LAST:event_txtAzimut5KeyTyped

    private void txtDistancia5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDistancia5KeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtDistancia5KeyTyped

    private void chkCoberturaSueloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCoberturaSueloActionPerformed
        if (chkCoberturaSuelo.isSelected()) {
            manipularControlesCobertura(true);
        } else {
            Object[] opciones = {"Si", "No"};
            int respuesta = JOptionPane.showOptionDialog(null, "Si capturo, se borrarán todos los datos de cobertura del suelo y erosion, ¿Esta seguro?",
                    "Suelo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[1]);
            if (respuesta == JOptionPane.YES_OPTION) {
                this.cdEvidencia.deleteCoberturaSueloSitio(this.sitioID);
                this.funciones.reiniciarTabla(grdEvidenciaErosion);
                llenarTablaEvidenciaErosion();
                this.funciones.reiniciarComboModel(cmbTransecto);
                fillCmbTransectos();
                this.funciones.reiniciarComboModel(cmbPunto);
                manipularControlesCobertura(false);
            } else {
                chkCoberturaSuelo.setSelected(true);
                chkCoberturaSuelo.requestFocus();
            }
        }
    }//GEN-LAST:event_chkCoberturaSueloActionPerformed

    private void txtObservacionesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtObservacionesKeyPressed
         if(evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB){
            evt.consume();
         }
    }//GEN-LAST:event_txtObservacionesKeyPressed

    private void chkCoberturaSueloKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chkCoberturaSueloKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (chkCoberturaSuelo.isSelected()) {
                chkCoberturaSuelo.setSelected(false);
            } else {
                chkCoberturaSuelo.setSelected(true);
            }
        }
    }//GEN-LAST:event_chkCoberturaSueloKeyPressed

    private void chkDoselKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chkDoselKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (chkDosel.isSelected()) {
                chkDosel.setSelected(false);
            } else {
                chkDosel.setSelected(true);
            }
        }
    }//GEN-LAST:event_chkDoselKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarPunto;
    private javax.swing.JButton btnContinuar;
    private javax.swing.JButton btnCrearTransecto;
    private javax.swing.JButton btnEliminarPunto;
    private javax.swing.JButton btnEliminarTransecto;
    private javax.swing.JButton btnModificarPunto;
    private javax.swing.JButton btnModificarTransecto;
    private javax.swing.JButton btnSalir;
    private javax.swing.JCheckBox chkCoberturaSuelo;
    private javax.swing.JCheckBox chkDosel;
    private javax.swing.JComboBox cmbLecturaTierra;
    private javax.swing.JComboBox cmbPunto;
    private javax.swing.JComboBox cmbTransecto;
    private javax.swing.JComboBox cmbUsoSuelo;
    private javax.swing.JTable grdEvidenciaErosion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAzimut;
    private javax.swing.JLabel lblCategoria;
    private javax.swing.JLabel lblCoberturaSuelo;
    private javax.swing.JLabel lblDistancia;
    private javax.swing.JLabel lblEspesor;
    private javax.swing.JLabel lblLecturaTierra;
    private javax.swing.JLabel lblNumeroVarilla;
    private javax.swing.JLabel lblObservaciones;
    private javax.swing.JLabel lblPendiente;
    private javax.swing.JLabel lblPendienteDominante;
    private javax.swing.JLabel lblProfundidad;
    private javax.swing.JLabel lblPunto;
    private javax.swing.JLabel lblSitio;
    private javax.swing.JLabel lblSuelo;
    private javax.swing.JLabel lblTransecto;
    private javax.swing.JLabel lblUPM;
    private javax.swing.JLabel lblUsoActualSuelo;
    private javax.swing.JLabel lblUsoSuelo;
    private javax.swing.JLabel lblVarilla1;
    private javax.swing.JLabel lblVarilla2;
    private javax.swing.JLabel lblVarilla3;
    private javax.swing.JLabel lblVarilla4;
    private javax.swing.JLabel lblVarilla5;
    private javax.swing.JLabel lblVarillaErosion;
    private javax.swing.JFormattedTextField txtAzimut1;
    private javax.swing.JFormattedTextField txtAzimut2;
    private javax.swing.JFormattedTextField txtAzimut3;
    private javax.swing.JFormattedTextField txtAzimut4;
    private javax.swing.JFormattedTextField txtAzimut5;
    private javax.swing.JFormattedTextField txtDistancia1;
    private javax.swing.JFormattedTextField txtDistancia2;
    private javax.swing.JFormattedTextField txtDistancia3;
    private javax.swing.JFormattedTextField txtDistancia4;
    private javax.swing.JFormattedTextField txtDistancia5;
    private javax.swing.JFormattedTextField txtEspesor;
    private javax.swing.JTextArea txtObservaciones;
    private javax.swing.JTextField txtOtroUsoSuelo;
    private javax.swing.JFormattedTextField txtPendienteCobertura;
    private javax.swing.JFormattedTextField txtPendienteDominante;
    private javax.swing.JFormattedTextField txtProfundidad1;
    private javax.swing.JFormattedTextField txtProfundidad2;
    private javax.swing.JFormattedTextField txtProfundidad3;
    private javax.swing.JFormattedTextField txtProfundidad4;
    private javax.swing.JFormattedTextField txtProfundidad5;
    private javax.swing.JTextField txtSitio;
    private javax.swing.JTextField txtUPM;
    // End of variables declaration//GEN-END:variables
}
