package gob.conafor.ini.vie;

import gob.conafor.upm.mod.CDUpm;
import gob.conafor.utils.FuncionesComunes;
import java.awt.Cursor;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

public class FrmEliminarUPM extends javax.swing.JInternalFrame {

    private FuncionesComunes funciones = new FuncionesComunes();
    private CDUpm cdUpm = new CDUpm();
    
    public FrmEliminarUPM() {
        initComponents();
        this.setLocation(300, 110);
    }
    
    private void fillUPMID() {
        cmbUPM.removeAll();
        List<Integer> listCapturado = new ArrayList<>();
        listCapturado = this.cdUpm.getUPMCapturados();
        if (listCapturado != null) {
            int size = listCapturado.size();
            for (int i = 0; i < size; i++) {
                cmbUPM.addItem(listCapturado.get(i));
            }
        }
    }
    
    public void iniciar(){
        funciones.manipularBotonesMenuPrincipal(true);
        fillUPMID();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblLogoConafor = new javax.swing.JLabel();
        lblEstatus = new javax.swing.JLabel();
        bpElminarBD = new javax.swing.JProgressBar();
        btnEjecutar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txtUPM = new javax.swing.JLabel();
        cmbUPM = new javax.swing.JComboBox();

        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/gob/conafor/utils/logo_conafor.png"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lblLogoConafor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogoConafor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gob/conafor/utils/CONAFOR_231_100px.png"))); // NOI18N

        lblEstatus.setText("Eliminar datos capturados");

        bpElminarBD.setForeground(new java.awt.Color(33, 98, 26));
        bpElminarBD.setStringPainted(true);

        btnEjecutar.setText("Ejecutar");
        btnEjecutar.setEnabled(false);
        btnEjecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEjecutarActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtUPM.setText("UPM:");

        cmbUPM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbUPMActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addComponent(txtUPM)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbUPM, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbUPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUPM))
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addComponent(btnEjecutar)
                        .addGap(18, 18, 18)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(131, 131, 131))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblLogoConafor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bpElminarBD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblEstatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLogoConafor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblEstatus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bpElminarBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEjecutar)
                    .addComponent(btnSalir))
                .addGap(23, 23, 23))
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
        this.btnEjecutar.setEnabled(false);
        cmbUPM.setSelectedItem(null);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnEjecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEjecutarActionPerformed
        Integer upm = (Integer) cmbUPM.getSelectedItem();
        Object[] opciones = {"Si", "No"};
        int respuesta = JOptionPane.showOptionDialog(null, "Esta a punto de eliminar toda la información del conglomerado " + upm + " seleccionado, ¿Esta seguro?",
                "Base de datos", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, opciones, opciones[1]);
        if (respuesta == JOptionPane.YES_OPTION) {
            HiloEliminacionUPM hiloEliminacion = new HiloEliminacionUPM(upm, this.cmbUPM, this.lblEstatus, this.bpElminarBD, this.btnEjecutar, this.btnSalir);
            hiloEliminacion.addPropertyChangeListener(new PropertyChangeListener() {
                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                    if (evt.getPropertyName().equalsIgnoreCase("progress")) {
                        setCursor(new Cursor(Cursor.WAIT_CURSOR));
                    } else if (evt.getPropertyName().equalsIgnoreCase("state")) {
                        switch ((SwingWorker.StateValue) evt.getNewValue()) {
                            case DONE:
                                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                                cmbUPM.removeAllItems();
                                fillUPMID();
                                break;
                            case STARTED:
                                setCursor(new Cursor(Cursor.WAIT_CURSOR));
                                break;
                            case PENDING:
                                break;
                        }
                    }
                }
            });
            hiloEliminacion.execute();
            
        } else {
            JOptionPane.showMessageDialog(null, "No se afecto la información de la base de datos");
        }
    }//GEN-LAST:event_btnEjecutarActionPerformed

    private void cmbUPMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbUPMActionPerformed
        if(cmbUPM.getSelectedItem() == null){
            btnEjecutar.setEnabled(false);
        } else {
            btnEjecutar.setEnabled(true);
        }
    }//GEN-LAST:event_cmbUPMActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar bpElminarBD;
    private javax.swing.JButton btnEjecutar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cmbUPM;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblEstatus;
    private javax.swing.JLabel lblLogoConafor;
    private javax.swing.JLabel txtUPM;
    // End of variables declaration//GEN-END:variables
}
