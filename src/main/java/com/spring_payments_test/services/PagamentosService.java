package com.spring_payments_test.services;

import ch.qos.logback.classic.spi.ILoggingEvent;
import com.spring_payments_test.controller.PagamentoDTO;
import com.spring_payments_test.exceptions.BadRequestEx;
import com.spring_payments_test.infrastructure.clients.NotClient;
import com.spring_payments_test.infrastructure.entity.Carteira;
import com.spring_payments_test.infrastructure.entity.Pagamentos;
import com.spring_payments_test.infrastructure.entity.Usuario;
import com.spring_payments_test.infrastructure.entity.Usuarios;
import com.spring_payments_test.infrastructure.repository.PagamentoRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;
import java.security.PrivilegedActionException;


@Service
@RequiredArgsConstructor

public class PagamentosService {

    private final UsuarioService usuarioService;
    private final AuthService authService;
    private final CarteiraService carteiraService;
    private final PagamentoRepository repository;
    private final NotService notService;

    @Transactional
    public void enviarValores(PagamentoDTO pagamentoDTO) {
        Usuario pagador = usuarioService.buscarUsuario(pagamentoDTO.payer());
        Usuario recebedor = usuarioService.buscarUsuario(pagamentoDTO.payee());

        validaPagadorFuncionario(pagador);
        validarSaldoDoUsuario(pagador, pagamentoDTO.value());
        validarPagamento();

        pagador.getCarteira().setSaldo(pagador.getCarteira().getSaldo().subtract(pagamentoDTO.value()));
        atualizarSaldoDaCarteira(pagador.getCarteira());

        recebedor.getCarteira().setSaldo(pagador.getCarteira().getSaldo().add(pagamentoDTO.value()));
        atualizarSaldoDaCarteira(recebedor.getCarteira());

        Pagamentos pagamentos = Pagamentos.builder()
                .valor(pagamentoDTO.value())
                .Pagador(pagador)
                .Recebedor(recebedor)
                .build();

        repository.save(pagamentos);
        enviarNotificacao();

    }

    private void validaPagadorFuncionario(Usuario usuario) {
        try {
            if (usuario.getUsuarios().equals(Usuarios.Funcionario)) {
                throw new IllegalArgumentException("Pagamento não Autorizado para este Usuario");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private void validarSaldoDoUsuario(Usuario usuario, BigDecimal valor){
        try {
            if (usuario.getCarteira().getSaldo().compareTo(valor) < 0){
                throw new IllegalArgumentException("Transação Não autorizada: Saldo insuficiente!");
            }
        } catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private void validarPagamento() {
        try {
            if (!authService.validarPagamento()) {
                throw new IllegalArgumentException("Transação Não autorizada pela api");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private void atualizarSaldoDaCarteira(Carteira carteira){
        carteiraService.salvar(carteira);
    }

    private void enviarNotificacao(){
        try{
            notService.enviarNotficacao();
        } catch (HttpClientErrorException e){
            throw new BadRequestEx("Erro ao enviar a notificação");
        }
    }
}
