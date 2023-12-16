package a01a.e1;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class AcceptorImpl<E, R> implements Acceptor<E, R> {

    List<E> in = new LinkedList<>();
    Predicate<E> elemCondition;
    Predicate<List<E>> seqCondition;
    Function<List<E>, R> out;
    
    public AcceptorImpl(Predicate<E> elemCondition, Predicate<List<E>> seqCondition, Function<List<E>, R> out) {
        this.elemCondition = elemCondition;
        this.seqCondition = seqCondition;
        this.out = out;
    }
    
    @Override
    public boolean accept(E e) {
        if(elemCondition.test(e)) {
            in.add(e);
            return true;
        }
        return false;
    }

    @Override
    public Optional<R> end() {
        if(seqCondition.test(this.in)) {
            return Optional.of(out.apply(in));
        }
        return Optional.empty();
    }





}
