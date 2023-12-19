package a01a.e2;

import java.util.HashSet;
import java.util.Set;

public class LogicsImpl implements Logics {

    private int size;
    private int turn = 0;
    private boolean over = false;
    private Set<Coord> p1 = new HashSet<>();
    private Set<Coord> p2 = new HashSet<>();
    
    public LogicsImpl(int size) {
        this.size = size;
    }

    @Override
    public void hit(Coord coord) {
        if(isValid(coord)) {
            if(turn % 2 == 0) {
                turn(p1, coord);
            } else {
                turn(p2, coord);
            }
            turn++;
        }
    }

    private void turn(Set<Coord> p, Coord coord) {
        p.add(coord);
        over = checkWin(p, coord);
    }

    private boolean checkWin(Set<Coord> p, Coord coord) {
        int cont = 0;
        //controllo orizzontale
        for(int x = coord.x()-2; x <= coord.x()+2; x++) {
            if(p.contains(new Coord(x, coord.y()))) {
                cont++;
                if(cont == 3) {
                    System.out.println("vinto");
                    return true;
                }
            } else {
                cont = 0;
            }
        }
        //controllo verticale
        cont = 0;
        for(int y = coord.y()-2; y <= coord.y()+2; y++) {
            if(p.contains(new Coord(coord.x(), y))) {
                cont++;
                if(cont == 3) {
                    System.out.println("vinto");
                    return true;
                }
            } else {
                cont = 0;
            }
        }
        return false;
    }

    private boolean isValid(Coord coord) {
        Coord under = new Coord(coord.x(), coord.y() + 1);
        //aggiungi controllo se coord è gia presente
        return (p1.contains(under) || p2.contains(under) || under.y() == size) && !p1.contains(coord) && !p2.contains(coord);
    }

    @Override
    public Pair<Set<Coord>, Set<Coord>> getMap() {
        return new Pair<>(p1, p2);
    }

    @Override
    public boolean isOver() {
        return over || (p1.size() + p2.size()) == (size-1)*(size-1);
    }

}
