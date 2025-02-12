package tools.pagamentos.toolsChallenge.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tools.pagamentos.toolsChallenge.TransacaoMapper;
import tools.pagamentos.toolsChallenge.controller.dto.TransacaoRequest;
import tools.pagamentos.toolsChallenge.models.Transacao;
import tools.pagamentos.toolsChallenge.service.PagamentoService;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/pagamentos")
@NoArgsConstructor
@AllArgsConstructor
public class PagamentosController {

    @Autowired
    private PagamentoService pagamentoService;
    @PostMapping
    public HttpEntity<Object> incluirPagamento(@RequestBody TransacaoRequest transacaoRequest){
        try {
            Transacao transacao = TransacaoMapper.INSTANCE.convertDtoToEntity(transacaoRequest.getTransacao());
            pagamentoService.incluirPagamento(transacao);
            return ResponseEntity.status(HttpStatus.CREATED).body(transacao);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @GetMapping
    public HttpEntity<Object> consultarPagamentos(){
        List<Transacao> pagamentos = pagamentoService.consultarPagamentos();
        return ResponseEntity.status(HttpStatus.OK).body(pagamentos);
    }

    @PatchMapping
    public HttpEntity<Object> realizarEstorno(@RequestParam String idTransacao){
        Optional<Transacao> transacao = pagamentoService.realizarEstorno(idTransacao);
        return transacao.isPresent() ? ResponseEntity.status(HttpStatus.OK).body(transacao.get()) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
