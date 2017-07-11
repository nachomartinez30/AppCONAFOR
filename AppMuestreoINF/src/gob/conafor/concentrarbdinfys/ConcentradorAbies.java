package gob.conafor.concentrarbdinfys;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;



import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JProgressBar;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class ConcentradorAbies extends JFrame {

	public String ruta;
	//private String configUser = "/src/lib/ConfigUser.db";
        private String configUser = "/src/db/ConfigUser.db";
	private ConfigUserConnection configUserConnection = new ConfigUserConnection();
	private Connection baseDatosConfig;
	private java.sql.Statement sqlConfig;

	private CDImportacionBD bdImportar = new CDImportacionBD();
	private JPanel contentPane;
	public JProgressBar pbExportacion;
	public JTextField txtUbicacion;
	public JTextField txtRutaSalida;
	public JButton btnBuscar;
	public JButton btnEjecutar;
	public JLabel lblEstatus;
	public File[] baseDatos;

	/**
	 * Create the frame.
	 */
	public ConcentradorAbies() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 796, 447);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		//getPathConcentrador(configUser);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblConcentrador = new JLabel("Concentrador");
		lblConcentrador.setFont(new Font("Dialog", Font.BOLD, 50));
		lblConcentrador.setHorizontalAlignment(SwingConstants.CENTER);
		lblConcentrador.setBounds(220, 57, 335, 56);
		panel.add(lblConcentrador);

		JLabel lblNewLabel = new JLabel("");
		//lblNewLabel.setIcon(new ImageIcon(ConcentradorAbies.class.getResource("/Icons/ConcentradorOCT.png")));
		lblNewLabel.setBounds(56, 12, 152, 162);
		panel.add(lblNewLabel);

		JLabel label = new JLabel("");
		//label.setIcon(new ImageIcon(ConcentradorAbies.class.getResource("/Icons/ConcentradorCONS.png")));
		label.setBounds(567, 12, 137, 162);
		panel.add(label);

		pbExportacion = new JProgressBar();
		pbExportacion.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pbExportacion.setForeground(new Color(184, 134, 11));
		pbExportacion.setBackground(Color.DARK_GRAY);
		pbExportacion.setBounds(12, 360, 752, 23);
		panel.add(pbExportacion);

		txtUbicacion = new JTextField();
		txtUbicacion.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtUbicacion.selectAll();
			}
		});
		txtUbicacion.setBounds(135, 215, 595, 20);
		panel.add(txtUbicacion);
		txtUbicacion.setColumns(10);

		lblEstatus = new JLabel("Importando...");
		lblEstatus.setBounds(12, 342, 752, 16);
		panel.add(lblEstatus);

		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				cargarBaseDatos();
			}
		});
		btnBuscar.setBounds(43, 213, 80, 24);
		panel.add(btnBuscar);

		txtRutaSalida = new JTextField();
		txtRutaSalida.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtRutaSalida.selectAll();
			}
		});
		txtRutaSalida.setBounds(401, 247, 329, 20);
		panel.add(txtRutaSalida);
		txtRutaSalida.setColumns(10);

		JLabel lblBaseDeDatos = new JLabel("Base de datos concentrada:");
		lblBaseDeDatos.setBounds(231, 249, 166, 16);
		panel.add(lblBaseDeDatos);

		btnEjecutar = new JButton("Ejecutar");
		btnEjecutar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HiloImportacion importacion =new HiloImportacion(lblEstatus, pbExportacion, btnEjecutar, baseDatos, btnBuscar, txtUbicacion);
				importacion.execute();
			}
		});
		btnEjecutar.setEnabled(false);
		btnEjecutar.setBounds(351, 293, 111, 24);
		panel.add(btnEjecutar);
	}

	public void setPathConcentrador(String ruta) {
		String query = "UPDATE configUserAbies SET pathConcentrador='" + ruta + "'";
		//System.out.println(query);
		Connection configConnection = ConfigUserConnection.getConnection(ruta);
		try {
			java.sql.Statement st = configConnection.createStatement();
			st.executeUpdate(query);
			configConnection.commit();
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void migrar(String pathUbicacion){
		lblEstatus.setText("Iniciando importación...");
		pbExportacion.setValue(0);
		btnEjecutar.setEnabled(false);
		btnBuscar.setEnabled(false);
		
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		lblEstatus.setText("Importando UPM...");
		bdImportar.validarRepetidos(pathUbicacion);
		bdImportar.importarUPM_UPM(pathUbicacion); // 1

		lblEstatus.setText("Importando Contacto...");
		bdImportar.importarUPMContacto(pathUbicacion); // 2
		pbExportacion.setValue(5);
		pbExportacion.repaint();

		lblEstatus.setText("Importando PC...");
		bdImportar.importarPC(pathUbicacion); // 3

		lblEstatus.setText("Importando Información de accesibilidad del PC...");
		bdImportar.importarAccesibilidadPC(pathUbicacion); // 4
		pbExportacion.setValue(10);
		pbExportacion.repaint();

		lblEstatus.setText("Importando Epífitas...");
		bdImportar.importarUPMEpifitas(pathUbicacion); // 5
		pbExportacion.setValue(15);
		pbExportacion.repaint();

		lblEstatus.setText("Importando Información de sitios...");
		bdImportar.importarSitios(pathUbicacion); // 6

		lblEstatus.setText("Importando Cobertura de suelo de sitio...");
		bdImportar.importarSitiosCoberturaSuelo(pathUbicacion); // 7
		// System.err.println("Entro a Cobertura Suelo");
		pbExportacion.setValue(20);
		pbExportacion.repaint();

		lblEstatus.setText("Importando Fotografía hemisferica...");
		bdImportar.importarFotografiaHemisferica(pathUbicacion); // 8
		pbExportacion.setValue(25);
		pbExportacion.repaint();

		lblEstatus.setText("Importando Información de transponder...");
		bdImportar.importarTransponder(pathUbicacion); // 9
		pbExportacion.setValue(30);
		pbExportacion.repaint();

		lblEstatus.setText("Importando Parómetros fósico quómicos...");
		bdImportar.importarParametrosFisicoQuimicos(pathUbicacion); // 10
		pbExportacion.setValue(35);
		pbExportacion.repaint();

		lblEstatus.setText("Importando Información de suelo...");
		bdImportar.importarSueloInformacion(pathUbicacion); // 11
		lblEstatus.setText("Importando varillas de erosión...");
		bdImportar.importarSueloVarillasErosion(pathUbicacion); // 12
		lblEstatus.setText("Importando cobertura del suelo...");
		bdImportar.importarSueloCobertura(pathUbicacion); // 13

		pbExportacion.setValue(40);
		pbExportacion.repaint();

		lblEstatus.setText("Importando evidencia de erosión del suelo...");
		bdImportar.importarSueloEvidenciaErosion(pathUbicacion); // 14
		lblEstatus.setText("Importando Pedestal...");
		bdImportar.importarSueloPedestal(pathUbicacion); // 15
		lblEstatus.setText("Importando Erosión laminar...");
		bdImportar.importarSueloErosionLaminar(pathUbicacion); // 16
		lblEstatus.setText("Importando Costras...");
		bdImportar.importarSueloCostras(pathUbicacion); // 17
		lblEstatus.setText("Importando Canalillo...");
		bdImportar.importarSueloCanalillo(pathUbicacion); // 18
		lblEstatus.setText("Importando Córcava...");
		bdImportar.importarSueloCarcava(pathUbicacion); // 19
		lblEstatus.setText("Importando Pavimentos...");
		bdImportar.importarSueloPavimentos(pathUbicacion); // 20
		lblEstatus.setText("Importando Medición canalillos...");
		bdImportar.importarSueloMedicionCanalillos(pathUbicacion); // 21
		lblEstatus.setText("Importando Medición carcavas...");
		bdImportar.importarSueloMedicionCarcavas(pathUbicacion); // 22
		lblEstatus.setText("Importando Medición dunas...");
		bdImportar.importarSueloMedicionDunas(pathUbicacion); // 23
		pbExportacion.setValue(45);
		pbExportacion.repaint();
		lblEstatus.setText("Importando Erosión hódrica canalillo...");
		bdImportar.importarSueloErosionHidricaCanalillo(pathUbicacion); // 24
		lblEstatus.setText("Importando Longitud canalillo...");
		bdImportar.importarSueloLongitudCanalillo(pathUbicacion); // 25
		lblEstatus.setText("Importando Erosión hidrica carcava...");
		bdImportar.importarSueloErosionHidricaCarcava(pathUbicacion); // 26
		lblEstatus.setText("Importando longitud de carcava...");
		bdImportar.importarSueloLongitudCarcava(pathUbicacion); // 27
		lblEstatus.setText("Importando deformación por viento...");
		bdImportar.importarSueloDeformacionViento(pathUbicacion); // 28
		lblEstatus.setText("Importando longitud montóculo...");
		bdImportar.importarSueloLongitudMonticulo(pathUbicacion); // 29
		lblEstatus.setText("Importando hojarasca...");
		bdImportar.importarSueloHojarasca(pathUbicacion); // 30
		lblEstatus.setText("Importando profundidad de suelo...");
		bdImportar.importarSueloProfundidad(pathUbicacion); // 31
		lblEstatus.setText("Importando perfil...");
		bdImportar.importarSueloMuestrasPerfil(pathUbicacion); // 32
		lblEstatus.setText("Importando muestras del perfil...");
		bdImportar.importarSueloMuestras(pathUbicacion); // 33
		pbExportacion.setValue(50);
		pbExportacion.repaint();

		lblEstatus.setText("Importando Información de carbono e incendios...");

		lblEstatus.setText("Importando Material leóoso caódo de 100...");
		bdImportar.importarCarbonoMaterialLenioso100(pathUbicacion); // 34
		lblEstatus.setText("Importando Material leóoso caódo de 1000...");
		bdImportar.importarCarbonoMaterialLenioso1000(pathUbicacion); // 35
		lblEstatus.setText("Importando cubierta vegetal...");
		bdImportar.importarCarbonoCubiertaVegetal(pathUbicacion); // 36
		lblEstatus.setText("Importando cobertura dosel...");
		bdImportar.importarCarbonoCoberturaDosel(pathUbicacion); // 37
		lblEstatus.setText("Importando longitud por componente...");
		bdImportar.importarCarbonoLongitudComponente(pathUbicacion); // 38

		pbExportacion.setValue(55);
		pbExportacion.repaint();

		lblEstatus.setText("Importando Arbolado...");
		bdImportar.importarTaxonomiaArbolado(pathUbicacion); // 39
		bdImportar.importarArboladoDanioSeveridad(pathUbicacion); // 40
		lblEstatus.setText("Importando Submuestra...");
		bdImportar.importarSubmuestra(pathUbicacion); // 41
		bdImportar.importarSubmuestraTroza(pathUbicacion); // 42
		bdImportar.importarSubmuestraObservaciones(pathUbicacion);
		pbExportacion.setValue(60);
		pbExportacion.repaint();

		lblEstatus.setText("Importando Repoblado...");
		bdImportar.importarTaxonomiaRepoblado(pathUbicacion); // 43
		pbExportacion.setValue(65);
		pbExportacion.repaint();

		lblEstatus.setText("Importando Repoblado vegetación menor...");
		bdImportar.importarTaxonomiaRepobladoVM(pathUbicacion); // 44
		pbExportacion.setValue(70);
		pbExportacion.repaint();

		lblEstatus.setText("Importando Sotobosque...");
		bdImportar.importarTaxonomiaSotoBosque(pathUbicacion); // 45
		pbExportacion.setValue(75);
		pbExportacion.repaint();

		lblEstatus.setText("Importando Vegetación mayor gregarios...");
		bdImportar.importarTaxonomiaVegetacionMayorGregarios(pathUbicacion); // 46
		bdImportar.importarVegetacionMayorGDanioSeveridad(pathUbicacion); // 47
		pbExportacion.setValue(80);
		pbExportacion.repaint();

		lblEstatus.setText("Importando Vegetación mayor individual...");
		bdImportar.importarTaxonomiaVegetacionMayorIndividual(pathUbicacion); // 48
		bdImportar.importarVegetacionMayorIDanioSeveridad(pathUbicacion); // 49
		pbExportacion.setValue(85);
		pbExportacion.repaint();

		lblEstatus.setText("Importando Vegetación menor...");
		bdImportar.importarTaxonomiaVegetacionMenor(pathUbicacion); // 50
		bdImportar.importarVegetacionMenorDanioSeveridad(pathUbicacion); // 51
		pbExportacion.setValue(90);
		pbExportacion.repaint();

		lblEstatus.setText("Importando Colecta botónica...");
		bdImportar.importarTaxonomiaColectaBotanica(pathUbicacion); // 52
		pbExportacion.setValue(95);
		pbExportacion.repaint();

		lblEstatus.setText("Importando datos de Brigada");
		bdImportar.importarBrigadas(pathUbicacion); // 53
		pbExportacion.setValue(97);
		pbExportacion.repaint();

		lblEstatus.setText("Finalizando importacion...");
		// bdImportar.importarSecuencias(pathUbicacion); //54
		// bdImportar.importarUPMRevision(pathUbicacion);
		pbExportacion.setValue(100);
		pbExportacion.repaint();
	
	}
	
	public void cargarBaseDatos() {
		JFileChooser fcBaseDatos = new JFileChooser(ruta);
		fcBaseDatos.setMultiSelectionEnabled(true);
		fcBaseDatos.setDialogTitle("Base de datos a importar");
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.oct", "oct");
		fcBaseDatos.setAcceptAllFileFilterUsed(false);
		fcBaseDatos.setFileFilter(filtro);
		int returnVal = fcBaseDatos.showOpenDialog(this);

		// fcBaseDatos.showOpenDialog(Main.main);
		try {
			baseDatos = fcBaseDatos.getSelectedFiles();
			ruta = baseDatos[0].getAbsolutePath();
			int tamanio = ruta.length();
			int cadena = tamanio - 3;
			String extension = ruta.substring(cadena, tamanio);
			if (!extension.equals("oct")) {
				JOptionPane.showMessageDialog(null, "Debe seleccionar un base de datos valida a importar" + "",
						"Importación", JOptionPane.INFORMATION_MESSAGE);
				txtUbicacion.setText("");
			} else {
				txtUbicacion.setText(ruta);
				Path currentPath = Paths.get("");
				String path = currentPath.toAbsolutePath().toString();
				txtRutaSalida.setText(path + "/MuestreoINF_2017.cons");
				//setPathConcentrador(ruta);
				btnEjecutar.setEnabled(true);

			}
		} catch (Exception e) {
			// JOptionPane.showMessageDialog(null, "El archivo que intenta
			// importar no es una base de datos balida" + e);
		}
	}

	public void excecuteConcentrar(String rute) {

	}

	public void getPathConcentrador(String bdConfig) {

		String query = "SELECT pathConcentrador FROM configUserAbies";

		this.baseDatosConfig = ConfigUserConnection.getConnection(ruta);
		try {
			sqlConfig = baseDatosConfig.createStatement();
			ResultSet rsConfig = sqlConfig.executeQuery(query);

			while (rsConfig.next()) {

				ruta = rsConfig.getString("pathConcentrador");
			}
			baseDatosConfig.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
