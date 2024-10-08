package implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
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
    private double precio;

    /* Constructor */
    public Estado(Paquetes paquetes, Transporte ofertas) {
        //inicializamos estado
        felicidad = 0;
        precio = 0;
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
    public void asignarPaquetesIniciales() {

        //Generar aleatorietat en l'ordre de paquet, els index dels paquets a colocar
        List<Integer> l = new ArrayList<>();
        for (int i = 0; i < paquetes.size(); i++) l.add(i);

        Collections.shuffle(l); // Shuffle the list

        //Assignem paquets
        for (int i = 0; i < paquetes.size(); i++) {
            Paquete paquete = paquetes.get(l.get(i));

            // Busca una oferta donde haya espacio para el paquete
            for (int j = 0; j < ofertas.size(); j++) {
                if (espacioDisponibleOfertas.get(j) >= paquete.getPeso()) {
                    int fel =  getDiasPaquete(paquete) - ofertas.get(j).getDias(); //veure la diferencia
                    if (fel >= 0) { //Comproba que s'entregui en el termini, que no sigui negatiu bàsicament
                        // Assignar el paquet a l'oferta
                        asignaciones.set(i, j);
                        espacioDisponibleOfertas.set(j, espacioDisponibleOfertas.get(j) - paquete.getPeso());
                        felicidad += fel*fel; //Proposta de fer-ho quadrat pero potser millor a la funció heurística
                        precio = paquete.getPeso()*ofertas.get(j).getPrecio();
                        break;
                    }
                }
            }
        }
    }

    //Retorna el termini d'entrega màxim en dies segons la prioritat d'un paquet
    private int getDiasPaquete(Paquete paquete) {
        int pr = paquete.getPrioridad();
        return switch (pr) {
            case 0 -> 1;
            case 1 -> 3;
            case 2 -> 5;
            default -> 0;
        };
    }


    /* Heuristic function */
    public double heuristic1(){
        //Proposta de heurística tenint en compte només el criteris de qualitat sobre els costos
        double val = 0;
        val = precio*precio; //minimitzar la funció, (minimitzar costos)
        return val;
    }

    /* Heuristic function */
    public double heuristic2(){
        //Proposta de heurística tenint en compte els dos criteris de qualitat de solució
       double val = 0;
        val = -felicidad*felicidad + precio*precio; //minimitzar la funció, (maximitzar la felicitat i minimitzar costos)
        return val;
    }


    /* Goal test */  // No se si s'ha d'usar o adaptar al nostre problema o no cal
    public boolean is_goal(){
        // compute if board = solution
        for (int i = 0; i < board.length; i++) {
            if (board[i] != solution[i]) {
                return false;
            }
        }
        return true;
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






