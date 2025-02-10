package tools.pagamentos.toolsChallenge.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import tools.pagamentos.toolsChallenge.models.enums.StatusPagamentoEnum;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Descricao {
        String valor;
        String dataHora;
        String estabelecimento;
        String nsu;
        String codigoAutorizacao;
        StatusPagamentoEnum status = StatusPagamentoEnum.AUTORIZADO;

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

        public StatusPagamentoEnum getStatus() {
                return status;
        }

        public void setStatus(StatusPagamentoEnum status) {
                this.status = status;
        }
}