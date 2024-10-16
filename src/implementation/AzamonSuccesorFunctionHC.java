package implementation;

import aima.search.framework.SuccessorFunction;
import aima.search.framework.Successor;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bejar on 17/01/17
 */
public class AzamonSuccesorFunctionHC implements SuccessorFunction{

    public List getSuccessors(Object state){
        ArrayList retval = new ArrayList();
        Estado amazon = (Estado) state;//copia estado

        //Todas las combinaciones de swap paquetes
        for(int i = 0; i < amazon.getNbPaquetes(); i++){
            for (int j = i+1; j < amazon.getNbPaquetes(); j++){
                //Solo si no coinciden los paquetes (no tiene sentido hacer swap de dos paquetes iguales)
                if (i != j) {
                    Estado new_state = amazon.copiar();
                    new_state.swapPaquets(i, j);
                    retval.add(new Successor("swap " + i + " " + j, new_state));
                }
            }
        }
        //Todas las combinaciones de mover paquete a oferta
        for (int i = 0; i < amazon.getNbPaquetes(); i++) {
            for (int j = 0; j < amazon.getNbOfertas(); j++) {
                Estado new_state = amazon.copiar();
                new_state.moverPaquete(i, j);
                retval.add(new Successor("mover " + i + " " + j, new_state));
            }
        }

        return retval;
    }
}
