package a02b.e1;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.*;
import java.util.*;

public class CursorHelpersImpl implements CursorHelpers {

    private <X> Cursor<X> generic(Iterator<X> it) {
        return new Cursor<X>() {

            private X value = it.next();
            
            @Override
            public X getElement() {
                return value;
            }

            @Override
            public boolean advance() {
                if(it.hasNext()) {
                    value = it.next();
                    return true;
                }
                return false;
            }

        };
    }
    
    @Override
    public <X> Cursor<X> fromNonEmptyList(List<X> list) {
        return generic(list.iterator());
    }

    @Override
    public Cursor<Integer> naturals() {
        return generic(IntStream.iterate(0, e -> e+1).iterator());
    }

    @Override
    public <X> Cursor<X> take(Cursor<X> input, int max) {
        return fromNonEmptyList(toList(input, max));
    }

    @Override
    public <X> void forEach(Cursor<X> input, Consumer<X> consumer) {
        do {
            consumer.accept(input.getElement());
        } while(input.advance());
    }

    @Override
    public <X> List<X> toList(Cursor<X> input, int max) {
        List<X> out = new ArrayList<>();
        int cont = 0;
        do {
            out.add(input.getElement());
            cont++;
        } while(input.advance() && cont < max);
        return out;
    }

}
