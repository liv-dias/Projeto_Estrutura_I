public class SLinkedList<E> implements MyLinkedList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public SLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E first() {
        if (isEmpty()) throw new IllegalStateException("Lista vazia");
        return head.getElement();
    }

    @Override
    public E last() {
        if (isEmpty()) throw new IllegalStateException("Lista vazia");
        return tail.getElement();
    }

    public Node<E> head() {
        return head;
    }

    @Override
    public void addFirst(E element) {
        Node<E> novo = new Node<>(element, head);
        head = novo;
        if (size == 0) tail = novo;
        size++;
    }

    @Override
    public void addLast(E element) {
        Node<E> novo = new Node<>(element, null);
        if (isEmpty()) {
            head = novo;
        } else {
            tail.setNext(novo);
        }
        tail = novo;
        size++;
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) throw new IllegalStateException("Lista vazia");
        E element = head.getElement();
        head = head.getNext();
        size--;
        if (isEmpty()) tail = null;
        return element;
    }
}