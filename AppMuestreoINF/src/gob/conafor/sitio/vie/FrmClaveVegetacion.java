package gob.conafor.sitio.vie;

import gob.conafor.sitio.mod.CDSitio;
import gob.conafor.sitio.mod.CESitio;
import gob.conafor.sitio.mod.CatEClaveSerieV;
import gob.conafor.sitio.mod.CatEFaseSucecional;
import gob.conafor.sys.mod.CDSecuencia;
import gob.conafor.sys.mod.CDSeguimientoUPM;
import gob.conafor.sys.mod.CESeccionesCapturadas;
import gob.conafor.upm.vie.UPMForms;
import gob.conafor.utils.FuncionesComunes;
import gob.conafor.utils.Version;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

public class FrmClaveVegetacion extends JInternalFrame {
    
    private final int claveVegetacion;
    private final int caracteristicasUPM;
    private CDSitio cdSitio = new CDSitio();
    private CESitio ceSitio = new CESitio();
    private static final int FORMATO_ID = 5;
    private int upmID;
    private int sitioID;
    private int sitio;
    private Integer claveSerieV;
    private Integer faseSucecional;
    private int condicion;
    private int arbolFuera;
    private int ecotono;
    private String condicionPresente;
    private String condicionEcotono;
    private CESeccionesCapturadas seccionActual = new CESeccionesCapturadas();
    private CESeccionesCapturadas seccionSiguiente = new CESeccionesCapturadas();
    private CDSeguimientoUPM seguimiento = new CDSeguimientoUPM();
    private CDSecuencia cdSecuencia = new CDSecuencia();
    private int modificar;
    private FuncionesComunes funciones = new FuncionesComunes();
    private Version ver=new Version();
    private String version=ver.getVersion();
    private boolean revision;
    
    public FrmClaveVegetacion() {
        initComponents();
        fillCmbClaveSerieV();
        fillCmbFaseSucecional();
        this.claveVegetacion = 12;
        this.caracteristicasUPM = 13;
    }
    
    public void setDatosIniciales(CESitio sitio){
        this.upmID = sitio.getUpmID();
        this.sitioID = sitio.getSitioID();
        this.sitio = sitio.getSitio();
        txtUPM.setText(String.valueOf(this.upmID));
        txtSitio.setText(String.valueOf(this.sitio));
        this.ceSitio = sitio;
        this.modificar = 0;
        funciones.manipularBotonesMenuPrincipal(true);
        limpiarControles();
    }
    
    public void revisarClaveVegetacion(CESitio sitio) {
        revision=true;
        limpiarControles();
        this.upmID = sitio.getUpmID();
        this.sitioID = sitio.getSitioID();
        this.sitio = sitio.getSitio();
        txtUPM.setText(String.valueOf(this.upmID));
        txtSitio.setText(String.valueOf(this.sitio));
        this.ceSitio = sitio;

        CESitio ceVegetacion = cdSitio.getClaveVegetacion(this.sitioID);
        CatEClaveSerieV claveVegetacion = new CatEClaveSerieV();
        claveVegetacion.setClaveSerievID(ceVegetacion.getClaveSerieVID());
        
        cmbClaveSerieV.setSelectedItem(claveVegetacion);
        rbtPrimario.setEnabled(true);
        rbtSecundario.setEnabled(true);
        chkArbolFueraBosque.setEnabled(true);
        chkEcotono.setEnabled(true);
        //System.out.println(ceVegetacion.getCondicion());
        if (ceVegetacion.getCondicion() == 1) {         //1=Primario
            rbtPrimario.setSelected(true);
            rbtSecundario.setSelected(false);
            cmbFaseSucecional.setEnabled(false);
        } else {                                        //2=Secundario
            rbtPrimario.setSelected(false);
            rbtSecundario.setSelected(true);
            CatEFaseSucecional ceFase = new CatEFaseSucecional();
            ceFase.setFaseSucecionalID(ceVegetacion.getFaseSucecionalID());
            cmbFaseSucecional.setEnabled(true);
            cmbFaseSucecional.setSelectedItem(ceFase);
        }
        if (ceVegetacion.getArbolFuera() == 1) {
            chkArbolFueraBosque.setSelected(true);
        } else {
            chkArbolFueraBosque.setSelected(false);
        }
        if (ceVegetacion.getEcotono() == 1) {
            chkEcotono.setSelected(true);
            txtDescripcionEcotono.setEnabled(true);
            
            txtDescripcionEcotono.setText(ceVegetacion.getCondicionEcotono());
        } else {
            chkEcotono.setSelected(false);
        }
        txtCondicionPresente.setText(ceVegetacion.getCondicionPresenteCampo());
        this.modificar = 1;
        validarComboBoxClaveVegetacion();
        
        
        funciones.manipularBotonesMenuPrincipal(true);
    }
    
