package concentrarbdinfys;

//import gob.conafor.sys.mod.CDImportacionBD;
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
      
        getEtiqueta().setText("Iniciando importación...");
        getBarraProgreso().setValue(0);
        getBtnEjecutar().setEnabled(false);
        getBtnSalir().setEnabled(false);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
             e.printStackTrace();
        }

        getEtiqueta().setText("Importando UPM..."); 
        bdImportar.validarRepetidos(this.ruta);
        bdImportar.importarUPM_UPM(this.ruta); //1
        
        getEtiqueta().setText("Importando Contacto..."); 
        bdImportar.importarUPMContacto(this.ruta); //2
        getBarraProgreso().setValue(5);
        getBarraProgreso().repaint();
        
        getEtiqueta().setText("Importando PC..."); 
        bdImportar.importarPC(this.ruta); //3
        
        getEtiqueta().setText("Importando Información de accesibilidad del PC..."); 
        bdImportar.importarAccesibilidadPC(this.ruta); //4
        getBarraProgreso().setValue(10);
        getBarraProgreso().repaint();
       
        getEtiqueta().setText("Importando Epífitas..."); 
        bdImportar.importarUPMEpifitas(this.ruta); //5
        getBarraProgreso().setValue(15);
        getBarraProgreso().repaint();
        
        getEtiqueta().setText("Importando Información de sitios..."); 
        bdImportar.importarSitios(this.ruta); //6
        
        getEtiqueta().setText("Importando Cobertura de suelo de sitio..."); 
        bdImportar.importarSitiosCoberturaSuelo(this.ruta); //7
        //System.err.println("Entro a Cobertura Suelo");
        getBarraProgreso().setValue(20);
        getBarraProgreso().repaint();
        
        getEtiqueta().setText("Importando Fotografía hemisferica..."); 
        bdImportar.importarFotografiaHemisferica(this.ruta); //8
        getBarraProgreso().setValue(25);
        getBarraProgreso().repaint();
        
        getEtiqueta().setText("Importando Información de transponder..."); 
        bdImportar.importarTransponder(this.ruta); //9
        getBarraProgreso().setValue(30);
        getBarraProgreso().repaint();
        
        getEtiqueta().setText("Importando Parámetros físico químicos..."); 
        bdImportar.importarParametrosFisicoQuimicos(this.ruta); //10
        getBarraProgreso().setValue(35);
        getBarraProgreso().repaint();
        
        getEtiqueta().setText("Importando Información de suelo..."); 
        bdImportar.importarSueloInformacion(this.ruta); //11
        getEtiqueta().setText("Importando varillas de erosión..."); 
        bdImportar.importarSueloVarillasErosion(this.ruta); //12
        getEtiqueta().setText("Importando cobertura del suelo..."); 
        bdImportar.importarSueloCobertura(this.ruta); //13
        
        getBarraProgreso().setValue(40);
        getBarraProgreso().repaint();
        
        getEtiqueta().setText("Importando evidencia de erosión del suelo..."); 
        bdImportar.importarSueloEvidenciaErosion(this.ruta); //14
        getEtiqueta().setText("Importando Pedestal..."); 
        bdImportar.importarSueloPedestal(this.ruta); //15
        getEtiqueta().setText("Importando Erosión laminar..."); 
        bdImportar.importarSueloErosionLaminar(this.ruta); //16
        getEtiqueta().setText("Importando Costras..."); 
        bdImportar.importarSueloCostras(this.ruta); //17
        getEtiqueta().setText("Importando Canalillo..."); 
        bdImportar.importarSueloCanalillo(this.ruta); //18
        getEtiqueta().setText("Importando Cárcava..."); 
        bdImportar.importarSueloCarcava(this.ruta); //19
        getEtiqueta().setText("Importando Pavimentos..."); 
        bdImportar.importarSueloPavimentos(this.ruta); //20
        getEtiqueta().setText("Importando Medición canalillos...");
        bdImportar.importarSueloMedicionCanalillos(this.ruta); //21
        getEtiqueta().setText("Importando Medición carcavas..."); 
        bdImportar.importarSueloMedicionCarcavas(this.ruta); //22
        getEtiqueta().setText("Importando Medición dunas..."); 
        bdImportar.importarSueloMedicionDunas(this.ruta); //23
        getBarraProgreso().setValue(45);
        getBarraProgreso().repaint();
        getEtiqueta().setText("Importando Erosión hídrica canalillo..."); 
        bdImportar.importarSueloErosionHidricaCanalillo(this.ruta); //24
        getEtiqueta().setText("Importando Longitud canalillo..."); 
        bdImportar.importarSueloLongitudCanalillo(this.ruta); //25
        getEtiqueta().setText("Importando Erosión hidrica carcava...");
        bdImportar.importarSueloErosionHidricaCarcava(this.ruta); //26
        getEtiqueta().setText("Importando longitud de carcava..."); 
        bdImportar.importarSueloLongitudCarcava(this.ruta); //27
        getEtiqueta().setText("Importando deformación por viento..."); 
        bdImportar.importarSueloDeformacionViento(this.ruta); //28
        getEtiqueta().setText("Importando longitud montículo..."); 
        bdImportar.importarSueloLongitudMonticulo(this.ruta); //29
        getEtiqueta().setText("Importando hojarasca...");
        bdImportar.importarSueloHojarasca(this.ruta); //30
         getEtiqueta().setText("Importando profundidad de suelo...");
        bdImportar.importarSueloProfundidad(this.ruta); //31
        getEtiqueta().setText("Importando perfil...");
        bdImportar.importarSueloMuestrasPerfil(this.ruta); //32
        getEtiqueta().setText("Importando muestras del perfil...");
        bdImportar.importarSueloMuestras(this.ruta); //33
        getBarraProgreso().setValue(50);
        getBarraProgreso().repaint();
        
        getEtiqueta().setText("Importando Información de carbono e incendios...");
        
        getEtiqueta().setText("Importando Material leñoso caído de 100..."); 
        bdImportar.importarCarbonoMaterialLenioso100(this.ruta); //34
        getEtiqueta().setText("Importando Material leñoso caído de 1000...");
        bdImportar.importarCarbonoMaterialLenioso1000(this.ruta); //35
        getEtiqueta().setText("Importando cubierta vegetal..."); 
        bdImportar.importarCarbonoCubiertaVegetal(this.ruta); //36
        getEtiqueta().setText("Importando cobertura dosel..."); 
        bdImportar.importarCarbonoCoberturaDosel(this.ruta); //37
        getEtiqueta().setText("Importando longitud por componente..."); 
        bdImportar.importarCarbonoLongitudComponente(this.ruta); //38
        
        getBarraProgreso().setValue(55);
        getBarraProgreso().repaint();
        
        getEtiqueta().setText("Importando Arbolado..."); 
        bdImportar.importarTaxonomiaArbolado(this.ruta); //39
        bdImportar.importarArboladoDanioSeveridad(this.ruta); //40
        getEtiqueta().setText("Importando Submuestra..."); 
        bdImportar.importarSubmuestra(this.ruta); //41
        bdImportar.importarSubmuestraTroza(this.ruta); //42
        bdImportar.importarSubmuestraObservaciones(this.ruta);
        getBarraProgreso().setValue(60);
        getBarraProgreso().repaint();
        
        getEtiqueta().setText("Importando Repoblado..."); 
        bdImportar.importarTaxonomiaRepoblado(this.ruta); //43
        getBarraProgreso().setValue(65);
        getBarraProgreso().repaint();
        
        getEtiqueta().setText("Importando Repoblado vegetación menor..."); 
        bdImportar.importarTaxonomiaRepobladoVM(this.ruta); //44
        getBarraProgreso().setValue(70);
        getBarraProgreso().repaint();
        
        getEtiqueta().setText("Importando Sotobosque..."); 
        bdImportar.importarTaxonomiaSotoBosque(this.ruta); //45
        getBarraProgreso().setValue(75);
        getBarraProgreso().repaint();
        
        getEtiqueta().setText("Importando Vegetación mayor gregarios..."); 
        bdImportar.importarTaxonomiaVegetacionMayorGregarios(this.ruta); //46
        bdImportar.importarVegetacionMayorGDanioSeveridad(this.ruta); //47
        getBarraProgreso().setValue(80);
        getBarraProgreso().repaint();
        
        getEtiqueta().setText("Importando Vegetación mayor individual..."); 
        bdImportar.importarTaxonomiaVegetacionMayorIndividual(this.ruta); //48
        bdImportar.importarVegetacionMayorIDanioSeveridad(this.ruta); //49
        getBarraProgreso().setValue(85);
        getBarraProgreso().repaint();
        
        getEtiqueta().setText("Importando Vegetación menor...");
        bdImportar.importarTaxonomiaVegetacionMenor(this.ruta); //50
        bdImportar.importarVegetacionMenorDanioSeveridad(this.ruta); //51
        getBarraProgreso().setValue(90);
        getBarraProgreso().repaint();
        
        getEtiqueta().setText("Importando Colecta botánica...");
        bdImportar.importarTaxonomiaColectaBotanica(this.ruta); //52
        getBarraProgreso().setValue(95);
        getBarraProgreso().repaint();
        
        getEtiqueta().setText("Importando datos de Brigada");
        bdImportar.importarBrigadas(this.ruta); //53
        getBarraProgreso().setValue(97);
        getBarraProgreso().repaint();
        
        getEtiqueta().setText("Finalizando importacion...");
        //bdImportar.importarSecuencias(this.ruta); //54
        //bdImportar.importarUPMRevision(this.ruta);
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
