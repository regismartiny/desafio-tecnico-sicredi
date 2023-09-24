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
    private Duration validadePadraoSessao;

    private String timeZone;
}
