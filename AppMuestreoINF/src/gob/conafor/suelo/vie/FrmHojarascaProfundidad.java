package gob.conafor.suelo.vie;

import gob.conafor.sitio.mod.CDSitio;
import gob.conafor.sitio.mod.CESitio;
import gob.conafor.suelo.mod.CDDensidadAparente;
import gob.conafor.suelo.mod.CDHojarasca;
import gob.conafor.suelo.mod.CDProfundidadSuelo;
import gob.conafor.suelo.mod.CEDensidadAparente;
import gob.conafor.suelo.mod.CEHojarasca;
import gob.conafor.suelo.mod.CEProfundidadSuelo;
import gob.conafor.suelo.mod.CatETipoHojarasca;
import gob.conafor.sys.mod.CDSecuencia;
import gob.conafor.sys.mod.CDSeguimientoUPM;
import gob.conafor.sys.mod.CESeccionesCapturadas;
import gob.conafor.upm.vie.UPMForms;
import gob.conafor.utils.Datos;
import gob.conafor.utils.FuncionesComunes;
import gob.conafor.utils.Tablas;
import gob.conafor.utils.Version;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import org.xhtmlrenderer.layout.Breaker;

public class FrmHojarascaProfundidad extends javax.swing.JInternalFrame {

    private boolean revision;
    private int upmID;
    private int sitioID;
    private int sitio;
    private int hojarascaProfundidad;
    private static final int FORMATO_ID = 15;
    private int muestraPerfil;
    private CESitio ceSitio = new CESitio();
    private CDHojarasca cdHojarasca = new CDHojarasca();
    private CDProfundidadSuelo cdProfundidad = new CDProfundidadSuelo();
    private CDDensidadAparente cdDensidad = new CDDensidadAparente();
    private CEHojarasca ceHojarasca = new CEHojarasca();
    private CEProfundidadSuelo ceProfundidad = new CEProfundidadSuelo();
    private CEDensidadAparente ceDensidad = new CEDensidadAparente();
    private Integer puntoHojarasca;
    private Integer tipoHojarasca;
    private Float espesorHO;
    private Float espesorF;
    private Float pesoTotalHO;
    private Float pesoTotalF;
    private Float pesoMuestraHO;
    private Float pesoMuestraF;
    private String observacionesHojarasca;
    private String claveHO;
    private String claveF;
    private Integer puntoProfundidad;
    private Float profundidad030;
    private Float profundidad3060;
    private Float pesoTotal030;
    private Float pesoTotal3060;
    private String equipo030;
    private String equipo3060;
    private String observacionesProfundidad;
    private Float profundidadReal;
    private Float diametroCilindro;
    private Float volumenMaterial;
    private Float pesoTotalSuelo;
    private Float pesoTotalMuestra;
    private String observacionesDAP;
    private String clave030;
    private String clave3060;
    private FuncionesComunes combo = new FuncionesComunes();
    private CESeccionesCapturadas seccionActual = new CESeccionesCapturadas();
    private CESeccionesCapturadas seccionSiguiente = new CESeccionesCapturadas();
    private CDSeguimientoUPM seguimiento = new CDSeguimientoUPM();
    private Datos numeros = new Datos();
    private CDSecuencia cdSecuencia = new CDSecuencia();
    private FuncionesComunes funciones = new FuncionesComunes();
    private int modificar;
    private Version ver = new Version();
    private String version = ver.getVersion();
    private CDSitio cdSitio = new CDSitio();

    public FrmHojarascaProfundidad() {
        initComponents();
        fillCmbPuntosHojarasca();
        fillCmbTiposHojarasca();
        fillCmbPuntosProfundidad();
    }

    public void setDatosiniciales(CESitio ceSitio) {
        //System.out.println("Linea 81 "+ceSitio.getSitioID());
        this.upmID = ceSitio.getUpmID();
        this.sitioID = ceSitio.getSitioID();
        this.sitio = ceSitio.getSitio();

        this.ceSitio.setSitioID(this.sitioID);
        this.ceSitio.setUpmID(this.upmID);
        this.ceSitio.setSitio(this.sitio);
        llenarTablaHojarsaca();
        llenarTablaProfundidad();
        this.ceSitio = ceSitio;
        this.modificar = 0;
        this.funciones.manipularBotonesMenuPrincipal(true);
        limpiarControlesHojarasca();
        limpiarControlesProfundidades();
    }

    public void llenarControles() {
        combo.reiniciarComboModel(this.cmbUPMID);
        cmbSitios.setEnabled(true);
        fillUPMID();
    }

    private void fillUPMID() {
        List<Integer> listCapturado = new ArrayList<>();
        listCapturado = this.cdSitio.getUPMSitios();
        if (listCapturado != null) {
            int size = listCapturado.size();
            for (int i = 0; i < size; i++) {
                cmbUPMID.addItem(listCapturado.get(i));
            }
        }
    }

    private void fillCmbSitio(int upmID) {

        List<Integer> listSitios = new ArrayList<>();
        listSitios = this.cdSitio.getSitiosDisponibles(upmID);
        if (listSitios != null) {
            int size = listSitios.size();
            for (int i = 0; i < size; i++) {
                cmbSitios.addItem(listSitios.get(i));
            }
        }
    }

    public void revisarHojarascaProfundidad(int sitioID) {
        /*revision = true;
        this.upmID = ceSitio.getUpmID();
        this.sitioID = ceSitio.getSitioID();
        this.sitio = ceSitio.getSitio();*/

        this.ceSitio.setSitioID(this.sitioID);
        this.ceSitio.setUpmID(this.upmID);
        this.ceSitio.setSitio(this.sitio);
        llenarTablaHojarsaca();
        llenarTablaProfundidad();
        this.modificar = 1;
        this.funciones.manipularBotonesMenuPrincipal(true);
        chkHojarasca.setSelected(funciones.habilitarCheckBox("SUELO_Hojarasca", this.sitioID));
        chkSueloProfundidades.setSelected(funciones.habilitarCheckBox("SUELO_Profundidad", this.sitioID));
        if (chkHojarasca.isSelected() == false)//si no esta marcado
        {
            manipularControlesHojarasca(false);
        } else {
            manipularControlesHojarasca(true);
        }
        if (chkSueloProfundidades.isSelected() == false)//si no esta marcado
        {
            manipularControlesProfundidades(false);
        } else {
            manipularControlesProfundidades(true);
        }
        limpiarControlesHojarasca();
        limpiarControlesProfundidades();
    }

    private void fillCmbPuntosHojarasca() {
        List<Integer> listPuntos = new ArrayList<>();
        listPuntos = this.cdHojarasca.getPuntosHojarasca(this.sitioID);
        if (listPuntos != null) {
            int size = listPuntos.size();
            for (int i = 0; i < size; i++) {
                cmbPuntoHojarasca.addItem(listPuntos.get(i));
            }
        }
    }

    private void fillCmbTiposHojarasca() {
        List<CatETipoHojarasca> listTipoHojarasca = new ArrayList<>();
        listTipoHojarasca = this.cdHojarasca.getTipohojarasca(this.sitioID);
        if (listTipoHojarasca != null) {
            int size = listTipoHojarasca.size();
            for (int i = 0; i < size; i++) {
                cmbTipoHojarasca.addItem(listTipoHojarasca.get(i));
            }
        }
    }

    private void fillCmbPuntosProfundidad() {
        List<Integer> listPuntos = new ArrayList<>();
        listPuntos = this.cdProfundidad.getPuntosProfundidad(this.sitioID);
        if (listPuntos != null) {
            int size = listPuntos.size();
            for (int i = 0; i < size; i++) {
                cmbPuntoProfundidad.addItem(listPuntos.get(i));
            }
        }
    }

    public void llenarTablaHojarsaca() {
        //System.out.println("linea 151 "+this.sitioID);
        grdHojarasca.setModel(cdHojarasca.getTablaHojarasca(this.sitioID));
        grdHojarasca.getColumnModel().getColumn(2).setPreferredWidth(50);
        grdHojarasca.getColumnModel().getColumn(3).setPreferredWidth(80);
        grdHojarasca.getColumnModel().getColumn(4).setPreferredWidth(70);
        grdHojarasca.getColumnModel().getColumn(5).setPreferredWidth(65);
        grdHojarasca.getColumnModel().getColumn(6).setPreferredWidth(80);
        grdHojarasca.getColumnModel().getColumn(7).setPreferredWidth(80);
        grdHojarasca.getColumnModel().getColumn(8).setPreferredWidth(100);
        grdHojarasca.getColumnModel().getColumn(9).setPreferredWidth(100);
        grdHojarasca.getColumnModel().getColumn(10).setPreferredWidth(165);
        grdHojarasca.getColumnModel().getColumn(11).setPreferredWidth(100);
        grdHojarasca.getColumnModel().getColumn(12).setPreferredWidth(100);
        Tablas tabla = new Tablas();
        int[] column_0 = {0};
        int[] column_1 = {1};
        tabla.hideColumnTable(grdHojarasca, column_0);
        tabla.hideColumnTable(grdHojarasca, column_1);
    }

    public void llenarTablaProfundidad() {
        grdProfundidad.setModel(cdProfundidad.getTablaProfundidad(this.sitioID));
        grdProfundidad.getColumnModel().getColumn(2).setPreferredWidth(50);
        grdProfundidad.getColumnModel().getColumn(3).setPreferredWidth(100);
        grdProfundidad.getColumnModel().getColumn(4).setPreferredWidth(100);
        grdProfundidad.getColumnModel().getColumn(5).setPreferredWidth(90);
        grdProfundidad.getColumnModel().getColumn(6).setPreferredWidth(90);
        grdProfundidad.getColumnModel().getColumn(7).setPreferredWidth(80);
        grdProfundidad.getColumnModel().getColumn(8).setPreferredWidth(80);
        grdProfundidad.getColumnModel().getColumn(9).setPreferredWidth(200);
        grdProfundidad.getColumnModel().getColumn(10).setPreferredWidth(100);
        grdProfundidad.getColumnModel().getColumn(11).setPreferredWidth(100);
        Tablas tabla = new Tablas();
        int[] column_0 = {0};
        int[] column_1 = {1};
        tabla.hideColumnTable(grdProfundidad, column_0);
        tabla.hideColumnTable(grdProfundidad, column_1);
    }

