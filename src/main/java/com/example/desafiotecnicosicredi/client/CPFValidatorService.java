package com.example.desafiotecnicosicredi.client;

import java.util.Locale;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.desafiotecnicosicredi.configuration.ConfigProperties;
import com.example.desafiotecnicosicredi.constants.I18Constants;
import com.example.desafiotecnicosicredi.dto.cpf.ValidatorResponse;
import com.example.desafiotecnicosicredi.exception.CPFValidationException;
import com.example.desafiotecnicosicredi.utils.CPFUtil;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class CPFValidatorService {

    @Autowired
    ConfigProperties configProperties;

    @Autowired
    MessageSource messageSource;

    @Autowired
    WebClient webClient;

    public boolean validarDocumento(String numeroDocumento) {
        log.info("Validando documento {}", numeroDocumento);

        if (!Boolean.TRUE.equals(configProperties.getCpfValidacaoExterna())) {
            log.info("Validando internamente");
            return CPFUtil.isCPF(numeroDocumento);
        }

        log.info("Validando na API externa");

        Mono<ValidatorResponse> validatorResponse = webClient.get()
                .uri(configProperties.getCpfValidacaoUrl(),
                        uri -> uri.queryParam("value", numeroDocumento).build())
                .header("Authorization", "Bearer " + configProperties.getCpfValidacaoToken())
                .retrieve()
                .onStatus(httpStatus -> !httpStatus.is2xxSuccessful(),
                        clientResponse -> tratarRespostaErro(clientResponse.statusCode()))
                .bodyToMono(ValidatorResponse.class);

        return Objects.requireNonNull(validatorResponse.block()).isValid();
    }

    private Mono<? extends Throwable> tratarRespostaErro(HttpStatusCode statusCode) {
        String mensagem = getLocalMessage(I18Constants.ERRO_AO_VALIDAR_CPF.getKey(), statusCode.toString());
        return Mono.error(new CPFValidationException(mensagem));
    }

    private String getLocalMessage(String key, String... params) {
        return messageSource.getMessage(key, params, LocaleContextHolder.getLocale());
    }
}
