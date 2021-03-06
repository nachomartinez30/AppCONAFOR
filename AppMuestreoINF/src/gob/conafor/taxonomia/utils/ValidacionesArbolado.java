package gob.conafor.taxonomia.utils;

import javax.swing.JOptionPane;

public class ValidacionesArbolado {
    
    public boolean esNumeracion(int numeroArbol){
        if(numeroArbol < 1){
            JOptionPane.showMessageDialog(null, "La numeracion del arbolado  no puede ser negativo ni cero", "Arbolado", JOptionPane.ERROR_MESSAGE);
            return true;
        }else{
            return false;
        }
    }

    public boolean esDiametroNormal(Float dn) { //Condicion sujeta a advertencia
        if (dn < 7.5 || dn > 400) {
            JOptionPane.showMessageDialog(null, "Error! el diámetro normal debe estar entre 7.5 y 400", "Arbolado", JOptionPane.ERROR_MESSAGE);
            return true;
        } else {
            return false;
        }
    }

    public boolean esAlturaTotal(Float at) {
        if (at != null) {
            if (at < 0.1) { //Condicion sujeta a advertencia
                JOptionPane.showMessageDialog(null, "Error! la altura total debe ser mayor a 0.1", "Arbolado", JOptionPane.ERROR_MESSAGE);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean esAnguloInclinacion(Integer ai) {
        if (ai != null) {
            if (ai < 0 || ai > 360) {// Solititó cambio Sergio Villela Gaytan dia 27/02/2017
                JOptionPane.showMessageDialog(null, "Error! el ángulo de inclinación debe estar entre 0 y 360 grados", "Arbolado", JOptionPane.ERROR_MESSAGE);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public boolean esAlturaFusteLimpio(Float afl) {
        if (afl != null) {
            if (afl < 0 || afl > 50) {
                JOptionPane.showMessageDialog(null, "Error! la altura del fuste limpio debe estar entre 0 y 50", "Arbolado", JOptionPane.ERROR_MESSAGE);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public boolean esAlturaComercial(Float ac) { //Sujeta a una advertencia
        if (ac < 0 || ac > 50) {
            JOptionPane.showMessageDialog(null, "Error! la altura comercial debe estar entre 0 y 50", "Arbolado", JOptionPane.ERROR_MESSAGE);
            return true;
        } else {
            return false;
        }
    }

    public boolean esDiametroCopaNS(Float ns) { // 
        if (ns < 0.0 || ns > 30) {
            JOptionPane.showMessageDialog(null, "Error! el diametro de copa norte-sur debe estar entre 0.0 y 30", "Arbolado", JOptionPane.ERROR_MESSAGE);
            return true;
        } else {
            return false;
        }
    }

    public boolean esDiametroCopaEO(Float ns) {
        if (ns < 0.0 || ns > 30) {
            JOptionPane.showMessageDialog(null, "Error! el diametro de copa este-oeste debe estar entre 0.0 y 30", "Arbolado", JOptionPane.ERROR_MESSAGE);
            return true;
        } else {
            return false;
        }
    }
    
    public boolean esDistanciaArbolado(Float distancia){
        if(distancia < 0.1 || distancia > 15.95){
            JOptionPane.showMessageDialog(null, "Error! La distancia debe estar 0.1 y 15.95", "Arbolado", JOptionPane.ERROR_MESSAGE);
            return true;
        } else{
            return false;
        }
    }
}
