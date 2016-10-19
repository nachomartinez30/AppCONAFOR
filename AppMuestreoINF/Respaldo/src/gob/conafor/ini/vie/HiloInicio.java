package gob.conafor.ini.vie;

import javax.swing.JProgressBar;


public class HiloInicio extends Thread{

    private JProgressBar progreso;

    public HiloInicio(JProgressBar progreso) {
        super();
        this.progreso = progreso;
    }
public void run(){
    for(int i = 1; i <= 100; i++){
        this.progreso.setValue(i);
        pausa(10);
    }
}

public void pausa(int mlSeg){
    try{
        Thread.sleep(mlSeg);
    } catch(Exception e){
        
    }
}
    
    
}
