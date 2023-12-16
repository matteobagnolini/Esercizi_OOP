package a01a.e1;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

public class AcceptorFactoryAdvancedImpl implements AcceptorFactory {

    @Override
    public Acceptor<String, Integer> countEmptyStringsOnAnySequence() {
        return new Acceptor<String,Integer>() {

            private int cont = 0;
            
            @Override
            public boolean accept(String e) {
                if(e.length() == 0) {
                    cont++;
                }
                return true;
            }

            @Override
            public Optional<Integer> end() {
                return Optional.of(cont);
            }
            
        };
    }

    @Override
    public Acceptor<Integer, String> showAsStringOnlyOnIncreasingSequences() {
        return new Acceptor<Integer,String>() {

            private String out = "";
            private Optional<Integer> last = Optional.empty();
            private Boolean valid = true;

            @Override
            public boolean accept(Integer e) {
                if(valid && (last.isEmpty() || e > last.get())) {
                    last = Optional.of(e);
                    out = out + (out.length() > 0 ? ":" : "") + e.toString();
                    return true;
                }
                valid = false;
                return false;
            }

            @Override
            public Optional<String> end() {
                if(valid) {
                    return Optional.of(out);
                }
                return Optional.empty();
            }
            
        };
    }

    @Override
    public Acceptor<Integer, Integer> sumElementsOnlyInTriples() {
        return new Acceptor<Integer,Integer>() {

            List<Integer> list = new LinkedList<>();
            private Boolean valid = true;
            
            @Override
            public boolean accept(Integer e) {
                list.add(e);
                if(list.size() <= 3 && valid) {
                    return true;
                }
                valid = false;
                return false;
            }

            @Override
            public Optional<Integer> end() {
                if(valid && list.size() == 3) {
                    int sum = 0;
                    for(var e : list) {
                        sum += e;
                    }
                    return Optional.of(sum);
                }
                return Optional.empty();
            }
            
        };
        
    }

    @Override
    public <E, O1, O2> Acceptor<E, Pair<O1, O2>> acceptBoth(Acceptor<E, O1> a1, Acceptor<E, O2> a2) {
        return new Acceptor<E,Pair<O1,O2>>() {

            @Override
            public boolean accept(E e) {
                return a1.accept(e) && a2.accept(e);
            }

            @Override
            public Optional<Pair<O1, O2>> end() {
                var r1 = a1.end();
                var r2 = a2.end();
                if(r1.isEmpty() || r2.isEmpty()) {
                    return Optional.empty();
                }
                return Optional.of(new Pair<>(r1.get(), r2.get()));
            }
            
        };
    }

    @Override
    public <E, O, S> Acceptor<E, O> generalised(S initial, BiFunction<E, S, Optional<S>> stateFun,
            Function<S, Optional<O>> outputFun) {
        return new Acceptor<E,O>() {

            Optional<S> elem = Optional.of(initial);
            
            @Override
            public boolean accept(E e) {
                if(!elem.isPresent()) {
                    return false;
                }
                elem = stateFun.apply(e, elem.get());
                return elem.isPresent();
            }

            @Override
            public Optional<O> end() {
                if(elem.isEmpty()) {
                    return Optional.empty();
                }
                return outputFun.apply(elem.get());
            }
            
        };
    }

}
