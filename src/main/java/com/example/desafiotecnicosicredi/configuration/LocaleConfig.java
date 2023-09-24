package com.example.desafiotecnicosicredi.configuration;

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
        var timeZone = configProperties.getTimeZone();
        if (StringUtils.isNotBlank(timeZone)) {
            TimeZone.setDefault(TimeZone.getTimeZone(timeZone));
            log.info("TimeZone configured to {}", timeZone);
        }
    }
}