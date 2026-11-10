// Implementacao de Fila Generica baseada em Vetor Circular
public class CircularArrayQueue<E> implements MyQueue<E> {
    private E[] elements;
    private int front;
    private int rear;
    private int count;
    public static final int DEFAULT_CAPACITY = 10;

    @SuppressWarnings("unchecked")
    public CircularArrayQueue() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
        front = 0;
        rear = -1;
        count = 0;
    }

    // Insere um elemento usando indice circular
    @Override
    public void enqueue(E element) {
        if (count == elements.length) {
            throw new IllegalStateException(" Fila cheia ");
        }
        rear = (rear + 1) % elements.length;
        elements[rear] = element;
        count++;
    }

    // Remove e retorna o primeiro elemento
    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException(" Fila vazia ");
        }
        E element = elements[front];
        elements[front] = null;
        front = (front + 1) % elements.length;
        count--;
        return element;
    }

    // Retorna o primeiro elemento sem remover
    @Override
    public E front() {
        if (isEmpty()) {
            throw new IllegalStateException(" Fila vazia ");
        }
        return elements[front];
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public int size() {
        return count;
    }
    
    // Imprime todos os elementos da fila sem esvazia-la
    @Override
    public void printQueue() {
        if (isEmpty()) {
            System.out.println("Fila vazia");
            return;
        }
        System.out.println("---Fila de Espera ---");
        int current = front;
        for (int i = 0; i < count; i++) {
            System.out.println(" - " + elements[current]);
            current = (current + 1) % elements.length; // Avanco circular
        }
    }

}