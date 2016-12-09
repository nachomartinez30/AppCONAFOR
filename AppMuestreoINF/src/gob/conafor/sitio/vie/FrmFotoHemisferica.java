package gob.conafor.sitio.vie;

import gob.conafor.sitio.mod.CDFotografiaHemisferica;
import gob.conafor.sitio.mod.CEFotografiaHemisferica;
import gob.conafor.sitio.mod.CESitio;
import gob.conafor.sys.mod.CDSecuencia;
import gob.conafor.upm.vie.UPMForms;
import gob.conafor.utils.Datos;
import gob.conafor.utils.FuncionesComunes;
import gob.conafor.utils.Version;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class FrmFotoHemisferica extends javax.swing.JInternalFrame {
    private boolean revision;
    private int upmID;
    private int sitioID;
    private int sitio;
    private static final int FORMATO_ID = 17;
    private CESitio ceSitio = new CESitio();
    private CEFotografiaHemisferica ceFoto = new CEFotografiaHemisferica();
    private CDFotografiaHemisferica cdFoto = new CDFotografiaHemisferica();
    private int coberturaArborea;
    private int tomaFotografia;
    private String hora;
    private Integer declinacioMagnetica;
    private Datos numeros = new Datos();
    private CDSecuencia cdSecuencia = new CDSecuencia();
    private int modificar;
    private FuncionesComunes funciones = new FuncionesComunes();
    private Version ver=new Version();
    private String version=ver.getVersion();
    
    public FrmFotoHemisferica() {
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
        this.ceSitio = ceSitio;
        //cdSecuencia.insertFormatoCapturado(this.ceSitio, FORMATO_ID);
        this.modificar = 0;
        this.funciones.manipularBotonesMenuPrincipal(true);
        limpiarControles();
    }

    public void revisarFotoHemisferica(CESitio ceSitio) {
        revision=true;
        this.upmID = ceSitio.getUpmID();
        this.sitioID = ceSitio.getSitioID();
        this.sitio = ceSitio.getSitio();
        this.txtUPM.setText(String.valueOf(this.upmID));
        this.txtSitio.setText(String.valueOf(this.sitio));
        this.ceSitio.setSitioID(this.sitioID);
        this.ceSitio.setUpmID(this.upmID);
        this.ceSitio.setSitio(this.sitio);
        this.ceFoto = this.cdFoto.getDatosFotografia(this.sitioID);
        if (this.ceFoto.getCoberturaArborea() == 1) {
            rbtCoberturaArboreaSi.setSelected(true);
            rbtCoberturaArboreaNo.setSelected(false);
        } else {
            rbtCoberturaArboreaSi.setSelected(false);
            rbtCoberturaArboreaNo.setSelected(true);
        }
        if (this.ceFoto.getTomaFotografia() == 1) {
            rbtTomaFotografiaSi.setSelected(true);
            rbtTomaFotografiaNo.setSelected(false);
        } else {
            rbtTomaFotografiaSi.setSelected(false);
            rbtTomaFotografiaNo.setSelected(true);
        }
        txtHora.setText(this.ceFoto.getHora());
        txtDeclinacionMagnetica.setText(String.valueOf(this.ceFoto.getDeclinacionMagnetica()));
        this.modificar = 1;
        this.funciones.manipularBotonesMenuPrincipal(true);
    }
    
    private void fijarDatosFotoHemisferica() {
        if (rbtCoberturaArboreaSi.isSelected()) {
            this.coberturaArborea = 1;
        } else {
            this.coberturaArborea = 0;
        }
        if (rbtTomaFotografiaSi.isSelected()) {
            this.tomaFotografia = 1;
        } else {
            this.tomaFotografia = 0;
        }
        this.hora = txtHora.getText();
        try {
            this.declinacioMagnetica = Integer.valueOf(txtDeclinacionMagnetica.getText());
        } catch (NumberFormatException e) {
            this.declinacioMagnetica = null;
        }
    }
    
    private void crearFotografiaHemisferica() {
        this.ceFoto.setSitioID(this.sitioID);
        this.ceFoto.setCoberturaArborea(this.coberturaArborea);
        this.ceFoto.setTomaFotografia(this.tomaFotografia);
        this.ceFoto.setHora(this.hora);
        this.ceFoto.setDeclinacionMagnetica(this.declinacioMagnetica);
        
        this.cdFoto.insertFotografiaHemisferica(this.ceFoto);
    }
    
    private void actualizarFotografiaHemisferica(){
        this.ceFoto.setSitioID(this.sitioID);
        this.ceFoto.setCoberturaArborea(this.coberturaArborea);
        this.ceFoto.setTomaFotografia(this.tomaFotografia);
        this.ceFoto.setHora(this.hora);
        this.ceFoto.setDeclinacionMagnetica(this.declinacioMagnetica);
        
        this.cdFoto.updateFotografiaHemisferica(this.ceFoto);
    }
    
    private void eliminarFotografiaHemisferica(){
        this.cdFoto.deleteFotografiaHemisferica(this.sitioID);
    }
    
    private boolean validarCamposobligatorios() {
        if (txtHora.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe proporcionar la hora "
                    + "", "Toma de fotografía hemisferica", JOptionPane.INFORMATION_MESSAGE);
            txtHora.requestFocus();
            return false;
        } else if (txtDeclinacionMagnetica.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe proporcionar la declinación magnética "
                    + "", "Toma de fotografía hemisferica", JOptionPane.INFORMATION_MESSAGE);
            txtDeclinacionMagnetica.requestFocus();
            return false;
        } else {
            return true;
        }
    }
    
    private void limpiarControles(){
        rbtTomaFotografiaSi.setSelected(true);
        rbtTomaFotografiaNo.setSelected(false);
        txtHora.setValue(null);
        txtHora.setText("");
        txtDeclinacionMagnetica.setValue(null);
        txtDeclinacionMagnetica.setText("");
    }
    
    private void seleccionarSiguienteFormulario(CESitio ceSitio) {
        Integer secuenciaID = ceSitio.getSecuencia();
        if (secuenciaID != null) {
            switch (secuenciaID) {
                case 1: //Módulo A
                    /* UPMForms.carbono.setDatosIniciales(ceSitio);
                    UPMForms.carbono.setVisible(true);
                    this.hide();*/
                    break;
                case 2: //Módulos A y C
                    /* UPMForms.carbono.setDatosIniciales(ceSitio);
                    UPMForms.carbono.setVisible(true);*/
                    break;
                case 3: //Modulos A, C, E y G
                    /* UPMForms.parametrosFQ.setDatosiniciales(ceSitio);
                    UPMForms.parametrosFQ.setVisible(true);*/
                    break;
                case 4: //Modulos A y E
                    /*UPMForms.hojarascaProfundidad.setDatosiniciales(ceSitio);
                    UPMForms.hojarascaProfundidad.setVisible(true);*/
                    funciones.manipularBotonesMenuPrincipal(false);
                    this.hide();
                    this.cdSecuencia.insertSecuenciaTerminada(ceSitio);
                    break;
                case 5: //Modulos A, C, D y F
                    /*UPMForms.carbono.setDatosIniciales(ceSitio);
                    UPMForms.carbono.setVisible(true);*/
                    funciones.manipularBotonesMenuPrincipal(false);
                    this.hide();
                    this.cdSecuencia.insertSecuenciaTerminada(ceSitio);
                    break;
                case 6://Modulos A, C y D
                    /*UPMForms.suelo.setDatosiniciales(ceSitio);
                    UPMForms.suelo.setVisible(true);*/
                    break;
                case 7://Modulos A, C, D y E
                    /* UPMForms.carbono.setDatosIniciales(ceSitio);
                    UPMForms.carbono.setVisible(true);*/
                   /* funciones.manipularBotonesMenuPrincipal(false);
                    this.hide();
                     this.cdSecuencia.insertSecuenciaTerminada(ceSitio);*/
                    break;
                case 8://Modulos A, C, D, E y F
                    /*UPMForms.fotoHemisferica.setDatosiniciales(ceSitio);
                    UPMForms.fotoHemisferica.setVisible(true);*/
                    funciones.manipularBotonesMenuPrincipal(false);
                    this.hide();
                    this.cdSecuencia.insertSecuenciaTerminada(ceSitio);
                    break;
                case 9://Modulos A, C y E
                    /*UPMForms.carbono.setDatosIniciales(ceSitio);
                    UPMForms.carbono.setVisible(true);*/
                    break;
                case 10://Modulos A, C, H
                    /*UPMForms.suelo.setDatosiniciales(ceSitio);
                    UPMForms.suelo.setVisible(true);*/
                    break;
                case 11://Modulos A y H
                    /* UPMForms.carbono.setDatosIniciales(ceSitio);
                    UPMForms.carbono.setVisible(true);*/
                    break;
                case 12://Modulos A, E y H
                    /*UPMForms.vegetacionMenor.setDatosIniciales(ceSitio);
                    UPMForms.vegetacionMenor.setVisible(true);*/
                    break;
                case 13://Modulos A, C, E y H
                    /*UPMForms.vegetacionMenor.setDatosIniciales(ceSitio);
                    UPMForms.vegetacionMenor.setVisible(true);*/
                    break;
                case 14://Modulos A, E y G
                    /* UPMForms.parametrosFQ.setDatosiniciales(ceSitio);
                    UPMForms.parametrosFQ.setVisible(true);*/
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

        rbgCobertraArborea = new javax.swing.ButtonGroup();
        rbgTomaFotografia = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        lblUPM = new javax.swing.JLabel();
        txtUPM = new javax.swing.JTextField();
        lblTomaFotografiaHemisferica = new javax.swing.JLabel();
        lblSitio = new javax.swing.JLabel();
        txtSitio = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        btnContinuar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lblCoberturaArborea = new javax.swing.JLabel();
        lblTomaFotografia = new javax.swing.JLabel();
        lblHora = new javax.swing.JLabel();
        lblDeclinacionMagnetica = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        rbtCoberturaArboreaSi = new javax.swing.JRadioButton();
        rbtCoberturaArboreaNo = new javax.swing.JRadioButton();
        rbtTomaFotografiaSi = new javax.swing.JRadioButton();
        rbtTomaFotografiaNo = new javax.swing.JRadioButton();
        txtHora = new javax.swing.JFormattedTextField();
        txtDeclinacionMagnetica = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Fotografía hemisférica, módulo F "+version);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/gob/conafor/utils/logo_conafor.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(940, 660));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setPreferredSize(new java.awt.Dimension(940, 650));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblUPM.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblUPM.setText("UPMID:");
        jPanel1.add(lblUPM, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 15, -1, -1));

        txtUPM.setEditable(false);
        txtUPM.setEnabled(false);
        jPanel1.add(txtUPM, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 13, 97, -1));

        lblTomaFotografiaHemisferica.setBackground(new java.awt.Color(153, 153, 153));
        lblTomaFotografiaHemisferica.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTomaFotografiaHemisferica.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTomaFotografiaHemisferica.setText("Toma de fotografías hemisféricas  ");
        lblTomaFotografiaHemisferica.setOpaque(true);
        jPanel1.add(lblTomaFotografiaHemisferica, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 39, 900, -1));

        lblSitio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblSitio.setText("Sitio:");
        jPanel1.add(lblSitio, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 10, -1, -1));

        txtSitio.setEditable(false);
        txtSitio.setEnabled(false);
        jPanel1.add(txtSitio, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 10, 100, -1));

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
                .addGap(301, 301, 301)
                .addComponent(btnContinuar)
                .addGap(18, 18, 18)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(310, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalir)
                    .addComponent(btnContinuar))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 520, 790, 70));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblCoberturaArborea.setText("¿El sitio de muestreo cuenta con un mínimo de 10% de cobertura arborea?");

        lblTomaFotografia.setText("¿Las condiciones climáticas y posición del sol  permitieron la toma de fotografías antes de cualquier otras actividad? ");

        lblHora.setText("Hora:");

        lblDeclinacionMagnetica.setText("Declinación magnética calculada (+/-):");

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        rbtCoberturaArboreaSi.setBackground(new java.awt.Color(204, 204, 204));
        rbgCobertraArborea.add(rbtCoberturaArboreaSi);
        rbtCoberturaArboreaSi.setSelected(true);
        rbtCoberturaArboreaSi.setText("Si");

        rbtCoberturaArboreaNo.setBackground(new java.awt.Color(204, 204, 204));
        rbgCobertraArborea.add(rbtCoberturaArboreaNo);
        rbtCoberturaArboreaNo.setText("No");

        rbtTomaFotografiaSi.setBackground(new java.awt.Color(204, 204, 204));
        rbgTomaFotografia.add(rbtTomaFotografiaSi);
        rbtTomaFotografiaSi.setSelected(true);
        rbtTomaFotografiaSi.setText("Si");

        rbtTomaFotografiaNo.setBackground(new java.awt.Color(204, 204, 204));
        rbgTomaFotografia.add(rbtTomaFotografiaNo);
        rbtTomaFotografiaNo.setText("No");

        txtHora.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("hh:mm"))));
        txtHora.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtHoraFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtHoraFocusLost(evt);
            }
        });

        txtDeclinacionMagnetica.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat(""))));
        txtDeclinacionMagnetica.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDeclinacionMagneticaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDeclinacionMagneticaFocusLost(evt);
            }
        });
        txtDeclinacionMagnetica.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDeclinacionMagneticaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(rbtCoberturaArboreaSi)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rbtCoberturaArboreaNo))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(rbtTomaFotografiaSi)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rbtTomaFotografiaNo)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtDeclinacionMagnetica, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtCoberturaArboreaSi)
                    .addComponent(rbtCoberturaArboreaNo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtTomaFotografiaSi)
                    .addComponent(rbtTomaFotografiaNo))
                .addGap(38, 38, 38)
                .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(txtDeclinacionMagnetica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblHora)
                    .addComponent(lblTomaFotografia)
                    .addComponent(lblDeclinacionMagnetica)
                    .addComponent(lblCoberturaArborea))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(90, 90, 90))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(61, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(lblCoberturaArborea)
                        .addGap(63, 63, 63)
                        .addComponent(lblTomaFotografia)
                        .addGap(40, 40, 40)
                        .addComponent(lblHora)
                        .addGap(48, 48, 48)
                        .addComponent(lblDeclinacionMagnetica)
                        .addGap(66, 66, 66))))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 790, 350));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 924, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtHoraFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHoraFocusGained
       SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
               txtHora.selectAll();
            }
        }); 
    }//GEN-LAST:event_txtHoraFocusGained

    private void txtDeclinacionMagneticaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDeclinacionMagneticaFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtDeclinacionMagnetica.selectAll();
            }
        });
    }//GEN-LAST:event_txtDeclinacionMagneticaFocusGained

    private void txtHoraFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHoraFocusLost
        if(txtHora.getText().isEmpty()){
            txtHora.setValue(null);
        }
    }//GEN-LAST:event_txtHoraFocusLost

    private void txtDeclinacionMagneticaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDeclinacionMagneticaFocusLost
       if(txtDeclinacionMagnetica.getText().isEmpty()){
           txtDeclinacionMagnetica.setValue(null);
       }
    }//GEN-LAST:event_txtDeclinacionMagneticaFocusLost

    private void btnContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuarActionPerformed
        fijarDatosFotoHemisferica();
        if (validarCamposobligatorios()) {
            if (this.modificar == 0) {
                crearFotografiaHemisferica();
                this.hide();
                seleccionarSiguienteFormulario(this.ceSitio);
                this.cdSecuencia.updateSecuencia(this.ceSitio, FORMATO_ID, 1);
            } else {
                actualizarFotografiaHemisferica();
                this.hide();
                this.funciones.manipularBotonesMenuPrincipal(false);
            }
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

    private void txtDeclinacionMagneticaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDeclinacionMagneticaKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtDeclinacionMagneticaKeyTyped

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnContinuar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lblCoberturaArborea;
    private javax.swing.JLabel lblDeclinacionMagnetica;
    private javax.swing.JLabel lblHora;
    private javax.swing.JLabel lblSitio;
    private javax.swing.JLabel lblTomaFotografia;
    private javax.swing.JLabel lblTomaFotografiaHemisferica;
    private javax.swing.JLabel lblUPM;
    private javax.swing.ButtonGroup rbgCobertraArborea;
    private javax.swing.ButtonGroup rbgTomaFotografia;
    private javax.swing.JRadioButton rbtCoberturaArboreaNo;
    private javax.swing.JRadioButton rbtCoberturaArboreaSi;
    private javax.swing.JRadioButton rbtTomaFotografiaNo;
    private javax.swing.JRadioButton rbtTomaFotografiaSi;
    private javax.swing.JFormattedTextField txtDeclinacionMagnetica;
    private javax.swing.JFormattedTextField txtHora;
    private javax.swing.JTextField txtSitio;
    private javax.swing.JTextField txtUPM;
    // End of variables declaration//GEN-END:variables
   
}
