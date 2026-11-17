public class Node<E> {
    // atributos
    private E element;
    private Node<E> next;

    // construtor
    public Node(E element, Node<E> next) {
        this.element = element;
        this.next = next;
    }

    // métodos
    public E getElement() {
        return element;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setElement(E element) {
        this.element = element;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }
}