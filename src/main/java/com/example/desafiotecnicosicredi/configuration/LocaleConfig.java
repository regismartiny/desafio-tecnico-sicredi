package com.example.desafiotecnicosicredi.configuration;

import java.util.Locale;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class LocaleConfig {

    @Autowired
    ConfigProperties configProperties;

    @PostConstruct
    public void init() {
        setDefaultLocale();
        setDefaultTimeZone();
    }

    private void setDefaultTimeZone() {
        var timeZone = configProperties.getTimeZone();
        if (StringUtils.isNotBlank(timeZone)) {
            TimeZone.setDefault(TimeZone.getTimeZone(timeZone));
            log.info("TimeZone configured to {}", timeZone);
        }
    }

    private void setDefaultLocale() {
        String language = configProperties.getLocaleLanguage();
        String country = configProperties.getLocaleCountry();
        Locale locale;

        if (StringUtils.isNotBlank(language) && StringUtils.isNotBlank(country)) {
            locale = Locale.of(language, country);
        } else {
            locale = Locale.ENGLISH;
        }

        Locale.setDefault(locale);
        log.info("Locale configured to {}", locale);
    }
}