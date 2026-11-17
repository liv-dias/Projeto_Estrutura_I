public class DNode<E>{
    private E element;
    private DNode<E> next;
    private DNode<E> prev;
    public DNode (E e, DNode<E> n, DNode<E> p){
        this.element = e;
        this.next = n;
        this.prev = p;
    }

    public E getElement(){
        return element;
    }

    public DNode<E> next(){
        return next;
    }

    public DNode<E> prev(){
        return prev;
    }

    public void setElement(E element){
        this.element = element;
    }

    public void setNext(DNode<E> next){
        this.next = next;
    }

    public void setPrev(DNode<E> prev){
        this.prev = prev;
    }
}