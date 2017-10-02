package gob.conafor.taxonomia.vie;

import gob.conafor.ini.vie.Main;
import gob.conafor.sitio.mod.CDCoberturaSuelo;
import gob.conafor.sitio.mod.CDSitio;
import gob.conafor.sitio.mod.CECoberturaSuelo;
import gob.conafor.sitio.mod.CESitio;
import gob.conafor.sys.mod.CDSecuencia;
import gob.conafor.sys.mod.CDSeguimientoUPM;
import gob.conafor.sys.mod.CESeccionesCapturadas;
import gob.conafor.taxonomia.mod.CDColectaBotanica;
import gob.conafor.taxonomia.mod.CDCondicionTaxonomica;
import gob.conafor.taxonomia.mod.CDDanioRepobladoVM;
import gob.conafor.taxonomia.mod.CDEspecies;
import gob.conafor.taxonomia.mod.CDRepobladoVM;
import gob.conafor.taxonomia.mod.CEColectaBotanica;
import gob.conafor.taxonomia.mod.CEDanioSeveridad;
import gob.conafor.taxonomia.mod.CERepobladoVM;
import gob.conafor.taxonomia.mod.CatEAgenteDanio;
import gob.conafor.taxonomia.mod.CatEEspecie;
import gob.conafor.taxonomia.mod.CatEFamiliaEspecie;
import gob.conafor.taxonomia.mod.CatEFormaVidaRepobladoVM;
import gob.conafor.taxonomia.mod.CatEGenero;
import gob.conafor.taxonomia.mod.CatEInfraespecie;
import gob.conafor.taxonomia.mod.CatEPorcentajeArbolado;
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

public class FrmRepobladoVM extends javax.swing.JInternalFrame {
    private boolean revision;
    private int sitio;
    private int repobladoVMID;
    private int sitioID;
    private int upmID;
    private static final int FORMATO_ID = 19;
    private final int cubiertaSuelo;
    private final int repobladoVM;
    private final int arboladoG;
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
    private CDEspecies especie = new CDEspecies();
    private CDCondicionTaxonomica condicion = new CDCondicionTaxonomica();
    private ValidacionesComunes validacion = new ValidacionesComunes();
    private CDRepobladoVM cdRepoblado = new CDRepobladoVM();
    private Integer frecuencia50 = null;
    private Integer porcentajeCobertura50 = null;
    private Integer frecuencia51200 = null;
    private Integer porcentajeCobertura51200 = null;
    private Integer frecuencia200 = null;
    private Integer porcentajeCobertura200 = null;
    private Integer repobladoFuera;
    private Integer porcentajeRepobladoFuera;
    private CESitio ceSitio = new CESitio();
    private CESeccionesCapturadas seccionActual = new CESeccionesCapturadas();
    private CESeccionesCapturadas seccionSiguiente = new CESeccionesCapturadas();
    private CDSeguimientoUPM seguimiento = new CDSeguimientoUPM();
    private FuncionesComunes combo = new FuncionesComunes();
    private Datos numeros = new Datos();
    private CDEspecies cdEspecies = new CDEspecies();
    private CDSecuencia cdSecuencia = new CDSecuencia();
    private CDSitio cdSitio = new CDSitio();
    private CDCoberturaSuelo cdCobertura = new CDCoberturaSuelo();
    private CECoberturaSuelo ceCobertura = new CECoberturaSuelo();
    private int modificar;
    private FuncionesComunes funciones = new FuncionesComunes();
    private Version ver=new Version();
    private String version=ver.getVersion();

    public FrmRepobladoVM() {
        initComponents();
        this.cubiertaSuelo = 7;
        this.repobladoVM = 29;
        this.arboladoG = 30;
        fillCmbFormaVida();
        fillCmbFamilia();
        fillCmbGenero();
        fillCmbAgenteDanio1();
        fillCmbAgenteDanio2();
        fillCmbVigor();
        txtGramineas.requestFocus();
    }
    
    public void setDatosIniciales(CESitio ceSitio) {
        this.upmID = ceSitio.getUpmID();
        this.sitioID = ceSitio.getSitioID();
        this.sitio = ceSitio.getSitio();
        txtUPM.setText(String.valueOf(this.upmID));
        txtSitio.setText(String.valueOf(this.sitio));
        this.ceSitio.setSitioID(this.sitioID);
        this.ceSitio.setUpmID(this.upmID);
        this.ceSitio.setSitio(this.sitio);
        this.ceSitio = ceSitio;
        llenarTabla();
        limpiarControles();
        limpiarControlesCubiertaSuelo();
        limpiarRepobladoFuera();
        this.modificar = 0;
        funciones.manipularBotonesMenuPrincipal(true);
    }

    public void revisarRepobladoVM(CESitio ceSitio) {
        revision=true;
        limpiarControles();
        CatEAgenteDanio agenteDanio1 = (CatEAgenteDanio) cmbAgenteDanio1.getSelectedItem();
        this.sitio = ceSitio.getSitio();
        this.sitioID = ceSitio.getSitioID();
        this.upmID = ceSitio.getUpmID();
        txtSitio.setText(String.valueOf(this.sitio));
        txtUPM.setText(String.valueOf(this.upmID));
        this.ceSitio.setSitioID(this.sitioID);
        this.ceSitio.setUpmID(this.upmID);
        this.ceSitio.setSitio(this.sitio);
        llenarTabla();
        this.ceCobertura = this.cdCobertura.getDatosCoberturaSitio(this.sitioID);
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
        CESitio ceRepobladoFuera;
        ceRepobladoFuera = this.cdSitio.getRepobladoFuera(this.sitioID);
        if (ceRepobladoFuera.getRepobladoFuera() == 1) {
            chkRepobladoFuera.setSelected(true);
            txtPorcentajeRepobladoFuera.setText(String.valueOf(ceRepobladoFuera.getPorcentajeRepoblado()));
            txtPorcentajeRepobladoFuera.setEnabled(true);
        } else {
            chkRepobladoFuera.setSelected(false);
        }
        this.modificar = 1;
        funciones.manipularBotonesMenuPrincipal(true);
        this.chkRepobladoVM.setSelected(funciones.habilitarCheckBox("TAXONOMIA_RepobladoVM", this.sitioID));
        
        boolean selecc=chkRepobladoVM.isSelected();
        //System.out.println(selecc);
        habilitarControles(selecc);
    }
    
