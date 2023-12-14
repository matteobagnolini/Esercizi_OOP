package a01b.e2;

import java.util.HashSet;
import java.util.Set;

public class LogicsImpl implements Logics {

    private final int size;
    private final Set<Coord> map = new HashSet<>();
    private int lastChanged = 0;

    public LogicsImpl(int size) {
        this.size = size;
    }

    @Override
    public void hit(Coord coord) {
        changeState(diagonal(coord));
    }

    private void changeState(Set<Coord> diagonals) {
        lastChanged = 0;
        for(var pos : diagonals) {
            if(!map.contains(pos)) {
                map.add(pos);
                System.out.println("aggiungo");
            } else {
                map.remove(pos);
                System.out.println("rimuovo");
                lastChanged++;
            }
        }
    }

    private Set<Coord> diagonal(Coord coord) {
        Set<Coord> out = new HashSet<>();
        int x = coord.x();
        int y = coord.y();
        if(x+1 < size && y+1 < size) {
            out.add(new Coord(x+1, y+1));
        }
        System.out.println(".");
        if(x+1 < size && y-1 > 0) {
            out.add(new Coord(x+1, y-1));
        }
        if(x-1 > 0 && y-1 > 0) {
            out.add(new Coord(x-1, y-1));
        }
        if(x-1 > 0 && y+1 < size) {
            out.add(new Coord(x-1, y+1));
        }
        return out;
    }

    @Override
    public Set<Coord> getMap() {
        return this.map;
    }

    @Override
    public boolean isOver() {
        return lastChanged == 3;
    }

}
