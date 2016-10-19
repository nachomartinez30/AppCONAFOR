package gob.conafor.upm.vie;

import gob.conafor.upm.mod.CDBrigada;
import gob.conafor.upm.mod.CEBrigadista;
import gob.conafor.upm.mod.CDUpm;
import gob.conafor.upm.mod.CatEMalla;
import gob.conafor.upm.mod.CatETipoUPM;
import gob.conafor.upm.mod.CDContacto;
import gob.conafor.upm.mod.CEBrigada;
import gob.conafor.upm.mod.CEContacto;
import gob.conafor.upm.mod.CEUPM;
import gob.conafor.upm.mod.CatETipoTenencia;
import gob.conafor.utils.FuncionesComunes;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.swing.plaf.basic.BasicInternalFrameUI;


public class FrmInformacionGeneral extends JInternalFrame {

    private int upm;
    private final int informacionGeneral;
    private int contacto;
    private int tipoUpm;
    private final CDUpm datosUpm = new CDUpm();
    private final Font letraResaltada = new Font("Arial", Font.BOLD, 12);
    private final Font letraNormal = new Font("Tahoma", Font.PLAIN, 11);
    private final CEUPM ceUpm = new CEUPM();
    private int modificar;
    private CatETipoUPM ceTipoUpm = new CatETipoUPM();
    private FuncionesComunes combo = new FuncionesComunes();
    private CDBrigada cdBrigada = new CDBrigada();
    private CEBrigada ceBrigada = new CEBrigada();
    
    public FrmInformacionGeneral() {
        initComponents();
       /* this.combo.reiniciarComboModel(this.cmbUPM);
        fillCmbUPM();*/
        fillCmbTipoUPM();
        fillCmbTenencia();
        fillCmbJefeBrigada();
        fillCmbAuxiliar1();
        fillCmbAuxiliar2();
        //deshabiliatarControles();
        this.informacionGeneral = 1;
        this.contacto = 2;
        //this.modificar = 0;
       /* BasicInternalFrameTitlePane titlePane =(BasicInternalFrameTitlePane)((BasicInternalFrameUI)this.getUI()).getNorthPane();
        this.remove(titlePane);*/
    }
    
    public void iniciarCaptura(){
        this.modificar = 0;
        this.combo.reiniciarComboModel(this.cmbUPM);
        fillCmbUPM();
        this.cmbUPM.setEnabled(true);
        this.cmbUPM.setSelectedItem(null);
        reiniciarControles();
        combo.manipularBotonesMenuPrincipal(true);
        //deshabiliatarControles()
    }
    
    public void revisarUPM(int upmID) {
        this.modificar = 1;
        setInformacionGeneral(upmID);
        this.combo.reiniciarComboModel(this.cmbUPM);
        fillCmbUPMCapturados();
        Integer upmid = upmID;
        CEUPM ceUPM = this.datosUpm.getInformacionGeneral(upmID);//Traer datos del upm seleccionado
        //Llenar los controles con los datos correspondientes al UPM
        this.cmbUPM.setSelectedItem(upmid);
        cmbUPM.setEnabled(false);
        this.ceTipoUpm.setTipoUPMID(ceUPM.getTipoUpmID());
        this.tipoUpm = ceUPM.getTipoUpmID();
        cmbTipoUPM.setSelectedItem(this.ceTipoUpm);
        cmbTipoUPM.setEnabled(true);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fecInicio = ceUPM.getFechaInicio();
        String fecFin = ceUPM.getFechaFin();
        Date fecIn = null;
        Date fecFi = null;
        try {
            fecIn = sdf.parse(fecInicio);
            fecFi = sdf.parse(fecFin);
            dpFechaInicio.setDate(fecIn);
            dpFechaFin.setDate(fecFi);
        } catch (Exception e) {
            System.out.println(e);
        }
        dpFechaInicio.setEnabled(true);
        dpFechaFin.setEnabled(true);
        txtPredio.setText(ceUPM.getPredio());
        txtPredio.setEnabled(true);
        txtParaje.setText(ceUPM.getParaje());
        txtParaje.setEnabled(true);
        CatETipoTenencia ceTipoTenencia = new CatETipoTenencia();
        ceTipoTenencia.setTipoTenenciaID(ceUPM.getTenenciaID());
        cmbTenencia.setSelectedItem(ceTipoTenencia);
        cmbTenencia.setEnabled(true);
        int contacto = ceUPM.getInformacionContacto();
        //Llenar los controles correspondientes al contacto si lo hubo
        chkInformacionContacto.setEnabled(true);
        if (contacto == 1) {
            chkInformacionContacto.setSelected(true);
            CDContacto cdContacto = new CDContacto();
            CEContacto ceContacto = cdContacto.getDatosContacto(upmID);
            Integer tipoContacto = ceContacto.getTipoContacto();
            Integer tipoTelefono = ceContacto.getTipoTelefono();
            Integer tieneCorreElectronico = ceContacto.getTieneCorreoElectronico();
            Integer tieneRadio = ceContacto.getTieneRadio();
            if (tipoContacto == 1) {
                rbtPresencial.setSelected(true);
                rbtRemoto.setSelected(false);
            } else {
                rbtPresencial.setSelected(false);
                rbtRemoto.setSelected(true);
            }
            if (tipoTelefono != null) {
                if (tipoTelefono == 1) {
                    rbtTelefonoFijo.setSelected(true);
                    rbtTelefonoMovil.setSelected(false);
                } else {
                    rbtTelefonoFijo.setSelected(false);
                    rbtTelefonoMovil.setSelected(true);
                }
            }
            txtNombreContacto.setText(ceContacto.getNombre());
            String direccion = ceContacto.getDireccion();
            if (direccion != null) {
                txtDireccionContacto.setText(direccion);
            }
            String telefono = ceContacto.getNumeroTelefono();
            if (telefono != null) {
                txtNumeroTelefonico.setText(telefono);
            }
            if (tieneCorreElectronico == 1) {
                chkCorreoElectronico.setSelected(true);
                txtCorreoElectronico.setText(ceContacto.getCorreoElectronico());
            }
            if (tieneRadio == 1) {
                chkRadio.setSelected(true);
                txtCanal.setText(ceContacto.getCanal());
                txtFrecuencia.setText(ceContacto.getFrecuencia());
            }
            txtObservaciones.setText(ceContacto.getObservaciones());
        } else {
            chkInformacionContacto.setSelected(false);
        }
        seleccionarBrigadaUPM(upmID);
        cmbJefeBrigada.setEnabled(true);
        cmbAuxiliar1.setEnabled(true);
        cmbAuxiliar2.setEnabled(true);
        combo.manipularBotonesMenuPrincipal(true);
    }
    
/*    public void revisarUPM(){
        this.combo.reiniciarComboModel(this.cmbUPM);
        fillCmbUPM();
        this.cmbUPM.setSelectedItem(null);
        fillCmbTipoUPM(); 
        fillCmbTenencia();
        fillCmbJefeBrigada();
        fillCmbAuxiliar1();
        fillCmbAuxiliar2();
        //deshabiliatarControles()
        this.modificar = 1;
    }*/

    private void fillCmbUPM() {
        List<Integer> listUPMID = new ArrayList();
        listUPMID = this.datosUpm.getUPMID();
        if (listUPMID != null) {
            int size = listUPMID.size();
            for (int i = 0; i < size; i++) {
                cmbUPM.addItem(listUPMID.get(i));
            }
        }
    }
    
    private void fillCmbUPMCapturados(){
        List<Integer> listUPMID = new ArrayList<>();
        listUPMID = this.datosUpm.getUPMCapturados();
        if (listUPMID != null) {
            int size = listUPMID.size();
            for (int i = 0; i < size; i++) {
                cmbUPM.addItem(listUPMID.get(i));
            }
        }
    }
   
