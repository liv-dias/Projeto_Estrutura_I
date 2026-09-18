public class ArrayStack<E> implements MyStack<E>{
    private E[] elements;
    private int topoIndex;
    private static final int DEFAULT_CAPACITY = 10;

    
    @SuppressWarnings("unchecked")
    public ArrayStack() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
        topoIndex = -1;
    }

    @Override 
    public void push(E element){
        if(topoIndex == elements.length - 1){
            throw new IllegalStateException("Pilha cheia!");
        }
        topoIndex++;
        elements[topoIndex] = element;
    }

    @Override 
    public E pop(){
        if(isEmpty()){
            throw new IllegalStateException("Pilha vazia!");
        }
        E element = elements[topoIndex];
        elements[topoIndex] = null;
        topoIndex--;
        return element;
    }

    @Override
    public E topo(){
        if(isEmpty()){
            throw new IllegalStateException("Pilha vazia!");
        }
        return elements[topoIndex];
    }

    @Override 
    public boolean isEmpty(){
        return topoIndex == -1;
    }

    @Override 
    public int size(){
        return topoIndex + 1;
    }

    @Override 
    public void printStackInverso(){
        if(isEmpty()){
            System.out.println("Pilha vazia!");
            return;
        }
        System.out.println("Histórico de operações (MAIS ANTIGO AO MAIS RECENTE): ");
        for(int i = 0; i <= topoIndex; i++){
            System.out.println(elements[i]);
        }
    }
}
