package gob.conafor.sitio.vie;

import gob.conafor.sitio.mod.CDSitio;
import gob.conafor.sitio.mod.CESitio;
import gob.conafor.sys.mod.CDSecuencia;
import gob.conafor.upm.vie.UPMForms;
import gob.conafor.utils.FuncionesComunes;
import gob.conafor.utils.Version;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class FrmObservaciones extends javax.swing.JInternalFrame {

    private boolean revision;
    private int upmID;
    private int sitioID;
    private int sitio;
    private static final int FORMATO_ID = 13;
    private CESitio ceSitio = new CESitio();
    private CDSitio cdSitio = new CDSitio();
    private String observaciones;
    private int intObservaciones;
    private CDSecuencia cdSecuencia = new CDSecuencia();
    private FuncionesComunes funciones = new FuncionesComunes();
    private int modificar;
    private Version ver = new Version();
    private String version = ver.getVersion();
    private FuncionesComunes combo = new FuncionesComunes();

    public FrmObservaciones() {
        initComponents();
        intObservaciones = 23;
    }

    public void setDatosiniciales(CESitio ceSitio) {
        this.upmID = ceSitio.getUpmID();
        this.sitioID = ceSitio.getSitioID();
        this.sitio = ceSitio.getSitio();

        this.ceSitio.setSitioID(this.sitioID);
        this.ceSitio.setUpmID(this.upmID);
        this.ceSitio.setSitio(this.sitio);
        this.ceSitio = ceSitio;
        modificar = 0;
        funciones.manipularBotonesMenuPrincipal(true);
        txtObservacionesSitio.setText("");
    }

    public void llenarControles() {
        combo.reiniciarComboModel(this.cmbUPMID);
        cmbSitios.setEnabled(true);
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

    private void fillCmbSitio(int upmID) {

        List<Integer> listSitios = new ArrayList<>();
        listSitios = this.cdSitio.getSitiosDisponibles(upmID);
        if (listSitios != null) {
            int size = listSitios.size();
            for (int i = 0; i < size; i++) {
                cmbSitios.addItem(listSitios.get(i));
            }
        }
    }

    public void revisarObservaciones(int sitioID) {
        /*revision=true;
        this.upmID = ceSitio.getUpmID();
        this.sitioID = ceSitio.getSitioID();
        this.sitio = ceSitio.getSitio();*/

        this.ceSitio.setSitioID(this.sitioID);
        this.ceSitio.setUpmID(this.upmID);
        this.ceSitio.setSitio(this.sitio);
        //this.ceSitio.setSecuencia(ceSitio.getSecuencia());
        CESitio ceObservaciones;
        ceObservaciones = this.cdSitio.getObservaciones(this.sitioID);
        txtObservacionesSitio.setText(ceObservaciones.getObservaciones());
        modificar = 1;
        funciones.manipularBotonesMenuPrincipal(true);
    }

    private void fijarDatosObservaciones() {
        this.observaciones = txtObservacionesSitio.getText();
        this.ceSitio.setSitioID(this.sitioID);
        this.ceSitio.setObservaciones(this.observaciones);
    }

    /*    private boolean validarObservaciones() {
        if (txtObservacionesSitio.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar las observaciones del sitio ",
                    "Sitio", JOptionPane.INFORMATION_MESSAGE);
            txtObservacionesSitio.requestFocus();
            return false;
        } else {
            return true;
        }
    }*/
    private void seleccionarSiguienteFormulario(CESitio ceSitio) {
        Integer secuenciaID = ceSitio.getSecuencia();
        Integer sitio = this.funciones.sitioCapturaSueloCarbono(this.upmID, 3);
        if (secuenciaID != null) {
            switch (secuenciaID) {
                case 1: //Módulo A
                    /* UPMForms.carbono.setDatosIniciales(ceSitio);
                    UPMForms.carbono.setVisible(true);*/
                    this.hide();
                    funciones.manipularBotonesMenuPrincipal(false);
                    this.cdSecuencia.insertSecuenciaTerminada(ceSitio);
                    break;
                case 2: //Módulos A y C
                    UPMForms.carbono.setDatosIniciales(ceSitio);
                    UPMForms.carbono.setVisible(true);
                    break;
                case 3: //Modulos A, C, E y G
                    UPMForms.carbono.setDatosIniciales(ceSitio);
                    UPMForms.carbono.setVisible(true);
                    break;
                case 4: //Modulos A y E
                    if (sitio == this.ceSitio.getSitio()) {
                        UPMForms.hojarascaProfundidad.setDatosiniciales(ceSitio);
                        UPMForms.hojarascaProfundidad.setVisible(true);
                    } else {
                        this.hide();
                        funciones.manipularBotonesMenuPrincipal(false);
                        this.cdSecuencia.insertSecuenciaTerminada(ceSitio);
                    }
                    break;
                case 5: //Modulos A, C, D y F
                    UPMForms.carbono.setDatosIniciales(ceSitio);
                    UPMForms.carbono.setVisible(true);
                    break;
                case 6://Modulos A, C y D
                    UPMForms.carbono.setDatosIniciales(ceSitio);
                    UPMForms.carbono.setVisible(true);
                    break;
                case 7://Modulos A, C, D y E
                    UPMForms.carbono.setDatosIniciales(ceSitio);
                    UPMForms.carbono.setVisible(true);
                    break;
                case 8://Modulos A, C, D, E y F
                    UPMForms.carbono.setDatosIniciales(ceSitio);
                    UPMForms.carbono.setVisible(true);
                    break;
                case 9://Modulos A, C y E
                    UPMForms.carbono.setDatosIniciales(ceSitio);
                    UPMForms.carbono.setVisible(true);
                    break;
                case 10://Modulos A, C, H
                    UPMForms.carbono.setDatosIniciales(ceSitio);
                    UPMForms.carbono.setVisible(true);
                    break;
                case 11://Modulos A y H
                    UPMForms.vegetacionMenor.setDatosIniciales(ceSitio);
                    UPMForms.vegetacionMenor.setVisible(true);
                    break;
                case 12://Modulos A, E y H
                    if (sitio == this.ceSitio.getSitio()) {
                        UPMForms.hojarascaProfundidad.setDatosiniciales(ceSitio);
                        UPMForms.hojarascaProfundidad.setVisible(true);
                    } else {
                        UPMForms.vegetacionMenor.setDatosIniciales(ceSitio);
                        UPMForms.vegetacionMenor.setVisible(true);
                    }
                    break;
                case 13://Modulos A, C, E y H
                    UPMForms.carbono.setDatosIniciales(ceSitio);
                    UPMForms.carbono.setVisible(true);
                    break;
                case 14://Modulos A, E y G
                    if (sitio == this.ceSitio.getSitio()) {
                        UPMForms.hojarascaProfundidad.setDatosiniciales(ceSitio);
                        UPMForms.hojarascaProfundidad.setVisible(true);
                    } else {
                        UPMForms.parametrosFQ.setDatosiniciales(ceSitio);
                        UPMForms.parametrosFQ.setVisible(true);
                    }
                    break;
                case 15://A y G
                    UPMForms.parametrosFQ.setDatosiniciales(ceSitio);
                    UPMForms.parametrosFQ.setVisible(true);
                    break;
                case 16:
                    UPMForms.carbono.setDatosIniciales(ceSitio);
                    UPMForms.carbono.setVisible(true);
                    break;
            }
        }
    }

    /*public void revisarSiguienteFormulario(CESitio ceSitio) {
        Integer secuenciaID = ceSitio.getSecuencia();
        Integer sitio = this.funciones.sitioCapturaSueloCarbono(this.upmID, 3);
        //System.out.println("secuencia="+secuenciaID);
        if (secuenciaID == null) {
            
            secuenciaID = this.funciones.buscarSecuencia(this.upmID);
            
        }
            //System.out.println("secuencia="+secuenciaID);
            switch (secuenciaID) {
                case 1: //Módulo A
                    /* UPMForms.carbono.setDatosIniciales(ceSitio);
                    UPMForms.carbono.setVisible(true);
                    this.hide();
                    funciones.manipularBotonesMenuPrincipal(false);
                    break;
                case 2: //Módulos A y C
                    UPMForms.carbono.revisarCarbono(ceSitio);
                    UPMForms.carbono.setVisible(true);
                    break;
                case 3: //Modulos A, C, E y G
                    UPMForms.carbono.revisarCarbono(ceSitio);
                    UPMForms.carbono.setVisible(true);
                    break;
                case 4: //Modulos A y E
                    if (sitio == this.ceSitio.getSitio()) {
                        UPMForms.hojarascaProfundidad.revisarHojarascaProfundidad(ceSitio);
                        UPMForms.hojarascaProfundidad.setVisible(true);
                    } else {
                        this.hide();
                        funciones.manipularBotonesMenuPrincipal(false);
                    }
                    break;
                case 5: //Modulos A, C, D y F
                    UPMForms.carbono.revisarCarbono(ceSitio);
                    UPMForms.carbono.setVisible(true);
                    break;
                case 6://Modulos A, C y D
                    UPMForms.carbono.revisarCarbono(ceSitio);
                    UPMForms.carbono.setVisible(true);
                    break;
                case 7://Modulos A, C, D y E
                    UPMForms.carbono.revisarCarbono(ceSitio);
                    UPMForms.carbono.setVisible(true);
                    break;
                case 8://Modulos A, C, D, E y F
                    UPMForms.carbono.revisarCarbono(ceSitio);
                    UPMForms.carbono.setVisible(true);
                    break;
                case 9://Modulos A, C y E
                    UPMForms.carbono.revisarCarbono(ceSitio);
                    UPMForms.carbono.setVisible(true);
                    break;
                case 10://Modulos A, C, H
                    UPMForms.carbono.revisarCarbono(ceSitio);
                    UPMForms.carbono.setVisible(true);
                    break;
                case 11://Modulos A y H
                    UPMForms.vegetacionMenor.revisarVegetacionMenor(ceSitio);
                    UPMForms.vegetacionMenor.setVisible(true);
                    break;
                case 12://Modulos A, E y H
                    if (sitio == this.ceSitio.getSitio()) {
                        UPMForms.hojarascaProfundidad.revisarHojarascaProfundidad(ceSitio);
                        UPMForms.hojarascaProfundidad.setVisible(true);
                    } else {
                        UPMForms.vegetacionMenor.revisarVegetacionMenor(ceSitio);
                        UPMForms.vegetacionMenor.setVisible(true);
                    }
                    break;
                case 13://Modulos A, C, E y H
                    UPMForms.carbono.revisarCarbono(ceSitio);
                    UPMForms.carbono.setVisible(true);
                    break;
                case 14://Modulos A, E y G
                    if (sitio == this.ceSitio.getSitio()) {
                        UPMForms.hojarascaProfundidad.revisarHojarascaProfundidad(ceSitio);
                        UPMForms.hojarascaProfundidad.setVisible(true);
                    } else {
                        UPMForms.parametrosFQ.continuarParametrosFQ(ceSitio);
                        UPMForms.parametrosFQ.setVisible(true);
                    }
                    break;
                case 15://A y G
                    UPMForms.parametrosFQ.continuarParametrosFQ(ceSitio);
                    UPMForms.parametrosFQ.setVisible(true);
                    break;
            }
        
    }*/
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblUPM = new javax.swing.JLabel();
        lblSuelo1 = new javax.swing.JLabel();
        lblSitio = new javax.swing.JLabel();
        btnContinuar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObservacionesSitio = new javax.swing.JTextArea();
        cmbUPMID = new javax.swing.JComboBox<>();
        cmbSitios = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximizable(true);
        setTitle("Observaciones por sitio del UPM "+version);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/gob/conafor/utils/logo_conafor.png"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        lblUPM.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblUPM.setText("UPMID:");

        lblSuelo1.setBackground(new java.awt.Color(153, 153, 153));
        lblSuelo1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblSuelo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSuelo1.setText("Observaciones");
        lblSuelo1.setOpaque(true);

        lblSitio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblSitio.setText("Sitio:");

        btnContinuar.setMnemonic('c');
        btnContinuar.setText("Continuar");
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

        txtObservacionesSitio.setColumns(20);
        txtObservacionesSitio.setLineWrap(true);
        txtObservacionesSitio.setRows(5);
        txtObservacionesSitio.setWrapStyleWord(true);
        txtObservacionesSitio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtObservacionesSitioKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(txtObservacionesSitio);

        cmbUPMID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbUPMIDActionPerformed(evt);
            }
        });

        cmbSitios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSitiosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(lblUPM)
                            .addGap(18, 18, 18)
                            .addComponent(cmbUPMID, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblSitio)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(cmbSitios, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblSuelo1, javax.swing.GroupLayout.PREFERRED_SIZE, 890, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 890, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnContinuar)
                        .addGap(31, 31, 31)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblUPM)
                        .addComponent(cmbUPMID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblSitio)
                        .addComponent(cmbSitios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSuelo1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnContinuar)
                    .addComponent(btnSalir))
                .addContainerGap(34, Short.MAX_VALUE))
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

    private void btnContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuarActionPerformed
        fijarDatosObservaciones();
        if (combo.isEnabledCmbSitios(cmbSitios) == false) {

        } else {
            this.cdSitio.setObservacionesSitio(this.ceSitio);
        }
    }//GEN-LAST:event_btnContinuarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        if (revision == false) {//esta en modo de captura
            this.hide();
            funciones.manipularBotonesMenuPrincipal(false);
        }
        if (revision == true) {//entro a modo de revision
            //System.err.println("Modo Revision");
            this.hide();
            //UPMForms.revisionModulos.iniciarRevision();
            UPMForms.revisionModulos.setVisible(true);
            UPMForms.revisionModulos.manipularBonesMenuprincipal();
            revision = false;
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtObservacionesSitioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtObservacionesSitioKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            evt.consume();
        }
    }//GEN-LAST:event_txtObservacionesSitioKeyPressed

    private void cmbUPMIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbUPMIDActionPerformed
        Integer upmID = (Integer) cmbUPMID.getSelectedItem();
        Integer sitio = (Integer) cmbSitios.getSelectedItem();
        if (cmbUPMID.getSelectedItem() != null) {
            this.upmID = (Integer) cmbUPMID.getSelectedItem();
            combo.reiniciarComboModel(cmbSitios);
            fillCmbSitio(upmID);
            cmbSitios.setEnabled(true);
        } else {
            combo.reiniciarComboModel(cmbSitios);
            cmbSitios.setSelectedItem(null);
            cmbSitios.setEnabled(false);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_cmbUPMIDActionPerformed

    private void cmbSitiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSitiosActionPerformed
        try {
            //System.out.println("item selected=\t"+cmbSitios.getSelectedItem());
            if (cmbSitios.getSelectedItem() == null) {
                this.sitioID = 0;
                /*limpiarControles();
                limpiarPorcentajes();*/
            } else {
                String upm = cmbUPMID.getSelectedItem().toString();
                String sitio = cmbSitios.getSelectedItem().toString();
                this.sitioID = cdSitio.getSitioIDNuevo(upm, sitio);

            }
            revisarObservaciones(this.sitioID);

//llenarTabla();
        } catch (Exception e) {
            e.getCause();
        }

    }//GEN-LAST:event_cmbSitiosActionPerformed

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnContinuar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<Integer> cmbSitios;
    private javax.swing.JComboBox<Integer> cmbUPMID;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblSitio;
    private javax.swing.JLabel lblSuelo1;
    private javax.swing.JLabel lblUPM;
    private javax.swing.JTextArea txtObservacionesSitio;
    // End of variables declaration//GEN-END:variables

}
