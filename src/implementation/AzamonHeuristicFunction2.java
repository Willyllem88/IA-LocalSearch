package implementation;

/**
 * Created by bejar on 17/01/17.
 *  La segona funció heurística té com a criteri la combinació de la minimització dels costos de transport i d'emmagatzematge
 *  i la maximització de la felicitat dels clients.
 */

import aima.search.framework.HeuristicFunction;

public class AzamonHeuristicFunction2 implements HeuristicFunction {

    public double getHeuristicValue(Object n){

        return ((Estado) n).heuristic2();
    }
}
