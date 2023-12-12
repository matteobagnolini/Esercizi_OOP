package a03a.e2;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LogicsImpl implements Logics {

    private int size;
    private final Set<Coord> selected = new HashSet<>();
    private Coord lastHit = new Coord(0, 0);
    private Boolean isOver = false;
    
    public LogicsImpl(final int size) {
        this.size = size;
    }
    
    @Override
    public void select(final Coord coord) {
        if (coord.x() > lastHit.x() && coord.y() > lastHit.y() && isInFirstQuadrant(coord)) {
            selected.addAll(getRectangle(coord));
            lastHit = coord;
        }
    }

    private Set <Coord> getRectangle(Coord coord) {
        Coord p1 = coord;
        Coord p2 = new Coord(size - coord.x(), coord.y());
        Coord p3 = new Coord(size - coord.x(), size+1 - coord.y());
        Coord p4 = new Coord(coord.x(), size - coord.y());
        Set<Coord> out = new HashSet<>();
        out.addAll(line(p1, p2));
        out.addAll(line(p2, p3));
        out.addAll(line(p3, p4));
        out.addAll(line(p4, p1));
        return out;
    }

    private Set<Coord> line(Coord p1, Coord p2) {
        Set<Coord> out = new HashSet<>();
        if(p1.x() == p2.x()) {
            for (var elem : IntStream.rangeClosed(Math.min(p1.y(), p2.y()), Math.max(p1.y(), p2.y())-1).boxed().collect(Collectors.toSet())) {
                out.add(new Coord(p1.x(), elem));
            }
         } else {
            System.out.println("dentro else");
            for (var elem : IntStream.rangeClosed(Math.min(p1.x(), p2.x()), Math.max(p1.x(), p2.x())-1).boxed().collect(Collectors.toSet())) {
                out.add(new Coord(elem, p2.y()));
            }
        }
        return out;
    }

    private boolean isInFirstQuadrant(Coord coord) {
        return coord.x() <= size/2 && coord.y() <= size/2;
    }

    @Override
    public Set<Coord> draw() {
        return selected;
    }

    @Override
    public Boolean isOver() {
        return selected.contains(new Coord(size/2, size/2));
    }

}
