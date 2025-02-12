package tools.pagamentos.toolsChallenge.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tools.pagamentos.toolsChallenge.models.enums.FormaPagamentoENUM;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormaPagamento {
    FormaPagamentoENUM tipo;
    String parcelas;

    public FormaPagamentoENUM getTipo() {
        return tipo;
    }

    public void setTipo(FormaPagamentoENUM tipo) {
        this.tipo = tipo;
    }

    public String getParcelas() {
        return parcelas;
    }

    public void setParcelas(String parcelas) {
        this.parcelas = parcelas;
    }
}
