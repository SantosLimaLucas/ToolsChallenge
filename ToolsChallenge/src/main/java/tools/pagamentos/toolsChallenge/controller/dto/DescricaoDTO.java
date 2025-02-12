package tools.pagamentos.toolsChallenge.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DescricaoDTO {
        String valor;
        String dataHora;
        String estabelecimento;
        String nsu;
        String codigoAutorizacao;
        String status;

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