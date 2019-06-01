package br.exercicio.semaforo.base;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * Representa o Semaforo
 *
 * @author mariojp
 */
public class Semaforo extends JPanel implements Runnable {

    public static final Color VERMELHO = Color.red;
    public static final Color AMARELO = Color.yellow;
    public static final Color VERDE = Color.green;


    private Semaforo semaforoAnterior;
    public int luz = 1;
    private Boolean isInit = false;

    private Luz luz1 = new Luz(null);
    private Luz luz2 = new Luz(null);
    private Luz luz3 = new Luz(null);


    public void setColorLuz1(Color cor) {
        this.luz1.setCor(cor);
    }
    public void setColorLuz2(Color cor) {
        this.luz2.setCor(cor);
    }
    public void setColorLuz3(Color cor) {
        this.luz3.setCor(cor);
    }


    public Semaforo(int inicial){
        this.luz = inicial;
        add(luz1);
        add(luz2);
        add(luz3);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawRect(0,0,30,80);
    }

    public Dimension getPreferredSize() {
        return new Dimension(30,80);
    }

    public void mudaCor(){
    	
        switch (luz){
            case 1 :
                luz1.setCor(null);
                luz2.setCor(null);
                luz3.setCor(VERDE);
                this.luz = 2;
                break;
            case 2 :
                luz1.setCor(null);
                luz2.setCor(AMARELO);
                luz3.setCor(null);
                this.luz = 3;
                break;
            case 3 :
            default:
            
                luz1.setCor(VERMELHO);
                luz2.setCor(null);
                luz3.setCor(null);
                this.luz = 1;
                break;
        }
        
    }

    public void run() {
        while(true){
        	if(semaforoAnterior != null) {
	        	synchronized (semaforoAnterior) {
	        		if( luz == 1 && semaforoAnterior.luz == 2) {
		    			semaforoAnterior.notifyAll();
		    			
		    		}
				}
        	}
        	this.mudaCor();
        	synchronized(this) {
	    		
	    		
	    		try {
	            	
	    			if(semaforoAnterior != null && luz == 2 && semaforoAnterior.luz != 1) {
	    				
	    				wait();
	    			} 
	    			
	    		} catch (InterruptedException e) {
	    		}
	    	
	            try {
	                switch (luz){
	                    case 1 :
	                        Thread.sleep(2000);
	                        break;
	                    case 2 :
	                        Thread.sleep(2000);
	                        break;
	                    case 3 :
	                        Thread.sleep(2000);
	                        break;
	
	
	                }
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            
	        }
        }
    }
    
    public void setSemaforoAnterior(Semaforo semaforo) {
    	this.semaforoAnterior = semaforo;
    }

}
