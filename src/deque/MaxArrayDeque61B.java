package deque;

import java.util.Comparator;

public class MaxArrayDeque61B<T> extends ArrayDeque61B<T> {
    Comparator<T> c;

    public MaxArrayDeque61B(Comparator<T> c){
        this.c = c;
    }

    public T max(){
        T max = null;
        for (T x : items) {
            if (x != null && max == null) {
                max = x;
            }
            else if(x != null && max != null){
                if (c.compare(x, max) > 0) {
                    max = x;
                }
            }
        }
        return max;
    }

    public T max(Comparator<T> c){
        T max = null;
        for (T x : items) {
            if (x != null && max == null) {
                max = x;
            }
            else if(x != null && max != null){
                if (c.compare(x, max) > 0) {
                    max = x;
                }
            }
        }
        return max;
    }
}
