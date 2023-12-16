package a02a.e1;

import java.util.ArrayList;
import java.util.List;

public class RecursiveIteratorHelpersImpl implements RecursiveIteratorHelpers {

    @Override
    public <X> RecursiveIterator<X> fromList(List<X> list) {
        if(list.size()>0) {
        return new RecursiveIterator<X>() {
            
            @Override
            public X getElement() {
                if(list.size()>0) {
                    return list.get(0);
                }
                return null;
            }

            @Override
            public RecursiveIterator<X> next() {
                if(list.size()>0) {
                    List<X> out = new ArrayList<>(list);
                    out.remove(0);
                    return fromList(out);
                }
                return null;
            }
            
        };
        }
        return null;
    }

    @Override
    public <X> List<X> toList(RecursiveIterator<X> input, int max) {
        var it = input;
        List<X> out = new ArrayList<>();
        for(int i = 0; i < max && it != null; i++) {
            out.add(it.getElement());
            it = it.next();
        }
        return out;
    }

    @Override
    public <X, Y> RecursiveIterator<Pair<X, Y>> zip(RecursiveIterator<X> first, RecursiveIterator<Y> second) {
        return new RecursiveIterator<Pair<X,Y>>() {

            @Override
            public Pair<X, Y> getElement() {
                    return new Pair<X,Y>(first.getElement(), second.getElement());
            }
                

            @Override
            public RecursiveIterator<Pair<X, Y>> next() {
                if(first.next() != null && second.next() != null) {
                    return zip(first.next(), second.next());
                }
                return null;
            }
            
        };
    }

    @Override
    public <X> RecursiveIterator<Pair<X, Integer>> zipWithIndex(RecursiveIterator<X> iterator) {
        var it = iterator;
        int cont = 0;
        while(it != null) {
            it = it.next();
            cont++;
        }
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i <= cont; i++) {
            list.add(i);
        }
        return zip(iterator, fromList(list));
    }

    @Override
    public <X> RecursiveIterator<X> alternate(RecursiveIterator<X> first, RecursiveIterator<X> second) {
        var it1 = first;
        var it2 = second;
        int turn = 0;
        List<X> list = new ArrayList<>();
        while(it1 != null && it2 != null) {
            if(turn++ % 2 == 0) {
                list.add(it1.getElement());
                it1 = it1.next();
            } else {
                list.add(it2.getElement());
                it2 = it2.next();
            }
        }
        while(it1 != null) {
            list.add(it1.getElement());
            it1 = it1.next();
        }
        while(it2 != null) {
            list.add(it2.getElement());
            it2 = it2.next();
        }
        return fromList(list);
    }

}
