package gob.conafor.taxonomia.vie;

import gob.conafor.ini.vie.Main;
import gob.conafor.sitio.mod.CDSitio;
import gob.conafor.sitio.mod.CESitio;
import gob.conafor.sys.mod.CDSecuencia;
import gob.conafor.sys.mod.CDSeguimientoUPM;
import gob.conafor.sys.mod.CESeccionesCapturadas;
import gob.conafor.taxonomia.mod.CDColectaBotanica;
import gob.conafor.taxonomia.mod.CDCondicionTaxonomica;
import gob.conafor.taxonomia.mod.CDEspecies;
import gob.conafor.taxonomia.mod.CDRepoblado;
import gob.conafor.taxonomia.mod.CEColectaBotanica;
import gob.conafor.taxonomia.mod.CERepoblado;
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
import gob.conafor.utils.Version;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class FrmRepoblado extends javax.swing.JInternalFrame {
    private boolean revision;
    private final int repoblado;
    private final int arbolado;
    private int repobladoID;
    private static final int FORMATO_ID = 2;
    private int upmID;
    private int sitioID;
    private int sitio;
    private Integer frecuencia025;
    private Integer edad025;
    private Integer frecuencia151;
    private Integer edad151;
    private Integer frecuencia275;
    private Integer edad275;
    private Integer porcentajeDanio;
    private int repobladoFuera;
    private Integer porcentajeRepobladoFuera;
    private CDEspecies especie = new CDEspecies();
    private CDCondicionTaxonomica condicion = new CDCondicionTaxonomica();
    private CDRepoblado datosRepoblado = new CDRepoblado();
    private CESitio ceSitio = new CESitio();
    private CDSitio cdSitio = new CDSitio();
    private CESeccionesCapturadas seccionActual = new CESeccionesCapturadas();
    private CESeccionesCapturadas seccionSiguiente = new CESeccionesCapturadas();
    private CDSeguimientoUPM seguimiento = new CDSeguimientoUPM();
    private Datos numeros = new Datos();
    private FuncionesComunes combo = new FuncionesComunes();
    private CDEspecies cdEspecies = new CDEspecies();
    private CDSecuencia cdSecuencia = new CDSecuencia();
    private int actulizar;
    private FuncionesComunes funciones = new FuncionesComunes();
    private Version ver=new Version();
    private String version=ver.getVersion();

    public FrmRepoblado() {
        initComponents();
        this.repoblado = 9;
        this.arbolado = 10;
        fillCmbFamilia();
        fillCmbGenero();
        fillCmbVigor();
        fillCmbDanio();
    }

    public void setDatosIniciales(CESitio sitio) {
        this.upmID = sitio.getUpmID();
        this.sitioID = sitio.getSitioID();
        this.sitio = sitio.getSitio();
        txtUPM.setText(String.valueOf(this.upmID));
        txtSitio.setText(String.valueOf(this.sitio));
        ceSitio.setUpmID(this.upmID);
        ceSitio.setSitioID(this.sitioID);
        ceSitio.setSitio(this.sitio);
        ceSitio = sitio;
        llenarTabla();
        this.actulizar = 0;
        funciones.manipularBotonesMenuPrincipal(true);
        reiniciarRepoblado();
        reiniciarRepobladoFuera();
    }

    public void revisarRepoblado(CESitio sitio) {
        revision=true;
        this.upmID = sitio.getUpmID();
        this.sitioID = sitio.getSitioID();
        this.sitio = sitio.getSitio();
        txtUPM.setText(String.valueOf(this.upmID));
        txtSitio.setText(String.valueOf(this.sitio));
        ceSitio.setUpmID(this.upmID);
        ceSitio.setSitioID(this.sitioID);
        ceSitio.setSitio(this.sitio);
        ceSitio.setSecuencia(sitio.getSecuencia());
        ceSitio = sitio;
        llenarTabla();
        CESitio repobladoFuera= this.cdSitio.getRepobladoFuera(this.sitioID);
        if (repobladoFuera.getRepobladoFuera() == 1) {
            chkRepobladoFuera.setSelected(true);
            txtPorcentajeRepobladoFuera.setEnabled(true);
            txtPorcentajeRepobladoFuera.setText(String.valueOf(repobladoFuera.getPorcentajeRepoblado()));
        } else {
            chkRepobladoFuera.setSelected(false);
            txtPorcentajeRepobladoFuera.setEnabled(false);
        }
        this.actulizar = 1;
        funciones.manipularBotonesMenuPrincipal(true);
        this.chkRepoblado.setSelected(funciones.habilitarCheckBox("TAXONOMIA_Repoblado", this.sitioID));
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

    private void asignarDatosRepoblado() {
        try {
            this.frecuencia025 = Integer.valueOf(txtFrecuencia025.getText());
        } catch (NumberFormatException e) {
            this.frecuencia025 = null;
        }
        try {
            this.edad025 = Integer.valueOf(txtEdad025.getText());
        } catch (NumberFormatException e) {
            this.edad025 = null;
        }
        try {
            this.frecuencia151 = Integer.valueOf(txtFrecuencia151.getText());
        } catch (NumberFormatException e) {
            this.frecuencia151 = null;
        }
        try {
            this.edad151 = Integer.valueOf(txtEdad151.getText());
        } catch (NumberFormatException e) {
            this.edad151 = null;
        }
        try {
            this.frecuencia275 = Integer.valueOf(txtFrecuencia275.getText());
        } catch (NumberFormatException e) {
            this.frecuencia275 = null;
        }
        try {
            this.edad275 = Integer.valueOf(txtEdad275.getText());
        } catch (NumberFormatException e) {
            this.edad275 = null;
        }
        try {
            this.porcentajeDanio = Integer.valueOf(txtPorcentajeDanio.getText());
        } catch (NumberFormatException e) {
            this.porcentajeDanio = null;
        }
    }
    
    private void asignarDatosRepobladoFuera(){
        if(chkRepobladoFuera.isSelected()){
            this.repobladoFuera = 1;
        }else{
            this.repobladoFuera = 0;
        }
        try{
            this.porcentajeRepobladoFuera = Integer.valueOf(txtPorcentajeRepobladoFuera.getText());
        }catch(NumberFormatException e){
            this.porcentajeRepobladoFuera = null;
        }
    }

    private boolean validarDatosRepoblado() {
        if (this.frecuencia025 != null && this.frecuencia025 < 1) {
            JOptionPane.showMessageDialog(null, "Error! Debe de capturar un valos mayor a 0 en frecuencia 0.25-1.50", "Repoblado", JOptionPane.INFORMATION_MESSAGE);
            txtFrecuencia025.requestFocus();
            return false;
        } else if (this.edad025 != null && this.edad025 < 1) {
            JOptionPane.showMessageDialog(null, "Error! Debe de capturar un valos mayor a 0 en Edad 0.25-1.50", "Repoblado", JOptionPane.INFORMATION_MESSAGE);
            txtEdad025.requestFocus();
            return false;
        } else if (this.frecuencia151 != null && this.frecuencia151 < 1) {
            JOptionPane.showMessageDialog(null, "Error! Debe de capturar un valos mayor a 0 en frecuencia 1.51-2.75", "Repoblado", JOptionPane.INFORMATION_MESSAGE);
            txtFrecuencia151.requestFocus();
            return false;
        } else if (this.edad151 != null && this.edad151 < 1) {
            JOptionPane.showMessageDialog(null, "Error! Debe de capturar un valos mayor a 0 en Edad 1.51-2.75", "Repoblado", JOptionPane.INFORMATION_MESSAGE);
            txtEdad151.requestFocus();
            return false;
        } else if (this.frecuencia275 != null && this.frecuencia275 < 1) {
            JOptionPane.showMessageDialog(null, "Error! Debe de capturar un valos mayor a 0 en frecuencia 2.75", "Repoblado", JOptionPane.INFORMATION_MESSAGE);
            txtFrecuencia275.requestFocus();
            return false;
        } else if (this.edad275 != null && this.edad275 < 1) {
            JOptionPane.showMessageDialog(null, "Error! Debe de capturar un valos mayor a 0 en Edad 2.75", "Repoblado", JOptionPane.INFORMATION_MESSAGE);
            txtEdad275.requestFocus();
            return false;
        } else {
            return true;
        }
    }
    
    private void reiniciarRepobladoFuera(){
        chkRepobladoFuera.setSelected(true);
        txtPorcentajeRepobladoFuera.setValue(null);
        txtPorcentajeRepobladoFuera.setText("");
    }
    private boolean validarRepobladoFuera(){
        if(chkRepobladoFuera.isSelected() && txtPorcentajeRepobladoFuera.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Si selecciona repoblado fuera, debe proporcionar el porcentaje", "Repoblado fuera", JOptionPane.INFORMATION_MESSAGE);
            txtPorcentajeRepobladoFuera.requestFocus();
            return false;
        }else{
            return true;
        }
    }

    private boolean validarRepobladoVacios() {
        CatEAgenteDanio danio = (CatEAgenteDanio) cmbDanio.getSelectedItem();
        if (!txtFrecuencia025.getText().isEmpty() && txtEdad025.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe de capturar porcenta de cobertura 0.25-1.50", "Repoblado", JOptionPane.INFORMATION_MESSAGE);
            txtEdad025.requestFocus();
            return false;
        } else if (!txtFrecuencia151.getText().isEmpty() && txtEdad151.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe de capturar porcenta de cobertura 1.51-2.75", "Repoblado", JOptionPane.INFORMATION_MESSAGE);
            txtEdad151.requestFocus();
            return false;
        } else if (!txtFrecuencia275.getText().isEmpty() && txtEdad275.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe de capturar porcenta de cobertura > 2.75", "Repoblado", JOptionPane.INFORMATION_MESSAGE);
            txtEdad275.requestFocus();
            return false;
        } else if (txtFrecuencia025.getText().isEmpty() && !txtEdad025.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe de capturar frecuencia 0.25-1.50", "Repoblado", JOptionPane.INFORMATION_MESSAGE);
            txtFrecuencia025.requestFocus();
            return false;
        } else if (txtFrecuencia151.getText().isEmpty() && !txtEdad151.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe de capturar frecuencia 1.51-2.75", "Repoblado", JOptionPane.INFORMATION_MESSAGE);
            txtFrecuencia151.requestFocus();
            return false;
        } else if (txtFrecuencia275.getText().isEmpty() && !txtEdad275.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe de capturar frecuencia > 2.75", "Repoblado", JOptionPane.INFORMATION_MESSAGE);
            txtFrecuencia275.requestFocus();
            return false;
        } else if (cmbVigor.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar un vigor", "Repoblado", JOptionPane.INFORMATION_MESSAGE);
            cmbVigor.requestFocus();
            return false;
        } else if (cmbDanio.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar un daño", "Repoblado", JOptionPane.INFORMATION_MESSAGE);
            cmbDanio.requestFocus();
            return false;
        } else if ((cmbDanio.getSelectedItem() != null && danio.getAgenteDanioID() != 1) && txtPorcentajeDanio.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Si selecciono un daño, debe proporcionar un porcentaje", "Repoblado", JOptionPane.INFORMATION_MESSAGE);
            txtPorcentajeDanio.requestFocus();
            return false;
        } else {
            return true;
        }
    }
    
     private boolean validarColectasObligatorias() {
        CDColectaBotanica colecta = new CDColectaBotanica();
        if (colecta.validarCapturaGenero("TAXONOMIA_Repoblado", this.sitioID)) {
            JOptionPane.showMessageDialog(null, "Error! Faltan por asignar claves de colecta", "Repoblado", JOptionPane.INFORMATION_MESSAGE);
            return false;
        } else {
            return true;
        }
    }

    private void crearRepoblado() {
        CatEFamiliaEspecie indexFamilia = (CatEFamiliaEspecie) cmbFamilia.getSelectedItem();
        CatEGenero indexGenero = (CatEGenero) cmbGenero.getSelectedItem();
        CatEEspecie indexEspecie = (CatEEspecie) cmbEspecie.getSelectedItem();
        CatEInfraespecie indexInfraespecie = (CatEInfraespecie) cmbInfraespecie.getSelectedItem();
        CatETipoVigor vigor = (CatETipoVigor) cmbVigor.getSelectedItem();
        CatEAgenteDanio danio = (CatEAgenteDanio) cmbDanio.getSelectedItem();
        CDRepoblado cdRepoblado = new CDRepoblado();
        CERepoblado rep = new CERepoblado();

        rep.setSitioID(this.sitioID); 
        if (indexFamilia != null) {
            rep.setFamiliaID(indexFamilia.getFamiliaID());
        }
        if (indexGenero != null) {
            rep.setGeneroID(indexGenero.getGeneroID());
        }
        if (indexEspecie != null) {
            rep.setEspecieID(indexEspecie.getEspecieID());
        }
        if(indexInfraespecie != null){
            rep.setInfraespecieID(indexInfraespecie.getInfraespecieID());
        }
        rep.setNombreComun(txtNombreComun.getText());
        rep.setFrecuecia025150(this.frecuencia025);
        rep.setEdad025150(this.edad025);
        rep.setFrecuencia151275(this.frecuencia151);
        rep.setEdad151275(this.edad151);
        rep.setFrecuencia275(this.frecuencia275);
        rep.setEdad275(this.edad275);
        rep.setVigorID(vigor.getVigorID());
        rep.setDanioID(danio.getAgenteDanioID());
        rep.setPorcentajeDanio(this.porcentajeDanio);

        cdRepoblado.insertDatosRepoblado(rep);
    }
    
    private void crearRepobladoFuera() {
        CESitio ceSitio = new CESitio();
        CDSitio cdSitio = new CDSitio();
        ceSitio.setSitioID(this.sitioID);
        ceSitio.setRepobladoFuera(this.repobladoFuera);
        ceSitio.setPorcentajeRepoblado(this.porcentajeRepobladoFuera);
        cdSitio.updateRepobladoFuera(ceSitio);
    }

    public void llenarTabla() {
        grdRepoblado.setModel(datosRepoblado.getTablaRepoblado(this.sitioID));
        grdRepoblado.getColumnModel().getColumn(2).setPreferredWidth(70);//Consecutivo
        grdRepoblado.getColumnModel().getColumn(3).setPreferredWidth(120);//Familia
        grdRepoblado.getColumnModel().getColumn(4).setPreferredWidth(120);//Genero
        grdRepoblado.getColumnModel().getColumn(5).setPreferredWidth(120);//Especie
        grdRepoblado.getColumnModel().getColumn(6).setPreferredWidth(120);//Infraespecie
        grdRepoblado.getColumnModel().getColumn(7).setPreferredWidth(120);//Nombre comun
        grdRepoblado.getColumnModel().getColumn(8).setPreferredWidth(120);//Frecuencia 0-25
        grdRepoblado.getColumnModel().getColumn(9).setPreferredWidth(120);//Edad 0-25
        grdRepoblado.getColumnModel().getColumn(10).setPreferredWidth(120);//Frecuencia 1-51
        grdRepoblado.getColumnModel().getColumn(11).setPreferredWidth(120);//Edad 1-51
        grdRepoblado.getColumnModel().getColumn(12).setPreferredWidth(120);//Frecuencia 2-75
        grdRepoblado.getColumnModel().getColumn(13).setPreferredWidth(120);//Edad 275
        grdRepoblado.getColumnModel().getColumn(14).setPreferredWidth(100);//Vigor
        grdRepoblado.getColumnModel().getColumn(15).setPreferredWidth(40);//Danio
        grdRepoblado.getColumnModel().getColumn(16).setPreferredWidth(90);//Porcentaje danio
        grdRepoblado.getColumnModel().getColumn(17).setPreferredWidth(180);//Clave de colecta
        Tablas tabla = new Tablas();
        int[] columna_0 = {0};
        int[] column_1 = {1};
        //Ocultar campo ID
        tabla.hideColumnTable(grdRepoblado, columna_0);
        tabla.hideColumnTable(grdRepoblado, column_1);
    }
    
    private void actualizarRepoblado() {
        try {
            int fila = grdRepoblado.getSelectedRow();
            String registro = grdRepoblado.getValueAt(fila, 0).toString();
           
            CatEFamiliaEspecie familia = (CatEFamiliaEspecie) cmbFamilia.getSelectedItem();
            CatEGenero generoIndex = (CatEGenero) cmbGenero.getSelectedItem();
            CatEEspecie especieIndex = (CatEEspecie) cmbEspecie.getSelectedItem();
            CatEInfraespecie indexInfraespecie = (CatEInfraespecie) cmbInfraespecie.getSelectedItem();
            CatEAgenteDanio danio = (CatEAgenteDanio) cmbDanio.getSelectedItem();
            CatETipoVigor vigor = (CatETipoVigor) cmbVigor.getSelectedItem();
            CERepoblado rep = new CERepoblado();
            rep.setSitioID(this.sitioID);
            rep.setRepobladoID(Integer.parseInt(registro));
            if (familia != null) {
                rep.setFamiliaID(familia.getFamiliaID());
            } else {
                rep.setFamiliaID(null);
            }
            if (generoIndex != null) {
                rep.setGeneroID(generoIndex.getGeneroID());
            } else {
                rep.setGeneroID(null);
            }
            if (especieIndex != null) {
                rep.setEspecieID(especieIndex.getEspecieID());
            } else {
                rep.setEspecieID(null);
            }
            if (indexInfraespecie != null) {
                rep.setInfraespecieID(indexInfraespecie.getInfraespecieID());
            } else {
                rep.setInfraespecieID(null);
            }
            rep.setNombreComun(txtNombreComun.getText());
            rep.setFrecuecia025150(this.frecuencia025);
            rep.setEdad025150(this.edad025);
            rep.setFrecuencia151275(this.frecuencia151);
            rep.setEdad151275(this.edad151);
            rep.setFrecuencia275(this.frecuencia275);
            rep.setEdad275(this.edad275);
            rep.setVigorID(vigor.getVigorID());
            rep.setDanioID(danio.getAgenteDanioID());
            rep.setPorcentajeDanio(this.porcentajeDanio);
            datosRepoblado.updateDatosRepoblado(rep);
            reiniciarRepoblado();
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla de repoblado"
                    + e.getClass().getName() + " : " + e.getMessage(), "Repoblado", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void eliminarRepoblado(){
        try {
            int fila = grdRepoblado.getSelectedRow();
            String registro = grdRepoblado.getValueAt(fila, 0).toString();
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Esta seguro de borrar el registro de repoblado?",
                    "Repoblado", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (respuesta == JOptionPane.YES_OPTION) {
                CERepoblado rep = new CERepoblado();
                rep.setRepobladoID(Integer.parseInt(registro));
                this.datosRepoblado.deleteDatosRepoblado(rep);
                reiniciarRepoblado();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla de repoblado"
                    + "", "Repoblado", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void reiniciarRepoblado() {
        cmbFamilia.setSelectedItem(null);
        cmbGenero.setSelectedItem(null);
        cmbEspecie.setSelectedItem(null);
        cmbInfraespecie.setSelectedItem(null);
        txtNombreComun.setText("");
        txtFrecuencia025.setValue(null);
        txtFrecuencia025.setText("");
        txtEdad025.setValue(null);
        txtEdad025.setText("");
        txtFrecuencia151.setValue(null);
        txtFrecuencia151.setText("");
        txtEdad151.setValue(null);
        txtEdad151.setText("");
        txtFrecuencia275.setValue(null);
        txtFrecuencia275.setText("");
        txtEdad275.setValue(null);
        txtEdad275.setText("");
        cmbVigor.setSelectedItem(null);
        cmbDanio.setSelectedIndex(0);
        txtPorcentajeDanio.setValue(null);
        txtPorcentajeDanio.setText("");
        cmbFamilia.requestFocus();
        txtClaveColecta.setText("");
    }

    private void seleccionarSiguienteFormulario(CESitio ceSitio) {
        Integer secuenciaID = ceSitio.getSecuencia();
        if (secuenciaID != null) {
            switch (secuenciaID) {
                case 1: //Módulo A
                    UPMForms.arbolado.setDatosIniciales(ceSitio);
                    UPMForms.arbolado.setVisible(true);
                    break;
                case 2: //Módulos A y C
                    UPMForms.arbolado.setDatosIniciales(ceSitio);
                    UPMForms.arbolado.setVisible(true);
                    break;
                case 3: //Modulos A, C, E y G
                    /*UPMForms.arboladoG.setDatosIniciales(ceSitio);
                    UPMForms.arboladoG.setVisible(true);*/
                    break;
                case 4: //Modulos A y E
                    UPMForms.arbolado.setDatosIniciales(ceSitio);
                    UPMForms.arbolado.setVisible(true);
                    break;
                case 5: //Modulos A, C, D y F
                    UPMForms.claveVegetacion.setDatosIniciales(ceSitio);
                    UPMForms.claveVegetacion.setVisible(true);
                    break;
                case 6://Modulos A, C y D
                    UPMForms.claveVegetacion.setDatosIniciales(ceSitio);
                    UPMForms.claveVegetacion.setVisible(true);
                    break;
                case 7://Modulos A, C, D y E
                    UPMForms.claveVegetacion.setDatosIniciales(ceSitio);
                    UPMForms.claveVegetacion.setVisible(true);
                    break;
                case 8://Modulos A, C, D, E y F
                    UPMForms.claveVegetacion.setDatosIniciales(ceSitio);
                    UPMForms.claveVegetacion.setVisible(true);
                    break;
                case 9://Modulos A, C y E
                    UPMForms.arbolado.setDatosIniciales(ceSitio);
                    UPMForms.arbolado.setVisible(true);
                    break;
                case 10://Modulos A, C, H
                    UPMForms.arbolado.setDatosIniciales(ceSitio);
                    UPMForms.arbolado.setVisible(true);
                    break;
                case 11://Modulos A y H
                    UPMForms.arbolado.setDatosIniciales(ceSitio);
                    UPMForms.arbolado.setVisible(true);
                    break;
                case 12://Modulos A, E y H
                    UPMForms.arbolado.setDatosIniciales(ceSitio);
                    UPMForms.arbolado.setVisible(true);
                    break;
                case 13://Modulos A, C, E y H
                    UPMForms.arbolado.setDatosIniciales(ceSitio);
                    UPMForms.arbolado.setVisible(true);
                    break;
                case 14://Modulos A, E y G
                    /* UPMForms.repobladoVM.setDatosIniciales(ceSitio);
                    UPMForms.repobladoVM.setVisible(true);*/
                    break;
                case 15://A y G
                    /* UPMForms.repobladoVM.setDatosIniciales(ceSitio);
                    UPMForms.repobladoVM.setVisible(true);*/
                    break;
            }
        }
    }
    
    private void revisarSiguienteFormulario(CESitio ceSitio){
        Integer secuenciaID = ceSitio.getSecuencia();
        if (secuenciaID != null) {
            switch (secuenciaID) {
                case 1: //Módulo A
                    UPMForms.arbolado.revisarArbolado(ceSitio);
                    UPMForms.arbolado.setVisible(true);
                    break;
                case 2: //Módulos A y C
                    UPMForms.arbolado.revisarArbolado(ceSitio);
                    UPMForms.arbolado.setVisible(true);
                    break;
                case 3: //Modulos A, C, E y G
                    /*UPMForms.arboladoG.setDatosIniciales(ceSitio);
                    UPMForms.arboladoG.setVisible(true);*/
                    break;
                case 4: //Modulos A y E
                    UPMForms.arbolado.revisarArbolado(ceSitio);
                    UPMForms.arbolado.setVisible(true);
                    break;
                case 5: //Modulos A, C, D y F
                    UPMForms.claveVegetacion.revisarClaveVegetacion(ceSitio);
                    UPMForms.claveVegetacion.setVisible(true);
                    break;
                case 6://Modulos A, C y D
                    UPMForms.claveVegetacion.revisarClaveVegetacion(ceSitio);
                    UPMForms.claveVegetacion.setVisible(true);
                    break;
                case 7://Modulos A, C, D y E
                    UPMForms.claveVegetacion.revisarClaveVegetacion(ceSitio);
                    UPMForms.claveVegetacion.setVisible(true);
                    break;
                case 8://Modulos A, C, D, E y F
                    UPMForms.claveVegetacion.revisarClaveVegetacion(ceSitio);
                    UPMForms.claveVegetacion.setVisible(true);
                    break;
                case 9://Modulos A, C y E
                    UPMForms.arbolado.revisarArbolado(ceSitio);
                    UPMForms.arbolado.setVisible(true);
                    break;
                case 10://Modulos A, C, H
                    UPMForms.arbolado.revisarArbolado(ceSitio);
                    UPMForms.arbolado.setVisible(true);
                    break;
                case 11://Modulos A y H
                    UPMForms.arbolado.revisarArbolado(ceSitio);
                    UPMForms.arbolado.setVisible(true);
                    break;
                case 12://Modulos A, E y H
                    UPMForms.arbolado.revisarArbolado(ceSitio);
                    UPMForms.arbolado.setVisible(true);
                    break;
                case 13://Modulos A, C, E y H
                    UPMForms.arbolado.revisarArbolado(ceSitio);
                    UPMForms.arbolado.setVisible(true);
                    break;
                case 14://Modulos A, E y G
                    /* UPMForms.repobladoVM.setDatosIniciales(ceSitio);
                    UPMForms.repobladoVM.setVisible(true);*/
                    break;
                case 15://A y G
                    /* UPMForms.repobladoVM.setDatosIniciales(ceSitio);
                    UPMForms.repobladoVM.setVisible(true);*/
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
        lblRegistroRepoblado = new javax.swing.JLabel();
        btnContinuar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        lbl025 = new javax.swing.JLabel();
        lblFrecuencia025 = new javax.swing.JLabel();
        lblEdad025 = new javax.swing.JLabel();
        lbl151 = new javax.swing.JLabel();
        lblFrecuencia151 = new javax.swing.JLabel();
        lblEdad151 = new javax.swing.JLabel();
        lbl275 = new javax.swing.JLabel();
        lblFrecuencia275 = new javax.swing.JLabel();
        lblEdad275 = new javax.swing.JLabel();
        lblVigor = new javax.swing.JLabel();
        cmbVigor = new javax.swing.JComboBox();
        lblDanio = new javax.swing.JLabel();
        cmbDanio = new javax.swing.JComboBox();
        lblPorcentajeDanio = new javax.swing.JLabel();
        txtFrecuencia025 = new javax.swing.JFormattedTextField();
        txtEdad025 = new javax.swing.JFormattedTextField();
        txtFrecuencia151 = new javax.swing.JFormattedTextField();
        txtEdad151 = new javax.swing.JFormattedTextField();
        txtFrecuencia275 = new javax.swing.JFormattedTextField();
        txtEdad275 = new javax.swing.JFormattedTextField();
        txtPorcentajeDanio = new javax.swing.JFormattedTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        grdRepoblado = new javax.swing.JTable();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
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
        chkRepobladoFuera = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        txtPorcentajeRepobladoFuera = new javax.swing.JFormattedTextField();
        chkRepoblado = new javax.swing.JCheckBox();
        lblClaveColecta = new javax.swing.JLabel();
        txtClaveColecta = new javax.swing.JTextField();
        btnColecta = new javax.swing.JButton();

        setMaximizable(true);
        setTitle("Repoblado (Sitio de 12.56m) módulo A "+version);
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

        lblRegistroRepoblado.setBackground(new java.awt.Color(153, 153, 153));
        lblRegistroRepoblado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblRegistroRepoblado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRegistroRepoblado.setText("Repoblado (Sitio de 12.56 m2)");
        lblRegistroRepoblado.setOpaque(true);

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

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbl025.setBackground(new java.awt.Color(153, 153, 153));
        lbl025.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl025.setText("0.25-1.50");
        lbl025.setOpaque(true);

        lblFrecuencia025.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFrecuencia025.setText("Frecuencia");
        lblFrecuencia025.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblEdad025.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEdad025.setText("Edad");

        lbl151.setBackground(new java.awt.Color(153, 153, 153));
        lbl151.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl151.setText("1.51-2.75");
        lbl151.setOpaque(true);

        lblFrecuencia151.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFrecuencia151.setText("Frecuencia");

        lblEdad151.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEdad151.setText("Edad");

        lbl275.setBackground(new java.awt.Color(153, 153, 153));
        lbl275.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl275.setText(">2.75");
        lbl275.setOpaque(true);

        lblFrecuencia275.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFrecuencia275.setText("Frecuencia");

        lblEdad275.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEdad275.setText("Edad");

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

        txtEdad025.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtEdad025.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtEdad025FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtEdad025FocusLost(evt);
            }
        });
        txtEdad025.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEdad025KeyTyped(evt);
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

        txtEdad151.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtEdad151.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtEdad151FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtEdad151FocusLost(evt);
            }
        });
        txtEdad151.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEdad151KeyTyped(evt);
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

        txtEdad275.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtEdad275.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtEdad275FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtEdad275FocusLost(evt);
            }
        });
        txtEdad275.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEdad275KeyTyped(evt);
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
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbl025, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblFrecuencia025, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                            .addComponent(txtFrecuencia025))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(lblEdad025, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEdad025)))))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblFrecuencia151, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFrecuencia151))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblEdad151, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                            .addComponent(txtEdad151))
                        .addGap(25, 25, 25))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lbl151, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblFrecuencia275, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFrecuencia275))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEdad275, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEdad275)))
                    .addComponent(lbl275, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(32, 32, 32)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbVigor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblVigor, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbDanio, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDanio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblPorcentajeDanio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPorcentajeDanio, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(lbl151)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblFrecuencia151)
                                    .addComponent(lblEdad151))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtFrecuencia151, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEdad151, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(lbl275)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblFrecuencia275)
                                    .addComponent(lblEdad275))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtFrecuencia275, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEdad275, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(lbl025)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblFrecuencia025)
                                    .addComponent(lblEdad025))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtFrecuencia025, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEdad025, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblVigor)
                            .addComponent(lblDanio)
                            .addComponent(lblPorcentajeDanio))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbVigor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbDanio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPorcentajeDanio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        grdRepoblado.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        grdRepoblado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        grdRepoblado.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        grdRepoblado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grdRepobladoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(grdRepoblado);

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
        btnEliminar.setNextFocusableComponent(btnContinuar);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblFamilia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblGenero)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEspecie)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbEspecie, 0, 100, Short.MAX_VALUE)
                .addGap(9, 9, 9)
                .addComponent(lblInfraespecie)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbInfraespecie, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNombreComun)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombreComun, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblFamilia)
                        .addComponent(cmbFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblGenero)
                        .addComponent(cmbGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblEspecie)
                            .addComponent(cmbEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblInfraespecie)
                            .addComponent(lblNombreComun)
                            .addComponent(txtNombreComun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbInfraespecie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        chkRepobladoFuera.setBackground(new java.awt.Color(204, 204, 204));
        chkRepobladoFuera.setSelected(true);
        chkRepobladoFuera.setText("¿Existe repoblado fuera del sitio de 12.56 m?");
        chkRepobladoFuera.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        chkRepobladoFuera.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                chkRepobladoFueraPropertyChange(evt);
            }
        });

        jLabel1.setText("Porcentaje del repoblado fuera del sitio de 12.56 m:");

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

        chkRepoblado.setBackground(new java.awt.Color(204, 204, 204));
        chkRepoblado.setSelected(true);
        chkRepoblado.setText("Repoblado");
        chkRepoblado.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        chkRepoblado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkRepobladoActionPerformed(evt);
            }
        });

        lblClaveColecta.setText("Clave:");

        txtClaveColecta.setEnabled(false);

        btnColecta.setText("Colecta");
        btnColecta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColectaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(177, Short.MAX_VALUE)
                .addComponent(chkRepobladoFuera)
                .addGap(39, 39, 39)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPorcentajeRepobladoFuera, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnContinuar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblUPM)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtUPM, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblSitio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtSitio, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(chkRepoblado)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 142, Short.MAX_VALUE)
                                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                                .addComponent(btnColecta)
                                .addGap(18, 18, 18)
                                .addComponent(lblClaveColecta)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtClaveColecta, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblRegistroRepoblado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                    .addComponent(txtUPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblRegistroRepoblado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnGuardar)
                        .addComponent(btnModificar)
                        .addComponent(btnEliminar)
                        .addComponent(chkRepoblado))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblClaveColecta)
                        .addComponent(txtClaveColecta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnColecta)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtPorcentajeRepobladoFuera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkRepobladoFuera))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnContinuar)
                    .addComponent(btnSalir))
                .addGap(14, 14, 14))
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

    private void txtNombreComunFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombreComunFocusGained
        txtNombreComun.selectAll();
    }//GEN-LAST:event_txtNombreComunFocusGained

    private void txtFrecuencia025FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFrecuencia025FocusGained
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                 txtFrecuencia025.selectAll();
            }
        });
    }//GEN-LAST:event_txtFrecuencia025FocusGained

    private void txtEdad025FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEdad025FocusGained
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                 txtEdad025.selectAll();
            }
        });
    }//GEN-LAST:event_txtEdad025FocusGained

    private void txtFrecuencia151FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFrecuencia151FocusGained
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                 txtFrecuencia151.selectAll();
            }
        });
    }//GEN-LAST:event_txtFrecuencia151FocusGained

    private void txtEdad151FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEdad151FocusGained
         SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                 txtEdad151.selectAll();
            }
        });
    }//GEN-LAST:event_txtEdad151FocusGained

    private void txtFrecuencia275FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFrecuencia275FocusGained
       SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                 txtFrecuencia275.selectAll();
            }
        });
    }//GEN-LAST:event_txtFrecuencia275FocusGained

    private void txtEdad275FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEdad275FocusGained
         SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                 txtEdad275.selectAll();
            }
        });
    }//GEN-LAST:event_txtEdad275FocusGained

    private void txtPorcentajeDanioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPorcentajeDanioFocusGained
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                 txtPorcentajeDanio.selectAll();
            }
        });
    }//GEN-LAST:event_txtPorcentajeDanioFocusGained

    private void txtFrecuencia025FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFrecuencia025FocusLost
        if(txtFrecuencia025.getText().isEmpty()){
           txtFrecuencia025.setValue(null);
        }
    }//GEN-LAST:event_txtFrecuencia025FocusLost

    private void txtEdad025FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEdad025FocusLost
       if(txtEdad025.getText().isEmpty()){
           txtEdad025.setValue(null);
        }
    }//GEN-LAST:event_txtEdad025FocusLost

    private void txtFrecuencia151FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFrecuencia151FocusLost
        if(txtFrecuencia151.getText().isEmpty()){
           txtFrecuencia151.setValue(null);
        }
    }//GEN-LAST:event_txtFrecuencia151FocusLost

    private void txtEdad151FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEdad151FocusLost
        if(txtEdad151.getText().isEmpty()){
           txtEdad151.setValue(null);
        }
    }//GEN-LAST:event_txtEdad151FocusLost

    private void txtFrecuencia275FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFrecuencia275FocusLost
        if(txtFrecuencia275.getText().isEmpty()){
           txtFrecuencia275.setValue(null);
        }
    }//GEN-LAST:event_txtFrecuencia275FocusLost

    private void txtEdad275FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEdad275FocusLost
        if(txtEdad275.getText().isEmpty()){
           txtEdad275.setValue(null);
        }
    }//GEN-LAST:event_txtEdad275FocusLost

    private void txtPorcentajeDanioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPorcentajeDanioFocusLost
        if(txtPorcentajeDanio.getText().isEmpty()){
           txtPorcentajeDanio.setValue(null);
        }
    }//GEN-LAST:event_txtPorcentajeDanioFocusLost

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
       asignarDatosRepoblado();
       if(validarRepobladoVacios() && validarDatosRepoblado()){
           crearRepoblado();
           this.datosRepoblado.enumerarConsecutivo(this.sitioID);
           llenarTabla();
           reiniciarRepoblado();
       }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        asignarDatosRepoblado();
        if (validarRepobladoVacios() && validarDatosRepoblado()) {
            actualizarRepoblado();
            llenarTabla();
            reiniciarRepoblado();
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminarRepoblado();
        this.datosRepoblado.enumerarConsecutivo(this.sitioID);
        llenarTabla();
        reiniciarRepoblado();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void grdRepobladoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grdRepobladoMouseClicked
       if (evt.getButton() == 1) {
            btnGuardar.setEnabled(true);
            int fila = grdRepoblado.getSelectedRow();
            String repoID = grdRepoblado.getValueAt(fila, 0).toString();
            this.repobladoID = Integer.parseInt(repoID);
            CERepoblado rep; 
            rep = datosRepoblado.getRegistroRepoblado(Integer.parseInt(repoID));
            
            CatEFamiliaEspecie fam = new CatEFamiliaEspecie();
            fam.setFamiliaID(rep.getFamiliaID());
            cmbFamilia.setSelectedItem(fam);
          
            CatEGenero gen = new CatEGenero();
            gen.setGeneroID(rep.getGeneroID());
            cmbGenero.setSelectedItem(gen);
            
            /*CatEGenero gen = new CatEGenero();
            gen.setGeneroID(rep.getGeneroID());
            cmbGenero.removeAllItems();
            fillCmbGenero(rep.getFamiliaID());
            cmbGenero.setSelectedItem(gen);*/
           
            CatEEspecie esp = new CatEEspecie();
            esp.setEspecieID(rep.getEspecieID());
            cmbEspecie.removeAllItems();
           fillCmbEspecie(rep.getGeneroID());
           cmbEspecie.setSelectedItem(esp);

           CatEInfraespecie inf = new CatEInfraespecie();
           inf.setInfraespecieID(rep.getInfraespecieID());
           cmbInfraespecie.removeAllItems();
           fillCmbInfraespecie(rep.getEspecieID());
           cmbInfraespecie.setSelectedItem(inf);

            txtNombreComun.setText(rep.getNombreComun());
            
            txtFrecuencia025.setText(String.valueOf(rep.getFrecuecia025150()));
            if((txtFrecuencia025.getText()).equals("0")) {
                txtFrecuencia025.setText("");
            }
            txtEdad025.setText(String.valueOf(rep.getEdad025150()));
            if(txtEdad025.getText().equals("0")) {
                txtEdad025.setText("");
            }
            txtFrecuencia151.setText(String.valueOf(rep.getFrecuencia151275()));
            if(txtFrecuencia151.getText().equals("0")) {
                txtFrecuencia151.setText("");
            }
            txtEdad151.setText(String.valueOf(rep.getEdad151275()));
            if(txtEdad151.getText().equals("0")){
                txtEdad151.setText("");
            }
            txtFrecuencia275.setText(String.valueOf(rep.getFrecuencia275()));
            if(txtFrecuencia275.getText().equals("0")){
                txtFrecuencia275.setText("");
            }
            txtEdad275.setText(String.valueOf(rep.getEdad275()));
            if(txtEdad275.getText().equals("0")){
                txtEdad275.setText("");
            }
            CatETipoVigor vig = new CatETipoVigor();
            vig.setVigorID(rep.getVigorID());
            cmbVigor.setSelectedItem(vig);
            
            CatEAgenteDanio danio = new CatEAgenteDanio();
            danio.setAgenteDanioID(rep.getDanioID());
            cmbDanio.setSelectedItem(danio);
            
            txtPorcentajeDanio.setText(String.valueOf(rep.getPorcentajeDanio()));
            if (txtPorcentajeDanio.getText().equals("0")) {
                txtPorcentajeDanio.setText("");
            }
            
            txtClaveColecta.setText(rep.getClaveColecta());
        }
    }//GEN-LAST:event_grdRepobladoMouseClicked

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

    private void txtPorcentajeRepobladoFueraFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPorcentajeRepobladoFueraFocusGained
       SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                 txtPorcentajeRepobladoFuera.selectAll();
            }
        });
    }//GEN-LAST:event_txtPorcentajeRepobladoFueraFocusGained

    private void btnContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuarActionPerformed
        if (validarRepobladoFuera()) {
            if (funciones.validarSeccionCapturada("TAXONOMIA_Repoblado", this.ceSitio) && chkRepoblado.isSelected()) {
                JOptionPane.showMessageDialog(null, "Si selecciona repoblado, se debe capturar", "Repoblado", JOptionPane.INFORMATION_MESSAGE);
                chkRepoblado.requestFocus();
            } else if (funciones.validarSeccionCapturada("TAXONOMIA_Repoblado", this.ceSitio) == false && chkRepoblado.isSelected()) {
                if (validarColectasObligatorias()) {
                    asignarDatosRepobladoFuera();
                    crearRepobladoFuera();
                    this.cdSecuencia.updateSecuencia(this.ceSitio, FORMATO_ID, 1);
                    this.hide();
                    if (this.actulizar == 0) {
                        seleccionarSiguienteFormulario(this.ceSitio);
                    } else {
                        revisarSiguienteFormulario(this.ceSitio);
                    }
                }
            } else if (funciones.validarSeccionCapturada("TAXONOMIA_Repoblado", this.ceSitio) == true && !chkRepoblado.isSelected()) {
                asignarDatosRepobladoFuera();
                crearRepobladoFuera();
                this.cdSecuencia.updateSecuencia(this.ceSitio, FORMATO_ID, -1);
                this.hide();
                if (this.actulizar == 0) {
                    seleccionarSiguienteFormulario(this.ceSitio);
                } else {
                    revisarSiguienteFormulario(this.ceSitio);
                }
            }
        }
    }//GEN-LAST:event_btnContinuarActionPerformed

    private void chkRepobladoFueraPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_chkRepobladoFueraPropertyChange
        if (chkRepobladoFuera.isSelected()) {
            txtPorcentajeRepobladoFuera.setEnabled(true);
        } else {
            txtPorcentajeRepobladoFuera.setEnabled(false);
            txtPorcentajeRepobladoFuera.setText("");
            txtPorcentajeRepobladoFuera.setValue(null);
        }
    }//GEN-LAST:event_chkRepobladoFueraPropertyChange

    private void cmbGeneroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbGeneroActionPerformed
        CatEGenero indexGenero = (CatEGenero) cmbGenero.getSelectedItem();
        DefaultComboBoxModel dcm = (DefaultComboBoxModel) cmbEspecie.getModel();
        dcm.removeAllElements();
        if (indexGenero != null) {
            fillCmbEspecie(indexGenero.getGeneroID());
        }
    }//GEN-LAST:event_cmbGeneroActionPerformed

    private void txtFrecuencia025KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFrecuencia025KeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtFrecuencia025KeyTyped

    private void txtEdad025KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEdad025KeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtEdad025KeyTyped

    private void txtFrecuencia151KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFrecuencia151KeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtFrecuencia151KeyTyped

    private void txtEdad151KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEdad151KeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtEdad151KeyTyped

    private void txtFrecuencia275KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFrecuencia275KeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtFrecuencia275KeyTyped

    private void txtEdad275KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEdad275KeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtEdad275KeyTyped

    private void txtPorcentajeDanioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPorcentajeDanioKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtPorcentajeDanioKeyTyped

    private void txtPorcentajeRepobladoFueraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPorcentajeRepobladoFueraKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtPorcentajeRepobladoFueraKeyTyped

    private void chkRepobladoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkRepobladoActionPerformed
        if (chkRepoblado.isSelected()) {
            cmbFamilia.setEnabled(true);
            cmbGenero.setEnabled(true);
            cmbEspecie.setEnabled(true);
            cmbInfraespecie.setEnabled(true);
            txtNombreComun.setEnabled(true);
            txtFrecuencia025.setEnabled(true);
            txtEdad025.setEnabled(true);
            txtFrecuencia151.setEnabled(true);
            txtEdad151.setEnabled(true);
            txtFrecuencia275.setEnabled(true);
            txtEdad275.setEnabled(true);
            cmbVigor.setEnabled(true);
            cmbDanio.setEnabled(true);
            txtPorcentajeDanio.setEnabled(true);
        } else {
             Object[] opciones = {"Si", "No"};
            int respuesta = JOptionPane.showOptionDialog(null, "Si capturó, se eliminará la información de repoblado del sitio " + this.sitio + ", ¿Esta seguro?",
                    "Repoblado", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[1]);
            if (respuesta == JOptionPane.YES_OPTION) {
                datosRepoblado.deleteRepobladoSitio(this.sitioID);
                funciones.reiniciarTabla(this.grdRepoblado);
                llenarTabla();
                reiniciarRepoblado();
                cmbFamilia.setEnabled(false);
                cmbFamilia.setSelectedItem(null);
                cmbGenero.setEnabled(false);
                cmbGenero.setSelectedItem(null);
                cmbEspecie.setEnabled(false);
                cmbEspecie.setSelectedItem(null);
                cmbInfraespecie.setEnabled(false);
                cmbInfraespecie.setSelectedItem(null);
                txtNombreComun.setEnabled(false);
                txtNombreComun.setText("");
                txtFrecuencia025.setEnabled(false);
                txtFrecuencia025.setValue(null);
                txtEdad025.setEnabled(false);
                txtEdad025.setValue(null);
                txtFrecuencia151.setEnabled(false);
                txtFrecuencia151.setValue(null);
                txtEdad151.setEnabled(false);
                txtEdad151.setValue(null);
                txtFrecuencia275.setEnabled(false);
                txtFrecuencia275.setEnabled(false);
                txtEdad275.setEnabled(false);
                txtEdad275.setValue(null);
                cmbVigor.setEnabled(false);
                cmbVigor.setSelectedItem(null);
                cmbDanio.setEnabled(false);
                cmbDanio.setSelectedItem(null);
                txtPorcentajeDanio.setEnabled(false);
                txtPorcentajeDanio.setValue(null);    
            } else {
                chkRepoblado.setSelected(true);
            }
        }
    }//GEN-LAST:event_chkRepobladoActionPerformed

    private void cmbDanioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDanioActionPerformed
        CatEAgenteDanio danio = (CatEAgenteDanio) cmbDanio.getSelectedItem();
        
        if (danio != null) {
            if (danio.getAgenteDanioID() == 1) {
                txtPorcentajeDanio.setEnabled(false);
                txtPorcentajeDanio.setText("");
                txtPorcentajeDanio.setValue(null);
            } else {
                txtPorcentajeDanio.setEnabled(true);
            }
        }else{
                txtPorcentajeDanio.setEnabled(false);
                txtPorcentajeDanio.setText("");
                txtPorcentajeDanio.setValue(null);
        }
    }//GEN-LAST:event_cmbDanioActionPerformed

    private void btnColectaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnColectaActionPerformed
        try {
            int fila = grdRepoblado.getSelectedRow();
            String consecutivo = grdRepoblado.getValueAt(fila, 2).toString();
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
            claveColecta.setDatosIniciales(ceColecta, FORMATO_ID, "TAXONOMIA_Repoblado", "Consecutivo", this.sitioID, Integer.parseInt(consecutivo));
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
    private javax.swing.JButton btnColecta;
    private javax.swing.JButton btnContinuar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JCheckBox chkRepoblado;
    private javax.swing.JCheckBox chkRepobladoFuera;
    private javax.swing.JComboBox cmbDanio;
    private javax.swing.JComboBox cmbEspecie;
    private javax.swing.JComboBox cmbFamilia;
    private javax.swing.JComboBox cmbGenero;
    private javax.swing.JComboBox cmbInfraespecie;
    private javax.swing.JComboBox cmbVigor;
    private javax.swing.JTable grdRepoblado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl025;
    private javax.swing.JLabel lbl151;
    private javax.swing.JLabel lbl275;
    private javax.swing.JLabel lblClaveColecta;
    private javax.swing.JLabel lblDanio;
    private javax.swing.JLabel lblEdad025;
    private javax.swing.JLabel lblEdad151;
    private javax.swing.JLabel lblEdad275;
    private javax.swing.JLabel lblEspecie;
    private javax.swing.JLabel lblFamilia;
    private javax.swing.JLabel lblFrecuencia025;
    private javax.swing.JLabel lblFrecuencia151;
    private javax.swing.JLabel lblFrecuencia275;
    private javax.swing.JLabel lblGenero;
    private javax.swing.JLabel lblInfraespecie;
    private javax.swing.JLabel lblNombreComun;
    private javax.swing.JLabel lblPorcentajeDanio;
    private javax.swing.JLabel lblRegistroRepoblado;
    private javax.swing.JLabel lblSitio;
    private javax.swing.JLabel lblUPM;
    private javax.swing.JLabel lblVigor;
    public javax.swing.JTextField txtClaveColecta;
    private javax.swing.JFormattedTextField txtEdad025;
    private javax.swing.JFormattedTextField txtEdad151;
    private javax.swing.JFormattedTextField txtEdad275;
    private javax.swing.JFormattedTextField txtFrecuencia025;
    private javax.swing.JFormattedTextField txtFrecuencia151;
    private javax.swing.JFormattedTextField txtFrecuencia275;
    private javax.swing.JTextField txtNombreComun;
    private javax.swing.JFormattedTextField txtPorcentajeDanio;
    private javax.swing.JFormattedTextField txtPorcentajeRepobladoFuera;
    private javax.swing.JTextField txtSitio;
    private javax.swing.JTextField txtUPM;
    // End of variables declaration//GEN-END:variables
}
