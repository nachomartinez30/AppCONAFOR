package gob.conafor.sys.mod;

import java.util.ArrayList;
import java.util.List;

public class CDFormatos {
    
    //Fijar UPM y Sitio para la captura de módulos
    
    public List<Integer> getSecuencia1() {
       List secuencia1 = new ArrayList<>();
       //Formatos del Modulo A
       secuencia1.add(0, 1);//Sotobosque
       secuencia1.add(1, 2);//Repoblado
       secuencia1.add(2, 3);//Arbolado
       secuencia1.add(3, 4);//Submuestra
       secuencia1.add(4, 5);//Clave de vegetacion
       secuencia1.add(5, 6);//Características del conglomerado
       secuencia1.add(6, 7);//Carbono e incendios
       secuencia1.add(7, 8);//Longitud interceptada por componente
       secuencia1.add(8, 9);//Suelo
       secuencia1.add(9, 10);//Condición de degradación
       secuencia1.add(10, 11);//Herosión hídrica
       secuencia1.add(11, 12);//Deformación del terreno por acción del viento
       secuencia1.add(12, 13);//Observaciones
       
       return secuencia1;
    }
    
    public List<Integer> getSecuencia2(){
        List secuencia2 = new ArrayList<>();
        //Formatos de los módulos A y C
        secuencia2.add(0, 1);//Sotobosque
        secuencia2.add(1, 2);//Repoblado
        secuencia2.add(2, 3);//Arbolado
        secuencia2.add(3, 4);//Submuestra
        secuencia2.add(4, 5);//Clave de vegetación
        secuencia2.add(5, 6);//Caracteristicas del conglomerado
        secuencia2.add(6, 9);//Suelo
        secuencia2.add(7, 10);//Condición de degradación
        secuencia2.add(8, 11);//Herosión hídrica
        secuencia2.add(9, 12);//Deformación del terreno por acción del viento
        secuencia2.add(10, 13);//Observaciones
        secuencia2.add(11, 7);//Carbono e incendios
        secuencia2.add(12, 8);//Longitud interceptada por componente
        
        return secuencia2;
    }
    
    public List<Integer> getSecuencia3(){
        List secuencia3 = new ArrayList<>();
        //Formatos de los módulos A, C, E, G
        secuencia3.add(0, 5);//Clave de vegetación
        secuencia3.add(1, 6);//Caracteristicas del conglomerado
        secuencia3.add(2, 9);//Suelo
        secuencia3.add(3, 10);//Condición de degradación
        secuencia3.add(4, 11);//Herosión hídrica
        secuencia3.add(5, 12);//Deformación del terreno por acción del viento
        secuencia3.add(6, 13);//Observaciones
        secuencia3.add(7, 7);//Carbono e incendios
        secuencia3.add(8, 8);//Longitud interceptada por componente
        secuencia3.add(9, 15);//Hojarscas y profundidad de suelo
        secuencia3.add(10, 16);//Muestra del perfil
        secuencia3.add(11, 18);//Parámetros físico químicos
        secuencia3.add(12, 19);//Repoblado vegetación menor
        secuencia3.add(13, 20);//Arbolado
        secuencia3.add(14,  4);//Submuestra
        
        return secuencia3;
    }
    
    public List<Integer> getSecuencia4() {
        List secuencia4 = new ArrayList<>();
        //Formatos de los módulos A y E.
        secuencia4.add(0, 1);//Sotobosque
        secuencia4.add(1, 2);//Repoblado
        secuencia4.add(2, 3);//Arbolado
        secuencia4.add(3, 4);//Submuestra
        secuencia4.add(4, 5);//Clave de vegetacion
        secuencia4.add(5, 6);//Características del conglomerado
        secuencia4.add(6, 7);//Carbono e incendios
        secuencia4.add(7, 8);//Longitud interceptada por componente
        secuencia4.add(8, 9);//Suelo
        secuencia4.add(9, 10);//Condición de degradación
        secuencia4.add(10, 11);//Herosión hídrica
        secuencia4.add(11, 12);//Deformación del terreno por acción del viento
        secuencia4.add(12, 13);//Observaciones
        secuencia4.add(13, 15);//Hojarscas y profundidad de suelo
        secuencia4.add(14, 16);//Muestra del perfil

        return secuencia4;
    }
    
