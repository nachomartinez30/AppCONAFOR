package gob.conafor.pc.vie;

import gob.conafor.pc.mod.CDAccesibilidadPC;
import gob.conafor.pc.mod.CDPuntoControl;
import gob.conafor.pc.mod.CEAccesibilidadPC;
import gob.conafor.pc.mod.CEPuntoControl;
import gob.conafor.pc.mod.CatECondicionAccesibilidad;
import gob.conafor.pc.mod.CatEMedioTransporte;
import gob.conafor.pc.mod.CatEViaAcceso;
import gob.conafor.upm.mod.CEUPM;
import gob.conafor.upm.vie.UPMForms;
import gob.conafor.utils.Datos;
import gob.conafor.utils.FuncionesComunes;
import gob.conafor.utils.Tablas;
import gob.conafor.utils.ValidacionesComunes;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class FrmPuntoControl extends javax.swing.JInternalFrame {

    private DefaultTableModel viaModel;
    private int upm;
    private int tipoUpm;
    private CEPuntoControl cePC = new CEPuntoControl();
    private CDAccesibilidadPC accesibilidad = new CDAccesibilidadPC();
    private ValidacionesComunes vc = new ValidacionesComunes();
    private CEUPM ceUpm;
    private final int puntoControl;
    private final int inaccesibleUPM;
    private final int ubicacionSitio;
    private Datos numeros = new Datos();
    private CDPuntoControl cdPC = new CDPuntoControl();
    private int modificar;
    private FuncionesComunes funciones = new FuncionesComunes();

    public FrmPuntoControl() {
        initComponents();
        fillCmbMedioTransporte();
        fillCmbCondicion();
        desabilitarBotonesViaAcceso();
        this.puntoControl = 3;
        this.inaccesibleUPM = 4;
        this.ubicacionSitio = 5;
        DefaultTableModel model = (DefaultTableModel) grdAccesibilidad.getModel();
        model.setRowCount(0);
        llenarTabla();
    }

    public void setDatosIniciales(CEUPM upm) {
        this.upm = upm.getUpmID();
        this.tipoUpm = upm.getTipoUpmID();
        txtUPM.setText(String.valueOf(upm.getUpmID()));
        this.ceUpm = upm;
        if (!accesibilidad.validarTablaAccesibilida(this.upm)) {
            llenarTabla();
        }
        this.modificar = 0;
        funciones.manipularBotonesMenuPrincipal(true);
        DefaultTableModel model = (DefaultTableModel) grdAccesibilidad.getModel();
        model.setRowCount(0);
        llenarTabla();
        limpiarControlesPC();
        limpiarControlesViaAcceso();
    }

    public void revisarPuntoControl(CEUPM upm) {
        this.ceUpm = upm;
        this.upm = upm.getUpmID();
        this.tipoUpm = upm.getTipoUpmID();
        txtUPM.setText(String.valueOf(upm.getUpmID()));
        this.ceUpm = upm;
        if (!accesibilidad.validarTablaAccesibilida(this.upm)) {
            llenarTabla();
        }
        this.cePC = this.cdPC.getPuntoControl(this.upm);
        txtDescripcion.setText(this.cePC.getDescripcion());
        txtParaje.setText(this.cePC.getParaje());
        txtGradosLatitud.setText(String.valueOf(this.cePC.getGradosLatitud()));
        txtMinutosLatitud.setText(String.valueOf(this.cePC.getMinutosLatitud()));
        txtSegundosLatitud.setText(String.valueOf(this.cePC.getSegundosLatitud()));
        txtGradosLongitud.setText(String.valueOf(this.cePC.getGradosLongitud()));
        txtMinutosLongitud.setText(String.valueOf(this.cePC.getMinutosLongitud()));
        txtSegundosLongitud.setText(String.valueOf(this.cePC.getSegundosLongitud()));
        txtErrorPresicion.setText(String.valueOf(this.cePC.getErrorPrecision()));
        txtAzimut.setText(String.valueOf(this.cePC.getAzimut()));
        txtDistancia.setText(String.valueOf(this.cePC.getDistancia()));
        this.modificar = 1;
        funciones.manipularBotonesMenuPrincipal(true);
        DefaultTableModel model = (DefaultTableModel) grdAccesibilidad.getModel();
        model.setRowCount(0);
        llenarTabla();
    }

    public boolean validarCamposObligatorios() {
        if (txtDescripcion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! el campo descripción es requerido ", "Punto de control", JOptionPane.ERROR_MESSAGE);
            txtDescripcion.requestFocus();
            return false;
        } else if (txtParaje.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! el campo paraje es requerido ", "Punto de control", JOptionPane.ERROR_MESSAGE);
            txtParaje.requestFocus();
            return false;
        } else if (txtGradosLatitud.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! el campo grados latitud es requerido ", "Punto de control", JOptionPane.ERROR_MESSAGE);
            txtGradosLatitud.requestFocus();
            return false;
        } else if (txtMinutosLatitud.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! el campo minutos latitud es requerido ", "Punto de control", JOptionPane.ERROR_MESSAGE);
            txtMinutosLatitud.requestFocus();
            return false;
        } else if (txtSegundosLatitud.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! el campo segundos latitud es requerido ", "Punto de control", JOptionPane.ERROR_MESSAGE);
            txtSegundosLatitud.requestFocus();
            return false;
        } else if (txtGradosLongitud.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! el campo grados longitud es requerido ", "Punto de control", JOptionPane.ERROR_MESSAGE);
            txtGradosLongitud.requestFocus();
            return false;
        } else if (txtMinutosLongitud.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! el campo minutos longitud es requerido ", "Punto de control", JOptionPane.ERROR_MESSAGE);
            txtMinutosLongitud.requestFocus();
            return false;
        } else if (txtSegundosLongitud.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! el campo segundos longitud es requerido ", "Punto de control", JOptionPane.ERROR_MESSAGE);
            txtSegundosLongitud.requestFocus();
            return false;
        } else if (txtErrorPresicion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! el campo error de presicion es requerido ", "Punto de control", JOptionPane.ERROR_MESSAGE);
            txtErrorPresicion.requestFocus();
            return false;
        } else if (txtAzimut.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! el campo azimut es requerido ", "Punto de control", JOptionPane.ERROR_MESSAGE);
            txtAzimut.requestFocus();
            return false;
        } else if (txtDistancia.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! el campo distancia es requerido ", "Punto de control", JOptionPane.ERROR_MESSAGE);
            txtDistancia.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    public boolean validarDatosPC() {
        int gradosLatitud = Integer.parseInt(txtGradosLatitud.getText());
        int minutosLatitud = Integer.parseInt(txtMinutosLatitud.getText());
        float segundosLatitud = Float.parseFloat(txtSegundosLatitud.getText());
        int gradosLongitud = Integer.parseInt(txtGradosLongitud.getText());
        int minutosLongitud = Integer.parseInt(txtMinutosLongitud.getText());
        float segundosLongitud = Float.parseFloat(txtSegundosLongitud.getText());
        int errorPresicion = Integer.parseInt(txtErrorPresicion.getText());
        int azimut = Integer.parseInt(txtAzimut.getText());
        float distancia = Float.parseFloat(txtDistancia.getText());

        if (this.vc.sonGradosLatitud(gradosLatitud, "Punto de control")) {
            txtGradosLatitud.requestFocus();
            txtGradosLatitud.setText("");
            return false;
        } else if (this.vc.sonMinutos(minutosLatitud, "Punto de control")) {
            txtMinutosLatitud.requestFocus();
            txtMinutosLatitud.setText("");
            return false;
        } else if (this.vc.sonSegundos(segundosLatitud, "Punto de control")) {
            txtSegundosLatitud.requestFocus();
            txtSegundosLatitud.setText("");
            return false;
        } else if (this.vc.sonGradosLongitud(gradosLongitud, "Punto de control")) {
            txtGradosLongitud.requestFocus();
            txtGradosLongitud.setText("");
            return false;
        } else if (this.vc.sonMinutos(minutosLongitud, "Punto de control")) {
            txtMinutosLongitud.requestFocus();
            txtMinutosLongitud.setText("");
            return false;
        } else if (this.vc.sonSegundos(segundosLongitud, "Punto de control")) {
            txtSegundosLongitud.requestFocus();
            txtSegundosLongitud.setText("");
            return false;
        } else if (this.vc.esErrorPrecision(errorPresicion, "Punto de control")) {
            txtErrorPresicion.requestFocus();
            txtErrorPresicion.setText("");
            return false;
        } else if (this.vc.esAzimut(azimut, "Punto de control")) {
            txtAzimut.requestFocus();
            txtAzimut.setText("");
            return false;
        } else if (this.vc.esDistancia(distancia, "Punto de control")) {
            txtDistancia.requestFocus();
            txtDistancia.setText("");
            return false;
        } else {
            return true;
        }
    }

    public void crearPC() {
        this.cePC.setUpmID(Integer.parseInt(txtUPM.getText()));
        this.cePC.setDescripcion(txtDescripcion.getText());
        this.cePC.setParaje(txtParaje.getText());
        this.cePC.setGradosLatitud(Integer.parseInt(txtGradosLatitud.getText()));
        this.cePC.setMinutosLatitud(Integer.parseInt(txtMinutosLatitud.getText()));
        this.cePC.setSegundosLatitud(Float.parseFloat(txtSegundosLatitud.getText()));
        this.cePC.setGradosLongitud(Integer.parseInt(txtGradosLongitud.getText()));
        this.cePC.setMinutosLongitud(Integer.parseInt(txtMinutosLongitud.getText()));
        this.cePC.setSegundosLongitud(Float.parseFloat(txtSegundosLongitud.getText()));
        this.cePC.setErrorPrecision(Integer.parseInt(txtErrorPresicion.getText()));
        this.cePC.setDatum(txtDatum.getText());
        this.cePC.setAzimut(Integer.parseInt(txtAzimut.getText()));
        this.cePC.setDistancia(Float.parseFloat(txtDistancia.getText()));
        this.cdPC.insertPuntoControl(this.cePC);
    }
    
    public void modificarPC(){
        this.cePC.setUpmID(Integer.parseInt(txtUPM.getText()));
        this.cePC.setDescripcion(txtDescripcion.getText());
        this.cePC.setParaje(txtParaje.getText());
        this.cePC.setGradosLatitud(Integer.parseInt(txtGradosLatitud.getText()));
        this.cePC.setMinutosLatitud(Integer.parseInt(txtMinutosLatitud.getText()));
        this.cePC.setSegundosLatitud(Float.parseFloat(txtSegundosLatitud.getText()));
        this.cePC.setGradosLongitud(Integer.parseInt(txtGradosLongitud.getText()));
        this.cePC.setMinutosLongitud(Integer.parseInt(txtMinutosLongitud.getText()));
        this.cePC.setSegundosLongitud(Float.parseFloat(txtSegundosLongitud.getText()));
        this.cePC.setErrorPrecision(Integer.parseInt(txtErrorPresicion.getText()));
        this.cePC.setDatum(txtDatum.getText());
        this.cePC.setAzimut(Integer.parseInt(txtAzimut.getText()));
        this.cePC.setDistancia(Float.parseFloat(txtDistancia.getText()));
        this.cdPC.updatePuntoControl(this.cePC);
    }
    
    //MANEJO DE CONTROLES Y TABLAS DE LA VIA DE ACCESO
    private void fillCmbMedioTransporte() {
        List<CatEMedioTransporte> listMedioTransporte = new ArrayList<>();
        listMedioTransporte = accesibilidad.getMedioTransporte();
        if (listMedioTransporte != null) {
            int size = listMedioTransporte.size();
            for (int i = 0; i < size; i++) {
                cmbMedioTransporte.addItem(listMedioTransporte.get(i));
            }
        }
    }

    private void fillCmbViaAcceso(int index) {
        List<CatEViaAcceso> listViaAcceso = new ArrayList();
        listViaAcceso = accesibilidad.getViaAcceso(index);
        if (listViaAcceso != null) {
            int size = listViaAcceso.size();
            for (int i = 0; i < size; i++) {
                cmbViaAcceso.addItem(listViaAcceso.get(i));
            }
        }
    }

    private void fillCmbCondicion() {
        List<CatECondicionAccesibilidad> listCondicion = new ArrayList<>();
        listCondicion = accesibilidad.getCondicion();
        if (listCondicion != null) {
            int size = listCondicion.size();
            for (int i = 0; i < size; i++) {
                cmbCondicionAcceso.addItem(listCondicion.get(i));
            }
        }
    }

    private void crearViaAcceso() {
        CEAccesibilidadPC ac = new CEAccesibilidadPC();
        CDAccesibilidadPC bd = new CDAccesibilidadPC();
        CatEMedioTransporte mt = (CatEMedioTransporte) cmbMedioTransporte.getSelectedItem();
        CatEViaAcceso va = (CatEViaAcceso) cmbViaAcceso.getSelectedItem();
        CatECondicionAccesibilidad ca = (CatECondicionAccesibilidad) cmbCondicionAcceso.getSelectedItem();

        ac.setUpmID(Integer.parseInt(txtUPM.getText()));
        ac.setMedioTransporteID(mt.getMedioTransporteID());
        ac.setViaAccesoID(va.getViaAccesibilidadID());
        ac.setDistancia(Float.parseFloat(txtDistanciaAccesibilidad.getValue().toString()));
        ac.setCondicionAccesoID(ca.getCondicionAccesibilidadID());

        bd.insertDatosAccesibilidadPC(ac);
    }

    private void actualizarViaAcceso() {
        try {
            int fila = grdAccesibilidad.getSelectedRow();
            String registro = grdAccesibilidad.getValueAt(fila, 0).toString();
            CDAccesibilidadPC bd = new CDAccesibilidadPC();
            CEAccesibilidadPC ac = new CEAccesibilidadPC();
            CatEMedioTransporte mt = (CatEMedioTransporte) cmbMedioTransporte.getSelectedItem();
            CatEViaAcceso va = (CatEViaAcceso) cmbViaAcceso.getSelectedItem();
            CatECondicionAccesibilidad ca = (CatECondicionAccesibilidad) cmbCondicionAcceso.getSelectedItem();

            ac.setAccesibilidadID(Integer.parseInt(registro));
            ac.setMedioTransporteID(mt.getMedioTransporteID());
            ac.setViaAccesoID(va.getViaAccesibilidadID());
            ac.setDistancia(Float.parseFloat(txtDistanciaAccesibilidad.getValue().toString()));
            ac.setCondicionAccesoID(ca.getCondicionAccesibilidadID());

            bd.updateDatosAccesibilidadPC(ac);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla", "Captura de Accesibiidad", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarViaAcceso() {
        try {
            int fila = grdAccesibilidad.getSelectedRow();
            String registro = grdAccesibilidad.getValueAt(fila, 0).toString();
            CDAccesibilidadPC bd = new CDAccesibilidadPC();
            CEAccesibilidadPC ac = new CEAccesibilidadPC();
            ac.setAccesibilidadID(Integer.parseInt(registro));
            bd.deleteDatosAccesibilidadPC(ac);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningun registro de la tabla", "Captura de accesibilidad", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void llenarTabla() {
        CDAccesibilidadPC bd = new CDAccesibilidadPC();
        grdAccesibilidad.setModel(bd.getDatosAccesibilidadPC(this.upm));
        Tablas tabla = new Tablas();
        int[] columna_0 = {0};
        int[] columna_1 = {1};
        //Ocultar campo ID
        tabla.hideColumnTable(grdAccesibilidad, columna_0);
        tabla.hideColumnTable(grdAccesibilidad, columna_1);
    }

    private boolean validarViaAcceso() {
        if (cmbMedioTransporte.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar un medio de transporte ", "Información de accesibilidad", JOptionPane.ERROR_MESSAGE);
            cmbMedioTransporte.requestFocus();
            return false;
        } else if (cmbViaAcceso.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar una via de acceso ", "Información de accesibilidad", JOptionPane.ERROR_MESSAGE);
            cmbViaAcceso.requestFocus();
            return false;
        } else if (txtDistanciaAccesibilidad.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar una distancia de acceso ", "Información de accesibilidad", JOptionPane.ERROR_MESSAGE);
            txtDistanciaAccesibilidad.requestFocus();
            return false;
        } else if (cmbCondicionAcceso.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar una condición de acceso ", "Información de accesibilidad", JOptionPane.ERROR_MESSAGE);
            cmbCondicionAcceso.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private void desabilitarBotonesViaAcceso() {
        btnActualizar.setEnabled(!accesibilidad.validarTablaAccesibilida(upm));
        btnEliminar.setEnabled(!accesibilidad.validarTablaAccesibilida(upm));
    }

    private void limpiarControlesViaAcceso() {
        cmbMedioTransporte.setSelectedItem(null);
        cmbViaAcceso.setSelectedItem(null);
        txtDistanciaAccesibilidad.setText("");
        cmbCondicionAcceso.setSelectedItem(null);
        cmbMedioTransporte.requestFocus();
    }
    
    private void limpiarControlesPC() {
        txtDescripcion.setText("");
        txtParaje.setText("");
        txtGradosLatitud.setValue(null);
        txtGradosLatitud.setText("");
        txtMinutosLatitud.setValue(null);
        txtMinutosLatitud.setText("");
        txtSegundosLatitud.setValue(null);
        txtSegundosLatitud.setText("");
        txtGradosLongitud.setValue(null);
        txtGradosLongitud.setText("");
        txtMinutosLongitud.setValue(null);
        txtMinutosLongitud.setText("");
        txtSegundosLongitud.setValue(null);
        txtSegundosLongitud.setText("");
        txtErrorPresicion.setValue(null);
        txtErrorPresicion.setText("");
        txtAzimut.setValue(null);
        txtAzimut.setText("");
        txtDistancia.setValue(null);
        txtDistancia.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        PnlUPM = new javax.swing.JPanel();
        txtUPM = new javax.swing.JTextField();
        lblUPMID = new javax.swing.JLabel();
        PnlCoordenadas = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        lblGradosLatitud = new javax.swing.JLabel();
        txtGradosLatitud = new javax.swing.JFormattedTextField();
        lblMinuosLatitud = new javax.swing.JLabel();
        txtMinutosLatitud = new javax.swing.JFormattedTextField();
        lblSegundosLatitud = new javax.swing.JLabel();
        txtSegundosLatitud = new javax.swing.JFormattedTextField();
        lblGradosLongitud = new javax.swing.JLabel();
        txtGradosLongitud = new javax.swing.JFormattedTextField();
        lblMinutosLongitud = new javax.swing.JLabel();
        txtMinutosLongitud = new javax.swing.JFormattedTextField();
        lblSegundosLongitud = new javax.swing.JLabel();
        txtSegundosLongitud = new javax.swing.JFormattedTextField();
        jPanel6 = new javax.swing.JPanel();
        lblLatitud = new javax.swing.JLabel();
        lblLongitud = new javax.swing.JLabel();
        lblCoordenadasGPS = new javax.swing.JLabel();
        LblInformacionComplementaria = new javax.swing.JLabel();
        PnlDireccionConglomerado = new javax.swing.JPanel();
        lblAzimut = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblDistancia = new javax.swing.JLabel();
        txtAzimut = new javax.swing.JFormattedTextField();
        txtDistancia = new javax.swing.JFormattedTextField();
        jLabel17 = new javax.swing.JLabel();
        PnlInformacionComplementaria = new javax.swing.JPanel();
        lblErrorPresicion = new javax.swing.JLabel();
        txtDatum = new javax.swing.JTextField();
        LblMetros = new javax.swing.JLabel();
        lblDatum = new javax.swing.JLabel();
        txtErrorPresicion = new javax.swing.JFormattedTextField();
        PnlDescripcion = new javax.swing.JPanel();
        lblDescripcion = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        LblParaje = new javax.swing.JLabel();
        txtParaje = new javax.swing.JTextField();
        PnlAccesibilidad = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        lblMedioTransporte = new javax.swing.JLabel();
        cmbMedioTransporte = new javax.swing.JComboBox();
        cmbViaAcceso = new javax.swing.JComboBox();
        lblViaAcceso = new javax.swing.JLabel();
        lblDistanciaAccesibilidad = new javax.swing.JLabel();
        lblCondicion = new javax.swing.JLabel();
        cmbCondicionAcceso = new javax.swing.JComboBox();
        txtDistanciaAccesibilidad = new javax.swing.JFormattedTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        grdAccesibilidad = new javax.swing.JTable();
        PnlContinuar = new javax.swing.JPanel();
        btnContinuar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        setTitle("Punto de control");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/gob/conafor/utils/logo_conafor.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(940, 650));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        PnlUPM.setBackground(new java.awt.Color(204, 204, 204));
        PnlUPM.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtUPM.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtUPM.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtUPM.setEnabled(false);

        lblUPMID.setText("UPMID:");

        javax.swing.GroupLayout PnlUPMLayout = new javax.swing.GroupLayout(PnlUPM);
        PnlUPM.setLayout(PnlUPMLayout);
        PnlUPMLayout.setHorizontalGroup(
            PnlUPMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlUPMLayout.createSequentialGroup()
                .addGap(190, 190, 190)
                .addComponent(lblUPMID)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUPM, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PnlUPMLayout.setVerticalGroup(
            PnlUPMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlUPMLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnlUPMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUPMID))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PnlCoordenadas.setBackground(new java.awt.Color(204, 204, 204));
        PnlCoordenadas.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblGradosLatitud.setText("Grados:");
        lblGradosLatitud.setToolTipText("");
        jPanel5.add(lblGradosLatitud);

        txtGradosLatitud.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtGradosLatitud.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGradosLatitud.setPreferredSize(new java.awt.Dimension(70, 20));
        txtGradosLatitud.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtGradosLatitudFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtGradosLatitudFocusLost(evt);
            }
        });
        txtGradosLatitud.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtGradosLatitudKeyTyped(evt);
            }
        });
        jPanel5.add(txtGradosLatitud);

        lblMinuosLatitud.setText("Minutos:");
        lblMinuosLatitud.setToolTipText("");
        jPanel5.add(lblMinuosLatitud);

        txtMinutosLatitud.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtMinutosLatitud.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMinutosLatitud.setPreferredSize(new java.awt.Dimension(70, 20));
        txtMinutosLatitud.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtMinutosLatitudFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtMinutosLatitudFocusLost(evt);
            }
        });
        txtMinutosLatitud.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMinutosLatitudKeyTyped(evt);
            }
        });
        jPanel5.add(txtMinutosLatitud);

        lblSegundosLatitud.setText("Segundos:");
        lblSegundosLatitud.setToolTipText("");
        jPanel5.add(lblSegundosLatitud);

        txtSegundosLatitud.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.000"))));
        txtSegundosLatitud.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSegundosLatitud.setPreferredSize(new java.awt.Dimension(70, 20));
        txtSegundosLatitud.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSegundosLatitudFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSegundosLatitudFocusLost(evt);
            }
        });
        txtSegundosLatitud.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSegundosLatitudKeyTyped(evt);
            }
        });
        jPanel5.add(txtSegundosLatitud);

        lblGradosLongitud.setText("Grados:");
        lblGradosLongitud.setToolTipText("");
        jPanel5.add(lblGradosLongitud);

        txtGradosLongitud.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtGradosLongitud.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGradosLongitud.setPreferredSize(new java.awt.Dimension(70, 20));
        txtGradosLongitud.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtGradosLongitudFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtGradosLongitudFocusLost(evt);
            }
        });
        txtGradosLongitud.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtGradosLongitudKeyTyped(evt);
            }
        });
        jPanel5.add(txtGradosLongitud);

        lblMinutosLongitud.setText("Minutos:");
        lblMinutosLongitud.setToolTipText("");
        jPanel5.add(lblMinutosLongitud);

        txtMinutosLongitud.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtMinutosLongitud.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMinutosLongitud.setPreferredSize(new java.awt.Dimension(70, 20));
        txtMinutosLongitud.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtMinutosLongitudFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtMinutosLongitudFocusLost(evt);
            }
        });
        txtMinutosLongitud.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMinutosLongitudKeyTyped(evt);
            }
        });
        jPanel5.add(txtMinutosLongitud);

        lblSegundosLongitud.setText("Segundos:");
        lblSegundosLongitud.setToolTipText("");
        jPanel5.add(lblSegundosLongitud);

        txtSegundosLongitud.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.000"))));
        txtSegundosLongitud.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSegundosLongitud.setPreferredSize(new java.awt.Dimension(70, 20));
        txtSegundosLongitud.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSegundosLongitudFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSegundosLongitudFocusLost(evt);
            }
        });
        txtSegundosLongitud.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSegundosLongitudKeyTyped(evt);
            }
        });
        jPanel5.add(txtSegundosLongitud);

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));

        lblLatitud.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblLatitud.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblLatitud.setText("Latitud:");
        lblLatitud.setPreferredSize(new java.awt.Dimension(50, 16));

        lblLongitud.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblLongitud.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblLongitud.setText("Longitud:");
        lblLongitud.setPreferredSize(new java.awt.Dimension(50, 16));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLongitud, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLatitud, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLatitud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblLongitud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblCoordenadasGPS.setBackground(new java.awt.Color(51, 51, 51));
        lblCoordenadasGPS.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblCoordenadasGPS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCoordenadasGPS.setText("Coordendas del GPS del PC");
        lblCoordenadasGPS.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        LblInformacionComplementaria.setBackground(new java.awt.Color(51, 51, 51));
        LblInformacionComplementaria.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        LblInformacionComplementaria.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblInformacionComplementaria.setText("Información complementaria");
        LblInformacionComplementaria.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        PnlDireccionConglomerado.setBackground(new java.awt.Color(204, 204, 204));
        PnlDireccionConglomerado.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblAzimut.setText("Azimut:");

        jLabel15.setText("m");

        lblDistancia.setText("Distancia:");

        txtAzimut.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtAzimut.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAzimut.setPreferredSize(new java.awt.Dimension(70, 20));
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
        txtDistancia.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDistancia.setPreferredSize(new java.awt.Dimension(70, 20));
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

        javax.swing.GroupLayout PnlDireccionConglomeradoLayout = new javax.swing.GroupLayout(PnlDireccionConglomerado);
        PnlDireccionConglomerado.setLayout(PnlDireccionConglomeradoLayout);
        PnlDireccionConglomeradoLayout.setHorizontalGroup(
            PnlDireccionConglomeradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnlDireccionConglomeradoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblAzimut)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAzimut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblDistancia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDistancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addGap(78, 78, 78))
        );
        PnlDireccionConglomeradoLayout.setVerticalGroup(
            PnlDireccionConglomeradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlDireccionConglomeradoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnlDireccionConglomeradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAzimut)
                    .addComponent(jLabel15)
                    .addComponent(lblDistancia)
                    .addComponent(txtAzimut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDistancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel17.setBackground(new java.awt.Color(51, 51, 51));
        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Dirección del conglomerado");
        jLabel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        PnlInformacionComplementaria.setBackground(new java.awt.Color(204, 204, 204));
        PnlInformacionComplementaria.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblErrorPresicion.setText("Error de presición:");

        txtDatum.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDatum.setText("WGS_84");
        txtDatum.setEnabled(false);
        txtDatum.setPreferredSize(new java.awt.Dimension(70, 20));

        LblMetros.setText("m");

        lblDatum.setText("Datum:");

        txtErrorPresicion.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtErrorPresicion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtErrorPresicion.setPreferredSize(new java.awt.Dimension(70, 20));
        txtErrorPresicion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtErrorPresicionFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtErrorPresicionFocusLost(evt);
            }
        });
        txtErrorPresicion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtErrorPresicionKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout PnlInformacionComplementariaLayout = new javax.swing.GroupLayout(PnlInformacionComplementaria);
        PnlInformacionComplementaria.setLayout(PnlInformacionComplementariaLayout);
        PnlInformacionComplementariaLayout.setHorizontalGroup(
            PnlInformacionComplementariaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlInformacionComplementariaLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(lblErrorPresicion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtErrorPresicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LblMetros)
                .addGap(26, 26, 26)
                .addComponent(lblDatum)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDatum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PnlInformacionComplementariaLayout.setVerticalGroup(
            PnlInformacionComplementariaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlInformacionComplementariaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnlInformacionComplementariaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblErrorPresicion)
                    .addComponent(txtDatum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblMetros)
                    .addComponent(lblDatum)
                    .addComponent(txtErrorPresicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PnlCoordenadasLayout = new javax.swing.GroupLayout(PnlCoordenadas);
        PnlCoordenadas.setLayout(PnlCoordenadasLayout);
        PnlCoordenadasLayout.setHorizontalGroup(
            PnlCoordenadasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnlCoordenadasLayout.createSequentialGroup()
                .addGroup(PnlCoordenadasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PnlCoordenadasLayout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PnlCoordenadasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(PnlCoordenadasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblCoordenadasGPS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(PnlDireccionConglomerado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(LblInformacionComplementaria, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(PnlInformacionComplementaria, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        PnlCoordenadasLayout.setVerticalGroup(
            PnlCoordenadasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlCoordenadasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCoordenadasGPS)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PnlCoordenadasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LblInformacionComplementaria)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PnlInformacionComplementaria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PnlDireccionConglomerado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        PnlDescripcion.setBackground(new java.awt.Color(204, 204, 204));
        PnlDescripcion.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblDescripcion.setText("Descripción:");

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        txtDescripcion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDescripcionFocusGained(evt);
            }
        });
        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(txtDescripcion);

        LblParaje.setText("Paraje:");

        txtParaje.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtParajeFocusGained(evt);
            }
        });

        javax.swing.GroupLayout PnlDescripcionLayout = new javax.swing.GroupLayout(PnlDescripcion);
        PnlDescripcion.setLayout(PnlDescripcionLayout);
        PnlDescripcionLayout.setHorizontalGroup(
            PnlDescripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlDescripcionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnlDescripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDescripcion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(PnlDescripcionLayout.createSequentialGroup()
                        .addGroup(PnlDescripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LblParaje, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtParaje, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        PnlDescripcionLayout.setVerticalGroup(
            PnlDescripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlDescripcionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDescripcion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addGap(18, 18, 18)
                .addComponent(LblParaje)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtParaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        PnlAccesibilidad.setBackground(new java.awt.Color(204, 204, 204));
        PnlAccesibilidad.setBorder(javax.swing.BorderFactory.createTitledBorder("Accesibilidad del punto de control  del UPM"));
        PnlAccesibilidad.setForeground(new java.awt.Color(204, 204, 204));

        jPanel12.setBackground(new java.awt.Color(204, 204, 204));

        lblMedioTransporte.setText("Medio de transporte:");

        cmbMedioTransporte.setPreferredSize(new java.awt.Dimension(150, 25));
        cmbMedioTransporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMedioTransporteActionPerformed(evt);
            }
        });

        cmbViaAcceso.setPreferredSize(new java.awt.Dimension(150, 25));

        lblViaAcceso.setText("Vía de acceso:");

        lblDistanciaAccesibilidad.setText("Distancia:");

        lblCondicion.setText("Condición:");

        cmbCondicionAcceso.setPreferredSize(new java.awt.Dimension(150, 25));

        txtDistanciaAccesibilidad.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("##0.00"))));
        txtDistanciaAccesibilidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDistanciaAccesibilidad.setPreferredSize(new java.awt.Dimension(90, 25));
        txtDistanciaAccesibilidad.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDistanciaAccesibilidadFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDistanciaAccesibilidadFocusLost(evt);
            }
        });
        txtDistanciaAccesibilidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDistanciaAccesibilidadKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblMedioTransporte)
                .addGap(18, 18, 18)
                .addComponent(cmbMedioTransporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblViaAcceso)
                .addGap(10, 10, 10)
                .addComponent(cmbViaAcceso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblDistanciaAccesibilidad)
                .addGap(2, 2, 2)
                .addComponent(txtDistanciaAccesibilidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblCondicion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbCondicionAcceso, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(cmbCondicionAcceso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblCondicion)
                .addComponent(lblDistanciaAccesibilidad)
                .addComponent(cmbViaAcceso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblViaAcceso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmbMedioTransporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblMedioTransporte)
                .addComponent(txtDistanciaAccesibilidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout PnlAccesibilidadLayout = new javax.swing.GroupLayout(PnlAccesibilidad);
        PnlAccesibilidad.setLayout(PnlAccesibilidadLayout);
        PnlAccesibilidadLayout.setHorizontalGroup(
            PnlAccesibilidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlAccesibilidadLayout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 887, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        PnlAccesibilidadLayout.setVerticalGroup(
            PnlAccesibilidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlAccesibilidadLayout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        grdAccesibilidad.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        grdAccesibilidad.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        grdAccesibilidad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grdAccesibilidadMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(grdAccesibilidad);

        PnlContinuar.setBackground(new java.awt.Color(204, 204, 204));
        PnlContinuar.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnContinuar.setText("Continuar");
        btnContinuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinuarActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.setPreferredSize(new java.awt.Dimension(79, 23));
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PnlContinuarLayout = new javax.swing.GroupLayout(PnlContinuar);
        PnlContinuar.setLayout(PnlContinuarLayout);
        PnlContinuarLayout.setHorizontalGroup(
            PnlContinuarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnlContinuarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnlContinuarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnContinuar, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                .addContainerGap())
        );
        PnlContinuarLayout.setVerticalGroup(
            PnlContinuarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnlContinuarLayout.createSequentialGroup()
                .addContainerGap(51, Short.MAX_VALUE)
                .addComponent(btnContinuar)
                .addGap(17, 17, 17)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setPreferredSize(new java.awt.Dimension(523, 51));

        btnGuardar.setText("Guardar");
        btnGuardar.setPreferredSize(new java.awt.Dimension(100, 23));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel3.add(btnGuardar);

        btnActualizar.setText("Actualizar");
        btnActualizar.setPreferredSize(new java.awt.Dimension(100, 23));
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        jPanel3.add(btnActualizar);

        btnEliminar.setText("Eliminar");
        btnEliminar.setPreferredSize(new java.awt.Dimension(100, 23));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel3.add(btnEliminar);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 724, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PnlContinuar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(PnlAccesibilidad, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(PnlDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(PnlCoordenadas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(PnlUPM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 724, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PnlDescripcion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(PnlUPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PnlCoordenadas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(PnlAccesibilidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PnlContinuar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
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

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.hide();
        funciones.manipularBotonesMenuPrincipal(false);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuarActionPerformed
        if (validarCamposObligatorios() && validarDatosPC()) {
            if (accesibilidad.validarTablaAccesibilida(upm)) {
                JOptionPane.showMessageDialog(null, "No ha captura la información de accesibilidad", "Accesibilidad", JOptionPane.ERROR_MESSAGE);
            } else if (modificar == 0) {
                crearPC();
                this.hide();
                if (this.tipoUpm > 2 && this.tipoUpm < 6) {
                    UPMForms.inaccesibleUPM.setDatosIniciales(this.ceUpm);
                    UPMForms.inaccesibleUPM.setVisible(true);
                } else {
                    funciones.manipularBotonesMenuPrincipal(false);
                }
            } else {
                System.out.println(this.tipoUpm);
                modificarPC();
                this.hide();
                if (this.tipoUpm > 2 && this.tipoUpm < 6) {
                    UPMForms.inaccesibleUPM.revisarUPMInaccesible(this.ceUpm);
                    UPMForms.inaccesibleUPM.setVisible(true);
                } else {
                    funciones.manipularBotonesMenuPrincipal(false);
                }
            }
        }
    }//GEN-LAST:event_btnContinuarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (validarViaAcceso()) {
            crearViaAcceso();
            llenarTabla();
            desabilitarBotonesViaAcceso();
            limpiarControlesViaAcceso();
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void grdAccesibilidadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grdAccesibilidadMouseClicked
        String[] camposAccesibilidad;

        if (evt.getButton() == 1) {
            desabilitarBotonesViaAcceso();
            // btnGuardar.setEnabled(false);
            int fila = grdAccesibilidad.getSelectedRow();
            String registro = grdAccesibilidad.getValueAt(fila, 0).toString();
            String[] datosCombo = accesibilidad.getDatosComboAcesibilidad(Integer.parseInt(registro));

            int mtIndex = Integer.parseInt(datosCombo[0]);
            cmbMedioTransporte.setSelectedIndex(mtIndex);
            cmbViaAcceso.removeAllItems();
            fillCmbViaAcceso(mtIndex);
            cmbViaAcceso.setSelectedIndex(Integer.parseInt(datosCombo[1]));
            txtDistanciaAccesibilidad.setText(datosCombo[2]);
            cmbCondicionAcceso.setSelectedIndex(Integer.parseInt(datosCombo[3]));
        }

    }//GEN-LAST:event_grdAccesibilidadMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if (validarViaAcceso()) {
            eliminarViaAcceso();
            llenarTabla();
            desabilitarBotonesViaAcceso();
            limpiarControlesViaAcceso();
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        if (validarViaAcceso()) {
            actualizarViaAcceso();
            llenarTabla();
            desabilitarBotonesViaAcceso();
            limpiarControlesViaAcceso();
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void txtDescripcionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDescripcionFocusGained
        txtDescripcion.selectAll();
    }//GEN-LAST:event_txtDescripcionFocusGained

    private void txtParajeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtParajeFocusGained
        txtParaje.selectAll();
    }//GEN-LAST:event_txtParajeFocusGained

    private void txtGradosLatitudFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGradosLatitudFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtGradosLatitud.selectAll();
            }
        });
    }//GEN-LAST:event_txtGradosLatitudFocusGained

    private void txtMinutosLatitudFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMinutosLatitudFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtMinutosLatitud.selectAll();
            }
        });
    }//GEN-LAST:event_txtMinutosLatitudFocusGained

    private void txtSegundosLatitudFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSegundosLatitudFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtSegundosLatitud.selectAll();
            }
        });
    }//GEN-LAST:event_txtSegundosLatitudFocusGained

    private void txtGradosLongitudFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGradosLongitudFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtGradosLongitud.selectAll();
            }
        });
    }//GEN-LAST:event_txtGradosLongitudFocusGained

    private void txtMinutosLongitudFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMinutosLongitudFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtMinutosLongitud.selectAll();
            }
        });
    }//GEN-LAST:event_txtMinutosLongitudFocusGained

    private void txtSegundosLongitudFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSegundosLongitudFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtSegundosLongitud.selectAll();
            }
        });
    }//GEN-LAST:event_txtSegundosLongitudFocusGained

    private void txtErrorPresicionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtErrorPresicionFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtErrorPresicion.selectAll();
            }
        });
    }//GEN-LAST:event_txtErrorPresicionFocusGained

    private void txtAzimutFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAzimutFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtAzimut.selectAll();
            }
        });
    }//GEN-LAST:event_txtAzimutFocusGained

    private void txtDistanciaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDistanciaFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtDistancia.selectAll();
            }
        });
    }//GEN-LAST:event_txtDistanciaFocusGained

    private void txtDistanciaAccesibilidadFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDistanciaAccesibilidadFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtDistanciaAccesibilidad.selectAll();
            }
        });
    }//GEN-LAST:event_txtDistanciaAccesibilidadFocusGained

    private void txtGradosLatitudFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGradosLatitudFocusLost
        if (txtGradosLatitud.getText().isEmpty()) {
            txtGradosLatitud.setValue(null);
        }
    }//GEN-LAST:event_txtGradosLatitudFocusLost

    private void txtMinutosLatitudFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMinutosLatitudFocusLost
        if (txtMinutosLatitud.getText().isEmpty()) {
            txtMinutosLatitud.setValue(null);
        }
    }//GEN-LAST:event_txtMinutosLatitudFocusLost

    private void txtSegundosLatitudFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSegundosLatitudFocusLost
        if (txtSegundosLatitud.getText().isEmpty()) {
            txtSegundosLatitud.setValue(null);
        }
    }//GEN-LAST:event_txtSegundosLatitudFocusLost

    private void txtGradosLongitudFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGradosLongitudFocusLost
        String gradosLongitud;
        if (txtGradosLongitud.getText().isEmpty()) {
            txtGradosLongitud.setValue(null);
        } else {
           gradosLongitud = txtGradosLongitud.getText();
           gradosLongitud = "-" + gradosLongitud;
           txtGradosLongitud.setText(gradosLongitud);
        }
    }//GEN-LAST:event_txtGradosLongitudFocusLost

    private void txtMinutosLongitudFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMinutosLongitudFocusLost
        if (txtMinutosLongitud.getText().isEmpty()) {
            txtMinutosLongitud.setValue(null);
        }
    }//GEN-LAST:event_txtMinutosLongitudFocusLost

    private void txtSegundosLongitudFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSegundosLongitudFocusLost
        if (txtSegundosLongitud.getText().isEmpty()) {
            txtSegundosLongitud.setValue(null);
        }
    }//GEN-LAST:event_txtSegundosLongitudFocusLost

    private void txtErrorPresicionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtErrorPresicionFocusLost
        if (txtErrorPresicion.getText().isEmpty()) {
            txtErrorPresicion.setValue(null);
        }
    }//GEN-LAST:event_txtErrorPresicionFocusLost

    private void txtAzimutFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAzimutFocusLost
        if (txtAzimut.getText().isEmpty()) {
            txtAzimut.setValue(null);
        }
    }//GEN-LAST:event_txtAzimutFocusLost

    private void txtDistanciaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDistanciaFocusLost
        if (txtDistancia.getText().isEmpty()) {
            txtDistancia.setValue(null);
        }
    }//GEN-LAST:event_txtDistanciaFocusLost

    private void txtDistanciaAccesibilidadFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDistanciaAccesibilidadFocusLost
        if (txtDistanciaAccesibilidad.getText().isEmpty()) {
            txtDistanciaAccesibilidad.setValue(null);
        }
    }//GEN-LAST:event_txtDistanciaAccesibilidadFocusLost

    private void cmbMedioTransporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMedioTransporteActionPerformed
        CatEMedioTransporte mt = (CatEMedioTransporte) cmbMedioTransporte.getSelectedItem();
        DefaultComboBoxModel dcm = (DefaultComboBoxModel) cmbViaAcceso.getModel();
        dcm.removeAllElements();

        if (mt != null) {
            fillCmbViaAcceso(mt.getMedioTransporteID());
        }
    }//GEN-LAST:event_cmbMedioTransporteActionPerformed

    private void txtDescripcionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            evt.consume();
        }
    }//GEN-LAST:event_txtDescripcionKeyPressed

    private void txtGradosLatitudKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGradosLatitudKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtGradosLatitudKeyTyped

    private void txtMinutosLatitudKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMinutosLatitudKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtMinutosLatitudKeyTyped

    private void txtSegundosLatitudKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSegundosLatitudKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtSegundosLatitudKeyTyped

    private void txtGradosLongitudKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGradosLongitudKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtGradosLongitudKeyTyped

    private void txtMinutosLongitudKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMinutosLongitudKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtMinutosLongitudKeyTyped

    private void txtSegundosLongitudKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSegundosLongitudKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtSegundosLongitudKeyTyped

    private void txtErrorPresicionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtErrorPresicionKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtErrorPresicionKeyTyped

    private void txtAzimutKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAzimutKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtAzimutKeyTyped

    private void txtDistanciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDistanciaKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtDistanciaKeyTyped

    private void txtDistanciaAccesibilidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDistanciaAccesibilidadKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtDistanciaAccesibilidadKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LblInformacionComplementaria;
    private javax.swing.JLabel LblMetros;
    private javax.swing.JLabel LblParaje;
    private javax.swing.JPanel PnlAccesibilidad;
    private javax.swing.JPanel PnlContinuar;
    private javax.swing.JPanel PnlCoordenadas;
    private javax.swing.JPanel PnlDescripcion;
    private javax.swing.JPanel PnlDireccionConglomerado;
    private javax.swing.JPanel PnlInformacionComplementaria;
    private javax.swing.JPanel PnlUPM;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnContinuar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cmbCondicionAcceso;
    private javax.swing.JComboBox cmbMedioTransporte;
    private javax.swing.JComboBox cmbViaAcceso;
    private javax.swing.JTable grdAccesibilidad;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAzimut;
    private javax.swing.JLabel lblCondicion;
    private javax.swing.JLabel lblCoordenadasGPS;
    private javax.swing.JLabel lblDatum;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblDistancia;
    private javax.swing.JLabel lblDistanciaAccesibilidad;
    private javax.swing.JLabel lblErrorPresicion;
    private javax.swing.JLabel lblGradosLatitud;
    private javax.swing.JLabel lblGradosLongitud;
    private javax.swing.JLabel lblLatitud;
    private javax.swing.JLabel lblLongitud;
    private javax.swing.JLabel lblMedioTransporte;
    private javax.swing.JLabel lblMinuosLatitud;
    private javax.swing.JLabel lblMinutosLongitud;
    private javax.swing.JLabel lblSegundosLatitud;
    private javax.swing.JLabel lblSegundosLongitud;
    private javax.swing.JLabel lblUPMID;
    private javax.swing.JLabel lblViaAcceso;
    private javax.swing.JFormattedTextField txtAzimut;
    private javax.swing.JTextField txtDatum;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JFormattedTextField txtDistancia;
    private javax.swing.JFormattedTextField txtDistanciaAccesibilidad;
    private javax.swing.JFormattedTextField txtErrorPresicion;
    private javax.swing.JFormattedTextField txtGradosLatitud;
    private javax.swing.JFormattedTextField txtGradosLongitud;
    private javax.swing.JFormattedTextField txtMinutosLatitud;
    private javax.swing.JFormattedTextField txtMinutosLongitud;
    private javax.swing.JTextField txtParaje;
    private javax.swing.JFormattedTextField txtSegundosLatitud;
    private javax.swing.JFormattedTextField txtSegundosLongitud;
    private javax.swing.JTextField txtUPM;
    // End of variables declaration//GEN-END:variables
}
