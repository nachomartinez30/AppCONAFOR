package gob.conafor.ini.vie;

import gob.conafor.sys.mod.CDImportacionBD;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

public class HiloImportacion extends SwingWorker<Integer, String> {

    private CDImportacionBD bdImportar = new CDImportacionBD();
    //private CDConentrarBD bdConcentrar =new CDConentrarBD();
    private JLabel etiqueta;
    private JProgressBar barraProgreso;
    private JButton btnEjecutar;
    private JButton btnSalir;
    private String ruta;

    public HiloImportacion(JLabel etiqueta, JProgressBar barraProgreso, JButton btnEjecutar, JButton btnSalir, String ruta) {
        this.etiqueta = etiqueta;
        this.barraProgreso = barraProgreso;
        this.btnEjecutar = btnEjecutar;
        this.btnSalir = btnSalir;
        this.ruta = ruta;
    }

    @Override
    protected Integer doInBackground() {
        getEtiqueta().setText("Iniciando...");
        getBarraProgreso().setValue(0);
        getBtnEjecutar().setEnabled(false);
        getBtnSalir().setEnabled(false);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
             e.printStackTrace();
        }

        getEtiqueta().setText("Validando repetidos");
        bdImportar.validarRepetidos(this.ruta);
        getEtiqueta().setText("UPM's...");
        bdImportar.importarUPM_UPM(this.ruta); //1
        
        getEtiqueta().setText("Contactos...");
        bdImportar.importarUPMContacto(this.ruta); //2
        getBarraProgreso().setValue(5);
        getBarraProgreso().repaint();
        
        getEtiqueta().setText("punto de control...");
        bdImportar.importarPC(this.ruta); //3
        
        getEtiqueta().setText("Acceso punto de control...");
        bdImportar.importarAccesibilidadPC(this.ruta); //4
        getBarraProgreso().setValue(10);
        getBarraProgreso().repaint();
       
        getEtiqueta().setText("Epifitas...");
        bdImportar.importarUPMEpifitas(this.ruta); //5
        getBarraProgreso().setValue(15);
        getBarraProgreso().repaint();
        
        getEtiqueta().setText("Sitios...");
        bdImportar.importarSitios(this.ruta); //6
        
        getEtiqueta().setText("Sitios cobertura suelo...");
        bdImportar.importarSitiosCoberturaSuelo(this.ruta); //7
        getBarraProgreso().setValue(20);
        getBarraProgreso().repaint();
        
        getEtiqueta().setText("Fotografias hemisferica...");
        bdImportar.importarFotografiaHemisferica(this.ruta); //8
        getBarraProgreso().setValue(25);
        getBarraProgreso().repaint();
        
        getEtiqueta().setText("Transponder...");
        bdImportar.importarTransponder(this.ruta); //9
        getBarraProgreso().setValue(30);
        getBarraProgreso().repaint();
        
        getEtiqueta().setText("Parametros Fisico- Quimicos...");
        bdImportar.importarParametrosFisicoQuimicos(this.ruta); //10
        getBarraProgreso().setValue(35);
        getBarraProgreso().repaint();
        
        getEtiqueta().setText("Suelo Informacion...");
        bdImportar.importarSueloInformacion(this.ruta); //11
        getEtiqueta().setText("Suelo Varillas Erosion...");
        bdImportar.importarSueloVarillasErosion(this.ruta); //12
        getEtiqueta().setText("Suelo Cobertura...");
        bdImportar.importarSueloCobertura(this.ruta); //13
        
        getBarraProgreso().setValue(40);
        getBarraProgreso().repaint();
        
