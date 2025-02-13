package tools.pagamentos.toolsChallenge.service.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import tools.pagamentos.toolsChallenge.exception.ToolsChallengeException;
import tools.pagamentos.toolsChallenge.models.FormaPagamento;
import tools.pagamentos.toolsChallenge.models.Transacao;
import tools.pagamentos.toolsChallenge.models.enums.FormaPagamentoENUM;
import tools.pagamentos.toolsChallenge.models.enums.StatusPagamentoEnum;
import tools.pagamentos.toolsChallenge.service.PagamentoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoServiceImpl implements PagamentoService {
    private List<Transacao> pagamentos = new ArrayList<>();
    @Override
    public void incluirPagamento(Transacao transacao){
        validarDados(transacao);
        gerarValoresNsuECodigoAutorizacao(transacao);
        this.pagamentos.add(transacao);
    }

    @Override
    public List<Transacao> consultarPagamentos() {
        return this.pagamentos;
    }

    @Override
    public Optional<Transacao> consultarPagamentoPorId(String idTransacao) {
        return pagamentos.stream()
                .filter(transacao -> transacao.getId().equals(idTransacao))
                .findFirst()
                ;
    }

    @Override
    public Optional<Transacao> realizarEstorno(String idTransacao) {
        return pagamentos.stream()
                .filter(transacao -> transacao.getId().equals(idTransacao))
                .findFirst()
                .map(transacao -> {
                    transacao.getDescricao().setStatus(StatusPagamentoEnum.CANCELADO);
                    return transacao;
                });
    }

    private void validarDados(Transacao transacao){
        transacao.getDescricao().setStatus(StatusPagamentoEnum.AUTORIZADO);
        validarIdUnico(transacao.getId());
        validarParcelasInformadas(transacao.getFormaPagamento());
    }

    private void gerarValoresNsuECodigoAutorizacao(Transacao transacao){
        Random rnd = new Random();
        Integer nsu = 100000 + rnd.nextInt(1000000000);
        Integer codigoAutorizacao = 100000 + rnd.nextInt(900000);
        transacao.getDescricao().setNsu(nsu.toString());
        transacao.getDescricao().setCodigoAutorizacao(codigoAutorizacao.toString());
    }
    private void validarIdUnico(String id){

        for(Transacao t : pagamentos){
            if(t.getId().equals(id)){
                throw new ToolsChallengeException("Id já utilizado!");
            }
        }
    }
    private void validarParcelasInformadas(FormaPagamento formaPagamento){
        if(!formaPagamento.getTipo().equals(FormaPagamentoENUM.AVISTA) &&(formaPagamento.getParcelas() == null || formaPagamento.getParcelas().isBlank())  ){
            throw new ToolsChallengeException("Para este tipo de pagamento o número de parcelas deve ser informado!");
        }
    }
}
