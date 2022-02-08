package HW17;


public interface List<T> {
    void add(T data);

    void add(int index, T data);

    void set(T data, int index);

    void remove(T data);

    void remove(int index);

    Node getElement(int index);

    boolean contains(T data);
}
