-- SitioID INTEGER, UPMID INTEGER REFERENCES UPM_UPM (UPMID) ON DELETE CASCADE
-- , PRIMARY KEY (SitioID, UPMID)
CREATE TABLE SITIOS_Sitio (
SitioID INTEGER
, UPMID INTEGER REFERENCES UPM_UPM (UPMID) ON DELETE CASCADE
, Sitio INT
, SenialGPS BOOLEAN
, GradosLatitud INT
, MinutosLatitud INT
, SegundosLatitud REAL
, GradosLongitud INT
, MinutosLongitud INT
, SegundosLongitud REAL
, ErrorPresicion INT
, EvidenciaMuestreo BOOLEAN
, Datum VARCHAR (6)
, Azimut INT
, Distancia REAL
, SitioAccesible BOOLEAN
, TipoInaccesibilidad INTEGER
, ExplicacionInaccesibilidad VARCHAR (255)
, CoberturaForestal BOOLEAN
, Condicion BOOLEAN
, ClaveSerieV INT
, FaseSucecional INT
, ArbolFuera BOOLEAN
, Ecotono INT
, CondicionPresenteCampo VARCHAR (255)
, CondicionEcotono VARCHAR (255)
, RepobladoFuera BOOLEAN
, PorcentajeRepoblado INT
, SotobosqueFuera BOOLEAN
, PorcentajeSotobosqueFuera INT
, Observaciones VARCHAR (255)
, HipsometroBrujula INT
, CintaClinometroBrujula INT
, Cuadrante1 INT
, Cuadrante2 INT
, Cuadrante3 INT
, Cuadrante4 INT
, Distancia1 REAL
, Distancia2 REAL
, Distancia3 REAL
, Distancia4 REAL
, Modulo CHAR (1)
, PRIMARY KEY (SitioID, UPMID));


CREATE TABLE SITIOS_CoberturaSuelo (
SitioID INTEGER
, UPMID INTEGER REFERENCES UPM_UPM (UPMID) ON DELETE CASCADE
, CoberturaID INTEGER
, Gramineas INT
, Helechos INT
, Musgos INT
, Liquenes INT
, Hierbas INT
, Roca INT
, SueloDesnudo INT
, Hojarasca INT
, Grava INT
, Otros INT
, Modulo CHAR (1)
, PRIMARY KEY (SitioID, UPMID,CoberturaID));


CREATE TABLE SITIOS_FotografiaHemisferica (
SitioID INTEGER
, UPMID INTEGER REFERENCES UPM_UPM (UPMID) ON DELETE CASCADE
, FotografiaHemisfericaID INTEGER
, CoberturaArborea BOOL
, TomaFotografia BOOL
, Hora DATETIME
, DeclinacionMagnetica INT
, Modulo CHAR (1)
, PRIMARY KEY (SitioID, UPMID,FotografiaHemisfericaID));


CREATE TABLE SITIOS_Observaciones (
SitioID INTEGER
, UPMID INTEGER REFERENCES UPM_UPM (UPMID) ON DELETE CASCADE
, ObservacionesID INTEGER
, FormatoID INT
, Observaciones VARCHAR (255)
, PRIMARY KEY (SitioID, UPMID,ObservacionesID));


CREATE TABLE SITIOS_ParametrosFisicoQuimicos (
SitioID INTEGER
, UPMID INTEGER REFERENCES UPM_UPM (UPMID) ON DELETE CASCADE
, ParametrosFQID INTEGER
, TipoAgua BOOL
, Salinidad REAL
, Temperatura REAL
, ConductividadElectrica REAL
, Ph REAL
, PotencialRedox REAL
, Profundidad REAL
, Observaciones VARCHAR (255)
, Modulo CHAR (1)
, PRIMARY KEY (SitioID, UPMID,ParametrosFQID));


