package gob.conafor.upm.vie;

import gob.conafor.carbono.vie.FrmMaterialLenioso;
import gob.conafor.carbono.vie.FrmLongitudInterceptada;
import gob.conafor.ini.vie.FrmBaseDatos;
import gob.conafor.ini.vie.FrmCapturaModulos;
import gob.conafor.ini.vie.FrmContinuarModulos;
import gob.conafor.ini.vie.FrmEliminarBD;
import gob.conafor.ini.vie.FrmEliminarUPM;
import gob.conafor.ini.vie.FrmImportarBD;
import gob.conafor.ini.vie.FrmMenuReportes;
import gob.conafor.ini.vie.FrmRevisionSitio;
import gob.conafor.ini.vie.FrmRevisionUPM;
import gob.conafor.pc.vie.FrmPuntoControl;
import gob.conafor.sitio.vie.FrmClaveVegetacion;
import gob.conafor.sitio.vie.FrmFotoHemisferica;
import gob.conafor.sitio.vie.FrmObservaciones;
import gob.conafor.sitio.vie.FrmParametrosFisicoQuimicos;
import gob.conafor.sitio.vie.FrmSitio;
import gob.conafor.suelo.vie.FrmCondicionDegradacionSuelo;
import gob.conafor.suelo.vie.FrmDeformacionViento;
import gob.conafor.suelo.vie.FrmErosionHidrica;
import gob.conafor.suelo.vie.FrmHojarascaProfundidad;
import gob.conafor.suelo.vie.FrmMuestrasPerfil;
import gob.conafor.suelo.vie.FrmSuelo;
import gob.conafor.ini.vie.FrmRevisionModulos;
import gob.conafor.reportes.FrmRptColectaBotanica;
import gob.conafor.reportes.FrmRptUPM;
import gob.conafor.sitio.vie.FrmSitioRevision;
import gob.conafor.taxonomia.vie.FrmArboladoA;
import gob.conafor.taxonomia.vie.FrmArboladoD;
import gob.conafor.taxonomia.vie.FrmArboladoG;
import gob.conafor.taxonomia.vie.FrmColectaBotanica;
import gob.conafor.taxonomia.vie.FrmRepoblado;
import gob.conafor.taxonomia.vie.FrmRepobladoVM;
import gob.conafor.taxonomia.vie.FrmSotoBosque;
import gob.conafor.taxonomia.vie.FrmSubmuestra;
import gob.conafor.taxonomia.vie.FrmVegetacionMayorGregarios;
import gob.conafor.taxonomia.vie.FrmVegetacionMayorIndividual;
import gob.conafor.taxonomia.vie.FrmVegetacionMenor;

public class UPMForms {
    public static final FrmInformacionGeneral informacionGeneralUPM = new FrmInformacionGeneral();
    public static final FrmPuntoControl puntoControlUPM = new FrmPuntoControl();
    public static final FrmUPMInaccesible inaccesibleUPM = new FrmUPMInaccesible();
    public static final FrmSitio ubicacionSitio = new FrmSitio();
    public static final FrmSotoBosque sotoBosque = new FrmSotoBosque();
    public static final FrmRepoblado repoblado = new FrmRepoblado();
    public static final FrmArboladoA arbolado = new FrmArboladoA();
    public static final FrmSubmuestra submuestra = new FrmSubmuestra();
    public static final FrmClaveVegetacion claveVegetacion = new FrmClaveVegetacion();
    public static final FrmColectaBotanica colectaBotanica = new FrmColectaBotanica();
    public static final FrmCaracteristicasUPM caracteristicasUPM = new FrmCaracteristicasUPM();
    public static final FrmMaterialLenioso carbono = new FrmMaterialLenioso();
    public static final FrmLongitudInterceptada longitud = new FrmLongitudInterceptada();
    public static final FrmRevisionModulos continuar = new FrmRevisionModulos();
    public static final FrmSuelo suelo = new FrmSuelo();
    public static final FrmCondicionDegradacionSuelo condicionDegradacion = new FrmCondicionDegradacionSuelo();
    public static final FrmErosionHidrica erosionHidrica = new FrmErosionHidrica();
    public static final FrmDeformacionViento deformacionTerreno = new FrmDeformacionViento();
    public static final FrmObservaciones observaciones = new FrmObservaciones();
    public static final FrmArboladoD arboladoD = new FrmArboladoD();
    public static final FrmHojarascaProfundidad hojarascaProfundidad = new FrmHojarascaProfundidad();
    public static final FrmMuestrasPerfil muestrasPerfil = new FrmMuestrasPerfil();
    public static final FrmFotoHemisferica fotoHemisferica = new FrmFotoHemisferica();
    public static final FrmParametrosFisicoQuimicos parametrosFQ = new FrmParametrosFisicoQuimicos();
    public static final FrmRepobladoVM repobladoVM = new FrmRepobladoVM();
    public static final FrmArboladoG arboladoG = new FrmArboladoG();
    public static final FrmVegetacionMenor vegetacionMenor = new FrmVegetacionMenor();
    public static final FrmVegetacionMayorIndividual vegetacionMayorI = new FrmVegetacionMayorIndividual();
    public static final FrmVegetacionMayorGregarios vegetacionMayorG = new FrmVegetacionMayorGregarios();
    public static final FrmRevisionUPM revisionUPM = new FrmRevisionUPM();
    public static final FrmRevisionSitio revisionSitio = new FrmRevisionSitio();
    public static final FrmSitioRevision sitioRevisio = new FrmSitioRevision();
    public static final FrmCapturaModulos capturarModulos = new FrmCapturaModulos();
    public static final FrmRevisionModulos revisionModulos = new FrmRevisionModulos();
    public static final FrmContinuarModulos continuarModulos = new FrmContinuarModulos();
    public static final FrmImportarBD importarBD = new FrmImportarBD();
    public static final FrmMenuReportes reportes = new FrmMenuReportes();
    public static final FrmRptUPM rptUpm = new FrmRptUPM();
    public static final FrmRptColectaBotanica rptColectaBotanica = new FrmRptColectaBotanica();
    public static final FrmBaseDatos baseDatos = new FrmBaseDatos();
    public static final FrmEliminarBD eliminarBD = new FrmEliminarBD();
    public static final FrmEliminarUPM eliminarUPM = new FrmEliminarUPM();
}
