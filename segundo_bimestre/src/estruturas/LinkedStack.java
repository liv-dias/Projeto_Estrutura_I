public class LinkedStack<E> {

    private SLinkedList<E> elements;

    public LinkedStack() {
        elements = new SLinkedList<>();
    }

    public void push(E element) {
        elements.addFirst(element);
    }

    public E pop() {
        if ( isEmpty() ) {
            throw new IllegalStateException (" Pilha vazia ");
        }
        return elements.removeFirst();
    }

    public E top() {
        if ( isEmpty() ) {
            throw new IllegalStateException (" Pilha vazia ");
        }
        return elements.first();
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public int size() {
        return elements.size();
    }

    public void printStackInverso() {
        if (isEmpty()) {
            System.out.println("Pilha vazia!");
            return;
        }
        System.out.println("Historico de operacoes (MAIS ANTIGO AO MAIS RECENTE): ");
        
        // Como o head é o topo (mais recente), criamos um array ou lista temporária para inverter a ordem
        int n = size();
        Object[] temp = new Object[n];
        Node<E> atual = elements.head(); // Precisas de garantir que tens acesso ao head ou percorres com first/remove
        
        // Preenche o array do topo para a base
        int i = 0;
        while (atual != null) {
            temp[i++] = atual.getElement();
            atual = atual.getNext();
        }
        
        // Imprime do final (mais antigo) para o início (mais recente)
        for (int j = n - 1; j >= 0; j--) {
            System.out.println(temp[j]);
        }
    }
}