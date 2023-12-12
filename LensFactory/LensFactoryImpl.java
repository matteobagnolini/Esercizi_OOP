package a03b.e1;

import java.util.*;

public class LensFactoryImpl implements LensFactory {

    @Override
    public <E> Lens<List<E>, E> indexer(int i) {
        return new Lens<List<E>,E>() {

            @Override
            public E get(List<E> s) {
                return s.get(i);
            }

            @Override
            public List<E> set(E a, List<E> s) {
                var out = new LinkedList<E>(s);
                int i = s.indexOf(get(s));
                out.set(i, a);
                return out;
            }
            
        };
    }

    @Override
    public <E> Lens<List<List<E>>, E> doubleIndexer(int i, int j) {
        Lens<List<List<E>>, List<E>> li = this.indexer(i);
        Lens<List<E>, E> lj = this.indexer(j);
        return new Lens<List<List<E>>,E>() {

            @Override
            public E get(List<List<E>> s) {
                return lj.get(li.get(s));
            }

            @Override
            public List<List<E>> set(E a, List<List<E>> s) {
                return li.set(lj.set(a, li.get(s)), s);
                
            }
            
        };
    }

    @Override
    public <A, B> Lens<Pair<A, B>, A> left() {
        return new Lens<Pair<A,B>,A>() {

            @Override
            public A get(Pair<A, B> s) {
                return s.get1();
            }

            @Override
            public Pair<A, B> set(A a, Pair<A, B> s) {
                var np = new Pair<A, B>(a, s.get2());
                return np;
            }
            
        };
    }

    @Override
    public <A, B> Lens<Pair<A, B>, B> right() {
        return new Lens<Pair<A,B>,B>() {

            @Override
            public B get(Pair<A, B> s) {
                return s.get2();
            }

            @Override
            public Pair<A, B> set(B a, Pair<A, B> s) {
                return new Pair<A, B>(s.get1(), a);
            }
            
        };
    }

    @Override
    public <A, B, C> Lens<List<Pair<A, Pair<B, C>>>, C> rightRightAtPos(int i) {
        
        Lens<List<Pair<A, Pair<B, C>>>, Pair<A, Pair<B, C>>> ll = this.indexer(2);
        Lens<Pair<A, Pair<B, C>>, Pair<B, C>> ls = this.right();
        Lens<Pair<B, C>, C> lss = this.right();
        return new Lens<List<Pair<A,Pair<B,C>>>,C>() {

            @Override
            public C get(List<Pair<A, Pair<B, C>>> s) {
                return lss.get(ls.get(ll.get(s)));
            }

            @Override
            public List<Pair<A, Pair<B, C>>> set(C a, List<Pair<A, Pair<B, C>>> s) {
                return ll.set(ls.set(lss.set(a, ls.get(ll.get(s))), ll.get(s)), s);
            }
            
        };
    }

}
