package a02a.e2;

import java.util.Set;

public interface Logics {

    void hit(Coord coord);

    Set<Coord> getMap();

    Boolean isOver();

    void reset();

}
