import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CircularListImpl implements CircularList {

    private final List<Integer> integerList;
    private int position = -1;

    public CircularListImpl() {
        this.integerList = new ArrayList<>();
    }

    @Override
    public void add(int element) {
        this.integerList.add(element);
        this.position = this.size() - 1;
    }

    @Override
    public int size() { return this.integerList.size(); }

    @Override
    public boolean isEmpty() {
        return this.integerList.isEmpty();
    }

    @Override
    public Optional<Integer> next() { return move(1); }

    @Override
    public Optional<Integer> previous() {
        return move(-1);
    }

    private final Optional<Integer> move(int offset){
        if(this.isEmpty()){
            return Optional.empty();
        }
        this.position = (this.position + this.size() + offset) % this.size();
        return Optional.of(integerList.get(this.position));
    }

    @Override
    public void reset() { this.position = 0; }

    @Override
    public Optional<Integer> next(SelectStrategy strategy) {
        Optional<Integer> current;
        for(int i = 0;i<this.size();i++){
            current = next();
            if(current.isPresent() && strategy.apply(current.get())){
                return current;
            }
        }
        return Optional.empty();
    }
}
