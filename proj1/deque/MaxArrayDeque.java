package deque;
import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T>  comparator;
    public MaxArrayDeque(Comparator<T> c){
        super();
        comparator = c;
    }

    public T max(){
        return max(comparator);
    }

    public T max(Comparator<T> c){
        if(isEmpty()){
            return null;
        }
        T max = get(0);
        for (T items : this){
            if(c.compare(items, max) > 0){
                max = items;
            }
        }
        return max;
    }

}

