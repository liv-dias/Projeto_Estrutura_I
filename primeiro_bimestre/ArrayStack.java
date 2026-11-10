public class ArrayStack<E> implements MyStack<E>{
    private E[] elements;          // Vetor que armazena os elementos da pilha
    private int topoIndex;         // Índice do elemento no topo (-1 quando vazia)
    private static final int DEFAULT_CAPACITY = 10;

    // Cria a pilha com capacidade fixa e inicia como vazia
    @SuppressWarnings("unchecked")
    public ArrayStack() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
        topoIndex = -1;
    }

    @Override 
    public void push(E element){
        if(topoIndex == elements.length - 1){
            throw new IllegalStateException("Pilha cheia!"); // Limite do vetor atingido
        }
        topoIndex++;
        elements[topoIndex] = element; // Insere no topo (LIFO)
    }

    @Override 
    public E pop(){
        if(isEmpty()){
            throw new IllegalStateException("Pilha vazia!");
        }
        E element = elements[topoIndex];
        elements[topoIndex] = null;    // Evita referência presa (memory leak)
        topoIndex--;
        return element;                // Remove e retorna o elemento do topo
    }

    @Override
    public E topo(){
        if(isEmpty()){
            throw new IllegalStateException("Pilha vazia!");
        }
        return elements[topoIndex];    // Consulta o topo sem remover
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
        // Percorre do início do vetor até o topo, ou seja, do mais antigo ao mais recente
        for(int i = 0; i <= topoIndex; i++){
            System.out.println(elements[i]);
        }
    }
}
