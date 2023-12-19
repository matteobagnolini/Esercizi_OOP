package a01a.e1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TreeFactoryImpl implements TreeFactory {

    @Override
    public <E> Tree<E> singleValue(E root) {
        return new Tree<E>() {

            @Override
            public E getRoot() {
                return root;
            }

            @Override
            public List<Tree<E>> getChildren() {
                return List.of();
            }

            @Override
            public Set<E> getLeafs() {
                return Set.of(root);
            }

            @Override
            public Set<E> getAll() {
                return Set.of(root);
            }
            
        };
    }

    @Override
    public <E> Tree<E> twoChildren(E root, Tree<E> child1, Tree<E> child2) {
        return new Tree<E>() {

            @Override
            public E getRoot() {
                return root;
            }

            @Override
            public List<Tree<E>> getChildren() {
                return List.of(child1, child2);
            }

            @Override
            public Set<E> getLeafs() {
                Set<E> out = new HashSet<>();
                out.addAll(child1.getLeafs());
                out.addAll(child2.getLeafs());
                return out;
            }

            @Override
            public Set<E> getAll() {
                Set<E> out = new HashSet<>();
                out.add(root);
                out.addAll(child1.getAll());
                out.addAll(child2.getAll());
                return out;
            }
            
        };
    }

    @Override
    public <E> Tree<E> oneLevel(E root, List<E> children) {
        return new Tree<E>() {

            @Override
            public E getRoot() {
                return root;
            }

            @Override
            public List<Tree<E>> getChildren() {
                List<Tree<E>> out = new ArrayList<>();
                children.forEach(s -> out.add(singleValue(s)));
                return out;
            }

            @Override
            public Set<E> getLeafs() {
                return Set.copyOf(children);
            }

            @Override
            public Set<E> getAll() {
                Set<E> out = new HashSet<>();
                out.addAll(children);
                out.add(root);
                return out;
            }
            
        };
    }

    @Override
    public <E> Tree<E> chain(E root, List<E> list) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'chain'");
    }

}
