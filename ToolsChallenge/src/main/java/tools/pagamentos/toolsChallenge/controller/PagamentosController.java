package tools.pagamentos.toolsChallenge.controller;

import jakarta.validation.Valid;
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
    public HttpEntity<Object> incluirPagamento(@Valid @RequestBody TransacaoRequest transacaoRequest){
        Transacao transacao = TransacaoMapper.INSTANCE.convertDtoToEntity(transacaoRequest.getTransacao());
        pagamentoService.incluirPagamento(transacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(transacao);
    }
    @GetMapping
    public HttpEntity<Object> consultarPagamentos(){
        List<Transacao> pagamentos = pagamentoService.consultarPagamentos();
        return ResponseEntity.status(HttpStatus.OK).body(pagamentos);
    }
    @GetMapping("/{id}")
    public HttpEntity<Object> consultarPagamentoPorId(@PathVariable("id") String idTransacao){
        Optional<Transacao> transacao = pagamentoService.consultarPagamentoPorId(idTransacao);
        return transacao.isPresent() ? ResponseEntity.status(HttpStatus.OK).body(transacao.get()) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PatchMapping("/{id}/estorno")
    public HttpEntity<Object> realizarEstorno(@PathVariable("id") String idTransacao){
        Optional<Transacao> transacao = pagamentoService.realizarEstorno(idTransacao);
        return transacao.isPresent() ? ResponseEntity.status(HttpStatus.OK).body(transacao.get()) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
