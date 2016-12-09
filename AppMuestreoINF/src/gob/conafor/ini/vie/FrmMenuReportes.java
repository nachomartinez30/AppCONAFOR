package gob.conafor.ini.vie;

import gob.conafor.sitio.mod.CDSitio;
import gob.conafor.taxonomia.mod.CDColectaBotanica;
import gob.conafor.upm.vie.UPMForms;
import gob.conafor.utils.FuncionesComunes;
import gob.conafor.utils.Version;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class FrmMenuReportes extends javax.swing.JInternalFrame {

    private FuncionesComunes funcionesComunes = new FuncionesComunes();
    private CDSitio cdSitio = new CDSitio();
    private CDColectaBotanica cdColecta = new CDColectaBotanica();
    private Version ver=new Version();
    private String version=ver.getVersion();
    
    public FrmMenuReportes() {
        initComponents();
        this.setLocation(300, 110);
    }
    
    private void fillCmbUPMS() {
        List<Integer> listUPMS = new ArrayList<>();
        listUPMS = cdSitio.getUPMSitios();
        if (listUPMS != null) {
            int size = listUPMS.size();
            for (int i = 0; i < size; i++) {
                cmbUPM.addItem(listUPMS.get(i));
            }
        }
    }
    
    public void setDatosIniciales() {
        this.funcionesComunes.manipularBotonesMenuPrincipal(true);
        funcionesComunes.reiniciarComboModel(this.cmbUPM);
        fillCmbUPMS();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgTipoReporte = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        lblLogoConafor = new javax.swing.JLabel();
        lblReportesCapturas = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        cmbUPM = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        rbtUPM = new javax.swing.JRadioButton();
        rbtTodo = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        btnUPM = new javax.swing.JButton();
        btnSitios = new javax.swing.JButton();
        btnModulos = new javax.swing.JButton();
        btnColectaBotanica = new javax.swing.JButton();
        btnColectaSuelo = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setTitle("Reportes impresos "+version);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/gob/conafor/utils/logo_conafor.png"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lblLogoConafor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gob/conafor/utils/CONAFOR_231_100px.png"))); // NOI18N

        lblReportesCapturas.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblReportesCapturas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblReportesCapturas.setText("Reportes de la captura realizada");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cmbUPM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbUPMActionPerformed(evt);
            }
        });

        jLabel1.setText("UPMID:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbUPM, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbUPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        rbtUPM.setBackground(new java.awt.Color(255, 255, 255));
        bgTipoReporte.add(rbtUPM);
        rbtUPM.setSelected(true);
        rbtUPM.setText("Por UPM");
        rbtUPM.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        rbtTodo.setBackground(new java.awt.Color(255, 255, 255));
        bgTipoReporte.add(rbtTodo);
        rbtTodo.setText("Todo");
        rbtTodo.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnUPM.setText("UPM");
        btnUPM.setEnabled(false);
        btnUPM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUPMActionPerformed(evt);
            }
        });

        btnSitios.setText("Sitios");
        btnSitios.setEnabled(false);

        btnModulos.setText("Módulos");
        btnModulos.setEnabled(false);

        btnColectaBotanica.setText("Colecta botánica");
        btnColectaBotanica.setEnabled(false);
        btnColectaBotanica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColectaBotanicaActionPerformed(evt);
            }
        });

        btnColectaSuelo.setText("Colecta suelo");
        btnColectaSuelo.setEnabled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnUPM, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSitios, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnModulos))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnColectaBotanica)
                        .addGap(18, 18, 18)
                        .addComponent(btnColectaSuelo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUPM)
                    .addComponent(btnSitios)
                    .addComponent(btnModulos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnColectaBotanica)
                    .addComponent(btnColectaSuelo))
                .addContainerGap())
        );

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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(lblLogoConafor))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblReportesCapturas, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(rbtUPM)
                        .addGap(18, 18, 18)
                        .addComponent(rbtTodo))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLogoConafor)
                .addGap(18, 18, 18)
                .addComponent(lblReportesCapturas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtUPM)
                    .addComponent(rbtTodo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSalir)
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnColectaBotanicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnColectaBotanicaActionPerformed
        int upmID = (Integer) cmbUPM.getSelectedItem();
        UPMForms.rptColectaBotanica.setVisible(true);
        UPMForms.rptColectaBotanica.llenarReporte(upmID);
        cmbUPM.setSelectedItem(null);
        this.hide();
    }//GEN-LAST:event_btnColectaBotanicaActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.hide();
        cmbUPM.setSelectedItem(null);
        this.funcionesComunes.manipularBotonesMenuPrincipal(false);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void cmbUPMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbUPMActionPerformed
        if (cmbUPM.getSelectedItem() == null) {
            /* btnUPM.setEnabled(false);
            btnSitios.setEnabled(false);
            btnModulos.setEnabled(false);*/
            btnColectaBotanica.setEnabled(false);
        } else /*btnUPM.setEnabled(true);
            btnSitios.setEnabled(true);
            btnModulos.setEnabled(true);*/ {
            if (cdColecta.hayColecta()) {
                btnColectaBotanica.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(null, "No se han capturado datos de colecta botánica");
            }
        }
    }//GEN-LAST:event_cmbUPMActionPerformed

    private void btnUPMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUPMActionPerformed
        Integer upmID = (Integer) cmbUPM.getSelectedItem();
        UPMForms.rptUpm.setVisible(true);
        UPMForms.rptUpm.llenarReporte(upmID);
    }//GEN-LAST:event_btnUPMActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgTipoReporte;
    private javax.swing.JButton btnColectaBotanica;
    private javax.swing.JButton btnColectaSuelo;
    private javax.swing.JButton btnModulos;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnSitios;
    private javax.swing.JButton btnUPM;
    private javax.swing.JComboBox<Integer> cmbUPM;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblLogoConafor;
    private javax.swing.JLabel lblReportesCapturas;
    private javax.swing.JRadioButton rbtTodo;
    private javax.swing.JRadioButton rbtUPM;
    // End of variables declaration//GEN-END:variables
}
