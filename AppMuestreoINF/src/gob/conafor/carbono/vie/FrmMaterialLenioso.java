package gob.conafor.carbono.vie;

import gob.conafor.carbono.mod.CDCombustiblesForestales;
import gob.conafor.carbono.mod.CECubiertaVegetal;
import gob.conafor.carbono.mod.CEMaterialLenioso100;
import gob.conafor.carbono.mod.CEMaterialLenioso1000;
import gob.conafor.carbono.mod.CatECarbonoComponente;
import gob.conafor.sitio.mod.CESitio;
import gob.conafor.sys.mod.CDSecuencia;
import gob.conafor.sys.mod.CDSeguimientoUPM;
import gob.conafor.sys.mod.CESeccionesCapturadas;
import gob.conafor.upm.vie.UPMForms;
import gob.conafor.utils.Datos;
import gob.conafor.utils.FuncionesComunes;
import gob.conafor.utils.Tablas;
import gob.conafor.utils.ValidacionesComunes;
import gob.conafor.utils.Version;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class FrmMaterialLenioso extends JInternalFrame {
    
    private int upmID;
    private int sitioID;
    private int sitio;
    private int carbono;
     private static final int FORMATO_ID = 7;
    private int longitudInterceptada;
    private int material100ID;
    private int transecto;
    private Integer pendiente;
    private Integer unaHora;
    private Integer diezHoras;
    private Integer cienHoras;
    private Float diametro1000;
    private Integer grado1000;
    private Float componente5m;
    private Float componente10m;  
    private Integer indexTrasectoCubierta;
    private Integer indexTransecto100;
    private Integer indexTransecto1000;
    private CESeccionesCapturadas seccionActual = new CESeccionesCapturadas();
    private CESeccionesCapturadas seccionSiguiente = new CESeccionesCapturadas();
    private CDSeguimientoUPM seguimiento = new CDSeguimientoUPM();
    private CDCombustiblesForestales cdCarbono = new CDCombustiblesForestales();
    private int materialLenioso100ID;
    private int cubiertaVegetalID;
    private CESitio ceSitio = new CESitio();
    private Datos numeros = new Datos();
    private CDSecuencia cdSecuencia = new CDSecuencia();
    private FuncionesComunes funciones = new FuncionesComunes();
    private int modificar; 
    private Version ver=new Version();
    private String version=ver.getVersion();
    private boolean  revision;
    
    public FrmMaterialLenioso() {
        initComponents();
        this.carbono = 14;
        this.longitudInterceptada = 17;
        //fillCmbMaterial100();
        fillCmbTransecto1000();
        fillCmbGradoPutrefaccion1000();
        fillCmbCubiertaVegetal();
        fillCmbComponentes();
    }
    
    public void setDatosIniciales(CESitio sitio){
        this.upmID = sitio.getUpmID();
        this.sitioID = sitio.getSitioID();
        this.sitio = sitio.getSitio();
        txtUPM.setText(String.valueOf(this.upmID));
        txtSitio.setText(String.valueOf(this.sitio));
        llenarTablaMaterialLenioso100();
        llenarTablaMaterialLenioso1000();
        llenarTablaCubiertaVegetal();
        funciones.reiniciarComboModel(cmbTransecto100);
        fillCmbMaterial100();
        ceSitio.setUpmID(this.upmID);
        ceSitio.setSitioID(this.sitioID);
        ceSitio.setSitio(this.sitio);
        this.ceSitio = sitio;
        funciones.manipularBotonesMenuPrincipal(true);
        this.modificar = 0;
       // cdSecuencia.insertFormatoCapturado(ceSitio, FORMATO_ID);
    }
    
    public void revisarCarbono(CESitio sitio){
        revision=true;
        this.upmID = sitio.getUpmID();
        this.sitioID = sitio.getSitioID();
        this.sitio = sitio.getSitio();
        txtUPM.setText(String.valueOf(this.upmID));
        txtSitio.setText(String.valueOf(this.sitio));
        llenarTablaMaterialLenioso100();
        llenarTablaMaterialLenioso1000();
        llenarTablaCubiertaVegetal();
        ceSitio.setUpmID(this.upmID);
        ceSitio.setSitioID(this.sitioID);
        ceSitio.setSitio(this.sitio);
        this.ceSitio.setSecuencia(sitio.getSecuencia());
        funciones.manipularBotonesMenuPrincipal(true);
        chkMaterialLenioso110100.setSelected(!cdCarbono.hayMaterialLenioso100(this.sitioID));
        if (chkMaterialLenioso110100.isSelected()){
            manipularControlesMaterial100(true);
        }else{
            manipularControlesMaterial100(false);
        }
        chkMaterialLenioso1000.setSelected(!this.cdCarbono.hayMaterialLenioso1000(this.sitioID));
        if (chkMaterialLenioso1000.isSelected()){
            manipularControlesMateriales1000(true);
        }else{
            manipularControlesMateriales1000(false);
        }
        chkAlturaArbustosHierbasPastos.setSelected(!this.cdCarbono.hayCubiertaVegetal(this.sitioID));
        if (chkAlturaArbustosHierbasPastos.isSelected()){
            manipularAlturaPastosHierbas(true);
        }else{
            manipularAlturaPastosHierbas(false);
        }
        this.modificar = 1;
        funciones.reiniciarComboModel(cmbTransecto100);
        fillCmbMaterial100();
    }
    
    private void fillCmbMaterial100(){
        List<Integer> listTransectos = new ArrayList<>();
        listTransectos = cdCarbono.getTransectoMaterial100(this.sitioID);
        if(listTransectos != null){
            int size = listTransectos.size();
            for(int i = 0; i < size; i++){
                cmbTransecto100.addItem(listTransectos.get(i));
            }
        }
    }
    
    private void fillCmbTransecto1000(){
        List<Integer> listTransectos = new ArrayList<>();
        listTransectos = cdCarbono.getTransectoMaterial1000();
        if(listTransectos != null){
            int size = listTransectos.size();
            for(int i = 0; i < size; i++){
                cmbTransecto1000.addItem(listTransectos.get(i));
            }
        }
    }
    
    private void fillCmbGradoPutrefaccion1000(){
        List<Integer> listTransectos = new ArrayList<>();
        listTransectos = cdCarbono.getGradoPutrefaccionl1000();
        if(listTransectos != null){
            int size = listTransectos.size();
            for(int i = 0; i < size; i++){
                cmbGradoPutrefaccion.addItem(listTransectos.get(i));
            }
        }
    }
    
    private void fillCmbCubiertaVegetal(){
        List<Integer> listTransectos = new ArrayList<>();
        listTransectos = cdCarbono.getTransectoCubiertaVegetal();
        if(listTransectos != null){
            int size = listTransectos.size();
            for(int i = 0; i < size; i++){
                cmbNoCubiertaVegetal.addItem(listTransectos.get(i));
            }
        }
    }
    
    private void fillCmbComponentes(){
        List<CatECarbonoComponente> listComponentes = new ArrayList<>();
        listComponentes = cdCarbono.getComponentesCV();
        if(listComponentes != null){
            int size = listComponentes.size();
            for(int i = 0; i < size; i++){
                cmbComponentes.addItem(listComponentes.get(i));
            }
        }
    }
    
    private void llenarTablaMaterialLenioso100() {
        grdMaterialLenioso100.setModel(cdCarbono.getTablaMaterialLenioso100(this.sitioID));
        grdMaterialLenioso100.getColumnModel().getColumn(2).setPreferredWidth(60);
        grdMaterialLenioso100.getColumnModel().getColumn(3).setPreferredWidth(60);
        grdMaterialLenioso100.getColumnModel().getColumn(4).setPreferredWidth(60);
        grdMaterialLenioso100.getColumnModel().getColumn(5).setPreferredWidth(70);
        grdMaterialLenioso100.getColumnModel().getColumn(6).setPreferredWidth(70);
        Tablas tabla = new Tablas();
        int[] column_0 = {0};
        int[] column_1 = {1};
        tabla.hideColumnTable(grdMaterialLenioso100, column_0);
        tabla.hideColumnTable(grdMaterialLenioso100, column_1);
    }

    private void llenarTablaMaterialLenioso1000() {
        grdMaterialLenioso1000.setModel(cdCarbono.getTablaMaterialLenioso1000(this.sitioID));
        grdMaterialLenioso1000.getColumnModel().getColumn(2).setPreferredWidth(60);
        grdMaterialLenioso1000.getColumnModel().getColumn(3).setPreferredWidth(100);
        grdMaterialLenioso1000.getColumnModel().getColumn(4).setPreferredWidth(100);
        Tablas tabla = new Tablas();
        int[] column_0 = {0};
        int[] column_1 = {1};
        tabla.hideColumnTable(grdMaterialLenioso1000, column_0);
        tabla.hideColumnTable(grdMaterialLenioso1000, column_1);
    }

    private void llenarTablaCubiertaVegetal() {
        grdCubiertaVegetal.setModel(cdCarbono.getTablaCubiertaVegetal(this.sitioID));
        grdCubiertaVegetal.getColumnModel().getColumn(2).setPreferredWidth(60);
        grdCubiertaVegetal.getColumnModel().getColumn(3).setPreferredWidth(80);
        grdCubiertaVegetal.getColumnModel().getColumn(4).setPreferredWidth(60);
        grdCubiertaVegetal.getColumnModel().getColumn(5).setPreferredWidth(60);
        Tablas tabla = new Tablas();
        int[] column_0 = {0};
        int[] column_1 = {1};
        tabla.hideColumnTable(grdCubiertaVegetal, column_0);
        tabla.hideColumnTable(grdCubiertaVegetal, column_1);
    }
    
    private void asignarDatosMaterial100() {
        this.indexTransecto100 = (Integer) cmbTransecto100.getSelectedItem();

        try {
            this.pendiente = Integer.valueOf(txtPendiente100.getText());
        } catch (NumberFormatException e) {
            this.pendiente = null;
        }

        try {
            this.unaHora = Integer.valueOf(txtUnaHora100.getText());
        } catch (NumberFormatException e) {
            this.unaHora = null;
        }

        try {
            this.diezHoras = Integer.valueOf(txtDiezHoras100.getText());
        } catch (NumberFormatException e) {
            this.diezHoras = null;
        }

        try {
            this.cienHoras = Integer.valueOf(txtCienHoras100.getText());
        } catch (NumberFormatException e) {
            this.cienHoras = null;
        }
    }
    
    private void asignarDatosMaterial1000() {
        this.indexTransecto1000 = (Integer) cmbTransecto1000.getSelectedItem();

        try {
            this.diametro1000 = Float.parseFloat(txtDiametro1000.getText());
        } catch (NumberFormatException e) {
            this.diametro1000 = null;
        }
        this.grado1000 = (Integer) cmbGradoPutrefaccion.getSelectedItem();
    }
    
    private void asignarDatosCubiertaVegetal() {
        this.indexTrasectoCubierta = (Integer) cmbNoCubiertaVegetal.getSelectedItem();
        try {
            this.componente5m = Float.parseFloat(txtComponente5m.getText());
        } catch (NumberFormatException e) {
            this.componente5m = null;
        }

        try {
            this.componente10m = Float.parseFloat(txtComponente10m.getText());
        } catch (NumberFormatException e) {
            this.componente10m = null;
        }
    }
    
    private boolean validarObligatorioMaterial100() {
        if (cmbTransecto100.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar un numero de transecto",
                    "Combustibles materiales 100", JOptionPane.INFORMATION_MESSAGE);
            cmbTransecto100.requestFocus();
            return false;
        } else if (txtPendiente100.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar la pendiente",
                    "Combustibles materiales 100", JOptionPane.INFORMATION_MESSAGE);
            txtPendiente100.requestFocus();
            return false;
        } else if (txtUnaHora100.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar combustibles de 1 hora",
                    "Combustibles materiales 100", JOptionPane.INFORMATION_MESSAGE);
            txtUnaHora100.requestFocus();
            return false;
        } else if (txtDiezHoras100.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar combustibles de 10 horas",
                    "Combustibles materiales 100", JOptionPane.INFORMATION_MESSAGE);
            txtDiezHoras100.requestFocus();
            return false;
        } else if (txtCienHoras100.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar combustibles de 100 horas",
                    "Combustibles materiales 100", JOptionPane.INFORMATION_MESSAGE);
            txtCienHoras100.requestFocus();
            return false;
        } else {
            return true;
        }
    }
    
    private boolean validarObligatorioMaterial100Modificacion() {
        if (txtPendiente100.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar la pendiente",
                    "Combustibles materiales 100", JOptionPane.INFORMATION_MESSAGE);
            txtPendiente100.requestFocus();
            return false;
        } else if (txtUnaHora100.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar combustibles de 1 hora",
                    "Combustibles materiales 100", JOptionPane.INFORMATION_MESSAGE);
            txtUnaHora100.requestFocus();
            return false;
        } else if (txtDiezHoras100.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar combustibles de 10 horas",
                    "Combustibles materiales 100", JOptionPane.INFORMATION_MESSAGE);
            txtDiezHoras100.requestFocus();
            return false;
        } else if (txtCienHoras100.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar combustibles de 100 horas",
                    "Combustibles materiales 100", JOptionPane.INFORMATION_MESSAGE);
            txtCienHoras100.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean validarOblitariosMateriales1000() {
        if (cmbTransecto1000.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar un numero de transecto",
                    "Combustibles materiales 1000", JOptionPane.INFORMATION_MESSAGE);
            cmbTransecto1000.requestFocus();
            return false;
        } else if (txtDiametro1000.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar el diametro",
                    "Combustibles materiales 1000", JOptionPane.INFORMATION_MESSAGE);
            txtDiametro1000.requestFocus();
            return false;
        } else if (cmbGradoPutrefaccion.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar el grado de putrefacción",
                    "Combustibles materiales 1000", JOptionPane.INFORMATION_MESSAGE);
            cmbGradoPutrefaccion.requestFocus();
            return false;
        } else {
            return true;
        }
    }
    
    private boolean validarOblitariosMateriales1000Modificacion() {
        if (txtDiametro1000.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar el diametro",
                    "Combustibles materiales 1000", JOptionPane.INFORMATION_MESSAGE);
            txtDiametro1000.requestFocus();
            return false;
        } else if (cmbGradoPutrefaccion.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar el grado de putrefacción",
                    "Combustibles materiales 1000", JOptionPane.INFORMATION_MESSAGE);
            cmbGradoPutrefaccion.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean validarObligatoriosCubiertaVegetal() {
        if (cmbNoCubiertaVegetal.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar el transecto",
                    "Cubierta vegetal", JOptionPane.INFORMATION_MESSAGE);
            cmbNoCubiertaVegetal.requestFocus();
            return false;
        } else if (cmbComponentes.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar el componente",
                    "Cubierta vegetal", JOptionPane.INFORMATION_MESSAGE);
            cmbComponentes.requestFocus();
            return false;
        } else if (txtComponente5m.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar distancia 5 metros ",
                    "Cubierta vegetal", JOptionPane.INFORMATION_MESSAGE);
            txtComponente5m.requestFocus();
            return false;
        } else if (txtComponente10m.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar distancia 10 metros ",
                    "Cubierta vegetal", JOptionPane.INFORMATION_MESSAGE);
            txtComponente10m.requestFocus();
            return false;
        } else {
            return true;
        }
    }
    
    private boolean validarDatosMateriales100() {
        ValidacionesComunes validar = new ValidacionesComunes();
        if (validar.esPorcentajeExtendido(this.pendiente)) {
            txtPendiente100.requestFocus();
            return false;
        } else if (this.unaHora < 0 && this.unaHora > 200) {
            txtUnaHora100.requestFocus();
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar un valor entre 0 y 200 ",
                    "Material leñoso 100", JOptionPane.INFORMATION_MESSAGE);
            return false;
        } else if (this.diezHoras < 0 && this.diezHoras > 200) {
            txtDiezHoras100.requestFocus();
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar un valor entre 0 y 200 ",
                    "Material leñoso 100", JOptionPane.INFORMATION_MESSAGE);
            txtDiezHoras100.requestFocus();
            return false;
        } else if (this.cienHoras < 0 && this.cienHoras > 200) {
            txtCienHoras100.requestFocus();
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar un valor entre 0 y 200 ",
                    "Material leñoso 100", JOptionPane.INFORMATION_MESSAGE);
            txtCienHoras100.requestFocus();
            return false;
        } else {
            return true;
        }
    }
    
    public boolean validarDatosCubiertaVegetal() {
        if (this.componente5m < 0 || this.componente5m > 1000) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar un valor entre 0 y 1000 ",
                    "Cubierta vegetal", JOptionPane.INFORMATION_MESSAGE);
            txtComponente5m.requestFocus();
            return false;
        } else if (this.componente10m < 0 || this.componente10m > 1000) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar un valor entre 0 y 1000 ",
                    "Cubierta vegetal", JOptionPane.INFORMATION_MESSAGE);
            txtComponente10m.requestFocus();
            return false;
        } else {
            return true;
        }
    }
    
    private boolean validarCubiertaVegetal() {
        Integer transectoComponente = (Integer) cmbNoCubiertaVegetal.getSelectedItem();
        CatECarbonoComponente caComponente = (CatECarbonoComponente) cmbComponentes.getSelectedItem();
        boolean existe = false;

        if (transectoComponente != null) {
            existe = cdCarbono.validarCubiertaVegetal(this.sitioID, transectoComponente, caComponente.getComponenteID());
            if (existe) {
                JOptionPane.showMessageDialog(null, "Ya existe ese componente con ese transecto, seleccione otro"
                        + "", "Carbono", JOptionPane.INFORMATION_MESSAGE);
                limpiarControlesCubierta();
                cmbNoCubiertaVegetal.requestFocus();
                return false;
            }
        }
        return true;
    }
    
    private void crearMaterialLenioso100(){
        Integer transecto100 = (Integer) cmbTransecto100.getSelectedItem();
        CEMaterialLenioso100 material100 = new CEMaterialLenioso100();
        
        material100.setSitioID(this.sitioID);
        if(transecto100 != null){
            material100.setTransecto(transecto100);
        }
        material100.setPendiente(this.pendiente);
        material100.setUnaHora(this.unaHora);
        material100.setDiezHoras(this.diezHoras);
        material100.setCienHoras(this.cienHoras);
        
        cdCarbono.insertMaterialLenioso100(material100);
    }
    
    private void crearMaterialLenioso1000(){
        Integer transecto1000 = (Integer) cmbTransecto1000.getSelectedItem();
        CEMaterialLenioso1000 material1000 = new CEMaterialLenioso1000();
        Integer gradoPutrefaccion = (Integer) cmbGradoPutrefaccion.getSelectedItem();
        
        material1000.setSitioID(this.sitioID);
        if(transecto1000 != null){
            material1000.setTransecto(transecto1000);
        }
        material1000.setDiametro(this.diametro1000);
        material1000.setGrado(gradoPutrefaccion);
        
        cdCarbono.insertMaterialLenioso1000(material1000);
    }
    
    private void crearCubiertaVegetal(){
        Integer transectoCV = (Integer) cmbNoCubiertaVegetal.getSelectedItem();
        CatECarbonoComponente componente = ( CatECarbonoComponente)cmbComponentes.getSelectedItem();
        CECubiertaVegetal cubierta = new CECubiertaVegetal();
        
        cubierta.setSitioID(this.sitioID);
        if(transectoCV != null){
            cubierta.setTransecto(transectoCV);
        }
        if(componente != null){
            cubierta.setComponente(componente.getComponenteID());
        }
        cubierta.setAltura5(this.componente5m);
        cubierta.setAltura10(this.componente10m);
        
        cdCarbono.insertCubiertaVegetal(cubierta);
    }
    
    private void actualizarMaterialLenioso100() {
        try {
            int fila = grdMaterialLenioso100.getSelectedRow();
            String registro = grdMaterialLenioso100.getValueAt(fila, 0).toString();

            Integer transecto100 = (Integer) cmbTransecto100.getSelectedItem();
            CEMaterialLenioso100 material100 = new CEMaterialLenioso100();

            material100.setMaterialLenioso100ID(Integer.parseInt(registro));
            material100.setSitioID(this.sitioID);
            if (transecto100 != null) {
                material100.setTransecto(transecto100);
            }
            material100.setPendiente(this.pendiente);
            material100.setUnaHora(this.unaHora);
            material100.setDiezHoras(this.diezHoras);
            material100.setCienHoras(this.cienHoras);

            cdCarbono.updateMaterialLenioso100(material100);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla de material leñoso 100"
                    + e.getClass().getName() + " : " + e.getMessage(), "Carbono", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void actualizarMaterialLenioso1000() {
        try {
            int fila = grdMaterialLenioso1000.getSelectedRow();
            String registro = grdMaterialLenioso1000.getValueAt(fila, 0).toString();

            Integer transecto1000 = (Integer) cmbTransecto1000.getSelectedItem();
            CEMaterialLenioso1000 material1000 = new CEMaterialLenioso1000();

            material1000.setMaterialLenioso1000ID(Integer.parseInt(registro));
            material1000.setSitioID(this.sitioID);

            if (transecto1000 != null) {
                material1000.setTransecto(transecto1000);
            }
            material1000.setDiametro(this.diametro1000);
            material1000.setGrado(this.grado1000);

            cdCarbono.updateMaterialLenioso1000(material1000);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla de material leñoso 1000"
                    + e.getClass().getName() + " : " + e.getMessage(), "Carbono", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void actualizarCubiertaVegetal() {
        try {
            int fila = grdCubiertaVegetal.getSelectedRow();
            String registro = grdCubiertaVegetal.getValueAt(fila, 0).toString();
            CatECarbonoComponente componente = (CatECarbonoComponente) cmbComponentes.getSelectedItem();

            Integer transectoCubierta = (Integer) cmbNoCubiertaVegetal.getSelectedItem();
            CECubiertaVegetal ceCubierta = new CECubiertaVegetal();

            ceCubierta.setCubiertaVegetalID(Integer.parseInt(registro));
            ceCubierta.setSitioID(this.sitioID);

            ceCubierta.setSitioID(this.sitioID);
            if (transectoCubierta != null) {
                ceCubierta.setTransecto(transectoCubierta);
            }
            if (componente != null) {
                ceCubierta.setComponente(componente.getComponenteID());
            }
            ceCubierta.setAltura5(this.componente5m);
            ceCubierta.setAltura10(this.componente10m);

            cdCarbono.updateCubiertaVegetal(ceCubierta);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla de cubierta vegetal"
                    + e.getClass().getName() + " : " + e.getMessage(), "Carbono", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void eliminarMaterial100(){
        try{
            int fila = grdMaterialLenioso100.getSelectedRow();
            String registro = grdMaterialLenioso100.getValueAt(fila, 0).toString();
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Esta seguro de borrar el registro de material leñoso 100?",
                    "Carbono", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (respuesta == JOptionPane.YES_OPTION) {
                CEMaterialLenioso100 ml = new CEMaterialLenioso100();
                ml.setMaterialLenioso100ID(Integer.parseInt(registro));
                this.cdCarbono.deleteMaterialLenioso100(ml);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla de material leñoso 100"
                    + "", "Carbono", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void eliminarMaterial1000(){
        try{
            int fila = grdMaterialLenioso1000.getSelectedRow();
            String registro = grdMaterialLenioso1000.getValueAt(fila, 0).toString();
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Esta seguro de borrar el registro de material leñoso 1000?",
                    "Carbono", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (respuesta == JOptionPane.YES_OPTION) {
                CEMaterialLenioso1000 ml = new CEMaterialLenioso1000();
                ml.setMaterialLenioso1000ID(Integer.parseInt(registro));
                this.cdCarbono.deleteMaterialLenioso1000(ml);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla de material leñoso 100"
                    + "", "Carbono", JOptionPane.ERROR_MESSAGE);
        }
    }
    
     private void eliminarCubiertaVegetal(){
        try{
            int fila = grdCubiertaVegetal.getSelectedRow();
            String registro = grdCubiertaVegetal.getValueAt(fila, 0).toString();
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Esta seguro de borrar el registro de cubierta vegetal?",
                    "Carbono", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (respuesta == JOptionPane.YES_OPTION) {
                CECubiertaVegetal ml = new CECubiertaVegetal();
                ml.setCubiertaVegetalID(Integer.parseInt(registro));
                this.cdCarbono.deleteCubiertaVegetal(ml);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla de cubierta vegetal"
                    + "", "Carbono", JOptionPane.INFORMATION_MESSAGE);
        }
    }
     
    private void manipularControlesMaterial100(boolean habilitados) {
        if (habilitados == true) {
            cmbTransecto100.setEnabled(true);
            txtPendiente100.setEnabled(true);
            txtUnaHora100.setEnabled(true);
            txtDiezHoras100.setEnabled(true);
            txtCienHoras100.setEnabled(true);
            btnGuardar100.setEnabled(true);
            btnModificar100.setEnabled(true);
            btnEliminar100.setEnabled(true);
        } else {
            cmbTransecto100.setEnabled(false);
            txtPendiente100.setEnabled(false);
            txtUnaHora100.setEnabled(false);
            txtDiezHoras100.setEnabled(false);
            txtCienHoras100.setEnabled(false);
            btnGuardar100.setEnabled(false);
            btnModificar100.setEnabled(false);
            btnEliminar100.setEnabled(false);
            limpiarControlesMaterial100();
        }
    }
    
    private void manipularControlesMateriales1000(boolean habilitados) {
        if (habilitados == true) {
            cmbTransecto1000.setEnabled(true);
            txtDiametro1000.setEnabled(true);
            cmbGradoPutrefaccion.setEnabled(true);
            btnGuardar1000.setEnabled(true);
            btnModificar1000.setEnabled(true);
            btnEliminar1000.setEnabled(true);
        } else {
            cmbTransecto1000.setEnabled(false);
            txtDiametro1000.setEnabled(false);
            cmbGradoPutrefaccion.setEnabled(false);
            btnGuardar1000.setEnabled(false);
            btnModificar1000.setEnabled(false);
            btnEliminar1000.setEnabled(false);
            limpiarControlesMaterial1000();
        }
    }
    
    private void manipularAlturaPastosHierbas(boolean habilitados) {
        if (habilitados == true) {
            cmbNoCubiertaVegetal.setEnabled(true);
            cmbComponentes.setEnabled(true);
            txtComponente5m.setEnabled(true);
            txtComponente10m.setEnabled(true);
            btnGuardarComponente.setEnabled(true);
            btnModificarComponentes.setEnabled(true);
            btnEliminarComponentes.setEnabled(true);
        } else {
            cmbNoCubiertaVegetal.setEnabled(false);
            cmbComponentes.setEnabled(false);
            txtComponente5m.setEnabled(false);
            txtComponente10m.setEnabled(false);
            btnGuardarComponente.setEnabled(false);
            btnModificarComponentes.setEnabled(false);
            btnEliminarComponentes.setEnabled(false);
            limpiarControlesCubierta();
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
        lblCarbonoIncendios = new javax.swing.JLabel();
        lblMaterialLenioso100 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        cmbTransecto100 = new javax.swing.JComboBox();
        lblTransecto100 = new javax.swing.JLabel();
        lblPendiente = new javax.swing.JLabel();
        lblUnaHora = new javax.swing.JLabel();
        lblDiezHoras = new javax.swing.JLabel();
        lblCienHoras = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnGuardar100 = new javax.swing.JButton();
        btnModificar100 = new javax.swing.JButton();
        btnEliminar100 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        grdMaterialLenioso100 = new javax.swing.JTable();
        txtPendiente100 = new javax.swing.JFormattedTextField();
        txtUnaHora100 = new javax.swing.JFormattedTextField();
        txtDiezHoras100 = new javax.swing.JFormattedTextField();
        txtCienHoras100 = new javax.swing.JFormattedTextField();
        chkMaterialLenioso110100 = new javax.swing.JCheckBox();
        lblMaterialLenioso1000 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        grdMaterialLenioso1000 = new javax.swing.JTable();
        lblGradoT1 = new javax.swing.JLabel();
        lblDiametroT1 = new javax.swing.JLabel();
        lblTransecto101 = new javax.swing.JLabel();
        cmbTransecto1000 = new javax.swing.JComboBox();
        jPanel8 = new javax.swing.JPanel();
        btnGuardar1000 = new javax.swing.JButton();
        btnModificar1000 = new javax.swing.JButton();
        btnEliminar1000 = new javax.swing.JButton();
        txtDiametro1000 = new javax.swing.JFormattedTextField();
        chkMaterialLenioso1000 = new javax.swing.JCheckBox();
        cmbGradoPutrefaccion = new javax.swing.JComboBox();
        lblMaterialLenioso102 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        cmbNoCubiertaVegetal = new javax.swing.JComboBox();
        lblTransectoArbustos = new javax.swing.JLabel();
        lblArbustos5m = new javax.swing.JLabel();
        lbl1Arbustos10m = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        btnGuardarComponente = new javax.swing.JButton();
        btnModificarComponentes = new javax.swing.JButton();
        btnEliminarComponentes = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        grdCubiertaVegetal = new javax.swing.JTable();
        lblTransectoPastos1 = new javax.swing.JLabel();
        cmbComponentes = new javax.swing.JComboBox();
        txtComponente5m = new javax.swing.JFormattedTextField();
        txtComponente10m = new javax.swing.JFormattedTextField();
        chkAlturaArbustosHierbasPastos = new javax.swing.JCheckBox();
        btnContinuar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setMaximizable(true);
        setTitle("Material leñoso caído de 100 y 1000, altura por forma biológica, módulo C "+version);
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

        lblCarbonoIncendios.setBackground(new java.awt.Color(153, 153, 153));
        lblCarbonoIncendios.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCarbonoIncendios.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCarbonoIncendios.setText("Carbono e incendios");
        lblCarbonoIncendios.setOpaque(true);

        lblMaterialLenioso100.setBackground(new java.awt.Color(153, 153, 153));
        lblMaterialLenioso100.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMaterialLenioso100.setText("Material leñoso caído 1, 10 y 100 hrs");
        lblMaterialLenioso100.setOpaque(true);

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTransecto100.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTransecto100.setText("Transecto");

        lblPendiente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPendiente.setText("Pendiente %");

        lblUnaHora.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUnaHora.setText("1 Hora");

        lblDiezHoras.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDiezHoras.setText("10 Horas");

        lblCienHoras.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCienHoras.setText("100 Horas");

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnGuardar100.setText("Guardar");
        btnGuardar100.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar100ActionPerformed(evt);
            }
        });

        btnModificar100.setText("Modificar");
        btnModificar100.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificar100ActionPerformed(evt);
            }
        });

        btnEliminar100.setText("Eliminar");
        btnEliminar100.setNextFocusableComponent(chkMaterialLenioso1000);
        btnEliminar100.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminar100ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnGuardar100)
                .addGap(26, 26, 26)
                .addComponent(btnModificar100)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEliminar100, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar100)
                    .addComponent(btnModificar100)
                    .addComponent(btnEliminar100))
                .addContainerGap())
        );

        grdMaterialLenioso100.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        grdMaterialLenioso100.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        grdMaterialLenioso100.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grdMaterialLenioso100MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(grdMaterialLenioso100);

        txtPendiente100.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat(""))));
        txtPendiente100.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPendiente100FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPendiente100FocusLost(evt);
            }
        });
        txtPendiente100.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPendiente100KeyTyped(evt);
            }
        });

        txtUnaHora100.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat(""))));
        txtUnaHora100.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtUnaHora100FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtUnaHora100FocusLost(evt);
            }
        });
        txtUnaHora100.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUnaHora100KeyTyped(evt);
            }
        });

        txtDiezHoras100.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat(""))));
        txtDiezHoras100.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDiezHoras100FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDiezHoras100FocusLost(evt);
            }
        });
        txtDiezHoras100.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiezHoras100ActionPerformed(evt);
            }
        });
        txtDiezHoras100.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDiezHoras100KeyTyped(evt);
            }
        });

        txtCienHoras100.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat(""))));
        txtCienHoras100.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCienHoras100FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCienHoras100FocusLost(evt);
            }
        });
        txtCienHoras100.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCienHoras100KeyTyped(evt);
            }
        });

        chkMaterialLenioso110100.setBackground(new java.awt.Color(204, 204, 204));
        chkMaterialLenioso110100.setSelected(true);
        chkMaterialLenioso110100.setText("Material leñoso caído 1, 10 y 100");
        chkMaterialLenioso110100.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkMaterialLenioso110100ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkMaterialLenioso110100)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblTransecto100, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmbTransecto100, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblPendiente, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGap(2, 2, 2)
                                    .addComponent(txtPendiente100, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblUnaHora, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtUnaHora100, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblDiezHoras, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtDiezHoras100, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblCienHoras, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtCienHoras100, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chkMaterialLenioso110100)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lblCienHoras)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDiezHoras100, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCienHoras100, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lblDiezHoras)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUnaHora100, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblUnaHora)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTransecto100)
                            .addComponent(lblPendiente))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbTransecto100, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPendiente100, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        lblMaterialLenioso1000.setBackground(new java.awt.Color(153, 153, 153));
        lblMaterialLenioso1000.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMaterialLenioso1000.setText("Material leñoso caído 1000 hrs");
        lblMaterialLenioso1000.setOpaque(true);

        jPanel7.setBackground(new java.awt.Color(204, 204, 204));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        grdMaterialLenioso1000.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        grdMaterialLenioso1000.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        grdMaterialLenioso1000.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grdMaterialLenioso1000MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(grdMaterialLenioso1000);

        lblGradoT1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGradoT1.setText("Grado");

        lblDiametroT1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDiametroT1.setText("Diámetro");

        lblTransecto101.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTransecto101.setText("Transecto");

        jPanel8.setBackground(new java.awt.Color(204, 204, 204));
        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnGuardar1000.setText("Guardar");
        btnGuardar1000.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar1000ActionPerformed(evt);
            }
        });

        btnModificar1000.setText("Modificar");
        btnModificar1000.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificar1000ActionPerformed(evt);
            }
        });

        btnEliminar1000.setText("Eliminar");
        btnEliminar1000.setNextFocusableComponent(chkAlturaArbustosHierbasPastos);
        btnEliminar1000.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminar1000ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnGuardar1000)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnModificar1000)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminar1000, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar1000)
                    .addComponent(btnModificar1000)
                    .addComponent(btnEliminar1000))
                .addContainerGap())
        );

        txtDiametro1000.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat(""))));
        txtDiametro1000.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDiametro1000FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDiametro1000FocusLost(evt);
            }
        });
        txtDiametro1000.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDiametro1000KeyTyped(evt);
            }
        });

        chkMaterialLenioso1000.setBackground(new java.awt.Color(204, 204, 204));
        chkMaterialLenioso1000.setSelected(true);
        chkMaterialLenioso1000.setText("Material leñoso caído 1000");
        chkMaterialLenioso1000.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkMaterialLenioso1000ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkMaterialLenioso1000)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cmbTransecto1000, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblTransecto101, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblDiametroT1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtDiametro1000, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblGradoT1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addComponent(cmbGradoPutrefaccion, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chkMaterialLenioso1000)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(lblTransecto101)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbTransecto1000, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblGradoT1)
                    .addComponent(lblDiametroT1)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDiametro1000, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbGradoPutrefaccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        lblMaterialLenioso102.setBackground(new java.awt.Color(153, 153, 153));
        lblMaterialLenioso102.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMaterialLenioso102.setText("Altura de arbustos, hierbas y pastos");
        lblMaterialLenioso102.setOpaque(true);

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTransectoArbustos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTransectoArbustos.setText("Transecto");

        lblArbustos5m.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblArbustos5m.setText("5 m");

        lbl1Arbustos10m.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl1Arbustos10m.setText("10 m");

        jPanel9.setBackground(new java.awt.Color(204, 204, 204));
        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnGuardarComponente.setText("Guardar");
        btnGuardarComponente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarComponenteActionPerformed(evt);
            }
        });

        btnModificarComponentes.setText("Modificar");
        btnModificarComponentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarComponentesActionPerformed(evt);
            }
        });

        btnEliminarComponentes.setText("Eliminar");
        btnEliminarComponentes.setNextFocusableComponent(btnContinuar);
        btnEliminarComponentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarComponentesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(btnGuardarComponente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnModificarComponentes, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminarComponentes)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarComponente)
                    .addComponent(btnModificarComponentes)
                    .addComponent(btnEliminarComponentes))
                .addContainerGap())
        );

        grdCubiertaVegetal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        grdCubiertaVegetal.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        grdCubiertaVegetal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grdCubiertaVegetalMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(grdCubiertaVegetal);

        lblTransectoPastos1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTransectoPastos1.setText("Componente");

        txtComponente5m.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat(""))));
        txtComponente5m.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtComponente5mFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtComponente5mFocusLost(evt);
            }
        });
        txtComponente5m.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtComponente5mKeyTyped(evt);
            }
        });

        txtComponente10m.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat(""))));
        txtComponente10m.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtComponente10mFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtComponente10mFocusLost(evt);
            }
        });
        txtComponente10m.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtComponente10mKeyTyped(evt);
            }
        });

        chkAlturaArbustosHierbasPastos.setBackground(new java.awt.Color(204, 204, 204));
        chkAlturaArbustosHierbasPastos.setSelected(true);
        chkAlturaArbustosHierbasPastos.setText("Altura de arbustos, hierbas y pastos");
        chkAlturaArbustosHierbasPastos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAlturaArbustosHierbasPastosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(chkAlturaArbustosHierbasPastos)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 258, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbNoCubiertaVegetal, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTransectoArbustos, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbComponentes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTransectoPastos1, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblArbustos5m, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtComponente5m, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl1Arbustos10m, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(txtComponente10m, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 5, Short.MAX_VALUE))))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chkAlturaArbustosHierbasPastos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lbl1Arbustos10m)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addComponent(lblTransectoArbustos)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cmbNoCubiertaVegetal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addComponent(lblTransectoPastos1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmbComponentes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtComponente10m, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblArbustos5m)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addComponent(txtComponente5m, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblUPM)
                        .addGap(10, 10, 10)
                        .addComponent(txtUPM, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(608, 608, 608)
                        .addComponent(lblSitio)
                        .addGap(8, 8, 8)
                        .addComponent(txtSitio, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnContinuar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblMaterialLenioso100, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblMaterialLenioso1000, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblMaterialLenioso102, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(lblCarbonoIncendios, javax.swing.GroupLayout.PREFERRED_SIZE, 909, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(18, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSitio)
                    .addComponent(txtSitio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(lblUPM))
                    .addComponent(txtUPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(lblCarbonoIncendios)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMaterialLenioso1000)
                            .addComponent(lblMaterialLenioso102))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnContinuar)
                            .addComponent(btnSalir)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblMaterialLenioso100)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardar100ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar100ActionPerformed
        DefaultComboBoxModel dcm = (DefaultComboBoxModel) cmbTransecto100.getModel();
        asignarDatosMaterial100();
        if (validarObligatorioMaterial100() && validarDatosMateriales100()) {
            crearMaterialLenioso100();
            llenarTablaMaterialLenioso100();
            limpiarControlesMaterial100();
            dcm.removeAllElements();
            fillCmbMaterial100();
        }
    }//GEN-LAST:event_btnGuardar100ActionPerformed

    private void btnGuardar1000ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar1000ActionPerformed
        asignarDatosMaterial1000();
        if (validarOblitariosMateriales1000()) {
            crearMaterialLenioso1000();
            limpiarControlesMaterial1000();
            llenarTablaMaterialLenioso1000();
        }
    }//GEN-LAST:event_btnGuardar1000ActionPerformed

    private void txtPendiente100FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPendiente100FocusGained
       SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                txtPendiente100.selectAll();
            }
        });
    }//GEN-LAST:event_txtPendiente100FocusGained

    private void txtUnaHora100FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUnaHora100FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtUnaHora100.selectAll();
            }
        });
    }//GEN-LAST:event_txtUnaHora100FocusGained

    private void txtDiezHoras100ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiezHoras100ActionPerformed
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtDiezHoras100.selectAll();
            }
        });
    }//GEN-LAST:event_txtDiezHoras100ActionPerformed

    private void txtCienHoras100FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCienHoras100FocusGained
       SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtCienHoras100.selectAll();
            }
        });
    }//GEN-LAST:event_txtCienHoras100FocusGained

    private void txtDiametro1000FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDiametro1000FocusGained
         SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtDiametro1000.selectAll();
            }
        });
    }//GEN-LAST:event_txtDiametro1000FocusGained

    private void txtComponente5mFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtComponente5mFocusGained
       SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtComponente5m.selectAll();
            }
        });
    }//GEN-LAST:event_txtComponente5mFocusGained

    private void txtComponente10mFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtComponente10mFocusGained
         SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtComponente10m.selectAll();
            }
        });
    }//GEN-LAST:event_txtComponente10mFocusGained

    private void txtPendiente100FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPendiente100FocusLost
         if(txtPendiente100.getText().isEmpty()){
            txtPendiente100.setValue(null);
        }
    }//GEN-LAST:event_txtPendiente100FocusLost

    private void txtUnaHora100FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUnaHora100FocusLost
        if(txtUnaHora100.getText().isEmpty()){
            txtUnaHora100.setValue(null);
        }
    }//GEN-LAST:event_txtUnaHora100FocusLost

    private void txtDiezHoras100FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDiezHoras100FocusGained
         SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtDiezHoras100.selectAll();
            }
        });
    }//GEN-LAST:event_txtDiezHoras100FocusGained

    private void txtDiezHoras100FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDiezHoras100FocusLost
        if(txtDiezHoras100.getText().isEmpty()){
            txtDiezHoras100.setValue(null);
        }
    }//GEN-LAST:event_txtDiezHoras100FocusLost

    private void txtCienHoras100FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCienHoras100FocusLost
         if(txtCienHoras100.getText().isEmpty()){
            txtCienHoras100.setValue(null);
        }
    }//GEN-LAST:event_txtCienHoras100FocusLost

    private void txtDiametro1000FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDiametro1000FocusLost
        if(txtDiametro1000.getText().isEmpty()){
            txtDiametro1000.setValue(null);
        }
    }//GEN-LAST:event_txtDiametro1000FocusLost

    private void txtComponente5mFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtComponente5mFocusLost
       if(txtComponente5m.getText().isEmpty()){
            txtComponente5m.setValue(null);
        }
    }//GEN-LAST:event_txtComponente5mFocusLost

    private void txtComponente10mFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtComponente10mFocusLost
       if(txtComponente10m.getText().isEmpty()){
            txtComponente10m.setValue(null);
        }
    }//GEN-LAST:event_txtComponente10mFocusLost

    private void btnContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuarActionPerformed
        if (chkMaterialLenioso110100.isSelected() && this.cdCarbono.hayMaterialLenioso100(this.ceSitio.getSitioID())) {
            JOptionPane.showMessageDialog(null, "Si selecciona Material leñoso caido 1, 10 y 100 se debe capturar"
                    + "", "Carbono e incendios", JOptionPane.INFORMATION_MESSAGE);
            cmbTransecto100.requestFocus();
        } else if (chkMaterialLenioso1000.isSelected() && this.cdCarbono.hayMaterialLenioso1000(this.ceSitio.getSitioID())) {
            JOptionPane.showMessageDialog(null, "Si selecciona Material leñoso caido 1000 se debe caprturar"
                    + "", "Carbono e incendios", JOptionPane.INFORMATION_MESSAGE);
            cmbTransecto1000.requestFocus();
        } else if (chkAlturaArbustosHierbasPastos.isSelected() && this.cdCarbono.hayCubiertaVegetal(this.ceSitio.getSitioID())) {
            JOptionPane.showMessageDialog(null, "Si selecciona cubierta vegetal, se debe de capturar"
                    + "", "Carbono e incendios", JOptionPane.INFORMATION_MESSAGE);
        } else if (this.modificar == 0) {
            this.hide();
            UPMForms.longitud.setDatosIniciales(this.ceSitio);
            UPMForms.longitud.setVisible(true);
            this.cdSecuencia.updateSecuencia(this.ceSitio, FORMATO_ID, 1);
            funciones.manipularBotonesMenuPrincipal(true);
        } else {
            this.hide();
            UPMForms.longitud.revisarLongitud(this.ceSitio);
            UPMForms.longitud.setVisible(true);
            funciones.manipularBotonesMenuPrincipal(true);
        }
    }//GEN-LAST:event_btnContinuarActionPerformed

    private void btnGuardarComponenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarComponenteActionPerformed
        asignarDatosCubiertaVegetal();
        if(validarObligatoriosCubiertaVegetal() && validarDatosCubiertaVegetal() && validarDatosCubiertaVegetal() && validarCubiertaVegetal()){
           crearCubiertaVegetal();
           limpiarControlesCubierta();
           llenarTablaCubiertaVegetal();
       }
    }//GEN-LAST:event_btnGuardarComponenteActionPerformed

    private void btnModificar100ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificar100ActionPerformed
         asignarDatosMaterial100();
        if(validarObligatorioMaterial100Modificacion()&& validarDatosMateriales100()){
            actualizarMaterialLenioso100();
            limpiarControlesMaterial100();
            llenarTablaMaterialLenioso100();
        }
    }//GEN-LAST:event_btnModificar100ActionPerformed

    private void btnModificar1000ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificar1000ActionPerformed
        asignarDatosMaterial1000();
        if (validarOblitariosMateriales1000()) {
            actualizarMaterialLenioso1000();
            limpiarControlesMaterial1000();
            llenarTablaMaterialLenioso1000();
        }
    }//GEN-LAST:event_btnModificar1000ActionPerformed

    private void btnModificarComponentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarComponentesActionPerformed
        asignarDatosCubiertaVegetal();
        if(validarDatosCubiertaVegetal() && validarDatosCubiertaVegetal()){
           actualizarCubiertaVegetal();
           limpiarControlesCubierta();
           llenarTablaCubiertaVegetal();
   
       }
    }//GEN-LAST:event_btnModificarComponentesActionPerformed

    private void btnEliminar100ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminar100ActionPerformed
        DefaultComboBoxModel dcm = (DefaultComboBoxModel) cmbTransecto100.getModel();
        eliminarMaterial100();
        limpiarControlesMaterial100();
        llenarTablaMaterialLenioso100();
        dcm.removeAllElements();
        fillCmbMaterial100();
    }//GEN-LAST:event_btnEliminar100ActionPerformed

    private void btnEliminar1000ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminar1000ActionPerformed
       eliminarMaterial1000();
       limpiarControlesMaterial1000();
       llenarTablaMaterialLenioso1000();
    }//GEN-LAST:event_btnEliminar1000ActionPerformed

    private void btnEliminarComponentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarComponentesActionPerformed
        eliminarCubiertaVegetal();
        limpiarControlesCubierta();
        llenarTablaCubiertaVegetal();
    }//GEN-LAST:event_btnEliminarComponentesActionPerformed

    private void grdMaterialLenioso100MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grdMaterialLenioso100MouseClicked
        if (evt.getButton() == 1) {
            int fila = grdMaterialLenioso100.getSelectedRow();
            String mlID = grdMaterialLenioso100.getValueAt(fila, 0).toString();
            this.material100ID = Integer.parseInt(mlID);
            CEMaterialLenioso100 ml100 = cdCarbono.getRegistroMaterialLenioso100(this.material100ID);
            Integer transecto = ml100.getTransecto();
            txtPendiente100.setText(String.valueOf(ml100.getPendiente()));
            txtUnaHora100.setText(String.valueOf(ml100.getUnaHora()));
            txtDiezHoras100.setText(String.valueOf(ml100.getDiezHoras()));
            txtCienHoras100.setText(String.valueOf(ml100.getCienHoras()));
        }
    }//GEN-LAST:event_grdMaterialLenioso100MouseClicked

    private void grdMaterialLenioso1000MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grdMaterialLenioso1000MouseClicked
        if (evt.getButton() == 1) {
            int fila = grdMaterialLenioso1000.getSelectedRow();
            String mlID = grdMaterialLenioso1000.getValueAt(fila, 0).toString();
            this.materialLenioso100ID = Integer.parseInt(mlID);
            CEMaterialLenioso1000 ml1000 = cdCarbono.getRegistroMaterialLenioso1000(this.materialLenioso100ID);
            Integer transecto = ml1000.getTransecto();
            cmbTransecto1000.setSelectedItem(transecto);
            txtDiametro1000.setText(String.valueOf(ml1000.getDiametro()));
            Integer grado = ml1000.getGrado();
            cmbGradoPutrefaccion.setSelectedItem(grado);
        }
    }//GEN-LAST:event_grdMaterialLenioso1000MouseClicked

    private void grdCubiertaVegetalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grdCubiertaVegetalMouseClicked
        if (evt.getButton() == 1) {
            int fila = grdCubiertaVegetal.getSelectedRow();
            String cv = grdCubiertaVegetal.getValueAt(fila, 0).toString();
            this.cubiertaVegetalID = Integer.parseInt(cv);
            CECubiertaVegetal ceCubierta = cdCarbono.getCubiertaVegetal(this.cubiertaVegetalID);
            Integer transecto = ceCubierta.getTransecto();
            CatECarbonoComponente componente = new CatECarbonoComponente();
            componente.setComponenteID(ceCubierta.getComponente());
            cmbNoCubiertaVegetal.setSelectedItem(transecto);
            cmbComponentes.setSelectedItem(componente);
            txtComponente5m.setText(String.valueOf(ceCubierta.getAltura5()));
            txtComponente10m.setText(String.valueOf(ceCubierta.getAltura10()));
        }
    }//GEN-LAST:event_grdCubiertaVegetalMouseClicked

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

    private void txtPendiente100KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPendiente100KeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtPendiente100KeyTyped

    private void txtUnaHora100KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUnaHora100KeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtUnaHora100KeyTyped

    private void txtDiezHoras100KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiezHoras100KeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtDiezHoras100KeyTyped

    private void txtCienHoras100KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCienHoras100KeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtCienHoras100KeyTyped

    private void txtDiametro1000KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiametro1000KeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtDiametro1000KeyTyped

    private void txtComponente5mKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtComponente5mKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtComponente5mKeyTyped

    private void txtComponente10mKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtComponente10mKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtComponente10mKeyTyped

    private void chkMaterialLenioso110100ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkMaterialLenioso110100ActionPerformed
        if (chkMaterialLenioso110100.isSelected()) {
            manipularControlesMaterial100(true);
        } else {
            int respuesta = JOptionPane.showConfirmDialog(null, "Si capturo, se borrarán los datos de combustibles 1, 10 y 100, ¿Esta seguro?",
                    "Carbono e incendios", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (respuesta == JOptionPane.YES_OPTION) {
                cdCarbono.deleteMaterialLenioso100Sitio(this.sitioID);
                this.funciones.reiniciarComboModel(cmbTransecto100);
                fillCmbMaterial100();
                this.funciones.reiniciarTabla(this.grdMaterialLenioso100);
                llenarTablaMaterialLenioso100();
                manipularControlesMaterial100(false);
                limpiarControlesMaterial100();
            } else {
                chkMaterialLenioso110100.setSelected(true);
                chkMaterialLenioso110100.requestFocus();
            }
        }
    }//GEN-LAST:event_chkMaterialLenioso110100ActionPerformed

    private void chkMaterialLenioso1000ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkMaterialLenioso1000ActionPerformed
        if (chkMaterialLenioso1000.isSelected()) {
            manipularControlesMateriales1000(true);
        } else {
            int respuesta = JOptionPane.showConfirmDialog(null, "Si capturo, se borrarán los datos de combustibles 1000, ¿Esta seguro?",
                    "Carbono e incendios", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (respuesta == JOptionPane.YES_OPTION) {
                cdCarbono.deleteMaterialLenioso1000Sitio(this.sitioID);
                this.funciones.reiniciarComboModel(cmbTransecto1000);
                fillCmbTransecto1000();
                this.funciones.reiniciarTabla(grdMaterialLenioso1000);
                llenarTablaMaterialLenioso1000();
                manipularControlesMateriales1000(false);
            } else {
                chkMaterialLenioso1000.setSelected(true);
                chkMaterialLenioso1000.requestFocus();
            }
        }
    }//GEN-LAST:event_chkMaterialLenioso1000ActionPerformed

    private void chkAlturaArbustosHierbasPastosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAlturaArbustosHierbasPastosActionPerformed
        if(chkAlturaArbustosHierbasPastos.isSelected()){
            manipularAlturaPastosHierbas(true);
        } else {
            int respuesta = JOptionPane.showConfirmDialog(null, "Si capturo, se borrarán los datos de combustibles 1000, ¿Esta seguro?",
                    "Carbono e incendios", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (respuesta == JOptionPane.YES_OPTION) {
                cdCarbono.deleteCubiertaVegetalSitio(this.sitioID);
                this.funciones.reiniciarComboModel(cmbNoCubiertaVegetal);
                fillCmbCubiertaVegetal();
                this.funciones.reiniciarComboModel(cmbComponentes);
                fillCmbComponentes();
                this.funciones.reiniciarTabla(grdCubiertaVegetal);
                llenarTablaCubiertaVegetal();
                manipularAlturaPastosHierbas(false);
            } else {
                chkAlturaArbustosHierbasPastos.setSelected(true);
                chkAlturaArbustosHierbasPastos.requestFocus();
            }
        }
    }//GEN-LAST:event_chkAlturaArbustosHierbasPastosActionPerformed

    private void limpiarControlesMaterial100(){
        cmbTransecto100.setSelectedItem(null);
        txtPendiente100.setText("");
        txtPendiente100.setValue(null);
        txtUnaHora100.setText("");
        txtUnaHora100.setValue(null);
        txtDiezHoras100.setText("");
        txtDiezHoras100.setValue(null);
        txtCienHoras100.setText("");
        txtCienHoras100.setValue(null);
        cmbTransecto100.requestFocus();
    }
    
    private void limpiarControlesMaterial1000(){
        cmbTransecto1000.setSelectedItem(null);
        txtDiametro1000.setText("");
        txtDiametro1000.setValue(null);
        cmbGradoPutrefaccion.setSelectedItem(null);
        cmbTransecto1000.requestFocus();
    }
    
    private void limpiarControlesCubierta(){
        cmbNoCubiertaVegetal.setSelectedItem(null);
        cmbComponentes.setSelectedItem(null);
        txtComponente5m.setText("");
        txtComponente5m.setValue(null);
        txtComponente10m.setText("");
        txtComponente10m.setValue(null);
        cmbNoCubiertaVegetal.requestFocus();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnContinuar;
    private javax.swing.JButton btnEliminar100;
    private javax.swing.JButton btnEliminar1000;
    private javax.swing.JButton btnEliminarComponentes;
    private javax.swing.JButton btnGuardar100;
    private javax.swing.JButton btnGuardar1000;
    private javax.swing.JButton btnGuardarComponente;
    private javax.swing.JButton btnModificar100;
    private javax.swing.JButton btnModificar1000;
    private javax.swing.JButton btnModificarComponentes;
    private javax.swing.JButton btnSalir;
    private javax.swing.JCheckBox chkAlturaArbustosHierbasPastos;
    private javax.swing.JCheckBox chkMaterialLenioso1000;
    private javax.swing.JCheckBox chkMaterialLenioso110100;
    private javax.swing.JComboBox cmbComponentes;
    private javax.swing.JComboBox cmbGradoPutrefaccion;
    private javax.swing.JComboBox cmbNoCubiertaVegetal;
    private javax.swing.JComboBox cmbTransecto100;
    private javax.swing.JComboBox cmbTransecto1000;
    private javax.swing.JTable grdCubiertaVegetal;
    private javax.swing.JTable grdMaterialLenioso100;
    private javax.swing.JTable grdMaterialLenioso1000;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lbl1Arbustos10m;
    private javax.swing.JLabel lblArbustos5m;
    private javax.swing.JLabel lblCarbonoIncendios;
    private javax.swing.JLabel lblCienHoras;
    private javax.swing.JLabel lblDiametroT1;
    private javax.swing.JLabel lblDiezHoras;
    private javax.swing.JLabel lblGradoT1;
    private javax.swing.JLabel lblMaterialLenioso100;
    private javax.swing.JLabel lblMaterialLenioso1000;
    private javax.swing.JLabel lblMaterialLenioso102;
    private javax.swing.JLabel lblPendiente;
    private javax.swing.JLabel lblSitio;
    private javax.swing.JLabel lblTransecto100;
    private javax.swing.JLabel lblTransecto101;
    private javax.swing.JLabel lblTransectoArbustos;
    private javax.swing.JLabel lblTransectoPastos1;
    private javax.swing.JLabel lblUPM;
    private javax.swing.JLabel lblUnaHora;
    private javax.swing.JFormattedTextField txtCienHoras100;
    private javax.swing.JFormattedTextField txtComponente10m;
    private javax.swing.JFormattedTextField txtComponente5m;
    private javax.swing.JFormattedTextField txtDiametro1000;
    private javax.swing.JFormattedTextField txtDiezHoras100;
    private javax.swing.JFormattedTextField txtPendiente100;
    private javax.swing.JTextField txtSitio;
    private javax.swing.JTextField txtUPM;
    private javax.swing.JFormattedTextField txtUnaHora100;
    // End of variables declaration//GEN-END:variables
}
