package gob.conafor.ini.vie;

import gob.conafor.sys.mod.CDSecuencia;
import gob.conafor.sys.mod.CDSeguimientoUPM;
import gob.conafor.upm.vie.UPMForms;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

public class FrmInicio extends javax.swing.JFrame {

    private int upm;
    public static FrmInicio principal;
    private CDSeguimientoUPM seguimiento = new CDSeguimientoUPM();
    private CDSecuencia navegacion = new CDSecuencia();

    public FrmInicio() {
        initComponents();
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension ventana = this.getSize();
        this.setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);
        btnCrearSitio.setEnabled(navegacion.habilitarCapturaSitio());
        btnCapturarModulos.setEnabled(navegacion.habilitarCapturaModulos());
        btnContinuarModulos.setEnabled(navegacion.habilitarContinuarModulos());
        btnColectaBotanica.setEnabled(navegacion.habilitarColectaBotanica());
        btnRevisarUPM.setEnabled(navegacion.habilitarRevisarUPM());
        btnRevisionSitios.setEnabled(navegacion.habilitarCapturaModulos());
        btnRevisarModulos.setEnabled(navegacion.habilitarContinuarModulos());
        btnVerReportes.setEnabled(navegacion.habilitarCapturaModulos());
        dpPrincipal.add(UPMForms.informacionGeneralUPM);
        dpPrincipal.add(UPMForms.puntoControlUPM);
        dpPrincipal.add(UPMForms.inaccesibleUPM);
        dpPrincipal.add(UPMForms.ubicacionSitio);
        dpPrincipal.add(UPMForms.sotoBosque);
        dpPrincipal.add(UPMForms.repoblado);
        dpPrincipal.add(UPMForms.arbolado);
        dpPrincipal.add(UPMForms.submuestra);
        dpPrincipal.add(UPMForms.claveVegetacion);
        dpPrincipal.add(UPMForms.caracteristicasUPM);
        dpPrincipal.add(UPMForms.carbono);
        dpPrincipal.add(UPMForms.longitud);
        dpPrincipal.add(UPMForms.suelo);
        dpPrincipal.add(UPMForms.condicionDegradacion);
        dpPrincipal.add(UPMForms.erosionHidrica);
        dpPrincipal.add(UPMForms.deformacionTerreno);
        dpPrincipal.add(UPMForms.observaciones);
        dpPrincipal.add(UPMForms.arboladoD);
        dpPrincipal.add(UPMForms.hojarascaProfundidad);
        dpPrincipal.add(UPMForms.muestrasPerfil);
        dpPrincipal.add(UPMForms.fotoHemisferica);
        dpPrincipal.add(UPMForms.parametrosFQ);
        dpPrincipal.add(UPMForms.repobladoVM);
        dpPrincipal.add(UPMForms.arboladoG);
        dpPrincipal.add(UPMForms.vegetacionMenor);
        dpPrincipal.add(UPMForms.vegetacionMayorI);
        dpPrincipal.add(UPMForms.vegetacionMayorG);
        dpPrincipal.add(UPMForms.revisionUPM);
        dpPrincipal.add(UPMForms.revisionSitio);
        dpPrincipal.add(UPMForms.sitioRevisio);
        dpPrincipal.add(UPMForms.capturarModulos);
        dpPrincipal.add(UPMForms.revisionModulos);
        dpPrincipal.add(UPMForms.continuarModulos);
        dpPrincipal.add(UPMForms.importarBD);
        dpPrincipal.add(UPMForms.colectaBotanica);
        dpPrincipal.add(UPMForms.reportes);
        dpPrincipal.add(UPMForms.rptUpm);
        dpPrincipal.add(UPMForms.rptColectaBotanica);
        dpPrincipal.add(UPMForms.baseDatos);
        dpPrincipal.add(UPMForms.eliminarBD);
        dpPrincipal.add(UPMForms.eliminarUPM);
       // btnVerReportes.setVisible(false);
    }
    
     public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("gob/conafor/utils/logo_conafor.png"));
        return retValue;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        dpPrincipal = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        dpMenuLateral = new javax.swing.JDesktopPane();
        btnCrearUPM = new javax.swing.JButton();
        btnCapturarModulos = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnCrearSitio = new javax.swing.JButton();
        btnRevisarUPM = new javax.swing.JButton();
        btnRevisionSitios = new javax.swing.JButton();
        btnRevisarModulos = new javax.swing.JButton();
        btnColectaBotanica = new javax.swing.JButton();
        btnVerReportes = new javax.swing.JButton();
        btnContinuarModulos = new javax.swing.JButton();
        mbMenuSuperiror = new javax.swing.JMenuBar();
        menGuardarArchivo = new javax.swing.JMenu();
        miImportar = new javax.swing.JMenuItem();
        miConentrar = new javax.swing.JMenuItem();
        miEliminarPorUPM = new javax.swing.JMenuItem();
        miEliminar = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        miSalir = new javax.swing.JMenuItem();

        jMenuItem2.setText("jMenuItem2");

        jMenuItem3.setText("jMenuItem3");

        jMenuItem4.setText("jMenuItem4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cliente de captura INFyS ");
        setIconImage(getIconImage());
        setIconImages(getIconImages());
        setName("Index"); // NOI18N
        setResizable(false);

        dpPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        dpPrincipal.setAutoscrolls(true);
        dpPrincipal.setPreferredSize(new java.awt.Dimension(939, 838));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gob/conafor/utils/CONAFOR.png"))); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gob/conafor/utils/logoSEMARNAT_hoz.png"))); // NOI18N

        jLabel3.setBackground(new java.awt.Color(90, 90, 90));
        jLabel3.setForeground(new java.awt.Color(10, 9, 9));
        jLabel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel3.setOpaque(true);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gob/conafor/utils/Titulo_Gerencia.png"))); // NOI18N

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gob/conafor/utils/Arbol.png"))); // NOI18N

        jLabel6.setBackground(new java.awt.Color(90, 90, 90));
        jLabel6.setForeground(new java.awt.Color(10, 9, 9));
        jLabel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel6.setOpaque(true);

        jLabel7.setText("Versión 1.3.1");

        dpPrincipal.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dpPrincipal.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dpPrincipal.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dpPrincipal.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dpPrincipal.setLayer(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dpPrincipal.setLayer(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dpPrincipal.setLayer(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout dpPrincipalLayout = new javax.swing.GroupLayout(dpPrincipal);
        dpPrincipal.setLayout(dpPrincipalLayout);
        dpPrincipalLayout.setHorizontalGroup(
            dpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dpPrincipalLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(50, 50, 50))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dpPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dpPrincipalLayout.createSequentialGroup()
                .addContainerGap(139, Short.MAX_VALUE)
                .addGroup(dpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dpPrincipalLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 708, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(98, 98, 98))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dpPrincipalLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(295, 295, 295)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dpPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        dpPrincipalLayout.setVerticalGroup(
            dpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dpPrincipalLayout.createSequentialGroup()
                .addGroup(dpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dpPrincipalLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dpPrincipalLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        dpMenuLateral.setBackground(new java.awt.Color(204, 204, 204));
        dpMenuLateral.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnCrearUPM.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnCrearUPM.setText("Crear UPM");
        btnCrearUPM.setToolTipText("");
        btnCrearUPM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearUPMActionPerformed(evt);
            }
        });

        btnCapturarModulos.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnCapturarModulos.setText("Capturar Módulos");
        btnCapturarModulos.setEnabled(false);
        btnCapturarModulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapturarModulosActionPerformed(evt);
            }
        });

        btnSalir.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnCrearSitio.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnCrearSitio.setText("Crear Sitios");
        btnCrearSitio.setEnabled(false);
        btnCrearSitio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearSitioActionPerformed(evt);
            }
        });

        btnRevisarUPM.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnRevisarUPM.setText("Revisar UPM");
        btnRevisarUPM.setToolTipText("");
        btnRevisarUPM.setEnabled(false);
        btnRevisarUPM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRevisarUPMActionPerformed(evt);
            }
        });

        btnRevisionSitios.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnRevisionSitios.setText("Revisar Sitios");
        btnRevisionSitios.setEnabled(false);
        btnRevisionSitios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRevisionSitiosActionPerformed(evt);
            }
        });

        btnRevisarModulos.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnRevisarModulos.setText("Revisar Módulos");
        btnRevisarModulos.setEnabled(false);
        btnRevisarModulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRevisarModulosActionPerformed(evt);
            }
        });

        btnColectaBotanica.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnColectaBotanica.setText("Colecta Botánica");
        btnColectaBotanica.setEnabled(false);
        btnColectaBotanica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColectaBotanicaActionPerformed(evt);
            }
        });

        btnVerReportes.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnVerReportes.setText("Ver Reportes");
        btnVerReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerReportesActionPerformed(evt);
            }
        });

        btnContinuarModulos.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnContinuarModulos.setText("Continuar Módulos");
        btnContinuarModulos.setEnabled(false);
        btnContinuarModulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinuarModulosActionPerformed(evt);
            }
        });

        dpMenuLateral.setLayer(btnCrearUPM, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dpMenuLateral.setLayer(btnCapturarModulos, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dpMenuLateral.setLayer(btnSalir, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dpMenuLateral.setLayer(btnCrearSitio, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dpMenuLateral.setLayer(btnRevisarUPM, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dpMenuLateral.setLayer(btnRevisionSitios, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dpMenuLateral.setLayer(btnRevisarModulos, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dpMenuLateral.setLayer(btnColectaBotanica, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dpMenuLateral.setLayer(btnVerReportes, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dpMenuLateral.setLayer(btnContinuarModulos, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout dpMenuLateralLayout = new javax.swing.GroupLayout(dpMenuLateral);
        dpMenuLateral.setLayout(dpMenuLateralLayout);
        dpMenuLateralLayout.setHorizontalGroup(
            dpMenuLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dpMenuLateralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dpMenuLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCrearUPM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCapturarModulos, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCrearSitio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRevisarUPM, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRevisionSitios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRevisarModulos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                    .addComponent(btnColectaBotanica, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                    .addComponent(btnVerReportes, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                    .addComponent(btnContinuarModulos, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE))
                .addContainerGap())
        );
        dpMenuLateralLayout.setVerticalGroup(
            dpMenuLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dpMenuLateralLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(btnCrearUPM, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCrearSitio, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCapturarModulos, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnContinuarModulos, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btnColectaBotanica, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(btnRevisarUPM, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRevisionSitios, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRevisarModulos, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVerReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mbMenuSuperiror.setBorder(null);
        mbMenuSuperiror.setPreferredSize(new java.awt.Dimension(89, 39));

        menGuardarArchivo.setBackground(new java.awt.Color(153, 153, 153));
        menGuardarArchivo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        menGuardarArchivo.setMnemonic('f');
        menGuardarArchivo.setText("Datos");
        menGuardarArchivo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        menGuardarArchivo.setPreferredSize(new java.awt.Dimension(47, 15));
        menGuardarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menGuardarArchivoActionPerformed(evt);
            }
        });

        miImportar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        miImportar.setMnemonic('s');
        miImportar.setText("Importar");
        miImportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miImportarActionPerformed(evt);
            }
        });
        menGuardarArchivo.add(miImportar);

        miConentrar.setText("Concentrar");
        miConentrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miConentrarActionPerformed(evt);
            }
        });
        menGuardarArchivo.add(miConentrar);

        miEliminarPorUPM.setText("Eliminar por UPM ...");
        miEliminarPorUPM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miEliminarPorUPMActionPerformed(evt);
            }
        });
        menGuardarArchivo.add(miEliminarPorUPM);

        miEliminar.setText("Eliminar todo ....");
        miEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miEliminarActionPerformed(evt);
            }
        });
        menGuardarArchivo.add(miEliminar);
        menGuardarArchivo.add(jSeparator1);

        miSalir.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        miSalir.setMnemonic('x');
        miSalir.setText("Salir");
        miSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miSalirActionPerformed(evt);
            }
        });
        menGuardarArchivo.add(miSalir);

        mbMenuSuperiror.add(menGuardarArchivo);

        setJMenuBar(mbMenuSuperiror);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(dpMenuLateral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dpPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 945, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dpMenuLateral)
            .addComponent(dpPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 662, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void miSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_miSalirActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnCrearUPMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearUPMActionPerformed
        UPMForms.informacionGeneralUPM.setVisible(true);
        UPMForms.informacionGeneralUPM.iniciarCaptura();
    }//GEN-LAST:event_btnCrearUPMActionPerformed

    private void btnCrearSitioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearSitioActionPerformed
        UPMForms.ubicacionSitio.setVisible(true);
        UPMForms.ubicacionSitio.setdatosIniciales();
        //UPMForms.ubicacionSitio.manipularBotonesMenu();
    }//GEN-LAST:event_btnCrearSitioActionPerformed

    private void btnCapturarModulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapturarModulosActionPerformed
        UPMForms.capturarModulos.llenarControles();
        UPMForms.capturarModulos.setVisible(true);
        UPMForms.capturarModulos.manipularBonesMenuprincipal();
    }//GEN-LAST:event_btnCapturarModulosActionPerformed

    private void btnRevisarUPMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRevisarUPMActionPerformed
        UPMForms.revisionUPM.setVisible(true);
        UPMForms.revisionUPM.iniciar();
    }//GEN-LAST:event_btnRevisarUPMActionPerformed

    private void btnRevisionSitiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRevisionSitiosActionPerformed
        UPMForms.revisionSitio.iniciarRevisionSitios();
        UPMForms.revisionSitio.setVisible(true);
        UPMForms.revisionSitio.manipularBotonesMenu();
    }//GEN-LAST:event_btnRevisionSitiosActionPerformed

    private void btnRevisarModulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRevisarModulosActionPerformed
       UPMForms.revisionModulos.iniciarRevision();
       UPMForms.revisionModulos.setVisible(true);
       UPMForms.revisionModulos.manipularBonesMenuprincipal();
    }//GEN-LAST:event_btnRevisarModulosActionPerformed

    private void btnColectaBotanicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnColectaBotanicaActionPerformed
      UPMForms.colectaBotanica.setVisible(true);
      UPMForms.colectaBotanica.procesarColecta();
    }//GEN-LAST:event_btnColectaBotanicaActionPerformed

    private void btnVerReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerReportesActionPerformed
        UPMForms.reportes.setVisible(true);
        UPMForms.reportes.setDatosIniciales();
    }//GEN-LAST:event_btnVerReportesActionPerformed

    private void btnContinuarModulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuarModulosActionPerformed
        UPMForms.continuarModulos.continuarModulos();
        UPMForms.continuarModulos.setVisible(true);
        UPMForms.continuarModulos.manipularBonesMenuprincipal();
    }//GEN-LAST:event_btnContinuarModulosActionPerformed

    private void miImportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miImportarActionPerformed
        UPMForms.importarBD.iniciar();
        UPMForms.importarBD.setVisible(true);
    }//GEN-LAST:event_miImportarActionPerformed

    private void miEliminarPorUPMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miEliminarPorUPMActionPerformed
        UPMForms.eliminarUPM.iniciar();
        UPMForms.eliminarUPM.setVisible(true);
    }//GEN-LAST:event_miEliminarPorUPMActionPerformed

    private void menGuardarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menGuardarArchivoActionPerformed
      
    }//GEN-LAST:event_menGuardarArchivoActionPerformed

    private void miEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miEliminarActionPerformed
       UPMForms.eliminarBD.setVisible(true);
    }//GEN-LAST:event_miEliminarActionPerformed

    private void miConentrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miConentrarActionPerformed
        UPMForms.importarBD.iniciar();
        UPMForms.importarBD.setVisible(true);
    }//GEN-LAST:event_miConentrarActionPerformed

    /**
     * @param args the command line arguments
     */
  //  public static void main(String args[]) {

        /*try {
         for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
         if ("System".equals(info.getName())) {
         javax.swing.UIManager.setLookAndFeel(info.getClassName());
         break;
         }
         }
         } catch (ClassNotFoundException ex) {
         java.util.logging.Logger.getLogger(Layout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
         } catch (InstantiationException ex) {
         java.util.logging.Logger.getLogger(Layout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
         } catch (IllegalAccessException ex) {
         java.util.logging.Logger.getLogger(Layout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
         } catch (javax.swing.UnsupportedLookAndFeelException ex) {
         java.util.logging.Logger.getLogger(Layout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
         }*/
 /*       try {
            // Set System L&F
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException e) {
            java.util.logging.Logger.getLogger(Layout.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        } catch (ClassNotFoundException e) {
           java.util.logging.Logger.getLogger(Layout.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        } catch (InstantiationException e) {
            java.util.logging.Logger.getLogger(Layout.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        } catch (IllegalAccessException e) {
            java.util.logging.Logger.getLogger(Layout.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new FrmInicio().setVisible(true);
            }
        });

    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnCapturarModulos;
    public javax.swing.JButton btnColectaBotanica;
    public javax.swing.JButton btnContinuarModulos;
    public javax.swing.JButton btnCrearSitio;
    public javax.swing.JButton btnCrearUPM;
    public javax.swing.JButton btnRevisarModulos;
    public javax.swing.JButton btnRevisarUPM;
    public javax.swing.JButton btnRevisionSitios;
    private javax.swing.JButton btnSalir;
    public javax.swing.JButton btnVerReportes;
    private javax.swing.JDesktopPane dpMenuLateral;
    public javax.swing.JDesktopPane dpPrincipal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenuBar mbMenuSuperiror;
    public javax.swing.JMenu menGuardarArchivo;
    private javax.swing.JMenuItem miConentrar;
    private javax.swing.JMenuItem miEliminar;
    private javax.swing.JMenuItem miEliminarPorUPM;
    private javax.swing.JMenuItem miImportar;
    private javax.swing.JMenuItem miSalir;
    // End of variables declaration//GEN-END:variables

}