        getEtiqueta().setText("Suelo Evidencia Erosion...");
        bdImportar.importarSueloEvidenciaErosion(this.ruta); //14
        getEtiqueta().setText("Suelo Pedestal...");
        bdImportar.importarSueloPedestal(this.ruta); //15
        getEtiqueta().setText("Suelo Erosion Laminar...");
        bdImportar.importarSueloErosionLaminar(this.ruta); //16
        getEtiqueta().setText("Suelo Costras...");
        bdImportar.importarSueloCostras(this.ruta); //17
        getEtiqueta().setText("Suelo Canalillo...");
        bdImportar.importarSueloCanalillo(this.ruta); //18
        getEtiqueta().setText("Suelo Carcava...");
        bdImportar.importarSueloCarcava(this.ruta); //19
        getEtiqueta().setText("Suelo Pavimentos...");
        bdImportar.importarSueloPavimentos(this.ruta); //20
        getEtiqueta().setText("Suelo Medicion Canalillos...");
        bdImportar.importarSueloMedicionCanalillos(this.ruta); //21
        getEtiqueta().setText("Suelo Medicion Carcavas...");
        bdImportar.importarSueloMedicionCarcavas(this.ruta); //22
        getEtiqueta().setText("Suelo Medicion Dunas...");
        bdImportar.importarSueloMedicionDunas(this.ruta); //23
        getBarraProgreso().setValue(45);
        getBarraProgreso().repaint();
        getEtiqueta().setText("Suelo Erosion Hidrica Canalillo...");
        bdImportar.importarSueloErosionHidricaCanalillo(this.ruta); //24
        getEtiqueta().setText("Suelo Longitud Canalillo...");
        bdImportar.importarSueloLongitudCanalillo(this.ruta); //25
        getEtiqueta().setText("Suelo Erosion Hidrica Carcava...");
        bdImportar.importarSueloErosionHidricaCarcava(this.ruta); //26
        getEtiqueta().setText("Suelo Longitud Carcava...");
        bdImportar.importarSueloLongitudCarcava(this.ruta); //27
        getEtiqueta().setText("Suelo Deformacion Viento...");
        bdImportar.importarSueloDeformacionViento(this.ruta); //28
        getEtiqueta().setText("Suelo Longitud Monticulo...");
        bdImportar.importarSueloLongitudMonticulo(this.ruta); //29
        getEtiqueta().setText("Suelo Hojarasca...");
        bdImportar.importarSueloHojarasca(this.ruta); //30
        getEtiqueta().setText("Suelo Profundidad...");
        bdImportar.importarSueloProfundidad(this.ruta); //31
        getEtiqueta().setText("Suelo Muestras Perfil...");
        bdImportar.importarSueloMuestrasPerfil(this.ruta); //32
        getEtiqueta().setText("Suelo Muestras...");
        bdImportar.importarSueloMuestras(this.ruta); //33
        getBarraProgreso().setValue(50);
        getBarraProgreso().repaint();
        
        
        getEtiqueta().setText("Carbono Material Lenioso100...");
        bdImportar.importarCarbonoMaterialLenioso100(this.ruta); //34
        getEtiqueta().setText("Carbono Material Lenioso1000...");
        bdImportar.importarCarbonoMaterialLenioso1000(this.ruta); //35
        getEtiqueta().setText("Carbono Cubierta Vegetal...");
        bdImportar.importarCarbonoCubiertaVegetal(this.ruta); //36
        getEtiqueta().setText("Carbono Cobertura Dosel...");
        bdImportar.importarCarbonoCoberturaDosel(this.ruta); //37
        getEtiqueta().setText("Carbono Longitud Componente...");
        bdImportar.importarCarbonoLongitudComponente(this.ruta); //38
        
        getBarraProgreso().setValue(55);
        getBarraProgreso().repaint();
        
        getEtiqueta().setText("Taxonomia Arbolado...");
        bdImportar.importarTaxonomiaArbolado(this.ruta); //39
        getEtiqueta().setText("Arbolado Danio Severidad...");
        bdImportar.importarArboladoDanioSeveridad(this.ruta); //40
        getEtiqueta().setText("Submuestra...");
        bdImportar.importarSubmuestra(this.ruta); //41
        getEtiqueta().setText("Submuestra Troza...");
        bdImportar.importarSubmuestraTroza(this.ruta); //42
        getEtiqueta().setText("Submuestra Observaciones...");
        bdImportar.importarSubmuestraObservaciones(this.ruta);
        getBarraProgreso().setValue(60);
        getBarraProgreso().repaint();
        