    private void limpiarControles(){
        cmbClaveSerieV.setSelectedItem(null);
        grbTipoVegetacion.clearSelection();
        rbtPrimario.setEnabled(false);
        rbtSecundario.setEnabled(false);
        cmbFaseSucecional.setSelectedItem(null);
        cmbFaseSucecional.setEnabled(false);
        chkArbolFueraBosque.setSelected(false);
        chkArbolFueraBosque.setEnabled(false);
        chkEcotono.setSelected(false);
        chkEcotono.setSelected(false);
        txtCondicionPresente.setText("");
        txtDescripcionEcotono.setText("");
        txtDescripcionEcotono.setEnabled(false);
    }
    
    private void fillCmbClaveSerieV(){
        List<CatEClaveSerieV> listClaveV = new ArrayList<>();
        listClaveV = cdSitio.getClaveVegetacion();
        if(listClaveV != null){
            int size = listClaveV.size();
            for(int i = 0; i < size; i++){
                cmbClaveSerieV.addItem(listClaveV.get(i));
            }
        }
    }
    
    private void fillCmbFaseSucecional(){
        List<CatEFaseSucecional> listFase = new ArrayList<>();
        listFase = cdSitio.getClaveSucecional();
        if(listFase != null){
            int size = listFase.size();
            for(int i = 0; i < size; i++){
                cmbFaseSucecional.addItem(listFase.get(i));
            }                   
        }
    }
    
    private void asignarDatosClaveVegetacion() {
        CatEClaveSerieV claveSerieV = (CatEClaveSerieV) cmbClaveSerieV.getSelectedItem();
        CatEFaseSucecional faseSucecional = (CatEFaseSucecional) cmbFaseSucecional.getSelectedItem();
        try {
            this.claveSerieV = claveSerieV.getClaveSerievID();
        } catch (NullPointerException e) {
            this.claveSerieV = null;
        }
        try {
            this.faseSucecional = faseSucecional.getFaseSucecionalID();
        } catch (NullPointerException e) {
            this.faseSucecional = null;
        }
        if (rbtPrimario.isSelected()==true) {
            this.condicion = 1;
        } else {
            this.condicion = 0;
        }
        //System.out.println("Condicion Continuar"+this.condicion);
        if (chkArbolFueraBosque.isSelected()) {
            this.arbolFuera = 1;
        } else {
            this.arbolFuera = 0;
        }
        if (chkEcotono.isSelected()) {
            this.ecotono = 1;
        } else {
            this.ecotono = 0;
        }
        this.condicionPresente = txtCondicionPresente.getText();
        this.condicionEcotono = txtDescripcionEcotono.getText();
    }
    
    private void crearClaveVegetacion() {
        this.ceSitio.setSitioID(this.sitioID);
        this.ceSitio.setSitio(this.sitio);
        if(lblCoberturaVegetal.getText().equals("Forestal")){
            this.ceSitio.setCoberturaForestal(1);
            System.out.println("Forestal");
        }
        if(lblCoberturaVegetal.getText().equals("No Forestal")){
            this.ceSitio.setCoberturaForestal(0);
            System.out.println("No Forestal");
        }
        this.ceSitio.setCondicion(this.condicion);
        this.ceSitio.setClaveSerieVID(this.claveSerieV);
        this.ceSitio.setFaseSucecionalID(this.faseSucecional);
        this.ceSitio.setArbolFuera(this.arbolFuera);
        this.ceSitio.setEcotono(this.ecotono);
        this.ceSitio.setCondicionPresenteCampo(this.condicionPresente);
        this.ceSitio.setCondicionEcotono(this.condicionEcotono);

        this.cdSitio.updateClaveVegetacion(ceSitio);
    }
    
    
    private boolean validarClaveVegetacion() {
        if (this.claveSerieV != null && txtCondicionPresente.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe proporcionar la condicion presente en el campo", "Clave de vegetacion", JOptionPane.INFORMATION_MESSAGE);
            txtCondicionPresente.requestFocus();
            return false;
        } else if (rbtSecundario.isSelected() && this.faseSucecional == null) {
            JOptionPane.showMessageDialog(null, "Si selecciono condicion secundaria, debe seleccionar fase sucecional", "Clave de vegetacion", JOptionPane.INFORMATION_MESSAGE);
            cmbFaseSucecional.requestFocus();
            return false;
        } else if (this.ecotono == 1 && txtDescripcionEcotono.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe proporcionar la descripcion del ecotono", "Clave de vegetacion", JOptionPane.INFORMATION_MESSAGE);
            txtDescripcionEcotono.requestFocus();
            return false;
        } else {
            return true;
        }
    }
    
