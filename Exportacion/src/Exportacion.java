import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Exportacion {
	
	private Connection bdl;
	private Connection bds;
	
	

	public void exportarUPM(){
		
		ResultSet rst;
		
		String fechaInicio,fechaFin,predio,paraje,datum,otroTipoInaccesibilidad,explicacionInaccesibilidad;
		boolean accesible;
		
		int upmid,tipoUpmid,pendienteRepresentativa,fisiografiaID,exposicionID,tipoTenenciaID,gradosLatitud,minutosLatitud,gradosLongitud,minutosLongitud;
		int azimut,errorPresicion,tipoInaccesibilidadID,informacionContacto,accesibleInt;
		
		float altitud,segundosLatitud,segundosLongitud,distancia;
		
		/*Consulta SELECT*/
		String selectQuery="SELECT UPMID,FechaInicio,FechaFin,TipoUPMID,Altitud,PendienteRepresentativa,FisiografiaID,ExposicionID,"
				+ "Predio,Paraje,TipoTenenciaID,Accesible,GradosLatitud,MinutosLatitud,SegundosLatitud,GradosLongitud,MinutosLongitud,"
				+ "SegundosLongitud,Datum,ErrorPresicion,Azimut,Distancia,TipoInaccesibilidadID,OtroTipoInaccesibilidad,ExplicacionInaccesibilidad,"
				+ "InformacionContacto FROM UPM_UPM";
		
		/*consulta Insert*/
		
		/*Conexiones a bd local y server*/
		bdl = LocalConnection.getConnection();
		bds = ServerConnection.getConnection();
		
		
		try {
			
			Statement localStatment = bdl.createStatement();
			Statement serverStatment=bds.createStatement();
			
			/*manda el Query al Resulset*/
			rst= localStatment.executeQuery(selectQuery);
			
			while(rst.next()){
				
												/*inicia recoleccion de datos*/
				
				upmid=rst.getInt("UPMID");
				fechaInicio=rst.getString("FechaInicio");
				fechaFin=rst.getString("FechaFin");
				tipoUpmid=rst.getInt("TipoUPMID");
				altitud=rst.getFloat("Altitud");
				pendienteRepresentativa=rst.getInt("PendienteRepresentativa");
				fisiografiaID=rst.getInt("FisiografiaID");
				exposicionID=rst.getInt("ExposicionID");
				predio=rst.getString("Predio");
				paraje=rst.getString("Paraje");
				tipoTenenciaID=rst.getInt("TipoTenenciaID");
				accesible=rst.getBoolean("Accesible");
				if(accesible==true){
					accesibleInt=1;
				}
				else{
					accesibleInt=0;
				}
				gradosLatitud=rst.getInt("GradosLatitud");
				minutosLatitud=rst.getInt("MinutosLatitud");
				segundosLatitud=rst.getFloat("SegundosLatitud");
				gradosLongitud=rst.getInt("GradosLongitud");
				minutosLongitud=rst.getInt("MinutosLongitud");
				segundosLongitud=rst.getFloat("SegundosLongitud");
				datum=rst.getString("Datum");
				errorPresicion=rst.getInt("ErrorPresicion");
				azimut=rst.getInt("Azimut");
				distancia=rst.getFloat("Distancia");
				tipoInaccesibilidadID=rst.getInt("TipoInaccesibilidadID");
				otroTipoInaccesibilidad=rst.getString("OtroTipoInaccesibilidad");
				explicacionInaccesibilidad=rst.getString("ExplicacionInaccesibilidad");
				informacionContacto=rst.getInt("InformacionContacto");
				
													/*termina recoleccion de datos*/
				
													/*inicia insercion de datos*/
				
				
				String insertQuery="INSERT INTO Upm.UPM(UPMID,FechaInicio,FechaFin,TipoUPMID,Altitud,PendienteRepresentativa,FisiografiaID,ExposicionID,"
						+ "Predio,Paraje,TipoTenenciaID,Accesible,GradosLatitud,MinutosLatitud,SegundosLatitud,GradosLongitud,MinutosLongitud,"
						+ "SegundosLongitud,Datum,ErrorPresicion,Azimut,Distancia,TipoInaccesibilidadID,OtroTipoInaccesibilidad,ExplicacionInaccesibilidad,"
						+ "InformacionContacto) VALUES ("+upmid+",'"+fechaInicio+"','"+fechaFin+"',"+tipoUpmid+","+altitud+","+pendienteRepresentativa+","+fisiografiaID+","
						+ exposicionID+",'"+predio+"','"+paraje+"',"+tipoTenenciaID+","+accesibleInt+","+gradosLatitud+","+minutosLatitud+","+segundosLatitud+","
						+ gradosLongitud+","+minutosLongitud+","+segundosLongitud+",'"+datum+"',"+errorPresicion+","+azimut+","+distancia+","+tipoInaccesibilidadID+",'"
						+ otroTipoInaccesibilidad+"','"+explicacionInaccesibilidad+"',"+informacionContacto+")";		
				
													/*Termina insercion de datos*/
				
				
				serverStatment.executeUpdate(insertQuery);
				JOptionPane.showMessageDialog(null, "Datos insertados en el servidor Correctamente");
				serverStatment.close();
				
			}
			
			localStatment.close();
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "no se pudo hacer la consulta "+ e);
		}
		
	}
	
	public void exportarUPMBrigada(){
		
		ResultSet rst;
		

		
		int brigadaID,upmID,brigadistaID,puestoID,empresaID;
	
		
		/*Consulta SELECT*/
		String selectQuery="SELECT BrigadaID,UPMID,BrigadistaID,PuestoID,EmpresaID FROM UPM_Brigada";
		
		
		
		/*Conexiones a bd local y server*/
		bdl = LocalConnection.getConnection();
		bds = ServerConnection.getConnection();
		
		
		try {
			
			Statement localStatment = bdl.createStatement();
			Statement serverStatment=bds.createStatement();
			
			/*manda el Query al Resulset*/
			rst= localStatment.executeQuery(selectQuery);
			
			while(rst.next()){
				
													/*inicia recoleccion de datos*/
				
				brigadaID=rst.getInt("BrigadaID");
				upmID=rst.getInt("UPMID");
				brigadistaID=rst.getInt("BrigadistaID");
				puestoID=rst.getInt("PuestoID");
				empresaID=rst.getInt("EmpresaID");
				
							
													/*termina recoleccion de datos*/
				
													/*inicia insercion de datos*/
				
				
				String insertQuery="INSERT INTO Upm.Brigada(BrigadaID,UPMID,BrigadistaID,PuestoID,EmpresaID) VALUES ("+brigadaID+","+upmID+","
															+brigadistaID+","+puestoID+","+empresaID+")";		
				
													/*Termina insercion de datos*/
				
				
				serverStatment.executeUpdate(insertQuery);
				JOptionPane.showMessageDialog(null, "Datos insertados en el servidor Correctamente");
				serverStatment.close();
				
				
			}
			
			localStatment.close();
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "no se pudo hacer la consulta "+ e);
		}
		
	}
	
	public void exportarUPMContacto(){
		
		ResultSet rst;
		

		int contactoID,upmID,tipoContacto,tieneCorreo,tieneRadio,tipoTelefono ;
		String nombre,direccion,numeroTelefono,direccionCorreo,canal,frecuencia,observaciones;
	
				
		
		/*Consulta SELECT*/
		String selectQuery="SELECT ContactoID,UPMID,TipoContacto,Nombre,Direccion,TipoTelefono,NumeroTelefono,TieneCorreo"
							+",DireccionCorreo,TieneRadio,Canal,Frecuencia,Observaciones FROM UPM_Contacto";
		
		
		
		/*Conexiones a bd local y server*/
		bdl = LocalConnection.getConnection();
		bds = ServerConnection.getConnection();
		
		
		try {
			
			Statement localStatment = bdl.createStatement();
			Statement serverStatment=bds.createStatement();
			
			/*manda el Query al Resulset*/
			rst= localStatment.executeQuery(selectQuery);
			
			while(rst.next()){
				
													/*inicia recoleccion de datos*/

				contactoID=rst.getInt("ContactoID");
				upmID=rst.getInt("UPMID");
				tipoContacto=rst.getInt("TipoContacto");
				nombre=rst.getString("Nombre");
				direccion=rst.getString("Direccion");
				tipoTelefono=rst.getInt("TipoTelefono");
				numeroTelefono=rst.getString("NumeroTelefono");
				tieneCorreo=rst.getInt("TieneCorreo");
				direccionCorreo=rst.getString("DireccionCorreo");
				tieneRadio=rst.getInt("TieneRadio");
				canal=rst.getString("Canal");
				frecuencia=rst.getString("Frecuencia");
				observaciones=rst.getString("Observaciones");

														/*termina recoleccion de datos*/
				
													/*inicia insercion de datos*/

				String insertQuery="INSERT INTO Upm.Contacto(ContactoID,UPMID,TipoContacto,Nombre,Direccion,TipoTelefono,NumeroTelefono,TieneCorreo,"
							+"DireccionCorreo,TieneRadio,Canal,Frecuencia,Observaciones) VALUES ("+contactoID+","+upmID+","+tipoContacto+",'"+nombre+"','"
							+direccion+"',"+tipoTelefono+",'"+numeroTelefono+"',"+tieneCorreo+",'"+direccionCorreo+"',"+tieneRadio+",'"+canal+"','"+frecuencia
							+"','"+observaciones+"')";		
				
													/*Termina insercion de datos*/
				
				
				serverStatment.executeUpdate(insertQuery);
				JOptionPane.showMessageDialog(null, "Datos insertados en el servidor Correctamente");
				serverStatment.close();
				
			}
			
			localStatment.close();
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "no se pudo hacer la consulta "+ e);
		}
	}
	
	public void exportarUPMEpifita(){
		
		ResultSet rst;
		

		int epifitaID,upmID,claseTipoID,presenciaTroncosID,presenciaRamasID;
	
		
		/*Consulta SELECT*/
		String selectQuery="SELECT EpifitaID,UPMID,ClaseTipoID,PresenciaTroncosID,PresenciaRamasID FROM UPM_Epifita";

		/*Conexiones a bd local y server*/
		bdl = LocalConnection.getConnection();
		bds = ServerConnection.getConnection();
		
		
		try {
			
			Statement localStatment = bdl.createStatement();
			Statement serverStatment=bds.createStatement();
			
			/*manda el Query al Resulset*/
			rst= localStatment.executeQuery(selectQuery);
			
			while(rst.next()){
				
													/*inicia recoleccion de datos*/

				epifitaID=rst.getInt("EpifitaID");
				upmID=rst.getInt("UPMID");
				claseTipoID=rst.getInt("ClaseTipoID");
				presenciaTroncosID=rst.getInt("PresenciaTroncosID");
				presenciaRamasID=rst.getInt("PresenciaRamasID");
				
															/*termina recoleccion de datos*/
				
													/*inicia insercion de datos*/
				
				

				String insertQuery="INSERT INTO Upm.Epifita(EpifitaID,UPMID,ClaseTipoID,PresenciaTroncosID,PresenciaRamasID)"
				+"VALUES ("+epifitaID+","+upmID+","+claseTipoID+","+presenciaTroncosID+","+presenciaRamasID+")";		
				
													/*Termina insercion de datos*/
				
				
				serverStatment.executeUpdate(insertQuery);
				JOptionPane.showMessageDialog(null, "Datos insertados en el servidor Correctamente");
				serverStatment.close();
				
			}
			
			localStatment.close();
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "no se pudo hacer la consulta "+ e);
		}
	}
	
	public void exportarSITIOSSitio() {
		
		ResultSet rst;
		

		int sitioID,upmID,sitio,senialGps,gradosLatitud,minutosLatitud,gradosLongitud,minutosLongitud,errorPresicion,evidenciaMuestreo ;
		int azimut,sitioAccesible,tipoInaccesibilidad,coberturaForestal,condicion,claveSerieV,faseSucecional,arbolFuera,ecotono,repobladoFuera ;
		int porcentajeRepoblado,sotobosqueFuera,porcentajeSotobosqueFuera,hipsometroBrujula,cintaClinometroBrujula ;
		int cuadrante1,cuadrante2,cuadrante3,cuadrante4;
		
		String  datum,explicacionInaccesibilidad,condicionPresenteCampo,condicionEcotono,observaciones ;
		
		float segundosLatitud,segundosLongitud,distancia,distancia1,distancia2,distancia3,distancia4 ;
	
		
		/*Consulta SELECT*/
		String selectQuery="SELECT SitioID,UPMID,Sitio,SenialGPS,GradosLatitud,MinutosLatitud,SegundosLatitud,"
		+"GradosLongitud,MinutosLongitud,SegundosLongitud,ErrorPresicion,EvidenciaMuestreo,Datum,Azimut,Distancia,"
		+"SitioAccesible,TipoInaccesibilidad,ExplicacionInaccesibilidad,CoberturaForestal,Condicion,ClaveSerieV,"
		+"FaseSucecional,ArbolFuera,Ecotono,CondicionPresenteCampo,CondicionEcotono,RepobladoFuera,PorcentajeRepoblado,"
		+"SotobosqueFuera,PorcentajeSotobosqueFuera,Observaciones,HipsometroBrujula,CintaClinometroBrujula,Cuadrante1,"
		+"Cuadrante2,Cuadrante3,Cuadrante4,Distancia1,Distancia2,Distancia3,Distancia4"
		+" FROM SITIOS_Sitio";

		/*Conexiones a bd local y server*/
		bdl = LocalConnection.getConnection();
		bds = ServerConnection.getConnection();
		
		
		try {
			
			Statement localStatment = bdl.createStatement();
			Statement serverStatment=bds.createStatement();
			
			/*manda el Query al Resulset*/
			rst= localStatment.executeQuery(selectQuery);
			
			while(rst.next()){
				
													/*inicia recoleccion de datos*/


				sitioID=rst.getInt("SitioID");
				upmID=rst.getInt("UPMID");
				sitio=rst.getInt("Sitio");
				senialGps=rst.getInt("SenialGPS");
				gradosLatitud=rst.getInt("GradosLatitud");
				minutosLatitud=rst.getInt("MinutosLatitud");
				gradosLongitud=rst.getInt("GradosLongitud");
				minutosLongitud=rst.getInt("MinutosLongitud");
				errorPresicion=rst.getInt("ErrorPresicion");
				evidenciaMuestreo=rst.getInt("EvidenciaMuestreo");
				azimut=rst.getInt("Azimut");
				sitioAccesible=rst.getInt("SitioAccesible");
				tipoInaccesibilidad=rst.getInt("TipoInaccesibilidad");
				coberturaForestal=rst.getInt("CoberturaForestal");
				condicion=rst.getInt("Condicion");
				claveSerieV=rst.getInt("ClaveSerieV");
				faseSucecional=rst.getInt("FaseSucecional");
				arbolFuera=rst.getInt("ArbolFuera");
				ecotono=rst.getInt("Ecotono");
				repobladoFuera=rst.getInt("RepobladoFuera");
				porcentajeRepoblado=rst.getInt("PorcentajeRepoblado");
				sotobosqueFuera=rst.getInt("SotobosqueFuera");
				porcentajeSotobosqueFuera=rst.getInt("PorcentajeSotobosqueFuera");
				hipsometroBrujula=rst.getInt("HipsometroBrujula");
				cintaClinometroBrujula=rst.getInt("CintaClinometroBrujula");
				cuadrante1=rst.getInt("Cuadrante1");
				cuadrante2=rst.getInt("Cuadrante2");
				cuadrante3=rst.getInt("Cuadrante3");
				cuadrante4=rst.getInt("Cuadrante4");


				datum=rst.getString("Datum");
				explicacionInaccesibilidad=rst.getString("ExplicacionInaccesibilidad");
				condicionPresenteCampo=rst.getString("CondicionPresenteCampo");
				condicionEcotono=rst.getString("CondicionEcotono");
				observaciones=rst.getString("Observaciones");


				segundosLongitud=rst.getFloat("SegundosLongitud");
				segundosLatitud=rst.getFloat("SegundosLatitud");
				distancia=rst.getFloat("Distancia");
				distancia1=rst.getFloat("Distancia1");
				distancia2=rst.getFloat("Distancia2");
				distancia3=rst.getFloat("Distancia3");
				distancia4=rst.getFloat("Distancia4");

				
															/*termina recoleccion de datos*/
				
													/*inicia insercion de datos*/
				
				String insertQuery="INSERT INTO Sitios.Sitio (SitioID,UPMID,Sitio,SenialGPS,GradosLatitud,MinutosLatitud,SegundosLatitud,"
									+"GradosLongitud,MinutosLongitud,SegundosLongitud,ErrorPresicion,EvidenciaMuestreo,Datum,Azimut,Distancia,"
									+"SitioAccesible,TipoInaccesibilidad,ExplicacionInaccesibilidad,CoberturaForestal,Condicion,ClaveSerieV,"
									+"FaseSucecional,ArbolFuera,Ecotono,CondicionPresenteCampo,CondicionEcotono,RepobladoFuera,PorcentajeRepoblado,"
									+"SotobosqueFuera,PorcentajeSotobosqueFuera,Observaciones,HipsometroBrujula,CintaClinometroBrujula,Cuadrante1,"
									+"Cuadrante2,Cuadrante3,Cuadrante4,Distancia1,Distancia2,Distancia3,Distancia4 )"
									+" VALUES ("+sitioID+","+upmID+","+sitio+","+senialGps+","+gradosLatitud+","+minutosLatitud+","+segundosLatitud+","+
									gradosLongitud+","+minutosLongitud+","+segundosLongitud+","+errorPresicion+","+evidenciaMuestreo+",'"+datum+"',"+
									azimut+","+distancia+","+sitioAccesible+","+tipoInaccesibilidad+",'"+explicacionInaccesibilidad+"',"+coberturaForestal+","+
									condicion+","+claveSerieV+","+faseSucecional+","+arbolFuera+","+ecotono+",'"+condicionPresenteCampo+"','"+condicionEcotono+"',"+
									repobladoFuera+","+porcentajeRepoblado+","+sotobosqueFuera+","+porcentajeSotobosqueFuera+",'"+observaciones+"',"+
									hipsometroBrujula+","+cintaClinometroBrujula+","+cuadrante1+","+cuadrante2+","+cuadrante3+","+cuadrante4+","+distancia1+","+
									distancia2+","+distancia3+","+distancia4+")";		
				
													/*Termina insercion de datos*/
				
				
				serverStatment.executeUpdate(insertQuery);
				JOptionPane.showMessageDialog(null, "Datos insertados en el servidor Correctamente");
				serverStatment.close();
				
			}
			
			localStatment.close();
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "no se pudo hacer la consulta "+ e);
		}
	}
	
	public void exportarSITIOSCoberturaSuelo() {
		
		
		ResultSet rst;
		

		int coberturaID,sitioID,gramineas,helechos,musgos,liquenes,hierbas,roca,sueloDesnudo,hojarasca,grava,otros;
		
	
	
		
		/*Consulta SELECT*/
		String selectQuery="SELECT CoberturaID,SitioID,Gramineas,Helechos,Musgos,Liquenes,Hierbas,Roca,SueloDesnudo,"
		+"Hojarasca,Grava,Otros"
		+" FROM SITIOS_CoberturaSuelo";

		/*Conexiones a bd local y server*/
		bdl = LocalConnection.getConnection();
		bds = ServerConnection.getConnection();
		
		
		try {
			
			Statement localStatment = bdl.createStatement();
			Statement serverStatment=bds.createStatement();
			
			/*manda el Query al Resulset*/
			rst= localStatment.executeQuery(selectQuery);
			
			while(rst.next()){
				
													/*inicia recoleccion de datos*/

				coberturaID=rst.getInt("CoberturaID");
				sitioID=rst.getInt("SitioID");
				gramineas=rst.getInt("Gramineas");
				helechos=rst.getInt("Helechos");
				musgos=rst.getInt("Musgos");
				liquenes=rst.getInt("Liquenes");
				hierbas=rst.getInt("Hierbas");
				roca=rst.getInt("Roca");
				sueloDesnudo=rst.getInt("SueloDesnudo");
				hojarasca=rst.getInt("Hojarasca");
				grava=rst.getInt("Grava");
				otros=rst.getInt("Otros");

															/*termina recoleccion de datos*/
				
													/*inicia insercion de datos*/
				
				

				String insertQuery="INSERT INTO Sitios.CoberturaSuelo (CoberturaID,SitioID,Gramineas,Helechos,Musgos,Liquenes,Hierbas,"
				+"Roca,SueloDesnudo,Hojarasca,Grava,Otros)"
				+" VALUES ("+coberturaID+","+sitioID+","+gramineas+","+helechos+","+musgos+","+liquenes+","+hierbas+","
				+roca+","+sueloDesnudo+","+hojarasca+","+grava+","+otros+")";		
				
													/*Termina insercion de datos*/
				
				
				serverStatment.executeUpdate(insertQuery);
				JOptionPane.showMessageDialog(null, "Datos insertados en el servidor Correctamente");
				serverStatment.close();
				
			}
			
			localStatment.close();
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "no se pudo hacer la consulta "+ e);
		}
		
	}

	public void exportarSITIOSFotografiaHemisferica() {
		
		ResultSet rst;
		

		int fotografiaHemisfericaID,sitioID,coberturaArborea,tomaFotografica,declinacionMagnetica;
		
		String  hora;
		
		
	
		
		/*Consulta SELECT*/
		String selectQuery="SELECT FotografiaHemisfericaID,SitioID,CoberturaArborea,TomaFotografia,"
		+"Hora,DeclinacionMagnetica"
		+" FROM SITIOS_FotografiaHemisferica";

		/*Conexiones a bd local y server*/
		bdl = LocalConnection.getConnection();
		bds = ServerConnection.getConnection();
		
		
		try {
			
			Statement localStatment = bdl.createStatement();
			Statement serverStatment=bds.createStatement();
			
			/*manda el Query al Resulset*/
			rst= localStatment.executeQuery(selectQuery);
			
			while(rst.next()){
				
													/*inicia recoleccion de datos*/

				fotografiaHemisfericaID=rst.getInt("FotografiaHemisfericaID");
				sitioID=rst.getInt("SitioID");
				coberturaArborea=rst.getInt("CoberturaArborea");
				tomaFotografica=rst.getInt("TomaFotografia");
				declinacionMagnetica=rst.getInt("DeclinacionMagnetica");

				hora=rst.getString("Hora");
				
															/*termina recoleccion de datos*/
				
													/*inicia insercion de datos*/
				
				

				String insertQuery="INSERT INTO Sitios.FotografiaHemisferica(FotografiaHemisfericaID,SitioID,CoberturaArborea,TomaFotografia,"
				+"Hora,DeclinacionMagnetica)"
				+" VALUES("+fotografiaHemisfericaID+","+sitioID+","+coberturaArborea+","+tomaFotografica+",'"+hora+"',"+declinacionMagnetica+")";		
				
													/*Termina insercion de datos*/
				
				
				serverStatment.executeUpdate(insertQuery);
				JOptionPane.showMessageDialog(null, "Datos insertados en el servidor Correctamente");
				serverStatment.close();
				
			}
			
			localStatment.close();
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "no se pudo hacer la consulta "+ e);
		}
	}
	
	public void exportarSITIOSObservaciones() {
		
ResultSet rst;
		

		int observacionesID,sitioID,formatoID;

		String observaciones;
		
		/*Consulta SELECT*/
		String selectQuery="SELECT ObservacionesID,SitioID,FormatoID,Observaciones"
		+" FROM SITIOS_Observaciones";

		/*Conexiones a bd local y server*/
		bdl = LocalConnection.getConnection();
		bds = ServerConnection.getConnection();
		
		
		try {
			
			Statement localStatment = bdl.createStatement();
			Statement serverStatment=bds.createStatement();
			
			/*manda el Query al Resulset*/
			rst= localStatment.executeQuery(selectQuery);
			
			while(rst.next()){
				
													/*inicia recoleccion de datos*/

				observacionesID=rst.getInt("ObservacionesID");
				sitioID=rst.getInt("SitioID");
				formatoID=rst.getInt("FormatoID");

				observaciones=rst.getString("Observaciones");

				
															/*termina recoleccion de datos*/
				
													/*inicia insercion de datos*/
				
				

				String insertQuery="INSERT INTO Sitios.Observaciones(ObservacionesID,SitioID,FormatoID,Observaciones)"
				+" VALUES ("+observacionesID+","+sitioID+","+formatoID+",'"+observaciones+"')";		
				
													/*Termina insercion de datos*/
				
				
				serverStatment.executeUpdate(insertQuery);
				JOptionPane.showMessageDialog(null, "Datos insertados en el servidor Correctamente");
				serverStatment.close();
				
			}
			
			localStatment.close();
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "no se pudo hacer la consulta "+ e);
		}
		
	}

	public void exportarSITIOSParametrosFisicoQuimicos() {
		
		
		ResultSet rst;
		

		int parametrosID,sitioID,tipoAgua;
		
		String  observaciones;
		
		float salinidad,temperatura,conductividadElectrica,ph,potencialRedox,profundidad;
	
		
		/*Consulta SELECT*/
		String selectQuery="SELECT ParametrosFQID,SitioID,TipoAgua,Salinidad,Temperatura,ConductividadElectrica,Ph,PotencialRedox,"
		+"Profundidad,Observaciones"
		+" FROM SITIOS_ParametrosFisicoQuimicos";

		/*Conexiones a bd local y server*/
		bdl = LocalConnection.getConnection();
		bds = ServerConnection.getConnection();
		
		
		try {
			
			Statement localStatment = bdl.createStatement();
			Statement serverStatment=bds.createStatement();
			
			/*manda el Query al Resulset*/
			rst= localStatment.executeQuery(selectQuery);
			
			while(rst.next()){
				
													/*inicia recoleccion de datos*/

				parametrosID=rst.getInt("ParametrosFQID");
				sitioID=rst.getInt("SitioID");
				tipoAgua=rst.getInt("TipoAgua");

				observaciones=rst.getString("Observaciones");

				salinidad=rst.getFloat("Salinidad");
				temperatura=rst.getFloat("Temperatura");
				conductividadElectrica=rst.getFloat("ConductividadElectrica");
				ph=rst.getFloat("Ph");
				potencialRedox=rst.getFloat("PotencialRedox");
				profundidad=rst.getFloat("Profundidad");

															/*termina recoleccion de datos*/
				
													/*inicia insercion de datos*/
				
				

				String insertQuery="INSERT INTO Sitios.ParametrosFisicoQuimicos(ParametrosFQID,SitioID,TipoAgua,Salinidad,Temperatura,ConductividadElectrica,"
				+"Ph,PotencialRedox,Profundidad,Observaciones)"
				+" VALUES ("+parametrosID+","+sitioID+","+tipoAgua+","+salinidad+","+temperatura+","+conductividadElectrica+","+ph+","+potencialRedox+","
				+profundidad+",'"+observaciones+"')";		
				
													/*Termina insercion de datos*/
				
				
				serverStatment.executeUpdate(insertQuery);
				JOptionPane.showMessageDialog(null, "Datos insertados en el servidor Correctamente");
				serverStatment.close();
				
			}
			
			localStatment.close();
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "no se pudo hacer la consulta "+ e);
		}
		
	}

	public void exportarSITIOSTransponder() {
		
	ResultSet rst;
		

		int transponderID,sitioID,tipoColocacionID;
		
		String  especifique,observaciones;
			
		
		/*Consulta SELECT*/
		String selectQuery="SELECT TransponderID,SitioID,TipoColocacionID,Especifique,Observaciones"
		+" FROM SITIOS_Transponder";

		/*Conexiones a bd local y server*/
		bdl = LocalConnection.getConnection();
		bds = ServerConnection.getConnection();
		
		
		try {
			
			Statement localStatment = bdl.createStatement();
			Statement serverStatment=bds.createStatement();
			
			/*manda el Query al Resulset*/
			rst= localStatment.executeQuery(selectQuery);
			
			while(rst.next()){
				
													/*inicia recoleccion de datos*/

				transponderID=rst.getInt("TransponderID");
				sitioID=rst.getInt("SitioID");
				tipoColocacionID=rst.getInt("TipoColocacionID");

				especifique=rst.getString("Especifique");
				observaciones=rst.getString("Observaciones");

				
															/*termina recoleccion de datos*/
				
													/*inicia insercion de datos*/
				
				

				String insertQuery="INSERT INTO Sitios.Transponder (TransponderID,SitioID,TipoColocacionID,Especifique,Observaciones)"
				+" VALUES ("+transponderID+","+sitioID+","+tipoColocacionID+",'"+especifique+"','"+observaciones  +"')";		
				
													/*Termina insercion de datos*/
				
				
				serverStatment.executeUpdate(insertQuery);
				JOptionPane.showMessageDialog(null, "Datos insertados en el servidor Correctamente");
				serverStatment.close();
				
			}
			
			localStatment.close();
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "no se pudo hacer la consulta "+ e);
		}
		
	}

	public void exportarTAXONOMIAArbolado() {
		
		
		ResultSet rst;
		

		int arboladoID,sitioID,consecutivo,noIndividuo,noRama,azimut,distancia,familiaID,generoID,especieID,infraespecieID;
		int esColecta,esSubmuestra,formaVidaID,formaFusteID,condicionID,muertoPieID,gradoPutrefaccionID,tipoToconID,diametroBasal;
		int proporcionCopaVivaID,exposicionCopaID,posicionCopaID,densidadCopaID,muerteRegresivaID,transparenciaFollajeID,vigorID;

		
		String nombreComun,claveColecta;
		
		float diametroNormal,alturaTotal,anguloInclinacion,alturaFustelimpio,alturaComercial,diametroCopaNS,diametroCopaEO;
	
		
		/*Consulta SELECT*/
		String selectQuery="SELECT ArboladoID,SitioID,Consecutivo,NoIndividuo,NoRama,Azimut,Distancia,FamiliaID,GeneroID,EspecieID,"
		+"InfraespecieID,NombreComun,EsColecta,EsSubmuestra,FormaVidaID,FormaFusteID,CondicionID,MuertoPieID,GradoPutrefaccionID,"
		+"TipoToconID,DiametroNormal,DiametroBasal,AlturaTotal,AnguloInclinacion,AlturaFustelimpio,AlturaComercial,DiametroCopaNS,"
		+"DiametroCopaEO,ProporcionCopaVivaID,ExposicionCopaID,PosicionCopaID,DensidadCopaID,MuerteRegresivaID,TransparenciaFollajeID,"
		+"VigorID,ClaveColecta"
		+" FROM TAXONOMIA_Arbolado";

		/*Conexiones a bd local y server*/
		bdl = LocalConnection.getConnection();
		bds = ServerConnection.getConnection();
		
		
		try {
			
			Statement localStatment = bdl.createStatement();
			Statement serverStatment=bds.createStatement();
			
			/*manda el Query al Resulset*/
			rst= localStatment.executeQuery(selectQuery);
			
			while(rst.next()){
				
													/*inicia recoleccion de datos*/
		
				arboladoID=rst.getInt("ArboladoID");
				sitioID=rst.getInt("SitioID");
				consecutivo=rst.getInt("Consecutivo");
				noIndividuo=rst.getInt("NoIndividuo");
				noRama=rst.getInt("NoRama");
				azimut=rst.getInt("Azimut");
				distancia=rst.getInt("Distancia");
				familiaID=rst.getInt("FamiliaID");
				generoID=rst.getInt("GeneroID");
				especieID=rst.getInt("EspecieID");
				infraespecieID=rst.getInt("InfraespecieID");
				esColecta=rst.getInt("EsColecta");
				esSubmuestra=rst.getInt("EsSubmuestra");
				formaVidaID=rst.getInt("FormaVidaID");
				formaFusteID=rst.getInt("FormaFusteID");
				condicionID=rst.getInt("CondicionID");
				muertoPieID=rst.getInt("MuertoPieID");
				gradoPutrefaccionID=rst.getInt("GradoPutrefaccionID");
				tipoToconID=rst.getInt("TipoToconID");
				diametroBasal=rst.getInt("DiametroBasal");
				proporcionCopaVivaID=rst.getInt("ProporcionCopaVivaID");
				exposicionCopaID=rst.getInt("ExposicionCopaID");
				posicionCopaID=rst.getInt("PosicionCopaID");
				densidadCopaID=rst.getInt("DensidadCopaID");
				muerteRegresivaID=rst.getInt("MuerteRegresivaID");
				transparenciaFollajeID=rst.getInt("TransparenciaFollajeID");
				vigorID=rst.getInt("VigorID");

				nombreComun=rst.getString("NombreComun");
				claveColecta=rst.getString("ClaveColecta");

				diametroNormal=rst.getFloat("DiametroNormal");
				alturaTotal=rst.getFloat("AlturaTotal");
				anguloInclinacion=rst.getFloat("AnguloInclinacion");
				alturaFustelimpio=rst.getFloat("AlturaFustelimpio");
				alturaComercial=rst.getFloat("AlturaComercial");
				diametroCopaNS=rst.getFloat("DiametroCopaNS");
				diametroCopaEO=rst.getFloat("DiametroCopaEO");

				
															/*termina recoleccion de datos*/
				
													/*inicia insercion de datos*/
				
				

				String insertQuery="INSERT INTO Taxonomia.Arbolado (ArboladoID,SitioID,Consecutivo,NoIndividuo,NoRama,Azimut,Distancia,FamiliaID,GeneroID,EspecieID,"
								   +"InfraespecieID,NombreComun,EsColecta,EsSubmuestra,FormaVidaID,FormaFusteID,CondicionID,MuertoPieID,GradoPutrefaccionID,"
								   +"TipoToconID,DiametroNormal,DiametroBasal,AlturaTotal,AnguloInclinacion,AlturaFustelimpio,AlturaComercial,DiametroCopaNS,"
								   +"DiametroCopaEO,ProporcionCopaVivaID,ExposicionCopaID,PosicionCopaID,DensidadCopaID,MuerteRegresivaID,TransparenciaFollajeID,"
							       +"VigorID,ClaveColecta)"
								   +" VALUES ("+arboladoID+","+sitioID+","+consecutivo+","+noIndividuo+","+noRama+","+azimut+","+distancia+","+familiaID+","+generoID+","
								   +especieID+","+infraespecieID+",'"+nombreComun+"',"+esColecta+","+esSubmuestra+","+formaVidaID+","+formaFusteID+","+condicionID+","+
								   +muertoPieID+","+gradoPutrefaccionID+","+tipoToconID+","+diametroNormal+","+diametroBasal+","+alturaTotal+","+anguloInclinacion+","
								   +alturaFustelimpio+","+alturaComercial+","+diametroCopaNS+","+diametroCopaEO+","+proporcionCopaVivaID+","+exposicionCopaID+","+posicionCopaID+","
								   +densidadCopaID+","+muerteRegresivaID+","+transparenciaFollajeID+","+vigorID+",'"+claveColecta+"')";		
				
													/*Termina insercion de datos*/
				
				
				serverStatment.executeUpdate(insertQuery);
				JOptionPane.showMessageDialog(null, "Datos insertados en el servidor Correctamente");
				serverStatment.close();
				
			}
			
			localStatment.close();
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "no se pudo hacer la consulta "+ e);
		}
		
	}

	public void exportarTAXONOMIAColectaBotanica(){
		
		ResultSet rst;
		

		int colectaBotanicaID,upmID,familiaID,generoID,especieID,colectorID,contraFuertes,exudado,color, cambioColor;
		int aceitesVolatiles,colorFlor,hojasTejidoVivo,fotoFlor,fotoFruto,fotoHojasAbajo,fotoHojasArriba,fotoArbolCompleto;
		int fotoCorteza,virutaIncluida,cortezaIncluida, maderaIncluida;
		
		String  infraespecie,nombreComun,claveColecta,indicarExudado,indicarColor,indicarColorFlor,observaciones;
			
		
		/*Consulta SELECT*/
		String selectQuery="SELECT ColectaBotanicaID,UPMID,FamiliaID,GeneroID,EspecieID,Infraespecie,NombreComun,ColectorID,ClaveColecta,"
		+"ContraFuertes,Exudado,IndicarExudado,Color,IndicarColor,CambioColor,AceitesVolatiles,ColorFlor,IndicarColorFlor,HojasTejidoVivo,"
		+"FotoFlor,FotoFruto,FotoHojasArriba,FotoHojasAbajo,FotoArbolCompleto,FotoCorteza,VirutaIncluida,CortezaIncluida,MaderaIncluida,"
		+"Observaciones"
		+" FROM TAXONOMIA_ColectaBotanica";

		/*Conexiones a bd local y server*/
		bdl = LocalConnection.getConnection();
		bds = ServerConnection.getConnection();
		
		
		try {
			
			Statement localStatment = bdl.createStatement();
			Statement serverStatment=bds.createStatement();
			
			/*manda el Query al Resulset*/
			rst= localStatment.executeQuery(selectQuery);
			
			while(rst.next()){
				
														/*inicia recoleccion de datos*/

				colectaBotanicaID=rst.getInt("ColectaBotanicaID");
				upmID=rst.getInt("UPMID");
				familiaID=rst.getInt("FamiliaID");
				generoID=rst.getInt("GeneroID");
				especieID=rst.getInt("EspecieID");
				colectorID=rst.getInt("ColectorID");
				contraFuertes=rst.getInt("ContraFuertes");
				exudado=rst.getInt("Exudado");
				color=rst.getInt("Color");
				cambioColor=rst.getInt("CambioColor");
				aceitesVolatiles=rst.getInt("AceitesVolatiles");
				colorFlor=rst.getInt("ColorFlor");
				hojasTejidoVivo=rst.getInt("HojasTejidoVivo");
				fotoFlor=rst.getInt("FotoFlor");
				fotoFruto=rst.getInt("FotoFlor");
				fotoHojasAbajo=rst.getInt("FotoHojasArriba");
				fotoHojasArriba=rst.getInt("FotoHojasAbajo");
				fotoArbolCompleto=rst.getInt("FotoArbolCompleto");
				fotoCorteza=rst.getInt("FotoCorteza");
				virutaIncluida=rst.getInt("VirutaIncluida");
				cortezaIncluida=rst.getInt("CortezaIncluida");
				maderaIncluida=rst.getInt("MaderaIncluida");
							

				infraespecie=rst.getString("Infraespecie");
				nombreComun=rst.getString("NombreComun");
				claveColecta=rst.getString("ClaveColecta");
				indicarExudado=rst.getString("IndicarExudado");
				indicarColor=rst.getString("IndicarColor");
				indicarColorFlor=rst.getString("IndicarColorFlor");
				observaciones=rst.getString("Observaciones");

				
				
															/*termina recoleccion de datos*/
				
													/*inicia insercion de datos*/
				
				

				String insertQuery="INSERT INTO Taxonomia.ColectaBotanica (ColectaBotanicaID,UPMID,FamiliaID,GeneroID,EspecieID,Infraespecie,NombreComun,ColectorID,ClaveColecta,"
				+"ContraFuertes,Exudado,IndicarExudado,Color,IndicarColor,CambioColor,AceitesVolatiles,ColorFlor,IndicarColorFlor,HojasTejidoVivo,"
				+"FotoFlor,FotoFruto,FotoHojasArriba,FotoHojasAbajo,FotoArbolCompleto,FotoCorteza,VirutaIncluida,CortezaIncluida,MaderaIncluida,"
				+"Observaciones)"
				+" VALUES ("+colectaBotanicaID+","+upmID+","+familiaID+","+generoID+","+especieID+",'"+infraespecie+"','"+nombreComun+"',"+colectorID+",'"
				+claveColecta+"',"+contraFuertes+","+exudado+",'"+indicarExudado+"',"+color+",'"+indicarColor+"',"+cambioColor+","+aceitesVolatiles+","
				+colorFlor+",'"+indicarColorFlor+"',"+hojasTejidoVivo+","+fotoFlor+","+fotoFruto+","+fotoHojasArriba+","+fotoHojasAbajo+","+fotoArbolCompleto+","
				+fotoCorteza+","+virutaIncluida+","+cortezaIncluida+","+maderaIncluida+",'"+observaciones+"')";	
								
													/*Termina insercion de datos*/
				
				
				serverStatment.executeUpdate(insertQuery);
				JOptionPane.showMessageDialog(null, "Datos insertados en el servidor Correctamente");
				serverStatment.close();
				
			}
			
			localStatment.close();
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "no se pudo hacer la consulta "+ e);
		}
	}
	
	public void exportarTAXONOMIARepoblado() {
		
		
		ResultSet rst;
		

		int repobladoID,sitioID,consecutivo,familiaID,generoID,especieID,infraespecieID,esColecta,frecuencia025150,edad025150,frecuencia151275;
		int edad151275,frecuencia275,edad275,vigorID,danioID,porcentajeDanio;
		
		String  nombreComun,claveColecta;
		
		
		/*Consulta SELECT*/
		String selectQuery="SELECT RepobladoID,SitioID,Consecutivo,FamiliaID,GeneroID,EspecieID,InfraespecieID,EsColecta,NombreComun,"
		+"Frecuencia025150,Edad025150,Frecuencia151275,Edad151275,Frecuencia275,Edad275,VigorID,DanioID,PorcentajeDanio,ClaveColecta"
		+" FROM TAXONOMIA_Repoblado";

		/*Conexiones a bd local y server*/
		bdl = LocalConnection.getConnection();
		bds = ServerConnection.getConnection();
		
		
		try {
			
			Statement localStatment = bdl.createStatement();
			Statement serverStatment=bds.createStatement();
			
			/*manda el Query al Resulset*/
			rst= localStatment.executeQuery(selectQuery);
			
			while(rst.next()){
				
													/*inicia recoleccion de datos*/

				repobladoID=rst.getInt("RepobladoID");
				sitioID=rst.getInt("SitioID");
				consecutivo=rst.getInt("Consecutivo");
				familiaID=rst.getInt("FamiliaID");
				generoID=rst.getInt("GeneroID");
				especieID=rst.getInt("EspecieID");
				infraespecieID=rst.getInt("InfraespecieID");
				esColecta=rst.getInt("EsColecta");
				frecuencia025150=rst.getInt("Frecuencia025150");
				edad025150=rst.getInt("Edad025150");
				frecuencia151275=rst.getInt("Frecuencia151275");
				edad151275=rst.getInt("Edad151275");
				frecuencia275=rst.getInt("Frecuencia275");
				edad275=rst.getInt("Edad275");
				vigorID=rst.getInt("VigorID");
				danioID=rst.getInt("DanioID");
				porcentajeDanio=rst.getInt("PorcentajeDanio");

				nombreComun=rst.getString("NombreComun");
				claveColecta=rst.getString("ClaveColecta");

				
															/*termina recoleccion de datos*/
				
													/*inicia insercion de datos*/
				
				

				String insertQuery="INSERT INTO Taxonomia.Repoblado(RepobladoID,SitioID,Consecutivo,FamiliaID,GeneroID,EspecieID,InfraespecieID,EsColecta,NombreComun,"
						+"Frecuencia025150,Edad025150,Frecuencia151275,Edad151275,Frecuencia275,Edad275,VigorID,DanioID,PorcentajeDanio,ClaveColecta)"
						+" VALUES ("+repobladoID+","+sitioID+","+consecutivo+","+familiaID+","+generoID+","+especieID+","+infraespecieID+","+esColecta+",'"+nombreComun+"',"
						+frecuencia025150+","+edad025150+","+frecuencia151275+","+edad151275+","+frecuencia275+","+edad275+","+vigorID+","+danioID+","+porcentajeDanio+",'"
						+claveColecta+"')";		
				
													/*Termina insercion de datos*/
				
				
				serverStatment.executeUpdate(insertQuery);
				JOptionPane.showMessageDialog(null, "Datos insertados en el servidor Correctamente");
				serverStatment.close();
				
			}
			
			localStatment.close();
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "no se pudo hacer la consulta "+ e);
		}
		
	}
	
	public void exportarTAXONOMIARepobladoVM() {
		
		
		ResultSet rst;
		

		int repobladoVMID,sitioID,consecutivo,formaVidaID,familiaID,generoID,especieID,infraespecieID,esColecta,frecuencia50;
		int porcentajeCobertura50,frecuencia51200,porcentajeCobertura51200,frecuencia200,porcentajeCobertura200,vigorID;
		
		String  nombreComun,claveColecta;
		
		
		/*Consulta SELECT*/
		String selectQuery="SELECT RepobladoVMID,SitioID,Consecutivo,FormaVidaID,FamiliaID,GeneroID,EspecieID,InfraespecieID,"
		+"NombreComun,EsColecta,Frecuencia50,PorcentajeCobertura50,Frecuencia51200,PorcentajeCobertura51200,Frecuencia200,PorcentajeCobertura200,"
		+"VigorID,ClaveColecta"
		+" FROM TAXONOMIA_RepobladoVM";

		/*Conexiones a bd local y server*/
		bdl = LocalConnection.getConnection();
		bds = ServerConnection.getConnection();
		
		
		try {
			
			Statement localStatment = bdl.createStatement();
			Statement serverStatment=bds.createStatement();
			
			/*manda el Query al Resulset*/
			rst= localStatment.executeQuery(selectQuery);
			
			while(rst.next()){
				
													/*inicia recoleccion de datos*/
				repobladoVMID=rst.getInt("RepobladoVMID");
				sitioID=rst.getInt("SitioID");
				consecutivo=rst.getInt("Consecutivo");
				formaVidaID=rst.getInt("FormaVidaID");
				familiaID=rst.getInt("FamiliaID");
				generoID=rst.getInt("GeneroID");
				especieID=rst.getInt("EspecieID");
				infraespecieID=rst.getInt("InfraespecieID");
				esColecta=rst.getInt("EsColecta");
				frecuencia50=rst.getInt("Frecuencia50");
				porcentajeCobertura50=rst.getInt("PorcentajeCobertura50");
				frecuencia51200=rst.getInt("Frecuencia51200");
				frecuencia200=rst.getInt("Frecuencia200");
				porcentajeCobertura200=rst.getInt("PorcentajeCobertura200");
				porcentajeCobertura51200=rst.getInt("PorcentajeCobertura51200");
				vigorID=rst.getInt("VigorID");
				

				nombreComun=rst.getString("NombreComun");
				claveColecta=rst.getString("ClaveColecta");
				
															/*termina recoleccion de datos*/
				
													/*inicia insercion de datos*/
				
				

				String insertQuery="INSERT INTO Taxonomia.RepobladoVM (RepobladoVMID,SitioID,Consecutivo,FormaVidaID,FamiliaID,GeneroID,EspecieID,InfraespecieID,"
							+"NombreComun,EsColecta,Frecuencia50,PorcentajeCobertura50,Frecuencia51200,PorcentajeCobertura51200,Frecuencia200,PorcentajeCobertura200,"
							+"VigorID,ClaveColecta)"
							+" VALUES ("+repobladoVMID+","+sitioID+","+consecutivo+","+formaVidaID+","+familiaID+","+generoID+","+especieID+","+infraespecieID+",'"
							+nombreComun+"',"+esColecta+","+frecuencia50+","+porcentajeCobertura50+","+frecuencia51200+","+porcentajeCobertura51200+","+frecuencia200+","
							+porcentajeCobertura200+","+vigorID+",'"+claveColecta+"')";		
				System.out.println(insertQuery);
													/*Termina insercion de datos*/
				
				
				serverStatment.executeUpdate(insertQuery);
				JOptionPane.showMessageDialog(null, "Datos insertados en el servidor Correctamente");
				serverStatment.close();
				
			}
			
			localStatment.close();
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "no se pudo hacer la consulta "+ e);
		}
		
	}
	
	public void exportarTAXONOMIASotobosque() {
		
		
		ResultSet rst;
		
		int sotobosqueID,sitioID,consecutivo,familiaID,generoID,especieID,infraespecieID,esColecta,frecuencia025150,cobertura025150;
		int frecuencia151275,cobertura151275,frecuencia275,cobertura275,vigorID,danioID,porcentajeDanio;

		String  nombreComun,claveColecta;
		
		
		/*Consulta SELECT*/
		String selectQuery="SELECT SotoBosqueID,SitioID,Consecutivo,FamiliaID,GeneroID,EspecieID,InfraespecieID,NombreComun,EsColecta,"
		+"Frecuencia025150,Cobertura025150,Frecuencia151275,Cobertura151275,Frecuencia275,Cobertura275,VigorID,DanioID,PorcentajeDanio,"
		+"ClaveColecta"
		+" FROM TAXONOMIA_Sotobosque";

		/*Conexiones a bd local y server*/
		bdl = LocalConnection.getConnection();
		bds = ServerConnection.getConnection();
		
		
		try {
			
			Statement localStatment = bdl.createStatement();
			Statement serverStatment=bds.createStatement();
			
			/*manda el Query al Resulset*/
			rst= localStatment.executeQuery(selectQuery);
			
			while(rst.next()){
				
													/*inicia recoleccion de datos*/
		
				sotobosqueID=rst.getInt("SotoBosqueID");
				sitioID=rst.getInt("SitioID");
				consecutivo=rst.getInt("Consecutivo");
				familiaID=rst.getInt("FamiliaID");
				generoID=rst.getInt("GeneroID");
				especieID=rst.getInt("EspecieID");
				infraespecieID=rst.getInt("InfraespecieID");
				esColecta=rst.getInt("EsColecta");
				frecuencia025150=rst.getInt("Frecuencia025150");
				cobertura025150=rst.getInt("Cobertura025150");
				frecuencia151275=rst.getInt("Frecuencia151275");
				cobertura151275=rst.getInt("Cobertura151275");
				frecuencia275=rst.getInt("Frecuencia275");
				cobertura275=rst.getInt("Cobertura275");
				vigorID=rst.getInt("VigorID");
				danioID=rst.getInt("DanioID");
				porcentajeDanio=rst.getInt("PorcentajeDanio");

				nombreComun=rst.getString("NombreComun");
				claveColecta=rst.getString("ClaveColecta");				
															/*termina recoleccion de datos*/
				
													/*inicia insercion de datos*/
				
				

				String insertQuery="INSERT INTO Taxonomia.Sotobosque(SotoBosqueID,SitioID,Consecutivo,FamiliaID,GeneroID,EspecieID,InfraespecieID,NombreComun,EsColecta,"
						+"Frecuencia025150,Cobertura025150,Frecuencia151275,Cobertura151275,Frecuencia275,Cobertura275,VigorID,DanioID,PorcentajeDanio,"
						+"ClaveColecta)"
						+" VALUES ("+sotobosqueID+","+sitioID+","+consecutivo+","+familiaID+","+generoID+","+especieID+","+infraespecieID+",'"+nombreComun+"',"+esColecta+","
						+frecuencia025150+","+cobertura025150+","+frecuencia151275+","+cobertura151275+","+frecuencia275+","+cobertura275+","+vigorID+","+danioID+","
						+porcentajeDanio+",'"+claveColecta+"')";		
				
													/*Termina insercion de datos*/
				
				
				serverStatment.executeUpdate(insertQuery);
				JOptionPane.showMessageDialog(null, "Datos insertados en el servidor Correctamente");
				serverStatment.close();
				
			}
			
			localStatment.close();
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "no se pudo hacer la consulta "+ e);
		}
		
	}
	
	
	
	public static void main(String[] args) {
		Exportacion exportacion=new Exportacion();
		
		exportacion.exportarTAXONOMIASotobosque();
	}

}
