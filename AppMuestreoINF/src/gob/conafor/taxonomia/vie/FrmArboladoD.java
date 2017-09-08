package gob.conafor.taxonomia.vie;

import gob.conafor.ini.vie.Main;
import gob.conafor.sitio.mod.CDTrazoSitio;
import gob.conafor.sitio.mod.CESitio;
import gob.conafor.sitio.vie.FrmTrazoSitio;
import gob.conafor.suelo.mod.CDClaveColecta;
import gob.conafor.sys.mod.CDSecuencia;
import gob.conafor.sys.mod.CDSeguimientoUPM;
import gob.conafor.sys.mod.CESeccionesCapturadas;
import gob.conafor.taxonomia.mod.CDArbolado;
import gob.conafor.taxonomia.mod.CDColectaBotanica;
import gob.conafor.taxonomia.mod.CDCondicionTaxonomica;
import gob.conafor.taxonomia.mod.CDDanioArbolado;
import gob.conafor.taxonomia.mod.CDEspecies;
import gob.conafor.taxonomia.mod.CDSubmuestra;
import gob.conafor.taxonomia.mod.CEArbolado;
import gob.conafor.taxonomia.mod.CEColectaBotanica;
import gob.conafor.taxonomia.mod.CEDanioSeveridad;
import gob.conafor.taxonomia.mod.CatEAgenteDanio;
import gob.conafor.taxonomia.mod.CatECondicionArbolado;
import gob.conafor.taxonomia.mod.CatECondicionMuertoPie;
import gob.conafor.taxonomia.mod.CatEEspecie;
import gob.conafor.taxonomia.mod.CatEExposicionLuzCopa;
import gob.conafor.taxonomia.mod.CatEFamiliaEspecie;
import gob.conafor.taxonomia.mod.CatEFormaFuste;
import gob.conafor.taxonomia.mod.CatEFormaVida;
import gob.conafor.taxonomia.mod.CatEGenero;
import gob.conafor.taxonomia.mod.CatEGradoPutrefaccionArbolado;
import gob.conafor.taxonomia.mod.CatEInfraespecie;
import gob.conafor.taxonomia.mod.CatEPorcentajeArbolado;
import gob.conafor.taxonomia.mod.CatEPosicionCopa;
import gob.conafor.taxonomia.mod.CatETipoTocon;
import gob.conafor.taxonomia.utils.ValidacionesArbolado;
import gob.conafor.upm.vie.UPMForms;
import gob.conafor.utils.Datos;
import gob.conafor.utils.FuncionesComunes;
import gob.conafor.utils.Tablas;
import gob.conafor.utils.ValidacionesComunes;
import gob.conafor.utils.Version;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class FrmArboladoD extends javax.swing.JInternalFrame {
    private boolean revision;
    private final int arbolado;
    private final int submuestra;
    private static final int FORMATO_ID = 14;
    private int upmID;
    private int sitioID;
    private int sitio;
    private CDEspecies especie = new CDEspecies();
    private CDCondicionTaxonomica condicion = new CDCondicionTaxonomica();
    private Integer consecutivo;
    private Integer noIndividuo;
    private Integer noRama;
    private Integer azimut;
    private Float distancia;
    private String infraespecie;
    private String nombreComun;
    private Float diametroNormal;
    private Float alturaTotal;
    private Integer anguloInclinacion;
    private Float alturaFusteLimpio;
    private Float alturaComercial;
    private Float diametroCopaNS;
    private Float diametroCopaEO;
    private Integer proporcionCopaViva;
    private Integer exposicionCopa;
    private Integer posicionCopa;
    private Integer densidadCopa;
    private Integer muerteRegresiva;
    private Integer transparenciaFollaje;
    private Boolean esSubmuestra;
    private int arboladoID;
    CatECondicionArbolado indexCondicionArbolado;
    private CDArbolado cdArbolado = new CDArbolado();
    private CESitio ceSitio;
    private CDSubmuestra cdSubmuestra = new CDSubmuestra();
    private CESeccionesCapturadas seccionActual = new CESeccionesCapturadas();
    private CESeccionesCapturadas seccionSiguiente = new CESeccionesCapturadas();
    private CDSeguimientoUPM seguimiento = new CDSeguimientoUPM();
    private FuncionesComunes combo = new FuncionesComunes();
    private Datos numeros = new Datos();
    private CDEspecies cdEspecies = new CDEspecies();
    private CDSecuencia cdSecuencia = new CDSecuencia();
    private FuncionesComunes funciones = new FuncionesComunes();
    private int modificar;
    private Version ver=new Version();
    private String version=ver.getVersion();
    public int[] indexs;
    FrmClaveColecta claveColecta = new FrmClaveColecta(Main.main, true);

    public FrmArboladoD() {
        initComponents();
        this.arbolado = 10;
        this.submuestra = 11;
        fillCmbFamilia();
        fillCmbGeneros();
        fillCmbFormaVida();
        fillCmbFormaFuste();
        fillCmbTipoTocon();
        fillCmbCondicionMuertoPie();
        fillCmbGradoPutrefaccion();
        fillCmbDanio1();
        fillCmbDanio2();
        fillCmbProporcionCopaViva();
        fillCmbExposicionLuzCopa();
        fillCmbPosicionCopa();
        fillCmbDensidadCopa();
        fillCmbMuerteRegresiva();
        fillCmbTransparenciaFollaje();
        txtNumeroIndividuo.requestFocus();
        estadoArbolVivo();
    }
    
    public void setDatosIniciales(CESitio sitio) {
        this.upmID = sitio.getUpmID();
        this.sitioID = sitio.getSitioID();
        this.sitio = sitio.getSitio();
        txtUPM.setText(String.valueOf(this.upmID));
        txtSitio.setText(String.valueOf(this.sitio));
        this.ceSitio = sitio;
        evitarCapturaPorTrazo(sitio);
        llenarTabla();
        txtNumeroIndividuo.requestFocus();
        //estadoArbolVivo();
        this.ceSitio = sitio;
        this.funciones.reiniciarComboModel(cmbConsecutivo);
        fillCmbConsecutivo();
        funciones.manipularBotonesMenuPrincipal(true);
        this.modificar  = 0;
        limpiarControles();
    }

    public void revisarArboladoD(CESitio sitio) {
        revision=true;
        limpiarControles();
        this.upmID = sitio.getUpmID();
        this.sitioID = sitio.getSitioID();
        this.sitio = sitio.getSitio();
        txtUPM.setText(String.valueOf(this.upmID));
        txtSitio.setText(String.valueOf(this.sitio));
        this.ceSitio = sitio;
        evitarCapturaPorTrazo(sitio);
        llenarTabla();
        txtNumeroIndividuo.requestFocus();
        estadoArbolVivo();
        this.funciones.reiniciarComboModel(cmbConsecutivo);
        fillCmbConsecutivo();
        funciones.manipularBotonesMenuPrincipal(true);
        this.chkArbolado.setSelected(funciones.habilitarCheckBox("TAXONOMIA_Arbolado", this.sitioID));
        this.modificar = 1;
    }
    
    private void fillCmbConsecutivo(){
        List<Integer> listConsecutivo = new ArrayList<>();
        listConsecutivo = cdArbolado.getConsecutivo(this.sitioID);
        if(listConsecutivo != null){
            int size = listConsecutivo.size();
            for(int i = 0; i < size; i++){
                cmbConsecutivo.addItem(listConsecutivo.get(i));
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

    private void fillCmbGeneros() {
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

    private void fillCmbFormaVida(){
        List<CatEFormaVida> listFormaVida = new ArrayList<>();
        CDCondicionTaxonomica ct = new CDCondicionTaxonomica();
        listFormaVida = ct.getFormaVida();
        if(listFormaVida != null){
            int size = listFormaVida.size();
            for(int i = 0; i < size; i++){
                cmbFormaVida.addItem(listFormaVida.get(i));
            }
        }
    }
    
    private void fillCmbFormaFuste(){
        List<CatEFormaFuste> listFormaFuste = new ArrayList<>();
        listFormaFuste = condicion.getFormaFuste();
        
        if(listFormaFuste != null){
            int size = listFormaFuste.size();
            for(int i = 0; i < size; i++){
                cmbFormaFuste.addItem(listFormaFuste.get(i));
            }
        }
    }
    
    private void fillCmbCondicionArbolado() {
        List<CatECondicionArbolado> listCondicion = new ArrayList<>();
        listCondicion = condicion.getCondicionArboladoArbol();
        if (listCondicion != null) {
            int size = listCondicion.size();
            if (cmbFormaVida.getSelectedIndex() == 7) {
                cmbCondicion.addItem(listCondicion.get(3));
                cmbCondicion.addItem(listCondicion.get(4));
            } else {
                for (int i = 0; i < size; i++) {
                    cmbCondicion.addItem(listCondicion.get(i));
                }
            }
        }
    }
    
    private void fillCmbCondicionLianas(){
         List<CatECondicionArbolado> listCondicion = new ArrayList<>();
        listCondicion = condicion.getCondicionArboladoLianas();

        if (listCondicion != null) {
            int size = listCondicion.size();
            for (int i = 0; i < size; i++) {
                cmbCondicion.addItem(listCondicion.get(i));
            }
        }
    }
    
    private void fillCmbCondicionMuertoPie(){
        List<CatECondicionMuertoPie> listMuertoPie = new ArrayList<>();
        listMuertoPie = condicion.getCondicionMuertoPie();
        
        if(listMuertoPie != null){
            int size = listMuertoPie.size();
            for(int i = 0; i < size; i++){
                cmbCondicionMuertoPie.addItem(listMuertoPie.get(i));
            }
        }
    }
    
    private void fillCmbGradoPutrefaccion(){
        List<CatEGradoPutrefaccionArbolado> listGradoPutrefaccion = new ArrayList();
        listGradoPutrefaccion = condicion.getGradoPutrefaccion();
        
        if(listGradoPutrefaccion != null){
            int size= listGradoPutrefaccion.size();
            for(int i= 0; i < size; i ++){
                cmbGradoPutrefaccion.addItem(listGradoPutrefaccion.get(i));
            }
        }
    }
    
    private void fillCmbTipoTocon() {
        List<CatETipoTocon> listTipoTocon = new ArrayList<>();
        listTipoTocon = condicion.getTipoTocon();

        if (listTipoTocon != null) {
            int size = listTipoTocon.size();
            for (int i = 0; i < size; i++) {
                cmbTipoTocon.addItem(listTipoTocon.get(i));
            }
        }
    }
    
    private void fillCmbDanio1() {
        List<CatEAgenteDanio> listAgenteDanio = new ArrayList<>();
        listAgenteDanio = condicion.getAgenteDanio();

        if (listAgenteDanio!= null) {
            int size = listAgenteDanio.size();
            for (int i = 0; i < size; i++) {
                cmbAgenteDanio1.addItem(listAgenteDanio.get(i));
            }
        }
    }
    
    private void fillCmbDanio2() {
        List<CatEAgenteDanio> listAgenteDanio = new ArrayList<>();
        listAgenteDanio = condicion.getAgenteDanio();

        if (listAgenteDanio != null) {
            int size = listAgenteDanio.size();
            for (int i = 0; i < size; i++) {
                cmbAgenteDanio2.addItem(listAgenteDanio.get(i));
            }
        }
    }

    private void fillCmbProporcionCopaViva() {
        List<CatEPorcentajeArbolado> listProporcion = new ArrayList<>();
        listProporcion = this.condicion.getPorcentajeArboladoCopa();

        if (listProporcion != null) {
            int size = listProporcion.size();
            for (int i = 0; i < size; i++) {
                cmbProporcionCopaViva.addItem(listProporcion.get(i));
            }
        }
    }

    private void fillCmbExposicionLuzCopa() {
        List<CatEExposicionLuzCopa> listExposicion = new ArrayList<>();
        listExposicion = this.condicion.getExposicionLuzCopa();

        if (listExposicion != null) {
            int size = listExposicion.size();
            for (int i = 0; i < size; i++) {
                cmbExposicionLuzCopa.addItem(listExposicion.get(i));
            }
        }
    }
    
    private void fillCmbPosicionCopa(){
        List<CatEPosicionCopa> listPosicion = new ArrayList<>();
        listPosicion = this.condicion.getPosicionCopa();
        
        if(listPosicion != null){
            int size = listPosicion.size();
            for(int i = 0; i < size; i++){
                cmbPosicionCopa.addItem(listPosicion.get(i));
            }
        }
    }
    
    private void fillCmbDensidadCopa(){
        List<CatEPorcentajeArbolado> listDensidad = new ArrayList<>();
        listDensidad = this.condicion.getPorcentajeArboladoCopa();

        if (listDensidad != null) {
            int size = listDensidad.size();
            for (int i = 0; i < size; i++) {
                cmbDencidadCopa.addItem(listDensidad.get(i));
            }
        }
    }
    
    private void fillCmbMuerteRegresiva(){
        List<CatEPorcentajeArbolado> listMuerte = new ArrayList<>();
        listMuerte = this.condicion.getPorcentajeArboladoCopa();

        if (listMuerte != null) {
            int size = listMuerte.size();
            for (int i = 0; i < size; i++) {
                cmbMuerteRegresiva.addItem(listMuerte.get(i));
            }
        }
    }
    
    private void fillCmbTransparenciaFollaje(){
         List<CatEPorcentajeArbolado> listTransparencia = new ArrayList<>();
        listTransparencia  = this.condicion.getPorcentajeArboladoCopa();

        if (listTransparencia  != null) {
            int size = listTransparencia .size();
            for (int i = 0; i < size; i++) {
                cmbTransparenciaFollaje.addItem(listTransparencia .get(i));
            }
        }
    }
    
    public void fillCmbSeveridad1(int agente) {
        List<CatEPorcentajeArbolado> listSeveridad = new ArrayList<>();
        if (agente == 21 || agente == 23 || agente == 24 || agente == 25 || agente == 33 || agente == 34) {
            combo.reiniciarComboModel(cmbSeveridad1);
            listSeveridad = condicion.getPorcentajeArboladoCopa();
        } else if (agente == 22) {
            combo.reiniciarComboModel(cmbSeveridad1);
            listSeveridad = condicion.getPorcentajeArboladoCopa6();
        }
        if (listSeveridad != null) {
            int size = listSeveridad.size();
            for (int i = 0; i < size; i++) {
                cmbSeveridad1.addItem(listSeveridad.get(i));
            }
        }
    }

    public void fillCmbSeveridad2(int agente) {
        List<CatEPorcentajeArbolado> listSeveridad = new ArrayList<>();
        if (agente == 21 || agente == 23 || agente == 24 || agente == 25 || agente == 33 || agente == 34) {
            combo.reiniciarComboModel(cmbSeveridad2);
            listSeveridad = condicion.getPorcentajeArboladoCopa();
        } else if (agente == 22) {
            combo.reiniciarComboModel(cmbSeveridad2);
            listSeveridad = condicion.getPorcentajeArboladoCopa6();
        }
        if (listSeveridad != null) {
            int size = listSeveridad.size();
            for (int i = 0; i < size; i++) {
                cmbSeveridad2.addItem(listSeveridad.get(i));
            }
        }
    }

    public void llenarTabla() {
        
        grdArbolado.setModel(cdArbolado.getTablaArboladoD(this.sitioID));
        
        grdArbolado.getColumnModel().getColumn(2).setPreferredWidth(70);//Consecutivo
        grdArbolado.getColumnModel().getColumn(3).setPreferredWidth(60);//individuo
        grdArbolado.getColumnModel().getColumn(4).setPreferredWidth(50);//rama
        grdArbolado.getColumnModel().getColumn(5).setPreferredWidth(50);//azimut
        grdArbolado.getColumnModel().getColumn(6).setPreferredWidth(60);//distancia
        grdArbolado.getColumnModel().getColumn(7).setPreferredWidth(120);//familia
        grdArbolado.getColumnModel().getColumn(8).setPreferredWidth(120);//genero
        grdArbolado.getColumnModel().getColumn(9).setPreferredWidth(120);//especie
        grdArbolado.getColumnModel().getColumn(10).setPreferredWidth(120);//infraespecie
        grdArbolado.getColumnModel().getColumn(11).setPreferredWidth(120);//Nombre comun
        grdArbolado.getColumnModel().getColumn(12).setPreferredWidth(70);//submuestra
        grdArbolado.getColumnModel().getColumn(13).setPreferredWidth(140);//forma vida
        grdArbolado.getColumnModel().getColumn(14).setPreferredWidth(140);//condicion
        grdArbolado.getColumnModel().getColumn(15).setPreferredWidth(120);//forma fuste
        grdArbolado.getColumnModel().getColumn(16).setPreferredWidth(120);//tipo muerto en pie
        grdArbolado.getColumnModel().getColumn(17).setPreferredWidth(120);//Grado putrefaccion
        grdArbolado.getColumnModel().getColumn(18).setPreferredWidth(130);//tipo tocon
        grdArbolado.getColumnModel().getColumn(19).setPreferredWidth(90);//Diametro normal
        grdArbolado.getColumnModel().getColumn(20).setPreferredWidth(81); //Altura total
        grdArbolado.getColumnModel().getColumn(21).setPreferredWidth(95); //Angulo inclinacion
        grdArbolado.getColumnModel().getColumn(22).setPreferredWidth(100); //Altura fuste limpio
        grdArbolado.getColumnModel().getColumn(23).setPreferredWidth(90); //Altura comercial
        grdArbolado.getColumnModel().getColumn(24).setPreferredWidth(100); // Diametro copa NS
        grdArbolado.getColumnModel().getColumn(25).setPreferredWidth(100); // Diametro copa EO
        grdArbolado.getColumnModel().getColumn(26).setPreferredWidth(94); //Proporcion de copa viva
        grdArbolado.getColumnModel().getColumn(27).setPreferredWidth(80); //Exposicion luz copa
        grdArbolado.getColumnModel().getColumn(28).setPreferredWidth(78); //Posicion de copa
        grdArbolado.getColumnModel().getColumn(29).setPreferredWidth(80); //Dencidad de copa
        grdArbolado.getColumnModel().getColumn(30).setPreferredWidth(95); //Muerte regresiva
        grdArbolado.getColumnModel().getColumn(31).setPreferredWidth(120); //Transparencia follaje
        grdArbolado.getColumnModel().getColumn(32).setPreferredWidth(50);//Danio 1
        grdArbolado.getColumnModel().getColumn(33).setPreferredWidth(80);//Porcentaje danio 1
        grdArbolado.getColumnModel().getColumn(34).setPreferredWidth(50);// Danio 2
        grdArbolado.getColumnModel().getColumn(35).setPreferredWidth(80);// Porcentaje danio 2
        grdArbolado.getColumnModel().getColumn(36).setPreferredWidth(180);//Clave de colecta
       
        Tablas tabla = new Tablas();
        int[] columna_0 = {0};
        int[] columna_1 = {1};
        tabla.hideColumnTable(grdArbolado, columna_0);
        tabla.hideColumnTable(grdArbolado, columna_1);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        btnTrazoSitio = new javax.swing.JButton();
        lblUPM = new javax.swing.JLabel();
        txtUPM = new javax.swing.JTextField();
        lblSitio = new javax.swing.JLabel();
        txtSitio = new javax.swing.JTextField();
        lblArbolado = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblNumeroIndividuo = new javax.swing.JLabel();
        lblRamaTallo = new javax.swing.JLabel();
        lblAzimut = new javax.swing.JLabel();
        lblDistancia = new javax.swing.JLabel();
        lblConsecutivo = new javax.swing.JLabel();
        cmbConsecutivo = new javax.swing.JComboBox();
        txtNumeroIndividuo = new javax.swing.JFormattedTextField();
        txtNumeroRamaTallo = new javax.swing.JFormattedTextField();
        txtAzimut = new javax.swing.JFormattedTextField();
        txtDistancia = new javax.swing.JFormattedTextField();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        lblFamilia = new javax.swing.JLabel();
        cmbFamilia = new javax.swing.JComboBox();
        lblGenero = new javax.swing.JLabel();
        cmbGenero = new javax.swing.JComboBox();
        lblEspecie = new javax.swing.JLabel();
        cmbEspecie = new javax.swing.JComboBox();
        lblInfraespecie = new javax.swing.JLabel();
        lblNombreComun = new javax.swing.JLabel();
        txtNombreComun = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        lblFormaVida = new javax.swing.JLabel();
        lblCondicion = new javax.swing.JLabel();
        cmbCondicion = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        cmbTipoTocon = new javax.swing.JComboBox();
        lblCondicionMuertoPie = new javax.swing.JLabel();
        cmbCondicionMuertoPie = new javax.swing.JComboBox();
        lblGradoPutrefaccion = new javax.swing.JLabel();
        cmbGradoPutrefaccion = new javax.swing.JComboBox();
        lblDiametroNormal = new javax.swing.JLabel();
        txtDiametroNormal = new javax.swing.JFormattedTextField();
        cmbFormaVida = new javax.swing.JComboBox();
        cmbFormaFuste = new javax.swing.JComboBox();
        lblFormaFuste = new javax.swing.JLabel();
        lblAlturaFusteLimpio = new javax.swing.JLabel();
        txtAlturaFusteLimpio = new javax.swing.JFormattedTextField();
        lblAlturaComercial = new javax.swing.JLabel();
        txtAlturaComercial = new javax.swing.JFormattedTextField();
        jLabel34 = new javax.swing.JLabel();
        txtDiametroCopaNS = new javax.swing.JFormattedTextField();
        jLabel35 = new javax.swing.JLabel();
        txtDiametroCopaEO = new javax.swing.JFormattedTextField();
        lblDanio1 = new javax.swing.JLabel();
        cmbAgenteDanio1 = new javax.swing.JComboBox();
        lblDanio2 = new javax.swing.JLabel();
        cmbAgenteDanio2 = new javax.swing.JComboBox();
        lblAnguloInclinacion = new javax.swing.JLabel();
        txtAnguloInclinacion = new javax.swing.JFormattedTextField();
        lblAlturaTotal = new javax.swing.JLabel();
        txtAlturaTotal = new javax.swing.JFormattedTextField();
        lblPorporcionCopaViva = new javax.swing.JLabel();
        cmbProporcionCopaViva = new javax.swing.JComboBox();
        lblExposicionLuzCopa = new javax.swing.JLabel();
        cmbExposicionLuzCopa = new javax.swing.JComboBox();
        lblPosicionCopa = new javax.swing.JLabel();
        cmbPosicionCopa = new javax.swing.JComboBox();
        lblExposicionLuzCopa1 = new javax.swing.JLabel();
        cmbDencidadCopa = new javax.swing.JComboBox();
        lblMuerteRegresiva = new javax.swing.JLabel();
        cmbMuerteRegresiva = new javax.swing.JComboBox();
        lblTransparenciaFollaje = new javax.swing.JLabel();
        cmbTransparenciaFollaje = new javax.swing.JComboBox();
        lblSeveridad1 = new javax.swing.JLabel();
        cmbSeveridad1 = new javax.swing.JComboBox();
        cmbSeveridad2 = new javax.swing.JComboBox();
        lblSeveridad2 = new javax.swing.JLabel();
        cmbInfraespecie = new javax.swing.JComboBox();
        btnAgregar = new javax.swing.JButton();
        btnElimnar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        grdArbolado = new javax.swing.JTable();
        btnContinuar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        chkEsSubmuestra = new javax.swing.JCheckBox();
        chkArbolado = new javax.swing.JCheckBox();
        lblClaveColecta = new javax.swing.JLabel();
        txtClaveColecta = new javax.swing.JTextField();
        btnColecta = new javax.swing.JButton();
        btnLimparControles = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximizable(true);
        setTitle("Arbolado módulo D "+version);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/gob/conafor/utils/logo_conafor.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(940, 650));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        btnTrazoSitio.setMnemonic('t');
        btnTrazoSitio.setText("Trazo del sitio");
        btnTrazoSitio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrazoSitioActionPerformed(evt);
            }
        });

        lblUPM.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblUPM.setText("UPMID:");

        txtUPM.setEditable(false);
        txtUPM.setEnabled(false);

        lblSitio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblSitio.setText("Sitio:");

        txtSitio.setEditable(false);
        txtSitio.setEnabled(false);

        lblArbolado.setBackground(new java.awt.Color(153, 153, 153));
        lblArbolado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblArbolado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblArbolado.setText("Arbolado");
        lblArbolado.setOpaque(true);

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblNumeroIndividuo.setText("Número de individuo:");

        lblRamaTallo.setText("Número de rama/tallo:");

        lblAzimut.setText("Azimut:");

        lblDistancia.setText("Distancia:");

        lblConsecutivo.setText("Consecutivo:");

        cmbConsecutivo.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cmbConsecutivoPropertyChange(evt);
            }
        });

        txtNumeroIndividuo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###0"))));
        txtNumeroIndividuo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNumeroIndividuoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNumeroIndividuoFocusLost(evt);
            }
        });
        txtNumeroIndividuo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumeroIndividuoKeyTyped(evt);
            }
        });

        txtNumeroRamaTallo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###0"))));
        txtNumeroRamaTallo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNumeroRamaTalloFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNumeroRamaTalloFocusLost(evt);
            }
        });
        txtNumeroRamaTallo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumeroRamaTalloKeyTyped(evt);
            }
        });

        txtAzimut.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###0"))));
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
        txtDistancia.setToolTipText("");
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(lblConsecutivo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbConsecutivo, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblNumeroIndividuo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNumeroIndividuo, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblRamaTallo)
                .addGap(2, 2, 2)
                .addComponent(txtNumeroRamaTallo, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblAzimut)
                .addGap(2, 2, 2)
                .addComponent(txtAzimut, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblDistancia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDistancia, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumeroIndividuo)
                    .addComponent(lblRamaTallo)
                    .addComponent(lblAzimut)
                    .addComponent(lblDistancia)
                    .addComponent(lblConsecutivo)
                    .addComponent(cmbConsecutivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumeroIndividuo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumeroRamaTallo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAzimut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDistancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel7.setBackground(new java.awt.Color(204, 204, 204));
        jPanel7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        lblFamilia.setText("Familia:");

        cmbFamilia.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

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

        lblNombreComun.setText("Nombre común:");

        txtNombreComun.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNombreComunFocusGained(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(204, 204, 204));

        lblFormaVida.setText("FV:");
        lblFormaVida.setToolTipText("Forma Vida");

        lblCondicion.setText("Condición:");

        cmbCondicion.setNextFocusableComponent(cmbCondicionMuertoPie);
        cmbCondicion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCondicionActionPerformed(evt);
            }
        });

        jLabel1.setText("TT:");
        jLabel1.setToolTipText("Tipo Tocón");

        lblCondicionMuertoPie.setText("CMP:");
        lblCondicionMuertoPie.setToolTipText("Condición muerto en píe");

        cmbCondicionMuertoPie.setToolTipText("");
        cmbCondicionMuertoPie.setNextFocusableComponent(cmbFormaFuste);
        cmbCondicionMuertoPie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCondicionMuertoPieActionPerformed(evt);
            }
        });

        lblGradoPutrefaccion.setText("GP:");
        lblGradoPutrefaccion.setToolTipText("Grado de putrefacción");

        cmbGradoPutrefaccion.setToolTipText("");

        lblDiametroNormal.setText("DN:");
        lblDiametroNormal.setToolTipText("Diámetro Normal");

        txtDiametroNormal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###0.###"))));
        txtDiametroNormal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDiametroNormalFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDiametroNormalFocusLost(evt);
            }
        });
        txtDiametroNormal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDiametroNormalKeyTyped(evt);
            }
        });

        cmbFormaVida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFormaVidaActionPerformed(evt);
            }
        });

        cmbFormaFuste.setNextFocusableComponent(cmbGradoPutrefaccion);
        cmbFormaFuste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFormaFusteActionPerformed(evt);
            }
        });

        lblFormaFuste.setText("FF:");
        lblFormaFuste.setToolTipText("Forma Fuste");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(lblFormaVida)
                .addGap(18, 18, 18)
                .addComponent(cmbFormaVida, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lblCondicion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbCondicion, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblCondicionMuertoPie)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbCondicionMuertoPie, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblFormaFuste)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbFormaFuste, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblGradoPutrefaccion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbGradoPutrefaccion, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbTipoTocon, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblDiametroNormal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDiametroNormal, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblFormaFuste)
                        .addComponent(cmbFormaFuste, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblGradoPutrefaccion)
                        .addComponent(cmbGradoPutrefaccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblDiametroNormal)
                        .addComponent(txtDiametroNormal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(cmbTipoTocon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblCondicion)
                        .addComponent(cmbCondicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbFormaVida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblCondicionMuertoPie)
                        .addComponent(cmbCondicionMuertoPie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblFormaVida))
                .addContainerGap())
        );

        lblAlturaFusteLimpio.setText("AFL:");
        lblAlturaFusteLimpio.setToolTipText("Altura Fuste Limpio");

        txtAlturaFusteLimpio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###0.###"))));
        txtAlturaFusteLimpio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtAlturaFusteLimpioFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtAlturaFusteLimpioFocusLost(evt);
            }
        });
        txtAlturaFusteLimpio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAlturaFusteLimpioKeyTyped(evt);
            }
        });

        lblAlturaComercial.setText("AC:");
        lblAlturaComercial.setToolTipText("Altura Comercial");

        txtAlturaComercial.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###0.###"))));
        txtAlturaComercial.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtAlturaComercialFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtAlturaComercialFocusLost(evt);
            }
        });
        txtAlturaComercial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAlturaComercialKeyTyped(evt);
            }
        });

        jLabel34.setText("DC N-S:");
        jLabel34.setToolTipText("Diámetro de Copa Norte-Sur");

        txtDiametroCopaNS.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###0.###"))));
        txtDiametroCopaNS.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDiametroCopaNSFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDiametroCopaNSFocusLost(evt);
            }
        });
        txtDiametroCopaNS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDiametroCopaNSKeyTyped(evt);
            }
        });

        jLabel35.setText("DC E-O:");
        jLabel35.setToolTipText("Diámetro de copa Este-Oeste");

        txtDiametroCopaEO.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###0.###"))));
        txtDiametroCopaEO.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDiametroCopaEOFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDiametroCopaEOFocusLost(evt);
            }
        });
        txtDiametroCopaEO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDiametroCopaEOKeyTyped(evt);
            }
        });

        lblDanio1.setText("Daño 1:");

        cmbAgenteDanio1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAgenteDanio1ActionPerformed(evt);
            }
        });

        lblDanio2.setText("Daño 2:");

        cmbAgenteDanio2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAgenteDanio2ActionPerformed(evt);
            }
        });

        lblAnguloInclinacion.setText("AI > 30:");
        lblAnguloInclinacion.setToolTipText("Ángulo de inclinación > 30");

        txtAnguloInclinacion.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtAnguloInclinacion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtAnguloInclinacionFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtAnguloInclinacionFocusLost(evt);
            }
        });
        txtAnguloInclinacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAnguloInclinacionKeyTyped(evt);
            }
        });

        lblAlturaTotal.setText("AT:");
        lblAlturaTotal.setToolTipText("Altura Total");

        txtAlturaTotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###0.###"))));
        txtAlturaTotal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtAlturaTotalFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtAlturaTotalFocusLost(evt);
            }
        });
        txtAlturaTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAlturaTotalKeyTyped(evt);
            }
        });

        lblPorporcionCopaViva.setText("PCVNC:");
        lblPorporcionCopaViva.setToolTipText("Proporción de copa viva");

        lblExposicionLuzCopa.setText("ECL:");
        lblExposicionLuzCopa.setToolTipText("Exposición a la luz");

        lblPosicionCopa.setText("PC:");
        lblPosicionCopa.setToolTipText("Posición de copa");

        lblExposicionLuzCopa1.setText("DC:");
        lblExposicionLuzCopa1.setToolTipText("Densidad de copa");

        lblMuerteRegresiva.setText("MR:");
        lblMuerteRegresiva.setToolTipText("Muerte regresiva");

        lblTransparenciaFollaje.setText("TF:");
        lblTransparenciaFollaje.setToolTipText("Transparencia de follaje");

        lblSeveridad1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSeveridad1.setText("S1:");
        lblSeveridad1.setToolTipText("Severidad 1");
        lblSeveridad1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        cmbSeveridad1.setEnabled(false);

        cmbSeveridad2.setEnabled(false);

        lblSeveridad2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSeveridad2.setText("S2:");
        lblSeveridad2.setToolTipText("Severidad 2");
        lblSeveridad2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(lblFamilia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                        .addComponent(cmbInfraespecie, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblNombreComun)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombreComun))
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(lblAlturaTotal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtAlturaTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblAnguloInclinacion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtAnguloInclinacion, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblAlturaFusteLimpio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtAlturaFusteLimpio, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblAlturaComercial)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtAlturaComercial, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel34)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDiametroCopaNS, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel35)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDiametroCopaEO, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblPorporcionCopaViva)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbProporcionCopaViva, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblExposicionLuzCopa)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbExposicionLuzCopa, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblPosicionCopa)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbPosicionCopa, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblExposicionLuzCopa1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbDencidadCopa, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(lblMuerteRegresiva)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbMuerteRegresiva, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblTransparenciaFollaje)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbTransparenciaFollaje, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblDanio1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbAgenteDanio1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblSeveridad1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbSeveridad1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblDanio2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbAgenteDanio2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblSeveridad2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbSeveridad2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(15, 15, 15))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblAnguloInclinacion)
                        .addComponent(txtAnguloInclinacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblAlturaTotal)
                        .addComponent(txtAlturaTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblAlturaFusteLimpio)
                        .addComponent(txtAlturaFusteLimpio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblExposicionLuzCopa1)
                        .addComponent(cmbDencidadCopa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblPosicionCopa)
                        .addComponent(cmbPosicionCopa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblExposicionLuzCopa)
                        .addComponent(cmbExposicionLuzCopa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblPorporcionCopaViva)
                        .addComponent(cmbProporcionCopaViva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel35)
                        .addComponent(txtDiametroCopaEO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel34)
                        .addComponent(txtDiametroCopaNS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtAlturaComercial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblAlturaComercial)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblTransparenciaFollaje)
                        .addComponent(cmbTransparenciaFollaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblDanio1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbAgenteDanio1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbSeveridad1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSeveridad1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblDanio2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbAgenteDanio2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbSeveridad2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSeveridad2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblMuerteRegresiva)
                        .addComponent(cmbMuerteRegresiva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
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
                .addGap(0, 0, Short.MAX_VALUE))
        );

        btnAgregar.setMnemonic('a');
        btnAgregar.setText("Agregar");
        btnAgregar.setNextFocusableComponent(btnModificar);
        btnAgregar.setPreferredSize(new java.awt.Dimension(79, 23));
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnElimnar.setMnemonic('e');
        btnElimnar.setText("Eliminar");
        btnElimnar.setNextFocusableComponent(btnContinuar);
        btnElimnar.setPreferredSize(new java.awt.Dimension(79, 23));
        btnElimnar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnElimnarActionPerformed(evt);
            }
        });

        btnModificar.setMnemonic('m');
        btnModificar.setText(" Modificar");
        btnModificar.setNextFocusableComponent(btnElimnar);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        grdArbolado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        grdArbolado.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        grdArbolado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grdArboladoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(grdArbolado);

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

        chkEsSubmuestra.setBackground(new java.awt.Color(204, 204, 204));
        chkEsSubmuestra.setText("Es submuestra:");
        chkEsSubmuestra.setEnabled(false);
        chkEsSubmuestra.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        chkEsSubmuestra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkEsSubmuestraActionPerformed(evt);
            }
        });

        chkArbolado.setBackground(new java.awt.Color(204, 204, 204));
        chkArbolado.setSelected(true);
        chkArbolado.setText("Arbolado");
        chkArbolado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkArboladoActionPerformed(evt);
            }
        });

        lblClaveColecta.setText("Clave:");

        txtClaveColecta.setEnabled(false);

        btnColecta.setMnemonic('o');
        btnColecta.setText("Colecta");
        btnColecta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColectaActionPerformed(evt);
            }
        });

        btnLimparControles.setMnemonic('l');
        btnLimparControles.setText("Limpiar controles");
        btnLimparControles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparControlesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(btnLimparControles)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnContinuar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(343, 343, 343))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblArbolado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnTrazoSitio, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(chkArbolado)
                        .addGap(11, 11, 11)
                        .addComponent(lblUPM)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtUPM, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblSitio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSitio, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(chkEsSubmuestra)
                        .addGap(182, 182, 182)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnModificar)
                        .addGap(18, 18, 18)
                        .addComponent(btnElimnar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnColecta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblClaveColecta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtClaveColecta, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSitio)
                    .addComponent(txtSitio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUPM)
                    .addComponent(txtUPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTrazoSitio)
                    .addComponent(chkArbolado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblArbolado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkEsSubmuestra)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblClaveColecta)
                        .addComponent(txtClaveColecta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnColecta))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnElimnar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnModificar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnContinuar)
                    .addComponent(btnSalir)
                    .addComponent(btnLimparControles))
                .addGap(22, 22, 22))
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

    public void asignarDatosArbolado() {
        try {
            this.consecutivo = Integer.valueOf(txtNumeroRamaTallo.getText());
        } catch (NumberFormatException e) {
            this.consecutivo = null;
        }
        try {
            this.noIndividuo = Integer.valueOf(txtNumeroIndividuo.getText());
        } catch (NumberFormatException e) {
            this.noIndividuo = null;
        }

        try {
            this.noRama = Integer.valueOf(txtNumeroRamaTallo.getText());
        } catch (NumberFormatException e) {
            this.noRama = null;
        }

        try {
            this.azimut = Integer.valueOf(txtAzimut.getText());
        } catch (NumberFormatException e) {
            this.azimut = null;
        }

        try {
            this.azimut = Integer.valueOf(txtAzimut.getText());
        } catch (NumberFormatException e) {
            this.azimut = null;
        }

        try {
            this.distancia = Float.valueOf(txtDistancia.getText());
        } catch (NumberFormatException e) {
            this.distancia = null;
        }

        try {
            this.diametroNormal = Float.valueOf(txtDiametroNormal.getText());
        } catch (NumberFormatException e) {
            this.diametroNormal = null;
        }

        try {
            this.alturaTotal = Float.valueOf(txtAlturaTotal.getText());
        } catch (NumberFormatException e) {
            this.alturaTotal = null;
        }
        try {
            this.anguloInclinacion = Integer.valueOf(txtAnguloInclinacion.getText());
        } catch (NumberFormatException e) {
            this.anguloInclinacion = null;
        }
        try {
            this.alturaFusteLimpio = Float.valueOf(txtAlturaFusteLimpio.getText());
        } catch (NumberFormatException e) {
            this.alturaFusteLimpio = null;
        }
        try {
            this.alturaComercial = Float.valueOf(txtAlturaComercial.getText());
        } catch (NumberFormatException e) {
            this.alturaComercial = null;
        }
        try {
            this.diametroCopaNS = Float.valueOf(txtDiametroCopaNS.getText());
        } catch (NumberFormatException e) {
            this.diametroCopaNS = null;
        }
        try {
            this.diametroCopaEO = Float.valueOf(txtDiametroCopaEO.getText());
        } catch (NumberFormatException e) {
            this.diametroCopaEO = null;
        }
        if (chkEsSubmuestra.isSelected()) {
            this.esSubmuestra = true;
        } else {
            this.esSubmuestra = false;
        }
    }
    
    private void crearArbolado() {
        CatEFamiliaEspecie indexFamilia = (CatEFamiliaEspecie) cmbFamilia.getSelectedItem();
        CatEGenero indexGenero = (CatEGenero) cmbGenero.getSelectedItem();
        CatEEspecie indexEspecie = (CatEEspecie) cmbEspecie.getSelectedItem();
        CatEInfraespecie indexInfraespecie = (CatEInfraespecie) cmbInfraespecie.getSelectedItem();
        CatEFormaVida indexFormaV = (CatEFormaVida) cmbFormaVida.getSelectedItem();
        CatECondicionArbolado indexCondicion = (CatECondicionArbolado) cmbCondicion.getSelectedItem();
        CatECondicionMuertoPie indexMuertoPie = (CatECondicionMuertoPie) cmbCondicionMuertoPie.getSelectedItem();
        CatEGradoPutrefaccionArbolado indexGradoPutrefaccion = (CatEGradoPutrefaccionArbolado) cmbGradoPutrefaccion.getSelectedItem();
        CatEFormaFuste indexFormafuste = (CatEFormaFuste) cmbFormaFuste.getSelectedItem();
        CatETipoTocon indexTipoTocon = (CatETipoTocon) cmbTipoTocon.getSelectedItem();
        CatEPorcentajeArbolado indexProporcionCopa = (CatEPorcentajeArbolado) cmbProporcionCopaViva.getSelectedItem();
        CatEExposicionLuzCopa indexExposicionLuz = (CatEExposicionLuzCopa) cmbExposicionLuzCopa.getSelectedItem();
        CatEPosicionCopa indexPosicionCopa = (CatEPosicionCopa) cmbPosicionCopa.getSelectedItem();
        CatEPorcentajeArbolado indexDensidadCopa = (CatEPorcentajeArbolado) cmbDencidadCopa.getSelectedItem();
        CatEPorcentajeArbolado indexMuerteRegresiva = (CatEPorcentajeArbolado) cmbMuerteRegresiva.getSelectedItem();
        CatEPorcentajeArbolado indexTransparenciaFollaje = (CatEPorcentajeArbolado) cmbTransparenciaFollaje.getSelectedItem();
        

        CEArbolado arb = new CEArbolado();
        
        arb.setSitioID(this.sitioID);
        
        if (indexFamilia != null) {
            arb.setFamiliaID(indexFamilia.getFamiliaID());
        }
        if (indexGenero != null) {
            arb.setGeneroID(indexGenero.getGeneroID());
        }
        if (indexEspecie != null) {
            arb.setEspecieID(indexEspecie.getEspecieID());
        }
        if(indexInfraespecie != null){
            arb.setInfraespecieID(indexInfraespecie.getInfraespecieID());
        }
        if (indexFormaV != null) {
            arb.setFormaVidaID(indexFormaV.getFormaVidaID());
        }
        if (indexCondicion != null) {
            arb.setCondicionID(indexCondicion.getCondicionID());
        }
        if (indexFormafuste != null) {
            arb.setFormaFusteID(indexFormafuste.getFormaFusteID());
        }
        if (indexMuertoPie != null) {
            arb.setMuertoPieID(indexMuertoPie.getMuertoPieID());
        }
        if (indexGradoPutrefaccion != null) {
            arb.setGradoPutrefaccionID(indexGradoPutrefaccion.getGradoPutrefaccionID());
        }
        if (indexTipoTocon != null) {
            arb.setTipoToconID(indexTipoTocon.getTipoToconID());
        }
        if (indexProporcionCopa != null) {
            arb.setProporcionCopaVivaID(indexProporcionCopa.getPorcentajeArboladoID());
        }
        if (indexExposicionLuz != null) {
            arb.setExposisicionCopaID(indexExposicionLuz.getExposicionLuzID());
        }
        if (indexPosicionCopa != null) {
            arb.setPosicionCopaID(indexPosicionCopa.getPosicionCopaID());
        }
        if (indexDensidadCopa != null) {
            arb.setDensidadCopaID(indexDensidadCopa.getPorcentajeArboladoID());
        }
        if (indexMuerteRegresiva != null) {
            arb.setMuerteRegresivaID(indexMuerteRegresiva.getPorcentajeArboladoID());
        }
        if (indexTransparenciaFollaje != null) {
            arb.setTransparenciaFollajeID(indexTransparenciaFollaje.getPorcentajeArboladoID());
        }
        arb.setConsecutivo(this.consecutivo);
        arb.setNumeroRama(this.noRama);
        arb.setNumeroIndividuo(this.noIndividuo);
        arb.setAzimut(this.azimut);
        arb.setDistancia(this.distancia);
        arb.setNombreComun(txtNombreComun.getText());
        arb.setDiametroNormal(this.diametroNormal);
        arb.setAlturaTotal(this.alturaTotal);
        if(this.anguloInclinacion != null){
            arb.setAnguloInclinacion(this.anguloInclinacion);
        }
        arb.setAlturaFusteLimpio(this.alturaFusteLimpio);
        if(this.alturaComercial != null){
            arb.setAlturaComercial(this.alturaComercial);
        }
        arb.setDiametroCopaNS(this.diametroCopaNS);
        arb.setDiametroCopaEO(this.diametroCopaEO);
        arb.setEsSubmuestra(this.esSubmuestra);

        this.cdArbolado.insertArboladoA(arb);
        this.arboladoID = arb.getArboladoID();
        crearDanios(arboladoID);
        
        if(chkEsSubmuestra.isSelected()){
            this.cdSubmuestra.insertSubmuestra(this.sitioID, this.arboladoID);
        }
    }
    
    private void crearDanios(int arboladoID) {
        CEDanioSeveridad ceDanio1 = new CEDanioSeveridad();
        CEDanioSeveridad ceDanio2 = new CEDanioSeveridad();
        CDDanioArbolado cdDanio = new CDDanioArbolado();
        CatEAgenteDanio indexAgenteDanio1 = (CatEAgenteDanio) cmbAgenteDanio1.getSelectedItem();
        CatEAgenteDanio indexAgenteDanio2 = (CatEAgenteDanio) cmbAgenteDanio2.getSelectedItem();
        CatEPorcentajeArbolado indexSeveridad1 = (CatEPorcentajeArbolado) cmbSeveridad1.getSelectedItem();
        CatEPorcentajeArbolado indexSeveridad2 = (CatEPorcentajeArbolado) cmbSeveridad2.getSelectedItem();

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
            severidad1 = indexSeveridad1.getPorcentajeArboladoID();
        } catch (NullPointerException e) {
            severidad1 = null;
        }

        try {
            severidad2 = indexSeveridad2.getPorcentajeArboladoID();
        } catch (NullPointerException e) {
            severidad2 = null;
        }

        ceDanio1.setSeccionID(arboladoID);
        //System.out.println("arbolado ID"+arboladoID);
        ceDanio1.setNumeroDanio(1);
        ceDanio1.setAgenteDanioID(danio1);
        ceDanio1.setSeveridadID(severidad1);

        cdDanio.insertDanioArbolado(ceDanio1);

        ceDanio2.setSeccionID(arboladoID);
        ceDanio2.setNumeroDanio(2);
        ceDanio2.setAgenteDanioID(danio2);
        ceDanio2.setSeveridadID(severidad2);

        cdDanio.insertDanioArbolado(ceDanio2);
    }
    
    private void actualizarArbolado() {
        try {
            int fila = grdArbolado.getSelectedRow();
            String registro = grdArbolado.getValueAt(fila, 0).toString();

            CatEFamiliaEspecie indexFamilia = (CatEFamiliaEspecie) cmbFamilia.getSelectedItem();
            CatEGenero indexGenero = (CatEGenero) cmbGenero.getSelectedItem();
            CatEEspecie indexEspecie = (CatEEspecie) cmbEspecie.getSelectedItem();
            CatEInfraespecie indexInfraespecie = (CatEInfraespecie) cmbInfraespecie.getSelectedItem();
            CatEFormaVida indexFormaV = (CatEFormaVida) cmbFormaVida.getSelectedItem();
            CatECondicionArbolado indexCondicion = (CatECondicionArbolado) cmbCondicion.getSelectedItem();
            CatECondicionMuertoPie indexMuertoPie = (CatECondicionMuertoPie) cmbCondicionMuertoPie.getSelectedItem();
            CatEGradoPutrefaccionArbolado indexGradoPutrefaccion = (CatEGradoPutrefaccionArbolado) cmbGradoPutrefaccion.getSelectedItem();
            CatEFormaFuste indexFormafuste = (CatEFormaFuste) cmbFormaFuste.getSelectedItem();
            CatETipoTocon indexTipoTocon = (CatETipoTocon) cmbTipoTocon.getSelectedItem();
            CatEAgenteDanio indexDanio1 = (CatEAgenteDanio) cmbAgenteDanio1.getSelectedItem();
            CatEAgenteDanio indexDanio2 = (CatEAgenteDanio) cmbAgenteDanio2.getSelectedItem();
            CatEPorcentajeArbolado indexSeveridad1 = (CatEPorcentajeArbolado) cmbSeveridad1.getSelectedItem();
            CatEPorcentajeArbolado indexSeveridad2 = (CatEPorcentajeArbolado) cmbSeveridad2.getSelectedItem();
            CatEPorcentajeArbolado indexProporcionCopa = (CatEPorcentajeArbolado) cmbProporcionCopaViva.getSelectedItem();
            CatEExposicionLuzCopa indexExposicionLuz = (CatEExposicionLuzCopa) cmbExposicionLuzCopa.getSelectedItem();
            CatEPosicionCopa indexPosicionCopa = (CatEPosicionCopa) cmbPosicionCopa.getSelectedItem();
            CatEPorcentajeArbolado indexDensidadCopa = (CatEPorcentajeArbolado) cmbDencidadCopa.getSelectedItem();
            CatEPorcentajeArbolado indexMuerteRegresiva = (CatEPorcentajeArbolado) cmbMuerteRegresiva.getSelectedItem();
            CatEPorcentajeArbolado indexTransparenciaFollaje = (CatEPorcentajeArbolado) cmbTransparenciaFollaje.getSelectedItem();

            CEArbolado arb = new CEArbolado();
            this.arboladoID = Integer.parseInt(registro);

            arb.setSitioID(this.sitioID);
            arb.setArboladoID(this.arboladoID);

            if (indexFamilia != null) {
                arb.setFamiliaID(indexFamilia.getFamiliaID());
            } else {
                arb.setFamiliaID(null);
            }
            if (indexGenero != null) {
                arb.setGeneroID(indexGenero.getGeneroID());
            } else {
                arb.setGeneroID(null);
            }
            if (indexEspecie != null) {
                arb.setEspecieID(indexEspecie.getEspecieID());
            } else {
                arb.setEspecieID(null);
            }
            if(indexInfraespecie != null){
                arb.setInfraespecieID(indexInfraespecie.getInfraespecieID());
            } else {
                arb.setInfraespecieID(null);
            }
            if (indexFormaV != null) {
                arb.setFormaVidaID(indexFormaV.getFormaVidaID());
            }
            if (indexCondicion != null) {
                arb.setCondicionID(indexCondicion.getCondicionID());
            }
            if (indexFormafuste != null) {
                arb.setFormaFusteID(indexFormafuste.getFormaFusteID());
            }
            if (indexMuertoPie != null) {
                arb.setMuertoPieID(indexMuertoPie.getMuertoPieID());
            }
            if (indexGradoPutrefaccion != null) {
                arb.setGradoPutrefaccionID(indexGradoPutrefaccion.getGradoPutrefaccionID());
            }
            if (indexTipoTocon != null) {
                arb.setTipoToconID(indexTipoTocon.getTipoToconID());
            }
            if (indexProporcionCopa != null) {
                arb.setProporcionCopaVivaID(indexProporcionCopa.getPorcentajeArboladoID());
            }
            if (indexExposicionLuz != null) {
                arb.setExposisicionCopaID(indexExposicionLuz.getExposicionLuzID());
            }
            if (indexPosicionCopa != null) {
                arb.setPosicionCopaID(indexPosicionCopa.getPosicionCopaID());
            }
            if (indexDensidadCopa != null) {
                arb.setDensidadCopaID(indexDensidadCopa.getPorcentajeArboladoID());
            }
            if (indexMuerteRegresiva != null) {
                arb.setMuerteRegresivaID(indexMuerteRegresiva.getPorcentajeArboladoID());
            }
            if (indexTransparenciaFollaje != null) {
                arb.setTransparenciaFollajeID(indexTransparenciaFollaje.getPorcentajeArboladoID());
            }
            arb.setConsecutivo(this.consecutivo);
            arb.setNumeroIndividuo(this.noIndividuo);
            arb.setNumeroRama(this.noRama);
            arb.setAzimut(this.azimut);
            arb.setDistancia(this.distancia);
            arb.setNombreComun(txtNombreComun.getText());
            arb.setDiametroNormal(this.diametroNormal);
            arb.setAlturaTotal(this.alturaTotal);
            arb.setAnguloInclinacion(this.anguloInclinacion);
            arb.setAlturaFusteLimpio(this.alturaFusteLimpio);
            if (this.alturaComercial != null) {
                arb.setAlturaComercial(this.alturaComercial);
            }
            arb.setDiametroCopaNS(this.diametroCopaNS);
            arb.setDiametroCopaEO(this.diametroCopaEO);
            
            if (chkEsSubmuestra.isSelected()) {
                arb.setEsSubmuestra(true);
            } else {
                arb.setEsSubmuestra(false);
            }
            
            CEDanioSeveridad danio1 = new CEDanioSeveridad();
            CEDanioSeveridad danio2 = new CEDanioSeveridad();
            CDDanioArbolado cdDanio = new CDDanioArbolado();

            danio1.setSeccionID(this.arboladoID);
            danio1.setNumeroDanio(1);
            danio1.setAgenteDanioID(indexDanio1.getAgenteDanioID());
            if (indexSeveridad1 != null) {
                danio1.setSeveridadID(indexSeveridad1.getPorcentajeArboladoID());
            }
            
            cdDanio.updateDanioArbolado(danio1);
            
            danio2.setSeccionID(this.arboladoID);
            danio2.setNumeroDanio(2);
            danio2.setAgenteDanioID(indexDanio2.getAgenteDanioID());
            if (indexSeveridad2 != null) {
                danio2.setSeveridadID(indexSeveridad2.getPorcentajeArboladoID());
            }
            
            cdDanio.updateDanioArbolado(danio2);


            this.cdArbolado.updateArboladoA(arb);

            limpiarControles();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla de arbolado"
                    + e.getClass().getName() + " : " + e.getMessage(), "Arbolado", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarArbolado() {
        try {
            int fila = grdArbolado.getSelectedRow();
            String registro = grdArbolado.getValueAt(fila, 0).toString();
            Object[] opciones = {"Si", "No"};
            int respuesta = JOptionPane.showOptionDialog(null, "Se eliminará el registro, ¿Esta seguro?",
                    "Arbolado", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[1]);
            if (respuesta == JOptionPane.YES_NO_OPTION) {
                CEArbolado arb = new CEArbolado();
                int arboladoID = Integer.parseInt(registro);
                arb.setArboladoID(arboladoID);
                this.cdArbolado.deleteDatosArbolado(arb);
                eliminarDanio(arboladoID);
                limpiarControles();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla de arbolado"
                    + "", "Arbolado", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarDanio(int arboladoID) {
         CEDanioSeveridad danio = new CEDanioSeveridad();
        CDDanioArbolado cdDanio = new CDDanioArbolado();
        danio.setSeccionID(arboladoID);
        cdDanio.deleteDanioArbolado(danio);
    }

    public void limpiarControles() {
        cmbConsecutivo.setSelectedItem(null);
        txtNumeroIndividuo.setText("");
        txtNumeroRamaTallo.setText("");
        txtAzimut.setText("");
        txtDistancia.setText("");
        cmbFamilia.setSelectedItem(null);
        cmbGenero.setSelectedItem(null);
        cmbEspecie.setSelectedItem(null);
        cmbInfraespecie.setSelectedItem(null);
        txtNombreComun.setText("");
        cmbFormaVida.setSelectedItem(null);
        cmbCondicion.setSelectedItem(null);
        cmbFormaFuste.setSelectedItem(null);
        cmbCondicionMuertoPie.setSelectedItem(null);
        cmbGradoPutrefaccion.setSelectedItem(null);
        cmbTipoTocon.setSelectedItem(null);
        cmbProporcionCopaViva.setSelectedItem(null);
        cmbExposicionLuzCopa.setSelectedItem(null);
        cmbPosicionCopa.setSelectedItem(null);
        cmbDencidadCopa.setSelectedItem(null);
        cmbMuerteRegresiva.setSelectedItem(null);
        cmbTransparenciaFollaje.setSelectedItem(null);
        txtDiametroNormal.setValue(null);
        txtDiametroNormal.setText("");
        txtAlturaTotal.setValue(null);
        txtAlturaTotal.setText("");
        txtAnguloInclinacion.setValue(null);
        txtAnguloInclinacion.setText("");
        txtAlturaFusteLimpio.setValue(null);
        txtAlturaFusteLimpio.setText("");
        txtAlturaComercial.setValue(null);
        txtAlturaComercial.setText("");
        txtDiametroCopaNS.setValue(null);
        txtDiametroCopaNS.setText("");
        txtDiametroCopaEO.setValue(null);
        txtDiametroCopaEO.setText("");
        cmbAgenteDanio1.setSelectedIndex(0);
        cmbAgenteDanio2.setSelectedIndex(0);
        txtNumeroIndividuo.requestFocus();
        chkEsSubmuestra.setSelected(false);
        txtClaveColecta.setText("");
        txtNumeroRamaTallo.setEnabled(true);
    }
    
    public void evitarCapturaPorTrazo(CESitio sitio) {
        CDTrazoSitio trazo = new CDTrazoSitio();

        int[] datosTrazo = trazo.validarCapturaTrazo(sitio);

        if (datosTrazo[0] == 0 && datosTrazo[1] == 0) {
            chkArbolado.setEnabled(false);
            deshabiliarControles();
        } else if (datosTrazo[0] == 1 || datosTrazo[1] == 1) {
            chkArbolado.setEnabled(true);
            habilitarControles();
            estadoArbolVivo();
        }
    }

    private void deshabiliarControles() {
        cmbConsecutivo.setEnabled(false);
        txtNumeroIndividuo.setEnabled(false);
        //txtNumeroRamaTallo.setEnabled(false);
        txtAzimut.setEnabled(false);
        txtDistancia.setEnabled(false);
        cmbFamilia.setEnabled(false);
        cmbGenero.setEnabled(false);
        cmbEspecie.setEnabled(false);
        cmbInfraespecie.setEnabled(false);
        txtNombreComun.setEnabled(false);
        cmbFormaVida.setEnabled(false);
        cmbFormaFuste.setEnabled(false);
        cmbCondicion.setEnabled(false);
        cmbTipoTocon.setEnabled(false);
        cmbProporcionCopaViva.setEnabled(false);
        cmbExposicionLuzCopa.setEnabled(false);
        cmbPosicionCopa.setEnabled(false);
        cmbDencidadCopa.setEnabled(false);
        cmbMuerteRegresiva.setEnabled(false);
        cmbTransparenciaFollaje.setEnabled(false);
        txtDiametroNormal.setEnabled(false);
        txtAlturaTotal.setEnabled(false);
        txtAnguloInclinacion.setEnabled(false);
        txtAlturaFusteLimpio.setEnabled(false);
        txtAlturaComercial.setEnabled(false);
        txtDiametroCopaNS.setEnabled(false);
        txtDiametroCopaEO.setEnabled(false);
        cmbAgenteDanio1.setEnabled(false);
        cmbSeveridad1.setEnabled(false);
        cmbAgenteDanio2.setEnabled(false);
        cmbSeveridad2.setEnabled(false);
    }

    private void habilitarControles() {
        cmbConsecutivo.setEnabled(true);
        txtNumeroIndividuo.setEnabled(true);
        //txtNumeroRamaTallo.setEnabled(true);
        txtAzimut.setEnabled(true);
        txtDistancia.setEnabled(true);
        cmbFamilia.setEnabled(true);
        cmbGenero.setEnabled(true);
        cmbEspecie.setEnabled(true);
        cmbInfraespecie.setEnabled(true);
        txtNombreComun.setEnabled(true);
        cmbFormaVida.setEnabled(true);
        cmbFormaFuste.setEnabled(true);
        cmbCondicion.setEnabled(true);
        cmbTipoTocon.setEnabled(true);
         cmbProporcionCopaViva.setEnabled(true);
        cmbExposicionLuzCopa.setEnabled(true);
        cmbPosicionCopa.setEnabled(true);
        cmbDencidadCopa.setEnabled(true);
        cmbMuerteRegresiva.setEnabled(true);
        cmbTransparenciaFollaje.setEnabled(true);
        txtDiametroNormal.setEnabled(true);
        txtAlturaTotal.setEnabled(true);
        txtAnguloInclinacion.setEnabled(true);
        txtAlturaFusteLimpio.setEnabled(true);
        txtAlturaComercial.setEnabled(true);
        txtDiametroCopaNS.setEnabled(true);
        txtDiametroCopaEO.setEnabled(true);
        cmbAgenteDanio1.setEnabled(true);
       // cmbSeveridad1.setEnabled(true);
        cmbAgenteDanio2.setEnabled(true);
       // cmbSeveridad2.setEnabled(true);
    }
    
    private boolean validarDistanciaCuadrante(Integer azimut, Float distancia) {
        CDTrazoSitio cdTrazoSitio = new CDTrazoSitio();
        CESitio trazo = cdTrazoSitio.getTrazoSitio(this.ceSitio);

        Float distancia1 = trazo.getDistancia1();
        Float distancia2 = trazo.getDistancia2();
        Float distancia3 = trazo.getDistancia3();
        Float distancia4 = trazo.getDistancia4();

        if ((azimut >= 0 && azimut <= 90) && distancia > distancia1) {
            JOptionPane.showMessageDialog(null, "La distancia introducida excede a laintrudicida en el cuadrante 1", "Arbolado", JOptionPane.ERROR_MESSAGE);
            txtDistancia.requestFocus();
            return false;
        } else if ((azimut >= 91 && azimut <= 180) && distancia > distancia2) {
            JOptionPane.showMessageDialog(null, "La distancia introducida excede a laintrudicida en el cuadrante 2", "Arbolado", JOptionPane.ERROR_MESSAGE);
            txtDistancia.requestFocus();
            return false;
        } else if ((azimut >= 181 && azimut <= 270) && distancia > distancia3) {
            JOptionPane.showMessageDialog(null, "La distancia introducida excede a laintrudicida en el cuadrante 3", "Arbolado", JOptionPane.ERROR_MESSAGE);
            txtDistancia.requestFocus();
            return false;
        } else if ((azimut >= 271 && azimut <= 359) && distancia > distancia4) {
            JOptionPane.showMessageDialog(null, "La distancia introducida excede a laintrudicida en el cuadrante 4", "Arbolado", JOptionPane.ERROR_MESSAGE);
            txtDistancia.requestFocus();
            return false;
        }
        return true;
    }
    
    
        private boolean validarCamposObligatorioModificar() {
        if (txtNumeroIndividuo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo número de individuo es obligatorio", "Arbolado A", JOptionPane.INFORMATION_MESSAGE);
            txtNumeroIndividuo.requestFocus();
            return false;
        } else if (txtNumeroRamaTallo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo de rama o tallo es obligatorio", "Arbolado", JOptionPane.ERROR_MESSAGE);
            txtNumeroRamaTallo.requestFocus();
            return false;
        } else if (txtAzimut.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo azimut es obligatorio", "Arbolado", JOptionPane.ERROR_MESSAGE);
            txtAzimut.requestFocus();
            return false;
        } else if (txtDistancia.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo distancia es obligatorio", "Arbolado", JOptionPane.ERROR_MESSAGE);
            txtDistancia.requestFocus();
            return false;
        } else if (txtDiametroNormal.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo diámetro normal es obligatorio", "Arbolado", JOptionPane.ERROR_MESSAGE);
            txtDiametroNormal.requestFocus();
            return false;
        } else if (cmbFormaVida.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar un tipo de forma de vida", "Arbolado", JOptionPane.ERROR_MESSAGE);
            cmbFormaVida.requestFocus();
            return false;
        } else if (cmbCondicion.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar un tipo de condicion", "Arbolado", JOptionPane.ERROR_MESSAGE);
            cmbCondicion.requestFocus();
            return false;
        }
        return true;
    }
    
    private boolean validarCamposObligatorio() {
        if (txtNumeroIndividuo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo número de individuo es obligatorio", "Arbolado A", JOptionPane.INFORMATION_MESSAGE);
            txtNumeroIndividuo.requestFocus();
            return false;
        } else if (txtNumeroRamaTallo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo de rama o tallo es obligatorio", "Arbolado", JOptionPane.ERROR_MESSAGE);
            txtNumeroRamaTallo.requestFocus();
            return false;
        } else if (cdArbolado.validarNumeroRamatallo/*se encuentra en taxonomia.mod.CDArbolado*/(this.sitioID, this.noRama)) { 
            JOptionPane.showMessageDialog(null, "El número de rama o tallo no debe repetirse", "Arbolado", JOptionPane.ERROR_MESSAGE);
            txtNumeroRamaTallo.setValue(null);
            txtNumeroRamaTallo.setText("");
            txtNumeroRamaTallo.requestFocus();
            return false;
        } else if (txtAzimut.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo azimut es obligatorio", "Arbolado", JOptionPane.ERROR_MESSAGE);
            txtAzimut.requestFocus();
            return false;
        } else if (txtDistancia.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo distancia es obligatorio", "Arbolado", JOptionPane.ERROR_MESSAGE);
            txtDistancia.requestFocus();
            return false;
        } else if (txtDiametroNormal.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo diámetro normal es obligatorio", "Arbolado", JOptionPane.ERROR_MESSAGE);
            txtDiametroNormal.requestFocus();
            return false;
        } else if (cmbFormaVida.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar un tipo de forma de vida", "Arbolado", JOptionPane.ERROR_MESSAGE);
            cmbFormaVida.requestFocus();
            return false;
        } else if (cmbCondicion.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar un tipo de condicion", "Arbolado", JOptionPane.ERROR_MESSAGE);
            cmbCondicion.requestFocus();
            return false;
        }
        return true;
    }
    
    private boolean validarMedicionesObligatorias() {
        ValidacionesComunes validacionC = new ValidacionesComunes();
        ValidacionesArbolado validacionAR = new ValidacionesArbolado();

        if (validacionAR.esNumeracion(this.noIndividuo)) {
            txtNumeroIndividuo.requestFocus();
            return false;
        } else if (validacionC.esAzimut(this.azimut, "Arbolado")) {
            txtAzimut.requestFocus();
            return false;
        } else if (validacionAR.esDistanciaArbolado(this.distancia)) {
            txtDistancia.requestFocus();
            return false;
        } else if (validacionAR.esDiametroNormal(this.diametroNormal)) {
            txtDiametroNormal.requestFocus();
            return false;
        } else if (validacionAR.esAlturaTotal(this.alturaTotal)) {
            txtAlturaTotal.requestFocus();
            return false;
        } else {
            return true;
        }
    }
    
    private boolean validarMedicionesOpcionales() {
        ValidacionesArbolado validacionAR = new ValidacionesArbolado();
        if (txtAnguloInclinacion.isEnabled() == true && txtAnguloInclinacion != null && validacionAR.esAnguloInclinacion(this.anguloInclinacion)) {
            txtAnguloInclinacion.requestFocus();
            return false;
        } else if (txtAlturaFusteLimpio.isEnabled() &&  validacionAR.esAlturaFusteLimpio(this.alturaFusteLimpio)) {
            txtAlturaFusteLimpio.requestFocus();
            return false;
        }  else if (txtDiametroCopaNS.isEnabled() && validacionAR.esDiametroCopaNS(this.diametroCopaNS)) {
            txtDiametroCopaNS.requestFocus();
            return false;
        } else if (txtDiametroCopaEO.isEnabled() && validacionAR.esDiametroCopaEO(this.diametroCopaEO)) {
            txtDiametroCopaEO.requestFocus();
            return false;
        
        } else {
            return true;
        }
    }
    
    private boolean validarAlturaComercial() {
        ValidacionesArbolado validacionAR = new ValidacionesArbolado();
        //System.out.println(cmbCondicion.getSelectedItem());
        if (this.diametroNormal >= 10 && txtAlturaComercial.getText().isEmpty()&&cmbCondicionMuertoPie.getSelectedIndex()<2&& cmbCondicion.getSelectedItem().equals("3-Tocon (corta autorizada)") && cmbCondicion.getSelectedItem().equals("4-Tocon (corta clandestina)")) {//si la altura <10, esta vacio Altura comercial y no son c o d en muerto en pie y si NO es tocon
            JOptionPane.showMessageDialog(null, "Error! Si el diámetro normal es mayor o igual a 10 se debe capturar altura comercial", "Arbolado D", JOptionPane.INFORMATION_MESSAGE);
            txtAlturaComercial.requestFocus();
            return false;
        } else if (this.diametroNormal >= 10 && !txtAlturaComercial.getText().isEmpty()) {
            if(validacionAR.esAlturaComercial(this.alturaComercial)){
                return false;
            } else {
                 return true;
            }
           
        } else {
            return true;
        }
    }
    
    private boolean validarCamposOpcionales() {
        CatECondicionArbolado condicion = (CatECondicionArbolado) cmbCondicion.getSelectedItem();
        CatEFormaVida formaVida = (CatEFormaVida) cmbFormaVida.getSelectedItem();
        if (cmbFormaFuste.isEnabled() && cmbFormaFuste.getSelectedItem() == null && condicion.getCondicionID() == 1) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar una forma de fuste", "Arbolado", JOptionPane.INFORMATION_MESSAGE);
            cmbFormaFuste.requestFocus();
            return false;
        } else if (cmbCondicionMuertoPie.isEnabled() && cmbCondicionMuertoPie.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar una condicion para muerto en pie", "Arbolado", JOptionPane.INFORMATION_MESSAGE);
            cmbCondicionMuertoPie.requestFocus();
            return false;
        } else if (cmbGradoPutrefaccion.isEnabled() && cmbGradoPutrefaccion.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar una grado de putrefaccion para muerto en pie", "Arbolado", JOptionPane.INFORMATION_MESSAGE);
            cmbGradoPutrefaccion.requestFocus();
            return false;
        } else if (cmbTipoTocon.isEnabled() && cmbTipoTocon.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Error! Si selecciono condicion de tocon debe seleccionar un tipo", "Arbolado", JOptionPane.INFORMATION_MESSAGE);
            cmbTipoTocon.requestFocus();
            return false;
        } else if (txtAlturaTotal.isEnabled() && this.alturaTotal == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe capturar la altura total", "Arbolado", JOptionPane.INFORMATION_MESSAGE);
            txtAlturaTotal.requestFocus();
            return false;
        } else/* if (txtAnguloInclinacion.isEnabled() && this.anguloInclinacion == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe capturar el angulo de inclinacion", "Arbolado", JOptionPane.INFORMATION_MESSAGE);
            txtAnguloInclinacion.requestFocus();
            return false;
        } else */if (txtAlturaFusteLimpio.isEnabled() && formaVida.getFormaVidaID() == 1 && this.alturaFusteLimpio == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe capturar la altura fuste limpio", "Arbolado", JOptionPane.INFORMATION_MESSAGE);
            txtAlturaFusteLimpio.requestFocus();
            return false;
        } else if (txtDiametroCopaNS.isEnabled() && this.diametroCopaNS == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe capturar el diametro de copa N-S", "Arbolado", JOptionPane.INFORMATION_MESSAGE);
            txtDiametroCopaNS.requestFocus();
            return false;
        } else if (txtDiametroCopaEO.isEnabled() && this.diametroCopaEO == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe capturar el diametro de copa E-O", "Arbolado", JOptionPane.INFORMATION_MESSAGE);
            txtDiametroCopaEO.requestFocus();
            return false;
        } else if (cmbProporcionCopaViva.isEnabled() && cmbProporcionCopaViva.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar proporción de copa viva", "Arbolado", JOptionPane.INFORMATION_MESSAGE);
            cmbProporcionCopaViva.requestFocus();
            return false;
        } else if (cmbExposicionLuzCopa.isEnabled() && cmbExposicionLuzCopa.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar exposicion luz copa ", "Arbolado", JOptionPane.INFORMATION_MESSAGE);
            cmbExposicionLuzCopa.requestFocus();
            return false;
        } else if (cmbPosicionCopa.isEnabled() && cmbPosicionCopa.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar posición de copa ", "Arbolado", JOptionPane.INFORMATION_MESSAGE);
            cmbPosicionCopa.requestFocus();
            return false;
        } else if (cmbDencidadCopa.isEnabled() && cmbDencidadCopa.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar densidad de copa ", "Arbolado", JOptionPane.INFORMATION_MESSAGE);
            cmbDencidadCopa.requestFocus();
            return false;
        } else if (cmbMuerteRegresiva.isEnabled() && cmbMuerteRegresiva.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar muerte regresiva ", "Arbolado", JOptionPane.INFORMATION_MESSAGE);
            cmbMuerteRegresiva.requestFocus();
            return false;
        } else if (cmbTransparenciaFollaje.isEnabled() && cmbTransparenciaFollaje.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar transparencia de follaje ", "Arbolado", JOptionPane.INFORMATION_MESSAGE);
            cmbTransparenciaFollaje.requestFocus();
            return false;
        } else {
            return true;
        }
    }
    
    private boolean validarDanioObligatorio() {
        CatEAgenteDanio agenteDanio = (CatEAgenteDanio) cmbAgenteDanio1.getSelectedItem();
        if (agenteDanio.getAgenteDanioID() == 1) {
            JOptionPane.showMessageDialog(null, "Error! Debe capturar al menos un daño", "Arbolado ", JOptionPane.ERROR_MESSAGE);
            cmbAgenteDanio1.requestFocus();
            return false;
        } else {
            return true;
        }
    }
    
    private boolean validarCampoDanio() {
      if (cmbSeveridad1.isEnabled() && cmbSeveridad1.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Error! Debe de seleccionar un nivel de severidad 1 ", "Arbolado D", JOptionPane.INFORMATION_MESSAGE);
            cmbSeveridad1.requestFocus();
            return false;
        } else if (cmbSeveridad2.isEnabled() && cmbSeveridad2.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Error! Debe de seleccionar un nivel de severidad 2 ", "Arbolado D", JOptionPane.INFORMATION_MESSAGE);
            cmbSeveridad2.requestFocus();
            return false;
        } else {
            return true;
        }
    }
    
    /*private boolean validarRamaTallo() {
        int registros = cdArbolado.getRamaTallo(this.sitioID, this.noIndividuo, this.noRama);
        if (registros > 0) {
            JOptionPane.showMessageDialog(null, "Error! El numero de rama o tallo no puede repetirse en el mismo individuo", "Arbolado", JOptionPane.ERROR_MESSAGE);
            txtNumeroRamaTallo.requestFocus();
            return false;
        } else {
            return true;
        }
    }*/
    
    private boolean validarColectasObligatorias() {
        CDColectaBotanica colecta = new CDColectaBotanica();
        if (colecta.validarCapturaEspecie("TAXONOMIA_Arbolado", this.sitioID)) {
            JOptionPane.showMessageDialog(null, "Error! Faltan por asignar claves de colecta", "Arbolado D", JOptionPane.INFORMATION_MESSAGE);
            return false;
        } else {
            return true;
        }
    }
    
    private void estadoArbolVivo() {
        cmbCondicionMuertoPie.setEnabled(false);
        cmbCondicionMuertoPie.setSelectedItem(null);
        cmbFormaFuste.setEnabled(true);
        cmbFormaFuste.setSelectedItem(null);
        cmbTipoTocon.setEnabled(false);
        cmbTipoTocon.setSelectedItem(null);
        //cmbFormaVida.setSelectedItem(null);
       
        cmbProporcionCopaViva.setEnabled(true);
        cmbExposicionLuzCopa.setEnabled(true);
        cmbPosicionCopa.setEnabled(true);
        cmbDencidadCopa.setEnabled(true);
        cmbMuerteRegresiva.setEnabled(true);
        cmbTransparenciaFollaje.setEnabled(true);
        cmbFormaFuste.setEnabled(true);
        cmbGradoPutrefaccion.setEnabled(false);
        cmbGradoPutrefaccion.setSelectedItem(null);
        txtDiametroNormal.setEnabled(true);
        txtAlturaTotal.setEnabled(true);
        txtAnguloInclinacion.setEnabled(true);
        txtAlturaFusteLimpio.setEnabled(true);
        txtAlturaComercial.setEnabled(true);
        txtDiametroCopaNS.setEnabled(true);
        txtDiametroCopaEO.setEnabled(true);
    
    }
    
    private void estadoMuertoPie(){
        cmbCondicionMuertoPie.setEnabled(true);
        cmbFormaFuste.setEnabled(false);
        cmbFormaFuste.setSelectedItem(null);
        cmbTipoTocon.setEnabled(false);
        cmbTipoTocon.setSelectedItem(null);
        cmbGradoPutrefaccion.setEnabled(false);
        cmbGradoPutrefaccion.setSelectedItem(null);
        cmbProporcionCopaViva.setEnabled(false);
        cmbProporcionCopaViva.setSelectedItem(null);
        cmbExposicionLuzCopa.setEnabled(false);
        cmbExposicionLuzCopa.setSelectedItem(null);
        cmbPosicionCopa.setEnabled(false);
        cmbPosicionCopa.setSelectedItem(null);
        cmbDencidadCopa.setEnabled(false);
        cmbDencidadCopa.setSelectedItem(null);
        cmbMuerteRegresiva.setEnabled(false);
        cmbMuerteRegresiva.setSelectedItem(null);
        cmbTransparenciaFollaje.setEnabled(false);
        cmbTransparenciaFollaje.setSelectedItem(null);
        txtDiametroNormal.setEnabled(true);
        txtAlturaTotal.setEnabled(true);
        txtAnguloInclinacion.setEnabled(true);
        txtAlturaFusteLimpio.setEnabled(false);
        txtAlturaFusteLimpio.setText("");
        txtAlturaFusteLimpio.setValue(null);
        txtAlturaComercial.setEnabled(true);
        txtDiametroCopaNS.setEnabled(false);
        txtDiametroCopaNS.setText("");
        txtDiametroCopaNS.setValue(null);
        txtDiametroCopaEO.setEnabled(false);
        txtDiametroCopaEO.setText("");
        txtDiametroCopaEO.setValue(null);
    }
    
    private void estadoMuertoPieCD(int muertoPieID){
        cmbGradoPutrefaccion.setEnabled(false);
        //cmbGradoPutrefaccion.setSelectedItem(null);
        cmbTipoTocon.setEnabled(false);
        cmbTipoTocon.setSelectedItem(null);
        cmbFormaFuste.setEnabled(false);
        cmbFormaFuste.setSelectedItem(null);
        txtDiametroNormal.setEnabled(true);
        txtAlturaTotal.setEnabled(true);
        txtAnguloInclinacion.setEnabled(false);
        txtAnguloInclinacion.setText("");
        txtAnguloInclinacion.setValue(null);
        txtAlturaFusteLimpio.setEnabled(false);
        txtAlturaFusteLimpio.setText("");
        txtAlturaFusteLimpio.setValue(null);
        txtAlturaComercial.setEnabled(false);
        txtAlturaComercial.setText("");
        txtAlturaComercial.setValue(null);
        txtDiametroCopaNS.setEnabled(false);
        txtDiametroCopaNS.setText("");
        txtDiametroCopaNS.setValue(null);
        txtDiametroCopaEO.setEnabled(false);
        txtDiametroCopaEO.setText("");
        txtDiametroCopaEO.setValue(null);
        
        if(muertoPieID == 4){
            cmbGradoPutrefaccion.setEnabled(true);
        }else{
            cmbGradoPutrefaccion.setEnabled(false);
        }
    }
    
    private void quitarVariablesArboladoD() {
        cmbProporcionCopaViva.setEnabled(false);
        cmbExposicionLuzCopa.setEnabled(false);
        cmbPosicionCopa.setEnabled(false);
        cmbDencidadCopa.setEnabled(false);
        cmbMuerteRegresiva.setEnabled(false);
        cmbTransparenciaFollaje.setEnabled(false);

        cmbProporcionCopaViva.setSelectedItem(null);
        cmbExposicionLuzCopa.setSelectedItem(null);
        cmbPosicionCopa.setSelectedItem(null);
        cmbDencidadCopa.setSelectedItem(null);
        cmbMuerteRegresiva.setSelectedItem(null);
        cmbTransparenciaFollaje.setSelectedItem(null);
    }
    
    private void estadoArbolTocon(){
        cmbCondicionMuertoPie.setEnabled(false);
        cmbCondicionMuertoPie.setSelectedItem(null);
        cmbFormaFuste.setEnabled(false);
        cmbFormaFuste.setSelectedItem(null);
        cmbGradoPutrefaccion.setEnabled(false);
        cmbGradoPutrefaccion.setSelectedItem(null);
        cmbTipoTocon.setEnabled(true);
        cmbProporcionCopaViva.setEnabled(false);
        cmbProporcionCopaViva.setSelectedItem(null);
        cmbExposicionLuzCopa.setEnabled(false);
        cmbExposicionLuzCopa.setSelectedItem(null);
        cmbPosicionCopa.setEnabled(false);
        cmbPosicionCopa.setSelectedItem(null);
        cmbDencidadCopa.setEnabled(false);
        cmbDencidadCopa.setSelectedItem(null);
        cmbMuerteRegresiva.setEnabled(false);
        cmbMuerteRegresiva.setSelectedItem(null);
        cmbTransparenciaFollaje.setEnabled(false);
        cmbTransparenciaFollaje.setSelectedItem(null);
        txtDiametroNormal.setEnabled(true);
        txtAlturaTotal.setEnabled(true);
        txtAnguloInclinacion.setEnabled(false);
        txtAnguloInclinacion.setText("");
        txtAnguloInclinacion.setValue(null);
        txtAlturaFusteLimpio.setEnabled(false);
        txtAlturaFusteLimpio.setText("");
        txtAlturaFusteLimpio.setValue(null);
        txtAlturaComercial.setEnabled(false);
        txtAlturaComercial.setText("");
        txtAlturaComercial.setValue(null);
        txtDiametroCopaNS.setEnabled(false);
        txtDiametroCopaNS.setText("");
        txtDiametroCopaNS.setValue(null);
        txtDiametroCopaEO.setEnabled(false);
        txtDiametroCopaEO.setText("");
        txtDiametroCopaEO.setValue(null);
    }
    
    private void estadoLianaCactaceasCaniasVivos(){
        cmbFormaFuste.setEnabled(true);
        cmbFormaFuste.setSelectedItem(null);
        cmbTipoTocon.setEnabled(false);
        cmbTipoTocon.setSelectedItem(null);
        cmbProporcionCopaViva.setEnabled(false);
        cmbProporcionCopaViva.setSelectedItem(null);
        cmbExposicionLuzCopa.setEnabled(false);
        cmbExposicionLuzCopa.setSelectedItem(null);
        cmbPosicionCopa.setEnabled(false);
        cmbPosicionCopa.setSelectedItem(null);
        cmbDencidadCopa.setEnabled(false);
        cmbDencidadCopa.setSelectedItem(null);
        cmbMuerteRegresiva.setEnabled(false);
        cmbMuerteRegresiva.setSelectedItem(null);
        cmbTransparenciaFollaje.setEnabled(false);
        cmbTransparenciaFollaje.setSelectedItem(null);
        //txtAlturaFusteLimpio.setEnabled(false);
        txtAlturaFusteLimpio.setText("");
        txtAlturaFusteLimpio.setValue(null);
        txtAlturaComercial.setEnabled(false);
        txtAlturaComercial.setText("");
        txtAlturaComercial.setValue(null);
        cmbCondicionMuertoPie.setEnabled(false);
        cmbCondicionMuertoPie.setSelectedItem(null);
        cmbGradoPutrefaccion.setEnabled(false);
        cmbGradoPutrefaccion.setSelectedItem(null);
    }
    
    private void estadoArborescenteVivo(boolean esArborecente) {
        if (esArborecente) {
            cmbProporcionCopaViva.setSelectedItem(null);
            cmbProporcionCopaViva.setEnabled(false);
            cmbExposicionLuzCopa.setSelectedItem(null);
            cmbExposicionLuzCopa.setEnabled(false);
            cmbPosicionCopa.setSelectedItem(null);
            cmbPosicionCopa.setEnabled(false);
            cmbDencidadCopa.setSelectedItem(null);
            cmbDencidadCopa.setEnabled(false);
            cmbMuerteRegresiva.setSelectedItem(null);
            cmbMuerteRegresiva.setEnabled(false);
            cmbTransparenciaFollaje.setSelectedItem(null);
            cmbTransparenciaFollaje.setEnabled(false);
        } else {
            cmbProporcionCopaViva.setEnabled(true);
            cmbExposicionLuzCopa.setEnabled(true);
            cmbPosicionCopa.setEnabled(true);
            cmbDencidadCopa.setEnabled(true);
            cmbMuerteRegresiva.setEnabled(true);
            cmbTransparenciaFollaje.setEnabled(true);
        }
    }
    
    private void estadoDanio1() {
        CatEAgenteDanio danio1 = (CatEAgenteDanio) cmbAgenteDanio1.getSelectedItem();
    }
    
    private void estadoDanio2() {
        CatEAgenteDanio danio2 = (CatEAgenteDanio) cmbAgenteDanio2.getSelectedItem();
    }
    
    private void fijarValoresPorCampo(int arbolID) {
        CEArbolado arbol;
        arbol = cdArbolado.getRegistroArboladoD(arbolID);
        
        txtNumeroIndividuo.setText(String.valueOf(arbol.getNumeroIndividuo()));
        txtNumeroRamaTallo.setText(String.valueOf(arbol.getNumeroRama()));
        txtAzimut.setText(String.valueOf(arbol.getAzimut()));
        txtDistancia.setText(String.valueOf(arbol.getDistancia()));
        
        CatEFamiliaEspecie fam = new CatEFamiliaEspecie();
        fam.setFamiliaID(arbol.getFamiliaID());
        cmbFamilia.setSelectedItem(fam);
        
        CatEGenero gen = new CatEGenero();
        gen.setGeneroID(arbol.getGeneroID());
        cmbGenero.setSelectedItem(gen);
        
       /* CatEGenero gen = new CatEGenero();
        gen.setGeneroID(arbol.getGeneroID());
        cmbGenero.removeAllItems();
        fillCmbGenero(arbol.getFamiliaID());
        cmbGenero.setSelectedItem(gen);*/
        
        CatEEspecie esp = new CatEEspecie();
        esp.setEspecieID(arbol.getEspecieID());
        cmbEspecie.removeAllItems();
        fillCmbEspecie(arbol.getGeneroID());
        cmbEspecie.setSelectedItem(esp);
        
        CatEInfraespecie inf = new CatEInfraespecie();
        inf.setInfraespecieID(arbol.getInfraespecieID());
        cmbInfraespecie.removeAllItems();
        fillCmbInfraespecie(arbol.getEspecieID());
        cmbInfraespecie.setSelectedItem(inf);
        
        CatEFormaVida fv = new CatEFormaVida();
        fv.setFormaVidaID(arbol.getFormaVidaID());
        cmbFormaVida.setSelectedItem(fv);
        
        CatEPorcentajeArbolado proporcionCopa = new CatEPorcentajeArbolado();
        proporcionCopa.setPorcentajeArboladoID(arbol.getProporcionCopaVivaID());
        cmbProporcionCopaViva.setSelectedItem(proporcionCopa);
        
        CatEExposicionLuzCopa exposicionLuz = new CatEExposicionLuzCopa();
        exposicionLuz.setExposicionLuzID(arbol.getExposisicionCopaID());
        cmbExposicionLuzCopa.setSelectedItem(exposicionLuz);
        
        CatEPosicionCopa posicionCopa = new CatEPosicionCopa();
        posicionCopa.setPosicionCopaID(arbol.getPosicionCopaID());
        cmbPosicionCopa.setSelectedItem(posicionCopa);
        
        CatEPorcentajeArbolado densidadCopa = new CatEPorcentajeArbolado();
        densidadCopa.setPorcentajeArboladoID(arbol.getDensidadCopaID());
        cmbDencidadCopa.setSelectedItem(densidadCopa);
        
        CatEPorcentajeArbolado muerteRegresiva = new CatEPorcentajeArbolado();
        muerteRegresiva.setPorcentajeArboladoID(arbol.getMuerteRegresivaID());
        cmbMuerteRegresiva.setSelectedItem(muerteRegresiva);
        
        CatEPorcentajeArbolado transparenciaFollaje = new CatEPorcentajeArbolado();
        transparenciaFollaje.setPorcentajeArboladoID(arbol.getTransparenciaFollajeID());
        cmbTransparenciaFollaje.setSelectedItem(transparenciaFollaje);
        
        txtNombreComun.setText(arbol.getNombreComun());
        
        CatECondicionArbolado ca = new CatECondicionArbolado();
        int condicionArbolado = arbol.getCondicionID();
        ca.setCondicionID(condicionArbolado);
        CatEGradoPutrefaccionArbolado gp = new CatEGradoPutrefaccionArbolado();
        gp.setGradoPutrefaccionID(arbol.getGradoPutrefaccionID());
        
        if (arbol.getFormaVidaID() < 4 || arbol.getFormaVidaID() == 7) {   //Ubicar si se habilitara la forma de fuste con base  en lo seleccionado en formas de vida arborecentes
            cmbCondicion.removeAllItems();
            fillCmbCondicionArbolado();
            cmbCondicion.setSelectedItem(ca);
            if (condicionArbolado == 1) { // VIVO
                CatEFormaFuste ff = new CatEFormaFuste();
                ff.setFormaFusteID(arbol.getFormaFusteID());
                estadoArbolVivo();
                cmbFormaFuste.setSelectedItem(ff);
            } else if (condicionArbolado == 2) {//muerto en pie
                CatECondicionMuertoPie mp = new CatECondicionMuertoPie();
                int muertoID = arbol.getMuertoPieID();
                int gradoP = arbol.getGradoPutrefaccionID();
                gp.setGradoPutrefaccionID(gradoP);
                mp.setMuertoPieID(muertoID);
                estadoMuertoPieCD(muertoID);
                cmbCondicionMuertoPie.setSelectedItem(mp);
                cmbCondicionMuertoPie.setEnabled(true);
                cmbGradoPutrefaccion.setSelectedItem(null);
                cmbGradoPutrefaccion.setEnabled(false);
                if (arbol.getMuertoPieID() == 4) {
                    cmbGradoPutrefaccion.setEnabled(true);
                    cmbGradoPutrefaccion.setSelectedItem(gp);
                    estadoMuertoPieCD(muertoID);
                }
            } else if (condicionArbolado > 2) {//Tocon
                estadoArbolTocon();
                CatETipoTocon tt = new CatETipoTocon();
                tt.setTipoToconID(arbol.getTipoToconID());
                cmbTipoTocon.setSelectedItem(tt);
            }
        } else {
            cmbCondicion.removeAllItems();
            fillCmbCondicionLianas();
            if (condicionArbolado == 1) {//vivo
                estadoLianaCactaceasCaniasVivos();
            } else {//muerto
                CatECondicionMuertoPie mp = new CatECondicionMuertoPie();
                int muertoID = arbol.getMuertoPieID();
                int gradoP = arbol.getGradoPutrefaccionID();
                mp.setMuertoPieID(muertoID);
                estadoMuertoPieCD(muertoID);
                cmbCondicionMuertoPie.setSelectedItem(mp);
                gp.setGradoPutrefaccionID(gradoP);
                cmbGradoPutrefaccion.setSelectedItem(gp);
                if (muertoID == 4) {
                    cmbGradoPutrefaccion.setEnabled(true);
                }
            }
        }
        
        txtDiametroNormal.setText(String.valueOf(arbol.getDiametroNormal()));
        txtAlturaTotal.setText(String.valueOf(arbol.getAlturaTotal()));
        if (arbol.getAnguloInclinacion() == null) {
            txtAnguloInclinacion.setText("");
        } else {
            txtAnguloInclinacion.setText(String.valueOf(arbol.getAnguloInclinacion()));
        }
        if (arbol.getAlturaFusteLimpio() == null) {
            txtAlturaFusteLimpio.setText("");
        } else {
            txtAlturaFusteLimpio.setText(String.valueOf(arbol.getAlturaFusteLimpio()));
        }
        if(arbol.getAlturaComercial() == null){
            txtAlturaComercial.setText("");
            txtAlturaComercial.setEnabled(false);
        } else {
            txtAlturaComercial.setText(String.valueOf(arbol.getAlturaComercial()));
        }
        txtDiametroCopaNS.setText(String.valueOf(arbol.getDiametroCopaNS()));
        txtDiametroCopaEO.setText(String.valueOf(arbol.getDiametroCopaEO()));
        
        if (arbol.getEsSubmuestra() == true) {
            chkEsSubmuestra.setSelected(true);
        } else {
            chkEsSubmuestra.setSelected(false);
        }

        //Fijando datos en campos de danios
        CEDanioSeveridad severidad1 = new CEDanioSeveridad();
        CEDanioSeveridad severidad2 = new CEDanioSeveridad();
        CatEAgenteDanio agente1 = new CatEAgenteDanio();
        CatEAgenteDanio agente2 = new CatEAgenteDanio();
        CDDanioArbolado cdDanio = new CDDanioArbolado();
        CatEPorcentajeArbolado porcentaje1 = new CatEPorcentajeArbolado();
        CatEPorcentajeArbolado porcentaje2 = new CatEPorcentajeArbolado();
        
        List<CEDanioSeveridad> listDanio = new ArrayList<>();
        listDanio = cdDanio.getDanio(arbolID);

        int size = listDanio.size();

        for (int i = 0; i < size; i++) {
            if (i == 0) {
                severidad1.setAgenteDanioID(listDanio.get(i).getAgenteDanioID());
                severidad1.setSeveridadID(listDanio.get(i).getSeveridadID());
                agente1.setAgenteDanioID(severidad1.getAgenteDanioID());
                porcentaje1.setPorcentajeArboladoID(severidad1.getSeveridadID());
            } else {
                severidad2.setAgenteDanioID(listDanio.get(i).getAgenteDanioID());
                severidad2.setSeveridadID(listDanio.get(i).getSeveridadID());
                agente2.setAgenteDanioID(severidad2.getAgenteDanioID());
                porcentaje2.setPorcentajeArboladoID(severidad2.getSeveridadID());
            }
        }

        cmbAgenteDanio1.setSelectedItem(agente1);
        combo.reiniciarComboModel(cmbSeveridad1);
        fillCmbSeveridad1(agente1.getAgenteDanioID());
        cmbSeveridad1.setSelectedItem(porcentaje1);
        
        
        cmbAgenteDanio2.setSelectedItem(agente2);
        combo.reiniciarComboModel(cmbSeveridad2);
        fillCmbSeveridad2(agente2.getAgenteDanioID());
        cmbSeveridad2.setSelectedItem(porcentaje2);
        
        if(!txtAnguloInclinacion.isEnabled()){
                txtAnguloInclinacion.setText("");
                txtAnguloInclinacion.setValue(null);
            }
            if(!txtAlturaFusteLimpio.isEnabled()){
                txtAlturaFusteLimpio.setText("");
                txtAlturaFusteLimpio.setValue(null);
            }
            if(!txtDiametroCopaNS.isEnabled()){
                txtDiametroCopaNS.setText("");
                txtDiametroCopaNS.setValue(null);
            }
            if(!txtDiametroCopaEO.isEnabled()){
                txtDiametroCopaEO.setText("");
                txtDiametroCopaEO.setValue(null);
            }
            if(!txtAlturaComercial.isEnabled()){
                txtAlturaComercial.setText("");
                txtAlturaComercial.setValue(null);
            }
        
        // txtNumeroIndividuo.requestFocus();
        cmbConsecutivo.requestFocus();
        txtClaveColecta.setText(arbol.getClaveColecta());
        
        if(arbol.getFormaVidaID()==3){//Arborecentes vivo
            estadoArborescenteVivo(true);
        }
            
        
    }
    
    private void seleccionarSiguienteFormulario(CESitio ceSitio) {
        Integer secuenciaID = ceSitio.getSecuencia();
        if (secuenciaID != null) {
            switch (secuenciaID) {
                case 1: //Módulo A
                   /* UPMForms.carbono.setDatosIniciales(ceSitio);
                    UPMForms.carbono.setVisible(true);*/
                    this.hide();
                    break;
                case 2: //Módulos A y C
                  /*  UPMForms.carbono.setDatosIniciales(ceSitio);
                    UPMForms.carbono.setVisible(true);*/
                    break;
                case 3: //Modulos A, C, E y G
                   /* UPMForms.carbono.setDatosIniciales(ceSitio);
                    UPMForms.carbono.setVisible(true);*/
                    break;
                case 4: //Modulos A y E
                  /*  UPMForms.hojarascaProfundidad.setDatosiniciales(ceSitio);
                    UPMForms.hojarascaProfundidad.setVisible(true);*/
                    break;
                case 5: //Modulos A, C, D y F
                    UPMForms.fotoHemisferica.setDatosiniciales(ceSitio);
                    UPMForms.fotoHemisferica.setVisible(true);
                    break;
                case 6://Modulos A, C y D
                    /*UPMForms.suelo.setDatosiniciales(ceSitio);
                    UPMForms.suelo.setVisible(true);*/
                    this.hide();
                    break;
                case 7://Modulos A, C, D y E
                    UPMForms.hojarascaProfundidad.setDatosiniciales(ceSitio);
                    UPMForms.hojarascaProfundidad.setVisible(true);
                    break;
                case 8://Modulos A, C, D, E y F
                    UPMForms.hojarascaProfundidad.setDatosiniciales(ceSitio);
                    UPMForms.hojarascaProfundidad.setVisible(true);
                    break;
                case 9://Modulos A, C y E
                   /* UPMForms.carbono.setDatosIniciales(ceSitio);
                    UPMForms.carbono.setVisible(true);*/
                    break;
                case 10://Modulos A, C, H
                   /* UPMForms.suelo.setDatosiniciales(ceSitio);
                    UPMForms.suelo.setVisible(true);*/
                    break;
                case 11://Modulos A y H
                    /*UPMForms.carbono.setDatosIniciales(ceSitio);
                    UPMForms.carbono.setVisible(true);*/
                    break;
                case 12://Modulos A, E y H
                   /* UPMForms.carbono.setDatosIniciales(ceSitio);
                    UPMForms.carbono.setVisible(true);*/
                    break;
                case 13://Modulos A, C, E y H
                   /* UPMForms.suelo.setDatosiniciales(ceSitio);
                    UPMForms.suelo.setVisible(true);*/
                    break;
                case 14://Modulos A, E y G
                    /*UPMForms.carbono.setDatosIniciales(ceSitio);
                    UPMForms.carbono.setVisible(true);*/
                    break;
                case 15://A y G
                    /* UPMForms.repobladoVM.setDatosIniciales(ceSitio);
                    UPMForms.repobladoVM.setVisible(true);*/
                    break;
            }
        }
    }
    
    private boolean validarCreacionSubmuestra() {
        boolean continuar = true;
        if (cdArbolado.validarCrearSubmuestra(this.sitioID)) {
            Object[] opciones = {"Si", "No"};
            int respuesta = JOptionPane.showOptionDialog(null, "No se han marcado árboles submuestra, ¿Desea continuar?",
                    "Arbolado", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[1]);
            if (respuesta == JOptionPane.YES_NO_OPTION) {
                continuar = true;
            } else {
                continuar = false;
            }
        }
        return continuar;
    }
    
    private void txtNombreComunFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombreComunFocusGained
        txtNombreComun.selectAll();
    }//GEN-LAST:event_txtNombreComunFocusGained

    private void txtDiametroNormalFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDiametroNormalFocusGained
         SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                txtDiametroNormal.selectAll();
            }
        });
    }//GEN-LAST:event_txtDiametroNormalFocusGained

    private void txtAlturaTotalFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAlturaTotalFocusGained
       SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                txtAlturaTotal.selectAll();
            }
        });
    }//GEN-LAST:event_txtAlturaTotalFocusGained

    private void txtAnguloInclinacionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAnguloInclinacionFocusGained
       SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                txtAnguloInclinacion.selectAll();
            }
        });
    }//GEN-LAST:event_txtAnguloInclinacionFocusGained

    private void txtAlturaFusteLimpioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAlturaFusteLimpioFocusGained
       SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                txtAlturaFusteLimpio.selectAll();
            }
        });
    }//GEN-LAST:event_txtAlturaFusteLimpioFocusGained

    private void txtAlturaComercialFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAlturaComercialFocusGained
       SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                txtAlturaComercial.selectAll();
            }
        });
    }//GEN-LAST:event_txtAlturaComercialFocusGained

    private void txtDiametroCopaNSFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDiametroCopaNSFocusGained
       SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                txtDiametroCopaNS.selectAll();
            }
        });
    }//GEN-LAST:event_txtDiametroCopaNSFocusGained

    private void txtDiametroCopaEOFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDiametroCopaEOFocusGained
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                txtDiametroCopaEO.selectAll();
            }
        });
    }//GEN-LAST:event_txtDiametroCopaEOFocusGained

    private void txtDiametroNormalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDiametroNormalFocusLost
      try {
          if(txtDiametroNormal.getText().isEmpty()){
          txtDiametroNormal.setValue(null);
          txtAlturaComercial.setEnabled(false);
      }
      if(Integer.parseInt(txtDiametroNormal.getText())>=10&&cmbFormaVida.getSelectedIndex()<3){//si el diametro normal es mayor o igual a 10 y no es arborecente
         
          if(cmbCondicion.getSelectedIndex()==2){//si la condicion muerto, no hay altura comercial
             txtAlturaComercial.setEnabled(false); 
          }else{
          txtAlturaComercial.setEnabled(true);
          }
      }
      if(Integer.parseInt(txtDiametroNormal.getText())<10){
          txtAlturaComercial.setEnabled(false);
      }
      if(cmbCondicion.getSelectedIndex()>2||cmbFormaVida.getSelectedIndex()>6)//es tocon o forma de vida no determinada
      {
          txtAlturaComercial.setEnabled(false);
      }
      //System.out.println(txtAlturaComercial.isEnabled());
        } catch (Exception e) {
        }
      
    }//GEN-LAST:event_txtDiametroNormalFocusLost

    private void txtAlturaTotalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAlturaTotalFocusLost
       if(txtAlturaTotal.getText().isEmpty()){
           txtAlturaTotal.setValue(null);
       }
    }//GEN-LAST:event_txtAlturaTotalFocusLost

    private void txtAnguloInclinacionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAnguloInclinacionFocusLost
       if(txtAnguloInclinacion.getText().isEmpty()){
           txtAnguloInclinacion.setValue(null);
       }
    }//GEN-LAST:event_txtAnguloInclinacionFocusLost

    private void txtAlturaFusteLimpioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAlturaFusteLimpioFocusLost
        if(txtAlturaFusteLimpio.getText().isEmpty()){
            txtAlturaFusteLimpio.setValue(null);
        }
    }//GEN-LAST:event_txtAlturaFusteLimpioFocusLost

    private void txtAlturaComercialFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAlturaComercialFocusLost
        if(txtAlturaComercial.getText().isEmpty()){
            txtAlturaComercial.setValue(null);
        }
    }//GEN-LAST:event_txtAlturaComercialFocusLost

    private void txtDiametroCopaNSFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDiametroCopaNSFocusLost
        if(txtDiametroCopaNS.getText().isEmpty()){
            txtDiametroCopaNS.setValue(null);
        }
    }//GEN-LAST:event_txtDiametroCopaNSFocusLost

    private void txtDiametroCopaEOFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDiametroCopaEOFocusLost
       if(txtDiametroCopaEO.getText().isEmpty()){
           txtDiametroCopaEO.setValue(null);
       }
    }//GEN-LAST:event_txtDiametroCopaEOFocusLost

    private void txtNumeroIndividuoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNumeroIndividuoFocusGained
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                txtNumeroIndividuo.selectAll();
            }
        });
    }//GEN-LAST:event_txtNumeroIndividuoFocusGained

    private void txtNumeroRamaTalloFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNumeroRamaTalloFocusGained
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                txtNumeroRamaTallo.selectAll();
            }
        });
    }//GEN-LAST:event_txtNumeroRamaTalloFocusGained

    private void txtAzimutFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAzimutFocusGained
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                txtAzimut.selectAll();
            }
        });
    }//GEN-LAST:event_txtAzimutFocusGained

    private void txtDistanciaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDistanciaFocusGained
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                txtDistancia.selectAll();
            }
        });
    }//GEN-LAST:event_txtDistanciaFocusGained

    private void txtNumeroIndividuoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNumeroIndividuoFocusLost
        if(txtNumeroIndividuo.getText().isEmpty()){
            txtNumeroIndividuo.setValue(null);
        }
    }//GEN-LAST:event_txtNumeroIndividuoFocusLost

    private void txtNumeroRamaTalloFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNumeroRamaTalloFocusLost
        if(txtNumeroRamaTallo.getText().isEmpty()){
            txtNumeroRamaTallo.setValue(null);
        }
    }//GEN-LAST:event_txtNumeroRamaTalloFocusLost

    private void txtAzimutFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAzimutFocusLost
       if(txtAzimut.getText().isEmpty()){
           txtAzimut.setValue(null);
       }
    }//GEN-LAST:event_txtAzimutFocusLost

    private void txtDistanciaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDistanciaFocusLost
        if(txtDistancia.getText().isEmpty()){
            txtDistancia.setValue(null);
        }
    }//GEN-LAST:event_txtDistanciaFocusLost

    private void grdArboladoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grdArboladoMouseClicked
       indexs =grdArbolado.getSelectedRows();
       //System.out.println("indices ");
       for(int i=0;i<=indexs.length;i++){
           try {
               //System.out.println( grdArbolado.getValueAt(indexs[i], 0).toString());
           } catch (Exception e) {
           }
       }
        
        if (evt.getButton() == 1) {
            int fila = grdArbolado.getSelectedRow();
            String strArbID = grdArbolado.getValueAt(fila, 0).toString();
            //System.out.println(strArbID);
            this.arboladoID = Integer.parseInt(strArbID);
            fijarValoresPorCampo(this.arboladoID);
            
            chkEsSubmuestra.setEnabled(true);
            txtNumeroRamaTallo.setEnabled(false);
        }
    }//GEN-LAST:event_grdArboladoMouseClicked

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

    private void btnTrazoSitioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrazoSitioActionPerformed
        FrmTrazoSitio trazo = new FrmTrazoSitio(Main.main, true);
        trazo.setLocationRelativeTo(Main.main);
        CESitio sitio = new CESitio();
        sitio.setUpmID(this.upmID);
        sitio.setSitioID(this.sitioID);
        sitio.setSitio(this.sitio);
        trazo.setDatosIniciales(sitio);
        trazo.setVisible(true);     
    }//GEN-LAST:event_btnTrazoSitioActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        asignarDatosArbolado();
        CatECondicionArbolado condicion = (CatECondicionArbolado) cmbCondicion.getSelectedItem();
        if (validarCamposObligatorio() && validarCamposOpcionales() && validarAlturaComercial() && validarMedicionesObligatorias() && validarMedicionesOpcionales()
                && validarCampoDanio()) {
            if (condicion.getCondicionID() > 1 && condicion.getCondicionID() <= 4) {
                if (validarDanioObligatorio()) {
                    crearArbolado();
                    //this.cdArbolado.enumerarConsecutivo(this.sitioID);
                    //this.cdArbolado.enumerarRama(this.sitioID);
                    llenarTabla();
                    limpiarControles();
                    combo.reiniciarComboModel(cmbConsecutivo);
                    fillCmbConsecutivo();
                }
            } else {
                crearArbolado();
                //this.cdArbolado.enumerarConsecutivo(this.sitioID);
                //this.cdArbolado.enumerarRama(this.sitioID);
                llenarTabla();
                limpiarControles();
                //combo.reiniciarComboModel(cmbConsecutivo);
                //fillCmbConsecutivo();
            }
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnElimnarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnElimnarActionPerformed
        eliminarArbolado();
        //this.cdArbolado.enumerarConsecutivo(this.sitioID);
        //this.cdArbolado.enumerarRama(this.sitioID);
        estadoArbolVivo();
        llenarTabla();
        combo.reiniciarComboModel(cmbConsecutivo);
        fillCmbConsecutivo();
    }//GEN-LAST:event_btnElimnarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        asignarDatosArbolado();
        CatECondicionArbolado condicion = (CatECondicionArbolado) cmbCondicion.getSelectedItem();
        if (validarCamposObligatorioModificar()&& validarCamposOpcionales() && validarAlturaComercial() && validarMedicionesObligatorias() && validarMedicionesOpcionales()
                && validarCampoDanio()) {
            if (condicion.getCondicionID() > 1 && condicion.getCondicionID() <= 4) {
                if (validarDanioObligatorio()) {
                    actualizarArbolado();
                    llenarTabla();
                    //limpiarControles();
                }
            } else {
                actualizarArbolado();
                llenarTabla();
                //limpiarControles();
            }
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void cmbConsecutivoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cmbConsecutivoPropertyChange
        Integer consecutivo;
        Integer arbolID;
        try {
            consecutivo = (Integer) cmbConsecutivo.getSelectedItem();
            arbolID = cdArbolado.getConsecutivoArbolado(consecutivo);
            fijarValoresPorCampo(arbolID);
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

    private void cmbFormaVidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFormaVidaActionPerformed
 CatEFormaVida formaVida = (CatEFormaVida) cmbFormaVida.getSelectedItem();
        DefaultComboBoxModel dcmCN = (DefaultComboBoxModel) cmbCondicion.getModel();
        dcmCN.removeAllElements();
        if (formaVida != null) {
            if(cmbFormaVida.getSelectedIndex()>2){
                //txtAlturaFusteLimpio.setEnabled(false);
                txtAlturaComercial.setEnabled(false);
                if(cmbFormaVida.getSelectedIndex()==6){//cactaceas arborecentes
                    txtAlturaFusteLimpio.setEnabled(true);
                }
            }else{
                txtAlturaFusteLimpio.setEnabled(!false);
                txtAlturaComercial.setEnabled(!false);
            }
            if (formaVida.getFormaVidaID() == 4 || formaVida.getFormaVidaID() == 5 || formaVida.getFormaVidaID() == 6) {
                //fillCmbCondicionLianas();
                 fillCmbCondicionArbolado();
                estadoLianaCactaceasCaniasVivos();
            } else if (formaVida.getFormaVidaID() == 1 || formaVida.getFormaVidaID() == 2 || formaVida.getFormaVidaID() == 3) {
                fillCmbCondicionArbolado();
            } if(formaVida.getFormaVidaID() == 7){
                fillCmbCondicionArbolado();
            }
            
            if(cmbFormaVida.getSelectedIndex()>2)//son arborecentes, bejucos, lianas, cañas y no especificados
            {
                quitarVariablesArboladoD();
            }
            
        }
    }//GEN-LAST:event_cmbFormaVidaActionPerformed

    private void cmbCondicionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCondicionActionPerformed
       CatECondicionArbolado condicion = (CatECondicionArbolado) cmbCondicion.getSelectedItem();
        CatEFormaVida formaVida = (CatEFormaVida) cmbFormaVida.getSelectedItem();
        DefaultComboBoxModel dcmMP = (DefaultComboBoxModel) cmbCondicionMuertoPie.getModel();
        DefaultComboBoxModel dcmTC = (DefaultComboBoxModel) cmbTipoTocon.getModel();
        // dcmMP.removeAllElements();
        //dcmTC.removeAllElements();
        if (condicion != null) {
            if (condicion.getCondicionID() == 4) {//si esta muerto 
                estadoMuertoPie();
                txtAnguloInclinacion.setEnabled(false);
                txtAlturaComercial.setEnabled(false);
            } else if (condicion.getCondicionID() == 3 || condicion.getCondicionID() == 4) {//
                //cmbTipoTocon.setEnabled(true);
                //cmbCondicion.setEnabled(false);
                //txtAnguloInclinacion.setEnabled(true);
                estadoArbolTocon();
            }
            if ((formaVida.getFormaVidaID() >= 1 && formaVida.getFormaVidaID() <= 3) && condicion.getCondicionID() == 1) {
                estadoArbolVivo();
            } else if ((formaVida.getFormaVidaID() >= 1 && formaVida.getFormaVidaID() <= 3) && (condicion.getCondicionID() >= 3 && condicion.getCondicionID() <= 4)) {
                estadoArbolTocon();
            }
            
            if(cmbCondicion.getSelectedIndex()==2){//es arbol muerto en pie
                cmbCondicionMuertoPie.setEnabled(true);
                cmbGradoPutrefaccion.setEnabled(false);
                cmbTipoTocon.setEnabled(false);
                txtAlturaFusteLimpio.setEnabled(false);
                txtAlturaComercial.setEnabled(false);
                txtDiametroCopaNS.setEnabled(false);
                txtDiametroCopaEO.setEnabled(false);
                quitarVariablesArboladoD();
                
            }
             if(cmbCondicion.getSelectedIndex()==1&&cmbFormaVida.getSelectedIndex()>2){//es arborecente vivo
                txtAlturaFusteLimpio.setEnabled(true);
                txtAlturaComercial.setEnabled(false);
                txtDiametroCopaNS.setEnabled(true);
                txtDiametroCopaEO.setEnabled(true);
               
            }
             if(cmbCondicion.getSelectedIndex()==1&&cmbFormaVida.getSelectedIndex()==3){//es arborecente 
                //txtAlturaFusteLimpio.setEnabled(false);
                txtAlturaComercial.setEnabled(false);
                txtDiametroCopaNS.setEnabled(true);
                txtDiametroCopaEO.setEnabled(true);
            }
              if(cmbCondicion.getSelectedIndex()>=1&&cmbFormaVida.getSelectedIndex()==7){//es tocon no determinado
                estadoArbolTocon();
            }
             if(cmbCondicion.getSelectedIndex()>=3){//es tocon
                estadoArbolTocon();
            }
             if(cmbFormaVida.getSelectedIndex()>2&&cmbCondicion.getSelectedIndex()==1){//si son arborecentes, lianas, bejucos, cañas, cactaceas vivos, no hay variables arbolado D
                 quitarVariablesArboladoD();
             }
        }
    }//GEN-LAST:event_cmbCondicionActionPerformed

    private void cmbCondicionMuertoPieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCondicionMuertoPieActionPerformed
        CatECondicionMuertoPie condicionMuerto = (CatECondicionMuertoPie) cmbCondicionMuertoPie.getSelectedItem();
         //System.out.println("ACTION PERFORMED"+cmbCondicionMuertoPie.getSelectedIndex());
            txtAnguloInclinacion.setEnabled(true);
            if(cmbCondicionMuertoPie.getSelectedIndex()==4)//si la condicion es D
            {
               
                txtAnguloInclinacion.setEnabled(false);
            }else{
                txtAnguloInclinacion.setEnabled(true);
            }
        
        if (condicionMuerto != null) {
            if (condicionMuerto.getMuertoPieID() == 4) {
                estadoMuertoPieCD(condicionMuerto.getMuertoPieID());
                if (condicionMuerto.getMuertoPieID() == 4) {
                    cmbGradoPutrefaccion.setEnabled(true);
                    cmbGradoPutrefaccion.setSelectedItem(null);
                }
            }
            if (condicionMuerto.getMuertoPieID()<4) {//si es CMP=D
                cmbGradoPutrefaccion.setEnabled(false);
                //cmbFormaFuste.setSelectedItem(null);
                cmbFormaFuste.setEnabled(true);
            } else {
                cmbFormaFuste.setSelectedItem(null);
                cmbFormaFuste.setEnabled(false);
                cmbGradoPutrefaccion.setEnabled(true);
            }
        }
    }//GEN-LAST:event_cmbCondicionMuertoPieActionPerformed

    private void cmbAgenteDanio1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAgenteDanio1ActionPerformed
       CatEAgenteDanio agenteDanio = (CatEAgenteDanio) cmbAgenteDanio1.getSelectedItem();
        if (agenteDanio != null) {
            if (agenteDanio.getAgenteDanioID() == 21 || agenteDanio.getAgenteDanioID() == 22 || agenteDanio.getAgenteDanioID() == 23 || agenteDanio.getAgenteDanioID() == 24 || agenteDanio.getAgenteDanioID() == 25 || agenteDanio.getAgenteDanioID() == 33 || agenteDanio.getAgenteDanioID() == 34) {
                cmbSeveridad1.setEnabled(true);
                fillCmbSeveridad1(agenteDanio.getAgenteDanioID());
            } else {
                combo.reiniciarComboModel(cmbSeveridad1);
                cmbSeveridad1.setSelectedItem(null);
                cmbSeveridad1.setEnabled(false);
            }
        }
    }//GEN-LAST:event_cmbAgenteDanio1ActionPerformed

    private void cmbAgenteDanio2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAgenteDanio2ActionPerformed
        CatEAgenteDanio agenteDanio = (CatEAgenteDanio) cmbAgenteDanio2.getSelectedItem();
        if (agenteDanio != null) {
            if (agenteDanio.getAgenteDanioID() == 21 || agenteDanio.getAgenteDanioID() == 22 || agenteDanio.getAgenteDanioID() == 23 || agenteDanio.getAgenteDanioID() == 24 || agenteDanio.getAgenteDanioID() == 25 || agenteDanio.getAgenteDanioID() == 33 || agenteDanio.getAgenteDanioID() == 34) {
                cmbSeveridad2.setEnabled(true);
                fillCmbSeveridad2(agenteDanio.getAgenteDanioID());
            } else {
                combo.reiniciarComboModel(cmbSeveridad2);
                cmbSeveridad2.setSelectedItem(null);
                cmbSeveridad2.setEnabled(false);
            }
        }
    }//GEN-LAST:event_cmbAgenteDanio2ActionPerformed

    private void chkEsSubmuestraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkEsSubmuestraActionPerformed
        if (chkEsSubmuestra.isSelected()) {
            int respuesta = JOptionPane.showConfirmDialog(null, "Va ha crear una submuestra, "
                    + " ¿Desea continuar?", "Arbolado", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (respuesta == JOptionPane.YES_OPTION) {
                this.cdSubmuestra.insertSubmuestra(this.sitioID, this.arboladoID);
                chkEsSubmuestra.setSelected(false);
                chkEsSubmuestra.setEnabled(false);
                grdArbolado.clearSelection();
                limpiarControles();
                txtNumeroIndividuo.requestFocus();
                this.cdArbolado.updateArboladoSubmuestra(true, this.arboladoID);
                combo.reiniciarTabla(this.grdArbolado);
                llenarTabla();
            } else {
                chkEsSubmuestra.setSelected(false);
                chkEsSubmuestra.setEnabled(false);
                grdArbolado.clearSelection();
                limpiarControles();
                txtNumeroIndividuo.requestFocus();
                this.cdArbolado.updateArboladoSubmuestra(false, this.arboladoID);
                combo.reiniciarTabla(this.grdArbolado);
                llenarTabla();
            }
        } else {
            int respuesta = JOptionPane.showConfirmDialog(null, "Va ha eliminar una submuestra "
                    + " ¿Desea continuar?", "Arbolado", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (respuesta == JOptionPane.YES_OPTION) {
                this.cdSubmuestra.deleteRegistroSubmuestra(this.arboladoID);
                chkEsSubmuestra.setSelected(false);
                chkEsSubmuestra.setEnabled(false);
                grdArbolado.clearSelection();
                limpiarControles();
                grdArbolado.clearSelection();
                txtNumeroIndividuo.requestFocus();
                this.cdArbolado.updateArboladoSubmuestra(false, this.arboladoID);
                combo.reiniciarTabla(this.grdArbolado);
                llenarTabla();
            } else {
                chkEsSubmuestra.setSelected(false);
                chkEsSubmuestra.setEnabled(false);
                grdArbolado.clearSelection();
                limpiarControles();
                txtNumeroIndividuo.requestFocus();
                this.cdArbolado.updateArboladoSubmuestra(true, this.arboladoID);
                combo.reiniciarTabla(this.grdArbolado);
                llenarTabla();
            }
        }
    }//GEN-LAST:event_chkEsSubmuestraActionPerformed

    private void btnContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuarActionPerformed
        if (funciones.validarSeccionCapturada("TAXONOMIA_Arbolado", this.ceSitio) && chkArbolado.isSelected()) {
            JOptionPane.showMessageDialog(null, "Si selecciona Arbolado, se debe capturar", "Arbolado D", JOptionPane.INFORMATION_MESSAGE);
            chkArbolado.requestFocus();
        } else if (funciones.validarSeccionCapturada("TAXONOMIA_Arbolado", this.ceSitio) == false && chkArbolado.isSelected()) {
            if (validarColectasObligatorias() && validarCreacionSubmuestra()) {
                this.hide();
                if (this.modificar == 0) {
                    UPMForms.submuestra.setDatosIniciales(this.ceSitio);
                    UPMForms.submuestra.setVisible(true);
                    this.cdSecuencia.updateSecuencia(this.ceSitio, FORMATO_ID, 1);
                } else {
                    UPMForms.submuestra.revisarSubmuestra(this.ceSitio);
                    UPMForms.submuestra.setVisible(true);
                    this.cdSecuencia.updateSecuencia(this.ceSitio, FORMATO_ID, 1);
                }
                //funciones.manipularBotonesMenuPrincipal(false);
                
            }
        } else if (funciones.validarSeccionCapturada("TAXONOMIA_Arbolado", this.ceSitio) == true && !chkArbolado.isSelected()) {
            this.hide();
            if (this.modificar == 0) {
                UPMForms.submuestra.setDatosIniciales(this.ceSitio);
                UPMForms.submuestra.setVisible(true);
                this.cdSecuencia.updateSecuencia(this.ceSitio, FORMATO_ID, -1);
            } else {
                UPMForms.submuestra.revisarSubmuestra(this.ceSitio);
                UPMForms.submuestra.setVisible(true);
                this.cdSecuencia.updateSecuencia(this.ceSitio, FORMATO_ID, -1);
            }
            // funciones.manipularBotonesMenuPrincipal(false);
            
        }
    }//GEN-LAST:event_btnContinuarActionPerformed

    private void txtNumeroIndividuoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroIndividuoKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtNumeroIndividuoKeyTyped

    private void txtNumeroRamaTalloKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroRamaTalloKeyTyped
       numeros.keyTyped(evt);
    }//GEN-LAST:event_txtNumeroRamaTalloKeyTyped

    private void txtAzimutKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAzimutKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtAzimutKeyTyped

    private void txtDistanciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDistanciaKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtDistanciaKeyTyped

    private void txtDiametroNormalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiametroNormalKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtDiametroNormalKeyTyped

    private void txtAlturaTotalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAlturaTotalKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtAlturaTotalKeyTyped

    private void txtAnguloInclinacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAnguloInclinacionKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtAnguloInclinacionKeyTyped

    private void txtAlturaFusteLimpioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAlturaFusteLimpioKeyTyped
       numeros.keyTyped(evt);
    }//GEN-LAST:event_txtAlturaFusteLimpioKeyTyped

    private void txtAlturaComercialKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAlturaComercialKeyTyped
       numeros.keyPressed(evt);
    }//GEN-LAST:event_txtAlturaComercialKeyTyped

    private void txtDiametroCopaNSKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiametroCopaNSKeyTyped
       numeros.keyTyped(evt);
    }//GEN-LAST:event_txtDiametroCopaNSKeyTyped

    private void txtDiametroCopaEOKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiametroCopaEOKeyTyped
       numeros.keyTyped(evt);
    }//GEN-LAST:event_txtDiametroCopaEOKeyTyped

    private void chkArboladoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkArboladoActionPerformed
        if (chkArbolado.isSelected()) {
            estadoArbolVivo();
        } else {
            Object[] opciones = {"Si", "No"};
            int respuesta = JOptionPane.showOptionDialog(null, "Si capturó, se eliminará la información del arbolado D, ¿Esta seguro?",
                    "Arbolado D", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[1]);
            if (respuesta == JOptionPane.YES_OPTION) {
                this.cdArbolado.deleteArboladoSitio(ceSitio.getSitioID());
                deshabiliarControles();
                this.combo.reiniciarTabla(this.grdArbolado);
                llenarTabla();
                this.combo.reiniciarComboModel(cmbConsecutivo);
                fillCmbConsecutivo();
            } else {
                chkArbolado.setSelected(true);
                txtNumeroIndividuo.requestFocus();
            }
        }
    }//GEN-LAST:event_chkArboladoActionPerformed

    private void btnColectaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnColectaActionPerformed
        try {
            int fila = grdArbolado.getSelectedRow();
            String noIndividuo = grdArbolado.getValueAt(fila, 3).toString();
            
           claveColecta.setLocationRelativeTo(Main.main);
            
            CatEFamiliaEspecie indexFamilia = (CatEFamiliaEspecie) cmbFamilia.getSelectedItem();
            CatEGenero indexGenero = (CatEGenero) cmbGenero.getSelectedItem();
            CatEEspecie indexEspecie = (CatEEspecie) cmbEspecie.getSelectedItem();
            CEColectaBotanica ceColecta = new CEColectaBotanica();
            if(indexFamilia != null){
                ceColecta.setFamiliaID(indexFamilia.getFamiliaID());
            }
            if(indexGenero != null){
                ceColecta.setGeneroID(indexGenero.getGeneroID());
            }
            if(indexEspecie != null){
                ceColecta.setEspecieID(indexEspecie.getEspecieID());
            }
            ceColecta.setUPMID(this.upmID);
            //ceColecta.setInfraespecie(txtInfraespecie.getText());
            ceColecta.setNombreComun(txtNombreComun.getText());
            claveColecta.setDatosIniciales(ceColecta, FORMATO_ID, "TAXONOMIA_Arbolado", "NoIndividuo", this.sitioID, Integer.parseInt(noIndividuo));
            claveColecta.setVisible(true);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un registro para asignar la clave de colecta"
                    + e.getClass().getName() + " : " + e.getMessage(), "Clave de colecta", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnColectaActionPerformed

    private void cmbEspecieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEspecieActionPerformed
       CatEEspecie indexEspecie = (CatEEspecie) cmbEspecie.getSelectedItem();
        DefaultComboBoxModel dcm = (DefaultComboBoxModel) cmbInfraespecie.getModel();
        dcm.removeAllElements();
        if (indexEspecie != null) {
            fillCmbInfraespecie(indexEspecie.getEspecieID());
        }
    }//GEN-LAST:event_cmbEspecieActionPerformed

    private void btnLimparControlesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparControlesActionPerformed
        // TODO add your handling code here:
        limpiarControles();
        txtNumeroIndividuo.requestFocus();
    }//GEN-LAST:event_btnLimparControlesActionPerformed

    private void cmbFormaFusteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFormaFusteActionPerformed
          if(cmbFormaFuste.getSelectedIndex()==-1){
           txtAnguloInclinacion.setEnabled(false);
       }else{
            txtAnguloInclinacion.setEnabled(true);
       }
    }//GEN-LAST:event_cmbFormaFusteActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnColecta;
    private javax.swing.JButton btnContinuar;
    private javax.swing.JButton btnElimnar;
    private javax.swing.JButton btnLimparControles;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnTrazoSitio;
    private javax.swing.JCheckBox chkArbolado;
    private javax.swing.JCheckBox chkEsSubmuestra;
    private javax.swing.JComboBox cmbAgenteDanio1;
    private javax.swing.JComboBox cmbAgenteDanio2;
    private javax.swing.JComboBox cmbCondicion;
    private javax.swing.JComboBox cmbCondicionMuertoPie;
    private javax.swing.JComboBox cmbConsecutivo;
    private javax.swing.JComboBox cmbDencidadCopa;
    private javax.swing.JComboBox cmbEspecie;
    private javax.swing.JComboBox cmbExposicionLuzCopa;
    private javax.swing.JComboBox cmbFamilia;
    private javax.swing.JComboBox cmbFormaFuste;
    private javax.swing.JComboBox cmbFormaVida;
    private javax.swing.JComboBox cmbGenero;
    private javax.swing.JComboBox cmbGradoPutrefaccion;
    private javax.swing.JComboBox cmbInfraespecie;
    private javax.swing.JComboBox cmbMuerteRegresiva;
    private javax.swing.JComboBox cmbPosicionCopa;
    private javax.swing.JComboBox cmbProporcionCopaViva;
    private javax.swing.JComboBox cmbSeveridad1;
    private javax.swing.JComboBox cmbSeveridad2;
    private javax.swing.JComboBox cmbTipoTocon;
    private javax.swing.JComboBox cmbTransparenciaFollaje;
    private javax.swing.JTable grdArbolado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblAlturaComercial;
    private javax.swing.JLabel lblAlturaFusteLimpio;
    private javax.swing.JLabel lblAlturaTotal;
    private javax.swing.JLabel lblAnguloInclinacion;
    private javax.swing.JLabel lblArbolado;
    private javax.swing.JLabel lblAzimut;
    private javax.swing.JLabel lblClaveColecta;
    private javax.swing.JLabel lblCondicion;
    private javax.swing.JLabel lblCondicionMuertoPie;
    private javax.swing.JLabel lblConsecutivo;
    private javax.swing.JLabel lblDanio1;
    private javax.swing.JLabel lblDanio2;
    private javax.swing.JLabel lblDiametroNormal;
    private javax.swing.JLabel lblDistancia;
    private javax.swing.JLabel lblEspecie;
    private javax.swing.JLabel lblExposicionLuzCopa;
    private javax.swing.JLabel lblExposicionLuzCopa1;
    private javax.swing.JLabel lblFamilia;
    private javax.swing.JLabel lblFormaFuste;
    private javax.swing.JLabel lblFormaVida;
    private javax.swing.JLabel lblGenero;
    private javax.swing.JLabel lblGradoPutrefaccion;
    private javax.swing.JLabel lblInfraespecie;
    private javax.swing.JLabel lblMuerteRegresiva;
    private javax.swing.JLabel lblNombreComun;
    private javax.swing.JLabel lblNumeroIndividuo;
    private javax.swing.JLabel lblPorporcionCopaViva;
    private javax.swing.JLabel lblPosicionCopa;
    private javax.swing.JLabel lblRamaTallo;
    private javax.swing.JLabel lblSeveridad1;
    private javax.swing.JLabel lblSeveridad2;
    private javax.swing.JLabel lblSitio;
    private javax.swing.JLabel lblTransparenciaFollaje;
    private javax.swing.JLabel lblUPM;
    private javax.swing.JFormattedTextField txtAlturaComercial;
    private javax.swing.JFormattedTextField txtAlturaFusteLimpio;
    private javax.swing.JFormattedTextField txtAlturaTotal;
    private javax.swing.JFormattedTextField txtAnguloInclinacion;
    private javax.swing.JFormattedTextField txtAzimut;
    public javax.swing.JTextField txtClaveColecta;
    private javax.swing.JFormattedTextField txtDiametroCopaEO;
    private javax.swing.JFormattedTextField txtDiametroCopaNS;
    private javax.swing.JFormattedTextField txtDiametroNormal;
    private javax.swing.JFormattedTextField txtDistancia;
    private javax.swing.JTextField txtNombreComun;
    private javax.swing.JFormattedTextField txtNumeroIndividuo;
    private javax.swing.JFormattedTextField txtNumeroRamaTallo;
    private javax.swing.JTextField txtSitio;
    private javax.swing.JTextField txtUPM;
    // End of variables declaration//GEN-END:variables
}
