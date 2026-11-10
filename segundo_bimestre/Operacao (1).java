public class Operacao{
    private String tipo;
    private Solicitacao solicitacao;

    public Operacao(String tipo, Solicitacao solicitacao){
        this.tipo = tipo;//padrao
        this.solicitacao = solicitacao;
    }

    public String getTipo(){
        return tipo;
    }

    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    public Solicitacao getSolicitacao(){
        return solicitacao;
    }

    public String toString(){
        return String.format("Solicitação %d %s", solicitacao.getCodigo(), tipo);
    }

}