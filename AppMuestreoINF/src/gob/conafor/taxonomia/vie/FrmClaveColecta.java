package gob.conafor.taxonomia.vie;

import gob.conafor.sitio.mod.CDTrazoSitio;
import gob.conafor.sitio.mod.CESitio;
import gob.conafor.sitio.mod.CETrazoSitio;
import gob.conafor.taxonomia.mod.CDColectaBotanica;
import gob.conafor.taxonomia.mod.CEColectaBotanica;
import gob.conafor.upm.vie.UPMForms;
import gob.conafor.utils.Datos;
import gob.conafor.utils.FuncionesComunes;
import gob.conafor.utils.Version;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

public class FrmClaveColecta extends javax.swing.JDialog {
    
    public int[] idArbolado;
    protected static final int RET_CANCEL = 0;
    protected static final int RET_OK = 1;
    protected boolean iniciar = false;
    private int UPMID;
    private Integer familiaID;
    private Integer generoID;
    private Integer especieID;
    private String nombreComun;
    private String infraespecie;
    private Integer sitioID;
    private CETrazoSitio trazo = new CETrazoSitio();
    private CDTrazoSitio cdTrazo = new CDTrazoSitio();
    private CESitio ceSitio = new CESitio();
    private CEColectaBotanica ceColecta = new CEColectaBotanica();
    private Datos numeros = new Datos();
    private FuncionesComunes funciones = new FuncionesComunes();
    private int inicialesLimite;
    private CDColectaBotanica cdColecta = new CDColectaBotanica();
    private String tabla;
    private String individuo;
    private Integer noIndividuo;
    private String claveColecta;
    private String preclave;
    private String nombreCampo;
    private int formatoID;
    private Version ver=new Version();
    private String version=ver.getVersion();
    public static FrmArboladoD arboladoD=new FrmArboladoD();
    
