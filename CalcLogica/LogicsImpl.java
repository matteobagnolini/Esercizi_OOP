package a05.e2;

import java.util.*;

public class LogicsImpl implements Logics {

    enum Op {
        AND, OR, XOR, TRUE, FALSE
    }

    private final Map<Coord, Op> map = new HashMap<>();
    private int turn = 0;
    private final List<Op> operations = new ArrayList<>();
    private Boolean result = true;
    private Coord lastHit;

    @Override
    public String setUp(Coord coord) {
        var rand  = new Random();         
        if(turn++ % 2 == 0) {
            if(rand.nextInt(2) == 1) {
                map.put(coord, Op.TRUE);
                return "True";
            } else {
                map.put(coord, Op.FALSE);
                return "False";
            }
         } else switch(rand.nextInt(3)) {
            case 0: map.put(coord, Op.AND); return "AND";
            case 1: map.put(coord, Op.OR); return "OR";
            case 2: map.put(coord, Op.XOR); return "XOR";
        }
        return null;
    }
    
    @Override
    public Boolean hit(Coord coord) {
        Boolean canHit;
        if(canHit = canHit(coord)) {
            operations.add(map.get(coord));
            lastHit = coord;
            System.out.println("Selected "+coord);
        }
        if(operations.size() == 3) {
            compute();
            operations.clear();
            operations.add(getOp(result));
        }
        return canHit;
    }

    private boolean canHit(Coord coord) {
        if(lastHit == null) {
            return true;
        }
        int x = coord.x();
        int y = coord.y();
        int x1 = lastHit.x();
        int y1 = lastHit.y();
        if((x == x1 && y == y1-1) || (x == x1 && y == y1+1) || 
            (x == x1+1 && y == y1) || (x == x1-1 && y == y1)) {
                return true;
            }
            System.out.println("Cant select");
        return false;
    }

    @Override
    public Boolean isOver() {
        System.out.println("REsult = "+result);
        return !result;
    }

    private void compute() {
        System.err.println("computing ..");
        var op1 = getBoolean(operations.get(0));
        System.out.println("op1 : "+op1);
        var op2 = getBoolean(operations.get(2));
        System.out.println("op2 : "+op2);
        switch(operations.get(1)) {
            case AND -> result = op1 && op2;
            case OR -> result = op1 || op2;
            case XOR -> result = op1 ^ op2;
        }
    }

    private Op getOp(Boolean bool) {
        if(bool) {
            return Op.TRUE;
        }
        return Op.FALSE;
    }

    private Boolean getBoolean(Op op) {
        if(op == Op.FALSE){
        return false;
        }
        return true;
    }

}
