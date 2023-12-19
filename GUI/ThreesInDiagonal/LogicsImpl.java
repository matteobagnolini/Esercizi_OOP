package a02b.e2;

import java.util.*;

public class LogicsImpl implements Logics {
    
    private int size;
    private Set<Coord> hits = new HashSet<>();


    public LogicsImpl(int size) {
        this.size = size;
    }

    @Override
    public void hit(Coord coord) {
        if(hits.contains(coord)) {
            hits.remove(coord);
        } else {
            hits.add(coord);
        }
    }

    @Override
    public Optional<Coord> check() {
        int countHits;
        int x;
        int y;

        for(int i = 1; i < size; i++) {
            countHits = 0;
            y = 1;
            for(x = i; x < size; x++, y++) {
                var pos = new Coord(x, y);
                if(hits.contains(pos)) {
                    countHits++;
                }
            }
            if(countHits == 3) {
                return Optional.of(new Coord(x-1, y-1));
            }
        }

        for(int i = 1; i < size; i++) {
            countHits = 0;
            x = 1;
            for(y = i; y < size; x++, y++) {
                var pos = new Coord(x, y);
                if(hits.contains(pos)) {
                    countHits++;
                }
            }
            if(countHits == 3) {
                return Optional.of(new Coord(x-1, y-1));
            }
        }

        return Optional.empty();
    }

    @Override
    public void reset() {
        hits.clear();
    }

}
