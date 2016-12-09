package gob.conafor.ini.vie;

import gob.conafor.sitio.mod.CDSitio;
import gob.conafor.sitio.mod.CESitio;
import gob.conafor.sys.mod.CDSecuencia;
import gob.conafor.sys.mod.CDSeguimientoUPM;
import gob.conafor.upm.mod.CDUpm;
import gob.conafor.upm.mod.CEUPM;
import gob.conafor.upm.vie.UPMForms;
import gob.conafor.utils.FuncionesComunes;
import gob.conafor.utils.Tablas;
import gob.conafor.utils.Version;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class FrmRevisionModulos extends javax.swing.JInternalFrame {

    CDSeguimientoUPM seguimiento = new CDSeguimientoUPM();
    private int upmID;
    private CEUPM ceUpm = new CEUPM();
    private CDUpm cdUpm = new CDUpm();
    private CDSitio cdSitio = new CDSitio();
    private FuncionesComunes combo = new FuncionesComunes();
    private CDSecuencia cdSecuencia = new CDSecuencia();
    private CESitio ceSitio = new CESitio();
    private Version ver=new Version();
    private String version=ver.getVersion();

    public FrmRevisionModulos() {
        initComponents();
        this.setLocation(100, 0);
        fillUPMID();
        cmbUPMID.setSelectedItem(null);
        cmbSitios.setSelectedItem(null);
    }

    public void iniciarRevision() {
        this.setLocation(100, 0);
        combo.reiniciarComboModel(cmbUPMID);
        fillUPMID();
        cmbUPMID.setSelectedItem(null);
        cmbSitios.setSelectedItem(null);
        cmbSitios.setEnabled(false);
    }

    public void manipularBonesMenuprincipal() {
        combo.manipularBotonesMenuPrincipal(true);
    }

    private void fillUPMID() {
       cmbUPMID.removeAllItems();
        List<Integer> listCapturado = new ArrayList<>();
        listCapturado = this.cdSecuencia.getUPMRevision();
        if (listCapturado != null) {
            int size = listCapturado.size();
            for (int i = 0; i < size; i++) {
                cmbUPMID.addItem(listCapturado.get(i));
            }
        }
    }

    private void fillCmbSitio(int upmID) {
        cmbSitios.removeAllItems();
        List<Integer> listSitios = new ArrayList<>();
        listSitios = this.cdSecuencia.getSitiosRevision(upmID);
        if (listSitios != null) {
            int size = listSitios.size();
            for (int i = 0; i < size; i++) {
                cmbSitios.addItem(listSitios.get(i));
            }
        }
    }

    private void mostrarFormato(int formatoID, int secuenciaID) {
        switch (formatoID) {
            case 1:
                if (secuenciaID == 3 || secuenciaID > 9) {
                    UPMForms.sotoBosque.desabilitarSotobosque();
                } else {
                    UPMForms.sotoBosque.habilitarSotobosque();
                }
                UPMForms.sotoBosque.setVisible(true);
                UPMForms.sotoBosque.revisarSotoBosque(this.ceSitio);
                break;
            case 2:
                UPMForms.repoblado.setVisible(true);
                UPMForms.repoblado.revisarRepoblado(this.ceSitio);
                break;
            case 3:
                UPMForms.arbolado.setVisible(true);
                UPMForms.arbolado.revisarArbolado(this.ceSitio);
                break;
            case 4:
                UPMForms.submuestra.setVisible(true);
                UPMForms.submuestra.revisarSubmuestra(this.ceSitio);
                break;
            case 5:
                UPMForms.claveVegetacion.setVisible(true);
                UPMForms.claveVegetacion.revisarClaveVegetacion(this.ceSitio);
                break;
            case 6:
                UPMForms.caracteristicasUPM.setVisible(true);
                UPMForms.caracteristicasUPM.revisarCaracteristicasUPM(this.ceSitio);
                break;
            case 7:
                UPMForms.carbono.setVisible(true);
                UPMForms.carbono.revisarCarbono(this.ceSitio);
                break;
            case 8:
                UPMForms.longitud.setVisible(true);
                UPMForms.longitud.revisarLongitud(this.ceSitio);
                break;
            case 9:
                UPMForms.suelo.setVisible(true);
                UPMForms.suelo.revisarSuelo(this.ceSitio);
                break;
            case 10:
                UPMForms.condicionDegradacion.setVisible(true);
                UPMForms.condicionDegradacion.revisarCondicionDegradacion(this.ceSitio);
                break;
            case 11:
                UPMForms.erosionHidrica.setVisible(true);
                UPMForms.erosionHidrica.revisarErosionHidrica(this.ceSitio);
                break;
            case 12:
                UPMForms.deformacionTerreno.setVisible(true);
                UPMForms.deformacionTerreno.revisarDeformacionViento(this.ceSitio);
                break;
            case 13:
                UPMForms.observaciones.setVisible(true);
                UPMForms.observaciones.revisarObservaciones(this.ceSitio);
                break;
            case 14:
                UPMForms.arboladoD.setVisible(true);
                UPMForms.arboladoD.revisarArboladoD(this.ceSitio);
                break;
            case 15:
                UPMForms.hojarascaProfundidad.setVisible(true);
                UPMForms.hojarascaProfundidad.revisarHojarascaProfundidad(this.ceSitio);
                break;
            case 16:
                UPMForms.muestrasPerfil.setVisible(true);
                UPMForms.muestrasPerfil.revisarMuestrasPerfil(this.ceSitio);
                break;
            case 17:
                UPMForms.fotoHemisferica.setVisible(true);
                UPMForms.fotoHemisferica.revisarFotoHemisferica(this.ceSitio);
                break;
            case 18:
                UPMForms.parametrosFQ.setVisible(true);
                UPMForms.parametrosFQ.continuarParametrosFQ(this.ceSitio);
                break;
            case 19:
                UPMForms.repobladoVM.setVisible(true);
                UPMForms.repobladoVM.revisarRepobladoVM(this.ceSitio);
                break;
            case 20:
                UPMForms.arboladoG.setVisible(true);
                UPMForms.arboladoG.revisarArboladoG(this.ceSitio);
                break;
            case 21:
                UPMForms.vegetacionMenor.setVisible(true);
                UPMForms.vegetacionMenor.revisarVegetacionMenor(this.ceSitio);
                break;
            case 22:
                UPMForms.vegetacionMayorI.setVisible(true);
                UPMForms.vegetacionMayorI.continuarVegetacionMayorIndividual(this.ceSitio);
                break;
            case 23:
                UPMForms.vegetacionMayorG.setVisible(true);
                UPMForms.vegetacionMayorG.revisarVegetacionMayorGregarios(this.ceSitio);
                break;
        }
    }

    private void llenarTablaSeguimiento(int upm, int sitio){
      grdSecuencia.setModel(cdSecuencia.getTablaSecuenciaCapturaRevision(upm, sitio));
      grdSecuencia.getColumnModel().getColumn(2).setPreferredWidth(70);
      grdSecuencia.getColumnModel().getColumn(3).setPreferredWidth(50);
      grdSecuencia.getColumnModel().getColumn(4).setPreferredWidth(150);
      grdSecuencia.getColumnModel().getColumn(5).setPreferredWidth(50);
      Tablas tabla = new Tablas();
        int[] column_0 = {0};
        int[] column_1 = {1};
        tabla.hideColumnTable(grdSecuencia, column_0);
        tabla.hideColumnTable(grdSecuencia, column_1);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        btnSalir = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        grdSecuencia = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cmbUPMID = new javax.swing.JComboBox();
        lblSitio = new javax.swing.JLabel();
        cmbSitios = new javax.swing.JComboBox();
        lblLogoConafor = new javax.swing.JLabel();

        setTitle("Lista de formatos capturados por Sitio "+version);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/gob/conafor/utils/logo_conafor.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(770, 650));
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

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(293, 293, 293))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSalir)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        grdSecuencia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        grdSecuencia.setToolTipText("Da click sobre el formato a revisar");
        grdSecuencia.setRowHeight(20);
        grdSecuencia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grdSecuenciaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(grdSecuencia);

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Seleccione un formato para revisión");
        jLabel2.setOpaque(true);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("UPMID:");

        cmbUPMID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbUPMIDActionPerformed(evt);
            }
        });

        lblSitio.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblSitio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblSitio.setText("Sitio:");

        cmbSitios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSitiosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmbUPMID, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(263, 263, 263)
                .addComponent(lblSitio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbSitios, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbSitios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSitio))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbUPMID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)))
                .addGap(28, 28, 28))
        );

        lblLogoConafor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gob/conafor/utils/CONAFOR_231_100px.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 691, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 39, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLogoConafor))
                .addGap(270, 270, 270))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(lblLogoConafor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void cmbSitiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSitiosActionPerformed
        Integer upm = (Integer) cmbUPMID.getSelectedItem();
        Integer sitio = (Integer) cmbSitios.getSelectedItem();
        DefaultTableModel model = (DefaultTableModel) grdSecuencia.getModel();
        model.setRowCount(0);
        if (cmbSitios.getSelectedItem() != null) {
            llenarTablaSeguimiento(upm, sitio);
        } else {
            model.setRowCount(0);
        }
    }//GEN-LAST:event_cmbSitiosActionPerformed

    private void cmbUPMIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbUPMIDActionPerformed
        if (cmbUPMID.getSelectedItem() != null) {
            Integer upm = (Integer) cmbUPMID.getSelectedItem();
            this.upmID = upm;
            combo.reiniciarComboModel(cmbSitios);
            fillCmbSitio(upmID);
            cmbSitios.setEnabled(true);
        } else {
            cmbSitios.setEnabled(false);
            cmbSitios.setSelectedItem(null);
        }
    }//GEN-LAST:event_cmbUPMIDActionPerformed

    private void grdSecuenciaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grdSecuenciaMouseClicked
        if (evt.getButton() == 1) {
            Integer upm = (Integer)cmbUPMID.getSelectedItem();
            Integer sitio = (Integer)cmbSitios.getSelectedItem();
            Integer sitioID = this.cdSitio.getSitioID(upm, sitio);
            this.ceSitio.setUpmID(upm);
            this.ceSitio.setSitio(sitio);
            this.ceSitio.setSitioID(sitioID);
            this.ceSitio.setSecuencia(cdSecuencia.getSecuencia(upm));
            int fila = grdSecuencia.getSelectedRow();
            String formatoID = grdSecuencia.getValueAt(fila, 1).toString();
            this.hide();
            mostrarFormato(Integer.parseInt(formatoID), cdSecuencia.getSecuencia(upm));
        }
    }//GEN-LAST:event_grdSecuenciaMouseClicked

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        this.combo.manipularBotonesMenuPrincipal(false);
    }//GEN-LAST:event_formInternalFrameClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cmbSitios;
    private javax.swing.JComboBox cmbUPMID;
    private javax.swing.JTable grdSecuencia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblLogoConafor;
    private javax.swing.JLabel lblSitio;
    // End of variables declaration//GEN-END:variables
}