    public FrmClaveColecta(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //Cerrar ventana al presionar cancelar
        String cancelName = "cancel";
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), cancelName);
        ActionMap actionMap = getRootPane().getActionMap();
        actionMap.put(cancelName, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                doClose(RET_CANCEL);
            }
        });
    }

    public void setDatosIniciales(CEColectaBotanica ceColecta, int formatoID, String tabla, String nombreCampo, Integer sitioID, Integer noIndividuo) {
        limpiarControles();
        this.UPMID = ceColecta.getUPMID();
        fillCmbClaveColecta(this.UPMID);
        this.formatoID = formatoID;
        this.ceColecta = ceColecta;
        this.tabla = tabla;
        this.sitioID = sitioID;
        this.noIndividuo = noIndividuo;
        this.preclave = this.cdColecta.getPreclave(this.UPMID);
        txtPreClave.setText(this.preclave);
        this.nombreCampo = nombreCampo;
    }
    
    public void limpiarControles() {
        cmbClaveColecta.removeAllItems();
        txtPreClave.setText("");
        txtIniciales.setText("");
        txtConsecutivo.setText("");
        rbtDigital.setSelected(false);
        rbtFisica.setSelected(false);
        txtClave.setText("");
    }

    private void fillCmbClaveColecta(int upmID) {
        List<String> listClave = new ArrayList<>();
        listClave = this.cdColecta.getClaveCreada(upmID);
        if (listClave != null) {
            int size = listClave.size();
            for (int i = 0; i < size; i++) {
                cmbClaveColecta.addItem(listClave.get(i));
            }
        }
    }
    
    private boolean validarCamposClaveColecta() {
        if (cmbClaveColecta.getSelectedItem() == null) {
            if (txtIniciales.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe proporcionar las iniciales del colector");
                txtIniciales.requestFocus();
                return false;
            } else if (txtConsecutivo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe proporcionar el consecutivo del colector");
                txtConsecutivo.requestFocus();
                return false;
            } else if (!rbtDigital.isSelected() && !rbtFisica.isSelected()) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo de colecta", "Clave de colecta", JOptionPane.INFORMATION_MESSAGE);
               
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }
    
    private boolean validarClaveRepetida(String claveColecta) {
        if (this.cdColecta.existeClave(claveColecta)) {
            JOptionPane.showMessageDialog(null, "Esa clave de colecta ya existe, verifique", "Clave de colecta", JOptionPane.INFORMATION_MESSAGE);
            txtIniciales.setText("");
            txtConsecutivo.setText("");
            txtClave.setText("");
            rbtDigital.setSelected(false);
            rbtFisica.setSelected(false);
            txtIniciales.requestFocus();
            return false;
        } else {
            return true;
        }
    }
    
    private String getConsecutivo(int consecutivo) {
        String conse = null;
        if (consecutivo < 10) {
            conse = "000" + consecutivo;
        } else if (consecutivo >= 10 && consecutivo < 100) {
            conse = "00" + consecutivo;
        } else if (consecutivo >= 100 && consecutivo < 1000) {
            conse = "0" + consecutivo;
        } else {
            conse = String.valueOf(consecutivo);
        }
        return conse;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rbgTipoColecta = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lblPreClave = new javax.swing.JLabel();
        txtPreClave = new javax.swing.JTextField();
        lblClave = new javax.swing.JLabel();
        txtClave = new javax.swing.JTextField();
        lblIniciales = new javax.swing.JLabel();
        lblConsecutivo = new javax.swing.JLabel();
        txtIniciales = new javax.swing.JTextField();
        txtConsecutivo = new javax.swing.JFormattedTextField();
        rbtFisica = new javax.swing.JRadioButton();
        rbtDigital = new javax.swing.JRadioButton();
        lblTipoColecta = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        lblClaveColecta = new javax.swing.JLabel();
        btnAsignar = new javax.swing.JButton();
        lblClavesExistentes = new javax.swing.JLabel();
        cmbClaveColecta = new javax.swing.JComboBox<>();
        lblClaveNueva = new javax.swing.JLabel();
        btnDesasignar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();

        setTitle("Generación de la clave de colecta botánica");
        setBackground(new java.awt.Color(204, 204, 204));
        setModal(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setPreferredSize(new java.awt.Dimension(931, 61));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblPreClave.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblPreClave.setText("Pre-clave:");

        txtPreClave.setEditable(false);
        txtPreClave.setEnabled(false);

        lblClave.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblClave.setText("Clave:");

        txtClave.setEditable(false);
        txtClave.setEnabled(false);

        lblIniciales.setText("Iniciales:");

        lblConsecutivo.setText("Consecutivo:");

        txtIniciales.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtInicialesKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtInicialesKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtInicialesKeyTyped(evt);
            }
        });

        txtConsecutivo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("0000"))));
        txtConsecutivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtConsecutivoKeyPressed(evt);
            }
        });

        rbtFisica.setBackground(new java.awt.Color(204, 204, 204));
        rbtFisica.setText("Física");
        rbtFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtFisicaActionPerformed(evt);
            }
        });

        rbtDigital.setBackground(new java.awt.Color(204, 204, 204));
        rbtDigital.setText("Digital");
        rbtDigital.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtDigitalActionPerformed(evt);
            }
        });

        lblTipoColecta.setText("Tipo de colecta:");

        jButton1.setMnemonic('l');
        jButton1.setText("limpiar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblIniciales)
                    .addComponent(lblPreClave)
                    .addComponent(lblConsecutivo)
                    .addComponent(lblClave)
                    .addComponent(lblTipoColecta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtIniciales, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtConsecutivo, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPreClave)
                            .addComponent(txtClave, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(rbtFisica)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbtDigital)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPreClave)
                    .addComponent(txtPreClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIniciales)
                    .addComponent(txtIniciales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblConsecutivo)
                    .addComponent(txtConsecutivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtFisica)
                    .addComponent(rbtDigital)
                    .addComponent(lblTipoColecta)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblClave)
                    .addComponent(txtClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        btnSalir.setMnemonic('s');
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        lblClaveColecta.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblClaveColecta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblClaveColecta.setText("Asignación de clave de colecta botánica");
        lblClaveColecta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btnAsignar.setMnemonic('a');
        btnAsignar.setText("Asignar");
        btnAsignar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsignarActionPerformed(evt);
            }
        });

        lblClavesExistentes.setText("Claves existentes:");

        cmbClaveColecta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbClaveColectaActionPerformed(evt);
            }
        });

        lblClaveNueva.setBackground(new java.awt.Color(0, 0, 0));
        lblClaveNueva.setForeground(new java.awt.Color(255, 255, 255));
        lblClaveNueva.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblClaveNueva.setText("Crear una clave nueva");
        lblClaveNueva.setOpaque(true);

        btnDesasignar.setMnemonic('d');
        btnDesasignar.setText("Desasignar");
        btnDesasignar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesasignarActionPerformed(evt);
            }
        });

        btnModificar.setMnemonic('m');
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnAsignar, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnDesasignar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnModificar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblClaveNueva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblClaveColecta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(lblClavesExistentes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbClaveColecta, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblClaveColecta)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblClavesExistentes)
                    .addComponent(cmbClaveColecta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblClaveNueva)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalir)
                    .addComponent(btnAsignar)
                    .addComponent(btnDesasignar)
                    .addComponent(btnModificar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        this.dispose();
    }//GEN-LAST:event_closeDialog

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        doClose(returnStatus);
    }//GEN-LAST:event_btnSalirActionPerformed
    
    private void btnAsignarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignarActionPerformed
        if (cmbClaveColecta.getSelectedItem() != null) {
            String clave = (String) cmbClaveColecta.getSelectedItem();
            this.cdColecta.asignarClaveColecta(this.tabla, this.nombreCampo, this.noIndividuo, this.sitioID, clave);
            limpiarControles();
            actualizarDatosFormato(this.formatoID, clave);
            doClose(returnStatus);
        } else if (validarCamposClaveColecta() && validarClaveRepetida(txtClave.getText())) {
            this.cdColecta.asignarClaveColecta(this.tabla, this.nombreCampo, this.noIndividuo, this.sitioID, this.claveColecta);
            this.cdColecta.insertarClave(this.ceColecta, this.UPMID, this.claveColecta);
            limpiarControles();
            actualizarDatosFormato(this.formatoID, this.claveColecta);
            doClose(returnStatus);
        }

    }//GEN-LAST:event_btnAsignarActionPerformed

    private void actualizarDatosFormato(int formID, String clave) {
        switch (formID) {
            case 1:
                UPMForms.sotoBosque.llenarTabla();
                UPMForms.sotoBosque.txtClaveColecta.setText(clave);
                UPMForms.sotoBosque.limpiarControles();
                break;
            case 2:
                UPMForms.repoblado.llenarTabla();
                UPMForms.repoblado.txtClaveColecta.setText(clave);
                UPMForms.repoblado.reiniciarRepoblado();
                break;
            case 3:
                UPMForms.arbolado.llenarTabla();
                UPMForms.arbolado.txtClaveColecta.setText(clave);
                UPMForms.arbolado.limpiarControles();
                break;
            case 14:
                UPMForms.arboladoD.llenarTabla();
                UPMForms.arboladoD.txtClaveColecta.setText(clave);
                UPMForms.arboladoD.limpiarControles();
                break;
            case 20:
                UPMForms.arboladoG.llenarTabla();
                UPMForms.arboladoG.txtClaveColecta.setText(clave);
                UPMForms.arboladoG.limpiarControles();
                break;
            case 19:
                UPMForms.repobladoVM.llenarTabla();
                UPMForms.repobladoVM.txtClaveColecta.setText(clave);
                UPMForms.repobladoVM.limpiarControles();
                break;
            case 23:
                UPMForms.vegetacionMayorG.llenarTabla();
                UPMForms.vegetacionMayorG.txtClaveColecta.setText(clave);
                UPMForms.vegetacionMayorG.limpiarControles();
                break;
            case 22:
                UPMForms.vegetacionMayorI.llenarTabla();
                UPMForms.vegetacionMayorI.txtClaveColecta.setText(clave);
                UPMForms.vegetacionMayorI.limpiarControles();
                break;
            case 21:
                UPMForms.vegetacionMenor.llenarTabla();
                UPMForms.vegetacionMenor.txtClaveColecta.setText(clave);
                UPMForms.vegetacionMenor.limpiarControles();
                break;
            case 8:
                UPMForms.longitud.llenarTabla();
                UPMForms.longitud.txtClaveColecta.setText(clave);
                UPMForms.longitud.limpiarControlesComponentes();
                break;
        }
    }
    
    private void txtInicialesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtInicialesKeyTyped
        if (txtIniciales.getText().length() == 3) {
            evt.consume();
        }
    }//GEN-LAST:event_txtInicialesKeyTyped

    private void rbtFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtFisicaActionPerformed
        rbtDigital.setSelected(false);
        if (txtIniciales.getText().isEmpty()/*&& rbtFisica.isSelected()*/) {
            JOptionPane.showMessageDialog(null, "Error! Debe Proporcionar las iniciales del colector", "Clave de colecta", JOptionPane.INFORMATION_MESSAGE);
            rbtFisica.setSelected(false);
            txtIniciales.requestFocus();
        } else if (txtConsecutivo.getText().isEmpty()/* && rbtFisica.isSelected()*/) {
            JOptionPane.showMessageDialog(null, "Error! Debe Proporcionar el número consecutivo de colecta", "Clave de colecta", JOptionPane.INFORMATION_MESSAGE);
            rbtFisica.setSelected(false);
            txtConsecutivo.requestFocus();
        } else {
            this.claveColecta = txtPreClave.getText() + "_" + txtIniciales.getText() + "_" + getConsecutivo(Integer.parseInt(txtConsecutivo.getText())) + "_" + "F";
            txtClave.setText(this.claveColecta);
            
        }
    }//GEN-LAST:event_rbtFisicaActionPerformed

    private void rbtDigitalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtDigitalActionPerformed
        rbtFisica.setSelected(false);
        if (txtIniciales.getText().isEmpty() && rbtDigital.isSelected()) {
            JOptionPane.showMessageDialog(null, "Error! Debe Proporcionar las iniciales del colector", "Clave de colecta", JOptionPane.INFORMATION_MESSAGE);
            rbtDigital.setSelected(false);
            txtIniciales.requestFocus();
        } else if (txtConsecutivo.getText().isEmpty() && rbtDigital.isSelected()) {
            JOptionPane.showMessageDialog(null, "Error! Debe Proporcionar el número consecutivo de colecta", "Clave de colecta", JOptionPane.INFORMATION_MESSAGE);
            rbtDigital.setSelected(false);
            txtConsecutivo.requestFocus();
        } else {
            this.claveColecta = txtPreClave.getText() + "_" + txtIniciales.getText() + "_" + getConsecutivo(Integer.parseInt(txtConsecutivo.getText())) + "_" + "D";
            txtClave.setText(this.claveColecta);
        }
    }//GEN-LAST:event_rbtDigitalActionPerformed

    private void cmbClaveColectaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbClaveColectaActionPerformed
        if (cmbClaveColecta.getSelectedItem() != null) {
            txtIniciales.setEnabled(true);
            txtConsecutivo.setEnabled(true);
            rbtDigital.setEnabled(true);
            rbtFisica.setEnabled(true);
            txtPreClave.setText(this.preclave);
        } /*else {
            txtPreClave.setText("");
            txtIniciales.setEnabled(false);
            txtIniciales.setText("");
            txtConsecutivo.setEnabled(false);
            txtConsecutivo.setText("");
            rbtDigital.setEnabled(false);
            rbtDigital.setSelected(false);
            rbtFisica.setEnabled(false);
            rbtFisica.setSelected(false);
        }*/
    }//GEN-LAST:event_cmbClaveColectaActionPerformed

    private void txtInicialesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtInicialesKeyReleased
        txtIniciales.setText(txtIniciales.getText().toUpperCase());
        txtIniciales.setText(txtIniciales.getText().trim());
    }//GEN-LAST:event_txtInicialesKeyReleased

    private void txtInicialesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtInicialesKeyPressed
      
    }//GEN-LAST:event_txtInicialesKeyPressed

    private void txtConsecutivoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConsecutivoKeyPressed
              
    }//GEN-LAST:event_txtConsecutivoKeyPressed

    private void btnDesasignarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesasignarActionPerformed
           this.cdColecta.asignarClaveColecta(this.tabla, this.nombreCampo, this.noIndividuo, this.sitioID,  "");
            limpiarControles();
            actualizarDatosFormato(this.formatoID, "");
            doClose(returnStatus);
    }//GEN-LAST:event_btnDesasignarActionPerformed

    public void setIndexArbolado(int[] index)
    {
        
        for(int i=0;i<=index.length;i++){
            //System.out.println(index[i]);
            idArbolado[i]=index[i];
        }
        
    }
    
    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        try {
            String Clave=cmbClaveColecta.getSelectedItem().toString(),ClaveNueva=txtClave.getText().toString();
            this.UPMID = ceColecta.getUPMID();
            this.cdColecta.modificarClaveColecta(Clave, this.UPMID,ClaveNueva);
            cmbClaveColecta.removeAllItems();
            limpiarControles();
            fillCmbClaveColecta(this.UPMID);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Debe Seleccionar una clave de colecta "
                , "Conexion BD", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.rbtFisica.setSelected(false);
        this.rbtDigital.setSelected(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void doClose(int retStatus) {
        returnStatus = retStatus;
        setVisible(false);
        this.dispose();
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAsignar;
    private javax.swing.JButton btnDesasignar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<String> cmbClaveColecta;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblClave;
    private javax.swing.JLabel lblClaveColecta;
    private javax.swing.JLabel lblClaveNueva;
    private javax.swing.JLabel lblClavesExistentes;
    private javax.swing.JLabel lblConsecutivo;
    private javax.swing.JLabel lblIniciales;
    private javax.swing.JLabel lblPreClave;
    private javax.swing.JLabel lblTipoColecta;
    private javax.swing.ButtonGroup rbgTipoColecta;
    private javax.swing.JRadioButton rbtDigital;
    private javax.swing.JRadioButton rbtFisica;
    private javax.swing.JTextField txtClave;
    private javax.swing.JFormattedTextField txtConsecutivo;
    private javax.swing.JTextField txtIniciales;
    private javax.swing.JTextField txtPreClave;
    // End of variables declaration//GEN-END:variables

    private int returnStatus = RET_CANCEL;
}
