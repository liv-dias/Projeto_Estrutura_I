/*
Integrantes do Grupo
Nome: Diego Wehby Del Nero - RA: 10736455 
Nome: Livia Calado de Carvalho Dias - RA: 10737709 
*/


import java.util.Scanner; 

// Classe principal que executa a interface de usuario no console
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        MyStack<Operacao> historicoOperacoes = new ArrayStack<>();       // Pilha (LIFO) para registrar o histórico de operacoes
        MyQueue<Solicitacao> filaAtendimento = new CircularArrayQueue<>(); // Fila (FIFO) para controlar a ordem de atendimento
        int contadorCodigo = 101; // Gerador de ID incremental
        
        int opcao = -1;
        // Loop do Menu: repete ate o usuário escolher a opção 0 (encerrar)
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

            // Valida se o que foi digitado e um numero antes de ler a opcao
            if (!scanner.hasNextInt()) {
                System.out.println("Por favor, digite um numero valido.");
                scanner.nextLine();
                continue;
            }
            
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o \n deixado no buffer pelo nextInt()
            
            switch (opcao) {
                case 1: // Cria e enfileira uma nova solicitacao
                    System.out.println("Digite o nome do solicitante: ");
                    String nome = scanner.nextLine();
                    
                    System.out.println("Descreva o problema: ");
                    String prob = scanner.nextLine();
                    
                    System.out.println("Digite a categoria (Presencial, Autoatendimento, Telefone): ");
                    String cat = scanner.nextLine();
                    
                    // Validacao do tipo inteiro para a prioridade
                    int prior = -1;
                    while (true) {
                        System.out.println("Digite a prioridade: ");
                        if (scanner.hasNextInt()) {
                            prior = scanner.nextInt();
                            scanner.nextLine(); // Limpa o buffer
                            break;
                        } else {
                            System.out.println("Digite apenas numeros inteiros para a prioridade.");
                            scanner.nextLine(); // Limpa o buffer com entrada invalida
                        }
                    }

                    System.out.println("Digite o nome do responsavel pelo solicitante: ");
                    String resp = scanner.nextLine();

                    // Validacao do formato de data e hora 
                    String data = "";
                    String hora = "";
                    while (true) {
                        System.out.println("Digite a data e hora (DD-MM-AAAA HH:MM): ");
                        String entrada = scanner.nextLine().trim();
                        String[] partes = entrada.split(" ");
                        
                        if (partes.length == 2 && !partes[0].isEmpty() && !partes[1].isEmpty()) {
                            data = partes[0].replace("-", "/");
                            hora = partes[1];
                            break;
                        } else {
                            System.out.println("Formato invalido! Tente novamente.");
                        }
                    }
                    
                    // Monta a solicitacao, insere na fila e registra a operação no historico
                    Solicitacao nova = new Solicitacao(contadorCodigo++, nome, prob, cat, prior, resp, data, hora);
                    filaAtendimento.enqueue(nova);
                    Operacao novaOperacao = new Operacao("CADASTRADA", nova);
                    historicoOperacoes.push(novaOperacao);
                    System.out.println("Solicitacao cadastrada com sucesso!");
                    break;
                    
                case 2: // Consulta o inicio da fila (sem remover)
                    if (filaAtendimento.isEmpty()) {
                        System.out.println("Nao tem solicitacao na fila");
                    } else {
                        System.out.println("Proxima solicitacao: " + filaAtendimento.front());
                    }
                    break;
                        
                case 3: // Desenfileira a proxima solicitacao e atualiza o status
                    if (filaAtendimento.isEmpty()) {
                        System.out.println("Nao existe solicitacao na fila");
                    } else {
                        Solicitacao atendida = filaAtendimento.dequeue();
                        atendida.setStatus("EM_ATENDIMENTO"); 
                        Operacao atendimento = new Operacao("ATENDIDA", atendida);
                        historicoOperacoes.push(atendimento); // Registra o atendimento no histórico
                        System.out.println("Atendimento iniciado para " + atendida);
                    }
                    break;
                    
                case 4: // Exibe todas as solicitacoes na fila
                    filaAtendimento.printQueue();
                    break;
                    
                case 5: // Exibe a quantidade de solicitacoes na fila
                    System.out.println("\nQuantidade de solicitacoes totais: " + filaAtendimento.size());
                    break; 
                    
                case 6: // Consulta o topo da pilha (ultima operação), sem remover
                    if (historicoOperacoes.isEmpty()) {
                        System.out.println("Nenhuma operação foi realizada!");
                    } else {
                        System.out.println("\nÚltima operação realizada:  " + historicoOperacoes.topo());
                    } 
                    break;
                    
                case 7: // Exibe todo o histórico de operacoes (do mais recente ao mais antigo)
                    if (historicoOperacoes.isEmpty()) {
                        System.out.println("Historico Vazio!");
                    } else {
                        historicoOperacoes.printStackInverso();
                    }
                    break;
                    
                case 8: // Desfaz a ultima operacao registrada na pilha
                    if (historicoOperacoes.isEmpty()) {
                        System.out.println("Nao ha nenhuma operacao para desfazer!");
                    } else {
                        Operacao ultimaOp = historicoOperacoes.pop();
                        Solicitacao ultimaSolicitacao = ultimaOp.getSolicitacao();
                        if (ultimaSolicitacao.getStatus().equalsIgnoreCase("Aguardando")) {
                            // Desfaz um cadastro: remove a solicitação da fila
                            Solicitacao ultima = filaAtendimento.dequeue();
                            System.out.println("Solicitacao de codigo " + ultima.getCodigo() + " foi removida!");
                        } else if (ultimaOp.getTipo().equalsIgnoreCase("ATENDIDA")) {
                            // Desfaz um atendimento: volta o status e reinsere na fila
                            ultimaSolicitacao.setStatus("Aguardando");
                            filaAtendimento.enqueue(ultimaSolicitacao);
                            System.out.println("A solicitacao de codigo " + ultimaSolicitacao.getCodigo() + " voltou ao estado de 'Aguardando'");
                        }
                    }
                    break;
                    
                case 0: // Encerra o programa
                    System.out.println("\nEncerrando o Sistema da Central de Atendimento. Ate mais!");
                    break;
                    
                default: // Trata opcoes fora do menu
                    System.out.println("\nOpcao invalida, escolha um numero que esta no menu");
            }
        }
        scanner.close(); // Fecha o Scanner
    }
}
