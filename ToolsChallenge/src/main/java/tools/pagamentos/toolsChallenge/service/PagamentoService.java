package tools.pagamentos.toolsChallenge.service;

import tools.pagamentos.toolsChallenge.models.Transacao;

import java.util.List;
import java.util.Optional;

public interface PagamentoService {
    void incluirPagamento(Transacao transacao);

    List<Transacao> consultarPagamentos();

    Optional<Transacao> consultarPagamentoPorId(String idTransacao);

    Optional<Transacao> realizarEstorno(String idTransacao);
}
