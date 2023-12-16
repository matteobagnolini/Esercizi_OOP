package a02a.e2;

import java.util.*;

public class LogicsImpl implements Logics {

    private Set<Coord> occupied = new HashSet<>();
    private int turn = 0;
    private int size;
    private Dir currDir = Dir.UP;
    private Coord lastHit;
    private int cont = 0;
    
    enum Dir {
        UP(new Coord(0 ,-1)), DOWN(new Coord(0, 1)), LEFT(new Coord(-1, 0)), RIGHT(new Coord(1, 0));

        private Coord coord;

        private Dir(Coord coord) {
            this.coord = coord;
        }

        public int x() {
            return this.coord.x();
        }

        public int y() {
            return this.coord.y();
        }

    }

    public LogicsImpl(final int size) {
        this.size = size;
    }

    @Override
    public Optional<Coord> hit() {
        if(turn == 0) {
            var rand = new Random();
            turn++;
            lastHit = new Coord(rand.nextInt(size-1)+1, rand.nextInt(size-1)+1);
            occupied.add(lastHit);
            return Optional.of(lastHit);
        }
        while(!isValid()) {
            changeDir();
            cont++;
            if(cont > 4) {
                return Optional.empty();
            }
            System.out.println(cont);
        }
        cont = 0;
        occupied.add(lastHit);
        return Optional.of(lastHit);
    }

    private boolean isValid() {
        Coord possibleHit = new Coord(lastHit.x() + currDir.x(), lastHit.y() + currDir.y());
        if(!occupied.contains(possibleHit)  && (possibleHit.x() < this.size && possibleHit.y() < this.size) && possibleHit.x() > 0 && possibleHit.y() > 0) {
            System.out.println(possibleHit);
            System.out.println(size);
            lastHit = possibleHit;
            return true;
        }
        return false;
    }

    private void changeDir() {
        System.out.println("Sto cambiando direzione");
        switch(currDir) {
            case UP: currDir = Dir.RIGHT; break;
            case  RIGHT: currDir = Dir.DOWN; break;
            case DOWN: currDir = Dir.LEFT; break;
            case LEFT: currDir = Dir.UP; break;
        }
    }

    

}
