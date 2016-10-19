package gob.conafor.ini.vie;

import gob.conafor.sys.mod.CDImportacionBD;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

public class HiloEliminacionUPM extends SwingWorker<Integer, String> {

    private JLabel etiqueta;
    private JProgressBar barraProgreso;
    private JButton btnEjecutar;
    private JButton btnSalir;
    private JComboBox combo;
    private CDImportacionBD bdEliminar = new CDImportacionBD();

    public HiloEliminacionUPM(Integer UPMID, JComboBox combo, JLabel etiqueta, JProgressBar barraProgreso, JButton btnEjecutar, JButton btnSalir) {
        this.etiqueta = etiqueta;
        this.barraProgreso = barraProgreso;
        this.btnEjecutar = btnEjecutar;
        this.btnSalir = btnSalir;
        this.combo = combo;
    } 

    @Override
    protected Integer doInBackground() {
        getEtiqueta().setText("Eliminando información...");
        getBarraProgreso().setValue(0);
        getBtnEjecutar().setEnabled(false);
        getBtnSalir().setEnabled(false);
        getCombo().setEnabled(false);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        getBarraProgreso().setValue(50);
        getBarraProgreso().repaint();
        bdEliminar.eliminarPorUPM(0);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        getBarraProgreso().setValue(100);
        getBarraProgreso().repaint();
        return null;
    }
    
     @Override
    protected void done(){
        JOptionPane.showMessageDialog(null, "Se eliminó la información de la base de datos");
        getBarraProgreso().setValue(0);
        getBarraProgreso().repaint();
        getBtnEjecutar().setEnabled(false);
        getBtnSalir().setEnabled(true);
        getCombo().setEnabled(true);
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

    public JComboBox getCombo() {
        return combo;
    }

    public void setCombo(JComboBox combo) {
        this.combo = combo;
    }
    
}
