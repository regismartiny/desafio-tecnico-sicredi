package com.example.desafiotecnicosicredi.configuration;

import java.time.Duration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "config")
@Getter
@Setter
public class ConfigProperties {

    @NotNull
    private Duration validadePadraoSessaoVotacao;

    private String timeZone;
    private String cpfValidacaoUrl;
    private String cpfValidacaoToken;
    private Boolean cpfValidacaoExterna;
}