    public List<Integer> getSecuencia5(){
        List secuencia5 = new ArrayList<>();
        //Formatos de los módulos A, C, D y F
        secuencia5.add(0, 1);//Sotobosque
        secuencia5.add(1, 2);//Repoblado
        secuencia5.add(2, 5);//Clave de vegetación
        secuencia5.add(3, 6);//Caracteristicas del conglomerado
        secuencia5.add(4, 9);//Suelo
        secuencia5.add(5, 10);//Condición de degradación
        secuencia5.add(6, 11);//Herosión hídrica
        secuencia5.add(7, 12);//Deformación del terreno por acción del viento
        secuencia5.add(8, 13);//Observaciones
        secuencia5.add(9, 7);//Carbono e incendios
        secuencia5.add(10, 8);//Longitud interceptada por componente
        secuencia5.add(11, 14);//Arbolado módulo D
        secuencia5.add(12, 4);//Submuestra
        secuencia5.add(13, 17);//Fotografía hemisférica
        return secuencia5;
    }
    
    public List<Integer> getSecuencia6() {
        List secuencia6 = new ArrayList<>();
        //Formatos de los módulos A, C y D
        secuencia6.add(0, 1);//Sotobosque
        secuencia6.add(1, 2);//Repoblado
        secuencia6.add(2, 5);//Clave de vegetación
        secuencia6.add(3, 6);//Caracteristicas del conglomerado
        secuencia6.add(4, 9);//Suelo
        secuencia6.add(5, 10);//Condición de degradación
        secuencia6.add(6, 11);//Herosión hídrica
        secuencia6.add(7, 12);//Deformación del terreno por acción del viento
        secuencia6.add(8, 13);//Observaciones
        secuencia6.add(9, 7);//Carbono e incendios
        secuencia6.add(10, 8);//Longitud interceptada por componente
        secuencia6.add(11, 14);//Arbolado módulo D
        secuencia6.add(12, 4);//Submuestra
        return secuencia6;
    }
    
    public List<Integer> getSecuencia7() {
        List secuencia7 = new ArrayList<>();
        //Formatos de los módulos A, C, D y E
        secuencia7.add(0, 1);//Sotobosque
        secuencia7.add(1, 2);//Repoblado
        secuencia7.add(2, 5);//Clave de vegetación
        secuencia7.add(3, 6);//Caracteristicas del conglomerado
        secuencia7.add(4, 9);//Suelo
        secuencia7.add(5, 10);//Condición de degradación
        secuencia7.add(6, 11);//Herosión hídrica
        secuencia7.add(7, 12);//Deformación del terreno por acción del viento
        secuencia7.add(8, 13);//Observaciones
        secuencia7.add(9, 7);//Carbono e incendios
        secuencia7.add(10, 8);//Longitud interceptada por componente
        secuencia7.add(11, 14);//Arbolado módulo D
        secuencia7.add(12, 4);//Submuestra
        secuencia7.add(13, 15);//Hojarscas y profundidad de suelo
        secuencia7.add(14, 16);//Muestra del perfil

        return secuencia7;
    }
    
    public List<Integer> getSecuencia8() {
        List secuencia8 = new ArrayList<>();
        //Formatos de los módulos A, C, D, E y F
        secuencia8.add(0, 1);//Sotobosque
        secuencia8.add(1, 2);//Repoblado
        secuencia8.add(2, 5);//Clave de vegetación
        secuencia8.add(3, 6);//Caracteristicas del conglomerado
        secuencia8.add(4, 9);//Suelo
        secuencia8.add(5, 10);//Condición de degradación
        secuencia8.add(6, 11);//Herosión hídrica
        secuencia8.add(7, 12);//Deformación del terreno por acción del viento
        secuencia8.add(8, 13);//Observaciones
        secuencia8.add(9, 7);//Carbono e incendios
        secuencia8.add(10, 8);//Longitud interceptada por componente
        secuencia8.add(11, 14);//Arbolado módulo D
        secuencia8.add(12, 4);//Submuestra
        secuencia8.add(13, 15);//Hojarscas y profundidad de suelo
        secuencia8.add(14, 16);//Muestra del perfil
        secuencia8.add(15, 17);//Fotografía hemisférica

        return secuencia8;
    }
    
