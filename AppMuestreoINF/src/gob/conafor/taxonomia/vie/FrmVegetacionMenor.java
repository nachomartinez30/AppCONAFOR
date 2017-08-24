package gob.conafor.taxonomia.vie;

import gob.conafor.ini.vie.Main;
import gob.conafor.sitio.mod.CESitio;
import gob.conafor.sys.mod.CDSecuencia;
import gob.conafor.sys.mod.CDSeguimientoUPM;
import gob.conafor.sys.mod.CESeccionesCapturadas;
import gob.conafor.taxonomia.mod.CDColectaBotanica;
import gob.conafor.taxonomia.mod.CDCondicionTaxonomica;
import gob.conafor.taxonomia.mod.CDDanioVM;
import gob.conafor.taxonomia.mod.CDEspecies;
import gob.conafor.taxonomia.mod.CDVegetacionMenor;
import gob.conafor.taxonomia.mod.CEColectaBotanica;
import gob.conafor.taxonomia.mod.CEDanioSeveridad;
import gob.conafor.taxonomia.mod.CEVegetacionMenor;
import gob.conafor.taxonomia.mod.CatEAgenteDanio;
import gob.conafor.taxonomia.mod.CatECondicionVM;
import gob.conafor.taxonomia.mod.CatEEspecie;
import gob.conafor.taxonomia.mod.CatEFamiliaEspecie;
import gob.conafor.taxonomia.mod.CatEFormaVidaZA;
import gob.conafor.taxonomia.mod.CatEGenero;
import gob.conafor.taxonomia.mod.CatEInfraespecie;
import gob.conafor.taxonomia.mod.CatEPorcentajeArbolado;
import gob.conafor.taxonomia.mod.CatESeveridadZA;
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

public class FrmVegetacionMenor extends javax.swing.JInternalFrame {
    private boolean revision;
    private int sitio;
    private int vegetacionMenorID;
    private int sitioID;
    private int upmID;
    private static final int FORMATO_ID = 21;
    private final int vegetacionMenor;
    private final int vegetacionMayor;
    private CDEspecies especie = new CDEspecies();
    private CDCondicionTaxonomica condicion = new CDCondicionTaxonomica();
    private ValidacionesComunes validacion = new ValidacionesComunes();
    private CDVegetacionMenor cdVegetacion = new CDVegetacionMenor();
    private Integer numero0110;
    private Integer numero1125;
    private Integer numero2650;
    private Integer numero5175;
    private Integer numero76100;
    private Integer numero101125;
    private Integer numero126150;
    private Integer numero150;
    private Integer porcentajeCobertura;
    private CESitio ceSitio = new CESitio();
    private CESeccionesCapturadas seccionActual = new CESeccionesCapturadas();
    private CESeccionesCapturadas seccionSiguiente = new CESeccionesCapturadas();
    private CDSeguimientoUPM seguimiento = new CDSeguimientoUPM();
    private FuncionesComunes combo = new FuncionesComunes();
    private Datos numeros = new Datos();
    private CDEspecies cdEspecies = new CDEspecies();
    private CDSecuencia cdSecuencia = new CDSecuencia();
    private FuncionesComunes funciones = new FuncionesComunes();
    private int actualizar;
    private Version ver=new Version();
    private String version=ver.getVersion();

    public FrmVegetacionMenor() {
        initComponents();
        this.vegetacionMenor = 31;
        this.vegetacionMayor = 32;
        fillCmbFamilia();
        fillCmbGenero();
        fillCmbFormaVida();
        fillCmbAgenteDanio1();
        fillCmbAgenteDanio2();
        fillCmbVigor();
        fillCondicion();
        fillCmbSeveridad1();
        fillCmbSeveridad2();
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
        this.actualizar = 0;
        funciones.manipularBotonesMenuPrincipal(true);
        limpiarControles();
        
    }