    private void seleccionarSiguienteFormulario(CESitio ceSitio) {
        Integer secuenciaID = ceSitio.getSecuencia();
        Integer sitio = this.funciones.sitioCapturaSueloCarbono(this.upmID, 3);
        if (secuenciaID != null) {
            switch (secuenciaID) {
                case 1: //Módulo A
                    if (sitio == this.ceSitio.getSitio()) {
                        UPMForms.carbono.setDatosIniciales(ceSitio);
                        UPMForms.carbono.setVisible(true);
                    } else {
                        UPMForms.observaciones.setDatosiniciales(ceSitio);
                        UPMForms.observaciones.setVisible(true);
                    }
                    break;
                case 2: //Módulos A y C
                    if (sitio == this.ceSitio.getSitio()) {
                        UPMForms.suelo.setDatosiniciales(ceSitio);
                        UPMForms.suelo.setVisible(true);
                    } else {
                        UPMForms.observaciones.setDatosiniciales(ceSitio);
                        UPMForms.observaciones.setVisible(true);
                    }
                    break;
                case 3: //Modulos A, C, E y G
                    if (sitio == this.ceSitio.getSitio()) {
                        UPMForms.suelo.setDatosiniciales(ceSitio);
                        UPMForms.suelo.setVisible(true);
                    } else {
                        UPMForms.observaciones.setDatosiniciales(ceSitio);
                        UPMForms.observaciones.setVisible(true);
                    }
                    break;
                case 4: //Modulos A y E
                    if (sitio == this.ceSitio.getSitio()) {
                        UPMForms.carbono.setDatosIniciales(ceSitio);
                        UPMForms.carbono.setVisible(true);
                    } else {
                        UPMForms.observaciones.setDatosiniciales(ceSitio);
                        UPMForms.observaciones.setVisible(true);
                    }
                    break;
                case 5: //Modulos A, C, D y F
                    if (sitio == this.ceSitio.getSitio()) {
                        UPMForms.suelo.setDatosiniciales(ceSitio);
                        UPMForms.suelo.setVisible(true);
                    } else {
                        UPMForms.observaciones.setDatosiniciales(ceSitio);
                        UPMForms.observaciones.setVisible(true);
                    }
                    break;
                case 6://Modulos A, C y D
                    if (sitio == this.ceSitio.getSitio()) {
                        UPMForms.suelo.setDatosiniciales(ceSitio);
                        UPMForms.suelo.setVisible(true);
                    } else {
                        UPMForms.observaciones.setDatosiniciales(ceSitio);
                        UPMForms.observaciones.setVisible(true);
                    }
                    break;
                case 7://Modulos A, C, D y E
                    if (sitio == this.ceSitio.getSitio()) {
                        UPMForms.suelo.setDatosiniciales(ceSitio);
                        UPMForms.suelo.setVisible(true);
                    } else {
                        UPMForms.observaciones.setDatosiniciales(ceSitio);
                        UPMForms.observaciones.setVisible(true);
                    }
                    break;
                case 8://Modulos A, C, D, E y F
                    if (sitio == this.ceSitio.getSitio()) {
                        UPMForms.suelo.setDatosiniciales(ceSitio);
                        UPMForms.suelo.setVisible(true);
                    } else {
                        UPMForms.observaciones.setDatosiniciales(ceSitio);
                        UPMForms.observaciones.setVisible(true);
                    }
                    break;
                case 9://Modulos A, C y E
                    if (sitio == this.ceSitio.getSitio()) {
                        UPMForms.suelo.setDatosiniciales(ceSitio);
                        UPMForms.suelo.setVisible(true);
                    } else {
                        UPMForms.observaciones.setDatosiniciales(ceSitio);
                        UPMForms.observaciones.setVisible(true);
                    }
                    break;
                case 10://Modulos A, C, H
                    if (sitio == this.ceSitio.getSitio()) {
                        UPMForms.suelo.setDatosiniciales(ceSitio);
                        UPMForms.suelo.setVisible(true);
                    } else {
                        UPMForms.observaciones.setDatosiniciales(ceSitio);
                        UPMForms.observaciones.setVisible(true);
                    }
                    break;
                case 11://Modulos A y H
                    if (sitio == this.ceSitio.getSitio()) {
                        UPMForms.carbono.setDatosIniciales(ceSitio);
                        UPMForms.carbono.setVisible(true);
                    } else {
                        UPMForms.observaciones.setDatosiniciales(ceSitio);
                        UPMForms.observaciones.setVisible(true);
                    }
                    break;
                case 12://Modulos A, E y H
                    if (sitio == this.ceSitio.getSitio()) {
                        UPMForms.carbono.setDatosIniciales(ceSitio);
                        UPMForms.carbono.setVisible(true);
                    } else {
                        UPMForms.observaciones.setDatosiniciales(ceSitio);
                        UPMForms.observaciones.setVisible(true);
                    }
                    break;
                case 13://Modulos A, C, E y H
                    if (sitio == this.ceSitio.getSitio()) {
                        UPMForms.suelo.setDatosiniciales(ceSitio);
                        UPMForms.suelo.setVisible(true);
                    } else {
                        UPMForms.observaciones.setDatosiniciales(ceSitio);
                        UPMForms.observaciones.setVisible(true);
                    }
                    break;
                case 14://Modulos A, E y G
                    if (sitio == this.ceSitio.getSitio()) {
                        UPMForms.carbono.setDatosIniciales(ceSitio);
                        UPMForms.carbono.setVisible(true);
                    } else {
                        UPMForms.observaciones.setDatosiniciales(ceSitio);
                        UPMForms.observaciones.setVisible(true);
                    }
                    break;
                case 15://A y G
                   if (sitio == this.ceSitio.getSitio()) {
                        UPMForms.carbono.setDatosIniciales(ceSitio);
                        UPMForms.carbono.setVisible(true);
                    } else {
                        UPMForms.observaciones.setDatosiniciales(ceSitio);
                        UPMForms.observaciones.setVisible(true);
                    }
                    break;
                case 16:
                    if (sitio == this.ceSitio.getSitio()) {
                        UPMForms.suelo.setDatosiniciales(ceSitio);
                        UPMForms.suelo.setVisible(true);
                    } else
                    {
                        UPMForms.observaciones.setDatosiniciales(ceSitio);
                        UPMForms.observaciones.setVisible(true);
                    }
                    break;
            }
        }
    }
    