    public List<Integer> getSecuencia9() {
        List secuencia9 = new ArrayList<>();
        //Formatos de los módulos A, C, E
        secuencia9.add(0, 1);//Sotobosque
        secuencia9.add(1, 2);//Repoblado
        secuencia9.add(2, 3);//Arbolado
        secuencia9.add(3, 4);//Submuestra
        secuencia9.add(4, 5);//Clave de vegetación
        secuencia9.add(5, 6);//Caracteristicas del conglomerado
        secuencia9.add(6, 9);//Suelo
        secuencia9.add(7, 10);//Condición de degradación
        secuencia9.add(8, 11);//Herosión hídrica
        secuencia9.add(9, 12);//Deformación del terreno por acción del viento
        secuencia9.add(10, 13);//Observaciones
        secuencia9.add(11, 7);//Carbono e incendios
        secuencia9.add(12, 8);//Longitud interceptada por componente
        secuencia9.add(13, 15);//Hojarscas y profundidad de suelo
        secuencia9.add(14, 16);//Muestra del perfil
        
        return secuencia9;
    }
    
    public List<Integer> getSecuencia10() {
        List secuencia10 = new ArrayList();
        //Formatos de los módulos A, C, H
        secuencia10.add(0, 2);//Repoblado
        secuencia10.add(1, 3);//Arbolado
        secuencia10.add(2, 4);//Submuestra
        secuencia10.add(3, 5);//Clave de vegetación
        secuencia10.add(4, 6);//Caracteristicas del conglomerado
        secuencia10.add(5, 9);//Suelo
        secuencia10.add(6, 10);//Condición de degradación
        secuencia10.add(7, 11);//Herosión hídrica
        secuencia10.add(8, 12);//Deformación del terreno por acción del viento
        secuencia10.add(9, 13);//Observaciones
        secuencia10.add(10, 7);//Carbono e incendios
        secuencia10.add(11, 8);//Longitud interceptada por componente
        secuencia10.add(12, 21);//Vegetación menor zonas áridas
        secuencia10.add(13, 22);//Vegetación mayor Individuales
        secuencia10.add(14, 23);//Vegetación mayor gregarios
        
        return secuencia10;
    }
   
    public List<Integer> getSecuencia11(){
        List secuencia11 = new ArrayList<>();
        //Formatos de los módulos A y H
       secuencia11.add(0, 2);//Repoblado
       secuencia11.add(1, 3);//Arbolado
       secuencia11.add(2, 4);//Submuestra
       secuencia11.add(3, 5);//Clave de vegetacion
       secuencia11.add(4, 6);//Características del conglomerado
       secuencia11.add(5, 7);//Carbono e incendios
       secuencia11.add(6, 8);//Longitud interceptada por componente
       secuencia11.add(7, 9);//Suelo
       secuencia11.add(8, 10);//Condición de degradación
       secuencia11.add(9, 11);//Herosión hídrica
       secuencia11.add(10, 12);//Deformación del terreno por acción del viento
       secuencia11.add(11, 13);//Observaciones
       secuencia11.add(12, 21);//Vegetación menor zonas áridas
       secuencia11.add(13, 22);//Vegetación mayor Individuales
       secuencia11.add(14, 23);//Vegetación mayor gregarios
       
       return secuencia11;
    }
    
