package a03b.e1;

import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class LazyTreeFactoryImpl implements LazyTreeFactory {

    @Override
    public <X> LazyTree<X> constantInfinite(X value) {
        return fromTwoIterations(value, s -> value, s -> value);
    }

    @Override
    public <X> LazyTree<X> fromMap(X root, Map<X, Pair<X, X>> map) {
        return fromTwoIterations(root, s -> map.get(s) != null ? map.get(s).getX() : null, s -> map.get(s) != null ? map.get(s).getY() : null);
    }

    @Override
    public <X> LazyTree<X> cons(Optional<X> root, Supplier<LazyTree<X>> leftSupp, Supplier<LazyTree<X>> rightSupp) {
        return new LazyTree<X>() {

            @Override
            public boolean hasRoot() {
                return root.isPresent();
            }

            @Override
            public X root() {
                if(hasRoot()) {
                    return root.get();
                }
                return null;
            }

            @Override
            public LazyTree<X> left() {
                return leftSupp.get();
                
            }

            @Override
            public LazyTree<X> right() {
                return rightSupp.get();
            }
            
        };
    }

    @Override
    public <X> LazyTree<X> fromTwoIterations(X root, UnaryOperator<X> leftOp, UnaryOperator<X> rightOp) {
        return cons(Optional.ofNullable(root), () -> fromTwoIterations(leftOp.apply(root), leftOp, rightOp), () -> fromTwoIterations(rightOp.apply(root), leftOp, rightOp));
    }

    @Override
    public <X> LazyTree<X> fromTreeWithBound(LazyTree<X> tree, int bound) {
        if(bound == 0 || !tree.hasRoot()) {
            return cons(Optional.empty(), null, null);
        }
        return cons(Optional.ofNullable(tree.root()), () -> fromTreeWithBound(tree.left(), bound-1), () -> fromTreeWithBound(tree.right(), bound-1));
        /*return new LazyTree<X>() {

            @Override
            public boolean hasRoot() {
                if(bound > 0) {
                    return tree.hasRoot();
                }
                return false;
            }

            @Override
            public X root() {
                if(hasRoot()) {
                    return tree.root();
                }
                return null;
            }

            @Override
            public LazyTree<X> left() {
                return fromTreeWithBound(tree.left(), bound-1);
            }

            @Override
            public LazyTree<X> right() {
                return fromTreeWithBound(tree.right(), bound-1);
            }
            
        };*/
    }

}
