package gob.conafor.taxonomia.vie;

import gob.conafor.ini.vie.FrmRevisionModulos;
import gob.conafor.ini.vie.Main;
import gob.conafor.sitio.mod.CDCoberturaSuelo;
import gob.conafor.sitio.mod.CDSitio;
import gob.conafor.sitio.mod.CECoberturaSuelo;
import gob.conafor.sitio.mod.CESitio;
import gob.conafor.sys.mod.CDSecuencia;
import gob.conafor.taxonomia.mod.CDColectaBotanica;
import gob.conafor.taxonomia.mod.CDCondicionTaxonomica;
import gob.conafor.taxonomia.mod.CDEspecies;
import gob.conafor.taxonomia.mod.CDSotoBosque;
import gob.conafor.taxonomia.mod.CEColectaBotanica;
import gob.conafor.taxonomia.mod.CESotoBosque;
import gob.conafor.taxonomia.mod.CatEAgenteDanio;
import gob.conafor.taxonomia.mod.CatEEspecie;
import gob.conafor.taxonomia.mod.CatEFamiliaEspecie;
import gob.conafor.taxonomia.mod.CatEGenero;
import gob.conafor.taxonomia.mod.CatEInfraespecie;
import gob.conafor.taxonomia.mod.CatETipoVigor;
import gob.conafor.upm.vie.UPMForms;
import gob.conafor.utils.Datos;
import gob.conafor.utils.FuncionesComunes;
import gob.conafor.utils.Tablas;
import gob.conafor.utils.ValidacionesComunes;
import gob.conafor.utils.Version;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class FrmSotoBosque extends javax.swing.JInternalFrame {

    private CESitio ceSitio;
    private boolean revision;
    private int sitio;
    private int sotoBosqueID;
    private static final int FORMATO_ID = 1;
    private int sitioID;
    private int upmID;
    private final int cubiertaSuelo;
    private final int sotoBosque;
    private final int repoblado;
    private int gramineas;
    private int helechos;
    private int musgos;
    private int liquenes;
    private int hierbas;
    private int rocas;
    private int sueloDesnudo;
    private int hojarasca;
    private int gravaPiedra;
    private int otros;
    private boolean sotobosqueHabilitado=true;//No esta Habilitado
    private CDEspecies especie = new CDEspecies();
    private CDCondicionTaxonomica condicion = new CDCondicionTaxonomica();
    private ValidacionesComunes validacion = new ValidacionesComunes();
    private CDCoberturaSuelo cdCobertura = new CDCoberturaSuelo();
    private CECoberturaSuelo ceCobertura = new CECoberturaSuelo();
    private CDSotoBosque cdSotobosque = new CDSotoBosque();
    private Integer frecuencia025 = null;
    private Integer cobertura025 = null;
    private Integer frecuencia151 = null;
    private Integer cobertura151 = null;
    private Integer frecuencia275 = null;
    private Integer cobertura275 = null;
    private Integer porcentajeDanio = null;
    private Integer sotobosqueFuera;
    private Integer porcentajeSotobosqueFuera;
    private Datos numeros = new Datos();
    private FuncionesComunes combo = new FuncionesComunes();
    private CDEspecies cdEspecies = new CDEspecies();
    private CDSecuencia cdSecuencia = new CDSecuencia();
    private CDSitio cdSitio = new CDSitio();
    private int actualizar;
    private FuncionesComunes funciones = new FuncionesComunes();
    private int secuenciaID;
    private Version ver=new Version();
    private String version=ver.getVersion();
    FrmRevisionModulos revisionModulos=new FrmRevisionModulos();
   
    public FrmSotoBosque() {
        initComponents();
        this.cubiertaSuelo = 7;
        this.sotoBosque = 8;
        this.repoblado = 9;
        fillCmbFamilia();
        fillCmbGenero(); //Prueba
        fillCmbVigor();
        fillCmbDanio();
    }
    
    public void setDatosIniciales(CESitio sitio) {
        this.actualizar = 0;
        CatEAgenteDanio danio = (CatEAgenteDanio) cmbDanio.getSelectedItem();
        this.ceSitio = sitio;
        this.upmID = sitio.getUpmID();
        this.sitioID = this.cdSotobosque.getSitioID(sitio.getUpmID(), sitio.getSitio());
        this.secuenciaID = sitio.getSecuencia();
        txtUPM.setText(String.valueOf(sitio.getUpmID()));
        txtSitio.setText(String.valueOf(sitio.getSitio()));
        limpiarControlesCubiertaSuelo();
        llenarTabla();
        if (danio.getAgenteDanioID() > 1) {
            txtPorcentajeDanio.setEnabled(true);
        }
        funciones.manipularBotonesMenuPrincipal(true);
    }

    public void revisarSotoBosque(CESitio sitio) {
        revision=true;
        
        CatEAgenteDanio danio = (CatEAgenteDanio) cmbDanio.getSelectedItem();
        CESitio sitioSotobosqueFuera;
        this.actualizar = 1;
        this.ceSitio = sitio;
        this.sitio = sitio.getSitio();
        this.sitioID = sitio.getSitioID();
        this.upmID = sitio.getUpmID();
        this.secuenciaID = sitio.getSecuencia();
        txtSitio.setText(String.valueOf(this.sitio));
        txtUPM.setText(String.valueOf(this.upmID));
        llenarTabla();
        if (danio.getAgenteDanioID() > 1) {
            txtPorcentajeDanio.setEnabled(true);
        }
        ceCobertura = this.cdCobertura.getDatosCoberturaSitio(sitio.getSitioID());
        txtGramineas.setText(String.valueOf(this.ceCobertura.getGramineas()));
        txtHelechos.setText(String.valueOf(this.ceCobertura.getHelechos()));
        txtMusgos.setText(String.valueOf(this.ceCobertura.getMusgos()));
        txtLiquenes.setText(String.valueOf(this.ceCobertura.getLiquenes()));
        txtHierbas.setText(String.valueOf(this.ceCobertura.getHierbas()));
        txtRoca.setText(String.valueOf(this.ceCobertura.getRoca()));
        txtSueloDesnudo.setText(String.valueOf(this.ceCobertura.getSueloDesnudo()));
        txtHojarasca.setText(String.valueOf(this.ceCobertura.getHojarasca()));
        txtGravaPiedra.setText(String.valueOf(this.ceCobertura.getGrava()));
        txtOtros.setText(String.valueOf(this.ceCobertura.getOtros()));
        sitioSotobosqueFuera = cdSitio.getSotoBosqueFuera(this.sitioID);
        chkSotobosque.setSelected(!this.cdSotobosque.validarTablaSotobosque(this.ceSitio.getSitioID()));
        if (sitioSotobosqueFuera.getSotobosqueFuera() == 1) {
            chkSotoBosqueFuera.setEnabled(true);
            chkSotoBosqueFuera.setSelected(true);
            txtPorcentajeSotobosqueFuera.setEnabled(true);
            txtPorcentajeSotobosqueFuera.setText(String.valueOf(sitioSotobosqueFuera.getPorcentajeSotobosque()));
        } else {
            chkSotoBosqueFuera.setSelected(false);
            txtPorcentajeSotobosqueFuera.setEnabled(false);
        }
        funciones.manipularBotonesMenuPrincipal(true);
        chkSotobosque.setEnabled(funciones.habilitarCheckBox("TAXONOMIA_SotoBosque", this.sitioID));
    }
    
    public void desabilitarSotobosque(){
        System.err.println("Sotobosque deshabilitado");
        chkSotobosque.setSelected(false);
        chkSotobosque.setVisible(false);
        chkSotoBosqueFuera.setSelected(false);
        pnlSotobosque.setVisible(false);
        sotobosqueHabilitado=false;
    }
    
    public void habilitarSotobosque(){
        chkSotobosque.setSelected(true);
        chkSotobosque.setVisible(true);
        chkSotoBosqueFuera.setSelected(true);
        pnlSotobosque.setVisible(true);
    }
    public boolean setDatosCobertura() {
        if (validarCamposVacios()) {
            this.gramineas = Integer.parseInt(txtGramineas.getText());
            this.helechos = Integer.parseInt(txtHelechos.getText());
            this.musgos = Integer.parseInt(txtMusgos.getText());
            this.liquenes = Integer.parseInt(txtLiquenes.getText());
            this.hierbas = Integer.parseInt(txtHierbas.getText());
            this.rocas = Integer.parseInt(txtRoca.getText());
            this.sueloDesnudo = Integer.parseInt(txtSueloDesnudo.getText());
            this.hojarasca = Integer.parseInt(txtHojarasca.getText());
            this.gravaPiedra = Integer.parseInt(txtGravaPiedra.getText());
            this.otros = Integer.parseInt(txtOtros.getText());
            return true;
        }else
            return false;
    }

    public void crearCoberturaSuelo() {
        CECoberturaSuelo cobertura = new CECoberturaSuelo();
        CDCoberturaSuelo cdCobertura = new CDCoberturaSuelo();
        cobertura.setSitioID(this.sitioID);
        cobertura.setGramineas(this.gramineas);
        cobertura.setHelechos(this.helechos);
        cobertura.setMusgos(this.musgos);
        cobertura.setLiquenes(this.liquenes);
        cobertura.setHierbas(this.hierbas);
        cobertura.setRoca(this.rocas);
        cobertura.setSueloDesnudo(this.sueloDesnudo);
        cobertura.setHojarasca(this.hojarasca);
        cobertura.setGrava(this.gravaPiedra);
        cobertura.setOtros(this.otros);

        cdCobertura.insertCoberturaSuelo(cobertura);
    }
    
    public void actualizarCoberturaSuelo(){
        CECoberturaSuelo ceCobertura = new CECoberturaSuelo();
        CDCoberturaSuelo cdCobertura = new CDCoberturaSuelo();
        ceCobertura.setSitioID(this.sitioID);
        ceCobertura.setGramineas(this.gramineas);
        ceCobertura.setHelechos(this.helechos);
        ceCobertura.setMusgos(this.musgos);
        ceCobertura.setLiquenes(this.liquenes);
        ceCobertura.setHierbas(this.hierbas);
        ceCobertura.setRoca(this.rocas);
        ceCobertura.setSueloDesnudo(this.sueloDesnudo);
        ceCobertura.setHojarasca(this.hojarasca);
        ceCobertura.setGrava(this.gravaPiedra);
        ceCobertura.setOtros(this.otros);
        cdCobertura.updateCoberturaSuelo(ceCobertura);
    }

    public boolean validarPorcentaje() {
        ValidacionesComunes validacion = new ValidacionesComunes();

        if (validacion.esPorcentaje(this.gramineas)) {
            txtGramineas.requestFocus();
            return false;
        } else if (validacion.esPorcentaje(this.helechos)) {
            txtHelechos.requestFocus();
            return false;
        } else if (validacion.esPorcentaje(this.musgos)) {
            txtMusgos.requestFocus();
            return false;
        } else if (validacion.esPorcentaje(this.liquenes)) {
            txtLiquenes.requestFocus();
            return false;
        } else if (validacion.esPorcentaje(this.hierbas)) {
            txtHierbas.requestFocus();
            return false;
        } else if (validacion.esPorcentaje(this.rocas)) {
            txtRoca.requestFocus();
            return false;
        } else if (validacion.esPorcentaje(this.sueloDesnudo)) {
            txtSueloDesnudo.requestFocus();
            return false;
        } else if (validacion.esPorcentaje(this.hojarasca)) {
            txtHojarasca.requestFocus();
            return false;
        } else if (validacion.esPorcentaje(this.gravaPiedra)) {
            txtGravaPiedra.requestFocus();
            return false;
        } else if (validacion.esPorcentaje(this.otros)) {
            txtOtros.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    public boolean validarCamposVacios() {
        if (txtGramineas.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe introducir un valor para Gramineas", "Vegetación menor", JOptionPane.ERROR_MESSAGE);
            txtGramineas.requestFocus();
            return false;
        } else if (txtHelechos.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe introducir un valor para Helechos", "Vegetación menor", JOptionPane.ERROR_MESSAGE);
            txtHelechos.requestFocus();
            return false;
        } else if (txtMusgos.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe introducir un valor para Musgos", "Vegetación menor", JOptionPane.ERROR_MESSAGE);
            txtMusgos.requestFocus();
            return false;
        } else if (txtLiquenes.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe introducir un valor para Líquenes", "Vegetación menor", JOptionPane.ERROR_MESSAGE);
            txtLiquenes.requestFocus();
            return false;
        } else if (txtHierbas.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe introducir un valor para Hierbas", "Vegetación menor", JOptionPane.ERROR_MESSAGE);
            txtHierbas.requestFocus();
            return false;
        } else if (txtRoca.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe introducir un valor para Roca", "Cubierta de suelo", JOptionPane.ERROR_MESSAGE);
            txtRoca.requestFocus();
            return false;
        } else if (txtSueloDesnudo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe introducir un valor para Suelo desnudo", "Cubierta de suelo", JOptionPane.ERROR_MESSAGE);
            txtSueloDesnudo.requestFocus();
            return false;
        } else if (txtHojarasca.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe introducir un valor para Hojarasca", "Cubierta de suelo", JOptionPane.ERROR_MESSAGE);
            txtHojarasca.requestFocus();
            return false;
        } else if (txtGravaPiedra.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe introducir un valor para Grava piedra", "Cubierta de suelo", JOptionPane.ERROR_MESSAGE);
            txtGravaPiedra.requestFocus();
            return false;
        } else if (txtOtros.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe introducir un valor para Otros", "Cubierta de suelo", JOptionPane.ERROR_MESSAGE);
            txtOtros.requestFocus();
            return false;
        } else {
            return true;
        }
    }
    
    public boolean validarSumaCubiertaSuelo() {
        int suma = this.rocas + this.sueloDesnudo + this.hojarasca + this.gravaPiedra + this.otros;
        if (suma != 100) {
            JOptionPane.showMessageDialog(null, "Los porcentajes de cubierta del suelo deben sumar 100", "Vegetación menor", JOptionPane.ERROR_MESSAGE);
            txtRoca.requestFocus();
            return false;
        }
        return true;
    }

    public void fillCmbFamilia() {
        List<CatEFamiliaEspecie> listFamilia = new ArrayList<>();
        listFamilia = especie.getFamiliaEspecies();
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
    
   /* public void fillCmbGeneroFiltrado(char filtro){
         List<CatEGenero> listGenero = new ArrayList<>();
        CDEspecies sp = new CDEspecies();
        listGenero = sp.getGeneroFiltrado(filtro);
        if (listGenero != null) {
            int size = listGenero.size();
            for (int i = 0; i < size; i++) {
                cmbGenero.addItem(listGenero.get(i));
            }
        }
    }*/
    
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

    public void fillCmbVigor() {
        List<CatETipoVigor> listVigor = new ArrayList<>();
        listVigor = condicion.getVigorSotobosqueRepoblado();
        if (listVigor != null) {
            int size = listVigor.size();
            for (int i = 0; i < size; i++) {
                cmbVigor.addItem(listVigor.get(i));
            }
        }
    }

    public void fillCmbDanio() {
        List<CatEAgenteDanio> listDanio = new ArrayList<>();
        listDanio = condicion.getAgenteDanio();
        if (listDanio != null) {
            int size = listDanio.size();
            for (int i = 0; i < size; i++) {
                cmbDanio.addItem(listDanio.get(i));
            }
        }
    }

    private boolean validarSotoBosqueVacios() {
        CatEAgenteDanio danio = (CatEAgenteDanio) cmbDanio.getSelectedItem();
        if (!txtFrecuencia025.getText().isEmpty() && txtCobertura025.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe de capturar porcenta de cobertura 0.25-1.50", "Sotobosque", JOptionPane.INFORMATION_MESSAGE);
            txtCobertura025.requestFocus();
            return false;
        } else if (!txtFrecuencia151.getText().isEmpty() && txtCobertura151.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe de capturar porcenta de cobertura 1.51-2.75", "Sotobosque", JOptionPane.INFORMATION_MESSAGE);
            txtCobertura151.requestFocus();
            return false;
        } else if (!txtFrecuencia275.getText().isEmpty() && txtCobertura275.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe de capturar porcenta de cobertura > 2.75", "Sotobosque", JOptionPane.INFORMATION_MESSAGE);
            txtCobertura275.requestFocus();
            return false;
        } else if (txtFrecuencia025.getText().isEmpty() && !txtCobertura025.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe de capturar frecuencia 0.25-1.50", "Sotobosque", JOptionPane.INFORMATION_MESSAGE);
            txtFrecuencia025.requestFocus();
            return false;
        } else if (txtFrecuencia151.getText().isEmpty() && !txtCobertura151.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe de capturar frecuencia 1.51-2.75", "Sotobosque", JOptionPane.INFORMATION_MESSAGE);
            txtFrecuencia151.requestFocus();
            return false;
        } else if (txtFrecuencia275.getText().isEmpty() && !txtCobertura275.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe de capturar frecuencia > 2.75", "Sotobosque", JOptionPane.INFORMATION_MESSAGE);
            txtFrecuencia275.requestFocus();
            return false;
        } else if (cmbVigor.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar un vigor", "Sotobosque", JOptionPane.INFORMATION_MESSAGE);
            cmbVigor.requestFocus();
            return false;
        } else if (cmbDanio.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar un daño", "Sotobosque", JOptionPane.INFORMATION_MESSAGE);
            cmbDanio.requestFocus();
            return false;
        } else if (danio.getAgenteDanioID() > 1 && txtPorcentajeDanio.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Si selecciono un daño, debe proporcionar un porcentaje", "Sotobosque", JOptionPane.INFORMATION_MESSAGE);
            txtPorcentajeDanio.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    public boolean validarSotobosqueFuera() {
        if(sotobosqueHabilitado==true){//Si hay sotobosque
        if (chkSotoBosqueFuera.isSelected()==true && txtPorcentajeSotobosqueFuera.getText().isEmpty()==true) {
            JOptionPane.showMessageDialog(null, "Si selecciona Sotobosque fuera, debe introducir un porcentaje", "Sotobosque fuera", JOptionPane.ERROR_MESSAGE);
            txtPorcentajeSotobosqueFuera.requestFocus();
            return false;
        } else {
            return true;
        }
        }else{
            return true;
        }
        
        
    }
    
    public void asignarDatosSotobosque() {
        try {
            this.frecuencia025 = Integer.valueOf(txtFrecuencia025.getText());
        } catch (NumberFormatException e) {
            this.frecuencia025 = null;
        }
        try {
            this.cobertura025 = Integer.valueOf(txtCobertura025.getText());
        } catch (NumberFormatException e) {
            this.cobertura025 = null;
        }
        try {
            this.frecuencia151 = Integer.valueOf(txtFrecuencia151.getText());
        } catch (NumberFormatException e) {
            this.frecuencia151 = null;
        }
        try {
            this.cobertura151 = Integer.valueOf(txtCobertura151.getText());
        } catch (NumberFormatException e) {
            this.cobertura151 = null;
        }
        try {
            this.frecuencia275 = Integer.valueOf(txtFrecuencia275.getText());
        } catch (NumberFormatException e) {
            this.frecuencia275 = null;
        }
        try {
            this.cobertura275 = Integer.valueOf(txtCobertura275.getText());
        } catch (NumberFormatException e) {
            this.cobertura275 = null;
        }
        try {
            this.porcentajeDanio = Integer.valueOf(txtPorcentajeDanio.getText());
        } catch (NumberFormatException e) {
            System.out.print(e);
            this.porcentajeDanio = null;
        }
    }
    
    private void asignarDatosSotobosqueFuera(){
        if(chkSotoBosqueFuera.isSelected()){
            this.sotobosqueFuera = 1;
        } else {
            this.sotobosqueFuera = 0;
        }
        try{
             this.porcentajeSotobosqueFuera = Integer.valueOf(txtPorcentajeSotobosqueFuera.getText());
        } catch (NumberFormatException e){
              this.porcentajeSotobosqueFuera = null;
        }
       
    }
            
    public boolean validarDatosSotoBosque() {
        if (this.frecuencia025 != null && this.frecuencia025 < 1) {
            JOptionPane.showMessageDialog(null, "Error! Debe de capturar un valos mayor a 0 en frecuencia 0.25-1.50", "Sotobosque", JOptionPane.ERROR_MESSAGE);
            txtFrecuencia025.requestFocus();
            return false;
        } else if (this.cobertura025 != null && this.cobertura025 < 1) {
            JOptionPane.showMessageDialog(null, "Error! Debe de capturar un valos mayor a 0 en cobertura 0.25-1.50", "Sotobosque", JOptionPane.ERROR_MESSAGE);
            txtCobertura025.requestFocus();
            return false;
        } else if (this.frecuencia151 != null && this.frecuencia151 < 1) {
            JOptionPane.showMessageDialog(null, "Error! Debe de capturar un valos mayor a 0 en cobertura 1.51-2.75", "Sotobosque", JOptionPane.ERROR_MESSAGE);
            txtFrecuencia151.requestFocus();
            return false;
        } else if (this.cobertura151 != null && this.cobertura151 < 1) {
            JOptionPane.showMessageDialog(null, "Error! Debe de capturar un valos mayor a 0 en cobertura 1.51-2.75", "Sotobosque", JOptionPane.ERROR_MESSAGE);
            txtCobertura151.requestFocus();
            return false;
        } else if (this.frecuencia275 != null && this.frecuencia275 < 1) {
            JOptionPane.showMessageDialog(null, "Error! Debe de capturar un valos mayor a 0 en frecuencia 2.75", "Sotobosque", JOptionPane.ERROR_MESSAGE);
            txtFrecuencia275.requestFocus();
            return false;
        } else if (this.cobertura275 != null && this.frecuencia275 < 1) {
            JOptionPane.showMessageDialog(null, "Error! Debe de capturar un valos mayor a 0 en cobertura 2.75", "Sotobosque", JOptionPane.ERROR_MESSAGE);
            txtCobertura275.requestFocus();
            return false;
        } else {
            return true;
        }
    }
    
     private boolean validarColectasObligatorias() {
        CDColectaBotanica colecta = new CDColectaBotanica();
        if (colecta.validarCapturaGenero("TAXONOMIA_SotoBosque", this.sitioID)) {
            JOptionPane.showMessageDialog(null, "Error! Faltan por asignar claves de colecta", "Sotobosque", JOptionPane.INFORMATION_MESSAGE);
            return false;
        } else {
            return true;
        }
    }

    private void crearSotobosque() {
        CatEFamiliaEspecie indexFamilia = (CatEFamiliaEspecie) cmbFamilia.getSelectedItem();
        CatEGenero indexGenero = (CatEGenero) cmbGenero.getSelectedItem();
        CatEEspecie indexEspecie = (CatEEspecie) cmbEspecie.getSelectedItem();
        CatEInfraespecie indexInfraespecie = (CatEInfraespecie) cmbInfraespecie.getSelectedItem();
        CatETipoVigor vigor = (CatETipoVigor) cmbVigor.getSelectedItem();
        CatEAgenteDanio danio = (CatEAgenteDanio) cmbDanio.getSelectedItem();
        CDSotoBosque sotobosque = new CDSotoBosque();
        CESotoBosque sb = new CESotoBosque();
        sb.setSitioID(this.sitioID);
        if(indexFamilia != null){
            sb.setFamiliaID(indexFamilia.getFamiliaID());
        }
        if (indexGenero != null) {
            sb.setGeneroID(indexGenero.getGeneroID());
        }
        if (indexEspecie != null) {
            sb.setEspecieID(indexEspecie.getEspecieID());
        }
         if (indexInfraespecie != null) {
            sb.setInfraespecieID(indexInfraespecie.getInfraespecieID());
        }
        sb.setNombreComun(txtNombreComun.getText());
        sb.setFrecuecia025150(this.frecuencia025);
        sb.setCobertura025150(this.cobertura025);
        sb.setFrecuencia151275(this.frecuencia151);
        sb.setCobertura151275(this.cobertura151);
        sb.setFrecuencia275(this.frecuencia275);
        sb.setCobertura275(this.cobertura275);
        sb.setVigorID(vigor.getVigorID());
        sb.setDanioID(danio.getAgenteDanioID());
        sb.setPorcentajeDanio(this.porcentajeDanio);

        sotobosque.insertDatosSotoBosque(sb);
    }
    
    private void crearSotoBosqueFuera(){
        CESitio ceSitio = new CESitio();
        CDSitio cdSitio = new CDSitio();
        ceSitio.setSitioID(this.sitioID);
        ceSitio.setSotobosqueFuera(this.sotobosqueFuera);
        ceSitio.setPorcentajeSotobosque(this.porcentajeSotobosqueFuera);
        cdSitio.updateSotobosqueFuera(ceSitio);
    }

    public void llenarTabla() {
        grdSotobosque.setModel(this.cdSotobosque.getTablaSotoBosque(this.sitioID));

        grdSotobosque.getColumnModel().getColumn(2).setPreferredWidth(90);//Consecutivo
        grdSotobosque.getColumnModel().getColumn(3).setPreferredWidth(120);//Familia
        grdSotobosque.getColumnModel().getColumn(4).setPreferredWidth(120);//Genero
        grdSotobosque.getColumnModel().getColumn(5).setPreferredWidth(120);//Especie
        grdSotobosque.getColumnModel().getColumn(6).setPreferredWidth(120);//Infraespecie
        grdSotobosque.getColumnModel().getColumn(7).setPreferredWidth(120);//Nombre comun
        grdSotobosque.getColumnModel().getColumn(8).setPreferredWidth(120);//Frecuencia 025-150
        grdSotobosque.getColumnModel().getColumn(9).setPreferredWidth(120);//Cobertura 025-150
        grdSotobosque.getColumnModel().getColumn(10).setPreferredWidth(120);//Frecuencia 151-275
        grdSotobosque.getColumnModel().getColumn(11).setPreferredWidth(120);//Cobertura 151-275
        grdSotobosque.getColumnModel().getColumn(12).setPreferredWidth(120);//Frecuencia 275
        grdSotobosque.getColumnModel().getColumn(13).setPreferredWidth(120);//Cobertura 275
        grdSotobosque.getColumnModel().getColumn(14).setPreferredWidth(120);//Vigor
        grdSotobosque.getColumnModel().getColumn(15).setPreferredWidth(120);//Clave danio
        grdSotobosque.getColumnModel().getColumn(16).setPreferredWidth(90);//Porcentaje danio
        grdSotobosque.getColumnModel().getColumn(17).setPreferredWidth(180);//Clave de colecta
        
        Tablas tabla = new Tablas();
        int[] columna_0 = {0};
        int[] column_1 = {1};
        //Ocultar campo ID
        tabla.hideColumnTable(grdSotobosque, columna_0);
        tabla.hideColumnTable(grdSotobosque, column_1);
    }
    
    private void actualizarSotoBosque() {
        try {
            int fila = grdSotobosque.getSelectedRow();
            String registro = grdSotobosque.getValueAt(fila, 0).toString();
            CatEFamiliaEspecie familia = (CatEFamiliaEspecie) cmbFamilia.getSelectedItem();
            CatEGenero generoIndex = (CatEGenero) cmbGenero.getSelectedItem();
            CatEEspecie especieIndex = (CatEEspecie) cmbEspecie.getSelectedItem();
            CatEInfraespecie indexInfraespecie = (CatEInfraespecie) cmbInfraespecie.getSelectedItem();
            CatEAgenteDanio danio = (CatEAgenteDanio) cmbDanio.getSelectedItem();
            CatETipoVigor vigor = (CatETipoVigor) cmbVigor.getSelectedItem();
            CESotoBosque sb = new CESotoBosque();
            sb.setSitioID(this.sitioID);
            sb.setSotoBosqueID(Integer.parseInt(registro));
            if (familia != null) {
                sb.setFamiliaID(familia.getFamiliaID());
            } else {
                sb.setFamiliaID(null);
            }
            if (generoIndex != null) {
                sb.setGeneroID(generoIndex.getGeneroID());
            } else {
                sb.setGeneroID(null);
            }
            if (especieIndex != null) {
                sb.setEspecieID(especieIndex.getEspecieID());
            } else {
                sb.setEspecieID(null);
            }
            if(indexInfraespecie != null){
                sb.setInfraespecieID(indexInfraespecie.getInfraespecieID());
            }
            sb.setNombreComun(txtNombreComun.getText());
            sb.setFrecuecia025150(this.frecuencia025);
            sb.setCobertura025150(this.cobertura025);
            sb.setFrecuencia151275(this.frecuencia151);
            sb.setCobertura151275(this.cobertura151);
            sb.setFrecuencia275(this.frecuencia275);
            sb.setCobertura275(this.cobertura275);
            sb.setVigorID(vigor.getVigorID());
            sb.setDanioID(danio.getAgenteDanioID());
            sb.setPorcentajeDanio(this.porcentajeDanio);
            this.cdSotobosque.updateDatosSotoBosque(sb);
            limpiarControles();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla de sotobosque"
                    + e.getClass().getName() + " : " + e.getMessage(), "Sotobosque", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void eliminarSotoBosque() {
        try {
            int fila = grdSotobosque.getSelectedRow();
            String registro = grdSotobosque.getValueAt(fila, 0).toString();
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Esta seguro de borrar el registro de sotobosque?",
                    "Sotobosque", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (respuesta == JOptionPane.YES_OPTION) {
                CESotoBosque sb = new CESotoBosque();
                sb.setSotoBosqueID(Integer.parseInt(registro));
                this.cdSotobosque.deleteDatosSotoBosque(sb);
                limpiarControles();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla de sotobosque"
                    + "", "Sotobosque", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void limpiarControles() {
        cmbFamilia.setSelectedItem(null);
        cmbGenero.setSelectedItem(null);
        cmbEspecie.setSelectedItem(null);
        cmbInfraespecie.setSelectedItem(null);
        txtNombreComun.setText("");
        txtFrecuencia025.setText("");
        txtCobertura025.setText("");
        txtFrecuencia151.setText("");
        txtCobertura151.setText("");
        txtFrecuencia275.setText("");
        txtCobertura275.setText("");
        cmbVigor.setSelectedItem(null);
        cmbDanio.setSelectedIndex(0);
        txtPorcentajeDanio.setText("");
        cmbFamilia.requestFocus();
        txtClaveColecta.setText("");
    }
    
    public void limpiarControlesCubiertaSuelo() {
        txtGramineas.setText("");
        txtGramineas.setValue(null);
        txtHelechos.setText("");
        txtHelechos.setValue(null);
        txtMusgos.setText("");
        txtMusgos.setValue(null);
        txtLiquenes.setText("");
        txtLiquenes.setValue(null);
        txtHierbas.setText("");
        txtHierbas.setValue(null);
        txtRoca.setText("");
        txtRoca.setValue(null);
        txtSueloDesnudo.setText("");
        txtSueloDesnudo.setValue(null);
        txtHojarasca.setText("");
        txtHojarasca.setValue(null);
        txtGravaPiedra.setText("");
        txtGravaPiedra.setValue(null);
        txtOtros.setText("");
        txtOtros.setValue(null);
        txtPorcentajeSotobosqueFuera.setText("");
        txtPorcentajeSotobosqueFuera.setValue(null);
        chkSotoBosqueFuera.setSelected(true);
    }
    
    private void seleccionarSiguienteFormulario(Integer secuenciaID){
         if (secuenciaID != null) {
            switch (secuenciaID) {
                case 1: //Módulo A
                    UPMForms.repoblado.setDatosIniciales(this.ceSitio); 
                    UPMForms.repoblado.setVisible(true);
                    break;
                case 2: //Módulos A y C
                    UPMForms.repoblado.setDatosIniciales(this.ceSitio); 
                    UPMForms.repoblado.setVisible(true);
                    break;
                case 3: //Modulos A, C, E y G
                    UPMForms.claveVegetacion.setDatosIniciales(this.ceSitio);
                    UPMForms.claveVegetacion.setVisible(true);
                    break;
                case 4: //Modulos A y E
                    UPMForms.repoblado.setDatosIniciales(this.ceSitio);
                    UPMForms.repoblado.setVisible(true);
                    break;
                case 5: //Modulos A, C, D y F
                    UPMForms.repoblado.setDatosIniciales(this.ceSitio);
                    UPMForms.repoblado.setVisible(true);
                    break;
                case 6://Modulos A, C y D
                    UPMForms.repoblado.setDatosIniciales(this.ceSitio);
                    UPMForms.repoblado.setVisible(true);
                    break;
                case 7://Modulos A, C, D y E
                    UPMForms.repoblado.setDatosIniciales(this.ceSitio);
                    UPMForms.repoblado.setVisible(true);
                    break;
                case 8://Modulos A, C, D, E y F
                    UPMForms.repoblado.setDatosIniciales(this.ceSitio);
                    UPMForms.repoblado.setVisible(true);
                    break;
                case 9://Modulos A, C y E
                    UPMForms.repoblado.setDatosIniciales(this.ceSitio);
                    UPMForms.repoblado.setVisible(true);
                    break;
                case 10://Modulos A, C, H
                    UPMForms.repoblado.setDatosIniciales(this.ceSitio);
                    UPMForms.repoblado.setVisible(true);
                    break;
                case 11://Modulos A y H
                    UPMForms.repoblado.setDatosIniciales(this.ceSitio);
                    UPMForms.repoblado.setVisible(true);
                    break;
                case 12://Modulos A, E y H
                    UPMForms.repoblado.setDatosIniciales(this.ceSitio);
                    UPMForms.repoblado.setVisible(true);
                    break;
                case 13://Modulos A, C, E y H
                    UPMForms.repoblado.setDatosIniciales(this.ceSitio);
                    UPMForms.repoblado.setVisible(true);
                    break;
                case 14://Modulos A, E y G
                    UPMForms.claveVegetacion.setDatosIniciales(this.ceSitio);
                    UPMForms.claveVegetacion.setVisible(true);
                    break;
                case 15://A y G
                    UPMForms.claveVegetacion.setDatosIniciales(this.ceSitio);
                    UPMForms.claveVegetacion.setVisible(true);
                    break;
            }
        }
    }
    
    private void revisarSiguienteFormulario(Integer secuenciaID) {
        if (secuenciaID != null) {
            switch (secuenciaID) {
                case 1: //Módulo A
                    UPMForms.repoblado.revisarRepoblado(this.ceSitio);
                    UPMForms.repoblado.setVisible(true);
                    break;
                case 2: //Módulos A y C
                    UPMForms.repoblado.revisarRepoblado(this.ceSitio);
                    UPMForms.repoblado.setVisible(true);
                    break;
                case 3: //Modulos A, C, E y G
                    UPMForms.claveVegetacion.revisarClaveVegetacion(this.ceSitio);
                    UPMForms.claveVegetacion.setVisible(true);
                    break;
                case 4: //Modulos A y E
                    UPMForms.repoblado.revisarRepoblado(this.ceSitio);
                    UPMForms.repoblado.setVisible(true);
                    break;
                case 5: //Modulos A, C, D y F
                    UPMForms.repoblado.revisarRepoblado(this.ceSitio);
                    UPMForms.repoblado.setVisible(true);
                    break;
                case 6://Modulos A, C y D
                    UPMForms.repoblado.revisarRepoblado(this.ceSitio);
                    UPMForms.repoblado.setVisible(true);
                    break;
                case 7://Modulos A, C, D y E
                    UPMForms.repoblado.revisarRepoblado(this.ceSitio);
                    UPMForms.repoblado.setVisible(true);
                    break;
                case 8://Modulos A, C, D, E y F
                    UPMForms.repoblado.revisarRepoblado(this.ceSitio);
                    UPMForms.repoblado.setVisible(true);
                    break;
                case 9://Modulos A, C y E
                    UPMForms.repoblado.revisarRepoblado(this.ceSitio);
                    UPMForms.repoblado.setVisible(true);
                    break;
                case 10://Modulos A, C, H
                    UPMForms.repoblado.revisarRepoblado(this.ceSitio);
                    UPMForms.repoblado.setVisible(true);
                    break;
                case 11://Modulos A y H
                    UPMForms.repoblado.revisarRepoblado(this.ceSitio);
                    UPMForms.repoblado.setVisible(true);
                    break;
                case 12://Modulos A, E y H
                    UPMForms.repoblado.revisarRepoblado(this.ceSitio);
                    UPMForms.repoblado.setVisible(true);
                    break;
                case 13://Modulos A, C, E y H
                    UPMForms.repoblado.revisarRepoblado(this.ceSitio);
                    UPMForms.repoblado.setVisible(true);
                    break;
                case 14://Modulos A, E y G
                    UPMForms.claveVegetacion.revisarClaveVegetacion(this.ceSitio);
                    UPMForms.claveVegetacion.setVisible(true);
                    break;
                case 15://A y G
                    UPMForms.claveVegetacion.revisarClaveVegetacion(this.ceSitio);
                    UPMForms.claveVegetacion.setVisible(true);
                    break;
            }
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
        lblVegetacionMenorCobertura = new javax.swing.JLabel();
        PnlDescripcion = new javax.swing.JPanel();
        PnlCoordenadas1 = new javax.swing.JPanel();
        lblGramineas = new javax.swing.JLabel();
        lblHelechos = new javax.swing.JLabel();
        lblMusgos = new javax.swing.JLabel();
        lblLiquenes = new javax.swing.JLabel();
        lblHierbas = new javax.swing.JLabel();
        lblVegetacionMenor = new javax.swing.JLabel();
        lblCoberturaVegetacionMenor = new javax.swing.JLabel();
        PnlCoordenadas2 = new javax.swing.JPanel();
        txtGramineas = new javax.swing.JFormattedTextField();
        txtHelechos = new javax.swing.JFormattedTextField();
        txtMusgos = new javax.swing.JFormattedTextField();
        txtLiquenes = new javax.swing.JFormattedTextField();
        txtHierbas = new javax.swing.JFormattedTextField();
        PnlDescripcion1 = new javax.swing.JPanel();
        PnlCoordenadas3 = new javax.swing.JPanel();
        lblRoca = new javax.swing.JLabel();
        lblSueloDesnudo = new javax.swing.JLabel();
        lblHojasrasca = new javax.swing.JLabel();
        lblGravaPiedra = new javax.swing.JLabel();
        lblOtros = new javax.swing.JLabel();
        lblCoberturaSuelo = new javax.swing.JLabel();
        lblPorcentajeCobertura = new javax.swing.JLabel();
        txtRoca = new javax.swing.JFormattedTextField();
        txtSueloDesnudo = new javax.swing.JFormattedTextField();
        txtHojarasca = new javax.swing.JFormattedTextField();
        txtGravaPiedra = new javax.swing.JFormattedTextField();
        txtOtros = new javax.swing.JFormattedTextField();
        btnContinuar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        chkSotobosque = new javax.swing.JCheckBox();
        pnlSotobosque = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        grdSotobosque = new javax.swing.JTable();
        lblRegistroSotobosque = new javax.swing.JLabel();
        lblFamilia = new javax.swing.JLabel();
        cmbFamilia = new javax.swing.JComboBox();
        lblGenero = new javax.swing.JLabel();
        cmbGenero = new javax.swing.JComboBox();
        lblEspecie = new javax.swing.JLabel();
        cmbEspecie = new javax.swing.JComboBox();
        lblInfraespecie = new javax.swing.JLabel();
        cmbInfraespecie = new javax.swing.JComboBox();
        lblNombreComun = new javax.swing.JLabel();
        txtNombreComun = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        lbl025 = new javax.swing.JLabel();
        lblFrecuencia025 = new javax.swing.JLabel();
        lblCobertura025 = new javax.swing.JLabel();
        lbl151 = new javax.swing.JLabel();
        lblFrecuencia151 = new javax.swing.JLabel();
        lblCobertura151 = new javax.swing.JLabel();
        lbl275 = new javax.swing.JLabel();
        lblFrecuencia275 = new javax.swing.JLabel();
        lblCobertura275 = new javax.swing.JLabel();
        lblVigor = new javax.swing.JLabel();
        cmbVigor = new javax.swing.JComboBox();
        lblDanio = new javax.swing.JLabel();
        cmbDanio = new javax.swing.JComboBox();
        lblPorcentajeDanio = new javax.swing.JLabel();
        txtFrecuencia025 = new javax.swing.JFormattedTextField();
        txtCobertura025 = new javax.swing.JFormattedTextField();
        txtFrecuencia151 = new javax.swing.JFormattedTextField();
        txtCobertura151 = new javax.swing.JFormattedTextField();
        txtFrecuencia275 = new javax.swing.JFormattedTextField();
        txtCobertura275 = new javax.swing.JFormattedTextField();
        txtPorcentajeDanio = new javax.swing.JFormattedTextField();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnColecta = new javax.swing.JButton();
        lblClaveColecta = new javax.swing.JLabel();
        txtClaveColecta = new javax.swing.JTextField();
        chkSotoBosqueFuera = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        txtPorcentajeSotobosqueFuera = new javax.swing.JFormattedTextField();

        setMaximizable(true);
        setTitle("Regitro de vegetación menor, cubierta de suelo y sotobosque módulo A "+version);
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

        lblVegetacionMenorCobertura.setBackground(new java.awt.Color(153, 153, 153));
        lblVegetacionMenorCobertura.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblVegetacionMenorCobertura.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVegetacionMenorCobertura.setText("Regisro de vegetación menor y cobertura del suelo");
        lblVegetacionMenorCobertura.setOpaque(true);

        PnlDescripcion.setBackground(new java.awt.Color(204, 204, 204));
        PnlDescripcion.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        PnlCoordenadas1.setBackground(new java.awt.Color(204, 204, 204));

        lblGramineas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblGramineas.setText("Gramíneas:");

        lblHelechos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblHelechos.setText("Helechos:");

        lblMusgos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMusgos.setText("Musgos:");

        lblLiquenes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblLiquenes.setText("Líquenes:");

        lblHierbas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblHierbas.setText("Hierbas:");

        javax.swing.GroupLayout PnlCoordenadas1Layout = new javax.swing.GroupLayout(PnlCoordenadas1);
        PnlCoordenadas1.setLayout(PnlCoordenadas1Layout);
        PnlCoordenadas1Layout.setHorizontalGroup(
            PnlCoordenadas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlCoordenadas1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(PnlCoordenadas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblHierbas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblGramineas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                    .addComponent(lblHelechos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblMusgos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblLiquenes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PnlCoordenadas1Layout.setVerticalGroup(
            PnlCoordenadas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlCoordenadas1Layout.createSequentialGroup()
                .addComponent(lblGramineas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblHelechos, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblMusgos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblLiquenes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblHierbas)
                .addGap(0, 26, Short.MAX_VALUE))
        );

        lblVegetacionMenor.setBackground(new java.awt.Color(153, 153, 153));
        lblVegetacionMenor.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblVegetacionMenor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVegetacionMenor.setText("Vegetación menor");
        lblVegetacionMenor.setOpaque(true);

        lblCoberturaVegetacionMenor.setBackground(new java.awt.Color(153, 153, 153));
        lblCoberturaVegetacionMenor.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblCoberturaVegetacionMenor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCoberturaVegetacionMenor.setText("Cobertura %");
        lblCoberturaVegetacionMenor.setOpaque(true);

        PnlCoordenadas2.setBackground(new java.awt.Color(204, 204, 204));

        txtGramineas.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtGramineas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtGramineasFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtGramineasFocusLost(evt);
            }
        });
        txtGramineas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtGramineasKeyTyped(evt);
            }
        });

        txtHelechos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtHelechos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtHelechosFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtHelechosFocusLost(evt);
            }
        });
        txtHelechos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtHelechosKeyTyped(evt);
            }
        });

        txtMusgos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtMusgos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtMusgosFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtMusgosFocusLost(evt);
            }
        });
        txtMusgos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMusgosKeyTyped(evt);
            }
        });

        txtLiquenes.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtLiquenes.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtLiquenesFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtLiquenesFocusLost(evt);
            }
        });
        txtLiquenes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLiquenesKeyTyped(evt);
            }
        });

        txtHierbas.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtHierbas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtHierbasFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtHierbasFocusLost(evt);
            }
        });
        txtHierbas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtHierbasKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout PnlCoordenadas2Layout = new javax.swing.GroupLayout(PnlCoordenadas2);
        PnlCoordenadas2.setLayout(PnlCoordenadas2Layout);
        PnlCoordenadas2Layout.setHorizontalGroup(
            PnlCoordenadas2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlCoordenadas2Layout.createSequentialGroup()
                .addGroup(PnlCoordenadas2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtGramineas, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                    .addComponent(txtHelechos)
                    .addComponent(txtMusgos)
                    .addComponent(txtLiquenes)
                    .addComponent(txtHierbas))
                .addContainerGap())
        );
        PnlCoordenadas2Layout.setVerticalGroup(
            PnlCoordenadas2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlCoordenadas2Layout.createSequentialGroup()
                .addComponent(txtGramineas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtHelechos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMusgos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtLiquenes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtHierbas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PnlDescripcionLayout = new javax.swing.GroupLayout(PnlDescripcion);
        PnlDescripcion.setLayout(PnlDescripcionLayout);
        PnlDescripcionLayout.setHorizontalGroup(
            PnlDescripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlDescripcionLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(PnlDescripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PnlCoordenadas1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblVegetacionMenor, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PnlDescripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PnlCoordenadas2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCoberturaVegetacionMenor, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(9, Short.MAX_VALUE))
        );
        PnlDescripcionLayout.setVerticalGroup(
            PnlDescripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlDescripcionLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PnlDescripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblVegetacionMenor)
                    .addComponent(lblCoberturaVegetacionMenor))
                .addGap(6, 6, 6)
                .addGroup(PnlDescripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PnlCoordenadas2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PnlCoordenadas1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3))
        );

        PnlDescripcion1.setBackground(new java.awt.Color(204, 204, 204));
        PnlDescripcion1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        PnlCoordenadas3.setBackground(new java.awt.Color(204, 204, 204));

        lblRoca.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblRoca.setText("Roca:");

        lblSueloDesnudo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblSueloDesnudo.setText("Suelo desnudo:");

        lblHojasrasca.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblHojasrasca.setText("Hojarasca:");

        lblGravaPiedra.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblGravaPiedra.setText("Grava piedras:");

        lblOtros.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblOtros.setText("Otros:");

        javax.swing.GroupLayout PnlCoordenadas3Layout = new javax.swing.GroupLayout(PnlCoordenadas3);
        PnlCoordenadas3.setLayout(PnlCoordenadas3Layout);
        PnlCoordenadas3Layout.setHorizontalGroup(
            PnlCoordenadas3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlCoordenadas3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnlCoordenadas3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSueloDesnudo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHojasrasca, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGravaPiedra, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblOtros, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRoca, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PnlCoordenadas3Layout.setVerticalGroup(
            PnlCoordenadas3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlCoordenadas3Layout.createSequentialGroup()
                .addComponent(lblRoca)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSueloDesnudo)
                .addGap(11, 11, 11)
                .addComponent(lblHojasrasca)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblGravaPiedra)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblOtros)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        lblCoberturaSuelo.setBackground(new java.awt.Color(153, 153, 153));
        lblCoberturaSuelo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblCoberturaSuelo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCoberturaSuelo.setText("Cobertura del suelo");
        lblCoberturaSuelo.setOpaque(true);

        lblPorcentajeCobertura.setBackground(new java.awt.Color(153, 153, 153));
        lblPorcentajeCobertura.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblPorcentajeCobertura.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPorcentajeCobertura.setText("Cobertura %");
        lblPorcentajeCobertura.setOpaque(true);

        txtRoca.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtRoca.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtRocaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtRocaFocusLost(evt);
            }
        });
        txtRoca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRocaKeyTyped(evt);
            }
        });

        txtSueloDesnudo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtSueloDesnudo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSueloDesnudoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSueloDesnudoFocusLost(evt);
            }
        });
        txtSueloDesnudo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSueloDesnudoKeyTyped(evt);
            }
        });

        txtHojarasca.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtHojarasca.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtHojarascaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtHojarascaFocusLost(evt);
            }
        });
        txtHojarasca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtHojarascaKeyTyped(evt);
            }
        });

        txtGravaPiedra.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtGravaPiedra.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtGravaPiedraFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtGravaPiedraFocusLost(evt);
            }
        });
        txtGravaPiedra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtGravaPiedraKeyTyped(evt);
            }
        });

        txtOtros.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtOtros.setNextFocusableComponent(chkSotobosque);
        txtOtros.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtOtrosFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtOtrosFocusLost(evt);
            }
        });
        txtOtros.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtOtrosKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout PnlDescripcion1Layout = new javax.swing.GroupLayout(PnlDescripcion1);
        PnlDescripcion1.setLayout(PnlDescripcion1Layout);
        PnlDescripcion1Layout.setHorizontalGroup(
            PnlDescripcion1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlDescripcion1Layout.createSequentialGroup()
                .addGroup(PnlDescripcion1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnlDescripcion1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lblCoberturaSuelo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(PnlDescripcion1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(PnlCoordenadas3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PnlDescripcion1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblPorcentajeCobertura, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                    .addComponent(txtRoca, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSueloDesnudo, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtHojarasca, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtGravaPiedra, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtOtros, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PnlDescripcion1Layout.setVerticalGroup(
            PnlDescripcion1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlDescripcion1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(PnlDescripcion1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPorcentajeCobertura)
                    .addComponent(lblCoberturaSuelo))
                .addGap(6, 6, 6)
                .addGroup(PnlDescripcion1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnlDescripcion1Layout.createSequentialGroup()
                        .addComponent(txtRoca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSueloDesnudo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtHojarasca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtGravaPiedra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtOtros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 16, Short.MAX_VALUE))
                    .addComponent(PnlCoordenadas3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        btnContinuar.setText("Continuar");
        btnContinuar.setNextFocusableComponent(btnSalir);
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

        chkSotobosque.setBackground(new java.awt.Color(204, 204, 204));
        chkSotobosque.setSelected(true);
        chkSotobosque.setText("Sotobosque");
        chkSotobosque.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        chkSotobosque.setNextFocusableComponent(cmbFamilia);
        chkSotobosque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSotobosqueActionPerformed(evt);
            }
        });

        pnlSotobosque.setBackground(new java.awt.Color(204, 204, 204));

        grdSotobosque.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        grdSotobosque.setToolTipText("");
        grdSotobosque.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        grdSotobosque.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grdSotobosqueMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(grdSotobosque);

        lblRegistroSotobosque.setBackground(new java.awt.Color(153, 153, 153));
        lblRegistroSotobosque.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblRegistroSotobosque.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRegistroSotobosque.setText("Registro de Sotobosque (Sitio de 12.68 m2)");
        lblRegistroSotobosque.setOpaque(true);

        lblFamilia.setText("Familia:");

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

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

        lbl025.setBackground(new java.awt.Color(153, 153, 153));
        lbl025.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl025.setText("0.25-1.50");
        lbl025.setOpaque(true);

        lblFrecuencia025.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFrecuencia025.setText("Frecuencia");
        lblFrecuencia025.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblCobertura025.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCobertura025.setText("%Cobertura");

        lbl151.setBackground(new java.awt.Color(153, 153, 153));
        lbl151.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl151.setText("1.51-2.75");
        lbl151.setOpaque(true);

        lblFrecuencia151.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFrecuencia151.setText("Frecuencia");

        lblCobertura151.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCobertura151.setText("%Cobertura");

        lbl275.setBackground(new java.awt.Color(153, 153, 153));
        lbl275.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl275.setText(">2.75");
        lbl275.setOpaque(true);

        lblFrecuencia275.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFrecuencia275.setText("Frecuencia");

        lblCobertura275.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCobertura275.setText("%Cobertura");

        lblVigor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVigor.setText("Vigor");

        lblDanio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDanio.setText("Daño");

        cmbDanio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDanioActionPerformed(evt);
            }
        });

        lblPorcentajeDanio.setText("% Daño");

        txtFrecuencia025.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtFrecuencia025.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtFrecuencia025FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtFrecuencia025FocusLost(evt);
            }
        });
        txtFrecuencia025.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFrecuencia025KeyTyped(evt);
            }
        });

        txtCobertura025.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtCobertura025.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCobertura025FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCobertura025FocusLost(evt);
            }
        });
        txtCobertura025.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCobertura025KeyTyped(evt);
            }
        });

        txtFrecuencia151.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtFrecuencia151.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtFrecuencia151FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtFrecuencia151FocusLost(evt);
            }
        });
        txtFrecuencia151.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFrecuencia151KeyTyped(evt);
            }
        });

        txtCobertura151.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtCobertura151.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCobertura151FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCobertura151FocusLost(evt);
            }
        });
        txtCobertura151.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCobertura151KeyTyped(evt);
            }
        });

        txtFrecuencia275.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtFrecuencia275.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtFrecuencia275FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtFrecuencia275FocusLost(evt);
            }
        });
        txtFrecuencia275.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFrecuencia275KeyTyped(evt);
            }
        });

        txtCobertura275.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtCobertura275.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCobertura275FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCobertura275FocusLost(evt);
            }
        });
        txtCobertura275.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCobertura275KeyTyped(evt);
            }
        });

        txtPorcentajeDanio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtPorcentajeDanio.setEnabled(false);
        txtPorcentajeDanio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPorcentajeDanioFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPorcentajeDanioFocusLost(evt);
            }
        });
        txtPorcentajeDanio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPorcentajeDanioKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbl025, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFrecuencia025, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtCobertura025, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(lblFrecuencia025, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblCobertura025)))
                        .addGap(10, 10, 10)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFrecuencia151, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFrecuencia151, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCobertura151)
                            .addComponent(txtCobertura151, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lbl151, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(lblFrecuencia275, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblCobertura275))
                    .addComponent(lbl275, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(txtFrecuencia275, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCobertura275, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(62, 62, 62)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbVigor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblVigor, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cmbDanio, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDanio, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPorcentajeDanio)
                    .addComponent(lblPorcentajeDanio, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(lbl025)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblFrecuencia025)
                            .addComponent(lblCobertura025))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFrecuencia025, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCobertura025, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFrecuencia151, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCobertura151, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lbl151)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblFrecuencia151)
                            .addComponent(lblCobertura151))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lbl275)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtCobertura275, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtFrecuencia275, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lblFrecuencia275)
                            .addComponent(lblCobertura275)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblVigor, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPorcentajeDanio, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDanio, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cmbVigor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPorcentajeDanio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbDanio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
        );

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnColecta.setText("Colecta");
        btnColecta.setNextFocusableComponent(chkSotoBosqueFuera);
        btnColecta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColectaActionPerformed(evt);
            }
        });

        lblClaveColecta.setText("Clave:");

        txtClaveColecta.setEnabled(false);

        chkSotoBosqueFuera.setBackground(new java.awt.Color(204, 204, 204));
        chkSotoBosqueFuera.setSelected(true);
        chkSotoBosqueFuera.setText("¿Existe sotobosque fuera del sitio de 12.56 m?");
        chkSotoBosqueFuera.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        chkSotoBosqueFuera.setNextFocusableComponent(txtPorcentajeSotobosqueFuera);
        chkSotoBosqueFuera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSotoBosqueFueraActionPerformed(evt);
            }
        });
        chkSotoBosqueFuera.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                chkSotoBosqueFueraPropertyChange(evt);
            }
        });

        jLabel1.setText("Porcentaje de sotobosque fuera del sito de 12.56 m:\n");

        txtPorcentajeSotobosqueFuera.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtPorcentajeSotobosqueFuera.setNextFocusableComponent(btnContinuar);
        txtPorcentajeSotobosqueFuera.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPorcentajeSotobosqueFueraFocusGained(evt);
            }
        });

        javax.swing.GroupLayout pnlSotobosqueLayout = new javax.swing.GroupLayout(pnlSotobosque);
        pnlSotobosque.setLayout(pnlSotobosqueLayout);
        pnlSotobosqueLayout.setHorizontalGroup(
            pnlSotobosqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSotobosqueLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSotobosqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSotobosqueLayout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap())
                    .addGroup(pnlSotobosqueLayout.createSequentialGroup()
                        .addComponent(lblRegistroSotobosque, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(6, 6, 6))
                    .addGroup(pnlSotobosqueLayout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSotobosqueLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(btnColecta)
                        .addGap(18, 18, 18)
                        .addComponent(lblClaveColecta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtClaveColecta, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(pnlSotobosqueLayout.createSequentialGroup()
                        .addComponent(lblFamilia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(lblGenero)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblEspecie)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblInfraespecie)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbInfraespecie, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblNombreComun)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombreComun, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlSotobosqueLayout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addComponent(chkSotoBosqueFuera)
                        .addGap(34, 34, 34)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPorcentajeSotobosqueFuera, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        pnlSotobosqueLayout.setVerticalGroup(
            pnlSotobosqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSotobosqueLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblRegistroSotobosque)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlSotobosqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFamilia)
                    .addComponent(cmbFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEspecie)
                    .addComponent(cmbEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGenero)
                    .addComponent(cmbGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblInfraespecie)
                    .addComponent(cmbInfraespecie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombreComun)
                    .addComponent(txtNombreComun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlSotobosqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblClaveColecta)
                    .addComponent(txtClaveColecta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnColecta)
                    .addComponent(btnGuardar)
                    .addComponent(btnModificar)
                    .addComponent(btnEliminar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlSotobosqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkSotoBosqueFuera)
                    .addComponent(txtPorcentajeSotobosqueFuera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(174, 174, 174)
                .addComponent(lblUPM)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtUPM, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblSitio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSitio, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(193, 193, 193))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblVegetacionMenorCobertura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(chkSotobosque)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PnlDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PnlDescripcion1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(pnlSotobosque, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnContinuar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(374, 374, 374))
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblVegetacionMenorCobertura)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(PnlDescripcion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(PnlDescripcion1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5))
                    .addComponent(chkSotobosque))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlSotobosque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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

    private void btnContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuarActionPerformed
        CDSotoBosque tblSotoBosque = new CDSotoBosque();
        asignarDatosSotobosqueFuera();
        if (setDatosCobertura() && validarSumaCubiertaSuelo() && validarPorcentaje() && validarSotobosqueFuera()) {
            if (funciones.validarSeccionCapturada("TAXONOMIA_SotoBosque", this.ceSitio) && chkSotobosque.isSelected()) {
                JOptionPane.showMessageDialog(null, "Si selecciona Sotobosque, se debe capturar", "Sotobosque", JOptionPane.INFORMATION_MESSAGE);
                chkSotobosque.requestFocus();
            } else if (funciones.validarSeccionCapturada("TAXONOMIA_SotoBosque", this.ceSitio) == false && chkSotobosque.isSelected()) {
                if (validarColectasObligatorias()) {
                    if (this.actualizar == 0) {
                        crearCoberturaSuelo();
                        this.cdSecuencia.updateSecuencia(this.ceSitio, FORMATO_ID, 1);
                        crearSotoBosqueFuera();
                        this.hide();
                        seleccionarSiguienteFormulario(this.ceSitio.getSecuencia());
                    } else {
                        actualizarCoberturaSuelo();
                        crearSotoBosqueFuera();
                        this.hide();
                        revisarSiguienteFormulario(this.ceSitio.getSecuencia());
                    }
                   // funciones.manipularBotonesMenuPrincipal(false);
                }
            } else if (funciones.validarSeccionCapturada("TAXONOMIA_SotoBosque", this.ceSitio) == true && !chkSotobosque.isSelected()) {
                if (this.actualizar == 0) {
                    crearCoberturaSuelo();
                    crearSotoBosqueFuera();
                    this.hide();
                    seleccionarSiguienteFormulario(this.ceSitio.getSecuencia());
                    this.cdSecuencia.updateSecuencia(this.ceSitio, FORMATO_ID, 1);
                } else {
                    actualizarCoberturaSuelo();
                    crearSotoBosqueFuera();
                    this.hide();
                    revisarSiguienteFormulario(this.ceSitio.getSecuencia());
                }
              /*  this.hide();
                UPMForms.repoblado.setDatosIniciales(this.ceSitio);
                UPMForms.repoblado.setVisible(true);*/
                
                //funciones.manipularBotonesMenuPrincipal(false);
            }
        }
    }//GEN-LAST:event_btnContinuarActionPerformed

    private void txtGramineasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGramineasFocusGained
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                 txtGramineas.selectAll();
            }
        });
        
    }//GEN-LAST:event_txtGramineasFocusGained

    private void txtHelechosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHelechosFocusGained
       SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                 txtHelechos.selectAll();
            }
        });
    }//GEN-LAST:event_txtHelechosFocusGained

    private void txtMusgosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMusgosFocusGained
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                 txtMusgos.selectAll();
            }
        });
    }//GEN-LAST:event_txtMusgosFocusGained

    private void txtLiquenesFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLiquenesFocusGained
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                 txtLiquenes.selectAll();
            }
        });
    }//GEN-LAST:event_txtLiquenesFocusGained

    private void txtHierbasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHierbasFocusGained
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                 txtHierbas.selectAll();
            }
        });
    }//GEN-LAST:event_txtHierbasFocusGained

    private void txtRocaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtRocaFocusGained
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                 txtRoca.selectAll();
            }
        });
    }//GEN-LAST:event_txtRocaFocusGained

    private void txtSueloDesnudoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSueloDesnudoFocusGained
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                 txtSueloDesnudo.selectAll();
            }
        });
    }//GEN-LAST:event_txtSueloDesnudoFocusGained

    private void txtHojarascaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHojarascaFocusGained
       SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                 txtHojarasca.selectAll();
            }
        });
    }//GEN-LAST:event_txtHojarascaFocusGained

    private void txtGravaPiedraFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGravaPiedraFocusGained
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                 txtGravaPiedra.selectAll();
            }
        });
    }//GEN-LAST:event_txtGravaPiedraFocusGained

    private void txtOtrosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtOtrosFocusGained
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                 txtOtros.selectAll();
            }
        });
    }//GEN-LAST:event_txtOtrosFocusGained

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        
        if(revision==false){//esta en modo de captura
            this.hide();
             //System.out.println("Modo Captura");
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

    private void txtGramineasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGramineasKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtGramineasKeyTyped

    private void txtHelechosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHelechosKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtHelechosKeyTyped

    private void txtMusgosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMusgosKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtMusgosKeyTyped

    private void txtLiquenesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLiquenesKeyTyped
       numeros.keyTyped(evt);
    }//GEN-LAST:event_txtLiquenesKeyTyped

    private void txtHierbasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHierbasKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtHierbasKeyTyped

    private void txtRocaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRocaKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtRocaKeyTyped

    private void txtSueloDesnudoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSueloDesnudoKeyTyped
       numeros.keyTyped(evt);
    }//GEN-LAST:event_txtSueloDesnudoKeyTyped

    private void txtHojarascaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHojarascaKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtHojarascaKeyTyped

    private void txtGravaPiedraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGravaPiedraKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtGravaPiedraKeyTyped

    private void txtOtrosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOtrosKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtOtrosKeyTyped

    private void txtGramineasFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGramineasFocusLost
        if(txtGramineas.getText().isEmpty()){
            txtGramineas.setValue(null);
        }     
    }//GEN-LAST:event_txtGramineasFocusLost

    private void txtHelechosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHelechosFocusLost
        if(txtHelechos.getText().isEmpty()){
            txtHelechos.setValue(null);
        }  
    }//GEN-LAST:event_txtHelechosFocusLost

    private void txtMusgosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMusgosFocusLost
        if(txtMusgos.getText().isEmpty()){
            txtMusgos.setValue(null);
        }  
    }//GEN-LAST:event_txtMusgosFocusLost

    private void txtLiquenesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLiquenesFocusLost
        if(txtLiquenes.getText().isEmpty()){
            txtLiquenes.setValue(null);
        }  
    }//GEN-LAST:event_txtLiquenesFocusLost

    private void txtHierbasFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHierbasFocusLost
        if(txtHierbas.getText().isEmpty()){
            txtHierbas.setValue(null);
        }  
    }//GEN-LAST:event_txtHierbasFocusLost

    private void txtRocaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtRocaFocusLost
        if(txtRoca.getText().isEmpty()){
            txtRoca.setValue(null);
        }  
    }//GEN-LAST:event_txtRocaFocusLost

    private void txtSueloDesnudoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSueloDesnudoFocusLost
        if(txtSueloDesnudo.getText().isEmpty()){
            txtSueloDesnudo.setValue(null);
        }  
    }//GEN-LAST:event_txtSueloDesnudoFocusLost

    private void txtHojarascaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHojarascaFocusLost
        if(txtHojarasca.getText().isEmpty()){
            txtHojarasca.setValue(null);
        }  
    }//GEN-LAST:event_txtHojarascaFocusLost

    private void txtGravaPiedraFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGravaPiedraFocusLost
        if(txtGravaPiedra.getText().isEmpty()){
            txtGravaPiedra.setValue(null);
        } 
    }//GEN-LAST:event_txtGravaPiedraFocusLost

    private void txtOtrosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtOtrosFocusLost
        if(txtOtros.getText().isEmpty()){
            txtOtros.setValue(null);
        } 
    }//GEN-LAST:event_txtOtrosFocusLost

    private void chkSotobosqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSotobosqueActionPerformed
        if (!chkSotobosque.isSelected() && this.cdSotobosque.validarTablaSotobosque(this.sitioID)) {
            Object[] opciones = {"Si", "No"};
            int respuesta = JOptionPane.showOptionDialog(null, "Si capturó, se eliminará la información de sotobosque del sitio, ¿Esta seguro?",
                    "Sotobosque", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[1]);
            if (respuesta == JOptionPane.YES_OPTION) {
                cdSotobosque.deleteSotobosqueSitio(this.sitioID);
                funciones.reiniciarTabla(this.grdSotobosque);
                llenarTabla();
                limpiarControles();
                cmbFamilia.setEnabled(false);
                cmbGenero.setEnabled(false);
                cmbEspecie.setEnabled(false);
                cmbInfraespecie.setEnabled(false);
                txtNombreComun.setEnabled(false);
                txtFrecuencia025.setEnabled(false);
                txtCobertura025.setEnabled(false);
                txtFrecuencia151.setEnabled(false);
                txtCobertura151.setEnabled(false);
                txtFrecuencia275.setEnabled(false);
                txtCobertura275.setEnabled(false);
                cmbDanio.setEnabled(false);
                txtPorcentajeDanio.setEnabled(false);
                cmbVigor.setEnabled(false);
                btnGuardar.setEnabled(false);
                btnModificar.setEnabled(false);
                btnEliminar.setEnabled(false);
            } else {
                chkSotobosque.setEnabled(true);
                cmbFamilia.requestFocus();
            }
        } else {
            cmbFamilia.setEnabled(true);
            cmbGenero.setEnabled(true);
            cmbEspecie.setEnabled(true);
            cmbInfraespecie.setEnabled(true);
            txtNombreComun.setEnabled(true);
            txtFrecuencia025.setEnabled(true);
            txtCobertura025.setEnabled(true);
            txtFrecuencia151.setEnabled(true);
            txtCobertura151.setEnabled(true);
            txtFrecuencia275.setEnabled(true);
            txtCobertura275.setEnabled(true);
            cmbDanio.setEnabled(true);
            txtPorcentajeDanio.setEnabled(true);
            cmbVigor.setEnabled(true);
            btnGuardar.setEnabled(true);
            btnModificar.setEnabled(true);
            btnEliminar.setEnabled(true);
        }
    }//GEN-LAST:event_chkSotobosqueActionPerformed

    private void cmbGeneroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbGeneroActionPerformed
        CatEGenero indexGenero = (CatEGenero) cmbGenero.getSelectedItem();
        DefaultComboBoxModel dcm = (DefaultComboBoxModel) cmbEspecie.getModel();
        dcm.removeAllElements();
        if (indexGenero != null) {
            fillCmbEspecie(indexGenero.getGeneroID());
        }
    }//GEN-LAST:event_cmbGeneroActionPerformed

    private void cmbEspecieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEspecieActionPerformed
        CatEEspecie indexEspecie = (CatEEspecie) cmbEspecie.getSelectedItem();
        DefaultComboBoxModel dcm = (DefaultComboBoxModel) cmbInfraespecie.getModel();
        dcm.removeAllElements();
        if (indexEspecie != null) {
            fillCmbInfraespecie(indexEspecie.getEspecieID());
        }
    }//GEN-LAST:event_cmbEspecieActionPerformed

    private void txtNombreComunFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombreComunFocusGained
        txtNombreComun.selectAll();
    }//GEN-LAST:event_txtNombreComunFocusGained

    private void cmbDanioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDanioActionPerformed
        CatEAgenteDanio danio = (CatEAgenteDanio) cmbDanio.getSelectedItem();

        if (danio != null) {
            if (danio.getAgenteDanioID() == 1) {
                txtPorcentajeDanio.setEnabled(false);
                txtPorcentajeDanio.setText("");
            } else {
                txtPorcentajeDanio.setEnabled(true);
            }
        } else {
            txtPorcentajeDanio.setEnabled(false);
            txtPorcentajeDanio.setText("");
        }
    }//GEN-LAST:event_cmbDanioActionPerformed

    private void txtFrecuencia025FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFrecuencia025FocusGained
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                txtFrecuencia025.selectAll();
            }
        });
    }//GEN-LAST:event_txtFrecuencia025FocusGained

    private void txtFrecuencia025FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFrecuencia025FocusLost
        if (txtFrecuencia025.getText().isEmpty()) {
            txtFrecuencia025.setValue(null);
        }
    }//GEN-LAST:event_txtFrecuencia025FocusLost

    private void txtFrecuencia025KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFrecuencia025KeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtFrecuencia025KeyTyped

    private void txtCobertura025FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCobertura025FocusGained
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                txtCobertura025.selectAll();
            }
        });
    }//GEN-LAST:event_txtCobertura025FocusGained

    private void txtCobertura025FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCobertura025FocusLost
        if (txtCobertura025.getText().isEmpty()) {
            txtCobertura025.setValue(null);
        }
    }//GEN-LAST:event_txtCobertura025FocusLost

    private void txtCobertura025KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCobertura025KeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtCobertura025KeyTyped

    private void txtFrecuencia151FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFrecuencia151FocusGained
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                txtFrecuencia151.selectAll();
            }
        });
    }//GEN-LAST:event_txtFrecuencia151FocusGained

    private void txtFrecuencia151FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFrecuencia151FocusLost
        if (txtFrecuencia151.getText().isEmpty()) {
            txtFrecuencia151.setValue(null);
        }
    }//GEN-LAST:event_txtFrecuencia151FocusLost

    private void txtFrecuencia151KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFrecuencia151KeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtFrecuencia151KeyTyped

    private void txtCobertura151FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCobertura151FocusGained
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                txtCobertura151.selectAll();
            }
        });
    }//GEN-LAST:event_txtCobertura151FocusGained

    private void txtCobertura151FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCobertura151FocusLost
        if (txtCobertura151.getText().isEmpty()) {
            txtCobertura151.setValue(null);
        }
    }//GEN-LAST:event_txtCobertura151FocusLost

    private void txtFrecuencia275FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFrecuencia275FocusGained
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                txtFrecuencia275.selectAll();
            }
        });
    }//GEN-LAST:event_txtFrecuencia275FocusGained

    private void txtFrecuencia275FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFrecuencia275FocusLost
        if (txtFrecuencia275.getText().isEmpty()) {
            txtFrecuencia275.setValue(null);
        }
    }//GEN-LAST:event_txtFrecuencia275FocusLost

    private void txtFrecuencia275KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFrecuencia275KeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtFrecuencia275KeyTyped

    private void txtCobertura275FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCobertura275FocusGained
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                txtCobertura275.selectAll();
            }
        });
    }//GEN-LAST:event_txtCobertura275FocusGained

    private void txtCobertura275FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCobertura275FocusLost
        if (txtCobertura275.getText().isEmpty()) {
            txtCobertura275.setValue(null);
        }
    }//GEN-LAST:event_txtCobertura275FocusLost

    private void txtCobertura275KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCobertura275KeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtCobertura275KeyTyped

    private void txtPorcentajeDanioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPorcentajeDanioFocusGained
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                txtPorcentajeDanio.selectAll();
            }
        });
    }//GEN-LAST:event_txtPorcentajeDanioFocusGained

    private void txtPorcentajeDanioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPorcentajeDanioFocusLost
        if (txtPorcentajeDanio.getText().isEmpty()) {
            txtPorcentajeDanio.setValue(null);
        }
    }//GEN-LAST:event_txtPorcentajeDanioFocusLost

    private void txtPorcentajeDanioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPorcentajeDanioKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtPorcentajeDanioKeyTyped

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        asignarDatosSotobosque();
        if (validarSotoBosqueVacios() && validarDatosSotoBosque()) {
            crearSotobosque();
            this.cdSotobosque.enumerarConsecutivo(this.sitioID);
            llenarTabla();
            limpiarControles();
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        asignarDatosSotobosque();
        if (validarSotoBosqueVacios() && validarDatosSotoBosque()) {
            actualizarSotoBosque();
            llenarTabla();
            limpiarControles();
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminarSotoBosque();
        this.cdSotobosque.enumerarConsecutivo(this.sitioID);
        llenarTabla();
        limpiarControles();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnColectaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnColectaActionPerformed
        String consecutivo = null;
        try {
            int fila = grdSotobosque.getSelectedRow();
            consecutivo = grdSotobosque.getValueAt(fila, 2).toString();
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
            claveColecta.setDatosIniciales(ceColecta, FORMATO_ID, "TAXONOMIA_SotoBosque", "Consecutivo", this.sitioID, Integer.parseInt(consecutivo));
            claveColecta.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla de sotobosque"
                + e.getClass().getName() + " : " + e.getMessage(), "Sotobosque", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnColectaActionPerformed

    private void grdSotobosqueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grdSotobosqueMouseClicked
        if (evt.getButton() == 1) {
            btnGuardar.setEnabled(true);
            int fila = grdSotobosque.getSelectedRow();
            String sotobosqueID = grdSotobosque.getValueAt(fila, 0).toString();
            this.sotoBosqueID = Integer.parseInt(sotobosqueID);
            CESotoBosque sb;
            sb = this.cdSotobosque.getRegistroSotobosque(Integer.parseInt(sotobosqueID));

            CatEFamiliaEspecie fam = new CatEFamiliaEspecie();
            fam.setFamiliaID(sb.getFamiliaID());
            cmbFamilia.setSelectedItem(fam);

            CatEGenero gen = new CatEGenero();
            gen.setGeneroID(sb.getGeneroID());
            cmbGenero.setSelectedItem(gen);

            /*CatEGenero gen = new CatEGenero();
            gen.setGeneroID(sb.getGeneroID());
            cmbGenero.removeAllItems();
            fillCmbGenero(sb.getFamiliaID());
            cmbGenero.setSelectedItem(gen);*/

            CatEEspecie esp = new CatEEspecie();
            esp.setEspecieID(sb.getEspecieID());
            cmbEspecie.removeAllItems();
            fillCmbEspecie(sb.getGeneroID());
            cmbEspecie.setSelectedItem(esp);

            CatEInfraespecie inf = new CatEInfraespecie();
            inf.setInfraespecieID(sb.getInfraespecieID());
            cmbInfraespecie.removeAllItems();
            fillCmbInfraespecie(sb.getEspecieID());
            cmbInfraespecie.setSelectedItem(inf);

            txtNombreComun.setText(sb.getNombreComun());

            txtFrecuencia025.setText(String.valueOf(sb.getFrecuecia025150()));
            if((txtFrecuencia025.getText()).equals("0")) {
                txtFrecuencia025.setText("");
            }
            txtCobertura025.setText(String.valueOf(sb.getCobertura025150()));
            if(txtCobertura025.getText().equals("0")) {
                txtCobertura025.setText("");
            }
            txtFrecuencia151.setText(String.valueOf(sb.getFrecuencia151275()));
            if(txtFrecuencia151.getText().equals("0")) {
                txtFrecuencia151.setText("");
            }
            txtCobertura151.setText(String.valueOf(sb.getCobertura151275()));
            if(txtCobertura151.getText().equals("0")){
                txtCobertura151.setText("");
            }
            txtFrecuencia275.setText(String.valueOf(sb.getFrecuencia275()));
            if(txtFrecuencia275.getText().equals("0")){
                txtFrecuencia275.setText("");
            }
            txtCobertura275.setText(String.valueOf(sb.getCobertura275()));
            if(txtCobertura275.getText().equals("0")){
                txtCobertura275.setText("");
            }
            CatETipoVigor vig = new CatETipoVigor();
            vig.setVigorID(sb.getVigorID());
            cmbVigor.setSelectedItem(vig);

            CatEAgenteDanio danio = new CatEAgenteDanio();
            danio.setAgenteDanioID(sb.getDanioID());
            cmbDanio.setSelectedItem(danio);

            txtPorcentajeDanio.setText(String.valueOf(sb.getPorcentajeDanio()));
            if(txtPorcentajeDanio.getText().equals("0")){
                txtPorcentajeDanio.setText("");
            }
            txtClaveColecta.setText(sb.getClaveColecta());
        }
    }//GEN-LAST:event_grdSotobosqueMouseClicked

    private void chkSotoBosqueFueraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSotoBosqueFueraActionPerformed
        if (chkSotoBosqueFuera.isSelected()) {
            txtPorcentajeSotobosqueFuera.setEnabled(true);
        } else {
            this.cdSitio.updateSotobosqueFuera(this.ceSitio);
            txtPorcentajeSotobosqueFuera.setEnabled(false);
            txtPorcentajeSotobosqueFuera.setText("");
            txtPorcentajeSotobosqueFuera.setValue(null);
        }
    }//GEN-LAST:event_chkSotoBosqueFueraActionPerformed

    private void chkSotoBosqueFueraPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_chkSotoBosqueFueraPropertyChange

    }//GEN-LAST:event_chkSotoBosqueFueraPropertyChange

    private void txtPorcentajeSotobosqueFueraFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPorcentajeSotobosqueFueraFocusGained
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                txtPorcentajeSotobosqueFuera.selectAll();
            }
        });
    }//GEN-LAST:event_txtPorcentajeSotobosqueFueraFocusGained

    private void txtCobertura151KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCobertura151KeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtCobertura151KeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PnlCoordenadas1;
    private javax.swing.JPanel PnlCoordenadas2;
    private javax.swing.JPanel PnlCoordenadas3;
    private javax.swing.JPanel PnlDescripcion;
    private javax.swing.JPanel PnlDescripcion1;
    private javax.swing.JButton btnColecta;
    private javax.swing.JButton btnContinuar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JCheckBox chkSotoBosqueFuera;
    private javax.swing.JCheckBox chkSotobosque;
    private javax.swing.JComboBox cmbDanio;
    private javax.swing.JComboBox cmbEspecie;
    private javax.swing.JComboBox cmbFamilia;
    private javax.swing.JComboBox cmbGenero;
    private javax.swing.JComboBox cmbInfraespecie;
    private javax.swing.JComboBox cmbVigor;
    private javax.swing.JTable grdSotobosque;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl025;
    private javax.swing.JLabel lbl151;
    private javax.swing.JLabel lbl275;
    private javax.swing.JLabel lblClaveColecta;
    private javax.swing.JLabel lblCobertura025;
    private javax.swing.JLabel lblCobertura151;
    private javax.swing.JLabel lblCobertura275;
    private javax.swing.JLabel lblCoberturaSuelo;
    private javax.swing.JLabel lblCoberturaVegetacionMenor;
    private javax.swing.JLabel lblDanio;
    private javax.swing.JLabel lblEspecie;
    private javax.swing.JLabel lblFamilia;
    private javax.swing.JLabel lblFrecuencia025;
    private javax.swing.JLabel lblFrecuencia151;
    private javax.swing.JLabel lblFrecuencia275;
    private javax.swing.JLabel lblGenero;
    private javax.swing.JLabel lblGramineas;
    private javax.swing.JLabel lblGravaPiedra;
    private javax.swing.JLabel lblHelechos;
    private javax.swing.JLabel lblHierbas;
    private javax.swing.JLabel lblHojasrasca;
    private javax.swing.JLabel lblInfraespecie;
    private javax.swing.JLabel lblLiquenes;
    private javax.swing.JLabel lblMusgos;
    private javax.swing.JLabel lblNombreComun;
    private javax.swing.JLabel lblOtros;
    private javax.swing.JLabel lblPorcentajeCobertura;
    private javax.swing.JLabel lblPorcentajeDanio;
    private javax.swing.JLabel lblRegistroSotobosque;
    private javax.swing.JLabel lblRoca;
    private javax.swing.JLabel lblSitio;
    private javax.swing.JLabel lblSueloDesnudo;
    private javax.swing.JLabel lblUPM;
    private javax.swing.JLabel lblVegetacionMenor;
    private javax.swing.JLabel lblVegetacionMenorCobertura;
    private javax.swing.JLabel lblVigor;
    private javax.swing.JPanel pnlSotobosque;
    public javax.swing.JTextField txtClaveColecta;
    private javax.swing.JFormattedTextField txtCobertura025;
    private javax.swing.JFormattedTextField txtCobertura151;
    private javax.swing.JFormattedTextField txtCobertura275;
    private javax.swing.JFormattedTextField txtFrecuencia025;
    private javax.swing.JFormattedTextField txtFrecuencia151;
    private javax.swing.JFormattedTextField txtFrecuencia275;
    private javax.swing.JFormattedTextField txtGramineas;
    private javax.swing.JFormattedTextField txtGravaPiedra;
    private javax.swing.JFormattedTextField txtHelechos;
    private javax.swing.JFormattedTextField txtHierbas;
    private javax.swing.JFormattedTextField txtHojarasca;
    private javax.swing.JFormattedTextField txtLiquenes;
    private javax.swing.JFormattedTextField txtMusgos;
    private javax.swing.JTextField txtNombreComun;
    private javax.swing.JFormattedTextField txtOtros;
    private javax.swing.JFormattedTextField txtPorcentajeDanio;
    private javax.swing.JFormattedTextField txtPorcentajeSotobosqueFuera;
    private javax.swing.JFormattedTextField txtRoca;
    private javax.swing.JTextField txtSitio;
    private javax.swing.JFormattedTextField txtSueloDesnudo;
    private javax.swing.JTextField txtUPM;
    // End of variables declaration//GEN-END:variables
}