CREATE TABLE SITIOS_Transponder (
SitioID INTEGER
, UPMID INTEGER REFERENCES UPM_UPM (UPMID) ON DELETE CASCADE
, TransponderID INTEGER
, TipoColocacionID INT
, Especifique VARCHAR (255)
, Observaciones VARCHAR (255)
, Modulo CHAR (1)
, PRIMARY KEY (SitioID, UPMID,TransponderID));


CREATE TABLE SUBMUESTRA_Observaciones (
SitioID INTEGER
, UPMID INTEGER REFERENCES UPM_UPM (UPMID) ON DELETE CASCADE
, SubmuestraObservacionesID INTEGER 
, Observaciones VARCHAR (255)
, PRIMARY KEY (SitioID, UPMID,SubmuestraObservacionesID));


CREATE TABLE SUELO_Canalillo (
SitioID INTEGER
, UPMID INTEGER REFERENCES UPM_UPM (UPMID) ON DELETE CASCADE
, CanalilloID INTEGER
, Numero INT
, Ancho REAL
, Profundidad REAL
, Modulo CHAR (1) 
, PRIMARY KEY (SitioID, UPMID,CanalilloID));


CREATE TABLE SUELO_Carcava (
SitioID INTEGER
, UPMID INTEGER REFERENCES UPM_UPM (UPMID) ON DELETE CASCADE
, CarcavaID INTEGER
, Numero INT
, Ancho REAL
, Profundidad REAL
, Modulo CHAR (1), PRIMARY KEY (SitioID, UPMID,CarcavaID));


CREATE TABLE SUELO_CoberturaSuelo (
SitioID INTEGER
, UPMID INTEGER REFERENCES UPM_UPM (UPMID) ON DELETE CASCADE
, CoberturaSueloID INTEGER
, Transecto INT
, Pendiente DECIMAL
, Modulo CHAR (1)
, PRIMARY KEY (SitioID, UPMID,CoberturaSueloID));


CREATE TABLE SUELO_MedicionCanalillos (
SitioID INTEGER
, UPMID INTEGER REFERENCES UPM_UPM (UPMID) ON DELETE CASCADE
, MedicionCanalillosID INTEGER
, NumeroCanalillos INT
, ProfundidadPromedio REAL
, Longitud REAL
, Volumen REAL
, Modulo CHAR (1)
, PRIMARY KEY (SitioID, UPMID,MedicionCanalillosID));


CREATE TABLE SUELO_MedicionCarcavas (
SitioID INTEGER
, UPMID INTEGER REFERENCES UPM_UPM (UPMID) ON DELETE CASCADE
, MedicionCarcavasID INTEGER
, NumeroCarcavas INT
, ProfundidadPromedio REAL
, AnchoPromedio REAL
, Longitud REAL
, Volumen REAL
, Modulo CHAR (1)
, PRIMARY KEY (SitioID, UPMID,MedicionCarcavasID));


CREATE TABLE SUELO_MedicionDunas (
SitioID INTEGER
, UPMID INTEGER REFERENCES UPM_UPM (UPMID) ON DELETE CASCADE
, MedicionDunas INTEGER 
, NumeroDunas INT
, AlturaPromedio REAL
, AnchoPromedio REAL
, Longitud REAL
, Volumen REAL
, Modulo CHAR (1)
, PRIMARY KEY (SitioID, UPMID,MedicionDunas));


CREATE TABLE SUELO_Muestras (
SitioID INTEGER
, UPMID INTEGER REFERENCES UPM_UPM (UPMID) ON DELETE CASCADE
, MuestrasID INTEGER
, ProfundidadID INTEGER
, PesoMuestra DECIMAL
, Lectura1 DECIMAL
, Lectura2 DECIMAL
, Lectura3 DECIMAL
, Lectura4 DECIMAL
, Promedio DECIMAL
, ClaveColecta VARCHAR (50)
, PRIMARY KEY (SitioID, UPMID,MuestrasID));


