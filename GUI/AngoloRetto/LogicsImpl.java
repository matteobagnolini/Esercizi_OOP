package a01b.e2;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/*Prima di tutto faccio una dummy implementation, per testare se effettivamente funzionano
* i metodi nella GUI. 
* Poi passo a implementare la logica correttamente
*/

public class LogicsImpl implements Logics {

    private int turn = 0;

    @Override
    public Optional<Integer> hit(Coord coord) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hit'");
    }

    @Override
    public Set<Coord> getEnabled() {
        return new HashSet<Coord>();
    }

    @Override
    public boolean isOver() {
        return false;
    }

}
