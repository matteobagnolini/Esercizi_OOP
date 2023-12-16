package a01b.e2;

import java.util.Optional;
import java.util.Set;

/**
 * Interfaccia per distaccare logica dalla GUI.
 * Ha un solo metodo hit
 */
public interface Logics {
    
    //va bene usare anche questa
    //boolean hit(Coord coord);

    Optional<Integer> hit(Coord coord);
    
    //ritorna un set con le coordinate dei pulsanti dove andremo a mettere gli asterischi
    Set<Coord> getEnabled();

    boolean isOver();
}
