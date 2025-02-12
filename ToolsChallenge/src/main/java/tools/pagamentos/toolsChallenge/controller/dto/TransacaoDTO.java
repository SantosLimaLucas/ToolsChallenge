package tools.pagamentos.toolsChallenge.controller.dto;

public class TransacaoDTO {
    private String cartao;
    private String id;
    private DescricaoDTO descricao;
    private FormaPagamentoDTO formaPagamento;

    public DescricaoDTO getDescricao() {
        return descricao;
    }

    public void setDescricao(DescricaoDTO descricao) {
        this.descricao = descricao;
    }

    public FormaPagamentoDTO getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamentoDTO formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public String getCartao() {
        return cartao;
    }

    public void setCartao(String cartao) {
        this.cartao = cartao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
