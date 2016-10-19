package gob.conafor.upm.vie;

import gob.conafor.sys.mod.CDSeguimientoUPM;
import gob.conafor.sys.mod.CESeccionesCapturadas;
import gob.conafor.upm.mod.CDUpmInaccesible;
import gob.conafor.upm.mod.CEUPM;
import gob.conafor.upm.mod.CatETipoInaccesibilidad;
import gob.conafor.utils.Datos;
import gob.conafor.utils.FuncionesComunes;
import gob.conafor.utils.ValidacionesComunes;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class FrmUPMInaccesible extends javax.swing.JInternalFrame {

    private int upm;
    private CDUpmInaccesible cdInaccesible = new CDUpmInaccesible();
    private CEUPM ceUpm = new CEUPM();
    private final int inaccesible;
    private CDSeguimientoUPM seguimiento = new CDSeguimientoUPM();
    private CESeccionesCapturadas inaccesibleUPM = new CESeccionesCapturadas();
    private Datos numeros = new Datos();
    private int modificar;
    private FuncionesComunes funciones = new FuncionesComunes();
   
    public FrmUPMInaccesible() {
        initComponents();
        fillCmbInaccesible();
        this.inaccesible = 3;
    }
    
    public void setDatosIniciales(CEUPM upm) {
        this.ceUpm = upm;
        this.upm = upm.getUpmID();
        txtUPM.setText(String.valueOf(upm.getUpmID()));
        this.modificar = 0;
        funciones.manipularBotonesMenuPrincipal(true);
        limpiarControles();
    }
    
    public void revisarUPMInaccesible(CEUPM upm){
        this.ceUpm = upm;
        this.upm = this.ceUpm.getUpmID();
        txtUPM.setText(String.valueOf(upm.getUpmID()));
        this.ceUpm = this.cdInaccesible.getUPMInaccesible(this.upm);
        txtGradosLatitud.setText(String.valueOf(this.ceUpm.getGradosLatitud()));
        txtMinutosLatitud.setText(String.valueOf(this.ceUpm.getMinutosLatitud()));
        txtSegundosLatitud.setText(String.valueOf(this.ceUpm.getSegundosLatitud()));
        txtGradosLongitud.setText(String.valueOf(this.ceUpm.getGradosLongitud()));
        txtMinutosLongitud.setText(String.valueOf(this.ceUpm.getMinutosLongitud()));
        txtSegundosLongitud.setText(String.valueOf(this.ceUpm.getSegundosLongitud()));
        txtEPE.setText(String.valueOf(this.ceUpm.getErrorPresicion()));
        txtAzimut.setText(String.valueOf(this.ceUpm.getAzimut()));
        txtDistancia.setText(String.valueOf(this.ceUpm.getDistancia()));
        CatETipoInaccesibilidad ceTipoInaccesibilidad = new CatETipoInaccesibilidad();
        ceTipoInaccesibilidad.setTipoInaccesibilidadID(this.ceUpm.getTipoInaccesibilidadID());
        cmbTipoInaccesibilidad.setSelectedItem(ceTipoInaccesibilidad);
        txtExplicacion.setText(this.ceUpm.getExplicacionInaccesibilidad());
        if(this.ceUpm.getTipoInaccesibilidadID() == 9){
            txtOtroTipo.setEnabled(true);
            txtOtroTipo.setText(this.ceUpm.getOtroTipoInaccesibilidad());
        }
        this.modificar = 1;
        funciones.manipularBotonesMenuPrincipal(true);
    }
    
    private void fillCmbInaccesible() {
        List<CatETipoInaccesibilidad> listInaccesibilidad = new ArrayList<>();
        listInaccesibilidad = this.cdInaccesible.getTipoInaccesibilidad();
        if (listInaccesibilidad != null) {
            int size = listInaccesibilidad.size();
            for (int i = 0; i < size; i++) {
                cmbTipoInaccesibilidad.addItem(listInaccesibilidad.get(i));
            }
        }
    }
    
    private boolean validarCamposObligatorios() {
        if (txtGradosLatitud.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Error! debe proporcionar grados latitud ",
                    "Sitio", JOptionPane.ERROR_MESSAGE);
            txtGradosLatitud.requestFocus();
            return false;
        } else if (txtMinutosLatitud.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Error! debe proporcionar minutos latitud ",
                    "Sitio", JOptionPane.ERROR_MESSAGE);
            txtMinutosLatitud.requestFocus();
            return false;
        } else if (txtSegundosLatitud.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Error! debe proporcionar segundos latitud ",
                    "Sitio", JOptionPane.ERROR_MESSAGE);
            txtSegundosLatitud.requestFocus();
            return false;
        } else if (txtGradosLongitud.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Error! debe proporcionar Grados longitud ",
                    "Sitio", JOptionPane.ERROR_MESSAGE);
            txtGradosLongitud.requestFocus();
            return false;
        } else if (txtMinutosLongitud.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Error! debe proporcionar Minutos longitud ",
                    "Sitio", JOptionPane.ERROR_MESSAGE);
            txtMinutosLongitud.requestFocus();
            return false;
        } else if (txtSegundosLongitud.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Error! debe proporcionar Segundos longitud ",
                    "Sitio", JOptionPane.ERROR_MESSAGE);
            txtSegundosLongitud.requestFocus();
            return false;
        } else if (txtEPE.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Error! debe proporcionar el error de presición ",
                    "Sitio", JOptionPane.ERROR_MESSAGE);
            txtEPE.requestFocus();
            return false;
        } else if (txtEPE.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Error! Debe proporcionar el Azimut ",
                    "Sitio", JOptionPane.ERROR_MESSAGE);
            txtEPE.requestFocus();
            return false;
        } else if (txtDistancia.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Error! Debe proporcionar la Distancia ",
                    "Sitio", JOptionPane.ERROR_MESSAGE);
            txtDistancia.requestFocus();
            return false;
        } else if (cmbTipoInaccesibilidad.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null,
                    "Error! Debe seleccionar un tipo de inaccesibilidad ",
                    "UPM inaccesible", JOptionPane.ERROR_MESSAGE);
            cmbTipoInaccesibilidad.requestFocus();
            return false;
        } else if (txtExplicacion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Error! Debe ingresar la explicación de inaccesibilidad",
                    "UPM inaccesible", JOptionPane.ERROR_MESSAGE);
            txtExplicacion.requestFocus();
            return false;
        } else if (txtOtroTipo.isEnabled() && txtOtroTipo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Error! Debe proporcionar otro tipo de inaccesibilidad ",
                    "UPM inaccesible", JOptionPane.ERROR_MESSAGE);
            txtOtroTipo.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean validarDatos() {
        ValidacionesComunes vc = new ValidacionesComunes();
        Integer gradosLatitud = Integer.parseInt(txtGradosLatitud.getText());
        Integer minutosLatitud = Integer.parseInt(txtMinutosLatitud.getText());
        Float segundosLatitud = Float.parseFloat(txtSegundosLatitud.getText());
        Integer gradosLongitud = 0;
        try {
            gradosLongitud = Integer.parseInt(txtGradosLongitud.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error! Debe introducir un dato válido para grados longitud ",
                    "UPM inaccesible", JOptionPane.ERROR_MESSAGE);
            txtGradosLongitud.requestFocus();
        }
        Integer minutosLongitud = Integer.parseInt(txtMinutosLongitud.getText());
        Float segundosLongitud = Float.parseFloat(txtSegundosLongitud.getText());
        Integer errorPresicion = Integer.parseInt(txtEPE.getText());
        Integer azimut = Integer.parseInt(txtEPE.getText());
        Float distancia = Float.parseFloat(txtDistancia.getText());
        if (vc.sonGradosLatitud(gradosLatitud, "Informacion del UPM")) {
            txtGradosLatitud.requestFocus();
            txtGradosLatitud.setText("");
            return false;
        } else if (vc.sonMinutos(minutosLatitud, "Informacion del UPM")) {
            txtMinutosLatitud.requestFocus();
            txtMinutosLatitud.setText("");
            return false;
        } else if (vc.sonSegundos(segundosLatitud, "Informacion del UPM")) {
            txtSegundosLatitud.requestFocus();
            txtSegundosLatitud.setText("");
            return false;
        } else if (vc.sonGradosLongitud(gradosLongitud, "Informacion del UPM")) {
            txtGradosLongitud.requestFocus();
            txtGradosLongitud.setText("");
            return false;
        } else if (vc.sonMinutos(minutosLongitud, "Informacion del UPM")) {
            txtMinutosLongitud.requestFocus();
            txtMinutosLongitud.setText("");
            return false;
        } else if (vc.sonSegundos(segundosLongitud, "Informacion del UPM")) {
            txtSegundosLongitud.requestFocus();
            txtSegundosLongitud.setText("");
            return false;
        } else if (vc.esErrorPrecision(errorPresicion, "Informacion del UPM")) {
            txtEPE.requestFocus();
            txtEPE.setText("");
            return false;
        } else if (vc.esAzimut(azimut, "Sitio")) {
            txtEPE.requestFocus();
            txtEPE.setText("");
            return false;
        } else if (vc.esDistancia(distancia, "Sitio")) {
            txtDistancia.requestFocus();
            txtDistancia.setText("");
            return false;
        } else {
            return true;
        }
    }

    private boolean validarTipoUPM() {
        CatETipoInaccesibilidad ceTipoInaccesibilidad = (CatETipoInaccesibilidad) cmbTipoInaccesibilidad.getSelectedItem();
        int tipoInaccesibilidadID = ceTipoInaccesibilidad.getTipoInaccesibilidadID();
        int tipoUpmID = this.ceUpm.getTipoUpmID();
        if (tipoUpmID == 3) {
            if (tipoInaccesibilidadID < 3 || tipoInaccesibilidadID > 8) {
                JOptionPane.showMessageDialog(null, "El tipo de inaccesibilidad seleccionado no corresponde a la inaccesibilidad por problema físico "
                        + "", "Captura de la UPM", JOptionPane.INFORMATION_MESSAGE);
                cmbTipoInaccesibilidad.requestFocus();
                return false;
            } else {
                return true;
            }
        } else if (tipoUpmID == 4) {
            if (tipoInaccesibilidadID < 10 || tipoInaccesibilidadID > 16 || tipoInaccesibilidadID != 1 || tipoInaccesibilidadID != 2) {
                JOptionPane.showMessageDialog(null, "El tipo de inaccesibilidad seleccionado no corresponde a la inaccesibilidad por problema social "
                        + "", "Captura de la UPM", JOptionPane.INFORMATION_MESSAGE);
                cmbTipoInaccesibilidad.requestFocus();
                return false;
            } else {
                return true;
            }
        } else if (tipoUpmID == 5) {
            if (tipoInaccesibilidadID != 17) {
                JOptionPane.showMessageDialog(null, "El tipo de inaccesibilidad seleccionado no corresponde a la inaccesibilidad de gabinete "
                        + "", "Captura de la UPM", JOptionPane.INFORMATION_MESSAGE);
                cmbTipoInaccesibilidad.requestFocus();
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    private void crearUpmInaccesible(){
        CatETipoInaccesibilidad inaccesibilidad = (CatETipoInaccesibilidad) cmbTipoInaccesibilidad.getSelectedItem();
        
        ceUpm.setUpmID(this.upm);
        ceUpm.setGradosLatitud(Integer.parseInt(txtGradosLatitud.getText()));
        ceUpm.setMinutosLatitud(Integer.parseInt(txtMinutosLatitud.getText()));
        ceUpm.setSegundosLatitud(Float.parseFloat(txtSegundosLatitud.getText()));
        ceUpm.setGradosLongitud(Integer.parseInt(txtGradosLongitud.getText()));
        ceUpm.setMinutosLongitud(Integer.parseInt(txtMinutosLongitud.getText()));
        ceUpm.setSegundosLongitud(Float.parseFloat(txtSegundosLongitud.getText()));
        ceUpm.setDatum(txtDatum.getText());
        ceUpm.setErrorPresicion(Integer.parseInt(txtEPE.getText()));
        ceUpm.setAzimut(Integer.parseInt(txtEPE.getText()));
        ceUpm.setDistancia(Float.parseFloat(txtDistancia.getText()));
        ceUpm.setTipoInaccesibilidadID(inaccesibilidad.getTipoInaccesibilidadID());
        ceUpm.setOtroTipoInaccesibilidad(txtOtroTipo.getText());
        ceUpm.setExplicacionInaccesibilidad(txtExplicacion.getText());
        
        this.cdInaccesible.updateInaccesibilidadUPMOtro(ceUpm);
    }
    
    private void limpiarControles() {
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
        txtEPE.setValue(null);
        txtEPE.setText("");
        txtAzimut.setValue(null);
        txtAzimut.setText("");
        txtDistancia.setValue(null);
        txtDistancia.setText("");
        cmbTipoInaccesibilidad.setSelectedItem(null);
        txtOtroTipo.setText("");
        txtOtroTipo.setEnabled(false);
        txtExplicacion.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        PnlIUPM = new javax.swing.JPanel();
        txtUPM = new javax.swing.JTextField();
        lblUPMID = new javax.swing.JLabel();
        PnlTitulo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        PnlCoordenadasExterior = new javax.swing.JPanel();
        PnlCoordenadasInterior = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        lblLatitud = new javax.swing.JLabel();
        lblLongitud = new javax.swing.JLabel();
        lblComplementaria = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        lblGradosLatitud = new javax.swing.JLabel();
        lblMinutosLatitud = new javax.swing.JLabel();
        lblSegundosLatitud = new javax.swing.JLabel();
        txtGradosLatitud = new javax.swing.JFormattedTextField();
        txtMinutosLatitud = new javax.swing.JFormattedTextField();
        txtSegundosLatitud = new javax.swing.JFormattedTextField();
        jPanel10 = new javax.swing.JPanel();
        lblGradosLongitud = new javax.swing.JLabel();
        lblMinutosLongitud = new javax.swing.JLabel();
        lblSegundosLongitud = new javax.swing.JLabel();
        txtGradosLongitud = new javax.swing.JFormattedTextField();
        txtMinutosLongitud = new javax.swing.JFormattedTextField();
        txtSegundosLongitud = new javax.swing.JFormattedTextField();
        jPanel11 = new javax.swing.JPanel();
        lblDatum = new javax.swing.JLabel();
        lblEPE = new javax.swing.JLabel();
        lblDistancia = new javax.swing.JLabel();
        txtDatum = new javax.swing.JTextField();
        lblAzimut = new javax.swing.JLabel();
        txtEPE = new javax.swing.JFormattedTextField();
        txtDistancia = new javax.swing.JFormattedTextField();
        txtAzimut = new javax.swing.JFormattedTextField();
        jPanel8 = new javax.swing.JPanel();
        lblTipoInaccesibilidad = new javax.swing.JLabel();
        cmbTipoInaccesibilidad = new javax.swing.JComboBox();
        lblOtro = new javax.swing.JLabel();
        txtOtroTipo = new javax.swing.JTextField();
        PnlExplicacionInaccesibilidad = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtExplicacion = new javax.swing.JTextArea();
        btnTerminar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setTitle("Características de inaccesibilidad del conglomerado, módulo 0");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/gob/conafor/utils/logo_conafor.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(940, 650));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        PnlIUPM.setBackground(new java.awt.Color(204, 204, 204));
        PnlIUPM.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtUPM.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtUPM.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtUPM.setEnabled(false);

        lblUPMID.setText("UPMID:");

        javax.swing.GroupLayout PnlIUPMLayout = new javax.swing.GroupLayout(PnlIUPM);
        PnlIUPM.setLayout(PnlIUPMLayout);
        PnlIUPMLayout.setHorizontalGroup(
            PnlIUPMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnlIUPMLayout.createSequentialGroup()
                .addContainerGap(139, Short.MAX_VALUE)
                .addComponent(lblUPMID)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUPM, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(136, 136, 136))
        );
        PnlIUPMLayout.setVerticalGroup(
            PnlIUPMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnlIUPMLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PnlIUPMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUPMID))
                .addContainerGap())
        );

        PnlTitulo.setBackground(new java.awt.Color(204, 204, 204));
        PnlTitulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTitulo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Conglomerado inaccesible");

        javax.swing.GroupLayout PnlTituloLayout = new javax.swing.GroupLayout(PnlTitulo);
        PnlTitulo.setLayout(PnlTituloLayout);
        PnlTituloLayout.setHorizontalGroup(
            PnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PnlTituloLayout.setVerticalGroup(
            PnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnlTituloLayout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(lblTitulo)
                .addContainerGap())
        );

        PnlCoordenadasExterior.setBackground(new java.awt.Color(204, 204, 204));
        PnlCoordenadasExterior.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        PnlCoordenadasInterior.setBackground(new java.awt.Color(204, 204, 204));
        PnlCoordenadasInterior.setBorder(javax.swing.BorderFactory.createTitledBorder("Coordenadas GPS del último punto de acceso"));

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));

        lblLatitud.setBackground(new java.awt.Color(0, 0, 0));
        lblLatitud.setForeground(new java.awt.Color(255, 255, 255));
        lblLatitud.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLatitud.setText("Latitud");
        lblLatitud.setOpaque(true);

        lblLongitud.setBackground(new java.awt.Color(0, 0, 0));
        lblLongitud.setForeground(new java.awt.Color(255, 255, 255));
        lblLongitud.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLongitud.setText("Longitud");
        lblLongitud.setOpaque(true);

        lblComplementaria.setBackground(new java.awt.Color(0, 0, 0));
        lblComplementaria.setForeground(new java.awt.Color(255, 255, 255));
        lblComplementaria.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblComplementaria.setText("Información complementaria");
        lblComplementaria.setOpaque(true);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(lblLatitud, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblLongitud, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(lblComplementaria, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblLatitud)
                .addComponent(lblLongitud)
                .addComponent(lblComplementaria))
        );

        jPanel7.setBackground(new java.awt.Color(204, 204, 204));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblGradosLatitud.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGradosLatitud.setText("Grados");
        lblGradosLatitud.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel7.add(lblGradosLatitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 80, -1));

        lblMinutosLatitud.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMinutosLatitud.setText("Minutos");
        lblMinutosLatitud.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblMinutosLatitud.setPreferredSize(new java.awt.Dimension(36, 16));
        jPanel7.add(lblMinutosLatitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 80, -1));

        lblSegundosLatitud.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSegundosLatitud.setText("Segundos");
        lblSegundosLatitud.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblSegundosLatitud.setPreferredSize(new java.awt.Dimension(36, 16));
        jPanel7.add(lblSegundosLatitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, 80, -1));

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
        jPanel7.add(txtGradosLatitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 80, -1));

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
        jPanel7.add(txtMinutosLatitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 80, -1));

        txtSegundosLatitud.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
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
        jPanel7.add(txtSegundosLatitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 80, -1));

        jPanel10.setBackground(new java.awt.Color(204, 204, 204));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblGradosLongitud.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGradosLongitud.setText("Grados");
        lblGradosLongitud.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel10.add(lblGradosLongitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 80, -1));

        lblMinutosLongitud.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMinutosLongitud.setText("Minutos");
        lblMinutosLongitud.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblMinutosLongitud.setPreferredSize(new java.awt.Dimension(36, 16));
        jPanel10.add(lblMinutosLongitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 80, -1));

        lblSegundosLongitud.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSegundosLongitud.setText("Segundos");
        lblSegundosLongitud.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblSegundosLongitud.setPreferredSize(new java.awt.Dimension(36, 16));
        jPanel10.add(lblSegundosLongitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, 80, -1));

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
        jPanel10.add(txtGradosLongitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 80, -1));

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
        jPanel10.add(txtMinutosLongitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 80, -1));

        txtSegundosLongitud.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
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
        jPanel10.add(txtSegundosLongitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 80, -1));

        jPanel11.setBackground(new java.awt.Color(204, 204, 204));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblDatum.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDatum.setText("Datum");
        lblDatum.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel11.add(lblDatum, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 80, -1));

        lblEPE.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEPE.setText("EPE");
        lblEPE.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblEPE.setPreferredSize(new java.awt.Dimension(36, 16));
        jPanel11.add(lblEPE, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 0, 80, -1));

        lblDistancia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDistancia.setText("Distancia");
        lblDistancia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblDistancia.setPreferredSize(new java.awt.Dimension(36, 16));
        jPanel11.add(lblDistancia, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 80, -1));

        txtDatum.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDatum.setText("WGS_84");
        txtDatum.setEnabled(false);
        jPanel11.add(txtDatum, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 80, 20));

        lblAzimut.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAzimut.setText("Azimut");
        lblAzimut.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblAzimut.setPreferredSize(new java.awt.Dimension(36, 16));
        jPanel11.add(lblAzimut, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 0, 80, -1));

        txtEPE.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtEPE.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEPE.setPreferredSize(new java.awt.Dimension(70, 20));
        txtEPE.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtEPEFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtEPEFocusLost(evt);
            }
        });
        txtEPE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEPEKeyTyped(evt);
            }
        });
        jPanel11.add(txtEPE, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 80, -1));

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
        jPanel11.add(txtDistancia, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 20, 80, -1));

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
        jPanel11.add(txtAzimut, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 80, -1));

        javax.swing.GroupLayout PnlCoordenadasInteriorLayout = new javax.swing.GroupLayout(PnlCoordenadasInterior);
        PnlCoordenadasInterior.setLayout(PnlCoordenadasInteriorLayout);
        PnlCoordenadasInteriorLayout.setHorizontalGroup(
            PnlCoordenadasInteriorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlCoordenadasInteriorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnlCoordenadasInteriorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(PnlCoordenadasInteriorLayout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        PnlCoordenadasInteriorLayout.setVerticalGroup(
            PnlCoordenadasInteriorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnlCoordenadasInteriorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PnlCoordenadasInteriorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PnlCoordenadasExteriorLayout = new javax.swing.GroupLayout(PnlCoordenadasExterior);
        PnlCoordenadasExterior.setLayout(PnlCoordenadasExteriorLayout);
        PnlCoordenadasExteriorLayout.setHorizontalGroup(
            PnlCoordenadasExteriorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlCoordenadasExteriorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PnlCoordenadasInterior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        PnlCoordenadasExteriorLayout.setVerticalGroup(
            PnlCoordenadasExteriorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlCoordenadasExteriorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PnlCoordenadasInterior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(204, 204, 204));

        lblTipoInaccesibilidad.setText("Tipo de inaccesibilidad:");

        cmbTipoInaccesibilidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipoInaccesibilidadActionPerformed(evt);
            }
        });

        lblOtro.setText("Otro:");

        txtOtroTipo.setEnabled(false);
        txtOtroTipo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtOtroTipoFocusGained(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(lblTipoInaccesibilidad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbTipoInaccesibilidad, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblOtro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtOtroTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipoInaccesibilidad)
                    .addComponent(cmbTipoInaccesibilidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblOtro)
                    .addComponent(txtOtroTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PnlExplicacionInaccesibilidad.setBackground(new java.awt.Color(204, 204, 204));
        PnlExplicacionInaccesibilidad.setBorder(javax.swing.BorderFactory.createTitledBorder("Explicación del motivo y/o causas de inaccesibilidad del conglomerado"));

        txtExplicacion.setColumns(20);
        txtExplicacion.setRows(5);
        txtExplicacion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtExplicacionFocusGained(evt);
            }
        });
        txtExplicacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtExplicacionKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(txtExplicacion);

        javax.swing.GroupLayout PnlExplicacionInaccesibilidadLayout = new javax.swing.GroupLayout(PnlExplicacionInaccesibilidad);
        PnlExplicacionInaccesibilidad.setLayout(PnlExplicacionInaccesibilidadLayout);
        PnlExplicacionInaccesibilidadLayout.setHorizontalGroup(
            PnlExplicacionInaccesibilidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnlExplicacionInaccesibilidadLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        PnlExplicacionInaccesibilidadLayout.setVerticalGroup(
            PnlExplicacionInaccesibilidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlExplicacionInaccesibilidadLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnTerminar.setText("Terminar");
        btnTerminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTerminarActionPerformed(evt);
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnTerminar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(341, 341, 341))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(PnlExplicacionInaccesibilidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PnlCoordenadasExterior, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(PnlIUPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PnlTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PnlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PnlIUPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PnlCoordenadasExterior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PnlExplicacionInaccesibilidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTerminar)
                    .addComponent(btnSalir))
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

    private void btnTerminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTerminarActionPerformed
        if (validarCamposObligatorios() && validarDatos() && validarTipoUPM()) {
            crearUpmInaccesible();//modifica la tabla de conglomerado en el UPM recibido
            this.hide();
            funciones.manipularBotonesMenuPrincipal(false);
        }
    }//GEN-LAST:event_btnTerminarActionPerformed

    private void txtGradosLatitudFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGradosLatitudFocusGained
       txtGradosLatitud.selectAll();
    }//GEN-LAST:event_txtGradosLatitudFocusGained

    private void txtMinutosLatitudFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMinutosLatitudFocusGained
       txtMinutosLatitud.selectAll();
    }//GEN-LAST:event_txtMinutosLatitudFocusGained

    private void txtSegundosLatitudFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSegundosLatitudFocusGained
       txtSegundosLatitud.selectAll();
    }//GEN-LAST:event_txtSegundosLatitudFocusGained

    private void txtGradosLongitudFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGradosLongitudFocusGained
       txtGradosLongitud.selectAll();
    }//GEN-LAST:event_txtGradosLongitudFocusGained

    private void txtMinutosLongitudFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMinutosLongitudFocusGained
       txtMinutosLongitud.selectAll();
    }//GEN-LAST:event_txtMinutosLongitudFocusGained

    private void txtSegundosLongitudFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSegundosLongitudFocusGained
       txtSegundosLongitud.selectAll();
    }//GEN-LAST:event_txtSegundosLongitudFocusGained

    private void txtEPEFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEPEFocusGained
        txtEPE.selectAll();
    }//GEN-LAST:event_txtEPEFocusGained

    private void txtAzimutFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAzimutFocusGained
        txtAzimut.selectAll();
    }//GEN-LAST:event_txtAzimutFocusGained

    private void txtDistanciaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDistanciaFocusGained
        txtDistancia.selectAll();
    }//GEN-LAST:event_txtDistanciaFocusGained

    private void txtOtroTipoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtOtroTipoFocusGained
        txtOtroTipo.selectAll();
    }//GEN-LAST:event_txtOtroTipoFocusGained

    private void txtExplicacionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtExplicacionFocusGained
        txtExplicacion.selectAll();
    }//GEN-LAST:event_txtExplicacionFocusGained

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.hide();
        funciones.manipularBotonesMenuPrincipal(false);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtGradosLatitudFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGradosLatitudFocusLost
       if(txtGradosLatitud.getText().isEmpty()){
            txtGradosLatitud.setValue(null);
       }        
    }//GEN-LAST:event_txtGradosLatitudFocusLost

    private void txtMinutosLatitudFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMinutosLatitudFocusLost
        if(txtMinutosLatitud.getText().isEmpty()){
            txtMinutosLatitud.setValue(null);
        }          
    }//GEN-LAST:event_txtMinutosLatitudFocusLost

    private void txtSegundosLatitudFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSegundosLatitudFocusLost
        if(txtSegundosLatitud.getText().isEmpty()){
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
       if(txtMinutosLongitud.getText().isEmpty()){
           txtMinutosLongitud.setValue(null);
       }
    }//GEN-LAST:event_txtMinutosLongitudFocusLost

    private void txtSegundosLongitudFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSegundosLongitudFocusLost
        if(txtSegundosLongitud.getText().isEmpty()){
            txtSegundosLatitud.setValue(null);
        }          
    }//GEN-LAST:event_txtSegundosLongitudFocusLost

    private void txtEPEFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEPEFocusLost
        if(txtEPE.getText().isEmpty()){
            txtEPE.setValue(null);
        }
    }//GEN-LAST:event_txtEPEFocusLost

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

    private void txtExplicacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtExplicacionKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            evt.consume();
        }
    }//GEN-LAST:event_txtExplicacionKeyPressed

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

    private void txtEPEKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEPEKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtEPEKeyTyped

    private void txtAzimutKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAzimutKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtAzimutKeyTyped

    private void txtDistanciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDistanciaKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtDistanciaKeyTyped

    private void cmbTipoInaccesibilidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoInaccesibilidadActionPerformed
        if (cmbTipoInaccesibilidad.getSelectedItem() != null) {
            CatETipoInaccesibilidad inaccesibilidad = (CatETipoInaccesibilidad) cmbTipoInaccesibilidad.getSelectedItem();
            if (inaccesibilidad.getTipoInaccesibilidadID() == 9) {
                txtOtroTipo.setEnabled(true);
            } else {
                txtOtroTipo.setEnabled(false);
            }
        }
    }//GEN-LAST:event_cmbTipoInaccesibilidadActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PnlCoordenadasExterior;
    private javax.swing.JPanel PnlCoordenadasInterior;
    private javax.swing.JPanel PnlExplicacionInaccesibilidad;
    private javax.swing.JPanel PnlIUPM;
    private javax.swing.JPanel PnlTitulo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnTerminar;
    private javax.swing.JComboBox cmbTipoInaccesibilidad;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAzimut;
    private javax.swing.JLabel lblComplementaria;
    private javax.swing.JLabel lblDatum;
    private javax.swing.JLabel lblDistancia;
    private javax.swing.JLabel lblEPE;
    private javax.swing.JLabel lblGradosLatitud;
    private javax.swing.JLabel lblGradosLongitud;
    private javax.swing.JLabel lblLatitud;
    private javax.swing.JLabel lblLongitud;
    private javax.swing.JLabel lblMinutosLatitud;
    private javax.swing.JLabel lblMinutosLongitud;
    private javax.swing.JLabel lblOtro;
    private javax.swing.JLabel lblSegundosLatitud;
    private javax.swing.JLabel lblSegundosLongitud;
    private javax.swing.JLabel lblTipoInaccesibilidad;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblUPMID;
    private javax.swing.JFormattedTextField txtAzimut;
    private javax.swing.JTextField txtDatum;
    private javax.swing.JFormattedTextField txtDistancia;
    private javax.swing.JFormattedTextField txtEPE;
    private javax.swing.JTextArea txtExplicacion;
    private javax.swing.JFormattedTextField txtGradosLatitud;
    private javax.swing.JFormattedTextField txtGradosLongitud;
    private javax.swing.JFormattedTextField txtMinutosLatitud;
    private javax.swing.JFormattedTextField txtMinutosLongitud;
    private javax.swing.JTextField txtOtroTipo;
    private javax.swing.JFormattedTextField txtSegundosLatitud;
    private javax.swing.JFormattedTextField txtSegundosLongitud;
    private javax.swing.JTextField txtUPM;
    // End of variables declaration//GEN-END:variables
}
