package tools.pagamentos.toolsChallenge.controller.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class TransacaoRequest {
    private TransacaoDTO transacao;

    public TransacaoDTO getTransacao(){
        return this.transacao;
    }

    public void setTransacao(TransacaoDTO transacao){
        this.transacao = transacao;
    }
}
