package gob.conafor.ini.vie;

import gob.conafor.sitio.mod.CDSitio;
import gob.conafor.sitio.mod.CESitio;
import gob.conafor.sys.mod.CDSecuencia;
import gob.conafor.upm.mod.CDUpm;
import gob.conafor.upm.mod.CEUPM;
import gob.conafor.upm.vie.UPMForms;
import gob.conafor.utils.FuncionesComunes;
import gob.conafor.utils.Version;
import java.util.ArrayList;
import java.util.List;

public class FrmCapturaModulos extends javax.swing.JInternalFrame {

    private CDUpm cdUpm = new CDUpm();
    private CEUPM ceUpm = new CEUPM();
    private CDSitio cdSitio = new CDSitio();
    private CDSecuencia cdSecuencia = new CDSecuencia();
    private FuncionesComunes combo = new FuncionesComunes();
    private Integer secuenciaID;
    private Integer sitioID;

    private Version ver = new Version();
    private String version = ver.getVersion();

    public FrmCapturaModulos() {
        initComponents();
        this.setLocation(300, 110);
    }

    public void llenarControles() {
        combo.reiniciarComboModel(this.cmbUPMID);
        fillUPMID();
    }

    public void manipularBonesMenuprincipal() {
        combo.manipularBotonesMenuPrincipal(true);
        cmbUPMID.setSelectedItem(null);
        cmbSitios.setSelectedItem(null);
        combo.reiniciarComboModel(cmbSitios);
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

    private void crearSecuencia(int secuenciaID, int upmID, int sitio) {
        switch (secuenciaID) {
            case 1:
                cdSecuencia.insertSecuencia1(upmID, sitio);
                break;
            case 2:
                cdSecuencia.insertSecuencia2(upmID, sitio);
                break;
            case 3:
                cdSecuencia.insertSecuencia3(upmID, sitio);
                break;
            case 4:
                cdSecuencia.insertSecuencia4(upmID, sitio);
                break;
            case 5:
                cdSecuencia.insertSecuencia5(upmID, sitio);
                break;
            case 6:
                cdSecuencia.insertSecuencia6(upmID, sitio);
                break;
            case 7:
                cdSecuencia.insertSecuencia7(upmID, sitio);
                break;
            case 8:
                cdSecuencia.insertSecuencia8(upmID, sitio);
                break;
            case 9:
                cdSecuencia.insertSecuencia9(upmID, sitio);
                break;
            case 10:
                cdSecuencia.insertSecuencia10(upmID, sitio);
                break;
            case 11:
                cdSecuencia.insertSecuencia11(upmID, sitio);
                break;
            case 12:
                cdSecuencia.insertSecuencia12(upmID, sitio);
                break;
            case 13:
                cdSecuencia.insertSecuencia13(upmID, sitio);
                break;
            case 14:
                cdSecuencia.insertSecuencia14(upmID, sitio);
                break;
            case 15:
                cdSecuencia.insertSecuencia15(upmID, sitio);
                break;
            case 16:
                cdSecuencia.insertSecuencia16(upmID, sitio);
                break;
            case 17:
                cdSecuencia.insertSecuencia17(upmID, sitio);
                break;
            case 18:
                cdSecuencia.insertSecuencia18(upmID, sitio);
                break;
            case 19:
                cdSecuencia.insertSecuencia19(upmID, sitio);
                break;
            case 20:
                cdSecuencia.insertSecuencia20(upmID, sitio);
                break;
        }
    }

    private void setSecuencia(int upmID) {
        secuenciaID = this.cdUpm.getSecuenciaID(upmID);
    }

    private Integer getSecuencia() {
        return secuenciaID;
    }

    private void setSitioID(Integer upmID, Integer sitio) {
        this.sitioID = cdSitio.getSitioID(upmID, sitio);
    }

    private Integer getSitioID() {
        return this.sitioID;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        cmbUPMID = new javax.swing.JComboBox();
        lblUPM = new javax.swing.JLabel();
        lblLogoConafor = new javax.swing.JLabel();
        lblSitio = new javax.swing.JLabel();
        cmbSitios = new javax.swing.JComboBox();
        btnComenzarCaptura = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("Captura de formatos por sitio "+version);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/gob/conafor/utils/logo_conafor.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(335, 317));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setForeground(new java.awt.Color(204, 204, 204));

        cmbUPMID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbUPMIDActionPerformed(evt);
            }
        });

        lblUPM.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblUPM.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblUPM.setText("UPM:");

        lblLogoConafor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gob/conafor/utils/CONAFOR_231_100px.png"))); // NOI18N

        lblSitio.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblSitio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblSitio.setText("Sitio:");

        cmbSitios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSitiosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLogoConafor)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(lblSitio, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cmbSitios, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(lblUPM, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cmbUPMID, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(67, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLogoConafor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbUPMID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUPM))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbSitios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSitio))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        btnComenzarCaptura.setMnemonic('c');
        btnComenzarCaptura.setText("Comenzar captura");
        btnComenzarCaptura.setEnabled(false);
        btnComenzarCaptura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComenzarCapturaActionPerformed(evt);
            }
        });

        btnSalir.setMnemonic('s');
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
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnComenzarCaptura, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnComenzarCaptura)
                    .addComponent(btnSalir))
                .addContainerGap(23, Short.MAX_VALUE))
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
        combo.manipularBotonesMenuPrincipal(false);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnComenzarCapturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComenzarCapturaActionPerformed
        this.hide();
        Integer upmID = (Integer) cmbUPMID.getSelectedItem();
        Integer sitio = (Integer) cmbSitios.getSelectedItem();

        setSitioID(upmID, sitio);
        CESitio ceSitio = new CESitio();
        setSecuencia(upmID);
        Integer secuencia = getSecuencia();
        ceSitio.setUpmID(upmID);
        ceSitio.setSitio(sitio);
        ceSitio.setSecuencia(secuencia);
        ceSitio.setSitioID(getSitioID());
        ceSitio.setSitio3Accesible(combo.sitioCapturaSueloCarbono(upmID, sitio));

        if (secuencia != null) {
            switch (secuencia) {
                case 1: //Módulo A
                    UPMForms.sotoBosque.setDatosIniciales(ceSitio);
                    UPMForms.sotoBosque.setVisible(true);
                    break;
                case 2: //Módulos A y C
                    UPMForms.sotoBosque.setDatosIniciales(ceSitio);
                    UPMForms.sotoBosque.setVisible(true);
                    break;
                case 3: //Modulos A, C, E y G
                    UPMForms.claveVegetacion.setDatosIniciales(ceSitio);
                    UPMForms.claveVegetacion.setVisible(true);
                    break;
                case 4: //Modulos A y E
                    UPMForms.sotoBosque.setDatosIniciales(ceSitio);
                    UPMForms.sotoBosque.setVisible(true);
                    break;
                case 5: //Modulos A, C, D y F
                    UPMForms.sotoBosque.setDatosIniciales(ceSitio);
                    UPMForms.sotoBosque.setVisible(true);
                    break;
                case 6://Modulos A, C y D
                    UPMForms.sotoBosque.setDatosIniciales(ceSitio);
                    UPMForms.sotoBosque.setVisible(true);
                    break;
                case 7://Modulos A, C, D y E
                    UPMForms.sotoBosque.setDatosIniciales(ceSitio);
                    UPMForms.sotoBosque.setVisible(true);
                    break;
                case 8://Modulos A, C, D, E y F
                    UPMForms.sotoBosque.setDatosIniciales(ceSitio);
                    UPMForms.sotoBosque.setVisible(true);
                    break;
                case 9://Modulos A, C y E
                    UPMForms.sotoBosque.setDatosIniciales(ceSitio);
                    UPMForms.sotoBosque.setVisible(true);
                    break;
                case 10://Modulos A, C, H
                    UPMForms.sotoBosque.setDatosIniciales(ceSitio);
                    UPMForms.sotoBosque.desabilitarSotobosque();
                    UPMForms.sotoBosque.setVisible(true);
                    break;
                case 11://Modulos A y H
                    UPMForms.sotoBosque.setDatosIniciales(ceSitio);
                    UPMForms.sotoBosque.desabilitarSotobosque();
                    UPMForms.sotoBosque.setVisible(true);
                    break;
                case 12://Modulos A, E y H
                    UPMForms.sotoBosque.setDatosIniciales(ceSitio);
                    UPMForms.sotoBosque.desabilitarSotobosque();
                    UPMForms.sotoBosque.setVisible(true);
                    break;
                case 13://Modulos A, C, E y H
                    UPMForms.sotoBosque.setDatosIniciales(ceSitio);
                    UPMForms.sotoBosque.desabilitarSotobosque();
                    UPMForms.sotoBosque.setVisible(true);
                    break;
                case 14://Modulos A, E y G
                    UPMForms.claveVegetacion.setDatosIniciales(ceSitio);
                    UPMForms.claveVegetacion.setVisible(true);
                    break;
                case 15://A y G
                    UPMForms.claveVegetacion.setDatosIniciales(ceSitio);
                    UPMForms.claveVegetacion.setVisible(true);
                    break;
                case 16://Modulos A, C y G
                    UPMForms.claveVegetacion.setDatosIniciales(ceSitio);
                    UPMForms.claveVegetacion.setVisible(true);
                    break;
                    
                    
                case 17: //Modulos A, C, E, F y G
                    UPMForms.claveVegetacion.setDatosIniciales(ceSitio);
                    UPMForms.claveVegetacion.setVisible(true);
                    break;
                 case 18: //Modulos A y F
                    UPMForms.sotoBosque.setDatosIniciales(ceSitio);
                    UPMForms.sotoBosque.setVisible(true);
                    break;
                 case 19://Modulos A, E y F
                    UPMForms.sotoBosque.setDatosIniciales(ceSitio);
                    UPMForms.sotoBosque.setVisible(true);
                    break;
                 case 20: //Modulos A, C y E
                    UPMForms.claveVegetacion.setDatosIniciales(ceSitio);
                    UPMForms.claveVegetacion.setVisible(true);
                    break;
            }
            crearSecuencia(secuencia, upmID, sitio);
        }
    }//GEN-LAST:event_btnComenzarCapturaActionPerformed

    private void cmbUPMIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbUPMIDActionPerformed
        Integer upmID = (Integer) cmbUPMID.getSelectedItem();
        Integer sitio = (Integer) cmbSitios.getSelectedItem();
        if (cmbUPMID.getSelectedItem() != null) {
            combo.reiniciarComboModel(cmbSitios);
            fillCmbSitio(upmID);
            cmbSitios.setEnabled(true);
        } else {
            combo.reiniciarComboModel(cmbSitios);
            cmbSitios.setSelectedItem(null);
            cmbSitios.setEnabled(false);
        }
        if (cmbUPMID.getSelectedItem() != null && cmbSitios.getSelectedItem() != null) {
            btnComenzarCaptura.setEnabled(true);
        } else {
            btnComenzarCaptura.setEnabled(false);
        }
    }//GEN-LAST:event_cmbUPMIDActionPerformed

    private void cmbSitiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSitiosActionPerformed
        if (cmbUPMID.getSelectedItem() != null && cmbSitios.getSelectedItem() != null) {
            btnComenzarCaptura.setEnabled(true);
        } else {
            btnComenzarCaptura.setEnabled(false);
        }
    }//GEN-LAST:event_cmbSitiosActionPerformed

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        this.combo.manipularBotonesMenuPrincipal(false);
    }//GEN-LAST:event_formInternalFrameClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnComenzarCaptura;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cmbSitios;
    private javax.swing.JComboBox cmbUPMID;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblLogoConafor;
    private javax.swing.JLabel lblSitio;
    private javax.swing.JLabel lblUPM;
    // End of variables declaration//GEN-END:variables
}
