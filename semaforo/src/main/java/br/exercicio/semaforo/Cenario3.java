package br.exercicio.semaforo;

import br.exercicio.semaforo.base.Semaforo;
import br.exercicio.semaforo.base.Simulador;

import java.util.ArrayList;
import java.util.List;


/**
 *
 *
 * Três semaforos
 *
 * Deve funcionar como uma onda verde.
 * Os semaforos devem abrir e fechar em sequencia.
 *
 *
 */
public class Cenario3 {


    Semaforo semaforo1 = new Semaforo(1);
    Semaforo semaforo2 = new Semaforo(2);
    Semaforo semaforo3 = new Semaforo(3);

    public Cenario3() {
    	semaforo1.setSemaforoAnterior(semaforo2);
    	semaforo2.setSemaforoAnterior(semaforo3);
    	semaforo3.setSemaforoAnterior(semaforo1);
        List<Semaforo> semaforos = new ArrayList<Semaforo>();
        semaforos.add(semaforo1);
        semaforos.add(semaforo2);
        semaforos.add(semaforo3);

        Simulador simulador = new Simulador(semaforos,300,150);
        new Thread(semaforo1).start();
        new Thread(semaforo2).start();
        new Thread(semaforo3).start();


    }

    public static void main(String[] args) {
        new Cenario3();
    }
}
