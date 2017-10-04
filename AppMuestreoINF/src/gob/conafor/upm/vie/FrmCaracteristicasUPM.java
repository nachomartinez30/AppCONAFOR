package gob.conafor.upm.vie;

import gob.conafor.sitio.mod.CDSitio;
import gob.conafor.sitio.mod.CESitio;
import gob.conafor.sys.mod.CDSecuencia;
import gob.conafor.sys.mod.CDSeguimientoUPM;
import gob.conafor.sys.mod.CESeccionesCapturadas;
import gob.conafor.upm.mod.CDEpifitas;
import gob.conafor.upm.mod.CDUpm;
import gob.conafor.upm.mod.CEEpifita;
import gob.conafor.upm.mod.CEUPM;
import gob.conafor.upm.mod.CatEPresenciaEpifita;
import gob.conafor.upm.mod.CatETipoEpifita;
import gob.conafor.upm.mod.CatETipoExposicion;
import gob.conafor.upm.mod.CatETipoFisiografia;
import gob.conafor.utils.Datos;
import gob.conafor.utils.FuncionesComunes;
import gob.conafor.utils.Tablas;
import gob.conafor.utils.Version;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class FrmCaracteristicasUPM extends javax.swing.JInternalFrame {
    private boolean revision;
    private int upmID;
    private CESitio ceSitio = new CESitio();
    private static final int FORMATO_ID = 6;
    private CDUpm cdUPM = new CDUpm();
    private CEUPM ceUpm = new CEUPM();
    private CDEpifitas cdEpifita = new CDEpifitas();
    private CEEpifita ceEpifita = new CEEpifita();
    private int epifitaID;
    private Float altitud;
    private Integer pendiente;
    private CESeccionesCapturadas seccionActual = new CESeccionesCapturadas();
    private CESeccionesCapturadas seccionSiguiente = new CESeccionesCapturadas();
    private CDSeguimientoUPM seguimiento = new CDSeguimientoUPM();
    private Datos numeros = new Datos();
    private CDSecuencia cdSecuencia = new CDSecuencia();
    private int modificar;
    private FuncionesComunes funciones = new FuncionesComunes();
    private Version ver=new Version();
    private String version=ver.getVersion();
    private FuncionesComunes combo = new FuncionesComunes();
    private CDSitio cdSitio = new CDSitio();

    public FrmCaracteristicasUPM() {
        initComponents();
        fillCmbFisiografia();
        fillCmbExposicion();
        fillCmbTipoEpifita();
        fillCmbPresenciaTroncos();
        fillCmbPresenciaRamas();
    }

    public void setDatosIniciales(CESitio ceSitio) {
        this.upmID = ceSitio.getUpmID();
        
        this.ceSitio = ceSitio;
        DefaultComboBoxModel dcm = (DefaultComboBoxModel) cmbClaseTipo.getModel();
        dcm.removeAllElements();
        fillCmbTipoEpifita();
        llenarTablaEpifitas();
        this.modificar = 0;
        funciones.manipularBotonesMenuPrincipal(true);
        limpiarControles();
        limpiarControlesUPM();
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


    
    
    
    public void revisarCaracteristicasUPM(int upmid) {
        //revision=true;
        //this.upmID = ceSitio.getUpmID();
        
        //this.ceSitio = ceSitio;
        DefaultComboBoxModel dcm = (DefaultComboBoxModel) cmbClaseTipo.getModel();
        dcm.removeAllElements();
        fillCmbTipoEpifita();
        llenarTablaEpifitas();
        this.ceUpm = this.cdUPM.getCaracteristicasUPM(upmid);
        txtAltitud.setText(String.valueOf(this.ceUpm.getAltitud()));
        txtPendiente.setText(String.valueOf(this.ceUpm.getPendienteRepresentativa()));
        CatETipoFisiografia ceFisiografia = new CatETipoFisiografia();
        ceFisiografia.setFisiografiaID(this.ceUpm.getFisiografiaID());
        cmbFisiografia.setSelectedItem(ceFisiografia);
        CatETipoExposicion ceExposicion = new CatETipoExposicion();
        ceExposicion.setExposicionID(this.ceUpm.getExposicionID());
        cmbExposicion.setSelectedItem(ceExposicion);
        //this.modificar = 1;
        funciones.manipularBotonesMenuPrincipal(true);
    }

    private void fillCmbFisiografia() {
        List<CatETipoFisiografia> listFisiografia = new ArrayList<>();
        listFisiografia = cdUPM.getTipoFisiografia();
        if (listFisiografia != null) {
            int size = listFisiografia.size();
            for (int i = 0; i < size; i++) {
                cmbFisiografia.addItem(listFisiografia.get(i));
            }
        }
    }

    private void fillCmbExposicion() {
        List<CatETipoExposicion> listExposicion = new ArrayList<>();
        listExposicion = cdUPM.getTipoExposicion();
        if (listExposicion != null) {
            int size = listExposicion.size();
            for (int i = 0; i < size; i++) {
                cmbExposicion.addItem(listExposicion.get(i));
            }
        }
    }

    private void fillCmbTipoEpifita() {
        List<CatETipoEpifita> listTipoEpifita = new ArrayList<>();
        listTipoEpifita = this.cdEpifita.getTipoEpiftasNoCapturadas(this.upmID);
        if (listTipoEpifita != null) {
            int size = listTipoEpifita.size();
            for (int i = 0; i < size; i++) {
                cmbClaseTipo.addItem(listTipoEpifita.get(i));
            }
        }
    }

    private void fillCmbPresenciaTroncos() {
        List<CatEPresenciaEpifita> listPresencia = new ArrayList<>();
        listPresencia = this.cdEpifita.getPresenciaEpifita();
        if (listPresencia != null) {
            int size = listPresencia.size();
            for (int i = 0; i < size; i++) {
                cmbPresenciaTroncos.addItem(listPresencia.get(i));
            }
        }
    }

    private void fillCmbPresenciaRamas() {
        List<CatEPresenciaEpifita> listPresencia = new ArrayList<>();
        listPresencia = this.cdEpifita.getPresenciaEpifita();
        if (listPresencia != null) {
            int size = listPresencia.size();
            for (int i = 0; i < size; i++) {
                cmbPresenciaRamas.addItem(listPresencia.get(i));
            }
        }
    }

    private void llenarTablaEpifitas() {
        grdEpifitas.setModel(this.cdEpifita.getTablaEpifitas(this.upmID));
        grdEpifitas.getColumnModel().getColumn(2).setPreferredWidth(70);
        grdEpifitas.getColumnModel().getColumn(3).setPreferredWidth(100);
        grdEpifitas.getColumnModel().getColumn(4).setPreferredWidth(100);
        Tablas tabla = new Tablas();
        int[] column_0 = {0};
        int[] column_1 = {1};
        tabla.hideColumnTable(grdEpifitas, column_0);
        tabla.hideColumnTable(grdEpifitas, column_1);
    }

    private void asignarDatosUPM() {
        try {
            this.altitud = Float.valueOf(txtAltitud.getText());
        } catch (NumberFormatException e) {
            this.altitud = null;
        }
        try {
            this.pendiente = Integer.valueOf(txtPendiente.getText());
        } catch (NumberFormatException e) {
            this.pendiente = null;
        }
    }

    private void crearCaracteristicasUPM() {
        this.upmID = (Integer) cmbUPMID.getSelectedItem();
        CatETipoFisiografia fisiografiaID = (CatETipoFisiografia) cmbFisiografia.getSelectedItem();
        CatETipoExposicion exposicionID = (CatETipoExposicion) cmbExposicion.getSelectedItem();
        CEUPM ceUpm = new CEUPM();
        ceUpm.setUpmID(this.upmID);
        ceUpm.setAltitud(this.altitud);
        ceUpm.setPendienteRepresentativa(this.pendiente);
        ceUpm.setFisiografiaID(fisiografiaID.getFisiografiaID());
        ceUpm.setExposicionID(exposicionID.getExposicionID());
        this.cdUPM.updateCaracteristicasUPM(ceUpm);
    }

    private void crearEpifita() {
        CatETipoEpifita indexTipoEpifita = (CatETipoEpifita) cmbClaseTipo.getSelectedItem();
        CatEPresenciaEpifita indexPresenciaTroncos = (CatEPresenciaEpifita) cmbPresenciaTroncos.getSelectedItem();
        CatEPresenciaEpifita indexPresenciaRamas = (CatEPresenciaEpifita) cmbPresenciaRamas.getSelectedItem();
        this.ceEpifita.setUpmID(this.upmID);
        if (indexTipoEpifita != null) {
            this.ceEpifita.setClaseTipoID(indexTipoEpifita.getTipoEpifitaID());
        }
        if (indexPresenciaTroncos != null) {
            this.ceEpifita.setPresenciaTroncosID(indexPresenciaTroncos.getPresenciaEpifitaID());
        }
        if (indexPresenciaRamas != null) {
            this.ceEpifita.setPresenciaRamasID(indexPresenciaRamas.getPresenciaEpifitaID());
        }
        this.cdEpifita.insertEpifita(ceEpifita);

        limpiarControles();
    }

    private void actualizarEpifitas() {
        try {
            int fila = grdEpifitas.getSelectedRow();
            String registro = grdEpifitas.getValueAt(fila, 0).toString();
            CatETipoEpifita indexTipoEpifita = (CatETipoEpifita) cmbClaseTipo.getSelectedItem();
            CatEPresenciaEpifita indexPresenciaTroncos = (CatEPresenciaEpifita) cmbPresenciaTroncos.getSelectedItem();
            CatEPresenciaEpifita indexPresenciaRamas = (CatEPresenciaEpifita) cmbPresenciaRamas.getSelectedItem();
            this.epifitaID = Integer.parseInt(registro);
            this.ceEpifita.setUpmID(this.upmID);
            this.ceEpifita.setEpifitaID(this.epifitaID);
            if (indexTipoEpifita != null) {
                this.ceEpifita.setClaseTipoID(indexTipoEpifita.getTipoEpifitaID());
            }
            if (indexPresenciaTroncos != null) {
                this.ceEpifita.setPresenciaTroncosID(indexPresenciaTroncos.getPresenciaEpifitaID());
            }
            if (indexPresenciaRamas != null) {
                this.ceEpifita.setPresenciaRamasID(indexPresenciaRamas.getPresenciaEpifitaID());
            }
            this.cdEpifita.updateEpifita(ceEpifita);
            limpiarControles();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla de epifitas"
                    + e.getClass().getName() + " : " + e.getMessage(), "Epifitas", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarEpifita() {
        try {
            int fila = grdEpifitas.getSelectedRow();
            String registro = grdEpifitas.getValueAt(fila, 0).toString();
            this.epifitaID = Integer.parseInt(registro);
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Esta seguro de borrar el registro de epífitas?",
                    "Epifitas", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (respuesta == JOptionPane.YES_OPTION) {
                this.ceEpifita.setEpifitaID(this.epifitaID);
                this.cdEpifita.deleteEpifita(this.ceEpifita.getEpifitaID());
                limpiarControles();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla de epifitas"
                    + "", "Epifitas", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarControles() {
        cmbClaseTipo.setSelectedItem(null);
        cmbPresenciaTroncos.setSelectedItem(null);
        cmbPresenciaRamas.setSelectedItem(null);
        cmbClaseTipo.requestFocus();
    }
    
    private void limpiarControlesUPM() {
        txtAltitud.setValue(null);
        txtAltitud.setText("");
        txtPendiente.setValue(null);
        txtPendiente.setText("");
        cmbFisiografia.setSelectedItem(null);
        cmbExposicion.setSelectedItem(null);
    }

    private boolean validarCaracteristicasConglomerado() {
        CatETipoFisiografia indexFisiografia = (CatETipoFisiografia) cmbFisiografia.getSelectedItem();
        CatETipoExposicion indexExposicion = (CatETipoExposicion) cmbExposicion.getSelectedItem();
        if (txtAltitud.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar la altitud de la UPM", "Caracteriscticas del conglomerado", JOptionPane.ERROR_MESSAGE);
            txtAltitud.requestFocus();
            return false;
        } else if (txtPendiente.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar la pendiente de la UPM", "Caracteriscticas del conglomerado", JOptionPane.ERROR_MESSAGE);
            txtPendiente.requestFocus();
            return false;
        } else if (indexFisiografia == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar un tipo de fisiografia", "Caracteriscticas del conglomerado", JOptionPane.ERROR_MESSAGE);
            cmbFisiografia.requestFocus();
            return false;
        } else if (indexExposicion == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar un tipo de exposicion", "Caracteriscticas del conglomerado", JOptionPane.ERROR_MESSAGE);
            cmbExposicion.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean validarEpifitas() {
        if (cmbClaseTipo.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar un tipo de epifita", "Epifitas", JOptionPane.ERROR_MESSAGE);
            cmbClaseTipo.requestFocus();
            return false;
        } else if (cmbPresenciaTroncos.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar presencia en troncos", "Epifitas", JOptionPane.ERROR_MESSAGE);
            cmbPresenciaTroncos.requestFocus();
            return false;
        } else if (cmbPresenciaRamas.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar presencia en ramas", "Epifitas", JOptionPane.ERROR_MESSAGE);
            cmbPresenciaRamas.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean validarActualizacionEpifitas() {
        if (cmbPresenciaTroncos.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar presencia en troncos", "Epifitas", JOptionPane.ERROR_MESSAGE);
            cmbPresenciaTroncos.requestFocus();
            return false;
        } else if (cmbPresenciaRamas.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar presencia en ramas", "Epifitas", JOptionPane.ERROR_MESSAGE);
            cmbPresenciaRamas.requestFocus();
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
                    if (sitio == this.ceSitio.getSitio()) {//si es el sitio 3
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
                    UPMForms.observaciones.setDatosiniciales(ceSitio);
                    UPMForms.observaciones.setVisible(true);
                    break;
            }
        }
    }
    
/*private void revisarSiguienteFormulario(CESitio ceSitio) {
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
                        UPMForms.observaciones.revisarObservaciones(ceSitio);
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
            }
        }
    }*/
    private void manipularControlesEpifitas(boolean habilitar) {
        if (habilitar == true) {
            cmbClaseTipo.setEnabled(true);
            cmbPresenciaTroncos.setEnabled(true);
            cmbPresenciaRamas.setEnabled(true);
            btnGuardar.setEnabled(true);
            btnModificar.setEnabled(true);
            btnEliminar.setEnabled(true);
        } else {
            cmbClaseTipo.setEnabled(false);
            cmbClaseTipo.setSelectedItem(null);
            cmbPresenciaTroncos.setEnabled(false);
            cmbPresenciaTroncos.setSelectedItem(null);
            cmbPresenciaRamas.setEnabled(false);
            cmbPresenciaRamas.setSelectedItem(null);
            btnGuardar.setEnabled(false);
            btnModificar.setEnabled(false);
            btnEliminar.setEnabled(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblUPM = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblAltitud = new javax.swing.JLabel();
        lblPendiente = new javax.swing.JLabel();
        lblPorcentaje = new javax.swing.JLabel();
        lblMetros = new javax.swing.JLabel();
        lblFisiografia = new javax.swing.JLabel();
        cmbFisiografia = new javax.swing.JComboBox();
        cmbExposicion = new javax.swing.JComboBox();
        lblExposicion = new javax.swing.JLabel();
        txtAltitud = new javax.swing.JFormattedTextField();
        txtPendiente = new javax.swing.JFormattedTextField();
        jPanel4 = new javax.swing.JPanel();
        lblClaseTipo = new javax.swing.JLabel();
        cmbClaseTipo = new javax.swing.JComboBox();
        lblPresenciaTroncos = new javax.swing.JLabel();
        cmbPresenciaTroncos = new javax.swing.JComboBox();
        lblPresenciaRamas = new javax.swing.JLabel();
        cmbPresenciaRamas = new javax.swing.JComboBox();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        lblDiversidadEpifita = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        grdEpifitas = new javax.swing.JTable();
        btnContinuar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        chkEpifitas = new javax.swing.JCheckBox();
        cmbUPMID = new javax.swing.JComboBox<>();
        lblModulo = new javax.swing.JLabel();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jPanel3 = new javax.swing.JPanel();
        lblUPM1 = new javax.swing.JLabel();
        txtUPM1 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        lblAltitud1 = new javax.swing.JLabel();
        lblPendiente1 = new javax.swing.JLabel();
        lblPorcentaje1 = new javax.swing.JLabel();
        lblMetros1 = new javax.swing.JLabel();
        lblFisiografia1 = new javax.swing.JLabel();
        cmbFisiografia1 = new javax.swing.JComboBox();
        cmbExposicion1 = new javax.swing.JComboBox();
        lblExposicion1 = new javax.swing.JLabel();
        txtAltitud1 = new javax.swing.JFormattedTextField();
        txtPendiente1 = new javax.swing.JFormattedTextField();
        jPanel6 = new javax.swing.JPanel();
        lblClaseTipo1 = new javax.swing.JLabel();
        cmbClaseTipo1 = new javax.swing.JComboBox();
        lblPresenciaTroncos1 = new javax.swing.JLabel();
        cmbPresenciaTroncos1 = new javax.swing.JComboBox();
        lblPresenciaRamas1 = new javax.swing.JLabel();
        cmbPresenciaRamas1 = new javax.swing.JComboBox();
        btnGuardar1 = new javax.swing.JButton();
        btnModificar1 = new javax.swing.JButton();
        btnEliminar1 = new javax.swing.JButton();
        lblDiversidadEpifita1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        grdEpifitas1 = new javax.swing.JTable();
        btnContinuar1 = new javax.swing.JButton();
        btnSalir1 = new javax.swing.JButton();
        chkEpifitas1 = new javax.swing.JCheckBox();

        setMaximizable(true);
        setTitle("Características del conglomerado y diversidad de epífitas en el arbolado, módulo "+version);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/gob/conafor/utils/logo_conafor.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(940, 650));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        lblUPM.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblUPM.setText("UPMID:");

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblAltitud.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAltitud.setText("Altitud del centro del conglomerado");
        jPanel2.add(lblAltitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 200, -1));

        lblPendiente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPendiente.setText("Pendiente representativa");
        jPanel2.add(lblPendiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, 150, -1));

        lblPorcentaje.setText("%");
        jPanel2.add(lblPorcentaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 30, -1, -1));

        lblMetros.setText("m.s.n.m");
        jPanel2.add(lblMetros, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, -1, -1));

        lblFisiografia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFisiografia.setText("Fisiografía");
        jPanel2.add(lblFisiografia, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, 130, -1));

        jPanel2.add(cmbFisiografia, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 30, 130, -1));

        jPanel2.add(cmbExposicion, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 30, 130, -1));

        lblExposicion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblExposicion.setText("Exposición");
        jPanel2.add(lblExposicion, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 10, 130, -1));

        txtAltitud.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtAltitud.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtAltitudFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtAltitudFocusLost(evt);
            }
        });
        txtAltitud.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAltitudKeyTyped(evt);
            }
        });
        jPanel2.add(txtAltitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 200, -1));

        txtPendiente.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("##0"))));
        txtPendiente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPendienteFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPendienteFocusLost(evt);
            }
        });
        txtPendiente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPendienteKeyTyped(evt);
            }
        });
        jPanel2.add(txtPendiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 30, 146, -1));

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblClaseTipo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblClaseTipo.setText("Clase tipo");

        lblPresenciaTroncos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPresenciaTroncos.setText("Presencia en troncos");

        lblPresenciaRamas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPresenciaRamas.setText("Presencia en ramas");

        btnGuardar.setMnemonic('g');
        btnGuardar.setText("Guardar");
        btnGuardar.setActionCommand("");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnModificar.setMnemonic('m');
        btnModificar.setText("Modificar");
        btnModificar.setActionCommand("");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setMnemonic('e');
        btnEliminar.setText("Eliminar");
        btnEliminar.setActionCommand("");
        btnEliminar.setNextFocusableComponent(btnContinuar);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbClaseTipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblClaseTipo, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
                .addGap(37, 37, 37)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblPresenciaTroncos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbPresenciaTroncos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(33, 33, 33)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbPresenciaRamas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPresenciaRamas, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lblClaseTipo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbClaseTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(lblPresenciaRamas)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cmbPresenciaRamas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(lblPresenciaTroncos)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cmbPresenciaTroncos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnGuardar)
                                .addComponent(btnModificar)
                                .addComponent(btnEliminar)))))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        lblDiversidadEpifita.setBackground(new java.awt.Color(153, 153, 153));
        lblDiversidadEpifita.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblDiversidadEpifita.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDiversidadEpifita.setText("Diversidad de epífitas en el arbolado");
        lblDiversidadEpifita.setOpaque(true);

        grdEpifitas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        grdEpifitas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grdEpifitasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(grdEpifitas);

        btnContinuar.setMnemonic('c');
        btnContinuar.setText("Capturar");
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

        chkEpifitas.setBackground(new java.awt.Color(204, 204, 204));
        chkEpifitas.setSelected(true);
        chkEpifitas.setText("Epifitas");
        chkEpifitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkEpifitasActionPerformed(evt);
            }
        });

        cmbUPMID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbUPMIDActionPerformed(evt);
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
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(chkEpifitas)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDiversidadEpifita, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 926, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lblUPM)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbUPMID, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblModulo)
                        .addGap(78, 78, 78))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnContinuar)
                .addGap(40, 40, 40)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblUPM)
                            .addComponent(cmbUPMID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblModulo)
                        .addGap(18, 18, 18)))
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDiversidadEpifita)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkEpifitas)
                .addGap(6, 6, 6)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnContinuar)
                    .addComponent(btnSalir))
                .addGap(23, 23, 23))
        );

        jInternalFrame1.setMaximizable(true);
        jInternalFrame1.setTitle("Características del conglomerado y diversidad de epífitas en el arbolado, módulo "+version);
        jInternalFrame1.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/gob/conafor/utils/logo_conafor.png"))); // NOI18N
        jInternalFrame1.setPreferredSize(new java.awt.Dimension(940, 650));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        lblUPM1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblUPM1.setText("UPMID:");

        txtUPM1.setEditable(false);
        txtUPM1.setEnabled(false);

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblAltitud1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAltitud1.setText("Altitud del centro del conglomerado");
        jPanel5.add(lblAltitud1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 200, -1));

        lblPendiente1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPendiente1.setText("Pendiente representativa");
        jPanel5.add(lblPendiente1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, 150, -1));

        lblPorcentaje1.setText("%");
        jPanel5.add(lblPorcentaje1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 30, -1, -1));

        lblMetros1.setText("m.s.n.m");
        jPanel5.add(lblMetros1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, -1, -1));

        lblFisiografia1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFisiografia1.setText("Fisiografía");
        jPanel5.add(lblFisiografia1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, 130, -1));

        jPanel5.add(cmbFisiografia1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 30, 130, -1));

        jPanel5.add(cmbExposicion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 30, 130, -1));

        lblExposicion1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblExposicion1.setText("Exposición");
        jPanel5.add(lblExposicion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 10, 130, -1));

        txtAltitud1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtAltitud1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtAltitud1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtAltitud1FocusLost(evt);
            }
        });
        txtAltitud1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAltitud1KeyTyped(evt);
            }
        });
        jPanel5.add(txtAltitud1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 200, -1));

        txtPendiente1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("##0"))));
        txtPendiente1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPendiente1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPendiente1FocusLost(evt);
            }
        });
        txtPendiente1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPendiente1KeyTyped(evt);
            }
        });
        jPanel5.add(txtPendiente1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 30, 146, -1));

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblClaseTipo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblClaseTipo1.setText("Clase tipo");

        lblPresenciaTroncos1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPresenciaTroncos1.setText("Presencia en troncos");

        lblPresenciaRamas1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPresenciaRamas1.setText("Presencia en ramas");

        btnGuardar1.setText("Guardar");
        btnGuardar1.setActionCommand("");
        btnGuardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar1ActionPerformed(evt);
            }
        });

        btnModificar1.setText("Modificar");
        btnModificar1.setActionCommand("");
        btnModificar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificar1ActionPerformed(evt);
            }
        });

        btnEliminar1.setText("Eliminar");
        btnEliminar1.setActionCommand("");
        btnEliminar1.setNextFocusableComponent(btnContinuar);
        btnEliminar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbClaseTipo1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblClaseTipo1, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
                .addGap(37, 37, 37)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblPresenciaTroncos1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbPresenciaTroncos1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(33, 33, 33)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbPresenciaRamas1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPresenciaRamas1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(btnModificar1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btnEliminar1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lblClaseTipo1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbClaseTipo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addComponent(lblPresenciaRamas1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cmbPresenciaRamas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addComponent(lblPresenciaTroncos1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cmbPresenciaTroncos1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnGuardar1)
                                .addComponent(btnModificar1)
                                .addComponent(btnEliminar1)))))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        lblDiversidadEpifita1.setBackground(new java.awt.Color(153, 153, 153));
        lblDiversidadEpifita1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblDiversidadEpifita1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDiversidadEpifita1.setText("Diversidad de epífitas en el arbolado");
        lblDiversidadEpifita1.setOpaque(true);

        grdEpifitas1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        grdEpifitas1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grdEpifitas1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(grdEpifitas1);

        btnContinuar1.setText("Continuar");
        btnContinuar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinuar1ActionPerformed(evt);
            }
        });

        btnSalir1.setText("Salir");
        btnSalir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalir1ActionPerformed(evt);
            }
        });

        chkEpifitas1.setBackground(new java.awt.Color(204, 204, 204));
        chkEpifitas1.setSelected(true);
        chkEpifitas1.setText("Epifitas");
        chkEpifitas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkEpifitas1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(355, 355, 355)
                .addComponent(btnContinuar1)
                .addGap(40, 40, 40)
                .addComponent(btnSalir1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(chkEpifitas1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lblUPM1)
                        .addGap(10, 10, 10)
                        .addComponent(txtUPM1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDiversidadEpifita1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(lblUPM1))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(txtUPM1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDiversidadEpifita1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkEpifitas1)
                .addGap(6, 6, 6)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnContinuar1)
                    .addComponent(btnSalir1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuarActionPerformed
        asignarDatosUPM();
        if (validarCaracteristicasConglomerado()) {
            if (chkEpifitas.isSelected() && cdEpifita.validarTablaEpifitas((Integer) cmbUPMID.getSelectedItem())) {
                JOptionPane.showMessageDialog(null, "Si selecciona Epifitas, se debe capturar", "Características del UPM", JOptionPane.INFORMATION_MESSAGE);
                chkEpifitas.requestFocus();
            } else if (this.modificar == 0) {
                crearCaracteristicasUPM();
                //this.hide();
                //seleccionarSiguienteFormulario(this.ceSitio);
                //this.cdSecuencia.updateSecuencia(this.ceSitio, FORMATO_ID, 1);
            } else {
                //this.hide();
                crearCaracteristicasUPM();
               //revisarSiguienteFormulario(this.ceSitio);
            }
        }
    }//GEN-LAST:event_btnContinuarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (validarEpifitas()) {
            crearEpifita();
            DefaultComboBoxModel dcm = (DefaultComboBoxModel) cmbClaseTipo.getModel();
            dcm.removeAllElements();
            fillCmbTipoEpifita();
            llenarTablaEpifitas();
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        if (validarActualizacionEpifitas()) {
            actualizarEpifitas();
            DefaultComboBoxModel dcm = (DefaultComboBoxModel) cmbClaseTipo.getModel();
            dcm.removeAllElements();
            fillCmbTipoEpifita();
            llenarTablaEpifitas();
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminarEpifita();
        DefaultComboBoxModel dcm = (DefaultComboBoxModel) cmbClaseTipo.getModel();
        dcm.removeAllElements();
        fillCmbTipoEpifita();
        llenarTablaEpifitas();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void grdEpifitasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grdEpifitasMouseClicked
        if (evt.getButton() == 1) {
            int fila = grdEpifitas.getSelectedRow();
            String epifitaID = grdEpifitas.getValueAt(fila, 0).toString();
            this.epifitaID = Integer.parseInt(epifitaID);
            this.ceEpifita = cdEpifita.getRegistroEpifitas(this.epifitaID);

            CatETipoEpifita tipoEpifita = new CatETipoEpifita();
            CatEPresenciaEpifita cePresenciaTronco = new CatEPresenciaEpifita();
            CatEPresenciaEpifita cePresenciaRamas = new CatEPresenciaEpifita();

            tipoEpifita.setTipoEpifitaID(this.ceEpifita.getClaseTipoID());
            cmbClaseTipo.setSelectedItem(tipoEpifita);

            cePresenciaTronco.setPresenciaEpifitaID(this.ceEpifita.getPresenciaTroncosID());
            cmbPresenciaTroncos.setSelectedItem(cePresenciaTronco);

            cePresenciaRamas.setPresenciaEpifitaID(this.ceEpifita.getPresenciaRamasID());
            cmbPresenciaRamas.setSelectedItem(cePresenciaRamas);
        }
    }//GEN-LAST:event_grdEpifitasMouseClicked

    private void txtAltitudFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAltitudFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtAltitud.selectAll();
            }
        });
    }//GEN-LAST:event_txtAltitudFocusGained

    private void txtAltitudFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAltitudFocusLost
        if (txtAltitud.getText().isEmpty()) {
            txtAltitud.setValue(null);
        }
    }//GEN-LAST:event_txtAltitudFocusLost

    private void txtPendienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPendienteFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtPendiente.selectAll();
            }
        });
    }//GEN-LAST:event_txtPendienteFocusGained

    private void txtPendienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPendienteFocusLost
        if (txtPendiente.getText().isEmpty()) {
            txtPendiente.setValue(null);
        }
    }//GEN-LAST:event_txtPendienteFocusLost

    private void txtAltitudKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAltitudKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtAltitudKeyTyped

    private void txtPendienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPendienteKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtPendienteKeyTyped

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

    private void chkEpifitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkEpifitasActionPerformed
        if (chkEpifitas.isSelected()) {
            manipularControlesEpifitas(true);
        } else {
            int respuesta = JOptionPane.showConfirmDialog(null, "Si capturo, se borrarán los datos de epífitas, ¿Esta seguro?",
                    "Epifitas", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (respuesta == JOptionPane.YES_OPTION) {
                manipularControlesEpifitas(false);
                this.cdEpifita.deleteEpifitasSitio(this.ceSitio.getUpmID());
                funciones.reiniciarTabla(this.grdEpifitas);
                llenarTablaEpifitas();
                manipularControlesEpifitas(false);
                funciones.reiniciarComboModel(cmbClaseTipo);
                fillCmbTipoEpifita();
            } else {
                chkEpifitas.setSelected(true);
                cmbClaseTipo.requestFocus();
            }
        }
    }//GEN-LAST:event_chkEpifitasActionPerformed

    private void txtAltitud1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAltitud1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAltitud1FocusGained

    private void txtAltitud1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAltitud1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAltitud1FocusLost

    private void txtAltitud1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAltitud1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAltitud1KeyTyped

    private void txtPendiente1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPendiente1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPendiente1FocusGained

    private void txtPendiente1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPendiente1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPendiente1FocusLost

    private void txtPendiente1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPendiente1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPendiente1KeyTyped

    private void btnGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardar1ActionPerformed

    private void btnModificar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnModificar1ActionPerformed

    private void btnEliminar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminar1ActionPerformed

    private void grdEpifitas1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grdEpifitas1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_grdEpifitas1MouseClicked

    private void btnContinuar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnContinuar1ActionPerformed

    private void btnSalir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalir1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalir1ActionPerformed

    private void chkEpifitas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkEpifitas1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkEpifitas1ActionPerformed

    private void cmbUPMIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbUPMIDActionPerformed
        Integer upmID = (Integer) cmbUPMID.getSelectedItem();
        this.upmID = upmID;
        revisarCaracteristicasUPM(upmID);
    }//GEN-LAST:event_cmbUPMIDActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnContinuar;
    private javax.swing.JButton btnContinuar1;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEliminar1;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnGuardar1;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnModificar1;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnSalir1;
    private javax.swing.JCheckBox chkEpifitas;
    private javax.swing.JCheckBox chkEpifitas1;
    private javax.swing.JComboBox cmbClaseTipo;
    private javax.swing.JComboBox cmbClaseTipo1;
    private javax.swing.JComboBox cmbExposicion;
    private javax.swing.JComboBox cmbExposicion1;
    private javax.swing.JComboBox cmbFisiografia;
    private javax.swing.JComboBox cmbFisiografia1;
    private javax.swing.JComboBox cmbPresenciaRamas;
    private javax.swing.JComboBox cmbPresenciaRamas1;
    private javax.swing.JComboBox cmbPresenciaTroncos;
    private javax.swing.JComboBox cmbPresenciaTroncos1;
    private javax.swing.JComboBox<Integer> cmbUPMID;
    private javax.swing.JTable grdEpifitas;
    private javax.swing.JTable grdEpifitas1;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAltitud;
    private javax.swing.JLabel lblAltitud1;
    private javax.swing.JLabel lblClaseTipo;
    private javax.swing.JLabel lblClaseTipo1;
    private javax.swing.JLabel lblDiversidadEpifita;
    private javax.swing.JLabel lblDiversidadEpifita1;
    private javax.swing.JLabel lblExposicion;
    private javax.swing.JLabel lblExposicion1;
    private javax.swing.JLabel lblFisiografia;
    private javax.swing.JLabel lblFisiografia1;
    private javax.swing.JLabel lblMetros;
    private javax.swing.JLabel lblMetros1;
    private javax.swing.JLabel lblModulo;
    private javax.swing.JLabel lblPendiente;
    private javax.swing.JLabel lblPendiente1;
    private javax.swing.JLabel lblPorcentaje;
    private javax.swing.JLabel lblPorcentaje1;
    private javax.swing.JLabel lblPresenciaRamas;
    private javax.swing.JLabel lblPresenciaRamas1;
    private javax.swing.JLabel lblPresenciaTroncos;
    private javax.swing.JLabel lblPresenciaTroncos1;
    private javax.swing.JLabel lblUPM;
    private javax.swing.JLabel lblUPM1;
    private javax.swing.JFormattedTextField txtAltitud;
    private javax.swing.JFormattedTextField txtAltitud1;
    private javax.swing.JFormattedTextField txtPendiente;
    private javax.swing.JFormattedTextField txtPendiente1;
    private javax.swing.JTextField txtUPM1;
    // End of variables declaration//GEN-END:variables
}
