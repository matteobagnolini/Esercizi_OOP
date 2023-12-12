package a02b.e2;

import java.util.*;

public class LogicsImpl implements Logics {

    Map<Coord, String> map = new HashMap<>();
    private Coord pos;
    private int size;
    private Dir currDir = Dir.UP;
    private Boolean isOver = false;

    public enum Dir {
        UP(0, -1), DOWN(0, 1), LEFT(-1, 0), RIGHT(1, 0);

        private int x;
        private int y;

        private Dir(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int x() {
            return x;
        }

        public int y() {
            return y;
        }

    }

    public LogicsImpl(int size) {
        this.size = size;
    }
    
    @Override
    public Map<Coord, String> set() {
        var rand = new Random();
        pos = new Coord(rand.nextInt(size-2)+1, size-1);
        map.put(pos, "*");
        System.out.println(pos);
        for(int i = 0; i < 20; i++) {
            var coord = new Coord(rand.nextInt(size-2)+1, rand.nextInt(size-2)+1);
            if(map.containsKey(coord)) {
                i--;
            } else {
                String letter = i % 2 == 0 ? "L" : "R";
                map.put(coord, letter);
            }
        }
        return Map.copyOf(map);
    }

    @Override
    public Map<Coord, String> next() {
        var oldPos = pos;
        pos = new Coord(pos.x() + currDir.x(), pos.y() + currDir.y());
        if(map.containsKey(pos)) {
            if(map.get(pos).equals("L")) {
                this.currDir = Dir.LEFT;
            } else if(map.get(pos).equals("R")){
                this.currDir = Dir.RIGHT;
            }
            map.put(pos, "*");           
        } else if(pos.x() < 1 || pos.x() >= size || pos.y() < 1 || pos.y() >= size) {
            this.isOver = true;
        }else {
            map.put(pos, "*");
        }
        map.put(oldPos, "");
        System.out.println("Ho rimosso vecchiapos");
        return Map.copyOf(map);
    }

    @Override
    public Boolean isOver() {
        return this.isOver;
    }

}