    private void fillCmbTipoUPM() {
        List<CatETipoUPM> listTipoUPM = new ArrayList<>();
        listTipoUPM = this.datosUpm.getTiposUPM();
        if (listTipoUPM != null) {
            int size = listTipoUPM.size();
            for (int i = 0; i < size; i++) {
                cmbTipoUPM.addItem(listTipoUPM.get(i));
            }
        }
    }

    private void fillCmbTenencia() {
        List<CatETipoTenencia> listTenencia = new ArrayList<>();
        listTenencia = datosUpm.getTiposTenencia();
        if (listTenencia != null) {
            int size = listTenencia.size();
            for (int i = 0; i < size; i++) {
                cmbTenencia.addItem(listTenencia.get(i));
            }
        }
    }
    
    private void fillCmbJefeBrigada() {
        List<CEBrigadista> listBrigadistas = new ArrayList<>();
        listBrigadistas = cdBrigada.getJefeBrigada();
        if(listBrigadistas != null){
            int size = listBrigadistas.size();
            for(int i = 0; i < size; i++){
                cmbJefeBrigada.addItem(listBrigadistas.get(i));
            }
        }
    }
    
    private void fillCmbAuxiliar1() {
        List<CEBrigadista> listBrigadistas = new ArrayList<>();
        listBrigadistas = cdBrigada.getBrigadistas();
        if (listBrigadistas != null) {
            int size = listBrigadistas.size();
            for (int i = 0; i < size; i++) {
                cmbAuxiliar1.addItem(listBrigadistas.get(i));
            }
        }
    }
    
    private void fillCmbAuxiliar2(){
        List<CEBrigadista> listBrigadistas = new ArrayList<>();
        listBrigadistas = cdBrigada.getBrigadistas();
        if (listBrigadistas != null) {
            int size = listBrigadistas.size();
            for (int i = 0; i < size; i++) {
                cmbAuxiliar2.addItem(listBrigadistas.get(i));
            }
        }
    }
    
