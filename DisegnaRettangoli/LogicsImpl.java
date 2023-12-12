package a01a.e2;

import java.util.*;

public class LogicsImpl implements Logics {

    private int turn = 1;
    private final Set<Coord> map = new HashSet<>();
    private Coord lastHit;
    private int size;

    public LogicsImpl(int size) {
        this.size = size;
    }
    
    @Override
    public Boolean hit(Coord coord) {
        if(turn == 1) {
            lastHit = coord;
            turn++;
            return false;
        } else {
            map.addAll(drawRectangle(lastHit, coord));
            turn = 1;
            return true;
        }
    }

    private Set<Coord> drawRectangle(Coord p1, Coord p2) {
        Set<Coord> out = new HashSet<>();
        int x1 = Math.min(p1.x(), p2.x());
        int x2 = Math.max(p1.x(), p2.x());
        int y1 = Math.min(p1.y(), p2.y());
        int y2 = Math.max(p1.y(), p2.y());
        for(int i = x1; i <= x2; i++) {
            for(int j = y1; j <= y2; j++) {
                out.add(new Coord(i, j));
            }
        }        
        return out;
    }

    @Override
    public Set<Coord> getMap() {
        return this.map;
    }

    @Override
    public Boolean isOver() {
        return map.size() == (size - 1)*(size - 1);
    }

}
