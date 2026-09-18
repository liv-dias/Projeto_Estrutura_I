import java.util.Scanner; 

// Classe principal que executa a interface de usuario no console
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        MyStack<Operacao> historicoOperacoes = new ArrayStack<>();
        MyQueue<Solicitacao> filaAtendimento = new CircularArrayQueue<>();
        int contadorCodigo = 101; // Gerador de ID incremental
        int opcao = -1;
        
        // Loop do Menu
        while (opcao != 0) {
            System.out.println("\n====================================");
            System.out.println("CENTRAL DE ATENDIMENTO");
            System.out.println("====================================");
            System.out.println(" 1 - Cadastrar nova solicitacao");
            System.out.println(" 2 - Consultar proxima solicitacao");
            System.out.println(" 3 - Atender proxima solicitacao");
            System.out.println(" 4 - Exibir fila de solicitacoes");
            System.out.println(" 5 - Exibir quantidade de solicitacoes");
            System.out.println(" 6 - Consultar ultima operacao realizada");
            System.out.println(" 7 - Exibir historico de operacoes");
            System.out.println(" 8 - Desfazer ultima operacao");
            System.out.println(" 0 - Encerrar");
            System.out.println("\nDigite uma opcao a ser realizada: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Por favor, digite um numero valido.");
                scanner.nextLine();
                continue;
            }
            
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do teclado
            
            switch (opcao) {
                case 1: // Cria e enfileira uma nova solicitacao
                    System.out.println("Digite o nome do solicitante: ");
                    String nome = scanner.nextLine();
                    
                    System.out.println("Descreva o problema: ");
                    String prob = scanner.nextLine();
                    
                    System.out.println("Digite a categoria (Presencial, Autoatendimento, Telefone): ");
                    String cat = scanner.nextLine();
                    
                    System.out.println("Digite a prioridade: ");
                    int prior = scanner.nextInt();
                    scanner.nextLine(); 

                    System.out.println("Digite o nome do responsável pelo solicitante: ");
                    String resp = scanner.nextLine();

                    System.out.println("Digite a data e hora (DD-MM-AAAA HH:MM): ");
                    String entrada = scanner.nextLine();
                    String[] partes = entrada.split(" ");
                    String data = partes[0].replace("-", "/");
                    String hora = partes[1];
                    
                    Solicitacao nova = new Solicitacao(contadorCodigo++, nome, prob, cat, prior, resp, data, hora);
                    filaAtendimento.enqueue(nova);
                    Operacao novaOperacao = new Operacao("CADASTRADA",nova);
                    historicoOperacoes.push(novaOperacao);
                    System.out.println("Solicitacao cadastrada com sucesso!");
                    break;
                    
                case 2: // Consulta o inicio da fila
                    if (filaAtendimento.isEmpty()) {
                        System.out.println("Nao tem solicitacao na fila");
                    } else {
                        System.out.println("Proxima solicitacao: " + filaAtendimento.front());
                    }
                    break;
                        
                case 3: // Desenfileira e altera o status
                    if (filaAtendimento.isEmpty()) {
                        System.out.println("Nao existe solicitacao na fila");
                    } else {
                        Solicitacao atendida = filaAtendimento.dequeue();
                        atendida.setStatus("EM_ATENDIMENTO"); 
                        Operacao atendimento = new Operacao("ATENDIDA", atendida);
                        historicoOperacoes.push(atendimento);
                        System.out.println("Atendimento iniciado para " + atendida);
                    }
                    break;
                    
                case 4: // Exibe a fila
                    filaAtendimento.printQueue();
                    break;
                    
                case 5: // Exibe a quantidade na fila
                    System.out.println("\nQuantidade de solicitacoes totais: " + filaAtendimento.size());
                    break; 
                    
                case 6:
                    if(historicoOperacoes.isEmpty()){
                        System.out.println("Nenhuma operação foi realizada!");
                    }else{
                        System.out.println("\nÚltima operação realizada:  " + historicoOperacoes.topo());
                    } 
                    break;
                    
                case 7:
                    if(historicoOperacoes.isEmpty()){
                        System.out.println("Histórico Vazio!");
                    }else{
                        historicoOperacoes.printStackInverso();
                    }
                    break;
                    
                case 8:
                    if (historicoOperacoes.isEmpty()){
                        System.out.println("Não há nenhuma operação para desfazer!");
                    }else{
                        Operacao ultimaOp = historicoOperacoes.pop();
                        Solicitacao ultimaSolicitacao = ultimaOp.getSolicitacao();
                        if(ultimaSolicitacao.getStatus().equalsIgnoreCase("Aguardando")){
                            Solicitacao ultima = filaAtendimento.dequeue();
                            System.out.println("Solicitação de código " + ultima.getCodigo() + " foi removida!");
                    
                    }   else if(ultimaOp.getTipo().equalsIgnoreCase("ATENDIDA")){
                        ultimaSolicitacao.setStatus("Aguardando");

                        filaAtendimento.enqueue(ultimaSolicitacao);
                        System.out.println("A solicitação de código " + ultimaSolicitacao.getCodigo() + " voltou ao estado de 'Aguardando'");
                    }}
                    break;
                    
                case 0: // Encerra o programa
                    System.out.println("\nEncerrando o Sistema da Central de Atendimento. Ate mais!");
                    break;
                    
                default: // Trata opcoes fora do menu
                    System.out.println("\nOpcao invalida, escolha um numero que esta no menu");
            }
        }
        scanner.close();
    }
}