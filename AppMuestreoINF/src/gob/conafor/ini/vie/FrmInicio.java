package gob.conafor.ini.vie;

import gob.conafor.concentrarbdinfys.ConcentradorAbies;
import gob.conafor.sys.mod.CDSecuencia;
import gob.conafor.sys.mod.CDSeguimientoUPM;
import gob.conafor.upm.vie.UPMForms;
import gob.conafor.utils.FuncionesComunes;
import gob.conafor.utils.Version;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.beans.PropertyVetoException;

public class FrmInicio extends javax.swing.JFrame {

    private int upm;
    public  FrmInicio principal;
    private CDSeguimientoUPM seguimiento = new CDSeguimientoUPM();
    private CDSecuencia navegacion = new CDSecuencia();
    private Version ver = new Version();
    private String version = ver.getVersion();
    private String versionLayout = version.substring(6, 9);
    public boolean panelOculto = false;//No esta oculto
    public FuncionesComunes funcionesComunes=new FuncionesComunes();

    public FrmInicio() {
        initComponents();
        scrpanelModulos.setVisible(false);
        menuChkBoxPanelLateral.setSelected(false);
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
        jMenuItem1 = new javax.swing.JMenuItem();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        dpPrincipal = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnOcultarPanel = new javax.swing.JButton();
        scrpanelModulos = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
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
        miConcentrar = new javax.swing.JMenuItem();
        miEliminarPorUPM = new javax.swing.JMenuItem();
        miEliminar = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        miSalir = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        menuChkBoxPanelLateral = new javax.swing.JCheckBoxMenuItem();

        jMenuItem2.setText("jMenuItem2");

        jMenuItem3.setText("jMenuItem3");

        jMenuItem4.setText("jMenuItem4");

        jMenuItem1.setText("jMenuItem1");

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cliente de captura INFyS "+version);
        setIconImage(getIconImage());
        setIconImages(getIconImages());
        setLocation(new java.awt.Point(0, 0));
        setName("Index"); // NOI18N

        dpPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        dpPrincipal.setAutoscrolls(true);
        dpPrincipal.setMaximumSize(new java.awt.Dimension(939, 838));
        dpPrincipal.setPreferredSize(new java.awt.Dimension(939, 838));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gob/conafor/utils/CONAFOR.png"))); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gob/conafor/utils/logoSEMARNAT_hoz.png"))); // NOI18N

        jLabel3.setBackground(new java.awt.Color(90, 90, 90));
        jLabel3.setForeground(new java.awt.Color(10, 9, 9));
        jLabel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel3.setOpaque(true);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gob/conafor/utils/gsnmf.png"))); // NOI18N

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gob/conafor/utils/arboles.png"))); // NOI18N

        jLabel6.setBackground(new java.awt.Color(90, 90, 90));
        jLabel6.setForeground(new java.awt.Color(10, 9, 9));
        jLabel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel6.setOpaque(true);

        jLabel7.setText("Versión "+version);

        btnOcultarPanel.setText("<");
        btnOcultarPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOcultarPanelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnOcultarPanel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnOcultarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, Short.MAX_VALUE)
        );

        dpPrincipal.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dpPrincipal.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dpPrincipal.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dpPrincipal.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dpPrincipal.setLayer(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dpPrincipal.setLayer(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dpPrincipal.setLayer(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dpPrincipal.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout dpPrincipalLayout = new javax.swing.GroupLayout(dpPrincipal);
        dpPrincipal.setLayout(dpPrincipalLayout);
        dpPrincipalLayout.setHorizontalGroup(
            dpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(dpPrincipalLayout.createSequentialGroup()
                .addGroup(dpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dpPrincipalLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(70, 70, 70))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dpPrincipalLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(dpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dpPrincipalLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
            .addGroup(dpPrincipalLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(dpPrincipalLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 708, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dpPrincipalLayout.createSequentialGroup()
                .addContainerGap(320, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addContainerGap(302, Short.MAX_VALUE))
        );
        dpPrincipalLayout.setVerticalGroup(
            dpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dpPrincipalLayout.createSequentialGroup()
                .addGroup(dpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dpPrincipalLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dpPrincipalLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                .addGap(14, 14, 14)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Modulos");
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("A");
        javax.swing.tree.DefaultMutableTreeNode treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Sotobosque");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Repoblado");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Arbolado");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Submuestra");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Clave de vegetación");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Características del conglomerado");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Carbono e incendios");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Longitud interceptada");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Suelos");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Degradación del suelo");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Erosión hídrica");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Deformación del terreno");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Observaciones");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("C");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Carbono e incendios");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Longitud interceptada");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("D");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Arbolado");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("E");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Hojarasca profundidad suelo");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Muestras del perfil");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("F");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Fotografía hemisferica");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("G");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Parámetros físico químicos");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Repoblado vegetación menor");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Arbolado");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("H");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Vegetación menor");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Vegetación mayor MCMI");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Vegetación mayor MCMG");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        jTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jTree1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTree1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTree1MouseClicked(evt);
            }
        });
        scrpanelModulos.setViewportView(jTree1);

        dpMenuLateral.setBackground(new java.awt.Color(204, 204, 204));
        dpMenuLateral.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnCrearUPM.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnCrearUPM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gob/conafor/utils/UPMIcon.png"))); // NOI18N
        btnCrearUPM.setText("Crear UPM");
        btnCrearUPM.setToolTipText("");
        btnCrearUPM.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCrearUPM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearUPMActionPerformed(evt);
            }
        });

        btnCapturarModulos.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnCapturarModulos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gob/conafor/utils/ModulosIcon.png"))); // NOI18N
        btnCapturarModulos.setText("Capturar Módulos");
        btnCapturarModulos.setEnabled(false);
        btnCapturarModulos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCapturarModulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapturarModulosActionPerformed(evt);
            }
        });

        btnSalir.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gob/conafor/utils/log-out.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnCrearSitio.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnCrearSitio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gob/conafor/utils/SitioIcon.png"))); // NOI18N
        btnCrearSitio.setText("Crear Sitios");
        btnCrearSitio.setEnabled(false);
        btnCrearSitio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCrearSitio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearSitioActionPerformed(evt);
            }
        });

        btnRevisarUPM.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnRevisarUPM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gob/conafor/utils/UPMRevisarIcon.png"))); // NOI18N
        btnRevisarUPM.setText("Revisar UPM");
        btnRevisarUPM.setToolTipText("");
        btnRevisarUPM.setEnabled(false);
        btnRevisarUPM.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnRevisarUPM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRevisarUPMActionPerformed(evt);
            }
        });

        btnRevisionSitios.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnRevisionSitios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gob/conafor/utils/SitiosRevisarIcon.png"))); // NOI18N
        btnRevisionSitios.setText("Revisar Sitios");
        btnRevisionSitios.setEnabled(false);
        btnRevisionSitios.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnRevisionSitios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRevisionSitiosActionPerformed(evt);
            }
        });

        btnRevisarModulos.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnRevisarModulos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gob/conafor/utils/RevisarModulosIcon.png"))); // NOI18N
        btnRevisarModulos.setText("Revisar Módulos");
        btnRevisarModulos.setEnabled(false);
        btnRevisarModulos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnRevisarModulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRevisarModulosActionPerformed(evt);
            }
        });

        btnColectaBotanica.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnColectaBotanica.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gob/conafor/utils/tree-leaf.png"))); // NOI18N
        btnColectaBotanica.setText("Colecta Botánica");
        btnColectaBotanica.setEnabled(false);
        btnColectaBotanica.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnColectaBotanica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColectaBotanicaActionPerformed(evt);
            }
        });

        btnVerReportes.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnVerReportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gob/conafor/utils/check.png"))); // NOI18N
        btnVerReportes.setText("Ver Reportes");
        btnVerReportes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnVerReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerReportesActionPerformed(evt);
            }
        });

        btnContinuarModulos.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnContinuarModulos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gob/conafor/utils/ContinuarModulosIcon.png"))); // NOI18N
        btnContinuarModulos.setText("Continuar Módulos");
        btnContinuarModulos.setEnabled(false);
        btnContinuarModulos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
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
                    .addComponent(btnCapturarModulos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCrearSitio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRevisarUPM, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRevisionSitios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRevisarModulos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnColectaBotanica, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnVerReportes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnContinuarModulos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGap(60, 60, 60)
                .addComponent(btnColectaBotanica, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(btnRevisarUPM, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRevisionSitios, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRevisarModulos, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVerReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        miImportar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        miImportar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        miImportar.setMnemonic('s');
        miImportar.setText("Importar");
        miImportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miImportarActionPerformed(evt);
            }
        });
        menGuardarArchivo.add(miImportar);

        miConcentrar.setText("Concentrar BD");
        miConcentrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miConcentrarActionPerformed(evt);
            }
        });
        menGuardarArchivo.add(miConcentrar);

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

        jMenu1.setText("Ventana");

        menuChkBoxPanelLateral.setSelected(true);
        menuChkBoxPanelLateral.setText("Ocultar panel lateral");
        menuChkBoxPanelLateral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuChkBoxPanelLateralActionPerformed(evt);
            }
        });
        jMenu1.add(menuChkBoxPanelLateral);

        mbMenuSuperiror.add(jMenu1);

        setJMenuBar(mbMenuSuperiror);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(dpMenuLateral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dpPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 813, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrpanelModulos, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dpMenuLateral)
            .addGroup(layout.createSequentialGroup()
                .addComponent(scrpanelModulos)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(dpPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 801, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
        //UPMForms.continuarModulos.manipularBonesMenuprincipal();
        scrpanelModulos.setVisible(true);
        UPMForms.continuarModulos.setVisible(false);
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

    private void menuChkBoxPanelLateralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuChkBoxPanelLateralActionPerformed
        panelOculto = !panelOculto;
        if (menuChkBoxPanelLateral.getState() == true) {
            dpMenuLateral.setVisible(false);
            this.btnOcultarPanel.setText(">");
        }
        if (menuChkBoxPanelLateral.getState() == false) {
            dpMenuLateral.setVisible(true);
            this.btnOcultarPanel.setText("<");
        }
    }//GEN-LAST:event_menuChkBoxPanelLateralActionPerformed

    private void btnOcultarPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOcultarPanelActionPerformed
        panelOculto = !panelOculto;
        if (panelOculto == true) {
            menuChkBoxPanelLateral.setState(true);
            dpMenuLateral.setVisible(false);
            this.btnOcultarPanel.setText(">");
        }
        if (panelOculto == false) {
            dpMenuLateral.setVisible(true);
            menuChkBoxPanelLateral.setState(false);
            this.btnOcultarPanel.setText("<");
        }
    }//GEN-LAST:event_btnOcultarPanelActionPerformed

    private void miConcentrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miConcentrarActionPerformed
        ConcentradorAbies concentrador = new ConcentradorAbies();
        concentrador.setVisible(true);
    }//GEN-LAST:event_miConcentrarActionPerformed

    private void jTree1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTree1MouseClicked
        String path = "";
        if (evt.getClickCount() == 2) {
            try {

                path = jTree1.getSelectionPath().toString();
                openForm(path);

            } catch (Exception e) {

            }
        }

    }//GEN-LAST:event_jTree1MouseClicked

    private void btnCapturarModulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapturarModulosActionPerformed
        /*UPMForms.capturarModulos.llenarControles();
        UPMForms.capturarModulos.setVisible(true);
        UPMForms.capturarModulos.manipularBonesMenuprincipal();*/
        scrpanelModulos.setVisible(true);
    }//GEN-LAST:event_btnCapturarModulosActionPerformed
    public void openForm(String path) throws PropertyVetoException {
        System.out.println("Pathe OPEN_FORM"+path);

            switch (path) {
                case "[Modulos, A, Sotobosque]":
                    UPMForms.sotoBosque.setVisible(true);
                    UPMForms.sotoBosque.setMaximum(true);
                    UPMForms.sotoBosque.llenarControles();
                    funcionesComunes.manipularBotonesMenuPrincipal(true);
                    break;
                case "[Modulos, A, Repoblado]":
                    UPMForms.repoblado.setVisible(true);
                    UPMForms.repoblado.llenarControles();
                    funcionesComunes.manipularBotonesMenuPrincipal(true);
                    break;
                case "[Modulos, A, Arbolado]":
                    UPMForms.arbolado.setVisible(true);
                    UPMForms.arbolado.llenarControles();
                    funcionesComunes.manipularBotonesMenuPrincipal(true);
                    break;
                case "[Modulos, A, Submuestra]":
                    UPMForms.submuestra.setVisible(true);
                    UPMForms.submuestra.llenarControles();
                    funcionesComunes.manipularBotonesMenuPrincipal(true);
                    break;
                case "[Modulos, A, Clave de vegetación]":
                    UPMForms.claveVegetacion.setVisible(true);
                    UPMForms.claveVegetacion.llenarControles();
                    funcionesComunes.manipularBotonesMenuPrincipal(true);
                    break;
                case "[Modulos, A, Características del conglomerado]":
                    UPMForms.caracteristicasUPM.setVisible(true);
                    UPMForms.caracteristicasUPM.llenarControles();
                    funcionesComunes.manipularBotonesMenuPrincipal(true);
                    break;
                case "[Modulos, A, Carbono e incendios]":
                    UPMForms.carbono.setVisible(true);
                    UPMForms.carbono.llenarControles();
                    UPMForms.carbono.lblModulo.setText("A");
                    funcionesComunes.manipularBotonesMenuPrincipal(true);
                    break;
                case "[Modulos, A, Longitud interceptada]":
                    UPMForms.longitud.setVisible(true);
                    UPMForms.longitud.llenarControles();
                    UPMForms.carbono.lblModulo.setText("A");
                    funcionesComunes.manipularBotonesMenuPrincipal(true);
                    break;
                case "[Modulos, A, Suelos]":
                    UPMForms.suelo.setVisible(true);
                    UPMForms.suelo.llenarControles();
                    funcionesComunes.manipularBotonesMenuPrincipal(true);
                    break;
                case "[Modulos, A, Degradación del suelo]":
                    UPMForms.condicionDegradacion.setVisible(true);
                    UPMForms.condicionDegradacion.llenarControles();
                    funcionesComunes.manipularBotonesMenuPrincipal(true);
                    break;
                case "[Modulos, A, Erosión hídrica]":
                    funcionesComunes.manipularBotonesMenuPrincipal(true);
                    UPMForms.erosionHidrica.setVisible(true);
                    UPMForms.erosionHidrica.llenarControles();
                    
                    break;
                case "[Modulos, A, Deformación del terreno]":
                    UPMForms.deformacionTerreno.setVisible(true);
                    UPMForms.deformacionTerreno.llenarControles();
                    funcionesComunes.manipularBotonesMenuPrincipal(true);
                    break;
                case "[Modulos, A, Observaciones]":
                    UPMForms.observaciones.setVisible(true);
                    UPMForms.observaciones.llenarControles();
                    funcionesComunes.manipularBotonesMenuPrincipal(true);
                    break;
                case "[Modulos, C, Carbono e incendios]":
                    UPMForms.carbono.setVisible(true);
                    UPMForms.carbono.llenarControles();
                    UPMForms.carbono.lblModulo.setText("C");
                    funcionesComunes.manipularBotonesMenuPrincipal(true);

                    break;
                case "[Modulos, C, Longitud interceptada]":
                    UPMForms.longitud.setVisible(true);
                    UPMForms.longitud.llenarControles();
                    UPMForms.carbono.lblModulo.setText("C");
                    funcionesComunes.manipularBotonesMenuPrincipal(true);
                    break;
                case "[Modulos, D, Arbolado]":
                    UPMForms.arboladoD.setVisible(true);
                    UPMForms.arboladoD.llenarControles();
                    funcionesComunes.manipularBotonesMenuPrincipal(true);
                    break;
                case "[Modulos, E, Hojarasca profundidad suelo]":
                    UPMForms.hojarascaProfundidad.setVisible(true);
                    UPMForms.hojarascaProfundidad.llenarControles();
                    funcionesComunes.manipularBotonesMenuPrincipal(true);
                    break;
                case "[Modulos, E, Muestras del perfil]":
                    UPMForms.muestrasPerfil.setVisible(true);
                    UPMForms.muestrasPerfil.llenarControles();
                    funcionesComunes.manipularBotonesMenuPrincipal(true);
                    break;
                case "[Modulos, F, Fotografía hemisferica]":
                    UPMForms.fotoHemisferica.setVisible(true);
                    UPMForms.fotoHemisferica.llenarControles();
                    funcionesComunes.manipularBotonesMenuPrincipal(true);
                    break;
                case "[Modulos, G, Parámetros físico químicos]":
                    UPMForms.parametrosFQ.setVisible(true);
                    UPMForms.parametrosFQ.llenarControles();
                    funcionesComunes.manipularBotonesMenuPrincipal(true);
                    break;
                case "[Modulos, G, Repoblado vegetación menor]":
                    UPMForms.repobladoVM.setVisible(true);
                    UPMForms.repobladoVM.llenarControles();
                    funcionesComunes.manipularBotonesMenuPrincipal(true);
                    break;
                case "[Modulos, G, Arbolado]":
                    UPMForms.arboladoG.setVisible(true);
                    UPMForms.arboladoG.llenarControles();
                    funcionesComunes.manipularBotonesMenuPrincipal(true);
                    break;
                case "[Modulos, H, Vegetación menor]":
                    UPMForms.vegetacionMenor.setVisible(true);
                    UPMForms.vegetacionMenor.llenarControles();
                    funcionesComunes.manipularBotonesMenuPrincipal(true);
                    break;
                case "[Modulos, H, Vegetación mayor MCMI]":
                    UPMForms.vegetacionMayorI.setVisible(true);
                    UPMForms.vegetacionMayorI.llenarControles();
                    funcionesComunes.manipularBotonesMenuPrincipal(true);
                    break;
                case "[Modulos, H, Vegetación mayor MCMG]":
                    UPMForms.vegetacionMayorG.setVisible(true);
                    UPMForms.vegetacionMayorG.llenarControles();
                    funcionesComunes.manipularBotonesMenuPrincipal(true);
                    break;
            }
            
    }

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

    
    public void capturarModulosIndependientes(){
           try {
            String path = jTree1.getSelectionPath().toString();
            System.out.println(path);
            if (path.equals("[Modulos, D, Arbolado]")) {
                UPMForms.arboladoD.setVisible(true);
                funcionesComunes.manipularBotonesMenuPrincipal(true);
            }
        } catch (Exception e) {
        }
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnCapturarModulos;
    public javax.swing.JButton btnColectaBotanica;
    public javax.swing.JButton btnContinuarModulos;
    public javax.swing.JButton btnCrearSitio;
    public javax.swing.JButton btnCrearUPM;
    private javax.swing.JButton btnOcultarPanel;
    public javax.swing.JButton btnRevisarModulos;
    public javax.swing.JButton btnRevisarUPM;
    public javax.swing.JButton btnRevisionSitios;
    private javax.swing.JButton btnSalir;
    public javax.swing.JButton btnVerReportes;
    private javax.swing.JDesktopPane dpMenuLateral;
    public javax.swing.JDesktopPane dpPrincipal;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTree jTree1;
    private javax.swing.JMenuBar mbMenuSuperiror;
    public javax.swing.JMenu menGuardarArchivo;
    private javax.swing.JCheckBoxMenuItem menuChkBoxPanelLateral;
    private javax.swing.JMenuItem miConcentrar;
    private javax.swing.JMenuItem miEliminar;
    private javax.swing.JMenuItem miEliminarPorUPM;
    private javax.swing.JMenuItem miImportar;
    private javax.swing.JMenuItem miSalir;
    public javax.swing.JScrollPane scrpanelModulos;
    // End of variables declaration//GEN-END:variables

}
