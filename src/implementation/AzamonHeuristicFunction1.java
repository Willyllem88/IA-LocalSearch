package implementation;

/**
 * Created by bejar on 17/01/17.
 * La primera funció heurística té com a criteri la minimització dels costos de transport i d'emmagatzematge.
 */

import aima.search.framework.HeuristicFunction;

public class AzamonHeuristicFunction1 implements HeuristicFunction {

    public double getHeuristicValue(Object n){

        return ((Estado) n).heuristic1();
    }
}
