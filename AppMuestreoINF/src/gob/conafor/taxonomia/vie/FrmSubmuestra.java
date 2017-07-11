package gob.conafor.taxonomia.vie;

import gob.conafor.sitio.mod.CDObservaciones;
import gob.conafor.sitio.mod.CEObservaciones;
import gob.conafor.sitio.mod.CESitio;
import gob.conafor.sys.mod.CDSecuencia;
import gob.conafor.sys.mod.CDSeguimientoUPM;
import gob.conafor.sys.mod.CESeccionesCapturadas;
import gob.conafor.taxonomia.mod.CDSubmuestra;
import gob.conafor.taxonomia.mod.CDSubmuestraObservacion;
import gob.conafor.taxonomia.mod.CESubmuestra;
import gob.conafor.taxonomia.mod.CESubmuestraObservaciones;
import gob.conafor.taxonomia.mod.CETroza;
import gob.conafor.taxonomia.mod.CatETipoTroza;
import gob.conafor.upm.vie.UPMForms;
import gob.conafor.utils.Datos;
import gob.conafor.utils.FuncionesComunes;
import gob.conafor.utils.Tablas;
import gob.conafor.utils.ValidacionesComunes;
import gob.conafor.utils.Version;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class FrmSubmuestra extends javax.swing.JInternalFrame {

    private boolean revision;
    private final int submuestra;
    private final int claveVegectacion;
    private static final int FORMATO_ID = 4;
    private int upmID;
    private int sitioID;
    private int sitio;
    private int submuestraID;
    private int trozaID;
    private Float diametroBasal;
    private Integer edad;
    private Integer numeroAnillos25;
    private Float longitudAnillos10;
    private Float grozorCorteza;
    private CDSubmuestra cdSubmuestra = new CDSubmuestra();
    private CESubmuestra ceSubmuestra = new CESubmuestra();
    private CESitio ceSitio = new CESitio();
    private CESeccionesCapturadas seccionActual = new CESeccionesCapturadas();
    private CESeccionesCapturadas seccionSiguiente = new CESeccionesCapturadas();
    private CDSeguimientoUPM seguimiento = new CDSeguimientoUPM();
    private Datos numeros = new Datos();
    private CDSecuencia cdSecuencia = new CDSecuencia();
    private FuncionesComunes funciones = new FuncionesComunes();
    private int modificar;
    private String observaciones;
    private Version ver = new Version();
    private String version = ver.getVersion();

    public FrmSubmuestra() {
        initComponents();
        this.submuestra = 11;
        this.claveVegectacion = 12;
        fillCmbTipoTroza();
    }

    public void setDatosIniciales(CESitio sitio) {
        this.upmID = sitio.getUpmID();
        this.sitioID = sitio.getSitioID();
        this.sitio = sitio.getSitio();
        ceSitio.setUpmID(this.upmID);
        ceSitio.setSitioID(this.sitioID);
        ceSitio.setSitio(this.sitio);
        ceSitio.setSecuencia(sitio.getSecuencia());
        txtUPM.setText(String.valueOf(this.upmID));
        txtSitio.setText(String.valueOf(this.sitio));
        llenarTablaSubmuestra();
        funciones.manipularBotonesMenuPrincipal(true);
        modificar = 0;
        limpiarControles();
        funciones.reiniciarTabla(grdTrozas);
    }

    public void revisarSubmuestra(CESitio sitio) {
        revision = true;
        this.upmID = sitio.getUpmID();
        this.sitioID = sitio.getSitioID();
        this.sitio = sitio.getSitio();
        ceSitio.setUpmID(this.upmID);
        ceSitio.setSitioID(this.sitioID);
        ceSitio.setSitio(this.sitio);
        ceSitio.setSecuencia(sitio.getSecuencia());
        txtUPM.setText(String.valueOf(this.upmID));
        txtSitio.setText(String.valueOf(this.sitio));
        llenarTablaSubmuestra();
        funciones.manipularBotonesMenuPrincipal(true);
        modificar = 1;
        limpiarControles();
        funciones.reiniciarTabla(grdTrozas);
        CEObservaciones ceObservaciones = new CEObservaciones();
        ceObservaciones.setFormatoID(4);
        ceObservaciones.setSitioID(this.sitioID);
        CDObservaciones observaciones = new CDObservaciones();
        txtObservaciones.setText(observaciones.getObservaciones(ceObservaciones));
    }

    private void llenarTablaSubmuestra() {
        grdSubmuestra.setModel(cdSubmuestra.getTablaSubmuestra(this.sitioID));

        grdSubmuestra.getColumnModel().getColumn(3).setPreferredWidth(70);//Consecutivo
        grdSubmuestra.getColumnModel().getColumn(4).setPreferredWidth(60);//Individuo
        grdSubmuestra.getColumnModel().getColumn(5).setPreferredWidth(70);//Rama
        grdSubmuestra.getColumnModel().getColumn(6).setPreferredWidth(150);//Familia
        grdSubmuestra.getColumnModel().getColumn(7).setPreferredWidth(150);//Genero
        grdSubmuestra.getColumnModel().getColumn(8).setPreferredWidth(150);//Especie
        grdSubmuestra.getColumnModel().getColumn(9).setPreferredWidth(100);//Diametro basal
        grdSubmuestra.getColumnModel().getColumn(10).setPreferredWidth(50);//edad
        grdSubmuestra.getColumnModel().getColumn(11).setPreferredWidth(120);
        grdSubmuestra.getColumnModel().getColumn(12).setPreferredWidth(100);
        grdSubmuestra.getColumnModel().getColumn(13).setPreferredWidth(90);

        Tablas tabla = new Tablas();
        int[] columna_0 = {0};
        int[] columna_1 = {1};
        int[] columna_2 = {2};
        tabla.hideColumnTable(grdSubmuestra, columna_0);
        tabla.hideColumnTable(grdSubmuestra, columna_1);
        tabla.hideColumnTable(grdSubmuestra, columna_2);
    }

    private void llenarTablaTroza() {
        grdTrozas.setModel(cdSubmuestra.getTablaTroza(this.submuestraID));

        grdTrozas.getColumnModel().getColumn(2).setPreferredWidth(50);
        grdTrozas.getColumnModel().getColumn(3).setPreferredWidth(250);

        Tablas tabla = new Tablas();
        int[] columna_0 = {0};
        int[] columna_1 = {1};

        tabla.hideColumnTable(grdTrozas, columna_0);
        tabla.hideColumnTable(grdTrozas, columna_1);
    }

    private void fijarValoresPorCampo(int submuestraID) {
        CESubmuestra submuestra;

        submuestra = cdSubmuestra.getRegistroSubmuestra(submuestraID);
        txtConsecutivo.setText(String.valueOf(submuestra.getConsecutivo()));
        txtIndividuo.setText(String.valueOf(submuestra.getNoIndividuo()));
        txtRamaTallo.setText(String.valueOf(submuestra.getNoRama()));
        txtFamilia.setText(submuestra.getFamilia());
        txtGenero.setText(submuestra.getGenero());
        txtEspecie.setText(submuestra.getEspecie());
        txtDiametroBasal.setText(String.valueOf(submuestra.getDiametroBasal()));
        if ("0.0".equals(txtDiametroBasal.getText())) {
            txtDiametroBasal.setText("");
            txtDiametroBasal.setValue(null);
        }
        txtEdad.setText(String.valueOf(submuestra.getEdad()));
        if ("0".equals(txtEdad.getText())) {
            txtEdad.setText("");
            txtEdad.setValue(null);
        }
        txtNumeroAnillos.setText(String.valueOf(submuestra.getNumeroAnillos25()));
        if ("0".equals(txtNumeroAnillos.getText())) {
            txtNumeroAnillos.setText("");
            txtNumeroAnillos.setValue(null);
        }
        txtLongitud10.setText(String.valueOf(submuestra.getLongitudAnillos10()));
        if ("0.0".equals(txtLongitud10.getText())) {
            txtLongitud10.setText("");
            txtLongitud10.setValue(null);
        }
        txtGrozorCorteza.setText(String.valueOf(submuestra.getGrozorCorteza()));
        if ("0.0".equals(txtGrozorCorteza.getText())) {
            txtGrozorCorteza.setText("");
            txtGrozorCorteza.setValue(null);
        }
    }

    private void fijarDatosTroza(int trozaID) {
        CETroza ceTroza = cdSubmuestra.getRegisroTroza(trozaID);
        CatETipoTroza tipoTroza = new CatETipoTroza();
        Integer numeroTroza = ceTroza.getNumeroTroza();
        tipoTroza.setTrozaID(ceTroza.getTipoTrozaID());
        cmbNumeroTroza.setSelectedItem(numeroTroza);
        cmbTipoTroza.setSelectedItem(tipoTroza);
    }

    private boolean validarCamposObligatorios() {
        if (txtDiametroBasal.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar un diametro basal", "Submuestra", JOptionPane.ERROR_MESSAGE);
            txtDiametroBasal.requestFocus();
            return false;
        } else if (txtEdad.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar la edad", "Submuestra", JOptionPane.ERROR_MESSAGE);
            txtEdad.requestFocus();
            return false;
        } else if (txtNumeroAnillos.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar numero de anillos 25", "Submuestra", JOptionPane.ERROR_MESSAGE);
            txtNumeroAnillos.requestFocus();
            return false;
        } else if (txtLongitud10.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar longitud 10", "Submuestra", JOptionPane.ERROR_MESSAGE);
            txtLongitud10.requestFocus();
            return false;
        } else if (txtGrozorCorteza.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! Debe proporcionar la edad", "Submustra", JOptionPane.ERROR_MESSAGE);
            txtGrozorCorteza.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean validarTroza() {
        CatETipoTroza tipoTroza = (CatETipoTroza) cmbTipoTroza.getSelectedItem();
        Integer numeroTroza = (Integer) cmbNumeroTroza.getSelectedItem();
        if (numeroTroza == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar numero de troza", "Submustra", JOptionPane.ERROR_MESSAGE);
            cmbNumeroTroza.requestFocus();
            return false;
        } else if (tipoTroza == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar un tipo de troza", "Submustra", JOptionPane.ERROR_MESSAGE);
            cmbTipoTroza.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean validarValoresSubmuestra() {
        ValidacionesComunes vc = new ValidacionesComunes();
        if (vc.esMayorCero(this.diametroBasal)) {
            txtDiametroBasal.requestFocus();
            return false;
        } else if (vc.esMayorCero(this.edad)) {
            txtEdad.requestFocus();
            return false;
        } else if (vc.esMayorCero(this.numeroAnillos25)) {
            txtNumeroAnillos.requestFocus();
            return false;
        } else if (vc.esMayorCero(this.longitudAnillos10)) {
            txtLongitud10.requestFocus();
            return false;
        } else if (vc.esMayorCero(this.grozorCorteza)) {
            txtGrozorCorteza.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private void limpiarControles() {
        txtConsecutivo.setText("");
        txtIndividuo.setText("");
        txtRamaTallo.setText("");
        txtFamilia.setText("");
        txtGenero.setText("");
        txtEspecie.setText("");
        txtDiametroBasal.setText("");
        txtDiametroBasal.setValue(null);
        txtEdad.setText("");
        txtEdad.setValue(null);
        txtNumeroAnillos.setText("");
        txtNumeroAnillos.setValue(null);
        txtLongitud10.setText("");
        txtLongitud10.setValue(null);
        txtGrozorCorteza.setText("");
        txtGrozorCorteza.setValue(null);
        txtObservaciones.setText("");
    }

    private void asignarDatosSubmuestra() {
        try {
            this.diametroBasal = Float.valueOf(txtDiametroBasal.getText());
        } catch (NumberFormatException e) {
            this.diametroBasal = null;
        }
        try {
            this.edad = Integer.valueOf(txtEdad.getText());
        } catch (NumberFormatException e) {
            this.edad = null;
        }

        try {
            this.numeroAnillos25 = Integer.valueOf(txtNumeroAnillos.getText());
        } catch (NumberFormatException e) {
            this.numeroAnillos25 = null;
        }

        try {
            this.longitudAnillos10 = Float.valueOf(txtLongitud10.getText());
        } catch (NumberFormatException e) {
            this.longitudAnillos10 = null;
        }

        try {
            this.grozorCorteza = Float.valueOf(txtGrozorCorteza.getText());
        } catch (NumberFormatException e) {
            this.grozorCorteza = null;
        }
    }

    private void crearSubmuestra(int submuestraID) {
        ceSubmuestra.setSubmuestraID(submuestraID);
        ceSubmuestra.setDiametroBasal(this.diametroBasal);
        ceSubmuestra.setEdad(this.edad);
        ceSubmuestra.setNumeroAnillos25(this.numeroAnillos25);
        ceSubmuestra.setLongitudAnillos10(this.longitudAnillos10);
        ceSubmuestra.setGrozorCorteza(this.grozorCorteza);
        cdSubmuestra.updateSubmuestra(ceSubmuestra);
    }

    private void crearObservaciones(int sitioID) {
        CESubmuestraObservaciones ceSubmuestraObs = new CESubmuestraObservaciones();
        ceSubmuestraObs.setSitioID(sitioID);
        ceSubmuestraObs.setObservaciones(txtObservaciones.getText());
        CDSubmuestraObservacion cdSubmuestraObs = new CDSubmuestraObservacion();
        cdSubmuestraObs.insertSubmuestra(ceSubmuestraObs);
    }

    private void actualizarObservaciones(int sitio) {
        CESubmuestraObservaciones ceSubmuestraObs = new CESubmuestraObservaciones();
        ceSubmuestraObs.setSitioID(sitioID);
        ceSubmuestraObs.setObservaciones(txtObservaciones.getText());
        CDSubmuestraObservacion cdSubmuestraObs = new CDSubmuestraObservacion();
        cdSubmuestraObs.updateTroza(ceSubmuestraObs);
    }

    private void crearTroza() {
        CatETipoTroza tipoTroza = (CatETipoTroza) cmbTipoTroza.getSelectedItem();
        Integer numeroTroza = (Integer) cmbNumeroTroza.getSelectedItem();
        CETroza ceTroza = new CETroza();
        ceTroza.setSubmuestraID(this.submuestraID);
        ceTroza.setNumeroTroza(numeroTroza);
        ceTroza.setTipoTrozaID(tipoTroza.getTrozaID());
        this.cdSubmuestra.insertTipoTroza(ceTroza);
    }

    private void actualizarTroza() {
        CatETipoTroza tipoTroza = (CatETipoTroza) cmbTipoTroza.getSelectedItem();
        Integer numeroTroza = (Integer) cmbNumeroTroza.getSelectedItem();
        CETroza ceTroza = new CETroza();
        ceTroza.setTrozaID(this.trozaID);
        //ceTroza.setNumeroTroza(numeroTroza);
        ceTroza.setTipoTrozaID(tipoTroza.getTrozaID());
        this.cdSubmuestra.updateTroza(ceTroza);
    }

    private void fillCmbTipoTroza() {
        List<CatETipoTroza> listTipoTroza = new ArrayList<>();
        listTipoTroza = cdSubmuestra.getTipotroza();
        if (listTipoTroza != null) {
            int size = listTipoTroza.size();
            for (int i = 0; i < size; i++) {
                cmbTipoTroza.addItem(listTipoTroza.get(i));
            }
        }
    }

    private void fillCmbNumeroTroza() {
        List<Integer> listNumeroTroza = new ArrayList<>();
        listNumeroTroza = cdSubmuestra.getNumeroTroza(this.submuestraID);

        if (listNumeroTroza != null) {
            int size = listNumeroTroza.size();
            for (int i = 0; i < size; i++) {
                cmbNumeroTroza.addItem(listNumeroTroza.get(i));
            }
        }
    }

    private void seleccionarSiguienteFormulario(CESitio ceSitio) {
        Integer secuenciaID = ceSitio.getSecuencia();
        Integer sitio = this.funciones.sitioCapturaSueloCarbono(this.upmID, 3);
        if (secuenciaID != null) {
            switch (secuenciaID) {
                case 1: //Módulo A
                    UPMForms.claveVegetacion.setDatosIniciales(ceSitio);
                    UPMForms.claveVegetacion.setVisible(true);
                    break;
                case 2: //Módulos A y C
                    UPMForms.claveVegetacion.setDatosIniciales(ceSitio);
                    UPMForms.claveVegetacion.setVisible(true);
                    break;
                case 3: //Modulos A, C, E y G
                    this.hide();
                    funciones.manipularBotonesMenuPrincipal(false);
                    this.cdSecuencia.insertSecuenciaTerminada(ceSitio);
                    break;
                case 4: //Modulos A y E
                    UPMForms.claveVegetacion.setDatosIniciales(ceSitio);
                    UPMForms.claveVegetacion.setVisible(true);
                    break;
                case 5: //Modulos A, C, D y F
                    UPMForms.fotoHemisferica.setDatosiniciales(ceSitio);
                    UPMForms.fotoHemisferica.setVisible(true);
                    break;
                case 6://Modulos A, C y D
                    /*UPMForms.suelo.setDatosiniciales(ceSitio);
                    UPMForms.suelo.setVisible(true);*/
                    this.hide();
                    funciones.manipularBotonesMenuPrincipal(false);
                    this.cdSecuencia.insertSecuenciaTerminada(ceSitio);
                    break;
                case 7://Modulos A, C, D y E
                    if (sitio == ceSitio.getSitio()) {
                        UPMForms.hojarascaProfundidad.setDatosiniciales(ceSitio);
                        UPMForms.hojarascaProfundidad.setVisible(true);
                    } else {
                        this.cdSecuencia.insertSecuenciaTerminada(ceSitio);
                        this.hide();
                        funciones.manipularBotonesMenuPrincipal(false);
                    }
                    break;
                case 8://Modulos A, C, D, E y F
                    if (sitio == ceSitio.getSitio()) {
                        UPMForms.hojarascaProfundidad.setDatosiniciales(ceSitio);
                        UPMForms.hojarascaProfundidad.setVisible(true);
                    } else {
                        UPMForms.fotoHemisferica.setDatosiniciales(ceSitio);
                        UPMForms.fotoHemisferica.setVisible(true);
                    }
                    break;
                case 9://Modulos A, C y E
                    UPMForms.claveVegetacion.setDatosIniciales(ceSitio);
                    UPMForms.claveVegetacion.setVisible(true);
                    break;
                case 10://Modulos A, C, H
                    UPMForms.claveVegetacion.setDatosIniciales(ceSitio);
                    UPMForms.claveVegetacion.setVisible(true);
                    break;
                case 11://Modulos A y H
                    UPMForms.claveVegetacion.setDatosIniciales(ceSitio);
                    UPMForms.claveVegetacion.setVisible(true);
                    break;
                case 12://Modulos A, E y H
                    UPMForms.claveVegetacion.setDatosIniciales(ceSitio);
                    UPMForms.claveVegetacion.setVisible(true);
                    break;
                case 13://Modulos A, C, E y H
                    UPMForms.claveVegetacion.setDatosIniciales(ceSitio);
                    UPMForms.claveVegetacion.setVisible(true);
                    break;
                case 14://Modulos A, E y G
                    /* UPMForms.carbono.setDatosIniciales(ceSitio);
                    UPMForms.carbono.setVisible(true);*/
                    this.hide();
                    funciones.manipularBotonesMenuPrincipal(false);
                    this.cdSecuencia.insertSecuenciaTerminada(ceSitio);
                    break;
                case 15://A y G
                    /* UPMForms.repobladoVM.setDatosIniciales(ceSitio);
                    UPMForms.repobladoVM.setVisible(true);*/
                    this.hide();
                    funciones.manipularBotonesMenuPrincipal(false);
                    this.cdSecuencia.insertSecuenciaTerminada(ceSitio);
                    break;
                case 16:
                    this.funciones.manipularBotonesMenuPrincipal(false);
                    this.cdSecuencia.insertSecuenciaTerminada(ceSitio);
                    break;
            }
        }
    }

    private void revisarSiguienteFormulario(CESitio ceSitio) {
        Integer secuenciaID = ceSitio.getSecuencia();
        Integer sitio = this.funciones.sitioCapturaSueloCarbono(this.upmID, 3);
        System.out.println("SecuenciaID="+secuenciaID);
        if (secuenciaID != null) {
            switch (secuenciaID) {
                case 1: //Módulo A
                    UPMForms.claveVegetacion.revisarClaveVegetacion(ceSitio);
                    UPMForms.claveVegetacion.setVisible(true);
                    break;
                case 2: //Módulos A y C
                    UPMForms.claveVegetacion.revisarClaveVegetacion(ceSitio);
                    UPMForms.claveVegetacion.setVisible(true);
                    break;
                case 3: //Modulos A, C, E y G
                    this.hide();
                    funciones.manipularBotonesMenuPrincipal(false);
                    break;
                case 4: //Modulos A y E
                    UPMForms.claveVegetacion.revisarClaveVegetacion(ceSitio);
                    UPMForms.claveVegetacion.setVisible(true);
                    break;
                case 5: //Modulos A, C, D y F
                    UPMForms.fotoHemisferica.revisarFotoHemisferica(ceSitio);
                    UPMForms.fotoHemisferica.setVisible(true);
                    break;
                case 6://Modulos A, C y D
                    /*UPMForms.suelo.setDatosiniciales(ceSitio);
                    UPMForms.suelo.setVisible(true);*/
                    this.hide();
                    funciones.manipularBotonesMenuPrincipal(false);
                    break;
                case 7://Modulos A, C, D y E
                    if (sitio == ceSitio.getSitio()) {
                        UPMForms.hojarascaProfundidad.revisarHojarascaProfundidad(ceSitio);
                        UPMForms.hojarascaProfundidad.setVisible(true);
                    } else {
                        this.hide();
                        funciones.manipularBotonesMenuPrincipal(false);
                    }
                    break;
                case 8://Modulos A, C, D, E y F
                    if (sitio == ceSitio.getSitio()) {
                        UPMForms.hojarascaProfundidad.revisarHojarascaProfundidad(ceSitio);
                        UPMForms.hojarascaProfundidad.setVisible(true);
                    } else {
                        UPMForms.fotoHemisferica.revisarFotoHemisferica(ceSitio);
                        UPMForms.fotoHemisferica.setVisible(true);
                    }
                    break;
                case 9://Modulos A, C y E
                    UPMForms.claveVegetacion.revisarClaveVegetacion(ceSitio);
                    UPMForms.claveVegetacion.setVisible(true);
                    break;
                case 10://Modulos A, C, H
                    UPMForms.claveVegetacion.revisarClaveVegetacion(ceSitio);
                    UPMForms.claveVegetacion.setVisible(true);
                    break;
                case 11://Modulos A y H
                    UPMForms.claveVegetacion.revisarClaveVegetacion(ceSitio);
                    UPMForms.claveVegetacion.setVisible(true);
                    break;
                case 12://Modulos A, E y H
                    UPMForms.claveVegetacion.revisarClaveVegetacion(ceSitio);
                    UPMForms.claveVegetacion.setVisible(true);
                    break;
                case 13://Modulos A, C, E y H
                    UPMForms.claveVegetacion.revisarClaveVegetacion(ceSitio);
                    UPMForms.claveVegetacion.setVisible(true);
                    break;
                case 14://Modulos A, E y G
                    /* UPMForms.carbono.setDatosIniciales(ceSitio);
                    UPMForms.carbono.setVisible(true);*/
                    this.hide();
                    funciones.manipularBotonesMenuPrincipal(false);
                    break;
                case 15://A y G
                    /* UPMForms.repobladoVM.setDatosIniciales(ceSitio);
                    UPMForms.repobladoVM.setVisible(true);*/
                    this.hide();
                    funciones.manipularBotonesMenuPrincipal(false);
                    break;
                case 16:
                    this.funciones.manipularBotonesMenuPrincipal(false);
                    break;
            }
        }
    }

    private void manipularControles(boolean habilitar) {
        if (habilitar == true) {
            txtDiametroBasal.setEnabled(true);
            txtEdad.setEnabled(true);
            txtNumeroAnillos.setEnabled(true);
            txtLongitud10.setEnabled(true);
            txtLongitud10.setEditable(true);
            txtGrozorCorteza.setEnabled(true);
            cmbNumeroTroza.setEnabled(true);
            cmbTipoTroza.setEnabled(true);
            btnAgregarTroza.setEnabled(true);
            btnElimnarTroza.setEnabled(true);
            btnModificarTroza.setEnabled(true);
        } else {
            txtDiametroBasal.setEnabled(false);
            txtEdad.setEnabled(false);
            txtNumeroAnillos.setEnabled(false);
            txtLongitud10.setEnabled(false);
            txtLongitud10.setEditable(false);
            txtGrozorCorteza.setEnabled(false);
            cmbNumeroTroza.setEnabled(false);
            cmbTipoTroza.setEnabled(false);
            btnAgregarTroza.setEnabled(false);
            btnElimnarTroza.setEnabled(false);
            btnModificarTroza.setEnabled(false);
        }
    }

    private void crearObservaciones() {
        this.observaciones = txtObservaciones.getText();
        CEObservaciones ceObservaciones = new CEObservaciones();
        ceObservaciones.setSitioID(this.sitioID);
        ceObservaciones.setFormatoID(this.FORMATO_ID);
        ceObservaciones.setObservaciones(observaciones);
        CDObservaciones cdObservaciones = new CDObservaciones();

        cdObservaciones.insertObservaciones(ceObservaciones);
    }

    private void actualizarObservaciones() {
        this.observaciones = txtObservaciones.getText();
        CEObservaciones ceObservaciones = new CEObservaciones();
        ceObservaciones.setSitioID(this.sitioID);
        ceObservaciones.setFormatoID(this.FORMATO_ID);
        ceObservaciones.setObservaciones(observaciones);
        CDObservaciones cdObservaciones = new CDObservaciones();

        cdObservaciones.updateObservaciones(ceObservaciones);
    }

    private boolean validarPresenciaSubmuestra() {
        boolean faltanDatos = false;
        if (this.cdSubmuestra.validarPresenciaSubmuestra(this.sitioID)) {
            if (this.cdSubmuestra.faltaDiametroBasal(this.sitioID)) {
                JOptionPane.showMessageDialog(null, "Faltan datos de diámetro basal");
                return faltanDatos;
            } else if (this.cdSubmuestra.faltaEdad(this.sitioID)) {
                JOptionPane.showMessageDialog(null, "Faltan datos de edad");
                return faltanDatos;
            } else if (this.cdSubmuestra.faltaNumeroAnillos25(this.sitioID)) {
                JOptionPane.showMessageDialog(null, "Faltan datos de número de anillos 25");
                return faltanDatos;
            } else if (this.cdSubmuestra.faltaLongitudAnillos10(this.sitioID)) {
                JOptionPane.showMessageDialog(null, "Faltan datos de número de longitud de anillos 10");
                return faltanDatos;
            } else if (this.cdSubmuestra.faltaGrozorCorteza(this.sitioID)) {
                JOptionPane.showMessageDialog(null, "Faltan datos de grozor corteza");
                return faltanDatos;
            } else {
                faltanDatos = true;
            }
        }
        return faltanDatos;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblUPM = new javax.swing.JLabel();
        txtUPM = new javax.swing.JTextField();
        lblSitio = new javax.swing.JLabel();
        txtSitio = new javax.swing.JTextField();
        lblVegetacionMenorCobertura = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblFamilia = new javax.swing.JLabel();
        txtFamilia = new javax.swing.JTextField();
        lblGenero = new javax.swing.JLabel();
        txtGenero = new javax.swing.JTextField();
        txtEspecie = new javax.swing.JTextField();
        lblConsecutivo = new javax.swing.JLabel();
        lblRamaTallo = new javax.swing.JLabel();
        txtRamaTallo = new javax.swing.JTextField();
        lblEspecie = new javax.swing.JLabel();
        txtConsecutivo = new javax.swing.JTextField();
        txtIndividuo = new javax.swing.JTextField();
        lblIndividuo1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblDiametroBasal = new javax.swing.JLabel();
        lblEdad = new javax.swing.JLabel();
        lblAnillos = new javax.swing.JLabel();
        lblLongitud10 = new javax.swing.JLabel();
        lblGrozorCorteza = new javax.swing.JLabel();
        txtDiametroBasal = new javax.swing.JFormattedTextField();
        txtEdad = new javax.swing.JFormattedTextField();
        txtNumeroAnillos = new javax.swing.JFormattedTextField();
        txtLongitud10 = new javax.swing.JFormattedTextField();
        txtGrozorCorteza = new javax.swing.JFormattedTextField();
        btnContinuar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        grdSubmuestra = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        lblTipoTroza = new javax.swing.JLabel();
        cmbTipoTroza = new javax.swing.JComboBox();
        btnAgregarTroza = new javax.swing.JButton();
        btnModificarTroza = new javax.swing.JButton();
        btnElimnarTroza = new javax.swing.JButton();
        lblNumeroTroza = new javax.swing.JLabel();
        cmbNumeroTroza = new javax.swing.JComboBox();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        grdTrozas = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtObservaciones = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        btnRegresar = new javax.swing.JButton();

        setMaximizable(true);
        setTitle("Submuestra "+version);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/gob/conafor/utils/logo_conafor.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(940, 650));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        lblUPM.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblUPM.setText("UPMID:");

        txtUPM.setEditable(false);
        txtUPM.setEnabled(false);

        lblSitio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblSitio.setText("Sitio:");

        txtSitio.setEditable(false);
        txtSitio.setEnabled(false);

        lblVegetacionMenorCobertura.setBackground(new java.awt.Color(153, 153, 153));
        lblVegetacionMenorCobertura.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblVegetacionMenorCobertura.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVegetacionMenorCobertura.setText("Arbolado de la submuestra");
        lblVegetacionMenorCobertura.setOpaque(true);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblFamilia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFamilia.setText("Familia");
        jPanel2.add(lblFamilia, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 0, 170, -1));

        txtFamilia.setEditable(false);
        txtFamilia.setEnabled(false);
        txtFamilia.setFocusable(false);
        jPanel2.add(txtFamilia, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, 170, -1));

        lblGenero.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGenero.setText("Género");
        jPanel2.add(lblGenero, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 0, 210, -1));

        txtGenero.setEditable(false);
        txtGenero.setEnabled(false);
        txtGenero.setFocusable(false);
        jPanel2.add(txtGenero, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 20, 210, -1));

        txtEspecie.setEditable(false);
        txtEspecie.setEnabled(false);
        txtEspecie.setFocusable(false);
        jPanel2.add(txtEspecie, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 20, 186, -1));

        lblConsecutivo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblConsecutivo.setText("Consecutivo");
        jPanel2.add(lblConsecutivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 80, -1));

        lblRamaTallo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRamaTallo.setText("No. de ramatallo");
        jPanel2.add(lblRamaTallo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 95, -1));

        txtRamaTallo.setEditable(false);
        txtRamaTallo.setEnabled(false);
        txtRamaTallo.setFocusable(false);
        jPanel2.add(txtRamaTallo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, 95, -1));

        lblEspecie.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEspecie.setText("Especie");
        jPanel2.add(lblEspecie, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 0, 186, -1));

        txtConsecutivo.setEditable(false);
        txtConsecutivo.setEnabled(false);
        txtConsecutivo.setFocusable(false);
        jPanel2.add(txtConsecutivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 80, -1));

        txtIndividuo.setEditable(false);
        txtIndividuo.setEnabled(false);
        txtIndividuo.setFocusable(false);
        jPanel2.add(txtIndividuo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 80, -1));

        lblIndividuo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIndividuo1.setText("No. de individuo");
        jPanel2.add(lblIndividuo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 0, 100, -1));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblDiametroBasal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDiametroBasal.setText("Diametro basal");
        jPanel3.add(lblDiametroBasal, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 80, -1));

        lblEdad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEdad.setText("Edad(años)");
        jPanel3.add(lblEdad, new org.netbeans.lib.awtextra.AbsoluteConstraints(103, 11, 70, -1));

        lblAnillos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAnillos.setText("No de anillos en 2.5 cm");
        jPanel3.add(lblAnillos, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 140, -1));

        lblLongitud10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLongitud10.setText("Longitud 10 anillos (mm)");
        jPanel3.add(lblLongitud10, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, 130, -1));

        lblGrozorCorteza.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGrozorCorteza.setText("Grozor de corteza (mm)");
        jPanel3.add(lblGrozorCorteza, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 10, 130, -1));

        txtDiametroBasal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtDiametroBasal.setEnabled(false);
        txtDiametroBasal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDiametroBasalFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDiametroBasalFocusLost(evt);
            }
        });
        txtDiametroBasal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDiametroBasalKeyTyped(evt);
            }
        });
        jPanel3.add(txtDiametroBasal, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 79, -1));

        txtEdad.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtEdad.setEnabled(false);
        txtEdad.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtEdadFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtEdadFocusLost(evt);
            }
        });
        txtEdad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEdadKeyTyped(evt);
            }
        });
        jPanel3.add(txtEdad, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, 78, -1));

        txtNumeroAnillos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat(""))));
        txtNumeroAnillos.setEnabled(false);
        txtNumeroAnillos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNumeroAnillosFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNumeroAnillosFocusLost(evt);
            }
        });
        txtNumeroAnillos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumeroAnillosKeyTyped(evt);
            }
        });
        jPanel3.add(txtNumeroAnillos, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, 142, -1));

        txtLongitud10.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat(""))));
        txtLongitud10.setEnabled(false);
        txtLongitud10.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtLongitud10FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtLongitud10FocusLost(evt);
            }
        });
        txtLongitud10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLongitud10KeyTyped(evt);
            }
        });
        jPanel3.add(txtLongitud10, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 30, 137, -1));

        txtGrozorCorteza.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat(""))));
        txtGrozorCorteza.setEnabled(false);
        txtGrozorCorteza.setNextFocusableComponent(btnAgregar);
        txtGrozorCorteza.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtGrozorCortezaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtGrozorCortezaFocusLost(evt);
            }
        });
        txtGrozorCorteza.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtGrozorCortezaKeyTyped(evt);
            }
        });
        jPanel3.add(txtGrozorCorteza, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 30, 136, -1));

        btnContinuar.setLabel("Continuar");
        btnContinuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinuarActionPerformed(evt);
            }
        });

        btnSalir.setLabel("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnAgregar.setText("Agregar");
        btnAgregar.setActionCommand("");
        btnAgregar.setNextFocusableComponent(btnEliminar);
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Modificar");
        btnEliminar.setActionCommand("");
        btnEliminar.setNextFocusableComponent(cmbNumeroTroza);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        grdSubmuestra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        grdSubmuestra.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        grdSubmuestra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grdSubmuestraMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(grdSubmuestra);

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Trozas"));

        lblTipoTroza.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTipoTroza.setText("Tipo");

        cmbTipoTroza.setEnabled(false);
        cmbTipoTroza.setNextFocusableComponent(btnAgregarTroza);

        btnAgregarTroza.setText("Agregar ");
        btnAgregarTroza.setEnabled(false);
        btnAgregarTroza.setNextFocusableComponent(btnModificarTroza);
        btnAgregarTroza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarTrozaActionPerformed(evt);
            }
        });

        btnModificarTroza.setText("Modificar");
        btnModificarTroza.setEnabled(false);
        btnModificarTroza.setNextFocusableComponent(btnElimnarTroza);
        btnModificarTroza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarTrozaActionPerformed(evt);
            }
        });

        btnElimnarTroza.setText("Eliminar");
        btnElimnarTroza.setEnabled(false);
        btnElimnarTroza.setNextFocusableComponent(btnContinuar);
        btnElimnarTroza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnElimnarTrozaActionPerformed(evt);
            }
        });

        lblNumeroTroza.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNumeroTroza.setText("Numero");

        cmbNumeroTroza.setEnabled(false);
        cmbNumeroTroza.setNextFocusableComponent(cmbTipoTroza);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(cmbTipoTroza, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTipoTroza, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(cmbNumeroTroza, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblNumeroTroza, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnModificarTroza, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnAgregarTroza, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnElimnarTroza, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lblNumeroTroza)
                        .addGap(6, 6, 6)
                        .addComponent(cmbNumeroTroza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTipoTroza)
                        .addGap(6, 6, 6)
                        .addComponent(cmbTipoTroza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnAgregarTroza)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificarTroza)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnElimnarTroza)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));

        grdTrozas.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        grdTrozas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        grdTrozas.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        grdTrozas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grdTrozasMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(grdTrozas);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtObservaciones.setColumns(20);
        txtObservaciones.setRows(5);
        jScrollPane2.setViewportView(txtObservaciones);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setText("Observaciones:");

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
            .addComponent(lblVegetacionMenorCobertura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(lblUPM)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtUPM, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblSitio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSitio, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jScrollPane1))
                                .addGap(13, 13, 13))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(31, 31, 31))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnContinuar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(19, 19, 19))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSitio)
                    .addComponent(txtSitio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUPM)
                    .addComponent(txtUPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblVegetacionMenorCobertura)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAgregar)
                            .addComponent(btnEliminar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnContinuar)
                    .addComponent(btnSalir)
                    .addComponent(btnRegresar))
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
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void grdSubmuestraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grdSubmuestraMouseClicked
        DefaultComboBoxModel dcm = (DefaultComboBoxModel) cmbNumeroTroza.getModel();
        if (evt.getButton() == 1) {
            int fila = grdSubmuestra.getSelectedRow();
            String strSubID = grdSubmuestra.getValueAt(fila, 0).toString();
            this.submuestraID = Integer.parseInt(strSubID);
            fijarValoresPorCampo(this.submuestraID);
            dcm.removeAllElements();
            fillCmbNumeroTroza();
            llenarTablaTroza();
            manipularControles(true);
            txtDiametroBasal.requestFocus();
        }
    }//GEN-LAST:event_grdSubmuestraMouseClicked

    private void txtDiametroBasalFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDiametroBasalFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtDiametroBasal.selectAll();
            }
        });
    }//GEN-LAST:event_txtDiametroBasalFocusGained

    private void txtEdadFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEdadFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtEdad.selectAll();
            }
        });
    }//GEN-LAST:event_txtEdadFocusGained

    private void txtNumeroAnillosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNumeroAnillosFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtNumeroAnillos.selectAll();
            }
        });
    }//GEN-LAST:event_txtNumeroAnillosFocusGained

    private void txtLongitud10FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLongitud10FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtLongitud10.selectAll();
            }
        });
    }//GEN-LAST:event_txtLongitud10FocusGained

    private void txtGrozorCortezaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGrozorCortezaFocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtGrozorCorteza.selectAll();
            }
        });
    }//GEN-LAST:event_txtGrozorCortezaFocusGained

    private void txtDiametroBasalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDiametroBasalFocusLost
        if (txtDiametroBasal.getText().isEmpty()) {
            txtDiametroBasal.setValue(null);
        }
    }//GEN-LAST:event_txtDiametroBasalFocusLost

    private void txtEdadFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEdadFocusLost
        if (txtEdad.getText().isEmpty()) {
            txtEdad.setValue(null);
        }
    }//GEN-LAST:event_txtEdadFocusLost

    private void txtNumeroAnillosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNumeroAnillosFocusLost
        if (txtNumeroAnillos.getText().isEmpty()) {
            txtNumeroAnillos.setValue(null);
        }
    }//GEN-LAST:event_txtNumeroAnillosFocusLost

    private void txtLongitud10FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLongitud10FocusLost
        if (txtLongitud10.getText().isEmpty()) {
            txtLongitud10.setValue(null);
        }
    }//GEN-LAST:event_txtLongitud10FocusLost

    private void txtGrozorCortezaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGrozorCortezaFocusLost
        if (txtGrozorCorteza.getText().isEmpty()) {
            txtLongitud10.setValue(null);
        }
    }//GEN-LAST:event_txtGrozorCortezaFocusLost

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        asignarDatosSubmuestra();
        if (validarCamposObligatorios() && validarValoresSubmuestra()) {
            if (this.submuestraID == 0) {
                JOptionPane.showMessageDialog(null, "Error! Debe seleccionar un registro de la submuestra", "Submustra", JOptionPane.ERROR_MESSAGE);
                grdSubmuestra.requestFocus();
            } else {
                crearSubmuestra(this.submuestraID);
                grdSubmuestra.clearSelection();
                limpiarControles();
                manipularControles(false);
                funciones.reiniciarTabla(grdTrozas);
                llenarTablaSubmuestra();
            }
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        asignarDatosSubmuestra();
        if (validarCamposObligatorios() && validarValoresSubmuestra()) {
            if (this.submuestraID == 0) {
                JOptionPane.showMessageDialog(null, "Error! Debe seleccionar un registro de la submuestra", "Submustra", JOptionPane.ERROR_MESSAGE);
                grdSubmuestra.requestFocus();
            } else {
                crearSubmuestra(this.submuestraID);
                limpiarControles();
                llenarTablaSubmuestra();
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void grdTrozasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grdTrozasMouseClicked
        if (evt.getButton() == 1) {
            int fila = grdTrozas.getSelectedRow();
            String strSubID = grdTrozas.getValueAt(fila, 0).toString();
            this.trozaID = Integer.parseInt(strSubID);
            fijarDatosTroza(this.trozaID);
        }
    }//GEN-LAST:event_grdTrozasMouseClicked

    private void btnAgregarTrozaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarTrozaActionPerformed
        if (this.submuestraID == 0) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar un registro de submuestra", "Submustra", JOptionPane.ERROR_MESSAGE);
            cmbTipoTroza.requestFocus();
        } else if (validarTroza()) {
            crearTroza();
            llenarTablaTroza();
            cmbNumeroTroza.setSelectedItem(null);
            cmbTipoTroza.setSelectedItem(null);
            cmbNumeroTroza.requestFocus();
            DefaultComboBoxModel dcm = (DefaultComboBoxModel) cmbNumeroTroza.getModel();
            dcm.removeAllElements();
            fillCmbNumeroTroza();
        }
    }//GEN-LAST:event_btnAgregarTrozaActionPerformed

    private void btnModificarTrozaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarTrozaActionPerformed
        if (this.trozaID == 0) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar un registro de submuestra", "Submustra", JOptionPane.ERROR_MESSAGE);
            cmbTipoTroza.requestFocus();
        } else {
            actualizarTroza();
            llenarTablaTroza();
            cmbNumeroTroza.setSelectedItem(null);
            cmbTipoTroza.setSelectedItem(null);
            cmbNumeroTroza.requestFocus();
            DefaultComboBoxModel dcm = (DefaultComboBoxModel) cmbNumeroTroza.getModel();
            dcm.removeAllElements();
            fillCmbNumeroTroza();
        }
    }//GEN-LAST:event_btnModificarTrozaActionPerformed

    private void btnElimnarTrozaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnElimnarTrozaActionPerformed
        if (this.trozaID == 0) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar un registro de submuestra", "Submustra", JOptionPane.ERROR_MESSAGE);
            cmbTipoTroza.requestFocus();
        } else {
            cdSubmuestra.deleteRegistroTroza(this.trozaID);
            llenarTablaTroza();
            cmbNumeroTroza.setSelectedItem(null);
            cmbTipoTroza.setSelectedItem(null);
            cmbNumeroTroza.requestFocus();
            DefaultComboBoxModel dcm = (DefaultComboBoxModel) cmbNumeroTroza.getModel();
            dcm.removeAllElements();
            fillCmbNumeroTroza();
        }
    }//GEN-LAST:event_btnElimnarTrozaActionPerformed

    private void btnContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuarActionPerformed
        if (modificar == 0) {
            crearObservaciones();
            this.cdSecuencia.updateSecuencia(this.ceSitio, FORMATO_ID, 1);
            this.hide();
            seleccionarSiguienteFormulario(this.ceSitio);

        } else {
            actualizarObservaciones();
            this.hide();
            revisarSiguienteFormulario(this.ceSitio);
        }
    }//GEN-LAST:event_btnContinuarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        if (revision == false) {//esta en modo de captura
            this.hide();
            funciones.manipularBotonesMenuPrincipal(false);
        }
        if (revision == true) {//entro a modo de revision
            //System.err.println("Modo Revision");
            this.hide();
            //UPMForms.revisionModulos.iniciarRevision();
            UPMForms.revisionModulos.setVisible(true);
            UPMForms.revisionModulos.manipularBonesMenuprincipal();
            revision = false;
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtDiametroBasalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiametroBasalKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtDiametroBasalKeyTyped

    private void txtEdadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEdadKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtEdadKeyTyped

    private void txtNumeroAnillosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroAnillosKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtNumeroAnillosKeyTyped

    private void txtLongitud10KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLongitud10KeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtLongitud10KeyTyped

    private void txtGrozorCortezaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGrozorCortezaKeyTyped
        numeros.keyTyped(evt);
    }//GEN-LAST:event_txtGrozorCortezaKeyTyped

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        Integer secuenciaID = ceSitio.getSecuencia();
        if (this.modificar == 0) {
            if (secuenciaID != null) {
                switch (secuenciaID) {
                    case 1: //Módulo A
                        this.hide();
                        UPMForms.arbolado.setDatosIniciales(ceSitio);
                        UPMForms.arbolado.setVisible(true);
                        break;
                    case 2: //Módulos A y C
                        this.hide();
                        UPMForms.arbolado.setDatosIniciales(ceSitio);
                        UPMForms.arbolado.setVisible(true);
                        break;
                    case 3: //Modulos A, C, E y G
                        this.hide();
                        UPMForms.arboladoG.setDatosIniciales(ceSitio);
                        UPMForms.arboladoG.setVisible(true);
                        break;
                    case 4: //Modulos A y E
                        this.hide();
                        UPMForms.arbolado.setDatosIniciales(ceSitio);
                        UPMForms.arbolado.setVisible(true);
                        break;
                    case 5: //Modulos A, C, D y F
                        this.hide();
                        UPMForms.arboladoD.setDatosIniciales(ceSitio);
                        UPMForms.arboladoD.setVisible(true);
                        break;
                    case 6://Modulos A, C y D
                        this.hide();
                        UPMForms.arboladoD.setDatosIniciales(ceSitio);
                        UPMForms.arboladoD.setVisible(true);
                        break;
                    case 7://Modulos A, C, D y E
                        this.hide();
                        UPMForms.arboladoD.setDatosIniciales(ceSitio);
                        UPMForms.arboladoD.setVisible(true);
                        break;
                    case 8://Modulos A, C, D, E y F
                        this.hide();
                        UPMForms.arboladoD.setDatosIniciales(ceSitio);
                        UPMForms.arboladoD.setVisible(true);
                        break;
                    case 9://Modulos A, C y E
                        this.hide();
                        UPMForms.arbolado.setDatosIniciales(ceSitio);
                        UPMForms.arbolado.setVisible(true);
                        break;
                    case 10://Modulos A, C, H
                        this.hide();
                        UPMForms.arbolado.setDatosIniciales(ceSitio);
                        UPMForms.arbolado.setVisible(true);
                        break;
                    case 11://Modulos A y H
                        this.hide();
                        UPMForms.arbolado.setDatosIniciales(ceSitio);
                        UPMForms.arbolado.setVisible(true);
                        break;
                    case 12://Modulos A, E y H
                        this.hide();
                        UPMForms.arbolado.setDatosIniciales(ceSitio);
                        UPMForms.arbolado.setVisible(true);
                        break;
                    case 13://Modulos A, C, E y H
                        this.hide();
                        UPMForms.arbolado.setDatosIniciales(ceSitio);
                        UPMForms.arbolado.setVisible(true);
                        break;
                    case 14://Modulos A, E y G
                        this.hide();
                        UPMForms.arboladoG.setDatosIniciales(ceSitio);
                        UPMForms.arboladoG.setVisible(true);
                        break;
                    case 15://A y G
                        this.hide();
                        UPMForms.arboladoG.setDatosIniciales(ceSitio);
                        UPMForms.arboladoG.setVisible(true);
                        break;
                    case 16: //A,C y G
                        this.funciones.manipularBotonesMenuPrincipal(false);
                        this.cdSecuencia.insertSecuenciaTerminada(ceSitio);
                        break;

                }
            }
        } else if (secuenciaID != null) {
            switch (secuenciaID) {
                case 1: //Módulo A
                    this.hide();
                    UPMForms.arbolado.revisarArbolado(ceSitio);
                    UPMForms.arbolado.setVisible(true);
                    break;
                case 2: //Módulos A y C
                    this.hide();
                    UPMForms.arbolado.revisarArbolado(ceSitio);
                    UPMForms.arbolado.setVisible(true);
                    break;
                case 3: //Modulos A, C, E y G
                    this.hide();
                    UPMForms.arboladoG.revisarArboladoG(ceSitio);
                    UPMForms.arboladoG.setVisible(true);
                    break;
                case 4: //Modulos A y E
                    this.hide();
                    UPMForms.arbolado.revisarArbolado(ceSitio);
                    UPMForms.arbolado.setVisible(true);
                    break;
                case 5: //Modulos A, C, D y F
                    this.hide();
                    UPMForms.arboladoD.revisarArboladoD(ceSitio);
                    UPMForms.arboladoD.setVisible(true);
                    break;
                case 6://Modulos A, C y D
                    this.hide();
                    UPMForms.arboladoD.revisarArboladoD(ceSitio);
                    UPMForms.arboladoD.setVisible(true);
                    break;
                case 7://Modulos A, C, D y E
                    this.hide();
                    UPMForms.arboladoD.revisarArboladoD(ceSitio);
                    UPMForms.arboladoD.setVisible(true);
                    break;
                case 8://Modulos A, C, D, E y F
                    this.hide();
                    UPMForms.arboladoD.revisarArboladoD(ceSitio);
                    UPMForms.arboladoD.setVisible(true);
                    break;
                case 9://Modulos A, C y E
                    this.hide();
                    UPMForms.arbolado.revisarArbolado(ceSitio);
                    UPMForms.arbolado.setVisible(true);
                    break;
                case 10://Modulos A, C, H
                    this.hide();
                    UPMForms.arbolado.revisarArbolado(ceSitio);
                    UPMForms.arbolado.setVisible(true);
                    break;
                case 11://Modulos A y H
                    this.hide();
                    UPMForms.arbolado.revisarArbolado(ceSitio);
                    UPMForms.arbolado.setVisible(true);
                    break;
                case 12://Modulos A, E y H
                    this.hide();
                    UPMForms.arbolado.revisarArbolado(ceSitio);
                    UPMForms.arbolado.setVisible(true);
                    break;
                case 13://Modulos A, C, E y H
                    this.hide();
                    UPMForms.arbolado.revisarArbolado(ceSitio);
                    UPMForms.arbolado.setVisible(true);
                    break;
                case 14://Modulos A, E y G
                    this.hide();
                    UPMForms.arboladoG.revisarArboladoG(ceSitio);
                    UPMForms.arboladoG.setVisible(true);
                    break;
                case 15://A y G
                    this.hide();
                    UPMForms.arboladoG.revisarArboladoG(ceSitio);
                    UPMForms.arboladoG.setVisible(true);
                    break;
                case 16: //A,C y G
                    this.funciones.manipularBotonesMenuPrincipal(false);
                    this.cdSecuencia.insertSecuenciaTerminada(ceSitio);
                    break;
            }
        }
    }//GEN-LAST:event_btnRegresarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAgregarTroza;
    private javax.swing.JButton btnContinuar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnElimnarTroza;
    private javax.swing.JButton btnModificarTroza;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cmbNumeroTroza;
    private javax.swing.JComboBox cmbTipoTroza;
    private javax.swing.JTable grdSubmuestra;
    private javax.swing.JTable grdTrozas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblAnillos;
    private javax.swing.JLabel lblConsecutivo;
    private javax.swing.JLabel lblDiametroBasal;
    private javax.swing.JLabel lblEdad;
    private javax.swing.JLabel lblEspecie;
    private javax.swing.JLabel lblFamilia;
    private javax.swing.JLabel lblGenero;
    private javax.swing.JLabel lblGrozorCorteza;
    private javax.swing.JLabel lblIndividuo1;
    private javax.swing.JLabel lblLongitud10;
    private javax.swing.JLabel lblNumeroTroza;
    private javax.swing.JLabel lblRamaTallo;
    private javax.swing.JLabel lblSitio;
    private javax.swing.JLabel lblTipoTroza;
    private javax.swing.JLabel lblUPM;
    private javax.swing.JLabel lblVegetacionMenorCobertura;
    private javax.swing.JTextField txtConsecutivo;
    private javax.swing.JFormattedTextField txtDiametroBasal;
    private javax.swing.JFormattedTextField txtEdad;
    private javax.swing.JTextField txtEspecie;
    private javax.swing.JTextField txtFamilia;
    private javax.swing.JTextField txtGenero;
    private javax.swing.JFormattedTextField txtGrozorCorteza;
    private javax.swing.JTextField txtIndividuo;
    private javax.swing.JFormattedTextField txtLongitud10;
    private javax.swing.JFormattedTextField txtNumeroAnillos;
    private javax.swing.JTextArea txtObservaciones;
    private javax.swing.JTextField txtRamaTallo;
    private javax.swing.JTextField txtSitio;
    private javax.swing.JTextField txtUPM;
    // End of variables declaration//GEN-END:variables
}
