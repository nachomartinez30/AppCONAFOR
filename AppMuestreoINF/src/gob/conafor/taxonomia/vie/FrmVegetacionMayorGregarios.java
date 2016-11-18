package gob.conafor.taxonomia.vie;

import gob.conafor.conn.dat.LocalConnection;
import gob.conafor.ini.vie.Main;
import gob.conafor.sitio.mod.CESitio;
import gob.conafor.sys.mod.CDSecuencia;
import gob.conafor.sys.mod.CDSeguimientoUPM;
import gob.conafor.sys.mod.CESeccionesCapturadas;
import gob.conafor.taxonomia.mod.CDColectaBotanica;
import gob.conafor.taxonomia.mod.CDCondicionTaxonomica;
import gob.conafor.taxonomia.mod.CDDanioVegetacionMayorG;
import gob.conafor.taxonomia.mod.CDEspecies;
import gob.conafor.taxonomia.mod.CDVegetacionMayorGregarios;
import gob.conafor.taxonomia.mod.CEColectaBotanica;
import gob.conafor.taxonomia.mod.CEDanioSeveridad;
import gob.conafor.taxonomia.mod.CEVegetacionMayorGregarios;
import gob.conafor.taxonomia.mod.CatEAgenteDanio;
import gob.conafor.taxonomia.mod.CatECondicionVM;
import gob.conafor.taxonomia.mod.CatEDensidadColonia;
import gob.conafor.taxonomia.mod.CatEEspecie;
import gob.conafor.taxonomia.mod.CatEFamiliaEspecie;
import gob.conafor.taxonomia.mod.CatEFormaCrecimiento;
import gob.conafor.taxonomia.mod.CatEFormaVidaZA;
import gob.conafor.taxonomia.mod.CatEGenero;
import gob.conafor.taxonomia.mod.CatEInfraespecie;
import gob.conafor.taxonomia.mod.CatESeveridadZA;
import gob.conafor.taxonomia.mod.CatEVigorArbolado;
import gob.conafor.utils.Datos;
import gob.conafor.utils.FuncionesComunes;
import gob.conafor.utils.Tablas;
import gob.conafor.utils.ValidacionesComunes;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class FrmVegetacionMayorGregarios extends javax.swing.JInternalFrame {

    private int upmID;
    private int sitioID;
    private int sitio;
    private static final int FORMATO_ID = 23;
    private CDEspecies especie = new CDEspecies();
    private CDCondicionTaxonomica condicion = new CDCondicionTaxonomica();
    private Integer consecutivo;
    private Integer noIndividuo;
    private String infraespecie;
    private String nombreComun;
    private Float alturaTotalMaxima;
    private Float alturaTotalMedia;
    private Float alturaTotalMinima;
    private Float diametroCoberturaMayor;
    private Float diametroCoberturaMenor;
    private int vegetacionMayorID;
    private CDVegetacionMayorGregarios cdVegetacionMayor = new CDVegetacionMayorGregarios();
    private CESitio ceSitio;
    private CESeccionesCapturadas seccionActual = new CESeccionesCapturadas();
    private CESeccionesCapturadas seccionSiguiente = new CESeccionesCapturadas();
    private CDSeguimientoUPM seguimiento = new CDSeguimientoUPM();
    private FuncionesComunes combo = new FuncionesComunes();
    private Datos numeros = new Datos();
    private CDEspecies cdEspecies = new CDEspecies();
    private CDSecuencia cdSecuencia = new CDSecuencia();
    private FuncionesComunes funciones = new FuncionesComunes();

    public FrmVegetacionMayorGregarios() {
        initComponents();
        fillCmbCondicionVegetacionMayor();
        fillCmbFamilia();
        fillCmbGenero();
        fillCmbFormaVida();
        fillCmbFormaCrecimiento();
        fillCmbDensidadColonial();
        fillCmbAgenteDanio1();
        fillCmbSeveridad1();
        fillCmbAgenteDanio2();
        fillCmbSeveridad2();
        fillCmbVigor();
        cmbFormaVida.requestFocus();
    }

    public void setDatosIniciales(CESitio sitio) {
        this.upmID = sitio.getUpmID();
        this.sitioID = sitio.getSitioID();
        this.sitio = sitio.getSitio();
        txtUPM.setText(String.valueOf(this.upmID));
        txtSitio.setText(String.valueOf(this.sitio));
        this.ceSitio = sitio;
        llenarTabla();
        cmbFormaVida.requestFocus();
        this.ceSitio = sitio;
        fillCmbConsecutivo();
        funciones.manipularBotonesMenuPrincipal(true);
        limpiarControles();
    }

    public void revisarVegetacionMayorGregarios(CESitio sitio) {
        this.upmID = sitio.getUpmID();
        this.sitioID = sitio.getSitioID();
        this.sitio = sitio.getSitio();
        txtUPM.setText(String.valueOf(this.upmID));
        txtSitio.setText(String.valueOf(this.sitio));
        this.ceSitio = sitio;
        llenarTabla();
        cmbFormaVida.requestFocus();
        fillCmbConsecutivo();
        txtNumeroIndividuo.setEnabled(true);
        funciones.manipularBotonesMenuPrincipal(true);
        chkVegetacionMayorMCG.setSelected(funciones.habilitarCheckBox("TAXONOMIA_VegetacionMayorGregarios", this.sitioID));
    }

    private void fillCmbConsecutivo() {
        List<Integer> listConsecutivo = new ArrayList<>();
        listConsecutivo = cdVegetacionMayor.getConsecutivo(this.sitioID);
        if (listConsecutivo != null) {
            int size = listConsecutivo.size();
            for (int i = 0; i < size; i++) {
                cmbConsecutivo.addItem(listConsecutivo.get(i));
            }
        }
    }
    
     private void fillCmbFormaVida() {
        List<CatEFormaVidaZA> listFormaVida = new ArrayList<>();
        CDCondicionTaxonomica ct = new CDCondicionTaxonomica();
        listFormaVida = ct.getFormaVidaVM();
        if (listFormaVida != null) {
            int size = listFormaVida.size();
            for (int i = 0; i < size; i++) {
                cmbFormaVida.addItem(listFormaVida.get(i));
            }
        }
    }
     
      private void fillCmbCondicionVegetacionMayor() {
         List<CatECondicionVM> listCondicionVM = new ArrayList<>();
        listCondicionVM = condicion.getCondicionVM();
        if (listCondicionVM != null) {
            int size = listCondicionVM.size();
            for (int i = 0; i < size; i++) {
                cmbCondicion.addItem(listCondicionVM.get(i));
            }
        }
    }

    private void fillCmbFamilia() {
        List<CatEFamiliaEspecie> listFamilia = new ArrayList<>();
        listFamilia = especie.getFamiliaEspecies();
        if (listFamilia != null) {
            int size = listFamilia.size();
            for (int i = 0; i < size; i++) {
                cmbFamilia.addItem(listFamilia.get(i));
            }
        }
    }

    private void fillCmbGenero() {
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
    
    private void fillCmbGeneroSF(){
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

    private void fillCmbEspecie(int index) {
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
    
    private void fillCmbInfraespecie(int index){
        List<CatEInfraespecie> listInfraespecie = new ArrayList<>();
        CDEspecies sp = new CDEspecies();
        listInfraespecie = sp.getInfraespecie(index);
        if(listInfraespecie != null){
            int size = listInfraespecie.size();
            for(int i = 0; i < size; i++){
                cmbInfraespecie.addItem(listInfraespecie.get(i));
            }
        }
    }

    private void fillCmbFormaCrecimiento() {
        List<CatEFormaCrecimiento> listFormaCrecimiento = new ArrayList<>();
        listFormaCrecimiento = condicion.getFormaCrecimiento();
        if (listFormaCrecimiento != null) {
            int size = listFormaCrecimiento.size();
            for (int i = 0; i < size; i++) {
                cmbFormaCrecimiento.addItem(listFormaCrecimiento.get(i));
            }
        }
    }

    private void fillCmbDensidadColonial(){
        List<CatEDensidadColonia> listDensidadColonial = new ArrayList<>();
        listDensidadColonial = condicion.getDensidadColonia();
        
        if(listDensidadColonial != null){
            int size = listDensidadColonial.size();
            for(int i = 0; i < size; i++){
                cmbDensidadColonia.addItem(listDensidadColonial.get(i));
            }
        }
    }

    private void fillCmbAgenteDanio1() {
        List<CatEAgenteDanio> listAgenteDanio = new ArrayList<>();
        listAgenteDanio = condicion.getAgenteDanio();

        if (listAgenteDanio != null) {
            int size = listAgenteDanio.size();
            for (int i = 0; i < size; i++) {
                cmbAgenteDanio1.addItem(listAgenteDanio.get(i));
            }
        }
    }

    private void fillCmbAgenteDanio2() {
        List<CatEAgenteDanio> listAgenteDanio = new ArrayList<>();
        listAgenteDanio = condicion.getAgenteDanio();

        if (listAgenteDanio != null) {
            int size = listAgenteDanio.size();
            for (int i = 0; i < size; i++) {
                cmbAgenteDanio2.addItem(listAgenteDanio.get(i));
            }
        }
    }

    public void fillCmbSeveridad1() {
       List<CatESeveridadZA> listSeveridad = new ArrayList<>();
       listSeveridad = condicion.getSeveridadZA();
       
       if(listSeveridad != null){
           int size = listSeveridad.size();
           for(int i =0; i < size; i ++){
               cmbSeveridad1.addItem(listSeveridad.get(i));
           }
       }
    }

    public void fillCmbSeveridad2() {
      List<CatESeveridadZA> listSeveridad = new ArrayList<>();
       listSeveridad = condicion.getSeveridadZA();
       
       if(listSeveridad != null){
           int size = listSeveridad.size();
           for(int i =0; i < size; i ++){
               cmbSeveridad2.addItem(listSeveridad.get(i));
           }
       }
    }
    
   public void fillCmbVigor() {
        List<CatEVigorArbolado> listVigor = new ArrayList<>();
        listVigor = condicion.getVigorArbolado();
        if (listVigor != null) {
            int size = listVigor.size();
            for (int i = 0; i < size; i++) {
                cmbVigor.addItem(listVigor.get(i));
            }
        }
    }

    public void llenarTabla() {
        grdVegetacionvMayor.setModel(cdVegetacionMayor.getTablaVegetacionMayor(this.sitioID));

        grdVegetacionvMayor.getColumnModel().getColumn(2).setPreferredWidth(70);//Consecutivo
        grdVegetacionvMayor.getColumnModel().getColumn(3).setPreferredWidth(60);//individuo
        grdVegetacionvMayor.getColumnModel().getColumn(4).setPreferredWidth(350);//Forma vida
        grdVegetacionvMayor.getColumnModel().getColumn(5).setPreferredWidth(130);//Condicion
        grdVegetacionvMayor.getColumnModel().getColumn(6).setPreferredWidth(120);//Familia
        grdVegetacionvMayor.getColumnModel().getColumn(7).setPreferredWidth(120);//Genero
        grdVegetacionvMayor.getColumnModel().getColumn(8).setPreferredWidth(120);//Especie
        grdVegetacionvMayor.getColumnModel().getColumn(9).setPreferredWidth(120);//Infraespecie
        grdVegetacionvMayor.getColumnModel().getColumn(10).setPreferredWidth(120);//Nombre comun
        grdVegetacionvMayor.getColumnModel().getColumn(11).setPreferredWidth(180);//Forma crecimiento
        grdVegetacionvMayor.getColumnModel().getColumn(12).setPreferredWidth(100);//Densidad colonia
        grdVegetacionvMayor.getColumnModel().getColumn(13).setPreferredWidth(170);//Altura total maxima
        grdVegetacionvMayor.getColumnModel().getColumn(14).setPreferredWidth(170);//Altura total media
        grdVegetacionvMayor.getColumnModel().getColumn(15).setPreferredWidth(170);//Altura total minima
        grdVegetacionvMayor.getColumnModel().getColumn(16).setPreferredWidth(155);//Diametro cobertura mayor
        grdVegetacionvMayor.getColumnModel().getColumn(17).setPreferredWidth(155);//Diametro cobertura menor
        grdVegetacionvMayor.getColumnModel().getColumn(18).setPreferredWidth(85);//Agente 1
        grdVegetacionvMayor.getColumnModel().getColumn(19).setPreferredWidth(110);//Severidad 1
        grdVegetacionvMayor.getColumnModel().getColumn(20).setPreferredWidth(85);//Agente 2
        grdVegetacionvMayor.getColumnModel().getColumn(21).setPreferredWidth(110);//Severidad 2
        grdVegetacionvMayor.getColumnModel().getColumn(22).setPreferredWidth(100); //Vigor
        grdVegetacionvMayor.getColumnModel().getColumn(23).setPreferredWidth(180);//Clave de colecta

        Tablas tabla = new Tablas();
        int[] columna_0 = {0};
        int[] columna_1 = {1};
        tabla.hideColumnTable(grdVegetacionvMayor, columna_0);
        tabla.hideColumnTable(grdVegetacionvMayor, columna_1);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblUPM = new javax.swing.JLabel();
        txtUPM = new javax.swing.JTextField();
        lblSitio = new javax.swing.JLabel();
        txtSitio = new javax.swing.JTextField();
        lblVegetacionMayorGragarios = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblNumeroIndividuo = new javax.swing.JLabel();
        lblConsecutivo = new javax.swing.JLabel();
        cmbConsecutivo = new javax.swing.JComboBox();
        txtNumeroIndividuo = new javax.swing.JFormattedTextField();
        lblFormaVida = new javax.swing.JLabel();
        cmbFormaVida = new javax.swing.JComboBox();
        cmbCondicion = new javax.swing.JComboBox();
        lblCondicion = new javax.swing.JLabel();
        lblFamilia = new javax.swing.JLabel();
        cmbFamilia = new javax.swing.JComboBox();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        lblGenero = new javax.swing.JLabel();
        cmbGenero = new javax.swing.JComboBox();
        lblEspecie = new javax.swing.JLabel();
        cmbEspecie = new javax.swing.JComboBox();
        lblInfraespecie = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        lblAlturaTotalMinima = new javax.swing.JLabel();
        txtAlturaTotalMinima = new javax.swing.JFormattedTextField();
        lblAlturaTotalMaxima = new javax.swing.JLabel();
        txtAlturaTotalMaxima = new javax.swing.JFormattedTextField();
        lblAlturaTotalMedia = new javax.swing.JLabel();
        txtAlturaTotalMedia = new javax.swing.JFormattedTextField();
        lblDiametroCoberturaMayor = new javax.swing.JLabel();
        txtDiametroCoberturaMayor = new javax.swing.JFormattedTextField();
        lblDanio1 = new javax.swing.JLabel();
        cmbAgenteDanio1 = new javax.swing.JComboBox();
        lblSeveridad1 = new javax.swing.JLabel();
        cmbSeveridad1 = new javax.swing.JComboBox();
        lblDanio2 = new javax.swing.JLabel();
        cmbAgenteDanio2 = new javax.swing.JComboBox();
        lblSeveridad2 = new javax.swing.JLabel();
        cmbSeveridad2 = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        cmbVigor = new javax.swing.JComboBox();
        lblDiametroCoberturaMenor1 = new javax.swing.JLabel();
        txtDiametroCoberturaMenor = new javax.swing.JFormattedTextField();
        lblNombreComun = new javax.swing.JLabel();
        txtNombreComun = new javax.swing.JTextField();
        lblFormaCrecimiento = new javax.swing.JLabel();
        cmbFormaCrecimiento = new javax.swing.JComboBox();
        lblDencidadColonia = new javax.swing.JLabel();
        cmbDensidadColonia = new javax.swing.JComboBox();
        cmbInfraespecie = new javax.swing.JComboBox();
        btnAgregar = new javax.swing.JButton();
        btnElimnar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        grdVegetacionvMayor = new javax.swing.JTable();
        btnContinuar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnColecta = new javax.swing.JButton();
        lblClaveColecta = new javax.swing.JLabel();
        txtClaveColecta = new javax.swing.JTextField();
        chkVegetacionMayorMCG = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Vegetación mayor, morfotipos creciendo de manera gregaria, módulo H");
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

        lblVegetacionMayorGragarios.setBackground(new java.awt.Color(153, 153, 153));
        lblVegetacionMayorGragarios.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblVegetacionMayorGragarios.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVegetacionMayorGragarios.setText("Morfotipos creciendo de manera gregaria");
        lblVegetacionMayorGragarios.setOpaque(true);

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblNumeroIndividuo.setText("Número de individuo:");

        lblConsecutivo.setText("Consecutivo:");

        cmbConsecutivo.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cmbConsecutivoPropertyChange(evt);
            }
        });

        txtNumeroIndividuo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###0"))));
        txtNumeroIndividuo.setEnabled(false);
        txtNumeroIndividuo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNumeroIndividuoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNumeroIndividuoFocusLost(evt);
            }
        });

        lblFormaVida.setText("FV:");
        lblFormaVida.setToolTipText("Forma de vida");

        lblCondicion.setText("Condición:");

        lblFamilia.setText("Familia:");

        cmbFamilia.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblConsecutivo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbConsecutivo, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNumeroIndividuo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNumeroIndividuo, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblFormaVida)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbFormaVida, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblCondicion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbCondicion, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(lblFamilia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblFamilia)
                        .addComponent(cmbFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblCondicion)
                        .addComponent(cmbCondicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbFormaVida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblFormaVida))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblNumeroIndividuo)
                        .addComponent(lblConsecutivo)
                        .addComponent(cmbConsecutivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNumeroIndividuo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel7.setBackground(new java.awt.Color(204, 204, 204));
        jPanel7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        lblGenero.setText("Género:");

        cmbGenero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbGeneroActionPerformed(evt);
            }
        });

        lblEspecie.setText("Especie:");

        cmbEspecie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEspecieActionPerformed(evt);
            }
        });

        lblInfraespecie.setText("Infraespecie:");

        jPanel8.setBackground(new java.awt.Color(204, 204, 204));

        lblAlturaTotalMinima.setText("ATMI:");
        lblAlturaTotalMinima.setToolTipText("Diámetro de cobertura mayor");

        txtAlturaTotalMinima.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtAlturaTotalMinima.setNextFocusableComponent(txtDiametroCoberturaMayor);
        txtAlturaTotalMinima.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtAlturaTotalMinimaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtAlturaTotalMinimaFocusLost(evt);
            }
        });
        txtAlturaTotalMinima.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAlturaTotalMinimaKeyTyped(evt);
            }
        });

        lblAlturaTotalMaxima.setText("ATMA:");
        lblAlturaTotalMaxima.setToolTipText("Altura total máxima");

        txtAlturaTotalMaxima.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtAlturaTotalMaxima.setNextFocusableComponent(txtAlturaTotalMedia);
        txtAlturaTotalMaxima.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtAlturaTotalMaximaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtAlturaTotalMaximaFocusLost(evt);
            }
        });
        txtAlturaTotalMaxima.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAlturaTotalMaximaKeyTyped(evt);
            }
        });

        lblAlturaTotalMedia.setText("ATME:");
        lblAlturaTotalMedia.setToolTipText("Altura Total media");

        txtAlturaTotalMedia.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtAlturaTotalMedia.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtAlturaTotalMediaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtAlturaTotalMediaFocusLost(evt);
            }
        });
        txtAlturaTotalMedia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAlturaTotalMediaKeyTyped(evt);
            }
        });

        lblDiametroCoberturaMayor.setText("DCMA:");
        lblDiametroCoberturaMayor.setToolTipText("Diamétro de cobertura mayor");

        txtDiametroCoberturaMayor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtDiametroCoberturaMayor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDiametroCoberturaMayorFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDiametroCoberturaMayorFocusLost(evt);
            }
        });
        txtDiametroCoberturaMayor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDiametroCoberturaMayorKeyTyped(evt);
            }
        });

        lblDanio1.setText("ADN1:");
        lblDanio1.setToolTipText("Ajente daño 1");

        cmbAgenteDanio1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAgenteDanio1ActionPerformed(evt);
            }
        });

        lblSeveridad1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSeveridad1.setText("S1:");
        lblSeveridad1.setToolTipText("Severidad 1");
        lblSeveridad1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblDanio2.setText("AD2:");
        lblDanio2.setToolTipText("Ajente daño 2");

        lblSeveridad2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSeveridad2.setText("S2:");
        lblSeveridad2.setToolTipText("Severidad 2");
        lblSeveridad2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel1.setText("Vigor:");

        lblDiametroCoberturaMenor1.setText("DCME:");
        lblDiametroCoberturaMenor1.setToolTipText("Diamétro de cobertura menor");

        txtDiametroCoberturaMenor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtDiametroCoberturaMenor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDiametroCoberturaMenorFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDiametroCoberturaMenorFocusLost(evt);
            }
        });
        txtDiametroCoberturaMenor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDiametroCoberturaMenorKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(lblAlturaTotalMaxima)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAlturaTotalMaxima, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAlturaTotalMedia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAlturaTotalMedia, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAlturaTotalMinima)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAlturaTotalMinima, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDiametroCoberturaMayor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDiametroCoberturaMayor, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDiametroCoberturaMenor1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDiametroCoberturaMenor, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lblDanio1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbAgenteDanio1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSeveridad1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbSeveridad1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDanio2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbAgenteDanio2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSeveridad2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbSeveridad2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbVigor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblDanio2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbAgenteDanio2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblDanio1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbAgenteDanio1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbSeveridad1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSeveridad1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbSeveridad2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSeveridad2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblDiametroCoberturaMenor1)
                        .addComponent(txtDiametroCoberturaMenor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblDiametroCoberturaMayor)
                        .addComponent(txtDiametroCoberturaMayor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblAlturaTotalMinima)
                        .addComponent(txtAlturaTotalMinima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblAlturaTotalMaxima)
                        .addComponent(txtAlturaTotalMaxima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblAlturaTotalMedia)
                        .addComponent(txtAlturaTotalMedia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbVigor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        lblNombreComun.setText("NC:");
        lblNombreComun.setToolTipText("Nombre común");

        txtNombreComun.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNombreComunFocusGained(evt);
            }
        });

        lblFormaCrecimiento.setText("FC");
        lblFormaCrecimiento.setToolTipText("Forma de crecimiento");

        lblDencidadColonia.setText("DC:");
        lblDencidadColonia.setToolTipText("Densidad de la colonia");

        cmbDensidadColonia.setToolTipText("");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(lblGenero)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEspecie)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lblInfraespecie)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbInfraespecie, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNombreComun)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombreComun, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblFormaCrecimiento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbFormaCrecimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblDencidadColonia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbDensidadColonia, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblDencidadColonia)
                        .addComponent(cmbDensidadColonia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblInfraespecie)
                        .addComponent(lblGenero)
                        .addComponent(cmbGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblEspecie)
                        .addComponent(cmbEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblFormaCrecimiento)
                        .addComponent(cmbFormaCrecimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNombreComun)
                        .addComponent(txtNombreComun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbInfraespecie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 13, Short.MAX_VALUE))
        );

        btnAgregar.setText("Agregar");
        btnAgregar.setNextFocusableComponent(btnModificar);
        btnAgregar.setPreferredSize(new java.awt.Dimension(79, 23));
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnElimnar.setText("Eliminar");
        btnElimnar.setNextFocusableComponent(txtNumeroIndividuo);
        btnElimnar.setPreferredSize(new java.awt.Dimension(79, 23));
        btnElimnar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnElimnarActionPerformed(evt);
            }
        });

        btnModificar.setText(" Modificar");
        btnModificar.setNextFocusableComponent(btnElimnar);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        grdVegetacionvMayor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        grdVegetacionvMayor.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        grdVegetacionvMayor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grdVegetacionvMayorMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(grdVegetacionvMayor);

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

        btnColecta.setText("Colecta");
        btnColecta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColectaActionPerformed(evt);
            }
        });

        lblClaveColecta.setText("Clave:");

        txtClaveColecta.setEnabled(false);

        chkVegetacionMayorMCG.setBackground(new java.awt.Color(204, 204, 204));
        chkVegetacionMayorMCG.setSelected(true);
        chkVegetacionMayorMCG.setText("Vegetación mayor MCG");
        chkVegetacionMayorMCG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkVegetacionMayorMCGActionPerformed(evt);
            }
        });

        jButton1.setText("Limpiar Controles");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnContinuar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(310, 310, 310))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblVegetacionMayorGragarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(chkVegetacionMayorMCG)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnModificar)
                                .addGap(18, 18, 18)
                                .addComponent(btnElimnar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(103, 103, 103)
                                .addComponent(btnColecta)
                                .addGap(18, 18, 18)
                                .addComponent(lblClaveColecta)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtClaveColecta, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblUPM)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtUPM, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblSitio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtSitio, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSitio)
                    .addComponent(txtSitio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUPM)
                    .addComponent(txtUPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblVegetacionMayorGragarios)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnElimnar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnModificar))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblClaveColecta)
                        .addComponent(txtClaveColecta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnColecta))
                    .addComponent(chkVegetacionMayorMCG))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnContinuar)
                    .addComponent(btnSalir)
                    .addComponent(jButton1))
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void asignarDatosVegetacionMayor() {
        try{
            this.consecutivo = Integer.valueOf(txtNumeroIndividuo.getText());
            //System.out.println("Consecutivo="+this.consecutivo);
        }catch(NumberFormatException e){
            this.consecutivo = null;
        }
        try{
            this.noIndividuo = Integer.valueOf(txtNumeroIndividuo.getText());
        }catch(NumberFormatException e){
            this.noIndividuo = null;
        }
        
        try {
            this.alturaTotalMaxima = Float.valueOf(txtAlturaTotalMaxima.getText());
        } catch (NumberFormatException e) {
            this.alturaTotalMaxima = null;
        }
        try {
            this.alturaTotalMedia = Float.valueOf(txtAlturaTotalMedia.getText());
        } catch (NumberFormatException e) {
            this.alturaTotalMedia = null;
        }
        try {
            this.alturaTotalMinima = Float.valueOf(txtAlturaTotalMinima.getText());
        } catch (NumberFormatException e) {
            this.alturaTotalMinima = null;
        }
        try {
            this.diametroCoberturaMayor = Float.valueOf(txtDiametroCoberturaMayor.getText());
        } catch (NumberFormatException e) {
            this.diametroCoberturaMayor = null;
        }
        try {
            this.diametroCoberturaMenor = Float.valueOf(txtDiametroCoberturaMenor.getText());
        } catch (NumberFormatException e) {
            this.diametroCoberturaMenor = null;
        }
    }

    private void crearVegetacionMayor() {
        CatEFormaVidaZA indexFormaVida = (CatEFormaVidaZA) cmbFormaVida.getSelectedItem();
        CatECondicionVM indexCondicion = (CatECondicionVM) cmbCondicion.getSelectedItem();
        CatEFamiliaEspecie indexFamilia = (CatEFamiliaEspecie) cmbFamilia.getSelectedItem();
        CatEGenero indexGenero = (CatEGenero) cmbGenero.getSelectedItem();
        CatEEspecie indexEspecie = (CatEEspecie) cmbEspecie.getSelectedItem();
        CatEInfraespecie indexInfraespecie = (CatEInfraespecie) cmbInfraespecie.getSelectedItem();
        CatEFormaCrecimiento indexFormaCrecimiento = (CatEFormaCrecimiento) cmbFormaCrecimiento.getSelectedItem();
        CatEDensidadColonia indexDensidadColonia = (CatEDensidadColonia) cmbDensidadColonia.getSelectedItem();
        CatEVigorArbolado indexVigor = (CatEVigorArbolado) cmbVigor.getSelectedItem();

        CEVegetacionMayorGregarios vegetacionMayor = new CEVegetacionMayorGregarios();

        vegetacionMayor.setSitioID(this.sitioID);
        vegetacionMayor.setFormaVidaID(indexFormaVida.getFormaVidaZAID());
        vegetacionMayor.setCondicionID(indexCondicion.getCondicionVMID());
        if(indexFamilia != null){
            vegetacionMayor.setFamiliaID(indexFamilia.getFamiliaID());
        }
        if (indexGenero != null) {
            vegetacionMayor.setGeneroID(indexGenero.getGeneroID());
        }
        if (indexEspecie != null) {
            vegetacionMayor.setEspecieID(indexEspecie.getEspecieID());
        }
        if(indexInfraespecie != null){
            vegetacionMayor.setInfraespecieID(indexInfraespecie.getInfraespecieID());
        }
        vegetacionMayor.setConsecutivo(this.consecutivo);
        //System.out.println("Consecutivo="+this.consecutivo);
        vegetacionMayor.setNumeroIndividuo(this.noIndividuo);
        vegetacionMayor.setNombreComun(txtNombreComun.getText());
        vegetacionMayor.setFormaCrecimientoID(indexFormaCrecimiento.getFormaCrecimientoID());
        vegetacionMayor.setDensidadColoniaID(indexDensidadColonia.getDensidadColoniaID());
        vegetacionMayor.setAlturaTotalMaxima(this.alturaTotalMaxima);
        vegetacionMayor.setAlturaTotalMedia(this.alturaTotalMedia);
        vegetacionMayor.setAlturaTotalMinima(this.alturaTotalMinima);
        vegetacionMayor.setDiametroCoberturaMayor(this.diametroCoberturaMayor);
        vegetacionMayor.setDiametroCoberturaMenor(this.diametroCoberturaMenor);
        vegetacionMayor.setVigorID(indexVigor.getVigorID());

        this.cdVegetacionMayor.insertVegetacionMayor(vegetacionMayor);
        this.vegetacionMayorID = this.cdVegetacionMayor.getLastVMID();
        crearDanios(this.vegetacionMayorID);
    }

    private void crearDanios(int vegetacionMayorID) {
        CEDanioSeveridad ceDanio1 = new CEDanioSeveridad();
        CEDanioSeveridad ceDanio2 = new CEDanioSeveridad();
        CDDanioVegetacionMayorG cdDanio = new CDDanioVegetacionMayorG();
        CatEAgenteDanio indexAgenteDanio1 = (CatEAgenteDanio) cmbAgenteDanio1.getSelectedItem();
        CatEAgenteDanio indexAgenteDanio2 = (CatEAgenteDanio) cmbAgenteDanio2.getSelectedItem();
        CatESeveridadZA indexSeveridad1 = (CatESeveridadZA) cmbSeveridad1.getSelectedItem();
        CatESeveridadZA indexSeveridad2 = (CatESeveridadZA) cmbSeveridad2.getSelectedItem();
        Integer danio1;
        Integer danio2;
        Integer severidad1;
        Integer severidad2;
        try {
            danio1 = indexAgenteDanio1.getAgenteDanioID();
        } catch (NullPointerException e) {
            danio1 = null;
        }

        try {
            danio2 = indexAgenteDanio2.getAgenteDanioID();
        } catch (NullPointerException e) {
            danio2 = null;
        }

        try {
            severidad1 = indexSeveridad1.getSeveridadID();
        } catch (NullPointerException e) {
            severidad1 = null;
        }

        try {
            severidad2 = indexSeveridad2.getSeveridadID();
        } catch (NullPointerException e) {
            severidad2 = null;
        }
        ceDanio1.setSeccionID(vegetacionMayorID);
        ceDanio1.setNumeroDanio(1);
        ceDanio1.setAgenteDanioID(danio1);
        ceDanio1.setSeveridadID(severidad1);

        cdDanio.insertDanio(ceDanio1);

        ceDanio2.setSeccionID(vegetacionMayorID);
        ceDanio2.setNumeroDanio(2);
        ceDanio2.setAgenteDanioID(danio2);
        ceDanio2.setSeveridadID(severidad2);

        cdDanio.insertDanio(ceDanio2);
    }

    private void actualizarVegetacionMayor() {
        try {
            int fila = grdVegetacionvMayor.getSelectedRow();
            String registro = grdVegetacionvMayor.getValueAt(fila, 0).toString();

            CatEFormaVidaZA indexFormaVida = (CatEFormaVidaZA) cmbFormaVida.getSelectedItem();
            CatECondicionVM indexCondicion = (CatECondicionVM) cmbCondicion.getSelectedItem();
            CatEFamiliaEspecie indexFamilia = (CatEFamiliaEspecie) cmbFamilia.getSelectedItem();
            CatEGenero indexGenero = (CatEGenero) cmbGenero.getSelectedItem();
            CatEEspecie indexEspecie = (CatEEspecie) cmbEspecie.getSelectedItem();
            CatEInfraespecie indexInfraespecie = (CatEInfraespecie) cmbInfraespecie.getSelectedItem();
            CatEFormaCrecimiento indexFormaCrecimiento = (CatEFormaCrecimiento) cmbFormaCrecimiento.getSelectedItem();
            CatEDensidadColonia indexDensidadColonia = (CatEDensidadColonia) cmbDensidadColonia.getSelectedItem();
            CatEAgenteDanio indexDanio1 = (CatEAgenteDanio) cmbAgenteDanio1.getSelectedItem();
            CatEAgenteDanio indexDanio2 = (CatEAgenteDanio) cmbAgenteDanio2.getSelectedItem();
            CatESeveridadZA indexSeveridad1 = (CatESeveridadZA) cmbSeveridad1.getSelectedItem();
            CatESeveridadZA indexSeveridad2 = (CatESeveridadZA) cmbSeveridad2.getSelectedItem();
            CatEVigorArbolado indexVigor = (CatEVigorArbolado) cmbVigor.getSelectedItem();
            CEVegetacionMayorGregarios vegetacionMayor = new CEVegetacionMayorGregarios();
            this.vegetacionMayorID = Integer.parseInt(registro);
            vegetacionMayor.setSitioID(this.sitioID);
            vegetacionMayor.setVegetacionMayorID(this.vegetacionMayorID);
            vegetacionMayor.setFormaVidaID(indexFormaVida.getFormaVidaZAID());
            vegetacionMayor.setCondicionID(indexCondicion.getCondicionVMID());
            if (indexFamilia != null) {
                vegetacionMayor.setFamiliaID(indexFamilia.getFamiliaID());
            } else {
                vegetacionMayor.setFamiliaID(null);
            }
            if (indexGenero != null) {
                vegetacionMayor.setGeneroID(indexGenero.getGeneroID());
            } else {
                vegetacionMayor.setGeneroID(null);
            }
            if (indexEspecie != null) {
                vegetacionMayor.setEspecieID(indexEspecie.getEspecieID());
            } else {
                vegetacionMayor.setEspecieID(null);
            }
            if(indexInfraespecie != null){
                vegetacionMayor.setInfraespecieID(indexInfraespecie.getInfraespecieID());
            } else {
                vegetacionMayor.setInfraespecieID(null);
            }
            vegetacionMayor.setNombreComun(txtNombreComun.getText());
            vegetacionMayor.setFormaCrecimientoID(indexFormaCrecimiento.getFormaCrecimientoID());
            vegetacionMayor.setDensidadColoniaID(indexDensidadColonia.getDensidadColoniaID());
            vegetacionMayor.setAlturaTotalMaxima(this.alturaTotalMaxima);
            vegetacionMayor.setAlturaTotalMedia(this.alturaTotalMedia);
            vegetacionMayor.setAlturaTotalMinima(this.alturaTotalMinima);
            vegetacionMayor.setDiametroCoberturaMayor(this.diametroCoberturaMayor);
            vegetacionMayor.setDiametroCoberturaMenor(this.diametroCoberturaMenor);
            vegetacionMayor.setVigorID(indexVigor.getVigorID());

            CEDanioSeveridad danio1 = new CEDanioSeveridad();
            CEDanioSeveridad danio2 = new CEDanioSeveridad();
            CDDanioVegetacionMayorG cdDanio = new CDDanioVegetacionMayorG();

            danio1.setSeccionID(this.vegetacionMayorID);
            danio1.setNumeroDanio(1);
            danio1.setAgenteDanioID(indexDanio1.getAgenteDanioID());
            if (indexSeveridad1 != null) {
                danio1.setSeveridadID(indexSeveridad1.getSeveridadID());
            }

            cdDanio.updateDanio(danio1);

            danio2.setSeccionID(this.vegetacionMayorID);
            danio2.setNumeroDanio(2);
            danio2.setAgenteDanioID(indexDanio2.getAgenteDanioID());
            if (indexSeveridad2 != null) {
                danio2.setSeveridadID(indexSeveridad2.getSeveridadID());
            }
            cdDanio.updateDanio(danio2);
            //System.out.println("Actualizar vegetacion "+this.consecutivo);
            //vegetacionMayor.setConsecutivo(this.consecutivo);
        //System.out.println("Consecutivo="+this.consecutivo);
            vegetacionMayor.setNumeroIndividuo(this.consecutivo);
            this.cdVegetacionMayor.updateVegetacionMayor(vegetacionMayor);
            limpiarControles();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla de vegetación mayor gregarios"
                    + e.getClass().getName() + " : " + e.getMessage(), "Vegetación mayor individual", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarVegetacionMayor() {
        try {
            int fila = grdVegetacionvMayor.getSelectedRow();
            String registro = grdVegetacionvMayor.getValueAt(fila, 0).toString();
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Esta seguro de borrar el registro de vegetación mayor?",
                    "Vegetación mayor gregarios", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (respuesta == JOptionPane.YES_NO_OPTION) {
                this.vegetacionMayorID = Integer.parseInt(registro);
                this.cdVegetacionMayor.deleteVegetacionMayor(this.vegetacionMayorID);
                eliminarDanio(this.vegetacionMayorID);
                limpiarControles();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla de vegetación mayor gregarios"
                    + "", "Vegetación mayor individual", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarDanio(int vegetacionMayorID) {
        CDDanioVegetacionMayorG cdDanio = new CDDanioVegetacionMayorG();
        cdDanio.deleteDanio(vegetacionMayorID);
    }

    public void limpiarControles() {
        cmbConsecutivo.setSelectedItem(null);
        txtNumeroIndividuo.setText("");
        txtNumeroIndividuo.setEnabled(true);
        cmbFamilia.setSelectedItem(null);
        cmbGenero.setSelectedItem(null);
        cmbEspecie.setSelectedItem(null);
        cmbInfraespecie.setSelectedItem(null);
        txtNombreComun.setText("");
        cmbFormaVida.setSelectedItem(null);
        cmbCondicion.setSelectedItem(null);
        cmbFormaCrecimiento.setSelectedItem(null);
        cmbDensidadColonia.setSelectedItem(null);
        txtAlturaTotalMaxima.setValue(null);
        txtAlturaTotalMaxima.setText("");
        txtAlturaTotalMinima.setValue(null);
        txtAlturaTotalMinima.setText("");
        txtAlturaTotalMedia.setValue(null);
        txtAlturaTotalMedia.setText("");
        txtDiametroCoberturaMayor.setValue(null);
        txtDiametroCoberturaMayor.setText("");
        txtDiametroCoberturaMenor.setValue(null);
        txtDiametroCoberturaMenor.setText("");
        cmbAgenteDanio1.setSelectedIndex(0);
        cmbAgenteDanio2.setSelectedIndex(0);
        cmbSeveridad1.setSelectedIndex(0);
        cmbSeveridad2.setSelectedIndex(0);
        cmbVigor.setSelectedItem(null);
        cmbVigor.setEnabled(true);
        txtClaveColecta.setText("");
    }

    private void deshabiliarControles() {
        cmbConsecutivo.setEnabled(false);
        //txtNumeroIndividuo.setEnabled(false);
        cmbFamilia.setEnabled(false);
        cmbGenero.setEnabled(false);
        cmbEspecie.setEnabled(false);
        cmbInfraespecie.setEnabled(false);
        txtNombreComun.setEnabled(false);
        cmbFormaVida.setEnabled(false);
        cmbFormaCrecimiento.setEnabled(false);
        cmbCondicion.setEnabled(false);
        txtAlturaTotalMaxima.setEnabled(false);
        txtAlturaTotalMedia.setEnabled(false);
        txtAlturaTotalMinima.setEnabled(false);
        txtDiametroCoberturaMayor.setEnabled(false);
        txtDiametroCoberturaMenor.setEnabled(false);
        cmbAgenteDanio1.setEnabled(false);
        cmbAgenteDanio2.setEnabled(false);
        cmbVigor.setEnabled(false);
    }

    private void habilitarControles() {
        cmbConsecutivo.setEnabled(true);
        //txtNumeroIndividuo.setEnabled(true);
        cmbFamilia.setEnabled(true);
        cmbGenero.setEnabled(true);
        cmbEspecie.setEnabled(true);
        cmbInfraespecie.setEnabled(true);
        txtNombreComun.setEnabled(true);
        cmbFormaVida.setEnabled(true);
        cmbFormaCrecimiento.setEnabled(true);
        cmbCondicion.setEnabled(true);
        txtAlturaTotalMinima.setEnabled(true);
        txtAlturaTotalMedia.setEnabled(true);
        txtAlturaTotalMaxima.setEnabled(true);
        txtDiametroCoberturaMayor.setEnabled(true);
        txtDiametroCoberturaMenor.setEnabled(true);
        cmbAgenteDanio1.setEnabled(true);
        cmbAgenteDanio2.setEnabled(true);
        cmbVigor.setEnabled(true);
    }


    private boolean validarCamposObligatorio() {
        ValidacionesComunes validacion = new ValidacionesComunes();
       if (cmbFormaVida.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar un tipo de forma de vida", "Vegetación mayor gregarios", JOptionPane.INFORMATION_MESSAGE);
            cmbFormaVida.requestFocus();
            return false;
        } else if (cmbCondicion.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar un tipo de condicion", "Vegetación mayor gregarios", JOptionPane.INFORMATION_MESSAGE);
            cmbCondicion.requestFocus();
            return false;
        }  else if (cmbFormaCrecimiento.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar una forma de crecimiento ", "Vegetación mayor gregarios", JOptionPane.INFORMATION_MESSAGE);
            cmbFormaCrecimiento.requestFocus();
            return false;
        } else if (cmbDensidadColonia.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar densidad de colonia ", "Vegetación mayor gregarios", JOptionPane.INFORMATION_MESSAGE);
            cmbDensidadColonia.requestFocus();
            return false;
        } else if (txtAlturaTotalMaxima.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar la altura total máxima ", "Vegetación mayor gregarios", JOptionPane.INFORMATION_MESSAGE);
            txtAlturaTotalMaxima.requestFocus();
            return false;
        } else if (txtAlturaTotalMedia.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar la altura total media ", "Vegetación mayor gregarios", JOptionPane.INFORMATION_MESSAGE);
            txtAlturaTotalMedia.requestFocus();
            return false;
        } else if (txtAlturaTotalMinima.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar la altura total mínima", "Vegetación mayor gregarios", JOptionPane.INFORMATION_MESSAGE);
            txtAlturaTotalMinima.requestFocus();
            return false;
        } else if (txtDiametroCoberturaMayor.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar el diámetro de cobertura mayor ", "Vegetación mayor gregarios", JOptionPane.INFORMATION_MESSAGE);
            txtDiametroCoberturaMayor.requestFocus();
            return false;
        } else if (txtDiametroCoberturaMenor.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar el diámetro de cobertura menor", "Vegetación mayor gregarios", JOptionPane.INFORMATION_MESSAGE);
            txtDiametroCoberturaMenor.requestFocus();
            return false;    
        } else if (cmbVigor.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar un tipo de vigor", "Vegetación mayor gregarios", JOptionPane.INFORMATION_MESSAGE);
            cmbVigor.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean validarMedicionesObligatorias() {
        if(this.alturaTotalMaxima != null && this.alturaTotalMaxima < 0){
            JOptionPane.showMessageDialog(null, "Error! La altura total máxima debe ser mayor a 0 ", "Vegetación mayor gregarios", JOptionPane.INFORMATION_MESSAGE);
            txtAlturaTotalMaxima.requestFocus();
            return false;
        } else if(this.alturaTotalMedia != null && this.alturaTotalMedia < 0){
            JOptionPane.showMessageDialog(null, "Error! La altura total media debe ser mayor a 0 ", "Vegetación mayor gregarios", JOptionPane.INFORMATION_MESSAGE);
            txtAlturaTotalMedia.requestFocus();
            return false;
        } else if(this.alturaTotalMinima != null && this.alturaTotalMinima < 0){
            JOptionPane.showMessageDialog(null, "Error! La altura total mínima debe ser mayor a 0 ", "Vegetación mayor gregarios", JOptionPane.INFORMATION_MESSAGE);
            txtAlturaTotalMinima.requestFocus();
            return false;
        } else if(this.diametroCoberturaMayor != null && this.diametroCoberturaMayor < 0){
            JOptionPane.showMessageDialog(null, "Error! El diámetro de cobertura mayor debe ser mayor a 0 ", "Vegetación mayor gregarios", JOptionPane.INFORMATION_MESSAGE);
            txtDiametroCoberturaMayor.requestFocus();
            return false;
        } else if(this.diametroCoberturaMenor != null && this.diametroCoberturaMenor < 0){
            JOptionPane.showMessageDialog(null, "Error! El diámetro de cobertura menor debe ser mayor a 0 ", "Vegetación mayor gregarios", JOptionPane.INFORMATION_MESSAGE);
            txtDiametroCoberturaMenor.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean validarDanioObligatorio() {
        CatECondicionVM condicion = (CatECondicionVM) cmbCondicion.getSelectedItem();
        CatEAgenteDanio agenteDanio = (CatEAgenteDanio) cmbAgenteDanio1.getSelectedItem();
        if (condicion.getCondicionVMID() == 2 && agenteDanio.getAgenteDanioID() == 1) {
            JOptionPane.showMessageDialog(null, "Error! Si selecciona muerto en pie debe seleccionar al menos un daño", "Vegetación mayor gregarios", JOptionPane.INFORMATION_MESSAGE);
            cmbAgenteDanio1.requestFocus();
            return false;
        } else {
            return true;
        }
    }


    private void fijarValoresPorCampo(int vegetacionMayorID) {
        CEVegetacionMayorGregarios ceVegetacionMayor;
        ceVegetacionMayor = this.cdVegetacionMayor.getRegistroVM(vegetacionMayorID);
        
        txtNumeroIndividuo.setText(String.valueOf(ceVegetacionMayor.getNumeroIndividuo()));
        
        CatEFormaVidaZA formaVida = new CatEFormaVidaZA();
        formaVida.setFormaVidaZAID(ceVegetacionMayor.getFormaVidaID());
        cmbFormaVida.setSelectedItem(formaVida);
        
        CatECondicionVM condicion = new CatECondicionVM();
        condicion.setCondicionVMID(ceVegetacionMayor.getCondicionID());
        cmbCondicion.setSelectedItem(condicion);
        
        CatEFamiliaEspecie fam = new CatEFamiliaEspecie();
        fam.setFamiliaID(ceVegetacionMayor.getFamiliaID());
        cmbFamilia.setSelectedItem(fam);

        CatEGenero gen = new CatEGenero();
        gen.setGeneroID(ceVegetacionMayor.getGeneroID());
        cmbGenero.setSelectedItem(gen);
        
       /* CatEGenero gen = new CatEGenero();
        gen.setGeneroID(ceVegetacionMayor.getGeneroID());
        cmbGenero.removeAllItems();
        fillCmbGenero(ceVegetacionMayor.getFamiliaID());
        cmbGenero.setSelectedItem(gen);*/

        CatEEspecie esp = new CatEEspecie();
        esp.setEspecieID(ceVegetacionMayor.getEspecieID());
        cmbEspecie.removeAllItems();
        fillCmbEspecie(ceVegetacionMayor.getGeneroID());
        cmbEspecie.setSelectedItem(esp);
        
        CatEInfraespecie inf = new CatEInfraespecie();
        inf.setInfraespecieID(ceVegetacionMayor.getInfraespecieID());
        cmbInfraespecie.removeAllItems();
        fillCmbInfraespecie(ceVegetacionMayor.getEspecieID());
        cmbInfraespecie.setSelectedItem(inf);
        
        txtNombreComun.setText(ceVegetacionMayor.getNombreComun());
        
        CatEFormaCrecimiento formaCrecimiento = new CatEFormaCrecimiento();
        formaCrecimiento.setFormaCrecimientoID(ceVegetacionMayor.getFormaCrecimientoID());
        cmbFormaCrecimiento.setSelectedItem(formaCrecimiento);
        
        CatEDensidadColonia densidadColonia = new CatEDensidadColonia();
        densidadColonia.setDensidadColoniaID(ceVegetacionMayor.getDensidadColoniaID());
        cmbDensidadColonia.setSelectedItem(densidadColonia);
        
        txtAlturaTotalMaxima.setText(String.valueOf(ceVegetacionMayor.getAlturaTotalMaxima()));
        txtAlturaTotalMedia.setText(String.valueOf(ceVegetacionMayor.getAlturaTotalMedia()));
        txtAlturaTotalMinima.setText(String.valueOf(ceVegetacionMayor.getAlturaTotalMinima()));
        txtDiametroCoberturaMayor.setText(String.valueOf(ceVegetacionMayor.getDiametroCoberturaMayor()));
        txtDiametroCoberturaMenor.setText(String.valueOf(ceVegetacionMayor.getDiametroCoberturaMenor()));
        CatEVigorArbolado tv = new CatEVigorArbolado();
        tv.setVigorID(ceVegetacionMayor.getVigorID());
        cmbVigor.setSelectedItem(tv);
 
         //Fijando datos en campos de danios
        CatEAgenteDanio agente1 = new CatEAgenteDanio();
        CatEAgenteDanio agente2 = new CatEAgenteDanio();
        CatESeveridadZA severidad1 = new CatESeveridadZA();
        CatESeveridadZA severidad2 = new CatESeveridadZA();
        CDDanioVegetacionMayorG cdDanio = new CDDanioVegetacionMayorG();
        
        
        List<CEDanioSeveridad> listDanio = new ArrayList<>();
        listDanio = cdDanio.getDanio(vegetacionMayorID);

        int size = listDanio.size();

        for (int i = 0; i < size; i++) {
            if (i == 0) {
                agente1.setAgenteDanioID(listDanio.get(i).getAgenteDanioID());
                severidad1.setSeveridadID(listDanio.get(i).getSeveridadID());
               
            } else {
                agente2.setAgenteDanioID(listDanio.get(i).getAgenteDanioID());
                severidad2.setSeveridadID(listDanio.get(i).getSeveridadID());
            }
        }
        cmbAgenteDanio1.setSelectedItem(agente1);
        cmbSeveridad1.setSelectedItem(severidad1);
      
        cmbAgenteDanio2.setSelectedItem(agente2);
        cmbSeveridad2.setSelectedItem(severidad2);
        txtClaveColecta.setText(ceVegetacionMayor.getColectaBotanica());
    }

    private boolean validarSeveridadDanio() {
        if (cmbAgenteDanio1.getSelectedIndex() != 0 && cmbSeveridad1.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Error! Debe de seleccionar un nivel de severidad 1 ", "Vegetación mayor gregarios", JOptionPane.INFORMATION_MESSAGE);
            cmbSeveridad1.requestFocus();
            return false;
        } else if (cmbAgenteDanio2.getSelectedIndex() != 0 && cmbSeveridad2.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Error! Debe de seleccionar un nivel de severidad 2 ", "Vegetación mayor gregarios", JOptionPane.INFORMATION_MESSAGE);
            cmbSeveridad2.requestFocus();
            return false;
        } else {
            return true;
        }
    }
    
    private boolean validarColectasObligatorias() {
        CDColectaBotanica colecta = new CDColectaBotanica();
        if (colecta.validarCapturaEspecie("TAXONOMIA_VegetacionMayorGregarios", this.sitioID)) {
            JOptionPane.showMessageDialog(null, "Error! Faltan por asignar claves de colecta", "Vegetación Mayor G", JOptionPane.INFORMATION_MESSAGE);
            return false;
        } else {
            return true;
        }
    }
    
    private void txtNombreComunFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombreComunFocusGained
        txtNombreComun.selectAll();
    }//GEN-LAST:event_txtNombreComunFocusGained

    private void txtAlturaTotalMinimaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAlturaTotalMinimaFocusGained
         SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                txtAlturaTotalMinima.selectAll();
            }
        });
    }//GEN-LAST:event_txtAlturaTotalMinimaFocusGained

    private void txtAlturaTotalMediaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAlturaTotalMediaFocusGained
       SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                txtAlturaTotalMedia.selectAll();
            }
        });
    }//GEN-LAST:event_txtAlturaTotalMediaFocusGained

    private void txtDiametroCoberturaMayorFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDiametroCoberturaMayorFocusGained
       SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                txtDiametroCoberturaMayor.selectAll();
            }
        });
    }//GEN-LAST:event_txtDiametroCoberturaMayorFocusGained

    private void txtAlturaTotalMinimaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAlturaTotalMinimaFocusLost
      if(txtAlturaTotalMinima.getText().isEmpty()){
          txtAlturaTotalMinima.setValue(null);
      }    
    }//GEN-LAST:event_txtAlturaTotalMinimaFocusLost

    private void txtAlturaTotalMediaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAlturaTotalMediaFocusLost
       if(txtAlturaTotalMedia.getText().isEmpty()){
           txtAlturaTotalMedia.setValue(null);
       }
    }//GEN-LAST:event_txtAlturaTotalMediaFocusLost

    private void txtDiametroCoberturaMayorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDiametroCoberturaMayorFocusLost
       if(txtDiametroCoberturaMayor.getText().isEmpty()){
           txtDiametroCoberturaMayor.setValue(null);
       }
    }//GEN-LAST:event_txtDiametroCoberturaMayorFocusLost

    private void txtNumeroIndividuoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNumeroIndividuoFocusGained
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                txtNumeroIndividuo.selectAll();
            }
        });
    }//GEN-LAST:event_txtNumeroIndividuoFocusGained

    private void txtNumeroIndividuoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNumeroIndividuoFocusLost
        if(txtNumeroIndividuo.getText().isEmpty()){
            txtNumeroIndividuo.setValue(null);
        }
    }//GEN-LAST:event_txtNumeroIndividuoFocusLost

    private void grdVegetacionvMayorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grdVegetacionvMayorMouseClicked
        if (evt.getButton() == 1) {
            int fila = grdVegetacionvMayor.getSelectedRow();
            String strArbID = grdVegetacionvMayor.getValueAt(fila, 0).toString();
            this.vegetacionMayorID= Integer.parseInt(strArbID);
            fijarValoresPorCampo(this.vegetacionMayorID);
            txtNumeroIndividuo.setEnabled(false);
        }
    }//GEN-LAST:event_grdVegetacionvMayorMouseClicked

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.hide();
        funciones.manipularBotonesMenuPrincipal(false);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
       asignarDatosVegetacionMayor();
        if (cmbAgenteDanio1.getSelectedIndex() == 0 && cmbSeveridad1.getSelectedIndex() > 0) {
            JOptionPane.showMessageDialog(null, "Si no hay Agente de daño, la Severidad es 0", "Vegetacion mayor individuales", JOptionPane.INFORMATION_MESSAGE);
            cmbAgenteDanio1.requestFocus();
        } else if (cmbAgenteDanio2.getSelectedIndex() == 0 && cmbSeveridad2.getSelectedIndex() > 0) {
            JOptionPane.showMessageDialog(null, "Si no hay Agente de daño, la Severidad es 0", "Vegetacion mayor individuales", JOptionPane.INFORMATION_MESSAGE);

        } else if (validarCamposObligatorio() && validarMedicionesObligatorias() && validarSeveridadDanio()) {
            if (validarDanioObligatorio()) {
                crearVegetacionMayor();
                //this.cdVegetacionMayor.enumerarConsecutivo(this.sitioID);
                //this.cdVegetacionMayor.enumerarIndividuo(this.sitioID);
                combo.reiniciarComboModel(cmbConsecutivo);
                fillCmbConsecutivo();
                llenarTabla();
                limpiarControles();
            }
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnElimnarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnElimnarActionPerformed
        eliminarVegetacionMayor();
        //this.cdVegetacionMayor.enumerarConsecutivo(this.sitioID);
        //this.cdVegetacionMayor.enumerarIndividuo(this.sitioID);
        combo.reiniciarComboModel(cmbConsecutivo);
        fillCmbConsecutivo();
        llenarTabla();
    }//GEN-LAST:event_btnElimnarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        asignarDatosVegetacionMayor();
        if (cmbAgenteDanio1.getSelectedIndex() == 0 && cmbSeveridad1.getSelectedIndex() > 0) {
            JOptionPane.showMessageDialog(null, "Si no hay Agente de daño, la Severidad es 0", "Vegetacion mayor individuales", JOptionPane.INFORMATION_MESSAGE);
            cmbAgenteDanio1.requestFocus();
        } else if (cmbAgenteDanio2.getSelectedIndex() == 0 && cmbSeveridad2.getSelectedIndex() > 0) {
            JOptionPane.showMessageDialog(null, "Si no hay Agente de daño, la Severidad es 0", "Vegetacion mayor individuales", JOptionPane.INFORMATION_MESSAGE);

        } else if (validarCamposObligatorio() && validarMedicionesObligatorias() && validarSeveridadDanio()) {
            if (validarDanioObligatorio()) {
                actualizarVegetacionMayor();
                //this.cdVegetacionMayor.enumerarConsecutivo(this.sitioID);
                //this.cdVegetacionMayor.enumerarIndividuo(this.sitioID);
                combo.reiniciarComboModel(cmbConsecutivo);
                fillCmbConsecutivo();
                llenarTabla();
                limpiarControles();
            }
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void cmbConsecutivoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cmbConsecutivoPropertyChange
        Integer consecutivo;
        Integer arbolID;
        try {
            consecutivo = (Integer) cmbConsecutivo.getSelectedItem();
            //arbolID = cdVegetacionMayor.getConsecutivoArbolado(consecutivo);
            //fijarValoresPorCampo(arbolID);
        } catch (NullPointerException e) {
            consecutivo = null;
        }
    }//GEN-LAST:event_cmbConsecutivoPropertyChange

    private void cmbGeneroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbGeneroActionPerformed
       CatEGenero indexGenero = (CatEGenero) cmbGenero.getSelectedItem();
        DefaultComboBoxModel dcm = (DefaultComboBoxModel) cmbEspecie.getModel();
        dcm.removeAllElements();
        if (indexGenero != null) {
            fillCmbEspecie(indexGenero.getGeneroID());
        }
    }//GEN-LAST:event_cmbGeneroActionPerformed

    private void btnContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuarActionPerformed
        if (validarColectasObligatorias()) {
            if (funciones.validarSeccionCapturada("TAXONOMIA_VegetacionMayorGregarios", this.ceSitio) && chkVegetacionMayorMCG.isSelected()) {
                JOptionPane.showMessageDialog(null, "Si seleccionó vegetación mayor gregarios, se debe capturar ", "Vegetacion mayor gregarios", JOptionPane.INFORMATION_MESSAGE);
                chkVegetacionMayorMCG.requestFocus();
            } else if (funciones.validarSeccionCapturada("TAXONOMIA_VegetacionMayorGregarios", this.ceSitio) == false && chkVegetacionMayorMCG.isSelected()) {
                this.hide();
                this.cdSecuencia.updateSecuencia(this.ceSitio, FORMATO_ID, 1);
                funciones.manipularBotonesMenuPrincipal(false);
                this.cdSecuencia.insertSecuenciaTerminada(this.ceSitio);
            } else if (funciones.validarSeccionCapturada("TAXONOMIA_VegetacionMayorGregarios", this.ceSitio) == true && !chkVegetacionMayorMCG.isSelected()) {
                this.hide();
                this.cdSecuencia.updateSecuencia(this.ceSitio, FORMATO_ID, -1);
                funciones.manipularBotonesMenuPrincipal(false);
                this.cdSecuencia.insertSecuenciaTerminada(this.ceSitio);
            }
        }
    }//GEN-LAST:event_btnContinuarActionPerformed

    private void txtAlturaTotalMaximaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAlturaTotalMaximaFocusGained
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                txtAlturaTotalMaxima.selectAll();
            }
        });
    }//GEN-LAST:event_txtAlturaTotalMaximaFocusGained

    private void txtAlturaTotalMaximaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAlturaTotalMaximaFocusLost
        if(txtAlturaTotalMaxima.getText().isEmpty()){
            txtAlturaTotalMaxima.setValue(null);
        }
    }//GEN-LAST:event_txtAlturaTotalMaximaFocusLost

    private void txtAlturaTotalMaximaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAlturaTotalMaximaKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtAlturaTotalMaximaKeyTyped

    private void txtAlturaTotalMediaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAlturaTotalMediaKeyTyped
       numeros.keyTyped(evt);
    }//GEN-LAST:event_txtAlturaTotalMediaKeyTyped

    private void txtAlturaTotalMinimaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAlturaTotalMinimaKeyTyped
       numeros.keyTyped(evt);
    }//GEN-LAST:event_txtAlturaTotalMinimaKeyTyped

    private void txtDiametroCoberturaMayorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiametroCoberturaMayorKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtDiametroCoberturaMayorKeyTyped

    private void txtDiametroCoberturaMenorFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDiametroCoberturaMenorFocusGained
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                txtDiametroCoberturaMenor.selectAll();
            }
        });
    }//GEN-LAST:event_txtDiametroCoberturaMenorFocusGained

    private void txtDiametroCoberturaMenorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDiametroCoberturaMenorFocusLost
         if(txtDiametroCoberturaMenor.getText().isEmpty()){
            txtDiametroCoberturaMenor.setValue(null);
        }
    }//GEN-LAST:event_txtDiametroCoberturaMenorFocusLost

    private void txtDiametroCoberturaMenorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiametroCoberturaMenorKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtDiametroCoberturaMenorKeyTyped

    private void btnColectaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnColectaActionPerformed
        try {
            int fila = grdVegetacionvMayor.getSelectedRow();
            String consecutivo = grdVegetacionvMayor.getValueAt(fila, 3).toString();
            FrmClaveColecta claveColecta = new FrmClaveColecta(Main.main, true);
            claveColecta.setLocationRelativeTo(Main.main);
            CatEFamiliaEspecie indexFamilia = (CatEFamiliaEspecie) cmbFamilia.getSelectedItem();
            CatEGenero indexGenero = (CatEGenero) cmbGenero.getSelectedItem();
            CatEEspecie indexEspecie = (CatEEspecie) cmbEspecie.getSelectedItem();
            CEColectaBotanica ceColecta = new CEColectaBotanica();
            ceColecta.setUPMID(this.upmID);
            if (indexFamilia != null) {
                ceColecta.setFamiliaID(indexFamilia.getFamiliaID());
            }
            if (indexGenero != null) {
                ceColecta.setGeneroID(indexGenero.getGeneroID());
            }
            if (indexEspecie != null) {
                ceColecta.setEspecieID(indexEspecie.getEspecieID());
            }
            //ceColecta.setInfraespecie(txtInfraespecie.getText());
            ceColecta.setNombreComun(txtNombreComun.getText());
            claveColecta.setDatosIniciales(ceColecta, FORMATO_ID, "TAXONOMIA_VegetacionMayorGregarios", "NoIndividuo", this.sitioID, Integer.parseInt(consecutivo));
            claveColecta.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar u registro para asignar la clave de colecta"
                    + e.getClass().getName() + " : " + e.getMessage(), "Clave de colecta", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnColectaActionPerformed

    private int consultarSitioID(int vegetacionMayorID){
        int sitioID=0;
        String query= "SELECT SitioID FROM TAXONOMIA_VegetacionMayorGregarios WHERE VegetacionMayorID="+vegetacionMayorID;
        //System.out.println("Consultar sitioID="+query);
        Connection conn = LocalConnection.getConnection();
       try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                sitioID= rs.getInt("SitioID");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los datos de Sitio ID ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            //return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en lista de Arbolado id", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        return sitioID;
    }
    
    private void chkVegetacionMayorMCGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkVegetacionMayorMCGActionPerformed
        if (!chkVegetacionMayorMCG.isSelected()) {
            Object[] opciones = {"Si", "No"};
            int respuesta = JOptionPane.showOptionDialog(null, "Si capturó, se eliminará la información de vegetación mayor gregarios del sitio " + this.sitio + ", ¿Esta seguro?",
                    "Vegetación mayor gregarios", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[1]);
            if (respuesta == JOptionPane.YES_OPTION) {
                String registro = grdVegetacionvMayor.getValueAt(0, 0).toString();
                this.vegetacionMayorID = Integer.parseInt(registro);
                this.cdVegetacionMayor.deleteAllVegetacionMayor(consultarSitioID(this.vegetacionMayorID));
                 llenarTabla();
                 funciones.reiniciarTabla(this.grdVegetacionvMayor);
                 limpiarControles();
                cmbConsecutivo.setEnabled(false);
                //txtNumeroIndividuo.setEnabled(false);
                cmbFormaVida.setEnabled(false);
                cmbCondicion.setEnabled(false);
                cmbFamilia.setEnabled(false);
                cmbGenero.setEnabled(false);
                cmbEspecie.setEnabled(false);
                cmbInfraespecie.setEnabled(false);
                txtNombreComun.setEnabled(false);
                cmbFormaCrecimiento.setEnabled(false);
                cmbDensidadColonia.setEnabled(false);
                txtAlturaTotalMaxima.setEnabled(false);
                txtAlturaTotalMedia.setEnabled(false);
                txtAlturaTotalMinima.setEnabled(false);
                txtDiametroCoberturaMayor.setEnabled(false);
                txtDiametroCoberturaMenor.setEnabled(false);
                cmbAgenteDanio1.setEnabled(false);
                cmbSeveridad1.setEnabled(false);
                cmbAgenteDanio2.setEnabled(false);
                cmbSeveridad2.setEnabled(false);
                cmbVigor.setEnabled(false);
            } 
        }else {
                cmbConsecutivo.setEnabled(true);
                //txtNumeroIndividuo.setEnabled(true);
                cmbFormaVida.setEnabled(true);
                cmbCondicion.setEnabled(true);
                cmbFamilia.setEnabled(true);
                cmbGenero.setEnabled(true);
                cmbEspecie.setEnabled(true);
                cmbInfraespecie.setEnabled(true);
                txtNombreComun.setEnabled(true);
                cmbFormaCrecimiento.setEnabled(true);
                cmbDensidadColonia.setEnabled(true);
                txtAlturaTotalMaxima.setEnabled(true);
                txtAlturaTotalMedia.setEnabled(true);
                txtAlturaTotalMinima.setEnabled(true);
                txtDiametroCoberturaMayor.setEnabled(true);
                txtDiametroCoberturaMenor.setEnabled(true);
                cmbAgenteDanio1.setEnabled(true);
                cmbSeveridad1.setEnabled(true);
                cmbAgenteDanio2.setEnabled(true);
                cmbSeveridad2.setEnabled(true);
                cmbVigor.setEnabled(true);
            }
    }//GEN-LAST:event_chkVegetacionMayorMCGActionPerformed

    private void cmbEspecieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEspecieActionPerformed
       CatEEspecie indexEspecie = (CatEEspecie) cmbEspecie.getSelectedItem();
        DefaultComboBoxModel dcm = (DefaultComboBoxModel) cmbInfraespecie.getModel();
        dcm.removeAllElements();
        if (indexEspecie != null) {
            fillCmbInfraespecie(indexEspecie.getEspecieID());
        }
    }//GEN-LAST:event_cmbEspecieActionPerformed

    private void cmbAgenteDanio1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAgenteDanio1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbAgenteDanio1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       limpiarControles();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnColecta;
    private javax.swing.JButton btnContinuar;
    private javax.swing.JButton btnElimnar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JCheckBox chkVegetacionMayorMCG;
    private javax.swing.JComboBox cmbAgenteDanio1;
    private javax.swing.JComboBox cmbAgenteDanio2;
    private javax.swing.JComboBox cmbCondicion;
    private javax.swing.JComboBox cmbConsecutivo;
    private javax.swing.JComboBox cmbDensidadColonia;
    private javax.swing.JComboBox cmbEspecie;
    private javax.swing.JComboBox cmbFamilia;
    private javax.swing.JComboBox cmbFormaCrecimiento;
    private javax.swing.JComboBox cmbFormaVida;
    private javax.swing.JComboBox cmbGenero;
    private javax.swing.JComboBox cmbInfraespecie;
    private javax.swing.JComboBox cmbSeveridad1;
    private javax.swing.JComboBox cmbSeveridad2;
    private javax.swing.JComboBox cmbVigor;
    private javax.swing.JTable grdVegetacionvMayor;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAlturaTotalMaxima;
    private javax.swing.JLabel lblAlturaTotalMedia;
    private javax.swing.JLabel lblAlturaTotalMinima;
    private javax.swing.JLabel lblClaveColecta;
    private javax.swing.JLabel lblCondicion;
    private javax.swing.JLabel lblConsecutivo;
    private javax.swing.JLabel lblDanio1;
    private javax.swing.JLabel lblDanio2;
    private javax.swing.JLabel lblDencidadColonia;
    private javax.swing.JLabel lblDiametroCoberturaMayor;
    private javax.swing.JLabel lblDiametroCoberturaMenor1;
    private javax.swing.JLabel lblEspecie;
    private javax.swing.JLabel lblFamilia;
    private javax.swing.JLabel lblFormaCrecimiento;
    private javax.swing.JLabel lblFormaVida;
    private javax.swing.JLabel lblGenero;
    private javax.swing.JLabel lblInfraespecie;
    private javax.swing.JLabel lblNombreComun;
    private javax.swing.JLabel lblNumeroIndividuo;
    private javax.swing.JLabel lblSeveridad1;
    private javax.swing.JLabel lblSeveridad2;
    private javax.swing.JLabel lblSitio;
    private javax.swing.JLabel lblUPM;
    private javax.swing.JLabel lblVegetacionMayorGragarios;
    private javax.swing.JFormattedTextField txtAlturaTotalMaxima;
    private javax.swing.JFormattedTextField txtAlturaTotalMedia;
    private javax.swing.JFormattedTextField txtAlturaTotalMinima;
    public javax.swing.JTextField txtClaveColecta;
    private javax.swing.JFormattedTextField txtDiametroCoberturaMayor;
    private javax.swing.JFormattedTextField txtDiametroCoberturaMenor;
    private javax.swing.JTextField txtNombreComun;
    private javax.swing.JFormattedTextField txtNumeroIndividuo;
    private javax.swing.JTextField txtSitio;
    private javax.swing.JTextField txtUPM;
    // End of variables declaration//GEN-END:variables
}