     public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("gob/conafor/utils/logo_conafor.png"));
        return retValue;
    }

    public void setInformacionGeneral(int upm) {
        //this.modificar = 0;
        this.upm = upm;
        byte A, B, C, D, E, F, G, H;
        List<CatEMalla> malla = datosUpm.getMalla(upm);
        CatEMalla datosMalla = malla.get(0);
        A = datosMalla.getA();
        B = datosMalla.getB();
        C = datosMalla.getC();
        D = datosMalla.getD();
        E = datosMalla.getE();
        F = datosMalla.getF();
        G = datosMalla.getG();
        H = datosMalla.getH();
        txtEstado.setText(datosMalla.getEstado());
        txtMunicipio.setText(datosMalla.getMunicipio());
        txtProyecto.setText(datosMalla.getProyecto());
        //Esta sección permite cambiar los módulos que se levantarán, de manera visual en este formulario.
        if (A == 1) {
            lblTModuloA.setFont(letraResaltada);
            lblTModuloA.setOpaque(true);
            lblTModuloA.setBackground(Color.GREEN);
            LblModuloA.setFont(letraResaltada);
        } else {
            lblTModuloA.setFont(letraNormal);
            LblModuloA.setFont(letraNormal);
            lblTModuloA.setOpaque(false);
        }
        if (C == 1) {
            lblTModuloC.setFont(letraResaltada);
            lblTModuloC.setOpaque(true);
            lblTModuloC.setBackground(Color.GREEN);
            LblModuloC.setFont(letraResaltada);
        } else {
            lblTModuloC.setFont(letraNormal);
            LblModuloC.setFont(letraNormal);
            lblTModuloC.setOpaque(false);
        }
        if (D == 1) {
            lblTModuloD.setFont(letraResaltada);
            lblTModuloD.setOpaque(true);
            lblTModuloD.setBackground(Color.GREEN);
            LblModuloD.setFont(letraResaltada);
        } else {
            lblTModuloD.setFont(letraNormal);
            LblModuloD.setFont(letraNormal);
            lblTModuloD.setOpaque(false);
        }
        if (E == 1) {
            lblTModuloE.setFont(letraResaltada);
            lblTModuloE.setOpaque(true);
            lblTModuloE.setBackground(Color.GREEN);
            LblModuloE.setFont(letraResaltada);
        } else {
            lblTModuloE.setFont(letraNormal);
            LblModuloE.setFont(letraNormal);
            lblTModuloE.setOpaque(false);
        }
        if (F == 1) {
            lblTModuloF.setFont(letraResaltada);
            lblTModuloF.setOpaque(true);
            lblTModuloF.setBackground(Color.GREEN);
            LblModuloF.setFont(letraResaltada);
        } else {
            lblTModuloF.setFont(letraNormal);
            LblModuloF.setFont(letraNormal);
            lblTModuloF.setOpaque(false);
        }
        if (G == 1) {
            lblTModuloG.setFont(letraResaltada);
            lblTModuloG.setOpaque(true);
            lblTModuloG.setBackground(Color.GREEN);
            LblModuloG.setFont(letraResaltada);
        } else {
            lblTModuloG.setFont(letraNormal);
            LblModuloG.setFont(letraNormal);
            lblTModuloG.setOpaque(false);
        }
        if (H == 1) {
            lblTModuloH.setFont(letraResaltada);
            lblTModuloH.setOpaque(true);
            lblTModuloH.setBackground(Color.GREEN);
            LblModuloH.setFont(letraResaltada);
        } else {
            lblTModuloH.setFont(letraNormal);
            LblModuloH.setFont(letraNormal);
            lblTModuloH.setOpaque(false);
        }  
    }
    
    public void iniciarUPM(){
        this.combo.reiniciarComboModel(cmbUPM);
        fillCmbUPM();
        cmbUPM.setSelectedItem(null);
        this.combo.manipularBotonesMenuPrincipal(true);
    }
    
    private void seleccionarBrigadaUPM(int upmID) {
        List<CEBrigadista> listBrigada = new ArrayList<>();
        listBrigada = cdBrigada.getBrigada(upmID);
        if (listBrigada != null) {
            int size = listBrigada.size();
            for (int i = 0; i < size; i++) {
                CEBrigadista ceBrigada = listBrigada.get(i);
                switch (ceBrigada.getPuestoID()) {
                    case 1:
                        cmbJefeBrigada.setSelectedItem(ceBrigada);
                        break;
                    case 2:
                        cmbAuxiliar1.setSelectedItem(ceBrigada);
                        break;
                    case 3:
                        cmbAuxiliar2.setSelectedItem(ceBrigada);
                        break;
                }
            }
        }
    }
    
    public void manipularBotonesMenu(){
        combo.manipularBotonesMenuPrincipal(true);
    }
    
    private boolean validarBrigadaObligatoria() {
        if (cmbJefeBrigada.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar al jefe de brigada", "Información UPM", JOptionPane.INFORMATION_MESSAGE);
            cmbJefeBrigada.requestFocus();
            return false;
        } else if (cmbAuxiliar1.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar al auxiliar 1", "Información UPM", JOptionPane.INFORMATION_MESSAGE);
            cmbAuxiliar1.requestFocus();
            return false;
        } else if (cmbAuxiliar2.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar al auxiliar 2", "Información UPM", JOptionPane.INFORMATION_MESSAGE);
            cmbAuxiliar2.requestFocus();
            return false;
        } else {
            return true;
        }
    }
    
    private boolean validarBrigadistaDiferente() {
        CEBrigadista ceAuxiliar1 = (CEBrigadista) cmbAuxiliar1.getSelectedItem();
        CEBrigadista ceAuxiliar2 = (CEBrigadista) cmbAuxiliar2.getSelectedItem();
        if(Objects.equals(ceAuxiliar1.getBrigadistaID(), ceAuxiliar2.getBrigadistaID())){
            JOptionPane.showMessageDialog(null, "Error! Está repitiendo al auxiliar de brigada, verifique", "Información UPM", JOptionPane.INFORMATION_MESSAGE);
            cmbAuxiliar1.requestFocus();
            return false;
        } else {
            return true;
        }
        
    }
    
    private void crearBrigada() {
        CEBrigadista jefeBrigada = (CEBrigadista) cmbJefeBrigada.getSelectedItem();
        CEBrigadista auxiliar1 = (CEBrigadista) cmbAuxiliar1.getSelectedItem();
        CEBrigadista auxiliar2 = (CEBrigadista) cmbAuxiliar2.getSelectedItem();
        this.ceBrigada.setBrigadistaID(jefeBrigada.getBrigadistaID());
        this.ceBrigada.setPuestoID(1);
        this.ceBrigada.setUpmID(this.upm);
        this.cdBrigada.insertBrigada(this.ceBrigada);
        this.ceBrigada.setBrigadistaID(auxiliar1.getBrigadistaID());
        this.ceBrigada.setPuestoID(2);
        this.ceBrigada.setUpmID(this.upm);
        this.cdBrigada.insertBrigada(this.ceBrigada);
        this.ceBrigada.setBrigadistaID(auxiliar2.getBrigadistaID());
        this.ceBrigada.setPuestoID(3);
        this.ceBrigada.setUpmID(this.upm);
        this.cdBrigada.insertBrigada(this.ceBrigada);
    }
    
    private void modificarBrigada() {
        CEBrigadista jefeBrigada = (CEBrigadista) cmbJefeBrigada.getSelectedItem();
        CEBrigadista auxiliar1 = (CEBrigadista) cmbAuxiliar1.getSelectedItem();
        CEBrigadista auxiliar2 = (CEBrigadista) cmbAuxiliar2.getSelectedItem();
        this.ceBrigada.setBrigadistaID(jefeBrigada.getBrigadistaID());
        this.ceBrigada.setPuestoID(1);
        this.ceBrigada.setUpmID(this.upm);
        this.cdBrigada.insertBrigada(this.ceBrigada);
        this.ceBrigada.setBrigadistaID(auxiliar1.getBrigadistaID());
        this.ceBrigada.setPuestoID(2);
        this.ceBrigada.setUpmID(this.upm);
        this.cdBrigada.insertBrigada(this.ceBrigada);
        this.ceBrigada.setBrigadistaID(auxiliar2.getBrigadistaID());
        this.ceBrigada.setPuestoID(3);
        this.ceBrigada.setUpmID(this.upm);
        this.cdBrigada.updateBrigada(this.ceBrigada);
    }

    private void crearUPM() {
        CatETipoTenencia tenencia = (CatETipoTenencia) cmbTenencia.getSelectedItem();
        CatETipoUPM tipoUpmID = (CatETipoUPM) cmbTipoUPM.getSelectedItem();
        Integer accesible = 1;
        Integer inaccesible = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fecIni = null;
        String fecFin = null;
        if (dpFechaInicio.getDate() != null) {
            fecIni = sdf.format(dpFechaInicio.getDate());
        }
        if (dpFechaFin.getDate() != null) {
            fecFin = sdf.format(dpFechaFin.getDate());
        }
        this.ceUpm.setUpmID(this.upm);
        this.ceUpm.setFechaInicio(fecIni);
        this.ceUpm.setFechaFin(fecFin);
        this.ceUpm.setPredio(txtPredio.getText());
        this.ceUpm.setParaje(txtParaje.getText());
        this.ceUpm.setTenenciaID(tenencia.getTipoTenenciaID());
        this.ceUpm.setTipoUpmID(this.tipoUpm);
         if (tipoUpmID.getTipoUPMID() > 2 && tipoUpmID.getTipoUPMID() < 6) {
            this.ceUpm.setAccesible(inaccesible);
        } else {
            this.ceUpm.setAccesible(accesible);
        }
        if (chkInformacionContacto.isSelected()) {
            this.ceUpm.setInformacionContacto(accesible);
        }
        datosUpm.insertDatosGeneralesUPM(this.ceUpm);
    }

    private void modificarUPM() {
        CatETipoTenencia tenencia = (CatETipoTenencia) cmbTenencia.getSelectedItem();
        CatETipoUPM tipoUpmID = (CatETipoUPM) cmbTipoUPM.getSelectedItem();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fecIni = null;
        String fecFin = null;
        Integer accesible = 1;
        Integer inaccesible = 0;
        if (dpFechaInicio.getDate() != null) {
            fecIni = sdf.format(dpFechaInicio.getDate());
        }
        if (dpFechaFin.getDate() != null) {
            fecFin = sdf.format(dpFechaFin.getDate());
        }
        this.ceUpm.setUpmID(this.upm);
        this.ceUpm.setFechaInicio(fecIni);
        this.ceUpm.setFechaFin(fecFin);
        this.ceUpm.setPredio(txtPredio.getText());
        this.ceUpm.setParaje(txtParaje.getText());
        this.ceUpm.setTenenciaID(tenencia.getTipoTenenciaID());
        this.ceUpm.setTipoUpmID(tipoUpmID.getTipoUPMID());
        if (tipoUpmID.getTipoUPMID() > 2 && tipoUpmID.getTipoUPMID() < 6) {
            this.ceUpm.setAccesible(inaccesible);
        } else {
            this.ceUpm.setAccesible(accesible);
        }
        if (chkInformacionContacto.isSelected()) {
            this.ceUpm.setInformacionContacto(accesible);
        }
        datosUpm.updateInformacionGeneral(this.ceUpm);
    }

    private void crearContacto() {
        CEContacto contacto = new CEContacto();
        CDContacto datosContacto = new CDContacto();
        Integer tipoContacto;
        Integer seleccionado = 1;
        Integer noSeleccionado = 0;
        Integer medioComunicacion = 0;
        Integer correoElectronico = 0;
        Integer radio = 0;
        //ascdeh_27
        tipoContacto = rbtPresencial.isSelected() ? seleccionado : noSeleccionado;
        medioComunicacion = rbtTelefonoFijo.isSelected() ? seleccionado : noSeleccionado;
        correoElectronico = chkCorreoElectronico.isSelected() ? seleccionado : noSeleccionado;
        radio = chkRadio.isSelected() ? seleccionado : noSeleccionado;

        contacto.setUpmID(this.upm);
        contacto.setTipoContacto(tipoContacto);
        contacto.setNombre(txtNombreContacto.getText());
        if (!txtDireccionContacto.getText().isEmpty()) {
            contacto.setDireccion(txtDireccionContacto.getText());
        }
        contacto.setTipoTelefono(medioComunicacion);
        contacto.setNumeroTelefono(txtNumeroTelefonico.getText());
        contacto.setTieneCorreoElectronico(correoElectronico);
        if (chkCorreoElectronico.isSelected()) {
            contacto.setCorreoElectronico(txtCorreoElectronico.getText());
        }
        contacto.setTieneRadio(radio);
        if (chkRadio.isSelected()) {
            contacto.setCanal(txtCanal.getText());
            contacto.setFrecuencia(txtFrecuencia.getText());
        }
        contacto.setObservaciones(txtObservaciones.getText());
        datosContacto.insertContacto(contacto);
    }

    private void modificarContacto() {
        CEContacto contacto = new CEContacto();
        CDContacto datosContacto = new CDContacto();
        Integer tipoContacto;
        Integer seleccionado = 1;
        Integer noSeleccionado = 0;
        Integer medioComunicacion = 0;
        Integer correoElectronico = 0;
        Integer radio = 0;
        
        tipoContacto = rbtPresencial.isSelected() ? seleccionado : noSeleccionado;
        medioComunicacion = rbtTelefonoFijo.isSelected() ? seleccionado : noSeleccionado;
        correoElectronico = chkCorreoElectronico.isSelected() ? seleccionado : noSeleccionado;
        radio = chkRadio.isSelected() ? seleccionado : noSeleccionado;
        
        contacto.setUpmID(this.upm);
        contacto.setTipoContacto(tipoContacto);
        contacto.setNombre(txtNombreContacto.getText());
        if (!txtDireccionContacto.getText().isEmpty()) {
            contacto.setDireccion(txtDireccionContacto.getText());
        }
        contacto.setTipoTelefono(medioComunicacion);
        contacto.setNumeroTelefono(txtNumeroTelefonico.getText());
        contacto.setTieneCorreoElectronico(correoElectronico);
        if (chkCorreoElectronico.isSelected()) {
            contacto.setCorreoElectronico(txtCorreoElectronico.getText());
        }
        contacto.setTieneRadio(radio);
        if (chkRadio.isSelected()) {
            contacto.setCanal(txtCanal.getText());
            contacto.setFrecuencia(txtFrecuencia.getText());
        }
        contacto.setObservaciones(txtObservaciones.getText());
        datosContacto.updateDatosContacto(contacto);
    }

    private boolean validarFecha() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String fecIni = null;
        String fecFin = null;
        String anio = "2016";
        if (dpFechaInicio.getDate() != null) {
            fecIni = sdf.format(dpFechaInicio.getDate());
        }
        if (dpFechaFin.getDate() != null) {
            fecFin = sdf.format(dpFechaFin.getDate());
        }
        if (dpFechaInicio.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Error! Fecha de inicio de muestreo es obligatoria", "Información UPM", JOptionPane.INFORMATION_MESSAGE);
            dpFechaInicio.requestFocus();
            return false;
        } else if (dpFechaFin.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Error! Fecha de fin de muestreo es obligatoria", "Información UPM", JOptionPane.INFORMATION_MESSAGE);
            dpFechaFin.requestFocus();
            return false;
        } else if (dpFechaInicio.getDate().after(dpFechaFin.getDate())) {
            JOptionPane.showMessageDialog(null, "Error! La fecha de inicio no puede ser mayor a la fecha de final de muestreo", "Información UPM", JOptionPane.INFORMATION_MESSAGE);
            dpFechaFin.requestFocus();
            return false;
        } else if (!fecIni.equals(anio)) {
            JOptionPane.showMessageDialog(null, "Error! La fecha de inicio debe estar dentro del periodo de " + anio, "Información UPM", JOptionPane.INFORMATION_MESSAGE);
            dpFechaInicio.requestFocus();
            return false;
        } else if (!fecFin.equals(anio)) {
            JOptionPane.showMessageDialog(null, "Error! La fecha de fin debe estar dentro del periodo de " + anio, "Información UPM", JOptionPane.INFORMATION_MESSAGE);
            dpFechaFin.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean validarUPM() {
        if (cmbUPM.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar un UPM", "Información UPM", JOptionPane.INFORMATION_MESSAGE);
            cmbUPM.requestFocus();
            return false;
        } else if (cmbTipoUPM.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar un tipo de UPM ", "Información UPM", JOptionPane.INFORMATION_MESSAGE);
            cmbTipoUPM.requestFocus();
            return false;
        } else if (txtPredio.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! El campo predio es obligatorio ", "Información UPM", JOptionPane.INFORMATION_MESSAGE);
            txtPredio.requestFocus();
            return false;
        } else if (txtParaje.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error! El campo paraje es obligatorio ", "Información UPM", JOptionPane.INFORMATION_MESSAGE);
            txtParaje.requestFocus();
            return false;
        } else if (cmbTenencia.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Error! Debe seleccionar un tipo de tenencia ", "Información UPM", JOptionPane.INFORMATION_MESSAGE);
            cmbTenencia.requestFocus();
            return false;
        }
        return true;
    }

    private boolean validarContacto() {
        if (chkInformacionContacto.isSelected()) {
            if (!rbtPresencial.isSelected() && !rbtRemoto.isSelected()) {
                JOptionPane.showMessageDialog(null, "Error! Debe seleccionar un tipo de contacto", "Información de contacto", JOptionPane.INFORMATION_MESSAGE);
                rbtPresencial.requestFocus();
                return false;
            } else if (txtNombreContacto.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Error! Debe proporcionar el nombre del contacto", "Información de contacto", JOptionPane.INFORMATION_MESSAGE);
                txtNombreContacto.requestFocus();
                return false;
            } else if (chkCorreoElectronico.isSelected() && txtCorreoElectronico.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Error! Debe proporcionar un correo electrónico", "Información de contacto", JOptionPane.INFORMATION_MESSAGE);
                txtCorreoElectronico.requestFocus();
                return false;
            } else if (chkRadio.isSelected() && txtCanal.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Error! Debe proporcionar canal", "Información de contacto", JOptionPane.INFORMATION_MESSAGE);
                txtCanal.requestFocus();
                return false;
            } else if (chkRadio.isSelected() && txtFrecuencia.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Error! Debe proporcionar frecuencia", "Información de contacto", JOptionPane.INFORMATION_MESSAGE);
                txtFrecuencia.requestFocus();
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }
    
    private boolean validarModificarTipoUPM(Integer tipoUpmID) {
        boolean cambioTipo = true;
        if (this.tipoUpm != tipoUpmID && this.tipoUpm > 2 && this.tipoUpm < 6  ) {
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Esta seguro de cambiar el tipo de conglomerado?, esto borrará la información de inaccesibilidad",
                    "Información general del UPM", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (respuesta == JOptionPane.YES_OPTION) {
                this.datosUpm.eliminarUPMInaccesible(this.ceUpm);
                cambioTipo = true;
            } else {
                cmbTipoUPM.setSelectedItem(this.ceTipoUpm);
                cambioTipo = false;
            }
        }
        return cambioTipo;
    }
  
    private void reiniciarControles() {
        if (cmbUPM.getSelectedItem() == null) {
            if (this.modificar == 0) {
                cmbTipoUPM.setSelectedItem(null);
                cmbTipoUPM.setEnabled(false);
            }
        } else {
            cmbTipoUPM.setEnabled(true);
        }
        dpFechaInicio.setDate(null);
        dpFechaInicio.setEnabled(false);
        dpFechaFin.setDate(null);
        dpFechaFin.setEnabled(false);
        txtPredio.setText("");
        txtPredio.setEnabled(false);
        txtParaje.setText("");
        txtParaje.setEnabled(false);
        cmbTenencia.setSelectedItem(null);
        cmbTenencia.setEnabled(false);
        chkInformacionContacto.setSelected(false);
        chkInformacionContacto.setEnabled(false);
        cmbJefeBrigada.setSelectedItem(null);
        cmbJefeBrigada.setEnabled(false);
        cmbAuxiliar1.setSelectedItem(null);
        cmbAuxiliar1.setEnabled(false);
        cmbAuxiliar2.setSelectedItem(null);
        cmbAuxiliar2.setEnabled(false);
    }

    private void habilitarControles() {
        cmbTipoUPM.setEnabled(true);
        dpFechaInicio.setEnabled(true);
        dpFechaFin.setEnabled(true);
        txtPredio.setEnabled(true);
        txtParaje.setEnabled(true);
        cmbTenencia.setEnabled(true);
        chkInformacionContacto.setEnabled(true);
        chkInformacionContacto.setSelected(true);
        cmbJefeBrigada.setEnabled(true);
        cmbAuxiliar1.setEnabled(true);
        cmbAuxiliar2.setEnabled(true);
    }

    private void reetablecerControles() {
        txtProyecto.setText("");
        txtEstado.setText("");
        txtMunicipio.setText("");

        lblTModuloA.setFont(letraNormal);
        lblTModuloA.setFont(letraNormal);
        lblTModuloA.setOpaque(false);
        LblModuloA.setFont(letraNormal);

        lblTModuloC.setFont(letraNormal);
        lblTModuloC.setFont(letraNormal);
        lblTModuloC.setOpaque(false);
        LblModuloC.setFont(letraNormal);

        lblTModuloD.setFont(letraNormal);
        lblTModuloD.setFont(letraNormal);
        lblTModuloD.setOpaque(false);
        LblModuloD.setFont(letraNormal);

        lblTModuloE.setFont(letraNormal);
        lblTModuloE.setFont(letraNormal);
        lblTModuloE.setOpaque(false);
        LblModuloE.setFont(letraNormal);

        lblTModuloF.setFont(letraNormal);
        lblTModuloF.setFont(letraNormal);
        lblTModuloF.setOpaque(false);
        LblModuloF.setFont(letraNormal);

        lblTModuloG.setFont(letraNormal);
        lblTModuloG.setFont(letraNormal);
        lblTModuloG.setOpaque(false);
        LblModuloG.setFont(letraNormal);

        lblTModuloH.setFont(letraNormal);
        lblTModuloH.setFont(letraNormal);
        lblTModuloH.setOpaque(false);
        LblModuloH.setFont(letraNormal);
    }

    //public void setMunicipio()
    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gbTipoContacto = new javax.swing.ButtonGroup();
        gbMedioComunicacion = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblUPMID = new javax.swing.JLabel();
        lblProyectoID = new javax.swing.JLabel();
        txtProyecto = new javax.swing.JTextField();
        lblTipoConglomerado = new javax.swing.JLabel();
        cmbUPM = new javax.swing.JComboBox();
        cmbTipoUPM = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        dpFechaFin = new org.jdesktop.swingx.JXDatePicker();
        dpFechaInicio = new org.jdesktop.swingx.JXDatePicker();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblTModuloA = new javax.swing.JLabel();
        lblTModuloC = new javax.swing.JLabel();
        lblTModuloD = new javax.swing.JLabel();
        lblTModuloE = new javax.swing.JLabel();
        lblTModuloG = new javax.swing.JLabel();
        lblLModuloA = new javax.swing.JLabel();
        lblLModuloC = new javax.swing.JLabel();
        lblLModuloD = new javax.swing.JLabel();
        lblLModuloE = new javax.swing.JLabel();
        lblLModuloG = new javax.swing.JLabel();
        LblModuloA = new javax.swing.JLabel();
        LblModuloC = new javax.swing.JLabel();
        LblModuloD = new javax.swing.JLabel();
        LblModuloE = new javax.swing.JLabel();
        LblModuloG = new javax.swing.JLabel();
        LblLevantados = new javax.swing.JLabel();
        LblTeoricos = new javax.swing.JLabel();
        LblDescripcion = new javax.swing.JLabel();
        lblTModuloF = new javax.swing.JLabel();
        LblModuloF = new javax.swing.JLabel();
        lblLModuloF = new javax.swing.JLabel();
        lblTModuloH = new javax.swing.JLabel();
        lblLModuloH = new javax.swing.JLabel();
        LblModuloH = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lblEstado = new javax.swing.JLabel();
        txtMunicipio = new javax.swing.JTextField();
        lblMunicipio = new javax.swing.JLabel();
        txtEstado = new javax.swing.JTextField();
        lblPredio = new javax.swing.JLabel();
        txtParaje = new javax.swing.JTextField();
        lblParaje = new javax.swing.JLabel();
        txtPredio = new javax.swing.JTextField();
        lblTenencia = new javax.swing.JLabel();
        cmbTenencia = new javax.swing.JComboBox();
        pnlContacto = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        rbtPresencial = new javax.swing.JRadioButton();
        rbtRemoto = new javax.swing.JRadioButton();
        jPanel8 = new javax.swing.JPanel();
        rbtTelefonoFijo = new javax.swing.JRadioButton();
        rbtTelefonoMovil = new javax.swing.JRadioButton();
        jPanel9 = new javax.swing.JPanel();
        lblCanal = new javax.swing.JLabel();
        lblFrecuencia = new javax.swing.JLabel();
        txtCanal = new javax.swing.JTextField();
        txtFrecuencia = new javax.swing.JTextField();
        chkRadio = new javax.swing.JCheckBox();
        jPanel10 = new javax.swing.JPanel();
        lblNombreContacto = new javax.swing.JLabel();
        txtNombreContacto = new javax.swing.JTextField();
        lblDireccion = new javax.swing.JLabel();
        txtDireccionContacto = new javax.swing.JTextField();
        lblTelefono = new javax.swing.JLabel();
        chkCorreoElectronico = new javax.swing.JCheckBox();
        txtCorreoElectronico = new javax.swing.JTextField();
        txtNumeroTelefonico = new javax.swing.JFormattedTextField();
        lblObservaciones = new javax.swing.JLabel();
        txtObservaciones = new javax.swing.JTextField();
        chkInformacionContacto = new javax.swing.JCheckBox();
        jPanel6 = new javax.swing.JPanel();
        lblJefeBrigada = new javax.swing.JLabel();
        cmbJefeBrigada = new javax.swing.JComboBox();
        lblAuxiliar1 = new javax.swing.JLabel();
        cmbAuxiliar1 = new javax.swing.JComboBox();
        lblAuxiliar2 = new javax.swing.JLabel();
        cmbAuxiliar2 = new javax.swing.JComboBox();
        jPanel11 = new javax.swing.JPanel();
        btnContinuar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setBorder(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("Información general del conglomerado, módulo 0");
        setAlignmentX(0.0F);
        setAlignmentY(-1.0F);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/gob/conafor/utils/logo_conafor.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(940, 650));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblUPMID.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUPMID.setText("UPM_ID");

        lblProyectoID.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProyectoID.setText("ID del proyecto");

        txtProyecto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtProyecto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtProyecto.setEnabled(false);

        lblTipoConglomerado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTipoConglomerado.setText("Tipo de UPM");

        cmbUPM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbUPMActionPerformed(evt);
            }
        });

        cmbTipoUPM.setEnabled(false);
        cmbTipoUPM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipoUPMActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(76, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbUPM, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblUPMID, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtProyecto)
                    .addComponent(lblProyectoID, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(121, 121, 121)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbTipoUPM, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTipoConglomerado, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE))
                .addGap(63, 63, 63))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProyectoID)
                    .addComponent(lblTipoConglomerado)
                    .addComponent(lblUPMID))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbUPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTipoUPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Fecha fin");

        dpFechaFin.setEnabled(false);

        dpFechaInicio.setEnabled(false);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Fecha de inicio");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dpFechaInicio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dpFechaFin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dpFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dpFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Módulos del conglomerado"));

        lblTModuloA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTModuloA.setText("A");
        lblTModuloA.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblTModuloC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTModuloC.setText("C");
        lblTModuloC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblTModuloD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTModuloD.setText("D");
        lblTModuloD.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblTModuloE.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTModuloE.setText("E");
        lblTModuloE.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblTModuloG.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTModuloG.setText("G");
        lblTModuloG.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblLModuloA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLModuloA.setText("A");
        lblLModuloA.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblLModuloC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLModuloC.setText("C");
        lblLModuloC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblLModuloD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLModuloD.setText("D");
        lblLModuloD.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblLModuloE.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLModuloE.setText("E");
        lblLModuloE.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblLModuloG.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLModuloG.setText("G");
        lblLModuloG.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        LblModuloA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblModuloA.setText("Variables ecológico silvicolas");
        LblModuloA.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        LblModuloC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblModuloC.setText("Carbono e incendios");
        LblModuloC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        LblModuloD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblModuloD.setText("Salud forestal");
        LblModuloD.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        LblModuloE.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblModuloE.setText("Suelos");
        LblModuloE.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        LblModuloG.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblModuloG.setText("Manglares y comunidades asociadas");
        LblModuloG.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        LblLevantados.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblLevantados.setText("Lev");
        LblLevantados.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        LblTeoricos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblTeoricos.setText("Teóricos");
        LblTeoricos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        LblDescripcion.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        LblDescripcion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblDescripcion.setText("Descripción");
        LblDescripcion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblTModuloF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTModuloF.setText("F");
        lblTModuloF.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        LblModuloF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblModuloF.setText("Fotos hemisféricas");
        LblModuloF.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblLModuloF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLModuloF.setText("F");
        lblLModuloF.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblTModuloH.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTModuloH.setText("H");
        lblTModuloH.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblLModuloH.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLModuloH.setText("H");
        lblLModuloH.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        LblModuloH.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblModuloH.setText("Zonas áridas");
        LblModuloH.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(LblTeoricos, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LblDescripcion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LblLevantados, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(lblTModuloA, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(LblModuloA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(lblTModuloC, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(LblModuloC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(lblTModuloD, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(LblModuloD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(lblTModuloE, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(LblModuloE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(lblTModuloG, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(LblModuloG, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblLModuloA, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblLModuloC, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblLModuloD, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblLModuloE, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblLModuloG, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(lblTModuloF, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(LblModuloF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(10, 10, 10)
                        .addComponent(lblLModuloF, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(lblTModuloH, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(LblModuloH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(10, 10, 10)
                        .addComponent(lblLModuloH, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LblLevantados)
                    .addComponent(LblTeoricos)
                    .addComponent(LblDescripcion))
                .addGap(4, 4, 4)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLModuloA)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblTModuloA)
                        .addComponent(LblModuloA)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblLModuloC)
                        .addComponent(LblModuloC))
                    .addComponent(lblTModuloC))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblLModuloD)
                        .addComponent(LblModuloD))
                    .addComponent(lblTModuloD))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLModuloE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblTModuloE)
                        .addComponent(LblModuloE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLModuloF)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblTModuloF)
                        .addComponent(LblModuloF)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLModuloG)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblTModuloG)
                        .addComponent(LblModuloG)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblLModuloH)
                        .addComponent(LblModuloH))
                    .addComponent(lblTModuloH))
                .addGap(54, 54, 54))
        );

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblEstado.setText("Estado:");
        jPanel5.add(lblEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        txtMunicipio.setEnabled(false);
        jPanel5.add(txtMunicipio, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 180, -1));

        lblMunicipio.setText("Municipio:");
        jPanel5.add(lblMunicipio, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        txtEstado.setEnabled(false);
        jPanel5.add(txtEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 180, -1));

        lblPredio.setText("Predio:");
        jPanel5.add(lblPredio, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        txtParaje.setEnabled(false);
        txtParaje.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtParajeFocusGained(evt);
            }
        });
        jPanel5.add(txtParaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 180, -1));

        lblParaje.setText("Paraje:");
        jPanel5.add(lblParaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        txtPredio.setEnabled(false);
        txtPredio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPredioFocusGained(evt);
            }
        });
        jPanel5.add(txtPredio, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 180, -1));

        lblTenencia.setText("Tenencia:");
        jPanel5.add(lblTenencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        cmbTenencia.setEnabled(false);
        jPanel5.add(cmbTenencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, 180, -1));

        pnlContacto.setBackground(new java.awt.Color(204, 204, 204));
        pnlContacto.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlContacto.setFocusCycleRoot(true);

        jPanel7.setBackground(new java.awt.Color(204, 204, 204));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Tipo de contacto"));
        jPanel7.setFocusCycleRoot(true);

        rbtPresencial.setBackground(new java.awt.Color(204, 204, 204));
        gbTipoContacto.add(rbtPresencial);
        rbtPresencial.setText("Presencial");
        rbtPresencial.setNextFocusableComponent(rbtRemoto);
        rbtPresencial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                rbtPresencialKeyPressed(evt);
            }
        });

        rbtRemoto.setBackground(new java.awt.Color(204, 204, 204));
        gbTipoContacto.add(rbtRemoto);
        rbtRemoto.setText("Remoto");
        rbtRemoto.setNextFocusableComponent(rbtTelefonoFijo);
        rbtRemoto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                rbtRemotoKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbtPresencial)
                    .addComponent(rbtRemoto))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(rbtPresencial)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rbtRemoto))
        );

        jPanel8.setBackground(new java.awt.Color(204, 204, 204));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Medio de comunicación"));
        jPanel8.setFocusCycleRoot(true);

        rbtTelefonoFijo.setBackground(new java.awt.Color(204, 204, 204));
        gbMedioComunicacion.add(rbtTelefonoFijo);
        rbtTelefonoFijo.setText("Teléfono fijo");
        rbtTelefonoFijo.setNextFocusableComponent(rbtTelefonoMovil);

        rbtTelefonoMovil.setBackground(new java.awt.Color(204, 204, 204));
        gbMedioComunicacion.add(rbtTelefonoMovil);
        rbtTelefonoMovil.setText("Teléfono móvil");
        rbtTelefonoMovil.setNextFocusableComponent(txtNombreContacto);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbtTelefonoFijo)
                    .addComponent(rbtTelefonoMovil))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(rbtTelefonoFijo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rbtTelefonoMovil))
        );

        jPanel9.setBackground(new java.awt.Color(204, 204, 204));
        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel9.setFocusCycleRoot(true);

        lblCanal.setLabelFor(txtCanal);
        lblCanal.setText("Canal:");

        lblFrecuencia.setLabelFor(txtFrecuencia);
        lblFrecuencia.setText("Frecuencia:");

        txtCanal.setEnabled(false);
        txtCanal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCanalFocusGained(evt);
            }
        });

        txtFrecuencia.setEnabled(false);
        txtFrecuencia.setNextFocusableComponent(txtObservaciones);
        txtFrecuencia.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtFrecuenciaFocusGained(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblFrecuencia)
                    .addComponent(lblCanal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCanal, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                    .addComponent(txtFrecuencia))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCanal)
                    .addComponent(txtCanal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFrecuencia)
                    .addComponent(txtFrecuencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        chkRadio.setBackground(new java.awt.Color(204, 204, 204));
        chkRadio.setText("Radio");
        chkRadio.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        chkRadio.setNextFocusableComponent(txtCanal);
        chkRadio.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                chkRadioPropertyChange(evt);
            }
        });
        chkRadio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                chkRadioKeyPressed(evt);
            }
        });

        jPanel10.setBackground(new java.awt.Color(204, 204, 204));
        jPanel10.setFocusCycleRoot(true);

        lblNombreContacto.setText("Nombre del contacto:");

        txtNombreContacto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNombreContactoFocusGained(evt);
            }
        });

        lblDireccion.setText("Dirección del contacto:");

        txtDireccionContacto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDireccionContactoFocusGained(evt);
            }
        });

        lblTelefono.setText("Número telefónico:");

        chkCorreoElectronico.setBackground(new java.awt.Color(204, 204, 204));
        chkCorreoElectronico.setText("Correo electrónico:");
        chkCorreoElectronico.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        chkCorreoElectronico.setNextFocusableComponent(txtCorreoElectronico);
        chkCorreoElectronico.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                chkCorreoElectronicoFocusLost(evt);
            }
        });
        chkCorreoElectronico.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                chkCorreoElectronicoPropertyChange(evt);
            }
        });
        chkCorreoElectronico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                chkCorreoElectronicoKeyPressed(evt);
            }
        });

        txtCorreoElectronico.setEnabled(false);
        txtCorreoElectronico.setNextFocusableComponent(chkRadio);
        txtCorreoElectronico.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCorreoElectronicoFocusGained(evt);
            }
        });

        try {
            txtNumeroTelefonico.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtNumeroTelefonico.setNextFocusableComponent(chkCorreoElectronico);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(chkCorreoElectronico)
                    .addComponent(lblDireccion)
                    .addComponent(lblNombreContacto)
                    .addComponent(lblTelefono))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(txtNumeroTelefonico, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 176, Short.MAX_VALUE))
                    .addComponent(txtNombreContacto)
                    .addComponent(txtDireccionContacto)
                    .addComponent(txtCorreoElectronico))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombreContacto)
                    .addComponent(txtNombreContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDireccion)
                    .addComponent(txtDireccionContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNumeroTelefonico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTelefono))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkCorreoElectronico)
                    .addComponent(txtCorreoElectronico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblObservaciones.setText("Observaciones:");

        txtObservaciones.setNextFocusableComponent(cmbJefeBrigada);
        txtObservaciones.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtObservacionesFocusGained(evt);
            }
        });

        javax.swing.GroupLayout pnlContactoLayout = new javax.swing.GroupLayout(pnlContacto);
        pnlContacto.setLayout(pnlContactoLayout);
        pnlContactoLayout.setHorizontalGroup(
            pnlContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContactoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnlContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContactoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblObservaciones)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtObservaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlContactoLayout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkRadio))))
                .addGap(23, 23, 23))
        );
        pnlContactoLayout.setVerticalGroup(
            pnlContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContactoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContactoLayout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlContactoLayout.createSequentialGroup()
                        .addGroup(pnlContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pnlContactoLayout.createSequentialGroup()
                                .addComponent(chkRadio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblObservaciones)
                            .addComponent(txtObservaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 4, Short.MAX_VALUE))
        );

        chkInformacionContacto.setText("Información del contacto");
        chkInformacionContacto.setEnabled(false);
        chkInformacionContacto.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                chkInformacionContactoPropertyChange(evt);
            }
        });
        chkInformacionContacto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                chkInformacionContactoKeyPressed(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Información de los integrantes de la brigada"));
        jPanel6.setFocusCycleRoot(true);

        lblJefeBrigada.setText("Jefe de brigada");

        cmbJefeBrigada.setEnabled(false);
        cmbJefeBrigada.setNextFocusableComponent(cmbAuxiliar1);

        lblAuxiliar1.setText("Auxiliar 1");

        cmbAuxiliar1.setEnabled(false);
        cmbAuxiliar1.setNextFocusableComponent(cmbAuxiliar2);
        cmbAuxiliar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAuxiliar1ActionPerformed(evt);
            }
        });

        lblAuxiliar2.setText("Auxiliar 2");

        cmbAuxiliar2.setEnabled(false);
        cmbAuxiliar2.setNextFocusableComponent(btnContinuar);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(cmbJefeBrigada, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(lblJefeBrigada)))
                .addGap(70, 70, 70)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbAuxiliar1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(lblAuxiliar1)))
                .addGap(59, 59, 59)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbAuxiliar2, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(lblAuxiliar2)))
                .addContainerGap(87, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lblJefeBrigada)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbJefeBrigada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lblAuxiliar1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbAuxiliar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lblAuxiliar2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbAuxiliar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12))
        );

        jPanel11.setBackground(new java.awt.Color(204, 204, 204));

        btnContinuar.setText("Continuar");
        btnContinuar.setNextFocusableComponent(btnSalir);
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

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnContinuar)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 20, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(btnContinuar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(chkInformacionContacto)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 216, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkInformacionContacto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chkInformacionContactoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_chkInformacionContactoPropertyChange
        if (chkInformacionContacto.isSelected()) {
            txtNombreContacto.setEnabled(true);
            txtNumeroTelefonico.setEnabled(true);
            txtDireccionContacto.setEnabled(true);
            rbtPresencial.setEnabled(true);
            rbtRemoto.setEnabled(true);
            rbtTelefonoFijo.setEnabled(true);
            rbtTelefonoMovil.setEnabled(true);
            chkCorreoElectronico.setEnabled(true);
            chkRadio.setEnabled(true);
            txtObservaciones.setEnabled(true);
            if (chkRadio.isSelected()) {
                txtCanal.setEnabled(true);
                txtFrecuencia.setEnabled(true);
            } else {
                txtCanal.setEnabled(false);
                txtFrecuencia.setEnabled(false);
            }
        } else {
            txtNombreContacto.setEnabled(false);
            txtNombreContacto.setText("");
            txtNumeroTelefonico.setEnabled(false);
            txtNumeroTelefonico.setText("");
            txtDireccionContacto.setEnabled(false);
            txtDireccionContacto.setText("");
            rbtPresencial.setEnabled(false);
            rbtRemoto.setEnabled(false);
            gbTipoContacto.clearSelection();
            rbtTelefonoFijo.setEnabled(false);
            rbtTelefonoMovil.setEnabled(false);
            gbMedioComunicacion.clearSelection();
            chkCorreoElectronico.setEnabled(false);
            chkCorreoElectronico.setSelected(false);
            chkRadio.setEnabled(false);
            chkRadio.setSelected(false);
            txtCanal.setEnabled(false);
            txtFrecuencia.setEnabled(false);
            txtObservaciones.setEnabled(false);
        }
    }//GEN-LAST:event_chkInformacionContactoPropertyChange

    private void chkRadioPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_chkRadioPropertyChange
        if (chkRadio.isSelected()) {
            txtCanal.setEnabled(true);
            txtFrecuencia.setEnabled(true);
        } else {
            txtCanal.setText("");
            txtFrecuencia.setText("");
            txtCanal.setEnabled(false);
            txtFrecuencia.setEnabled(false);
        }
    }//GEN-LAST:event_chkRadioPropertyChange

    private void chkCorreoElectronicoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_chkCorreoElectronicoPropertyChange
        if (chkCorreoElectronico.isSelected()) {
            txtCorreoElectronico.setEnabled(true);
        } else {
            txtCorreoElectronico.setText("");
            txtCorreoElectronico.setEnabled(false);
        }
    }//GEN-LAST:event_chkCorreoElectronicoPropertyChange

    private void btnContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuarActionPerformed
       CatETipoUPM tipoUpmID = (CatETipoUPM) cmbTipoUPM.getSelectedItem();
        if (validarUPM() && validarFecha()) {
            if (validarContacto() && validarBrigadaObligatoria() && validarBrigadistaDiferente()) {
                if (this.modificar == 0) {
                    crearUPM();
                    crearBrigada();
                    if (chkInformacionContacto.isSelected()) {
                        crearContacto();
                    }
                    this.hide();
                    this.cmbUPM.setSelectedItem(null);
                    this.cmbUPM.setEnabled(true);
                    UPMForms.puntoControlUPM.setDatosIniciales(this.ceUpm);
                    UPMForms.puntoControlUPM.setVisible(true);
                } else if (validarModificarTipoUPM(tipoUpmID.getTipoUPMID())) {
                    if (validarBrigadaObligatoria() && validarBrigadistaDiferente()) {
                        modificarUPM();
                        modificarBrigada();
                    }
                    if (chkInformacionContacto.isSelected()) {
                        modificarContacto();
                    }
                    this.hide();
                    UPMForms.puntoControlUPM.revisarPuntoControl(this.ceUpm);
                    UPMForms.puntoControlUPM.setVisible(true);
                }
            }
        }
    }//GEN-LAST:event_btnContinuarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.hide();
        combo.manipularBotonesMenuPrincipal(false);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtPredioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPredioFocusGained
        txtPredio.selectAll();
    }//GEN-LAST:event_txtPredioFocusGained

    private void txtParajeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtParajeFocusGained
        txtParaje.selectAll();
    }//GEN-LAST:event_txtParajeFocusGained

    private void txtNombreContactoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombreContactoFocusGained
        txtNombreContacto.selectAll();
    }//GEN-LAST:event_txtNombreContactoFocusGained

    private void txtDireccionContactoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDireccionContactoFocusGained
        txtDireccionContacto.selectAll();
    }//GEN-LAST:event_txtDireccionContactoFocusGained

    private void txtCorreoElectronicoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCorreoElectronicoFocusGained
        txtCorreoElectronico.selectAll();
    }//GEN-LAST:event_txtCorreoElectronicoFocusGained

    private void txtCanalFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCanalFocusGained
        txtCanal.selectAll();
    }//GEN-LAST:event_txtCanalFocusGained

    private void txtFrecuenciaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFrecuenciaFocusGained
        txtFrecuencia.selectAll();
    }//GEN-LAST:event_txtFrecuenciaFocusGained

    private void cmbUPMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbUPMActionPerformed
        if (cmbUPM.getSelectedItem() != null) {
            cmbTipoUPM.setEnabled(true);
            Integer indexMalla = (Integer) cmbUPM.getSelectedItem();
            setInformacionGeneral(indexMalla);
        } else {
            cmbTipoUPM.setEnabled(false);
            cmbTipoUPM.setSelectedItem(null);
            reetablecerControles();
        }
    }//GEN-LAST:event_cmbUPMActionPerformed

    private void cmbTipoUPMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoUPMActionPerformed
        if (this.modificar == 0) {
            if (cmbTipoUPM.getSelectedItem() == null) {
                reiniciarControles();
                reetablecerControles();
            } else {
                CatETipoUPM indexTipoUpm = (CatETipoUPM) cmbTipoUPM.getSelectedItem();
                this.tipoUpm = indexTipoUpm.getTipoUPMID();
                habilitarControles();
            }
        } /*else {
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Esta seguro de cambiar el tipo de conglomerado?, esto borrará la información relacionada",
                    "Información general del UPM", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (respuesta == JOptionPane.YES_OPTION) {
                this.datosUpm.eliminarUPMInaccesible(this.ceUpm);
            } else {
                cmbTipoUPM.setSelectedItem(this.ceTipoUpm);
            }
        }*/
    }//GEN-LAST:event_cmbTipoUPMActionPerformed

    private void chkCorreoElectronicoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_chkCorreoElectronicoFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_chkCorreoElectronicoFocusLost

    private void txtObservacionesFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtObservacionesFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtObservacionesFocusGained

    private void rbtPresencialKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rbtPresencialKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (rbtPresencial.isSelected()) {
                rbtPresencial.setSelected(false);
            } else {
                rbtPresencial.setSelected(true);
            }
        }
    }//GEN-LAST:event_rbtPresencialKeyPressed

    private void rbtRemotoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rbtRemotoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (rbtRemoto.isSelected()) {
                rbtRemoto.setSelected(false);
            } else {
                rbtRemoto.setSelected(true);
            }
        }
    }//GEN-LAST:event_rbtRemotoKeyPressed

    private void chkCorreoElectronicoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chkCorreoElectronicoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (chkCorreoElectronico.isSelected()) {
                chkCorreoElectronico.setSelected(false);
                txtCorreoElectronico.setEnabled(false);
                txtCorreoElectronico.setText("");
            } else {
                chkCorreoElectronico.setSelected(true);
                txtCorreoElectronico.setEnabled(true);
            }
        }
    }//GEN-LAST:event_chkCorreoElectronicoKeyPressed

    private void chkRadioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chkRadioKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (chkRadio.isSelected()) {
                chkRadio.setSelected(false);
            } else {
                chkRadio.setSelected(true);
            }
        }
    }//GEN-LAST:event_chkRadioKeyPressed

    private void chkInformacionContactoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chkInformacionContactoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if(chkInformacionContacto.isSelected()){
                chkInformacionContacto.setSelected(false);
            } else {
                chkInformacionContacto.setSelected(true);
            }
        }
    }//GEN-LAST:event_chkInformacionContactoKeyPressed

    private void cmbAuxiliar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAuxiliar1ActionPerformed
        
    }//GEN-LAST:event_cmbAuxiliar1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LblDescripcion;
    private javax.swing.JLabel LblLevantados;
    private javax.swing.JLabel LblModuloA;
    private javax.swing.JLabel LblModuloC;
    private javax.swing.JLabel LblModuloD;
    private javax.swing.JLabel LblModuloE;
    private javax.swing.JLabel LblModuloF;
    private javax.swing.JLabel LblModuloG;
    private javax.swing.JLabel LblModuloH;
    private javax.swing.JLabel LblTeoricos;
    private javax.swing.JButton btnContinuar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JCheckBox chkCorreoElectronico;
    private javax.swing.JCheckBox chkInformacionContacto;
    private javax.swing.JCheckBox chkRadio;
    private javax.swing.JComboBox cmbAuxiliar1;
    private javax.swing.JComboBox cmbAuxiliar2;
    private javax.swing.JComboBox cmbJefeBrigada;
    private javax.swing.JComboBox cmbTenencia;
    private javax.swing.JComboBox cmbTipoUPM;
    private javax.swing.JComboBox cmbUPM;
    private org.jdesktop.swingx.JXDatePicker dpFechaFin;
    private org.jdesktop.swingx.JXDatePicker dpFechaInicio;
    private javax.swing.ButtonGroup gbMedioComunicacion;
    private javax.swing.ButtonGroup gbTipoContacto;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel lblAuxiliar1;
    private javax.swing.JLabel lblAuxiliar2;
    private javax.swing.JLabel lblCanal;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblFrecuencia;
    private javax.swing.JLabel lblJefeBrigada;
    private javax.swing.JLabel lblLModuloA;
    private javax.swing.JLabel lblLModuloC;
    private javax.swing.JLabel lblLModuloD;
    private javax.swing.JLabel lblLModuloE;
    private javax.swing.JLabel lblLModuloF;
    private javax.swing.JLabel lblLModuloG;
    private javax.swing.JLabel lblLModuloH;
    private javax.swing.JLabel lblMunicipio;
    private javax.swing.JLabel lblNombreContacto;
    private javax.swing.JLabel lblObservaciones;
    private javax.swing.JLabel lblParaje;
    private javax.swing.JLabel lblPredio;
    private javax.swing.JLabel lblProyectoID;
    private javax.swing.JLabel lblTModuloA;
    private javax.swing.JLabel lblTModuloC;
    private javax.swing.JLabel lblTModuloD;
    private javax.swing.JLabel lblTModuloE;
    private javax.swing.JLabel lblTModuloF;
    private javax.swing.JLabel lblTModuloG;
    private javax.swing.JLabel lblTModuloH;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblTenencia;
    private javax.swing.JLabel lblTipoConglomerado;
    private javax.swing.JLabel lblUPMID;
    private javax.swing.JPanel pnlContacto;
    private javax.swing.JRadioButton rbtPresencial;
    private javax.swing.JRadioButton rbtRemoto;
    private javax.swing.JRadioButton rbtTelefonoFijo;
    private javax.swing.JRadioButton rbtTelefonoMovil;
    private javax.swing.JTextField txtCanal;
    private javax.swing.JTextField txtCorreoElectronico;
    private javax.swing.JTextField txtDireccionContacto;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtFrecuencia;
    private javax.swing.JTextField txtMunicipio;
    private javax.swing.JTextField txtNombreContacto;
    private javax.swing.JFormattedTextField txtNumeroTelefonico;
    private javax.swing.JTextField txtObservaciones;
    private javax.swing.JTextField txtParaje;
    private javax.swing.JTextField txtPredio;
    private javax.swing.JTextField txtProyecto;
    // End of variables declaration//GEN-END:variables
}
