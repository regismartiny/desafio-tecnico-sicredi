package com.example.desafiotecnicosicredi.constants;

import lombok.Getter;

@Getter
public enum I18Constants {
    REGISTRO_NAO_ENCONTRADO("registro.nao.encontrado"),
    CAMPOS_INVALIDOS("campos.invalidos"),
    SESSAO_NAO_INICIOU("sessao.nao.iniciou"),
    SESSAO_EXPIRADA("sessao.expirada");

    final String key;
    I18Constants(String key) {
        this.key = key;
    }
}
