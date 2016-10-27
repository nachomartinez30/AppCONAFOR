
package concentrarbdinfys;

//import gob.conafor.utils.FuncionesComunes;
import java.awt.Cursor;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Concentrar extends javax.swing.JFrame {

    private String ruta;
    
    public Concentrar() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtUbicacion = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnEjecutar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        pbExportacion = new javax.swing.JProgressBar();
        lblEstatus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtUbicacion.setEnabled(false);

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

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

        pbExportacion.setForeground(new java.awt.Color(33, 98, 26));
        pbExportacion.setStringPainted(true);

        lblEstatus.setText("Importar base de datos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pbExportacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtUbicacion, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEjecutar)
                        .addGap(18, 18, 18)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(144, 144, 144))
                    .addComponent(lblEstatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(153, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEjecutar)
                    .addComponent(btnSalir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEstatus)
                .addGap(7, 7, 7)
                .addComponent(pbExportacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        JFileChooser fcBaseDatos = new JFileChooser();
        fcBaseDatos.setDialogTitle("Base de datos a importar");
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.oct", "oct");
        fcBaseDatos.setAcceptAllFileFilterUsed(false);
        fcBaseDatos.setFileFilter(filtro);
        fcBaseDatos.showOpenDialog(this);
        //fcBaseDatos.showOpenDialog(Main.main);
        try {
            File baseDatos = fcBaseDatos.getSelectedFile();
            String ruta = baseDatos.getAbsolutePath();
            int tamanio = ruta.length();
            int cadena = tamanio - 3;
            String extension = ruta.substring(cadena, tamanio);
            if (!extension.equals("oct")) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un base de datos valida a importar"
                    + "", "Importación", JOptionPane.INFORMATION_MESSAGE);
                txtUbicacion.setText("");
            } else {
                txtUbicacion.setText(ruta);
                this.ruta = ruta;
                btnEjecutar.setEnabled(true);
            }
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "El archivo que intenta importar no es una base de datos balida" + e);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnEjecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEjecutarActionPerformed
        System.out.println(this.ruta);
        HiloImportacion hiloImportacion = new HiloImportacion(this.lblEstatus, this.pbExportacion, this.btnEjecutar, this.btnSalir, this.ruta);
        hiloImportacion.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equalsIgnoreCase("progress")) {
                    setCursor(new Cursor(Cursor.WAIT_CURSOR));
                } else if (evt.getPropertyName().equalsIgnoreCase("state")) {
                    switch ((SwingWorker.StateValue) evt.getNewValue()) {
                        case DONE:
                        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
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
        hiloImportacion.execute();
        txtUbicacion.setText("");
    }//GEN-LAST:event_btnEjecutarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
        this.txtUbicacion.setText("");
        this.btnEjecutar.setEnabled(false);
    }//GEN-LAST:event_btnSalirActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Concentrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Concentrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Concentrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Concentrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Concentrar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEjecutar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel lblEstatus;
    private static javax.swing.JProgressBar pbExportacion;
    private javax.swing.JTextField txtUbicacion;
    // End of variables declaration//GEN-END:variables
}
