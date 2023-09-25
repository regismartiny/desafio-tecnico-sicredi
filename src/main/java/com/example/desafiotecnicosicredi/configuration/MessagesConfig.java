package com.example.desafiotecnicosicredi.configuration;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class MessagesConfig {

    @Bean
    public ResourceBundleMessageSource messageSource() {

        var source = new ResourceBundleMessageSource();
        source.setBasenames("lang/messages");
        source.setDefaultEncoding("ISO_8859_1");
        source.setUseCodeAsDefaultMessage(true);

        return source;
    }
}
