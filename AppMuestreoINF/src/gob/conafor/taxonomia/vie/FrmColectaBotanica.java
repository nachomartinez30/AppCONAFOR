package gob.conafor.taxonomia.vie;

import gob.conafor.sitio.mod.CDSitio;
import gob.conafor.taxonomia.mod.CDColectaBotanica;
import gob.conafor.taxonomia.mod.CDEspecies;
import gob.conafor.taxonomia.mod.CEColectaBotanica;
import gob.conafor.taxonomia.mod.CatEEspecie;
import gob.conafor.taxonomia.mod.CatEFamiliaEspecie;
import gob.conafor.taxonomia.mod.CatEGenero;
import gob.conafor.taxonomia.mod.CatEInfraespecie;
import gob.conafor.taxonomia.mod.CatESeccionTaxonomica;
import gob.conafor.utils.FuncionesComunes;
import gob.conafor.utils.Version;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class FrmColectaBotanica extends javax.swing.JInternalFrame {

    private CDColectaBotanica cdColecta = new CDColectaBotanica();
    private CEColectaBotanica ceColecta = new CEColectaBotanica();
    private FuncionesComunes funciones = new FuncionesComunes();
    private CDEspecies cdEspecies = new CDEspecies();
    private Integer upmID;
    private String claveColecta;
    private Integer familiaID;
    private Integer generoID;
    private Integer especieID;
    private String infraespecie;
    private String nombreComun;
    private Integer sitio;
    private Integer seccionID;
    private Integer consecutivo;
    private Integer contraFuertes;
    private Integer exudado;
    private String indicarExudado;
    private Integer color;
    private String indicarColor;
    private Integer cambioColor;
    private Integer aceitesVolatiles;
    private Integer colorFlor;
    private String indicaColorFlor;
    private Integer hojasTejidoVivo;
    private Integer fotoFlor;
    private Integer fotoFruto;
    private Integer fotoHojasArriva;
    private Integer fotoHojasAbajo;
    private Integer fotoArbolCompleto;
    private Integer fotoCorteza;
    private Integer virutaIncluida;
    private Integer cortezaIncluida;
    private Integer maderaIncluida;
    private String observaciones;
    private Version ver = new Version();
    private String version = ver.getVersion();

    public FrmColectaBotanica() {
        initComponents();
    }

    public void procesarColecta() {
        limpiarControles(false);
        funciones.reiniciarComboModel(cmbUPMID);
        funciones.manipularBotonesMenuPrincipal(true);
        fillCmbUPM();
        fillCmbFamilia();
        fillCmbGenero();
        fillCmbSeccionesTaxonomicas();
    }

    private void fillCmbUPM() {
        List<Integer> listUPMID = new ArrayList<>();
        listUPMID = this.cdColecta.getUPMID();
        if (listUPMID != null) {
            int size = listUPMID.size();
            for (int i = 0; i < size; i++) {
                cmbUPMID.addItem(listUPMID.get(i));
            }
        }
    }

    private void fillCmbSeccionesTaxonomicas() {
        List<CatESeccionTaxonomica> listSeccion = new ArrayList<>();
        listSeccion = this.cdColecta.getSecciones();
        if (listSeccion != null) {
            int size = listSeccion.size();
            for (int i = 0; i < size; i++) {
                cmbSeccion.addItem(listSeccion.get(i));
            }
        }
    }

    private void fillCmbSitio(int upmID) {
        List<Integer> listSitio = new ArrayList<>();
        CDSitio cdSitio = new CDSitio();
        listSitio = cdSitio.getSitiosAccesibles(upmID);
        if (listSitio != null) {
            int size = listSitio.size();
            for (int i = 0; i < size; i++) {
                cmbSitio.addItem(listSitio.get(i));
            }
        }
    }

    private void fillCmbClaveColecta(int upmID) {
        List<String> listClave = new ArrayList<>();
        listClave = this.cdColecta.getClaveColecta(upmID);
        if (listClave != null) {
            int size = listClave.size();
            for (int i = 0; i < size; i++) {
                cmbClaveColecta.addItem(listClave.get(i));
            }
        }
    }

    public void fillCmbFamilia() {
        List<CatEFamiliaEspecie> listFamilia = new ArrayList<>();
        listFamilia = cdEspecies.getFamiliaEspecies();
        if (listFamilia != null) {
            int size = listFamilia.size();
            for (int i = 0; i < size; i++) {
                cmbFamilia.addItem(listFamilia.get(i));
            }
        }
    }

    public void fillCmbGenero() {
        List<CatEGenero> listGenero = new ArrayList<>();
        CDEspecies sp = new CDEspecies();
        listGenero = sp.getGenero();
        if (listGenero != null) {
            int size = listGenero.size();
            for (int i = 0; i < size; i++) {
                cmbGenero.addItem(listGenero.get(i));
            }
        }
    }

    private void fillCmbGeneroSF() {
        List<CatEGenero> listGenero = new ArrayList<>();
        CDEspecies sp = new CDEspecies();
        listGenero = sp.getGenerosSF();
        if (listGenero != null) {
            int size = listGenero.size();
            for (int i = 0; i < size; i++) {
                cmbGenero.addItem(listGenero.get(i));
            }
        }
    }

    public void fillCmbEspecie(int index) {
        List<CatEEspecie> listEspecie = new ArrayList<>();
        CDEspecies sp = new CDEspecies();
        listEspecie = sp.getEspecies(index);
        if (listEspecie != null) {
            int size = listEspecie.size();
            for (int i = 0; i < size; i++) {
                cmbEspecie.addItem(listEspecie.get(i));
            }
        }
    }

    private void fillCmbInfraespecie(int index) {
        List<CatEInfraespecie> listInfraespecie = new ArrayList<>();
        CDEspecies sp = new CDEspecies();
        listInfraespecie = sp.getInfraespecie(index);
        if (listInfraespecie != null) {
            int size = listInfraespecie.size();
            for (int i = 0; i < size; i++) {
                cmbInfraespecie.addItem(listInfraespecie.get(i));
            }
        }
    }

    private void fijarValoresColecta() {
        if (chkContraFuertes.isSelected()) {
            this.contraFuertes = 1;
        } else {
            this.contraFuertes = 0;
        }
        if (chkExudado.isSelected()) {
            this.exudado = 1;
            this.indicarExudado = txtExudado.getText();
        } else {
            this.exudado = 0;
        }
        if (chkColor.isSelected()) {
            this.color = 1;
            this.indicarColor = txtColor.getText();
        } else {
            this.color = 0;
        }
        if (chkCambioColor.isSelected()) {
            this.cambioColor = 1;
        } else {
            this.cambioColor = 0;
        }
        if (chkAceitesVolatiles.isSelected()) {
            this.aceitesVolatiles = 1;
        } else {
            this.aceitesVolatiles = 0;
        }
        if (chkColoresFlores.isSelected()) {
            this.colorFlor = 1;
            this.indicaColorFlor = txtColoresFlores.getText();
        } else {
            this.colorFlor = 0;
        }
        if (chkHojas.isSelected()) {
            this.hojasTejidoVivo = 1;
        } else {
            this.hojasTejidoVivo = 0;
        }
        if (chkFotoFlor.isSelected()) {
            this.fotoFlor = 1;
        } else {
            this.fotoFlor = 0;
        }
        if (chkFotoFruto.isSelected()) {
            this.fotoFruto = 1;
        } else {
            this.fotoFruto = 0;
        }
        if (chkHojasVistasArriba.isSelected()) {
            this.fotoHojasArriva = 1;
        } else {
            this.fotoHojasArriva = 0;
        }
        if (chkHojasVistasAbajo.isSelected()) {
            this.fotoHojasAbajo = 1;
        } else {
            this.fotoHojasAbajo = 0;
        }
        if (chkFotoArbolCompleto.isSelected()) {
            this.fotoArbolCompleto = 1;
        } else {
            this.fotoArbolCompleto = 0;
        }
        if (chkFotoCorteza.isSelected()) {
            this.fotoCorteza = 1;
        } else {
            this.fotoCorteza = 0;
        }
        if (chkVirutaIncluida.isSelected()) {
            this.virutaIncluida = 1;
        } else {
            this.virutaIncluida = 0;
        }
        if (chkCortezaIncluida.isSelected()) {
            this.cortezaIncluida = 1;
        } else {
            this.cortezaIncluida = 0;
        }
        if (chkMaderaIncluida.isSelected()) {
            this.maderaIncluida = 1;
        } else {
            this.maderaIncluida = 0;
        }
        this.observaciones = txtObservacionesColecta.getText();
    }

    private void guardarDatosColecta() {
        String claveColecta = (String) cmbClaveColecta.getSelectedItem();
        Integer sitio = (Integer) cmbSitio.getSelectedItem();
        if(cmbSitio.getSelectedItem()==null){
            sitio=999;
        }
        
        
        Integer seccion=999;
        if(cmbSeccion.getSelectedItem()==null){
            seccion=999;
        }else{
            CatESeccionTaxonomica seccionID = (CatESeccionTaxonomica) cmbSeccion.getSelectedItem();
            seccion=(Integer) seccionID.getSeccionTaxonomicaID();
        }
        Integer consecutivo = Integer.valueOf(txtConsecutivo.getText());
        this.ceColecta.setSitio(sitio);
        this.ceColecta.setSeccionID(seccion);
        this.ceColecta.setConsecutivo(consecutivo);
        this.ceColecta.setClaveColecta(claveColecta);
        this.ceColecta.setContraFuertes(this.contraFuertes);
        this.ceColecta.setExudado(this.exudado);
        this.ceColecta.setIndicarExudado(this.indicarExudado);
        this.ceColecta.setColor(this.color);
        this.ceColecta.setIndicarColor(this.indicarColor);
        this.ceColecta.setCambioColor(this.cambioColor);
        this.ceColecta.setAceitesVolatiles(this.aceitesVolatiles);
        this.ceColecta.setColorFlor(this.colorFlor);
        this.ceColecta.setIndicarColorFlor(this.indicaColorFlor);
        this.ceColecta.setHojasTejidoVivo(this.hojasTejidoVivo);
        this.ceColecta.setFotoFlor(this.fotoFlor);
        this.ceColecta.setFotoFruto(this.fotoFruto);
        this.ceColecta.setFotoHojasArriba(this.fotoHojasArriva);
        this.ceColecta.setFotoHojasAbajo(this.fotoHojasAbajo);
        this.ceColecta.setFotoArbolCompleto(this.fotoArbolCompleto);
        this.ceColecta.setFotoCorteza(this.fotoCorteza);
        this.ceColecta.setVirutaIncluida(this.virutaIncluida);
        this.ceColecta.setCortezaIncluida(this.cortezaIncluida);
        this.ceColecta.setMaderaIncluida(this.maderaIncluida);
        this.ceColecta.setObservaciones(this.observaciones);
        this.cdColecta.actualizarColectaBotanica(this.ceColecta);
    }

    private void fijarValoresCampos(String claveColecta) {
        this.ceColecta = this.cdColecta.getColectaBotanica(claveColecta);
        CatEFamiliaEspecie ceFamilia = new CatEFamiliaEspecie();
        CatEGenero ceGenero = new CatEGenero();
        CatEEspecie ceEspecie = new CatEEspecie();
        CatEInfraespecie ceInfraespecie = new CatEInfraespecie();
        CatESeccionTaxonomica ceSeccion = new CatESeccionTaxonomica();

        ceFamilia.setFamiliaID(this.ceColecta.getFamiliaID());
        ceGenero.setGeneroID(this.ceColecta.getGeneroID());
        ceEspecie.setEspecieID(this.ceColecta.getEspecieID());
        ceInfraespecie.setEspecieID(this.ceColecta.getInfraespecie());
        ceSeccion.setSeccionTaxonomicaID(this.ceColecta.getSeccionID());
        Integer sitio = this.ceColecta.getSitio();
        if(sitio==999){
            chkExterna.setSelected(true);
            cmbSitio.setEnabled(false);
            cmbSitio.setSelectedItem(null);
            cmbSeccion.setEnabled(false);
            cmbSeccion.setSelectedItem(null);
            txtConsecutivo.setEnabled(false);
            txtConsecutivo.setText("0");
        }
        Integer consecutivo = this.ceColecta.getConsecutivo();
        cmbFamilia.setSelectedItem(ceFamilia);
        cmbGenero.setSelectedItem(ceGenero);
        cmbEspecie.setSelectedItem(ceEspecie);
        cmbInfraespecie.setSelectedItem(ceInfraespecie);
        cmbSeccion.setSelectedItem(ceSeccion);
        cmbSitio.setSelectedItem(sitio);
        txtConsecutivo.setText(String.valueOf(consecutivo));
        txtNombreComun.setText(this.ceColecta.getNombreComun());

        if (this.cdColecta.hayDatosColecta(claveColecta)) {
            if (this.ceColecta.getContraFuertes() == 1) {
                chkContraFuertes.setSelected(true);
            } else {
                chkContraFuertes.setSelected(false);
            }
            if (this.ceColecta.getExudado() == 1) {
                chkExudado.setSelected(true);
                txtExudado.setText(this.ceColecta.getIndicarExudado());
                txtExudado.setEnabled(true);
            } else {
                chkExudado.setSelected(false);
                txtExudado.setText("");
                txtExudado.setEnabled(false);
            }
            if (this.ceColecta.getColor() == 1) {
                chkColor.setSelected(true);
                txtColor.setText(this.ceColecta.getIndicarColor());
                txtColor.setEnabled(true);
            } else {
                chkColor.setSelected(false);
                txtColor.setText("");
                txtColor.setEnabled(false);
            }
            if (this.ceColecta.getCambioColor() == 1) {
                chkCambioColor.setSelected(true);
            } else {
                chkCambioColor.setSelected(false);
            }
            if (this.ceColecta.getAceitesVolatiles() == 1) {
                chkAceitesVolatiles.setSelected(true);
            } else {
                chkAceitesVolatiles.setSelected(false);
            }
            if (this.ceColecta.getColorFlor() == 1) {
                chkColoresFlores.setSelected(true);
                txtColoresFlores.setText(this.ceColecta.getIndicarColorFlor());
                txtColoresFlores.setEnabled(true);
            } else {
                chkColoresFlores.setSelected(false);
                txtColoresFlores.setText("");
                txtColoresFlores.setEnabled(false);
            }
            if (this.ceColecta.getHojasTejidoVivo() == 1) {
                chkHojas.setSelected(true);
            } else {
                chkHojas.setSelected(false);
            }
            if (this.ceColecta.getFotoFlor() == 1) {
                chkFotoFlor.setSelected(true);
            } else {
                chkFotoFlor.setSelected(false);
            }
            if (this.ceColecta.getFotoFruto() == 1) {
                chkFotoFruto.setSelected(true);
            } else {
                chkFotoFruto.setSelected(false);
            }
            if (this.ceColecta.getFotoHojasArriba() == 1) {
                chkHojasVistasArriba.setSelected(true);
            } else {
                chkHojasVistasArriba.setSelected(false);
            }
            if (this.ceColecta.getFotoHojasAbajo() == 1) {
                chkHojasVistasAbajo.setSelected(true);
            } else {
                chkHojasVistasAbajo.setSelected(false);
            }
            if (this.ceColecta.getFotoArbolCompleto() == 1) {
                chkFotoArbolCompleto.setSelected(true);
            } else {
                chkFotoArbolCompleto.setSelected(false);
            }
            if (this.ceColecta.getFotoCorteza() == 1) {
                chkFotoCorteza.setSelected(true);
            } else {
                chkFotoCorteza.setSelected(false);
            }
            if (this.ceColecta.getVirutaIncluida() == 1) {
                chkVirutaIncluida.setSelected(true);
            } else {
                chkVirutaIncluida.setSelected(false);
            }
            if (this.ceColecta.getCortezaIncluida() == 1) {
                chkCortezaIncluida.setSelected(true);
            } else {
                chkCortezaIncluida.setSelected(false);
            }
            if (this.ceColecta.getMaderaIncluida() == 1) {
                chkMaderaIncluida.setSelected(true);
            } else {
                chkMaderaIncluida.setSelected(false);
            }
            txtObservacionesColecta.setText(ceColecta.getObservaciones());
        }
    }

    private void limpiarControles(boolean habilitado) {
        if (habilitado == false) {
            chkExterna.setEnabled(false);
            chkExterna.setSelected(false);
            //System.out.println("Limpíar Falso");
            cmbFamilia.setSelectedItem(null);
            cmbGenero.setSelectedItem(null);
            cmbEspecie.setSelectedItem(null);
            cmbInfraespecie.setSelectedItem(null);
            txtNombreComun.setText("");
            cmbSitio.setSelectedItem(null);
            cmbSitio.setEnabled(false);
            cmbSeccion.setSelectedItem(null);
            cmbSeccion.setEnabled(false);
            txtConsecutivo.setText("");
            txtConsecutivo.setValue(null);
            txtConsecutivo.setEnabled(false);
            chkContraFuertes.setSelected(false);
            chkContraFuertes.setEnabled(false);
            chkExudado.setSelected(false);
            chkExudado.setEnabled(false);
            txtExudado.setText("");
            txtExudado.setEnabled(false);
            chkColor.setSelected(false);
            chkColor.setEnabled(false);
            txtColor.setText("");
            txtColor.setEnabled(false);
            chkCambioColor.setSelected(false);
            chkCambioColor.setEnabled(false);
            chkAceitesVolatiles.setSelected(false);
            chkAceitesVolatiles.setEnabled(false);
            chkColoresFlores.setSelected(false);
            chkColoresFlores.setEnabled(false);
            txtColoresFlores.setText("");
            txtColoresFlores.setEnabled(false);
            chkHojas.setSelected(false);
            chkHojas.setEnabled(false);
            chkFotoFlor.setSelected(false);
            chkFotoFlor.setEnabled(false);
            chkFotoFruto.setSelected(false);
            chkFotoFruto.setEnabled(false);
            chkHojasVistasArriba.setSelected(false);
            chkHojasVistasArriba.setEnabled(false);
            chkHojasVistasAbajo.setSelected(false);
            chkHojasVistasAbajo.setEnabled(false);
            chkFotoArbolCompleto.setSelected(false);
            chkFotoArbolCompleto.setEnabled(false);
            chkFotoCorteza.setSelected(false);
            chkFotoCorteza.setEnabled(false);
            chkVirutaIncluida.setSelected(false);
            chkVirutaIncluida.setEnabled(false);
            chkCortezaIncluida.setSelected(false);
            chkCortezaIncluida.setEnabled(false);
            chkMaderaIncluida.setSelected(false);
            chkMaderaIncluida.setEnabled(false);
            txtObservacionesColecta.setText("");
            txtObservacionesColecta.setEnabled(false);
            btnAgregar.setEnabled(false);
        } else {
            chkExterna.setEnabled(true);
            chkExterna.setSelected(false);
            //System.out.println("Limpíar verdaro");
            cmbSitio.setEnabled(true);
            cmbSitio.setSelectedIndex(0);
            cmbSeccion.setEnabled(true);
            cmbSeccion.setSelectedIndex(0);
            txtConsecutivo.setEnabled(true);
            chkContraFuertes.setEnabled(true);
            chkExudado.setEnabled(true);
            txtExudado.setEnabled(false);
            chkColor.setEnabled(true);
            txtColor.setEnabled(false);
            chkCambioColor.setEnabled(true);
            chkAceitesVolatiles.setEnabled(true);
            chkColoresFlores.setEnabled(true);
            txtColoresFlores.setEnabled(true);
            chkHojas.setEnabled(true);
            chkFotoFlor.setEnabled(true);
            chkFotoFruto.setEnabled(true);
            chkHojasVistasArriba.setEnabled(true);
            chkHojasVistasAbajo.setEnabled(true);
            chkFotoArbolCompleto.setEnabled(true);
            chkFotoCorteza.setEnabled(true);
            chkVirutaIncluida.setEnabled(true);
            chkCortezaIncluida.setEnabled(true);
            chkMaderaIncluida.setEnabled(true);
            txtObservacionesColecta.setEnabled(true);
            btnAgregar.setEnabled(true);
        }
    }

    private boolean validarCamposObligatorios() {
        boolean validacion=true;
        if(chkExterna.isSelected()==false){
            if (cmbSitio.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar el número de sitio de origen", "Colecta botánica", JOptionPane.INFORMATION_MESSAGE);
            cmbSitio.requestFocus();
            validacion=false;
        } 
        if (cmbSeccion.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar la sección de origen", "Colecta botánica", JOptionPane.INFORMATION_MESSAGE);
            cmbSeccion.requestFocus();
            validacion=false;
        } 
         if (txtConsecutivo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar el consecutivo de origen", "Colecta botánica", JOptionPane.INFORMATION_MESSAGE);
            txtConsecutivo.requestFocus();
            validacion=false;
        } 
        }
         
         if (chkExudado.isSelected() && txtExudado.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Si selecciona presencia de exudado, debe proporcionar caracteristicas", "Colecta botánica", JOptionPane.INFORMATION_MESSAGE);
            txtExudado.requestFocus();
            validacion=false;
        } 
         if (chkColor.isSelected() && txtColor.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Si selecciona presencia de color, debe proporcionar caracteristicas", "Colecta botánica", JOptionPane.INFORMATION_MESSAGE);
            txtColor.requestFocus();
            validacion=false;
        }
         if (chkColoresFlores.isSelected() && txtColoresFlores.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Si selecciona presencia de colores de flores o frutos, los debe proporcionar", "Colecta botánica", JOptionPane.INFORMATION_MESSAGE);
            txtColoresFlores.requestFocus();
            validacion=false;
        }
        
         return validacion;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblUPM = new javax.swing.JLabel();
        lblSuelo1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        chkColor = new javax.swing.JCheckBox();
        chkCambioColor = new javax.swing.JCheckBox();
        lblColor = new javax.swing.JLabel();
        txtExudado = new javax.swing.JTextField();
        chkExudado = new javax.swing.JCheckBox();
        lblExudado = new javax.swing.JLabel();
        txtColor = new javax.swing.JTextField();
        chkContraFuertes = new javax.swing.JCheckBox();
        chkAceitesVolatiles = new javax.swing.JCheckBox();
        chkColoresFlores = new javax.swing.JCheckBox();
        lblColoresFlores = new javax.swing.JLabel();
        txtColoresFlores = new javax.swing.JTextField();
        chkHojas = new javax.swing.JCheckBox();
        chkFotoFlor = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        chkFotoFruto = new javax.swing.JCheckBox();
        chkHojasVistasArriba = new javax.swing.JCheckBox();
        chkHojasVistasAbajo = new javax.swing.JCheckBox();
        chkFotoArbolCompleto = new javax.swing.JCheckBox();
        chkFotoCorteza = new javax.swing.JCheckBox();
        lblArbolesDiametro1015 = new javax.swing.JLabel();
        chkVirutaIncluida = new javax.swing.JCheckBox();
        chkCortezaIncluida = new javax.swing.JCheckBox();
        chkMaderaIncluida = new javax.swing.JCheckBox();
        lblObservacionesColecta = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObservacionesColecta = new javax.swing.JTextArea();
        btnSalir = new javax.swing.JButton();
        lblPreClave = new javax.swing.JLabel();
        cmbClaveColecta = new javax.swing.JComboBox();
        btnAgregar = new javax.swing.JButton();
        cmbUPMID = new javax.swing.JComboBox();
        lblFamilia = new javax.swing.JLabel();
        cmbFamilia = new javax.swing.JComboBox();
        lblGenero = new javax.swing.JLabel();
        cmbGenero = new javax.swing.JComboBox();
        lblEspecie = new javax.swing.JLabel();
        cmbEspecie = new javax.swing.JComboBox();
        lblInfraespecie = new javax.swing.JLabel();
        lblNombreComun = new javax.swing.JLabel();
        txtNombreComun = new javax.swing.JTextField();
        cmbInfraespecie = new javax.swing.JComboBox();
        cmbSitio = new javax.swing.JComboBox();
        lbSitio = new javax.swing.JLabel();
        lblSeccion = new javax.swing.JLabel();
        cmbSeccion = new javax.swing.JComboBox();
        lbSitio1 = new javax.swing.JLabel();
        txtConsecutivo = new javax.swing.JFormattedTextField();
        chkExterna = new javax.swing.JCheckBox();

        setMaximizable(true);
        setTitle("Registro de colecta botánica "+version);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/gob/conafor/utils/logo_conafor.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(940, 650));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        lblUPM.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblUPM.setText("UPMID:");

        lblSuelo1.setBackground(new java.awt.Color(153, 153, 153));
        lblSuelo1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblSuelo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSuelo1.setText("Colecta botánica");
        lblSuelo1.setOpaque(true);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        chkColor.setBackground(new java.awt.Color(204, 204, 204));
        chkColor.setText("¿Color?");
        chkColor.setEnabled(false);
        chkColor.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkColorActionPerformed(evt);
            }
        });
        chkColor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                chkColorKeyPressed(evt);
            }
        });

        chkCambioColor.setBackground(new java.awt.Color(204, 204, 204));
        chkCambioColor.setText("¿Color cambia con el aire?");
        chkCambioColor.setEnabled(false);
        chkCambioColor.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkCambioColor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                chkCambioColorKeyPressed(evt);
            }
        });

        lblColor.setText("Indicar:");

        txtExudado.setEnabled(false);

        chkExudado.setBackground(new java.awt.Color(204, 204, 204));
        chkExudado.setText("¿Presencia de exudado?");
        chkExudado.setEnabled(false);
        chkExudado.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkExudado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkExudadoActionPerformed(evt);
            }
        });
        chkExudado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                chkExudadoKeyPressed(evt);
            }
        });

        lblExudado.setText("Indicar:");

        txtColor.setEnabled(false);

        chkContraFuertes.setBackground(new java.awt.Color(204, 204, 204));
        chkContraFuertes.setText("¿Presencia de contrafuertes?");
        chkContraFuertes.setEnabled(false);
        chkContraFuertes.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkContraFuertes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                chkContraFuertesKeyPressed(evt);
            }
        });

        chkAceitesVolatiles.setBackground(new java.awt.Color(204, 204, 204));
        chkAceitesVolatiles.setText("¿Aceites volátiles presentes en hojas o corteza?");
        chkAceitesVolatiles.setEnabled(false);
        chkAceitesVolatiles.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAceitesVolatiles.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                chkAceitesVolatilesKeyPressed(evt);
            }
        });

        chkColoresFlores.setBackground(new java.awt.Color(204, 204, 204));
        chkColoresFlores.setText("¿Colores de flores y/o frutos o conos?");
        chkColoresFlores.setEnabled(false);
        chkColoresFlores.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkColoresFlores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkColoresFloresActionPerformed(evt);
            }
        });
        chkColoresFlores.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                chkColoresFloresKeyPressed(evt);
            }
        });

        lblColoresFlores.setText("Indicar:");

        txtColoresFlores.setEnabled(false);

        chkHojas.setBackground(new java.awt.Color(204, 204, 204));
        chkHojas.setText("¿Hojas u otros tejidos vivos en gel de silice incluido?");
        chkHojas.setEnabled(false);
        chkHojas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkHojas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                chkHojasKeyPressed(evt);
            }
        });

        chkFotoFlor.setBackground(new java.awt.Color(204, 204, 204));
        chkFotoFlor.setText("¿Se tomó foto de flor?");
        chkFotoFlor.setEnabled(false);
        chkFotoFlor.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkFotoFlor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                chkFotoFlorKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkContraFuertes)
                            .addComponent(chkExudado)
                            .addComponent(chkColor))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(lblExudado)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtExudado, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(lblColor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtColor, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(chkHojas, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkFotoFlor, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkCambioColor, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkAceitesVolatiles, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(chkColoresFlores)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                        .addComponent(lblColoresFlores)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtColoresFlores, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(chkContraFuertes)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtExudado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblExudado))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblColor)
                            .addComponent(txtColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(chkExudado)
                        .addGap(18, 18, 18)
                        .addComponent(chkColor)))
                .addGap(20, 20, 20)
                .addComponent(chkCambioColor)
                .addGap(18, 18, 18)
                .addComponent(chkAceitesVolatiles)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkColoresFlores)
                    .addComponent(lblColoresFlores)
                    .addComponent(txtColoresFlores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(chkHojas)
                .addGap(18, 18, 18)
                .addComponent(chkFotoFlor)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        chkFotoFruto.setBackground(new java.awt.Color(204, 204, 204));
        chkFotoFruto.setText("¿Se tomó foto de fruto?");
        chkFotoFruto.setEnabled(false);
        chkFotoFruto.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkFotoFruto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                chkFotoFrutoKeyPressed(evt);
            }
        });

        chkHojasVistasArriba.setBackground(new java.awt.Color(204, 204, 204));
        chkHojasVistasArriba.setText("¿Se tomó foto de hojas vistas desde arriba?");
        chkHojasVistasArriba.setEnabled(false);
        chkHojasVistasArriba.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkHojasVistasArriba.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                chkHojasVistasArribaKeyPressed(evt);
            }
        });

        chkHojasVistasAbajo.setBackground(new java.awt.Color(204, 204, 204));
        chkHojasVistasAbajo.setText("¿Se tomó foto de hojas vistas desde abajo?");
        chkHojasVistasAbajo.setEnabled(false);
        chkHojasVistasAbajo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkHojasVistasAbajo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                chkHojasVistasAbajoKeyPressed(evt);
            }
        });

        chkFotoArbolCompleto.setBackground(new java.awt.Color(204, 204, 204));
        chkFotoArbolCompleto.setText("¿Se tomó foto del árbol completo?");
        chkFotoArbolCompleto.setEnabled(false);
        chkFotoArbolCompleto.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkFotoArbolCompleto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                chkFotoArbolCompletoKeyPressed(evt);
            }
        });

        chkFotoCorteza.setBackground(new java.awt.Color(204, 204, 204));
        chkFotoCorteza.setText("¿Se tomó foto de la corteza?");
        chkFotoCorteza.setEnabled(false);
        chkFotoCorteza.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkFotoCorteza.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                chkFotoCortezaKeyPressed(evt);
            }
        });

        lblArbolesDiametro1015.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblArbolesDiametro1015.setText("Para árboles con diámetro mayor a 10-15");

        chkVirutaIncluida.setBackground(new java.awt.Color(204, 204, 204));
        chkVirutaIncluida.setText("¿Viruta incluida?");
        chkVirutaIncluida.setEnabled(false);
        chkVirutaIncluida.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkVirutaIncluida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                chkVirutaIncluidaKeyPressed(evt);
            }
        });

        chkCortezaIncluida.setBackground(new java.awt.Color(204, 204, 204));
        chkCortezaIncluida.setText("En caso de corteza fértil ¿Corteza incluida?");
        chkCortezaIncluida.setEnabled(false);
        chkCortezaIncluida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                chkCortezaIncluidaKeyPressed(evt);
            }
        });

        chkMaderaIncluida.setBackground(new java.awt.Color(204, 204, 204));
        chkMaderaIncluida.setText("En caso de corteza fértil ¿Madera incluida?");
        chkMaderaIncluida.setEnabled(false);
        chkMaderaIncluida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                chkMaderaIncluidaKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkCortezaIncluida)
                    .addComponent(chkVirutaIncluida)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(chkFotoCorteza)
                        .addComponent(chkFotoArbolCompleto)
                        .addComponent(chkHojasVistasAbajo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(chkHojasVistasArriba, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(chkFotoFruto)
                        .addComponent(lblArbolesDiametro1015, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(chkMaderaIncluida))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chkFotoFruto)
                .addGap(18, 18, 18)
                .addComponent(chkHojasVistasArriba)
                .addGap(18, 18, 18)
                .addComponent(chkHojasVistasAbajo)
                .addGap(18, 18, 18)
                .addComponent(chkFotoArbolCompleto)
                .addGap(18, 18, 18)
                .addComponent(chkFotoCorteza)
                .addGap(18, 18, 18)
                .addComponent(lblArbolesDiametro1015)
                .addGap(7, 7, 7)
                .addComponent(chkVirutaIncluida)
                .addGap(18, 18, 18)
                .addComponent(chkCortezaIncluida)
                .addGap(18, 18, 18)
                .addComponent(chkMaderaIncluida)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        lblObservacionesColecta.setText("Observaciones (características del sitio):");

        txtObservacionesColecta.setColumns(20);
        txtObservacionesColecta.setRows(5);
        txtObservacionesColecta.setEnabled(false);
        txtObservacionesColecta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtObservacionesColectaKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(txtObservacionesColecta);

        btnSalir.setMnemonic('s');
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        lblPreClave.setText("Clave de colecta:");

        cmbClaveColecta.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "001_160525_EOC1_F", "001_160525_EOC2_F", "001_160525_EOC3_F", "001_160525_EOC4_F", "001_160525_EOC5_F" }));
        cmbClaveColecta.setEnabled(false);
        cmbClaveColecta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbClaveColectaActionPerformed(evt);
            }
        });

        btnAgregar.setMnemonic('a');
        btnAgregar.setText("Agregar");
        btnAgregar.setEnabled(false);
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        cmbUPMID.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "1", "10", "140", "300" }));
        cmbUPMID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbUPMIDActionPerformed(evt);
            }
        });

        lblFamilia.setText("Familia:");

        cmbFamilia.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cmbFamilia.setEnabled(false);

        lblGenero.setText("Género:");

        cmbGenero.setEnabled(false);
        cmbGenero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbGeneroActionPerformed(evt);
            }
        });

        lblEspecie.setText("Especie:");

        cmbEspecie.setEnabled(false);
        cmbEspecie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEspecieActionPerformed(evt);
            }
        });

        lblInfraespecie.setText("Infraespecie:");

        lblNombreComun.setText("Nombre común:");

        txtNombreComun.setEnabled(false);
        txtNombreComun.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNombreComunFocusGained(evt);
            }
        });

        cmbInfraespecie.setEnabled(false);

        cmbSitio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        cmbSitio.setEnabled(false);
        cmbSitio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSitioActionPerformed(evt);
            }
        });

        lbSitio.setText("Sitio:");

        lblSeccion.setText("Sección:");

        cmbSeccion.setEnabled(false);

        lbSitio1.setText("Consecutivo:");
        lbSitio1.setToolTipText("");

        txtConsecutivo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtConsecutivo.setEnabled(false);

        chkExterna.setBackground(new java.awt.Color(204, 204, 204));
        chkExterna.setText("Externa?");
        chkExterna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkExternaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(255, 255, 255)
                        .addComponent(lblUPM)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbUPMID, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(lblPreClave)
                        .addGap(2, 2, 2)
                        .addComponent(cmbClaveColecta, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSuelo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblFamilia)
                                    .addComponent(lbSitio))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(cmbFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(17, 17, 17)
                                        .addComponent(lblGenero)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(lblEspecie)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblInfraespecie)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbInfraespecie, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblNombreComun))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(cmbSitio, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(chkExterna)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblSeccion)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cmbSeccion, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lbSitio1)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNombreComun, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                                    .addComponent(txtConsecutivo))))
                        .addContainerGap())
                    .addComponent(lblObservacionesColecta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPreClave)
                    .addComponent(cmbClaveColecta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUPM)
                    .addComponent(cmbUPMID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSuelo1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFamilia)
                    .addComponent(cmbFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGenero)
                    .addComponent(cmbGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEspecie)
                    .addComponent(cmbEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblInfraespecie)
                    .addComponent(lblNombreComun)
                    .addComponent(txtNombreComun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbInfraespecie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbSitio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbSitio)
                    .addComponent(cmbSeccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSeccion)
                    .addComponent(lbSitio1)
                    .addComponent(txtConsecutivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkExterna))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblObservacionesColecta, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalir)
                    .addComponent(btnAgregar))
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

    private void txtObservacionesColectaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtObservacionesColectaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            evt.consume();
        }
    }//GEN-LAST:event_txtObservacionesColectaKeyPressed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.hide();
        funciones.manipularBotonesMenuPrincipal(false);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtNombreComunFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombreComunFocusGained
        txtNombreComun.selectAll();
    }//GEN-LAST:event_txtNombreComunFocusGained

    private void cmbClaveColectaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbClaveColectaActionPerformed
        if (cmbClaveColecta.getSelectedItem() == null) {
            limpiarControles(false);
        } else {
            String claveColecta = (String) cmbClaveColecta.getSelectedItem();
            limpiarControles(true);
            fijarValoresCampos(claveColecta);
        }
    }//GEN-LAST:event_cmbClaveColectaActionPerformed

    private void cmbUPMIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbUPMIDActionPerformed
        cmbSitio.removeAllItems();
        if (cmbUPMID.getSelectedItem() == null) {
            cmbClaveColecta.setSelectedItem(null);
            cmbClaveColecta.setEnabled(false);
            funciones.reiniciarComboModel(cmbSitio);
        } else {
            Integer upmID = (Integer) cmbUPMID.getSelectedItem();
            funciones.reiniciarComboModel(cmbClaveColecta);
            fillCmbClaveColecta(upmID);
            cmbClaveColecta.setEnabled(true);
            fillCmbSitio(upmID);
        }
    }//GEN-LAST:event_cmbUPMIDActionPerformed

    private void chkExudadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkExudadoActionPerformed
        if (chkExudado.isSelected()) {
            txtExudado.setEnabled(true);
        } else {
            txtExudado.setText("");
            txtExudado.setEnabled(false);
        }
    }//GEN-LAST:event_chkExudadoActionPerformed

    private void chkColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkColorActionPerformed
        if (chkColor.isSelected()) {
            txtColor.setEnabled(true);
        } else {
            txtColor.setText("");
            txtColor.setEnabled(false);
        }
    }//GEN-LAST:event_chkColorActionPerformed

    private void chkColoresFloresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkColoresFloresActionPerformed
        if (chkColoresFlores.isSelected()) {
            txtColoresFlores.setEnabled(true);
        } else {
            txtColoresFlores.setText("");
            txtColoresFlores.setEnabled(false);
        }
    }//GEN-LAST:event_chkColoresFloresActionPerformed

    private void cmbGeneroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbGeneroActionPerformed
        CatEGenero indexGenero = (CatEGenero) cmbGenero.getSelectedItem();
        CatEFamiliaEspecie indexFamilia = (CatEFamiliaEspecie) cmbFamilia.getSelectedItem();
        DefaultComboBoxModel dcm = (DefaultComboBoxModel) cmbEspecie.getModel();
        dcm.removeAllElements();
        CatEFamiliaEspecie familia;
        if (indexFamilia == null && indexGenero != null) {
            familia = this.cdEspecies.getFamilia(indexGenero.getGeneroID());
            cmbFamilia.setSelectedItem(familia);
        }
        if (indexGenero != null) {
            fillCmbEspecie(indexGenero.getGeneroID());
        }
    }//GEN-LAST:event_cmbGeneroActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        fijarValoresColecta();
        if (validarCamposObligatorios()) {
            guardarDatosColecta();
            limpiarControles(false);
            cmbUPMID.requestFocus();
            cmbUPMID.setSelectedItem(null);
            funciones.reiniciarComboModel(cmbClaveColecta);
            cmbClaveColecta.setSelectedItem(null);
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void chkContraFuertesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chkContraFuertesKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (chkContraFuertes.isSelected()) {
                chkContraFuertes.setSelected(false);
            } else {
                chkContraFuertes.setSelected(true);
            }
        }
    }//GEN-LAST:event_chkContraFuertesKeyPressed

    private void chkExudadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chkExudadoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (chkExudado.isSelected()) {
                chkExudado.setSelected(false);
                txtExudado.setText("");
                txtExudado.setEnabled(false);
            } else {
                chkExudado.setSelected(true);
                txtExudado.setEnabled(true);
            }
        }
    }//GEN-LAST:event_chkExudadoKeyPressed

    private void chkColorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chkColorKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (chkColor.isSelected()) {
                chkColor.setSelected(false);
                txtColor.setText("");
                txtColor.setEnabled(false);
            } else {
                chkColor.setSelected(true);
                txtColor.setEnabled(true);
            }
        }
    }//GEN-LAST:event_chkColorKeyPressed

    private void chkCambioColorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chkCambioColorKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (chkCambioColor.isSelected()) {
                chkCambioColor.setSelected(false);
            } else {
                chkCambioColor.setSelected(true);
            }
        }
    }//GEN-LAST:event_chkCambioColorKeyPressed

    private void chkAceitesVolatilesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chkAceitesVolatilesKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (chkAceitesVolatiles.isSelected()) {
                chkAceitesVolatiles.setSelected(false);
            } else {
                chkAceitesVolatiles.setSelected(true);
            }
        }
    }//GEN-LAST:event_chkAceitesVolatilesKeyPressed

    private void chkColoresFloresKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chkColoresFloresKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (chkColoresFlores.isSelected()) {
                chkColoresFlores.setSelected(false);
                txtColoresFlores.setText("");
                txtColoresFlores.setEnabled(false);
            } else {
                chkColoresFlores.setSelected(true);
                txtColoresFlores.setEnabled(true);
            }
        }
    }//GEN-LAST:event_chkColoresFloresKeyPressed

    private void chkHojasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chkHojasKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (chkHojas.isSelected()) {
                chkHojas.setSelected(false);
            } else {
                chkHojas.setSelected(true);
            }
        }
    }//GEN-LAST:event_chkHojasKeyPressed

    private void chkFotoFlorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chkFotoFlorKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (chkFotoFlor.isSelected()) {
                chkFotoFlor.setSelected(false);
            } else {
                chkFotoFlor.setSelected(true);
            }
        }
    }//GEN-LAST:event_chkFotoFlorKeyPressed

    private void chkFotoFrutoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chkFotoFrutoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (chkFotoFruto.isSelected()) {
                chkFotoFruto.setSelected(false);
            } else {
                chkFotoFruto.setSelected(true);
            }
        }
    }//GEN-LAST:event_chkFotoFrutoKeyPressed

    private void chkHojasVistasArribaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chkHojasVistasArribaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (chkHojasVistasArriba.isSelected()) {
                chkHojasVistasArriba.setSelected(false);
            } else {
                chkHojasVistasArriba.setSelected(true);
            }
        }
    }//GEN-LAST:event_chkHojasVistasArribaKeyPressed

    private void chkHojasVistasAbajoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chkHojasVistasAbajoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (chkHojasVistasAbajo.isSelected()) {
                chkHojasVistasAbajo.setSelected(false);
            } else {
                chkHojasVistasAbajo.setSelected(true);
            }
        }
    }//GEN-LAST:event_chkHojasVistasAbajoKeyPressed

    private void chkFotoArbolCompletoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chkFotoArbolCompletoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (chkFotoArbolCompleto.isSelected()) {
                chkFotoArbolCompleto.setSelected(false);
            } else {
                chkFotoArbolCompleto.setSelected(true);
            }
        }
    }//GEN-LAST:event_chkFotoArbolCompletoKeyPressed

    private void chkFotoCortezaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chkFotoCortezaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (chkFotoCorteza.isSelected()) {
                chkFotoCorteza.setSelected(false);
            } else {
                chkFotoCorteza.setSelected(true);
            }
        }
    }//GEN-LAST:event_chkFotoCortezaKeyPressed

    private void chkVirutaIncluidaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chkVirutaIncluidaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (chkVirutaIncluida.isSelected()) {
                chkVirutaIncluida.setSelected(false);
            } else {
                chkVirutaIncluida.setSelected(true);
            }
        }
    }//GEN-LAST:event_chkVirutaIncluidaKeyPressed

    private void chkMaderaIncluidaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chkMaderaIncluidaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (chkMaderaIncluida.isSelected()) {
                chkMaderaIncluida.setSelected(false);
            } else {
                chkMaderaIncluida.setSelected(true);
            }
        }
    }//GEN-LAST:event_chkMaderaIncluidaKeyPressed

    private void chkCortezaIncluidaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chkCortezaIncluidaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (chkCortezaIncluida.isSelected()) {
                chkCortezaIncluida.setSelected(false);
            } else {
                chkCortezaIncluida.setSelected(true);
            }
        }
    }//GEN-LAST:event_chkCortezaIncluidaKeyPressed

    private void cmbEspecieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEspecieActionPerformed
        CatEEspecie indexEspecie = (CatEEspecie) cmbEspecie.getSelectedItem();
        DefaultComboBoxModel dcm = (DefaultComboBoxModel) cmbInfraespecie.getModel();
        dcm.removeAllElements();
        if (indexEspecie != null) {
            fillCmbInfraespecie(indexEspecie.getEspecieID());
        }
    }//GEN-LAST:event_cmbEspecieActionPerformed

    private void chkExternaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkExternaActionPerformed
        if (chkExterna.isSelected()) {//SI ES EXTERNA
            cmbSitio.setEnabled(false);
            cmbSitio.setSelectedItem(null);
            cmbSeccion.setEnabled(false);
            cmbSeccion.setSelectedItem(null);
            txtConsecutivo.setEnabled(false);
            txtConsecutivo.setText("0");
        } else {
            cmbSitio.setEnabled(true);
            //cmbSitio.setSelectedItem(null);
            cmbSeccion.setEnabled(true);
            //cmbSeccion.setSelectedItem(null);
            txtConsecutivo.setEnabled(true);
        }
    }//GEN-LAST:event_chkExternaActionPerformed

    private void cmbSitioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSitioActionPerformed

        if (cmbSitio.getSelectedItem() != null) {
            chkExterna.setEnabled(false);
        } else {
            chkExterna.setEnabled(true);
        }
        if (cmbClaveColecta.getSelectedItem() == null) {
            chkExterna.setEnabled(false);
        }
    }//GEN-LAST:event_cmbSitioActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JCheckBox chkAceitesVolatiles;
    private javax.swing.JCheckBox chkCambioColor;
    private javax.swing.JCheckBox chkColor;
    private javax.swing.JCheckBox chkColoresFlores;
    private javax.swing.JCheckBox chkContraFuertes;
    private javax.swing.JCheckBox chkCortezaIncluida;
    private javax.swing.JCheckBox chkExterna;
    private javax.swing.JCheckBox chkExudado;
    private javax.swing.JCheckBox chkFotoArbolCompleto;
    private javax.swing.JCheckBox chkFotoCorteza;
    private javax.swing.JCheckBox chkFotoFlor;
    private javax.swing.JCheckBox chkFotoFruto;
    private javax.swing.JCheckBox chkHojas;
    private javax.swing.JCheckBox chkHojasVistasAbajo;
    private javax.swing.JCheckBox chkHojasVistasArriba;
    private javax.swing.JCheckBox chkMaderaIncluida;
    private javax.swing.JCheckBox chkVirutaIncluida;
    private javax.swing.JComboBox cmbClaveColecta;
    private javax.swing.JComboBox cmbEspecie;
    private javax.swing.JComboBox cmbFamilia;
    private javax.swing.JComboBox cmbGenero;
    private javax.swing.JComboBox cmbInfraespecie;
    private javax.swing.JComboBox cmbSeccion;
    private javax.swing.JComboBox cmbSitio;
    private javax.swing.JComboBox cmbUPMID;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbSitio;
    private javax.swing.JLabel lbSitio1;
    private javax.swing.JLabel lblArbolesDiametro1015;
    private javax.swing.JLabel lblColor;
    private javax.swing.JLabel lblColoresFlores;
    private javax.swing.JLabel lblEspecie;
    private javax.swing.JLabel lblExudado;
    private javax.swing.JLabel lblFamilia;
    private javax.swing.JLabel lblGenero;
    private javax.swing.JLabel lblInfraespecie;
    private javax.swing.JLabel lblNombreComun;
    private javax.swing.JLabel lblObservacionesColecta;
    private javax.swing.JLabel lblPreClave;
    private javax.swing.JLabel lblSeccion;
    private javax.swing.JLabel lblSuelo1;
    private javax.swing.JLabel lblUPM;
    private javax.swing.JTextField txtColor;
    private javax.swing.JTextField txtColoresFlores;
    private javax.swing.JFormattedTextField txtConsecutivo;
    private javax.swing.JTextField txtExudado;
    private javax.swing.JTextField txtNombreComun;
    private javax.swing.JTextArea txtObservacionesColecta;
    // End of variables declaration//GEN-END:variables
}
