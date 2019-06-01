package br.exercicio.semaforo;

import java.util.ArrayList;
import java.util.List;

import br.exercicio.semaforo.base.Semaforo;
import br.exercicio.semaforo.base.Simulador;


/**
 *
 * Dois Semaforos voce deve sincronizalos como um cruzamento
 *
 */
public class Cenario2 {


    Semaforo semaforo1 = new Semaforo(1);
    Semaforo semaforo2 = new Semaforo(3);

    public Cenario2() {
    	semaforo1.setSemaforoAnterior(semaforo2);
    	semaforo2.setSemaforoAnterior(semaforo1);
        List<Semaforo> semaforos = new ArrayList<Semaforo>();
        semaforos.add(semaforo1);
        semaforos.add(semaforo2);

        Simulador simulador = new Simulador(semaforos,300,150);
        start();


    }

    private void start() {
    	new Thread(semaforo1).start();
    	
        new Thread(semaforo2).start();
		
	}

	public static void main(String[] args) {
        new Cenario2();
    }
}
