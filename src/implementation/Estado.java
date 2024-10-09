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
            asignaciones.add(-1);//-1 porque no estan asignados a ninguna oferta
        }

        espacioDisponibleOfertas = new ArrayList<Double>(ofertas.size());
        for (int i = 0; i < ofertas.size(); i++) {
            espacioDisponibleOfertas.add(ofertas.get(i).getPesomax());
        }
    }

    //solución inicial.
    public void asignarPaquetesIniciales1() {

        //Generar aleatoriedad en el orden de paquete, el índice de los paquetes a colocar
        List<Integer> l = new ArrayList<>();
        for (int i = 0; i < paquetes.size(); i++) l.add(i);

        Collections.shuffle(l); // Shuffle en la lista

        //Asignamos paquetes
        for (int i = 0; i < paquetes.size(); i++) {
            Paquete paquete = paquetes.get(l.get(i));

            // Busca una oferta donde haya espacio para el paquete
            for (int j = 0; j < ofertas.size(); j++) {
                if (espacioDisponibleOfertas.get(j) >= paquete.getPeso()) {
                    int fel =  felicitatPaquetAOferta(paquete, ofertas.get(j)); //ver la diferencia
                    if (fel >= 0) { //Comprueba que se entregue en el plazo, que no sea negativo básicamente
                        //Asignar el paquete a la oferta
                        asignaciones.set(i, j);
                        espacioDisponibleOfertas.set(j, espacioDisponibleOfertas.get(j) - paquete.getPeso());
                        felicidad += fel;
                        precio += precioPaqueteAOferta(paquete, ofertas.get(j));
                        break;
                    }
                }
            }
        }
    }

    //Otra propuesta de generación de solución inicial, menos perfecta
    public void asignarPaquetesIniciales2() {

        //Enviamos los paquetes justo cuando toca, no buscamos si hay espacio en ofertas que se entreguen antes (no habrá felicidad)
        for (int i = 0; i < paquetes.size(); i++) {
            Paquete paquete = paquetes.get(i);
            int diasEntrega = getDiasPaquete(paquetes.get(i));
            for (int j = 0; j < ofertas.size(); j++) {
                if((diasEntrega == ofertas.get(j).getDias()) && (espacioDisponibleOfertas.get(j) >= paquete.getPeso())){//si coincide el plazo de entrega y cabe, lo asignamos
                    asignaciones.set(i, j);
                    espacioDisponibleOfertas.set(j, espacioDisponibleOfertas.get(j) - paquete.getPeso());
                    precio += precioPaqueteAOferta(paquete, ofertas.get(j));
                    break;
                    //TODOS ENTRARAN AQUI??????????????????????
                }

            }

        }

    }

    //Devuelve el plazo de entrega máximo en días según la prioridad de un paquete
    private int getDiasPaquete(Paquete paquete) {
        int pr = paquete.getPrioridad();
        return switch (pr) {
            case 0 -> 1;
            case 1 -> 3;
            case 2 -> 5;
            default -> 0;
        };
    }

    /*Funciones  auxiliares para los operadores*/

    //Devuelve por un paquete p si se puede asignar en la oferta o, teniendo en cuenta las restricciones de peso y tiempo
    private boolean cumpleCondicionAplicabilidad(Paquete p, Oferta o, int i, double incrementPes) {
        int priorPaq = getDiasPaquete(p);
        return o.getDias() <= priorPaq && espacioDisponibleOfertas.get(i) >= incrementPes;
    }

    //Retorna el precio de un paquete p a una oferta o
    private double precioPaqueteAOferta(Paquete p, Oferta o) {
        return p.getPeso()*o.getPrecio();
    }

    //Retorna la felicidad de los clientes de un paquete p asignado a una oferta o, negativo  si el paquete no llega a tiempo
    private int felicitatPaquetAOferta(Paquete p, Oferta o) {
        return getDiasPaquete(p) - o.getDias();
    }

    /*Operadores del problema*/

    //Intercambia dos paquetes que estén en ofertas distintas.
    //Condición de aplicabilidad: después del swap ninguna de las 2 oferta excede su capacidad máxima y los paquetes intercambiados llegan dentro del plazo
    public void swapPaquets(int p1, int p2) { //índice de los paquetes a intercambiar
        int oferta1 = asignaciones.get(p1);
        int oferta2 = asignaciones.get(p2);

        if (oferta1 != oferta2) { //Sólo si no están en la misma oferta
            Paquete paq1 = paquetes.get(p1);
            Paquete paq2 = paquetes.get(p2);
            Oferta o1 = ofertas.get(oferta1);
            Oferta o2 = ofertas.get(oferta2);
            double incPes1 =  paquetes.get(p1).getPeso() - paquetes.get(p2).getPeso(); //poner dif de peso de p1 a 02
            double incPes2 =  paquetes.get(p2).getPeso() - paquetes.get(p1).getPeso(); //poner dif de peso de p2 a 01

            //Hacemos el intercambio después de realizar las comprobaciones
            if (cumpleCondicionAplicabilidad(paq1, o2, oferta2, Math.max(0.0,incPes1)) && cumpleCondicionAplicabilidad(paq2, o1, oferta2,Math.max(0.0, incPes2))) {
                Collections.swap(asignaciones, p1, p2);
                felicidad  += (felicitatPaquetAOferta(paq1, o2) - felicitatPaquetAOferta(paq1, o1)) + (felicitatPaquetAOferta(paq2, o1) - felicitatPaquetAOferta(paq2, o2)); //Recalcular la felicidad
                precio += (precioPaqueteAOferta(paq1, o2) - precioPaqueteAOferta(paq1, o1)) + (precioPaqueteAOferta(paq2, o1) - precioPaqueteAOferta(paq2, o2)); //Recalcular el precio
                //Actualizamos el espacio disponible
                espacioDisponibleOfertas.set(oferta1, espacioDisponibleOfertas.get(oferta1)-incPes2);
                espacioDisponibleOfertas.set(oferta2, espacioDisponibleOfertas.get(oferta2)-incPes1);
            }
        }
    }

    //Mueve un paquete para una oferta o diferente a la que está
    //Condición de aplicabilidad: después del movimiento la oferta no excede su capacidad máxima y el paquete movido llega dentro del plazo
   public void moverPaquete(int p, int o) { //índice del paquete y de la oferta
        int oActual = asignaciones.get(p);

        if (o != oActual) { //Solo si es una oferta diferente a la que está
            double pes = paquetes.get(p).getPeso();
            if (cumpleCondicionAplicabilidad(paquetes.get(p), ofertas.get(o), o, pes)) { //Asignamos al paquete p la oferta o si cumple la condición de aplicabilidad
                asignaciones.set(p, o);
                //Actualizamos las características del estado
                felicidad += felicitatPaquetAOferta(paquetes.get(p), ofertas.get(o)) - felicitatPaquetAOferta(paquetes.get(p), ofertas.get(oActual));
                precio += precioPaqueteAOferta(paquetes.get(p), ofertas.get(o)) - precioPaqueteAOferta(paquetes.get(p), ofertas.get(oActual));
                espacioDisponibleOfertas.set(o, espacioDisponibleOfertas.get(o)-pes);
                espacioDisponibleOfertas.set(oActual, espacioDisponibleOfertas.get(oActual)+pes);

            }
        }
    }

    /* Heuristic function 1*/
    public double heuristic1(){
        //Propuesta de heurística teniendo en cuenta sólo los criterios de calidad sobre los costes
        double val = 0;
        val = precio*precio; //minimizar la función (minimizar costes)
        return val;
    }

    /* Heuristic function 2*/
    public double heuristic2(){
        //Propuesta de heurística teniendo en cuenta los dos criterios de calidad de solución
        double val = 0;
        val = -felicidad*felicidad + precio*precio; //minimizar la función, (maximizar la felicidad y minimizar costes)
        return val;
    }


    /* Goal test */
    public boolean is_goal(){
        return false;
    }



}






