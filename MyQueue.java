// Interface generica que define as operacoes da Fila
public interface MyQueue<E> {
    void enqueue(E element); // Adiciona no final
    E dequeue();            // Remove do inicio
    E front();              // Consulta o primeiro
    boolean isEmpty();      // Verifica se esta vazia
    int size();             // Retorna a quantidade
    void printQueue();     // Exibe os elementos
}