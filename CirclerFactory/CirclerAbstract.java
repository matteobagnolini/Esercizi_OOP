package a06.e1;

import java.util.ArrayList;
import java.util.List;

public abstract class CirclerAbstract<T> implements Circler<T> {

    protected List<T> elements;
    protected int index = 0;

    @Override
    public void setSource(List<T> elements) {
        this.elements = elements;
        this.index = 0;
    }

    @Override
    abstract public T produceOne();

    @Override
    public List<T> produceMany(int n) {
        List<T> out = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            out.add(produceOne());
        }
        return out;
    }

}
