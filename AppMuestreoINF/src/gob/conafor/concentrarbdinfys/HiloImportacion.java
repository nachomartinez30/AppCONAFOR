package gob.conafor.concentrarbdinfys;

import java.awt.BasicStroke;
import java.io.File;

//import gob.conafor.sys.mod.CDImportacionBD;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

public class HiloImportacion extends SwingWorker<Integer, String> {

    private CDImportacionBD bdImportar = new CDImportacionBD();
    // private CDConentrarBD bdConcentrar =new CDConentrarBD();
    private JLabel lblEstatus;
    public JTextField txtUbicacion;
    private JProgressBar pbExportacion;
    private JButton btnEjecutar, btnBuscar;
    public JTextArea txtaMonitoreo;
    public JCheckBox chckbxContinuarSinRepetidos;

    public boolean termino;
    public File[] baseDatos;
    public int contadorBD = 0;

    public HiloImportacion(JLabel lblEstatus, JProgressBar pbExportacion, JButton btnEjecutar, File[] baseDatos,
            JButton btnBuscar, JTextField txtUbicacion, JTextArea txtaMonitoreo, JCheckBox chckbxContinuarSinRepetidos) {
        this.lblEstatus = lblEstatus;
        this.baseDatos = baseDatos;
        this.pbExportacion = pbExportacion;
        this.btnEjecutar = btnEjecutar;
        this.btnBuscar = btnBuscar;
        this.txtUbicacion = txtUbicacion;
        this.baseDatos = baseDatos;
        this.txtaMonitoreo = txtaMonitoreo;
        this.chckbxContinuarSinRepetidos = chckbxContinuarSinRepetidos;
    }

