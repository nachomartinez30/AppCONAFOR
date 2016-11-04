package gob.conafor.utils;

import javax.swing.JOptionPane;

public class ValidacionesComunes {

    public boolean sonGradosLatitud(int gradosLatitud, String seccion) {
        if (gradosLatitud >= 14 && gradosLatitud <= 32) {
            return false;
        } else {
            JOptionPane.showMessageDialog(null, "El valor de grados latitud debe ser entre 14 y 32", seccion, JOptionPane.INFORMATION_MESSAGE);
            return true;
        }
    }

    public boolean sonGradosLongitud(Integer gradosLongitud, String seccion) {
        if (gradosLongitud <= -86 && gradosLongitud >= -118) {
            return false;
        } else {
            JOptionPane.showMessageDialog(null, "El valor de grados longitud debe ser entre -86 y -118", seccion, JOptionPane.INFORMATION_MESSAGE);
            return true;
        }
    }

    public boolean sonMinutos(int minutos, String seccion) {
        if (minutos >= 0 && minutos < 60) {
            return false;
        } else {
            JOptionPane.showMessageDialog(null, "El valor de los minutos debe ser entre 0 y 59", seccion, JOptionPane.INFORMATION_MESSAGE);
            return true;
        }
    }

    public boolean sonSegundos(float segundos, String seccion) {
        if (segundos >= 0 && segundos < 60) {
            return false;
        } else {
            JOptionPane.showMessageDialog(null, "El valor de los segundos debe ser entre 0 y 59", seccion, JOptionPane.INFORMATION_MESSAGE);
            return true;
        }
    }

    public boolean esErrorPrecision(int errorPrecision, String seccion) {
        if (errorPrecision >= 0) {
            return false;
        } else {
            JOptionPane.showMessageDialog(null, "El valor de error de precisión debe ser mayor o igual a cero", seccion, JOptionPane.INFORMATION_MESSAGE);
            return true;
        }
    }

    public boolean esAzimut(int azimut, String seccion) {
        if (azimut >= 0 && azimut <= 359) {
            return false;
        } else {
            JOptionPane.showMessageDialog(null, "El valor del azimut debe ser entre 0 y 359", seccion, JOptionPane.INFORMATION_MESSAGE);
            return true;
        }
    }

    public boolean esDistancia(float distancia, String seccion) {
        if (distancia >= 0) {
            return false;
        } else {
            JOptionPane.showMessageDialog(null, "El valor de distancia debe ser mayor o igual a cero", seccion, JOptionPane.INFORMATION_MESSAGE);
            return true;
        }
    }

    public boolean esPorcentaje(int porcentaje) {
        if (porcentaje >= 0 && porcentaje <= 100) {
            return false;
        } else {
            JOptionPane.showMessageDialog(null, "El valor de porcentaje debe estar entre 0 y 100", null, JOptionPane.INFORMATION_MESSAGE);
            return true;
        }
    }
    
    public boolean esPorcentajeExtendido(int porcentaje) {
        if (porcentaje >= -100 && porcentaje <= 100) {
            return false;
        } else {
            JOptionPane.showMessageDialog(null, "El valor de porcentaje debe estar entre -100 y 100", null, JOptionPane.INFORMATION_MESSAGE);
            return true;
        }
    }

    public boolean esCuadrante(Integer cuadrante) {
        if (cuadrante < -150 || cuadrante > 150) {
            JOptionPane.showMessageDialog(null, "El valor del cuadrante debe estar entre -150 y 150 ", null, JOptionPane.INFORMATION_MESSAGE);
            return true;
        } else {
            return false;
        }
    }

    public boolean esNumerico(String dato) {
        try {
            Integer.parseInt(dato);
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El valor intruducido no tiene formato de número, verifique", null, JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
    }

    public boolean esMayorCero(float numero) {
        if (numero <= 0) {
            JOptionPane.showMessageDialog(null, "El valor intruducido debe ser mayor que cero, verifique", null, JOptionPane.INFORMATION_MESSAGE);
            return true;
        } else {
            return false;
        }
    }
}
