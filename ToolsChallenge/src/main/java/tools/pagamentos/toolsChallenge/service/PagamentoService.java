package tools.pagamentos.toolsChallenge.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import tools.pagamentos.toolsChallenge.models.Transacao;
import tools.pagamentos.toolsChallenge.models.enums.StatusPagamentoEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoService {
    private List<Transacao> pagamentos = new ArrayList<>();

    public void incluirPagamento(Transacao transacao) throws Exception {
        validarDados(transacao);
        gerarValoresNsuECodigoAutorizacao(transacao);
        this.pagamentos.add(transacao);
    }

    private void validarDados(Transacao transacao) throws Exception {
        transacao.getDescricao().setStatus(StatusPagamentoEnum.AUTORIZADO);
        validarIdUnico(transacao.getId());
    }
    private void gerarValoresNsuECodigoAutorizacao(Transacao transacao){
        Random rnd = new Random();
        Integer nsu = 100000 + rnd.nextInt(1000000000);
        Integer codigoAutorizacao = 100000 + rnd.nextInt(900000);
        transacao.getDescricao().setNsu(nsu.toString());
        transacao.getDescricao().setCodigoAutorizacao(codigoAutorizacao.toString());
    }
    private void validarIdUnico(String id) throws Exception {

        for(Transacao t : pagamentos){
            if(t.getId().equals(id)){
                throw new Exception("Id já utilizado!");
            }
        }
    }

    public List<Transacao> consultarPagamentos() {
        return this.pagamentos;
    }

    public Optional<Transacao> realizarEstorno(String idTransacao) {
        return pagamentos.stream()
                .filter(transacao -> transacao.getId().equals(idTransacao))
                .findFirst()
                .map(transacao -> {
                    transacao.getDescricao().setStatus(StatusPagamentoEnum.CANCELADO);
                    return transacao;
                });
    }
}