    private void fijarDatosHojarasca() {
        try {
            this.espesorHO = Float.valueOf(txtEspesorHO.getText());
        } catch (NumberFormatException e) {
            this.espesorHO = null;
        }
        try {
            this.espesorF = Float.valueOf(txtEspesorF.getText());
        } catch (NumberFormatException e) {
            this.espesorF = null;
        }
        try {
            this.pesoTotalHO = Float.valueOf(txtPesoTotalHO.getText());
        } catch (NumberFormatException e) {
            this.pesoTotalHO = null;
        }
        try {
            this.pesoTotalF = Float.valueOf(txtPesoTotalF.getText());
        } catch (NumberFormatException e) {
            this.pesoTotalF = null;
        }
        try {
            this.pesoMuestraHO = Float.valueOf(txtPesoMuestraHO.getText());
        } catch (NumberFormatException e) {
            this.pesoMuestraHO = null;
        }
        try {
            this.pesoMuestraF = Float.valueOf(txtPesoMuestraF.getText());
        } catch (NumberFormatException e) {
            this.pesoMuestraF = null;
        }
        this.observacionesHojarasca = txtObservacionesHF.getText();
    }

    private void fijarDatosProfundidad() {
        try {
            this.profundidad030 = Float.valueOf(txtProfundidad030.getText());
        } catch (NumberFormatException e) {
            this.profundidad030 = null;
        }
        try {
            this.profundidad3060 = Float.valueOf(txtProfundidad3060.getText());
        } catch (NumberFormatException e) {
            this.profundidad3060 = null;
        }

        try {
            this.pesoTotal030 = Float.valueOf(txtPesoTotal030.getText());
        } catch (NumberFormatException e) {
            this.pesoTotal030 = null;
        }
        try {
            this.pesoTotal3060 = Float.valueOf(txtPesoTotal3060.getText());
        } catch (NumberFormatException e) {
            this.pesoTotal3060 = null;
        }
        this.equipo030 = txtEquipo030.getText();
        this.equipo3060 = txtEquipo3060.getText();
        this.observacionesProfundidad = txtObservacionesProfundidades.getText();
    }

    private void crearHojarasca() {
        Integer indexPunto = (Integer) cmbPuntoHojarasca.getSelectedItem();
        CatETipoHojarasca tipoHojarasca = (CatETipoHojarasca) cmbTipoHojarasca.getSelectedItem();
        this.ceHojarasca.setSitioID(this.sitioID);
        this.ceHojarasca.setPunto(indexPunto);
        this.ceHojarasca.setTipoID(tipoHojarasca.getTipoHojarascaID());
        this.ceHojarasca.setEspesorHO(this.espesorHO);
        this.ceHojarasca.setEspesorF(this.espesorF);
        this.ceHojarasca.setPesoTotalHO(this.pesoTotalHO);
        this.ceHojarasca.setPesoTotalF(this.pesoTotalF);
        this.ceHojarasca.setPesoMuestraHO(this.pesoMuestraHO);
        this.ceHojarasca.setPesoMuestraF(this.pesoMuestraF);
        this.ceHojarasca.setObservaciones(this.observacionesHojarasca);
        if (!txtEspesorHO.getText().isEmpty()) {
            this.claveHO = crearClave(this.upmID, this.sitio, indexPunto, "HO");
            this.ceHojarasca.setClaveHO(this.claveHO);
        }
        if (!txtEspesorF.getText().isEmpty()) {
            this.claveF = crearClave(this.upmID, this.sitio, indexPunto, "F");
            this.ceHojarasca.setClaveF(this.claveF);
        }
        this.cdHojarasca.insertHojarasca(this.ceHojarasca);
    }

    private void crearProfundidad() {
        this.clave030 = "";
        this.clave3060 = "";
        Integer indexPunto = (Integer) cmbPuntoProfundidad.getSelectedItem();
        this.ceProfundidad.setSitioID(this.sitioID);
        this.ceProfundidad.setPunto(indexPunto);
        this.ceProfundidad.setProfundidad030(this.profundidad030);
        this.ceProfundidad.setProfundidad3060(this.profundidad3060);
        this.ceProfundidad.setPeso030(this.pesoTotal030);
        this.ceProfundidad.setPeso3060(this.pesoTotal3060);
        this.ceProfundidad.setEquipo030(this.equipo030);
        this.ceProfundidad.setEquipo3060(this.equipo3060);
        this.ceProfundidad.setObservaciones(this.observacionesProfundidad);
        if (!txtProfundidad030.getText().isEmpty()) {
            //System.err.println("Profundidad 0-30");
            this.clave030 = crearClave(this.upmID, this.sitio, indexPunto, "S1");
        }
        if (!txtProfundidad3060.getText().isEmpty()) {
            // System.err.println("Profundidad 30-60");
            this.clave3060 = crearClave(this.upmID, this.sitio, indexPunto, "S2");
        }
        this.ceProfundidad.setClave030(this.clave030);
        this.ceProfundidad.setClave3060(this.clave3060);
        //System.err.println(this.clave3060);
        this.cdProfundidad.insertProfundidad(this.ceProfundidad);
    }

