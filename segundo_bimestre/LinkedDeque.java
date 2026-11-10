public class LinkedDeque<E> implements MyDeque<E> {
    private MyLinkedList<E> lista;

    public LinkedDeque() {
        lista = new DLinkedList<>();
    }

    @Override
    public void addFirst(E element) {
        lista.addFirst(element);
    }

    @Override
    public void addLast(E element) {
        lista.addLast(element);
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) throw new IllegalStateException("Fila vazia");
        return lista.removeFirst();
    }

    @Override
    public E removeLast() {
        if (isEmpty()) throw new IllegalStateException("Fila vazia");
        return ((DLinkedList<E>) lista).removeLast();
    }

    @Override
    public E peekFirst() {
        if (isEmpty()) throw new IllegalStateException("Fila vazia");
        return lista.first();
    }

    @Override
    public E peekLast() {
        if (isEmpty()) throw new IllegalStateException("Fila vazia");
        return lista.last();
    }

    @Override
    public boolean isEmpty() {
        return lista.isEmpty();
    }

    @Override
    public int size() {
        return lista.size();
    }
    
    public void printDeque() {
    if (isEmpty()) {
        System.out.println("Fila vazia");
        return;
    }
    System.out.println("--- Fila de Espera ---");
    ((DLinkedList<E>) lista).imprime();
    }
}

