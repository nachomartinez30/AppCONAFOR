package gob.conafor.suelo.vie;

import gob.conafor.sitio.mod.CESitio;
import gob.conafor.suelo.mod.CDCanalillo;
import gob.conafor.suelo.mod.CDCarcava;
import gob.conafor.suelo.mod.CDCostras;
import gob.conafor.suelo.mod.CDErosionLaminar;
import gob.conafor.suelo.mod.CDPavimentosErosion;
import gob.conafor.suelo.mod.CDPedestal;
import gob.conafor.suelo.mod.CECanalillo;
import gob.conafor.suelo.mod.CECarcavas;
import gob.conafor.suelo.mod.CECostras;
import gob.conafor.suelo.mod.CEErosionLaminar;
import gob.conafor.suelo.mod.CEPavimentosErosion;
import gob.conafor.suelo.mod.CEPedestal;
import gob.conafor.sys.mod.CDSecuencia;
import gob.conafor.sys.mod.CDSeguimientoUPM;
import gob.conafor.sys.mod.CESeccionesCapturadas;
import gob.conafor.upm.vie.UPMForms;
import gob.conafor.utils.Datos;
import gob.conafor.utils.FuncionesComunes;
import gob.conafor.utils.Tablas;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class FrmCondicionDegradacionSuelo extends javax.swing.JInternalFrame {

    private int upmID;
    private int sitioID;
    private int sitio;
    private static final int FORMATO_ID = 10;
    private int pedestalID;
    private int erosionLaminarID;
    private int costrasID;
    private int canalilloID;
    private int carcavaID;
    private int pavimentoID;
    private int condicionDegradacion;
    private int erosionHidrica;
    private CESitio ceSitio = new CESitio();
    private CDPedestal cdPedestal = new CDPedestal();
    private CDErosionLaminar cdErosion = new CDErosionLaminar();
    private CDCostras cdCostras = new CDCostras();
    private CDCanalillo cdCanalillo = new CDCanalillo();
    private CDCarcava cdCarcava = new CDCarcava();
    private CDPavimentosErosion cdPavimento = new CDPavimentosErosion();
    private Float alturaPedestal;
    private Float anchoErosion;
    private Float largoErosion;
    private Float diametroCostras;
    private Float anchoCanalillo;
    private Float profundidadCanalillo;
    private Float anchoCarcava;
    private Float profundidadCarcava;
    private Float diametroPavimento;
    private CEPedestal cePedestal = new CEPedestal();
    private CEErosionLaminar ceErosion = new CEErosionLaminar();
    private CECostras ceCostras = new CECostras();
    private CECanalillo ceCanalillo = new CECanalillo();
    private CECarcavas ceCarcavas = new CECarcavas();
    private CEPavimentosErosion cePavimento = new CEPavimentosErosion();
    private FuncionesComunes combo = new FuncionesComunes();
    private CESeccionesCapturadas seccionActual = new CESeccionesCapturadas();
    private CESeccionesCapturadas seccionSiguiente = new CESeccionesCapturadas();
    private CDSeguimientoUPM seguimiento = new CDSeguimientoUPM();
    private Datos numeros = new Datos();
    private CDSecuencia cdSecuencia = new CDSecuencia();
    private FuncionesComunes funciones = new FuncionesComunes();
    private int modificar;

    public FrmCondicionDegradacionSuelo() {
        initComponents();
        this.condicionDegradacion = 20;
        this.erosionHidrica = 21;
    }

    public void setDatosiniciales(CESitio ceSitio) {
        this.upmID = ceSitio.getUpmID();
        this.sitioID = ceSitio.getSitioID();
        this.sitio = ceSitio.getSitio();
        this.txtUPM1.setText(String.valueOf(this.upmID));
        this.txtSitio1.setText(String.valueOf(this.sitio));
        this.txtUPM2.setText(String.valueOf(this.upmID));
        this.txtSitio2.setText(String.valueOf(this.sitio));
        this.ceSitio.setSitioID(this.sitioID);
        this.ceSitio.setUpmID(this.upmID);
        this.ceSitio.setSitio(this.sitio);
        this.ceSitio = ceSitio;
       // cdSecuencia.insertFormatoCapturado(this.ceSitio, FORMATO_ID);
        llenarTablaPedestal();
        fillCmbNumeroPedestal();
        llenarTablaErosionLaminar();
        fillCmbNumeroErosion();
        llenarTablaCostras();
        fillCmbNumeroCostras();
        llenarTablaCanalillo();
        fillCmbNumeroCanalillo();
        llenarTablaCarcava();
        fillCmbNumeroCarcavas();
        llenarTablaPavimento();
        fillCmbNumeroPavimento();
        /*chkPedestal.setSelected(!this.cdPedestal.validarTablaPedestal(ceSitio.getSitioID()));
        chkErosionLaminar.setSelected(!this.cdErosion.validarTablaErosionLaminar(ceSitio.getSitioID()));
        chkCostras.setSelected(!this.cdCostras.validarTablaCostras(ceSitio.getSitioID()));
        chkCanalillo.setSelected(!this.cdCanalillo.validarTablaCanalillos(ceSitio.getSitioID()));
        chkCarcavas.setSelected(!this.cdCarcava.validarTablaCarcavas(ceSitio.getSitioID()));
        chkPavimentos.setSelected(!this.cdPavimento.validarTablaPavimentos(ceSitio.getSitioID()));*/
        funciones.manipularBotonesMenuPrincipal(true);
        this.modificar = 0;
    }

    public void revisarCondicionDegradacion(CESitio ceSitio) {
        this.upmID = ceSitio.getUpmID();
        this.sitioID = ceSitio.getSitioID();
        this.sitio = ceSitio.getSitio();
        this.txtUPM1.setText(String.valueOf(this.upmID));
        this.txtSitio1.setText(String.valueOf(this.sitio));
        this.txtUPM2.setText(String.valueOf(this.upmID));
        this.txtSitio2.setText(String.valueOf(this.sitio));
        this.ceSitio.setSitioID(this.sitioID);
        this.ceSitio.setUpmID(this.upmID);
        this.ceSitio.setSitio(this.sitio);
        //System.out.println("condicion degradacion Suelo "+this.ceSitio.getSecuencia());
        llenarTablaPedestal();
        // combo.reiniciarComboModel(cmbNoPedestal);
        fillCmbNumeroPedestal();
        llenarTablaErosionLaminar();
        //combo.reiniciarComboModel(cmbNoErosionLaminar);
        fillCmbNumeroErosion();
        llenarTablaCostras();
        fillCmbNumeroCostras();
        llenarTablaCanalillo();
        fillCmbNumeroCanalillo();
        llenarTablaCarcava();
        fillCmbNumeroCarcavas();
        llenarTablaPavimento();
        fillCmbNumeroPavimento();
        chkPedestal.setSelected(!this.cdPedestal.validarTablaPedestal(ceSitio.getSitioID()));
        chkErosionLaminar.setSelected(!this.cdErosion.validarTablaErosionLaminar(ceSitio.getSitioID()));
        chkCostras.setSelected(!this.cdCostras.validarTablaCostras(ceSitio.getSitioID()));
        chkCanalillo.setSelected(!this.cdCanalillo.validarTablaCanalillos(ceSitio.getSitioID()));
        chkCarcavas.setSelected(!this.cdCarcava.validarTablaCarcavas(ceSitio.getSitioID()));
        chkPavimentos.setSelected(!this.cdPavimento.validarTablaPavimentos(ceSitio.getSitioID()));
        funciones.manipularBotonesMenuPrincipal(true);
        this.modificar = 1;
    }

    private void llenarTablaPedestal() {
        grdPedestal.setModel(cdPedestal.getTablaPedestal(this.sitioID));
        grdPedestal.getColumnModel().getColumn(2).setPreferredWidth(50);
        grdPedestal.getColumnModel().getColumn(3).setPreferredWidth(80);

        Tablas tabla = new Tablas();
        int[] columna_0 = {0};
        int[] column_1 = {1};
        //Ocultar campo ID
        tabla.hideColumnTable(grdPedestal, columna_0);
        tabla.hideColumnTable(grdPedestal, column_1);
    }

    private void fillCmbNumeroPedestal() {
        List<Integer> listNumero = new ArrayList<>();
        listNumero = cdPedestal.getNumeroPedestal(this.sitioID);
        if (listNumero != null) {
            int size = listNumero.size();
            for (int i = 0; i < size; i++) {
                cmbNoPedestal.addItem(listNumero.get(i));
            }
        }
    }

    private void fijarDatosPedestal() {
        try {
            this.alturaPedestal = Float.valueOf(txtAlturaPedestal.getText());
        } catch (NumberFormatException e) {
            this.alturaPedestal = null;
        }
    }

    private boolean validarPedestal() {
        if (txtAlturaPedestal.getText().isEmpty() || this.alturaPedestal < 0.01) {
            JOptionPane.showMessageDialog(null, "Debe proporcionar un valor superior a 0.01 para pedestal "
                    + "", "Condicion de degradacion", JOptionPane.INFORMATION_MESSAGE);
            txtAlturaPedestal.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private void crearPedestal() {
        this.cePedestal.setSitioID(sitioID);
        this.cePedestal.setAltura(this.alturaPedestal);
        this.cdPedestal.insertPedestal(cePedestal);
    }

    private void modificarPedestal() {
        try {
            int fila = grdPedestal.getSelectedRow();
            String registro = grdPedestal.getValueAt(fila, 0).toString();
            this.cePedestal.setPedestalID(Integer.parseInt(registro));
            this.cePedestal.setAltura(this.alturaPedestal);
            this.cdPedestal.updatePedestal(cePedestal);
            this.cePedestal.setSitioID(this.sitioID);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla de pedestal "
                    + e.getClass().getName() + " : " + e.getMessage(), "Condicion de degradacion", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void eliminarPedestal() {
        try {
            int fila = grdPedestal.getSelectedRow();
            String registro = grdPedestal.getValueAt(fila, 0).toString();
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Esta seguro de borrar el registro de pedestal?",
                    "Condicion de degradacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (respuesta == JOptionPane.YES_OPTION) {
                this.cdPedestal.deletePedestal(Integer.parseInt(registro));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla de pedestal "
                    + "", "Condicion de degradacion", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void llenarTablaErosionLaminar() {
        grdErosionLaminar.setModel(cdErosion.getTablaErosion(this.sitioID));

        grdErosionLaminar.getColumnModel().getColumn(2).setPreferredWidth(50);
        grdErosionLaminar.getColumnModel().getColumn(3).setPreferredWidth(50);
        grdErosionLaminar.getColumnModel().getColumn(4).setPreferredWidth(50);

        Tablas tabla = new Tablas();
        int[] columna_0 = {0};
        int[] column_1 = {1};
        //Ocultar campo ID
        tabla.hideColumnTable(grdErosionLaminar, columna_0);
        tabla.hideColumnTable(grdErosionLaminar, column_1);
    }

    private void fillCmbNumeroErosion() {
        List<Integer> listNumero = new ArrayList<>();
        listNumero = cdErosion.getNumeroErosion(this.sitioID);
        if (listNumero != null) {
            int size = listNumero.size();
            for (int i = 0; i < size; i++) {
                cmbNoErosionLaminar.addItem(listNumero.get(i));
            }
        }
    }

    private void fijarDatosErosion() {
        try {
            this.anchoErosion = Float.valueOf(txtAnchoErosionLaminar.getText());
        } catch (NumberFormatException e) {
            this.anchoErosion = null;
        }

        try {
            this.largoErosion = Float.valueOf(txtLargoErosionLaminar.getText());
        } catch (NumberFormatException e) {
            this.largoErosion = null;
        }
    }

    private boolean validarErosion() {
        if (txtAnchoErosionLaminar.getText().isEmpty() || this.anchoErosion < 0.01) {
            JOptionPane.showMessageDialog(null, "El valor proporcionado para ancho de erosion laminar debe ser superior a 0.01 "
                    + "", "Condicion de degradacion", JOptionPane.INFORMATION_MESSAGE);
            txtAnchoErosionLaminar.requestFocus();
            return false;
        } else if (txtLargoErosionLaminar.getText().isEmpty() || this.largoErosion < 0.01) {
            JOptionPane.showMessageDialog(null, "El valor proporcionado para largo de erosion laminar debe ser superior a 0.01 "
                    + "", "Condicion de degradacion", JOptionPane.INFORMATION_MESSAGE);
            txtLargoErosionLaminar.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private void crearErosion() {
        this.ceErosion.setSitioID(this.sitioID);
        this.ceErosion.setAncho(this.anchoErosion);
        this.ceErosion.setLargo(this.largoErosion);
        this.cdErosion.insertErosion(this.ceErosion);
    }

    private void modificarErosion() {
        try {
            int fila = grdErosionLaminar.getSelectedRow();
            String registro = grdErosionLaminar.getValueAt(fila, 0).toString();

            this.ceErosion.setErosionLaminarID(Integer.parseInt(registro));
            this.ceErosion.setAncho(this.anchoErosion);
            this.ceErosion.setLargo(this.largoErosion);
            this.cdErosion.updateErosion(this.ceErosion);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla de erosion "
                    + e.getClass().getName() + " : " + e.getMessage(), "Condicion de degradacion", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void eliminarErosion() {
        try {
            int fila = grdErosionLaminar.getSelectedRow();
            String registro = grdErosionLaminar.getValueAt(fila, 0).toString();
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Esta seguro de borrar el registro de erosion laminar?",
                    "Condicion de degradacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (respuesta == JOptionPane.YES_OPTION) {
                this.cdErosion.deleteErosion(Integer.parseInt(registro));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla de erosion "
                    + e.getClass().getName() + " : " + e.getMessage(), "Condicion de degradacion", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void llenarTablaCostras() {
        grdCostras.setModel(cdCostras.getTablaCostras(this.sitioID));

        grdCostras.getColumnModel().getColumn(2).setPreferredWidth(50);
        grdCostras.getColumnModel().getColumn(3).setPreferredWidth(80);

        Tablas tabla = new Tablas();
        int[] columna_0 = {0};
        int[] column_1 = {1};
        //Ocultar campo ID
        tabla.hideColumnTable(grdCostras, columna_0);
        tabla.hideColumnTable(grdCostras, column_1);
    }

    private void fillCmbNumeroCostras() {
        List<Integer> listNumero = new ArrayList<>();
        listNumero = cdCostras.getNumeroCostras(this.sitioID);
        if (listNumero != null) {
            int size = listNumero.size();
            for (int i = 0; i < size; i++) {
                cmbNoCostra.addItem(listNumero.get(i));
            }
        }
    }

    private void fijarDatosCostras() {
        try {
            this.diametroCostras = Float.valueOf(txtDiametroCostras.getText());
        } catch (NumberFormatException e) {
            this.diametroCostras = null;
        }
    }

    private void crearCostras() {
        this.ceCostras.setSitioID(this.sitioID);
        this.ceCostras.setDiametro(this.diametroCostras);
        this.cdCostras.insertCostras(ceCostras);
    }

    private boolean validarCostras() {
        if (txtDiametroCostras.getText().isEmpty() || this.diametroCostras < 0.01) {
            JOptionPane.showMessageDialog(null, "El valor proporcionado para diametro de costras debe ser superior a 0.01 "
                    + "", "Condicion de degradacion", JOptionPane.INFORMATION_MESSAGE);
            txtDiametroCostras.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private void modificarCostra() {
        try {
            int fila = grdCostras.getSelectedRow();
            String registro = grdCostras.getValueAt(fila, 0).toString();
            this.ceCostras.setCostrasID(Integer.parseInt(registro));
            this.ceCostras.setDiametro(this.diametroCostras);
            this.cdCostras.updateCostras(this.ceCostras);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla de costra "
                    + e.getClass().getName() + " : " + e.getMessage(), "Condicion de degradacion", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void eliminarCostra() {
        try {
            int fila = grdCostras.getSelectedRow();
            String registro = grdCostras.getValueAt(fila, 0).toString();
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Esta seguro de borrar el registro de costra?",
                    "Condicion de degradacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (respuesta == JOptionPane.YES_OPTION) {
                this.cdCostras.deleteCostras(Integer.parseInt(registro));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla de costra"
                    + "", "Condicion de degradacion", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void llenarTablaCanalillo() {
        grdCanalillo.setModel(cdCanalillo.getTablaCanalillo(this.sitioID));
        grdCanalillo.getColumnModel().getColumn(2).setPreferredWidth(50);
        grdCanalillo.getColumnModel().getColumn(3).setPreferredWidth(50);
        grdCanalillo.getColumnModel().getColumn(4).setPreferredWidth(50);
        Tablas tabla = new Tablas();
        int[] columna_0 = {0};
        int[] column_1 = {1};
        //Ocultar campo ID
        tabla.hideColumnTable(grdCanalillo, columna_0);
        tabla.hideColumnTable(grdCanalillo, column_1);
    }

    private void fillCmbNumeroCanalillo() {
        List<Integer> listNumero = new ArrayList<>();
        listNumero = cdCanalillo.getNumeroCanalillo(this.sitioID);
        if (listNumero != null) {
            int size = listNumero.size();
            for (int i = 0; i < size; i++) {
                cmbNoCanalillo.addItem(listNumero.get(i));
            }
        }
    }

    private void fijarDatosCanalillo() {
        try {
            this.anchoCanalillo = Float.valueOf(txtAnchoCanalillo.getText());
        } catch (NumberFormatException e) {
            this.anchoCanalillo = null;
        }
        try {
            this.profundidadCanalillo = Float.valueOf(txtProfundidadCanalillos.getText());
        } catch (NumberFormatException e) {
            this.profundidadCanalillo = null;
        }
    }

    private boolean validarCanalillos() {
        if (txtAnchoCanalillo.getText().isEmpty() || this.anchoCanalillo < 0.01) {
            JOptionPane.showMessageDialog(null, "El valor proporcionado para ancho de canalillo debe ser superior a 0.01 "
                    + "", "Condicion de degradacion", JOptionPane.INFORMATION_MESSAGE);
            txtAnchoCanalillo.requestFocus();
            return false;
        } else if (txtProfundidadCanalillos.getText().isEmpty() || this.profundidadCanalillo < 0.01) {
            JOptionPane.showMessageDialog(null, "El valor proporcionado para profundiad de canalillo debe ser superior a 0.01 "
                    + "", "Condicion de degradacion", JOptionPane.INFORMATION_MESSAGE);
            txtProfundidadCanalillos.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private void crearCanalillo() {
        this.ceCanalillo.setSitioID(this.sitioID);
        this.ceCanalillo.setAncho(this.anchoCanalillo);
        this.ceCanalillo.setProfundidad(this.profundidadCanalillo);
        this.cdCanalillo.insertCanalillo(ceCanalillo);
    }

    private void modificarCanalillo() {
        try {
            int fila = grdCanalillo.getSelectedRow();
            String registro = grdCanalillo.getValueAt(fila, 0).toString();
            this.ceCanalillo.setCanalilloID(Integer.parseInt(registro));
            this.ceCanalillo.setAncho(this.anchoCanalillo);
            this.ceCanalillo.setProfundidad(this.profundidadCanalillo);
            this.cdCanalillo.updateCanalillo(ceCanalillo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla de canalillo "
                    + e.getClass().getName() + " : " + e.getMessage(), "Condicion de degradacion", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void eliminarCanalillo() {
        try {
            int fila = grdCanalillo.getSelectedRow();
            String registro = grdCanalillo.getValueAt(fila, 0).toString();
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Esta seguro de borrar el registro de canalillo?",
                    "Condcion de degradacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (respuesta == JOptionPane.YES_OPTION) {
                this.cdCanalillo.deleteCanalillo(Integer.parseInt(registro));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla de canalillo "
                    + "", "Condicion de degradacion", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void llenarTablaCarcava() {
        grdCarcava.setModel(cdCarcava.getTablaCarcava(sitioID));
        grdCarcava.getColumnModel().getColumn(2).setPreferredWidth(50);
        grdCarcava.getColumnModel().getColumn(3).setPreferredWidth(50);
        grdCarcava.getColumnModel().getColumn(4).setPreferredWidth(50);
        Tablas tabla = new Tablas();
        int[] columna_0 = {0};
        int[] column_1 = {1};
        //Ocultar campo ID
        tabla.hideColumnTable(grdCarcava, columna_0);
        tabla.hideColumnTable(grdCarcava, column_1);
    }

    private void fillCmbNumeroCarcavas() {
        List<Integer> listNumero = new ArrayList<>();
        listNumero = cdCarcava.getNumeroCarcavas(this.sitioID);
        if (listNumero != null) {
            int size = listNumero.size();
            for (int i = 0; i < size; i++) {
                cmbNoCarcava.addItem(listNumero.get(i));
            }
        }
    }

    private void fijarDatosCarcava() {
        try {
            this.anchoCarcava = Float.valueOf(txtAnchoCarcavas.getText());
        } catch (NumberFormatException e) {
            this.anchoCarcava = null;
        }
        try {
            this.profundidadCarcava = Float.valueOf(txtProfundidadCarcavas.getText());
        } catch (NumberFormatException e) {
            this.profundidadCarcava = null;
        }
    }

    private boolean validarCarcava() {
        if (txtAnchoCarcavas.getText().isEmpty() || this.anchoCarcava < 0.01) {
            JOptionPane.showMessageDialog(null, "El valor proporcionado para ancho de carcava debe ser superior a 0.01 "
                    + "", "Condicion de degradacion", JOptionPane.INFORMATION_MESSAGE);
            txtAnchoCarcavas.requestFocus();
            return false;
        } else if (txtProfundidadCarcavas.getText().isEmpty() || this.profundidadCarcava < 0.01) {
            JOptionPane.showMessageDialog(null, "El valor proporcionado para profundidad de carcava debe ser superior a 0.01 "
                    + "", "Condicion de degradacion", JOptionPane.INFORMATION_MESSAGE);
            txtProfundidadCarcavas.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private void crearCarcava() {
        this.ceCarcavas.setSitioID(this.sitioID);
        this.ceCarcavas.setAncho(this.anchoCarcava);
        this.ceCarcavas.setProfundidad(this.profundidadCarcava);
        this.cdCarcava.insertCarcavas(ceCarcavas);
    }

    private void modificarCarcava() {
        try {
            int fila = grdCarcava.getSelectedRow();
            String registro = grdCarcava.getValueAt(fila, 0).toString();
            this.ceCarcavas.setCarcavasID(Integer.parseInt(registro));
            this.ceCarcavas.setAncho(this.anchoCarcava);
            this.ceCarcavas.setProfundidad(this.profundidadCarcava);
            this.cdCarcava.updateCaracava(this.ceCarcavas);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla de carcava "
                    + e.getClass().getName() + " : " + e.getMessage(), "Condcion de degradacion", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void eliminarCarcava() {
        try {
            int fila = grdCarcava.getSelectedRow();
            String registro = grdCarcava.getValueAt(fila, 0).toString();
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Esta seguro de borrar el registro de carcava?",
                    "Condcion de degradacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (respuesta == JOptionPane.YES_OPTION) {
                this.cdCarcava.deleteCarcava(Integer.parseInt(registro));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla de carcava "
                    + "", "Condicion de degradacion", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void llenarTablaPavimento() {
        grdPavimento.setModel(cdPavimento.getTablaPavimento(sitioID));

        grdPavimento.getColumnModel().getColumn(2).setPreferredWidth(50);
        grdPavimento.getColumnModel().getColumn(3).setPreferredWidth(80);

        Tablas tabla = new Tablas();
        int[] columna_0 = {0};
        int[] column_1 = {1};
        //Ocultar campo ID
        tabla.hideColumnTable(grdPavimento, columna_0);
        tabla.hideColumnTable(grdPavimento, column_1);
    }

    private void fillCmbNumeroPavimento() {
        List<Integer> listNumero = new ArrayList<>();
        listNumero = cdPavimento.getNumeroPavimento(this.sitioID);
        if (listNumero != null) {
            int size = listNumero.size();
            for (int i = 0; i < size; i++) {
                cmbNoPavimento.addItem(listNumero.get(i));
            }
        }
    }

    private void fijarDatosPavimento() {
        try {
            this.diametroPavimento = Float.valueOf(txtDiametroPavimento.getText());
        } catch (NumberFormatException e) {
            this.diametroPavimento = null;
        }
    }

    private boolean validarPavimento() {
        if (txtDiametroPavimento.getText().isEmpty() || this.diametroPavimento < 0.01) {
            JOptionPane.showMessageDialog(null, "El valor proporcionado para diametro de pavimento debe ser superior a 0.01 "
                    + "", "Condicion de degradacion", JOptionPane.INFORMATION_MESSAGE);
            txtDiametroPavimento.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private void crearPavimento() {
        this.cePavimento.setSitioID(this.sitioID);
        this.cePavimento.setDiametro(this.diametroPavimento);
        this.cdPavimento.insertPavimento(this.cePavimento);
    }

    private void modificarPavimento() {
        try {
            int fila = grdPavimento.getSelectedRow();
            String registro = grdPavimento.getValueAt(fila, 0).toString();

            this.cePavimento.setPavimentosErosionID(Integer.parseInt(registro));
            this.cePavimento.setDiametro(this.diametroPavimento);

            this.cdPavimento.updatePavimento(this.cePavimento);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla de pavimento "
                    + e.getClass().getName() + " : " + e.getMessage(), "Condicion de degradacion", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void eliminarPavimento() {
        try {
            int fila = grdPavimento.getSelectedRow();
            String registro = grdPavimento.getValueAt(fila, 0).toString();
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Esta seguro de borrar el registro de pavimento?",
                    "Condcion de degradacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (respuesta == JOptionPane.YES_OPTION) {
                this.cdPavimento.deletePavimento(Integer.parseInt(registro));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla de pavimento "
                    + "", "Condicion de degradacion", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void manipularControlesPedestal(boolean activar) {
        if (activar == true) {
            cmbNoPedestal.setEnabled(true);
            txtAlturaPedestal.setEnabled(true);
            btnAgregarPedestal.setEnabled(true);
            btnModificarPedestal.setEnabled(true);
            btnModificarPedestal.setEnabled(true);
        } else {
            cmbNoPedestal.setEnabled(false);
            cmbNoPedestal.setSelectedItem(null);
            txtAlturaPedestal.setEnabled(false);
            btnAgregarPedestal.setEnabled(false);
            btnModificarPedestal.setEnabled(false);
            btnModificarPedestal.setEnabled(false);
        }
    }
    
    private void manipularControlesErosionLaminar(boolean activar) {
        if (activar == true) {
            cmbNoErosionLaminar.setEnabled(true);
            txtAnchoErosionLaminar.setEnabled(true);
            txtLargoErosionLaminar.setEditable(true);
            btnAgregarErosionLaminar.setEnabled(true);
            btnAgregarErosionLaminar.setEnabled(true);
            btnEliminarErosion.setEnabled(true);
        } else {
            cmbNoErosionLaminar.setSelectedItem(null);
            cmbNoErosionLaminar.setEnabled(false);
            txtAnchoErosionLaminar.setEnabled(false);
            txtLargoErosionLaminar.setEditable(false);
            btnAgregarErosionLaminar.setEnabled(false);
            btnAgregarErosionLaminar.setEnabled(false);
            btnEliminarErosion.setEnabled(false);
        }
    }
    
    private void manipularControlesCostras(boolean activar){
        if(activar == true){
            cmbNoCostra.setEnabled(true);
            txtDiametroCostras.setEnabled(true);
            btnAgregarCostra.setEnabled(true);
            btnModificarCostra.setEnabled(true);
            btnEliminarCostra.setEnabled(true);
        } else {
            cmbNoCostra.setEnabled(false);
            cmbNoCostra.setSelectedItem(null);
            txtDiametroCostras.setEnabled(false);
            btnAgregarCostra.setEnabled(false);
            btnModificarCostra.setEnabled(false);
            btnEliminarCostra.setEnabled(false);
        }
    }
    
    private void manipularControlesCanalillo(boolean activar){
        if(activar == true){
            cmbNoCanalillo.setEnabled(true);
            txtAnchoCanalillo.setEnabled(true);
            txtProfundidadCanalillos.setEnabled(true);
            btnAgregarCanalillo.setEnabled(true);
            btnModificarCanalillo.setEnabled(true);
            btnEliminarCanalillo.setEnabled(true);
        } else {
            cmbNoCanalillo.setEnabled(false);
            cmbNoCanalillo.setSelectedItem(null);
            txtAnchoCanalillo.setEnabled(false);
            txtProfundidadCanalillos.setEnabled(false);
            btnAgregarCanalillo.setEnabled(false);
            btnModificarCanalillo.setEnabled(false);
            btnEliminarCanalillo.setEnabled(false);
        }
    }
    
    private void manipularControlesCarcava(boolean activar){
        if(activar == true){
            cmbNoCarcava.setEnabled(true);
            txtAnchoCarcavas.setEnabled(true);
            txtProfundidadCarcavas.setEnabled(true);
            btnAgregarCarcava.setEnabled(true);
            btnModificarCarcava.setEnabled(true);
            btnEliminarCarcava.setEnabled(true);
        } else {
            cmbNoCarcava.setEnabled(false);
            cmbNoCanalillo.setSelectedItem(null);
            txtAnchoCarcavas.setEnabled(false);
            txtProfundidadCarcavas.setEnabled(false);
            btnAgregarCarcava.setEnabled(false);
            btnModificarCarcava.setEnabled(false);
            btnEliminarCarcava.setEnabled(false);
        }
    }
    
    private void manipularControlesPavimentos(boolean activar){
        if(activar == true){
            cmbNoPavimento.setEnabled(true);
            txtDiametroPavimento.setEnabled(true);
            btnAgregarPavimento.setEnabled(true);
            btnModificarPavimento.setEnabled(true);
            btnEliminarPavimento.setEnabled(true);
        } else {
            cmbNoPavimento.setEnabled(false);
            cmbNoPavimento.setSelectedItem(null);
            txtDiametroPavimento.setEnabled(false);
            btnAgregarPavimento.setEnabled(false);
            btnModificarPavimento.setEnabled(false);
            btnEliminarPavimento.setEnabled(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jPanel1 = new javax.swing.JPanel();
        lblUPM = new javax.swing.JLabel();
        txtUPM = new javax.swing.JTextField();
        lblSitio = new javax.swing.JLabel();
        txtSitio = new javax.swing.JTextField();
        lblSuelo = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox();
        jTextField4 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        lblUPM1 = new javax.swing.JLabel();
        txtUPM1 = new javax.swing.JTextField();
        lblSitio1 = new javax.swing.JLabel();
        txtSitio1 = new javax.swing.JTextField();
        lblSuelo1 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        lblPedestal = new javax.swing.JLabel();
        lblNoPedestal = new javax.swing.JLabel();
        lblAlturaPedestal = new javax.swing.JLabel();
        cmbNoPedestal = new javax.swing.JComboBox();
        btnAgregarPedestal = new javax.swing.JButton();
        btnModificarPedestal = new javax.swing.JButton();
        btnEliminarPedestal = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        grdPedestal = new javax.swing.JTable();
        txtAlturaPedestal = new javax.swing.JFormattedTextField();
        chkPedestal = new javax.swing.JCheckBox();
        jPanel8 = new javax.swing.JPanel();
        lblErosionLaminar = new javax.swing.JLabel();
        lblNoErosionLaminar = new javax.swing.JLabel();
        cmbNoErosionLaminar = new javax.swing.JComboBox();
        lblAnchoErosionLaminar = new javax.swing.JLabel();
        lblLargoErosionLaminar = new javax.swing.JLabel();
        btnAgregarErosionLaminar = new javax.swing.JButton();
        btnModificarErosion = new javax.swing.JButton();
        btnEliminarErosion = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        grdErosionLaminar = new javax.swing.JTable();
        txtAnchoErosionLaminar = new javax.swing.JFormattedTextField();
        txtLargoErosionLaminar = new javax.swing.JFormattedTextField();
        chkErosionLaminar = new javax.swing.JCheckBox();
        jPanel10 = new javax.swing.JPanel();
        lblCostras = new javax.swing.JLabel();
        lblNoCostra = new javax.swing.JLabel();
        lblDiametroCostra = new javax.swing.JLabel();
        cmbNoCostra = new javax.swing.JComboBox();
        btnAgregarCostra = new javax.swing.JButton();
        btnModificarCostra = new javax.swing.JButton();
        btnEliminarCostra = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        grdCostras = new javax.swing.JTable();
        txtDiametroCostras = new javax.swing.JFormattedTextField();
        chkCostras = new javax.swing.JCheckBox();
        btnContinuar1 = new javax.swing.JButton();
        btnSalir1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        lblUPM2 = new javax.swing.JLabel();
        txtUPM2 = new javax.swing.JTextField();
        lblSuelo2 = new javax.swing.JLabel();
        lblSitio2 = new javax.swing.JLabel();
        txtSitio2 = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        lblCanalillo = new javax.swing.JLabel();
        lblNoCanalillos = new javax.swing.JLabel();
        cmbNoCanalillo = new javax.swing.JComboBox();
        lblAnchoCanalillos = new javax.swing.JLabel();
        lblProfundidadCanalillos = new javax.swing.JLabel();
        btnAgregarCanalillo = new javax.swing.JButton();
        btnModificarCanalillo = new javax.swing.JButton();
        btnEliminarCanalillo = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        grdCanalillo = new javax.swing.JTable();
        txtAnchoCanalillo = new javax.swing.JFormattedTextField();
        txtProfundidadCanalillos = new javax.swing.JFormattedTextField();
        chkCanalillo = new javax.swing.JCheckBox();
        jPanel12 = new javax.swing.JPanel();
        lblCarcaba = new javax.swing.JLabel();
        lblNoCarcavas = new javax.swing.JLabel();
        cmbNoCarcava = new javax.swing.JComboBox();
        lblAnchoCarcava = new javax.swing.JLabel();
        lblProfundidadCarcava = new javax.swing.JLabel();
        btnAgregarCarcava = new javax.swing.JButton();
        btnModificarCarcava = new javax.swing.JButton();
        btnEliminarCarcava = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        grdCarcava = new javax.swing.JTable();
        txtAnchoCarcavas = new javax.swing.JFormattedTextField();
        txtProfundidadCarcavas = new javax.swing.JFormattedTextField();
        chkCarcavas = new javax.swing.JCheckBox();
        jPanel13 = new javax.swing.JPanel();
        lblPavimentos = new javax.swing.JLabel();
        lblNoPavimento = new javax.swing.JLabel();
        lblDiametroPavimento = new javax.swing.JLabel();
        cmbNoPavimento = new javax.swing.JComboBox();
        btnAgregarPavimento = new javax.swing.JButton();
        btnModificarPavimento = new javax.swing.JButton();
        btnEliminarPavimento = new javax.swing.JButton();
        jScrollPane10 = new javax.swing.JScrollPane();
        grdPavimento = new javax.swing.JTable();
        txtDiametroPavimento = new javax.swing.JFormattedTextField();
        chkPavimentos = new javax.swing.JCheckBox();
        btnContinuar2 = new javax.swing.JButton();
        btnSalir2 = new javax.swing.JButton();

        setTitle("Evaluación de las condiciones de degradación del suelo");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/gob/conafor/utils/logo_conafor.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(940, 650));

        jTabbedPane1.setBackground(new java.awt.Color(204, 204, 204));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jInternalFrame1.setPreferredSize(new java.awt.Dimension(940, 650));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        lblUPM.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblUPM.setText("UPMID:");

        txtUPM.setEditable(false);
        txtUPM.setEnabled(false);

        lblSitio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblSitio.setText("Sitio:");

        txtSitio.setEditable(false);
        txtSitio.setEnabled(false);

        lblSuelo.setBackground(new java.awt.Color(153, 153, 153));
        lblSuelo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblSuelo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSuelo.setText("Evaluación de las condiciones de degradación del suelo");
        lblSuelo.setOpaque(true);

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setBackground(new java.awt.Color(153, 153, 153));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Costras");
        jLabel1.setOpaque(true);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("No");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Diámetro");
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton1.setText("Agregar");

        jButton2.setText("Modificar");

        jButton3.setText("Eliminar");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addGap(0, 8, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1)
                    .addComponent(jButton3))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setBackground(new java.awt.Color(153, 153, 153));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Erosión laminar");
        jLabel4.setOpaque(true);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("No");
        jLabel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Ancho");
        jLabel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Largo");
        jLabel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton4.setText("Agregar");

        jButton5.setText("Modificar");

        jButton6.setText("Eliminar");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                            .addComponent(jTextField2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton6)
                        .addGap(0, 11, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton4)
                    .addComponent(jButton6))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel8.setBackground(new java.awt.Color(153, 153, 153));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Pedestal");
        jLabel8.setOpaque(true);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("No");
        jLabel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Altura");
        jLabel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton7.setText("Agregar");

        jButton8.setText("Modificar");

        jButton9.setText("Eliminar");

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(jTable3);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField4)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton9)))
                        .addGap(0, 8, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton8)
                    .addComponent(jButton7)
                    .addComponent(jButton9))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(lblUPM)
                        .addGap(4, 4, 4)
                        .addComponent(txtUPM, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(485, 485, 485)
                        .addComponent(lblSitio)
                        .addGap(4, 4, 4)
                        .addComponent(txtSitio, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblSuelo, javax.swing.GroupLayout.PREFERRED_SIZE, 904, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtUPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSitio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblUPM)
                            .addComponent(lblSitio))))
                .addGap(8, 8, 8)
                .addComponent(lblSuelo)
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel2.add(jInternalFrame1, new org.netbeans.lib.awtextra.AbsoluteConstraints(462, 296, 0, 0));

        lblUPM1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblUPM1.setText("UPMID:");
        jPanel2.add(lblUPM1, new org.netbeans.lib.awtextra.AbsoluteConstraints(78, 13, -1, -1));

        txtUPM1.setEditable(false);
        txtUPM1.setEnabled(false);
        jPanel2.add(txtUPM1, new org.netbeans.lib.awtextra.AbsoluteConstraints(127, 11, 97, -1));

        lblSitio1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblSitio1.setText("Sitio:");
        jPanel2.add(lblSitio1, new org.netbeans.lib.awtextra.AbsoluteConstraints(709, 13, -1, -1));

        txtSitio1.setEditable(false);
        txtSitio1.setEnabled(false);
        jPanel2.add(txtSitio1, new org.netbeans.lib.awtextra.AbsoluteConstraints(745, 11, 100, -1));

        lblSuelo1.setBackground(new java.awt.Color(153, 153, 153));
        lblSuelo1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblSuelo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSuelo1.setText("Evaluación de las condiciones de degradación del suelo");
        lblSuelo1.setOpaque(true);
        jPanel2.add(lblSuelo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 39, 904, -1));

        jPanel7.setBackground(new java.awt.Color(204, 204, 204));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblPedestal.setBackground(new java.awt.Color(153, 153, 153));
        lblPedestal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPedestal.setText("Pedestal");
        lblPedestal.setOpaque(true);

        lblNoPedestal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNoPedestal.setText("No");
        lblNoPedestal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblAlturaPedestal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAlturaPedestal.setText("Altura");
        lblAlturaPedestal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        cmbNoPedestal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbNoPedestalActionPerformed(evt);
            }
        });

        btnAgregarPedestal.setText("Agregar");
        btnAgregarPedestal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarPedestalActionPerformed(evt);
            }
        });

        btnModificarPedestal.setText("Modificar");
        btnModificarPedestal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarPedestalActionPerformed(evt);
            }
        });

        btnEliminarPedestal.setText("Eliminar");
        btnEliminarPedestal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarPedestalActionPerformed(evt);
            }
        });

        grdPedestal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        grdPedestal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grdPedestalMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(grdPedestal);

        txtAlturaPedestal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat(""))));
        txtAlturaPedestal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtAlturaPedestalFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtAlturaPedestalFocusLost(evt);
            }
        });
        txtAlturaPedestal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAlturaPedestalKeyTyped(evt);
            }
        });

        chkPedestal.setBackground(new java.awt.Color(204, 204, 204));
        chkPedestal.setSelected(true);
        chkPedestal.setText("Pedestal");
        chkPedestal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPedestalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPedestal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNoPedestal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbNoPedestal, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblAlturaPedestal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtAlturaPedestal, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkPedestal)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(btnAgregarPedestal, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnModificarPedestal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEliminarPedestal)))
                        .addGap(0, 8, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPedestal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkPedestal)
                .addGap(10, 10, 10)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNoPedestal)
                    .addComponent(lblAlturaPedestal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbNoPedestal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAlturaPedestal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificarPedestal)
                    .addComponent(btnAgregarPedestal)
                    .addComponent(btnEliminarPedestal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 65, -1, 460));

        jPanel8.setBackground(new java.awt.Color(204, 204, 204));
        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblErosionLaminar.setBackground(new java.awt.Color(153, 153, 153));
        lblErosionLaminar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblErosionLaminar.setText("Erosión laminar");
        lblErosionLaminar.setOpaque(true);

        lblNoErosionLaminar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNoErosionLaminar.setText("No");
        lblNoErosionLaminar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        cmbNoErosionLaminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbNoErosionLaminarActionPerformed(evt);
            }
        });

        lblAnchoErosionLaminar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAnchoErosionLaminar.setText("Ancho");
        lblAnchoErosionLaminar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblLargoErosionLaminar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLargoErosionLaminar.setText("Largo");
        lblLargoErosionLaminar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnAgregarErosionLaminar.setText("Agregar");
        btnAgregarErosionLaminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarErosionLaminarActionPerformed(evt);
            }
        });

        btnModificarErosion.setText("Modificar");
        btnModificarErosion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarErosionActionPerformed(evt);
            }
        });

        btnEliminarErosion.setText("Eliminar");
        btnEliminarErosion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarErosionActionPerformed(evt);
            }
        });

        grdErosionLaminar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        grdErosionLaminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grdErosionLaminarMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(grdErosionLaminar);

        txtAnchoErosionLaminar.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat(""))));
        txtAnchoErosionLaminar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtAnchoErosionLaminarFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtAnchoErosionLaminarFocusLost(evt);
            }
        });
        txtAnchoErosionLaminar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAnchoErosionLaminarKeyTyped(evt);
            }
        });

        txtLargoErosionLaminar.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat(""))));
        txtLargoErosionLaminar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtLargoErosionLaminarFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtLargoErosionLaminarFocusLost(evt);
            }
        });
        txtLargoErosionLaminar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLargoErosionLaminarKeyTyped(evt);
            }
        });

        chkErosionLaminar.setBackground(new java.awt.Color(204, 204, 204));
        chkErosionLaminar.setSelected(true);
        chkErosionLaminar.setText("Erosión laminar");
        chkErosionLaminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkErosionLaminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(chkErosionLaminar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(btnAgregarErosionLaminar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificarErosion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminarErosion)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(lblErosionLaminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNoErosionLaminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbNoErosionLaminar, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblAnchoErosionLaminar, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                                    .addComponent(txtAnchoErosionLaminar))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblLargoErosionLaminar, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                                    .addComponent(txtLargoErosionLaminar))))
                        .addContainerGap())))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblErosionLaminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkErosionLaminar)
                .addGap(7, 7, 7)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNoErosionLaminar)
                            .addComponent(lblAnchoErosionLaminar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbNoErosionLaminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(lblLargoErosionLaminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAnchoErosionLaminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLargoErosionLaminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificarErosion)
                    .addComponent(btnAgregarErosionLaminar)
                    .addComponent(btnEliminarErosion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 70, 270, 450));

        jPanel10.setBackground(new java.awt.Color(204, 204, 204));
        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblCostras.setBackground(new java.awt.Color(153, 153, 153));
        lblCostras.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCostras.setText("Costras");
        lblCostras.setOpaque(true);

        lblNoCostra.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNoCostra.setText("No");
        lblNoCostra.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblDiametroCostra.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDiametroCostra.setText("Diámetro");
        lblDiametroCostra.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        cmbNoCostra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbNoCostraActionPerformed(evt);
            }
        });

        btnAgregarCostra.setText("Agregar");
        btnAgregarCostra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarCostraActionPerformed(evt);
            }
        });

        btnModificarCostra.setText("Modificar");
        btnModificarCostra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarCostraActionPerformed(evt);
            }
        });

        btnEliminarCostra.setText("Eliminar");
        btnEliminarCostra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarCostraActionPerformed(evt);
            }
        });

        grdCostras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        grdCostras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grdCostrasMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(grdCostras);

        txtDiametroCostras.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat(""))));
        txtDiametroCostras.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDiametroCostrasFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDiametroCostrasFocusLost(evt);
            }
        });
        txtDiametroCostras.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDiametroCostrasKeyTyped(evt);
            }
        });

        chkCostras.setBackground(new java.awt.Color(204, 204, 204));
        chkCostras.setSelected(true);
        chkCostras.setText("Costras");
        chkCostras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCostrasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCostras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNoCostra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbNoCostra, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblDiametroCostra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDiametroCostras, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkCostras)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(btnAgregarCostra, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnModificarCostra)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEliminarCostra)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCostras)
                .addGap(9, 9, 9)
                .addComponent(chkCostras)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNoCostra)
                    .addComponent(lblDiametroCostra))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbNoCostra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiametroCostras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificarCostra)
                    .addComponent(btnAgregarCostra)
                    .addComponent(btnEliminarCostra))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 70, -1, 450));

        btnContinuar1.setText("Continuar");
        btnContinuar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinuar1ActionPerformed(evt);
            }
        });
        jPanel2.add(btnContinuar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(385, 531, -1, -1));

        btnSalir1.setText("Salir");
        btnSalir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalir1ActionPerformed(evt);
            }
        });
        jPanel2.add(btnSalir1, new org.netbeans.lib.awtextra.AbsoluteConstraints(495, 531, 78, -1));

        jTabbedPane1.addTab("Unidad de medida de las condiciones 1", jPanel2);

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblUPM2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblUPM2.setText("UPMID:");
        jPanel3.add(lblUPM2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, -1, -1));

        txtUPM2.setEditable(false);
        txtUPM2.setEnabled(false);
        jPanel3.add(txtUPM2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 97, -1));

        lblSuelo2.setBackground(new java.awt.Color(153, 153, 153));
        lblSuelo2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblSuelo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSuelo2.setText("Evaluación de las condiciones de degradación del suelo");
        lblSuelo2.setOpaque(true);
        jPanel3.add(lblSuelo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 890, -1));

        lblSitio2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblSitio2.setText("Sitio:");
        jPanel3.add(lblSitio2, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 10, -1, -1));

        txtSitio2.setEditable(false);
        txtSitio2.setEnabled(false);
        jPanel3.add(txtSitio2, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 10, 100, -1));

        jPanel11.setBackground(new java.awt.Color(204, 204, 204));
        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblCanalillo.setBackground(new java.awt.Color(153, 153, 153));
        lblCanalillo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCanalillo.setText("Canalillo");
        lblCanalillo.setOpaque(true);

        lblNoCanalillos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNoCanalillos.setText("No");
        lblNoCanalillos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        cmbNoCanalillo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbNoCanalilloActionPerformed(evt);
            }
        });

        lblAnchoCanalillos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAnchoCanalillos.setText("Ancho");
        lblAnchoCanalillos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblProfundidadCanalillos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProfundidadCanalillos.setText("Profundidad");
        lblProfundidadCanalillos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnAgregarCanalillo.setText("Agregar");
        btnAgregarCanalillo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarCanalilloActionPerformed(evt);
            }
        });

        btnModificarCanalillo.setText("Modificar");
        btnModificarCanalillo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarCanalilloActionPerformed(evt);
            }
        });

        btnEliminarCanalillo.setText("Eliminar");
        btnEliminarCanalillo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarCanalilloActionPerformed(evt);
            }
        });

        grdCanalillo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        grdCanalillo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grdCanalilloMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(grdCanalillo);

        txtAnchoCanalillo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat(""))));
        txtAnchoCanalillo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtAnchoCanalilloFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtAnchoCanalilloFocusLost(evt);
            }
        });
        txtAnchoCanalillo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAnchoCanalilloKeyTyped(evt);
            }
        });

        txtProfundidadCanalillos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat(""))));
        txtProfundidadCanalillos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtProfundidadCanalillosFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtProfundidadCanalillosFocusLost(evt);
            }
        });
        txtProfundidadCanalillos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtProfundidadCanalillosKeyTyped(evt);
            }
        });

        chkCanalillo.setBackground(new java.awt.Color(204, 204, 204));
        chkCanalillo.setSelected(true);
        chkCanalillo.setText("Canalillo");
        chkCanalillo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCanalilloActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(chkCanalillo)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(lblCanalillo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                                    .addComponent(btnAgregarCanalillo, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnModificarCanalillo)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnEliminarCanalillo)))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(cmbNoCanalillo, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtAnchoCanalillo, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtProfundidadCanalillos, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(lblNoCanalillos, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblAnchoCanalillos, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblProfundidadCanalillos, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(18, Short.MAX_VALUE))))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCanalillo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkCanalillo)
                .addGap(7, 7, 7)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNoCanalillos)
                    .addComponent(lblAnchoCanalillos)
                    .addComponent(lblProfundidadCanalillos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbNoCanalillo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAnchoCanalillo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProfundidadCanalillos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificarCanalillo)
                    .addComponent(btnAgregarCanalillo)
                    .addComponent(btnEliminarCanalillo))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 270, 460));

        jPanel12.setBackground(new java.awt.Color(204, 204, 204));
        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblCarcaba.setBackground(new java.awt.Color(153, 153, 153));
        lblCarcaba.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCarcaba.setText("Cárcava");
        lblCarcaba.setOpaque(true);

        lblNoCarcavas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNoCarcavas.setText("No");
        lblNoCarcavas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        cmbNoCarcava.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbNoCarcavaActionPerformed(evt);
            }
        });

        lblAnchoCarcava.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAnchoCarcava.setText("Ancho");
        lblAnchoCarcava.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblProfundidadCarcava.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProfundidadCarcava.setText("Profundidad");
        lblProfundidadCarcava.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnAgregarCarcava.setText("Agregar");
        btnAgregarCarcava.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarCarcavaActionPerformed(evt);
            }
        });

        btnModificarCarcava.setText("Modificar");
        btnModificarCarcava.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarCarcavaActionPerformed(evt);
            }
        });

        btnEliminarCarcava.setText("Eliminar");
        btnEliminarCarcava.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarCarcavaActionPerformed(evt);
            }
        });

        grdCarcava.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        grdCarcava.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grdCarcavaMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(grdCarcava);

        txtAnchoCarcavas.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat(""))));
        txtAnchoCarcavas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtAnchoCarcavasFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtAnchoCarcavasFocusLost(evt);
            }
        });
        txtAnchoCarcavas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAnchoCarcavasKeyTyped(evt);
            }
        });

        txtProfundidadCarcavas.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat(""))));
        txtProfundidadCarcavas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtProfundidadCarcavasFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtProfundidadCarcavasFocusLost(evt);
            }
        });
        txtProfundidadCarcavas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtProfundidadCarcavasKeyTyped(evt);
            }
        });

        chkCarcavas.setBackground(new java.awt.Color(204, 204, 204));
        chkCarcavas.setSelected(true);
        chkCarcavas.setText("Cárcava");
        chkCarcavas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCarcavasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(chkCarcavas)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(lblCarcaba, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblNoCarcavas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbNoCarcava, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblAnchoCarcava, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                            .addComponent(txtAnchoCarcavas))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtProfundidadCarcavas)
                            .addComponent(lblProfundidadCarcava, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE))
                        .addGap(10, 10, 10))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(btnAgregarCarcava, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnModificarCarcava)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEliminarCarcava)))
                        .addContainerGap(11, Short.MAX_VALUE))))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCarcaba)
                .addGap(4, 4, 4)
                .addComponent(chkCarcavas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblNoCarcavas)
                        .addComponent(lblAnchoCarcava))
                    .addComponent(lblProfundidadCarcava))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbNoCarcava, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAnchoCarcavas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProfundidadCarcavas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificarCarcava)
                    .addComponent(btnAgregarCarcava)
                    .addComponent(btnEliminarCarcava))
                .addGap(13, 13, 13)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 70, 260, 460));

        jPanel13.setBackground(new java.awt.Color(204, 204, 204));
        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblPavimentos.setBackground(new java.awt.Color(153, 153, 153));
        lblPavimentos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPavimentos.setText("Pavimentos de erosión");
        lblPavimentos.setOpaque(true);

        lblNoPavimento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNoPavimento.setText("No");
        lblNoPavimento.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblDiametroPavimento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDiametroPavimento.setText("Diámetro");
        lblDiametroPavimento.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        cmbNoPavimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbNoPavimentoActionPerformed(evt);
            }
        });

        btnAgregarPavimento.setText("Agregar");
        btnAgregarPavimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarPavimentoActionPerformed(evt);
            }
        });

        btnModificarPavimento.setText("Modificar");
        btnModificarPavimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarPavimentoActionPerformed(evt);
            }
        });

        btnEliminarPavimento.setText("Eliminar");
        btnEliminarPavimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarPavimentoActionPerformed(evt);
            }
        });

        grdPavimento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        grdPavimento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grdPavimentoMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(grdPavimento);

        txtDiametroPavimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat(""))));
        txtDiametroPavimento.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDiametroPavimentoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDiametroPavimentoFocusLost(evt);
            }
        });
        txtDiametroPavimento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDiametroPavimentoKeyTyped(evt);
            }
        });

        chkPavimentos.setBackground(new java.awt.Color(204, 204, 204));
        chkPavimentos.setSelected(true);
        chkPavimentos.setText("Pavimentos de erosión");
        chkPavimentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPavimentosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPavimentos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNoPavimento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbNoPavimento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblDiametroPavimento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDiametroPavimento, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkPavimentos)
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(btnAgregarPavimento, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnModificarPavimento)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEliminarPavimento)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPavimentos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkPavimentos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNoPavimento)
                    .addComponent(lblDiametroPavimento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbNoPavimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiametroPavimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificarPavimento)
                    .addComponent(btnAgregarPavimento)
                    .addComponent(btnEliminarPavimento))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 70, -1, 460));

        btnContinuar2.setText("Continuar");
        btnContinuar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinuar2ActionPerformed(evt);
            }
        });
        jPanel3.add(btnContinuar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 550, -1, -1));

        btnSalir2.setText("Salir");
        btnSalir2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalir2ActionPerformed(evt);
            }
        });
        jPanel3.add(btnSalir2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 550, 78, -1));

        jTabbedPane1.addTab("Unidad de medida de las condiciones 2", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void grdCanalilloMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grdCanalilloMouseClicked
        if (evt.getButton() == 1) {
            int fila = grdCanalillo.getSelectedRow();
            String id = grdCanalillo.getValueAt(fila, 0).toString();
            this.canalilloID = Integer.parseInt(id);
            this.ceCanalillo = this.cdCanalillo.getCanalillo(this.canalilloID);
            Integer indexNumero = this.ceCanalillo.getNumero();
            cmbNoCanalillo.setSelectedItem(indexNumero);
            txtAnchoCanalillo.setText(String.valueOf(this.ceCanalillo.getAncho()));
            txtProfundidadCanalillos.setText(String.valueOf(this.ceCanalillo.getProfundidad()));
        }
    }//GEN-LAST:event_grdCanalilloMouseClicked

    private void grdCarcavaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grdCarcavaMouseClicked
        if (evt.getButton() == 1) {
            int fila = grdCarcava.getSelectedRow();
            String id = grdCarcava.getValueAt(fila, 0).toString();
            this.carcavaID = Integer.parseInt(id);
            this.ceCarcavas = this.cdCarcava.getCarcavas(this.carcavaID);
            Integer indexNumero = this.ceCarcavas.getNumero();
            cmbNoCarcava.setSelectedItem(indexNumero);
            txtAnchoCarcavas.setText(String.valueOf(this.ceCarcavas.getAncho()));
            txtProfundidadCarcavas.setText(String.valueOf(this.ceCarcavas.getProfundidad()));
        }
    }//GEN-LAST:event_grdCarcavaMouseClicked

    private void grdPavimentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grdPavimentoMouseClicked
        if (evt.getButton() == 1) {
            int fila = grdPavimento.getSelectedRow();
            String id = grdPavimento.getValueAt(fila, 0).toString();
            this.pavimentoID = Integer.parseInt(id);
            this.cePavimento = this.cdPavimento.getPavimento(this.pavimentoID);
            Integer indexNumero = this.cePavimento.getNumero();
            cmbNoPavimento.setSelectedItem(indexNumero);
            txtDiametroPavimento.setText(String.valueOf(this.cePavimento.getDiametro()));
        }
    }//GEN-LAST:event_grdPavimentoMouseClicked

    private void grdPedestalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grdPedestalMouseClicked
        if (evt.getButton() == 1) {
            int fila = grdPedestal.getSelectedRow();
            String id = grdPedestal.getValueAt(fila, 0).toString();
            this.pedestalID = Integer.parseInt(id);
            this.cePedestal = this.cdPedestal.getPedestal(this.pedestalID);
            Integer indexNumero = this.cePedestal.getNumero();
            cmbNoPedestal.setSelectedItem(indexNumero);
            txtAlturaPedestal.setText(String.valueOf(this.cePedestal.getAltura()));
        }
    }//GEN-LAST:event_grdPedestalMouseClicked

    private void grdErosionLaminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grdErosionLaminarMouseClicked
        if (evt.getButton() == 1) {
            int fila = grdErosionLaminar.getSelectedRow();
            String id = grdErosionLaminar.getValueAt(fila, 0).toString();
            this.erosionLaminarID = Integer.parseInt(id);
            this.ceErosion = this.cdErosion.getErosion(this.erosionLaminarID);
            Integer indexNumero = this.ceErosion.getNumero();
            cmbNoErosionLaminar.setSelectedItem(indexNumero);
            txtAnchoErosionLaminar.setText(String.valueOf(this.ceErosion.getAncho()));
            txtLargoErosionLaminar.setText(String.valueOf(this.ceErosion.getLargo()));
        }
    }//GEN-LAST:event_grdErosionLaminarMouseClicked

    private void grdCostrasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grdCostrasMouseClicked
        if (evt.getButton() == 1) {
            int fila = grdCostras.getSelectedRow();
            String id = grdCostras.getValueAt(fila, 0).toString();
            this.costrasID = Integer.parseInt(id);
            this.ceCostras = this.cdCostras.getCostras(this.costrasID);
            Integer indexNumero = this.ceCostras.getNumero();
            cmbNoCostra.setSelectedItem(indexNumero);
            txtDiametroCostras.setText(String.valueOf(this.ceCostras.getDiametro()));
        }
    }//GEN-LAST:event_grdCostrasMouseClicked

    private void btnAgregarPedestalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarPedestalActionPerformed
        fijarDatosPedestal();
        if (validarPedestal()) {
            crearPedestal();
            this.cdPedestal.renumerarRegistros(this.sitioID);
            this.combo.reiniciarComboModel(cmbNoPedestal);
            fillCmbNumeroPedestal();
            llenarTablaPedestal();
            cmbNoPedestal.setSelectedItem(null);
            txtAlturaPedestal.setText("");
            txtAlturaPedestal.requestFocus();
        }
    }//GEN-LAST:event_btnAgregarPedestalActionPerformed

    private void btnModificarPedestalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarPedestalActionPerformed
        fijarDatosPedestal();
        if (validarPedestal()) {
            modificarPedestal();
            llenarTablaPedestal();
            cmbNoPedestal.setSelectedItem(null);
            txtAlturaPedestal.setText("");
            txtAlturaPedestal.requestFocus();
        }
    }//GEN-LAST:event_btnModificarPedestalActionPerformed

    private void btnEliminarPedestalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarPedestalActionPerformed
        eliminarPedestal();
        this.cdPedestal.renumerarRegistros(this.sitioID);
        this.combo.reiniciarComboModel(cmbNoPedestal);
        fillCmbNumeroPedestal();
        llenarTablaPedestal();
        cmbNoPedestal.setSelectedItem(null);
        txtAlturaPedestal.setText("");
        txtAlturaPedestal.requestFocus();
    }//GEN-LAST:event_btnEliminarPedestalActionPerformed

    private void btnAgregarErosionLaminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarErosionLaminarActionPerformed
        fijarDatosErosion();
        if (validarErosion()) {
            crearErosion();
            this.cdErosion.renumerarRegistros(this.sitioID);
            combo.reiniciarComboModel(cmbNoErosionLaminar);
            fillCmbNumeroErosion();
            llenarTablaErosionLaminar();
            cmbNoErosionLaminar.setSelectedItem(null);
            txtAnchoErosionLaminar.setText("");
            txtLargoErosionLaminar.setText("");
            txtAnchoErosionLaminar.requestFocus();
        }
    }//GEN-LAST:event_btnAgregarErosionLaminarActionPerformed

    private void btnModificarErosionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarErosionActionPerformed
        fijarDatosErosion();
        if (validarErosion()) {
            modificarErosion();
            llenarTablaErosionLaminar();
            cmbNoErosionLaminar.setSelectedItem(null);
            txtAnchoErosionLaminar.setText("");
            txtLargoErosionLaminar.setText("");
            txtAnchoErosionLaminar.requestFocus();
        }
    }//GEN-LAST:event_btnModificarErosionActionPerformed

    private void btnEliminarErosionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarErosionActionPerformed
        eliminarErosion();
        this.cdErosion.renumerarRegistros(this.sitioID);
        combo.reiniciarComboModel(cmbNoErosionLaminar);
        fillCmbNumeroErosion();
        llenarTablaErosionLaminar();
        cmbNoErosionLaminar.setSelectedItem(null);
        txtAnchoErosionLaminar.setText("");
        txtLargoErosionLaminar.setText("");
        txtAnchoErosionLaminar.requestFocus();
    }//GEN-LAST:event_btnEliminarErosionActionPerformed

    private void btnAgregarCostraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarCostraActionPerformed
        fijarDatosCostras();
        if (validarCostras()) {
            crearCostras();
            this.cdCostras.renumerarRegistros(this.sitioID);
            combo.reiniciarComboModel(cmbNoCostra);
            fillCmbNumeroCostras();
            llenarTablaCostras();
            cmbNoCostra.setSelectedItem(null);
            txtDiametroCostras.setText("");
            txtDiametroCostras.requestFocus();
        }
    }//GEN-LAST:event_btnAgregarCostraActionPerformed

    private void btnModificarCostraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarCostraActionPerformed
        fijarDatosCostras();
        if (validarCostras()) {
            modificarCostra();
            llenarTablaCostras();
            cmbNoCostra.setSelectedItem(null);
            txtDiametroCostras.setText("");
            txtDiametroCostras.requestFocus();
        }
    }//GEN-LAST:event_btnModificarCostraActionPerformed

    private void btnEliminarCostraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarCostraActionPerformed
        eliminarCostra();
        this.cdCostras.renumerarRegistros(this.sitioID);
        combo.reiniciarComboModel(cmbNoCostra);
        fillCmbNumeroCostras();
        llenarTablaCostras();
        cmbNoCostra.setSelectedItem(null);
        txtDiametroCostras.setText("");
        txtDiametroCostras.requestFocus();
    }//GEN-LAST:event_btnEliminarCostraActionPerformed

    private void btnAgregarCanalilloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarCanalilloActionPerformed
        fijarDatosCanalillo();
        if (validarCanalillos()) {
            crearCanalillo();
            this.cdCanalillo.renumerarRegistros(this.sitioID);
            combo.reiniciarComboModel(cmbNoCanalillo);
            fillCmbNumeroCanalillo();
            llenarTablaCanalillo();
            cmbNoCanalillo.setSelectedItem(null);
            txtAnchoCanalillo.setText("");
            txtProfundidadCanalillos.setText("");
            txtAnchoCanalillo.requestFocus();
        }
    }//GEN-LAST:event_btnAgregarCanalilloActionPerformed

    private void btnModificarCanalilloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarCanalilloActionPerformed
        fijarDatosCanalillo();
        if (validarCanalillos()) {
            modificarCanalillo();
            llenarTablaCanalillo();
            cmbNoCanalillo.setSelectedItem(null);
            txtAnchoCanalillo.setText("");
            txtProfundidadCanalillos.setText("");
            txtAnchoCanalillo.requestFocus();
        }
    }//GEN-LAST:event_btnModificarCanalilloActionPerformed

    private void btnEliminarCanalilloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarCanalilloActionPerformed
        eliminarCanalillo();
        this.cdCanalillo.renumerarRegistros(this.sitioID);
        combo.reiniciarComboModel(cmbNoCanalillo);
        fillCmbNumeroCanalillo();
        llenarTablaCanalillo();
        cmbNoCanalillo.setSelectedItem(null);
        txtAnchoCanalillo.setText("");
        txtProfundidadCanalillos.setText("");
        txtAnchoCanalillo.requestFocus();
    }//GEN-LAST:event_btnEliminarCanalilloActionPerformed

    private void btnAgregarCarcavaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarCarcavaActionPerformed
        fijarDatosCarcava();
        if (validarCarcava()) {
            crearCarcava();
            this.cdCarcava.renumerarRegistros(this.sitioID);
            combo.reiniciarComboModel(cmbNoCarcava);
            fillCmbNumeroCarcavas();
            llenarTablaCarcava();
            cmbNoCarcava.setSelectedItem(null);
            txtAnchoCarcavas.setText("");
            txtProfundidadCarcavas.setText("");
            txtAnchoCarcavas.requestFocus();
        }
    }//GEN-LAST:event_btnAgregarCarcavaActionPerformed

    private void btnModificarCarcavaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarCarcavaActionPerformed
        fijarDatosCarcava();
        if (validarCarcava()) {
            modificarCarcava();
            llenarTablaCarcava();
            cmbNoCarcava.setSelectedItem(null);
            txtAnchoCarcavas.setText("");
            txtProfundidadCarcavas.setText("");
            txtAnchoCarcavas.requestFocus();
        }
    }//GEN-LAST:event_btnModificarCarcavaActionPerformed

    private void btnEliminarCarcavaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarCarcavaActionPerformed
        eliminarCarcava();
        this.cdCarcava.renumerarRegistros(this.sitioID);
        combo.reiniciarComboModel(cmbNoCarcava);
        fillCmbNumeroCarcavas();
        llenarTablaCarcava();
        cmbNoCarcava.setSelectedItem(null);
        txtAnchoCarcavas.setText("");
        txtProfundidadCarcavas.setText("");
        txtAnchoCarcavas.requestFocus();
    }//GEN-LAST:event_btnEliminarCarcavaActionPerformed

    private void btnAgregarPavimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarPavimentoActionPerformed
        fijarDatosPavimento();
        if (validarPavimento()) {
            crearPavimento();
            this.cdPavimento.renumerarRegistros(this.sitioID);
            combo.reiniciarComboModel(cmbNoPavimento);
            fillCmbNumeroPavimento();
            llenarTablaPavimento();
            cmbNoPavimento.setSelectedItem(null);
            txtDiametroPavimento.setText("");
            txtDiametroPavimento.requestFocus();
        }
    }//GEN-LAST:event_btnAgregarPavimentoActionPerformed

    private void btnModificarPavimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarPavimentoActionPerformed
        fijarDatosPavimento();
        if (validarPavimento()) {
            modificarPavimento();
            llenarTablaPavimento();
            cmbNoPavimento.setSelectedItem(null);
            txtDiametroPavimento.setText("");
            txtDiametroPavimento.requestFocus();
        }
    }//GEN-LAST:event_btnModificarPavimentoActionPerformed

    private void btnEliminarPavimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarPavimentoActionPerformed
        eliminarPavimento();
        this.cdPavimento.renumerarRegistros(this.sitioID);
        combo.reiniciarComboModel(cmbNoPavimento);
        fillCmbNumeroPavimento();
        llenarTablaPavimento();
        cmbNoPavimento.setSelectedItem(null);
        txtDiametroPavimento.setText("");
        txtDiametroPavimento.requestFocus();
    }//GEN-LAST:event_btnEliminarPavimentoActionPerformed

    private void txtAlturaPedestalFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAlturaPedestalFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtAlturaPedestal.selectAll();
            }
        });
    }//GEN-LAST:event_txtAlturaPedestalFocusGained

    private void txtAnchoErosionLaminarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAnchoErosionLaminarFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtAnchoErosionLaminar.selectAll();
            }
        });
    }//GEN-LAST:event_txtAnchoErosionLaminarFocusGained

    private void txtLargoErosionLaminarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLargoErosionLaminarFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtLargoErosionLaminar.selectAll();
            }
        });
    }//GEN-LAST:event_txtLargoErosionLaminarFocusGained

    private void txtDiametroCostrasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDiametroCostrasFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtDiametroCostras.selectAll();
            }
        });
    }//GEN-LAST:event_txtDiametroCostrasFocusGained

    private void txtAnchoCanalilloFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAnchoCanalilloFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtAnchoCanalillo.selectAll();
            }
        });
    }//GEN-LAST:event_txtAnchoCanalilloFocusGained

    private void txtProfundidadCanalillosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtProfundidadCanalillosFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtProfundidadCanalillos.selectAll();
            }
        });
    }//GEN-LAST:event_txtProfundidadCanalillosFocusGained

    private void txtAnchoCarcavasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAnchoCarcavasFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtAnchoCarcavas.selectAll();
            }
        });
    }//GEN-LAST:event_txtAnchoCarcavasFocusGained

    private void txtProfundidadCarcavasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtProfundidadCarcavasFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtProfundidadCarcavas.selectAll();
            }
        });
    }//GEN-LAST:event_txtProfundidadCarcavasFocusGained

    private void txtDiametroPavimentoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDiametroPavimentoFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtDiametroPavimento.selectAll();
            }
        });
    }//GEN-LAST:event_txtDiametroPavimentoFocusGained

    private void txtAnchoCanalilloFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAnchoCanalilloFocusLost
        if (txtAnchoCanalillo.getText().isEmpty()) {
            txtAnchoCanalillo.setValue(null);
        }
    }//GEN-LAST:event_txtAnchoCanalilloFocusLost

    private void txtProfundidadCanalillosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtProfundidadCanalillosFocusLost
        if (txtProfundidadCanalillos.getText().isEmpty()) {
            txtProfundidadCanalillos.setValue(null);
        }
    }//GEN-LAST:event_txtProfundidadCanalillosFocusLost

    private void txtAnchoCarcavasFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAnchoCarcavasFocusLost
        if (txtAnchoCarcavas.getText().isEmpty()) {
            txtAnchoCarcavas.setValue(null);
        }
    }//GEN-LAST:event_txtAnchoCarcavasFocusLost

    private void txtProfundidadCarcavasFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtProfundidadCarcavasFocusLost
        if (txtProfundidadCarcavas.getText().isEmpty()) {
            txtProfundidadCarcavas.setValue(null);
        }
    }//GEN-LAST:event_txtProfundidadCarcavasFocusLost

    private void txtDiametroPavimentoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDiametroPavimentoFocusLost
        if (txtDiametroPavimento.getText().isEmpty()) {
            txtDiametroPavimento.setValue(null);
        }
    }//GEN-LAST:event_txtDiametroPavimentoFocusLost

    private void txtAlturaPedestalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAlturaPedestalFocusLost
        if (txtAlturaPedestal.getText().isEmpty()) {
            txtAlturaPedestal.setValue(null);
        }
    }//GEN-LAST:event_txtAlturaPedestalFocusLost

    private void txtAnchoErosionLaminarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAnchoErosionLaminarFocusLost
        if (txtAnchoErosionLaminar.getText().isEmpty()) {
            txtAnchoErosionLaminar.setValue(null);
        }
    }//GEN-LAST:event_txtAnchoErosionLaminarFocusLost

    private void txtLargoErosionLaminarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLargoErosionLaminarFocusLost
        if (txtLargoErosionLaminar.getText().isEmpty()) {
            txtLargoErosionLaminar.setValue(null);
        }
    }//GEN-LAST:event_txtLargoErosionLaminarFocusLost

    private void txtDiametroCostrasFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDiametroCostrasFocusLost
        if (txtDiametroCostras.getText().isEmpty()) {
            txtDiametroCostras.setValue(null);
        }
    }//GEN-LAST:event_txtDiametroCostrasFocusLost

    private void cmbNoCanalilloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbNoCanalilloActionPerformed
        if(cmbNoCanalillo.getSelectedItem() != null){
            CECanalillo canalillo;
            this.ceCanalillo.setSitioID(this.sitioID);
            this.ceCanalillo.setNumero((Integer)cmbNoCanalillo.getSelectedItem());
            canalillo = this.cdCanalillo.getCanalilloPorNumero(ceCanalillo);
            txtAnchoCanalillo.setText(String.valueOf(canalillo.getAncho()));
            txtProfundidadCanalillos.setText(String.valueOf(canalillo.getProfundidad()));
        } else {
            txtAnchoCanalillo.setText("");
            txtProfundidadCanalillos.setText("");
        }
    }//GEN-LAST:event_cmbNoCanalilloActionPerformed

    private void cmbNoPedestalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbNoPedestalActionPerformed
        if (cmbNoPedestal.getSelectedItem() != null) {
            CEPedestal pedestal;
            this.cePedestal.setSitioID(this.sitioID);
            this.cePedestal.setNumero((Integer) cmbNoPedestal.getSelectedItem());
            pedestal = this.cdPedestal.getPedestalPorNumero(cePedestal);
            txtAlturaPedestal.setText(String.valueOf(pedestal.getAltura()));
        } else {
            txtAlturaPedestal.setText("");
        }
    }//GEN-LAST:event_cmbNoPedestalActionPerformed

    private void cmbNoErosionLaminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbNoErosionLaminarActionPerformed
        if (cmbNoErosionLaminar.getSelectedItem() != null) {
            CEErosionLaminar erosion;
            this.ceErosion.setSitioID(this.sitioID);
            this.ceErosion.setNumero((Integer) cmbNoErosionLaminar.getSelectedItem());
            erosion = this.cdErosion.getErosionPorNumero(ceErosion);
            txtAnchoErosionLaminar.setText(String.valueOf(erosion.getAncho()));
            txtLargoErosionLaminar.setText(String.valueOf(erosion.getLargo()));
        } else {
            txtAnchoErosionLaminar.setText("");
            txtLargoErosionLaminar.setText("");
        }
    }//GEN-LAST:event_cmbNoErosionLaminarActionPerformed

    private void cmbNoCostraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbNoCostraActionPerformed
        if (cmbNoCostra.getSelectedItem() != null) {
            CECostras costra;
            this.ceCostras.setSitioID(sitioID);
            this.ceCostras.setNumero((Integer) cmbNoCostra.getSelectedItem());
            costra = this.cdCostras.getCostrasPorNumero(ceCostras);
            txtDiametroCostras.setText(String.valueOf(costra.getDiametro()));
        } else {
            txtDiametroCostras.setText("");
        }
    }//GEN-LAST:event_cmbNoCostraActionPerformed

    private void cmbNoCarcavaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbNoCarcavaActionPerformed
        if (cmbNoCarcava.getSelectedItem() != null) {
            CECarcavas carcava;
            this.ceCarcavas.setSitioID(this.sitioID);
            this.ceCarcavas.setNumero((Integer) cmbNoCarcava.getSelectedItem());
            carcava = this.cdCarcava.getCarcavasPorNumero(this.ceCarcavas);
            txtAnchoCarcavas.setText(String.valueOf(carcava.getAncho()));
            txtProfundidadCarcavas.setText(String.valueOf(carcava.getProfundidad()));
        } else {
            txtAnchoCarcavas.setText("");
            txtProfundidadCarcavas.setText("");
        }
    }//GEN-LAST:event_cmbNoCarcavaActionPerformed

    private void cmbNoPavimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbNoPavimentoActionPerformed
        if (cmbNoPavimento.getSelectedItem() != null) {
            CEPavimentosErosion pavimento;
            this.cePavimento.setSitioID(this.sitioID);
            this.cePavimento.setNumero((Integer) cmbNoPavimento.getSelectedItem());
            pavimento = this.cdPavimento.getPavimentoPorNumero(this.cePavimento);
            txtDiametroPavimento.setText(String.valueOf(pavimento.getDiametro()));
        } else {
            txtDiametroPavimento.setText("");
        }
    }//GEN-LAST:event_cmbNoPavimentoActionPerformed

    private void btnContinuar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuar1ActionPerformed
        
        if (chkPedestal.isSelected() && this.cdPedestal.validarTablaPedestal(this.sitioID)) {
            JOptionPane.showMessageDialog(null, "Si selecciona pedestales, se deben capturar"
                    + "", "Suelo", JOptionPane.INFORMATION_MESSAGE);
            txtAlturaPedestal.requestFocus();
        } else if (chkErosionLaminar.isSelected() && this.cdErosion.validarTablaErosionLaminar(this.sitioID)) {
            JOptionPane.showMessageDialog(null, "Si selecciona erosión laminar, se debe capturar"
                    + "", "Suelo", JOptionPane.INFORMATION_MESSAGE);
            txtAnchoErosionLaminar.requestFocus();
        } else if (chkCostras.isSelected() && this.cdCostras.validarTablaCostras(this.sitioID)) {
            JOptionPane.showMessageDialog(null, "Si selecciona costras, se deben capturar"
                    + "", "Suelo", JOptionPane.INFORMATION_MESSAGE);
            txtDiametroCostras.requestFocus();
        } else if (chkCanalillo.isSelected() && this.cdCanalillo.validarTablaCanalillos(this.sitioID)) {
            JOptionPane.showMessageDialog(null, "Si selecciona canalillos, se deben capturar"
                    + "", "Suelo", JOptionPane.INFORMATION_MESSAGE);
            //cmbNoCanalillo.requestFocus();
        } else if (chkCarcavas.isSelected() && this.cdCarcava.validarTablaCarcavas(this.sitioID)) {
            JOptionPane.showMessageDialog(null, "Si selecciona carcavas, se deben capturar"
                    + "", "Suelo", JOptionPane.INFORMATION_MESSAGE);
            //cmbNoCarcava.requestFocus();
        } else if (chkPavimentos.isSelected() && this.cdPavimento.validarTablaPavimentos(this.sitioID)) {
            JOptionPane.showMessageDialog(null, "Si selecciona pavimentos, se deben capturar"
                    + "", "Suelo", JOptionPane.INFORMATION_MESSAGE);
            //cmbNoPavimento.requestFocus();
        } else if (modificar == 0) {
            this.hide();
            UPMForms.erosionHidrica.setDatosiniciales(this.ceSitio);
            UPMForms.erosionHidrica.setVisible(true);
            this.cdSecuencia.updateSecuencia(this.ceSitio, FORMATO_ID, 1);
        } else {
            
            this.hide();
            UPMForms.erosionHidrica.revisarErosionHidrica(this.ceSitio);
            UPMForms.erosionHidrica.setVisible(true);
        }
    }//GEN-LAST:event_btnContinuar1ActionPerformed

    private void txtAlturaPedestalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAlturaPedestalKeyTyped
       numeros.keyTyped(evt);
    }//GEN-LAST:event_txtAlturaPedestalKeyTyped

    private void txtAnchoErosionLaminarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAnchoErosionLaminarKeyTyped
       numeros.keyTyped(evt);
    }//GEN-LAST:event_txtAnchoErosionLaminarKeyTyped

    private void txtLargoErosionLaminarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLargoErosionLaminarKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtLargoErosionLaminarKeyTyped

    private void txtDiametroCostrasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiametroCostrasKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtDiametroCostrasKeyTyped

    private void txtAnchoCanalilloKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAnchoCanalilloKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtAnchoCanalilloKeyTyped

    private void txtProfundidadCanalillosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProfundidadCanalillosKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtProfundidadCanalillosKeyTyped

    private void txtAnchoCarcavasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAnchoCarcavasKeyTyped
       numeros.keyTyped(evt);
    }//GEN-LAST:event_txtAnchoCarcavasKeyTyped

    private void txtProfundidadCarcavasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProfundidadCarcavasKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtProfundidadCarcavasKeyTyped

    private void txtDiametroPavimentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiametroPavimentoKeyTyped
       numeros.keyTyped(evt);
    }//GEN-LAST:event_txtDiametroPavimentoKeyTyped

    private void btnSalir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalir1ActionPerformed
        this.hide();
        funciones.manipularBotonesMenuPrincipal(false);
    }//GEN-LAST:event_btnSalir1ActionPerformed

    private void btnContinuar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuar2ActionPerformed
        if (chkPedestal.isSelected() && this.cdPedestal.validarTablaPedestal(this.sitioID)) {
            JOptionPane.showMessageDialog(null, "Si selecciona pedestales, se deben capturar"
                    + "", "Suelo", JOptionPane.INFORMATION_MESSAGE);
           // cmbNoPedestal.requestFocus();
        } else if (chkErosionLaminar.isSelected() && this.cdErosion.validarTablaErosionLaminar(this.sitioID)) {
            JOptionPane.showMessageDialog(null, "Si selecciona erosión laminar, se debe capturar"
                    + "", "Suelo", JOptionPane.INFORMATION_MESSAGE);
           // cmbNoErosionLaminar.requestFocus();
        } else if (chkCostras.isSelected() && this.cdCostras.validarTablaCostras(this.sitioID)) {
            JOptionPane.showMessageDialog(null, "Si selecciona costras, se deben capturar"
                    + "", "Suelo", JOptionPane.INFORMATION_MESSAGE);
           // cmbNoCostra.requestFocus();
        } else if (chkCanalillo.isSelected() && this.cdCanalillo.validarTablaCanalillos(this.sitioID)) {
            JOptionPane.showMessageDialog(null, "Si selecciona canalillos, se deben capturar"
                    + "", "Suelo", JOptionPane.INFORMATION_MESSAGE);
            txtAnchoCanalillo.requestFocus();
        } else if (chkCarcavas.isSelected() && this.cdCarcava.validarTablaCarcavas(this.sitioID)) {
            JOptionPane.showMessageDialog(null, "Si selecciona carcavas, se deben capturar"
                    + "", "Suelo", JOptionPane.INFORMATION_MESSAGE);
            txtAnchoCarcavas.requestFocus();
        } else if (chkPavimentos.isSelected() && this.cdPavimento.validarTablaPavimentos(this.sitioID)) {
            JOptionPane.showMessageDialog(null, "Si selecciona pavimentos, se deben capturar"
                    + "", "Suelo", JOptionPane.INFORMATION_MESSAGE);
            txtDiametroPavimento.requestFocus();
        } else if (modificar == 0) {
            this.hide();
            UPMForms.erosionHidrica.setDatosiniciales(this.ceSitio);
            UPMForms.erosionHidrica.setVisible(true);
            this.cdSecuencia.updateSecuencia(this.ceSitio, FORMATO_ID, 1);
        } else {
            this.hide();
            UPMForms.erosionHidrica.revisarErosionHidrica(this.ceSitio);
            UPMForms.erosionHidrica.setVisible(true);
        }
    }//GEN-LAST:event_btnContinuar2ActionPerformed

    private void btnSalir2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalir2ActionPerformed
       this.hide();
       funciones.manipularBotonesMenuPrincipal(false);
    }//GEN-LAST:event_btnSalir2ActionPerformed

    private void chkPedestalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPedestalActionPerformed
        if (chkPedestal.isSelected()) {
            manipularControlesPedestal(true);
        } else {
            Object[] opciones = {"Si", "No"};
            int respuesta = JOptionPane.showOptionDialog(null, "Si capturo, se borrarán todos los datos de pedestales, ¿Esta seguro?",
                    "Suelo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[1]);
            if (respuesta == JOptionPane.YES_OPTION) {
                this.cdPedestal.deletePedestalSitio(this.sitioID);
                this.funciones.reiniciarTabla(grdPedestal);
                llenarTablaPedestal();
                this.funciones.reiniciarComboModel(cmbNoPedestal);
                manipularControlesPedestal(false);
            } else {
                chkPedestal.setSelected(true);
                chkPedestal.requestFocus();
            }
        }
    }//GEN-LAST:event_chkPedestalActionPerformed

    private void chkErosionLaminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkErosionLaminarActionPerformed
        if (chkErosionLaminar.isSelected()) {
            manipularControlesErosionLaminar(true);
        } else {
            Object[] opciones = {"Si", "No"};
            int respuesta = JOptionPane.showOptionDialog(null, "Si capturo, se borrarán todos los datos de erosión laminar, ¿Esta seguro?",
                    "Suelo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[1]);
            if (respuesta == JOptionPane.YES_OPTION) {
                this.cdErosion.deleteErosionLaminarSitio(this.sitioID);
                this.funciones.reiniciarTabla(grdErosionLaminar);
                llenarTablaErosionLaminar();
                this.funciones.reiniciarComboModel(cmbNoErosionLaminar);
                manipularControlesErosionLaminar(false);
            } else {
                chkErosionLaminar.setSelected(true);
                chkErosionLaminar.requestFocus();
            }
        }
    }//GEN-LAST:event_chkErosionLaminarActionPerformed

    private void chkCostrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCostrasActionPerformed
        if (chkCostras.isSelected()) {
            manipularControlesCostras(true);
        } else {
            Object[] opciones = {"Si", "No"};
            int respuesta = JOptionPane.showOptionDialog(null, "Si capturo, se borrarán todos los datos de costras, ¿Esta seguro?",
                    "Suelo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[1]);
            if (respuesta == JOptionPane.YES_OPTION) {
                this.cdCostras.deleteCostrasSitio(this.sitioID);
                this.funciones.reiniciarTabla(grdCostras);
                llenarTablaCostras();
                this.funciones.reiniciarComboModel(cmbNoCostra);
                manipularControlesCostras(false);
            } else {
                chkCostras.setSelected(true);
                chkCostras.requestFocus();
            }
        }
    }//GEN-LAST:event_chkCostrasActionPerformed

    private void chkCanalilloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCanalilloActionPerformed
        if (chkCanalillo.isSelected()) {
            manipularControlesCanalillo(true);
        } else {
            Object[] opciones = {"Si", "No"};
            int respuesta = JOptionPane.showOptionDialog(null, "Si capturo, se borrarán todos los datos de canalillos, ¿Esta seguro?",
                    "Suelo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[1]);
            if (respuesta == JOptionPane.YES_OPTION) {
                this.cdCanalillo.deleteCanalilloSitio(this.sitioID);
                this.funciones.reiniciarTabla(grdCanalillo);
                llenarTablaCanalillo();
                this.funciones.reiniciarComboModel(cmbNoCanalillo);
                manipularControlesCanalillo(false);
            } else {
                chkCanalillo.setSelected(true);
                chkCanalillo.requestFocus();
            }
        }
    }//GEN-LAST:event_chkCanalilloActionPerformed

    private void chkCarcavasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCarcavasActionPerformed
        if (chkCarcavas.isSelected()) {
            manipularControlesCarcava(true);
        } else {
            Object[] opciones = {"Si", "No"};
            int respuesta = JOptionPane.showOptionDialog(null, "Si capturo, se borrarán todos los datos de carcavas, ¿Esta seguro?",
                    "Suelo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[1]);
            if (respuesta == JOptionPane.YES_OPTION) {
                this.cdCarcava.deleteCarcavaSitio(this.sitioID);
                this.funciones.reiniciarTabla(grdCarcava);
                llenarTablaCarcava();
                this.funciones.reiniciarComboModel(cmbNoCarcava);
                manipularControlesCarcava(false);
            } else {
                chkCarcavas.setSelected(true);
                chkCarcavas.requestFocus();
            }
        }
    }//GEN-LAST:event_chkCarcavasActionPerformed

    private void chkPavimentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPavimentosActionPerformed
        if (chkPavimentos.isSelected()) {
            manipularControlesPavimentos(true);
        } else {
            Object[] opciones = {"Si", "No"};
            int respuesta = JOptionPane.showOptionDialog(null, "Si capturo, se borrarán todos los datos de pavimentos, ¿Esta seguro?",
                    "Suelo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[1]);
            if (respuesta == JOptionPane.YES_OPTION) {
                this.cdPavimento.deletePavimentoSitio(this.sitioID);
                this.funciones.reiniciarTabla(grdPavimento);
                llenarTablaPavimento();
                this.funciones.reiniciarComboModel(cmbNoPavimento);
                manipularControlesPavimentos(false);
            } else {
                chkPavimentos.setSelected(true);
                chkPavimentos.requestFocus();
            }
        }
    }//GEN-LAST:event_chkPavimentosActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarCanalillo;
    private javax.swing.JButton btnAgregarCarcava;
    private javax.swing.JButton btnAgregarCostra;
    private javax.swing.JButton btnAgregarErosionLaminar;
    private javax.swing.JButton btnAgregarPavimento;
    private javax.swing.JButton btnAgregarPedestal;
    private javax.swing.JButton btnContinuar1;
    private javax.swing.JButton btnContinuar2;
    private javax.swing.JButton btnEliminarCanalillo;
    private javax.swing.JButton btnEliminarCarcava;
    private javax.swing.JButton btnEliminarCostra;
    private javax.swing.JButton btnEliminarErosion;
    private javax.swing.JButton btnEliminarPavimento;
    private javax.swing.JButton btnEliminarPedestal;
    private javax.swing.JButton btnModificarCanalillo;
    private javax.swing.JButton btnModificarCarcava;
    private javax.swing.JButton btnModificarCostra;
    private javax.swing.JButton btnModificarErosion;
    private javax.swing.JButton btnModificarPavimento;
    private javax.swing.JButton btnModificarPedestal;
    private javax.swing.JButton btnSalir1;
    private javax.swing.JButton btnSalir2;
    private javax.swing.JCheckBox chkCanalillo;
    private javax.swing.JCheckBox chkCarcavas;
    private javax.swing.JCheckBox chkCostras;
    private javax.swing.JCheckBox chkErosionLaminar;
    private javax.swing.JCheckBox chkPavimentos;
    private javax.swing.JCheckBox chkPedestal;
    private javax.swing.JComboBox cmbNoCanalillo;
    private javax.swing.JComboBox cmbNoCarcava;
    private javax.swing.JComboBox cmbNoCostra;
    private javax.swing.JComboBox cmbNoErosionLaminar;
    private javax.swing.JComboBox cmbNoPavimento;
    private javax.swing.JComboBox cmbNoPedestal;
    private javax.swing.JTable grdCanalillo;
    private javax.swing.JTable grdCarcava;
    private javax.swing.JTable grdCostras;
    private javax.swing.JTable grdErosionLaminar;
    private javax.swing.JTable grdPavimento;
    private javax.swing.JTable grdPedestal;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JLabel lblAlturaPedestal;
    private javax.swing.JLabel lblAnchoCanalillos;
    private javax.swing.JLabel lblAnchoCarcava;
    private javax.swing.JLabel lblAnchoErosionLaminar;
    private javax.swing.JLabel lblCanalillo;
    private javax.swing.JLabel lblCarcaba;
    private javax.swing.JLabel lblCostras;
    private javax.swing.JLabel lblDiametroCostra;
    private javax.swing.JLabel lblDiametroPavimento;
    private javax.swing.JLabel lblErosionLaminar;
    private javax.swing.JLabel lblLargoErosionLaminar;
    private javax.swing.JLabel lblNoCanalillos;
    private javax.swing.JLabel lblNoCarcavas;
    private javax.swing.JLabel lblNoCostra;
    private javax.swing.JLabel lblNoErosionLaminar;
    private javax.swing.JLabel lblNoPavimento;
    private javax.swing.JLabel lblNoPedestal;
    private javax.swing.JLabel lblPavimentos;
    private javax.swing.JLabel lblPedestal;
    private javax.swing.JLabel lblProfundidadCanalillos;
    private javax.swing.JLabel lblProfundidadCarcava;
    private javax.swing.JLabel lblSitio;
    private javax.swing.JLabel lblSitio1;
    private javax.swing.JLabel lblSitio2;
    private javax.swing.JLabel lblSuelo;
    private javax.swing.JLabel lblSuelo1;
    private javax.swing.JLabel lblSuelo2;
    private javax.swing.JLabel lblUPM;
    private javax.swing.JLabel lblUPM1;
    private javax.swing.JLabel lblUPM2;
    private javax.swing.JFormattedTextField txtAlturaPedestal;
    private javax.swing.JFormattedTextField txtAnchoCanalillo;
    private javax.swing.JFormattedTextField txtAnchoCarcavas;
    private javax.swing.JFormattedTextField txtAnchoErosionLaminar;
    private javax.swing.JFormattedTextField txtDiametroCostras;
    private javax.swing.JFormattedTextField txtDiametroPavimento;
    private javax.swing.JFormattedTextField txtLargoErosionLaminar;
    private javax.swing.JFormattedTextField txtProfundidadCanalillos;
    private javax.swing.JFormattedTextField txtProfundidadCarcavas;
    private javax.swing.JTextField txtSitio;
    private javax.swing.JTextField txtSitio1;
    private javax.swing.JTextField txtSitio2;
    private javax.swing.JTextField txtUPM;
    private javax.swing.JTextField txtUPM1;
    private javax.swing.JTextField txtUPM2;
    // End of variables declaration//GEN-END:variables
}
