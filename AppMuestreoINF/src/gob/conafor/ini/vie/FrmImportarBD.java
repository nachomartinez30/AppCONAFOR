package gob.conafor.ini.vie;

import gob.conafor.utils.FuncionesComunes;
import gob.conafor.utils.Version;
import java.awt.Cursor;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FrmImportarBD extends javax.swing.JInternalFrame {

    private FuncionesComunes funciones = new FuncionesComunes();
    private String ruta;
    private Version ver=new Version();
    private String version=ver.getVersion();

    public FrmImportarBD() {
        initComponents();
        this.setLocation(300, 110);
    }

    public void iniciar() {
        btgTipoImportacion.clearSelection();
        funciones.manipularBotonesMenuPrincipal(true);
        //pbExportacion.removeAll();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btgTipoImportacion = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        lblLogoConafor = new javax.swing.JLabel();
        txtUbicacion = new javax.swing.JTextField();
        lblArchivo = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        pbExportacion = new javax.swing.JProgressBar();
        btnEjecutar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        lblEstatus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("Importación de datos del INFYS "+version);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/gob/conafor/utils/logo_conafor.png"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lblLogoConafor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogoConafor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gob/conafor/utils/CONAFOR_231_100px.png"))); // NOI18N

        txtUbicacion.setEnabled(false);

        lblArchivo.setText("Archivo");
        lblArchivo.setToolTipText("");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        pbExportacion.setForeground(new java.awt.Color(33, 98, 26));
        pbExportacion.setStringPainted(true);

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

        lblEstatus.setText("Importar base de datos");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblLogoConafor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pbExportacion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblArchivo)
                                .addGap(212, 212, 212))
                            .addComponent(txtUbicacion, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)))
                    .addComponent(lblEstatus, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEjecutar)
                .addGap(18, 18, 18)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(154, 154, 154))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addComponent(lblLogoConafor)
                .addGap(14, 14, 14)
                .addComponent(lblArchivo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEjecutar)
                    .addComponent(btnSalir))
                .addGap(7, 7, 7)
                .addComponent(lblEstatus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pbExportacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
        this.txtUbicacion.setText("");
        this.btnEjecutar.setEnabled(false);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        JFileChooser fcBaseDatos = new JFileChooser();
        fcBaseDatos.setDialogTitle("Base de datos a importar");
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.oct", "oct");
        fcBaseDatos.setAcceptAllFileFilterUsed(false);
        fcBaseDatos.setFileFilter(filtro);
        fcBaseDatos.showOpenDialog(Main.main);
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgTipoImportacion;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEjecutar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblArchivo;
    private javax.swing.JLabel lblEstatus;
    private javax.swing.JLabel lblLogoConafor;
    private static javax.swing.JProgressBar pbExportacion;
    private javax.swing.JTextField txtUbicacion;
    // End of variables declaration//GEN-END:variables

}
