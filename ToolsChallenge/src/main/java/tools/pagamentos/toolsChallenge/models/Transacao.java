package tools.pagamentos.toolsChallenge.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
public class Transacao {
    //@JsonProperty("cartao")
    private String cartao;
    //@JsonProperty("id")
    private String id;
    //@JsonProperty("descricao")
    private Descricao descricao;
    //@JsonProperty("formaPagamento")
    private FormaPagamento formaPagamento;

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

    public Descricao getDescricao() {
        return descricao;
    }

    public void setDescricao(Descricao descricao) {
        this.descricao = descricao;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
}
