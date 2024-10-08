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
    public void asignarPaquetesIniciales1() {

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
                    int fel =  felicitatPaquetAOferta(paquete, ofertas.get(j)); //veure la diferencia
                    if (fel >= 0) { //Comproba que s'entregui en el termini, que no sigui negatiu bàsicament
                        // Assignar el paquet a l'oferta
                        asignaciones.set(i, j);
                        espacioDisponibleOfertas.set(j, espacioDisponibleOfertas.get(j) - paquete.getPeso());
                        felicidad += fel;
                        precio += preuPaquetAOferta(paquete, ofertas.get(j));
                        break;
                    }
                }
            }
        }
    }

    //Una altra proposta de generació de solució inicial encara no implementada
    public void asignarPaquetesIniciales2() {

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

    /*Funcions  auxiliar per als operadors*/

    //Retorna per un paquet p si es pot assignar en la oferta o, tenint en compte les restriccions de pes i temps
    private boolean compleixCondicioAplicabilitat(Paquete p, Oferta o, int i, double incrementPes) {
        int priorPaq = getDiasPaquete(p);
        return o.getDias() <= priorPaq && espacioDisponibleOfertas.get(i) >= incrementPes;
    }

    //Retorna el preu de un paquet p a una oferta o
    private double preuPaquetAOferta(Paquete p, Oferta o) {
        return p.getPeso()*o.getPrecio();
    }

    //Retorna la felicitat dels clients d'un paquet p assignat a una oferta o, negatiu  si el paquet no arriba a temps
    private int felicitatPaquetAOferta(Paquete p, Oferta o) {
        return getDiasPaquete(p) - o.getDias();
    }

    /*Operadors del problema*/

    //Intercanvia dos paquets que estiguin en ofertes diferents.
    //Condició d'aplicabilitat: després del swap cap de les 2 oferta excedeix la seva capacitat màxima i els paquets intercanviats arriben dins el termini
    public void swapPaquets(int p1, int p2) { //index dels paquet a intercanviar
        int oferta1 = asignaciones.get(p1);
        int oferta2 = asignaciones.get(p2);

        if (oferta1 != oferta2) { //Només si no estan a la mateixa oferta
            Paquete paq1 = paquetes.get(p1);
            Paquete paq2 = paquetes.get(p2);
            Oferta o1 = ofertas.get(oferta1);
            Oferta o2 = ofertas.get(oferta2);
            double incPes1 =  paquetes.get(p1).getPeso() - paquetes.get(p2).getPeso(); //posar dif de pes de p1 a 02
            double incPes2 =  paquetes.get(p2).getPeso() - paquetes.get(p1).getPeso(); //posar dif de pes de p2 a 01

            //Fem l'intercanvi després de fer les comprovacions
            if (compleixCondicioAplicabilitat(paq1, o2, oferta2, Math.max(0.0,incPes1)) && compleixCondicioAplicabilitat(paq2, o1, oferta2,Math.max(0.0, incPes2))) {
                Collections.swap(asignaciones, p1, p2);
                felicidad  += (felicitatPaquetAOferta(paq1, o2) - felicitatPaquetAOferta(paq1, o1)) + (felicitatPaquetAOferta(paq2, o1) - felicitatPaquetAOferta(paq2, o2)); //Recalcul de la felicitat
                precio += (preuPaquetAOferta(paq1, o2) - preuPaquetAOferta(paq1, o1)) + (preuPaquetAOferta(paq2, o1) - preuPaquetAOferta(paq2, o2)); //Recalcul del preu
                //Actualitzem l'espai disponible
                espacioDisponibleOfertas.set(oferta1, espacioDisponibleOfertas.get(oferta1)-incPes2);
                espacioDisponibleOfertas.set(oferta2, espacioDisponibleOfertas.get(oferta2)-incPes1);
            }
        }
    }

    //Mou un paquet p a un oferta o diferent a la qual està
    //Condició d'aplicabilitat: després del moviment la oferta no excedeix la seva capacitat màxima i el paquet mogut arriba dins el termini
    public void mourePaquet(int p, int o) { //index del paquet i de la oferta
        int oActual = asignaciones.get(p);

        if (o != oActual) { //Nomes si es una oferta diferent a la qual esta
            double pes = paquetes.get(p).getPeso();
            if (compleixCondicioAplicabilitat(paquetes.get(p), ofertas.get(o), o, pes)) { //Assignem al paquet p la oferta o si compleix la condicio d'aplicabilitat
                asignaciones.set(p, o);
                //Actualitzem les característiques de l'estat
                felicidad += felicitatPaquetAOferta(paquetes.get(p), ofertas.get(o)) - felicitatPaquetAOferta(paquetes.get(p), ofertas.get(oActual));
                precio += preuPaquetAOferta(paquetes.get(p), ofertas.get(o)) - preuPaquetAOferta(paquetes.get(p), ofertas.get(oActual));
                espacioDisponibleOfertas.set(o, espacioDisponibleOfertas.get(o)-pes);
                espacioDisponibleOfertas.set(oActual, espacioDisponibleOfertas.get(oActual)+pes);

            }
        }
    }

    /* Heuristic function 1*/
    public double heuristic1(){
        //Proposta de heurística tenint en compte només el criteris de qualitat sobre els costos
        double val = 0;
        val = precio*precio; //minimitzar la funció, (minimitzar costos)
        return val;
    }

    /* Heuristic function 2*/
    public double heuristic2(){
        //Proposta de heurística tenint en compte els dos criteris de qualitat de solució
        double val = 0;
        val = -felicidad*felicidad + precio*precio; //minimitzar la funció, (maximitzar la felicitat i minimitzar costos)
        return val;
    }


    /* Goal test */  // No se si s'ha d'usar o adaptar al nostre problema o no cal
    public boolean is_goal(){
        // compute if board = solution
        /*
        for (int i = 0; i < board.length; i++) {
            if (board[i] != solution[i]) {
                return false;
            }
        }*/
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






