package a03b.e2;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class LogicsImpl implements Logics {

    private final int size;
    private int eatPossible = 0;
    private final Set<Coord> pawns = new HashSet<>();
    private final Set<Coord> enemies = new HashSet<>();
    
    public LogicsImpl(int size) {
        this.size = size;
        //add player pawns
        for (int j = 1; j < size; j++) {
            pawns.add(new Coord(j, size-1));
            System.out.println(pawns.size());
        }
        //aggiungo emeies pawns
        var rand = new Random();
        int cont = 0;
        while(cont != 4) {
            for(int i = 1; i < size; i++) {
                var pos = new Coord(i, rand.nextInt(2) +1);
                enemies.add(pos);
                cont++;
            }
        }
    }

    @Override
    public void hit(Coord coord) {
        if(canEat(coord)) {
            eat(coord);
        } else {
            moveForward(coord);
        }
    }

    private void eat(Coord coord) {
        var right = new Coord(coord.x()+1, coord.y()-1);
        var left = new Coord(coord.x()-1, coord.y()-1);
        if(enemies.contains(right)) {
            update(coord, right);
        } else if(enemies.contains(left)) {
            update(coord, left);
        }
    }

    private void moveForward(Coord coord) {
        var pos = new Coord(coord.x(), coord.y()-1 > 0 ? coord.y()-1 : coord.y());
        if(!enemies.contains(pos)) {
            pawns.remove(coord);
            pawns.add(pos);
        }
    }

    private boolean canEat(Coord coord) {
        var right = new Coord(coord.x()+1, coord.y()-1);
        var left = new Coord(coord.x()-1, coord.y()-1);
        if(enemies.contains(right)) {
            eatPossible = 1;
        } else {
            eatPossible = 0;
        }
        if(enemies.contains(left)) {
            eatPossible++;
        }
        return eatPossible > 0;
    }

    @Override
    public Set<Coord> getPlayerPawns() {
        return pawns;
    }

    @Override
    public Set<Coord> getEnemyPawns() {
        return enemies;
    }

    @Override
    public Boolean isOver() {
        return enemies.size() == 0;
    }

    void update(Coord old, Coord newer) {
        enemies.remove(newer);
        pawns.add(newer);
        pawns.remove(old);
    }

}