    private void revisarSiguienteFormulario(CESitio ceSitio){
        Integer secuenciaID = ceSitio.getSecuencia();
        Integer sitio = this.funciones.sitioCapturaSueloCarbono(this.upmID, 3);
        if (secuenciaID != null) {
            switch (secuenciaID) {
                case 1: //Módulo A
                    if (sitio == this.ceSitio.getSitio()) {
                        UPMForms.carbono.revisarCarbono(ceSitio);
                        UPMForms.carbono.setVisible(true);
                    } else {
                        UPMForms.observaciones.revisarObservaciones(ceSitio);
                        UPMForms.observaciones.setVisible(true);
                    }
                    break;
                case 2: //Módulos A y C
                    if (sitio == this.ceSitio.getSitio()) {
                        UPMForms.suelo.revisarSuelo(ceSitio);
                        UPMForms.suelo.setVisible(true);
                    } else {
                        UPMForms.observaciones.revisarObservaciones(ceSitio);
                        UPMForms.observaciones.setVisible(true);
                    }
                    break;
                case 3: //Modulos A, C, E y G
                    if (sitio == this.ceSitio.getSitio()) {
                        UPMForms.suelo.revisarSuelo(ceSitio);
                        UPMForms.suelo.setVisible(true);
                    } else {
                        UPMForms.observaciones.setDatosiniciales(ceSitio);
                        UPMForms.observaciones.setVisible(true);
                    }
                    break;
                case 4: //Modulos A y E
                    if (sitio == this.ceSitio.getSitio()) {
                        UPMForms.carbono.revisarCarbono(ceSitio);
                        UPMForms.carbono.setVisible(true);
                    } else {
                        UPMForms.observaciones.revisarObservaciones(ceSitio);
                        UPMForms.observaciones.setVisible(true);
                    }
                    break;
                case 5: //Modulos A, C, D y F
                    if (sitio == this.ceSitio.getSitio()) {
                        UPMForms.suelo.revisarSuelo(ceSitio);
                        UPMForms.suelo.setVisible(true);
                    } else {
                        UPMForms.observaciones.revisarObservaciones(ceSitio);
                        UPMForms.observaciones.setVisible(true);
                    }
                    break;
                case 6://Modulos A, C y D
                    if (sitio == this.ceSitio.getSitio()) {
                        UPMForms.suelo.revisarSuelo(ceSitio);
                        UPMForms.suelo.setVisible(true);
                    } else {
                        UPMForms.observaciones.revisarObservaciones(ceSitio);
                        UPMForms.observaciones.setVisible(true);
                    }
                    break;
                case 7://Modulos A, C, D y E
                    if (sitio == this.ceSitio.getSitio()) {
                        UPMForms.suelo.revisarSuelo(ceSitio);
                        UPMForms.suelo.setVisible(true);
                    } else {
                        UPMForms.observaciones.revisarObservaciones(ceSitio);
                        UPMForms.observaciones.setVisible(true);
                    }
                    break;
                case 8://Modulos A, C, D, E y F
                    if (sitio == this.ceSitio.getSitio()) {
                        UPMForms.suelo.revisarSuelo(ceSitio);
                        UPMForms.suelo.setVisible(true);
                    } else {
                        UPMForms.observaciones.revisarObservaciones(ceSitio);
                        UPMForms.observaciones.setVisible(true);
                    }
                    break;
                case 9://Modulos A, C y E
                    if (sitio == this.ceSitio.getSitio()) {
                        UPMForms.suelo.revisarSuelo(ceSitio);
                        UPMForms.suelo.setVisible(true);
                    } else {
                        UPMForms.observaciones.revisarObservaciones(ceSitio);
                        UPMForms.observaciones.setVisible(true);
                    }
                    break;
                case 10://Modulos A, C, H
                    if (sitio == this.ceSitio.getSitio()) {
                        UPMForms.suelo.revisarSuelo(ceSitio);
                        UPMForms.suelo.setVisible(true);
                    } else {
                        UPMForms.observaciones.revisarObservaciones(ceSitio);
                        UPMForms.observaciones.setVisible(true);
                    }
                    break;
                case 11://Modulos A y H
                    if (sitio == this.ceSitio.getSitio()) {
                        UPMForms.carbono.revisarCarbono(ceSitio);
                        UPMForms.carbono.setVisible(true);
                    } else {
                        UPMForms.observaciones.revisarObservaciones(ceSitio);
                        UPMForms.observaciones.setVisible(true);
                    }
                    break;
                case 12://Modulos A, E y H
                    if (sitio == this.ceSitio.getSitio()) {
                        UPMForms.carbono.revisarCarbono(ceSitio);
                        UPMForms.carbono.setVisible(true);
                    } else {
                        UPMForms.observaciones.revisarObservaciones(ceSitio);
                        UPMForms.observaciones.setVisible(true);
                    }
                    break;
                case 13://Modulos A, C, E y H
                    if (sitio == this.ceSitio.getSitio()) {
                        UPMForms.suelo.revisarSuelo(ceSitio);
                        UPMForms.suelo.setVisible(true);
                    } else {
                        UPMForms.observaciones.revisarObservaciones(ceSitio);
                        UPMForms.observaciones.setVisible(true);
                    }
                    break;
                case 14://Modulos A, E y G
                    if (sitio == this.ceSitio.getSitio()) {
                        UPMForms.carbono.revisarCarbono(ceSitio);
                        UPMForms.carbono.setVisible(true);
                    } else {
                        UPMForms.observaciones.revisarObservaciones(ceSitio);
                        UPMForms.observaciones.setVisible(true);
                    }
                    break;
                case 15://A y G
                    if (sitio == this.ceSitio.getSitio()) {
                        UPMForms.carbono.revisarCarbono(ceSitio);
                        UPMForms.carbono.setVisible(true);
                    } else {
                        UPMForms.observaciones.revisarObservaciones(ceSitio);
                        UPMForms.observaciones.setVisible(true);
                    }
                    break;
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grbTipoVegetacion = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        lblUPM = new javax.swing.JLabel();
        txtUPM = new javax.swing.JTextField();
        lblSitio = new javax.swing.JLabel();
        txtSitio = new javax.swing.JTextField();
        lblClaveVegetacion = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblCondicion = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        rbtPrimario = new javax.swing.JRadioButton();
        rbtSecundario = new javax.swing.JRadioButton();
        lblFaseSucecional = new javax.swing.JLabel();
        cmbFaseSucecional = new javax.swing.JComboBox();
        lblClaveSerieV = new javax.swing.JLabel();
        cmbClaveSerieV = new javax.swing.JComboBox();
        chkArbolFueraBosque = new javax.swing.JCheckBox();
        chkEcotono = new javax.swing.JCheckBox();
        lblCoberturaVegetal = new javax.swing.JLabel();
        lblEcosistema = new javax.swing.JLabel();
        lblEcotono = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcionEcotono = new javax.swing.JTextArea();
        lblCondicionPresente1 = new javax.swing.JLabel();
        lblCondicionPresente2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtCondicionPresente = new javax.swing.JTextArea();
        btnContinuar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setMaximizable(true);
        setTitle("Clave de vegetacion "+version);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/gob/conafor/utils/logo_conafor.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(940, 650));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        lblUPM.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblUPM.setText("UPMID:");

        txtUPM.setEditable(false);
        txtUPM.setEnabled(false);

        lblSitio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblSitio.setText("Sitio:");

        txtSitio.setEditable(false);
        txtSitio.setEnabled(false);

        lblClaveVegetacion.setBackground(new java.awt.Color(153, 153, 153));
        lblClaveVegetacion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblClaveVegetacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblClaveVegetacion.setText("Clave de vegetación");
        lblClaveVegetacion.setOpaque(true);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblCondicion.setBackground(new java.awt.Color(153, 153, 153));
        lblCondicion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCondicion.setText("Condición");
        lblCondicion.setOpaque(true);

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        rbtPrimario.setBackground(new java.awt.Color(204, 204, 204));
        grbTipoVegetacion.add(rbtPrimario);
        rbtPrimario.setText("Primario");
        rbtPrimario.setEnabled(false);
        rbtPrimario.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rbtPrimario.setNextFocusableComponent(rbtSecundario);
        rbtPrimario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtPrimarioActionPerformed(evt);
            }
        });

        rbtSecundario.setBackground(new java.awt.Color(204, 204, 204));
        grbTipoVegetacion.add(rbtSecundario);
        rbtSecundario.setText("Secundario");
        rbtSecundario.setEnabled(false);
        rbtSecundario.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rbtSecundario.setNextFocusableComponent(cmbFaseSucecional);
        rbtSecundario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtSecundarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rbtPrimario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rbtSecundario)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtPrimario)
                    .addComponent(rbtSecundario))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblFaseSucecional.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFaseSucecional.setText("Fase sucecional");

        cmbFaseSucecional.setEnabled(false);

        lblClaveSerieV.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblClaveSerieV.setText("Clave serie V");

        cmbClaveSerieV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbClaveSerieVActionPerformed(evt);
            }
        });

        chkArbolFueraBosque.setBackground(new java.awt.Color(204, 204, 204));
        chkArbolFueraBosque.setText("¿Arbol fuera del bosque?");
        chkArbolFueraBosque.setEnabled(false);
        chkArbolFueraBosque.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkArbolFueraBosque.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        chkEcotono.setBackground(new java.awt.Color(204, 204, 204));
        chkEcotono.setText("¿Ecotono?");
        chkEcotono.setEnabled(false);
        chkEcotono.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkEcotono.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        chkEcotono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkEcotonoActionPerformed(evt);
            }
        });

        lblCoberturaVegetal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCoberturaVegetal.setText("Cobertura vegetal");

        lblEcosistema.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEcosistema.setText("Ecosistema");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblCoberturaVegetal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblEcosistema, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblFaseSucecional, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCondicion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(lblClaveSerieV, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(chkEcotono)
                                .addComponent(chkArbolFueraBosque)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(20, 20, 20))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmbClaveSerieV, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(cmbFaseSucecional, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblClaveSerieV)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbClaveSerieV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblCoberturaVegetal, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblEcosistema)
                .addGap(24, 24, 24)
                .addComponent(lblCondicion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(lblFaseSucecional)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbFaseSucecional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(chkArbolFueraBosque)
                .addGap(18, 18, 18)
                .addComponent(chkEcotono)
                .addContainerGap(136, Short.MAX_VALUE))
        );

        lblEcotono.setText("En caso de presentarse describa brevemente la condición del ecotono:");

        txtDescripcionEcotono.setColumns(20);
        txtDescripcionEcotono.setRows(5);
        txtDescripcionEcotono.setEnabled(false);
        txtDescripcionEcotono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDescripcionEcotonoKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(txtDescripcionEcotono);

        lblCondicionPresente1.setText("Describa brevemente los criterios que utilizo para la condición presente en campo (condiciones ecológicas, florísticas , fisonómicas, uso del suelo");

        lblCondicionPresente2.setText("suelo, topografía, exposición,  entre otros:");

        txtCondicionPresente.setColumns(20);
        txtCondicionPresente.setRows(5);
        txtCondicionPresente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCondicionPresenteKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(txtCondicionPresente);

        btnContinuar.setMnemonic('c');
        btnContinuar.setText("Continuar");
        btnContinuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinuarActionPerformed(evt);
            }
        });

        btnSalir.setMnemonic('s');
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblUPM)
                                .addGap(10, 10, 10)
                                .addComponent(txtUPM, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(588, 588, 588)
                                .addComponent(lblSitio)
                                .addGap(18, 18, 18)
                                .addComponent(txtSitio, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 688, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(lblCondicionPresente2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblCondicionPresente1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 688, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 687, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblEcotono, javax.swing.GroupLayout.PREFERRED_SIZE, 697, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lblClaveVegetacion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 920, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnContinuar)
                        .addGap(28, 28, 28)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(lblUPM))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblSitio)
                                .addComponent(txtSitio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtUPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(6, 6, 6)
                .addComponent(lblClaveVegetacion)
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(lblCondicionPresente1)
                        .addGap(1, 1, 1)
                        .addComponent(lblCondicionPresente2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblEcotono)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)))
                .addGap(33, 33, 33)
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
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        if(revision==false){//esta en modo de captura
            this.hide();
            funciones.manipularBotonesMenuPrincipal(false);
        }
        if(revision==true){//entro a modo de revision
            //System.err.println("Modo Revision");
            this.hide();
            //UPMForms.revisionModulos.iniciarRevision();
            UPMForms.revisionModulos.setVisible(true);
            UPMForms.revisionModulos.manipularBonesMenuprincipal();
            revision=false;
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuarActionPerformed
        asignarDatosClaveVegetacion();
        if (validarClaveVegetacion()) {
            if (this.modificar == 0) {
                crearClaveVegetacion();
                this.hide();
                if (this.ceSitio.getSitio() == 1) { //es sitio 1
                    UPMForms.caracteristicasUPM.setDatosIniciales(ceSitio);
                    UPMForms.caracteristicasUPM.setVisible(true);
                } else {
                    seleccionarSiguienteFormulario(this.ceSitio);
                }
                this.cdSecuencia.updateSecuencia(this.ceSitio, FORMATO_ID, 1);
            } else {
                crearClaveVegetacion();
                this.hide();
                if (this.ceSitio.getSitio() == 1) {
                    UPMForms.caracteristicasUPM.revisarCaracteristicasUPM(ceSitio);
                    UPMForms.caracteristicasUPM.setVisible(true);
                } else {
                    revisarSiguienteFormulario(this.ceSitio);
                }
            }
        }
    }//GEN-LAST:event_btnContinuarActionPerformed

    private void txtCondicionPresenteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCondicionPresenteKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB){
            evt.consume();
        }
    }//GEN-LAST:event_txtCondicionPresenteKeyPressed

    private void txtDescripcionEcotonoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionEcotonoKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB){
            evt.consume();
        }
    }//GEN-LAST:event_txtDescripcionEcotonoKeyPressed

    private void chkEcotonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkEcotonoActionPerformed
        if (chkEcotono.isSelected()==true) {
            txtDescripcionEcotono.setEnabled(true);
        } else {
            txtDescripcionEcotono.setEnabled(false);
            txtDescripcionEcotono.setText("");
        }
    }//GEN-LAST:event_chkEcotonoActionPerformed

    private void cmbClaveSerieVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbClaveSerieVActionPerformed
       validarComboBoxClaveVegetacion();
       
    }//GEN-LAST:event_cmbClaveSerieVActionPerformed
    
    public void validarComboBoxClaveVegetacion(){
        try {
            CatEClaveSerieV claveV = (CatEClaveSerieV) cmbClaveSerieV.getSelectedItem();
            if (claveV.getEsForestal() == 1) {
                lblCoberturaVegetal.setText("Forestal");
                rbtPrimario.setEnabled(true);
                rbtSecundario.setEnabled(true);
                chkArbolFueraBosque.setEnabled(false);
                chkArbolFueraBosque.setSelected(false);
                chkEcotono.setEnabled(true);
                //System.err.println(txtDescripcionEcotono.getText());
            } else if (claveV.getEsForestal() == 0 && claveV.getClaveSerievID() != 255) {
                lblCoberturaVegetal.setText("No Forestal");
                rbtPrimario.setEnabled(false);
                rbtSecundario.setEnabled(false);
                grbTipoVegetacion.clearSelection();
                chkArbolFueraBosque.setEnabled(true);
                cmbFaseSucecional.setSelectedItem(null);
                cmbFaseSucecional.setEnabled(false);
                chkEcotono.setEnabled(false);
                chkEcotono.setSelected(false);
                txtDescripcionEcotono.setText("");
                txtDescripcionEcotono.setEnabled(false);
            } else if (claveV.getEsForestal() == 0 && claveV.getClaveSerievID() == 255) {
                lblCoberturaVegetal.setText("No aplica");
                rbtPrimario.setEnabled(false);
                rbtSecundario.setEnabled(false);
                grbTipoVegetacion.clearSelection();
                chkArbolFueraBosque.setEnabled(false);
                cmbFaseSucecional.setSelectedItem(null);
                cmbFaseSucecional.setEnabled(false);
                chkEcotono.setSelected(false);
                chkEcotono.setEnabled(false);
                 
                txtDescripcionEcotono.setText("");
                txtDescripcionEcotono.setEnabled(false);
            }

            lblEcosistema.setText(claveV.getTipoVegetacion());
        } catch (NullPointerException e) {
            lblCoberturaVegetal.setText("Cobertura vegetal");
            lblEcosistema.setText("Ecosistema");
            rbtPrimario.setSelected(false);
            rbtSecundario.setSelected(false);
            grbTipoVegetacion.clearSelection();
            cmbFaseSucecional.setSelectedItem(null);
            cmbFaseSucecional.setEnabled(false);
            chkArbolFueraBosque.setEnabled(false);
            chkArbolFueraBosque.setSelected(false);
            chkEcotono.setSelected(false);
            chkEcotono.setEnabled(false);
            txtDescripcionEcotono.setText("");
            txtDescripcionEcotono.setEnabled(false);
        }
    }
    
    private void rbtSecundarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtSecundarioActionPerformed
        cmbFaseSucecional.setEnabled(true);
    }//GEN-LAST:event_rbtSecundarioActionPerformed

    private void rbtPrimarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtPrimarioActionPerformed
        cmbFaseSucecional.setEnabled(false);
        cmbFaseSucecional.setSelectedItem(null);
    }//GEN-LAST:event_rbtPrimarioActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnContinuar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JCheckBox chkArbolFueraBosque;
    private javax.swing.JCheckBox chkEcotono;
    private javax.swing.JComboBox cmbClaveSerieV;
    private javax.swing.JComboBox cmbFaseSucecional;
    private javax.swing.ButtonGroup grbTipoVegetacion;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblClaveSerieV;
    private javax.swing.JLabel lblClaveVegetacion;
    private javax.swing.JLabel lblCoberturaVegetal;
    private javax.swing.JLabel lblCondicion;
    private javax.swing.JLabel lblCondicionPresente1;
    private javax.swing.JLabel lblCondicionPresente2;
    private javax.swing.JLabel lblEcosistema;
    private javax.swing.JLabel lblEcotono;
    private javax.swing.JLabel lblFaseSucecional;
    private javax.swing.JLabel lblSitio;
    private javax.swing.JLabel lblUPM;
    private javax.swing.JRadioButton rbtPrimario;
    private javax.swing.JRadioButton rbtSecundario;
    private javax.swing.JTextArea txtCondicionPresente;
    private javax.swing.JTextArea txtDescripcionEcotono;
    private javax.swing.JTextField txtSitio;
    private javax.swing.JTextField txtUPM;
    // End of variables declaration//GEN-END:variables
}
