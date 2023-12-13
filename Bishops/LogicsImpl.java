package a02a.e2;

import java.util.HashSet;
import java.util.Set;

public class LogicsImpl implements Logics {

    private final Set<Coord> map = new HashSet<>();
    private final Set<Coord> hit = new HashSet<>();
    private int size;
    
    public LogicsImpl(int size) {
        this.size = size;
    }

    @Override
    public void hit(Coord coord) {
        map.addAll(getDisabled(coord));
    }

    private Set<Coord> getDisabled(Coord coord) {
        Set<Coord> out = new HashSet<>();
        hit.add(coord);
        int i = coord.y()+1;
        int j = coord.x()+1;
        for(; i < size && j < size; i++, j++) { 
            var pos = new Coord(j, i);
            out.add(pos);
        }
        i = coord.y()-1;
        j = coord.x()+1;
        for(; i > 0 && j < size; i--, j++) { 
            var pos = new Coord(j, i);
            out.add(pos);
        }
        i = coord.y()-1;
        j = coord.x()-1;
        for(; i > 0 && j > 0; i--, j--) { 
            var pos = new Coord(j, i);
            out.add(pos);
        }
        i = coord.y()+1;
        j = coord.x()-1;
        for(; i < size && j > 0; i++, j--) { 
            var pos = new Coord(j, i);
            out.add(pos);
        }
        return out;
    }

    @Override
    public Set<Coord> getMap() {
        return this.map;
    }

    @Override
    public Boolean isOver() {
        System.out.println("Dimensione mappa = " + map.size() + hit.size());
        System.out.println("size*size = " + (size-1)*(size-1));
        return map.size() + hit.size() == (size-1)*(size-1);
    }

    @Override
    public void reset() {
        map.clear();
        hit.clear();
        System.out.println("size final :" + map.size() + hit.size());
    }

}
