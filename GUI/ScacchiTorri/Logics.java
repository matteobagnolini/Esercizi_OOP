package a03a.e2;

public interface Logics {

    boolean hit(Coord coord);

    Pair<Coord, Coord> getTowers();

    void init();

    boolean isOver();

}
