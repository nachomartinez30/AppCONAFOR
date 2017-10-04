package gob.conafor.carbono.vie;

import com.itextpdf.text.log.SysoCounter;
import gob.conafor.carbono.mod.CDLongitud;
import gob.conafor.carbono.mod.CECoberturaDosel;
import gob.conafor.carbono.mod.CELongitudComponente;
import gob.conafor.carbono.mod.CatECarbonoComponente;
import gob.conafor.ini.vie.Main;
import gob.conafor.sitio.mod.CDSitio;
import gob.conafor.sitio.mod.CESitio;
import gob.conafor.sys.mod.CDSecuencia;
import gob.conafor.sys.mod.CDSeguimientoUPM;
import gob.conafor.sys.mod.CESeccionesCapturadas;
import gob.conafor.taxonomia.mod.CDColectaBotanica;
import gob.conafor.taxonomia.mod.CDEspecies;
import gob.conafor.taxonomia.mod.CEColectaBotanica;
import gob.conafor.taxonomia.mod.CatEEspecie;
import gob.conafor.taxonomia.mod.CatEFamiliaEspecie;
import gob.conafor.taxonomia.mod.CatEGenero;
import gob.conafor.taxonomia.mod.CatEInfraespecie;
import gob.conafor.taxonomia.vie.FrmClaveColecta;
import gob.conafor.upm.vie.UPMForms;
import gob.conafor.utils.Datos;
import gob.conafor.utils.FuncionesComunes;
import gob.conafor.utils.Tablas;
import gob.conafor.utils.Version;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class FrmLongitudInterceptada extends javax.swing.JInternalFrame {

    private boolean revision;
    private int upmID;
    private int sitioID;
    private int sitio;
    private int longitudInterceptada;
    private int suelo;
    private static final int FORMATO_ID = 8;
    private Integer transectoComponente;
    /* private CatECarbonoComponente indexComponente;
    private CatEFamiliaEspecie indexFamilia;
    private CatEGenero indexGenero;
    private CatEEspecie indexEspecie;*/
    private String infraespecie;
    private String nombreComun;
    private Integer segmento1;
    private Integer segmento2;
    private Integer segmento3;
    private Integer segmento4;
    private Integer segmento5;
    private Integer segmento6;
    private Integer segmento7;
    private Integer segmento8;
    private Integer segmento9;
    private Integer segmento10;
    private Integer total;
    private Integer transectoDosel;
    private int punto1;
    private int punto2;
    private int punto3;
    private int punto4;
    private int punto5;
    private int punto6;
    private int punto7;
    private int punto8;
    private int punto9;
    private int punto10;
    private int componenteID;
    private int coberturaID;
    private CDEspecies cdEspecie = new CDEspecies();
    private CDLongitud cdLongitud = new CDLongitud();
    private Datos numeros = new Datos();
    private CELongitudComponente ceComponente = new CELongitudComponente();
    private CECoberturaDosel ceCobertura = new CECoberturaDosel();
    private CESeccionesCapturadas seccionActual = new CESeccionesCapturadas();
    private CESeccionesCapturadas seccionSiguiente = new CESeccionesCapturadas();
    private CDSeguimientoUPM seguimiento = new CDSeguimientoUPM();
    private CESitio ceSitio = new CESitio();
    private FuncionesComunes combo = new FuncionesComunes();
    private CDEspecies cdEspecies = new CDEspecies();
    private CDSecuencia cdSecuencia = new CDSecuencia();
    private FuncionesComunes funciones = new FuncionesComunes();
    private int modificar;
    private Version ver = new Version();
    private String version = ver.getVersion();
    private CDSitio cdSitio = new CDSitio();

    public FrmLongitudInterceptada() {
        initComponents();
        this.longitudInterceptada = 17;
        this.suelo = 19;
        fillCmbTransectoComponente();
        fillCmbComponente();
        fillCmbFamilia();
        fillCmbGenero();
        //txtLongitud1.addKeyListener(numeros);
    }

    public void setDatosIniciales(CESitio sitio) {
        this.upmID = sitio.getUpmID();
        this.sitioID = sitio.getSitioID();
        this.sitio = sitio.getSitio();

        llenarTabla();
        llenarTablaCoberturaDosel();
        //funciones.reiniciarComboModel(cmbTransectoComponente);
        //cmbTransectoComponente.removeAllItems();
        funciones.reiniciarComboModel(cmbTransectoDosel);
        fillCmbTransectos();
        //fillCmbTransectoComponente();
        this.ceSitio.setUpmID(this.upmID);
        this.ceSitio.setSitioID(this.sitioID);
        this.ceSitio.setSitio(this.sitio);
        this.ceSitio = sitio;
        funciones.manipularBotonesMenuPrincipal(true);
        this.modificar = 0;
    }

    public void llenarControles() {
        combo.reiniciarComboModel(this.cmbUPMID);
        fillUPMID();
    }

    private void fillUPMID() {
        List<Integer> listCapturado = new ArrayList<>();
        listCapturado = this.cdSitio.getUPMSitios();
        if (listCapturado != null) {
            int size = listCapturado.size();
            for (int i = 0; i < size; i++) {
                cmbUPMID.addItem(listCapturado.get(i));
            }
        }
    }

    private void fillCmbSitio(int upmID) {
        List<Integer> listSitios = new ArrayList<>();
        listSitios = this.cdSitio.getSitiosDisponibles(upmID);
        if (listSitios != null) {
            int size = listSitios.size();
            for (int i = 0; i < size; i++) {
                cmbSitios.addItem(listSitios.get(i));
            }
        }
    }

    public void revisarLongitud(int sitio) {
        System.out.println("Degradacion de suelo= " + this.ceSitio.getSecuencia());
        /*revision = true;
        this.upmID = sitio.getUpmID();
        this.sitioID = sitio.getSitioID();
        this.sitio = sitio.getSitio();
         */
        llenarTabla();
        llenarTablaCoberturaDosel();
        /* funciones.reiniciarComboModel(cmbTransectoComponente);
        fillCmbTransectoComponente();*/
        funciones.reiniciarComboModel(cmbTransectoDosel);
        fillCmbTransectos();
        this.ceSitio.setUpmID(this.upmID);
        this.ceSitio.setSitioID(this.sitioID);
        this.ceSitio.setSitio(this.sitio);
        //this.ceSitio.setSecuencia(sitio.getSecuencia());
        funciones.manipularBotonesMenuPrincipal(true);
        //this.modificar = 1;
        limpiarControlesCobertura();
        limpiarControlesComponentes();
        chkLongitudComponentes.setSelected(funciones.habilitarCheckBox("CARBONO_LongitudComponente", this.sitioID));
        chkCoberturaDosel.setSelected(funciones.habilitarCheckBox("CARBONO_CoberturaDosel", this.sitioID));
    }

    private void fillCmbTransectoComponente() {
        List<Integer> listTransectos = new ArrayList<>();
        listTransectos = this.cdLongitud.getTransectoCoberturaDosel(this.sitioID);
        if (listTransectos != null) {
            int size = listTransectos.size();
            for (int i = 0; i < size; i++) {
                cmbTransectoComponente.addItem(listTransectos.get(i));
            }
        }
    }

    private void fillCmbComponente() {
        List<CatECarbonoComponente> listComponente = new ArrayList<>();
        listComponente = cdLongitud.getComponentesSegemento();
        if (listComponente != null) {
            int size = listComponente.size();
            for (int i = 0; i < size; i++) {
                cmbComponente.addItem(listComponente.get(i));
            }
        }
    }

    private void fillCmbFamilia() {
        List<CatEFamiliaEspecie> listFamilia = new ArrayList<>();
        listFamilia = cdEspecie.getFamiliaEspecies();
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

    private void fillCmbTransectos() {
        List<Integer> listTransectos = new ArrayList<>();
        listTransectos = this.cdLongitud.getTransectoCoberturaDosel(this.sitioID);
        if (listTransectos != null) {
            int size = listTransectos.size();
            for (int i = 0; i < size; i++) {
                cmbTransectoDosel.addItem(listTransectos.get(i));
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblCarbonoIncendios = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblComponente = new javax.swing.JLabel();
        cmbComponente = new javax.swing.JComboBox();
        lblFamilia = new javax.swing.JLabel();
        cmbFamilia = new javax.swing.JComboBox();
        lblGenero = new javax.swing.JLabel();
        cmbGenero = new javax.swing.JComboBox();
        lblEspecie = new javax.swing.JLabel();
        cmbEspecie = new javax.swing.JComboBox();
        lblInfraespecie = new javax.swing.JLabel();
        lblNombreComun = new javax.swing.JLabel();
        txtNombreComun = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        lblComponente1 = new javax.swing.JLabel();
        lblComponente2 = new javax.swing.JLabel();
        lblComponente3 = new javax.swing.JLabel();
        lblComponente4 = new javax.swing.JLabel();
        lblComponente5 = new javax.swing.JLabel();
        lblComponente6 = new javax.swing.JLabel();
        lblComponente7 = new javax.swing.JLabel();
        lblComponente8 = new javax.swing.JLabel();
        lblComponente9 = new javax.swing.JLabel();
        lblComponente10 = new javax.swing.JLabel();
        lblComponenteTotal = new javax.swing.JLabel();
        txtSegmento1 = new javax.swing.JFormattedTextField();
        txtSegmento2 = new javax.swing.JFormattedTextField();
        txtSegmento3 = new javax.swing.JFormattedTextField();
        txtSegmento4 = new javax.swing.JFormattedTextField();
        txtSegmento5 = new javax.swing.JFormattedTextField();
        txtSegmento6 = new javax.swing.JFormattedTextField();
        txtSegmento7 = new javax.swing.JFormattedTextField();
        txtSegmento8 = new javax.swing.JFormattedTextField();
        txtSegmento9 = new javax.swing.JFormattedTextField();
        txtSegmento10 = new javax.swing.JFormattedTextField();
        txtTotal = new javax.swing.JFormattedTextField();
        lblComponente11 = new javax.swing.JLabel();
        cmbTransectoComponente = new javax.swing.JComboBox();
        cmbInfraespecie = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        btnAgregarComponente = new javax.swing.JButton();
        btnModificarComponente = new javax.swing.JButton();
        btnEliminarComponente = new javax.swing.JButton();
        btnColecta = new javax.swing.JButton();
        lblClaveColecta = new javax.swing.JLabel();
        txtClaveColecta = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        grdComponentes = new javax.swing.JTable();
        lblCoberturaDosel = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lblTransecto = new javax.swing.JLabel();
        cmbTransectoDosel = new javax.swing.JComboBox();
        btnAgregarDosel = new javax.swing.JButton();
        btnModificarDosel = new javax.swing.JButton();
        btnEliminarDosel = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        chkPunto1 = new javax.swing.JCheckBox();
        jPanel8 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        chkPunto3 = new javax.swing.JCheckBox();
        jPanel9 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        chkPunto4 = new javax.swing.JCheckBox();
        jPanel10 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        chkPunto5 = new javax.swing.JCheckBox();
        jPanel11 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        chkPunto2 = new javax.swing.JCheckBox();
        jPanel12 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        chkPunto6 = new javax.swing.JCheckBox();
        jPanel13 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        chkPunto7 = new javax.swing.JCheckBox();
        jPanel14 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        chkPunto8 = new javax.swing.JCheckBox();
        jPanel15 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        chkPunto9 = new javax.swing.JCheckBox();
        jPanel16 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        chkPunto10 = new javax.swing.JCheckBox();
        jScrollPane4 = new javax.swing.JScrollPane();
        grdCoberturaDosel = new javax.swing.JTable();
        btnSalir = new javax.swing.JButton();
        lblUPM = new javax.swing.JLabel();
        lblSitio = new javax.swing.JLabel();
        chkCoberturaDosel = new javax.swing.JCheckBox();
        chkLongitudComponentes = new javax.swing.JCheckBox();
        cmbUPMID = new javax.swing.JComboBox<>();
        cmbSitios = new javax.swing.JComboBox<>();
        lblModulo = new javax.swing.JLabel();

        setMaximizable(true);
        setTitle("Longitud interceptada por componente, cobertura dosel, módulo A o C "+version);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/gob/conafor/utils/logo_conafor.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(940, 650));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        lblCarbonoIncendios.setBackground(new java.awt.Color(153, 153, 153));
        lblCarbonoIncendios.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCarbonoIncendios.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCarbonoIncendios.setText("Longitud interceptada por componente cm");
        lblCarbonoIncendios.setOpaque(true);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblComponente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblComponente.setText("Componente");

        cmbComponente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbComponenteActionPerformed(evt);
            }
        });

        lblFamilia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFamilia.setText("Familia");

        lblGenero.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGenero.setText("Genero");

        cmbGenero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbGeneroActionPerformed(evt);
            }
        });

        lblEspecie.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEspecie.setText("Especie");

        cmbEspecie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEspecieActionPerformed(evt);
            }
        });

        lblInfraespecie.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInfraespecie.setText("Infraespecie");

        lblNombreComun.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreComun.setText("Nombre común");

        txtNombreComun.setNextFocusableComponent(txtSegmento1);
        txtNombreComun.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNombreComunFocusGained(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblComponente1.setBackground(new java.awt.Color(153, 153, 153));
        lblComponente1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblComponente1.setText("1");
        lblComponente1.setOpaque(true);
        jPanel3.add(lblComponente1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, -1));

        lblComponente2.setBackground(new java.awt.Color(153, 153, 153));
        lblComponente2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblComponente2.setText("2");
        lblComponente2.setOpaque(true);
        jPanel3.add(lblComponente2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 60, -1));

        lblComponente3.setBackground(new java.awt.Color(153, 153, 153));
        lblComponente3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblComponente3.setText("3");
        lblComponente3.setOpaque(true);
        jPanel3.add(lblComponente3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, 60, -1));

        lblComponente4.setBackground(new java.awt.Color(153, 153, 153));
        lblComponente4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblComponente4.setText("4");
        lblComponente4.setOpaque(true);
        jPanel3.add(lblComponente4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, 60, -1));

        lblComponente5.setBackground(new java.awt.Color(153, 153, 153));
        lblComponente5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblComponente5.setText("5");
        lblComponente5.setOpaque(true);
        jPanel3.add(lblComponente5, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, 60, -1));

        lblComponente6.setBackground(new java.awt.Color(153, 153, 153));
        lblComponente6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblComponente6.setText("6");
        lblComponente6.setOpaque(true);
        jPanel3.add(lblComponente6, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, 60, -1));

        lblComponente7.setBackground(new java.awt.Color(153, 153, 153));
        lblComponente7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblComponente7.setText("7");
        lblComponente7.setOpaque(true);
        jPanel3.add(lblComponente7, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 0, 60, -1));

        lblComponente8.setBackground(new java.awt.Color(153, 153, 153));
        lblComponente8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblComponente8.setText("8");
        lblComponente8.setOpaque(true);
        jPanel3.add(lblComponente8, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 0, 60, -1));

        lblComponente9.setBackground(new java.awt.Color(153, 153, 153));
        lblComponente9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblComponente9.setText("9");
        lblComponente9.setOpaque(true);
        jPanel3.add(lblComponente9, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 0, 60, -1));

        lblComponente10.setBackground(new java.awt.Color(153, 153, 153));
        lblComponente10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblComponente10.setText("10");
        lblComponente10.setOpaque(true);
        jPanel3.add(lblComponente10, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 0, 60, -1));

        lblComponenteTotal.setBackground(new java.awt.Color(153, 153, 153));
        lblComponenteTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblComponenteTotal.setText("Total");
        lblComponenteTotal.setOpaque(true);
        jPanel3.add(lblComponenteTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 0, 60, 10));

        txtSegmento1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtSegmento1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSegmento1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSegmento1FocusLost(evt);
            }
        });
        txtSegmento1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSegmento1KeyTyped(evt);
            }
        });
        jPanel3.add(txtSegmento1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 60, -1));

        txtSegmento2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtSegmento2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSegmento2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSegmento2FocusLost(evt);
            }
        });
        txtSegmento2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSegmento2KeyTyped(evt);
            }
        });
        jPanel3.add(txtSegmento2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 60, -1));

        txtSegmento3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtSegmento3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSegmento3FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSegmento3FocusLost(evt);
            }
        });
        txtSegmento3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSegmento3KeyTyped(evt);
            }
        });
        jPanel3.add(txtSegmento3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 60, -1));

        txtSegmento4.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtSegmento4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSegmento4FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSegmento4FocusLost(evt);
            }
        });
        txtSegmento4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSegmento4KeyTyped(evt);
            }
        });
        jPanel3.add(txtSegmento4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, 60, -1));

        txtSegmento5.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtSegmento5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSegmento5FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSegmento5FocusLost(evt);
            }
        });
        txtSegmento5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSegmento5KeyTyped(evt);
            }
        });
        jPanel3.add(txtSegmento5, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 20, 60, -1));

        txtSegmento6.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtSegmento6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSegmento6FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSegmento6FocusLost(evt);
            }
        });
        txtSegmento6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSegmento6KeyTyped(evt);
            }
        });
        jPanel3.add(txtSegmento6, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, 60, -1));

        txtSegmento7.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtSegmento7.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSegmento7FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSegmento7FocusLost(evt);
            }
        });
        txtSegmento7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSegmento7KeyTyped(evt);
            }
        });
        jPanel3.add(txtSegmento7, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, 60, -1));

        txtSegmento8.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtSegmento8.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSegmento8FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSegmento8FocusLost(evt);
            }
        });
        txtSegmento8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSegmento8KeyTyped(evt);
            }
        });
        jPanel3.add(txtSegmento8, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 20, 60, -1));

        txtSegmento9.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtSegmento9.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSegmento9FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSegmento9FocusLost(evt);
            }
        });
        txtSegmento9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSegmento9KeyTyped(evt);
            }
        });
        jPanel3.add(txtSegmento9, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 20, 60, -1));

        txtSegmento10.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtSegmento10.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSegmento10FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSegmento10FocusLost(evt);
            }
        });
        txtSegmento10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSegmento10KeyTyped(evt);
            }
        });
        jPanel3.add(txtSegmento10, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 20, 60, -1));

        txtTotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtTotal.setEnabled(false);
        jPanel3.add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 20, 60, 20));

        lblComponente11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblComponente11.setText("Transecto");

        cmbTransectoComponente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTransectoComponenteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 868, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblComponente11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbTransectoComponente, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbComponente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblComponente, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFamilia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbFamilia, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblGenero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblEspecie, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                            .addComponent(cmbEspecie, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(lblInfraespecie, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(cmbInfraespecie, 0, 113, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNombreComun)
                            .addComponent(lblNombreComun, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFamilia)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblComponente)
                                .addComponent(lblComponente11))
                            .addComponent(lblInfraespecie)
                            .addComponent(lblNombreComun)
                            .addComponent(lblEspecie)
                            .addComponent(lblGenero)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbInfraespecie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombreComun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbComponente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbTransectoComponente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnAgregarComponente.setText("Agregar");
        btnAgregarComponente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarComponenteActionPerformed(evt);
            }
        });

        btnModificarComponente.setText("Modificar");
        btnModificarComponente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarComponenteActionPerformed(evt);
            }
        });

        btnEliminarComponente.setText("Eliminar");
        btnEliminarComponente.setNextFocusableComponent(btnColecta);
        btnEliminarComponente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarComponenteActionPerformed(evt);
            }
        });

        btnColecta.setText("Colecta");
        btnColecta.setNextFocusableComponent(chkCoberturaDosel);
        btnColecta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColectaActionPerformed(evt);
            }
        });

        lblClaveColecta.setText("Clave:");

        txtClaveColecta.setEditable(false);
        txtClaveColecta.setEnabled(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(135, Short.MAX_VALUE)
                .addComponent(btnAgregarComponente)
                .addGap(18, 18, 18)
                .addComponent(btnModificarComponente)
                .addGap(18, 18, 18)
                .addComponent(btnEliminarComponente)
                .addGap(42, 42, 42)
                .addComponent(btnColecta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblClaveColecta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtClaveColecta, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(155, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblClaveColecta)
                        .addComponent(txtClaveColecta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnColecta))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAgregarComponente)
                        .addComponent(btnModificarComponente)
                        .addComponent(btnEliminarComponente)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        grdComponentes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        grdComponentes.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        grdComponentes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grdComponentesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(grdComponentes);

        lblCoberturaDosel.setBackground(new java.awt.Color(153, 153, 153));
        lblCoberturaDosel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCoberturaDosel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCoberturaDosel.setText("Cobertura DOSEL");
        lblCoberturaDosel.setOpaque(true);

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTransecto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTransecto.setText("Transecto");

        cmbTransectoDosel.setNextFocusableComponent(chkPunto1);

        btnAgregarDosel.setText("Agregar");
        btnAgregarDosel.setNextFocusableComponent(btnModificarDosel);
        btnAgregarDosel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarDoselActionPerformed(evt);
            }
        });

        btnModificarDosel.setText("Modificar");
        btnModificarDosel.setNextFocusableComponent(btnEliminarDosel);
        btnModificarDosel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarDoselActionPerformed(evt);
            }
        });

        btnEliminarDosel.setText("Eliminar");
        btnEliminarDosel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarDoselActionPerformed(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));

        jPanel7.setBackground(new java.awt.Color(204, 204, 204));

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("1");

        chkPunto1.setBackground(new java.awt.Color(204, 204, 204));
        chkPunto1.setNextFocusableComponent(chkPunto2);
        chkPunto1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                chkPunto1KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chkPunto1)
                .addContainerGap(9, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkPunto1)
                .addGap(0, 7, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(204, 204, 204));

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("3");

        chkPunto3.setBackground(new java.awt.Color(204, 204, 204));
        chkPunto3.setNextFocusableComponent(chkPunto4);
        chkPunto3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                chkPunto3KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chkPunto3)
                .addContainerGap(9, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkPunto3)
                .addGap(0, 7, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(204, 204, 204));

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("4");

        chkPunto4.setBackground(new java.awt.Color(204, 204, 204));
        chkPunto4.setToolTipText("");
        chkPunto4.setNextFocusableComponent(chkPunto5);
        chkPunto4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                chkPunto4KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chkPunto4)
                .addContainerGap(9, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkPunto4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(204, 204, 204));

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("5");

        chkPunto5.setBackground(new java.awt.Color(204, 204, 204));
        chkPunto5.setNextFocusableComponent(chkPunto6);
        chkPunto5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                chkPunto5KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chkPunto5)
                .addContainerGap(9, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(chkPunto5)
                .addContainerGap())
        );

        jPanel11.setBackground(new java.awt.Color(204, 204, 204));

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("2");

        chkPunto2.setBackground(new java.awt.Color(204, 204, 204));
        chkPunto2.setNextFocusableComponent(chkPunto3);
        chkPunto2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                chkPunto2KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chkPunto2)
                .addContainerGap(9, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkPunto2)
                .addGap(0, 7, Short.MAX_VALUE))
        );

        jPanel12.setBackground(new java.awt.Color(204, 204, 204));

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("6");

        chkPunto6.setBackground(new java.awt.Color(204, 204, 204));
        chkPunto6.setNextFocusableComponent(chkPunto7);
        chkPunto6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                chkPunto6KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chkPunto6)
                .addContainerGap(9, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(chkPunto6)
                .addContainerGap())
        );

        jPanel13.setBackground(new java.awt.Color(204, 204, 204));

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("7");

        chkPunto7.setBackground(new java.awt.Color(204, 204, 204));
        chkPunto7.setNextFocusableComponent(chkPunto8);
        chkPunto7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                chkPunto7KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chkPunto7)
                .addContainerGap(9, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkPunto7)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel14.setBackground(new java.awt.Color(204, 204, 204));

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("8");

        chkPunto8.setBackground(new java.awt.Color(204, 204, 204));
        chkPunto8.setNextFocusableComponent(chkPunto9);
        chkPunto8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                chkPunto8KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chkPunto8)
                .addContainerGap(9, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkPunto8)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel15.setBackground(new java.awt.Color(204, 204, 204));

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("9");

        chkPunto9.setBackground(new java.awt.Color(204, 204, 204));
        chkPunto9.setNextFocusableComponent(chkPunto10);
        chkPunto9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                chkPunto9KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chkPunto9)
                .addContainerGap(9, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkPunto9)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel16.setBackground(new java.awt.Color(204, 204, 204));

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("10");

        chkPunto10.setBackground(new java.awt.Color(204, 204, 204));
        chkPunto10.setNextFocusableComponent(btnAgregarDosel);
        chkPunto10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                chkPunto10KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chkPunto10)
                .addContainerGap(9, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkPunto10)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbTransectoDosel, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTransecto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(btnAgregarDosel)
                .addGap(18, 18, 18)
                .addComponent(btnModificarDosel)
                .addGap(18, 18, 18)
                .addComponent(btnEliminarDosel)
                .addGap(35, 35, 35))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(lblTransecto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbTransectoDosel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAgregarDosel)
                            .addComponent(btnModificarDosel)
                            .addComponent(btnEliminarDosel))
                        .addGap(22, 22, 22))))
        );

        grdCoberturaDosel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        grdCoberturaDosel.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        grdCoberturaDosel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grdCoberturaDoselMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(grdCoberturaDosel);

        btnSalir.setMnemonic('s');
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        lblUPM.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblUPM.setText("UPMID:");

        lblSitio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblSitio.setText("Sitio:");

        chkCoberturaDosel.setBackground(new java.awt.Color(204, 204, 204));
        chkCoberturaDosel.setSelected(true);
        chkCoberturaDosel.setText("Cobertura DOSEL");
        chkCoberturaDosel.setNextFocusableComponent(cmbTransectoDosel);
        chkCoberturaDosel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCoberturaDoselActionPerformed(evt);
            }
        });

        chkLongitudComponentes.setBackground(new java.awt.Color(204, 204, 204));
        chkLongitudComponentes.setSelected(true);
        chkLongitudComponentes.setText("Longitud por componentes");
        chkLongitudComponentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLongitudComponentesActionPerformed(evt);
            }
        });

        cmbUPMID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbUPMIDActionPerformed(evt);
            }
        });

        cmbSitios.setEnabled(false);
        cmbSitios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSitiosActionPerformed(evt);
            }
        });

        lblModulo.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        lblModulo.setForeground(new java.awt.Color(255, 0, 0));
        lblModulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblModulo.setText("A");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(chkCoberturaDosel)
                        .addComponent(lblCoberturaDosel, javax.swing.GroupLayout.DEFAULT_SIZE, 892, Short.MAX_VALUE)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 892, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(chkLongitudComponentes)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1)
                        .addComponent(lblCarbonoIncendios, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 844, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(lblUPM)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(cmbUPMID, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblModulo)
                            .addGap(18, 18, 18)
                            .addComponent(lblSitio)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(cmbSitios, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblUPM)
                            .addComponent(cmbUPMID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblModulo)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSitio)
                            .addComponent(cmbSitios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblCarbonoIncendios)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(chkLongitudComponentes)
                .addGap(1, 1, 1)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(chkCoberturaDosel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCoberturaDosel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSalir)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void chkLongitudComponentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLongitudComponentesActionPerformed
        if (chkLongitudComponentes.isSelected()) {
            manipularControleslongitud(true);
        } else {
            Object[] opciones = {"Si", "No"};
            int respuesta = JOptionPane.showOptionDialog(null, "Si capturo, se borrarán los datos de longitud interceptada por componente, ¿Esta seguro?",
                    "Carbono e incendios", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[1]);
            if (respuesta == JOptionPane.YES_OPTION) {
                cdLongitud.deleteLongitudComponentesSitio(this.sitioID);
                this.funciones.reiniciarTabla(this.grdComponentes);
                llenarTabla();
                manipularControleslongitud(false);
                limpiarControlesComponentes();
            } else {
                chkLongitudComponentes.setSelected(true);
                chkLongitudComponentes.requestFocus();
            }
        }
    }//GEN-LAST:event_chkLongitudComponentesActionPerformed

    private void chkCoberturaDoselActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCoberturaDoselActionPerformed
        if (chkCoberturaDosel.isSelected()) {
            manipularControlesCobertura(true);
        } else {
            Object[] opciones = {"Si", "No"};
            int respuesta = JOptionPane.showOptionDialog(null, "Si capturo, se borrarán los datos de cobertura dosel, ¿Esta seguro?",
                    "Carbono e incendios", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[1]);
            if (respuesta == JOptionPane.YES_OPTION) {
                cdLongitud.deleteCoberturaDoselSitio(this.sitioID);
                this.funciones.reiniciarTabla(this.grdCoberturaDosel);
                llenarTablaCoberturaDosel();
                manipularControlesCobertura(false);
                limpiarControlesCobertura();
                this.funciones.reiniciarComboModel(cmbTransectoDosel);
                fillCmbTransectos();
            } else {
                chkCoberturaDosel.setSelected(true);
                chkCoberturaDosel.requestFocus();
            }
        }
    }//GEN-LAST:event_chkCoberturaDoselActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        if (revision == false) {//esta en modo de captura
            this.hide();
            funciones.manipularBotonesMenuPrincipal(false);
        }
        if (revision == true) {//entro a modo de revision
            //System.err.println("Modo Revision");
            this.hide();
            //UPMForms.revisionModulos.iniciarRevision();
            UPMForms.revisionModulos.setVisible(true);
            UPMForms.revisionModulos.manipularBonesMenuprincipal();
            revision = false;
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    private void grdCoberturaDoselMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grdCoberturaDoselMouseClicked
        if (evt.getButton() == 1) {
            int fila = grdCoberturaDosel.getSelectedRow();
            String coberID = grdCoberturaDosel.getValueAt(fila, 0).toString();
            this.coberturaID = Integer.parseInt(coberID);
            CECoberturaDosel ceDosel = new CECoberturaDosel();
            ceDosel = this.cdLongitud.getRegistroCoberturaDosel(this.coberturaID);
            Integer trans = ceDosel.getTransecto();
            cmbTransectoDosel.setSelectedItem(trans);

            if (ceDosel.getPunto1() == 1) {
                chkPunto1.setSelected(true);
            } else {
                chkPunto1.setSelected(false);
            }
            if (ceDosel.getPunto2() == 1) {
                chkPunto2.setSelected(true);
            } else {
                chkPunto2.setSelected(false);
            }
            if (ceDosel.getPunto3() == 1) {
                chkPunto3.setSelected(true);
            } else {
                chkPunto3.setSelected(false);
            }
            if (ceDosel.getPunto4() == 1) {
                chkPunto4.setSelected(true);
            } else {
                chkPunto4.setSelected(false);
            }
            if (ceDosel.getPunto5() == 1) {
                chkPunto5.setSelected(true);
            } else {
                chkPunto5.setSelected(false);
            }
            if (ceDosel.getPunto6() == 1) {
                chkPunto6.setSelected(true);
            } else {
                chkPunto6.setSelected(false);
            }
            if (ceDosel.getPunto7() == 1) {
                chkPunto7.setSelected(true);
            } else {
                chkPunto7.setSelected(false);
            }
            if (ceDosel.getPunto8() == 1) {
                chkPunto8.setSelected(true);
            } else {
                chkPunto8.setSelected(false);
            }
            if (ceDosel.getPunto9() == 1) {
                chkPunto9.setSelected(true);
            } else {
                chkPunto9.setSelected(false);
            }
            if (ceDosel.getPunto10() == 1) {
                chkPunto10.setSelected(true);
            } else {
                chkPunto10.setSelected(false);
            }
        }
    }//GEN-LAST:event_grdCoberturaDoselMouseClicked

    private void chkPunto10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chkPunto10KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (chkPunto10.isSelected()) {
                chkPunto10.setSelected(false);
            } else {
                chkPunto10.setSelected(true);
            }
        }
    }//GEN-LAST:event_chkPunto10KeyPressed

    private void chkPunto9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chkPunto9KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (chkPunto9.isSelected()) {
                chkPunto9.setSelected(false);
            } else {
                chkPunto9.setSelected(true);
            }
        }
    }//GEN-LAST:event_chkPunto9KeyPressed

    private void chkPunto8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chkPunto8KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (chkPunto8.isSelected()) {
                chkPunto8.setSelected(false);
            } else {
                chkPunto8.setSelected(true);
            }
        }
    }//GEN-LAST:event_chkPunto8KeyPressed

    private void chkPunto7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chkPunto7KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (chkPunto7.isSelected()) {
                chkPunto7.setSelected(false);
            } else {
                chkPunto7.setSelected(true);
            }
        }
    }//GEN-LAST:event_chkPunto7KeyPressed

    private void chkPunto6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chkPunto6KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (chkPunto6.isSelected()) {
                chkPunto6.setSelected(false);
            } else {
                chkPunto6.setSelected(true);
            }
        }
    }//GEN-LAST:event_chkPunto6KeyPressed

    private void chkPunto2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chkPunto2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (chkPunto2.isSelected()) {
                chkPunto2.setSelected(false);
            } else {
                chkPunto2.setSelected(true);
            }
        }
    }//GEN-LAST:event_chkPunto2KeyPressed

    private void chkPunto5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chkPunto5KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (chkPunto5.isSelected()) {
                chkPunto5.setSelected(false);
            } else {
                chkPunto5.setSelected(true);
            }
        }
    }//GEN-LAST:event_chkPunto5KeyPressed

    private void chkPunto4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chkPunto4KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (chkPunto4.isSelected()) {
                chkPunto4.setSelected(false);
            } else {
                chkPunto4.setSelected(true);
            }
        }
    }//GEN-LAST:event_chkPunto4KeyPressed

    private void chkPunto3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chkPunto3KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (chkPunto3.isSelected()) {
                chkPunto3.setSelected(false);
            } else {
                chkPunto3.setSelected(true);
            }
        }
    }//GEN-LAST:event_chkPunto3KeyPressed

    private void chkPunto1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chkPunto1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (chkPunto1.isSelected()) {
                chkPunto1.setSelected(false);
            } else {
                chkPunto1.setSelected(true);
            }
        }
    }//GEN-LAST:event_chkPunto1KeyPressed

    private void btnEliminarDoselActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarDoselActionPerformed
        DefaultComboBoxModel dcm = (DefaultComboBoxModel) cmbTransectoDosel.getModel();
        if (combo.isEnabledCmbSitios(cmbSitios) == false) {

        } else {
            eliminarCoberturaDosel();
            funciones.reiniciarTabla(this.grdCoberturaDosel);
            llenarTablaCoberturaDosel();
            limpiarControlesCobertura();
            dcm.removeAllElements();
            fillCmbTransectos();
            btnAgregarDosel.setEnabled(true);
        }
    }//GEN-LAST:event_btnEliminarDoselActionPerformed

    private void btnModificarDoselActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarDoselActionPerformed
        asignarDatosDosel();
        if (combo.isEnabledCmbSitios(cmbSitios) == false) {

        } else {
            actualizarCoberturaDosel();
            llenarTablaCoberturaDosel();
            limpiarControlesCobertura();
            btnAgregarDosel.setEnabled(true);
        }
    }//GEN-LAST:event_btnModificarDoselActionPerformed

    private void btnAgregarDoselActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarDoselActionPerformed
        DefaultComboBoxModel dcm = (DefaultComboBoxModel) cmbTransectoDosel.getModel();
        asignarDatosDosel();
        if (combo.isEnabledCmbSitios(cmbSitios) == false) {

        } else {
            if (validarCoberturaDosel()) {
                crearCoberturaDosel();
                llenarTablaCoberturaDosel();
                limpiarControlesCobertura();
                dcm.removeAllElements();
                fillCmbTransectos();
            }
        }
    }//GEN-LAST:event_btnAgregarDoselActionPerformed

    private void grdComponentesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grdComponentesMouseClicked
        if (evt.getButton() == 1) {
            int fila = grdComponentes.getSelectedRow();
            String compID = grdComponentes.getValueAt(fila, 0).toString();
            this.componenteID = Integer.parseInt(compID);
            CELongitudComponente ceLongitud = new CELongitudComponente();
            ceLongitud = this.cdLongitud.getRegistroSegementoComponente(this.componenteID);
            Integer trans = ceLongitud.getTransecto();
            cmbTransectoComponente.setSelectedItem(trans);
            CatECarbonoComponente comp = new CatECarbonoComponente();
            comp.setComponenteID(ceLongitud.getComponenteID());
            cmbComponente.setSelectedItem(comp);
            CatEFamiliaEspecie fami = new CatEFamiliaEspecie();
            fami.setFamiliaID(ceLongitud.getFamiliaID());
            cmbFamilia.setSelectedItem(fami);

            CatEGenero gen = new CatEGenero();
            gen.setGeneroID(ceLongitud.getGeneroID());
            cmbGenero.setSelectedItem(gen);

            CatEEspecie esp = new CatEEspecie();
            esp.setEspecieID(ceLongitud.getEspecieID());
            cmbEspecie.removeAllItems();
            cmbEspecie.setSelectedItem(esp);

            CatEInfraespecie inf = new CatEInfraespecie();
            inf.setInfraespecieID(ceLongitud.getInfraespecieID());
            cmbInfraespecie.removeAllItems();
            fillCmbInfraespecie(ceLongitud.getEspecieID());
            cmbInfraespecie.setSelectedItem(inf);

            txtNombreComun.setText(ceLongitud.getNombreComun());
            txtSegmento1.setText(String.valueOf(ceLongitud.getSegmento1()));
            if ((txtSegmento1.getText()).equals("0.0")) {
                txtSegmento1.setText("");
            }
            txtSegmento2.setText(String.valueOf(ceLongitud.getSegmento2()));
            if ((txtSegmento2.getText()).equals("0.0")) {
                txtSegmento2.setText("");
            }
            txtSegmento3.setText(String.valueOf(ceLongitud.getSegmento3()));
            if ((txtSegmento3.getText()).equals("0.0")) {
                txtSegmento3.setText("");
            }
            txtSegmento4.setText(String.valueOf(ceLongitud.getSegmento4()));
            if ((txtSegmento4.getText()).equals("0.0")) {
                txtSegmento4.setText("");
            }
            txtSegmento5.setText(String.valueOf(ceLongitud.getSegmento5()));
            if ((txtSegmento5.getText()).equals("0.0")) {
                txtSegmento5.setText("");
            }
            txtSegmento6.setText(String.valueOf(ceLongitud.getSegmento6()));
            if ((txtSegmento6.getText()).equals("0.0")) {
                txtSegmento6.setText("");
            }
            txtSegmento7.setText(String.valueOf(ceLongitud.getSegmento7()));
            if ((txtSegmento7.getText()).equals("0.0")) {
                txtSegmento7.setText("");
            }
            txtSegmento8.setText(String.valueOf(ceLongitud.getSegmento8()));
            if ((txtSegmento8.getText()).equals("0.0")) {
                txtSegmento8.setText("");
            }
            txtSegmento9.setText(String.valueOf(ceLongitud.getSegmento9()));
            if ((txtSegmento9.getText()).equals("0.0")) {
                txtSegmento9.setText("");
            }
            txtSegmento10.setText(String.valueOf(ceLongitud.getSegmento10()));
            if ((txtSegmento10.getText()).equals("0.0")) {
                txtSegmento10.setText("");
            }
            txtTotal.setText(String.valueOf(ceLongitud.getTotal()));
            if ((txtTotal.getText()).equals("0.0")) {
                txtTotal.setText("");
            }
            txtClaveColecta.setText(ceLongitud.getColectaBotanica());
        }
    }//GEN-LAST:event_grdComponentesMouseClicked

    private void btnColectaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnColectaActionPerformed
        try {
            if (combo.isEnabledCmbSitios(cmbSitios) == false) {

            } else {
                int fila = grdComponentes.getSelectedRow();
                String consecutivo = grdComponentes.getValueAt(fila, 2).toString();
                FrmClaveColecta claveColecta = new FrmClaveColecta(Main.main, true);
                claveColecta.setLocationRelativeTo(Main.main);
                CatEFamiliaEspecie indexFamilia = (CatEFamiliaEspecie) cmbFamilia.getSelectedItem();
                CatEGenero indexGenero = (CatEGenero) cmbGenero.getSelectedItem();
                CatEEspecie indexEspecie = (CatEEspecie) cmbEspecie.getSelectedItem();
                CEColectaBotanica ceColecta = new CEColectaBotanica();
                if (indexFamilia != null) {
                    ceColecta.setFamiliaID(indexFamilia.getFamiliaID());
                }
                if (indexGenero != null) {
                    ceColecta.setGeneroID(indexGenero.getGeneroID());
                }
                if (indexEspecie != null) {
                    ceColecta.setEspecieID(indexEspecie.getEspecieID());
                }
                ceColecta.setUPMID(this.upmID);
                //ceColecta.setInfraespecie(txtInfraespecie.getText());
                ceColecta.setNombreComun(txtNombreComun.getText());
                claveColecta.setDatosIniciales(ceColecta, FORMATO_ID, "CARBONO_LongitudComponente", "Consecutivo", this.sitioID, Integer.parseInt(consecutivo));
                claveColecta.setVisible(true);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un registro para asignar la clave de colecta"
                    + e.getClass().getName() + " : " + e.getMessage(), "Clave de colecta", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnColectaActionPerformed

    private void btnEliminarComponenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarComponenteActionPerformed
        if (combo.isEnabledCmbSitios(cmbSitios) == false) {

        } else {
            eliminarComponente();
            this.cdLongitud.enumerarConsecutivo(this.sitioID);
            funciones.reiniciarTabla(this.grdComponentes);
            llenarTabla();
            limpiarControlesComponentes();
        }
    }//GEN-LAST:event_btnEliminarComponenteActionPerformed

    private void btnModificarComponenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarComponenteActionPerformed
        asignarDatosLongitudComponente();
        if (combo.isEnabledCmbSitios(cmbSitios) == false) {

        } else {
            if (validarComponentes()) {
                actualizarComponente();
                funciones.reiniciarTabla(this.grdComponentes);
                llenarTabla();
                reiniciarValoresComponentes();
                limpiarControlesComponentes();
            }
        }
    }//GEN-LAST:event_btnModificarComponenteActionPerformed

    private void btnAgregarComponenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarComponenteActionPerformed
        asignarDatosLongitudComponente();
        if (combo.isEnabledCmbSitios(cmbSitios) == false) {

        } else {
            if (validarComponentes() /*&& validarLongitudComponente()*/) {
                crearComponente();
                this.cdLongitud.enumerarConsecutivo(this.sitioID);
                funciones.reiniciarTabla(this.grdComponentes);
                llenarTabla();
                reiniciarValoresComponentes();
                limpiarControlesComponentes();
            }
        }
    }//GEN-LAST:event_btnAgregarComponenteActionPerformed

    private void cmbTransectoComponenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTransectoComponenteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTransectoComponenteActionPerformed

    private void txtSegmento10KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSegmento10KeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtSegmento10KeyTyped

    private void txtSegmento10FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSegmento10FocusLost
        if (txtSegmento10.getText().isEmpty()) {
            txtSegmento10.setValue(null);
        }
        asignarDatosLongitudComponente();
    }//GEN-LAST:event_txtSegmento10FocusLost

    private void txtSegmento10FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSegmento10FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtSegmento10.selectAll();
            }
        });
    }//GEN-LAST:event_txtSegmento10FocusGained

    private void txtSegmento9KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSegmento9KeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtSegmento9KeyTyped

    private void txtSegmento9FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSegmento9FocusLost
        if (txtSegmento9.getText().isEmpty()) {
            txtSegmento9.setValue(null);
        }
        asignarDatosLongitudComponente();
    }//GEN-LAST:event_txtSegmento9FocusLost

    private void txtSegmento9FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSegmento9FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtSegmento9.selectAll();
            }
        });
    }//GEN-LAST:event_txtSegmento9FocusGained

    private void txtSegmento8KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSegmento8KeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtSegmento8KeyTyped

    private void txtSegmento8FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSegmento8FocusLost
        if (txtSegmento8.getText().isEmpty()) {
            txtSegmento8.setValue(null);
        }
        asignarDatosLongitudComponente();
    }//GEN-LAST:event_txtSegmento8FocusLost

    private void txtSegmento8FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSegmento8FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtSegmento8.selectAll();
            }
        });
    }//GEN-LAST:event_txtSegmento8FocusGained

    private void txtSegmento7KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSegmento7KeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtSegmento7KeyTyped

    private void txtSegmento7FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSegmento7FocusLost
        if (txtSegmento7.getText().isEmpty()) {
            txtSegmento7.setValue(null);
        }
        asignarDatosLongitudComponente();
    }//GEN-LAST:event_txtSegmento7FocusLost

    private void txtSegmento7FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSegmento7FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtSegmento7.selectAll();
            }
        });
    }//GEN-LAST:event_txtSegmento7FocusGained

    private void txtSegmento6KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSegmento6KeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtSegmento6KeyTyped

    private void txtSegmento6FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSegmento6FocusLost
        if (txtSegmento6.getText().isEmpty()) {
            txtSegmento6.setValue(null);
        }
        asignarDatosLongitudComponente();
    }//GEN-LAST:event_txtSegmento6FocusLost

    private void txtSegmento6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSegmento6FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtSegmento6.selectAll();
            }
        });
    }//GEN-LAST:event_txtSegmento6FocusGained

    private void txtSegmento5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSegmento5KeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtSegmento5KeyTyped

    private void txtSegmento5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSegmento5FocusLost
        if (txtSegmento5.getText().isEmpty()) {
            txtSegmento5.setValue(null);
        }
        asignarDatosLongitudComponente();
    }//GEN-LAST:event_txtSegmento5FocusLost

    private void txtSegmento5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSegmento5FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtSegmento5.selectAll();
            }
        });
    }//GEN-LAST:event_txtSegmento5FocusGained

    private void txtSegmento4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSegmento4KeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtSegmento4KeyTyped

    private void txtSegmento4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSegmento4FocusLost
        if (txtSegmento4.getText().isEmpty()) {
            txtSegmento4.setValue(null);
        }
        asignarDatosLongitudComponente();
    }//GEN-LAST:event_txtSegmento4FocusLost

    private void txtSegmento4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSegmento4FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtSegmento4.selectAll();
            }
        });
    }//GEN-LAST:event_txtSegmento4FocusGained

    private void txtSegmento3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSegmento3KeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtSegmento3KeyTyped

    private void txtSegmento3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSegmento3FocusLost
        if (txtSegmento3.getText().isEmpty()) {
            txtSegmento3.setValue(null);
        }
        asignarDatosLongitudComponente();
    }//GEN-LAST:event_txtSegmento3FocusLost

    private void txtSegmento3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSegmento3FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtSegmento3.selectAll();
            }
        });
    }//GEN-LAST:event_txtSegmento3FocusGained

    private void txtSegmento2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSegmento2KeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtSegmento2KeyTyped

    private void txtSegmento2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSegmento2FocusLost
        if (txtSegmento2.getText().isEmpty()) {
            txtSegmento2.setValue(null);
        }
        asignarDatosLongitudComponente();
    }//GEN-LAST:event_txtSegmento2FocusLost

    private void txtSegmento2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSegmento2FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtSegmento2.selectAll();
            }
        });
    }//GEN-LAST:event_txtSegmento2FocusGained

    private void txtSegmento1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSegmento1KeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtSegmento1KeyTyped

    private void txtSegmento1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSegmento1FocusLost
        if (txtSegmento1.getText().isEmpty()) {
            txtSegmento1.setValue(null);
        }
        asignarDatosLongitudComponente();
    }//GEN-LAST:event_txtSegmento1FocusLost

    private void txtSegmento1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSegmento1FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtSegmento1.selectAll();
            }
        });
    }//GEN-LAST:event_txtSegmento1FocusGained

    private void txtNombreComunFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombreComunFocusGained
        txtNombreComun.selectAll();
    }//GEN-LAST:event_txtNombreComunFocusGained

    private void cmbEspecieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEspecieActionPerformed
        CatEEspecie indexEspecie = (CatEEspecie) cmbEspecie.getSelectedItem();
        DefaultComboBoxModel dcm = (DefaultComboBoxModel) cmbInfraespecie.getModel();
        dcm.removeAllElements();
        if (indexEspecie != null) {
            fillCmbInfraespecie(indexEspecie.getEspecieID());
        }
    }//GEN-LAST:event_cmbEspecieActionPerformed

    private void cmbGeneroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbGeneroActionPerformed
        CatEGenero indexGenero = (CatEGenero) cmbGenero.getSelectedItem();
        CatEEspecie indexFamilia = (CatEEspecie) cmbEspecie.getSelectedItem();
        DefaultComboBoxModel dcm = (DefaultComboBoxModel) cmbEspecie.getModel();
        dcm.removeAllElements();
        CatEFamiliaEspecie familia;
        if (indexFamilia == null && indexGenero != null) {
            familia = this.cdEspecies.getFamilia(indexGenero.getGeneroID());
            cmbEspecie.setSelectedItem(familia);
        }
        if (indexGenero != null) {
            fillCmbEspecie(indexGenero.getGeneroID());
        }
    }//GEN-LAST:event_cmbGeneroActionPerformed

    private void cmbComponenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbComponenteActionPerformed
        CatECarbonoComponente componente = (CatECarbonoComponente) cmbComponente.getSelectedItem();
        if (componente != null) {
            if (componente.getComponenteID() > 4) {
                cmbFamilia.setSelectedItem(null);
                cmbFamilia.setEnabled(false);
                cmbGenero.setSelectedItem(null);
                cmbGenero.setEnabled(false);
                cmbEspecie.setSelectedItem(null);
                cmbEspecie.setEnabled(false);
                cmbInfraespecie.setSelectedItem(null);
                cmbInfraespecie.setEnabled(false);
                txtNombreComun.setText("");
                txtNombreComun.setEnabled(false);

            } else {
                cmbFamilia.setEnabled(true);
                cmbGenero.setEnabled(true);
                cmbEspecie.setEnabled(true);
                cmbInfraespecie.setEnabled(true);
                txtNombreComun.setEnabled(true);

            }
        }
    }//GEN-LAST:event_cmbComponenteActionPerformed

    private void cmbUPMIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbUPMIDActionPerformed
        Integer upmID = (Integer) cmbUPMID.getSelectedItem();
        this.upmID = (Integer) cmbUPMID.getSelectedItem();
        Integer sitio = (Integer) cmbSitios.getSelectedItem();
        if (cmbUPMID.getSelectedItem() != null) {
            combo.reiniciarComboModel(cmbSitios);
            fillCmbSitio(upmID);
            cmbSitios.setEnabled(true);
        } else {
            combo.reiniciarComboModel(cmbSitios);
            cmbSitios.setSelectedItem(null);
            cmbSitios.setEnabled(false);
        }
    }//GEN-LAST:event_cmbUPMIDActionPerformed

    private void cmbSitiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSitiosActionPerformed
        try {
            //System.out.println("item selected=\t"+cmbSitios.getSelectedItem());
            if (cmbSitios.getSelectedItem() == null) {
                this.sitioID = 0;
                /*limpiarControles();
                limpiarPorcentajes();*/
            } else {
                String upm = cmbUPMID.getSelectedItem().toString();
                String sitio = cmbSitios.getSelectedItem().toString();
                this.sitioID = cdSitio.getSitioIDNuevo(upm, sitio);

            }
            revisarLongitud(this.sitioID);

//llenarTabla();
        } catch (Exception e) {
            e.getCause();
        }
    }//GEN-LAST:event_cmbSitiosActionPerformed

    public void llenarTabla() {
        grdComponentes.setModel(cdLongitud.getTablaComponentes(this.sitioID));

        grdComponentes.getColumnModel().getColumn(2).setPreferredWidth(80);//Consecutivo
        grdComponentes.getColumnModel().getColumn(3).setPreferredWidth(80);//Transecto
        grdComponentes.getColumnModel().getColumn(4).setPreferredWidth(150);//Componente
        grdComponentes.getColumnModel().getColumn(5).setPreferredWidth(150);//Familia
        grdComponentes.getColumnModel().getColumn(6).setPreferredWidth(150);//Genero
        grdComponentes.getColumnModel().getColumn(7).setPreferredWidth(150);//Especie
        grdComponentes.getColumnModel().getColumn(8).setPreferredWidth(150);//Infraespecie
        grdComponentes.getColumnModel().getColumn(9).setPreferredWidth(150);//Nombre comun
        grdComponentes.getColumnModel().getColumn(10).setPreferredWidth(70);//Segmento 1
        grdComponentes.getColumnModel().getColumn(11).setPreferredWidth(70);//Segmento 2
        grdComponentes.getColumnModel().getColumn(12).setPreferredWidth(70);//Segmento 3
        grdComponentes.getColumnModel().getColumn(13).setPreferredWidth(70);//Segmento 4
        grdComponentes.getColumnModel().getColumn(14).setPreferredWidth(70);//Segmento 5
        grdComponentes.getColumnModel().getColumn(15).setPreferredWidth(70);//Segmento 6
        grdComponentes.getColumnModel().getColumn(16).setPreferredWidth(70);//Segmento 7
        grdComponentes.getColumnModel().getColumn(17).setPreferredWidth(70);//Segmento 8
        grdComponentes.getColumnModel().getColumn(18).setPreferredWidth(70);//Segmento 9
        grdComponentes.getColumnModel().getColumn(19).setPreferredWidth(75);//Segmento 10
        grdComponentes.getColumnModel().getColumn(20).setPreferredWidth(70);//Total
        grdComponentes.getColumnModel().getColumn(21).setPreferredWidth(160);//Clave colecta

        Tablas tablas = new Tablas();
        int[] column_0 = {0};
        int[] column_1 = {1};

        tablas.hideColumnTable(grdComponentes, column_0);
        tablas.hideColumnTable(grdComponentes, column_1);
    }

    private void llenarTablaCoberturaDosel() {
        grdCoberturaDosel.setModel(cdLongitud.getTablaCoberturaDosel(this.sitioID));

        grdCoberturaDosel.getColumnModel().getColumn(2).setPreferredWidth(83);
        grdCoberturaDosel.getColumnModel().getColumn(3).setPreferredWidth(83);
        grdCoberturaDosel.getColumnModel().getColumn(4).setPreferredWidth(83);
        grdCoberturaDosel.getColumnModel().getColumn(5).setPreferredWidth(83);
        grdCoberturaDosel.getColumnModel().getColumn(6).setPreferredWidth(83);
        grdCoberturaDosel.getColumnModel().getColumn(7).setPreferredWidth(83);
        grdCoberturaDosel.getColumnModel().getColumn(8).setPreferredWidth(83);
        grdCoberturaDosel.getColumnModel().getColumn(9).setPreferredWidth(83);
        grdCoberturaDosel.getColumnModel().getColumn(10).setPreferredWidth(83);
        grdCoberturaDosel.getColumnModel().getColumn(11).setPreferredWidth(83);
        grdCoberturaDosel.getColumnModel().getColumn(12).setPreferredWidth(84);

        Tablas tablas = new Tablas();

        int[] column_0 = {0};
        int[] column_1 = {1};

        tablas.hideColumnTable(grdCoberturaDosel, column_0);
        tablas.hideColumnTable(grdCoberturaDosel, column_1);
    }

    private void asignarDatosLongitudComponente() {
        this.total = 0;
        this.nombreComun = txtNombreComun.getText();
        try {
            this.segmento1 = Integer.valueOf(txtSegmento1.getText());
            this.total = this.total + this.segmento1;
            txtTotal.setText(String.valueOf(this.total));
        } catch (NumberFormatException e) {
            this.segmento1 = null;
        }
        try {
            this.segmento2 = Integer.valueOf(txtSegmento2.getText());
            this.total = this.total + this.segmento2;
            txtTotal.setText(String.valueOf(this.total));
        } catch (NumberFormatException e) {
            this.segmento2 = null;
        }
        try {
            this.segmento3 = Integer.valueOf(txtSegmento3.getText());
            this.total = this.total + this.segmento3;
            txtTotal.setText(String.valueOf(this.total));
        } catch (NumberFormatException e) {
            this.segmento3 = null;
        }
        try {
            this.segmento4 = Integer.valueOf(txtSegmento4.getText());
            this.total = this.total + this.segmento4;
            txtTotal.setText(String.valueOf(this.total));
        } catch (NumberFormatException e) {
            this.segmento4 = null;
        }
        try {
            this.segmento5 = Integer.valueOf(txtSegmento5.getText());
            this.total = this.total + this.segmento5;
            txtTotal.setText(String.valueOf(this.total));
        } catch (NumberFormatException e) {
            this.segmento5 = null;
        }
        try {
            this.segmento6 = Integer.valueOf(txtSegmento6.getText());
            this.total = this.total + this.segmento6;
            txtTotal.setText(String.valueOf(this.total));
        } catch (NumberFormatException e) {
            this.segmento6 = null;
        }
        try {
            this.segmento7 = Integer.valueOf(txtSegmento7.getText());
            this.total = this.total + this.segmento7;
            txtTotal.setText(String.valueOf(this.total));
        } catch (NumberFormatException e) {
            this.segmento7 = null;
        }
        try {
            this.segmento8 = Integer.valueOf(txtSegmento8.getText());
            this.total = this.total + this.segmento8;
            txtTotal.setText(String.valueOf(this.total));
        } catch (NumberFormatException e) {
            this.segmento8 = null;
        }
        try {
            this.segmento9 = Integer.valueOf(txtSegmento9.getText());
            this.total = this.total + this.segmento9;
            txtTotal.setText(String.valueOf(this.total));
        } catch (NumberFormatException e) {
            this.segmento9 = null;
        }
        try {
            this.segmento10 = Integer.valueOf(txtSegmento10.getText());
            this.total = this.total + this.segmento10;
            txtTotal.setText(String.valueOf(this.total));
        } catch (NumberFormatException e) {
            this.segmento10 = null;
        }
        try {
            txtTotal.setText(String.valueOf(this.total));
        } catch (NumberFormatException e) {
            this.total = null;
        }
    }

    private void reiniciarValoresComponentes() {
        this.segmento1 = null;
        this.segmento2 = null;
        this.segmento3 = null;
        this.segmento4 = null;
        this.segmento5 = null;
        this.segmento6 = null;
        this.segmento7 = null;
        this.segmento8 = null;
        this.segmento9 = null;
        this.segmento10 = null;
        this.total = null;
    }

    private void asignarDatosDosel() {
        this.transectoDosel = (Integer) cmbTransectoDosel.getSelectedItem();
        if (chkPunto1.isSelected()) {
            punto1 = 1;
        } else {
            punto1 = 0;
        }
        if (chkPunto2.isSelected()) {
            punto2 = 1;
        } else {
            punto2 = 0;
        }
        if (chkPunto3.isSelected()) {
            punto3 = 1;
        } else {
            punto3 = 0;
        }
        if (chkPunto4.isSelected()) {
            punto4 = 1;
        } else {
            punto4 = 0;
        }
        if (chkPunto5.isSelected()) {
            punto5 = 1;
        } else {
            punto5 = 0;
        }
        if (chkPunto6.isSelected()) {
            punto6 = 1;
        } else {
            punto6 = 0;
        }
        if (chkPunto7.isSelected()) {
            punto7 = 1;
        } else {
            punto7 = 0;
        }
        if (chkPunto8.isSelected()) {
            punto8 = 1;
        } else {
            punto8 = 0;
        }
        if (chkPunto9.isSelected()) {
            punto9 = 1;
        } else {
            punto9 = 0;
        }
        if (chkPunto10.isSelected()) {
            punto10 = 1;
        } else {
            punto10 = 0;
        }
    }

    private boolean validarComponentes() {
        CatECarbonoComponente indexComponente = (CatECarbonoComponente) cmbComponente.getSelectedItem();
        this.transectoComponente = (Integer) cmbTransectoComponente.getSelectedItem();
        try {
            if (this.transectoComponente == null) {
                JOptionPane.showMessageDialog(null, "Error! Debe seleccionar un transecto para componente ",
                        "longitud interceptada por componente", JOptionPane.INFORMATION_MESSAGE);
                cmbTransectoComponente.requestFocus();
                return false;
            } else if (indexComponente == null) {
                JOptionPane.showMessageDialog(null, "Error! Debe seleccionar un componente ",
                        "longitud interceptada por componente", JOptionPane.INFORMATION_MESSAGE);
                cmbComponente.requestFocus();
                return false;
            } else if (this.segmento1 != null && this.segmento1 < 0 || this.segmento1 > 100) {
                JOptionPane.showMessageDialog(null, "Error! Debe proporcionar un valor entre 0 y 100 para el segmento 1 ",
                        "longitud interceptada por componente", JOptionPane.INFORMATION_MESSAGE);
                txtSegmento1.requestFocus();
                return false;
            } else if (this.segmento2 != null && this.segmento2 < 0 || this.segmento2 > 100) {
                JOptionPane.showMessageDialog(null, "Error! Debe proporcionar un valor entre 0 y 100 para el segmento 2 ",
                        "longitud interceptada por componente", JOptionPane.INFORMATION_MESSAGE);
                txtSegmento2.requestFocus();
                return false;
            } else if (this.segmento3 != null && this.segmento3 < 0 || this.segmento3 > 100) {
                JOptionPane.showMessageDialog(null, "Error! Debe proporcionar un valor entre 0 y 100 para el segmento 3 ",
                        "longitud interceptada por componente", JOptionPane.INFORMATION_MESSAGE);
                txtSegmento3.requestFocus();
                return false;
            } else if (this.segmento4 != null && this.segmento4 < 0 || this.segmento4 > 100) {
                JOptionPane.showMessageDialog(null, "Error! Debe proporcionar un valor entre 0 y 100 para el segmento 4 ",
                        "longitud interceptada por componente", JOptionPane.INFORMATION_MESSAGE);
                txtSegmento4.requestFocus();
                return false;
            } else if (this.segmento5 != null && this.segmento5 < 0 || this.segmento5 > 100) {
                JOptionPane.showMessageDialog(null, "Error! Debe proporcionar un valor entre 0 y 100 para el segmento 5 ",
                        "longitud interceptada por componente", JOptionPane.INFORMATION_MESSAGE);
                txtSegmento5.requestFocus();
                return false;
            } else if (this.segmento6 != null && this.segmento6 < 0 || this.segmento6 > 100) {
                JOptionPane.showMessageDialog(null, "Error! Debe proporcionar un valor entre 0 y 100 para el segmento 6 ",
                        "longitud interceptada por componente", JOptionPane.INFORMATION_MESSAGE);
                txtSegmento6.requestFocus();
                return false;
            } else if (this.segmento7 != null && this.segmento7 < 0 || this.segmento7 > 100) {
                JOptionPane.showMessageDialog(null, "Error! Debe proporcionar un valor entre 0 y 100 para el segmento 7 ",
                        "longitud interceptada por componente", JOptionPane.INFORMATION_MESSAGE);
                txtSegmento7.requestFocus();
                return false;
            } else if (this.segmento8 != null && this.segmento8 < 0 || this.segmento8 > 100) {
                JOptionPane.showMessageDialog(null, "Error! Debe proporcionar un valor entre 0 y 100 para el segmento 8 ",
                        "longitud interceptada por componente", JOptionPane.INFORMATION_MESSAGE);
                txtSegmento8.requestFocus();
                return false;
            } else if (this.segmento9 != null && this.segmento9 < 0 || this.segmento9 > 100) {
                JOptionPane.showMessageDialog(null, "Error! Debe proporcionar un valor entre 0 y 100 para el segmento 9 ",
                        "longitud interceptada por componente", JOptionPane.INFORMATION_MESSAGE);
                txtSegmento9.requestFocus();
                return false;
            } else if (this.segmento10 != null && this.segmento10 < 0 || this.segmento10 > 100) {
                JOptionPane.showMessageDialog(null, "Error! Debe proporcionar un valor entre 0 y 100 para el segmento 10 ",
                        "Longitud interceptada por componente", JOptionPane.INFORMATION_MESSAGE);
                txtSegmento10.requestFocus();
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            return true;
        }
    }

    private boolean validarCoberturaDosel() {
        this.transectoDosel = (Integer) cmbTransectoDosel.getSelectedItem();
        if (this.transectoDosel == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar un transecto ",
                    "Cobertura dosel", JOptionPane.ERROR_MESSAGE);
            cmbTransectoDosel.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean validarLongitudComponente() {
        Integer transecto = (Integer) cmbTransectoComponente.getSelectedItem();
        CatECarbonoComponente componente = (CatECarbonoComponente) cmbComponente.getSelectedItem();
        boolean existe = false;
        if (transecto != null) {
            existe = cdLongitud.validarComponente(this.sitioID, transecto, componente.getComponenteID());
            if (existe) {
                JOptionPane.showMessageDialog(null, "Ya existe ese componente con ese transecto, seleccione otro "
                        + "", "Longitud de componente", JOptionPane.ERROR_MESSAGE);
                limpiarControlesComponentes();
                cmbTransectoComponente.requestFocus();
                return false;
            }
        }
        return true;
    }

    private boolean validarColectasObligatorias() {
        CDColectaBotanica colecta = new CDColectaBotanica();
        if (colecta.validarCapturaGenero("CARBONO_LongitudComponente", this.sitioID) && colecta.validarTipoComponente(this.sitioID)) {
            JOptionPane.showMessageDialog(null, "Error! Faltan por asignar claves de colecta", "Longitud componente", JOptionPane.INFORMATION_MESSAGE);
            return false;
        } else {
            return true;
        }
    }

    private void crearComponente() {
        CatECarbonoComponente indexComponente = (CatECarbonoComponente) cmbComponente.getSelectedItem();
        CatEFamiliaEspecie indexFamilia = (CatEFamiliaEspecie) cmbFamilia.getSelectedItem();
        CatEGenero indexGenero = (CatEGenero) cmbGenero.getSelectedItem();
        CatEEspecie indexEspecie = (CatEEspecie) cmbEspecie.getSelectedItem();
        CatEInfraespecie indexInfraespecie = (CatEInfraespecie) cmbInfraespecie.getSelectedItem();
        CELongitudComponente ceLongitud = new CELongitudComponente();
        ceLongitud.setSitioID(this.sitioID);
        ceLongitud.setTransecto(this.transectoComponente);
        ceLongitud.setComponenteID(indexComponente.getComponenteID());

        if (indexFamilia != null) {
            ceLongitud.setFamiliaID(indexFamilia.getFamiliaID());
        }
        if (indexGenero != null) {
            ceLongitud.setGeneroID(indexGenero.getGeneroID());
        }
        if (indexEspecie != null) {
            ceLongitud.setEspecieID(indexEspecie.getEspecieID());
        }
        if (indexInfraespecie != null) {
            ceLongitud.setInfraespecieID(indexInfraespecie.getInfraespecieID());
        }
        ceLongitud.setNombreComun(this.nombreComun);

        // if(this.segmento1 != null){
        ceLongitud.setSegmento1(this.segmento1);
        // }
        // if(this.segmento2 != null){
        ceLongitud.setSegmento2(this.segmento2);
        // }
        // if(this.segmento3 != null){
        ceLongitud.setSegmento3(this.segmento3);
        // }
        // if(this.segmento4 != null){
        ceLongitud.setSegmento4(this.segmento4);
        // }
        //if(this.segmento5 != null){
        ceLongitud.setSegmento5(this.segmento5);
        //}
        // if(this.segmento6 != null){
        ceLongitud.setSegmento6(this.segmento6);
        //}
        //if(this.segmento7 != null){
        ceLongitud.setSegmento7(this.segmento7);
        //}
        //if(this.segmento8 != null){
        ceLongitud.setSegmento8(this.segmento8);
        // }
        // if(this.segmento9 != null){
        ceLongitud.setSegmento9(this.segmento9);
        // }
        // if(this.segmento10 != null){
        ceLongitud.setSegmento10(this.segmento10);
        // }
        // if(this.total != null){
        ceLongitud.setTotal(this.total);
        //  }
        cdLongitud.insertDatosSegementoComponente(ceLongitud);
    }

    private void actualizarComponente() {
        try {
            int fila = grdComponentes.getSelectedRow();
            CELongitudComponente ceLongitud = new CELongitudComponente();
            String registro = grdComponentes.getValueAt(fila, 0).toString();
            ceLongitud.setLongitudComponenteID(Integer.parseInt(registro));
            Integer transecto = (Integer) cmbTransectoComponente.getSelectedItem();
            CatECarbonoComponente indexComponente = (CatECarbonoComponente) cmbComponente.getSelectedItem();
            CatEFamiliaEspecie indexFamilia = (CatEFamiliaEspecie) cmbFamilia.getSelectedItem();
            CatEGenero indexGenero = (CatEGenero) cmbGenero.getSelectedItem();
            CatEEspecie indexEspecie = (CatEEspecie) cmbEspecie.getSelectedItem();
            CatEInfraespecie indexInfraespecie = (CatEInfraespecie) cmbInfraespecie.getSelectedItem();
            ceLongitud.setSitioID(this.sitioID);
            ceLongitud.setTransecto(transecto);
            ceLongitud.setComponenteID(indexComponente.getComponenteID());
            if (indexFamilia != null) {
                ceLongitud.setFamiliaID(indexFamilia.getFamiliaID());
            } else {
                ceLongitud.setFamiliaID(null);
            }
            if (indexGenero != null) {
                ceLongitud.setGeneroID(indexGenero.getGeneroID());
            } else {
                ceLongitud.setGeneroID(null);
            }
            if (indexEspecie != null) {
                ceLongitud.setEspecieID(indexEspecie.getEspecieID());
            } else {
                ceLongitud.setEspecieID(null);
            }
            if (indexInfraespecie != null) {
                ceLongitud.setInfraespecieID(indexInfraespecie.getInfraespecieID());
            }
            ceLongitud.setNombreComun(this.nombreComun);
            //if (this.segmento1 != null) {
            ceLongitud.setSegmento1(this.segmento1);
            //}
            // if (this.segmento2 != null) {
            ceLongitud.setSegmento2(this.segmento2);
            //}
            // if (this.segmento3 != null) {
            ceLongitud.setSegmento3(this.segmento3);
            //}
            // if (this.segmento4 != null) {
            ceLongitud.setSegmento4(this.segmento4);
            //}
            // if (this.segmento5 != null) {
            ceLongitud.setSegmento5(this.segmento5);
            // }
            // if (this.segmento6 != null) {
            ceLongitud.setSegmento6(this.segmento6);
            //}
            //if (this.segmento7 != null) {
            ceLongitud.setSegmento7(this.segmento7);
            // }
            //if (this.segmento8 != null) {
            ceLongitud.setSegmento8(this.segmento8);
            // }
            // if (this.segmento9 != null) {
            ceLongitud.setSegmento9(this.segmento9);
            // }
            // if (this.segmento10 != null) {
            ceLongitud.setSegmento10(this.segmento10);
            //  }
            // if (this.total != null) {
            ceLongitud.setTotal(this.total);
            // }
            this.cdLongitud.updateDatosLongitudComponente(ceLongitud);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla de longitud de componentes "
                    + e.getClass().getName() + " : " + e.getMessage(), "Longitud de componentes", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarComponente() {
        try {
            int fila = grdComponentes.getSelectedRow();
            String registro = grdComponentes.getValueAt(fila, 0).toString();
            Object[] opciones = {"Si", "No"};
            int respuesta = JOptionPane.showOptionDialog(null, "¿Está seguro de eliminar el registro de longitud por componente?",
                    "Carbono e incendios", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[1]);
            if (respuesta == JOptionPane.YES_OPTION) {
                this.ceComponente.setComponenteID(Integer.parseInt(registro));
                this.cdLongitud.deleteDatosSegementoComponente(this.ceComponente);
            } else {
                cmbTransectoComponente.requestFocus();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla de Longitud de componenets"
                    + "", "Longitud de componentes", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void limpiarControlesComponentes() {
        cmbTransectoComponente.setSelectedItem(null);
        cmbComponente.setSelectedItem(null);
        cmbFamilia.setSelectedItem(null);
        cmbGenero.setSelectedItem(null);
        cmbEspecie.setSelectedItem(null);
        cmbInfraespecie.setSelectedItem(null);
        txtNombreComun.setText("");
        txtSegmento1.setValue(null);
        txtSegmento1.setText("");
        txtSegmento2.setValue(null);
        txtSegmento2.setText("");
        txtSegmento3.setValue(null);
        txtSegmento3.setText("");
        txtSegmento4.setValue(null);
        txtSegmento4.setText("");
        txtSegmento5.setValue(null);
        txtSegmento5.setText("");
        txtSegmento6.setValue(null);
        txtSegmento6.setText("");
        txtSegmento7.setValue(null);
        txtSegmento7.setText("");
        txtSegmento8.setValue(null);
        txtSegmento8.setText("");
        txtSegmento9.setValue(null);
        txtSegmento9.setText("");
        txtSegmento10.setValue(null);
        txtSegmento10.setText("");
        txtTotal.setValue(null);
        txtTotal.setText("");
        txtClaveColecta.setText("");
        cmbTransectoComponente.requestFocus();
    }

    private void crearCoberturaDosel() {
        this.transectoDosel = (Integer) cmbTransectoDosel.getSelectedItem();
        CECoberturaDosel ceCoberturaDosel = new CECoberturaDosel();
        ceCoberturaDosel.setSitioID(this.sitioID);
        ceCoberturaDosel.setTransecto(this.transectoDosel);
        ceCoberturaDosel.setPunto1(this.punto1);
        ceCoberturaDosel.setPunto2(this.punto2);
        ceCoberturaDosel.setPunto3(this.punto3);
        ceCoberturaDosel.setPunto4(this.punto4);
        ceCoberturaDosel.setPunto5(this.punto5);
        ceCoberturaDosel.setPunto6(this.punto6);
        ceCoberturaDosel.setPunto7(this.punto7);
        ceCoberturaDosel.setPunto8(this.punto8);
        ceCoberturaDosel.setPunto9(this.punto9);
        ceCoberturaDosel.setPunto10(this.punto10);
        this.cdLongitud.insertDatosCoberturaDosel(ceCoberturaDosel);
    }

    private void actualizarCoberturaDosel() {
        try {
            int fila = grdCoberturaDosel.getSelectedRow();
            String registro = grdCoberturaDosel.getValueAt(fila, 0).toString();

            this.transectoDosel = (Integer) cmbTransectoDosel.getSelectedItem();
            CECoberturaDosel ceCoberturaDosel = new CECoberturaDosel();
            ceCoberturaDosel.setCoberturaDoselID(Integer.parseInt(registro));
            ceCoberturaDosel.setPunto1(this.punto1);
            ceCoberturaDosel.setPunto2(this.punto2);
            ceCoberturaDosel.setPunto3(this.punto3);
            ceCoberturaDosel.setPunto4(this.punto4);
            ceCoberturaDosel.setPunto5(this.punto5);
            ceCoberturaDosel.setPunto6(this.punto6);
            ceCoberturaDosel.setPunto7(this.punto7);
            ceCoberturaDosel.setPunto8(this.punto8);
            ceCoberturaDosel.setPunto9(this.punto9);
            ceCoberturaDosel.setPunto10(this.punto10);

            this.cdLongitud.updateDatosCoberturaDosel(ceCoberturaDosel);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla de cobertura dosel"
                    + e.getClass().getName() + " : " + e.getMessage(), "Carbono", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void eliminarCoberturaDosel() {
        try {
            int fila = grdCoberturaDosel.getSelectedRow();
            String registro = grdCoberturaDosel.getValueAt(fila, 0).toString();
            Object[] opciones = {"Si", "No"};
            int respuesta = JOptionPane.showOptionDialog(null, "¿Está seguro de eliminar el registro de cobertura dosel?",
                    "Carbono e incendios", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[1]);
            if (respuesta == JOptionPane.YES_OPTION) {
                this.ceCobertura.setCoberturaDoselID(Integer.parseInt(registro));
                this.cdLongitud.deleteDatosCoberturaDosel(this.ceCobertura);
            } else {
                cmbTransectoDosel.requestFocus();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla de cobertura dosel"
                    + "", "Cobertura dosel", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarControlesCobertura() {
        cmbTransectoDosel.setSelectedItem(null);
        chkPunto1.setSelected(false);
        chkPunto2.setSelected(false);
        chkPunto3.setSelected(false);
        chkPunto4.setSelected(false);
        chkPunto5.setSelected(false);
        chkPunto6.setSelected(false);
        chkPunto7.setSelected(false);
        chkPunto8.setSelected(false);
        chkPunto9.setSelected(false);
        chkPunto10.setSelected(false);

        cmbTransectoDosel.requestFocus();

    }

    private void manipularControleslongitud(boolean habilitar) {
        if (habilitar == true) {
            cmbTransectoComponente.setEnabled(true);
            cmbComponente.setEnabled(true);
            cmbFamilia.setEnabled(true);
            cmbGenero.setEnabled(true);
            cmbEspecie.setEnabled(true);
            cmbInfraespecie.setEnabled(true);
            txtNombreComun.setEnabled(true);
            txtSegmento1.setEnabled(true);
            txtSegmento2.setEnabled(true);
            txtSegmento3.setEnabled(true);
            txtSegmento4.setEnabled(true);
            txtSegmento5.setEnabled(true);
            txtSegmento6.setEnabled(true);
            txtSegmento7.setEnabled(true);
            txtSegmento8.setEnabled(true);
            txtSegmento9.setEnabled(true);
            txtSegmento10.setEnabled(true);
            btnAgregarComponente.setEnabled(true);
            btnModificarComponente.setEnabled(true);
            btnEliminarComponente.setEnabled(true);
        } else {
            cmbTransectoComponente.setEnabled(false);
            cmbComponente.setEnabled(false);
            cmbFamilia.setEnabled(false);
            cmbGenero.setEnabled(false);
            cmbEspecie.setEnabled(false);
            cmbInfraespecie.setEnabled(false);
            txtNombreComun.setEnabled(false);
            txtSegmento1.setEnabled(false);
            txtSegmento2.setEnabled(false);
            txtSegmento3.setEnabled(false);
            txtSegmento4.setEnabled(false);
            txtSegmento5.setEnabled(false);
            txtSegmento6.setEnabled(false);
            txtSegmento7.setEnabled(false);
            txtSegmento8.setEnabled(false);
            txtSegmento9.setEnabled(false);
            txtSegmento10.setEnabled(false);
            btnAgregarComponente.setEnabled(false);
            btnModificarComponente.setEnabled(false);
            btnEliminarComponente.setEnabled(false);
        }
    }

    public void manipularControlesCobertura(boolean habilitar) {
        if (habilitar == true) {
            cmbTransectoDosel.setEnabled(true);
            chkPunto1.setEnabled(true);
            chkPunto2.setEnabled(true);
            chkPunto3.setEnabled(true);
            chkPunto4.setEnabled(true);
            chkPunto5.setEnabled(true);
            chkPunto6.setEnabled(true);
            chkPunto7.setEnabled(true);
            chkPunto8.setEnabled(true);
            chkPunto9.setEnabled(true);
            chkPunto10.setEnabled(true);
            btnAgregarDosel.setEnabled(true);
            btnModificarDosel.setEnabled(true);
            btnEliminarDosel.setEnabled(true);
        } else {
            cmbTransectoDosel.setEnabled(false);
            chkPunto1.setEnabled(false);
            chkPunto2.setEnabled(false);
            chkPunto3.setEnabled(false);
            chkPunto4.setEnabled(false);
            chkPunto5.setEnabled(false);
            chkPunto6.setEnabled(false);
            chkPunto7.setEnabled(false);
            chkPunto8.setEnabled(false);
            chkPunto9.setEnabled(false);
            chkPunto10.setEnabled(false);
            btnAgregarDosel.setEnabled(false);
            btnModificarDosel.setEnabled(false);
            btnEliminarDosel.setEnabled(false);
        }
    }

    private void seleccionarSiguienteFormulario(CESitio ceSitio) {
        Integer secuenciaID = ceSitio.getSecuencia();
        Integer sitio = this.funciones.sitioCapturaSueloCarbono(this.upmID, 3);
        if (secuenciaID != null) {
            switch (secuenciaID) {
                case 1: //Módulo A
                    UPMForms.suelo.setDatosiniciales(ceSitio);
                    UPMForms.suelo.setVisible(true);
                    //funciones.manipularBotonesMenuPrincipal(true);
                    break;
                case 2: //Módulos A y C
                    /*UPMForms.carbono.setDatosIniciales(ceSitio);
                    UPMForms.carbono.setVisible(true);*/
                    funciones.manipularBotonesMenuPrincipal(false);
                    this.cdSecuencia.insertSecuenciaTerminada(ceSitio);
                    this.hide();
                    break;
                case 3: //Modulos A, C, E y G
                    if (sitio == ceSitio.getSitio()) {//si el sitio!= del sitio 3
                        UPMForms.hojarascaProfundidad.setDatosiniciales(ceSitio);
                        UPMForms.hojarascaProfundidad.setVisible(true);
                    } else {
                        UPMForms.parametrosFQ.setDatosiniciales(ceSitio);
                        UPMForms.parametrosFQ.setVisible(true);
                    }
                    //funciones.manipularBotonesMenuPrincipal(true);
                    break;
                case 4: //Modulos A y E        
                    UPMForms.suelo.setDatosiniciales(ceSitio);
                    UPMForms.suelo.setVisible(true);
                    //funciones.manipularBotonesMenuPrincipal(true);           
                    break;
                case 5: //Modulos A, C, D y F
                    UPMForms.arboladoD.setDatosIniciales(ceSitio);
                    UPMForms.arboladoD.setVisible(true);
                    //funciones.manipularBotonesMenuPrincipal(true);
                    break;
                case 6://Modulos A, C y D
                    UPMForms.arboladoD.setDatosIniciales(ceSitio);
                    UPMForms.arboladoD.setVisible(true);
                    //funciones.manipularBotonesMenuPrincipal(true);
                    break;
                case 7://Modulos A, C, D y E
                    UPMForms.arboladoD.setDatosIniciales(ceSitio);
                    UPMForms.arboladoD.setVisible(true);
                    //funciones.manipularBotonesMenuPrincipal(true);
                    break;
                case 8://Modulos A, C, D, E y F

                    UPMForms.arboladoD.setDatosIniciales(ceSitio);
                    UPMForms.arboladoD.setVisible(true);
                    //funciones.manipularBotonesMenuPrincipal(true);
                    break;
                case 9://Modulos A, C y E
                    UPMForms.hojarascaProfundidad.setDatosiniciales(ceSitio);
                    UPMForms.hojarascaProfundidad.setVisible(true);
                    break;
                case 10://Modulos A, C, H
                    //funciones.manipularBotonesMenuPrincipal(true);
                    UPMForms.vegetacionMenor.setDatosIniciales(ceSitio);
                    UPMForms.vegetacionMenor.setVisible(true);
                    break;
                case 11://Modulos A y H
                    //funciones.manipularBotonesMenuPrincipal(true);
                    UPMForms.suelo.setDatosiniciales(ceSitio);
                    UPMForms.suelo.setVisible(true);
                    break;
                case 12://Modulos A, E y H
                    //funciones.manipularBotonesMenuPrincipal(true);

                    UPMForms.suelo.setDatosiniciales(ceSitio);
                    UPMForms.suelo.setVisible(true);
                    break;
                case 13://Modulos A, C, E y H
                    //funciones.manipularBotonesMenuPrincipal(true);
                    if (sitio == ceSitio.getSitio()) {
                        UPMForms.hojarascaProfundidad.setDatosiniciales(ceSitio);
                        UPMForms.hojarascaProfundidad.setVisible(true);
                    } else {
                        UPMForms.vegetacionMenor.setDatosIniciales(ceSitio);
                        UPMForms.vegetacionMenor.setVisible(true);
                    }
                    break;
                case 14://Modulos A, E y G
                    //funciones.manipularBotonesMenuPrincipal(true);
                    UPMForms.suelo.setDatosiniciales(ceSitio);
                    UPMForms.suelo.setVisible(true);
                    break;
                case 15://A y G
                    //funciones.manipularBotonesMenuPrincipal(true);
                    UPMForms.suelo.setDatosiniciales(ceSitio);
                    UPMForms.suelo.setVisible(true);
                    break;
                case 16:
                    UPMForms.parametrosFQ.setDatosiniciales(ceSitio);
                    UPMForms.parametrosFQ.setVisible(true);
                    break;
            }
        }
    }

    private String getModuloActual(CESitio ceSitio) {
        Integer secuenciaID = ceSitio.getSecuencia();
        String modulo = "";
        if (secuenciaID != null) {
            switch (secuenciaID) {
                case 1: //Módulo A
                    modulo = "A";
                    break;
                case 2: //Módulos A y C
                    modulo = "C";
                    break;
                case 3: //Modulos A, C, E y G
                    modulo = "C";
                    break;
                case 4: //Modulos A y E
                    modulo = "A";
                    break;
                case 5: //Modulos A, C, D y F
                    modulo = "C";
                    break;
                case 6://Modulos A, C y D
                    modulo = "C";
                    break;
                case 7://Modulos A, C, D y E
                    modulo = "C";
                    break;
                case 8://Modulos A, C, D, E y F
                    modulo = "C";
                    break;
                case 9://Modulos A, C y E
                    modulo = "C";
                    break;
                case 10://Modulos A, C, H
                    modulo = "C";
                    break;
                case 11://Modulos A y H
                    modulo = "A";
                    break;
                case 12://Modulos A, E y H
                    modulo = "A";
                    break;
                case 13://Modulos A, C, E y H
                    modulo = "C";
                    break;
                case 14://Modulos A, E y G
                    modulo = "A";
                    break;
                case 15://A y G
                    modulo = "A";
                    break;
            }
        }
        return modulo;
    }

    /* private void revisarSiguienteFormulario(CESitio ceSitio) {
        Integer secuenciaID = ceSitio.getSecuencia();
        if (secuenciaID != null) {
            System.out.println("Secuencia 2967=" + secuenciaID);
            switch (secuenciaID) {
                case 1: //Módulo A
                    UPMForms.suelo.revisarSuelo(ceSitio);
                    UPMForms.suelo.setVisible(true);
                    break;
                case 2: //Módulos A y C
                    /*UPMForms.carbono.setDatosIniciales(ceSitio);
                    UPMForms.carbono.setVisible(true);
                    funciones.manipularBotonesMenuPrincipal(false);
                    this.hide();
                    break;
                case 3: //Modulos A, C, E y G
                    UPMForms.hojarascaProfundidad.revisarHojarascaProfundidad(ceSitio);
                    UPMForms.hojarascaProfundidad.setVisible(true);
                    break;
                case 4: //Modulos A y E
                    UPMForms.suelo.revisarSuelo(ceSitio);
                    UPMForms.suelo.setVisible(true);
                    break;
                case 5: //Modulos A, C, D y F
                    UPMForms.arboladoD.revisarArboladoD(ceSitio);
                    UPMForms.arboladoD.setVisible(true);
                    break;
                case 6://Modulos A, C y D
                    UPMForms.arboladoD.revisarArboladoD(ceSitio);
                    UPMForms.arboladoD.setVisible(true);
                    break;
                case 7://Modulos A, C, D y E
                    UPMForms.arboladoD.revisarArboladoD(ceSitio);
                    UPMForms.arboladoD.setVisible(true);
                    break;
                case 8://Modulos A, C, D, E y F
                    UPMForms.arboladoD.revisarArboladoD(ceSitio);
                    UPMForms.arboladoD.setVisible(true);
                    break;
                case 9://Modulos A, C y E
                    UPMForms.hojarascaProfundidad.revisarHojarascaProfundidad(ceSitio);
                    UPMForms.hojarascaProfundidad.setVisible(true);
                    break;
                case 10://Modulos A, C, H
                    UPMForms.vegetacionMenor.revisarVegetacionMenor(ceSitio);
                    UPMForms.vegetacionMenor.setVisible(true);
                    break;
                case 11://Modulos A y H
                    UPMForms.suelo.revisarSuelo(ceSitio);
                    UPMForms.suelo.setVisible(true);
                    break;
                case 12://Modulos A, E y H
                    UPMForms.suelo.revisarSuelo(ceSitio);
                    UPMForms.suelo.setVisible(true);
                    break;
                case 13://Modulos A, C, E y H
                    UPMForms.hojarascaProfundidad.revisarHojarascaProfundidad(ceSitio);
                    UPMForms.hojarascaProfundidad.setVisible(true);
                    break;
                case 14://Modulos A, E y G
                    UPMForms.suelo.revisarSuelo(ceSitio);
                    UPMForms.suelo.setVisible(true);
                    break;
                case 15://A y G
                    UPMForms.suelo.revisarSuelo(ceSitio);
                    UPMForms.suelo.setVisible(true);
                    break;
                case 16:
                    UPMForms.parametrosFQ.setDatosiniciales(ceSitio);
                    UPMForms.parametrosFQ.setVisible(true);
                    break;
            }
        }
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarComponente;
    private javax.swing.JButton btnAgregarDosel;
    private javax.swing.JButton btnColecta;
    private javax.swing.JButton btnEliminarComponente;
    private javax.swing.JButton btnEliminarDosel;
    private javax.swing.JButton btnModificarComponente;
    private javax.swing.JButton btnModificarDosel;
    private javax.swing.JButton btnSalir;
    private javax.swing.JCheckBox chkCoberturaDosel;
    private javax.swing.JCheckBox chkLongitudComponentes;
    private javax.swing.JCheckBox chkPunto1;
    private javax.swing.JCheckBox chkPunto10;
    private javax.swing.JCheckBox chkPunto2;
    private javax.swing.JCheckBox chkPunto3;
    private javax.swing.JCheckBox chkPunto4;
    private javax.swing.JCheckBox chkPunto5;
    private javax.swing.JCheckBox chkPunto6;
    private javax.swing.JCheckBox chkPunto7;
    private javax.swing.JCheckBox chkPunto8;
    private javax.swing.JCheckBox chkPunto9;
    private javax.swing.JComboBox cmbComponente;
    private javax.swing.JComboBox cmbEspecie;
    private javax.swing.JComboBox cmbFamilia;
    private javax.swing.JComboBox cmbGenero;
    private javax.swing.JComboBox cmbInfraespecie;
    private javax.swing.JComboBox<Integer> cmbSitios;
    private javax.swing.JComboBox cmbTransectoComponente;
    private javax.swing.JComboBox cmbTransectoDosel;
    private javax.swing.JComboBox<Integer> cmbUPMID;
    private javax.swing.JTable grdCoberturaDosel;
    private javax.swing.JTable grdComponentes;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblCarbonoIncendios;
    private javax.swing.JLabel lblClaveColecta;
    private javax.swing.JLabel lblCoberturaDosel;
    private javax.swing.JLabel lblComponente;
    private javax.swing.JLabel lblComponente1;
    private javax.swing.JLabel lblComponente10;
    private javax.swing.JLabel lblComponente11;
    private javax.swing.JLabel lblComponente2;
    private javax.swing.JLabel lblComponente3;
    private javax.swing.JLabel lblComponente4;
    private javax.swing.JLabel lblComponente5;
    private javax.swing.JLabel lblComponente6;
    private javax.swing.JLabel lblComponente7;
    private javax.swing.JLabel lblComponente8;
    private javax.swing.JLabel lblComponente9;
    private javax.swing.JLabel lblComponenteTotal;
    private javax.swing.JLabel lblEspecie;
    private javax.swing.JLabel lblFamilia;
    private javax.swing.JLabel lblGenero;
    private javax.swing.JLabel lblInfraespecie;
    private javax.swing.JLabel lblModulo;
    private javax.swing.JLabel lblNombreComun;
    private javax.swing.JLabel lblSitio;
    private javax.swing.JLabel lblTransecto;
    private javax.swing.JLabel lblUPM;
    public javax.swing.JTextField txtClaveColecta;
    private javax.swing.JTextField txtNombreComun;
    private javax.swing.JFormattedTextField txtSegmento1;
    private javax.swing.JFormattedTextField txtSegmento10;
    private javax.swing.JFormattedTextField txtSegmento2;
    private javax.swing.JFormattedTextField txtSegmento3;
    private javax.swing.JFormattedTextField txtSegmento4;
    private javax.swing.JFormattedTextField txtSegmento5;
    private javax.swing.JFormattedTextField txtSegmento6;
    private javax.swing.JFormattedTextField txtSegmento7;
    private javax.swing.JFormattedTextField txtSegmento8;
    private javax.swing.JFormattedTextField txtSegmento9;
    private javax.swing.JFormattedTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
