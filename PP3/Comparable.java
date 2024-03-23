

public interface Comparable<E> {
    public default int compareTo(E obj){ //default?
        return -1;
    }

}
