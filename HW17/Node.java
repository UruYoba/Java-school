package HW17;

public class Node<T> {
    T data;
    Node next;
    Node previous;

    public Node(T data, Node next, Node previous) {
        this.data = data;
        this.next = next;
        this.previous = previous;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
