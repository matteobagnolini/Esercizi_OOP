package a03a.e2;

import java.util.Optional;
import java.util.Random;

public class LogicsImpl implements Logics {

    private int size;
    private Coord cTower;
    private Coord pTower;
    private boolean cWin = false;

    public LogicsImpl(final int size) {
        this.size = size;
    }

    @Override
    public boolean hit(Coord coord) {
        if(isValid(pTower, coord)) {
            pTower = coord;
            if(pTower.equals(cTower)) {
                System.out.println("player wins");
                return true;
            }
            enemyMove();
            return true;
        }
        return false;
    }

    private void enemyMove() {
        if(isValid(cTower, pTower)) {
            cTower = pTower;
            System.out.println("computer wins");;
        } else {
            var rand  = new Random();
            Coord newPos;
            do {
                newPos = new Coord(rand.nextInt(size-2) +1, rand.nextInt(size-2)+1);
            } while(!isValid(cTower, newPos) && cTower.equals(newPos));
            System.out.println(newPos);
            cTower = newPos;
        }
    }

    private boolean isValid(Coord tower, Coord coord) {
        return tower.x() == coord.x() || tower.y() == coord.y();
    }

    @Override
    public Pair<Coord, Coord> getTowers() {
        return new Pair<Coord, Coord>(pTower, cTower);
    }

    @Override
    public void init() {
        var rand = new Random();
        pTower = new Coord(rand.nextInt(size-1) +1, rand.nextInt(size-1) +1);
        System.out.println("p: " + pTower);
        do {
            cTower = new Coord(rand.nextInt(size-1)+1, rand.nextInt(size-1)+1);
        } while(pTower.equals(cTower));
        System.out.println("c  :" + cTower);
    }

    @Override
    public boolean isOver() {
        return cTower.equals(pTower);
    }

}
