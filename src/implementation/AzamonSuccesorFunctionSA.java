package implementation;

import java.util.ArrayList;
import java.util.List;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;

/**
 * Created by bejar on 17/01/17
 */
public class AzamonSuccesorFunctionSA implements SuccessorFunction{

    public List getSuccessors(Object eActual){
        ArrayList retval = new ArrayList();
        Estado estado = ((Estado) eActual).copiar();

        int posiblesSwaps = (estado.getNbPaquetes() * (estado.getNbPaquetes()-1))/2;
        int posiblesOfertas = estado.getNbPaquetes() * estado.getNbOfertas();

        int opSel = (int)(Math.random()*(posiblesSwaps+posiblesOfertas));

        // Elegiremos que operador usamos, si opRGN < 0.5, entonces haremos swap. En caso contrário haremos un move.
        double opRGN = Math.random();
        if (opSel < posiblesSwaps){
            // Usar operador swap

            int numPaq1, numPaq2;
            numPaq1 = numPaq2 = 0;

            // Cogeremos dos enteros aleatorios entre [0, n) que sean distintos entre ellos
            while (numPaq1 == numPaq2) {
                numPaq1 = (int)(Math.random() * estado.getNbPaquetes());
                numPaq2 = (int)(Math.random() * estado.getNbPaquetes());
            }
            estado.swapPaquets(numPaq1, numPaq2);
            retval.add(new Successor("swap " + numPaq1 + " " + numPaq2, estado));
        }
        else {
            //Usar operador move

            int numPaq, numOferta;
            numPaq = (int)(Math.random() * estado.getNbPaquetes());
            numOferta = (int)(Math.random() * estado.getNbOfertas());

            estado.moverPaquete(numPaq, numOferta);
            retval.add(new Successor("mover " + numPaq + " " + numOferta, estado));
        }


        return retval;
    }
}
