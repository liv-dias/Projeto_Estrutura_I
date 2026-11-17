public interface MyDeque<E> {
    void addFirst(E element);   // Insere no inicio
    void addLast(E element);    // Insere no final
    E removeFirst();            // Remove e retorna o inicio
    E removeLast();             // Remove e retorna o final
    E peekFirst();              // Consulta o inicio
    E peekLast();               // Consulta o final
    boolean isEmpty();          // Verifica se esta vazia
    int size();                 // Retorna a quantidade
    void printDeque();
}