    private void actualizarHojarasca() {
        try {
            int fila = grdHojarasca.getSelectedRow();
            String registro = grdHojarasca.getValueAt(fila, 0).toString();
            CatETipoHojarasca tipoHojarasca = (CatETipoHojarasca) cmbTipoHojarasca.getSelectedItem();
            this.ceHojarasca.setHojarascaID(Integer.parseInt(registro));
            this.ceHojarasca.setSitioID(this.sitioID);
            this.ceHojarasca.setTipoID(tipoHojarasca.getTipoHojarascaID());
            this.ceHojarasca.setEspesorHO(this.espesorHO);
            this.ceHojarasca.setEspesorF(this.espesorF);
            this.ceHojarasca.setPesoTotalHO(this.pesoTotalHO);
            this.ceHojarasca.setPesoTotalF(this.pesoTotalF);
            this.ceHojarasca.setPesoMuestraHO(this.pesoMuestraHO);
            this.ceHojarasca.setPesoMuestraF(this.pesoMuestraF);
            this.ceHojarasca.setObservaciones(this.observacionesHojarasca);
            this.cdHojarasca.updateHojarasca(this.ceHojarasca);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla de hojarasca "
                    + e.getClass().getName() + " : " + e.getMessage(), "Longitud de componentes", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void actualizarProfundidad() {
        try {
            int fila = grdProfundidad.getSelectedRow();
            String registro = grdProfundidad.getValueAt(fila, 0).toString();
            this.ceProfundidad.setProfundidadSueloID(Integer.parseInt(registro));
            this.ceProfundidad.setProfundidad030(this.profundidad030);
            this.ceProfundidad.setProfundidad3060(this.profundidad3060);
            this.ceProfundidad.setPeso030(this.pesoTotal030);
            this.ceProfundidad.setPeso3060(this.pesoTotal3060);
            this.ceProfundidad.setEquipo030(this.equipo030);
            this.ceProfundidad.setEquipo3060(this.equipo3060);
            this.ceProfundidad.setObservaciones(this.observacionesProfundidad);
            this.cdProfundidad.updateProfundidad(this.ceProfundidad);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla de profundidad de suelo "
                    + e.getClass().getName() + " : " + e.getMessage(), "Longitud de componentes", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void actualizarDensidad() {
        this.ceDensidad.setSitioID(this.sitioID);
        this.ceDensidad.setProfundidadReal(this.profundidadReal);
        this.ceDensidad.setDiametroCilindro(this.diametroCilindro);
        this.ceDensidad.setVolumen(this.volumenMaterial);
        this.ceDensidad.setPesoTotalSuelo(this.pesoTotalSuelo);
        this.ceDensidad.setObservaciones(observacionesDAP);

        this.cdDensidad.updateDensidadAparente(this.ceDensidad);
    }

    private void eliminarHojarasca() {
        try {
            int fila = grdHojarasca.getSelectedRow();
            String registro = grdHojarasca.getValueAt(fila, 0).toString();

            this.cdHojarasca.deleteHojarasca(Integer.parseInt(registro));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla de hojarasca "
                    + e.getClass().getName() + " : " + e.getMessage(), "Hojarasca y fermentación", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void eliminarProfundidad() {
        try {
            int fila = grdProfundidad.getSelectedRow();
            String registro = grdProfundidad.getValueAt(fila, 0).toString();

            this.cdProfundidad.deleteProfundidad(Integer.parseInt(registro));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla de profundidad de suelos "
                    + e.getClass().getName() + " : " + e.getMessage(), "Profundidad de suelos", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void eliminarDensidad() {
        this.cdDensidad.deleteDensidadAparente(this.sitioID);
    }

    private boolean validarCamposObligatoriosHojarasca() {
        if (cmbPuntoHojarasca.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un punto de hojarasca "
                    + "", "Hojarasca", JOptionPane.INFORMATION_MESSAGE);
            cmbPuntoHojarasca.requestFocus();
            return false;
        } else if (cmbTipoHojarasca.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo de hojarasca "
                    + "", "Hojarasca", JOptionPane.INFORMATION_MESSAGE);
            cmbTipoHojarasca.requestFocus();
            return false;
        } else if (txtEspesorHO.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe proporcionar un espesor de hojarasca HO "
                    + "", "Hojarasca", JOptionPane.INFORMATION_MESSAGE);
            txtEspesorHO.requestFocus();
            return false;
        } else if (txtEspesorF.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe proporcionar un espesor de hojarasca F "
                    + "", "Hojarasca", JOptionPane.INFORMATION_MESSAGE);
            txtEspesorF.requestFocus();
            return false;
        } else if (txtPesoTotalHO.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe proporcionar un peso total HO "
                    + "", "Hojarasca", JOptionPane.INFORMATION_MESSAGE);
            txtPesoTotalHO.requestFocus();
            return false;
        } else if (txtPesoTotalF.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe proporcionar un peso total F "
                    + "", "Hojarasca", JOptionPane.INFORMATION_MESSAGE);
            txtPesoTotalF.requestFocus();
            return false;
        } else if (txtPesoMuestraHO.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe proporcionar un peso muestra HO "
                    + "", "Hojarasca", JOptionPane.INFORMATION_MESSAGE);
            txtPesoMuestraHO.requestFocus();
            return false;
        } else if (txtPesoMuestraF.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe proporcionar un peso muestra F "
                    + "", "Hojarasca", JOptionPane.INFORMATION_MESSAGE);
            txtPesoMuestraF.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean validarCapturaHojarasca() {
        if (cmbPuntoHojarasca.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un punto de hojarasca ",
                    "Hojarasca", JOptionPane.INFORMATION_MESSAGE);
            cmbPuntoHojarasca.requestFocus();
            return false;
        } else if (cmbTipoHojarasca.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo de hojarasca ",
                    "Hojarasca", JOptionPane.INFORMATION_MESSAGE);
            cmbTipoHojarasca.requestFocus();
            return false;
        } else if (!txtEspesorHO.getText().isEmpty() && txtPesoTotalHO.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Si capturó espesor de hojarasca debe capturar peso total de hojarasca",
                    "Hojarasca", JOptionPane.INFORMATION_MESSAGE);
            txtPesoTotalHO.requestFocus();
            return false;
        } else if (!txtEspesorHO.getText().isEmpty() && txtPesoMuestraHO.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Si capturó espesor de hojarasca debe capturar peso de la muestra de hojarasca", "Hojarasca", JOptionPane.INFORMATION_MESSAGE);
            txtPesoMuestraHO.requestFocus();
            return false;
        } else if (!txtPesoTotalHO.getText().isEmpty() && txtEspesorHO.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Si capturó peso total de hojarasca debe capturar espesor de la muestra de hojarasca ", "Hojarasca", JOptionPane.INFORMATION_MESSAGE);
            txtEspesorHO.requestFocus();
            return false;
        } else if (!txtPesoTotalHO.getText().isEmpty() && txtPesoMuestraHO.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Si capturó peso total de hojarasca debe capturar peso de la muestra de hojarasca ", "Hojarasca", JOptionPane.INFORMATION_MESSAGE);
            txtPesoMuestraHO.requestFocus();
            return false;
        } else if (!txtPesoMuestraHO.getText().isEmpty() && txtEspesorHO.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Si capturó peso muestra de hojarasca debe capturar espesor de la muestra de hojarasca ", "Hojarasca", JOptionPane.INFORMATION_MESSAGE);
            txtEspesorHO.requestFocus();
            return false;
        } else if (!txtPesoMuestraHO.getText().isEmpty() && txtPesoTotalHO.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Si capturó peso muestra de hojarasca debe capturar peso total de la muestra de hojarasca ", "Hojarasca", JOptionPane.INFORMATION_MESSAGE);
            txtPesoTotalHO.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean validarCapturaFermentacion() {
        /* if (cmbPuntoHojarasca.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un punto de hojarasca "
                    , "Hojarasca", JOptionPane.INFORMATION_MESSAGE);
            cmbPuntoHojarasca.requestFocus();
            return false;
        } else*/ if (cmbTipoHojarasca.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo de hojarasca ",
                    "Hojarasca", JOptionPane.INFORMATION_MESSAGE);
            cmbTipoHojarasca.requestFocus();
            return false;
        } else if (!txtEspesorF.getText().isEmpty() && txtPesoTotalF.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Si capturó espesor de fermentación debe capturar peso total de fermentación",
                    "Hojarasca", JOptionPane.INFORMATION_MESSAGE);
            txtPesoTotalF.requestFocus();
            return false;
        } else if (!txtEspesorF.getText().isEmpty() && txtPesoMuestraF.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Si capturó espesor de fermentación debe capturar peso de la muestra de fermentación", "Hojarasca", JOptionPane.INFORMATION_MESSAGE);
            txtPesoMuestraF.requestFocus();
            return false;
        } else if (!txtPesoTotalF.getText().isEmpty() && txtEspesorF.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Si capturó peso total de fermentacion debe capturar espesor de la muestra de fermentación", "Hojarasca", JOptionPane.INFORMATION_MESSAGE);
            txtEspesorF.requestFocus();
            return false;
        } else if (!txtPesoTotalF.getText().isEmpty() && txtPesoMuestraF.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Si capturó peso total de fermentación debe capturar peso de la muestra de fermentación ", "Hojarasca", JOptionPane.INFORMATION_MESSAGE);
            txtPesoMuestraF.requestFocus();
            return false;
        } else if (!txtPesoMuestraF.getText().isEmpty() && txtEspesorF.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Si capturó peso muestra de fermentación debe capturar espesor de la muestra de fermentación ", "Hojarasca", JOptionPane.INFORMATION_MESSAGE);
            txtEspesorF.requestFocus();
            return false;
        } else if (!txtPesoMuestraF.getText().isEmpty() && txtPesoTotalF.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Si capturó peso muestra de fermentación debe capturar peso total de la muestra de fermentación ", "Hojarasca", JOptionPane.INFORMATION_MESSAGE);
            txtPesoTotalF.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean validarDatosHojarasca() {
        if (this.espesorHO < 0.0) {
            JOptionPane.showMessageDialog(null, "Debe proporcionar un valor mayor o igual a cero para espesor HO ",
                    "Hojarasca", JOptionPane.INFORMATION_MESSAGE);
            txtEspesorHO.requestFocus();
            return false;
        } else if (this.espesorF < 0.0) {
            JOptionPane.showMessageDialog(null, "Debe proporcionar un valor mayor o igual a cero para espesor F ",
                    "Hojarasca", JOptionPane.INFORMATION_MESSAGE);
            txtEspesorF.requestFocus();
            return false;
        } else if (this.pesoTotalHO < 0.0) {
            JOptionPane.showMessageDialog(null, "Debe proporcionar un valor mayor o igual cero para peso total HO "
                    + "", "Hojarasca", JOptionPane.INFORMATION_MESSAGE);
            txtPesoTotalHO.requestFocus();
            return false;
        } else if (this.pesoTotalF < 0.0) {
            JOptionPane.showMessageDialog(null, "Debe proporcionar un valor mayor o igual cero para peso total F ",
                    "Hojarasca", JOptionPane.INFORMATION_MESSAGE);
            txtPesoTotalF.requestFocus();
            return false;
        } else if (this.pesoMuestraHO < 0.0) {
            JOptionPane.showMessageDialog(null, "Debe proporcionar un valor mayor o igual a cero para peso muestra HO ",
                    "Hojarasca", JOptionPane.INFORMATION_MESSAGE);
            txtPesoMuestraHO.requestFocus();
            return false;
        } else if (this.pesoMuestraF < 0.0) {
            JOptionPane.showMessageDialog(null, "Debe proporcionar un valor mayor o igual a cero para peso muestra F ",
                    "Hojarasca", JOptionPane.INFORMATION_MESSAGE);
            txtPesoMuestraF.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    /*  private boolean validarCamposObligatoriosProfundidad030() {
        if (cmbPuntoProfundidad.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Debe proporcionar un punto para profundidad "
                    + "", "Profundidades suelo", JOptionPane.INFORMATION_MESSAGE);
            cmbPuntoProfundidad.requestFocus();
            return false;
        } else if (txtProfundidad030.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe proporcionar profundidad suelo 0-30 "
                    + "", "Profundidades suelo", JOptionPane.INFORMATION_MESSAGE);
            txtProfundidad030.requestFocus();
            return false;
        } else if (txtPesoTotal030.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe proporcionar peso total suelo 0-30 "
                    + "", "Profundidades suelo", JOptionPane.INFORMATION_MESSAGE);
            txtPesoTotal030.requestFocus();
            return false;
        } else if (txtEquipo030.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe proporcionar equipo suelo 0-30 "
                    + "", "Profundidades suelo", JOptionPane.INFORMATION_MESSAGE);
            txtEquipo030.requestFocus();
            return false;
        } else {
            return true;
        }
    }*/

 /*private boolean validarDatosProfundidad030() {
        if (this.profundidad030 < 0 || this.profundidad030 > 30) {
            JOptionPane.showMessageDialog(null, "Debe proporcionar un valor entre 0 y 30 para profundidad real 0-30 "
                    + "", "Profundidades suelo", JOptionPane.INFORMATION_MESSAGE);
            txtProfundidad030.requestFocus();
            return false;
        } else if (this.pesoTotal030 < 0 || this.pesoTotal030 > 30) {
            JOptionPane.showMessageDialog(null, "Debe proporcionar un valor entre 0 y 30 para peso total 0-30 "
                    + "", "Profundidades suelo", JOptionPane.INFORMATION_MESSAGE);
            txtPesoTotal030.requestFocus();
            return false;
        } else {
            return true;
        }
    }*/
    private boolean validarProfundidad030() {
        if (this.profundidad030 < 0 || this.profundidad030 > 300) {
            JOptionPane.showMessageDialog(null, "Debe proporcionar un valor entre 0 y 300 para profundidad real 0-30 "
                    + "", "Profundidades suelo", JOptionPane.INFORMATION_MESSAGE);
            txtProfundidad030.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean validarProfundidad3060() {
        //System.out.println(txtProfundidad3060.getText());
        if (txtProfundidad3060.getText().equals("0.00")) {
            return true;

        }
        if (this.profundidad3060 != null) {
            if (this.profundidad3060 < 301 || this.profundidad3060 > 600) {

                JOptionPane.showMessageDialog(null, "Debe proporcionar un valor entre 301 y 600  para profundidad real 30-60 "
                        + "", "Profundidades suelo", JOptionPane.INFORMATION_MESSAGE);
                txtProfundidad3060.requestFocus();
                return false;
            }
        }

        return true;
    }

    private boolean validarPesoTotal030() {
        if (this.pesoTotal030 < 0 || this.pesoTotal030 > 1000) {
            JOptionPane.showMessageDialog(null, "Debe proporcionar un valor entre 0 y 1000 para peso total 0-30 ", "Profundidades suelo", JOptionPane.INFORMATION_MESSAGE);
            txtPesoTotal030.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean validarPesoTotal3060() {
        if (this.pesoTotal3060 != null) {
            if (this.pesoTotal3060 < 0 || this.pesoTotal3060 > 1000) {
                JOptionPane.showMessageDialog(null, "Debe proporcionar una valor entre 0 y 1000 para peso total suelo 30-60 ", "Profundidades suelo", JOptionPane.INFORMATION_MESSAGE);
                txtPesoTotal3060.requestFocus();
                return false;
            }
        }
        return true;
    }

    private boolean validarPuntoHojarasca() {
        Integer punto = (Integer) cmbPuntoHojarasca.getSelectedItem();
        CDHojarasca cdHojarasca = new CDHojarasca();
        CEHojarasca ceHojarasca = new CEHojarasca();
        boolean existePunto = false;
        ceHojarasca.setSitioID(this.sitioID);
        ceHojarasca.setPunto(punto);
        if (cdHojarasca.validarCDPuntoHojarasca(ceHojarasca)) {
            existePunto = false;
            JOptionPane.showMessageDialog(null, "El punto introducido ya existe, verifique ", "Hojarasca", JOptionPane.INFORMATION_MESSAGE);
            cmbPuntoHojarasca.requestFocus();
        } else {
            existePunto = true;
        }
        return existePunto;
    }

    private boolean validarPuntoProfundidad() {
        Integer punto = (Integer) cmbPuntoProfundidad.getSelectedItem();
        CDProfundidadSuelo cdProfundidad = new CDProfundidadSuelo();
        CEProfundidadSuelo ceProfundidad = new CEProfundidadSuelo();
        boolean existePunto = false;
        ceProfundidad.setSitioID(this.sitioID);
        ceProfundidad.setPunto(punto);
        if (cdProfundidad.validarCDPuntoProfundidad(ceProfundidad)) {
            existePunto = false;
            JOptionPane.showMessageDialog(null, "El punto introducido ya existe, verifique ", "Profundidad suelo", JOptionPane.INFORMATION_MESSAGE);
            cmbPuntoProfundidad.requestFocus();
        } else {
            existePunto = true;
        }
        return existePunto;
    }

    /*  private boolean validarCamposObligatoriosProfundidad3060() {
        if (!txtProfundidad3060.getText().isEmpty() || !txtPesoTotal3060.getText().isEmpty() || !txtEquipo3060.getText().isEmpty()) {
            if (cmbPuntoProfundidad.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Debe proporcionar un punto para profundidad "
                        + "", "Profundidades suelo", JOptionPane.INFORMATION_MESSAGE);
                cmbPuntoProfundidad.requestFocus();
                return false;
            } else if (txtProfundidad3060.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe proporcionar profundidad suelo 30-60 "
                        + "", "Profundidades suelo", JOptionPane.INFORMATION_MESSAGE);
                txtProfundidad3060.requestFocus();
                return false;
            } else if (txtPesoTotal3060.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe proporcionar peso total suelo 30-60 "
                        + "", "Profundidades suelo", JOptionPane.INFORMATION_MESSAGE);
                txtPesoTotal3060.requestFocus();
                return false;
            } else if (txtEquipo3060.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe proporcionar equipo suelo 30-60 "
                        + "", "Profundidades suelo", JOptionPane.INFORMATION_MESSAGE);
                txtEquipo3060.requestFocus();
                return false;
            } else {
                return validarDatosProfundidad3060();
            }
        }
        return true;
    }*/

 /*private boolean validarDatosProfundidad3060() {
        if (this.profundidad3060 < 31 || this.profundidad3060 > 60) {
            JOptionPane.showMessageDialog(null, "Debe proporcionar un valor entre 31 y 60 para profundidad real 30-60 "
                    + "", "Profundidades suelo", JOptionPane.INFORMATION_MESSAGE);
            txtProfundidad3060.requestFocus();
            return false;
        } else if (this.pesoTotal3060 < 31 || this.pesoTotal3060 > 60) {
            JOptionPane.showMessageDialog(null, "Debe proporcionar un valor entre 31 y 60 para peso total 30-60 "
                    + "", "Profundidades suelo", JOptionPane.INFORMATION_MESSAGE);
            txtPesoTotal3060.requestFocus();
            return false;
        } else {
            return true;
        }
    }*/
    private boolean validarDatosProfundidad36() {
        if (!txtProfundidad3060.getText().isEmpty() || !txtPesoTotal3060.getText().isEmpty() || !txtEquipo3060.getText().isEmpty()) {
            if (this.profundidad3060 < 301 || this.profundidad3060 > 600) {
                JOptionPane.showMessageDialog(null, "Debe proporcionar un valor entre 301 y 600 para profundidad real 30-60 ", "Profundidades suelo", JOptionPane.INFORMATION_MESSAGE);
                txtProfundidad3060.requestFocus();
                return false;
            } else if (this.pesoTotal3060 < 31 || this.pesoTotal3060 > 60) {
                JOptionPane.showMessageDialog(null, "Debe proporcionar un valor entre 31 y 60 para peso total 30-60 ", "Profundidades suelo", JOptionPane.INFORMATION_MESSAGE);
                txtPesoTotal3060.requestFocus();
                return false;
            } else {
                return true;
            }
        }
        return true;
    }

    private boolean validarCapturaProfundidad030() {
        if (cmbPuntoProfundidad.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Debe proporcionar un punto para profundidad "
                    + "", "Profundidades suelo", JOptionPane.INFORMATION_MESSAGE);
            cmbPuntoProfundidad.requestFocus();
            return false;
        } else if (!this.txtProfundidad030.getText().isEmpty() && this.txtPesoTotal030.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Si capturó profundidad 0-30 debe capturar peso total 0-30", "Profundidades suelo", JOptionPane.INFORMATION_MESSAGE);
            txtPesoTotal030.requestFocus();
            return false;
        } else if (!this.txtProfundidad030.getText().isEmpty() && this.txtEquipo030.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Si capturó profundidad 0-30 debe capturar equipo utilizado 0-30", "Profundidades suelo", JOptionPane.INFORMATION_MESSAGE);
            txtEquipo030.requestFocus();
            return false;
        } else if (!this.txtPesoTotal030.getText().isEmpty() && this.txtProfundidad030.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Si capturó profundidad 0-30 debe capturar profundidad 0-30", "Profundidades suelo", JOptionPane.INFORMATION_MESSAGE);
            txtProfundidad030.requestFocus();
            return false;
        } else if (!this.txtPesoTotal030.getText().isEmpty() && this.txtEquipo030.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Si capturó peso total 0-30 debe capturar equipo utilizado 0-30", "Profundidades suelo", JOptionPane.INFORMATION_MESSAGE);
            txtEquipo030.requestFocus();
            return false;
        } else if (!this.txtEquipo030.getText().isEmpty() && this.txtProfundidad030.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Si capturó equipo utilizado 0-30 debe capturar profundidad 0-30", "Profundidades suelo", JOptionPane.INFORMATION_MESSAGE);
            txtEquipo030.requestFocus();
            return false;
        } else if (!this.txtEquipo030.getText().isEmpty() && this.txtPesoTotal030.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Si capturó equipo utilizado 0-30 debe capturar peso total 0-30", "Profundidades suelo", JOptionPane.INFORMATION_MESSAGE);
            txtEquipo030.requestFocus();
            return false;
        }
        return true;
    }

    private boolean validarCapturaProfundidad3060() {
        /* if (cmbPuntoProfundidad.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Debe proporcionar un punto para profundidad "
                    + "", "Profundidades suelo", JOptionPane.INFORMATION_MESSAGE);
            cmbPuntoProfundidad.requestFocus();
            return false;
        } else if (!this.txtProfundidad3060.getText().isEmpty() && this.txtPesoTotal3060.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Si capturó profundidad 30-60 debe capturar peso total 30-60", "Profundidades suelo", JOptionPane.INFORMATION_MESSAGE);
            txtPesoTotal3060.requestFocus();
            return false;
        } else if (!this.txtProfundidad3060.getText().isEmpty() && this.txtEquipo3060.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Si capturó profundidad 30-60 debe capturar equipo utilizado 30-60", "Profundidades suelo", JOptionPane.INFORMATION_MESSAGE);
            txtEquipo3060.requestFocus();
            return false;
        } else if (!this.txtPesoTotal3060.getText().isEmpty() && this.txtProfundidad3060.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Si capturó profundidad 30-60 debe capturar profundidad 30-60", "Profundidades suelo", JOptionPane.INFORMATION_MESSAGE);
            txtProfundidad3060.requestFocus();
            return false;
        } else if (!this.txtPesoTotal3060.getText().isEmpty() && this.txtEquipo3060.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Si capturó peso total 30-60 debe capturar equipo utilizado 30-60", "Profundidades suelo", JOptionPane.INFORMATION_MESSAGE);
            txtEquipo3060.requestFocus();
            return false;
        } else if (!this.txtEquipo3060.getText().isEmpty() && this.txtProfundidad3060.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Si capturó equipo utilizado 30-60 debe capturar profundidad 30-60", "Profundidades suelo", JOptionPane.INFORMATION_MESSAGE);
            txtProfundidad3060.requestFocus();
            return false;
        } else if (!this.txtEquipo3060.getText().isEmpty() && this.txtPesoTotal3060.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Si capturó equipo utilizado 30-60 debe capturar peso total 30-60", "Profundidades suelo", JOptionPane.INFORMATION_MESSAGE);
            txtPesoTotal3060.requestFocus();
            return false;
        }*/

        if (cmbPuntoProfundidad.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Debe proporcionar un punto para profundidad "
                    + "", "Profundidades suelo", JOptionPane.INFORMATION_MESSAGE);
            cmbPuntoProfundidad.requestFocus();
            return false;
        } else if (txtProfundidad030.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Se debe capturar la profundidad 0-30", "Profundidades suelo", JOptionPane.INFORMATION_MESSAGE);
            txtProfundidad030.requestFocus();
            return false;
        } else if (txtPesoTotal030.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Se debe capturar peso total 0-30", "Profundidades suelo", JOptionPane.INFORMATION_MESSAGE);
            txtPesoTotal030.requestFocus();
            return false;
        } else if (txtEquipo030.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Se debe capturar equipo 0-30", "Profundidades suelo", JOptionPane.INFORMATION_MESSAGE);
            txtEquipo030.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private void limpiarControlesHojarasca() {
        cmbPuntoHojarasca.setSelectedItem(null);
        cmbTipoHojarasca.setSelectedItem(null);
        txtEspesorHO.setText("");
        txtEspesorHO.setValue(null);
        txtEspesorF.setText("");
        txtEspesorF.setValue(null);
        txtPesoTotalHO.setText("");
        txtPesoTotalHO.setValue(null);
        txtPesoTotalF.setText("");
        txtPesoTotalF.setValue(null);
        txtPesoMuestraHO.setText("");
        txtPesoMuestraHO.setValue(null);
        txtPesoMuestraF.setText("");
        txtPesoMuestraF.setValue(null);
        txtObservacionesHF.setText("");
        txtClaveHO.setText("");
        txtClaveF.setText("");
        cmbPuntoHojarasca.requestFocus();
        grdHojarasca.removeAll();
    }

    private void limpiarControlesProfundidades() {
        cmbPuntoProfundidad.setSelectedItem(null);
        txtProfundidad030.setText("");
        txtProfundidad030.setValue(null);
        txtProfundidad3060.setText("");
        txtProfundidad3060.setValue(null);
        txtPesoTotal030.setText("");
        txtPesoTotal030.setValue(null);
        txtPesoTotal3060.setText("");
        txtPesoTotal3060.setValue(null);
        txtEquipo030.setText("");
        txtEquipo3060.setText("");
        txtObservacionesProfundidades.setText("");
        txtClave030.setText("");
        txtClave3060.setText("");
        cmbPuntoProfundidad.requestFocus();
    }

    private void manipularControlesHojarasca(boolean actualizar) {
        if (actualizar == true) {
            this.cmbPuntoHojarasca.setEnabled(true);
            this.cmbTipoHojarasca.setEnabled(true);
            this.txtEspesorHO.setEnabled(true);
            this.txtEspesorF.setEnabled(true);
            this.txtPesoTotalHO.setEnabled(true);
            this.txtPesoTotalF.setEnabled(true);
            this.txtPesoMuestraHO.setEnabled(true);
            this.txtPesoMuestraF.setEnabled(true);
            this.txtObservacionesHF.setEnabled(true);
        } else {
            this.cmbPuntoHojarasca.setEnabled(false);
            this.cmbTipoHojarasca.setEnabled(false);
            this.txtEspesorHO.setEnabled(false);
            this.txtEspesorF.setEnabled(false);
            this.txtPesoTotalHO.setEnabled(false);
            this.txtPesoTotalF.setEnabled(false);
            this.txtPesoMuestraHO.setEnabled(false);
            this.txtPesoMuestraF.setEnabled(false);
            this.txtObservacionesHF.setEnabled(false);
        }
    }

    private void manipularControlesProfundidades(boolean activar) {
        if (activar == true) {
            this.cmbPuntoProfundidad.setEnabled(true);
            this.txtProfundidad030.setEnabled(true);
            this.txtProfundidad3060.setEnabled(true);
            this.txtPesoTotal030.setEnabled(true);
            this.txtPesoTotal3060.setEnabled(true);
            this.txtEquipo030.setEnabled(true);
            this.txtEquipo3060.setEnabled(true);
            this.txtObservacionesProfundidades.setEnabled(true);
        } else {
            this.cmbPuntoProfundidad.setEnabled(false);
            this.txtProfundidad030.setEnabled(false);
            this.txtProfundidad3060.setEnabled(false);
            this.txtPesoTotal030.setEnabled(false);
            this.txtPesoTotal3060.setEnabled(false);
            this.txtEquipo030.setEnabled(false);
            this.txtEquipo3060.setEnabled(false);
            this.txtObservacionesProfundidades.setEnabled(false);
        }
    }

    private String crearClave(int upmID, int sitio, int punto, String seccion) {
        String clave = null;
        String upm = crearUPM(upmID);
        String noSitio = crearSitio(sitio);
        clave = upm + "-" + noSitio + "-" + punto + "-" + seccion;
        return clave;
    }

    private String crearUPM(int upmID) {
        String upm = null;
        if (upmID < 10) {
            upm = "00000" + upmID;
        } else if (upmID >= 10 && upmID < 100) {
            upm = "0000" + upmID;
        } else if (upmID >= 100 && upmID < 1000) {
            upm = "000" + upmID;
        } else if (upmID >= 1000 && upmID < 10000) {
            upm = "00" + upmID;
        } else if (upmID >= 10000 && upmID < 100000) {
            upm = "0" + upmID;
        } else {
            upm = String.valueOf(upmID);
        }
        return upm;
    }

    private String crearSitio(int sitio) {
        return "S" + sitio;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblUPM = new javax.swing.JLabel();
        lblHojarascaFermentacion = new javax.swing.JLabel();
        lblSitio = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        cmbTipoHojarasca = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        lblTipoHojarasca = new javax.swing.JLabel();
        lblEspesor = new javax.swing.JLabel();
        lblEspesorHO = new javax.swing.JLabel();
        lblEspesorF = new javax.swing.JLabel();
        lblPesoCapa = new javax.swing.JLabel();
        lblPesoHO = new javax.swing.JLabel();
        lblTotalF = new javax.swing.JLabel();
        lblPesoMuestra = new javax.swing.JLabel();
        lblPesoMuestraHO = new javax.swing.JLabel();
        lblPesoMuestraF = new javax.swing.JLabel();
        lblObservacionesHojarasca = new javax.swing.JLabel();
        lblPuntoHojarasca = new javax.swing.JLabel();
        lblClaveHojarasca = new javax.swing.JLabel();
        lblClaveFermentacion = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtObservacionesHF = new javax.swing.JTextField();
        txtEspesorHO = new javax.swing.JFormattedTextField();
        txtEspesorF = new javax.swing.JFormattedTextField();
        txtPesoTotalHO = new javax.swing.JFormattedTextField();
        txtPesoTotalF = new javax.swing.JFormattedTextField();
        txtPesoMuestraHO = new javax.swing.JFormattedTextField();
        txtPesoMuestraF = new javax.swing.JFormattedTextField();
        cmbPuntoHojarasca = new javax.swing.JComboBox();
        txtClaveHO = new javax.swing.JTextField();
        txtClaveF = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        grdProfundidad = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        lblProfundidadReal = new javax.swing.JLabel();
        lblProfundidad030 = new javax.swing.JLabel();
        lblProfundidad3060 = new javax.swing.JLabel();
        lblPesoTotalSuelo = new javax.swing.JLabel();
        lblPesoTotal030 = new javax.swing.JLabel();
        lblPesoTotal3060 = new javax.swing.JLabel();
        lblEquipoUtilizado = new javax.swing.JLabel();
        lblEquipo030 = new javax.swing.JLabel();
        lblEquipo3060 = new javax.swing.JLabel();
        lblObservacionesProfundidad = new javax.swing.JLabel();
        txtProfundidad3060 = new javax.swing.JFormattedTextField();
        txtProfundidad030 = new javax.swing.JFormattedTextField();
        txtPesoTotal030 = new javax.swing.JFormattedTextField();
        txtPesoTotal3060 = new javax.swing.JFormattedTextField();
        txtObservacionesProfundidades = new javax.swing.JTextField();
        cmbPuntoProfundidad = new javax.swing.JComboBox();
        lblPuntoProfundidad = new javax.swing.JLabel();
        txtEquipo030 = new javax.swing.JTextField();
        txtEquipo3060 = new javax.swing.JTextField();
        txtClave030 = new javax.swing.JTextField();
        lblClaveProfundidad = new javax.swing.JLabel();
        lblClave030 = new javax.swing.JLabel();
        lblClave3060 = new javax.swing.JLabel();
        txtClave3060 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        grdHojarasca = new javax.swing.JTable();
        lblHojarascaFermentacion2 = new javax.swing.JLabel();
        chkHojarasca = new javax.swing.JCheckBox();
        chkSueloProfundidades = new javax.swing.JCheckBox();
        btnAgregarProfundidad = new javax.swing.JButton();
        btnModificarProfundidad = new javax.swing.JButton();
        btnEliminarProfundidad = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        btnSalir = new javax.swing.JButton();
        btnAgregarHojarasca = new javax.swing.JButton();
        btnModificarHojarsca = new javax.swing.JButton();
        btnEliminarHojarasca = new javax.swing.JButton();
        cmbUPMID = new javax.swing.JComboBox<>();
        cmbSitios = new javax.swing.JComboBox<>();
        lblModulo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximizable(true);
        setTitle("Suelos capas de hojarasca y profundidades, módulo E "+version);
        setToolTipText("");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/gob/conafor/utils/logo_conafor.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(940, 675));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setMinimumSize(new java.awt.Dimension(0, 0));
        jPanel1.setPreferredSize(new java.awt.Dimension(927, 630));

        lblUPM.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblUPM.setText("UPMID:");

        lblHojarascaFermentacion.setBackground(new java.awt.Color(153, 153, 153));
        lblHojarascaFermentacion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblHojarascaFermentacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHojarascaFermentacion.setText("Capas de Hojarasca (HO) y Fermentación (F)");
        lblHojarascaFermentacion.setOpaque(true);

        lblSitio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblSitio.setText("Sitio:");

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cmbTipoHojarasca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipoHojarascaActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTipoHojarasca.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTipoHojarasca.setText("Tipo");
        lblTipoHojarasca.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.add(lblTipoHojarasca, new org.netbeans.lib.awtextra.AbsoluteConstraints(53, 0, 50, 41));

        lblEspesor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEspesor.setText("Espesor (mm)");
        lblEspesor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.add(lblEspesor, new org.netbeans.lib.awtextra.AbsoluteConstraints(109, 0, 128, -1));

        lblEspesorHO.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEspesorHO.setText("HO");
        lblEspesorHO.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.add(lblEspesorHO, new org.netbeans.lib.awtextra.AbsoluteConstraints(109, 25, 61, -1));

        lblEspesorF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEspesorF.setText("F");
        lblEspesorF.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.add(lblEspesorF, new org.netbeans.lib.awtextra.AbsoluteConstraints(176, 25, 61, -1));

        lblPesoCapa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPesoCapa.setText("Peso total de la capa (gr)");
        lblPesoCapa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.add(lblPesoCapa, new org.netbeans.lib.awtextra.AbsoluteConstraints(247, 0, 130, -1));

        lblPesoHO.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPesoHO.setText("HO");
        lblPesoHO.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.add(lblPesoHO, new org.netbeans.lib.awtextra.AbsoluteConstraints(247, 25, 61, -1));

        lblTotalF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalF.setText("F");
        lblTotalF.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.add(lblTotalF, new org.netbeans.lib.awtextra.AbsoluteConstraints(314, 25, 61, -1));

        lblPesoMuestra.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPesoMuestra.setText("Peso de la muestra (gr)");
        lblPesoMuestra.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.add(lblPesoMuestra, new org.netbeans.lib.awtextra.AbsoluteConstraints(379, 0, 130, -1));

        lblPesoMuestraHO.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPesoMuestraHO.setText("HO");
        lblPesoMuestraHO.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.add(lblPesoMuestraHO, new org.netbeans.lib.awtextra.AbsoluteConstraints(381, 25, 61, -1));

        lblPesoMuestraF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPesoMuestraF.setText("F");
        lblPesoMuestraF.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.add(lblPesoMuestraF, new org.netbeans.lib.awtextra.AbsoluteConstraints(448, 25, 61, -1));

        lblObservacionesHojarasca.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblObservacionesHojarasca.setText("Observaciones");
        lblObservacionesHojarasca.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.add(lblObservacionesHojarasca, new org.netbeans.lib.awtextra.AbsoluteConstraints(515, 0, 180, 41));

        lblPuntoHojarasca.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPuntoHojarasca.setText("Punto");
        lblPuntoHojarasca.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.add(lblPuntoHojarasca, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 0, 42, 41));

        lblClaveHojarasca.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblClaveHojarasca.setText("HO");
        lblClaveHojarasca.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.add(lblClaveHojarasca, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 20, 90, -1));

        lblClaveFermentacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblClaveFermentacion.setText("F");
        lblClaveFermentacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.add(lblClaveFermentacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 20, 80, -1));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Clave de colecta");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 0, 180, -1));

        txtObservacionesHF.setNextFocusableComponent(btnAgregarHojarasca);
        txtObservacionesHF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtObservacionesHFFocusGained(evt);
            }
        });

        txtEspesorHO.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtEspesorHO.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtEspesorHOFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtEspesorHOFocusLost(evt);
            }
        });
        txtEspesorHO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEspesorHOKeyTyped(evt);
            }
        });

        txtEspesorF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtEspesorF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtEspesorFFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtEspesorFFocusLost(evt);
            }
        });
        txtEspesorF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEspesorFActionPerformed(evt);
            }
        });
        txtEspesorF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEspesorFKeyTyped(evt);
            }
        });

        txtPesoTotalHO.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtPesoTotalHO.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPesoTotalHOFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPesoTotalHOFocusLost(evt);
            }
        });
        txtPesoTotalHO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPesoTotalHOKeyTyped(evt);
            }
        });

        txtPesoTotalF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtPesoTotalF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPesoTotalFFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPesoTotalFFocusLost(evt);
            }
        });
        txtPesoTotalF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPesoTotalFKeyTyped(evt);
            }
        });

        txtPesoMuestraHO.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtPesoMuestraHO.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPesoMuestraHOFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPesoMuestraHOFocusLost(evt);
            }
        });
        txtPesoMuestraHO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPesoMuestraHOKeyTyped(evt);
            }
        });

        txtPesoMuestraF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtPesoMuestraF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPesoMuestraFFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPesoMuestraFFocusLost(evt);
            }
        });
        txtPesoMuestraF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPesoMuestraFKeyTyped(evt);
            }
        });

        txtClaveHO.setToolTipText("");
        txtClaveHO.setEnabled(false);

        txtClaveF.setToolTipText("");
        txtClaveF.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 880, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(cmbPuntoHojarasca, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(cmbTipoHojarasca, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(txtEspesorHO, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(txtEspesorF, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(txtPesoTotalHO, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(txtPesoTotalF, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(txtPesoMuestraHO, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(txtPesoMuestraF, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(txtObservacionesHF, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtClaveHO, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtClaveF, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbPuntoHojarasca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTipoHojarasca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEspesorHO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEspesorF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPesoTotalHO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPesoTotalF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPesoMuestraHO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPesoMuestraF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtObservacionesHF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtClaveHO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtClaveF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(2, 2, 2))
        );

        grdProfundidad.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        grdProfundidad.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        grdProfundidad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grdProfundidadMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(grdProfundidad);

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));

        lblProfundidadReal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProfundidadReal.setText("Profundidad real (mm)");
        lblProfundidadReal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblProfundidad030.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProfundidad030.setText("0-30 cm");
        lblProfundidad030.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblProfundidad3060.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProfundidad3060.setText("30-60 cm");
        lblProfundidad3060.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblPesoTotalSuelo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPesoTotalSuelo.setText("Peso total del suelo  (gr)");
        lblPesoTotalSuelo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblPesoTotal030.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPesoTotal030.setText("0-30 cm");
        lblPesoTotal030.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblPesoTotal3060.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPesoTotal3060.setText("30-60 cm");
        lblPesoTotal3060.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblEquipoUtilizado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEquipoUtilizado.setText("Equipo utilizado");
        lblEquipoUtilizado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblEquipo030.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEquipo030.setText("0-30");
        lblEquipo030.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblEquipo3060.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEquipo3060.setText("30-60");
        lblEquipo3060.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblObservacionesProfundidad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblObservacionesProfundidad.setText("Observaciones");
        lblObservacionesProfundidad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtProfundidad3060.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtProfundidad3060.setNextFocusableComponent(txtPesoTotal030);
        txtProfundidad3060.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtProfundidad3060FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtProfundidad3060FocusLost(evt);
            }
        });
        txtProfundidad3060.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtProfundidad3060KeyTyped(evt);
            }
        });

        txtProfundidad030.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtProfundidad030.setNextFocusableComponent(txtProfundidad3060);
        txtProfundidad030.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtProfundidad030FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtProfundidad030FocusLost(evt);
            }
        });
        txtProfundidad030.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtProfundidad030KeyTyped(evt);
            }
        });

        txtPesoTotal030.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtPesoTotal030.setNextFocusableComponent(txtPesoTotal3060);
        txtPesoTotal030.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPesoTotal030FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPesoTotal030FocusLost(evt);
            }
        });
        txtPesoTotal030.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPesoTotal030KeyTyped(evt);
            }
        });

        txtPesoTotal3060.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtPesoTotal3060.setNextFocusableComponent(txtEquipo030);
        txtPesoTotal3060.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPesoTotal3060FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPesoTotal3060FocusLost(evt);
            }
        });
        txtPesoTotal3060.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPesoTotal3060KeyTyped(evt);
            }
        });

        txtObservacionesProfundidades.setNextFocusableComponent(btnAgregarProfundidad);
        txtObservacionesProfundidades.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtObservacionesProfundidadesFocusGained(evt);
            }
        });

        cmbPuntoProfundidad.setNextFocusableComponent(txtProfundidad030);
        cmbPuntoProfundidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPuntoProfundidadActionPerformed(evt);
            }
        });

        lblPuntoProfundidad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPuntoProfundidad.setText("Punto");
        lblPuntoProfundidad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtEquipo030.setNextFocusableComponent(txtEquipo3060);
        txtEquipo030.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtEquipo030FocusGained(evt);
            }
        });

        txtEquipo3060.setNextFocusableComponent(txtObservacionesProfundidades);
        txtEquipo3060.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtEquipo3060FocusGained(evt);
            }
        });

        txtClave030.setToolTipText("");
        txtClave030.setEnabled(false);

        lblClaveProfundidad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblClaveProfundidad.setText("Clave de colecta");
        lblClaveProfundidad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblClaveProfundidad.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblClave030.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblClave030.setText("0-30");
        lblClave030.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblClave3060.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblClave3060.setText("30-60");
        lblClave3060.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtClave3060.setToolTipText("");
        txtClave3060.setEnabled(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbPuntoProfundidad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPuntoProfundidad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lblProfundidadReal, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPesoTotalSuelo, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(txtProfundidad030, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtProfundidad3060, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(lblProfundidad030, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblProfundidad3060, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPesoTotal030, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPesoTotal030, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblPesoTotal3060, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPesoTotal3060, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEquipoUtilizado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblEquipo030, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(lblEquipo3060, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(txtEquipo030, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtEquipo3060, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblObservacionesProfundidad, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                    .addComponent(txtObservacionesProfundidades))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtClave030, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblClave030, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtClave3060)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(lblClave3060, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lblClaveProfundidad, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPuntoProfundidad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblProfundidadReal)
                                    .addComponent(lblPesoTotalSuelo)
                                    .addComponent(lblEquipoUtilizado))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblProfundidad030)
                                    .addComponent(lblProfundidad3060)
                                    .addComponent(lblPesoTotal030)
                                    .addComponent(lblPesoTotal3060)
                                    .addComponent(lblEquipo030)
                                    .addComponent(lblEquipo3060)))
                            .addComponent(lblObservacionesProfundidad, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(lblClaveProfundidad)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblClave030)
                                    .addComponent(lblClave3060))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtProfundidad030, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProfundidad3060, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPesoTotal030, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPesoTotal3060, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtObservacionesProfundidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbPuntoProfundidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEquipo030, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEquipo3060, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtClave030, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtClave3060, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 880, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        grdHojarasca.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        grdHojarasca.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        grdHojarasca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grdHojarascaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(grdHojarasca);

        lblHojarascaFermentacion2.setBackground(new java.awt.Color(153, 153, 153));
        lblHojarascaFermentacion2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblHojarascaFermentacion2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHojarascaFermentacion2.setText("Suelo a las profundidades de 0-30 CM y 30-60 CM");
        lblHojarascaFermentacion2.setOpaque(true);

        chkHojarasca.setBackground(new java.awt.Color(204, 204, 204));
        chkHojarasca.setSelected(true);
        chkHojarasca.setText("Hay Capas de hojarasca (HO) y fermentación (F)");
        chkHojarasca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkHojarascaActionPerformed(evt);
            }
        });

        chkSueloProfundidades.setBackground(new java.awt.Color(204, 204, 204));
        chkSueloProfundidades.setSelected(true);
        chkSueloProfundidades.setText("Hay Suelo a las profundidades de 0-30 y 30-60 ");
        chkSueloProfundidades.setNextFocusableComponent(cmbPuntoProfundidad);
        chkSueloProfundidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSueloProfundidadesActionPerformed(evt);
            }
        });

        btnAgregarProfundidad.setText("Agregar");
        btnAgregarProfundidad.setNextFocusableComponent(btnModificarProfundidad);
        btnAgregarProfundidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProfundidadActionPerformed(evt);
            }
        });

        btnModificarProfundidad.setText("Modificar");
        btnModificarProfundidad.setNextFocusableComponent(btnEliminarProfundidad);
        btnModificarProfundidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarProfundidadActionPerformed(evt);
            }
        });

        btnEliminarProfundidad.setText("Eliminar");
        btnEliminarProfundidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProfundidadActionPerformed(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(204, 204, 204));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnSalir.setMnemonic('s');
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(404, 404, 404)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(409, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addComponent(btnSalir)
                .addContainerGap())
        );

        btnAgregarHojarasca.setText("Agregar");
        btnAgregarHojarasca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarHojarascaActionPerformed(evt);
            }
        });

        btnModificarHojarsca.setText("Modificar");
        btnModificarHojarsca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarHojarscaActionPerformed(evt);
            }
        });

        btnEliminarHojarasca.setText("Eliminar");
        btnEliminarHojarasca.setNextFocusableComponent(chkSueloProfundidades);
        btnEliminarHojarasca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarHojarascaActionPerformed(evt);
            }
        });

        cmbUPMID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbUPMIDActionPerformed(evt);
            }
        });

        cmbSitios.setEnabled(false);
        cmbSitios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSitiosActionPerformed(evt);
            }
        });

        lblModulo.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        lblModulo.setForeground(new java.awt.Color(255, 0, 0));
        lblModulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblModulo.setText("E");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAgregarHojarasca, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificarHojarsca)
                        .addGap(16, 16, 16)
                        .addComponent(btnEliminarHojarasca, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lblHojarascaFermentacion2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addComponent(lblHojarascaFermentacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblUPM)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmbUPMID, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblModulo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblSitio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmbSitios, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(chkSueloProfundidades))
                            .addComponent(chkHojarasca))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jScrollPane1)))
                        .addGap(10, 10, 10))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAgregarProfundidad, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(btnModificarProfundidad)
                        .addGap(7, 7, 7)
                        .addComponent(btnEliminarProfundidad, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblModulo)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmbSitios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblSitio))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblUPM)
                                .addComponent(cmbUPMID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(12, 12, 12)
                .addComponent(lblHojarascaFermentacion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkHojarasca)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAgregarHojarasca)
                        .addComponent(btnModificarHojarsca))
                    .addComponent(btnEliminarHojarasca))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblHojarascaFermentacion2)
                .addGap(5, 5, 5)
                .addComponent(chkSueloProfundidades)
                .addGap(6, 6, 6)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAgregarProfundidad)
                    .addComponent(btnModificarProfundidad)
                    .addComponent(btnEliminarProfundidad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 938, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtEspesorHOFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEspesorHOFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtEspesorHO.selectAll();
            }
        });
    }//GEN-LAST:event_txtEspesorHOFocusGained

    private void txtEspesorFFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEspesorFFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtEspesorF.selectAll();
            }
        });
    }//GEN-LAST:event_txtEspesorFFocusGained

    private void txtPesoTotalHOFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPesoTotalHOFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtPesoTotalHO.selectAll();
            }
        });
    }//GEN-LAST:event_txtPesoTotalHOFocusGained

    private void txtPesoTotalFFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPesoTotalFFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtPesoTotalF.selectAll();
            }
        });
    }//GEN-LAST:event_txtPesoTotalFFocusGained

    private void txtPesoMuestraHOFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPesoMuestraHOFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtPesoMuestraHO.selectAll();
            }
        });
    }//GEN-LAST:event_txtPesoMuestraHOFocusGained

    private void txtPesoMuestraFFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPesoMuestraFFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtPesoMuestraF.selectAll();
            }
        });
    }//GEN-LAST:event_txtPesoMuestraFFocusGained

    private void txtObservacionesHFFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtObservacionesHFFocusGained
        txtObservacionesHF.selectAll();
    }//GEN-LAST:event_txtObservacionesHFFocusGained

    private void txtProfundidad030FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtProfundidad030FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtProfundidad030.selectAll();
            }
        });
    }//GEN-LAST:event_txtProfundidad030FocusGained

    private void txtProfundidad3060FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtProfundidad3060FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtProfundidad3060.selectAll();
            }
        });
    }//GEN-LAST:event_txtProfundidad3060FocusGained

    private void txtPesoTotal030FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPesoTotal030FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtPesoTotal030.selectAll();
            }
        });
    }//GEN-LAST:event_txtPesoTotal030FocusGained

    private void txtPesoTotal3060FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPesoTotal3060FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtPesoTotal3060.selectAll();
            }
        });
    }//GEN-LAST:event_txtPesoTotal3060FocusGained

    private void txtObservacionesProfundidadesFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtObservacionesProfundidadesFocusGained
        txtObservacionesProfundidades.selectAll();
    }//GEN-LAST:event_txtObservacionesProfundidadesFocusGained

    private void txtEspesorHOFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEspesorHOFocusLost
        if (txtEspesorHO.getText().isEmpty()) {
            txtEspesorHO.setValue(null);
        }
    }//GEN-LAST:event_txtEspesorHOFocusLost

    private void txtEspesorFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEspesorFFocusLost
        if (txtEspesorF.getText().isEmpty()) {
            txtEspesorF.setValue(null);
        } else {
            if (txtEspesorF.getText().equals("0")) {
                txtPesoTotalF.setText("0.0");
                txtPesoMuestraF.setText("0.0");
            }
        }
    }//GEN-LAST:event_txtEspesorFFocusLost

    private void txtPesoTotalHOFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPesoTotalHOFocusLost
        if (txtPesoTotalHO.getText().isEmpty()) {
            txtPesoTotalHO.setValue(null);
        }
    }//GEN-LAST:event_txtPesoTotalHOFocusLost

    private void txtPesoTotalFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPesoTotalFFocusLost
        if (txtPesoTotalF.getText().isEmpty()) {
            txtPesoTotalF.setValue(null);
        }
    }//GEN-LAST:event_txtPesoTotalFFocusLost

    private void txtPesoMuestraHOFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPesoMuestraHOFocusLost
        if (txtPesoMuestraHO.getText().isEmpty()) {
            txtPesoMuestraHO.setValue(null);
        }
    }//GEN-LAST:event_txtPesoMuestraHOFocusLost

    private void txtPesoMuestraFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPesoMuestraFFocusLost
        if (txtPesoMuestraF.getText().isEmpty()) {
            txtPesoMuestraF.setValue(null);
        }
    }//GEN-LAST:event_txtPesoMuestraFFocusLost

    private void txtProfundidad030FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtProfundidad030FocusLost
        if (txtProfundidad030.getText().isEmpty()) {
            txtProfundidad030.setValue(null);
        }
    }//GEN-LAST:event_txtProfundidad030FocusLost

    private void txtProfundidad3060FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtProfundidad3060FocusLost
        if (txtProfundidad3060.getText().isEmpty()) {
            txtProfundidad3060.setValue(null);
        }
    }//GEN-LAST:event_txtProfundidad3060FocusLost

    private void txtPesoTotal030FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPesoTotal030FocusLost
        if (txtPesoTotal030.getText().isEmpty()) {
            txtPesoTotal030.setValue(null);
        }
    }//GEN-LAST:event_txtPesoTotal030FocusLost

    private void txtPesoTotal3060FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPesoTotal3060FocusLost
        if (txtPesoTotal3060.getText().isEmpty()) {
            txtPesoTotal3060.setValue(null);
        }
    }//GEN-LAST:event_txtPesoTotal3060FocusLost

    private void txtEquipo030FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEquipo030FocusGained
        txtEquipo030.selectAll();
    }//GEN-LAST:event_txtEquipo030FocusGained

    private void txtEquipo3060FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEquipo3060FocusGained
        txtEquipo3060.selectAll();
    }//GEN-LAST:event_txtEquipo3060FocusGained

    private void btnAgregarHojarascaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarHojarascaActionPerformed
        fijarDatosHojarasca();
        if (combo.isEnabledCmbSitios(cmbSitios) == false) {

        } else {
            if (validarCapturaHojarasca() && validarCapturaFermentacion() && validarDatosHojarasca() && validarCamposObligatoriosHojarasca() && validarPuntoHojarasca()) {
                crearHojarasca();
                //this.cdHojarasca.reenumerarHojarasca(this.sitioID);
                llenarTablaHojarsaca();
                //this.combo.reiniciarComboModel(cmbPuntoHojarasca);
                //fillCmbPuntosHojarasca();
                limpiarControlesHojarasca();
            }
        }
    }//GEN-LAST:event_btnAgregarHojarascaActionPerformed

    private void btnModificarHojarscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarHojarscaActionPerformed
        fijarDatosHojarasca();
        if (combo.isEnabledCmbSitios(cmbSitios) == false) {

        } else {
            if (validarCapturaHojarasca() && validarCapturaFermentacion() && validarDatosHojarasca() && validarCamposObligatoriosHojarasca()) {
                actualizarHojarasca();
                llenarTablaHojarsaca();
                limpiarControlesHojarasca();
            }
        }
    }//GEN-LAST:event_btnModificarHojarscaActionPerformed

    private void btnEliminarHojarascaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarHojarascaActionPerformed
        try {
            if (combo.isEnabledCmbSitios(cmbSitios) == false) {

            } else {
                int fila = grdHojarasca.getSelectedRow();
                String registro = grdHojarasca.getValueAt(fila, 0).toString();
                int respuesta = JOptionPane.showConfirmDialog(null, "¿Esta seguro de borrar el registro de hojarasca",
                        "Hojarasca", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (respuesta == JOptionPane.YES_OPTION) {
                    this.cdHojarasca.deleteHojarasca(Integer.parseInt(registro));
                    //this.cdHojarasca.reenumerarHojarasca(this.sitioID);
                    llenarTablaHojarsaca();
                    //this.combo.reiniciarComboModel(cmbPuntoHojarasca);
                    //fillCmbPuntosHojarasca();
                    limpiarControlesHojarasca();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla de hojarasca"
                    + "", "Hojarasca", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarHojarascaActionPerformed

    private void btnAgregarProfundidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProfundidadActionPerformed
        fijarDatosProfundidad();
        if (combo.isEnabledCmbSitios(cmbSitios) == false) {

        } else {
            if (validarCapturaProfundidad030() && validarCapturaProfundidad3060() && validarPesoTotal030() && validarPesoTotal3060() && validarProfundidad030() && validarProfundidad3060() && validarPuntoProfundidad()) {
                crearProfundidad();
                // this.cdProfundidad.reenumerarProfundidad(this.sitioID);
                llenarTablaProfundidad();
                //this.combo.reiniciarComboModel(cmbPuntoProfundidad);
                // fillCmbPuntosProfundidad();
                limpiarControlesProfundidades();
            }
        }
    }//GEN-LAST:event_btnAgregarProfundidadActionPerformed

    private void btnModificarProfundidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarProfundidadActionPerformed
        fijarDatosProfundidad();
        if (combo.isEnabledCmbSitios(cmbSitios) == false) {

        } else {
            if (validarCapturaProfundidad030() && validarCapturaProfundidad3060() && validarPesoTotal030() && validarPesoTotal3060() && validarProfundidad030() && validarProfundidad3060()) {
                actualizarProfundidad();
                llenarTablaProfundidad();
                limpiarControlesProfundidades();
            }
        }
    }//GEN-LAST:event_btnModificarProfundidadActionPerformed

    private void btnEliminarProfundidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProfundidadActionPerformed
        try {
            if (combo.isEnabledCmbSitios(cmbSitios) == false) {

            } else {
                int fila = grdProfundidad.getSelectedRow();
                String registro = grdProfundidad.getValueAt(fila, 0).toString();
                int respuesta = JOptionPane.showConfirmDialog(null, "¿Esta seguro de borrar el registro de profundidad?",
                        "Profundidad de suelo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (respuesta == JOptionPane.YES_OPTION) {
                    this.cdProfundidad.deleteProfundidad(Integer.parseInt(registro));
                    //this.cdProfundidad.reenumerarProfundidad(this.sitioID);
                    llenarTablaProfundidad();
                    //this.combo.reiniciarComboModel(cmbPuntoProfundidad);
                    // fillCmbPuntosProfundidad();
                    limpiarControlesProfundidades();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla de profundidades de suelo "
                    + "", "Profundidad suelo", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarProfundidadActionPerformed

    private void grdHojarascaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grdHojarascaMouseClicked
        if (evt.getButton() == 1) {
            int fila = grdHojarasca.getSelectedRow();
            String id = grdHojarasca.getValueAt(fila, 0).toString();
            this.ceHojarasca = this.cdHojarasca.getDatosHojarasca(Integer.parseInt(id));
            Integer indexPunto = this.ceHojarasca.getPunto();
            cmbPuntoHojarasca.setSelectedItem(indexPunto);
            CatETipoHojarasca tipoHojarasca = new CatETipoHojarasca();
            tipoHojarasca.setTipoHojarascaID(this.ceHojarasca.getTipoID());
            cmbTipoHojarasca.setSelectedItem(tipoHojarasca);
            txtEspesorHO.setText(String.valueOf(this.ceHojarasca.getEspesorHO()));
            txtEspesorF.setText(String.valueOf(this.ceHojarasca.getEspesorF()));
            txtPesoTotalHO.setText(String.valueOf(this.ceHojarasca.getPesoTotalHO()));
            txtPesoTotalF.setText(String.valueOf(this.ceHojarasca.getPesoTotalF()));
            txtPesoMuestraHO.setText(String.valueOf(this.ceHojarasca.getPesoMuestraHO()));
            txtPesoMuestraF.setText(String.valueOf(this.ceHojarasca.getPesoMuestraF()));
            txtObservacionesHF.setText(this.ceHojarasca.getObservaciones());
            txtClaveHO.setText(this.ceHojarasca.getClaveHO());
            txtClaveF.setText(this.ceHojarasca.getClaveF());
        }
    }//GEN-LAST:event_grdHojarascaMouseClicked

    private void grdProfundidadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grdProfundidadMouseClicked
        if (evt.getButton() == 1) {
            int fila = grdProfundidad.getSelectedRow();
            String id = grdProfundidad.getValueAt(fila, 0).toString();
            this.ceProfundidad = this.cdProfundidad.getDatosProfundidad(Integer.parseInt(id));
            Integer indexPunto = this.ceProfundidad.getPunto();
            cmbPuntoProfundidad.setSelectedItem(indexPunto);
            txtProfundidad030.setText(String.valueOf(this.ceProfundidad.getProfundidad030()));
            txtProfundidad3060.setText(String.valueOf(this.ceProfundidad.getProfundidad3060()));
            if ("0.0".equals(txtProfundidad3060.getText())) {
                txtProfundidad3060.setText("");
            }
            txtPesoTotal030.setText(String.valueOf(this.ceProfundidad.getPeso030()));
            txtPesoTotal3060.setText(String.valueOf(this.ceProfundidad.getPeso3060()));
            if ("0.0".equals(txtPesoTotal3060.getText())) {
                txtPesoTotal3060.setText("");
            }
            txtEquipo030.setText(this.ceProfundidad.getEquipo030());
            txtEquipo3060.setText(this.ceProfundidad.getEquipo3060());
            txtObservacionesProfundidades.setText(this.ceProfundidad.getObservaciones());
            txtClave030.setText(this.ceProfundidad.getClave030());
            txtClave3060.setText(this.ceProfundidad.getClave3060());
        }
    }//GEN-LAST:event_grdProfundidadMouseClicked

    private void txtEspesorHOKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEspesorHOKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtEspesorHOKeyTyped

    private void txtEspesorFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEspesorFKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtEspesorFKeyTyped

    private void txtPesoTotalHOKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesoTotalHOKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtPesoTotalHOKeyTyped

    private void txtPesoTotalFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesoTotalFKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtPesoTotalFKeyTyped

    private void txtPesoMuestraHOKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesoMuestraHOKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtPesoMuestraHOKeyTyped

    private void txtPesoMuestraFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesoMuestraFKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtPesoMuestraFKeyTyped

    private void txtProfundidad030KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProfundidad030KeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtProfundidad030KeyTyped

    private void txtProfundidad3060KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProfundidad3060KeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtProfundidad3060KeyTyped

    private void txtPesoTotal030KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesoTotal030KeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtPesoTotal030KeyTyped

    private void txtPesoTotal3060KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesoTotal3060KeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtPesoTotal3060KeyTyped

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.hide();
        funciones.manipularBotonesMenuPrincipal(false);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void chkHojarascaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkHojarascaActionPerformed
        if (chkHojarasca.isSelected()) {
            manipularControlesHojarasca(true);
        } else {
            Object[] opciones = {"Si", "No"};
            int respuesta = JOptionPane.showOptionDialog(null, "Si capturo, se borrarán todos los datos de hojarasca y fermentacion ¿Esta seguro?",
                    "Suelo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[1]);
            if (respuesta == JOptionPane.YES_OPTION) {
                this.cdHojarasca.deleteHojarascaSitio(this.sitioID);
                this.funciones.reiniciarTabla(this.grdHojarasca);
                llenarTablaHojarsaca();
                //this.funciones.reiniciarComboModel(cmbPuntoHojarasca);
                //fillCmbPuntosHojarasca();
                limpiarControlesHojarasca();
                manipularControlesHojarasca(false);
            } else {
                chkHojarasca.setSelected(true);
                chkHojarasca.requestFocus();
            }
        }
    }//GEN-LAST:event_chkHojarascaActionPerformed

    private void chkSueloProfundidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSueloProfundidadesActionPerformed
        if (chkSueloProfundidades.isSelected()) {
            manipularControlesProfundidades(true);
        } else {
            Object[] opciones = {"Si", "No"};
            int respuesta = JOptionPane.showOptionDialog(null, "Si capturo, se borrarán todos los datos de de profundidades 0-30 y 30-60 ¿Esta seguro?",
                    "Suelo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[1]);
            if (respuesta == JOptionPane.YES_OPTION) {
                this.cdProfundidad.deleteProfundidadSitio(this.sitioID);
                this.funciones.reiniciarTabla(this.grdProfundidad);
                llenarTablaProfundidad();
                //this.funciones.reiniciarComboModel(cmbPuntoProfundidad);
                // fillCmbPuntosProfundidad();
                limpiarControlesProfundidades();
                manipularControlesProfundidades(false);
            } else {
                chkSueloProfundidades.setSelected(true);
                chkSueloProfundidades.requestFocus();
            }
        }
    }//GEN-LAST:event_chkSueloProfundidadesActionPerformed

    private void cmbPuntoProfundidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPuntoProfundidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbPuntoProfundidadActionPerformed

    private void txtEspesorFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEspesorFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEspesorFActionPerformed

    private void cmbTipoHojarascaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoHojarascaActionPerformed
        if (cmbTipoHojarasca.getSelectedIndex() == 9) {
            txtPesoTotalHO.setText("0.0");
            txtPesoTotalF.setText("0.0");
            txtEspesorF.setText("0.0");
            txtEspesorHO.setText("0.0");
            txtPesoMuestraF.setText("0.0");
            txtPesoMuestraHO.setText("0.0");
        }
    }//GEN-LAST:event_cmbTipoHojarascaActionPerformed

    private void cmbUPMIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbUPMIDActionPerformed
        Integer upmID = (Integer) cmbUPMID.getSelectedItem();
        Integer sitio = (Integer) cmbSitios.getSelectedItem();
        if (cmbUPMID.getSelectedItem() != null) {
            this.upmID = (Integer) cmbUPMID.getSelectedItem();
            combo.reiniciarComboModel(cmbSitios);
            fillCmbSitio(upmID);
            cmbSitios.setEnabled(true);
        } else {
            combo.reiniciarComboModel(cmbSitios);
            cmbSitios.setSelectedItem(null);
            cmbSitios.setEnabled(false);
        }

    }//GEN-LAST:event_cmbUPMIDActionPerformed

    private void cmbSitiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSitiosActionPerformed
        try {
            //System.out.println("item selected=\t"+cmbSitios.getSelectedItem());
            if (cmbSitios.getSelectedItem() == null) {
                this.sitioID = 0;
                /*limpiarControles();
                limpiarPorcentajes();*/
            } else {
                String upm = cmbUPMID.getSelectedItem().toString();
                String sitio = cmbSitios.getSelectedItem().toString();
                this.sitioID = cdSitio.getSitioIDNuevo(upm, sitio);

            }
            revisarHojarascaProfundidad(this.sitioID);

//llenarTabla();
        } catch (Exception e) {
            e.getCause();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSitiosActionPerformed

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarHojarasca;
    private javax.swing.JButton btnAgregarProfundidad;
    private javax.swing.JButton btnEliminarHojarasca;
    private javax.swing.JButton btnEliminarProfundidad;
    private javax.swing.JButton btnModificarHojarsca;
    private javax.swing.JButton btnModificarProfundidad;
    private javax.swing.JButton btnSalir;
    private javax.swing.JCheckBox chkHojarasca;
    private javax.swing.JCheckBox chkSueloProfundidades;
    private javax.swing.JComboBox cmbPuntoHojarasca;
    private javax.swing.JComboBox cmbPuntoProfundidad;
    private javax.swing.JComboBox<Integer> cmbSitios;
    private javax.swing.JComboBox cmbTipoHojarasca;
    private javax.swing.JComboBox<Integer> cmbUPMID;
    private javax.swing.JTable grdHojarasca;
    private javax.swing.JTable grdProfundidad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblClave030;
    private javax.swing.JLabel lblClave3060;
    private javax.swing.JLabel lblClaveFermentacion;
    private javax.swing.JLabel lblClaveHojarasca;
    private javax.swing.JLabel lblClaveProfundidad;
    private javax.swing.JLabel lblEquipo030;
    private javax.swing.JLabel lblEquipo3060;
    private javax.swing.JLabel lblEquipoUtilizado;
    private javax.swing.JLabel lblEspesor;
    private javax.swing.JLabel lblEspesorF;
    private javax.swing.JLabel lblEspesorHO;
    private javax.swing.JLabel lblHojarascaFermentacion;
    private javax.swing.JLabel lblHojarascaFermentacion2;
    private javax.swing.JLabel lblModulo;
    private javax.swing.JLabel lblObservacionesHojarasca;
    private javax.swing.JLabel lblObservacionesProfundidad;
    private javax.swing.JLabel lblPesoCapa;
    private javax.swing.JLabel lblPesoHO;
    private javax.swing.JLabel lblPesoMuestra;
    private javax.swing.JLabel lblPesoMuestraF;
    private javax.swing.JLabel lblPesoMuestraHO;
    private javax.swing.JLabel lblPesoTotal030;
    private javax.swing.JLabel lblPesoTotal3060;
    private javax.swing.JLabel lblPesoTotalSuelo;
    private javax.swing.JLabel lblProfundidad030;
    private javax.swing.JLabel lblProfundidad3060;
    private javax.swing.JLabel lblProfundidadReal;
    private javax.swing.JLabel lblPuntoHojarasca;
    private javax.swing.JLabel lblPuntoProfundidad;
    private javax.swing.JLabel lblSitio;
    private javax.swing.JLabel lblTipoHojarasca;
    private javax.swing.JLabel lblTotalF;
    private javax.swing.JLabel lblUPM;
    public javax.swing.JTextField txtClave030;
    public javax.swing.JTextField txtClave3060;
    public javax.swing.JTextField txtClaveF;
    public javax.swing.JTextField txtClaveHO;
    private javax.swing.JTextField txtEquipo030;
    private javax.swing.JTextField txtEquipo3060;
    private javax.swing.JFormattedTextField txtEspesorF;
    private javax.swing.JFormattedTextField txtEspesorHO;
    private javax.swing.JTextField txtObservacionesHF;
    private javax.swing.JTextField txtObservacionesProfundidades;
    private javax.swing.JFormattedTextField txtPesoMuestraF;
    private javax.swing.JFormattedTextField txtPesoMuestraHO;
    private javax.swing.JFormattedTextField txtPesoTotal030;
    private javax.swing.JFormattedTextField txtPesoTotal3060;
    private javax.swing.JFormattedTextField txtPesoTotalF;
    private javax.swing.JFormattedTextField txtPesoTotalHO;
    private javax.swing.JFormattedTextField txtProfundidad030;
    private javax.swing.JFormattedTextField txtProfundidad3060;
    // End of variables declaration//GEN-END:variables

}
