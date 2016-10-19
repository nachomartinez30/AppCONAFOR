package gob.conafor.suelo.vie;

import gob.conafor.sitio.mod.CDTransponder;
import gob.conafor.sitio.mod.CESitio;
import gob.conafor.sitio.mod.CETransponder;
import gob.conafor.suelo.mod.CDDeformacionViento;
import gob.conafor.suelo.mod.CDSuelo;
import gob.conafor.suelo.mod.CEDeformacionViento;
import gob.conafor.suelo.mod.CELongitudMonticulos;
import gob.conafor.suelo.mod.CESuelo;
import gob.conafor.sys.mod.CDSecuencia;
import gob.conafor.sys.mod.CDSeguimientoUPM;
import gob.conafor.sys.mod.CESeccionesCapturadas;
import gob.conafor.upm.vie.UPMForms;
import gob.conafor.utils.Datos;
import gob.conafor.utils.FuncionesComunes;
import gob.conafor.utils.Tablas;
import gob.conafor.utils.ValidacionesComunes;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class FrmDeformacionViento extends JInternalFrame {

    private int upmID;
    private int sitioID;
    private int sitio;
    private static final int FORMATO_ID = 12;
    private int deformacionTerreno;
    private int observacion;
    private CESitio ceSitio = new CESitio();
    private CESuelo ceSuelo = new CESuelo();
    private CDSuelo cdSuelo = new CDSuelo();
    private CEDeformacionViento ceMonticulo = new CEDeformacionViento();
    private CELongitudMonticulos ceLongitud = new CELongitudMonticulos();
    private CDDeformacionViento cdDeformacion = new CDDeformacionViento();
    private FuncionesComunes combo = new FuncionesComunes();
    private Float altura;
    private Float diametro;
    private Float longitudDeformacion;
    private Float distancia;
    private Integer azimut;
    private Float longitudMonticulos;
    private Integer numeroMonticulos;
    private Float alturaPromedio;
    private Float anchoPromedio;
    private Float longitudPromedio;
    private Float volumenCalculado;
    private Integer tipoColocacionID;
    private String especificarTipo;
    private String observaciones;
    private ValidacionesComunes validacion = new ValidacionesComunes();
    private CETransponder ceTransponder = new CETransponder();
    private CDTransponder cdTransponder = new CDTransponder();
    private CESeccionesCapturadas seccionActual = new CESeccionesCapturadas();
    private CESeccionesCapturadas seccionSiguiente = new CESeccionesCapturadas();
    private CDSeguimientoUPM seguimiento = new CDSeguimientoUPM();
    private Datos numeros = new Datos();
    private CDSecuencia cdSecuencia = new CDSecuencia();
    private int actualizar;
    private FuncionesComunes funciones = new FuncionesComunes();

    public FrmDeformacionViento() {
        initComponents();
        combo.reiniciarComboModel(cmbMedicionMonticulos);
        combo.reiniciarComboModel(cmbLongitudMonticulos);
        this.deformacionTerreno = 22;
        this.observacion = 23;
    }

    public void setDatosiniciales(CESitio ceSitio) {
        this.upmID = ceSitio.getUpmID();
        this.sitioID = ceSitio.getSitioID();
        this.sitio = ceSitio.getSitio();
        this.txtUPM.setText(String.valueOf(this.upmID));
        this.txtSitio.setText(String.valueOf(this.sitio));
        this.ceSitio.setSitioID(this.sitioID);
        this.ceSitio.setUpmID(this.upmID);
        this.ceSitio.setSitio(this.sitio);
        this.ceSitio = ceSitio;
        // cdSecuencia.insertFormatoCapturado(this.ceSitio, FORMATO_ID);
        fillCmbMedionesMonticulos();
        fillCmbLongitudMonticulos();
        llenarTablaDeformacionViento();
        llenarTablaLongitudMonticulos();
        calcularMonticulos();
        limpiarCamposCalculados();
        limpiarDatosMonticulos();
        limpiarDatosLongitud();
        limpiarColocacionTAG();
        this.actualizar = 0;
        funciones.manipularBotonesMenuPrincipal(true);
    }

    public void revisarDeformacionViento(CESitio ceSitio) {
        this.upmID = ceSitio.getUpmID();
        this.sitioID = ceSitio.getSitioID();
        this.sitio = ceSitio.getSitio();
        this.txtUPM.setText(String.valueOf(this.upmID));
        this.txtSitio.setText(String.valueOf(this.sitio));
        this.ceSitio.setSitioID(this.sitioID);
        this.ceSitio.setUpmID(this.upmID);
        this.ceSitio.setSitio(this.sitio);
        this.ceSitio.setSecuencia(ceSitio.getSecuencia());
        fillCmbMedionesMonticulos();
        fillCmbLongitudMonticulos();
        llenarTablaDeformacionViento();
        llenarTablaLongitudMonticulos();
        calcularMonticulos();
            this.ceTransponder = this.cdTransponder.getTransponder(ceSitio.getSitioID());
            int tipoColocacion = this.ceTransponder.getTipoColocacionID();
            switch (tipoColocacion) {
                case 1:
                    rbtUnidosVarilla.setSelected(true);
                    break;
                case 2:
                    rbtUnidosPegamento.setSelected(true);
                    break;
                case 3:
                    rbtClavadoArbol.setSelected(true);
                    break;
                case 4:
                    rbtOtroLugar.setSelected(true);
                    break;
                case 5:
                    rbtNoSepudo.setSelected(true);
                    break;
            }
            if (tipoColocacion == 5 || tipoColocacion == 6) {
                txtEspecifiqueTransponder.setEnabled(true);
                txtEspecifiqueTransponder.setText(this.ceTransponder.getEspecifique());
            }
            txtObservacionesTransponder.setText(this.ceTransponder.getObservaciones());
      
        this.actualizar = 1;
        funciones.manipularBotonesMenuPrincipal(true);
        limpiarDatosMonticulos();
        limpiarDatosLongitud();
    }

    private void fillCmbMedionesMonticulos() {
        List<Integer> listMedionesMonticulos = new ArrayList<>();
        listMedionesMonticulos = this.cdDeformacion.getMedicionMonticulo(this.sitioID);
        if (listMedionesMonticulos != null) {
            int size = listMedionesMonticulos.size();
            for (int i = 0; i < size; i++) {
                cmbMedicionMonticulos.addItem(listMedionesMonticulos.get(i));
            }
        }
    }

    private void fillCmbLongitudMonticulos() {
        List<Integer> listLongitudMonticulo = new ArrayList<>();
        listLongitudMonticulo = this.cdDeformacion.getCampoLongitudMonticulo(this.sitioID);
        if (listLongitudMonticulo != null) {
            int size = listLongitudMonticulo.size();
            for (int i = 0; i < size; i++) {
                cmbLongitudMonticulos.addItem(listLongitudMonticulo.get(i));
            }
        }
    }

    private void llenarTablaDeformacionViento() {
        grdMonticulos.setModel(this.cdDeformacion.getTablaDeformacionViento(this.sitioID));
        grdMonticulos.getColumnModel().getColumn(2).setPreferredWidth(70);
        grdMonticulos.getColumnModel().getColumn(3).setPreferredWidth(70);
        grdMonticulos.getColumnModel().getColumn(4).setPreferredWidth(70);
        grdMonticulos.getColumnModel().getColumn(5).setPreferredWidth(70);
        grdMonticulos.getColumnModel().getColumn(6).setPreferredWidth(70);
        grdMonticulos.getColumnModel().getColumn(7).setPreferredWidth(70);
        Tablas tabla = new Tablas();
        int[] columna_0 = {0};
        int[] column_1 = {1};
        //Ocultar campo ID
        tabla.hideColumnTable(grdMonticulos, columna_0);
        tabla.hideColumnTable(grdMonticulos, column_1);
    }

    private void llenarTablaLongitudMonticulos() {
        grdLongitudMonticulos.setModel(this.cdDeformacion.getTablaLongitudMonticulo(this.sitioID));
        grdLongitudMonticulos.getColumnModel().getColumn(2).setPreferredWidth(100);
        grdLongitudMonticulos.getColumnModel().getColumn(3).setPreferredWidth(100);
        Tablas tabla = new Tablas();
        int[] columna_0 = {0};
        int[] column_1 = {1};
        //Ocultar campo ID
        tabla.hideColumnTable(grdLongitudMonticulos, columna_0);
        tabla.hideColumnTable(grdLongitudMonticulos, column_1);
    }

    private void fijarDatosDeformacionTerreno() {
        try {
            this.altura = Float.valueOf(txtAlturaMonticulos.getText());
        } catch (NumberFormatException e) {
            this.altura = null;
        }
        try {
            this.diametro = Float.valueOf(txtDiametroMonticulos.getText());
        } catch (NumberFormatException e) {
            this.diametro = null;
        }
        try {
            this.longitudDeformacion = Float.valueOf(txtLongitudMonticulos.getText());
        } catch (NumberFormatException e) {
            this.longitudDeformacion = null;
        }
        try {
            this.distancia = Float.valueOf(txtDistanciaMonticulos.getText());
        } catch (NumberFormatException e) {
            this.distancia = null;
        }
        try {
            this.azimut = Integer.valueOf(txtAzimutMonticulos.getText());
        } catch (NumberFormatException e) {
            this.azimut = null;
        }
    }

    private void fijarDatosLongitudMonticulos() {
        try {
            this.longitudMonticulos = Float.valueOf(txtLongitud.getText());
        } catch (NumberFormatException e) {
            this.longitudMonticulos = null;
        }
    }

    private void fijarDatosSuelo() {
        try {
            this.numeroMonticulos = Integer.valueOf(txtNoMonticulos.getText());
            if (this.numeroMonticulos == 0) {
                this.numeroMonticulos = null;
            }
        } catch (NumberFormatException e) {
            this.numeroMonticulos = null;
        }
        try {
            this.alturaPromedio = Float.valueOf(txtAlturaPromedioMonticulos.getText());
            if (this.alturaPromedio == (float) 0.0) {
                this.altura = null;
            }
        } catch (NumberFormatException e) {
            this.alturaPromedio = null;
        }
        try {
            this.anchoPromedio = Float.valueOf(txtAnchoPromedioMonticulos.getText());
            if (this.anchoPromedio == 0.0) {
                this.anchoPromedio = null;
            }
        } catch (NumberFormatException e) {
            this.anchoPromedio = null;
        }
        try {
            this.longitudPromedio = Float.valueOf(txtLongitudPromedioMonticulos.getText());
            if (this.longitudPromedio == 0.0) {
                this.longitudPromedio = null;
            }
        } catch (NumberFormatException e) {
            this.longitudPromedio = null;
        }
        try {
            this.volumenCalculado = Float.valueOf(txtVolumenMonticulos.getText());
            if (this.volumenCalculado == 0.0) {
                this.volumenCalculado = null;
            }
        } catch (NumberFormatException e) {
            this.volumenCalculado = null;
        }
    }

    private void fijarDatosTransponder() {
        if (rbtUnidosVarilla.isSelected()) {
            this.tipoColocacionID = 1;
        } else if (rbtUnidosPegamento.isSelected()) {
            this.tipoColocacionID = 2;
        } else if (rbtClavadoArbol.isSelected()) {
            this.tipoColocacionID = 3;
        } else if (rbtOtroLugar.isSelected()) {
            this.tipoColocacionID = 4;
        } else if (rbtNoSepudo.isSelected()) {
            this.tipoColocacionID = 5;
        }
        this.especificarTipo = txtEspecifiqueTransponder.getText();
        this.observaciones = txtObservacionesTransponder.getText();
    }

    private void crearTransponder() {
        this.ceTransponder.setSitioID(this.sitioID);
        this.ceTransponder.setTipoColocacionID(this.tipoColocacionID);
        this.ceTransponder.setEspecifique(this.especificarTipo);
        this.ceTransponder.setObservaciones(this.observaciones);
        this.cdTransponder.insertDatosTransponder(ceTransponder);
    }

    private void crearMonticulo() {
        Integer medicionMonticulos = (Integer) cmbMedicionMonticulos.getSelectedItem();
        this.ceMonticulo.setMedicionMonticulos(medicionMonticulos);
        this.ceMonticulo.setSitioID(this.sitioID);
        this.ceMonticulo.setAlturaMonticulos(this.altura);
        this.ceMonticulo.setDiametroMonticulos(this.diametro);
        this.ceMonticulo.setLongitudMonticulos(this.longitudDeformacion);
        this.ceMonticulo.setDistanciaMonticulos(this.distancia);
        this.ceMonticulo.setAzimutMonticulos(this.azimut);
        this.cdDeformacion.insertMonticulos(ceMonticulo);
    }

    private void crearLongitud() {
        Integer campoLongitud = (Integer) cmbLongitudMonticulos.getSelectedItem();
        this.ceLongitud.setSitioID(this.sitioID);
        this.ceLongitud.setCampoLongitud(campoLongitud);
        this.ceLongitud.setLongitud(this.longitudMonticulos);
        this.cdDeformacion.insertLongitudMonticulo(ceLongitud);
    }

    private void agregarDatosSuelo() {
        this.ceSuelo.setSitioID(this.sitioID);
        this.ceSuelo.setNumeroMonticulos(this.numeroMonticulos);
        this.ceSuelo.setAlturaPromedioMonticulos(this.alturaPromedio);
        this.ceSuelo.setAnchoPromedioMonticulos(this.anchoPromedio);
        this.ceSuelo.setLongitudPromedioMonticulos(this.longitudPromedio);
        this.ceSuelo.setVolumenMonticulos(this.volumenCalculado);
        this.cdSuelo.agregarDeformacionTerrenoSuelo(ceSuelo);
    }

    private void modificarMonticulos() {
        try {
            int fila = grdMonticulos.getSelectedRow();
            String registro = grdMonticulos.getValueAt(fila, 0).toString();
            this.ceMonticulo.setDeformacionVientoID(Integer.parseInt(registro));
            this.ceMonticulo.setAlturaMonticulos(this.altura);
            this.ceMonticulo.setDiametroMonticulos(this.diametro);
            this.ceMonticulo.setLongitudMonticulos(this.longitudDeformacion);
            this.ceMonticulo.setDistanciaMonticulos(this.distancia);
            this.ceMonticulo.setAzimutMonticulos(this.azimut);
            this.cdDeformacion.updateMonticulos(ceMonticulo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla de deformacion del terreno "
                    + e.getClass().getName() + " : " + e.getMessage(), "Erosion hidrica", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void modificarLongitudMonticulos() {
        try {
            int fila = grdLongitudMonticulos.getSelectedRow();
            String registro = grdLongitudMonticulos.getValueAt(fila, 0).toString();
            this.ceLongitud.setLongitudMonticulosID(Integer.parseInt(registro));
            this.ceLongitud.setLongitud(this.longitudMonticulos);
            this.cdDeformacion.updateLongitudMonticulo(ceLongitud);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla de longitud de moticulos "
                    + e.getClass().getName() + " : " + e.getMessage(), "Erosion hidrica", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void modificarTransponder() {
        this.ceTransponder.setSitioID(this.sitioID);
        this.ceTransponder.setTipoColocacionID(this.tipoColocacionID);
        this.ceTransponder.setEspecifique(this.especificarTipo);
        this.ceTransponder.setObservaciones(this.observaciones);
        this.cdTransponder.updateDatosTransponder(ceTransponder);
    }

    public void eliminarMonticulos() {
        try {
            int fila = grdMonticulos.getSelectedRow();
            String registro = grdMonticulos.getValueAt(fila, 0).toString();
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Esta seguro de borrar el registro de deformacion del terreno?",
                    "Erosion hidrica", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (respuesta == JOptionPane.YES_OPTION) {
                this.cdDeformacion.deleteMonticulo(Integer.parseInt(registro));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla de deformacion del terreno "
                    + "", "Erosion hidrica", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void eliminarLongitudMonticulos() {
        try {
            int fila = grdLongitudMonticulos.getSelectedRow();
            String registro = grdLongitudMonticulos.getValueAt(fila, 0).toString();
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Esta seguro de borrar el registro de longitud de monticulo?",
                    "Erosion hidrica", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (respuesta == JOptionPane.YES_OPTION) {
                this.cdDeformacion.deleteLongitudMonticulo(Integer.parseInt(registro));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla de longitud de monticulos "
                    + "", "Erosion hidrica", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void eliminarTransponder() {
        cdTransponder.deleteDatosTransponder(this.sitioID);
    }

    private boolean validarMonticulosObligatorio() {
        if (cmbMedicionMonticulos.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar un número de medición ",
                    "Deformacion por viento", JOptionPane.INFORMATION_MESSAGE);
            cmbMedicionMonticulos.requestFocus();
            return false;
        } else if (txtAlturaMonticulos.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar altura ",
                    "Deformacion por viento", JOptionPane.INFORMATION_MESSAGE);
            txtAlturaMonticulos.requestFocus();
            return false;
        } else if (txtDiametroMonticulos.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar diametro ",
                    "Deformacion por viento", JOptionPane.INFORMATION_MESSAGE);
            txtDiametroMonticulos.requestFocus();
            return false;
        } else if (txtLongitudMonticulos.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar longitud ",
                    "Deformacion por viento", JOptionPane.INFORMATION_MESSAGE);
            txtLongitudMonticulos.requestFocus();
            return false;
        } else if (txtDistanciaMonticulos.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar distancia ",
                    "Deformacion por viento", JOptionPane.INFORMATION_MESSAGE);
            txtDistanciaMonticulos.requestFocus();
            return false;
        } else if (txtAzimutMonticulos.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar azimut ",
                    "Deformacion por viento", JOptionPane.INFORMATION_MESSAGE);
            txtAzimutMonticulos.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean validarDatosMonticulos() {
        if (validacion.esMayorCero(this.altura)) {
            txtAlturaMonticulos.setText("");
            txtAlturaMonticulos.requestFocus();
            return false;
        } else if (validacion.esMayorCero(this.diametro)) {
            txtDiametroMonticulos.setText("");
            txtDiametroMonticulos.requestFocus();
            return false;
        } else if (validacion.esMayorCero(this.longitudDeformacion)) {
            txtLongitudMonticulos.setText("");
            txtLongitudMonticulos.requestFocus();
            return false;
        } else if (validacion.esDistancia(this.distancia, "Deformacion del terreno")) {
            txtDistanciaMonticulos.setText("");
            txtDistanciaMonticulos.requestFocus();
            return false;
        } else if (validacion.esAzimut(this.azimut, "Deformacion del terreno")) {
            txtAzimutMonticulos.setText("");
            txtAzimutMonticulos.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private void limpiarDatosMonticulos() {
        txtAlturaMonticulos.setValue(null);
        txtAlturaMonticulos.setText("");
        txtDiametroMonticulos.setValue(null);
        txtDiametroMonticulos.setText("");
        txtLongitudMonticulos.setValue(null);
        txtLongitudMonticulos.setText("");
        txtDistanciaMonticulos.setValue(null);
        txtDistanciaMonticulos.setText("");
        txtAlturaMonticulos.setValue(null);
        txtAzimutMonticulos.setText("");
        cmbMedicionMonticulos.requestFocus();
    }

    private void limpiarDatosLongitud() {
        txtLongitud.setValue(null);
        txtLongitud.setText("");
        cmbLongitudMonticulos.requestFocus();
    }

    private void limpiarCamposCalculados() {
        txtNoMonticulos.setText("");
        txtNoMonticulos.setValue(null);
        txtAlturaPromedioMonticulos.setText("");
        txtAlturaPromedioMonticulos.setValue(null);
        txtAnchoPromedioMonticulos.setText("");
        txtAnchoPromedioMonticulos.setValue(null);
        txtLongitudPromedioMonticulos.setText("");
        txtLongitudPromedioMonticulos.setValue(null);
        txtVolumenMonticulos.setText("");
        txtVolumenMonticulos.setValue(null);
    }

    private void limpiarColocacionTAG(){
        btgColocacionTAG.clearSelection();
        txtObservacionesTransponder.setText("");
        txtEspecifiqueTransponder.setText("");
        txtEspecifiqueTransponder.setEnabled(false);
    }
            
    private boolean validarLongitudMoticulosObligatorios() {
        if (cmbLongitudMonticulos.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar un campo de longitud ",
                    "Deformacion por viento", JOptionPane.INFORMATION_MESSAGE);
            cmbLongitudMonticulos.requestFocus();
            return false;
        } else if (txtLongitud.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar una longitud de moticulo ",
                    "Deformacion por viento", JOptionPane.INFORMATION_MESSAGE);
            txtLongitud.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean validarDatosLongitud() {
        if (validacion.esMayorCero(this.longitudMonticulos)) {
            txtLongitudMonticulos.setText("");
            txtLongitudMonticulos.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean validarTransponder() {
        if (this.tipoColocacionID != null) {
            if ((this.tipoColocacionID > 3 || this.tipoColocacionID == 5) && txtEspecifiqueTransponder.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Error! Debe especificar el tipo de colocación de transponder ",
                        "Transponder", JOptionPane.INFORMATION_MESSAGE);
                txtEspecifiqueTransponder.requestFocus();
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    private void calcularMonticulos() {
        txtNoMonticulos.setText(String.valueOf(this.cdDeformacion.getNumeroRegistros("SUELO_LongitudMonticulo", this.sitioID)));
        txtAlturaPromedioMonticulos.setText(String.valueOf(this.cdDeformacion.getPromedioCampo("SUELO_DeformacionViento", "Altura", this.sitioID)));
        txtAnchoPromedioMonticulos.setText(String.valueOf(this.cdDeformacion.getPromedioCampo("SUELO_DeformacionViento", "Diametro", this.sitioID)));
        txtLongitudPromedioMonticulos.setText(String.valueOf(this.cdDeformacion.getPromedioCampo("SUELO_LongitudMonticulo", "Longitud", this.sitioID)));
        Float volumen = ((Float.parseFloat(txtAlturaPromedioMonticulos.getText()) * Float.parseFloat(txtAnchoPromedioMonticulos.getText())) / 2 * Float.parseFloat(txtLongitudPromedioMonticulos.getText()));
        txtVolumenMonticulos.setText(String.valueOf(volumen));
    }

    private void manipularControlesMonticulos(boolean activar) {
        if (activar == true) {
            cmbMedicionMonticulos.setEnabled(true);
            txtAlturaMonticulos.setEnabled(true);
            txtDiametroMonticulos.setEnabled(true);
            txtLongitudMonticulos.setEnabled(true);
            txtDistanciaMonticulos.setEnabled(true);
            txtAzimutMonticulos.setEnabled(true);
            btnAgregarMonticulos.setEnabled(true);
            btnModificarMonticulos.setEnabled(true);
            btnEliminarMonticulos.setEnabled(true);
            cmbLongitudMonticulos.setEnabled(true);
            txtLongitud.setEnabled(true);
            btnAgregarLongitudMonticulos.setEnabled(true);
            btnModificarLongitudMonticulos.setEnabled(true);
            btnEliminarLongitudMonticulos.setEnabled(true);
        } else {
            cmbMedicionMonticulos.setEnabled(false);
            cmbMedicionMonticulos.setSelectedItem(null);
            txtAlturaMonticulos.setEnabled(false);
            txtDiametroMonticulos.setEnabled(false);
            txtLongitudMonticulos.setEnabled(false);
            txtDistanciaMonticulos.setEnabled(false);
            txtAzimutMonticulos.setEnabled(false);
            btnAgregarMonticulos.setEnabled(false);
            btnModificarMonticulos.setEnabled(false);
            btnEliminarMonticulos.setEnabled(false);
            cmbLongitudMonticulos.setEnabled(false);
            cmbLongitudMonticulos.setSelectedItem(null);
            txtLongitud.setEnabled(false);
            btnAgregarLongitudMonticulos.setEnabled(false);
            btnModificarLongitudMonticulos.setEnabled(false);
            btnEliminarLongitudMonticulos.setEnabled(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btgColocacionTAG = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        lblUPM = new javax.swing.JLabel();
        txtUPM = new javax.swing.JTextField();
        lblDeformacionTerreno = new javax.swing.JLabel();
        lblSitio = new javax.swing.JLabel();
        txtSitio = new javax.swing.JTextField();
        lblRegistroMonticulos = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lblMedicionMonticulos = new javax.swing.JLabel();
        cmbMedicionMonticulos = new javax.swing.JComboBox();
        lblAlturaMonticulos = new javax.swing.JLabel();
        lblDiametroMonticulos = new javax.swing.JLabel();
        lblDistanciaMonticulos = new javax.swing.JLabel();
        lblAzimtMonticulos = new javax.swing.JLabel();
        txtAlturaMonticulos = new javax.swing.JFormattedTextField();
        txtDiametroMonticulos = new javax.swing.JFormattedTextField();
        txtDistanciaMonticulos = new javax.swing.JFormattedTextField();
        txtAzimutMonticulos = new javax.swing.JFormattedTextField();
        txtLongitudMonticulos = new javax.swing.JFormattedTextField();
        lblLongitudMonticulo = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        grdMonticulos = new javax.swing.JTable();
        btnAgregarMonticulos = new javax.swing.JButton();
        btnModificarMonticulos = new javax.swing.JButton();
        btnEliminarMonticulos = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        lblLongitudMonticulos = new javax.swing.JLabel();
        cmbLongitudMonticulos = new javax.swing.JComboBox();
        lblLongitud = new javax.swing.JLabel();
        txtLongitud = new javax.swing.JFormattedTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        grdLongitudMonticulos = new javax.swing.JTable();
        btnAgregarLongitudMonticulos = new javax.swing.JButton();
        btnModificarLongitudMonticulos = new javax.swing.JButton();
        btnEliminarLongitudMonticulos = new javax.swing.JButton();
        lblNoMonticulos = new javax.swing.JLabel();
        txtNoMonticulos = new javax.swing.JFormattedTextField();
        lblAlturaPromedioMonticulos = new javax.swing.JLabel();
        txtAlturaPromedioMonticulos = new javax.swing.JFormattedTextField();
        lblAlturaPromedioMonticulos1 = new javax.swing.JLabel();
        txtAnchoPromedioMonticulos = new javax.swing.JFormattedTextField();
        lblAlturaPromedioMonticulos2 = new javax.swing.JLabel();
        txtLongitudPromedioMonticulos = new javax.swing.JFormattedTextField();
        lblVolumenMonticulo = new javax.swing.JLabel();
        txtVolumenMonticulos = new javax.swing.JFormattedTextField();
        chkMonticulosDunas = new javax.swing.JCheckBox();
        lblColocacionTAG = new javax.swing.JLabel();
        pnlColocacionTag = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        rbtUnidosVarilla = new javax.swing.JRadioButton();
        rbtUnidosPegamento = new javax.swing.JRadioButton();
        rbtClavadoArbol = new javax.swing.JRadioButton();
        rbtOtroLugar = new javax.swing.JRadioButton();
        rbtNoSepudo = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtEspecifiqueTransponder = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtObservacionesTransponder = new javax.swing.JTextArea();
        btnContinuar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setTitle("Deformación del terreno por acción del viento, colocación del TAG");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/gob/conafor/utils/logo_conafor.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(940, 650));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        lblUPM.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblUPM.setText("UPMID:");

        txtUPM.setEditable(false);
        txtUPM.setEnabled(false);

        lblDeformacionTerreno.setBackground(new java.awt.Color(153, 153, 153));
        lblDeformacionTerreno.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblDeformacionTerreno.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDeformacionTerreno.setText("Deformación del terreno por acción del viento");
        lblDeformacionTerreno.setOpaque(true);

        lblSitio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblSitio.setText("Sitio:");

        txtSitio.setEditable(false);
        txtSitio.setEnabled(false);

        lblRegistroMonticulos.setBackground(new java.awt.Color(153, 153, 153));
        lblRegistroMonticulos.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblRegistroMonticulos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblRegistroMonticulos.setText("Registro de montículos o dunas");
        lblRegistroMonticulos.setOpaque(true);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblMedicionMonticulos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMedicionMonticulos.setText("Medición");

        lblAlturaMonticulos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAlturaMonticulos.setText("Altura (cm)");

        lblDiametroMonticulos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDiametroMonticulos.setText("Diámetro (cm)");

        lblDistanciaMonticulos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDistanciaMonticulos.setText("Distancia (cm)");

        lblAzimtMonticulos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAzimtMonticulos.setText("Azimut");

        txtAlturaMonticulos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat(""))));
        txtAlturaMonticulos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtAlturaMonticulosFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtAlturaMonticulosFocusLost(evt);
            }
        });
        txtAlturaMonticulos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAlturaMonticulosKeyTyped(evt);
            }
        });

        txtDiametroMonticulos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat(""))));
        txtDiametroMonticulos.setNextFocusableComponent(txtLongitudMonticulos);
        txtDiametroMonticulos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDiametroMonticulosFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDiametroMonticulosFocusLost(evt);
            }
        });
        txtDiametroMonticulos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDiametroMonticulosKeyTyped(evt);
            }
        });

        txtDistanciaMonticulos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat(""))));
        txtDistanciaMonticulos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDistanciaMonticulosFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDistanciaMonticulosFocusLost(evt);
            }
        });
        txtDistanciaMonticulos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDistanciaMonticulosKeyTyped(evt);
            }
        });

        txtAzimutMonticulos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat(""))));
        txtAzimutMonticulos.setNextFocusableComponent(btnAgregarMonticulos);
        txtAzimutMonticulos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtAzimutMonticulosFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtAzimutMonticulosFocusLost(evt);
            }
        });
        txtAzimutMonticulos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAzimutMonticulosKeyTyped(evt);
            }
        });

        txtLongitudMonticulos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat(""))));
        txtLongitudMonticulos.setNextFocusableComponent(txtDistanciaMonticulos);
        txtLongitudMonticulos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtLongitudMonticulosFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtLongitudMonticulosFocusLost(evt);
            }
        });
        txtLongitudMonticulos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLongitudMonticulosKeyTyped(evt);
            }
        });

        lblLongitudMonticulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLongitudMonticulo.setText("Longitud (cm)");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblMedicionMonticulos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbMedicionMonticulos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblAlturaMonticulos, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                    .addComponent(txtAlturaMonticulos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDiametroMonticulos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDiametroMonticulos, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtLongitudMonticulos, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLongitudMonticulo, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDistanciaMonticulos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDistanciaMonticulos, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtAzimutMonticulos)
                    .addComponent(lblAzimtMonticulos, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDiametroMonticulos)
                    .addComponent(lblAzimtMonticulos)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDistanciaMonticulos)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(txtDistanciaMonticulos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblMedicionMonticulos)
                                .addComponent(lblAlturaMonticulos))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmbMedicionMonticulos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtAlturaMonticulos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtDiametroMonticulos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtAzimutMonticulos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(lblLongitudMonticulo)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtLongitudMonticulos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        grdMonticulos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        grdMonticulos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grdMonticulosMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(grdMonticulos);

        btnAgregarMonticulos.setText("Agregar");
        btnAgregarMonticulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarMonticulosActionPerformed(evt);
            }
        });

        btnModificarMonticulos.setText("Modificar");
        btnModificarMonticulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarMonticulosActionPerformed(evt);
            }
        });

        btnEliminarMonticulos.setText("Eliminar");
        btnEliminarMonticulos.setNextFocusableComponent(cmbMedicionMonticulos);
        btnEliminarMonticulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarMonticulosActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblLongitudMonticulos.setText("Campo longitud");

        lblLongitud.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLongitud.setText("Longitud (cm)");

        txtLongitud.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat(""))));
        txtLongitud.setNextFocusableComponent(btnAgregarLongitudMonticulos);
        txtLongitud.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtLongitudFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtLongitudFocusLost(evt);
            }
        });
        txtLongitud.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLongitudKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblLongitudMonticulos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbLongitudMonticulos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLongitud, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLongitud, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lblLongitud)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtLongitud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lblLongitudMonticulos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbLongitudMonticulos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        grdLongitudMonticulos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        grdLongitudMonticulos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grdLongitudMonticulosMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(grdLongitudMonticulos);

        btnAgregarLongitudMonticulos.setText("Agregar");
        btnAgregarLongitudMonticulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarLongitudMonticulosActionPerformed(evt);
            }
        });

        btnModificarLongitudMonticulos.setText("Modificar");
        btnModificarLongitudMonticulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarLongitudMonticulosActionPerformed(evt);
            }
        });

        btnEliminarLongitudMonticulos.setText("Eliminar");
        btnEliminarLongitudMonticulos.setNextFocusableComponent(cmbLongitudMonticulos);
        btnEliminarLongitudMonticulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarLongitudMonticulosActionPerformed(evt);
            }
        });

        lblNoMonticulos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNoMonticulos.setText("No de montículos o dunas");

        txtNoMonticulos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtNoMonticulos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNoMonticulos.setEnabled(false);
        txtNoMonticulos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtNoMonticulos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNoMonticulosFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNoMonticulosFocusLost(evt);
            }
        });

        lblAlturaPromedioMonticulos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAlturaPromedioMonticulos.setText("Altura promedio");

        txtAlturaPromedioMonticulos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat(""))));
        txtAlturaPromedioMonticulos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAlturaPromedioMonticulos.setEnabled(false);
        txtAlturaPromedioMonticulos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtAlturaPromedioMonticulos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtAlturaPromedioMonticulosFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtAlturaPromedioMonticulosFocusLost(evt);
            }
        });

        lblAlturaPromedioMonticulos1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAlturaPromedioMonticulos1.setText("Ancho promedio");

        txtAnchoPromedioMonticulos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat(""))));
        txtAnchoPromedioMonticulos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAnchoPromedioMonticulos.setEnabled(false);
        txtAnchoPromedioMonticulos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtAnchoPromedioMonticulos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtAnchoPromedioMonticulosFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtAnchoPromedioMonticulosFocusLost(evt);
            }
        });

        lblAlturaPromedioMonticulos2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAlturaPromedioMonticulos2.setText("Longitud");

        txtLongitudPromedioMonticulos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat(""))));
        txtLongitudPromedioMonticulos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtLongitudPromedioMonticulos.setEnabled(false);
        txtLongitudPromedioMonticulos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtLongitudPromedioMonticulos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtLongitudPromedioMonticulosFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtLongitudPromedioMonticulosFocusLost(evt);
            }
        });

        lblVolumenMonticulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVolumenMonticulo.setText("Volumen");

        txtVolumenMonticulos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat(""))));
        txtVolumenMonticulos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtVolumenMonticulos.setEnabled(false);
        txtVolumenMonticulos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtVolumenMonticulos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtVolumenMonticulosFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtVolumenMonticulosFocusLost(evt);
            }
        });

        chkMonticulosDunas.setBackground(new java.awt.Color(204, 204, 204));
        chkMonticulosDunas.setSelected(true);
        chkMonticulosDunas.setText("Montículos o dunas");
        chkMonticulosDunas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkMonticulosDunasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(btnAgregarMonticulos)
                        .addGap(18, 18, 18)
                        .addComponent(btnModificarMonticulos)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminarMonticulos))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkMonticulosDunas)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btnAgregarLongitudMonticulos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificarLongitudMonticulos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminarLongitudMonticulos))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblVolumenMonticulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtLongitudPromedioMonticulos, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblAlturaPromedioMonticulos2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtAnchoPromedioMonticulos, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblAlturaPromedioMonticulos1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtAlturaPromedioMonticulos, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblAlturaPromedioMonticulos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNoMonticulos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                    .addComponent(txtNoMonticulos, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtVolumenMonticulos))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblNoMonticulos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNoMonticulos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblAlturaPromedioMonticulos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAlturaPromedioMonticulos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblAlturaPromedioMonticulos1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAnchoPromedioMonticulos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblAlturaPromedioMonticulos2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtLongitudPromedioMonticulos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblVolumenMonticulo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtVolumenMonticulos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(chkMonticulosDunas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnAgregarLongitudMonticulos)
                                        .addComponent(btnModificarLongitudMonticulos)
                                        .addComponent(btnEliminarLongitudMonticulos))
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnAgregarMonticulos)
                                        .addComponent(btnModificarMonticulos)
                                        .addComponent(btnEliminarMonticulos))))
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap())
        );

        lblColocacionTAG.setBackground(new java.awt.Color(153, 153, 153));
        lblColocacionTAG.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblColocacionTAG.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblColocacionTAG.setText("Colocación del TAG o transponder");
        lblColocacionTAG.setOpaque(true);

        pnlColocacionTag.setBackground(new java.awt.Color(204, 204, 204));
        pnlColocacionTag.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.setNextFocusableComponent(txtEspecifiqueTransponder);

        rbtUnidosVarilla.setBackground(new java.awt.Color(204, 204, 204));
        btgColocacionTAG.add(rbtUnidosVarilla);
        rbtUnidosVarilla.setText("Unidos a la varilla del centro del sitio 1  ");
        rbtUnidosVarilla.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rbtUnidosVarilla.setNextFocusableComponent(txtEspecifiqueTransponder);
        rbtUnidosVarilla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtUnidosVarillaActionPerformed(evt);
            }
        });

        rbtUnidosPegamento.setBackground(new java.awt.Color(204, 204, 204));
        btgColocacionTAG.add(rbtUnidosPegamento);
        rbtUnidosPegamento.setText("Unidos con pegamento al centro del sitio 1 ");
        rbtUnidosPegamento.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rbtUnidosPegamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtUnidosPegamentoActionPerformed(evt);
            }
        });

        rbtClavadoArbol.setBackground(new java.awt.Color(204, 204, 204));
        btgColocacionTAG.add(rbtClavadoArbol);
        rbtClavadoArbol.setText("Clavado al arbol mas cercano al centro del sitio 1");
        rbtClavadoArbol.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rbtClavadoArbol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtClavadoArbolActionPerformed(evt);
            }
        });

        rbtOtroLugar.setBackground(new java.awt.Color(204, 204, 204));
        btgColocacionTAG.add(rbtOtroLugar);
        rbtOtroLugar.setText("Otro lugar");
        rbtOtroLugar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rbtOtroLugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtOtroLugarActionPerformed(evt);
            }
        });

        rbtNoSepudo.setBackground(new java.awt.Color(204, 204, 204));
        btgColocacionTAG.add(rbtNoSepudo);
        rbtNoSepudo.setText("No se pudo colocar el TAG");
        rbtNoSepudo.setToolTipText("");
        rbtNoSepudo.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rbtNoSepudo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtNoSepudoActionPerformed(evt);
            }
        });

        jLabel3.setText("a)");

        jLabel4.setText("b)");

        jLabel5.setText("c)");

        jLabel6.setText("d)");

        jLabel7.setText("e)");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rbtNoSepudo)
                    .addComponent(rbtOtroLugar)
                    .addComponent(rbtUnidosPegamento)
                    .addComponent(rbtClavadoArbol)
                    .addComponent(rbtUnidosVarilla))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtUnidosVarilla)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtUnidosPegamento)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtClavadoArbol)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtOtroLugar)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtNoSepudo)
                    .addComponent(jLabel7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("En caso de registrar d) o e), especifique");

        txtEspecifiqueTransponder.setColumns(20);
        txtEspecifiqueTransponder.setRows(5);
        txtEspecifiqueTransponder.setEnabled(false);
        txtEspecifiqueTransponder.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtEspecifiqueTransponderFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtEspecifiqueTransponderFocusLost(evt);
            }
        });
        txtEspecifiqueTransponder.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEspecifiqueTransponderKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(txtEspecifiqueTransponder);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Comentarios u observaciones en la colocación del TAG");

        txtObservacionesTransponder.setColumns(20);
        txtObservacionesTransponder.setRows(5);
        txtObservacionesTransponder.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtObservacionesTransponderFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtObservacionesTransponderFocusLost(evt);
            }
        });
        txtObservacionesTransponder.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtObservacionesTransponderKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(txtObservacionesTransponder);

        javax.swing.GroupLayout pnlColocacionTagLayout = new javax.swing.GroupLayout(pnlColocacionTag);
        pnlColocacionTag.setLayout(pnlColocacionTagLayout);
        pnlColocacionTagLayout.setHorizontalGroup(
            pnlColocacionTagLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlColocacionTagLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlColocacionTagLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlColocacionTagLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        pnlColocacionTagLayout.setVerticalGroup(
            pnlColocacionTagLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlColocacionTagLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlColocacionTagLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlColocacionTagLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlColocacionTagLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlColocacionTagLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                            .addComponent(jScrollPane2)))
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        btnContinuar.setText("Continuar");
        btnContinuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinuarActionPerformed(evt);
            }
        });

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnContinuar)
                .addGap(29, 29, 29)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(338, 338, 338))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDeformacionTerreno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblUPM)
                        .addGap(10, 10, 10)
                        .addComponent(txtUPM, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(596, 596, 596)
                        .addComponent(lblSitio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSitio, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblColocacionTAG, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblRegistroMonticulos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnlColocacionTag, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(lblUPM))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtUPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtSitio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSitio)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDeformacionTerreno)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblRegistroMonticulos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblColocacionTAG)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlColocacionTag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnContinuar)
                    .addComponent(btnSalir))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtAlturaMonticulosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAlturaMonticulosFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtAlturaMonticulos.selectAll();
            }
        });
    }//GEN-LAST:event_txtAlturaMonticulosFocusGained

    private void txtDiametroMonticulosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDiametroMonticulosFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtDiametroMonticulos.selectAll();
            }
        });
    }//GEN-LAST:event_txtDiametroMonticulosFocusGained

    private void txtDistanciaMonticulosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDistanciaMonticulosFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtDistanciaMonticulos.selectAll();
            }
        });
    }//GEN-LAST:event_txtDistanciaMonticulosFocusGained

    private void txtAzimutMonticulosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAzimutMonticulosFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtAlturaMonticulos.selectAll();
            }
        });
    }//GEN-LAST:event_txtAzimutMonticulosFocusGained

    private void txtLongitudMonticulosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLongitudMonticulosFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtLongitudMonticulos.selectAll();
            }
        });
    }//GEN-LAST:event_txtLongitudMonticulosFocusGained

    private void txtNoMonticulosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNoMonticulosFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtNoMonticulos.selectAll();
            }
        });
    }//GEN-LAST:event_txtNoMonticulosFocusGained

    private void txtEspecifiqueTransponderFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEspecifiqueTransponderFocusGained
        txtEspecifiqueTransponder.selectAll();
    }//GEN-LAST:event_txtEspecifiqueTransponderFocusGained

    private void txtObservacionesTransponderFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtObservacionesTransponderFocusGained
        txtObservacionesTransponder.selectAll();
    }//GEN-LAST:event_txtObservacionesTransponderFocusGained

    private void txtAlturaMonticulosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAlturaMonticulosFocusLost
        if (txtAlturaMonticulos.getText().isEmpty()) {
            txtAlturaMonticulos.setValue(null);
        }
    }//GEN-LAST:event_txtAlturaMonticulosFocusLost

    private void txtDiametroMonticulosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDiametroMonticulosFocusLost
        if (txtDiametroMonticulos.getText().isEmpty()) {
            txtDiametroMonticulos.setValue(null);
        }
    }//GEN-LAST:event_txtDiametroMonticulosFocusLost

    private void txtDistanciaMonticulosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDistanciaMonticulosFocusLost
        if (txtDistanciaMonticulos.getText().isEmpty()) {
            txtDistanciaMonticulos.setValue(null);
        }
    }//GEN-LAST:event_txtDistanciaMonticulosFocusLost

    private void txtAzimutMonticulosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAzimutMonticulosFocusLost
        if (txtAlturaMonticulos.getText().isEmpty()) {
            txtAlturaMonticulos.setValue(null);
        }
    }//GEN-LAST:event_txtAzimutMonticulosFocusLost

    private void txtLongitudMonticulosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLongitudMonticulosFocusLost
        if (txtLongitudMonticulos.getText().isEmpty()) {
            txtLongitudMonticulos.setValue(null);
        }
    }//GEN-LAST:event_txtLongitudMonticulosFocusLost

    private void txtNoMonticulosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNoMonticulosFocusLost
        if (txtNoMonticulos.getText().isEmpty()) {
            txtNoMonticulos.setValue(null);
        }
    }//GEN-LAST:event_txtNoMonticulosFocusLost

    private void txtEspecifiqueTransponderFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEspecifiqueTransponderFocusLost

    }//GEN-LAST:event_txtEspecifiqueTransponderFocusLost

    private void txtObservacionesTransponderFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtObservacionesTransponderFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtObservacionesTransponderFocusLost

    private void txtAlturaPromedioMonticulosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAlturaPromedioMonticulosFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAlturaPromedioMonticulosFocusGained

    private void txtAlturaPromedioMonticulosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAlturaPromedioMonticulosFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAlturaPromedioMonticulosFocusLost

    private void txtAnchoPromedioMonticulosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAnchoPromedioMonticulosFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAnchoPromedioMonticulosFocusGained

    private void txtAnchoPromedioMonticulosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAnchoPromedioMonticulosFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAnchoPromedioMonticulosFocusLost

    private void txtLongitudPromedioMonticulosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLongitudPromedioMonticulosFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLongitudPromedioMonticulosFocusGained

    private void txtLongitudPromedioMonticulosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLongitudPromedioMonticulosFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLongitudPromedioMonticulosFocusLost

    private void txtVolumenMonticulosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtVolumenMonticulosFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVolumenMonticulosFocusGained

    private void txtVolumenMonticulosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtVolumenMonticulosFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVolumenMonticulosFocusLost

    private void grdMonticulosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grdMonticulosMouseClicked
        if (evt.getButton() == 1) {
            int fila = grdMonticulos.getSelectedRow();
            String id = grdMonticulos.getValueAt(fila, 0).toString();
            this.ceMonticulo = this.cdDeformacion.getMonticulo(Integer.parseInt(id));
            Integer medicion = this.ceMonticulo.getMedicionMonticulos();
            cmbMedicionMonticulos.setSelectedItem(medicion);
            txtAlturaMonticulos.setText(String.valueOf(this.ceMonticulo.getAlturaMonticulos()));
            txtDiametroMonticulos.setText(String.valueOf(this.ceMonticulo.getDiametroMonticulos()));
            txtLongitudMonticulos.setText(String.valueOf(this.ceMonticulo.getLongitudMonticulos()));
            txtDistanciaMonticulos.setText(String.valueOf(this.ceMonticulo.getDistanciaMonticulos()));
            txtAzimutMonticulos.setText(String.valueOf(this.ceMonticulo.getAzimutMonticulos()));
        }
    }//GEN-LAST:event_grdMonticulosMouseClicked

    private void grdLongitudMonticulosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grdLongitudMonticulosMouseClicked
        if (evt.getButton() == 1) {
            int fila = grdLongitudMonticulos.getSelectedRow();
            String id = grdLongitudMonticulos.getValueAt(fila, 0).toString();
            this.ceLongitud = this.cdDeformacion.getLongitudMonticulo(Integer.parseInt(id));
            Integer medicion = this.ceLongitud.getCampoLongitud();
            cmbLongitudMonticulos.setSelectedItem(medicion);
            txtLongitud.setText(String.valueOf(this.ceLongitud.getLongitud()));
        }
    }//GEN-LAST:event_grdLongitudMonticulosMouseClicked

    private void btnAgregarMonticulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarMonticulosActionPerformed
        fijarDatosDeformacionTerreno();
        if (validarMonticulosObligatorio() && validarDatosMonticulos()) {
            crearMonticulo();
            this.cdDeformacion.reenumerarMonticulos(this.sitioID);
            llenarTablaDeformacionViento();
            this.combo.reiniciarComboModel(cmbMedicionMonticulos);
            fillCmbMedionesMonticulos();
            limpiarDatosMonticulos();
            calcularMonticulos();
        }
    }//GEN-LAST:event_btnAgregarMonticulosActionPerformed

    private void btnModificarMonticulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarMonticulosActionPerformed
        fijarDatosDeformacionTerreno();
        if (validarDatosMonticulos()) {
            modificarMonticulos();
            llenarTablaDeformacionViento();
            limpiarDatosMonticulos();
            calcularMonticulos();
        }
    }//GEN-LAST:event_btnModificarMonticulosActionPerformed

    private void btnEliminarMonticulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarMonticulosActionPerformed
        eliminarMonticulos();
        this.cdDeformacion.reenumerarMonticulos(this.sitioID);
        llenarTablaDeformacionViento();
        this.combo.reiniciarComboModel(cmbMedicionMonticulos);
        fillCmbMedionesMonticulos();
        limpiarDatosMonticulos();
        calcularMonticulos();
    }//GEN-LAST:event_btnEliminarMonticulosActionPerformed

    private void btnAgregarLongitudMonticulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarLongitudMonticulosActionPerformed
        fijarDatosLongitudMonticulos();
        if (validarLongitudMoticulosObligatorios() && validarDatosLongitud()) {
            crearLongitud();
            this.cdDeformacion.reenumerarLongitudMonticulo(sitioID);
            llenarTablaLongitudMonticulos();
            this.combo.reiniciarComboModel(cmbLongitudMonticulos);
            fillCmbLongitudMonticulos();
            limpiarDatosLongitud();
            calcularMonticulos();
        }
    }//GEN-LAST:event_btnAgregarLongitudMonticulosActionPerformed

    private void btnModificarLongitudMonticulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarLongitudMonticulosActionPerformed
        fijarDatosLongitudMonticulos();
        if (validarDatosLongitud()) {
            modificarLongitudMonticulos();
            llenarTablaLongitudMonticulos();
            limpiarDatosLongitud();
            calcularMonticulos();
        }
    }//GEN-LAST:event_btnModificarLongitudMonticulosActionPerformed

    private void btnEliminarLongitudMonticulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarLongitudMonticulosActionPerformed
        eliminarLongitudMonticulos();
        this.cdDeformacion.reenumerarLongitudMonticulo(this.sitioID);
        llenarTablaLongitudMonticulos();
        this.combo.reiniciarComboModel(cmbLongitudMonticulos);
        fillCmbLongitudMonticulos();
        limpiarDatosLongitud();
        calcularMonticulos();

    }//GEN-LAST:event_btnEliminarLongitudMonticulosActionPerformed

    private void txtLongitudFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLongitudFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtLongitud.selectAll();
            }
        });
    }//GEN-LAST:event_txtLongitudFocusGained

    private void txtLongitudFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLongitudFocusLost
        if (txtLongitud.getText().isEmpty()) {
            txtLongitud.setValue(null);
        }
    }//GEN-LAST:event_txtLongitudFocusLost

    private void btnContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuarActionPerformed
        fijarDatosSuelo();
        fijarDatosTransponder();
        agregarDatosSuelo();
        if (chkMonticulosDunas.isSelected() && this.cdDeformacion.validarTablaErosionViento(this.sitioID)) {
            JOptionPane.showMessageDialog(null, "Si selecciona motículos o dunas, se deben capturar"
                    + "", "Suelo", JOptionPane.INFORMATION_MESSAGE);
            cmbMedicionMonticulos.requestFocus();
        } else if (this.actualizar == 0) {
                if (validarTransponder()) {
                    crearTransponder();
                }
            this.hide();
            UPMForms.observaciones.setDatosiniciales(this.ceSitio);
            UPMForms.observaciones.setVisible(true);
            this.cdSecuencia.updateSecuencia(this.ceSitio, FORMATO_ID, 1);
        } else {
                if (validarTransponder()) {
                    modificarTransponder();
                }
            this.hide();
            UPMForms.observaciones.revisarObservaciones(this.ceSitio);
            UPMForms.observaciones.setVisible(true);
        }
    }//GEN-LAST:event_btnContinuarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.hide();
        funciones.manipularBotonesMenuPrincipal(true);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void rbtOtroLugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtOtroLugarActionPerformed
        if (rbtOtroLugar.isSelected()) {
            txtEspecifiqueTransponder.setEnabled(true);
        }
    }//GEN-LAST:event_rbtOtroLugarActionPerformed

    private void rbtNoSepudoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtNoSepudoActionPerformed
        if (rbtNoSepudo.isSelected()) {
            txtEspecifiqueTransponder.setEnabled(true);
        }
    }//GEN-LAST:event_rbtNoSepudoActionPerformed

    private void rbtClavadoArbolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtClavadoArbolActionPerformed
        if (rbtClavadoArbol.isSelected()) {
            txtEspecifiqueTransponder.setEnabled(false);
        }
    }//GEN-LAST:event_rbtClavadoArbolActionPerformed

    private void rbtUnidosPegamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtUnidosPegamentoActionPerformed
        if (rbtUnidosPegamento.isSelected()) {
            txtEspecifiqueTransponder.setEnabled(false);
        }
    }//GEN-LAST:event_rbtUnidosPegamentoActionPerformed

    private void rbtUnidosVarillaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtUnidosVarillaActionPerformed
        if (rbtUnidosVarilla.isSelected()) {
            txtEspecifiqueTransponder.setEnabled(false);
        }
    }//GEN-LAST:event_rbtUnidosVarillaActionPerformed

    private void txtAlturaMonticulosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAlturaMonticulosKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtAlturaMonticulosKeyTyped

    private void txtDiametroMonticulosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiametroMonticulosKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtDiametroMonticulosKeyTyped

    private void txtLongitudMonticulosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLongitudMonticulosKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtLongitudMonticulosKeyTyped

    private void txtDistanciaMonticulosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDistanciaMonticulosKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtDistanciaMonticulosKeyTyped

    private void txtAzimutMonticulosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAzimutMonticulosKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtAzimutMonticulosKeyTyped

    private void txtLongitudKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLongitudKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtLongitudKeyTyped

    private void chkMonticulosDunasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkMonticulosDunasActionPerformed
        if (chkMonticulosDunas.isSelected()) {
            manipularControlesMonticulos(true);
        } else {
            Object[] opciones = {"Si", "No"};
            int respuesta = JOptionPane.showOptionDialog(null, "Si capturo, se borrarán todos los datos de erosión por viento y sus mediciones ¿Esta seguro?",
                    "Suelo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[1]);
            if (respuesta == JOptionPane.YES_OPTION) {
                this.cdDeformacion.deleteMonticuloSitio(this.sitioID);
                this.funciones.reiniciarTabla(this.grdMonticulos);
                llenarTablaDeformacionViento();
                this.cdDeformacion.deleteLongitudMonticuloSitio(this.sitioID);
                llenarTablaLongitudMonticulos();
                this.funciones.reiniciarComboModel(cmbMedicionMonticulos);
                fillCmbMedionesMonticulos();
                this.funciones.reiniciarComboModel(cmbLongitudMonticulos);
                fillCmbLongitudMonticulos();
                limpiarDatosMonticulos();
                limpiarDatosLongitud();
                limpiarCamposCalculados();
                manipularControlesMonticulos(false);
            } else {
                chkMonticulosDunas.setSelected(true);
                chkMonticulosDunas.requestFocus();
            }
        }
    }//GEN-LAST:event_chkMonticulosDunasActionPerformed

    private void txtEspecifiqueTransponderKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEspecifiqueTransponderKeyPressed
         if(evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB){
            evt.consume();
         }
    }//GEN-LAST:event_txtEspecifiqueTransponderKeyPressed

    private void txtObservacionesTransponderKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtObservacionesTransponderKeyPressed
       if(evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB){
            evt.consume();
         }
    }//GEN-LAST:event_txtObservacionesTransponderKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgColocacionTAG;
    private javax.swing.JButton btnAgregarLongitudMonticulos;
    private javax.swing.JButton btnAgregarMonticulos;
    private javax.swing.JButton btnContinuar;
    private javax.swing.JButton btnEliminarLongitudMonticulos;
    private javax.swing.JButton btnEliminarMonticulos;
    private javax.swing.JButton btnModificarLongitudMonticulos;
    private javax.swing.JButton btnModificarMonticulos;
    private javax.swing.JButton btnSalir;
    private javax.swing.JCheckBox chkMonticulosDunas;
    private javax.swing.JComboBox cmbLongitudMonticulos;
    private javax.swing.JComboBox cmbMedicionMonticulos;
    private javax.swing.JTable grdLongitudMonticulos;
    private javax.swing.JTable grdMonticulos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblAlturaMonticulos;
    private javax.swing.JLabel lblAlturaPromedioMonticulos;
    private javax.swing.JLabel lblAlturaPromedioMonticulos1;
    private javax.swing.JLabel lblAlturaPromedioMonticulos2;
    private javax.swing.JLabel lblAzimtMonticulos;
    private javax.swing.JLabel lblColocacionTAG;
    private javax.swing.JLabel lblDeformacionTerreno;
    private javax.swing.JLabel lblDiametroMonticulos;
    private javax.swing.JLabel lblDistanciaMonticulos;
    private javax.swing.JLabel lblLongitud;
    private javax.swing.JLabel lblLongitudMonticulo;
    private javax.swing.JLabel lblLongitudMonticulos;
    private javax.swing.JLabel lblMedicionMonticulos;
    private javax.swing.JLabel lblNoMonticulos;
    private javax.swing.JLabel lblRegistroMonticulos;
    private javax.swing.JLabel lblSitio;
    private javax.swing.JLabel lblUPM;
    private javax.swing.JLabel lblVolumenMonticulo;
    private javax.swing.JPanel pnlColocacionTag;
    private javax.swing.JRadioButton rbtClavadoArbol;
    private javax.swing.JRadioButton rbtNoSepudo;
    private javax.swing.JRadioButton rbtOtroLugar;
    private javax.swing.JRadioButton rbtUnidosPegamento;
    private javax.swing.JRadioButton rbtUnidosVarilla;
    private javax.swing.JFormattedTextField txtAlturaMonticulos;
    private javax.swing.JFormattedTextField txtAlturaPromedioMonticulos;
    private javax.swing.JFormattedTextField txtAnchoPromedioMonticulos;
    private javax.swing.JFormattedTextField txtAzimutMonticulos;
    private javax.swing.JFormattedTextField txtDiametroMonticulos;
    private javax.swing.JFormattedTextField txtDistanciaMonticulos;
    private javax.swing.JTextArea txtEspecifiqueTransponder;
    private javax.swing.JFormattedTextField txtLongitud;
    private javax.swing.JFormattedTextField txtLongitudMonticulos;
    private javax.swing.JFormattedTextField txtLongitudPromedioMonticulos;
    private javax.swing.JFormattedTextField txtNoMonticulos;
    private javax.swing.JTextArea txtObservacionesTransponder;
    private javax.swing.JTextField txtSitio;
    private javax.swing.JTextField txtUPM;
    private javax.swing.JFormattedTextField txtVolumenMonticulos;
    // End of variables declaration//GEN-END:variables
}