CREATE TABLE SUELO_MuestrasPerfil (
SitioID INTEGER
, UPMID INTEGER REFERENCES UPM_UPM (UPMID) ON DELETE CASCADE
, MuestrasPerfilID INTEGER
, GradosLatitud INTEGER
, MinutosLatitud INTEGER
, SegundosLatitud DECIMAL
, GradosLongitud INTEGER
, MinutosLongitud INTEGER
, SegundosLongitud DECIMAL
, Elevacion DECIMAL
, DiametroInterno DECIMAL
, DiametroExterno DECIMAL
, Altura DECIMAL
, Observaciones VARCHAR (255)
, PRIMARY KEY (SitioID, UPMID,MuestrasPerfilID));


CREATE TABLE SUELO_ErosionHidricaCarcava (
SitioID INTEGER
, UPMID INTEGER REFERENCES UPM_UPM (UPMID) ON DELETE CASCADE
, ErosionCarcavaID INTEGER
, Medicion INT
, Profundidad REAL
, Ancho REAL
, Distancia REAL
, Azimut INT
, Modulo CHAR (1)
, PRIMARY KEY (SitioID, UPMID,ErosionCarcavaID));


CREATE TABLE SUELO_EvidenciaErosion (
SitioID INTEGER
, UPMID INTEGER REFERENCES UPM_UPM (UPMID) ON DELETE CASCADE
, EvidenciaErosionID INTEGER
, CoberturaSueloID INTEGER
, Punto INT
, Dosel INT
, LecturaTierraID INT
, PRIMARY KEY (SitioID, UPMID,EvidenciaErosionID));


CREATE TABLE SUELO_Costras (
SitioID INTEGER
, UPMID INTEGER REFERENCES UPM_UPM (UPMID) ON DELETE CASCADE
, CostrasID INTEGER
, Numero INT
, Diametro REAL
, Modulo CHAR (1)
, PRIMARY KEY (SitioID, UPMID,CostrasID));


CREATE TABLE SUELO_DeformacionViento (
SitioID INTEGER
, UPMID INTEGER REFERENCES UPM_UPM (UPMID) ON DELETE CASCADE
, DeformacionVientoID INTEGER
, Medicion INT
, Altura REAL
, Diametro REAL
, Longitud REAL
, Distancia REAL
, Azimut INT
, Modulo CHAR (1)
, PRIMARY KEY (SitioID, UPMID,DeformacionVientoID));


CREATE TABLE SUELO_DensidadAparente (
SitioID INTEGER
, UPMID INTEGER REFERENCES UPM_UPM (UPMID) ON DELETE CASCADE
, DensidadAparenteID INTEGER
, ProfundidadReal REAL
, DiametroCilindro REAL
, VolumenMaterial REAL
, PesoTotalSuelo REAL
, PesoTotalMuestra REAL
, Observaciones VARCHAR (255)
, Modulo CHAR (1)
, PRIMARY KEY (SitioID, UPMID,DensidadAparenteID));


CREATE TABLE SUELO_ErosionHidricaCanalillo (
SitioID INTEGER
, UPMID INTEGER REFERENCES UPM_UPM (UPMID) ON DELETE CASCADE
, ErosionCanalilloID INTEGER
, Medicion INT
, Profundidad REAL
, Ancho REAL
, Distancia REAL
, Azimut INT
, Modulo CHAR (1)
, PRIMARY KEY (SitioID, UPMID,ErosionCanalilloID));


CREATE TABLE SUELO_ErosionLaminar (
SitioID INTEGER
, UPMID INTEGER REFERENCES UPM_UPM (UPMID) ON DELETE CASCADE
, ErosionLaminarID INTEGER
, Numero INT
, Ancho REAL
, Largo REAL
, Modulo CHAR (1)
, PRIMARY KEY (SitioID, UPMID,ErosionLaminarID));

CREATE TABLE SUELO_Hojarasca (
SitioID INTEGER
, UPMID INTEGER REFERENCES UPM_UPM (UPMID) ON DELETE CASCADE
, HojarascaID INTEGER
, Punto INT
, TipoHojarascaID INT
, EspesorHO REAL
, EspesorF REAL
, PesoTotalHO REAL
, PesoTotalF REAL
, PesoMuestraHO REAL
, PesoMuestraF REAL
, Observaciones VARCHAR (255)
, ClaveHO VARCHAR (50)
, ClaveF VARCHAR (50)
, Modulo CHAR (1)
, PRIMARY KEY (SitioID, UPMID,HojarascaID));

