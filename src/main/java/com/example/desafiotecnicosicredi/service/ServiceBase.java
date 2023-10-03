package com.example.desafiotecnicosicredi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.desafiotecnicosicredi.configuration.CustomMessageSource;

@Service
public class ServiceBase {

    @Autowired
    CustomMessageSource messageSource;

    String getLocalMessage(String key, String... params) {
        return messageSource.getMessage(key, params);
    }

}
