package a01b.e1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class FlattenerFactoryImpl implements FlattenerFactory {

    @Override
    public Flattener<Integer, Integer> sumEach() {
        return new Flattener<Integer,Integer>() {

            @Override
            public List<Integer> flatten(List<List<Integer>> list) {
                List<Integer> out = new ArrayList<>();
                for(var slist : list) {
                    int sum = 0;
                    for(var elem : slist) {
                        sum += elem;
                    }
                    out.add(sum);
                }
                return out;
            }
            
        };
    }

    @Override
    public <X> Flattener<X, X> flattenAll() {
        return new Flattener<X,X>() {

            @Override
            public List<X> flatten(List<List<X>> list) {
                List<X> out = new ArrayList<>();
                for(var slist : list) {
                    for(var elem : slist) {
                        out.add(elem);
                    }
                }
                return out;
            }
            
        };
    }

    @Override
    public Flattener<String, String> concatPairs() {
        return new Flattener<String,String>() {

            @Override
            public List<String> flatten(List<List<String>> list) {
                List<String> out = new ArrayList<>();
                int cont = 2;
                String s1 = "";
                String s2 = "";
                var l1 = list.get(0);
                var l2 = list.get(1);
                while(l1 != null && l2 != null) {
                    for(var elem : l1) {
                        s1 = s1.concat(elem);
                    }
                    for(var elem : l2) {
                        s2 = s2.concat(elem);
                    }
                    out.add(s1.concat(s2));
                    s1 = "";
                    s2 = "";
                    if(cont + 2 <= list.size()) {
                        l1 = list.get(cont++);
                        l2 = list.get(cont++);
                    } else {
                        if(cont + 1 <= list.size()) {
                            l1 = list.get(cont++);
                            l2 = null;
                        } else {
                            l1 = null;
                            l2 = null;
                        }
                    }
                    
                }
                if(l1 != null) {
                    for(var elem : l1) {
                        s1 = s1.concat(elem);
                    }
                    out.add(s1);
                }
                return out;
            }
            
        };
    }

    @Override
    public <I, O> Flattener<I, O> each(Function<List<I>, O> mapper) {
        return new Flattener<I,O>() {

            @Override
            public List<O> flatten(List<List<I>> list) {
                List<O> out = new ArrayList<>();
                for(var slist : list) {
                    out.add(mapper.apply(slist));
                }
                return out;
            }
            
        };
    }

    @Override
    public Flattener<Integer, Integer> sumVectors() {
        return new Flattener<Integer,Integer>() {

            @Override
            public List<Integer> flatten(List<List<Integer>> list) {
                List<Integer> out = new ArrayList<>();
                int dim = list.get(0).size();
                for(int i = 0; i < dim; i++) {
                    int sum = 0;
                    for(var slist : list) {
                        sum += slist.get(i);
                    }
                    out.add(sum);
                }
                return out;
            }
            
        };
    }

}
