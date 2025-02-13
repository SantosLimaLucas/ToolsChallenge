package tools.pagamentos.toolsChallenge;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeEach;
import tools.pagamentos.toolsChallenge.exception.ToolsChallengeException;
import tools.pagamentos.toolsChallenge.models.Descricao;
import tools.pagamentos.toolsChallenge.models.FormaPagamento;
import tools.pagamentos.toolsChallenge.models.Transacao;
import tools.pagamentos.toolsChallenge.models.enums.FormaPagamentoENUM;
import tools.pagamentos.toolsChallenge.models.enums.StatusPagamentoEnum;
import tools.pagamentos.toolsChallenge.service.impl.PagamentoServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ToolsChallengeApplicationTests {

    @Test
    void contextLoads() {
    }

    private PagamentoServiceImpl pagamentoService;

    @BeforeEach
    public void setUp() {
        pagamentoService = new PagamentoServiceImpl();
    }

    @Test
    public void testIncluirPagamento_Sucesso() {
        FormaPagamento formaPagamento = new FormaPagamento();
        formaPagamento.setTipo(FormaPagamentoENUM.AVISTA);
        formaPagamento.setParcelas("1");
        Transacao transacao = criarTransacao("1", formaPagamento);

        pagamentoService.incluirPagamento(transacao);
        List<Transacao> pagamentos = pagamentoService.consultarPagamentos();

        assertEquals(1, pagamentos.size());
        assertEquals("1", pagamentos.get(0).getId());
        assertEquals(StatusPagamentoEnum.AUTORIZADO, pagamentos.get(0).getDescricao().getStatus());
        assertNotNull(pagamentos.get(0).getDescricao().getNsu());
        assertNotNull(pagamentos.get(0).getDescricao().getCodigoAutorizacao());
    }

    @Test
    public void testIncluirPagamento_ShouldThrowException_IdDuplicado() {
        FormaPagamento formaPagamento = new FormaPagamento();
        formaPagamento.setTipo(FormaPagamentoENUM.AVISTA);
        formaPagamento.setParcelas("1");
        Transacao transacao1 = criarTransacao("1", formaPagamento);
        Transacao transacao2 = criarTransacao("1", formaPagamento);

        pagamentoService.incluirPagamento(transacao1);

        ToolsChallengeException excecao = assertThrows(ToolsChallengeException.class, () -> {
            pagamentoService.incluirPagamento(transacao2);
        });

        assertEquals("Id já utilizado!", excecao.getMessage());
    }

	@Test
	public void testIncluirPagamento_ShouldThrowException_ParcelasNaoInformadas() {
		FormaPagamento formaPagamento = new FormaPagamento();
		formaPagamento.setTipo(FormaPagamentoENUM.PARCELADO_EMISSOR);
		Transacao transacao1 = criarTransacao("1", formaPagamento);

		ToolsChallengeException excecao = assertThrows(ToolsChallengeException.class, () -> {
			pagamentoService.incluirPagamento(transacao1);
		});

		assertEquals("Para este tipo de pagamento o número de parcelas deve ser informado!", excecao.getMessage());
	}

    @Test
    public void testConsultarPagamentos() {
        FormaPagamento formaPagamento = new FormaPagamento();
        formaPagamento.setTipo(FormaPagamentoENUM.AVISTA);
        formaPagamento.setParcelas("1");
        pagamentoService.incluirPagamento(criarTransacao("1", formaPagamento));
        pagamentoService.incluirPagamento(criarTransacao("2", formaPagamento));

        List<Transacao> pagamentos = pagamentoService.consultarPagamentos();

        assertEquals(2, pagamentos.size());
    }

    @Test
    public void testConsultarPagamentoPorId_Sucesso() {
        FormaPagamento formaPagamento = new FormaPagamento();
        formaPagamento.setTipo(FormaPagamentoENUM.AVISTA);
        formaPagamento.setParcelas("1");
        pagamentoService.incluirPagamento(criarTransacao("1", formaPagamento));

        Optional<Transacao> transacao = pagamentoService.consultarPagamentoPorId("1");

        assertTrue(transacao.isPresent());
        assertEquals("1", transacao.get().getId());
    }

    @Test
    public void testConsultarPagamentoPorID_RetornarVazioIdNaoEncontrado() {
        Optional<Transacao> transacao = pagamentoService.consultarPagamentoPorId("99");

        assertTrue(transacao.isEmpty());
    }

    @Test
    public void testRealizarEstorno_Sucesso() {
        FormaPagamento formaPagamento = new FormaPagamento();
        formaPagamento.setTipo(FormaPagamentoENUM.AVISTA);
        formaPagamento.setParcelas("1");
        pagamentoService.incluirPagamento(criarTransacao("1", formaPagamento));

        Optional<Transacao> transacaoEstornada = pagamentoService.realizarEstorno("1");

        assertTrue(transacaoEstornada.isPresent());
        assertEquals(StatusPagamentoEnum.CANCELADO, transacaoEstornada.get().getDescricao().getStatus());
    }

    @Test
    public void testRealizarEstorno_IdNaoEncontrado() {
        Optional<Transacao> transacaoEstornada = pagamentoService.realizarEstorno("99");

        assertTrue(transacaoEstornada.isEmpty());
    }

    // Método auxiliar para criar Transacao
    private Transacao criarTransacao(String id, FormaPagamento formaPagamento) {
        Descricao descricao = new Descricao();
        descricao.setValor("100.00");
        descricao.setDataHora("01/05/2021 18:30:00");
        descricao.setEstabelecimento("Pet Shop Brasil");

        Transacao transacao = new Transacao();
        transacao.setId(id);
        transacao.setCartao("111111111");
        transacao.setDescricao(descricao);
        transacao.setFormaPagamento(formaPagamento);

        return transacao;
    }
}


