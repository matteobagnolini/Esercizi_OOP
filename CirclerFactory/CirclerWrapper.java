package a06.e1;

import java.util.List;

class CirclerWrapper<T> extends CirclerAbstract<T> {

    private Circler<T> wrapper;

    public CirclerWrapper(Circler<T> wrapper) {
        this.wrapper = wrapper;
    }


    @Override
    public void setSource(List<T> elements) {
        super.setSource(elements);
        this.wrapper.setSource(elements);
    }
    
    @Override
    public T produceOne() {
        var out = wrapper.produceOne();
        wrapper.produceOne();
        return out;
    }

}
