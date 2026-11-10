public class LinkedStack<E> implements MyStack<E> {

    private SLinkedList<E> elements;

    public LinkedStack() {
        elements = new SLinkedList<>();
    }

    @Override
    public void push(E element) {
        elements.addFirst(element);
    }

    @Override
    public E pop() {
        if ( isEmpty() ) {
            throw new IllegalStateException (" Pilha vazia ");
        }
        return elements.removeFirst();
    }

    @Override
    public E top() {
        if ( isEmpty() ) {
            throw new IllegalStateException (" Pilha vazia ");
        }
        return elements.first();
    }

    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    @Override
    public int size() {
        return elements.size();
    }
    
    @Override
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