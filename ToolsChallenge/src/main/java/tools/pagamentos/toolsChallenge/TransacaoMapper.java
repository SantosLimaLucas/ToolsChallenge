package tools.pagamentos.toolsChallenge;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import tools.pagamentos.toolsChallenge.controller.dto.TransacaoDTO;
import tools.pagamentos.toolsChallenge.models.Transacao;
import tools.pagamentos.toolsChallenge.models.enums.FormaPagamentoENUM;

@Mapper
public interface TransacaoMapper {

    TransacaoMapper INSTANCE = Mappers.getMapper(TransacaoMapper.class);
    @Mapping(target = "descricao.valor", source = "descricao.valor")
    @Mapping(target = "descricao.dataHora", source = "descricao.dataHora")
    @Mapping(target = "descricao.estabelecimento", source = "descricao.estabelecimento")
    @Mapping(target = "descricao.nsu", source = "descricao.nsu")
    @Mapping(target = "descricao.codigoAutorizacao", source = "descricao.codigoAutorizacao")
    @Mapping(target = "descricao.status", source = "descricao.status")
    @Mapping(target = "formaPagamento.tipo", source = "formaPagamento.tipo", qualifiedByName = "stringToEnum")
    @Mapping(target = "formaPagamento.parcelas", source = "formaPagamento.parcelas")
    Transacao convertDtoToEntity(TransacaoDTO transacaoDTO);

    @Named("stringToEnum")
    default FormaPagamentoENUM stringToEnum(String formaPagamento) {
        if (formaPagamento == null) {
            return null;
        }
        for (FormaPagamentoENUM e : FormaPagamentoENUM.values()) {
            if (e.formaPagamento.equalsIgnoreCase(formaPagamento)) {
                return e;
            }
        }
        throw new IllegalArgumentException("Forma de pagamento inválida: " + formaPagamento);
    }
}
