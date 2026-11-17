import java.util.Scanner; 

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 

        // ---------- Estruturas de dados do sistema ----------//
        MyStack<Operacao> historicoOperacoes = new LinkedStack<>();      // Pilha: operacoes recentes (desfazer)
        MyDeque<Solicitacao> filaAtendimento = new LinkedDeque<>();      // Fila: solicitacoes aguardando
        DLinkedList<Solicitacao> cadastroGeral = new DLinkedList<>();    // Lista: todas as solicitacoes
        DLinkedList<Solicitacao> historicoAtendimentos = new DLinkedList<>(); // Lista dupla: atendimentos concluidos
        int contadorCodigo = 101;                                        // Gera codigos automaticos
        
        int opcao = -1;
        while (opcao != 0) {
            // ---------- Exibicao do menu ----------
            System.out.println("\n====================================");
            System.out.println("CENTRAL DE ATENDIMENTO");
            System.out.println("====================================");
            System.out.println(" \nATENDIMENTO");
            System.out.println(" 1 - Cadastrar nova solicitacao");
            System.out.println(" 2 - Consultar proxima solicitacao");
            System.out.println(" 3 - Atender proxima solicitacao");
            System.out.println(" 4 - Exibir fila de espera");
            System.out.println(" 5 - Quantidade de solicitacoes aguardando");
            System.out.println(" \nOPERACOES");
            System.out.println(" 6 - Consultar ultima operacao");
            System.out.println(" 7 - Exibir historico recente");
            System.out.println(" 8 - Desfazer ultima operacao");
            System.out.println(" \nCADASTRO GERAL");
            System.out.println(" 9 - Exibir todas as soliciacoes");
            System.out.println(" 10 - Pesquisar solicitacao por codigo");
            System.out.println(" 11 - Remover solicitacao");
            System.out.println(" 12 - Exibir soliciacoes por status");
            System.out.println(" 13 - Exibir solicitacoes por categoria");
            System.out.println(" \nHISTORICO DE ATENDIMENTOS");
            System.out.println(" 14 - Exibir do primeiro ao ultimo");
            System.out.println(" 15 - Exibir do ultimo ao primeiro");
            System.out.println(" 16 - Navegar pelo historico");
            System.out.println(" 17 - Pesquisar atendimento");
            System.out.println(" \nRELATORIOS");
            System.out.println(" 18 - Exibir resumo do sistema");
            System.out.println(" \n 0 - Encerrar");
            System.out.println(" \nDigite uma opcao a ser realizada: ");

            // Valida se a entrada e um inteiro
            if (!scanner.hasNextInt()) {
                System.out.println("Por favor, digite um numero valido.");
                scanner.nextLine();
                continue;
            }
            
            opcao = scanner.nextInt();
            scanner.nextLine(); // limpa o Enter que sobrou
    
            switch (opcao) {
                // ===== 1: Cadastrar -> entra na Fila, no Cadastro e na Pilha =====
                case 1:
                    System.out.println("Digite o nome do solicitante: ");
                    String nome = scanner.nextLine();
                    
                    System.out.println("Descreva o problema: ");
                    String prob = scanner.nextLine();
                    
                    System.out.println("Digite a categoria (Presencial, Autoatendimento, Telefone): ");
                    String cat = scanner.nextLine();
                    
                    // Repete ate o usuario digitar um inteiro
                    int prior = -1;
                    while (true) {
                        System.out.println("Digite a prioridade: ");
                        if (scanner.hasNextInt()) {
                            prior = scanner.nextInt();
                            scanner.nextLine();
                            break;
                        } else {
                            System.out.println("Digite apenas numeros inteiros para a prioridade.");
                            scanner.nextLine();
                        }
                    }

                    System.out.println("Digite o nome do responsavel pelo solicitante: ");
                    String resp = scanner.nextLine();

                    // Valida formato "data hora" (duas partes separadas por espaco)
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
                    
                    Solicitacao nova = new Solicitacao(contadorCodigo++, nome, prob, cat, prior, resp, data, hora);
                    filaAtendimento.addLast(nova);   // FIFO: entra no fim da fila
                    cadastroGeral.addLast(nova);     // registro geral
                    Operacao novaOperacao = new Operacao("CADASTRADA", nova);
                    historicoOperacoes.push(novaOperacao); // registra para poder desfazer
                    System.out.println("Solicitacao cadastrada com sucesso!");
                    break;
                    
                // ===== 2: Consultar a proxima da fila (sem remover) =====
                case 2:
                    if (filaAtendimento.isEmpty()) {
                        System.out.println("Nao tem solicitacao na fila");
                    } else {
                        System.out.println("Proxima solicitacao: " + filaAtendimento.peekFirst());
                    }
                    break;
                        
                // ===== 3: Atender -> sai da Fila, vai ao historico (lista dupla) e a Pilha =====
                case 3:
                    if (filaAtendimento.isEmpty()) {
                        System.out.println("Nao existe solicitacao na fila");
                    } else {
                        Solicitacao atendida = filaAtendimento.removeFirst(); // FIFO: sai do inicio
                        atendida.setStatus("CONCLUIDA"); 
                        historicoAtendimentos.addLast(atendida);
                        Operacao atendimento = new Operacao("ATENDIDA", atendida);
                        historicoOperacoes.push(atendimento);
                        System.out.println("Atendimento iniciado para " + atendida);
                    }
                    break;
                    
                // ===== 4: Exibir a fila =====
                case 4:
                    filaAtendimento.printDeque();
                    break;
                    
                // ===== 5: Quantidade na fila =====
                case 5:
                    System.out.println("\nQuantidade de solicitacoes totais: " + filaAtendimento.size());
                    break; 
                    
                // ===== 6: Ultima operacao (topo da pilha, sem remover) =====
                case 6:
                    if (historicoOperacoes.isEmpty()) {
                        System.out.println("Nenhuma operação foi realizada!");
                    } else {
                        System.out.println("\nÚltima operação realizada:  " + historicoOperacoes.top());
                    } 
                    break;
                    
                // ===== 7: Historico recente (do topo para a base) =====
                case 7:
                    if (historicoOperacoes.isEmpty()) {
                        System.out.println("Historico Vazio!");
                    } else {
                        historicoOperacoes.printStackInverso();
                    }
                    break;
                    
                // ===== 8: Desfazer -> desempilha e reverte conforme o tipo da operacao =====
                case 8:
                    if (historicoOperacoes.isEmpty()) {
                        System.out.println("Nao ha nenhuma operacao para desfazer!");
                    } else {
                        Operacao ultimaOp = historicoOperacoes.pop();
                        Solicitacao ultimaSolicitacao = ultimaOp.getSolicitacao();
                        if (ultimaSolicitacao.getStatus().equalsIgnoreCase("Aguardando")) {
                            // Desfaz CADASTRO: remove da fila e do cadastro (era o ultimo inserido)
                            Solicitacao ultima = filaAtendimento.removeLast();
                            cadastroGeral.removeLast();
                            System.out.println("Solicitacao de codigo " + ultima.getCodigo() + " foi removida!");
                        } else if (ultimaOp.getTipo().equalsIgnoreCase("ATENDIDA")) {
                            // Desfaz ATENDIMENTO: tira do historico e devolve ao inicio da fila
                            historicoAtendimentos.removeLast();
                            ultimaSolicitacao.setStatus("Aguardando");
                            filaAtendimento.addFirst(ultimaSolicitacao);
                            System.out.println("A solicitacao de codigo " + ultimaSolicitacao.getCodigo() + " voltou ao estado de 'Aguardando'");
                        }
                    }
                    break;
                    
                // ===== 9: Exibir todo o cadastro =====
                case 9:
                    if (cadastroGeral.isEmpty()) {
                        System.out.println("Nenhuma solicitacao cadastrada no sistema.");
                    } else {
                        System.out.println("--- Todas as Solicitacoes ---");
                        cadastroGeral.imprime();
                    }
                    break;
                    
                // ===== 10: Busca sequencial por codigo no cadastro =====
                case 10:
                    if (cadastroGeral.isEmpty()) {
                        System.out.println("Nenhuma solicitacao cadastrada no sistema.");
                        break;
                    }
                    System.out.println("Digite o codigo da solicitacao que deseja pesquisar: ");
                    if (!scanner.hasNextInt()) {
                        System.out.println("Codigo invalido! Digite apenas numeros.");
                        scanner.nextLine();
                        break;
                    }
                    int codPesquisa = scanner.nextInt();
                    scanner.nextLine();
                    
                    // Percorre a lista ate achar o codigo
                    DNode<Solicitacao> atualPesquisa = cadastroGeral.getHead();
                    Solicitacao encontrada = null;
                    while (atualPesquisa != null) {
                        if (atualPesquisa.getElement().getCodigo() == codPesquisa) {
                            encontrada = atualPesquisa.getElement();
                            break;
                        }
                        atualPesquisa = atualPesquisa.getNext();
                    }
                    
                    if (encontrada != null) {
                        System.out.println("Solicitacao encontrada: " + encontrada);
                    } else {
                        System.out.println("Nenhuma solicitacao encontrada com o codigo " + codPesquisa);
                    }
                    break;
                    
                // ===== 11: Remover do cadastro por codigo (busca o no e remove) =====
                case 11:
                    if (cadastroGeral.isEmpty()) {
                        System.out.println("Nenhuma solicitacao cadastrada no sistema.");
                        break;
                    }
                    System.out.println("Digite o codigo da solicitacao que deseja remover: ");
                    if (!scanner.hasNextInt()) {
                        System.out.println("Codigo invalido! Digite apenas numeros.");
                        scanner.nextLine();
                        break;
                    }
                    int codRemover = scanner.nextInt();
                    scanner.nextLine();
                    
                    DNode<Solicitacao> atualRemover = cadastroGeral.getHead();
                    DNode<Solicitacao> noParaRemover = null;
                    while (atualRemover != null) {
                        if (atualRemover.getElement().getCodigo() == codRemover) {
                            noParaRemover = atualRemover;
                            break;
                        }
                        atualRemover = atualRemover.getNext();
                    }
                    
                    if (noParaRemover != null) {
                        cadastroGeral.removeNode(noParaRemover);
                        System.out.println("Solicitacao de codigo " + codRemover + " removida do cadastro geral!");
                    } else {
                        System.out.println("Nenhuma solicitacao encontrada com o codigo " + codRemover);
                    }
                    break;
                    
                // ===== 12: Filtrar cadastro por status =====
                case 12:
                    if (cadastroGeral.isEmpty()) {
                        System.out.println("Nenhuma solicitacao cadastrada no sistema.");
                        break;
                    }
                    System.out.println("Digite o status que deseja filtrar (ex: Aguardando, EM_ATENDIMENTO): ");
                    String statusFiltro = scanner.nextLine().trim();
                    
                    DNode<Solicitacao> atualStatus = cadastroGeral.getHead();
                    boolean achouStatus = false;
                    System.out.println("--- Solicitacoes com status: " + statusFiltro + " ---");
                    while (atualStatus != null) {
                        if (atualStatus.getElement().getStatus().equalsIgnoreCase(statusFiltro)) {
                            System.out.println(atualStatus.getElement());
                            achouStatus = true;
                        }
                        atualStatus = atualStatus.getNext();
                    }
                    if (!achouStatus) {
                        System.out.println("Nenhuma solicitacao encontrada com esse status.");
                    }
                    break;
                    
                // ===== 13: Filtrar cadastro por categoria =====
                case 13:
                    if (cadastroGeral.isEmpty()) {
                        System.out.println("Nenhuma solicitacao cadastrada no sistema.");
                        break;
                    }
                    System.out.println("Digite a categoria que deseja filtrar (Presencial, Autoatendimento, Telefone): ");
                    String catFiltro = scanner.nextLine().trim();
                    
                    DNode<Solicitacao> atualCat = cadastroGeral.getHead();
                    boolean achouCat = false;
                    System.out.println("--- Solicitacoes da categoria: " + catFiltro + " ---");
                    while (atualCat != null) {
                        if (atualCat.getElement().getCategoria().equalsIgnoreCase(catFiltro)) {
                            System.out.println(atualCat.getElement());
                            achouCat = true;
                        }
                        atualCat = atualCat.getNext();
                    }
                    if (!achouCat) {
                        System.out.println("Nenhuma solicitacao encontrada nessa categoria.");
                    }
                    break;
                    
                // ===== 14: Percurso direto do historico (head -> tail via getNext) =====
                case 14:
                    if (historicoAtendimentos.isEmpty()){
                        System.out.println("Nenhum atendimento concluido ainda.");
                        break;
                    }
                    System.out.println("---Historico(primeiro ao último)---");
                    DNode<Solicitacao> no_14 = historicoAtendimentos.getHead();
                    int pos = 1;
                    while(no_14 != null){
                        System.out.println(pos++ + ". " + no_14.getElement()); // pos++ numera cada linha
                        no_14 = no_14.getNext();
                    }
                    break;
                    
                // ===== 15: Percurso inverso do historico (tail -> head via getPrev) =====
                case 15:
                    if(historicoAtendimentos.isEmpty()){
                        System.out.println("Nenhum atendimento concluido ainda.");
                        break;
                    }
                    System.out.println("---Histórico(do último ao primeiro)---");
                    DNode<Solicitacao> no_15 = historicoAtendimentos.getTail();
                    while(no_15 != null){
                        System.out.println(no_15.getElement());
                        no_15 = no_15.getPrev();
                    }
                    break;
                    
                // ===== 16: Navegacao interativa (anterior/proximo) pelo historico =====
                case 16:
                    if (historicoAtendimentos.isEmpty()) {
                        System.out.println("Nenhum atendimento concluido ainda.");
                        break;
                    }
                    DNode<Solicitacao> atual_16 = historicoAtendimentos.getTail(); // comeca no mais recente
                    boolean sair_16 = false;
                    while (!sair_16) {
                        System.out.println("\n--- Consultar historico navegavel ---");
                        System.out.println("Atendimento atual: " + atual_16.getElement().getCodigo());
                        System.out.println(atual_16.getElement());
                        // Avisa quando esta numa das pontas da lista
                        if (atual_16.getPrev() == null) System.out.println("(primeiro registro do historico)");
                        if (atual_16.getNext() == null) System.out.println("(ultimo registro do historico)");
                        System.out.println("[A] Anterior");
                        System.out.println("[P] Proximo");
                        System.out.println("[S] Sair");
                        System.out.print("Opcao: ");
                        String nav = scanner.nextLine().trim().toUpperCase();
                
                        // So move o ponteiro se existir no na direcao escolhida
                        if (nav.equals("A")) {
                            if (atual_16.getPrev() == null) System.out.println("Nao ha registro anterior.");
                            else atual_16 = atual_16.getPrev();
                        } else if (nav.equals("P")) {
                            if (atual_16.getNext() == null) System.out.println("Nao ha proximo registro.");
                            else atual_16 = atual_16.getNext();
                        } else if (nav.equals("S")) {
                            sair_16 = true;
                        } else {
                            System.out.println("Opcao invalida. Use A, P ou S.");
                        }
                    }
                    break;
                    
                // ===== 17: Pesquisar atendimento por codigo (mostra vizinhos anterior/proximo) =====
                case 17:
                    if (historicoAtendimentos.isEmpty()) {
                        System.out.println("Nenhum atendimento concluido ainda.");
                        break;
                    }
                    System.out.println("Digite o codigo do atendimento que deseja pesquisar: ");
                    if (!scanner.hasNextInt()) {
                        System.out.println("Codigo invalido! Digite apenas numeros.");
                        scanner.nextLine();
                        break;
                    }
                    int cod_17 = scanner.nextInt();
                    scanner.nextLine();
                
                    // Avanca ate achar o codigo ou chegar ao fim (null)
                    DNode<Solicitacao> no_17 = historicoAtendimentos.getHead();
                    while (no_17 != null && no_17.getElement().getCodigo() != cod_17) {
                        no_17 = no_17.getNext();
                    }
                
                    if (no_17 == null) {
                        System.out.println("Atendimento " + cod_17 + " nao encontrado no historico.");
                    } else {
                        System.out.println("Encontrado: " + no_17.getElement());
                        System.out.println("Anterior  : " + (no_17.getPrev() == null ? "(nenhum)" : no_17.getPrev().getElement()));
                        System.out.println("Proximo   : " + (no_17.getNext() == null ? "(nenhum)" : no_17.getNext().getElement()));
                    }
                    break;
                    
                // ===== 18: Resumo -> conta solicitacoes por status e tamanhos das estruturas =====
                case 18:
                    int total = 0, aguardando = 0, emAtend = 0, concluidas = 0, canceladas = 0;
                    DNode<Solicitacao> no18 = cadastroGeral.getHead();
                    while (no18 != null) {
                        String st = no18.getElement().getStatus();
                        total++;
                        if (st.equalsIgnoreCase("AGUARDANDO")) aguardando++;
                        else if (st.equalsIgnoreCase("EM_ATENDIMENTO")) emAtend++;
                        else if (st.equalsIgnoreCase("CONCLUIDA")) concluidas++;
                        else if (st.equalsIgnoreCase("CANCELADA")) canceladas++;
                        no18 = no18.getNext();
                    }
                
                    // Conta os atendimentos concluidos percorrendo a lista dupla
                    int totalHist = 0;
                    DNode<Solicitacao> h18 = historicoAtendimentos.getHead();
                    while (h18 != null) { totalHist++; h18 = h18.getNext(); }
                
                    System.out.println("========== RESUMO DO SISTEMA ==========");
                    System.out.println("Solicitacoes cadastradas (lista) : " + total);
                    System.out.println("  - Aguardando                   : " + aguardando);
                    System.out.println("  - Em atendimento               : " + emAtend);
                    System.out.println("  - Concluidas                   : " + concluidas);
                    System.out.println("  - Canceladas                   : " + canceladas);
                    System.out.println("Na fila de espera                : " + filaAtendimento.size());
                    System.out.println("Operacoes no historico recente   : " + historicoOperacoes.size()); // ajuste se nao existir size()
                    System.out.println("Atendimentos concluidos (lista)  : " + totalHist);
                    if (totalHist > 0) {
                        System.out.println("  - Primeiro: " + historicoAtendimentos.getHead().getElement().getCodigo());
                        System.out.println("  - Ultimo  : " + historicoAtendimentos.getTail().getElement().getCodigo());
                    }
                    System.out.println("=======================================");
                    break;
                    
                // ===== 0: Sair =====
                case 0:
                    System.out.println("\nEncerrando o Sistema da Central de Atendimento. Ate mais!");
                    break;
                    
                default:
                    System.out.println("\nOpcao invalida, escolha um numero que esta no menu");
            }
        }
        scanner.close();
    }
}