    public List<Integer> getSecuencia12(){
        List secuencia12 = new ArrayList<>();
        //Formatos de los módulos A, E y H
        secuencia12.add(0, 2);//Repoblado
        secuencia12.add(1, 3);//Arbolado
        secuencia12.add(2, 4);//Submuestra
        secuencia12.add(3, 5);//Clave de vegetacion
        secuencia12.add(4, 6);//Características del conglomerado
        secuencia12.add(5, 7);//Carbono e incendios
        secuencia12.add(6, 8);//Longitud interceptada por componente
        secuencia12.add(7, 9);//Suelo
        secuencia12.add(8, 10);//Condición de degradación
        secuencia12.add(9, 11);//Herosión hídrica
        secuencia12.add(10, 12);//Deformación del terreno por acción del viento
        secuencia12.add(11, 13);//Observaciones
        secuencia12.add(12, 15);//Hojarscas y profundidad de suelo
        secuencia12.add(13, 16);//Muestra del perfil
        secuencia12.add(14, 21);//Vegetación menor zonas áridas
        secuencia12.add(15, 22);//Vegetación mayor Individuales
        secuencia12.add(16, 23);//Vegetación mayor gregarios
    
        return secuencia12;
    }
    
    public List<Integer> getSecuencia13(){
        List secuencia13 = new ArrayList<>();
        //Formatos de los módulos A, C, E, H
        secuencia13.add(0, 2);//Repoblado
        secuencia13.add(1, 3);//Arbolado
        secuencia13.add(2, 4);//Submuestra
        secuencia13.add(3, 5);//Clave de vegetación
        secuencia13.add(4, 6);//Caracteristicas del conglomerado
        secuencia13.add(5, 9);//Suelo
        secuencia13.add(6, 10);//Condición de degradación
        secuencia13.add(7, 11);//Herosión hídrica
        secuencia13.add(8, 12);//Deformación del terreno por acción del viento
        secuencia13.add(9, 13);//Observaciones
        secuencia13.add(10, 7);//Carbono e incendios
        secuencia13.add(11, 8);//Longitud interceptada por componente
        secuencia13.add(12, 15);//Hojarascas y profundidad de suelo
        secuencia13.add(13, 16);//Muestra del perfil
        secuencia13.add(14, 21);//Vegetación menor zonas áridas
        secuencia13.add(15, 22);//Vegetación mayor Individuales
        secuencia13.add(16, 23);//Vegetación mayor gregarios
        
        return secuencia13;
    }
    
    public List<Integer> getSecuencia14(){
        List secuencia14 = new ArrayList();
        //Formatos de los modulos A, E, G
        secuencia14.add(0, 5);//Clave de vegetación
        secuencia14.add(1, 6);//Caracteristicas del conglomerado
        secuencia14.add(2, 7);//Carbono e incendios
        secuencia14.add(3, 8);//Longitud interceptada por componente
        secuencia14.add(4, 9);//Suelo
        secuencia14.add(5, 10);//Condición de degradación
        secuencia14.add(6, 11);//Herosión hídrica
        secuencia14.add(7, 12);//Deformación del terreno por acción del viento
        secuencia14.add(8, 13);//Observaciones
        secuencia14.add(9, 15);//Hojarascas y profundidad de suelo
        secuencia14.add(10, 16);//Muestra del perfil
        secuencia14.add(11, 18);//Parámetros físico químicos
        secuencia14.add(12, 19);//Repoblado vegetación menor
        secuencia14.add(13, 20);//Arbolado G
        secuencia14.add(14, 4);//Submuestra
        
        return secuencia14;
    }
    
    public List<Integer> getSecuencia15(){
        List secuencia15 = new ArrayList();
        //Formatos de los modulos A y G
        secuencia15.add(0, 5);//Clave de vegetación
        secuencia15.add(1, 6);//Caracteristicas del conglomerado
        secuencia15.add(2, 7);//Carbono e incendios
        secuencia15.add(3, 8);//Longitud interceptada por componente
        secuencia15.add(4, 9);//Suelo
        secuencia15.add(5, 10);//Condición de degradación
        secuencia15.add(6, 11);//Herosión hídrica
        secuencia15.add(7, 12);//Deformación del terreno por acción del viento
        secuencia15.add(8, 13);//Observaciones
        secuencia15.add(9, 18);//Parámetros físico químicos
        secuencia15.add(10, 19);//Repoblado vegetación menor
        secuencia15.add(11, 20);//Arbolado G
        secuencia15.add(12, 4);//Submuestra
        
        return secuencia15;
    }
    
}
