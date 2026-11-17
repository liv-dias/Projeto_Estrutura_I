import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Atendimento{
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private Solicitacao solicitacao;
    private String responsavel;
    private LocalDateTime dataConclusao;
    private String observacao;//opcional

    public Atendimento(Solicitacao solicitacao, String responsavel){
        this(solicitacao, responsavel, "");//construtor caso nao seja usado a observação
    }

    public Atendimento(Solicitacao solicitacao, String responsavel, LocalDateTime dataConclusao, String observacao){
        this.solicitacao = solicitacao;
        this.responsavel = responsavel;
        this.dataConclusao = LocalDateTime.now();
        this.observacao = observacao;
    }

    public Solicitacao getSolicitacao(){
        return solicitacao;
    }

    public String getResponsavel(){
        return responsavel;
    }

    public LocalDateTime getDataConclusao(){
        return dataConclusao;
    }

    public String getObservacao(){
        return observacao;
    }
 
    public String toString(){
        String texto = "Atendimento da solicitação " + solicitacao.getCodigo()
                + " | Responsável: " + responsavel 
                + " | Conclusão: " + dataConclusao.format(FORMATO);
        if (observacao != null && !observacao.isEmpty()){
            texto += " | OBS: " + observacao;
        }
        return texto;
    }

}