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
import java.util.Random;


//Classe importada para leer desde la terminal
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception{
        // Se define el número de paquetes (n) y la semilla (seed) para generar aleatoriedad
        int n = 30;
        int seed = 1304;
        double prop = 1.3;

        Random random = new Random(seed);

        // Creamos el Scanner para leer input
        Scanner scanner = new Scanner(System.in);

        // Pedimos al usuario configuraciones
        System.out.print("Introduce número de paquetes, semilla y una proporcion: ");
        n = scanner.nextInt();
        seed = scanner.nextInt();
        prop = scanner.nextDouble();

        // Se crean los conjuntos de paquetes y ofertas
        Paquetes paq = new Paquetes(n,seed);
        Transporte ofertas = new Transporte(paq,prop,seed);

        // Se crea el estado inicial de Azamon con los paquetes y las ofertas generadas
        Estado azamon = new Estado(paq, ofertas, random);
        System.out.println("Escoge generador de estado inicial");
        System.out.println(" [0] Solución más 'buena'");
        System.out.println(" [1] Solución más aleatoria");
        int tipoGen = scanner.nextInt();
        if (tipoGen == 0) azamon.asignarPaquetesIniciales1();
        else azamon.asignarPaquetesIniciales2();

        //Pedimos al usuario que escoga entre las dos heurísticas
        System.out.println("Escoge heurística del algoritmo:");
        System.out.println(" [0] Precio");
        System.out.println(" [1] Precio + felicidad");
        int heur = scanner.nextInt();


        // Pedimos al usuario el algoritmo a usar a escoger entre 2
        System.out.println("Escoge un algoritmo a usar: ");
        System.out.println(" [0] Hill Climbing");
        System.out.println(" [1] Simulated Annealing");
        int tipoAlg = scanner.nextInt();

        long startTime = System.currentTimeMillis();

        Search alg; //Objeto ara el algoritmo

        // Se instancia el algoritmo de búsqueda Hill Climbing (subida de colinas)
        if (tipoAlg == 0) alg = new HillClimbingSearch(); // No requiere argumentos adicionales
        else {
            System.out.print("Introduce los siguientes parámetros del algoritmo: steps, nIteraciones, k, lambda: ");
            int steps = scanner.nextInt();
            int nIter = scanner.nextInt();
            int k = scanner.nextInt();
            double lambda  = scanner.nextDouble();
            alg = new SimulatedAnnealingSearch(steps, nIter, k, lambda); //Simulated Annealing escogido
        }
        Problem p;
        if (heur == 0) { //Heurística 1
            // Se crea el objeto Problem, que incluye el estado inicial (azamon), la función sucesora,
            // el test de objetivo y la función heurística
            p = new Problem(azamon,
                    (tipoAlg == 1) ? new AzamonSuccesorFunctionSA() : new AzamonSuccesorFunctionHC(),
                    new AzamonGoalTest(),
                    new AzamonHeuristicFunction1());
        }
        else { //Heurística 2
            p = new Problem(azamon,
                    (tipoAlg == 1) ? new AzamonSuccesorFunctionSA() : new AzamonSuccesorFunctionHC(),
                    new AzamonGoalTest(),
                    new AzamonHeuristicFunction2());
        }

        // Se instancia el agente de búsqueda, que ejecuta el problema con el algoritmo especificado
        SearchAgent agent = new SearchAgent(p, alg);

        long endTime = System.currentTimeMillis();

        // Se imprimen las acciones realizadas por el agente durante la búsqueda y las propiedades de instrumentación que brindan detalles sobre la búsqueda
        //System.out.println();
        //printActions(agent.getActions());
        //printInstrumentation(agent.getInstrumentation());

        // Printeamos los parámetros del estado inicial
        System.out.println();
        System.out.println("PARÁMETROS DEL ESTADO INICIAL:");
        System.out.println("  - Felicidad: " + azamon.getFelicidad());
        System.out.println("  - Precio: " + azamon.getPrecio());

        // Se printea los parámetros resultantes
        Estado goal = (Estado)alg.getGoalState();
        System.out.println();
        System.out.println("PARÁMETROS DEL ESTADO RESULTANTE:");
        System.out.println("  - Felicidad: " + goal.getFelicidad());
        System.out.println("  - Precio: " + goal.getPrecio());

        // Pintamos cuanto tiempo se ha tardado en obtener el resultado
        System.out.println();
        System.out.println("Tiempo requerido: " + (endTime - startTime) + "ms");

        main(args);
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
            Object action = actions.get(i);
            System.out.println(action.toString());
        }
    }
}
