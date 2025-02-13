package tools.pagamentos.toolsChallenge.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class DescricaoDTO {
    @NotBlank(message = "É obrigatório informar o valor do pagamento")
    public String valor;
    @NotBlank(message = "É obrigatório informar a data/hora do pagamento")
    public String dataHora;
    @NotBlank(message = "É obrigatório informar o estabelecimento")
    public String estabelecimento;
    public String nsu;
    public String codigoAutorizacao;

    public String status;

   public String getValor() {
            return valor;
    }

    public void setValor(String valor) {
            this.valor = valor;
    }

    public String getDataHora() {
            return dataHora;
    }

    public void setDataHora(String dataHora) {
            this.dataHora = dataHora;
    }

    public String getEstabelecimento() {
            return estabelecimento;
    }

    public void setEstabelecimento(String estabelecimento) {
            this.estabelecimento = estabelecimento;
    }

    public String getNsu() {
            return nsu;
    }

    public void setNsu(String nsu) {
            this.nsu = nsu;
    }

    public String getCodigoAutorizacao() {
            return codigoAutorizacao;
    }

    public void setCodigoAutorizacao(String codigoAutorizacao) {
            this.codigoAutorizacao = codigoAutorizacao;
    }

    public String getStatus() {
            return status;
    }

    public void setStatus(String status) {
            this.status = status;
    }
}