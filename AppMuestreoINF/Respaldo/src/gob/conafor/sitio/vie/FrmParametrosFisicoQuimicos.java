package gob.conafor.sitio.vie;

import gob.conafor.sitio.mod.CDParametrosFisicoQuimicos;
import gob.conafor.sitio.mod.CEParametrosFisicoQuimicos;
import gob.conafor.sitio.mod.CESitio;
import gob.conafor.sys.mod.CDSecuencia;
import gob.conafor.sys.mod.CDSeguimientoUPM;
import gob.conafor.sys.mod.CESeccionesCapturadas;
import gob.conafor.upm.vie.UPMForms;
import gob.conafor.utils.Datos;
import gob.conafor.utils.FuncionesComunes;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class FrmParametrosFisicoQuimicos extends javax.swing.JInternalFrame {
    
    private int upmID;
    private int sitioID;
    private int sitio;
    private int parametrosFQ;
    private int repobladoVM;
    private static final int FORMATO_ID = 18;
    private CESitio ceSitio = new CESitio();
    private CEParametrosFisicoQuimicos ceParametro = new CEParametrosFisicoQuimicos();
    private CDParametrosFisicoQuimicos cdParametro = new CDParametrosFisicoQuimicos();
    private Float salinidad;
    private Float temperatura;
    private Float conductividadElectrica;
    private Float Ph;
    private Float potencialRedox;
    private Float profundidad;
    private int tipoAgua;
    private String observaciones;
    private CESeccionesCapturadas seccionActual = new CESeccionesCapturadas();
    private CESeccionesCapturadas seccionSiguiente = new CESeccionesCapturadas();
    private CDSeguimientoUPM seguimiento = new CDSeguimientoUPM();
    private Datos numeros = new Datos();
    private CDSecuencia cdSecuencia = new CDSecuencia();
    private int actualizar;
    private FuncionesComunes funciones = new FuncionesComunes();
    
    public FrmParametrosFisicoQuimicos() {
        initComponents();
    }
    
    public void setDatosiniciales(CESitio ceSitio) {
        this.upmID = ceSitio.getUpmID();
        this.sitioID = ceSitio.getSitioID();
        this.sitio = ceSitio.getSitio();
        this.txtUPM.setText(String.valueOf(this.upmID));
        this.txtSitio.setText(String.valueOf(this.sitio));
        this.ceSitio.setSitioID(this.sitioID);
        this.ceSitio.setUpmID(this.upmID);
        this.ceSitio.setSitio(this.sitio);
        this.parametrosFQ = 28;
        this.repobladoVM = 29;
        txtSalinidadAguaSuperficial.requestFocus();
        this.ceSitio = ceSitio;
        this.actualizar = 0;
        funciones.manipularBotonesMenuPrincipal(true);
        limpiarControles();
    }
    
    public void continuarParametrosFQ(CESitio ceSitio) {
        this.upmID = ceSitio.getUpmID();
        this.sitioID = ceSitio.getSitioID();
        this.sitio = ceSitio.getSitio();
        this.txtUPM.setText(String.valueOf(this.upmID));
        this.txtSitio.setText(String.valueOf(this.sitio));
        this.ceSitio.setSitioID(this.sitioID);
        this.ceSitio.setUpmID(this.upmID);
        this.ceSitio.setSitio(this.sitio);
        this.parametrosFQ = 28;
        this.repobladoVM = 29;
        this.actualizar = 1;
        txtSalinidadAguaSuperficial.requestFocus();
        funciones.manipularBotonesMenuPrincipal(true);
    }
    
    private void limpiarControles(){
        rbtAguasuperficial.setSelected(true);
        rbtAguaIntersticial.setSelected(false);
        txtSalinidadAguaSuperficial.setValue(null);
        txtSalinidadAguaSuperficial.setText("");
        txtTemperaturaAguaSuperficial.setValue(null);
        txtTemperaturaAguaSuperficial.setText("");
        txtConductividadAguaSuperficial.setValue(null);
        txtConductividadAguaSuperficial.setText("");
        txtPHAguaSuperficial.setValue(null);
        txtPHAguaSuperficial.setText("");
        txtPotencialAguaSuperficial.setValue(null);
        txtPotencialAguaSuperficial.setText("");
        txtProfundidadAguaSuperficial.setValue(null);
        txtProfundidadAguaSuperficial.setText("");
        
        txtSalinidadAguaIntersticial.setValue(null);
        txtSalinidadAguaIntersticial.setText("");
        txtSalinidadAguaIntersticial.setEnabled(false);
        txtTemperaturaAguaIntersticial.setValue(null);
        txtTemperaturaAguaIntersticial.setText("");
        txtTemperaturaAguaIntersticial.setEnabled(false);
        txtConductividadAguaIntersticial.setValue(null);
        txtConductividadAguaIntersticial.setText("");
        txtConductividadAguaIntersticial.setEnabled(false);
        txtPHAguaIntersticial.setValue(null);
        txtPHAguaIntersticial.setText("");
        txtPHAguaIntersticial.setEnabled(false);
        txtPotencialAguaIntersticial.setValue(null);
        txtPotencialAguaIntersticial.setText("");
        txtPotencialAguaIntersticial.setEnabled(false);
        txtProfundidadAguaIntersticial.setValue(null);
        txtProfundidadAguaIntersticial.setText("");
        txtProfundidadAguaIntersticial.setEnabled(false);
        txtObservaciones.setText("");
    }
    
    private boolean validarCamposParametrosFQ() {
        if (rbtAguasuperficial.isSelected()) {
            if (txtSalinidadAguaSuperficial.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe proporcionar salinidad de agua superficial "
                        + "", "Parámetros físico químicos", JOptionPane.INFORMATION_MESSAGE);
                txtSalinidadAguaSuperficial.requestFocus();
                return false;
            } else if (txtTemperaturaAguaSuperficial.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe proporcionar temperatura agua superficial "
                        + "", "Parámetros físico químicos", JOptionPane.INFORMATION_MESSAGE);
                txtTemperaturaAguaSuperficial.requestFocus();
                return false;
            } else if (txtConductividadAguaSuperficial.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe proporcionar conductividad electrica agua superficial "
                        + "", "Parámetros físico químicos", JOptionPane.INFORMATION_MESSAGE);
                txtConductividadAguaSuperficial.requestFocus();
                return false;
            } else if (txtPHAguaSuperficial.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe proporcionar PH de agua superficial "
                        + "", "Parámetros físico químicos", JOptionPane.INFORMATION_MESSAGE);
                txtPHAguaSuperficial.requestFocus();
                return false;
            } else if (txtPotencialAguaSuperficial.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe proporcionar potencial redox de agua superficial "
                        + "", "Parámetros físico químicos", JOptionPane.INFORMATION_MESSAGE);
                txtPotencialAguaSuperficial.requestFocus();
                return false;
            } else if (txtProfundidadAguaSuperficial.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe proporcionar la profundidad de agua superficial "
                        + "", "Parámetros físico químicos", JOptionPane.INFORMATION_MESSAGE);
                txtProfundidadAguaSuperficial.requestFocus();
                return false;
            } else {
                return true;
            }
        } else if (rbtAguaIntersticial.isSelected()) {
            if (txtSalinidadAguaIntersticial.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe proporcionar salinidad de agua intersticial "
                        + "", "Parámetros físico químicos", JOptionPane.INFORMATION_MESSAGE);
                txtSalinidadAguaIntersticial.requestFocus();
                return false;
            } else if (txtTemperaturaAguaIntersticial.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe proporcionar temperatura agua Intersticial "
                        + "", "Parámetros físico químicos", JOptionPane.INFORMATION_MESSAGE);
                txtTemperaturaAguaIntersticial.requestFocus();
                return false;
            } else if (txtConductividadAguaIntersticial.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe proporcionar conductividad electrica agua Intersticial "
                        + "", "Parámetros físico químicos", JOptionPane.INFORMATION_MESSAGE);
                txtConductividadAguaIntersticial.requestFocus();
                return false;
            } else if (txtPHAguaIntersticial.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe proporcionar PH de agua intersticial "
                        + "", "Parámetros físico químicos", JOptionPane.INFORMATION_MESSAGE);
                txtPHAguaIntersticial.requestFocus();
                return false;
            } else if (txtPotencialAguaIntersticial.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe proporcionar potencial redox de agua intersticial "
                        + "", "Parámetros físico químicos", JOptionPane.INFORMATION_MESSAGE);
                txtPotencialAguaIntersticial.requestFocus();
                return false;
            } else if (txtProfundidadAguaIntersticial.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe proporcionar la profundidad de agua intersticial "
                        + "", "Parámetros físico químicos", JOptionPane.INFORMATION_MESSAGE);
                txtProfundidadAguaIntersticial.requestFocus();
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
    
    private void fijarDatosParametros() {
        if (rbtAguasuperficial.isSelected()) {
            this.tipoAgua = 1;
        } else if (rbtAguaIntersticial.isSelected()) {
            this.tipoAgua = 0;
        }

        if (this.tipoAgua == 1) {
            try {
                this.salinidad = Float.valueOf(txtSalinidadAguaSuperficial.getText());
            } catch (NumberFormatException e) {
                this.salinidad = null;
            }

            try {
                this.temperatura = Float.valueOf(txtTemperaturaAguaSuperficial.getText());
            } catch (NumberFormatException e) {
                this.temperatura = null;
            }

            try {
                this.conductividadElectrica = Float.valueOf(txtConductividadAguaSuperficial.getText());
            } catch (NumberFormatException e) {
                this.conductividadElectrica = null;
            }

            try {
                this.Ph = Float.valueOf(txtPHAguaSuperficial.getText());
            } catch (NumberFormatException e) {
                this.Ph = null;
            }

            try {
                this.potencialRedox = Float.valueOf(txtPotencialAguaSuperficial.getText());
            } catch (NumberFormatException e) {
                this.potencialRedox = null;
            }

            try {
                this.profundidad = Float.valueOf(txtProfundidadAguaSuperficial.getText());
            } catch (NumberFormatException e) {
                this.profundidad = null;
            }
        } else {

            try {
                this.salinidad = Float.valueOf(txtSalinidadAguaIntersticial.getText());
            } catch (NumberFormatException e) {
                this.salinidad = null;
            }

            try {
                this.temperatura = Float.valueOf(txtTemperaturaAguaIntersticial.getText());
            } catch (NumberFormatException e) {
                this.temperatura = null;
            }

            try {
                this.conductividadElectrica = Float.valueOf(txtConductividadAguaIntersticial.getText());
            } catch (NumberFormatException e) {
                this.conductividadElectrica = null;
            }

            try {
                this.Ph = Float.valueOf(txtPHAguaIntersticial.getText());
            } catch (NumberFormatException e) {
                this.Ph = null;
            }

            try {
                this.potencialRedox = Float.valueOf(txtPotencialAguaIntersticial.getText());
            } catch (NumberFormatException e) {
                this.potencialRedox = null;
            }

            try {
                this.profundidad = Float.valueOf(txtProfundidadAguaIntersticial.getText());
            } catch (NumberFormatException e) {
                this.profundidad = null;
            }
        }
        this.observaciones = txtObservaciones.getText();
    }
    
    private void crearParametro() {
        this.ceParametro.setSitioID(this.sitioID);
        this.ceParametro.setTipoAgua(this.tipoAgua);
        this.ceParametro.setSalinidad(this.salinidad);
        this.ceParametro.setTemperaturaAgua(this.temperatura);
        this.ceParametro.setConductividadElectrica(this.conductividadElectrica);
        this.ceParametro.setPh(this.Ph);
        this.ceParametro.setPotencialRedox(this.potencialRedox);
        this.ceParametro.setProfundidad(this.profundidad);
        this.ceParametro.setObservaciones(this.observaciones);
        
        this.cdParametro.inserParametrosFQ(ceParametro);
    }
    
    private void actualizarParametro() {
        this.ceParametro.setSitioID(this.sitioID);
        this.ceParametro.setTipoAgua(this.tipoAgua);
        this.ceParametro.setSalinidad(this.salinidad);
        this.ceParametro.setTemperaturaAgua(this.temperatura);
        this.ceParametro.setConductividadElectrica(this.conductividadElectrica);
        this.ceParametro.setPh(this.Ph);
        this.ceParametro.setPotencialRedox(this.potencialRedox);
        this.ceParametro.setProfundidad(this.profundidad);
        this.ceParametro.setObservaciones(this.observaciones);
        
        this.cdParametro.updateParametrosFQ(ceParametro);
    }
    
    private void eliminarParametro() {
        this.cdParametro.deleteParametrosFQ(this.sitioID);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rbgTipoAgua = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        lblUPM = new javax.swing.JLabel();
        txtUPM = new javax.swing.JTextField();
        lblHojarascaFermentacion = new javax.swing.JLabel();
        lblSitio = new javax.swing.JLabel();
        txtSitio = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        btnContinuar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        rbtAguasuperficial = new javax.swing.JRadioButton();
        rbtAguaIntersticial = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        lblSalinidadSuperficial = new javax.swing.JLabel();
        lblTemperaturaSuperficial = new javax.swing.JLabel();
        lblConductividadSuperficial = new javax.swing.JLabel();
        lblPHSuperficial = new javax.swing.JLabel();
        lblPotencialSuperficial = new javax.swing.JLabel();
        lblProfundidadSuperficial = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtSalinidadAguaSuperficial = new javax.swing.JFormattedTextField();
        txtTemperaturaAguaSuperficial = new javax.swing.JFormattedTextField();
        txtConductividadAguaSuperficial = new javax.swing.JFormattedTextField();
        txtPHAguaSuperficial = new javax.swing.JFormattedTextField();
        txtPotencialAguaSuperficial = new javax.swing.JFormattedTextField();
        txtProfundidadAguaSuperficial = new javax.swing.JFormattedTextField();
        jPanel5 = new javax.swing.JPanel();
        lblSalinidadIntersticial = new javax.swing.JLabel();
        lblTemperaturaIntersticial = new javax.swing.JLabel();
        lblConductividadIntersticial = new javax.swing.JLabel();
        lblPHIntersticial = new javax.swing.JLabel();
        lblPotencialIntersticial = new javax.swing.JLabel();
        lblProfundidadIntersticial = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        txtSalinidadAguaIntersticial = new javax.swing.JFormattedTextField();
        txtTemperaturaAguaIntersticial = new javax.swing.JFormattedTextField();
        txtConductividadAguaIntersticial = new javax.swing.JFormattedTextField();
        txtPHAguaIntersticial = new javax.swing.JFormattedTextField();
        txtPotencialAguaIntersticial = new javax.swing.JFormattedTextField();
        txtProfundidadAguaIntersticial = new javax.swing.JFormattedTextField();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObservaciones = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Parametros fisico quimicos");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/gob/conafor/utils/logo_conafor.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(940, 650));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setPreferredSize(new java.awt.Dimension(940, 650));

        lblUPM.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblUPM.setText("UPMID:");

        txtUPM.setEditable(false);
        txtUPM.setEnabled(false);

        lblHojarascaFermentacion.setBackground(new java.awt.Color(153, 153, 153));
        lblHojarascaFermentacion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblHojarascaFermentacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHojarascaFermentacion.setText("Parámetros físico-químicos");
        lblHojarascaFermentacion.setOpaque(true);

        lblSitio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblSitio.setText("Sitio:");

        txtSitio.setEditable(false);
        txtSitio.setEnabled(false);

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(347, 347, 347)
                .addComponent(btnContinuar)
                .addGap(18, 18, 18)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalir)
                    .addComponent(btnContinuar))
                .addGap(19, 19, 19))
        );

        rbtAguasuperficial.setBackground(new java.awt.Color(204, 204, 204));
        rbgTipoAgua.add(rbtAguasuperficial);
        rbtAguasuperficial.setSelected(true);
        rbtAguasuperficial.setText("Agua superficial");
        rbtAguasuperficial.setNextFocusableComponent(txtSalinidadAguaSuperficial);
        rbtAguasuperficial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtAguasuperficialActionPerformed(evt);
            }
        });

        rbtAguaIntersticial.setBackground(new java.awt.Color(204, 204, 204));
        rbgTipoAgua.add(rbtAguaIntersticial);
        rbtAguaIntersticial.setText("Agua intersticial");
        rbtAguaIntersticial.setNextFocusableComponent(txtSalinidadAguaIntersticial);
        rbtAguaIntersticial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtAguaIntersticialActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        lblSalinidadSuperficial.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSalinidadSuperficial.setText("Salinidad (0/100) (ppm)");
        lblSalinidadSuperficial.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblTemperaturaSuperficial.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTemperaturaSuperficial.setText("Temperatura agua (°C)");
        lblTemperaturaSuperficial.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblConductividadSuperficial.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblConductividadSuperficial.setText("Conductividad electrica (mS/cm)");
        lblConductividadSuperficial.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblPHSuperficial.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPHSuperficial.setText("PH");
        lblPHSuperficial.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblPotencialSuperficial.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPotencialSuperficial.setText("Potencial redox (Eh)");
        lblPotencialSuperficial.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblProfundidadSuperficial.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProfundidadSuperficial.setText("Profundidad (cm)");
        lblProfundidadSuperficial.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(lblSalinidadSuperficial, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTemperaturaSuperficial, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblConductividadSuperficial, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPHSuperficial, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPotencialSuperficial, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblProfundidadSuperficial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTemperaturaSuperficial, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
            .addComponent(lblConductividadSuperficial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblPHSuperficial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblPotencialSuperficial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblProfundidadSuperficial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblSalinidadSuperficial, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        txtSalinidadAguaSuperficial.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSalinidadAguaSuperficialFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSalinidadAguaSuperficialFocusLost(evt);
            }
        });
        txtSalinidadAguaSuperficial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSalinidadAguaSuperficialKeyTyped(evt);
            }
        });

        txtTemperaturaAguaSuperficial.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTemperaturaAguaSuperficialFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTemperaturaAguaSuperficialFocusLost(evt);
            }
        });
        txtTemperaturaAguaSuperficial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTemperaturaAguaSuperficialKeyTyped(evt);
            }
        });

        txtConductividadAguaSuperficial.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtConductividadAguaSuperficialFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtConductividadAguaSuperficialFocusLost(evt);
            }
        });
        txtConductividadAguaSuperficial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtConductividadAguaSuperficialKeyTyped(evt);
            }
        });

        txtPHAguaSuperficial.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPHAguaSuperficialFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPHAguaSuperficialFocusLost(evt);
            }
        });
        txtPHAguaSuperficial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPHAguaSuperficialKeyTyped(evt);
            }
        });

        txtPotencialAguaSuperficial.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPotencialAguaSuperficialFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPotencialAguaSuperficialFocusLost(evt);
            }
        });
        txtPotencialAguaSuperficial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPotencialAguaSuperficialKeyTyped(evt);
            }
        });

        txtProfundidadAguaSuperficial.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtProfundidadAguaSuperficialFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtProfundidadAguaSuperficialFocusLost(evt);
            }
        });
        txtProfundidadAguaSuperficial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtProfundidadAguaSuperficialKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(txtSalinidadAguaSuperficial, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTemperaturaAguaSuperficial, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtConductividadAguaSuperficial, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPHAguaSuperficial, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPotencialAguaSuperficial, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtProfundidadAguaSuperficial))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSalinidadAguaSuperficial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTemperaturaAguaSuperficial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtConductividadAguaSuperficial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPHAguaSuperficial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPotencialAguaSuperficial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProfundidadAguaSuperficial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));

        lblSalinidadIntersticial.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSalinidadIntersticial.setText("Salinidad (0/100) (ppm)");
        lblSalinidadIntersticial.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblTemperaturaIntersticial.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTemperaturaIntersticial.setText("Temperatura agua (°C)");
        lblTemperaturaIntersticial.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblConductividadIntersticial.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblConductividadIntersticial.setText("Conductividad electrica (mS/cm)");
        lblConductividadIntersticial.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblPHIntersticial.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPHIntersticial.setText("PH");
        lblPHIntersticial.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblPotencialIntersticial.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPotencialIntersticial.setText("Potencial redox (Eh)");
        lblPotencialIntersticial.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblProfundidadIntersticial.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProfundidadIntersticial.setText("Profundidad (cm)");
        lblProfundidadIntersticial.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(lblSalinidadIntersticial, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTemperaturaIntersticial, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblConductividadIntersticial, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPHIntersticial, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPotencialIntersticial, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblProfundidadIntersticial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTemperaturaIntersticial, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
            .addComponent(lblConductividadIntersticial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblPHIntersticial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblPotencialIntersticial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblProfundidadIntersticial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblSalinidadIntersticial, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));

        txtSalinidadAguaIntersticial.setEnabled(false);
        txtSalinidadAguaIntersticial.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSalinidadAguaIntersticialFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSalinidadAguaIntersticialFocusLost(evt);
            }
        });
        txtSalinidadAguaIntersticial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSalinidadAguaIntersticialKeyTyped(evt);
            }
        });

        txtTemperaturaAguaIntersticial.setEnabled(false);
        txtTemperaturaAguaIntersticial.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTemperaturaAguaIntersticialFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTemperaturaAguaIntersticialFocusLost(evt);
            }
        });
        txtTemperaturaAguaIntersticial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTemperaturaAguaIntersticialKeyTyped(evt);
            }
        });

        txtConductividadAguaIntersticial.setEnabled(false);
        txtConductividadAguaIntersticial.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtConductividadAguaIntersticialFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtConductividadAguaIntersticialFocusLost(evt);
            }
        });
        txtConductividadAguaIntersticial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtConductividadAguaIntersticialKeyTyped(evt);
            }
        });

        txtPHAguaIntersticial.setEnabled(false);
        txtPHAguaIntersticial.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPHAguaIntersticialFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPHAguaIntersticialFocusLost(evt);
            }
        });
        txtPHAguaIntersticial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPHAguaIntersticialKeyTyped(evt);
            }
        });

        txtPotencialAguaIntersticial.setEnabled(false);
        txtPotencialAguaIntersticial.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPotencialAguaIntersticialFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPotencialAguaIntersticialFocusLost(evt);
            }
        });
        txtPotencialAguaIntersticial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPotencialAguaIntersticialKeyTyped(evt);
            }
        });

        txtProfundidadAguaIntersticial.setEnabled(false);
        txtProfundidadAguaIntersticial.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtProfundidadAguaIntersticialFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtProfundidadAguaIntersticialFocusLost(evt);
            }
        });
        txtProfundidadAguaIntersticial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtProfundidadAguaIntersticialKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(txtSalinidadAguaIntersticial, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTemperaturaAguaIntersticial, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtConductividadAguaIntersticial, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPHAguaIntersticial, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPotencialAguaIntersticial, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtProfundidadAguaIntersticial))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSalinidadAguaIntersticial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTemperaturaAguaIntersticial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtConductividadAguaIntersticial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPHAguaIntersticial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPotencialAguaIntersticial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProfundidadAguaIntersticial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(204, 204, 204));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Observaciones"));

        txtObservaciones.setColumns(20);
        txtObservaciones.setRows(5);
        txtObservaciones.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtObservacionesFocusGained(evt);
            }
        });
        txtObservaciones.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtObservacionesKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(txtObservaciones);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblUPM)
                                .addGap(4, 4, 4)
                                .addComponent(txtUPM, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(614, 614, 614)
                                .addComponent(lblSitio)
                                .addGap(8, 8, 8)
                                .addComponent(txtSitio, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblHojarascaFermentacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rbtAguasuperficial)
                                .addGap(19, 19, 19)
                                .addComponent(rbtAguaIntersticial))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(14, 14, 14))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(lblUPM))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(txtUPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblSitio)
                    .addComponent(txtSitio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(lblHojarascaFermentacion)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbtAguasuperficial)
                    .addComponent(rbtAguaIntersticial))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 924, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rbtAguasuperficialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtAguasuperficialActionPerformed
        if (rbtAguasuperficial.isSelected()) {
            txtSalinidadAguaSuperficial.setEnabled(true);
            txtTemperaturaAguaSuperficial.setEnabled(true);
            txtConductividadAguaSuperficial.setEnabled(true);
            txtPHAguaSuperficial.setEnabled(true);
            txtPotencialAguaSuperficial.setEnabled(true);
            txtProfundidadAguaSuperficial.setEnabled(true);
            
            txtSalinidadAguaIntersticial.setEnabled(false);
            txtSalinidadAguaIntersticial.setText("");
            txtSalinidadAguaIntersticial.setValue(null);
            txtTemperaturaAguaIntersticial.setEnabled(false);
            txtTemperaturaAguaIntersticial.setText("");
            txtTemperaturaAguaIntersticial.setValue(null);
            txtConductividadAguaIntersticial.setEnabled(false);
            txtConductividadAguaIntersticial.setText("");
            txtConductividadAguaIntersticial.setValue("");
            txtPHAguaIntersticial.setEnabled(false);
            txtPHAguaIntersticial.setText("");
            txtPHAguaIntersticial.setValue(null);
            txtPotencialAguaIntersticial.setEnabled(false);
            txtPotencialAguaIntersticial.setText("");
            txtPotencialAguaIntersticial.setValue(null);
            txtProfundidadAguaIntersticial.setEnabled(false);
            txtProfundidadAguaIntersticial.setText("");
            txtProfundidadAguaIntersticial.setValue(null);
            
            txtSalinidadAguaSuperficial.requestFocus();
        }        
        
    }//GEN-LAST:event_rbtAguasuperficialActionPerformed

    private void rbtAguaIntersticialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtAguaIntersticialActionPerformed
        if (rbtAguaIntersticial.isSelected()) {
            txtSalinidadAguaSuperficial.setEnabled(false);
            txtSalinidadAguaSuperficial.setText("");
            txtSalinidadAguaSuperficial.setValue(null);
            txtTemperaturaAguaSuperficial.setEnabled(false);
            txtTemperaturaAguaSuperficial.setText("");
            txtTemperaturaAguaSuperficial.setValue(null);
            txtConductividadAguaSuperficial.setEnabled(false);
            txtConductividadAguaSuperficial.setText("");
            txtConductividadAguaSuperficial.setValue(null);
            txtPHAguaSuperficial.setEnabled(false);
            txtPHAguaSuperficial.setText("");
            txtPHAguaSuperficial.setValue(null);
            txtPotencialAguaSuperficial.setEnabled(false);
            txtPotencialAguaSuperficial.setText("");
            txtPotencialAguaSuperficial.setValue(null);
            txtProfundidadAguaSuperficial.setEnabled(false);
            txtProfundidadAguaSuperficial.setText("");
            txtProfundidadAguaSuperficial.setValue(null);
            
            txtSalinidadAguaIntersticial.setEnabled(true);
            txtTemperaturaAguaIntersticial.setEnabled(true);
            txtConductividadAguaIntersticial.setEnabled(true);
            txtPHAguaIntersticial.setEnabled(true);
            txtPotencialAguaIntersticial.setEnabled(true);
            txtProfundidadAguaIntersticial.setEnabled(true);
            
            txtSalinidadAguaIntersticial.requestFocus();
        }
    }//GEN-LAST:event_rbtAguaIntersticialActionPerformed

    private void btnContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuarActionPerformed
        fijarDatosParametros();
        if (validarCamposParametrosFQ()) {
            if (actualizar == 0) {
                crearParametro();
                this.hide();
                UPMForms.repobladoVM.setDatosIniciales(this.ceSitio);
                UPMForms.repobladoVM.setVisible(true);
                this.cdSecuencia.updateSecuencia(this.ceSitio, FORMATO_ID, 1);
            } else {
                actualizarParametro();
                this.hide();
                UPMForms.repobladoVM.revisarRepobladoVM(this.ceSitio);
                UPMForms.repobladoVM.setVisible(true);
            }
        }
    }//GEN-LAST:event_btnContinuarActionPerformed

    private void txtSalinidadAguaSuperficialFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSalinidadAguaSuperficialFocusLost
        if (txtSalinidadAguaSuperficial.getText().isEmpty()) {
            txtSalinidadAguaSuperficial.setValue(null);
        }
    }//GEN-LAST:event_txtSalinidadAguaSuperficialFocusLost

    private void txtTemperaturaAguaSuperficialFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTemperaturaAguaSuperficialFocusLost
        if (txtTemperaturaAguaSuperficial.getText().isEmpty()) {
            txtTemperaturaAguaSuperficial.setValue(null);
        }
    }//GEN-LAST:event_txtTemperaturaAguaSuperficialFocusLost

    private void txtConductividadAguaSuperficialFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtConductividadAguaSuperficialFocusLost
        if (txtConductividadAguaSuperficial.getText().isEmpty()) {
            txtConductividadAguaSuperficial.setValue(null);
        }
    }//GEN-LAST:event_txtConductividadAguaSuperficialFocusLost

    private void txtPHAguaSuperficialFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPHAguaSuperficialFocusLost
        if (txtPHAguaSuperficial.getText().isEmpty()) {
            txtPHAguaSuperficial.setValue(null);
        }
    }//GEN-LAST:event_txtPHAguaSuperficialFocusLost

    private void txtPotencialAguaSuperficialFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPotencialAguaSuperficialFocusLost
        if (txtPotencialAguaSuperficial.getText().isEmpty()) {
            txtPotencialAguaSuperficial.setValue(null);
        }
    }//GEN-LAST:event_txtPotencialAguaSuperficialFocusLost

    private void txtProfundidadAguaSuperficialFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtProfundidadAguaSuperficialFocusLost
        if (txtProfundidadAguaSuperficial.getText().isEmpty()) {
            txtProfundidadAguaSuperficial.setValue(null);
        }
    }//GEN-LAST:event_txtProfundidadAguaSuperficialFocusLost

    private void txtSalinidadAguaIntersticialFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSalinidadAguaIntersticialFocusLost
        if (txtSalinidadAguaIntersticial.getText().isEmpty()) {
            txtSalinidadAguaIntersticial.setValue(null);
        }
    }//GEN-LAST:event_txtSalinidadAguaIntersticialFocusLost

    private void txtTemperaturaAguaIntersticialFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTemperaturaAguaIntersticialFocusLost
        if (txtTemperaturaAguaIntersticial.getText().isEmpty()) {
            txtTemperaturaAguaIntersticial.setValue(null);
        }
    }//GEN-LAST:event_txtTemperaturaAguaIntersticialFocusLost

    private void txtConductividadAguaIntersticialFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtConductividadAguaIntersticialFocusLost
        if (txtConductividadAguaIntersticial.getText().isEmpty()) {
            txtConductividadAguaIntersticial.setValue(null);
        }
    }//GEN-LAST:event_txtConductividadAguaIntersticialFocusLost

    private void txtPHAguaIntersticialFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPHAguaIntersticialFocusLost
        if (txtPHAguaIntersticial.getText().isEmpty()) {
            txtPHAguaIntersticial.setValue(null);
        }
    }//GEN-LAST:event_txtPHAguaIntersticialFocusLost

    private void txtPotencialAguaIntersticialFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPotencialAguaIntersticialFocusLost
        if (txtPotencialAguaIntersticial.getText().isEmpty()) {
            txtPotencialAguaIntersticial.setValue(null);
        }
    }//GEN-LAST:event_txtPotencialAguaIntersticialFocusLost

    private void txtProfundidadAguaIntersticialFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtProfundidadAguaIntersticialFocusLost
        if (txtProfundidadAguaIntersticial.getText().isEmpty()) {
            txtProfundidadAguaIntersticial.setValue(null);
        }
    }//GEN-LAST:event_txtProfundidadAguaIntersticialFocusLost

    private void txtSalinidadAguaSuperficialFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSalinidadAguaSuperficialFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtSalinidadAguaSuperficial.selectAll();
            }
        });
    }//GEN-LAST:event_txtSalinidadAguaSuperficialFocusGained

    private void txtTemperaturaAguaSuperficialFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTemperaturaAguaSuperficialFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtTemperaturaAguaSuperficial.selectAll();
            }
        });
    }//GEN-LAST:event_txtTemperaturaAguaSuperficialFocusGained

    private void txtConductividadAguaSuperficialFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtConductividadAguaSuperficialFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtConductividadAguaSuperficial.selectAll();
            }
        });
    }//GEN-LAST:event_txtConductividadAguaSuperficialFocusGained

    private void txtPHAguaSuperficialFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPHAguaSuperficialFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtPHAguaSuperficial.selectAll();
            }
        });
    }//GEN-LAST:event_txtPHAguaSuperficialFocusGained

    private void txtPotencialAguaSuperficialFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPotencialAguaSuperficialFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtPotencialAguaSuperficial.selectAll();
            }
        });
    }//GEN-LAST:event_txtPotencialAguaSuperficialFocusGained

    private void txtProfundidadAguaSuperficialFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtProfundidadAguaSuperficialFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtProfundidadAguaSuperficial.selectAll();
            }
        });
    }//GEN-LAST:event_txtProfundidadAguaSuperficialFocusGained

    private void txtSalinidadAguaIntersticialFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSalinidadAguaIntersticialFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtSalinidadAguaIntersticial.selectAll();
            }
        });
    }//GEN-LAST:event_txtSalinidadAguaIntersticialFocusGained

    private void txtTemperaturaAguaIntersticialFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTemperaturaAguaIntersticialFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtTemperaturaAguaIntersticial.selectAll();
            }
        });
    }//GEN-LAST:event_txtTemperaturaAguaIntersticialFocusGained

    private void txtConductividadAguaIntersticialFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtConductividadAguaIntersticialFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtConductividadAguaIntersticial.selectAll();
            }
        });
    }//GEN-LAST:event_txtConductividadAguaIntersticialFocusGained

    private void txtPHAguaIntersticialFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPHAguaIntersticialFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtPHAguaIntersticial.selectAll();
            }
        });
    }//GEN-LAST:event_txtPHAguaIntersticialFocusGained

    private void txtPotencialAguaIntersticialFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPotencialAguaIntersticialFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtPotencialAguaIntersticial.selectAll();
            }
        });
    }//GEN-LAST:event_txtPotencialAguaIntersticialFocusGained

    private void txtProfundidadAguaIntersticialFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtProfundidadAguaIntersticialFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtProfundidadAguaIntersticial.selectAll();
            }
        });
        
    }//GEN-LAST:event_txtProfundidadAguaIntersticialFocusGained

    private void txtObservacionesFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtObservacionesFocusGained
        txtObservaciones.selectAll();
    }//GEN-LAST:event_txtObservacionesFocusGained

    private void txtObservacionesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtObservacionesKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB){
            evt.consume();
        }
    }//GEN-LAST:event_txtObservacionesKeyPressed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.hide();
        funciones.manipularBotonesMenuPrincipal(false);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtSalinidadAguaSuperficialKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSalinidadAguaSuperficialKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtSalinidadAguaSuperficialKeyTyped

    private void txtTemperaturaAguaSuperficialKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTemperaturaAguaSuperficialKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtTemperaturaAguaSuperficialKeyTyped

    private void txtConductividadAguaSuperficialKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConductividadAguaSuperficialKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtConductividadAguaSuperficialKeyTyped

    private void txtPHAguaSuperficialKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPHAguaSuperficialKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtPHAguaSuperficialKeyTyped

    private void txtPotencialAguaSuperficialKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPotencialAguaSuperficialKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtPotencialAguaSuperficialKeyTyped

    private void txtProfundidadAguaSuperficialKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProfundidadAguaSuperficialKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtProfundidadAguaSuperficialKeyTyped

    private void txtSalinidadAguaIntersticialKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSalinidadAguaIntersticialKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtSalinidadAguaIntersticialKeyTyped

    private void txtTemperaturaAguaIntersticialKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTemperaturaAguaIntersticialKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtTemperaturaAguaIntersticialKeyTyped

    private void txtConductividadAguaIntersticialKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConductividadAguaIntersticialKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtConductividadAguaIntersticialKeyTyped

    private void txtPHAguaIntersticialKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPHAguaIntersticialKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtPHAguaIntersticialKeyTyped

    private void txtPotencialAguaIntersticialKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPotencialAguaIntersticialKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtPotencialAguaIntersticialKeyTyped

    private void txtProfundidadAguaIntersticialKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProfundidadAguaIntersticialKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtProfundidadAguaIntersticialKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnContinuar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblConductividadIntersticial;
    private javax.swing.JLabel lblConductividadSuperficial;
    private javax.swing.JLabel lblHojarascaFermentacion;
    private javax.swing.JLabel lblPHIntersticial;
    private javax.swing.JLabel lblPHSuperficial;
    private javax.swing.JLabel lblPotencialIntersticial;
    private javax.swing.JLabel lblPotencialSuperficial;
    private javax.swing.JLabel lblProfundidadIntersticial;
    private javax.swing.JLabel lblProfundidadSuperficial;
    private javax.swing.JLabel lblSalinidadIntersticial;
    private javax.swing.JLabel lblSalinidadSuperficial;
    private javax.swing.JLabel lblSitio;
    private javax.swing.JLabel lblTemperaturaIntersticial;
    private javax.swing.JLabel lblTemperaturaSuperficial;
    private javax.swing.JLabel lblUPM;
    private javax.swing.ButtonGroup rbgTipoAgua;
    private javax.swing.JRadioButton rbtAguaIntersticial;
    private javax.swing.JRadioButton rbtAguasuperficial;
    private javax.swing.JFormattedTextField txtConductividadAguaIntersticial;
    private javax.swing.JFormattedTextField txtConductividadAguaSuperficial;
    private javax.swing.JTextArea txtObservaciones;
    private javax.swing.JFormattedTextField txtPHAguaIntersticial;
    private javax.swing.JFormattedTextField txtPHAguaSuperficial;
    private javax.swing.JFormattedTextField txtPotencialAguaIntersticial;
    private javax.swing.JFormattedTextField txtPotencialAguaSuperficial;
    private javax.swing.JFormattedTextField txtProfundidadAguaIntersticial;
    private javax.swing.JFormattedTextField txtProfundidadAguaSuperficial;
    private javax.swing.JFormattedTextField txtSalinidadAguaIntersticial;
    private javax.swing.JFormattedTextField txtSalinidadAguaSuperficial;
    private javax.swing.JTextField txtSitio;
    private javax.swing.JFormattedTextField txtTemperaturaAguaIntersticial;
    private javax.swing.JFormattedTextField txtTemperaturaAguaSuperficial;
    private javax.swing.JTextField txtUPM;
    // End of variables declaration//GEN-END:variables
   
}
