import IA.Azamon.Paquetes;
import IA.Azamon.Transporte;
import implementation.*;

import aima.search.framework.GraphSearch;
import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
//Ya no hacen falta estos algoritmos
//import aima.search.informed.AStarSearch;
//import aima.search.informed.IterativeDeepeningAStarSearch;

// importamos los algoritmos que usamos para la práctica
import aima.search.informed.HillClimbingSearch;
import aima.search.informed.SimulatedAnnealingSearch;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws Exception{
        /**
         *  For a problem to be solvable:
         *    count(0,prob) % 2 == count(0,sol) %2
         */
        // int [] prob = new int []{1 ,1, 1, 0, 0};
        //int [] sol = new int[]{1, 0, 0, 1, 1};

        //ProbIA5Board board = new ProbIA5Board(prob, sol );
        int n = 30;
        int seed = 1304;
        Paquetes paq = new Paquetes(n,seed);
        double prop = 1.2; //No he entes aquest parametre, ni com el podem treure
        Transporte ofertas = new Transporte(paq,prop,seed);

        Estado azamon = new Estado(paq, ofertas);

        // Create the Problem object
        Problem p = new  Problem(/*board*/azamon,
                                new AzamonSuccesorFunctionHC(),
                                new AzamonGoalTest(),
                                new AzamonHeuristicFunction1());

        // Instantiate the search algorithm
	    // AStarSearch(new GraphSearch()) or IterativeDeepeningAStarSearch()
        // Search alg1 = new AStarSearch(new GraphSearch());
        //Ponemos nuestro algoritmo Hill Climbing por ejemplo
        Search alg = new HillClimbingSearch(); //no le hace falta pasar ningun argumento



        // Instantiate the SearchAgent object
        SearchAgent agent = new SearchAgent(p, alg);

	// We print the results of the search
        System.out.println();
        printActions(agent.getActions());
        printInstrumentation(agent.getInstrumentation());

        // You can access also to the goal state using the
	// method getGoalState of class Search

    }

        private static void printInstrumentation(Properties properties) {
        Iterator keys = properties.keySet().iterator();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            String property = properties.getProperty(key);
            System.out.println(key + " : " + property);
        }
        
    }
    
    private static void printActions(List actions) {
        for (int i = 0; i < actions.size(); i++) {
            String action = (String) actions.get(i);
            System.out.println(action);
        }
    }
    
}