CREATE TABLE SUELO_LongitudCanalillo (
SitioID INTEGER
, UPMID INTEGER REFERENCES UPM_UPM (UPMID) ON DELETE CASCADE
, LongitudCanalilloID INTEGER
, CampoLongitud INT
, Longitud REAL
, Modulo CHAR (1)
, PRIMARY KEY (SitioID, UPMID,LongitudCanalilloID));

CREATE TABLE SUELO_LongitudCarcava (
SitioID INTEGER
, UPMID INTEGER REFERENCES UPM_UPM (UPMID) ON DELETE CASCADE
, LongitudCarcavaID INTEGER
, CampoLongitud INT
, Longitud REAL
, Modulo CHAR (1)
, PRIMARY KEY (SitioID, UPMID,LongitudCarcavaID));

CREATE TABLE SUELO_LongitudMonticulo (
SitioID INTEGER
, UPMID INTEGER REFERENCES UPM_UPM (UPMID) ON DELETE CASCADE
, LongitudMonticuloID INTEGER
, CampoLongitud INT
, Longitud REAL
, Modulo CHAR (1)
, PRIMARY KEY (SitioID, UPMID,LongitudMonticuloID));

CREATE TABLE SUELO_PavimentoErosion (
SitioID INTEGER
, UPMID INTEGER REFERENCES UPM_UPM (UPMID) ON DELETE CASCADE
, PavimentoErosionID INTEGER
, Numero INT
, Diametro REAL
, Modulo CHAR (1)
, PRIMARY KEY (SitioID, UPMID,PavimentoErosionID));

CREATE TABLE SUELO_Pedestal (
SitioID INTEGER
, UPMID INTEGER REFERENCES UPM_UPM (UPMID) ON DELETE CASCADE
, PedestalID INTEGER
, Numero INT
, Altura REAL
, Modulo CHAR (1)
, PRIMARY KEY (SitioID, UPMID,PedestalID));

CREATE TABLE SUELO_Profundidad (
SitioID INTEGER
, UPMID INTEGER REFERENCES UPM_UPM (UPMID) ON DELETE CASCADE
, ProfundidadSueloID INTEGER
, Punto INT
, Profundidad030 REAL
, Profundidad3060 REAL
, PesoTotal030 REAL
, PesoTotal3060 REAL
, Equipo030 VARCHAR (255)
, Equipo3060 VARCHAR (255)
, Observaciones VARCHAR (255)
, Clave030 VARCHAR (50)
, Clave3060 VARCHAR (50)
, Modulo CHAR (1)
, PRIMARY KEY (SitioID, UPMID,ProfundidadSueloID));

CREATE TABLE SUELO_Suelo (
SitioID INTEGER
, UPMID INTEGER REFERENCES UPM_UPM (UPMID) ON DELETE CASCADE
, SueloID INTEGER
, UsoSueloID INT
, OtroUsoSuelo VARCHAR (100)
, Espesor REAL
, PendienteDominante INT
, Observaciones VARCHAR (255)
, NumeroCanalillos INT
, ProfundidadPromedioCanalillos DECIMAL
, AnchoPromedioCanalillos DECIMAL
, LongitudCanalillos DECIMAL
, VolumenCanalillos DECIMAL
, NumeroCarcavas INT
, ProfundidadPromedioCarcavas DECIMAL
, AnchoPromedioCarcavas DECIMAL
, LongitudCarcavas DECIMAL
, VolumenCarcavas DECIMAL
, NumeroMonticulos INT
, AlturaPromedioMonticulos DECIMAL
, AnchoPromedioMonticulos DECIMAL
, LongitudPromedioMonticulos DECIMAL
, VolumenMonticulos DECIMAL
, Modulo CHAR (1)
, PRIMARY KEY (SitioID, UPMID,SueloID));

