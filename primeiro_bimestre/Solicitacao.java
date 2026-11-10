// Classe de dominio que representa uma solicitacao de atendimento
public class Solicitacao {
    // Atributos privados do atendimento (encapsulamento)
    private int codigo;
    private String solicitante;
    private String descricao;
    private String categoria;
    private int prioridade;
    private String status;
    private String responsavel;
    private String data;
    private String hora;
    
    // Construtor: inicializa os dados e define o status inicial
    public Solicitacao(int codigo, String solicitante, String descricao, String categoria, int prioridade, String responsavel, String data, String hora) {
        this.codigo = codigo;
        this.solicitante = solicitante;
        this.descricao = descricao;
        this.categoria = categoria;
        this.prioridade = prioridade;
        this.status = "Aguardando"; // Status padrao de entrada
        this.responsavel = responsavel;
        this.data = data;
        this.hora = hora;
    }
    
    // Metodos de acesso (Getters e Setters)
    public int getCodigo() {
        return codigo;
    }
    
    public String getSolicitante() {
        return solicitante;
    }

    public String getDescricao(){
        return descricao;
    }

    public int getPrioridade(){
        return prioridade;
    }

    public String getResponsavel(){
        return responsavel;
    }
    
    public String getData(){
        return data;
    }

    public String getHora(){
        return hora;
    }

    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    // Retorna a representacao em texto formatada do objeto
    public String toString() {
        return String.format("\nID: %d | Solicitante: %s | Descrição: %s | Categoria: %s | Prioridade: %d | Status: %s | Responsavel: %s | Data: %s | Hora: %s\n", codigo, solicitante, descricao, categoria, prioridade, status, responsavel, data, hora);
    }
}