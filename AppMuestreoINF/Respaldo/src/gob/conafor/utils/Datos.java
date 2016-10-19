
package gob.conafor.utils;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Datos implements KeyListener{

    @Override
    public void keyTyped(KeyEvent e) {

        char caracter = e.getKeyChar();

            if ((((caracter < '0') || (caracter > '9')) && (caracter != '\b')  && (caracter != '.') && (caracter != '-'))) {
                 e.consume();
            }
    }

    @Override
    public void keyPressed(KeyEvent e) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
