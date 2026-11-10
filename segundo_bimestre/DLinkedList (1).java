public class DLinkedList<E> implements MyLinkedList<E> {
    private int size;
    private DNode<E> header;
    private DNode<E> trailer;

    public DLinkedList() {
        header = new DNode<>(null, null, null);
        trailer = new DNode<>(null, header, null);
        header.setNext(trailer);
        size = 0;
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
        if (isEmpty())
            throw new IllegalStateException();
        return header.getNext().getElement();
    }

    @Override
    public E last() {
        if (isEmpty())
            throw new IllegalStateException();
        return trailer.getPrev().getElement();
    }

    private void addBetween(E e, DNode<E> pred, DNode<E> succ) {
        DNode<E> novo = new DNode<>(e, pred, succ);
        pred.setNext(novo);
        succ.setPrev(novo);
        size++;
    }

    @Override
    public void addFirst(E element) {
        addBetween(element, header, header.getNext());
    }

    @Override
    public void addLast(E element) {
        addBetween(element, trailer.getPrev(), trailer);
    }

    private E remove(DNode<E> node) {
        DNode<E> pred = node.getPrev();
        DNode<E> succ = node.getNext();
        pred.setNext(succ);
        succ.setPrev(pred);
        size--;
        node.setNext(null);
        node.setPrev(null);
        return node.getElement();
    }

    @Override
    public E removeFirst() {
        return remove(header.getNext());
    }

    public E removeLast() {
        return remove(trailer.getPrev());
    }

    public void reverse() {
        DNode<E> no = header;
        DNode<E> temp;
        while (no != null) {
            temp = no.getNext();
            no.setNext(no.getPrev());
            no.setPrev(temp);
            no = temp;
        }
        temp = header;
        header = trailer;
        trailer = temp;
    }

    public void imprime() {
        DNode<E> no = header.getNext();
        while (no.getNext() != null) {
            System.out.print(no.getElement() + " ");
            no = no.getNext();
        }
        System.out.println();
    }
}