CREATE TABLE SUELO_VarillaErosion (
SitioID INTEGER
, UPMID INTEGER REFERENCES UPM_UPM (UPMID) ON DELETE CASCADE
, VarillaID INTEGER
, NoVarilla INT
, Azimut INT
, Distancia REAL
, Profundidad REAL
, Modulo CHAR (1)
, PRIMARY KEY (SitioID, UPMID,VarillaID));

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE CARBONO_CoberturaDosel (
SitioID INTEGER
, UPMID INTEGER REFERENCES UPM_UPM (UPMID) ON DELETE CASCADE
, CoberturaDoselID INTEGER
, Transecto INT
, Punto1 BOOL
, Punto2 BOOL
, Punto3 BOOL
, Punto4 BOOL
, Punto5 BOOL
, Punto6 BOOL
, Punto7 BOOL
, Punto8 BOOL
, Punto9 BOOL
, Punto10 BOOL
, Modulo CHAR (1) 
, PRIMARY KEY (SitioID, UPMID,CoberturaDoselID));

CREATE TABLE CARBONO_CubiertaVegetal (
SitioID INTEGER
, UPMID INTEGER REFERENCES UPM_UPM (UPMID) ON DELETE CASCADE
, CubiertaVegetalID INTEGER
, Transecto INT
, ComponenteID INT
, Altura5 REAL
, Altura10 REAL
, Modulo CHAR (1) 
, PRIMARY KEY (SitioID, UPMID,CubiertaVegetalID));

CREATE TABLE CARBONO_LongitudComponente (
SitioID INTEGER
, UPMID INTEGER REFERENCES UPM_UPM (UPMID) ON DELETE CASCADE
, LongitudComponenteID INTEGER
, Consecutivo INTEGER
, Transecto INT
, ComponenteID INT
, FamiliaID INT
, GeneroID INT
, EspecieID INT
, InfraespecieID INT
, NombreComun VARCHAR (100)
, EsColecta BOOL
, Segmento1 INTEGER
, Segmento2 INTEGER
, Segmento3 INTEGER
, Segmento4 INTEGER
, Segmento5 INTEGER
, Segmento6 INTEGER
, Segmento7 INTEGER
, Segmento8 INTEGER
, Segmento9 INTEGER
, Segmento10 INTEGER
, Total INTEGER
, Modulo CHAR (1)
, ClaveColecta VARCHAR (50) DEFAULT NULL 
, PRIMARY KEY (SitioID, UPMID,LongitudComponenteID));

CREATE TABLE CARBONO_MaterialLenioso100 (
SitioID INTEGER
, UPMID INTEGER REFERENCES UPM_UPM (UPMID) ON DELETE CASCADE
, MaterialLenioso100ID INTEGER
, Transecto INT
, Pendiente INT
, UnaHora INT
, DiezHoras INT
, CienHoras INT
, Modulo CHAR (1) 
, PRIMARY KEY (SitioID, UPMID,MaterialLenioso100ID));

CREATE TABLE CARBONO_MaterialLenioso1000 (
SitioID INTEGER
, UPMID INTEGER REFERENCES UPM_UPM (UPMID) ON DELETE CASCADE
, MaterialLenioso1000ID INTEGER
, Transecto INT
, Diametro REAL
, Grado INT
, Modulo CHAR (1) 
, PRIMARY KEY (SitioID, UPMID,MaterialLenioso1000ID));

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE ARBOLADO_DanioSeveridad (
SitioID INTEGER
, UPMID INTEGER REFERENCES UPM_UPM (UPMID) ON DELETE CASCADE
, DanioSeveridadID INTEGER
, ArboladoID INTEGER 
, NumeroDanio INT
, AgenteDanioID INT
, SeveridadID INT
, PRIMARY KEY (SitioID, UPMID,DanioSeveridadID));

