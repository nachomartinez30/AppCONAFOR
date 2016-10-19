package gob.conafor.upm.vie;

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
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class FrmCaracteristicasUPM extends javax.swing.JInternalFrame {

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
        txtUPM.setText(String.valueOf(this.upmID));
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

    public void revisarCaracteristicasUPM(CESitio ceSitio) {
        this.upmID = ceSitio.getUpmID();
        txtUPM.setText(String.valueOf(this.upmID));
        this.ceSitio = ceSitio;
        DefaultComboBoxModel dcm = (DefaultComboBoxModel) cmbClaseTipo.getModel();
        dcm.removeAllElements();
        fillCmbTipoEpifita();
        llenarTablaEpifitas();
        this.ceUpm = this.cdUPM.getCaracteristicasUPM(ceSitio.getUpmID());
        txtAltitud.setText(String.valueOf(this.ceUpm.getAltitud()));
        txtPendiente.setText(String.valueOf(this.ceUpm.getPendienteRepresentativa()));
        CatETipoFisiografia ceFisiografia = new CatETipoFisiografia();
        ceFisiografia.setFisiografiaID(this.ceUpm.getFisiografiaID());
        cmbFisiografia.setSelectedItem(ceFisiografia);
        CatETipoExposicion ceExposicion = new CatETipoExposicion();
        ceExposicion.setExposicionID(this.ceUpm.getExposicionID());
        cmbExposicion.setSelectedItem(ceExposicion);
        this.modificar = 1;
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
                    if (sitio == this.ceSitio.getSitio()) {
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
            }
        }
    }

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
        txtUPM = new javax.swing.JTextField();
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

        setResizable(true);
        setTitle("Características del conglomerado y diversidad de epífitas en el arbolado, módulo 0");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/gob/conafor/utils/logo_conafor.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(940, 650));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        lblUPM.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblUPM.setText("UPMID:");

        txtUPM.setEditable(false);
        txtUPM.setEnabled(false);

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

        btnGuardar.setText("Guardar");
        btnGuardar.setActionCommand("");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.setActionCommand("");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

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
                    .addComponent(lblPresenciaTroncos, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
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

        chkEpifitas.setBackground(new java.awt.Color(204, 204, 204));
        chkEpifitas.setSelected(true);
        chkEpifitas.setText("Epifitas");
        chkEpifitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkEpifitasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(355, 355, 355)
                .addComponent(btnContinuar)
                .addGap(40, 40, 40)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(chkEpifitas)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lblUPM)
                        .addGap(10, 10, 10)
                        .addComponent(txtUPM, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDiversidadEpifita, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 904, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(lblUPM))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(txtUPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDiversidadEpifita)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkEpifitas)
                .addGap(6, 6, 6)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnContinuar)
                    .addComponent(btnSalir))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuarActionPerformed
        asignarDatosUPM();
        if (validarCaracteristicasConglomerado()) {
            if (chkEpifitas.isSelected() && cdEpifita.validarTablaEpifitas(ceSitio.getUpmID())) {
                JOptionPane.showMessageDialog(null, "Si selecciona Epifitas, se debe capturar", "Características del UPM", JOptionPane.INFORMATION_MESSAGE);
                chkEpifitas.requestFocus();
            } else if (this.modificar == 0) {
                crearCaracteristicasUPM();
                this.hide();
                seleccionarSiguienteFormulario(this.ceSitio);
                this.cdSecuencia.updateSecuencia(this.ceSitio, FORMATO_ID, 1);
            } else {
                this.hide();
                crearCaracteristicasUPM();
                seleccionarSiguienteFormulario(this.ceSitio);
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
        this.hide();
        funciones.manipularBotonesMenuPrincipal(false);
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnContinuar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JCheckBox chkEpifitas;
    private javax.swing.JComboBox cmbClaseTipo;
    private javax.swing.JComboBox cmbExposicion;
    private javax.swing.JComboBox cmbFisiografia;
    private javax.swing.JComboBox cmbPresenciaRamas;
    private javax.swing.JComboBox cmbPresenciaTroncos;
    private javax.swing.JTable grdEpifitas;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAltitud;
    private javax.swing.JLabel lblClaseTipo;
    private javax.swing.JLabel lblDiversidadEpifita;
    private javax.swing.JLabel lblExposicion;
    private javax.swing.JLabel lblFisiografia;
    private javax.swing.JLabel lblMetros;
    private javax.swing.JLabel lblPendiente;
    private javax.swing.JLabel lblPorcentaje;
    private javax.swing.JLabel lblPresenciaRamas;
    private javax.swing.JLabel lblPresenciaTroncos;
    private javax.swing.JLabel lblUPM;
    private javax.swing.JFormattedTextField txtAltitud;
    private javax.swing.JFormattedTextField txtPendiente;
    private javax.swing.JTextField txtUPM;
    // End of variables declaration//GEN-END:variables
}
