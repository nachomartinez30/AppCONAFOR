package gob.conafor.sitio.vie;

import gob.conafor.sitio.mod.CDSitio;
import gob.conafor.sitio.mod.CESitio;
import gob.conafor.sys.mod.CDSecuencia;
import gob.conafor.sys.mod.CDSeguimientoUPM;
import gob.conafor.sys.mod.CESeccionesCapturadas;
import gob.conafor.upm.mod.CEUPM;
import gob.conafor.upm.mod.CatETipoInaccesibilidad;
import gob.conafor.utils.Datos;
import gob.conafor.utils.FuncionesComunes;
import gob.conafor.utils.ValidacionesComunes;
import gob.conafor.utils.Version;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class FrmSitio extends javax.swing.JInternalFrame {

    private CEUPM upm;
    private int upmID;
    private boolean accesible;
    private boolean senial;
    private CDSitio cdSitio = new CDSitio();
    private CESitio ceSitio = new CESitio();
    private final int ubicacionSitio;
    private final int accesibilidad;
    private final int sotoBosque;
    private CDSeguimientoUPM seguimiento = new CDSeguimientoUPM();
    private CESeccionesCapturadas sitioCapturado = new CESeccionesCapturadas();
    private CESeccionesCapturadas siguinete = new CESeccionesCapturadas();
    private Datos numeros = new Datos();
    private FuncionesComunes combo = new FuncionesComunes();
    private Integer esAccesible;
    private Integer senialGPS;
    private Integer azimut;
    private Float distancia;
    private Integer gradosLatitud;
    private Integer minutosLatitud;
    private Float segundosLatitud;
    private Integer gradosLongitud;
    private Integer minutosLongitud;
    private Float segundosLongitud;
    private Integer errorPresicion;
    private Integer evidenciaMuestreo;
    private Integer tipoInaccesibilidad;
    private String explicacionaInaccesibilidad;
    private int actualizacion;
    private Integer sitioID;
    private CDSecuencia navegacion = new CDSecuencia();
    private Version ver=new Version();
    private String version=ver.getVersion();
    
    public FrmSitio() {
        initComponents();
       // combo.reiniciarComboModel(this.cmbUPM);
        //fillCmbUPMS();
        fillCmbInaccesible();
        this.ubicacionSitio = 5;
        this.accesibilidad = 6;
        this.sotoBosque = 8;
    }
    
    public void setdatosIniciales() {
        habilitarControlesIniciales(false);
        //combo.reiniciarComboModel(cmbSitio);
        // fillSitios(upm.getUpmID());
        // Integer sitio = 0;
        fillCmbUPMS();
        combo.manipularBotonesMenuPrincipal(true);
        this.actualizacion = 0;
    }
    
    public void revisarSitio(int sitioID) {
        this.actualizacion = 1;
        this.sitioID = sitioID;
        this.ceSitio = this.cdSitio.getSitioRevision(this.sitioID);
        this.upmID = this.ceSitio.getUpmID();
        Integer upmID = this.ceSitio.getUpmID();
        combo.reiniciarComboModel(cmbSitio);
        Integer sitio = 1;
        fillSitiosRevision();
        cmbSitio.setSelectedItem(sitio);
        chkAccesible.setEnabled(false);
        chkAccesible.setSelected(true);
        cmbSitio.setEnabled(true);
        cmbUPM.setSelectedItem(upmID);
        cmbUPM.setEnabled(false);
        llenarControlesSitio(this.ceSitio);
        chkAccesible.setEnabled(false);
        chkAccesible.setSelected(true);
    }
    
     public void manipularBotonesMenu(){
        combo.manipularBotonesMenuPrincipal(true);
    }
    
    private void fillCmbUPMS(){
        List<Integer> listUPMS = new ArrayList<>();
        listUPMS = cdSitio.getUPMCreado();
        if(listUPMS != null){
            int size = listUPMS.size();
            for(int i = 0; i < size; i++){
                cmbUPM.addItem(listUPMS.get(i));
            }
        }
    }
    
    private void fillSitios(int upmID){
       List<Integer> listSitios = new ArrayList();
        listSitios = cdSitio.getSitios(upmID);
        if(listSitios != null){
            int size = listSitios.size();
            for(int i = 1; i < size; i++){
                cmbSitio.addItem(listSitios.get(i));
            }
        }
    }
    
    private void fillSitiosRevision(){
         List<Integer> listSitios = new ArrayList();
        listSitios = cdSitio.getSitiosRevision();
        if(listSitios != null){
            int size = listSitios.size();
            for(int i = 1; i < size; i++){
                cmbSitio.addItem(listSitios.get(i));
            }
        }
    }
    
    private void fillCmbInaccesible(){
        List<CatETipoInaccesibilidad> listInaccesibilidad = new ArrayList<>();
        listInaccesibilidad = cdSitio.getTipoInaccesibilidad();
        if(listInaccesibilidad != null){
            int size = listInaccesibilidad.size();
            for(int i = 0; i < size; i++){
                cmbInaccesibilidad.addItem(listInaccesibilidad.get(i));
            }
        }
    }
    
    private void llenarControlesSitio(CESitio ceSitio){
        if (ceSitio.getSitioAccesible() == 1) {
            chkAccesible.setSelected(true);
            if (this.ceSitio.getSenialGPS() == 1) {
                chkSenial.setSelected(true);
                txtAzimut.setEnabled(false);
                txtAzimut.setText("");
                txtAzimut.setValue(null);
                txtDistancia.setEnabled(false);
                txtDistancia.setText("");
                txtDistancia.setValue(null);
            } else {
                chkSenial.setSelected(false);
                txtAzimut.setEnabled(true);
                txtAzimut.setText(String.valueOf(ceSitio.getAzimut()));
                txtDistancia.setEnabled(true);
                txtDistancia.setText(String.valueOf(ceSitio.getDistancia()));
            }
            chkSenial.setEnabled(true);
            txtGradosLatitud.setEnabled(true);
            txtGradosLatitud.setText(String.valueOf(ceSitio.getGradosLatitud()));
            txtMinutosLatitud.setEnabled(true);
            txtMinutosLatitud.setText(String.valueOf(ceSitio.getMinutosLatitud()));
            txtSegundosLatitud.setEnabled(true);
            txtSegundosLatitud.setText(String.valueOf(ceSitio.getSegundosLatitud()));
            txtGradosLongitud.setEnabled(true);
            txtGradosLongitud.setText(String.valueOf(ceSitio.getGradosLongitud()));
            txtMinutosLongitud.setEnabled(true);
            txtMinutosLongitud.setText(String.valueOf(ceSitio.getMinutosLongitud()));
            txtSegundosLongitud.setEnabled(true);
            txtSegundosLongitud.setText(String.valueOf(ceSitio.getSegundosLongitud()));
            txtEPE.setEnabled(true);
            txtEPE.setText(String.valueOf(ceSitio.getErrorPrecision()));
            rbtEvidenciaSi.setEnabled(true);
            rbtEvidenciaNo.setEnabled(true);
            if (ceSitio.getEvidenciaMuestreo() == 1) {
                rbtEvidenciaSi.setSelected(true);
                rbtEvidenciaNo.setSelected(false);
            } else {
                rbtEvidenciaSi.setSelected(false);
                 
                rbtEvidenciaNo.setSelected(true);
            }
            cmbInaccesibilidad.setSelectedItem(null);
            cmbInaccesibilidad.setEnabled(false);
            txtExplicacion.setText("");
            txtExplicacion.setEnabled(false);
        } else { //Estado de Sitio inaccesible
            txtAzimut.setEnabled(false);
            txtAzimut.setText("");
            txtAzimut.setValue(null);
            txtDistancia.setEnabled(false);
            txtDistancia.setText("");
            txtDistancia.setValue(null);
            chkSenial.setSelected(false);
            chkSenial.setEnabled(false);
            txtGradosLatitud.setEnabled(false);
            txtGradosLatitud.setText("");
            txtGradosLatitud.setValue(null);
            txtMinutosLatitud.setEnabled(false);
            txtMinutosLatitud.setText("");
            txtMinutosLatitud.setValue(null);
            txtSegundosLatitud.setEnabled(false);
            txtSegundosLatitud.setText("");
            txtSegundosLatitud.setValue(null);
            txtGradosLongitud.setEnabled(false);
            txtGradosLongitud.setText("");
            txtGradosLongitud.setValue(null);
            txtMinutosLongitud.setEnabled(false);
            txtMinutosLongitud.setText("");
            txtMinutosLongitud.setValue(null);
            txtSegundosLongitud.setEnabled(false);
            txtSegundosLongitud.setText("");
            txtSegundosLongitud.setValue(null);
            txtEPE.setEnabled(false);
            txtEPE.setText("");
            txtEPE.setValue(null);
            rbtEvidenciaNo.setSelected(false);
            rbtEvidenciaNo.setEnabled(false);
            rbtEvidenciaSi.setSelected(false);
            rbtEvidenciaSi.setEnabled(false);
            chkAccesible.setSelected(false);
  
            CatETipoInaccesibilidad ceTipoInaccesible = new CatETipoInaccesibilidad();
            ceTipoInaccesible.setTipoInaccesibilidadID(ceSitio.getTipoInaccesibilidadID());
            cmbInaccesibilidad.setSelectedItem(ceTipoInaccesible);
            cmbInaccesibilidad.setEnabled(true);
            txtExplicacion.setText(ceSitio.getExplicacionInaccesibilidad());
            txtExplicacion.setEnabled(true);
        }
    }
    
    private void habilitarControlesIniciales(boolean habilitar) {
        if (habilitar == true) {
            chkAccesible.setEnabled(true);
            chkAccesible.setSelected(true);
            chkSenial.setEnabled(true);
            chkSenial.setSelected(true);
            txtGradosLatitud.setEnabled(true);
            txtMinutosLatitud.setEnabled(true);
            txtSegundosLatitud.setEnabled(true);
            txtGradosLongitud.setEnabled(true);
            txtMinutosLongitud.setEnabled(true);
            txtSegundosLongitud.setEnabled(true);
            txtEPE.setEnabled(true);
            rbtEvidenciaSi.setEnabled(true);
            rbtEvidenciaNo.setEnabled(true);
            cmbInaccesibilidad.setEnabled(false);
            txtExplicacion.setEnabled(false);
        } else {
            chkAccesible.setEnabled(false);
            chkAccesible.setSelected(false);
            chkSenial.setEnabled(false);
            chkSenial.setSelected(false);
            txtAzimut.setEnabled(false);
            txtDistancia.setEnabled(false);
            txtGradosLatitud.setEnabled(false);
            txtMinutosLatitud.setEnabled(false);
            txtSegundosLatitud.setEnabled(false);
            txtGradosLongitud.setEnabled(false);
            txtMinutosLongitud.setEnabled(false);
            txtSegundosLongitud.setEnabled(false);
            txtEPE.setEnabled(false);
            rbtEvidenciaSi.setEnabled(false);
            rbtEvidenciaSi.setSelected(false);
            rbtEvidenciaNo.setEnabled(false);
            rbtEvidenciaNo.setSelected(false);
            cmbInaccesibilidad.setEnabled(false);
            txtExplicacion.setEnabled(false);
        }
    }
   
    private void habilitarControlesPorSitio(int tipo) {
        switch (tipo) {
            case 1:
                chkAccesible.setEnabled(true);
                chkAccesible.setSelected(true);
                chkSenial.setEnabled(true);
                chkSenial.setSelected(true);
                txtAzimut.setEnabled(false);
                txtDistancia.setEnabled(false);
                txtGradosLatitud.setEnabled(true);
                txtMinutosLatitud.setEnabled(true);
                txtSegundosLatitud.setEnabled(true);
                txtGradosLongitud.setEnabled(true);
                txtMinutosLongitud.setEnabled(true);
                txtSegundosLongitud.setEnabled(true);
                txtEPE.setEnabled(true);
                rbtEvidenciaSi.setEnabled(true);
                rbtEvidenciaNo.setEnabled(true);
                cmbInaccesibilidad.setEnabled(false);
                txtExplicacion.setEnabled(false);
                break;
            case 2:
                chkAccesible.setEnabled(true);
                chkAccesible.setSelected(true);
                chkSenial.setEnabled(true);
                chkSenial.setSelected(false);
                txtAzimut.setEnabled(true);
                txtDistancia.setEnabled(true);
                txtGradosLatitud.setEnabled(true);
                txtMinutosLatitud.setEnabled(true);
                txtSegundosLatitud.setEnabled(true);
                txtGradosLongitud.setEnabled(true);
                txtMinutosLongitud.setEnabled(true);
                txtSegundosLongitud.setEnabled(true);
                txtEPE.setEnabled(true);
                rbtEvidenciaSi.setEnabled(true);
                rbtEvidenciaNo.setEnabled(true);
                cmbInaccesibilidad.setEnabled(false);
                txtExplicacion.setEnabled(false);
                break;
            case 3:
                chkAccesible.setEnabled(true);
                chkAccesible.setSelected(false);
                chkSenial.setEnabled(false);
                chkSenial.setSelected(false);
                txtAzimut.setEnabled(false);
                txtDistancia.setEnabled(false);
                txtGradosLatitud.setEnabled(false);
                txtMinutosLatitud.setEnabled(false);
                txtSegundosLatitud.setEnabled(false);
                txtGradosLongitud.setEnabled(false);
                txtMinutosLongitud.setEnabled(false);
                txtSegundosLongitud.setEnabled(false);
                txtEPE.setEnabled(false);
                rbtEvidenciaSi.setEnabled(false);
                rbtEvidenciaNo.setEnabled(false);
                cmbInaccesibilidad.setEnabled(true);
                txtExplicacion.setEnabled(true);
                break;
        }
    }
    
    private boolean esAccesible() {
        if (chkAccesible.isSelected()) {
            chkSenial.setEnabled(true);
            chkSenial.setSelected(true);
            txtGradosLatitud.setEnabled(true);
            txtGradosLatitud.setText("");
            txtMinutosLatitud.setEnabled(true);
            txtMinutosLatitud.setText("");
            txtSegundosLatitud.setEnabled(true);
            txtSegundosLatitud.setText("");
            txtGradosLongitud.setEnabled(true);
            txtGradosLongitud.setText("");
            txtMinutosLongitud.setEnabled(true);
            txtMinutosLongitud.setText("");
            txtSegundosLongitud.setEnabled(true);
            txtSegundosLongitud.setText("");
            txtEPE.setText("");
            txtEPE.setEnabled(true);
            rbtEvidenciaNo.setEnabled(true);
            rbtEvidenciaNo.setSelected(false);
            rbtEvidenciaSi.setEnabled(true);
            rbtEvidenciaSi.setSelected(false);
            cmbInaccesibilidad.setEnabled(false);
            cmbInaccesibilidad.setSelectedIndex(0);
            txtExplicacion.setEnabled(false);
            txtExplicacion.setText("");
            return true;
        } else {
            chkSenial.setEnabled(false);
            chkSenial.setSelected(false);
            txtGradosLatitud.setEnabled(false);
            txtGradosLatitud.setText("");
            txtMinutosLatitud.setEnabled(false);
            txtMinutosLatitud.setText("");
            txtSegundosLatitud.setEnabled(false);
            txtSegundosLatitud.setText("");
            txtGradosLongitud.setEnabled(false);
            txtGradosLongitud.setText("");
            txtMinutosLongitud.setEnabled(false);
            txtMinutosLongitud.setText("");
            txtSegundosLongitud.setEnabled(false);
            txtSegundosLongitud.setText("");
            txtEPE.setEnabled(false);
            txtEPE.setText("");
            rbtEvidenciaNo.setEnabled(false);
            rbtEvidenciaNo.setSelected(false);
            rbtEvidenciaSi.setEnabled(false);
            rbtEvidenciaSi.setSelected(false);
            cmbInaccesibilidad.setEnabled(true);
            cmbInaccesibilidad.setSelectedIndex(0);
            txtExplicacion.setEnabled(true);
            txtExplicacion.setText("");
            return false;
        }
    }
    
    private boolean haySenial() {
        if (chkSenial.isSelected()) {
            txtAzimut.setEnabled(false);
            txtAzimut.setText("");
            txtDistancia.setEnabled(false);
            txtDistancia.setText("");
        } else {
            txtAzimut.setEnabled(true);
            txtAzimut.setText("");
            txtDistancia.setEnabled(true);
            txtDistancia.setText("");
        }
        return false;
    }
    
    private boolean validarCamposObligatoriosSitioAccesible() {
        if (cmbUPM.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null,
                    "Error! debe seleccionar una UPM",
                    "Sitio", JOptionPane.INFORMATION_MESSAGE);
            cmbUPM.requestFocus();
            return false;
        } else if (txtGradosLatitud.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Error! debe proporcionar grados latitud ",
                    "Sitio", JOptionPane.INFORMATION_MESSAGE);
            txtGradosLatitud.requestFocus();
            return false;
        } else if (txtMinutosLatitud.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Error! debe proporcionar minutos latitud ",
                    "Sitio", JOptionPane.INFORMATION_MESSAGE);
            txtMinutosLatitud.requestFocus();
            return false;
        } else if (txtSegundosLatitud.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Error! debe proporcionar segundos latitud ",
                    "Sitio", JOptionPane.INFORMATION_MESSAGE);
            txtSegundosLatitud.requestFocus();
            return false;
        } else if (txtGradosLongitud.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Error! debe proporcionar Grados longitud ",
                    "Sitio", JOptionPane.INFORMATION_MESSAGE);
            txtGradosLongitud.requestFocus();
            return false;
        } else if (txtMinutosLongitud.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Error! debe proporcionar Minutos longitud ",
                    "Sitio", JOptionPane.INFORMATION_MESSAGE);
            txtMinutosLongitud.requestFocus();
            return false;
        } else if (txtSegundosLongitud.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Error! debe proporcionar Segundos longitud ",
                    "Sitio", JOptionPane.INFORMATION_MESSAGE);
            txtSegundosLongitud.requestFocus();
            return false;
        } else if (txtEPE.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Error! debe proporcionar el error de presición ",
                    "Sitio", JOptionPane.INFORMATION_MESSAGE);
            txtEPE.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean validarCamposAuxiliaresObligatorios() {
        if (txtAzimut.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Error! Debe proporcionar el Azimut ",
                    "Sitio", JOptionPane.INFORMATION_MESSAGE);
            txtAzimut.requestFocus();
            return false;
        } else if (txtDistancia.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Error! Debe proporcionar la Distancia ",
                    "Sitio", JOptionPane.INFORMATION_MESSAGE);
            txtDistancia.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean validarCamposObligatoriosSitioInaccesible() {
        if (cmbInaccesibilidad.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null,
                    "Error! Debe seleccionar un tipo de inaccesibilidad ",
                    "Sitio", JOptionPane.INFORMATION_MESSAGE);
            cmbInaccesibilidad.requestFocus();
            return false;
        } else if (txtExplicacion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Error! Debe proporcionar una explicación de inaccesibilidad ",
                    "Sitio", JOptionPane.INFORMATION_MESSAGE);
            txtExplicacion.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean validarDatosCoordenadas() {
        ValidacionesComunes vc = new ValidacionesComunes();
        try {
            this.gradosLatitud = Integer.parseInt(txtGradosLatitud.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Debe proporcionar un dato válido para grados latitud", "Sitio", JOptionPane.INFORMATION_MESSAGE);
            txtGradosLatitud.requestFocus();
        }
        try {
            this.minutosLatitud = Integer.parseInt(txtMinutosLatitud.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Debe proporcionar un dato válido para minutos latitud", "Sitio", JOptionPane.INFORMATION_MESSAGE);
            txtMinutosLatitud.requestFocus();

        }
        try {
            this.segundosLatitud = Float.parseFloat(txtSegundosLatitud.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Debe proporcionar un dato válido para segundos latitud", "Sitio", JOptionPane.INFORMATION_MESSAGE);
            txtSegundosLatitud.requestFocus();

        }
        try {
            this.gradosLongitud = Integer.parseInt(txtGradosLongitud.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Debe proporcionar un dato válido para grados longitud", "Sitio", JOptionPane.INFORMATION_MESSAGE);
            txtGradosLongitud.requestFocus();
        }
        try {
            this.minutosLongitud = Integer.parseInt(txtMinutosLongitud.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Debe proporcionar un dato válido para grados longitud", "Sitio", JOptionPane.INFORMATION_MESSAGE);
            txtMinutosLongitud.requestFocus();
        }
        try {
            this.segundosLongitud = Float.parseFloat(txtSegundosLongitud.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Debe proporcionar un dato válido para segundos longitud", "Sitio", JOptionPane.INFORMATION_MESSAGE);
            txtSegundosLongitud.requestFocus();
        }

        Integer errorPresicion = Integer.parseInt(txtEPE.getText());

        if (vc.sonGradosLatitud(this.gradosLatitud, "Sitio")) {
            txtGradosLatitud.requestFocus();
            txtGradosLatitud.setText("");
            return false;
        } else if (vc.sonMinutos(this.minutosLatitud, "Sitio")) {
            txtMinutosLatitud.requestFocus();
            txtMinutosLatitud.setText("");
            return false;
        } else if (vc.sonSegundos(this.segundosLatitud, "Sitio")) {
            txtSegundosLatitud.requestFocus();
            txtSegundosLatitud.setText("");
            return false;
        } else if (vc.sonGradosLongitud(this.gradosLongitud, "Sitio")) {
            txtGradosLongitud.requestFocus();
            txtGradosLongitud.setText("");
            return false;
        } else if (vc.sonMinutos(this.minutosLongitud, "Sitio")) {
            txtMinutosLongitud.requestFocus();
            txtMinutosLongitud.setText("");
            return false;
        } else if (vc.sonSegundos(this.segundosLongitud, "Sitio")) {
            txtSegundosLongitud.requestFocus();
            txtSegundosLongitud.setText("");
            return false;
        } else if (vc.esErrorPrecision(errorPresicion, "Sitio")) {
            txtEPE.requestFocus();
            txtEPE.setText("");
            return false;
        } else {
            return true;
        }
    }
     
    private boolean validarAzimutDistancia() {
        ValidacionesComunes vc = new ValidacionesComunes();
        int azimut = Integer.parseInt(txtAzimut.getText());
        float distancia = Float.parseFloat(txtDistancia.getText());

        if (vc.esAzimut(azimut, "Sitio")) {
            txtAzimut.requestFocus();
            txtAzimut.setText("");
            return false;
        } else if (vc.esDistancia(distancia, "Sitio")) {
            txtDistancia.requestFocus();
            txtDistancia.setText("");
            return false;
        } else {
            return true;
        }
    }
    
    private CESitio crearSitioAccesible() {
        Integer upmID = (Integer) cmbUPM.getSelectedItem();
        Integer numeroSitio = (Integer) cmbSitio.getSelectedItem();
        Integer esAccesible = chkAccesible.isSelected() ? 1 : 0;

        Integer haySenial = chkSenial.isSelected() ? 1 : 0;
        this.ceSitio.setSitio(numeroSitio);
        this.ceSitio.setSitioAccesible(esAccesible);
        this.ceSitio.setSenialGPS(haySenial);
        this.ceSitio.setUpmID(upmID);
        this.ceSitio.setGradosLatitud(Integer.parseInt(txtGradosLatitud.getText()));
        this.ceSitio.setMinutosLatitud(Integer.parseInt(txtMinutosLatitud.getText()));
        this.ceSitio.setSegundosLatitud(Float.parseFloat(txtSegundosLatitud.getText()));
        this.ceSitio.setGradosLongitud(Integer.parseInt(txtGradosLongitud.getText()));
        this.ceSitio.setMinutosLongitud(Integer.parseInt(txtMinutosLongitud.getText()));
        this.ceSitio.setSegundosLongitud(Float.parseFloat(txtSegundosLongitud.getText()));
        this.ceSitio.setErrorPrecision(Integer.parseInt(txtEPE.getText()));
        this.ceSitio.setDatum(txtDatum.getText());
        if (rbtEvidenciaSi.isSelected()) {
            this.ceSitio.setEvidenciaMuestreo(1);
        } else if (rbtEvidenciaNo.isSelected()) {
            this.ceSitio.setEvidenciaMuestreo(0);
        }
        return ceSitio;
    }
    
    private CESitio crearSitioSenial(){
        int upmID = (Integer) cmbUPM.getSelectedItem();
        int numeroSitio = (Integer) cmbSitio.getSelectedItem();
        int esAccesible = chkAccesible.isSelected() ? 1 : 0;
        int haySenial = chkSenial.isSelected() ? 1 : 0;
        this.ceSitio.setSitio(numeroSitio);
        this.ceSitio.setSitioAccesible(esAccesible);
        this.ceSitio.setSenialGPS(haySenial);
        this.ceSitio.setAzimut(Integer.parseInt(txtAzimut.getText()));
        this.ceSitio.setDistancia(Float.parseFloat(txtDistancia.getText()));
        this.ceSitio.setUpmID(upmID);
        this.ceSitio.setGradosLatitud(Integer.parseInt(txtGradosLatitud.getText()));
        this.ceSitio.setMinutosLatitud(Integer.parseInt(txtMinutosLatitud.getText()));
        this.ceSitio.setSegundosLatitud(Float.parseFloat(txtSegundosLatitud.getText()));
        this.ceSitio.setGradosLongitud(Integer.parseInt(txtGradosLongitud.getText()));
        this.ceSitio.setMinutosLongitud(Integer.parseInt(txtMinutosLongitud.getText()));
        this.ceSitio.setSegundosLongitud(Float.parseFloat(txtSegundosLongitud.getText()));
        this.ceSitio.setErrorPrecision(Integer.parseInt(txtEPE.getText()));
        this.ceSitio.setDatum(txtDatum.getText());
        //this.ceSitio.setEvidenciaMuestreo(evidenciaMuestreo);
         if (rbtEvidenciaSi.isSelected()) {
            this.ceSitio.setEvidenciaMuestreo(1);
        } else if (rbtEvidenciaNo.isSelected()) {
            this.ceSitio.setEvidenciaMuestreo(0);
        } else {
            this.ceSitio.setEvidenciaMuestreo(null);
        }
        return ceSitio;
    }
    
    private CESitio crearSitioInaccesible(){
         int upmID = (Integer) cmbUPM.getSelectedItem();
         int numeroSitio = (Integer) cmbSitio.getSelectedItem();
         int esAccesible = chkAccesible.isSelected() ? 1 : 0;
         CatETipoInaccesibilidad inaccesibilidad = (CatETipoInaccesibilidad) cmbInaccesibilidad.getSelectedItem();
         this.ceSitio.setUpmID(upmID);
         this.ceSitio.setSitio(numeroSitio);
         this.ceSitio.setSitioAccesible(esAccesible);
         this.ceSitio.setTipoInaccesibilidadID(inaccesibilidad.getTipoInaccesibilidadID());
         this.ceSitio.setExplicacionInaccesibilidad(txtExplicacion.getText());
         
         return ceSitio;
    }
    
    private void fijarDatosSitio() {
        if (chkAccesible.isSelected()) {
            this.esAccesible = 1;
        } else {
            this.esAccesible = 0;
        }
        if (chkSenial.isSelected()) {
            this.senialGPS = 1;
        } else {
            this.senialGPS = 0;
        }
        try {
            this.azimut = Integer.parseInt(txtAzimut.getText());
        } catch (NumberFormatException e) {
            this.azimut = null;
        }
        try {
            this.distancia = Float.parseFloat(txtDistancia.getText());
        } catch (NumberFormatException e) {
            this.distancia = null;
        }
        try {
            this.gradosLatitud = Integer.parseInt(txtGradosLatitud.getText());
        } catch (NumberFormatException e) {
            this.gradosLatitud = null;
        }
        try {
            this.minutosLatitud = Integer.parseInt(txtMinutosLatitud.getText());
        } catch (NumberFormatException e) {
            this.minutosLatitud = null;
        }
        try {
            this.segundosLatitud = Float.parseFloat(txtSegundosLatitud.getText());
        } catch (NumberFormatException e) {
            this.segundosLatitud = null;
        }
        try {
            this.gradosLongitud = Integer.parseInt(txtGradosLongitud.getText());
        } catch (NumberFormatException e) {
            this.gradosLongitud = null;
        }
        try {
            this.minutosLongitud = Integer.parseInt(txtMinutosLongitud.getText());
        } catch (NumberFormatException e) {
            this.minutosLongitud = null;
        }
        try {
            this.segundosLongitud = Float.parseFloat(txtSegundosLongitud.getText());
        } catch (NumberFormatException e) {
            this.segundosLongitud = null;
        }
        try {
            this.errorPresicion = Integer.parseInt(txtEPE.getText());
        } catch (NumberFormatException e) {
            this.errorPresicion = null;
        }
        if (rbtEvidenciaSi.isSelected()) {
            this.evidenciaMuestreo = 1;
        } else if (rbtEvidenciaNo.isSelected()) {
            this.evidenciaMuestreo = 0;
        } else {
            this.evidenciaMuestreo = null;
        }
        CatETipoInaccesibilidad ceTipo;
        ceTipo = (CatETipoInaccesibilidad) cmbInaccesibilidad.getSelectedItem();
        try {
            this.tipoInaccesibilidad = (ceTipo.getTipoInaccesibilidadID());
        } catch (NullPointerException e) {
            this.tipoInaccesibilidad = null;
        }
        this.explicacionaInaccesibilidad = txtExplicacion.getText();
    }
    
    private void actualizarSitio(){
        fijarDatosSitio();
        Integer sitio = (Integer) cmbSitio.getSelectedItem();
        Integer upm = (Integer) cmbUPM.getSelectedItem();
        this.ceSitio.setUpmID(upm);
        this.ceSitio.setSitio(sitio);
        this.ceSitio.setSitioAccesible(this.esAccesible);
        this.ceSitio.setSenialGPS(this.senialGPS);
        this.ceSitio.setAzimut(this.azimut);
        this.ceSitio.setDistancia(this.distancia);
        this.ceSitio.setGradosLatitud(this.gradosLatitud);
        this.ceSitio.setMinutosLatitud(this.minutosLatitud);
        this.ceSitio.setSegundosLatitud(this.segundosLatitud);
        this.ceSitio.setGradosLongitud(this.gradosLongitud);
        this.ceSitio.setMinutosLongitud(this.minutosLongitud);
        this.ceSitio.setSegundosLongitud(this.segundosLongitud);
        this.ceSitio.setDatum(txtDatum.getText());
        this.ceSitio.setErrorPrecision(this.errorPresicion);
        this.ceSitio.setEvidenciaMuestreo(this.evidenciaMuestreo);
        this.ceSitio.setTipoInaccesibilidadID(this.tipoInaccesibilidad);
        this.ceSitio.setExplicacionInaccesibilidad(this.explicacionaInaccesibilidad);
        this.cdSitio.updateSitio(ceSitio);
    }

    private void reiniciarForma() {
        //cmbUPM.setSelectedItem(null);
       // cmbUPM.requestFocus();
        chkSenial.setSelected(true);
        chkAccesible.setSelected(true);
        txtAzimut.setText("");
        txtAzimut.setText(null);
        txtDistancia.setText("");
        txtDistancia.setValue(null);
        txtGradosLatitud.setText("");
        txtGradosLatitud.setValue(null);
        txtMinutosLatitud.setText("");
        txtMinutosLatitud.setValue(null);
        txtSegundosLatitud.setText("");
        txtSegundosLatitud.setValue(null);
        txtGradosLongitud.setText("");
        txtGradosLongitud.setValue(null);
        txtMinutosLongitud.setText("");
        txtMinutosLongitud.setValue(null);
        txtSegundosLongitud.setText("");
        txtSegundosLongitud.setValue(null);
        txtEPE.setText("");
        txtEPE.setValue(null);
        rbtEvidenciaSi.setSelected(false);
        rbtEvidenciaSi.setSelected(false);
        rbtEvidenciaNo.setSelected(false);
        rbtEvidenciaNo.setSelected(false);
        cmbInaccesibilidad.setSelectedItem(null);
        cmbInaccesibilidad.setEnabled(false);
        txtExplicacion.setText("");
        txtExplicacion.setEnabled(false);
        BgrEvidenciaMuestreo.clearSelection();
        cmbSitio.requestFocus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BgrEvidenciaMuestreo = new javax.swing.ButtonGroup();
        PnlSitio = new javax.swing.JPanel();
        PnlNumeroSitio = new javax.swing.JPanel();
        lblUPM = new javax.swing.JLabel();
        cmbUPM = new javax.swing.JComboBox();
        PnlUPM = new javax.swing.JPanel();
        lblSitio = new javax.swing.JLabel();
        cmbSitio = new javax.swing.JComboBox();
        chkAccesible = new javax.swing.JCheckBox();
        chkSenial = new javax.swing.JCheckBox();
        PnlCoordenadasSitio = new javax.swing.JPanel();
        PnlCoordenadasInterior = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        LblLatitud = new javax.swing.JLabel();
        LblLongitud = new javax.swing.JLabel();
        LblComplementaria = new javax.swing.JLabel();
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
        LblDatum = new javax.swing.JLabel();
        LblEPE = new javax.swing.JLabel();
        txtDatum = new javax.swing.JTextField();
        LblEvidencia = new javax.swing.JLabel();
        rbtEvidenciaSi = new javax.swing.JRadioButton();
        rbtEvidenciaNo = new javax.swing.JRadioButton();
        txtEPE = new javax.swing.JFormattedTextField();
        lblAzimut = new javax.swing.JLabel();
        lblDistancia = new javax.swing.JLabel();
        PnlCaracteristicas = new javax.swing.JPanel();
        PnlInaccesibilidad = new javax.swing.JPanel();
        lblInaccesibilidad = new javax.swing.JLabel();
        cmbInaccesibilidad = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtExplicacion = new javax.swing.JTextArea();
        btnGuardar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        txtAzimut = new javax.swing.JFormattedTextField();
        txtDistancia = new javax.swing.JFormattedTextField();

        setBorder(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setMaximizable(true);
        setTitle("Información del sitio de muestreo "+version);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/gob/conafor/utils/logo_conafor.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(940, 650));

        PnlSitio.setBackground(new java.awt.Color(204, 204, 204));

        PnlNumeroSitio.setBackground(new java.awt.Color(204, 204, 204));
        PnlNumeroSitio.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblUPM.setText("UPMID:");

        cmbUPM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbUPMActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PnlNumeroSitioLayout = new javax.swing.GroupLayout(PnlNumeroSitio);
        PnlNumeroSitio.setLayout(PnlNumeroSitioLayout);
        PnlNumeroSitioLayout.setHorizontalGroup(
            PnlNumeroSitioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlNumeroSitioLayout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(lblUPM)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbUPM, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PnlNumeroSitioLayout.setVerticalGroup(
            PnlNumeroSitioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PnlNumeroSitioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnlNumeroSitioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUPM)
                    .addComponent(cmbUPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        PnlUPM.setBackground(new java.awt.Color(204, 204, 204));
        PnlUPM.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblSitio.setText("Sitio:");

        cmbSitio.setEnabled(false);
        cmbSitio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSitioActionPerformed(evt);
            }
        });

        chkAccesible.setBackground(new java.awt.Color(204, 204, 204));
        chkAccesible.setSelected(true);
        chkAccesible.setText("¿Es accesible?");
        chkAccesible.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        chkAccesible.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAccesibleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PnlUPMLayout = new javax.swing.GroupLayout(PnlUPM);
        PnlUPM.setLayout(PnlUPMLayout);
        PnlUPMLayout.setHorizontalGroup(
            PnlUPMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnlUPMLayout.createSequentialGroup()
                .addContainerGap(74, Short.MAX_VALUE)
                .addComponent(lblSitio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbSitio, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(chkAccesible)
                .addGap(53, 53, 53))
        );
        PnlUPMLayout.setVerticalGroup(
            PnlUPMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnlUPMLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PnlUPMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSitio)
                    .addComponent(cmbSitio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkAccesible))
                .addContainerGap())
        );

        chkSenial.setBackground(new java.awt.Color(204, 204, 204));
        chkSenial.setText("¿Se obtuvo señal GPS del sitio?");
        chkSenial.setEnabled(false);
        chkSenial.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        chkSenial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSenialActionPerformed(evt);
            }
        });

        PnlCoordenadasSitio.setBackground(new java.awt.Color(204, 204, 204));
        PnlCoordenadasSitio.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        PnlCoordenadasSitio.setNextFocusableComponent(btnSalir);

        PnlCoordenadasInterior.setBackground(new java.awt.Color(204, 204, 204));
        PnlCoordenadasInterior.setBorder(javax.swing.BorderFactory.createTitledBorder("Coordenadas GPS del Sitio"));
        PnlCoordenadasInterior.setFocusTraversalPolicyProvider(true);
        PnlCoordenadasInterior.setNextFocusableComponent(btnGuardar);

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

        LblComplementaria.setBackground(new java.awt.Color(0, 0, 0));
        LblComplementaria.setForeground(new java.awt.Color(255, 255, 255));
        LblComplementaria.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblComplementaria.setText("Información complementaria");
        LblComplementaria.setOpaque(true);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(LblLatitud, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(LblLongitud, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LblComplementaria, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(LblLatitud)
                .addComponent(LblLongitud)
                .addComponent(LblComplementaria))
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
        txtGradosLatitud.setEnabled(false);
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
        txtMinutosLatitud.setEnabled(false);
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
        txtSegundosLatitud.setEnabled(false);
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
        txtGradosLongitud.setEnabled(false);
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
        txtMinutosLongitud.setEnabled(false);
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
        txtSegundosLongitud.setEnabled(false);
        txtSegundosLongitud.setFocusCycleRoot(true);
        txtSegundosLongitud.setNextFocusableComponent(txtEPE);
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
        jPanel11.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel11.setFocusCycleRoot(true);
        jPanel11.setFocusTraversalPolicyProvider(true);
        jPanel11.setNextFocusableComponent(btnGuardar);

        LblDatum.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblDatum.setText("Datum");
        LblDatum.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        LblEPE.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblEPE.setText("EPE");
        LblEPE.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        LblEPE.setPreferredSize(new java.awt.Dimension(36, 16));

        txtDatum.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDatum.setText("WGS_84");
        txtDatum.setEnabled(false);

        LblEvidencia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblEvidencia.setText("¿Se trazo evidencia de muestreo?");
        LblEvidencia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        LblEvidencia.setPreferredSize(new java.awt.Dimension(36, 16));

        rbtEvidenciaSi.setBackground(new java.awt.Color(204, 204, 204));
        BgrEvidenciaMuestreo.add(rbtEvidenciaSi);
        rbtEvidenciaSi.setText("Si");
        rbtEvidenciaSi.setEnabled(false);
        rbtEvidenciaSi.setNextFocusableComponent(btnGuardar);

        rbtEvidenciaNo.setBackground(new java.awt.Color(204, 204, 204));
        BgrEvidenciaMuestreo.add(rbtEvidenciaNo);
        rbtEvidenciaNo.setText("No");
        rbtEvidenciaNo.setEnabled(false);
        rbtEvidenciaNo.setNextFocusableComponent(btnGuardar);

        txtEPE.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtEPE.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEPE.setEnabled(false);
        txtEPE.setFocusCycleRoot(true);
        txtEPE.setNextFocusableComponent(rbtEvidenciaSi);
        txtEPE.setPreferredSize(new java.awt.Dimension(70, 20));
        txtEPE.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtEPEFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtEPEFocusLost(evt);
            }
        });
        txtEPE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEPEKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(LblDatum, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(LblEPE, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(LblEvidencia, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(txtDatum, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txtEPE, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(rbtEvidenciaSi)
                .addGap(7, 7, 7)
                .addComponent(rbtEvidenciaNo))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LblDatum)
                    .addComponent(LblEPE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblEvidencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDatum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEPE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbtEvidenciaSi)
                    .addComponent(rbtEvidenciaNo)))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
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
                    .addComponent(txtSegundosLongitud, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LblGradosLongitud)
                    .addComponent(LblMinutosLongitud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblSegundosLongitud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtGradosLongitud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtMinutosLongitud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtSegundosLongitud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout PnlCoordenadasInteriorLayout = new javax.swing.GroupLayout(PnlCoordenadasInterior);
        PnlCoordenadasInterior.setLayout(PnlCoordenadasInteriorLayout);
        PnlCoordenadasInteriorLayout.setHorizontalGroup(
            PnlCoordenadasInteriorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlCoordenadasInteriorLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PnlCoordenadasInteriorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(PnlCoordenadasInteriorLayout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 28, Short.MAX_VALUE))))
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
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout PnlCoordenadasSitioLayout = new javax.swing.GroupLayout(PnlCoordenadasSitio);
        PnlCoordenadasSitio.setLayout(PnlCoordenadasSitioLayout);
        PnlCoordenadasSitioLayout.setHorizontalGroup(
            PnlCoordenadasSitioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlCoordenadasSitioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PnlCoordenadasInterior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PnlCoordenadasSitioLayout.setVerticalGroup(
            PnlCoordenadasSitioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlCoordenadasSitioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PnlCoordenadasInterior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        lblAzimut.setText("Azimut:");

        lblDistancia.setText("Distancia:");

        PnlCaracteristicas.setBackground(new java.awt.Color(204, 204, 204));
        PnlCaracteristicas.setBorder(javax.swing.BorderFactory.createTitledBorder("Características de inaccesibilidad del sitio"));

        PnlInaccesibilidad.setBackground(new java.awt.Color(204, 204, 204));
        PnlInaccesibilidad.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblInaccesibilidad.setText("Inaccesibilidad:");

        cmbInaccesibilidad.setEnabled(false);

        javax.swing.GroupLayout PnlInaccesibilidadLayout = new javax.swing.GroupLayout(PnlInaccesibilidad);
        PnlInaccesibilidad.setLayout(PnlInaccesibilidadLayout);
        PnlInaccesibilidadLayout.setHorizontalGroup(
            PnlInaccesibilidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlInaccesibilidadLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblInaccesibilidad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbInaccesibilidad, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PnlInaccesibilidadLayout.setVerticalGroup(
            PnlInaccesibilidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnlInaccesibilidadLayout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addGroup(PnlInaccesibilidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblInaccesibilidad)
                    .addComponent(cmbInaccesibilidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jLabel6.setText("Explicación del motivo y/o causa de inaccesibilidad del sitio.");

        txtExplicacion.setColumns(20);
        txtExplicacion.setRows(5);
        txtExplicacion.setEnabled(false);
        txtExplicacion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtExplicacionFocusGained(evt);
            }
        });
        txtExplicacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtExplicacionKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(txtExplicacion);

        javax.swing.GroupLayout PnlCaracteristicasLayout = new javax.swing.GroupLayout(PnlCaracteristicas);
        PnlCaracteristicas.setLayout(PnlCaracteristicasLayout);
        PnlCaracteristicasLayout.setHorizontalGroup(
            PnlCaracteristicasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlCaracteristicasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnlCaracteristicasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PnlInaccesibilidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        PnlCaracteristicasLayout.setVerticalGroup(
            PnlCaracteristicasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlCaracteristicasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PnlInaccesibilidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        txtAzimut.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtAzimut.setEnabled(false);
        txtAzimut.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtAzimutFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtAzimutFocusLost(evt);
            }
        });
        txtAzimut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAzimutKeyTyped(evt);
            }
        });

        txtDistancia.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtDistancia.setEnabled(false);
        txtDistancia.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDistanciaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDistanciaFocusLost(evt);
            }
        });
        txtDistancia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDistanciaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout PnlSitioLayout = new javax.swing.GroupLayout(PnlSitio);
        PnlSitio.setLayout(PnlSitioLayout);
        PnlSitioLayout.setHorizontalGroup(
            PnlSitioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlSitioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnlSitioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnlSitioLayout.createSequentialGroup()
                        .addComponent(chkSenial)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblAzimut)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAzimut, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(lblDistancia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDistancia, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(104, 104, 104))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnlSitioLayout.createSequentialGroup()
                        .addGroup(PnlSitioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(PnlCaracteristicas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(PnlCoordenadasSitio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(PnlSitioLayout.createSequentialGroup()
                                .addComponent(PnlNumeroSitio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(PnlUPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
            .addGroup(PnlSitioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardar)
                .addGap(18, 18, 18)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        PnlSitioLayout.setVerticalGroup(
            PnlSitioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlSitioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnlSitioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PnlUPM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PnlNumeroSitio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PnlSitioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkSenial)
                    .addComponent(lblAzimut)
                    .addComponent(lblDistancia)
                    .addComponent(txtAzimut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDistancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PnlCoordenadasSitio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PnlCaracteristicas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PnlSitioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnSalir))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PnlSitio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PnlSitio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getAccessibleContext().setAccessibleName("Ubicación del sitio de muestreo");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        ComboBoxModel listSitios = cmbSitio.getModel();
        if (this.actualizacion == 0) {
            if (cmbSitio.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null,
                        "Error! debe seleccionar un número de sitio ",
                        "Datos de ubicación del Sitio", JOptionPane.INFORMATION_MESSAGE);
                cmbSitio.requestFocus();
            } else if (chkAccesible.isSelected() && chkSenial.isSelected()) {//ES ACCESIBLE Y SE TUVO SEÑAL GPS
                if (validarCamposObligatoriosSitioAccesible() && validarDatosCoordenadas()) {
                    cdSitio.insertSitioAccesible(crearSitioAccesible());
                    combo.reiniciarComboModel(cmbSitio);
                    Integer upmID = (Integer) cmbUPM.getSelectedItem();
                    if (upmID != null) {
                        fillSitios(upmID);
                    }
                    JOptionPane.showMessageDialog(null, "Ha creado un nuevo Sitio", "Datos de ubicación del sitio", JOptionPane.INFORMATION_MESSAGE);
                    reiniciarForma();
                    cmbSitio.setEnabled(true);
                    cmbSitio.setSelectedItem(null);
                    if(listSitios.getSize() == 1){//Ocultar cuando sea el utlimo sitio
                        this.hide();
                        habilitarControlesIniciales(false);
                        combo.manipularBotonesMenuPrincipal(false);
                        combo.reiniciarComboModel(cmbUPM);
                    }
                    habilitarControlesIniciales(false);
                }
            } else if (chkAccesible.isSelected() && !chkSenial.isSelected()) {//ES ACCESIBLE Y NO SE TUVO SEÑAL GPS
                if ((validarCamposObligatoriosSitioAccesible() && validarDatosCoordenadas()) && (validarCamposAuxiliaresObligatorios() && validarAzimutDistancia())) {
                    cdSitio.insertSitioAccesibleSenial(crearSitioSenial());
                    combo.reiniciarComboModel(cmbSitio);
                    Integer upmID = (Integer) cmbUPM.getSelectedItem();
                    if (upmID != null) {
                        fillSitios(upmID);
                    }
                    JOptionPane.showMessageDialog(null, "Ha creado un nuevo Sitio", "Datos de ubicación del sitio", JOptionPane.INFORMATION_MESSAGE);
                    reiniciarForma();
                    combo.reiniciarComboModel(cmbSitio);
                    fillSitios(upmID);
                    cmbSitio.setSelectedItem(null);
                    habilitarControlesIniciales(false);
                    if(listSitios.getSize() == 1){//Ocultar cuando sea el ultimo sitio
                        this.hide();
                        habilitarControlesIniciales(false);
                        combo.manipularBotonesMenuPrincipal(false);
                        combo.reiniciarComboModel(cmbUPM);
                    }
                }
            } else if (!chkAccesible.isSelected()) {//NO ES ACCESIBLE
                if (validarCamposObligatoriosSitioInaccesible()) {
                    cdSitio.insertSitioInaccesible(crearSitioInaccesible());
                    JOptionPane.showMessageDialog(null, "Ha creado un nuevo Sitio inaccesible", "Captura de la UPM", JOptionPane.INFORMATION_MESSAGE);
                    reiniciarForma();
                    Integer upmID = (Integer) cmbUPM.getSelectedItem();
                    combo.reiniciarComboModel(cmbSitio);
                    fillSitios(upmID);
                    cmbSitio.setSelectedItem(null);
                    habilitarControlesIniciales(false);
                    if(listSitios.getSize() == 1){//Ocultar cuando sea el ultimo sitio
                        this.hide();
                        habilitarControlesIniciales(false);
                        combo.manipularBotonesMenuPrincipal(false);
                        combo.reiniciarComboModel(cmbUPM);
                    }
                }
            }
        } else if (cmbSitio.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null,
                    "Error! debe seleccionar un número de sitio ",
                    "Datos de ubicación del Sitio", JOptionPane.ERROR_MESSAGE);
            cmbSitio.requestFocus();
        } else if (chkAccesible.isSelected() && chkSenial.isSelected()) {
            if (validarCamposObligatoriosSitioAccesible() && validarDatosCoordenadas()) {
                actualizarSitio();
                JOptionPane.showMessageDialog(null, "Ha modificado un Sitio", "Datos de ubicación del sitio", JOptionPane.INFORMATION_MESSAGE);
                reiniciarForma();
                habilitarControlesIniciales(false);
                cmbUPM.requestFocus();
            }
        } else if (chkAccesible.isSelected() && !chkSenial.isSelected()) {
            if ((validarCamposObligatoriosSitioAccesible() && validarDatosCoordenadas()) && (validarCamposAuxiliaresObligatorios() && validarAzimutDistancia())) {
                actualizarSitio();
                JOptionPane.showMessageDialog(null, "Ha modificado un Sitio", "Datos de ubicación del sitio", JOptionPane.INFORMATION_MESSAGE);
                reiniciarForma();
                habilitarControlesIniciales(false);
                cmbUPM.requestFocus();
            }
        } else if (!chkAccesible.isSelected()) {
            if (validarCamposObligatoriosSitioInaccesible()) {
                actualizarSitio();
                JOptionPane.showMessageDialog(null, "Ha modificado un Sitio como inaccesible", "Captura de la UPM", JOptionPane.INFORMATION_MESSAGE);
                reiniciarForma();
                habilitarControlesIniciales(false);
                cmbUPM.requestFocus();
            }
        }
      /* ComboBoxModel cmb = cmbSitio.getModel();
       int size = cmb.getSize();
       if(size == 0){
           int respuesta = JOptionPane.showConfirmDialog(null, "Capturo los 4 sitios¿Desea salir de este formato?",
                    "Cobertura dosel", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (respuesta == JOptionPane.YES_OPTION) {
                combo.manipularBotonesMenuPrincipal(false);
                this.hide();
            }
       }*/
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        combo.reiniciarComboModel(this.cmbUPM);
        combo.manipularBotonesMenuPrincipal(false);
        this.hide();
        
    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtGradosLatitudFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGradosLatitudFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtGradosLatitud.selectAll();
            }
        });
    }//GEN-LAST:event_txtGradosLatitudFocusGained

    private void txtMinutosLatitudFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMinutosLatitudFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtMinutosLatitud.selectAll();
            }
        });
    }//GEN-LAST:event_txtMinutosLatitudFocusGained

    private void txtSegundosLatitudFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSegundosLatitudFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtSegundosLatitud.selectAll();
            }
        });
    }//GEN-LAST:event_txtSegundosLatitudFocusGained

    private void txtGradosLongitudFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGradosLongitudFocusGained
       SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtGradosLongitud.selectAll();
            }
        });
    }//GEN-LAST:event_txtGradosLongitudFocusGained

    private void txtMinutosLongitudFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMinutosLongitudFocusGained
       SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtMinutosLongitud.selectAll();
            }
        });
    }//GEN-LAST:event_txtMinutosLongitudFocusGained

    private void txtSegundosLongitudFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSegundosLongitudFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtSegundosLongitud.selectAll();
            }
        });
    }//GEN-LAST:event_txtSegundosLongitudFocusGained

    private void txtEPEFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEPEFocusGained
         SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtEPE.selectAll();
            }
        });
    }//GEN-LAST:event_txtEPEFocusGained

    private void txtExplicacionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtExplicacionFocusGained
        txtExplicacion.selectAll();
    }//GEN-LAST:event_txtExplicacionFocusGained

    private void txtExplicacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtExplicacionKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB){
            evt.consume();
        }
    }//GEN-LAST:event_txtExplicacionKeyPressed

    private void txtGradosLatitudKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGradosLatitudKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtGradosLatitudKeyTyped

    private void txtMinutosLatitudKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMinutosLatitudKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtMinutosLatitudKeyTyped

    private void txtSegundosLatitudKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSegundosLatitudKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtSegundosLatitudKeyTyped

    private void txtGradosLongitudKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGradosLongitudKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtGradosLongitudKeyTyped

    private void txtMinutosLongitudKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMinutosLongitudKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtMinutosLongitudKeyTyped

    private void txtSegundosLongitudKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSegundosLongitudKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtSegundosLongitudKeyTyped

    private void txtEPEKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEPEKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtEPEKeyTyped

    private void txtAzimutFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAzimutFocusGained
         SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtAzimut.selectAll();
            }
        });
    }//GEN-LAST:event_txtAzimutFocusGained

    private void txtDistanciaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDistanciaFocusGained
         SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtDistancia.selectAll();
            }
        });
    }//GEN-LAST:event_txtDistanciaFocusGained

    private void txtAzimutFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAzimutFocusLost
         if (txtAzimut.getText().isEmpty()) {
            txtAzimut.setValue(null);
        }
    }//GEN-LAST:event_txtAzimutFocusLost

    private void txtDistanciaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDistanciaFocusLost
         if (txtDistancia.getText().isEmpty()) {
            txtDistancia.setValue(null);
        }
    }//GEN-LAST:event_txtDistanciaFocusLost

    private void txtGradosLatitudFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGradosLatitudFocusLost
        if (txtGradosLatitud.getText().isEmpty()) {
            txtGradosLatitud.setValue(null);
        }
    }//GEN-LAST:event_txtGradosLatitudFocusLost

    private void txtMinutosLatitudFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMinutosLatitudFocusLost
        if (txtMinutosLatitud.getText().isEmpty()) {
            txtMinutosLatitud.setValue(null);
        }
    }//GEN-LAST:event_txtMinutosLatitudFocusLost

    private void txtSegundosLatitudFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSegundosLatitudFocusLost
        if (txtSegundosLatitud.getText().isEmpty()) {
            txtSegundosLatitud.setValue(null);
        }
    }//GEN-LAST:event_txtSegundosLatitudFocusLost

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

    private void txtMinutosLongitudFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMinutosLongitudFocusLost
        if (txtMinutosLongitud.getText().isEmpty()) {
            txtMinutosLongitud.setValue(null);
        }
    }//GEN-LAST:event_txtMinutosLongitudFocusLost

    private void txtSegundosLongitudFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSegundosLongitudFocusLost
       if (txtSegundosLongitud.getText().isEmpty()) {
            txtSegundosLongitud.setValue(null);
        }
    }//GEN-LAST:event_txtSegundosLongitudFocusLost

    private void txtEPEFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEPEFocusLost
       if (txtEPE.getText().isEmpty()) {
            txtEPE.setValue(null);
        }
    }//GEN-LAST:event_txtEPEFocusLost

    private void txtAzimutKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAzimutKeyTyped
         numeros.keyTyped(evt);
    }//GEN-LAST:event_txtAzimutKeyTyped

    private void txtDistanciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDistanciaKeyTyped
         numeros.keyTyped(evt);
    }//GEN-LAST:event_txtDistanciaKeyTyped

    private void cmbUPMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbUPMActionPerformed
        Integer upmID = (Integer) cmbUPM.getSelectedItem();
        if (cmbUPM.getSelectedItem() == null) {
            cmbSitio.setEnabled(false);
            combo.reiniciarComboModel(this.cmbSitio);
        } else if (cdSitio.son4Sitios(upmID)) {
            JOptionPane.showMessageDialog(null, "ya se han capturado los 4 sitios para ese UPM, seleccione otro", "Datos de ubicación del sitio", JOptionPane.INFORMATION_MESSAGE);
            cmbSitio.setEnabled(false);
            cmbSitio.setSelectedItem(null);
            cmbUPM.setSelectedItem(null);
            cmbUPM.requestFocus();
            btnGuardar.setEnabled(false);
        } else {
            cmbSitio.setEnabled(true);
            btnGuardar.setEnabled(true);
            combo.reiniciarComboModel(this.cmbSitio);
            fillSitios(upmID);
            cmbSitio.setSelectedItem(null);
            if (this.actualizacion == 0) {
                if (this.cdSitio.validarSitio1(upmID)) {
                    combo.reiniciarComboModel(cmbSitio);
                    fillSitios(upmID);
                    cmbSitio.setEnabled(true);
                    cmbSitio.setSelectedItem(null);
                    chkAccesible.setSelected(true);
                } else {
                    Integer sitio1 = 1;
                    cmbSitio.setEnabled(false);
                    combo.reiniciarComboModel(cmbSitio);
                    fillSitios(upmID);
                    cmbSitio.setSelectedItem(sitio1);
                    // habilitarControlesIniciales(true);
                    chkAccesible.setEnabled(false);
                    txtGradosLatitud.requestFocus();
                }
            } else {
                fillSitiosRevision();
            }
        }
    }//GEN-LAST:event_cmbUPMActionPerformed

    private void chkAccesibleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAccesibleActionPerformed
        this.accesible = esAccesible();
        if (this.actualizacion == 1) {
            Integer upmid = (Integer) cmbUPM.getSelectedItem();
            Integer sitio = (Integer) cmbUPM.getSelectedItem();
            if (chkAccesible.isSelected()) {
                int respuesta = JOptionPane.showConfirmDialog(null, "¿Esta seguro de borrar el registro de sitio y todos los registros relacionados?",
                        "Sitio", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (respuesta == JOptionPane.YES_OPTION) {
                    this.cdSitio.deleteSitio(upmid, sitio);
                }
            }
        }
    }//GEN-LAST:event_chkAccesibleActionPerformed

    private void chkSenialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSenialActionPerformed
        this.senial = haySenial();
        if (chkSenial.isSelected() == false) {
            txtAzimut.requestFocus();
        }
        if (chkSenial.isSelected()) {
            cmbInaccesibilidad.requestFocus();
        }
    }//GEN-LAST:event_chkSenialActionPerformed

    private void cmbSitioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSitioActionPerformed
        
        if (this.actualizacion == 0) {
            if (cmbSitio.getSelectedItem() != null) {
                
                habilitarControlesIniciales(true);
            } else {
               reiniciarForma();
            }
        } else if (this.actualizacion == 1) {
            Integer upm = (Integer) cmbUPM.getSelectedItem();
            Integer sitio = (Integer) cmbSitio.getSelectedItem();
            Integer sitioID = cdSitio.getSitioID(upm, sitio);
            this.ceSitio = cdSitio.getSitioRevision(sitioID);
            llenarControlesSitio(ceSitio);
            int accesible = this.ceSitio.getSitioAccesible();
            int senial = this.ceSitio.getSenialGPS();
            if (accesible == 1 && senial == 1) {
                habilitarControlesPorSitio(1);
            } else if (accesible == 0) {
                habilitarControlesPorSitio(3);
            } else if (accesible == 1 && senial == 0) {
                habilitarControlesPorSitio(2);
            }
        }
        Integer sitio = (Integer) cmbSitio.getSelectedItem();
        if (sitio != null && sitio == 1) {
            chkAccesible.setEnabled(false);
            chkAccesible.setSelected(true);
        }
    }//GEN-LAST:event_cmbSitioActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup BgrEvidenciaMuestreo;
    private javax.swing.JLabel LblComplementaria;
    private javax.swing.JLabel LblDatum;
    private javax.swing.JLabel LblEPE;
    private javax.swing.JLabel LblEvidencia;
    private javax.swing.JLabel LblGradosLatitud;
    private javax.swing.JLabel LblGradosLongitud;
    private javax.swing.JLabel LblLatitud;
    private javax.swing.JLabel LblLongitud;
    private javax.swing.JLabel LblMinutosLatitud;
    private javax.swing.JLabel LblMinutosLongitud;
    private javax.swing.JLabel LblSegundosLatitud;
    private javax.swing.JLabel LblSegundosLongitud;
    private javax.swing.JPanel PnlCaracteristicas;
    private javax.swing.JPanel PnlCoordenadasInterior;
    private javax.swing.JPanel PnlCoordenadasSitio;
    private javax.swing.JPanel PnlInaccesibilidad;
    private javax.swing.JPanel PnlNumeroSitio;
    private javax.swing.JPanel PnlSitio;
    private javax.swing.JPanel PnlUPM;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JCheckBox chkAccesible;
    private javax.swing.JCheckBox chkSenial;
    private javax.swing.JComboBox cmbInaccesibilidad;
    private javax.swing.JComboBox cmbSitio;
    private javax.swing.JComboBox cmbUPM;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAzimut;
    private javax.swing.JLabel lblDistancia;
    private javax.swing.JLabel lblInaccesibilidad;
    private javax.swing.JLabel lblSitio;
    private javax.swing.JLabel lblUPM;
    private javax.swing.JRadioButton rbtEvidenciaNo;
    private javax.swing.JRadioButton rbtEvidenciaSi;
    private javax.swing.JFormattedTextField txtAzimut;
    private javax.swing.JTextField txtDatum;
    private javax.swing.JFormattedTextField txtDistancia;
    private javax.swing.JFormattedTextField txtEPE;
    private javax.swing.JTextArea txtExplicacion;
    private javax.swing.JFormattedTextField txtGradosLatitud;
    private javax.swing.JFormattedTextField txtGradosLongitud;
    private javax.swing.JFormattedTextField txtMinutosLatitud;
    private javax.swing.JFormattedTextField txtMinutosLongitud;
    private javax.swing.JFormattedTextField txtSegundosLatitud;
    private javax.swing.JFormattedTextField txtSegundosLongitud;
    // End of variables declaration//GEN-END:variables
}
