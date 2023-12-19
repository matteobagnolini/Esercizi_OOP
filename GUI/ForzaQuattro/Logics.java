package a01a.e2;

import java.util.Set;

public interface Logics {

    void hit(Coord coord);

    Pair<Set<Coord>, Set<Coord>> getMap();

    boolean isOver();

}