CREATE TABLE ARBOLADO_Submuestra (
SitioID INTEGER
, UPMID INTEGER REFERENCES UPM_UPM (UPMID) ON DELETE CASCADE
, SubmuestraID INTEGER
, ArboladoID INTEGER 
, DiametroBasal REAL
, Edad INT
, NumeroAnillos25 INT
, LongitudAnillos10 REAL
, GrozorCorteza REAL
, Modulo CHAR (1)
, PRIMARY KEY (SitioID, UPMID,SubmuestraID));

CREATE TABLE ARBOLADO_Troza (
SitioID INTEGER
, UPMID INTEGER REFERENCES UPM_UPM (UPMID) ON DELETE CASCADE
, TrozaID INTEGER
, SubmuestraID INTEGER
, NoTroza INT
, TipoTrozaID INT
, Modulo CHAR (1)
, PRIMARY KEY (SitioID, UPMID,TrozaID));

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE TAXONOMIA_Arbolado (
SitioID INTEGER
, UPMID INTEGER REFERENCES UPM_UPM (UPMID) ON DELETE CASCADE
, ArboladoID INTEGER
, Consecutivo INTEGER
, NoIndividuo INT
, NoRama INT
, Azimut INT
, Distancia REAL
, FamiliaID INT
, GeneroID INT
, EspecieID INT
, InfraespecieID INT
, NombreComun VARCHAR (200)
, EsColecta BOOLEAN
, EsSubmuestra BOOLEAN
, FormaVidaID INT
, FormaFusteID INT
, CondicionID INT
, MuertoPieID INT
, GradoPutrefaccionID INT
, TipoToconID INT
, DiametroNormal REAL
, DiametroBasal REAL DEFAULT NULL
, AlturaTotal REAL
, AnguloInclinacion INTEGER
, AlturaFusteLimpio REAL
, AlturaComercial REAL
, DiametroCopaNS REAL
, DiametroCopaEO REAL
, ProporcionCopaVivaID INT
, ExposicionCopaID INT
, PosicionCopaID INT
, DensidadCopaID INT
, MuerteRegresivaID INT
, TransparenciaFollajeID INT
, VigorID INT
, NivelVigorID INT
, Modulo CHAR (1)
, ClaveColecta VARCHAR (50) 
, PRIMARY KEY (SitioID, UPMID,ArboladoID));

CREATE TABLE TAXONOMIA_ColectaBotanica ( 
SitioID INTEGER
, UPMID INTEGER REFERENCES UPM_UPM (UPMID) ON DELETE CASCADE
, ColectaBotanicaID INTEGER
, FamiliaID INTEGER
, GeneroID INTEGER
, EspecieID INTEGER
, InfraespecieID INTEGER
, NombreComun VARCHAR (100)
, Sitio INTEGER
, SeccionID INTEGER
, Consecutivo INTEGER
, ClaveColecta VARCHAR (50)
, ContraFuertes BOOL
, Exudado BOOL
, IndicarExudado VARCHAR (255)
, Color BOOL
, IndicarColor VARCHAR (255)
, CambioColor BOOL
, AceitesVolatiles BOOL
, ColorFlor BOOL
, IndicarColorFlor VARCHAR (100)
, HojasTejidoVivo BOOL
, FotoFlor BOOL
, FotoFruto BOOL
, FotoHojasArriba BOOL
, FotoHojasAbajo BOOL
, FotoArbolCompleto BOOL
, FotoCorteza BOOL
, VirutaIncluida BOOL
, CortezaIncluida BOOL
, MaderaIncluida BOOL
, Observaciones VARCHAR (255)
, Modulo CHAR (1) 
, PRIMARY KEY (SitioID, UPMID,ColectaBotanicaID));

CREATE TABLE TAXONOMIA_Repoblado (
SitioID INTEGER
, UPMID INTEGER REFERENCES UPM_UPM (UPMID) ON DELETE CASCADE
, RepobladoID INTEGER
, Consecutivo INTEGER
, FamiliaID INTEGER
, GeneroID INT
, EspecieID INT
, InfraespecieID INT
, EsColecta INT
, NombreComun VARCHAR (50)
, Frecuencia025150 INT
, Edad025150 INT
, Frecuencia151275 INT
, Edad151275 INT
, Frecuencia275 INT
, Edad275 INT
, VigorID INT
, DanioID INT
, PorcentajeDanio INT
, Modulo CHAR (1)
, ClaveColecta VARCHAR (50) 
, PRIMARY KEY (SitioID, UPMID,RepobladoID));