    public void fillCmbFormaVida() {
        List<CatEFormaVidaRepobladoVM> listFormaVida = new ArrayList<>();
        listFormaVida = condicion.getFormaVidaRepobladoVM();

        if (listFormaVida != null) {
            int size = listFormaVida.size();
            for (int i = 0; i < size; i++) {
                cmbFormaVida.addItem(listFormaVida.get(i));
            }
        }
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

    public void fillCmbAgenteDanio1() {
        List<CatEAgenteDanio> listDanio = new ArrayList<>();
        listDanio = condicion.getAgenteDanio();
        if (listDanio != null) {
            int size = listDanio.size();
            for (int i = 0; i < size; i++) {
                cmbAgenteDanio1.addItem(listDanio.get(i));
            }
        }
    }

    public void fillCmbAgenteDanio2() {
        List<CatEAgenteDanio> listDanio = new ArrayList<>();
        listDanio = condicion.getAgenteDanio();
        if (listDanio != null) {
            int size = listDanio.size();
            for (int i = 0; i < size; i++) {
                cmbAgenteDanio2.addItem(listDanio.get(i));
            }
        }
    }

    public void fillCmbSeveridad1(int agente) {
        List<CatEPorcentajeArbolado> listSeveridad = new ArrayList<>();
        if (agente == 21 || agente == 33) {
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
         if(agente == 21 || agente == 33){
             combo.reiniciarComboModel(cmbSeveridad2);
            listSeveridad = condicion.getPorcentajeArboladoCopa();
        } else if(agente == 22){
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
        grdRepobladoVM.setModel(cdRepoblado.getTablaRepobladoVM(this.sitioID));

        grdRepobladoVM.getColumnModel().getColumn(2).setPreferredWidth(60);//Consecutivo
        grdRepobladoVM.getColumnModel().getColumn(3).setPreferredWidth(120);//Forma de vida
        grdRepobladoVM.getColumnModel().getColumn(4).setPreferredWidth(120);//Familia
        grdRepobladoVM.getColumnModel().getColumn(5).setPreferredWidth(120);//Genero
        grdRepobladoVM.getColumnModel().getColumn(6).setPreferredWidth(120);//Especie
        grdRepobladoVM.getColumnModel().getColumn(7).setPreferredWidth(100);//Infraespecie
        grdRepobladoVM.getColumnModel().getColumn(8).setPreferredWidth(100);//Nombre comun
        grdRepobladoVM.getColumnModel().getColumn(9).setPreferredWidth(60);//Frecuencia 50
        grdRepobladoVM.getColumnModel().getColumn(10).setPreferredWidth(70);//Porcentaje cobertura 50
        grdRepobladoVM.getColumnModel().getColumn(11).setPreferredWidth(60);//Frecuencia 51 200 
        grdRepobladoVM.getColumnModel().getColumn(12).setPreferredWidth(70);//Porcentaje cobertura 51 200
        grdRepobladoVM.getColumnModel().getColumn(13).setPreferredWidth(60);//Frecuencia 200
        grdRepobladoVM.getColumnModel().getColumn(14).setPreferredWidth(70);//Porcentaje cobertura 200
        grdRepobladoVM.getColumnModel().getColumn(15).setPreferredWidth(60);//Agente 1
        grdRepobladoVM.getColumnModel().getColumn(16).setPreferredWidth(70);//Severidad 1
        grdRepobladoVM.getColumnModel().getColumn(17).setPreferredWidth(60);//Agente 2
        grdRepobladoVM.getColumnModel().getColumn(18).setPreferredWidth(70);//Severidad 2
        grdRepobladoVM.getColumnModel().getColumn(19).setPreferredWidth(120);//Vigor
        grdRepobladoVM.getColumnModel().getColumn(20).setPreferredWidth(180);//Clave colecta
      
        Tablas tabla = new Tablas();
        int[] columna_0 = {0};
        int[] column_1 = {1};
        //Ocultar campo ID
        tabla.hideColumnTable(grdRepobladoVM, columna_0);
        tabla.hideColumnTable(grdRepobladoVM, column_1);
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
        } else {
            return false;
        }
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
        this.ceCobertura.setSitioID(this.sitioID);
        this.ceCobertura.setGramineas(this.gramineas);
        this.ceCobertura.setHelechos(this.helechos);
        this.ceCobertura.setMusgos(this.musgos);
        this.ceCobertura.setLiquenes(this.liquenes);
        this.ceCobertura.setHierbas(this.hierbas);
        this.ceCobertura.setRoca(this.rocas);
        this.ceCobertura.setSueloDesnudo(this.sueloDesnudo);
        this.ceCobertura.setHojarasca(this.hojarasca);
        this.ceCobertura.setGrava(this.gravaPiedra);
        this.ceCobertura.setOtros(this.otros);
        this.cdCobertura.updateCoberturaSuelo(ceCobertura);
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
            JOptionPane.showMessageDialog(null, "Debe introducir un valor para Gramineas", "Vegetación menor", JOptionPane.INFORMATION_MESSAGE);
            txtGramineas.requestFocus();
            return false;
        } else if (txtHelechos.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe introducir un valor para Helechos", "Vegetación menor", JOptionPane.INFORMATION_MESSAGE);
            txtHelechos.requestFocus();
            return false;
        } else if (txtMusgos.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe introducir un valor para Musgos", "Vegetación menor", JOptionPane.INFORMATION_MESSAGE);
            txtMusgos.requestFocus();
            return false;
        } else if (txtLiquenes.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe introducir un valor para Líquenes", "Vegetación menor", JOptionPane.INFORMATION_MESSAGE);
            txtLiquenes.requestFocus();
            return false;
        } else if (txtHierbas.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe introducir un valor para Hierbas", "Vegetación menor", JOptionPane.INFORMATION_MESSAGE);
            txtHierbas.requestFocus();
            return false;
        } else if (txtRoca.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe introducir un valor para Roca", "Cubierta de suelo", JOptionPane.INFORMATION_MESSAGE);
            txtRoca.requestFocus();
            return false;
        } else if (txtSueloDesnudo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe introducir un valor para Suelo desnudo", "Cubierta de suelo", JOptionPane.INFORMATION_MESSAGE);
            txtSueloDesnudo.requestFocus();
            return false;
        } else if (txtHojarasca.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe introducir un valor para Hojarasca", "Cubierta de suelo", JOptionPane.INFORMATION_MESSAGE);
            txtHojarasca.requestFocus();
            return false;
        } else if (txtGravaPiedra.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe introducir un valor para Grava piedra", "Cubierta de suelo", JOptionPane.INFORMATION_MESSAGE);
            txtGravaPiedra.requestFocus();
            return false;
        } else if (txtOtros.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe introducir un valor para Otros", "Cubierta de suelo", JOptionPane.INFORMATION_MESSAGE);
            txtOtros.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    public boolean validarSumaCubiertaSuelo() {
        int suma = this.rocas + this.sueloDesnudo + this.hojarasca + this.gravaPiedra + this.otros;
        if (suma != 100) {
            JOptionPane.showMessageDialog(null, "Los porcentajes de cubierta del suelo deben sumar 100 " , "Vegetación menor", JOptionPane.INFORMATION_MESSAGE);
            txtRoca.requestFocus();
            return false;
        }
        return true;
    }

    private boolean validarRepobladoVacios() {
        if (cmbFormaVida.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar una forma de vida", "Repoblado VM", JOptionPane.INFORMATION_MESSAGE);
            cmbFormaVida.requestFocus();
            return false;
        } else if (!txtFrecuencia50.getText().isEmpty() && txtPorcentajeCobertura50.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe de capturar porcenta de cobertura <=50 ", "Repoblado VM", JOptionPane.INFORMATION_MESSAGE);
            txtPorcentajeCobertura50.requestFocus();
            return false;
        } else if (!txtFrecuencia51200.getText().isEmpty() && txtPorcentajeCobertura51200.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe de capturar porcenta de cobertura 51-200 cm ", "Repoblado VM", JOptionPane.INFORMATION_MESSAGE);
            txtPorcentajeCobertura51200.requestFocus();
            return false;
        } else if (!txtFrecuencia200.getText().isEmpty() && txtPorcentajeCobertura200.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe de capturar porcenta de cobertura >= 200 ", "Repoblado VM", JOptionPane.INFORMATION_MESSAGE);
            txtPorcentajeCobertura200.requestFocus();
            return false;
        } else if (cmbVigor.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar un vigor", "Repoblado VM", JOptionPane.INFORMATION_MESSAGE);
            cmbVigor.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    public boolean validarRepobladoFuera() {
        if (chkRepobladoFuera.isSelected() && txtPorcentajeRepobladoFuera.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Si selecciona repoblado fuera de introducir un porcentaje", "Repoblado fuera", JOptionPane.INFORMATION_MESSAGE);
            txtPorcentajeRepobladoFuera.requestFocus();
            return false;
        } else {
            return true;
        }
    }
    

    public void asignarDatosRepoblado() {
        try {
            this.frecuencia50 = Integer.valueOf(txtFrecuencia50.getText());
        } catch (NumberFormatException e) {
            this.frecuencia50 = null;
        }
        try {
            this.porcentajeCobertura50 = Integer.valueOf(txtPorcentajeCobertura50.getText());
        } catch (NumberFormatException e) {
            this.porcentajeCobertura50 = null;
        }
        try {
            this.frecuencia51200 = Integer.valueOf(txtFrecuencia51200.getText());
        } catch (NumberFormatException e) {
            this.frecuencia51200 = null;
        }
        try {
            this.porcentajeCobertura51200 = Integer.valueOf(txtPorcentajeCobertura51200.getText());
        } catch (NumberFormatException e) {
            this.porcentajeCobertura51200 = null;
        }
        try {
            this.frecuencia200 = Integer.valueOf(txtFrecuencia200.getText());
        } catch (NumberFormatException e) {
            this.frecuencia200 = null;
        }
        try {
            this.porcentajeCobertura200 = Integer.valueOf(txtPorcentajeCobertura200.getText());
        } catch (NumberFormatException e) {
            this.porcentajeCobertura200 = null;
        }
    }

    private void asignarDatosRepobladoFuera() {
        if (chkRepobladoFuera.isSelected()) {
            this.repobladoFuera = 1;
        } else {
            this.repobladoFuera = 0;
        }
        try {
            this.porcentajeRepobladoFuera = Integer.valueOf(txtPorcentajeRepobladoFuera.getText());
        } catch (NumberFormatException e) {
            this.porcentajeRepobladoFuera = null;
        }
    }

    public boolean validarDatosRepoblado() {
        if (this.frecuencia50 != null && this.frecuencia50 < 1) {
            JOptionPane.showMessageDialog(null, "Error! Debe de capturar un valos mayor a 0 en frecuencia <= 50 cm ", "Repoblado VM", JOptionPane.INFORMATION_MESSAGE);
            txtFrecuencia50.requestFocus();
            return false;
        } else if (this.porcentajeCobertura50 != null && this.porcentajeCobertura50 < 1) {
            JOptionPane.showMessageDialog(null, "Error! Debe de capturar un valos mayor a 0 en porcentaje cobertura <= 50 cm ", "Repoblado VM", JOptionPane.INFORMATION_MESSAGE);
            txtPorcentajeCobertura50.requestFocus();
            return false;
        } else if (this.frecuencia51200 != null && this.frecuencia51200 < 1) {
            JOptionPane.showMessageDialog(null, "Error! Debe de capturar un valos mayor a 0 en cobertura 51-200 cm ", "Repoblado VM", JOptionPane.INFORMATION_MESSAGE);
            txtFrecuencia51200.requestFocus();
            return false;
        } else if (this.porcentajeCobertura51200 != null && this.porcentajeCobertura51200 < 1) {
            JOptionPane.showMessageDialog(null, "Error! Debe de capturar un valos mayor a 0 en porcentaje cobertura 51-200 cm ", "Repoblado VM", JOptionPane.INFORMATION_MESSAGE);
            txtPorcentajeCobertura51200.requestFocus();
            return false;
        } else if (this.frecuencia200 != null && this.frecuencia200 < 1) {
            JOptionPane.showMessageDialog(null, "Error! Debe de capturar un valos mayor a 0 en frecuencia >= 200 ", "Repoblado VM", JOptionPane.INFORMATION_MESSAGE);
            txtFrecuencia200.requestFocus();
            return false;
        } else if (this.porcentajeCobertura200 != null && this.frecuencia200 < 1) {
            JOptionPane.showMessageDialog(null, "Error! Debe de capturar un valos mayor a 0 en porcentaje cobertura >= 200 ", "Repoblado VM", JOptionPane.INFORMATION_MESSAGE);
            txtPorcentajeCobertura200.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private void crearRepobladoVM() {
        CatEFormaVidaRepobladoVM indexFormaVida = (CatEFormaVidaRepobladoVM) cmbFormaVida.getSelectedItem();
        CatEFamiliaEspecie indexFamilia = (CatEFamiliaEspecie) cmbFamilia.getSelectedItem();
        CatEGenero indexGenero = (CatEGenero) cmbGenero.getSelectedItem();
        CatEEspecie indexEspecie = (CatEEspecie) cmbEspecie.getSelectedItem();
        CatEInfraespecie indexInfraespecie = (CatEInfraespecie) cmbInfraespecie.getSelectedItem();
        CatETipoVigor vigor = (CatETipoVigor) cmbVigor.getSelectedItem();
        CDRepobladoVM cdRepoblado = new CDRepobladoVM();
        CERepobladoVM ceRepoblado = new CERepobladoVM();

        ceRepoblado.setSitioID(this.sitioID);
        if (indexFormaVida != null) {
            ceRepoblado.setFormaVidaID(indexFormaVida.getFormaVidaRepobladoVMID());
        }
        if (indexFamilia != null) {
            ceRepoblado.setFamiliaID(indexFamilia.getFamiliaID());
        }
        if (indexGenero != null) {
            ceRepoblado.setGeneroID(indexGenero.getGeneroID());
        }
        if (indexEspecie != null) {
            ceRepoblado.setEspecieID(indexEspecie.getEspecieID());
        }
        if (indexInfraespecie != null) {
            ceRepoblado.setInfraespecieID(indexInfraespecie.getInfraespecieID());
        }
        ceRepoblado.setNombreComun(txtNombreComun.getText());
        ceRepoblado.setFrecuencia50(this.frecuencia50);
        ceRepoblado.setPorcentajeCobertura50(this.porcentajeCobertura50);
        ceRepoblado.setFrecuencia51200(this.frecuencia51200);
        ceRepoblado.setPorcentajeCobertura51200(this.porcentajeCobertura51200);
        ceRepoblado.setFrecuencia200(this.frecuencia200);
        ceRepoblado.setPorcentajeCobertura200(this.porcentajeCobertura200);
        ceRepoblado.setVigorID(vigor.getVigorID());

        cdRepoblado.insertRepobladoVM(ceRepoblado);
        this.repobladoVMID = cdRepoblado.getLastRepobladoVMID();
        crearDanios(this.repobladoVMID);
    }

    private void crearDanios(int repobladoVMID) {
        System.out.println(repobladoVMID);
        CEDanioSeveridad ceDanio1 = new CEDanioSeveridad();
        CEDanioSeveridad ceDanio2 = new CEDanioSeveridad();
        CDDanioRepobladoVM cdDanio = new CDDanioRepobladoVM();
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

        ceDanio1.setSeccionID(repobladoVMID);
        ceDanio1.setNumeroDanio(1);
        ceDanio1.setAgenteDanioID(danio1);
        ceDanio1.setSeveridadID(severidad1);

        cdDanio.insertDanio(ceDanio1);

        ceDanio2.setSeccionID(repobladoVMID);
        ceDanio2.setNumeroDanio(2);
        ceDanio2.setAgenteDanioID(danio2);
        ceDanio2.setSeveridadID(severidad2);

        cdDanio.insertDanio(ceDanio2);
    }

    private void actualizarRepoblado() {
        try {
            int fila = grdRepobladoVM.getSelectedRow();
            String registro = grdRepobladoVM.getValueAt(fila, 0).toString();
            
            CatEFormaVidaRepobladoVM indexFormaVida = (CatEFormaVidaRepobladoVM) cmbFormaVida.getSelectedItem();
            CatEFamiliaEspecie familia = (CatEFamiliaEspecie) cmbFamilia.getSelectedItem();
            CatEGenero generoIndex = (CatEGenero) cmbGenero.getSelectedItem();
            CatEEspecie especieIndex = (CatEEspecie) cmbEspecie.getSelectedItem();
            CatEInfraespecie indexInfraespecie = (CatEInfraespecie) cmbInfraespecie.getSelectedItem();
            CatETipoVigor vigor = (CatETipoVigor) cmbVigor.getSelectedItem();
            CEDanioSeveridad ceDanio1 = new CEDanioSeveridad();
            CEDanioSeveridad ceDanio2 = new CEDanioSeveridad();
            CDDanioRepobladoVM cdDanio = new CDDanioRepobladoVM();
            CatEAgenteDanio indexAgenteDanio1 = (CatEAgenteDanio) cmbAgenteDanio1.getSelectedItem();
            CatEAgenteDanio indexAgenteDanio2 = (CatEAgenteDanio) cmbAgenteDanio2.getSelectedItem();
            CatEPorcentajeArbolado indexSeveridad1 = (CatEPorcentajeArbolado) cmbSeveridad1.getSelectedItem();
            CatEPorcentajeArbolado indexSeveridad2 = (CatEPorcentajeArbolado) cmbSeveridad2.getSelectedItem();
            CERepobladoVM ceRepobladoVM = new CERepobladoVM();
            ceRepobladoVM.setRepobladoVMID(Integer.parseInt(registro));
            ceRepobladoVM.setSitioID(this.sitioID);
            ceRepobladoVM.setFormaVidaID(indexFormaVida.getFormaVidaRepobladoVMID());
            if (familia != null) {
                ceRepobladoVM.setFamiliaID(familia.getFamiliaID());
            } else {
                ceRepobladoVM.setFamiliaID(null);
            }
            if (generoIndex != null) {
                ceRepobladoVM.setGeneroID(generoIndex.getGeneroID());
            } else {
                ceRepobladoVM.setGeneroID(null);
            }
            if (especieIndex != null) {
                ceRepobladoVM.setEspecieID(especieIndex.getEspecieID());
            } else {
                ceRepobladoVM.setEspecieID(null);
            }
            if (indexInfraespecie != null) {
                ceRepobladoVM.setInfraespecieID(indexInfraespecie.getInfraespecieID());
            } else {
                ceRepobladoVM.setInfraespecieID(null);
            }
            ceRepobladoVM.setNombreComun(txtNombreComun.getText());
            ceRepobladoVM.setFrecuencia50(this.frecuencia50);
            ceRepobladoVM.setPorcentajeCobertura50(this.porcentajeCobertura50);
            ceRepobladoVM.setFrecuencia51200(this.frecuencia51200);
            ceRepobladoVM.setPorcentajeCobertura51200(this.porcentajeCobertura51200);
            ceRepobladoVM.setFrecuencia200(this.frecuencia200);
            ceRepobladoVM.setPorcentajeCobertura200(this.porcentajeCobertura200);
            ceRepobladoVM.setVigorID(vigor.getVigorID());
            
            ceDanio1.setSeccionID(Integer.parseInt(registro));
            ceDanio1.setNumeroDanio(1);
            ceDanio1.setAgenteDanioID(indexAgenteDanio1.getAgenteDanioID());
            if(indexSeveridad1 != null){
                ceDanio1.setSeveridadID(indexSeveridad1.getPorcentajeArboladoID());
            }
            
            cdDanio.updateDanio(ceDanio1);
            
            ceDanio2.setSeccionID(Integer.parseInt(registro));
            ceDanio2.setNumeroDanio(2);
            ceDanio2.setAgenteDanioID(indexAgenteDanio2.getAgenteDanioID());
            if(indexSeveridad2 != null){
                ceDanio2.setSeveridadID(indexSeveridad2.getPorcentajeArboladoID());
            }
            cdDanio.updateDanio(ceDanio2);
            
            cdRepoblado.updateRepobladoVM(ceRepobladoVM);
            
            limpiarControles();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla de repoblado vegetación menor "
                    + e.getClass().getName() + " : " + e.getMessage(), "Repoblado", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void eliminarRepoblado() {
        try {
            int fila = grdRepobladoVM.getSelectedRow();
            String registro = grdRepobladoVM.getValueAt(fila, 0).toString();
            CDDanioRepobladoVM cdDanio = new CDDanioRepobladoVM();
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Esta seguro de borrar el registro de repoblado vegetación menor?",
                    "Repoblado", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (respuesta == JOptionPane.YES_OPTION) {
                this.cdRepoblado.deleteRepobladoVM(Integer.parseInt(registro));
                cdDanio.deleteDanio(Integer.parseInt(registro));
                limpiarControles();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla de repoblado vegetación menor "
                    + "", "Repoblado", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void limpiarControles() {
        cmbFormaVida.setSelectedItem(null);
        cmbFamilia.setSelectedItem(null);
        cmbGenero.setSelectedItem(null);
        cmbEspecie.setSelectedItem(null);
        cmbInfraespecie.setSelectedItem(null);
        txtNombreComun.setText("");
        txtFrecuencia50.setValue(null);
        txtFrecuencia50.setText("");
        txtPorcentajeCobertura50.setValue(null);
        txtPorcentajeCobertura50.setText("");
        txtFrecuencia51200.setValue(null);
        txtFrecuencia51200.setText("");
        txtPorcentajeCobertura51200.setValue(null);
        txtPorcentajeCobertura51200.setText("");
        txtFrecuencia200.setValue(null);
        txtFrecuencia200.setText("");
        txtPorcentajeCobertura200.setValue(null);
        txtPorcentajeCobertura200.setText("");
        cmbAgenteDanio1.setSelectedIndex(0);
        cmbSeveridad1.setSelectedItem(null);
        cmbAgenteDanio2.setSelectedIndex(0);
        cmbSeveridad2.setSelectedItem(null);
        cmbVigor.setSelectedItem(null);
        txtClaveColecta.setText("");
    }
    
    private void limpiarRepobladoFuera(){
        chkRepobladoFuera.setSelected(true);
        txtPorcentajeRepobladoFuera.setValue(null);
        txtPorcentajeRepobladoFuera.setText("");
    }
    
    public void limpiarControlesCubiertaSuelo() {
        txtGramineas.setValue(null);
        txtGramineas.setText("");
        txtHelechos.setValue(null);
        txtHelechos.setText("");
        txtMusgos.setValue(null);
        txtMusgos.setText("");
        txtLiquenes.setValue(null);
        txtLiquenes.setText("");
        txtHierbas.setValue(null);
        txtHierbas.setText("");
        txtRoca.setValue(null);
        txtRoca.setText("");
        txtSueloDesnudo.setValue(null);
        txtSueloDesnudo.setText("");
        txtHojarasca.setValue(null);
        txtHojarasca.setText("");
        txtGravaPiedra.setValue(null);
        txtGravaPiedra.setText("");
        txtOtros.setValue(null);
        txtOtros.setText("");
    }
    
    private boolean validarSeveridadDanio(){
        if(cmbSeveridad1.isEnabled() && cmbSeveridad1.getSelectedItem() == null){
            JOptionPane.showMessageDialog(null, "Error! Debe de seleccionar un nivel de severidad 1 ", "Repoblado VM", JOptionPane.INFORMATION_MESSAGE);
            cmbSeveridad1.requestFocus();
            return false;
        } else if(cmbSeveridad2.isEnabled() && cmbSeveridad2.getSelectedItem() == null){
            JOptionPane.showMessageDialog(null, "Error! Debe de seleccionar un nivel de severidad 2 ", "Repoblado VM", JOptionPane.INFORMATION_MESSAGE);
            cmbSeveridad2.requestFocus();
            return false;
        } else {
            return true;
        }
    }
     private boolean validarColectasObligatorias() {
        CDColectaBotanica colecta = new CDColectaBotanica();
        if (colecta.validarCapturaGenero("TAXONOMIA_RepobladoVM", this.sitioID)) {
            JOptionPane.showMessageDialog(null, "Error! Faltan por asignar claves de colecta", "Repoblado VM", JOptionPane.INFORMATION_MESSAGE);
            return false;
        } else {
            return true;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPrincipal = new javax.swing.JPanel();
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
        lblRegistroRepobladoVegetacionMenor = new javax.swing.JLabel();
        btnContinuar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        chkRepobladoVM = new javax.swing.JCheckBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        grdRepobladoVM = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        chkRepobladoFuera = new javax.swing.JCheckBox();
        txtPorcentajeRepobladoFuera = new javax.swing.JFormattedTextField();
        PnlCoordenadas4 = new javax.swing.JPanel();
        lblFormaVida = new javax.swing.JLabel();
        cmbFormaVida = new javax.swing.JComboBox();
        lblFamilia = new javax.swing.JLabel();
        cmbFamilia = new javax.swing.JComboBox();
        lblGenero = new javax.swing.JLabel();
        cmbGenero = new javax.swing.JComboBox();
        lblEspecie = new javax.swing.JLabel();
        cmbEspecie = new javax.swing.JComboBox();
        lblInfraespecie = new javax.swing.JLabel();
        lblFrecuenciaIndividuos = new javax.swing.JLabel();
        lblNombreComun = new javax.swing.JLabel();
        txtNombreComun = new javax.swing.JTextField();
        lblFrecuencia025 = new javax.swing.JLabel();
        txtFrecuencia50 = new javax.swing.JFormattedTextField();
        txtPorcentajeCobertura50 = new javax.swing.JFormattedTextField();
        lblCobertura025 = new javax.swing.JLabel();
        lblFrecuencia151 = new javax.swing.JLabel();
        txtFrecuencia51200 = new javax.swing.JFormattedTextField();
        txtPorcentajeCobertura51200 = new javax.swing.JFormattedTextField();
        lblCobertura151 = new javax.swing.JLabel();
        lblFrecuencia275 = new javax.swing.JLabel();
        txtFrecuencia200 = new javax.swing.JFormattedTextField();
        txtPorcentajeCobertura200 = new javax.swing.JFormattedTextField();
        lblCobertura275 = new javax.swing.JLabel();
        lblAgenteDanio1 = new javax.swing.JLabel();
        cmbAgenteDanio1 = new javax.swing.JComboBox();
        lblSeveridad1 = new javax.swing.JLabel();
        cmbSeveridad1 = new javax.swing.JComboBox();
        cmbAgenteDanio2 = new javax.swing.JComboBox();
        lblAgenteDanio2 = new javax.swing.JLabel();
        lblSeveridad2 = new javax.swing.JLabel();
        cmbSeveridad2 = new javax.swing.JComboBox();
        lblVigor = new javax.swing.JLabel();
        cmbVigor = new javax.swing.JComboBox();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnColecta = new javax.swing.JButton();
        lblClaveColecta = new javax.swing.JLabel();
        txtClaveColecta = new javax.swing.JTextField();
        cmbInfraespecie = new javax.swing.JComboBox();

        setMaximizable(true);
        setTitle("Registro de repoblado y vegetación menor módulo G "+version);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/gob/conafor/utils/logo_conafor.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(940, 650));

        pnlPrincipal.setBackground(new java.awt.Color(204, 204, 204));

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
        lblVegetacionMenorCobertura.setText("Regisro de vegetación menor y cobertura del suelo (sitios de 1m2)");
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
                .addGap(0, 6, Short.MAX_VALUE))
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
                    .addComponent(PnlCoordenadas1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PnlCoordenadas2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(11, 11, 11))
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
                .addGap(0, 11, Short.MAX_VALUE))
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
        });
        txtGravaPiedra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtGravaPiedraKeyTyped(evt);
            }
        });

        txtOtros.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtOtros.setNextFocusableComponent(chkRepobladoVM);
        txtOtros.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtOtrosFocusGained(evt);
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                        .addComponent(txtOtros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(PnlCoordenadas3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        lblRegistroRepobladoVegetacionMenor.setBackground(new java.awt.Color(153, 153, 153));
        lblRegistroRepobladoVegetacionMenor.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblRegistroRepobladoVegetacionMenor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRegistroRepobladoVegetacionMenor.setText("Registro de repoblado y vegetación menor");
        lblRegistroRepobladoVegetacionMenor.setOpaque(true);

        btnContinuar.setMnemonic('c');
        btnContinuar.setText("Continuar");
        btnContinuar.setNextFocusableComponent(btnSalir);
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

        chkRepobladoVM.setBackground(new java.awt.Color(204, 204, 204));
        chkRepobladoVM.setSelected(true);
        chkRepobladoVM.setText("Repoblado");
        chkRepobladoVM.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        chkRepobladoVM.setNextFocusableComponent(cmbFormaVida);
        chkRepobladoVM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkRepobladoVMActionPerformed(evt);
            }
        });

        grdRepobladoVM.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        grdRepobladoVM.setToolTipText("");
        grdRepobladoVM.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        grdRepobladoVM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grdRepobladoVMMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(grdRepobladoVM);

        jLabel1.setText("Porcentaje de repoblado fuera del sito de 12.56 m: ");

        chkRepobladoFuera.setBackground(new java.awt.Color(204, 204, 204));
        chkRepobladoFuera.setSelected(true);
        chkRepobladoFuera.setText("¿Existe repoblado fuera del sitio de 12.56 m2?");
        chkRepobladoFuera.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        chkRepobladoFuera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkRepobladoFueraActionPerformed(evt);
            }
        });

        txtPorcentajeRepobladoFuera.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtPorcentajeRepobladoFuera.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPorcentajeRepobladoFueraFocusGained(evt);
            }
        });
        txtPorcentajeRepobladoFuera.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPorcentajeRepobladoFueraKeyTyped(evt);
            }
        });

        PnlCoordenadas4.setBackground(new java.awt.Color(204, 204, 204));
        PnlCoordenadas4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        PnlCoordenadas4.setPreferredSize(new java.awt.Dimension(900, 145));

        lblFormaVida.setText("FV:");
        lblFormaVida.setToolTipText("Forma de Vida");

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

        lblFrecuenciaIndividuos.setBackground(new java.awt.Color(153, 153, 153));
        lblFrecuenciaIndividuos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFrecuenciaIndividuos.setText("Frecuencia de individuos");
        lblFrecuenciaIndividuos.setOpaque(true);

        lblNombreComun.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreComun.setText("Nombre común");

        txtNombreComun.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNombreComunFocusGained(evt);
            }
        });

        lblFrecuencia025.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFrecuencia025.setText("<=50 cm");
        lblFrecuencia025.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        txtFrecuencia50.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtFrecuencia50.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtFrecuencia50FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtFrecuencia50FocusLost(evt);
            }
        });
        txtFrecuencia50.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFrecuencia50KeyTyped(evt);
            }
        });

        txtPorcentajeCobertura50.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtPorcentajeCobertura50.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPorcentajeCobertura50FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPorcentajeCobertura50FocusLost(evt);
            }
        });
        txtPorcentajeCobertura50.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPorcentajeCobertura50KeyTyped(evt);
            }
        });

        lblCobertura025.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCobertura025.setText("%Cobertura");

        lblFrecuencia151.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFrecuencia151.setText("51-200 cm");

        txtFrecuencia51200.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtFrecuencia51200.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtFrecuencia51200FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtFrecuencia51200FocusLost(evt);
            }
        });
        txtFrecuencia51200.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFrecuencia51200KeyTyped(evt);
            }
        });

        txtPorcentajeCobertura51200.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtPorcentajeCobertura51200.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPorcentajeCobertura51200FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPorcentajeCobertura51200FocusLost(evt);
            }
        });
        txtPorcentajeCobertura51200.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPorcentajeCobertura51200KeyTyped(evt);
            }
        });

        lblCobertura151.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCobertura151.setText("%Cobertura");

        lblFrecuencia275.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFrecuencia275.setText(">=200 cm");

        txtFrecuencia200.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtFrecuencia200.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtFrecuencia200FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtFrecuencia200FocusLost(evt);
            }
        });
        txtFrecuencia200.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFrecuencia200KeyTyped(evt);
            }
        });

        txtPorcentajeCobertura200.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtPorcentajeCobertura200.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPorcentajeCobertura200FocusGained(evt);
            }
        });
        txtPorcentajeCobertura200.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPorcentajeCobertura200KeyTyped(evt);
            }
        });

        lblCobertura275.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCobertura275.setText("%Cobertura");

        lblAgenteDanio1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAgenteDanio1.setText("AD1");
        lblAgenteDanio1.setToolTipText("Agente daño 1");
        lblAgenteDanio1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        cmbAgenteDanio1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAgenteDanio1ActionPerformed(evt);
            }
        });

        lblSeveridad1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSeveridad1.setText("Severidad 1");
        lblSeveridad1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        cmbSeveridad1.setEnabled(false);

        cmbAgenteDanio2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAgenteDanio2ActionPerformed(evt);
            }
        });

        lblAgenteDanio2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAgenteDanio2.setText("AD2");
        lblAgenteDanio2.setToolTipText("Agente daño 2");
        lblAgenteDanio2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblSeveridad2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSeveridad2.setText("Severidad 2");
        lblSeveridad2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        cmbSeveridad2.setEnabled(false);

        lblVigor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVigor.setText("Vigor");

        btnGuardar.setMnemonic('g');
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnModificar.setMnemonic('m');
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setMnemonic('e');
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnColecta.setMnemonic('o');
        btnColecta.setText("Colecta");
        btnColecta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColectaActionPerformed(evt);
            }
        });

        lblClaveColecta.setText("Clave:");

        txtClaveColecta.setEnabled(false);

        javax.swing.GroupLayout PnlCoordenadas4Layout = new javax.swing.GroupLayout(PnlCoordenadas4);
        PnlCoordenadas4.setLayout(PnlCoordenadas4Layout);
        PnlCoordenadas4Layout.setHorizontalGroup(
            PnlCoordenadas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlCoordenadas4Layout.createSequentialGroup()
                .addGroup(PnlCoordenadas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnlCoordenadas4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(PnlCoordenadas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnlCoordenadas4Layout.createSequentialGroup()
                                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PnlCoordenadas4Layout.createSequentialGroup()
                                .addGroup(PnlCoordenadas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNombreComun)
                                    .addComponent(lblNombreComun, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(PnlCoordenadas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(PnlCoordenadas4Layout.createSequentialGroup()
                                        .addGroup(PnlCoordenadas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblFrecuencia025, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtFrecuencia50, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(PnlCoordenadas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtPorcentajeCobertura50, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblCobertura025))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(PnlCoordenadas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtFrecuencia51200, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblFrecuencia151, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(PnlCoordenadas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblCobertura151)
                                            .addComponent(txtPorcentajeCobertura51200, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(PnlCoordenadas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtFrecuencia200, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblFrecuencia275, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(PnlCoordenadas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblCobertura275)
                                            .addComponent(txtPorcentajeCobertura200, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(lblFrecuenciaIndividuos, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(PnlCoordenadas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmbAgenteDanio1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblAgenteDanio1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PnlCoordenadas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(PnlCoordenadas4Layout.createSequentialGroup()
                                .addGroup(PnlCoordenadas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmbSeveridad1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblSeveridad1))
                                .addGroup(PnlCoordenadas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PnlCoordenadas4Layout.createSequentialGroup()
                                        .addGap(9, 9, 9)
                                        .addComponent(lblAgenteDanio2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(PnlCoordenadas4Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbAgenteDanio2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(PnlCoordenadas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblSeveridad2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbSeveridad2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(PnlCoordenadas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PnlCoordenadas4Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cmbVigor, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnlCoordenadas4Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(lblVigor, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(PnlCoordenadas4Layout.createSequentialGroup()
                                .addComponent(btnColecta)
                                .addGap(18, 18, 18)
                                .addComponent(lblClaveColecta)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtClaveColecta, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(PnlCoordenadas4Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(lblFormaVida)
                        .addGap(4, 4, 4)
                        .addComponent(cmbFormaVida, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(lblFamilia)
                        .addGap(4, 4, 4)
                        .addComponent(cmbFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblGenero)
                        .addGap(1, 1, 1)
                        .addComponent(cmbGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(lblEspecie)
                        .addGap(0, 0, 0)
                        .addComponent(cmbEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblInfraespecie)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbInfraespecie, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        PnlCoordenadas4Layout.setVerticalGroup(
            PnlCoordenadas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlCoordenadas4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnlCoordenadas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFormaVida)
                    .addComponent(cmbFormaVida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFamilia)
                    .addComponent(cmbFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGenero)
                    .addComponent(cmbGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEspecie)
                    .addComponent(cmbEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PnlCoordenadas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblInfraespecie)
                        .addComponent(cmbInfraespecie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(PnlCoordenadas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnlCoordenadas4Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(cmbVigor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PnlCoordenadas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(PnlCoordenadas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAgenteDanio2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(PnlCoordenadas4Layout.createSequentialGroup()
                                .addComponent(lblSeveridad2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbSeveridad2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblVigor))
                        .addGroup(PnlCoordenadas4Layout.createSequentialGroup()
                            .addComponent(lblNombreComun)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtNombreComun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(PnlCoordenadas4Layout.createSequentialGroup()
                            .addComponent(lblFrecuenciaIndividuos)
                            .addGap(10, 10, 10)
                            .addGroup(PnlCoordenadas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(PnlCoordenadas4Layout.createSequentialGroup()
                                    .addComponent(lblFrecuencia025)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtFrecuencia50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(PnlCoordenadas4Layout.createSequentialGroup()
                                    .addComponent(lblCobertura025)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtPorcentajeCobertura50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(PnlCoordenadas4Layout.createSequentialGroup()
                                    .addComponent(lblFrecuencia151)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtFrecuencia51200, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(PnlCoordenadas4Layout.createSequentialGroup()
                                    .addComponent(lblCobertura151)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtPorcentajeCobertura51200, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(PnlCoordenadas4Layout.createSequentialGroup()
                                    .addComponent(lblFrecuencia275)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtFrecuencia200, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(PnlCoordenadas4Layout.createSequentialGroup()
                                    .addComponent(lblCobertura275)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtPorcentajeCobertura200, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(PnlCoordenadas4Layout.createSequentialGroup()
                                    .addComponent(lblAgenteDanio1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cmbAgenteDanio1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(PnlCoordenadas4Layout.createSequentialGroup()
                                    .addComponent(lblSeveridad1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(PnlCoordenadas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cmbSeveridad1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cmbAgenteDanio2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(PnlCoordenadas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnlCoordenadas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblClaveColecta)
                        .addComponent(txtClaveColecta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnColecta))
                    .addGroup(PnlCoordenadas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnGuardar)
                        .addComponent(btnModificar)
                        .addComponent(btnEliminar)))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlPrincipalLayout = new javax.swing.GroupLayout(pnlPrincipal);
        pnlPrincipal.setLayout(pnlPrincipalLayout);
        pnlPrincipalLayout.setHorizontalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPrincipalLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnContinuar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(lblUPM)
                                .addGap(10, 10, 10)
                                .addComponent(txtUPM, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(608, 608, 608)
                                .addComponent(lblSitio)
                                .addGap(8, 8, 8)
                                .addComponent(txtSitio, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(chkRepobladoFuera)
                                .addGap(11, 11, 11)
                                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                                        .addGap(260, 260, 260)
                                        .addComponent(txtPorcentajeRepobladoFuera, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(chkRepobladoVM)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(PnlDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(PnlDescripcion1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblVegetacionMenorCobertura, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblRegistroRepobladoVegetacionMenor, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(PnlCoordenadas4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 931, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(31, 31, 31))
        );
        pnlPrincipalLayout.setVerticalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblUPM))
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtUPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblSitio)
                    .addComponent(txtSitio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(lblVegetacionMenorCobertura)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PnlDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PnlDescripcion1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPrincipalLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkRepobladoVM)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblRegistroRepobladoVegetacionMenor)
                .addGap(1, 1, 1)
                .addComponent(PnlCoordenadas4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkRepobladoFuera)
                    .addComponent(jLabel1)
                    .addComponent(txtPorcentajeRepobladoFuera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnContinuar)
                    .addComponent(btnSalir))
                .addGap(4, 4, 4))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuarActionPerformed
        asignarDatosRepobladoFuera();
        ceSitio.setSitioID(this.sitioID);
        ceSitio.setRepobladoFuera(this.repobladoFuera);
        ceSitio.setPorcentajeRepoblado(this.porcentajeRepobladoFuera);
        if (setDatosCobertura() && validarSumaCubiertaSuelo() && validarPorcentaje() && validarRepobladoFuera()) {
            if (funciones.validarSeccionCapturada("TAXONOMIA_RepobladoVM", sitioID) && chkRepobladoVM.isSelected()) {
                JOptionPane.showMessageDialog(null, "Si selecciona Repoblado, se debe capturar ", "Repoblado vegetación menor", JOptionPane.INFORMATION_MESSAGE);
                chkRepobladoVM.requestFocus();
            } else if (funciones.validarSeccionCapturada("TAXONOMIA_RepobladoVM", sitioID) == false && chkRepobladoVM.isSelected()) {
                if (validarColectasObligatorias()) {
                    if (this.modificar == 0) {
                        crearCoberturaSuelo();
                        this.cdRepoblado.updateRepobladoFuera(this.ceSitio);
                        this.hide();
                        UPMForms.arboladoG.setDatosIniciales(this.ceSitio);
                        UPMForms.arboladoG.setVisible(true);
                    } else {
                        actualizarCoberturaSuelo();
                        this.cdRepoblado.updateRepobladoFuera(this.ceSitio);
                        this.hide();
                        UPMForms.arboladoG.revisarArboladoG(this.ceSitio);
                        UPMForms.arboladoG.setVisible(true);
                    }
                    this.cdSecuencia.updateSecuencia(this.ceSitio, FORMATO_ID, 1);
                }
            } else if (funciones.validarSeccionCapturada("TAXONOMIA_RepobladoVM", sitioID) == true && !chkRepobladoVM.isSelected()) {
                if (this.modificar == 0) {
                    crearCoberturaSuelo();
                    this.cdRepoblado.updateRepobladoFuera(this.ceSitio);
                    this.hide();
                    UPMForms.arboladoG.setDatosIniciales(this.ceSitio);
                    UPMForms.arboladoG.setVisible(true);
                } else {
                    actualizarCoberturaSuelo();
                    this.cdRepoblado.updateRepobladoFuera(this.ceSitio);
                    this.hide();
                    UPMForms.arboladoG.revisarArboladoG(this.ceSitio);
                    UPMForms.arboladoG.setVisible(true);
                }
                this.cdSecuencia.updateSecuencia(this.ceSitio, FORMATO_ID, -1);
            }
        }
    }//GEN-LAST:event_btnContinuarActionPerformed

    private void txtGramineasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGramineasFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtGramineas.selectAll();
            }
        });
    }//GEN-LAST:event_txtGramineasFocusGained

    private void txtHelechosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHelechosFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtHelechos.selectAll();
            }
        });
    }//GEN-LAST:event_txtHelechosFocusGained

    private void txtMusgosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMusgosFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtMusgos.selectAll();
            }
        });
    }//GEN-LAST:event_txtMusgosFocusGained

    private void txtLiquenesFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLiquenesFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtLiquenes.selectAll();
            }
        });
    }//GEN-LAST:event_txtLiquenesFocusGained

    private void txtHierbasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHierbasFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtHierbas.selectAll();
            }
        });
    }//GEN-LAST:event_txtHierbasFocusGained

    private void txtRocaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtRocaFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtRoca.selectAll();
            }
        });
    }//GEN-LAST:event_txtRocaFocusGained

    private void txtSueloDesnudoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSueloDesnudoFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtSueloDesnudo.selectAll();
            }
        });
    }//GEN-LAST:event_txtSueloDesnudoFocusGained

    private void txtHojarascaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHojarascaFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtHojarasca.selectAll();
            }
        });
    }//GEN-LAST:event_txtHojarascaFocusGained

    private void txtGravaPiedraFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGravaPiedraFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtGravaPiedra.selectAll();
            }
        });
    }//GEN-LAST:event_txtGravaPiedraFocusGained

    private void txtOtrosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtOtrosFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtOtros.selectAll();
            }
        });
    }//GEN-LAST:event_txtOtrosFocusGained

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

    private void grdRepobladoVMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grdRepobladoVMMouseClicked
        if (evt.getButton() == 1) {
            btnGuardar.setEnabled(true);
            int fila = grdRepobladoVM.getSelectedRow();
            String repobladoVMID = grdRepobladoVM.getValueAt(fila, 0).toString();
            this.repobladoVMID = Integer.parseInt(repobladoVMID);
            CERepobladoVM ceRepobladoVM;
            ceRepobladoVM = cdRepoblado.getRegistroRepobladoVM(Integer.parseInt(repobladoVMID));

            CatEFormaVidaRepobladoVM formaVida = new CatEFormaVidaRepobladoVM();
            formaVida.setFormaVidaRepobladoVMID(ceRepobladoVM.getFormaVidaID());
            cmbFormaVida.setSelectedItem(formaVida);

            CatEFamiliaEspecie fam = new CatEFamiliaEspecie();
            fam.setFamiliaID(ceRepobladoVM.getFamiliaID());
            cmbFamilia.setSelectedItem(fam);
            
            CatEGenero gen = new CatEGenero();
            gen.setGeneroID(ceRepobladoVM.getGeneroID());
            cmbGenero.setSelectedItem(gen);
            /*CatEGenero gen = new CatEGenero();
            gen.setGeneroID(ceRepobladoVM.getGeneroID());
            cmbGenero.removeAllItems();
            fillCmbGenero(ceRepobladoVM.getFamiliaID());
            cmbGenero.setSelectedItem(gen);*/

            CatEEspecie esp = new CatEEspecie();
            esp.setEspecieID(ceRepobladoVM.getEspecieID());
            cmbEspecie.removeAllItems();
            fillCmbEspecie(ceRepobladoVM.getGeneroID());
            cmbEspecie.setSelectedItem(esp);

            CatEInfraespecie inf = new CatEInfraespecie();
            inf.setInfraespecieID(ceRepobladoVM.getInfraespecieID());
            cmbInfraespecie.removeAllItems();
            fillCmbInfraespecie(ceRepobladoVM.getEspecieID());
            cmbInfraespecie.setSelectedItem(inf);
            
            txtNombreComun.setText(ceRepobladoVM.getNombreComun());
            txtFrecuencia50.setText(String.valueOf(ceRepobladoVM.getFrecuencia50()));
            if ((txtFrecuencia50.getText()).equals("0")) {
                txtFrecuencia50.setText("");
            }
            txtPorcentajeCobertura50.setText(String.valueOf(ceRepobladoVM.getPorcentajeCobertura50()));
            if (txtPorcentajeCobertura50.getText().equals("0")) {
                txtPorcentajeCobertura50.setText("");
            }
            txtFrecuencia51200.setText(String.valueOf(ceRepobladoVM.getFrecuencia51200()));
            if (txtFrecuencia51200.getText().equals("0")) {
                txtFrecuencia51200.setText("");
            }
            txtPorcentajeCobertura51200.setText(String.valueOf(ceRepobladoVM.getPorcentajeCobertura51200()));
            if (txtPorcentajeCobertura51200.getText().equals("0")) {
                txtPorcentajeCobertura51200.setText("");
            }
            txtFrecuencia200.setText(String.valueOf(ceRepobladoVM.getFrecuencia200()));
            if (txtFrecuencia200.getText().equals("0")) {
                txtFrecuencia200.setText("");
            }
            txtPorcentajeCobertura200.setText(String.valueOf(ceRepobladoVM.getPorcentajeCobertura200()));
            if (txtPorcentajeCobertura200.getText().equals("0")) {
                txtPorcentajeCobertura200.setText("");
            }
            CatETipoVigor vig = new CatETipoVigor();
            vig.setVigorID(ceRepobladoVM.getVigorID());
            cmbVigor.setSelectedItem(vig);
            
            CatEAgenteDanio agente1 = new CatEAgenteDanio();
            agente1.setAgenteDanioID(ceRepobladoVM.getAgenteDanio1ID());
            cmbAgenteDanio1.setSelectedItem(agente1);
            
            CatEAgenteDanio agente2 = new CatEAgenteDanio();
            agente2.setAgenteDanioID(ceRepobladoVM.getAgenteDanio2ID());
            cmbAgenteDanio2.setSelectedItem(agente2);
            
            CatEPorcentajeArbolado severidad1 = new CatEPorcentajeArbolado();
            severidad1.setPorcentajeArboladoID(ceRepobladoVM.getSeveridad1ID());
            combo.reiniciarComboModel(cmbSeveridad1);
            fillCmbSeveridad1(ceRepobladoVM.getAgenteDanio1ID());
            cmbSeveridad1.setSelectedItem(severidad1);
            
            CatEPorcentajeArbolado severidad2 = new CatEPorcentajeArbolado();
            severidad2.setPorcentajeArboladoID(ceRepobladoVM.getSeveridad2ID());
            combo.reiniciarComboModel(cmbSeveridad2);
            fillCmbSeveridad2(ceRepobladoVM.getAgenteDanio2ID());
            cmbSeveridad2.setSelectedItem(severidad2);
            
            txtClaveColecta.setText(ceRepobladoVM.getClaveColecta());
        }
    }//GEN-LAST:event_grdRepobladoVMMouseClicked

    private void txtPorcentajeRepobladoFueraFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPorcentajeRepobladoFueraFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtPorcentajeRepobladoFuera.selectAll();
            }
        });
    }//GEN-LAST:event_txtPorcentajeRepobladoFueraFocusGained

    private void chkRepobladoFueraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkRepobladoFueraActionPerformed
        if (chkRepobladoFuera.isSelected()) {
            txtPorcentajeRepobladoFuera.setEnabled(true);
            txtPorcentajeRepobladoFuera.requestFocus();
        } else {
            txtPorcentajeRepobladoFuera.setEnabled(false);
            txtPorcentajeRepobladoFuera.setText("");
            txtPorcentajeRepobladoFuera.setValue(null);
        }
    }//GEN-LAST:event_chkRepobladoFueraActionPerformed

    private void cmbGeneroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbGeneroActionPerformed
        CatEGenero indexGenero = (CatEGenero) cmbGenero.getSelectedItem();
        DefaultComboBoxModel dcm = (DefaultComboBoxModel) cmbEspecie.getModel();
        dcm.removeAllElements();
        if (indexGenero != null) {
            fillCmbEspecie(indexGenero.getGeneroID());
        }
    }//GEN-LAST:event_cmbGeneroActionPerformed

    private void txtNombreComunFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombreComunFocusGained
        txtNombreComun.selectAll();
    }//GEN-LAST:event_txtNombreComunFocusGained

    private void txtFrecuencia50FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFrecuencia50FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtFrecuencia50.selectAll();
            }
        });
    }//GEN-LAST:event_txtFrecuencia50FocusGained

    private void txtFrecuencia50FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFrecuencia50FocusLost

        if (txtFrecuencia50.getText().isEmpty()) {
            txtFrecuencia50.setValue(null);
        }
    }//GEN-LAST:event_txtFrecuencia50FocusLost

    private void txtPorcentajeCobertura50FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPorcentajeCobertura50FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtPorcentajeCobertura50.selectAll();
            }
        });
    }//GEN-LAST:event_txtPorcentajeCobertura50FocusGained

    private void txtPorcentajeCobertura50FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPorcentajeCobertura50FocusLost
        if (txtPorcentajeCobertura50.getText().isEmpty()) {
            txtPorcentajeCobertura50.setValue(null);
        }
    }//GEN-LAST:event_txtPorcentajeCobertura50FocusLost

    private void txtFrecuencia51200FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFrecuencia51200FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtFrecuencia51200.selectAll();
            }
        });
    }//GEN-LAST:event_txtFrecuencia51200FocusGained

    private void txtFrecuencia51200FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFrecuencia51200FocusLost
        if (txtFrecuencia51200.getText().isEmpty()) {
            txtFrecuencia51200.setValue(null);
        }
    }//GEN-LAST:event_txtFrecuencia51200FocusLost

    private void txtPorcentajeCobertura51200FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPorcentajeCobertura51200FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtPorcentajeCobertura51200.selectAll();
            }
        });
    }//GEN-LAST:event_txtPorcentajeCobertura51200FocusGained

    private void txtPorcentajeCobertura51200FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPorcentajeCobertura51200FocusLost
        if (txtFrecuencia51200.getText().isEmpty()) {
            txtFrecuencia51200.setValue(null);
        }
    }//GEN-LAST:event_txtPorcentajeCobertura51200FocusLost

    private void txtFrecuencia200FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFrecuencia200FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtFrecuencia200.selectAll();
            }
        });
    }//GEN-LAST:event_txtFrecuencia200FocusGained

    private void txtFrecuencia200FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFrecuencia200FocusLost
        if (txtFrecuencia200.getText().isEmpty()) {
            txtFrecuencia200.setValue(null);
        }
    }//GEN-LAST:event_txtFrecuencia200FocusLost

    private void txtPorcentajeCobertura200FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPorcentajeCobertura200FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtPorcentajeCobertura200.selectAll();
            }
        });
    }//GEN-LAST:event_txtPorcentajeCobertura200FocusGained

    private void cmbAgenteDanio1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAgenteDanio1ActionPerformed
        CatEAgenteDanio agenteDanio = (CatEAgenteDanio) cmbAgenteDanio1.getSelectedItem();
        if (agenteDanio != null) {
            if (agenteDanio.getAgenteDanioID() == 21 || agenteDanio.getAgenteDanioID() == 34 || agenteDanio.getAgenteDanioID() == 22) {
                cmbSeveridad1.setEnabled(true);
                fillCmbSeveridad1(agenteDanio.getAgenteDanioID());
            } else {
                combo.reiniciarComboModel(cmbSeveridad1);
                cmbSeveridad1.setSelectedItem(null);
                cmbSeveridad1.setEnabled(false);
            }
        }
    }//GEN-LAST:event_cmbAgenteDanio1ActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        asignarDatosRepoblado();
        if (validarRepobladoVacios() && validarDatosRepoblado() && validarSeveridadDanio()) {
            crearRepobladoVM();
            this.cdRepoblado.enumerarConsecutivo(this.sitioID);
            llenarTabla();
            limpiarControles();
            cmbFormaVida.requestFocus();
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        asignarDatosRepoblado();
        if (validarRepobladoVacios() && validarDatosRepoblado() && validarSeveridadDanio()) {
            actualizarRepoblado();
            llenarTabla();
            limpiarControles();
            cmbFormaVida.requestFocus();
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminarRepoblado();
        this.cdRepoblado.enumerarConsecutivo(this.sitioID);
        llenarTabla();
        limpiarControles();
        cmbFormaVida.requestFocus();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void chkRepobladoVMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkRepobladoVMActionPerformed
        if (!chkRepobladoVM.isSelected()) {
            if (this.cdRepoblado.validarHayRegistros(this.sitioID)) {
                int respuesta = JOptionPane.showConfirmDialog(null, "Si capturó, se eliminará la información de repoblado vegetación menor del sitio " + this.sitio,
                        "Repoblado vegetación menor", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (respuesta == JOptionPane.YES_OPTION) {
                    this.cdRepoblado.deleteRepobladoSitio(this.sitioID);
                    limpiarControles();
                    cmbFormaVida.setEnabled(false);
                    cmbFamilia.setEnabled(false);
                    cmbGenero.setEnabled(false);
                    cmbEspecie.setEnabled(false);
                    cmbInfraespecie.setEnabled(false);
                    txtNombreComun.setEnabled(false);
                    txtFrecuencia50.setEnabled(false);
                    txtPorcentajeCobertura50.setEnabled(false);
                    txtFrecuencia51200.setEnabled(false);
                    txtPorcentajeCobertura51200.setEnabled(false);
                    txtFrecuencia200.setEnabled(false);
                    txtPorcentajeCobertura200.setEnabled(false);
                    cmbAgenteDanio1.setEnabled(false);
                    cmbSeveridad1.setEnabled(false);
                    cmbAgenteDanio2.setEnabled(false);
                    cmbSeveridad2.setEnabled(false);
                    cmbVigor.setEnabled(false);
                    btnGuardar.setEnabled(false);
                    btnModificar.setEnabled(false);
                    btnEliminar.setEnabled(false);
                }
            } else if(!chkRepobladoVM.isSelected()){
                cmbFormaVida.setEnabled(false);
                cmbFamilia.setEnabled(false);
                cmbGenero.setEnabled(false);
                cmbEspecie.setEnabled(false);
                cmbInfraespecie.setEnabled(false);
                txtNombreComun.setEnabled(false);
                txtFrecuencia50.setEnabled(false);
                txtPorcentajeCobertura50.setEnabled(false);
                txtFrecuencia51200.setEnabled(false);
                txtPorcentajeCobertura51200.setEnabled(false);
                txtFrecuencia200.setEnabled(false);
                txtPorcentajeCobertura200.setEnabled(false);
                cmbAgenteDanio1.setEnabled(false);
                cmbSeveridad1.setEnabled(false);
                cmbAgenteDanio2.setEnabled(false);
                cmbSeveridad2.setEnabled(false);
                cmbVigor.setEnabled(false);
                btnGuardar.setEnabled(false);
                btnModificar.setEnabled(false);
                btnEliminar.setEnabled(false);
            }
        } else {
            cmbFormaVida.setEnabled(true);
            cmbFamilia.setEnabled(true);
            cmbGenero.setEnabled(true);
            cmbEspecie.setEnabled(true);
            cmbInfraespecie.setEnabled(true);
            txtNombreComun.setEnabled(true);
            txtFrecuencia50.setEnabled(true);
            txtPorcentajeCobertura50.setEnabled(true);
            txtFrecuencia51200.setEnabled(true);
            txtPorcentajeCobertura51200.setEnabled(true);
            txtFrecuencia200.setEnabled(true);
            txtPorcentajeCobertura200.setEnabled(true);
            cmbAgenteDanio1.setEnabled(true);
            cmbSeveridad1.setEnabled(true);
            cmbAgenteDanio2.setEnabled(true);
            cmbSeveridad2.setEnabled(true);
            cmbVigor.setEnabled(true);
            btnGuardar.setEnabled(true);
            btnModificar.setEnabled(true);
            btnEliminar.setEnabled(true);
        }
    }//GEN-LAST:event_chkRepobladoVMActionPerformed

    public void habilitarControles(boolean seleccionado){
        
        if(seleccionado==false){
            cmbFormaVida.setEnabled(false);
                cmbFamilia.setEnabled(false);
                cmbGenero.setEnabled(false);
                cmbEspecie.setEnabled(false);
                cmbInfraespecie.setEnabled(false);
                txtNombreComun.setEnabled(false);
                txtFrecuencia50.setEnabled(false);
                txtPorcentajeCobertura50.setEnabled(false);
                txtFrecuencia51200.setEnabled(false);
                txtPorcentajeCobertura51200.setEnabled(false);
                txtFrecuencia200.setEnabled(false);
                txtPorcentajeCobertura200.setEnabled(false);
                cmbAgenteDanio1.setEnabled(false);
                cmbSeveridad1.setEnabled(false);
                cmbAgenteDanio2.setEnabled(false);
                cmbSeveridad2.setEnabled(false);
                cmbVigor.setEnabled(false);
                btnGuardar.setEnabled(false);
                btnModificar.setEnabled(false);
                btnEliminar.setEnabled(false);
        }else{
            cmbFormaVida.setEnabled(true);
            cmbFamilia.setEnabled(true);
            cmbGenero.setEnabled(true);
            cmbEspecie.setEnabled(true);
            cmbInfraespecie.setEnabled(true);
            txtNombreComun.setEnabled(true);
            txtFrecuencia50.setEnabled(true);
            txtPorcentajeCobertura50.setEnabled(true);
            txtFrecuencia51200.setEnabled(true);
            txtPorcentajeCobertura51200.setEnabled(true);
            txtFrecuencia200.setEnabled(true);
            txtPorcentajeCobertura200.setEnabled(true);
            cmbAgenteDanio1.setEnabled(true);
            cmbSeveridad1.setEnabled(true);
            cmbAgenteDanio2.setEnabled(true);
            cmbSeveridad2.setEnabled(true);
            cmbVigor.setEnabled(true);
            btnGuardar.setEnabled(true);
            btnModificar.setEnabled(true);
            btnEliminar.setEnabled(true);
        }
        
    }
    
    private void cmbAgenteDanio2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAgenteDanio2ActionPerformed
        CatEAgenteDanio agenteDanio = (CatEAgenteDanio) cmbAgenteDanio2.getSelectedItem();
        if (agenteDanio != null) {
            if (agenteDanio.getAgenteDanioID() == 21 || agenteDanio.getAgenteDanioID() == 34 || agenteDanio.getAgenteDanioID() == 22) {
                cmbSeveridad2.setEnabled(true);
                fillCmbSeveridad2(agenteDanio.getAgenteDanioID());
            } else {
                combo.reiniciarComboModel(cmbSeveridad2);
                cmbSeveridad2.setSelectedItem(null);
                cmbSeveridad2.setEnabled(false);
            }
        }
    }//GEN-LAST:event_cmbAgenteDanio2ActionPerformed

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

    private void txtFrecuencia50KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFrecuencia50KeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtFrecuencia50KeyTyped

    private void txtPorcentajeCobertura50KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPorcentajeCobertura50KeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtPorcentajeCobertura50KeyTyped

    private void txtFrecuencia51200KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFrecuencia51200KeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtFrecuencia51200KeyTyped

    private void txtPorcentajeCobertura51200KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPorcentajeCobertura51200KeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtPorcentajeCobertura51200KeyTyped

    private void txtFrecuencia200KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFrecuencia200KeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtFrecuencia200KeyTyped

    private void txtPorcentajeCobertura200KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPorcentajeCobertura200KeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtPorcentajeCobertura200KeyTyped

    private void txtPorcentajeRepobladoFueraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPorcentajeRepobladoFueraKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtPorcentajeRepobladoFueraKeyTyped

    private void btnColectaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnColectaActionPerformed
        try {
            int fila = grdRepobladoVM.getSelectedRow();
            String consecutivo = grdRepobladoVM.getValueAt(fila, 2).toString();
            FrmClaveColecta claveColecta = new FrmClaveColecta(Main.main, true);
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
            claveColecta.setDatosIniciales(ceColecta, FORMATO_ID, "TAXONOMIA_RepobladoVM", "Consecutivo", this.sitioID, Integer.parseInt(consecutivo));
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PnlCoordenadas1;
    private javax.swing.JPanel PnlCoordenadas2;
    private javax.swing.JPanel PnlCoordenadas3;
    private javax.swing.JPanel PnlCoordenadas4;
    private javax.swing.JPanel PnlDescripcion;
    private javax.swing.JPanel PnlDescripcion1;
    private javax.swing.JButton btnColecta;
    private javax.swing.JButton btnContinuar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JCheckBox chkRepobladoFuera;
    private javax.swing.JCheckBox chkRepobladoVM;
    private javax.swing.JComboBox cmbAgenteDanio1;
    private javax.swing.JComboBox cmbAgenteDanio2;
    private javax.swing.JComboBox cmbEspecie;
    private javax.swing.JComboBox cmbFamilia;
    private javax.swing.JComboBox cmbFormaVida;
    private javax.swing.JComboBox cmbGenero;
    private javax.swing.JComboBox cmbInfraespecie;
    private javax.swing.JComboBox cmbSeveridad1;
    private javax.swing.JComboBox cmbSeveridad2;
    private javax.swing.JComboBox cmbVigor;
    private javax.swing.JTable grdRepobladoVM;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAgenteDanio1;
    private javax.swing.JLabel lblAgenteDanio2;
    private javax.swing.JLabel lblClaveColecta;
    private javax.swing.JLabel lblCobertura025;
    private javax.swing.JLabel lblCobertura151;
    private javax.swing.JLabel lblCobertura275;
    private javax.swing.JLabel lblCoberturaSuelo;
    private javax.swing.JLabel lblCoberturaVegetacionMenor;
    private javax.swing.JLabel lblEspecie;
    private javax.swing.JLabel lblFamilia;
    private javax.swing.JLabel lblFormaVida;
    private javax.swing.JLabel lblFrecuencia025;
    private javax.swing.JLabel lblFrecuencia151;
    private javax.swing.JLabel lblFrecuencia275;
    private javax.swing.JLabel lblFrecuenciaIndividuos;
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
    private javax.swing.JLabel lblRegistroRepobladoVegetacionMenor;
    private javax.swing.JLabel lblRoca;
    private javax.swing.JLabel lblSeveridad1;
    private javax.swing.JLabel lblSeveridad2;
    private javax.swing.JLabel lblSitio;
    private javax.swing.JLabel lblSueloDesnudo;
    private javax.swing.JLabel lblUPM;
    private javax.swing.JLabel lblVegetacionMenor;
    private javax.swing.JLabel lblVegetacionMenorCobertura;
    private javax.swing.JLabel lblVigor;
    private javax.swing.JPanel pnlPrincipal;
    public javax.swing.JTextField txtClaveColecta;
    private javax.swing.JFormattedTextField txtFrecuencia200;
    private javax.swing.JFormattedTextField txtFrecuencia50;
    private javax.swing.JFormattedTextField txtFrecuencia51200;
    private javax.swing.JFormattedTextField txtGramineas;
    private javax.swing.JFormattedTextField txtGravaPiedra;
    private javax.swing.JFormattedTextField txtHelechos;
    private javax.swing.JFormattedTextField txtHierbas;
    private javax.swing.JFormattedTextField txtHojarasca;
    private javax.swing.JFormattedTextField txtLiquenes;
    private javax.swing.JFormattedTextField txtMusgos;
    private javax.swing.JTextField txtNombreComun;
    private javax.swing.JFormattedTextField txtOtros;
    private javax.swing.JFormattedTextField txtPorcentajeCobertura200;
    private javax.swing.JFormattedTextField txtPorcentajeCobertura50;
    private javax.swing.JFormattedTextField txtPorcentajeCobertura51200;
    private javax.swing.JFormattedTextField txtPorcentajeRepobladoFuera;
    private javax.swing.JFormattedTextField txtRoca;
    private javax.swing.JTextField txtSitio;
    private javax.swing.JFormattedTextField txtSueloDesnudo;
    private javax.swing.JTextField txtUPM;
    // End of variables declaration//GEN-END:variables
}
