package a03b.e2;

import java.util.Set;

public interface Logics {

    void hit(Coord coord);

    Set<Coord> getPlayerPawns();

    Set<Coord> getEnemyPawns();

    Boolean isOver();

}