CREATE TABLE TAXONOMIA_RepobladoVM (
SitioID INTEGER
, UPMID INTEGER REFERENCES UPM_UPM (UPMID) ON DELETE CASCADE
, RepobladoVMID INTEGER
, Consecutivo INT
, FormaVidaID INT
, FamiliaID INT
, GeneroID INT
, EspecieID INT
, InfraespecieID INT
, NombreComun VARCHAR (100)
, EsColecta INT
, Frecuencia50 INT
, PorcentajeCobertura50 INT
, Frecuencia51200 INT
, PorcentajeCobertura51200 INT
, Frecuencia200 INT
, PorcentajeCobertura200 INT
, VigorID INT
, Modulo CHAR (1)
, ClaveColecta VARCHAR (50) 
, PRIMARY KEY (SitioID, UPMID,RepobladoVMID));

CREATE TABLE TAXONOMIA_SotoBosque (
SitioID INTEGER
, UPMID INTEGER REFERENCES UPM_UPM (UPMID) ON DELETE CASCADE
, SotoBosqueID INTEGER
, Consecutivo INTEGER
, FamiliaID INTEGER
, GeneroID INTEGER
, EspecieID INTEGER
, InfraespecieID INT
, NombreComun VARCHAR (100)
, EsColecta BOOL
, Frecuencia025150 INT
, Cobertura025150 INT
, Frecuencia151275 INT
, Cobertura151275 INT
, Frecuencia275 INT
, Cobertura275 INT
, VigorID INT
, DanioID INT
, PorcentajeDanio INT
, Modulo CHAR (1)
, ClaveColecta VARCHAR (50) 
, PRIMARY KEY (SitioID, UPMID,SotoBosqueID));

CREATE TABLE TAXONOMIA_VegetacionMayorGregarios (
SitioID INTEGER
, UPMID INTEGER REFERENCES UPM_UPM (UPMID) ON DELETE CASCADE
, VegetacionMayorID INTEGER
, Consecutivo INT
, NoIndividuo INT
, FormaVidaID INT
, CondicionID INT
, FamiliaID INT
, GeneroID INT
, EspecieID INT
, InfraespecieID INT
, EsColecta BOOL
, NombreComun VARCHAR (50)
, FormaCrecimientoID INT
, DensidadColoniaID INT
, AlturaTotalMaxima REAL
, AlturaTotalMedia REAL
, AlturaTotalMinima REAL
, DiametroCoberturaMayor REAL
, DiametroCoberturaMenor REAL
, VigorID INT
, Modulo CHAR (1)
, ClaveColecta VARCHAR (50) 
, PRIMARY KEY (SitioID, UPMID,VegetacionMayorID));

CREATE TABLE TAXONOMIA_VegetacionMayorIndividual (
SitioID INTEGER
, UPMID INTEGER REFERENCES UPM_UPM (UPMID) ON DELETE CASCADE
, VegetacionMayorID INTEGER
, Consecutivo INTEGER
, NoIndividuo INT
, FormaVidaID INT
, CondicionID INT
, FamiliaID INT
, GeneroID INT
, EspecieID INT
, InfraespecieID INT
, EsColecta BOOL
, NombreComun VARCHAR (100)
, FormaGeometricaID INT
, DensidadFollajeID INT
, DiametroBase REAL
, AlturaTotal REAL
, DiametroCoberturaMayor REAL
, DiametroCoberturaMenor REAL
, VigorID INT
, Modulo CHAR (1)
, ClaveColecta VARCHAR (50) 
, PRIMARY KEY (SitioID, UPMID,VegetacionMayorID));