    public void revisarVegetacionMenor(CESitio ceSitio) {
        revision=true;
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
        this.actualizar = 1;
        cmbSeveridad1.setEnabled(true);
        cmbSeveridad2.setEnabled(true);
        fillCmbSeveridad1();
        fillCmbSeveridad2();
        funciones.manipularBotonesMenuPrincipal(true);
        chkVegetacionMenor.setEnabled(funciones.habilitarCheckBox("TAXONOMIA_VegetacionMenor", this.sitioID));
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

    public void fillCmbFormaVida() {
        List<CatEFormaVidaZA> listFormaVida = new ArrayList<>();
        listFormaVida = condicion.getFormaVidaZA();
        if (listFormaVida != null) {
            int size = listFormaVida.size();
            for (int i = 0; i < size; i++) {
                cmbFormaVida.addItem(listFormaVida.get(i));
            }
        }
    }

    public void fillCondicion() {
        List<CatECondicionVM> listCondicionVM = new ArrayList<>();
        listCondicionVM = condicion.getCondicionVM();
        if (listCondicionVM != null) {
            int size = listCondicionVM.size();
            for (int i = 0; i < size; i++) {
                cmbCondicion.addItem(listCondicionVM.get(i));
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

    public void fillCmbSeveridad1() {
        cmbSeveridad1.removeAllItems();
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
        cmbSeveridad2.removeAllItems();
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
        List<CatETipoVigor> listVigor = new ArrayList<>();
        listVigor = condicion.getVigorSotobosqueRepoblado();
        if (listVigor != null) {
            int size = listVigor.size();
            for (int i = 0; i < size; i++) {
                cmbVigor.addItem(listVigor.get(i));
            }
        }
    }

    public void llenarTabla() {
        grdVegetacionMenor.setModel(cdVegetacion.getTableVegetacionMenor(this.sitioID));

        grdVegetacionMenor.getColumnModel().getColumn(2).setPreferredWidth(75);//Consecutivo
        grdVegetacionMenor.getColumnModel().getColumn(3).setPreferredWidth(120);//familia
        grdVegetacionMenor.getColumnModel().getColumn(4).setPreferredWidth(120);//Genero
        grdVegetacionMenor.getColumnModel().getColumn(5).setPreferredWidth(120);//Especie
        grdVegetacionMenor.getColumnModel().getColumn(6).setPreferredWidth(120);//Infraespecie
        grdVegetacionMenor.getColumnModel().getColumn(7).setPreferredWidth(120);//Nombre comun
        grdVegetacionMenor.getColumnModel().getColumn(8).setPreferredWidth(300);// Forma de vida
        grdVegetacionMenor.getColumnModel().getColumn(9).setPreferredWidth(150);//Condición
        grdVegetacionMenor.getColumnModel().getColumn(10).setPreferredWidth(50);//Numero0110
        grdVegetacionMenor.getColumnModel().getColumn(11).setPreferredWidth(50);//Numero1125 
        grdVegetacionMenor.getColumnModel().getColumn(12).setPreferredWidth(50);//Numero2650
        grdVegetacionMenor.getColumnModel().getColumn(13).setPreferredWidth(50);//Numero5175
        grdVegetacionMenor.getColumnModel().getColumn(14).setPreferredWidth(50);//Numero76100
        grdVegetacionMenor.getColumnModel().getColumn(15).setPreferredWidth(50);//Numero101125
        grdVegetacionMenor.getColumnModel().getColumn(16).setPreferredWidth(50);//Numero126150
        grdVegetacionMenor.getColumnModel().getColumn(17).setPreferredWidth(50);//Numero150
        grdVegetacionMenor.getColumnModel().getColumn(18).setPreferredWidth(75);//PorcentajeCobertura
        grdVegetacionMenor.getColumnModel().getColumn(19).setPreferredWidth(85);//Agente1
        grdVegetacionMenor.getColumnModel().getColumn(20).setPreferredWidth(75);//Severidad1
        grdVegetacionMenor.getColumnModel().getColumn(21).setPreferredWidth(85);//Agente2
        grdVegetacionMenor.getColumnModel().getColumn(22).setPreferredWidth(75);//Severidad2
        grdVegetacionMenor.getColumnModel().getColumn(23).setPreferredWidth(120);//Vigor
        grdVegetacionMenor.getColumnModel().getColumn(24).setPreferredWidth(180);//Clave colecta
      
        Tablas tabla = new Tablas();
        int[] columna_0 = {0};
        int[] column_1 = {1};
        //Ocultar campo ID
        tabla.hideColumnTable(grdVegetacionMenor, columna_0);
        tabla.hideColumnTable(grdVegetacionMenor, column_1);
    }

    private boolean validarVegetacionMenorObligatorios() {
        if(cmbFormaVida.getSelectedItem() == null){
             JOptionPane.showMessageDialog(null, "Error! Debe seleccionar una forma de vida  ", "Vegetación menor", JOptionPane.INFORMATION_MESSAGE);
             cmbFormaVida.requestFocus();
             return false;
        } else if(cmbCondicion.getSelectedItem() == null){
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar una condicion  ", "Vegetación menor", JOptionPane.INFORMATION_MESSAGE);
             cmbCondicion.requestFocus();
             return false; 
        } else if(txtPorcentajeCobertura.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar porcentaje de cobertura ", "Vegetación menor", JOptionPane.INFORMATION_MESSAGE);
            txtPorcentajeCobertura.requestFocus();
            return false;
        } else if(cmbVigor.getSelectedItem() == null){
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar un vigor  ", "Vegetación menor", JOptionPane.INFORMATION_MESSAGE);
             cmbVigor.requestFocus();
             return false;
        } else {
            return true;
        }
    }

    public void asignarDatosVegetacionMenor() {
        try {
            this.numero0110 = Integer.valueOf(txtNumero0110.getText());
        } catch (NumberFormatException e) {
            this.numero0110 = null;
        }
        try {
            this.numero1125 = Integer.valueOf(txtNumero1125.getText());
        } catch (NumberFormatException e) {
            this.numero1125 = null;
        }
        try {
            this.numero2650 = Integer.valueOf(txtNumero2650.getText());
        } catch (NumberFormatException e) {
            this.numero2650 = null;
        }
        try {
            this.numero5175 = Integer.valueOf(txtNumero5175.getText());
        } catch (NumberFormatException e) {
            this.numero5175 = null;
        }
        try {
            this.numero76100 = Integer.valueOf(txtNumero76100.getText());
        } catch (NumberFormatException e) {
            this.numero76100 = null;
        }
        try {
            this.numero101125 = Integer.valueOf(txtNumero101125.getText());
        } catch (NumberFormatException e) {
            this.numero101125 = null;
        }
        try {
            this.numero126150 = Integer.valueOf(txtNumero126150.getText());
        } catch (NumberFormatException e) {
            this.numero126150 = null;
        }
        try {
            this.numero150 = Integer.valueOf(txtNumero150.getText());
        } catch (NumberFormatException e) {
            this.numero150 = null;
        }
        try {
            this.porcentajeCobertura = Integer.valueOf(txtPorcentajeCobertura.getText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            this.porcentajeCobertura = null;
        }
    }

    private void crearVegetacionMenor() {
        CatEFamiliaEspecie indexFamilia = (CatEFamiliaEspecie) cmbFamilia.getSelectedItem();
        CatEGenero indexGenero = (CatEGenero) cmbGenero.getSelectedItem();
        CatEEspecie indexEspecie = (CatEEspecie) cmbEspecie.getSelectedItem();
        CatEInfraespecie indexInfraespecie = (CatEInfraespecie) cmbInfraespecie.getSelectedItem();
        CatEFormaVidaZA indexFormaVida = (CatEFormaVidaZA) cmbFormaVida.getSelectedItem();
        CatECondicionVM indexCondicion = (CatECondicionVM) cmbCondicion.getSelectedItem();
        CatETipoVigor indexvigor = (CatETipoVigor) cmbVigor.getSelectedItem();
        CDVegetacionMenor cdVegetacion = new CDVegetacionMenor();
        CEVegetacionMenor ceVegetacion = new CEVegetacionMenor();
        ceVegetacion.setSitioID(this.sitioID);
        if (indexFamilia != null) {
            ceVegetacion.setFamiliaID(indexFamilia.getFamiliaID());
        }
        if (indexGenero != null) {
            ceVegetacion.setGeneroID(indexGenero.getGeneroID());
        }
        if (indexEspecie != null) {
            ceVegetacion.setEspecieID(indexEspecie.getEspecieID());
        }
        if (indexInfraespecie != null) {
            ceVegetacion.setInfraespecieID(indexInfraespecie.getInfraespecieID());
        }
        ceVegetacion.setNombreComun(txtNombreComun.getText());
        ceVegetacion.setFormaVidaID(indexFormaVida.getFormaVidaZAID());
        ceVegetacion.setCondicionID(indexCondicion.getCondicionVMID());
        ceVegetacion.setNumero0110(this.numero0110);
        ceVegetacion.setNumero1125(this.numero1125);
        ceVegetacion.setNumero2650(this.numero2650);
        ceVegetacion.setNumero5175(this.numero5175);
        ceVegetacion.setNumero76100(this.numero76100);
        ceVegetacion.setNumero101125(this.numero101125);
        ceVegetacion.setNumero126150(this.numero126150);
        ceVegetacion.setNumero150(this.numero150);
        ceVegetacion.setPorcentajeCobertura(this.porcentajeCobertura);
        ceVegetacion.setVigorID(indexvigor.getVigorID());

        cdVegetacion.insertVegetacionMenor(ceVegetacion);
        this.vegetacionMenorID = cdVegetacion.getLastIndexInsertedVegetacionMenor();
        crearDanios(this.vegetacionMenorID);

    }

    private void crearDanios(int vegetacionMenorID) {
        CEDanioSeveridad ceDanio1 = new CEDanioSeveridad();
        CEDanioSeveridad ceDanio2 = new CEDanioSeveridad();
        CDDanioVM cdDanio = new CDDanioVM();
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

        ceDanio1.setSeccionID(vegetacionMenorID);
        ceDanio1.setNumeroDanio(1);
        ceDanio1.setAgenteDanioID(danio1);
        ceDanio1.setSeveridadID(severidad1);

        cdDanio.insertDanio(ceDanio1);

        ceDanio2.setSeccionID(vegetacionMenorID);
        ceDanio2.setNumeroDanio(2);
        ceDanio2.setAgenteDanioID(danio2);
        ceDanio2.setSeveridadID(severidad2);

        cdDanio.insertDanio(ceDanio2);
    }

    private void actualizarVegetacionMenor() {
        try {
            int fila = grdVegetacionMenor.getSelectedRow();
            String registro = grdVegetacionMenor.getValueAt(fila, 0).toString();
            this.vegetacionMenorID = Integer.parseInt(registro);
            CatEFamiliaEspecie familia = (CatEFamiliaEspecie) cmbFamilia.getSelectedItem();
            CatEGenero generoIndex = (CatEGenero) cmbGenero.getSelectedItem();
            CatEEspecie especieIndex = (CatEEspecie) cmbEspecie.getSelectedItem();
            CatEInfraespecie indexInfraespecie = (CatEInfraespecie) cmbInfraespecie.getSelectedItem();
            CatEFormaVidaZA indexFormaVida = (CatEFormaVidaZA) cmbFormaVida.getSelectedItem();
            CatECondicionVM indexCondcion = (CatECondicionVM) cmbCondicion.getSelectedItem();
            CEDanioSeveridad ceDanio1 = new CEDanioSeveridad();
            CEDanioSeveridad ceDanio2 = new CEDanioSeveridad();
            CDDanioVM cdDanio = new CDDanioVM();
            CatEAgenteDanio indexAgenteDanio1 = (CatEAgenteDanio) cmbAgenteDanio1.getSelectedItem();
            CatEAgenteDanio indexAgenteDanio2 = (CatEAgenteDanio) cmbAgenteDanio2.getSelectedItem();
            CatESeveridadZA indexSeveridad1 = (CatESeveridadZA) cmbSeveridad1.getSelectedItem();
            CatESeveridadZA indexSeveridad2 = (CatESeveridadZA) cmbSeveridad2.getSelectedItem();
            CatETipoVigor indexVigor = (CatETipoVigor) cmbVigor.getSelectedItem();
            CEVegetacionMenor ceVegetacionMenor = new CEVegetacionMenor();
            ceVegetacionMenor.setVegetacionMenorID(this.vegetacionMenorID);
            ceVegetacionMenor.setSitioID(this.sitioID);
            if (familia != null) {
                ceVegetacionMenor.setFamiliaID(familia.getFamiliaID());
            } else {
                ceVegetacionMenor.setFamiliaID(null);
            }
            if (generoIndex != null) {
                ceVegetacionMenor.setGeneroID(generoIndex.getGeneroID());
            } else {
                ceVegetacionMenor.setGeneroID(null);
            }
            if (especieIndex != null) {
                ceVegetacionMenor.setEspecieID(especieIndex.getEspecieID());
            } else {
                ceVegetacionMenor.setEspecieID(null);
            }
            if(indexInfraespecie != null) {
                ceVegetacionMenor.setInfraespecieID(indexInfraespecie.getInfraespecieID());
            } else {
                ceVegetacionMenor.setInfraespecieID(null);
            }
            ceVegetacionMenor.setNombreComun(txtNombreComun.getText());
            ceVegetacionMenor.setFormaVidaID(indexFormaVida.getFormaVidaZAID());
            ceVegetacionMenor.setCondicionID(indexCondcion.getCondicionVMID());
            ceVegetacionMenor.setNumero0110(this.numero0110);
            ceVegetacionMenor.setNumero1125(this.numero1125);
            ceVegetacionMenor.setNumero2650(this.numero2650);
            ceVegetacionMenor.setNumero5175(this.numero5175);
            ceVegetacionMenor.setNumero76100(this.numero76100);
            ceVegetacionMenor.setNumero101125(this.numero101125);
            ceVegetacionMenor.setVigorID(indexVigor.getVigorID());
            ceVegetacionMenor.setPorcentajeCobertura(this.porcentajeCobertura);
            ceDanio1.setSeccionID(this.vegetacionMenorID);
            ceDanio1.setNumeroDanio(1);
            ceDanio1.setAgenteDanioID(indexAgenteDanio1.getAgenteDanioID());
            if (indexSeveridad1 != null) {
                ceDanio1.setSeveridadID(indexSeveridad1.getSeveridadID());
            }

            ceDanio2.setSeccionID(this.vegetacionMenorID);
            ceDanio2.setNumeroDanio(2);
            ceDanio2.setAgenteDanioID(indexAgenteDanio2.getAgenteDanioID());
            if (indexSeveridad2 != null) {
                ceDanio2.setSeveridadID(indexSeveridad2.getSeveridadID());
            }

            cdDanio.updateDanio(ceDanio1);

            cdDanio.updateDanio(ceDanio2);

            cdVegetacion.updateVegetacionMenor(ceVegetacionMenor);

            limpiarControles();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla de repoblado vegetación menor "
                    + e.getClass().getName() + " : " + e.getMessage(), "Repoblado", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private boolean validarMediciones() {
        if (this.numero0110 != null && this.numero0110 < 0) {
            JOptionPane.showMessageDialog(null, "Error! Debe de capturar un valor mayor o igual a 0 para numero de plantas 01-10 ", "Vegetación menor", JOptionPane.INFORMATION_MESSAGE);
            txtNumero0110.requestFocus();
            return false;
        } else if (this.numero1125 != null && this.numero1125 < 0) {
            JOptionPane.showMessageDialog(null, "Error! Debe de capturar un valor mayor o igual a 0  para numero de plantas 11-25 ", "Vegetación menor", JOptionPane.INFORMATION_MESSAGE);
            txtNumero1125.requestFocus();
            return false;
        } else if (this.numero2650 != null && this.numero2650 < 0) {
            JOptionPane.showMessageDialog(null, "Error! Debe de capturar un valor mayor o igual a 0  para numero de plantas 26-50 ", "Vegetación menor", JOptionPane.INFORMATION_MESSAGE);
            txtNumero2650.requestFocus();
            return false;
        } else if (this.numero5175 != null && this.numero5175 < 0) {
            JOptionPane.showMessageDialog(null, "Error! Debe de capturar un valor mayor o igual a 0  para numero de plantas 51-75 ", "Vegetación menor", JOptionPane.INFORMATION_MESSAGE);
            txtNumero5175.requestFocus();
            return false;
        } else if (this.numero76100 != null && this.numero76100 < 0) {
            JOptionPane.showMessageDialog(null, "Error! Debe de capturar un valor mayor o igual a 0  para numero de plantas 76-100 ", "Vegetación menor", JOptionPane.INFORMATION_MESSAGE);
            txtNumero76100.requestFocus();
            return false;
        } else if (this.numero101125 != null && this.numero101125 < 0) {
            JOptionPane.showMessageDialog(null, "Error! Debe de capturar un valor mayor o igual a 0  para numero de plantas 101-125 ", "Vegetación menor", JOptionPane.INFORMATION_MESSAGE);
            txtNumero101125.requestFocus();
            return false;
        } else if (this.numero126150 != null && this.numero126150 < 0) {
            JOptionPane.showMessageDialog(null, "Error! Debe de capturar un valor mayor o igual a 0  para numero de plantas 126-150 ", "Vegetación menor", JOptionPane.INFORMATION_MESSAGE);
            txtNumero126150.requestFocus();
            return false;
        } else if (this.numero150 != null && this.numero150 < 0) {
            JOptionPane.showMessageDialog(null, "Error! Debe de capturar un valor mayor o igual a 0  para numero de plantas 150 ", "Vegetación menor", JOptionPane.INFORMATION_MESSAGE);
            txtNumero150.requestFocus();
            return false;
        } else if (this.porcentajeCobertura != null && this.porcentajeCobertura < 0 || this.porcentajeCobertura > 100) {
            JOptionPane.showMessageDialog(null, "Error! Debe de capturar un valor entre 0 y 100  para porcentaje de cobertura ", "Vegetación menor", JOptionPane.INFORMATION_MESSAGE);
            txtPorcentajeCobertura.requestFocus();
            return false;
        
        }else {
            return true;
        }
    }
    
    private void eliminarVegetacionMenor() {
        try {
            int fila = grdVegetacionMenor.getSelectedRow();
            String registro = grdVegetacionMenor.getValueAt(fila, 0).toString();
            CDDanioVM cdDanio = new CDDanioVM();
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Esta seguro de borrar el registro de vegetación menor?",
                    "Vegetación menor", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (respuesta == JOptionPane.YES_OPTION) {
                 this.cdVegetacion.deleteVegetacionMenor(Integer.parseInt(registro));
                cdDanio.deleteDanio(Integer.parseInt(registro));
                limpiarControles();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla de vegetación menor "
                    + "", "Vegetación menor", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void limpiarControles() {
        cmbFamilia.setSelectedItem(null);
        cmbGenero.setSelectedItem(null);
        cmbEspecie.setSelectedItem(null);
        cmbInfraespecie.setSelectedItem(null);
        txtNombreComun.setText("");
        cmbFormaVida.setSelectedItem(null);
        cmbCondicion.setSelectedItem(null);
        txtNumero0110.setValue(null);
        txtNumero0110.setText("");
        txtNumero1125.setValue(null);
        txtNumero1125.setText("");
        txtNumero2650.setValue(null);
        txtNumero2650.setText("");
        txtNumero5175.setValue(null);
        txtNumero5175.setText("");
        txtNumero76100.setValue(null);
        txtNumero76100.setText("");
        txtNumero101125.setValue(null);
        txtNumero101125.setText("");
        txtNumero126150.setValue(null);
        txtNumero126150.setText("");
        txtNumero150.setValue(null);
        txtNumero150.setText("");
        txtPorcentajeCobertura.setText("");
        cmbAgenteDanio1.setSelectedIndex(0);
        cmbSeveridad1.setSelectedItem(null);
        cmbAgenteDanio2.setSelectedIndex(0);
        cmbSeveridad2.setSelectedItem(null);
        cmbVigor.setSelectedItem(null);
        txtClaveColecta.setText("");
    }
    
    private boolean validarSeveridadDanio(){
        if(cmbAgenteDanio1.getSelectedIndex()!=0 && cmbSeveridad1.getSelectedItem() == null){
            JOptionPane.showMessageDialog(null, "Error! Debe de seleccionar un nivel de severidad 1 ", "Vegetacion menor", JOptionPane.INFORMATION_MESSAGE);
            cmbSeveridad1.requestFocus();
            return false;
        } else if(cmbAgenteDanio2.getSelectedIndex()!=0 && cmbSeveridad2.getSelectedItem() == null){
            JOptionPane.showMessageDialog(null, "Error! Debe de seleccionar un nivel de severidad 2 ", "Vegetacion menor", JOptionPane.INFORMATION_MESSAGE);
            cmbSeveridad2.requestFocus();
            return false;
        } else {
            return true;
        }
    }
    
    private boolean validarCondicionMuertoPie(){
        CatECondicionVM condicionVM = (CatECondicionVM) cmbCondicion.getSelectedItem();
        CatEAgenteDanio danio1 = (CatEAgenteDanio) cmbAgenteDanio1.getSelectedItem();
        if(condicionVM.getCondicionVMID() == 2 && danio1.getAgenteDanioID() == 1){
            JOptionPane.showMessageDialog(null, "Error! Para esta condición debe de seleccionar un daño ", "Vegetacion menor", JOptionPane.INFORMATION_MESSAGE);
            cmbSeveridad1.requestFocus();
            return false;
        } else {
            return true;
        }
    }
    
    private boolean validarColectasObligatorias() {
        CDColectaBotanica colecta = new CDColectaBotanica();
        if (colecta.validarCapturaGenero("TAXONOMIA_VegetacionMenor", this.sitioID)) {
            JOptionPane.showMessageDialog(null, "Error! Faltan por asignar claves de colecta", "Vegetación menor", JOptionPane.INFORMATION_MESSAGE);
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
        btnContinuar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        grdVegetacionMenor = new javax.swing.JTable();
        PnlCoordenadas4 = new javax.swing.JPanel();
        lblFamilia = new javax.swing.JLabel();
        cmbFamilia = new javax.swing.JComboBox();
        lblGenero = new javax.swing.JLabel();
        cmbGenero = new javax.swing.JComboBox();
        lblEspecie = new javax.swing.JLabel();
        cmbEspecie = new javax.swing.JComboBox();
        lblInfraespecie = new javax.swing.JLabel();
        lblNombreComun = new javax.swing.JLabel();
        txtNombreComun = new javax.swing.JTextField();
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
        lblPorcentajeCobertura1 = new javax.swing.JLabel();
        txtPorcentajeCobertura = new javax.swing.JFormattedTextField();
        jPanel2 = new javax.swing.JPanel();
        txtNumero0110 = new javax.swing.JFormattedTextField();
        lblFrecuenciaIndividuos = new javax.swing.JLabel();
        lblNoPlanta0110 = new javax.swing.JLabel();
        lblNoPlanta1125 = new javax.swing.JLabel();
        txtNumero1125 = new javax.swing.JFormattedTextField();
        txtNumero2650 = new javax.swing.JFormattedTextField();
        lblNoPlanta2650 = new javax.swing.JLabel();
        lblNoPlanta5175 = new javax.swing.JLabel();
        txtNumero5175 = new javax.swing.JFormattedTextField();
        txtNumero76100 = new javax.swing.JFormattedTextField();
        lblNoPlanta76100 = new javax.swing.JLabel();
        lblNoPlanta101125 = new javax.swing.JLabel();
        txtNumero101125 = new javax.swing.JFormattedTextField();
        txtNumero126150 = new javax.swing.JFormattedTextField();
        lblNoPlanta126150 = new javax.swing.JLabel();
        lblPorcentajeCobertura = new javax.swing.JLabel();
        txtNumero150 = new javax.swing.JFormattedTextField();
        lblFormaVida = new javax.swing.JLabel();
        cmbFormaVida = new javax.swing.JComboBox();
        lblCondicion = new javax.swing.JLabel();
        cmbCondicion = new javax.swing.JComboBox();
        cmbInfraespecie = new javax.swing.JComboBox();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnColecta = new javax.swing.JButton();
        lblClaveColecta = new javax.swing.JLabel();
        txtClaveColecta = new javax.swing.JTextField();
        chkVegetacionMenor = new javax.swing.JCheckBox();

        setMaximizable(true);
        setTitle("Vegetación menor, módulo H "+version);
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
        lblVegetacionMenorCobertura.setText("Registro de vegetación menor");
        lblVegetacionMenorCobertura.setOpaque(true);

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

        grdVegetacionMenor.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        grdVegetacionMenor.setToolTipText("");
        grdVegetacionMenor.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        grdVegetacionMenor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grdVegetacionMenorMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(grdVegetacionMenor);

        PnlCoordenadas4.setBackground(new java.awt.Color(204, 204, 204));
        PnlCoordenadas4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        PnlCoordenadas4.setPreferredSize(new java.awt.Dimension(900, 145));

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

        lblNombreComun.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreComun.setText("Nombre común:");

        txtNombreComun.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNombreComunFocusGained(evt);
            }
        });

        lblAgenteDanio1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAgenteDanio1.setText("AD1");
        lblAgenteDanio1.setToolTipText("Agente daño 1");
        lblAgenteDanio1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        cmbAgenteDanio1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbAgenteDanio1ItemStateChanged(evt);
            }
        });
        cmbAgenteDanio1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAgenteDanio1ActionPerformed(evt);
            }
        });

        lblSeveridad1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSeveridad1.setText("Severidad 1");
        lblSeveridad1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

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

        lblVigor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVigor.setText("Vigor");

        lblPorcentajeCobertura1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPorcentajeCobertura1.setText("Cobertura%");

        txtPorcentajeCobertura.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtPorcentajeCobertura.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPorcentajeCoberturaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPorcentajeCoberturaFocusLost(evt);
            }
        });
        txtPorcentajeCobertura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPorcentajeCoberturaKeyTyped(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        txtNumero0110.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtNumero0110.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNumero0110FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNumero0110FocusLost(evt);
            }
        });
        txtNumero0110.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumero0110KeyTyped(evt);
            }
        });

        lblFrecuenciaIndividuos.setBackground(new java.awt.Color(153, 153, 153));
        lblFrecuenciaIndividuos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFrecuenciaIndividuos.setText("No de plantas por categoría de altura (cm)");
        lblFrecuenciaIndividuos.setOpaque(true);

        lblNoPlanta0110.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNoPlanta0110.setText("01-10");
        lblNoPlanta0110.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblNoPlanta1125.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNoPlanta1125.setText("11-25");

        txtNumero1125.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtNumero1125.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNumero1125FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNumero1125FocusLost(evt);
            }
        });
        txtNumero1125.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumero1125KeyTyped(evt);
            }
        });

        txtNumero2650.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtNumero2650.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNumero2650FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNumero2650FocusLost(evt);
            }
        });
        txtNumero2650.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumero2650KeyTyped(evt);
            }
        });

        lblNoPlanta2650.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNoPlanta2650.setText("26-50");

        lblNoPlanta5175.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNoPlanta5175.setText("51-75");

        txtNumero5175.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtNumero5175.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNumero5175FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNumero5175FocusLost(evt);
            }
        });
        txtNumero5175.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumero5175KeyTyped(evt);
            }
        });

        txtNumero76100.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtNumero76100.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNumero76100FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNumero76100FocusLost(evt);
            }
        });
        txtNumero76100.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumero76100KeyTyped(evt);
            }
        });

        lblNoPlanta76100.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNoPlanta76100.setText("76-100");

        lblNoPlanta101125.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNoPlanta101125.setText("101-125");

        txtNumero101125.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtNumero101125.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNumero101125FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNumero101125FocusLost(evt);
            }
        });
        txtNumero101125.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumero101125KeyTyped(evt);
            }
        });

        txtNumero126150.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtNumero126150.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNumero126150FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNumero126150FocusLost(evt);
            }
        });
        txtNumero126150.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumero126150KeyTyped(evt);
            }
        });

        lblNoPlanta126150.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNoPlanta126150.setText("126-150");

        lblPorcentajeCobertura.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPorcentajeCobertura.setText(">150");

        txtNumero150.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtNumero150.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNumero150FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNumero150FocusLost(evt);
            }
        });
        txtNumero150.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumero150KeyTyped(evt);
            }
        });

        lblFormaVida.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFormaVida.setText("Forma de vida");
        lblFormaVida.setToolTipText("Forma de Vida");

        lblCondicion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCondicion.setText("Condición");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbFormaVida, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblFormaVida, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbCondicion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCondicion, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNoPlanta0110, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNumero0110, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtNumero1125, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNoPlanta1125, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNumero2650)
                            .addComponent(lblNoPlanta2650, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNumero5175)
                            .addComponent(lblNoPlanta5175, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNumero76100, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNoPlanta76100, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNumero101125)
                            .addComponent(lblNoPlanta101125, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNumero126150, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNoPlanta126150, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNumero150)
                            .addComponent(lblPorcentajeCobertura, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblFrecuenciaIndividuos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(lblFrecuenciaIndividuos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblNoPlanta0110)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNumero0110, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblNoPlanta1125)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNumero1125, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblNoPlanta2650)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNumero2650, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblNoPlanta5175)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNumero5175, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblNoPlanta76100)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNumero76100, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblNoPlanta101125)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNumero101125, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblNoPlanta126150)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNumero126150, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblPorcentajeCobertura)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNumero150, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblFormaVida)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbFormaVida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbCondicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblCondicion)))
        );

        javax.swing.GroupLayout PnlCoordenadas4Layout = new javax.swing.GroupLayout(PnlCoordenadas4);
        PnlCoordenadas4.setLayout(PnlCoordenadas4Layout);
        PnlCoordenadas4Layout.setHorizontalGroup(
            PnlCoordenadas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlCoordenadas4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PnlCoordenadas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnlCoordenadas4Layout.createSequentialGroup()
                        .addGroup(PnlCoordenadas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPorcentajeCobertura1)
                            .addComponent(txtPorcentajeCobertura, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(PnlCoordenadas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbAgenteDanio1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblAgenteDanio1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PnlCoordenadas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbSeveridad1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblSeveridad1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PnlCoordenadas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PnlCoordenadas4Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(lblAgenteDanio2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cmbAgenteDanio2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(PnlCoordenadas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblSeveridad2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbSeveridad2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(PnlCoordenadas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblVigor, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbVigor, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(PnlCoordenadas4Layout.createSequentialGroup()
                        .addGroup(PnlCoordenadas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(PnlCoordenadas4Layout.createSequentialGroup()
                                .addComponent(lblFamilia)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblGenero)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblEspecie)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblInfraespecie)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbInfraespecie, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblNombreComun, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombreComun, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(16, Short.MAX_VALUE))))
        );
        PnlCoordenadas4Layout.setVerticalGroup(
            PnlCoordenadas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlCoordenadas4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnlCoordenadas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
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
                .addGap(14, 14, 14)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PnlCoordenadas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnlCoordenadas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(PnlCoordenadas4Layout.createSequentialGroup()
                            .addComponent(lblAgenteDanio1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cmbAgenteDanio1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnlCoordenadas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(PnlCoordenadas4Layout.createSequentialGroup()
                                .addComponent(lblAgenteDanio2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PnlCoordenadas4Layout.createSequentialGroup()
                                .addComponent(lblSeveridad1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(PnlCoordenadas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cmbSeveridad1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbAgenteDanio2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(PnlCoordenadas4Layout.createSequentialGroup()
                        .addComponent(lblVigor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbVigor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PnlCoordenadas4Layout.createSequentialGroup()
                        .addComponent(lblPorcentajeCobertura1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPorcentajeCobertura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PnlCoordenadas4Layout.createSequentialGroup()
                        .addComponent(lblSeveridad2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbSeveridad2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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

        chkVegetacionMenor.setBackground(new java.awt.Color(204, 204, 204));
        chkVegetacionMenor.setSelected(true);
        chkVegetacionMenor.setText("Vegetación menor");
        chkVegetacionMenor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkVegetacionMenorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlPrincipalLayout = new javax.swing.GroupLayout(pnlPrincipal);
        pnlPrincipal.setLayout(pnlPrincipalLayout);
        pnlPrincipalLayout.setHorizontalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PnlCoordenadas4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 938, Short.MAX_VALUE)
            .addComponent(lblVegetacionMenorCobertura, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addComponent(lblUPM)
                .addGap(10, 10, 10)
                .addComponent(txtUPM, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblSitio)
                .addGap(8, 8, 8)
                .addComponent(txtSitio, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGap(305, 305, 305)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGap(635, 635, 635)
                        .addComponent(btnColecta))
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGap(755, 755, 755)
                        .addComponent(txtClaveColecta, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGap(715, 715, 715)
                        .addComponent(lblClaveColecta))
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGap(405, 405, 405)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(chkVegetacionMenor))
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGap(505, 505, 505)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPrincipalLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnContinuar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlPrincipalLayout.setVerticalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(lblUPM))
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSitio)
                            .addComponent(txtSitio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(2, 2, 2)
                .addComponent(lblVegetacionMenorCobertura)
                .addGap(8, 8, 8)
                .addComponent(PnlCoordenadas4, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGuardar)
                    .addComponent(btnColecta)
                    .addComponent(txtClaveColecta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblClaveColecta)
                    .addComponent(btnModificar)
                    .addComponent(chkVegetacionMenor)
                    .addComponent(btnEliminar))
                .addGap(7, 7, 7)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnContinuar)
                    .addComponent(btnSalir))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        ceSitio.setSitioID(this.sitioID);
        if (funciones.validarSeccionCapturada("TAXONOMIA_VegetacionMenor", this.ceSitio) && chkVegetacionMenor.isSelected()) {
            JOptionPane.showMessageDialog(null, "Si seleccionó vegetación menor, se debe capturar ", "Vegetacion menor", JOptionPane.INFORMATION_MESSAGE);
            chkVegetacionMenor.requestFocus();
        } else if (funciones.validarSeccionCapturada("TAXONOMIA_VegetacionMenor", this.ceSitio) == false && chkVegetacionMenor.isSelected()) {
            if (validarColectasObligatorias()) {
                this.hide();
                if (this.actualizar == 0) {
                    UPMForms.vegetacionMayorI.setDatosIniciales(this.ceSitio);
                    UPMForms.vegetacionMayorI.setVisible(true);
                } else {
                    UPMForms.vegetacionMayorI.continuarVegetacionMayorIndividual(this.ceSitio);
                    UPMForms.vegetacionMayorI.setVisible(true);
                }
                this.cdSecuencia.updateSecuencia(this.ceSitio, FORMATO_ID, 1);
            }
        } else if (funciones.validarSeccionCapturada("TAXONOMIA_VegetacionMenor", this.ceSitio) == true && !chkVegetacionMenor.isSelected()) {
            this.hide();
            if (this.actualizar == 0) {
                UPMForms.vegetacionMayorI.setDatosIniciales(this.ceSitio);
                UPMForms.vegetacionMayorI.setVisible(true);
            } else {
                UPMForms.vegetacionMayorI.continuarVegetacionMayorIndividual(this.ceSitio);
                UPMForms.vegetacionMayorI.setVisible(true);
            }
            this.cdSecuencia.updateSecuencia(this.ceSitio, FORMATO_ID, -1);
        }
    }//GEN-LAST:event_btnContinuarActionPerformed

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

    private void grdVegetacionMenorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grdVegetacionMenorMouseClicked
        if (evt.getButton() == 1) {
            btnGuardar.setEnabled(true);
            int fila = grdVegetacionMenor.getSelectedRow();
            String registroVMID = grdVegetacionMenor.getValueAt(fila, 0).toString();
            this.vegetacionMenorID = Integer.parseInt(registroVMID);
            CEVegetacionMenor ceVegetacionMenor;
            ceVegetacionMenor = cdVegetacion.getRegistroVegetacionMenor(this.vegetacionMenorID);

            CatEFamiliaEspecie fam = new CatEFamiliaEspecie();
            fam.setFamiliaID(ceVegetacionMenor.getFamiliaID());
            cmbFamilia.setSelectedItem(fam);
            
            CatEGenero gen = new CatEGenero();
            gen.setGeneroID(ceVegetacionMenor.getGeneroID());
            cmbGenero.setSelectedItem(gen);
            
            /*atEGenero gen = new CatEGenero();
            gen.setGeneroID(ceVegetacionMenor.getGeneroID());
            cmbGenero.removeAllItems();
            fillCmbGenero(ceVegetacionMenor.getFamiliaID());
            cmbGenero.setSelectedItem(gen);*/

            CatEEspecie esp = new CatEEspecie();
            esp.setEspecieID(ceVegetacionMenor.getEspecieID());
            cmbEspecie.removeAllItems();
            fillCmbEspecie(ceVegetacionMenor.getGeneroID());
            cmbEspecie.setSelectedItem(esp);

            CatEInfraespecie inf = new CatEInfraespecie();
            inf.setInfraespecieID(ceVegetacionMenor.getInfraespecieID());
            cmbInfraespecie.removeAllItems();
            fillCmbInfraespecie(ceVegetacionMenor.getEspecieID());
            cmbInfraespecie.setSelectedItem(inf);
            
            txtNombreComun.setText(ceVegetacionMenor.getNombreComun());

            CatEFormaVidaZA formaVida = new CatEFormaVidaZA();
            formaVida.setFormaVidaZAID(ceVegetacionMenor.getFormaVidaID());
            cmbFormaVida.setSelectedItem(formaVida);

            CatECondicionVM condicion = new CatECondicionVM();
            condicion.setCondicionVMID(ceVegetacionMenor.getCondicionID());
            cmbCondicion.setSelectedItem(condicion);
            txtNumero0110.setText(String.valueOf(ceVegetacionMenor.getNumero0110()));
            if ((txtNumero0110.getText()).equals("0")) {
                txtNumero0110.setText("");
            }
            txtNumero1125.setText(String.valueOf(ceVegetacionMenor.getNumero1125()));
            if (txtNumero1125.getText().equals("0")) {
                txtNumero1125.setText("");
            }
            txtNumero2650.setText(String.valueOf(ceVegetacionMenor.getNumero2650()));
            if (txtNumero2650.getText().equals("0")) {
                txtNumero2650.setText("");
            }
            txtNumero5175.setText(String.valueOf(ceVegetacionMenor.getNumero5175()));
            if (txtNumero5175.getText().equals("0")) {
                txtNumero5175.setText("");
            }
            txtNumero76100.setText(String.valueOf(ceVegetacionMenor.getNumero76100()));
            if (txtNumero76100.getText().equals("0")) {
                txtNumero76100.setText("");
            }
            txtNumero101125.setText(String.valueOf(ceVegetacionMenor.getNumero101125()));
            if (txtNumero101125.getText().equals("0")) {
                txtNumero101125.setText("");
            }
            txtNumero126150.setText(String.valueOf(ceVegetacionMenor.getNumero126150()));
            if (txtNumero126150.getText().equals("0")) {
                txtNumero126150.setText("");
            }
            txtNumero150.setText(String.valueOf(ceVegetacionMenor.getNumero150()));
            if (txtNumero150.getText().equals("0")) {
                txtNumero150.setText("");
            }

            txtPorcentajeCobertura.setText(String.valueOf(ceVegetacionMenor.getPorcentajeCobertura()));
            if (txtPorcentajeCobertura.getText().equals("0")) {
                txtPorcentajeCobertura.setText("");
            }

            CatEAgenteDanio agente1 = new CatEAgenteDanio();
            agente1.setAgenteDanioID(ceVegetacionMenor.getAgenteDanio1ID());
            cmbAgenteDanio1.setSelectedItem(agente1);

            CatEAgenteDanio agente2 = new CatEAgenteDanio();
            agente2.setAgenteDanioID(ceVegetacionMenor.getAgenteDanio2ID());
            cmbAgenteDanio2.setSelectedItem(agente2);

            CatESeveridadZA severidad1 = new CatESeveridadZA();
            severidad1.setSeveridadID(ceVegetacionMenor.getSeveridad1ID());
            combo.reiniciarComboModel(cmbSeveridad1);
            fillCmbSeveridad1();
            cmbSeveridad1.setSelectedItem(severidad1);

            CatESeveridadZA severidad2 = new CatESeveridadZA();
            severidad2.setSeveridadID(ceVegetacionMenor.getSeveridad2ID());
            combo.reiniciarComboModel(cmbSeveridad2);
            fillCmbSeveridad2();
            cmbSeveridad2.setSelectedItem(severidad2);

            CatETipoVigor vig = new CatETipoVigor();
            vig.setVigorID(ceVegetacionMenor.getVigorID());
            cmbVigor.setSelectedItem(vig);
            
            txtClaveColecta.setText(ceVegetacionMenor.getClaveColecta());
        }
    }//GEN-LAST:event_grdVegetacionMenorMouseClicked

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

    private void cmbAgenteDanio1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAgenteDanio1ActionPerformed

    }//GEN-LAST:event_cmbAgenteDanio1ActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        asignarDatosVegetacionMenor();
        if (validarVegetacionMenorObligatorios() && validarMediciones() && validarSeveridadDanio()  && validarCondicionMuertoPie()) {
            crearVegetacionMenor();
            this.cdVegetacion.enumerarConsecutivo(this.sitioID);
            llenarTabla();
            limpiarControles();
            cmbFamilia.requestFocus();
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        asignarDatosVegetacionMenor();
        if (validarVegetacionMenorObligatorios() && validarMediciones()&& validarSeveridadDanio() && validarCondicionMuertoPie()) {
            actualizarVegetacionMenor();
            llenarTabla();
            limpiarControles();
            cmbFamilia.requestFocus();
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminarVegetacionMenor();
        this.cdVegetacion.enumerarConsecutivo(this.sitioID);
        llenarTabla();
        limpiarControles();
        cmbFamilia.requestFocus();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void cmbAgenteDanio2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAgenteDanio2ActionPerformed
       /* CatEAgenteDanio agenteDanio = (CatEAgenteDanio) cmbAgenteDanio2.getSelectedItem();
        if (agenteDanio != null) {
            if (agenteDanio.getAgenteDanioID() == 21 || agenteDanio.getAgenteDanioID() == 34 || agenteDanio.getAgenteDanioID() == 22) {
                cmbSeveridad2.setEnabled(true);
                fillCmbSeveridad2();
            } else {
                combo.reiniciarComboModel(cmbSeveridad2);
                cmbSeveridad2.setSelectedItem(null);
                cmbSeveridad2.setEnabled(false);
            }
        }*/
    }//GEN-LAST:event_cmbAgenteDanio2ActionPerformed

    private void txtPorcentajeCoberturaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPorcentajeCoberturaFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtPorcentajeCobertura.selectAll();
            }
        });
    }//GEN-LAST:event_txtPorcentajeCoberturaFocusGained

    private void txtNumero0110FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNumero0110FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtNumero0110.selectAll();
            }
        });
    }//GEN-LAST:event_txtNumero0110FocusGained

    private void txtNumero0110FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNumero0110FocusLost
        if (txtNumero0110.getText().isEmpty()) {
            txtNumero0110.setValue(null);
        }
    }//GEN-LAST:event_txtNumero0110FocusLost

    private void txtNumero1125FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNumero1125FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtNumero1125.selectAll();
            }
        });
    }//GEN-LAST:event_txtNumero1125FocusGained

    private void txtNumero1125FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNumero1125FocusLost
        if (txtNumero1125.getText().isEmpty()) {
            txtNumero1125.setValue(null);
        }
    }//GEN-LAST:event_txtNumero1125FocusLost

    private void txtNumero2650FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNumero2650FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtNumero2650.selectAll();
            }
        });
    }//GEN-LAST:event_txtNumero2650FocusGained

    private void txtNumero2650FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNumero2650FocusLost
        if (txtNumero2650.getText().isEmpty()) {
            txtNumero2650.setValue(null);
        }
    }//GEN-LAST:event_txtNumero2650FocusLost

    private void txtNumero5175FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNumero5175FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtNumero5175.selectAll();
            }
        });
    }//GEN-LAST:event_txtNumero5175FocusGained

    private void txtNumero5175FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNumero5175FocusLost
        if (txtNumero2650.getText().isEmpty()) {
            txtNumero2650.setValue(null);
        }
    }//GEN-LAST:event_txtNumero5175FocusLost

    private void txtNumero76100FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNumero76100FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtNumero76100.selectAll();
            }
        });
    }//GEN-LAST:event_txtNumero76100FocusGained

    private void txtNumero76100FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNumero76100FocusLost
        if (txtNumero76100.getText().isEmpty()) {
            txtNumero76100.setValue(null);
        }
    }//GEN-LAST:event_txtNumero76100FocusLost

    private void txtNumero101125FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNumero101125FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtNumero101125.selectAll();
            }
        });
    }//GEN-LAST:event_txtNumero101125FocusGained

    private void txtNumero126150FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNumero126150FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtNumero126150.selectAll();
            }
        });
    }//GEN-LAST:event_txtNumero126150FocusGained

    private void txtNumero126150FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNumero126150FocusLost
        if (txtNumero126150.getText().isEmpty()) {
            txtNumero126150.setValue(null);
        }
    }//GEN-LAST:event_txtNumero126150FocusLost

    private void txtNumero150FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNumero150FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtNumero150.selectAll();
            }
        });
    }//GEN-LAST:event_txtNumero150FocusGained

    private void txtPorcentajeCoberturaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPorcentajeCoberturaFocusLost
        if (txtPorcentajeCobertura.getText().isEmpty()) {
            txtPorcentajeCobertura.setValue(null);
        }
    }//GEN-LAST:event_txtPorcentajeCoberturaFocusLost

    private void txtNumero150FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNumero150FocusLost
        if (txtNumero150.getText().isEmpty()) {
            txtNumero150.setValue(null);
        }
    }//GEN-LAST:event_txtNumero150FocusLost

    private void txtNumero101125FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNumero101125FocusLost
        if (txtNumero101125.getText().isEmpty()) {
            txtNumero101125.setValue(null);
        }
    }//GEN-LAST:event_txtNumero101125FocusLost

    private void txtNumero0110KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumero0110KeyTyped
       this.numeros.keyTyped(evt);
    }//GEN-LAST:event_txtNumero0110KeyTyped

    private void txtNumero1125KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumero1125KeyTyped
        this.numeros.keyTyped(evt);
    }//GEN-LAST:event_txtNumero1125KeyTyped

    private void txtNumero2650KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumero2650KeyTyped
        this.numeros.keyTyped(evt);
    }//GEN-LAST:event_txtNumero2650KeyTyped

    private void txtNumero5175KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumero5175KeyTyped
        this.numeros.keyTyped(evt);
    }//GEN-LAST:event_txtNumero5175KeyTyped

    private void txtNumero76100KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumero76100KeyTyped
       this.numeros.keyTyped(evt);
    }//GEN-LAST:event_txtNumero76100KeyTyped

    private void txtNumero101125KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumero101125KeyTyped
       this.numeros.keyTyped(evt);
    }//GEN-LAST:event_txtNumero101125KeyTyped

    private void txtNumero126150KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumero126150KeyTyped
       this.numeros.keyTyped(evt);
    }//GEN-LAST:event_txtNumero126150KeyTyped

    private void txtNumero150KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumero150KeyTyped
       this.numeros.keyTyped(evt);
    }//GEN-LAST:event_txtNumero150KeyTyped

    private void txtPorcentajeCoberturaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPorcentajeCoberturaKeyTyped
        this.numeros.keyTyped(evt);
    }//GEN-LAST:event_txtPorcentajeCoberturaKeyTyped

    private void btnColectaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnColectaActionPerformed
        try {
            int fila = grdVegetacionMenor.getSelectedRow();
            String consecutivo = grdVegetacionMenor.getValueAt(fila, 2).toString();
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
            claveColecta.setDatosIniciales(ceColecta, FORMATO_ID, "TAXONOMIA_VegetacionMenor", "Consecutivo" ,this.sitioID, Integer.parseInt(consecutivo));
            claveColecta.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un registro para asignar la clave de colecta"
                    + e.getClass().getName() + " : " + e.getMessage(), "Clave de colecta", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnColectaActionPerformed

    private void chkVegetacionMenorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkVegetacionMenorActionPerformed
        if(!chkVegetacionMenor.isSelected()){
            Object[] opciones = {"Si", "No"};
            int respuesta = JOptionPane.showOptionDialog(null, "Si capturo, se eliminará la información de vegetación menor del sitio " + this.sitio + ", ¿Esta seguro?",
                    "Vegetación menor", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[1]);
            if (respuesta == JOptionPane.YES_OPTION) {
                cdVegetacion.deleteVegetacionMenor(this.sitioID);
                funciones.reiniciarTabla(this.grdVegetacionMenor);
                limpiarControles();
                cmbFamilia.setEnabled(false);
                cmbGenero.setEnabled(false);
                cmbEspecie.setEnabled(false);
                cmbInfraespecie.setEnabled(false);
                txtNombreComun.setEnabled(false);
                cmbFormaVida.setEnabled(false);
                cmbCondicion.setEnabled(false);
                txtNumero0110.setEnabled(false);
                txtNumero1125.setEnabled(false);
                txtNumero2650.setEnabled(false);
                txtNumero5175.setEditable(false);
                txtNumero76100.setEnabled(false);
                txtNumero101125.setEnabled(false);
                txtNumero126150.setEnabled(false);
                txtNumero150.setEnabled(false);
                txtPorcentajeCobertura.setEnabled(false);
                cmbAgenteDanio1.setEnabled(false);
                //cmbSeveridad1.setEnabled(false);
                cmbAgenteDanio2.setEnabled(false);
                //cmbSeveridad2.setEnabled(false);
                cmbVigor.setEnabled(false);
            } 
        }else{
            cmbFamilia.setEnabled(true);
                cmbGenero.setEnabled(true);
                cmbEspecie.setEnabled(true);
                cmbInfraespecie.setEnabled(true);
                txtNombreComun.setEnabled(true);
                cmbFormaVida.setEnabled(true);
                cmbCondicion.setEnabled(true);
                txtNumero0110.setEnabled(true);
                txtNumero1125.setEnabled(true);
                txtNumero2650.setEnabled(true);
                txtNumero5175.setEditable(true);
                txtNumero76100.setEnabled(true);
                txtNumero101125.setEnabled(true);
                txtNumero126150.setEnabled(true);
                txtNumero150.setEnabled(true);
                txtPorcentajeCobertura.setEnabled(true);
                cmbAgenteDanio1.setEnabled(true);
                cmbSeveridad1.setEnabled(true);
                cmbAgenteDanio2.setEnabled(true);
                cmbSeveridad2.setEnabled(true);
                cmbVigor.setEnabled(true);
        }
    }//GEN-LAST:event_chkVegetacionMenorActionPerformed

    private void cmbEspecieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEspecieActionPerformed
       CatEEspecie indexEspecie = (CatEEspecie) cmbEspecie.getSelectedItem();
        DefaultComboBoxModel dcm = (DefaultComboBoxModel) cmbInfraespecie.getModel();
        dcm.removeAllElements();
        if (indexEspecie != null) {
            fillCmbInfraespecie(indexEspecie.getEspecieID());
        }
    }//GEN-LAST:event_cmbEspecieActionPerformed

    private void cmbAgenteDanio1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbAgenteDanio1ItemStateChanged
     
    }//GEN-LAST:event_cmbAgenteDanio1ItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PnlCoordenadas4;
    private javax.swing.JButton btnColecta;
    private javax.swing.JButton btnContinuar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JCheckBox chkVegetacionMenor;
    private javax.swing.JComboBox cmbAgenteDanio1;
    private javax.swing.JComboBox cmbAgenteDanio2;
    private javax.swing.JComboBox cmbCondicion;
    private javax.swing.JComboBox cmbEspecie;
    private javax.swing.JComboBox cmbFamilia;
    private javax.swing.JComboBox cmbFormaVida;
    private javax.swing.JComboBox cmbGenero;
    private javax.swing.JComboBox cmbInfraespecie;
    private javax.swing.JComboBox cmbSeveridad1;
    private javax.swing.JComboBox cmbSeveridad2;
    private javax.swing.JComboBox cmbVigor;
    private javax.swing.JTable grdVegetacionMenor;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAgenteDanio1;
    private javax.swing.JLabel lblAgenteDanio2;
    private javax.swing.JLabel lblClaveColecta;
    private javax.swing.JLabel lblCondicion;
    private javax.swing.JLabel lblEspecie;
    private javax.swing.JLabel lblFamilia;
    private javax.swing.JLabel lblFormaVida;
    private javax.swing.JLabel lblFrecuenciaIndividuos;
    private javax.swing.JLabel lblGenero;
    private javax.swing.JLabel lblInfraespecie;
    private javax.swing.JLabel lblNoPlanta0110;
    private javax.swing.JLabel lblNoPlanta101125;
    private javax.swing.JLabel lblNoPlanta1125;
    private javax.swing.JLabel lblNoPlanta126150;
    private javax.swing.JLabel lblNoPlanta2650;
    private javax.swing.JLabel lblNoPlanta5175;
    private javax.swing.JLabel lblNoPlanta76100;
    private javax.swing.JLabel lblNombreComun;
    private javax.swing.JLabel lblPorcentajeCobertura;
    private javax.swing.JLabel lblPorcentajeCobertura1;
    private javax.swing.JLabel lblSeveridad1;
    private javax.swing.JLabel lblSeveridad2;
    private javax.swing.JLabel lblSitio;
    private javax.swing.JLabel lblUPM;
    private javax.swing.JLabel lblVegetacionMenorCobertura;
    private javax.swing.JLabel lblVigor;
    private javax.swing.JPanel pnlPrincipal;
    public javax.swing.JTextField txtClaveColecta;
    private javax.swing.JTextField txtNombreComun;
    private javax.swing.JFormattedTextField txtNumero0110;
    private javax.swing.JFormattedTextField txtNumero101125;
    private javax.swing.JFormattedTextField txtNumero1125;
    private javax.swing.JFormattedTextField txtNumero126150;
    private javax.swing.JFormattedTextField txtNumero150;
    private javax.swing.JFormattedTextField txtNumero2650;
    private javax.swing.JFormattedTextField txtNumero5175;
    private javax.swing.JFormattedTextField txtNumero76100;
    private javax.swing.JFormattedTextField txtPorcentajeCobertura;
    private javax.swing.JTextField txtSitio;
    private javax.swing.JTextField txtUPM;
    // End of variables declaration//GEN-END:variables
}
