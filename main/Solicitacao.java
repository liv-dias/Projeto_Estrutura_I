public class Solicitacao{
    private int codigo;
    private String solicitante;
    private String descricao;
    private String categoria;
    private int data;
    private float hora;
    private String responsavel;
    private int prioridade;
    private String status;
}

public solicitacao(int codigo, String solicitante, String descricao, String categoria, int data, float hora, String responsavel, int prioridade, String status){
    this.codigo = codigo;
    this.solicitante = solicitante;
    this.descricao = descricao;
    this.categoria = categoria;
    this.data = data;
    this.hora = hora;
    this.responsavel = responsavel;
    this.prioridade = prioridade;
    this.status = status;
}