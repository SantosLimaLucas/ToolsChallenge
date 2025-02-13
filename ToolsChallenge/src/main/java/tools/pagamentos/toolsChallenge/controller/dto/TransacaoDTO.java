package tools.pagamentos.toolsChallenge.controller.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class TransacaoDTO {
    @NotBlank(message = "É obrigatório informar o cartão")
    private String cartao;
    @NotBlank(message = "É obrigatório informar o id do pagamento")
    private String id;
    @Valid
    private DescricaoDTO descricao;
    @Valid
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
