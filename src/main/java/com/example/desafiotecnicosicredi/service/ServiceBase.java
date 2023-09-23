package com.example.desafiotecnicosicredi.service;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class ServiceBase {

    @Autowired
    MessageSource messageSource;

    String getLocalMessage(String key, String... params) {
        return messageSource.getMessage(key, params, Locale.getDefault());
    }

}
