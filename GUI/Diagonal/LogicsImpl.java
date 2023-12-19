package a01a.e2;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class LogicsImpl implements Logics {

    private final Set<Coord> hits = new HashSet<>();
    private final List<Coord> lastHits = new ArrayList<>();
    private boolean isOver = false;

    @Override
    public void hit(Coord coord) {
        if(!hits.contains(coord)) {
            if(lastHits.size() >= 3) {
                lastHits.remove(0);
            }
            lastHits.add(coord);
            hits.add(coord);
            System.out.println(lastHits.size() + " hit valide");
            if(lastHits.size() == 3) {
                System.out.println("Check diagonals");
                checkDiagonals(coord);
            }   
        } else {
            hits.remove(coord);
            lastHits.clear();
        }
    }

    private void checkDiagonals(Coord coord) {
        if(lastHits.size() == 3) {
            Coord d1 = delta(lastHits.get(0), lastHits.get(1));
            Coord d2 = delta(lastHits.get(1), lastHits.get(2));
            isOver = d1.x() == d2.x() && d1.y() == d2.y();
        }
        
    }

    private Coord delta(Coord coord, Coord coord2) {
        return new Coord(coord.x() - coord2.x(), coord.y() - coord2.y());
    }

    @Override
    public boolean isOver() {
        return this.isOver;
    }

}