    @Override
    protected Integer doInBackground() {
        int i = 0;
        pbExportacion.setIndeterminate(true);
        while (i != baseDatos.length) {
            System.out.println(baseDatos[i].getPath().toString());
            txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + baseDatos[i].getPath().toString());
            txtUbicacion.setText(baseDatos[i].getPath().toString());
            migrar(baseDatos[i].getPath().toString());

            i++;
        }
        pbExportacion.setIndeterminate(false);
        JOptionPane.showMessageDialog(null, "Concentrado finalizado");
        btnBuscar.setEnabled(true);
        return 0;

    }

    public JButton getBtnEjecutar() {
        return btnEjecutar;
    }

    public void setBtnEjecutar(JButton btnEjecutar) {
        this.btnEjecutar = btnEjecutar;
    }

    public void migrar(String pathUbicacion) {
        btnEjecutar.setEnabled(false);
        btnBuscar.setEnabled(false);

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        bdImportar.validarRepetidos(pathUbicacion);
        Object[] opciones = {"Si", "No"};
        for (int j = 0; j < bdImportar.arregloRepetidos.size(); j++) {
            if (chckbxContinuarSinRepetidos.isSelected() == true) {//continuar sin repetidos
                txtaMonitoreo.setText(txtaMonitoreo.getText() + "\nUPM REPETIDO=" + bdImportar.arregloRepetidos.get(j));
            } else {
                int respuesta = JOptionPane.showOptionDialog(null, "El UPMID: " + bdImportar.arregloRepetidos.get(j) + " ya se encuentra en la base de datos local, Â¿desea reeplazarlo?", "Importación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[1]);
                if (respuesta == JOptionPane.YES_OPTION) {
                    bdImportar.eliminarRepetido(bdImportar.arregloRepetidos.get(j));
                }
                if (respuesta == JOptionPane.NO_OPTION) {
                    System.out.println("opcion no");
                }
            }
        }

        txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
        lblEstatus.setText("UPMs");
        bdImportar.importarUPM_UPM(pathUbicacion); // 1
        txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());

        lblEstatus.setText("Contacto");
        bdImportar.importarUPMContacto(pathUbicacion); // 2
        txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());

        lblEstatus.setText("Punto de control");
        bdImportar.importarPC(pathUbicacion); // 3
        txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());

        lblEstatus.setText("Accesibilidad Punto de control");
        bdImportar.importarAccesibilidadPC(pathUbicacion); // 4
        txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());

        lblEstatus.setText("Epifitas");
        bdImportar.importarUPMEpifitas(pathUbicacion); // 5
        txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());

        lblEstatus.setText("Sitios");
        bdImportar.importarSitios(pathUbicacion); // 6
        txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());

        lblEstatus.setText("Sitios Cobertura Suelo");
        bdImportar.importarSitiosCoberturaSuelo(pathUbicacion); // 7
        txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
        // System.err.println("Entro a Cobertura Suelo");

        lblEstatus.setText("Fotografia Hemisferica");
        bdImportar.importarFotografiaHemisferica(pathUbicacion); // 8
        txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());

        lblEstatus.setText("Transponder");
        bdImportar.importarTransponder(pathUbicacion); // 9
        txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());

        lblEstatus.setText("Parametros Fisico Quimicos");
        bdImportar.importarParametrosFisicoQuimicos(pathUbicacion); // 10
        txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());

        lblEstatus.setText("Suelo Informacion");
        bdImportar.importarSueloInformacion(pathUbicacion); // 11
        txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
        lblEstatus.setText("Suelo Varillas Erosion");
        bdImportar.importarSueloVarillasErosion(pathUbicacion); // 12
        txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
        lblEstatus.setText("Suelo Cobertura");
        bdImportar.importarSueloCobertura(pathUbicacion); // 13
        txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());

        lblEstatus.setText("Suelo Evidencia Erosion");
        bdImportar.importarSueloEvidenciaErosion(pathUbicacion); // 14
        txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
        lblEstatus.setText("Suelo Pedestal");
        bdImportar.importarSueloPedestal(pathUbicacion); // 15
        txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
        lblEstatus.setText("Suelo Erosion Laminar");
        bdImportar.importarSueloErosionLaminar(pathUbicacion); // 16
        txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
        lblEstatus.setText("Suelo Costras");
        bdImportar.importarSueloCostras(pathUbicacion); // 17
        txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
        lblEstatus.setText("Suelo Canalillo");
        bdImportar.importarSueloCanalillo(pathUbicacion); // 18
        txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
        lblEstatus.setText("Suelo Carcava");
        bdImportar.importarSueloCarcava(pathUbicacion); // 19
        txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
        lblEstatus.setText("Suelo Pavimentos");
        bdImportar.importarSueloPavimentos(pathUbicacion); // 20
        txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
        lblEstatus.setText("Suelo Medicion Canalillos");
        bdImportar.importarSueloMedicionCanalillos(pathUbicacion); // 21
        txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
        lblEstatus.setText("Suelo Medicion Carcavas");
        bdImportar.importarSueloMedicionCarcavas(pathUbicacion); // 22
        txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
        lblEstatus.setText("Suelo Medicion Dunas");
        bdImportar.importarSueloMedicionDunas(pathUbicacion); // 23
        txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
        lblEstatus.setText("Suelo Erosion Hidrica Canalillo");
        bdImportar.importarSueloErosionHidricaCanalillo(pathUbicacion); // 24
        txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
        lblEstatus.setText("Suelo Longitud Canalillo");
        bdImportar.importarSueloLongitudCanalillo(pathUbicacion); // 25
        txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
        lblEstatus.setText("Suelo Erosion Hidrica Carcava");
        bdImportar.importarSueloErosionHidricaCarcava(pathUbicacion); // 26
        txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
        lblEstatus.setText("Suelo Longitud Carcava");
        bdImportar.importarSueloLongitudCarcava(pathUbicacion); // 27
        txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
        lblEstatus.setText("Suelo Deformacion Viento");
        bdImportar.importarSueloDeformacionViento(pathUbicacion); // 28
        txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
        lblEstatus.setText("Suelo Longitud Monticulo");
        bdImportar.importarSueloLongitudMonticulo(pathUbicacion); // 29
        txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
        lblEstatus.setText("Suelo Hojarasca");
        bdImportar.importarSueloHojarasca(pathUbicacion); // 30
        txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
        lblEstatus.setText("Suelo Profundidad");
        bdImportar.importarSueloProfundidad(pathUbicacion); // 31
        txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
        lblEstatus.setText("Suelo Muestras Perfil");
        bdImportar.importarSueloMuestrasPerfil(pathUbicacion); // 32
        txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
        lblEstatus.setText("Suelo Muestras");
        bdImportar.importarSueloMuestras(pathUbicacion); // 33
        txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());

        lblEstatus.setText("Carbono Material Lenioso100");
        bdImportar.importarCarbonoMaterialLenioso100(pathUbicacion); // 34
        txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
        lblEstatus.setText("Carbono Material Lenioso1000");
        bdImportar.importarCarbonoMaterialLenioso1000(pathUbicacion); // 35
        txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
        lblEstatus.setText("Carbono Cubierta Vegetal");
        bdImportar.importarCarbonoCubiertaVegetal(pathUbicacion); // 36
        txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
        lblEstatus.setText("Carbono Cobertura Dosel");
        bdImportar.importarCarbonoCoberturaDosel(pathUbicacion); // 37
        txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
        lblEstatus.setText("Carbono Longitud Componente");
        bdImportar.importarCarbonoLongitudComponente(pathUbicacion); // 38
        txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());

        lblEstatus.setText("Taxonomia Arbolado");
        bdImportar.importarTaxonomiaArbolado(pathUbicacion); // 39
        txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
        lblEstatus.setText("Arbolado Danio Severidad");
        bdImportar.importarArboladoDanioSeveridad(pathUbicacion); // 40
        txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
        lblEstatus.setText("Submuestra");
        bdImportar.importarSubmuestra(pathUbicacion); // 41
        txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
        lblEstatus.setText("Submuestra Troza");
        bdImportar.importarSubmuestraTroza(pathUbicacion); // 42
        txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
        lblEstatus.setText("Submuestra Observaciones");
        bdImportar.importarSubmuestraObservaciones(pathUbicacion);
        txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());

        lblEstatus.setText("Taxonomia Repoblado");
        bdImportar.importarTaxonomiaRepoblado(pathUbicacion); // 43
        txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());

        lblEstatus.setText("Taxonomia Repoblado V M");
        bdImportar.importarTaxonomiaRepobladoVM(pathUbicacion); // 44
        txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());

        lblEstatus.setText("Repoblado Danio Severidad");
        bdImportar.importarRepobladoDanioSeveridad(pathUbicacion);//44.1
        txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());

        lblEstatus.setText("Taxonomia Soto Bosque");
        bdImportar.importarTaxonomiaSotoBosque(pathUbicacion); // 45
        txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());

        lblEstatus.setText("Taxonomia Vegetacion Mayor Gregarios");
        bdImportar.importarTaxonomiaVegetacionMayorGregarios(pathUbicacion); // 46
        txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
        lblEstatus.setText("Vegetacion Mayor Gregarios Danio Severidad");
        bdImportar.importarVegetacionMayorGDanioSeveridad(pathUbicacion); // 47
        txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());

        lblEstatus.setText("Taxonomia Vegetacion Mayor Individual");
        bdImportar.importarTaxonomiaVegetacionMayorIndividual(pathUbicacion); // 48
        txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
        lblEstatus.setText("Vegetacion Mayor Individual Danio Severidad");
        bdImportar.importarVegetacionMayorIDanioSeveridad(pathUbicacion); // 49
        txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());

        lblEstatus.setText("Taxonomia Vegetacion Menor");
        bdImportar.importarTaxonomiaVegetacionMenor(pathUbicacion); // 50
        txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
        lblEstatus.setText("Vegetacion Menor Danio Severidad");
        bdImportar.importarVegetacionMenorDanioSeveridad(pathUbicacion); // 51
        txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());

        lblEstatus.setText("Taxonomia Colecta Botanica");
        bdImportar.importarTaxonomiaColectaBotanica(pathUbicacion); // 52
        txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());

        lblEstatus.setText("Brigadas");
        bdImportar.importarBrigadas(pathUbicacion); // 53
        txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());

        lblEstatus.setText("Observaciones Sitio");
        bdImportar.importarObservacionesSitio(pathUbicacion); // 54
        txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());

        // bdImportar.importarSecuencias(pathUbicacion); //54
        txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());

        // bdImportar.importarUPMRevision(pathUbicacion);
        txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());

    }
}
