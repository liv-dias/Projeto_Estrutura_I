public interface MyStack<E>{
    void push(E element);
    E pop();
    E topo();
    boolean isEmpty();
    int size();
    void printStackInverso();
}