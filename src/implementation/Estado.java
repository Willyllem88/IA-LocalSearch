package implementation;

import java.util.ArrayList;
import java.util.List;
import IA.Azamon.Paquete;
import IA.Azamon.Oferta;
import IA.Azamon.Paquetes;
import IA.Azamon.Transporte;


/**
 * Created by bejar on 17/01/17.
 */
public class Estado {
    /* Class independent from AIMA classes
       - It has to implement the state of the problem and its operators
     */

    /* State data structure
       List with the current assignments of packages to offers
     */



    //*********************això no sé si hauria de ser static
    private Paquetes paquetes;//listado de paquetes a asignar
    private Transporte ofertas;//listado de ofertas de transporte

    private List<Integer> asignaciones = new ArrayList<>(); //el índice indica el paquete, el contenido
    // la oferta a la está asociado, -1 si no tiene ninguna oferta asignada
    private List<Double> espacioDisponibleOfertas;
    private int felicidad;

    /* Constructor */
    public Estado(Paquetes paquetes, Transporte ofertas) {
        //inicializamos estado
        felicidad = 0;
        this.paquetes = paquetes;
        this.ofertas = ofertas;

        asignaciones = new ArrayList<Integer>(paquetes.size());
        for (int i = 0; i < paquetes.size(); i++) {
            asignaciones.add(-1);
        }

        espacioDisponibleOfertas = new ArrayList<Double>(ofertas.size());
        for (int i = 0; i < ofertas.size(); i++) {
            espacioDisponibleOfertas.add(ofertas.get(i).getPesomax());
        }

    }

    //solución inicial. NO RECUERDO CUÁL SE DIJO EN CLASE, esto es básicamente dónde quepa (a cambiar)
    public void asignarPaquetesIniciales(){
        for (int i = 0; i < paquetes.size(); i++) {
            Paquete paquete = paquetes.get(i);

            // Busca una oferta donde haya espacio para el paquete
            for (int j = 0; j < ofertas.size(); j++) {
                if (espacioDisponibleOfertas.get(j) >= paquete.getPeso()) {
                    // Assignar el paquet a l'oferta
                    asignaciones.set(i, j);
                    espacioDisponibleOfertas.set(j, espacioDisponibleOfertas.get(j) - paquete.getPeso());
                    break;
                }
            }
        }
    }



    /**
     * Clase que representa una asignación de un paquete a una oferta de transporte
     * No borro por no dañar los sentimientos de nadie but creo que ya no hace falta

    public class Asignacion {
        public Paquete paquete;
        public Oferta oferta;

        public Asignacion(Paquete paquete, Oferta oferta) {
            this.paquete = paquete;
            this.oferta = oferta;
        }
    }
     */






    /*
    public void flip_it(int i){
        // flip the coins i and i + 1
        board[i] = 1 - board[i];
        board[(i+1)%board.length] = 1 - board[(i+1)%board.length];
    }

    //Heuristic function
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
*/
    /* Goal test */

    /*
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


     */

    /* auxiliary functions */
    // Some functions will be needed for creating a copy of the state, getConfiguration()
/*
    public int[] getConfiguration(){
        // return the configuration of the board
        return board;
    }

    public int[] getSolution(){
        // return the solution
        return solution;
    }
*/

}