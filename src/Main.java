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
        // Se define el número de paquetes (n) y la semilla (seed) para generar aleatoriedad
        int n = 30;
        int seed = 1304;
        double prop = 1.2;

        // Se crean los conjuntos de paquetes y ofertas
        Paquetes paq = new Paquetes(n,seed);
        Transporte ofertas = new Transporte(paq,prop,seed);


        // Se crea el estado inicial de Azamon con los paquetes y las ofertas generadas
        Estado azamon = new Estado(paq, ofertas);
        azamon.asignarPaquetesIniciales1();

        // Printeamos los parámetros del estado inicial
        System.out.println("PARÁMETROS DEL ESTADO INICIAL:");
        System.out.println("  - Felicidad: " + azamon.getFelicidad());
        System.out.println("  - Percio: " + azamon.getPrecio());

        // Se crea el objeto Problem, que incluye el estado inicial (azamon), la función sucesora,
        // el test de objetivo y la función heurística
        Problem p = new Problem(/*board*/azamon,
                new AzamonSuccesorFunctionHC(),
                new AzamonGoalTest(),
                new AzamonHeuristicFunction1());

        // Se instancia el algoritmo de búsqueda Hill Climbing (subida de colinas)
        Search alg = new HillClimbingSearch(); // No requiere argumentos adicionales

        // Se instancia el agente de búsqueda, que ejecuta el problema con el algoritmo especificado
        SearchAgent agent = new SearchAgent(p, alg);

        // Se imprimen las acciones realizadas por el agente durante la búsqueda y las propiedades de instrumentación que brindan detalles sobre la búsqueda
        System.out.println();
        printActions(agent.getActions());
        printInstrumentation(agent.getInstrumentation());

        // Se printea los paraámetros resultantes
        Estado goal = (Estado)alg.getGoalState();
        System.out.println();
        System.out.println("PARÁMETROS DEL ESTADO RESULTANTE:");
        System.out.println("  - Felicidad: " + goal.getFelicidad());
        System.out.println("  - Percio: " + goal.getPrecio());
    }

    // Método para imprimir las propiedades de instrumentación, como el tiempo de ejecución, nodos expandidos, etc.
    private static void printInstrumentation(Properties properties) {
        Iterator keys = properties.keySet().iterator();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            String property = properties.getProperty(key);
            System.out.println(key + " : " + property);
        }
    }

    // Método para imprimir las acciones realizadas por el agente durante la búsqueda
    private static void printActions(List actions) {
        for (int i = 0; i < actions.size(); i++) {
            String action = (String) actions.get(i);
            System.out.println(action);
        }
    }
}
