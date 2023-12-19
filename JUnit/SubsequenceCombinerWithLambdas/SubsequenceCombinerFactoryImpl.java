package a01a.e1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class SubsequenceCombinerFactoryImpl implements SubsequenceCombinerFactory {

    private <X, Y> SubsequenceCombiner<X, Y> generic(Predicate<List<X>> pre, Function<List<X>, Y> fun) {
        return new SubsequenceCombiner<X,Y>() {

            @Override
            public List<Y> combine(List<X> list) {
                List<Y> out = new ArrayList<>();
                List<X> tmp = new ArrayList<>();
                for(var x : list) {
                    tmp.add(x);
                    if(pre.test(tmp)) {
                        out.add(fun.apply(tmp));
                        tmp.clear();
                    }
                }
                if(!tmp.isEmpty()) {
                    out.add(fun.apply(tmp));
                }
                return out;
            }
            
        };
    }
    
    @Override
    public SubsequenceCombiner<Integer, Integer> tripletsToSum() {
        return generic(l -> l.size() == 3, l -> l.stream().mapToInt(e -> e).sum());
    }

    @Override
    public <X> SubsequenceCombiner<X, List<X>> tripletsToList() {
        return generic(l -> l.size() == 3, l -> l.stream().collect(Collectors.toList()));
    }

    @Override
    public SubsequenceCombiner<Integer, Integer> countUntilZero() {
        return generic(l -> l.get(l.size()-1) == 0, l -> (int)l.stream().filter(e -> e != 0).count());
    }

    @Override
    public <X, Y> SubsequenceCombiner<X, Y> singleReplacer(Function<X, Y> function) {
        return generic(l -> true, l -> function.apply(l.get(0)));
    }

    @Override
    public SubsequenceCombiner<Integer, List<Integer>> cumulateToList(int threshold) {
        return generic(l -> l.stream().mapToInt(e -> e).sum() >= threshold, ArrayList::new);
    }

}
