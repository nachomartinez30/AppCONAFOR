package gob.conafor.ini.vie;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
//import com.jtattoo.plaf.noire.*;

public class Main {

    public static FrmInicio main;

    public static void main(String args[]) {
        try {
             //UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
           UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            java.util.logging.Logger.getLogger(Layout.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }

        main = new FrmInicio();
        main.setVisible(true);
    }
}
