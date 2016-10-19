package gob.conafor.reportes;

import gob.conafor.conn.dat.LocalConnection;
import gob.conafor.upm.vie.UPMForms;
import gob.conafor.utils.FuncionesComunes;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.swing.JRViewer;

public class FrmRptUPM extends javax.swing.JInternalFrame {
    
    private FuncionesComunes funcionesComunes = new FuncionesComunes();
    
    public FrmRptUPM() {
        initComponents();
    }
    
    public void llenarReporte(int upmID) {
        Path currentPath = Paths.get("");
        String path = currentPath.toAbsolutePath().toString();
        /*
        try {
            InputStream is = (InputStream) this.getClass().getClassLoader().getResourceAsStream("C:\\Users\\ecalderon\\Documents\\JasperStudio\\infys\\ColectaBotanica.jrxml");
            JasperDesign design = JRXmlLoader.load(is);
            JasperReport jcm = JasperCompileManager.compileReport(design);
            JasperPrint jp = JasperFillManager.fillReport(jcm, null, conn);
            JRViewer jr = new JRViewer(jp);
            pnlReporte.setLayout(new BorderLayout());
            pnlReporte.add(jr);
            pnlReporte.revalidate();
            is.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al llenar el reporte de  " + e.getClass().getName() + " : " + e.getMessage(),
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } */
        try {
            Map upmParametros = new HashMap();
            BufferedImage logoConafor = ImageIO.read(getClass().getResource("/gob/conafor/reportes/CONAFOR_231_100px.png"));
            BufferedImage logoSemarnat = ImageIO.read(getClass().getResource("/gob/conafor/reportes/LOGO_SEMARNAT.jpg"));
            File informacionGeneral = new File("src/gob/conafor/reportes/informacion_general.jasper");
            File informacionContacto = new File("src/gob/conafor/reportes/upm_contacto.jasper");
            File puntoControl = new File("src/gob/conafor/reportes/ubicacion_punto_control.jasper");
            File accesibilidadUPM = new File ("src/gob/conafor/reportes/accesibilidad_al_conglomerado.jasper");

            upmParametros.put("UPMID", upmID);
            upmParametros.put("logoConafor", logoConafor);
            upmParametros.put("logoSemarnat", logoSemarnat);
            upmParametros.put("informacionGeneral", informacionGeneral.getAbsolutePath());
            upmParametros.put("informacionContacto", informacionContacto.getAbsolutePath());
            upmParametros.put("puntoControl", puntoControl.getAbsolutePath());
            upmParametros.put("accesibilidadUPM", accesibilidadUPM.getAbsolutePath());
            
            Connection conn = LocalConnection.getConnection();
            File fileReporteColecta = new File("src/gob/conafor/reportes/upm.jrxml");
            JasperReport jr = JasperCompileManager.compileReport(fileReporteColecta.getAbsolutePath());
          
            JasperPrint jp = JasperFillManager.fillReport(jr, upmParametros, conn);
            JRViewer jv = new JRViewer(jp);
            pnlReporte.setLayout(new BorderLayout());
            pnlReporte.add(jv);
            pnlReporte.revalidate();
            conn.close();
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null,
                    "Error! no se pudo obtener el reporte de UPM'S levantados " + e.getClass().getName() + " : " + e.getMessage(),
                    "Reportes", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlReporte = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnSalir = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("Reporte de colecta botánica");
        setPreferredSize(new java.awt.Dimension(940, 649));

        pnlReporte.setBackground(new java.awt.Color(204, 204, 204));
        pnlReporte.setPreferredSize(new java.awt.Dimension(940, 650));

        javax.swing.GroupLayout pnlReporteLayout = new javax.swing.GroupLayout(pnlReporte);
        pnlReporte.setLayout(pnlReporteLayout);
        pnlReporteLayout.setHorizontalGroup(
            pnlReporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 924, Short.MAX_VALUE)
        );
        pnlReporteLayout.setVerticalGroup(
            pnlReporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 566, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(361, 361, 361))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalir)
                    .addComponent(btnRegresar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlReporte, javax.swing.GroupLayout.DEFAULT_SIZE, 924, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 566, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.hide();
        funcionesComunes.manipularBotonesMenuPrincipal(false);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        this.hide();
        UPMForms.reportes.setVisible(true);
    }//GEN-LAST:event_btnRegresarActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel pnlReporte;
    // End of variables declaration//GEN-END:variables
}
