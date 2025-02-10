package tools.pagamentos.toolsChallenge.models.enums;

public enum FormaPagamentoENUM {
    AVISTA("AVISTA"),
    PARCELADO_LOJA("PARCELADO LOJA"),
    PARCELADO_EMISSOR("PARCELADO EMISSOR");

    public final String formaPagamento;

    private FormaPagamentoENUM(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
}
