package IA.ProbIA5;

import java.util.ArrayList; // Para usar ArrayList
import java.util.List; // Para usar List
import IA.Azamon.Oferta; // Importar Oferta de Azamon.jar
import IA.Azamon.Paquete; // Importar Paquete de Azamon.jar

/**
 * Created by bejar on 17/01/17.
 */
public class ProbIA5Board {
    /* Class independent from AIMA classes
       - It has to implement the state of the problem and its operators
     */

    /**
     * Clase que representa una asignación de un paquete a una oferta de transporte
     */
    public class Asignacion {
        public Paquete paquete;
        public Oferta oferta;

        public Asignacion(Paquete paquete, Oferta oferta) {
            this.paquete = paquete;
            this.oferta = oferta;
        }
    }

    /* State data structure
       List with the current assignments of packages to offers
     */
    private List<Asignacion> estado = new ArrayList<>();


    /// ELIMINAR: esto es el estado de probIA5
    private int [] board;
    private static int [] solution;

    /* Constructor */
    public ProbIA5Board(int []init, int[] goal) {

        board = new int[init.length];
        solution = new int[init.length];

        for (int i = 0; i< init.length; i++) {
            board[i] = init[i];
            solution[i] = goal[i];
        }

    }

    /* vvvvv TO COMPLETE vvvvv */
    public void flip_it(int i){
        // flip the coins i and i + 1
        board[i] = 1 - board[i];
        board[(i+1)%board.length] = 1 - board[(i+1)%board.length];
    }

    /* Heuristic function */
    public double heuristic(){
        // compute the number of coins out of place respect to solution
        int val = 0;
        for (int i = 0; i < board.length; i++) {
            if (board[i] != solution[i]) {
                val++;
            }
        }
        return val;
    }

     /* Goal test */
     public boolean is_goal(){
        // output board and solution
        //  System.out.println("Board:    " + board[0] + board[1] + board[2] + board[3] + board[4]);
        //  System.out.println("Solution: " + solution[0] + solution[1] + solution[2] + solution[3] + solution[4]);

        // compute if board = solution
        for (int i = 0; i < board.length; i++) {
            if (board[i] != solution[i]) {
                return false;
            }
        }
        return true; 
     }

     /* auxiliary functions */
     // Some functions will be needed for creating a copy of the state, getConfiguration()
     
     public int[] getConfiguration(){
        // return the configuration of the board
        return board;
     }

     public int[] getSolution(){
        // return the solution
        return solution;
     }

    /* ^^^^^ TO COMPLETE ^^^^^ */

}
