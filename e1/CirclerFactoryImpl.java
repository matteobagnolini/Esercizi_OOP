package a06.e1;

public class CirclerFactoryImpl implements CirclerFactory {

    @Override
    public <T> Circler<T> leftToRight() {
        return new CirclerAbstract<T>() {

            @Override
            public T produceOne() {
                return this.elements.get((this.index++) % this.elements.size());
            }
            
        };
    }

    @Override
    public <T> Circler<T> alternate() {
        return new CirclerAbstract<T>() {

            @Override
            public T produceOne() {
                int currentInd = this.index % (2*this.elements.size());
                if(currentInd >= this.elements.size()) {
                    currentInd = 2*this.elements.size() - currentInd - 1;
                }
                this.index++;
                return this.elements.get(currentInd++);
            }
        };
    }

    @Override
    public <T> Circler<T> stayToLast() {
        return new CirclerAbstract<T>() {

            @Override
            public T produceOne() {
                if(this.index == this.elements.size()-1) {
                    return this.elements.get(index);
                }
                return this.elements.get(index++);
            }
            
        };
    }

    @Override
    public <T> Circler<T> leftToRightSkipOne() {
        return new CirclerWrapper<T>(leftToRight());
    }

    @Override
    public <T> Circler<T> alternateSkipOne() {
        return new CirclerWrapper<T>(alternate());
    }
    @Override
    public <T> Circler<T> stayToLastSkipOne() {
        return new CirclerWrapper<T>(stayToLast());
        
    }

}