        getEtiqueta().setText("Taxonomia Repoblado...");
        bdImportar.importarTaxonomiaRepoblado(this.ruta); //43
        getBarraProgreso().setValue(65);
        getBarraProgreso().repaint();
        
        getEtiqueta().setText("Taxonomia Repoblado Vegetacion Menor...");
        bdImportar.importarTaxonomiaRepobladoVM(this.ruta); //44
        getBarraProgreso().setValue(70);
        getBarraProgreso().repaint();
        
        getEtiqueta().setText("Taxonomia Soto Bosque...");
        bdImportar.importarTaxonomiaSotoBosque(this.ruta); //45
        getBarraProgreso().setValue(75);
        getBarraProgreso().repaint();
        
        getEtiqueta().setText("Taxonomia Vegetacion Mayor Gregarios...");
        bdImportar.importarTaxonomiaVegetacionMayorGregarios(this.ruta); //46
        getEtiqueta().setText("Vegetacion Mayor Gregarios Danio Severidad...");
        bdImportar.importarVegetacionMayorGDanioSeveridad(this.ruta); //47
        getBarraProgreso().setValue(80);
        getBarraProgreso().repaint();
        
        getEtiqueta().setText("Taxonomia Vegetacion Mayor Individual...");
        bdImportar.importarTaxonomiaVegetacionMayorIndividual(this.ruta); //48
        getEtiqueta().setText("Vegetacion Mayor Individual Danio Severidad...");
        bdImportar.importarVegetacionMayorIDanioSeveridad(this.ruta); //49
        getBarraProgreso().setValue(85);
        getBarraProgreso().repaint();
        
        getEtiqueta().setText("Repoblado Danio Severidad...");
        bdImportar.importarRepobladoDanioSeveridad(this.ruta); 
        getBarraProgreso().setValue(88);
        getBarraProgreso().repaint();
        
        
        getEtiqueta().setText("Taxonomia Vegetacion Menor...");
        bdImportar.importarTaxonomiaVegetacionMenor(this.ruta); //50
        getEtiqueta().setText("Vegetacion Menor Danio Severidad...");
        bdImportar.importarVegetacionMenorDanioSeveridad(this.ruta); //51
        getBarraProgreso().setValue(90);
        getBarraProgreso().repaint();
        
        getEtiqueta().setText("Taxonomia Colecta Botanica...");
        bdImportar.importarTaxonomiaColectaBotanica(this.ruta); //52
        getBarraProgreso().setValue(95);
        getBarraProgreso().repaint();
        
        getEtiqueta().setText("Observaciones Sitio...");
        bdImportar.importarObservacionesSitio(this.ruta); //52.1
        getBarraProgreso().setValue(98);
        getBarraProgreso().repaint();
        
        
        getEtiqueta().setText("Brigadas...");
        bdImportar.importarBrigadas(this.ruta); //53
        getBarraProgreso().setValue(99);
        getBarraProgreso().repaint();
        
        getEtiqueta().setText("Secuencias...");
        bdImportar.importarSecuencias(this.ruta); //54
        getEtiqueta().setText("UPM Revision...");
        bdImportar.importarUPMRevision(this.ruta);
        getBarraProgreso().setValue(100);
        getBarraProgreso().repaint();
        return null;
    }
    
    @Override
    protected void done(){
        JOptionPane.showMessageDialog(null, "Importacion terminada");
        getBarraProgreso().setValue(0);
        getBarraProgreso().repaint();
        getBtnEjecutar().setEnabled(false);
        getBtnSalir().setEnabled(true);
    }

    public JLabel getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(JLabel etiqueta) {
        this.etiqueta = etiqueta;
    }

    public JProgressBar getBarraProgreso() {
        return barraProgreso;
    }

    public void setBarraProgreso(JProgressBar barraProgreso) {
        this.barraProgreso = barraProgreso;
    }

    public JButton getBtnEjecutar() {
        return btnEjecutar;
    }

    public void setBtnEjecutar(JButton btnEjecutar) {
        this.btnEjecutar = btnEjecutar;
    }

    public JButton getBtnSalir() {
        return btnSalir;
    }

    public void setBtnSalir(JButton btnSalir) {
        this.btnSalir = btnSalir;
    }

}
