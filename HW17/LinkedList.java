package HW17;


public class LinkedList<T> implements List<T> {

    int size;
    Node<T> first;
    Node<T> last;

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>(1);
        list.add(2);
        System.out.println(list);
        list.add(0, 3);
        list.add(0, 4);
        list.add(0, 5);
        System.out.println(list);
        list.remove(1);
        list.remove(5);
        System.out.println(list);
        System.out.println(list.contains(2));
        list.set(5, 3);
        System.out.println(list);
        System.out.println(list.contains(2));
    }

    public LinkedList() {
        this.size = 0;
    }

    public LinkedList(T data) {
        this.size = 0;
        add(data);
    }

    /**
     * Добавляет данные в связанный список
     * @param data  Данные, которые добавляются в связанный список
     */
    @Override
    public void add(T data) {
        if (this.size == 0) {
            this.first = new Node<>(data, null, null);
            this.last = this.first;
        } else {
            Node last = this.last;
            Node newElement = new Node<T>(data, null, last);
            this.last = newElement;
            last.next = newElement;
        }
        this.size++;
    }

    /**
     * Добавляет данные в связанный список по индексу
     * @param index индекс, в который добавляется элемент
     * @param data - данные, добавляемые в связанный список
     */
    @Override
    public void add(int index, T data) {
        if(index == 0){
            Node newElement = new Node(data, this.first, null);
            this.first.previous = newElement;
            this.first = newElement;
            this.size++;
            return;
        } else if(index == this.size - 1){
            add(data);
            return;
        }
        Node currentElement = getElement(index);
        if (currentElement != null) {
            Node prevElement = currentElement.previous;
            Node newElement = new Node(data, currentElement, prevElement);
            if (prevElement != null) {
                prevElement.next = newElement;
            }
            currentElement.previous = newElement;
        }
        this.size++;
    }

    /**
     * Переопределение данных по индексу
     * @param data новые данные
     * @param index индекс, в котором хранятся данные
     */
    @Override
    public void set(T data, int index) {
        Node element = getElement(index);
        // Показывать сообщение не нужно, оно показывается в методе getElement
        if (element != null) {
            element.data = data;
        }
    }

    /**
     * Удаление элемента, в котором есть указанные данные
     * @param data данные для удаления
     */
    @Override
    public void remove(T data) {
        Node element = null;
        Node current = this.first;
        for (int i = 0; i < this.size; i++) {
            if (current.data.equals(data)) {
                element = current;
                break;
            }
            current = current.next;
        }
        if (element == null) {
            System.out.println(
                    String.format(
                            "Данных со значением %s не найдено.",
                            data.toString()
                    )
            );
        } else {
            remove(element);
        }
    }

    /**
     * Удаляет ноду
     * @param element - нода, которую удаляем
     */
    private void remove(Node element) {
        Node nextElement = element.next;
        Node prevElement = element.previous;
        // Добавил обработку null, потому что первый и последний элементы имеют ссылки на null
        // А у null нельзя вызвать никаких свойств
        if (nextElement == null) {
            prevElement.next = null;
            this.last = prevElement;
        } else if (prevElement == null) {
            nextElement.previous = null;
            this.first = nextElement;
        } else {
            nextElement.previous = prevElement;
            prevElement.next = nextElement;
        }
        this.size--;
    }

    /**
     * Удаление данных по индексу
     * @param index индекс, в котором хранятся данные
     */
    @Override
    public void remove(int index) {
        Node element = getElement(index);
        if (element != null) {
            remove(element);
        }
    }

    /**
     * Получение данных по индексу
     * @param index индекс, в котором хранятся данные
     * @return null, если индекс не входит в размер листа, или ноду по индексу
     */
    @Override
    public Node getElement(int index) {
        if (index >= size | index < 0) {
            System.out.println(String.format("Элемент по индексу '%d' не существует!", index));
            return null;
        }
        Node current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    /**
     * Проверка содержания данных в листе
     * @param data данные
     * @return true, если данные содержатся в листе, false, если нет.
     */
    @Override
    public boolean contains(T data) {
        Node current = this.first;
        for (int i = 0; i < this.size; i++) {
            // Реализацию метода equals оставляем на пользователя
            if (current.data.equals(data)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public String toString() {
        String result = "[";
        Node current = this.first;
        for (int i = 0; i < this.size; i++) {
            if (current != null) {
                result += ( i > 0 ? ", " : "") + current.data.toString();
            } else {
                break;
            }
            current = current.next;
        }
        return result + "]";
    }
}
