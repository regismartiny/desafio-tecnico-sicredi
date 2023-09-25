package com.example.desafiotecnicosicredi.constants;

import lombok.Getter;

@Getter
public enum I18Constants {
    VOTO_NAO_ENCONTRADO("voto.nao.encontrado"),
    SESSAO_VOTACAO_NAO_ENCONTRADA("sessao.votacao.nao.encontrada"),
    PAUTA_NAO_ENCONTRADA("pauta.nao.encontrada"),
    CAMPOS_INVALIDOS("campos.invalidos"),
    SESSAO_NAO_INICIOU("sessao.nao.iniciou"),
    SESSAO_ENCERRADA("sessao.ja.encerrada"),
    ASSOCIADO_JA_VOTOU("associado.ja.votou");

    final String key;
    I18Constants(String key) {
        this.key = key;
    }
}
