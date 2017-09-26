package gob.conafor.sitio.vie;

import gob.conafor.sitio.mod.CDTrazoSitio;
import gob.conafor.sitio.mod.CESitio;
import gob.conafor.sitio.mod.CETrazoSitio;
import gob.conafor.upm.vie.UPMForms;
import gob.conafor.utils.Datos;
import gob.conafor.utils.FuncionesComunes;
import gob.conafor.utils.ValidacionesComunes;
import gob.conafor.utils.Version;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

public class FrmTrazoSitio extends javax.swing.JDialog {
    
    protected static final int RET_CANCEL = 0;
    protected static final int RET_OK = 1;
    protected boolean iniciar = false;
    private int UPMID;
    private int sitioID;
    private int sitio;
    private CETrazoSitio trazo = new CETrazoSitio();
    private int hipsometroBrujula;
    private int cintaClinometroBrujula;
    private Integer cuadrante1;
    private Integer cuadrante2;
    private Integer cuadrante3;
    private Integer cuadrante4;
    private Float distancia1;
    private Float distancia2;
    private Float distancia3;
    private Float distancia4;
    private CDTrazoSitio cdTrazo = new CDTrazoSitio();
    private CESitio ceSitio = new CESitio();
    private Datos numeros = new Datos();
    private FuncionesComunes funciones = new FuncionesComunes();
    private Version ver=new Version();
    private String version=ver.getVersion();
    
    
    public FrmTrazoSitio(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //Cerrar ventana al presionar cancelar
        String cancelName = "cancel";
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), cancelName);
        ActionMap actionMap = getRootPane().getActionMap();
        actionMap.put(cancelName, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                doClose(RET_CANCEL);
            }
        });
    }
    
    public void setDatosIniciales(CESitio sitio) {
        this.UPMID = sitio.getUpmID();
        this.sitioID = sitio.getSitioID();
        this.sitio = sitio.getSitio();
        this.ceSitio = sitio;

        txtSitio.setText(String.valueOf(this.sitio));
        txtUPM.setText(String.valueOf(this.UPMID));
        mostrarTrazoActual(sitio);
        funciones.manipularBotonesMenuPrincipal(true);
    }

    private void asignarDatosTrazo() {
        if (rbtCintaClinometro.isSelected()) {
            this.cintaClinometroBrujula = 1;
        } else {
            this.cintaClinometroBrujula = 0;
        }
        if (rbtHipsometroBrujula.isSelected()) {
            this.hipsometroBrujula = 1;
        } else {
            this.hipsometroBrujula = 0;
        }
        try {
            this.cuadrante1 = Integer.valueOf(txtCuadrante1.getText());
        } catch (NumberFormatException e) {
            this.cuadrante1 = null;
        }
        try {
            this.cuadrante2 = Integer.valueOf(txtCuadrante2.getText());
        } catch (NumberFormatException e) {
            this.cuadrante2 = null;
        }
        try {
            this.cuadrante3 = Integer.valueOf(txtCuadrante3.getText());
        } catch (NumberFormatException e) {
            this.cuadrante3 = null;
        }
        try {
            this.cuadrante4 = Integer.valueOf(txtCuadrante4.getText());
        } catch (NumberFormatException e) {
            this.cuadrante4 = null;
        }
        try {
            this.distancia1 = Float.valueOf(txtDistancia1.getText());
        } catch (NumberFormatException e) {
            this.distancia1 = null;
        }
        try {
            this.distancia2 = Float.valueOf(txtDistancia2.getText());
        } catch (NumberFormatException e) {
            this.distancia2 = null;
        }
        try {
            this.distancia3 = Float.valueOf(txtDistancia3.getText());
        } catch (NumberFormatException e) {
            this.distancia3 = null;
        }
        try {
            this.distancia4 = Float.valueOf(txtDistancia4.getText());
        } catch (NumberFormatException e) {
            this.distancia4 = null;
        }
    }

    private void mostrarTrazoActual(CESitio sitio) {
        CESitio ceSitio;
        ceSitio = this.cdTrazo.getTrazoSitio(sitio);
        int hipsometro = ceSitio.getHipsometroBrujula();
        int cinta = ceSitio.getCintaClinometroBrujula();
        if (hipsometro == 1) {
            rbtHipsometroBrujula.setSelected(true);
        } else if (cinta == 1) {
            rbtCintaClinometro.setSelected(true);
        } else if (hipsometro == 0 && cinta == 0) {
            rbtHipsometroBrujula.setSelected(false);
            rbtCintaClinometro.setSelected(false);
            limpiarControles();
        }
        if (hipsometro == 1 || cinta == 1) {
            txtCuadrante1.setText(String.valueOf(ceSitio.getCuadrante1()));
            txtCuadrante2.setText(String.valueOf(ceSitio.getCuadrante2()));
            txtCuadrante3.setText(String.valueOf(ceSitio.getCuadrante3()));
            txtCuadrante4.setText(String.valueOf(ceSitio.getCuadrante4()));
            txtDistancia1.setText(String.valueOf(ceSitio.getDistancia1()));
            txtDistancia2.setText(String.valueOf(ceSitio.getDistancia2()));
            txtDistancia3.setText(String.valueOf(ceSitio.getDistancia3()));
            txtDistancia4.setText(String.valueOf(ceSitio.getDistancia4()));
        }
    }

    private boolean validarCuadranteVacio() {
        if (!(rbtHipsometroBrujula.isSelected() || rbtCintaClinometro.isSelected())) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar algún instrumento", "Trazo de sitio", JOptionPane.ERROR_MESSAGE);
            rbtHipsometroBrujula.requestFocus();
            return false;
        } else if (txtCuadrante1.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar el cuadrante 1", "Trazo de sitio", JOptionPane.ERROR_MESSAGE);
            txtCuadrante1.requestFocus();
            return false;
        } else if (txtCuadrante2.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar el cuadrante 2", "Trazo de sitio", JOptionPane.ERROR_MESSAGE);
            txtCuadrante2.requestFocus();
            return false;
        } else if (txtCuadrante3.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar el cuadrante 3", "Trazo de sitio", JOptionPane.ERROR_MESSAGE);
            txtCuadrante3.requestFocus();
            return false;
        } else if (txtCuadrante4.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar el cuadrante 4", "Trazo de sitio", JOptionPane.ERROR_MESSAGE);
            txtCuadrante4.requestFocus();
            return false;
        } else if (txtDistancia1.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar el cuadrante 1", "Trazo de sitio", JOptionPane.ERROR_MESSAGE);
            txtCuadrante1.requestFocus();
            return false;
        } else if (txtDistancia2.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar el cuadrante 2", "Trazo de sitio", JOptionPane.ERROR_MESSAGE);
            txtCuadrante2.requestFocus();
            return false;
        } else if (txtDistancia3.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar el cuadrante 3", "Trazo de sitio", JOptionPane.ERROR_MESSAGE);
            txtCuadrante3.requestFocus();
            return false;
        } else if (txtDistancia4.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar el cuadrante 4", "Trazo de sitio", JOptionPane.ERROR_MESSAGE);
            txtCuadrante4.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean validarCuadrante() {
        ValidacionesComunes cuadrante = new ValidacionesComunes();
        if (cuadrante.esCuadrante(this.cuadrante1)) {
            txtCuadrante1.requestFocus();
            return false;
        } else if (cuadrante.esCuadrante(this.cuadrante2)) {
            txtCuadrante2.requestFocus();
            return false;
        } else if (cuadrante.esCuadrante(this.cuadrante3)) {
            txtCuadrante3.requestFocus();
            return false;
        } else if (cuadrante.esCuadrante(this.cuadrante4)) {
            txtCuadrante4.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private void crearTrazoSitio() {
        CETrazoSitio trazo = new CETrazoSitio();
        trazo.setSitioID(this.sitioID);
        trazo.setHipsometroBrujula(this.hipsometroBrujula);
        trazo.setCintaClinometroBrujula(this.cintaClinometroBrujula);
        trazo.setCuadrante1(this.cuadrante1);
        trazo.setCuadrante2(this.cuadrante2);
        trazo.setCuadrante3(this.cuadrante3);
        trazo.setCuadrante4(this.cuadrante4);
        trazo.setDistancia1(this.distancia1);
        trazo.setDistancia2(this.distancia2);
        trazo.setDistancia3(this.distancia3);
        trazo.setDistancia4(this.distancia4);

        this.cdTrazo.updateTrazoSitio(trazo);

    }

    private void eliminarTrazoSitio(int sitioID) {
        Object[] opciones = {"Si", "No"};
            int respuesta = JOptionPane.showOptionDialog(null, "Se eliminará la información de pendientes por cuadrante, ¿Esta seguro?",
                    "Trazo del sitio", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[1]);
        if (respuesta == JOptionPane.YES_OPTION) {
            CETrazoSitio trazo = new CETrazoSitio();
            trazo.setSitioID(sitioID);
            trazo.setHipsometroBrujula(0);
            trazo.setCintaClinometroBrujula(0);
            trazo.setCuadrante1(null);
            trazo.setCuadrante2(null);
            trazo.setCuadrante3(null);
            trazo.setCuadrante4(null);
            trazo.setDistancia1(null);
            trazo.setDistancia2(null);
            trazo.setDistancia3(null);
            trazo.setDistancia4(null);

            this.cdTrazo.updateTrazoSitio(trazo);
            limpiarControles();
        }
    }

    private void limpiarControles() {
        txtCuadrante1.setText("");
        txtCuadrante1.setValue(null);
        txtCuadrante2.setText("");
        txtCuadrante2.setValue(null);
        txtCuadrante3.setText("");
        txtCuadrante3.setValue(null);
        txtCuadrante4.setText("");
        txtCuadrante4.setValue(null);

        txtDistancia1.setText("");
        txtDistancia1.setValue(null);
        txtDistancia2.setText("");
        txtDistancia2.setValue(null);
        txtDistancia3.setText("");
        txtDistancia3.setValue(null);
        txtDistancia4.setText("");
        txtDistancia4.setValue(null);
    }

    /**
     * @return the return status of this dialog - one of RET_OK or RET_CANCEL
     */
    /* public int getReturnStatus() {
     return returnStatus;
     }*/
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rbgHerramientas = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblCuadrante1 = new javax.swing.JLabel();
        txtCuadrante1 = new javax.swing.JFormattedTextField();
        lblPorcentaje1 = new javax.swing.JLabel();
        lblCuadrante2 = new javax.swing.JLabel();
        txtCuadrante2 = new javax.swing.JFormattedTextField();
        lblPorcentaje2 = new javax.swing.JLabel();
        lblCuadrante3 = new javax.swing.JLabel();
        txtCuadrante3 = new javax.swing.JFormattedTextField();
        lblPorcentaje3 = new javax.swing.JLabel();
        lblCuadrante4 = new javax.swing.JLabel();
        txtCuadrante4 = new javax.swing.JFormattedTextField();
        lblPorcentaje4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtDistancia1 = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtDistancia2 = new javax.swing.JFormattedTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtDistancia3 = new javax.swing.JFormattedTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtDistancia4 = new javax.swing.JFormattedTextField();
        jLabel16 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblUPM = new javax.swing.JLabel();
        txtUPM = new javax.swing.JTextField();
        lblSitio = new javax.swing.JLabel();
        txtSitio = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        rbtHipsometroBrujula = new javax.swing.JRadioButton();
        rbtCintaClinometro = new javax.swing.JRadioButton();

        setTitle("Trazo del Sitio");
        setBackground(new java.awt.Color(204, 204, 204));
        setModal(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setPreferredSize(new java.awt.Dimension(931, 61));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblCuadrante1.setText("C1:");

        txtCuadrante1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtCuadrante1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCuadrante1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCuadrante1FocusLost(evt);
            }
        });
        txtCuadrante1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtCuadrante1PropertyChange(evt);
            }
        });
        txtCuadrante1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCuadrante1KeyTyped(evt);
            }
        });

        lblPorcentaje1.setText("%");

        lblCuadrante2.setText("C2:");

        txtCuadrante2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtCuadrante2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCuadrante2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCuadrante2FocusLost(evt);
            }
        });
        txtCuadrante2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCuadrante2ActionPerformed(evt);
            }
        });
        txtCuadrante2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtCuadrante2PropertyChange(evt);
            }
        });
        txtCuadrante2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCuadrante2KeyTyped(evt);
            }
        });

        lblPorcentaje2.setText("%");

        lblCuadrante3.setText("C3:");

        txtCuadrante3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###0"))));
        txtCuadrante3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCuadrante3FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCuadrante3FocusLost(evt);
            }
        });
        txtCuadrante3.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtCuadrante3PropertyChange(evt);
            }
        });
        txtCuadrante3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCuadrante3KeyTyped(evt);
            }
        });

        lblPorcentaje3.setText("%");

        lblCuadrante4.setText("C4:");

        txtCuadrante4.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtCuadrante4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCuadrante4FocusGained(evt);
            }
        });
        txtCuadrante4.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtCuadrante4PropertyChange(evt);
            }
        });
        txtCuadrante4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCuadrante4KeyTyped(evt);
            }
        });

        lblPorcentaje4.setText("%");

        jLabel9.setText("D1:");

        txtDistancia1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat(""))));
        txtDistancia1.setEnabled(false);

        jLabel10.setText("m");

        jLabel11.setText("D2:");

        txtDistancia2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat(""))));
        txtDistancia2.setEnabled(false);

        jLabel12.setText("m");

        jLabel13.setText("D3:");

        txtDistancia3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat(""))));
        txtDistancia3.setEnabled(false);

        jLabel14.setText("m");

        jLabel15.setText("D4:");

        txtDistancia4.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat(""))));
        txtDistancia4.setEnabled(false);

        jLabel16.setText("m");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblCuadrante1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCuadrante1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPorcentaje1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblCuadrante2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCuadrante2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPorcentaje2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblCuadrante3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCuadrante3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPorcentaje3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblCuadrante4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCuadrante4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPorcentaje4))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDistancia1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDistancia2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDistancia3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDistancia4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCuadrante1)
                    .addComponent(txtCuadrante1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPorcentaje1)
                    .addComponent(lblCuadrante2)
                    .addComponent(txtCuadrante2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPorcentaje2)
                    .addComponent(lblCuadrante3)
                    .addComponent(txtCuadrante3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPorcentaje3)
                    .addComponent(lblCuadrante4)
                    .addComponent(txtCuadrante4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPorcentaje4))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtDistancia1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(txtDistancia2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(txtDistancia3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(txtDistancia4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblUPM.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblUPM.setText("UPMID:");

        txtUPM.setEditable(false);
        txtUPM.setEnabled(false);

        lblSitio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblSitio.setText("Sitio:");

        txtSitio.setEditable(false);
        txtSitio.setEnabled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblUPM)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtUPM, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                .addComponent(lblSitio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSitio, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblSitio)
                        .addComponent(txtSitio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblUPM)
                        .addComponent(txtUPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        rbtHipsometroBrujula.setBackground(new java.awt.Color(204, 204, 204));
        rbgHerramientas.add(rbtHipsometroBrujula);
        rbtHipsometroBrujula.setText("Hipsómetro/Brújula");
        rbtHipsometroBrujula.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        rbtCintaClinometro.setBackground(new java.awt.Color(204, 204, 204));
        rbgHerramientas.add(rbtCintaClinometro);
        rbtCintaClinometro.setText("Cinta/Clinómetro/Brújula");
        rbtCintaClinometro.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(69, 69, 69)
                                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(60, 60, 60)
                                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(rbtHipsometroBrujula)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rbtCintaClinometro)
                        .addGap(21, 21, 21))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtHipsometroBrujula)
                    .addComponent(rbtCintaClinometro))
                .addGap(7, 7, 7)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnEliminar)
                    .addComponent(btnSalir))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        this.dispose();
    }//GEN-LAST:event_closeDialog

    private void txtCuadrante1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtCuadrante1PropertyChange
        try {
            trazo.setCuadrante1(Integer.valueOf(txtCuadrante1.getText()));
            txtDistancia1.setText(trazo.getDistancia1().toString());
        } catch (NumberFormatException e) {
            txtDistancia1.setValue(null);
            txtDistancia1.setText("");
        }
    }//GEN-LAST:event_txtCuadrante1PropertyChange

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        doClose(returnStatus);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtCuadrante2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtCuadrante2PropertyChange
        try {
            trazo.setCuadrante2(Integer.valueOf(txtCuadrante2.getText()));
            txtDistancia2.setText(trazo.getDistancia2().toString());
        } catch (NumberFormatException e) {
            txtDistancia2.setValue(null);
            txtDistancia2.setText("");
        }
    }//GEN-LAST:event_txtCuadrante2PropertyChange

    private void txtCuadrante1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCuadrante1FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtCuadrante1.selectAll();
            }
        });
    }//GEN-LAST:event_txtCuadrante1FocusGained

    private void txtCuadrante2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCuadrante2FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtCuadrante2.selectAll();
            }
        });
    }//GEN-LAST:event_txtCuadrante2FocusGained

    private void txtCuadrante3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCuadrante3FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtCuadrante3.selectAll();
            }
        });
    }//GEN-LAST:event_txtCuadrante3FocusGained

    private void txtCuadrante4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCuadrante4FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtCuadrante4.selectAll();
            }
        });
    }//GEN-LAST:event_txtCuadrante4FocusGained

    private void txtCuadrante1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCuadrante1FocusLost
        if (txtCuadrante1.getText().isEmpty()) {
            txtCuadrante1.setValue(null);
        }
    }//GEN-LAST:event_txtCuadrante1FocusLost

    private void txtCuadrante2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCuadrante2FocusLost
        if (txtCuadrante2.getText().isEmpty()) {
            txtCuadrante2.setValue(null);
        }
    }//GEN-LAST:event_txtCuadrante2FocusLost

    private void txtCuadrante3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCuadrante3FocusLost
        if (txtCuadrante2.getText().isEmpty()) {
            txtCuadrante2.setValue(null);
        }
    }//GEN-LAST:event_txtCuadrante3FocusLost

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        asignarDatosTrazo();
        if (validarCuadranteVacio() && validarCuadrante()) {
            crearTrazoSitio();
            UPMForms.arbolado.evitarCapturaPorTrazo(this.ceSitio);
            UPMForms.arboladoD.evitarCapturaPorTrazo(this.ceSitio);
            UPMForms.arboladoG.evitarCapturaPorTrazo(this.ceSitio);
            doClose(returnStatus);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminarTrazoSitio(this.sitioID);
        UPMForms.arbolado.evitarCapturaPorTrazo(this.ceSitio);
        UPMForms.arboladoD.evitarCapturaPorTrazo(this.ceSitio);
        UPMForms.arboladoG.evitarCapturaPorTrazo(this.ceSitio);
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtCuadrante3PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtCuadrante3PropertyChange
        try {
            trazo.setCuadrante3(Integer.valueOf(txtCuadrante3.getText()));
            txtDistancia3.setText(trazo.getDistancia3().toString());
        } catch (NumberFormatException e) {
            txtDistancia3.setValue(null);
            txtDistancia3.setText("");
        }
    }//GEN-LAST:event_txtCuadrante3PropertyChange

    private void txtCuadrante4PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtCuadrante4PropertyChange
        try {
            trazo.setCuadrante4(Integer.valueOf(txtCuadrante4.getText()));
            txtDistancia4.setText(trazo.getDistancia4().toString());
        } catch (Exception e) {
            //txtDistancia4.setValue(null);
           // txtDistancia4.setText("");
        }
    }//GEN-LAST:event_txtCuadrante4PropertyChange

    private void txtCuadrante1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCuadrante1KeyTyped
        //System.out.println(evt.getKeyChar());
        if (evt.getKeyChar()== KeyEvent.VK_PERIOD) {
            evt.consume();
        } else {
            numeros.keyTyped(evt);
        }
    }//GEN-LAST:event_txtCuadrante1KeyTyped

    private void txtCuadrante2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCuadrante2KeyTyped
        numeros.keyTyped(evt);
        if (evt.getKeyChar() == KeyEvent.VK_PERIOD) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCuadrante2KeyTyped

    private void txtCuadrante3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCuadrante3KeyTyped
        numeros.keyTyped(evt);
        if (evt.getKeyChar() == KeyEvent.VK_PERIOD) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCuadrante3KeyTyped

    private void txtCuadrante4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCuadrante4KeyTyped
        numeros.keyTyped(evt);
        if (evt.getKeyChar() == KeyEvent.VK_PERIOD) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCuadrante4KeyTyped

    private void txtCuadrante2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCuadrante2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCuadrante2ActionPerformed

    private void doClose(int retStatus) {
        returnStatus = retStatus;
        setVisible(false);
        dispose();
    }

    /**
     * @param args the command line arguments
     */
    /*  public static void main(String args[]) {
     // Set the Nimbus look and feel 
     //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
     /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
     * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
     */
    /*  try{
             
     UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            
     }catch (Exception e){
            
     e.printStackTrace();
            
     }*/
        //</editor-fold>
    //Create and display the dialog 
    /*    java.awt.EventQueue.invokeLater(new Runnable() {
     public void run() {
     DlgTipoUPM dialog = new DlgTipoUPM(new javax.swing.JFrame(), true);
     dialog.addWindowListener(new java.awt.event.WindowAdapter() {
     @Override
     public void windowClosing(java.awt.event.WindowEvent e) {
     System.exit(0);
     }
     });
     dialog.setVisible(true);
     }
     });
     }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblCuadrante1;
    private javax.swing.JLabel lblCuadrante2;
    private javax.swing.JLabel lblCuadrante3;
    private javax.swing.JLabel lblCuadrante4;
    private javax.swing.JLabel lblPorcentaje1;
    private javax.swing.JLabel lblPorcentaje2;
    private javax.swing.JLabel lblPorcentaje3;
    private javax.swing.JLabel lblPorcentaje4;
    private javax.swing.JLabel lblSitio;
    private javax.swing.JLabel lblUPM;
    private javax.swing.ButtonGroup rbgHerramientas;
    private javax.swing.JRadioButton rbtCintaClinometro;
    private javax.swing.JRadioButton rbtHipsometroBrujula;
    private javax.swing.JFormattedTextField txtCuadrante1;
    private javax.swing.JFormattedTextField txtCuadrante2;
    private javax.swing.JFormattedTextField txtCuadrante3;
    private javax.swing.JFormattedTextField txtCuadrante4;
    private javax.swing.JFormattedTextField txtDistancia1;
    private javax.swing.JFormattedTextField txtDistancia2;
    private javax.swing.JFormattedTextField txtDistancia3;
    private javax.swing.JFormattedTextField txtDistancia4;
    private javax.swing.JTextField txtSitio;
    private javax.swing.JTextField txtUPM;
    // End of variables declaration//GEN-END:variables

    private int returnStatus = RET_CANCEL;
}