CREATE TABLE TAXONOMIA_VegetacionMenor (
SitioID INTEGER
, UPMID INTEGER REFERENCES UPM_UPM (UPMID) ON DELETE CASCADE
, VegetacionMenorID INTEGER
, Consecutivo INT
, FamiliaID INT
, GeneroID INT
, EspecieID INT
, InfraespecieID INT
, NombreComun VARCHAR (50)
, EsColecta BOOL
, FormaVidaID INT
, CondicionID INT
, Numero0110 INT
, Numero1125 INT
, Numero2650 INT
, Numero5175 INT
, Numero76100 INT
, Numero101125 INT
, Numero126150 INT
, Numero150 INT
, PorcentajeCobertura INT
, VigorID INT
, Modulo CHAR (1)
, ClaveColecta VARCHAR (50) 
, PRIMARY KEY (SitioID, UPMID,VegetacionMenorID));

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE VEGETACIONMAYORG_DanioSeveridad (
SitioID INTEGER
, UPMID INTEGER REFERENCES UPM_UPM (UPMID) ON DELETE CASCADE
, DanioSeveridadID INTEGER
, VegetacionMayorID INTEGER
, NumeroDanio INTEGER
, AgenteDanioID INT
, SeveridadID INT 
, PRIMARY KEY (SitioID, UPMID,DanioSeveridadID));

CREATE TABLE VEGETACIONMAYORI_DanioSeveridad (
SitioID INTEGER
, UPMID INTEGER REFERENCES UPM_UPM (UPMID) ON DELETE CASCADE
, DanioSeveridadID INTEGER
, VegetacionMayorID INTEGER 
, NumeroDanio INT
, AgenteDanioID INT
, SeveridadID INT 
, PRIMARY KEY (SitioID, UPMID,DanioSeveridadID));

CREATE TABLE VEGETACIONMENOR_DanioSeveridad (
SitioID INTEGER
, UPMID INTEGER REFERENCES UPM_UPM (UPMID) ON DELETE CASCADE
, DanioSeveridadVMID INTEGER 
, VegetacionMenorID INTEGER
, NumeroDanio INT
, AgenteDanioID INT
, SeveridadID INT 
, PRIMARY KEY (SitioID, UPMID,DanioSeveridadVMID));

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE REPOBLADO_AgenteDanio (
SitioID INTEGER
, UPMID INTEGER REFERENCES UPM_UPM (UPMID) ON DELETE CASCADE
, RepobladoDanioID INTEGER
, RepobladoVMID INTEGER
, NumeroDanio INT
, AgenteDanioID INT
, SeveridadID INT
, PRIMARY KEY (SitioID, UPMID,RepobladoDanioID));

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE UPM_Contacto (ContactoID INTEGER
, UPMID INT REFERENCES UPM_UPM (UPMID) ON DELETE CASCADE
, TipoContacto BOOLEAN
, Nombre VARCHAR (100)
, Direccion VARCHAR (100)
, TipoTelefono BOOLEAN
, NumeroTelefono VARCHAR (50)
, TieneCorreo BOOLEAN
, DireccionCorreo VARCHAR (100)
, TieneRadio BOOLEAN
, Canal VARCHAR (10)
, Frecuencia VARCHAR (10)
, Observaciones VARCHAR (255)
, Modulo CHAR (1)
, PRIMARY KEY (UPMID,ContactoID));

CREATE TABLE UPM_Epifita (EpifitaID INTEGER 
, UPMID INT REFERENCES UPM_UPM (UPMID) ON DELETE CASCADE
, ClaseTipoID INT
, PresenciaTroncosID INT
, PresenciaRamasID INT
, Modulo VARCHAR (1)
, PRIMARY KEY (UPMID,EpifitaID));

CREATE TABLE UPM_Brigada (BrigadaID INTEGER 
, UPMID INT REFERENCES UPM_UPM (UPMID) ON DELETE CASCADE
, BrigadistaID INT
, PuestoID INT
, EmpresaID INTEGER
, PRIMARY KEY (UPMID,BrigadistaID,BrigadaID));