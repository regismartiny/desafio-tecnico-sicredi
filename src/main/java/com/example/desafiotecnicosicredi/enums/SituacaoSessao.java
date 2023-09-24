package com.example.desafiotecnicosicredi.enums;

import java.util.Arrays;

import com.example.desafiotecnicosicredi.exception.ApplicationException;

import lombok.Getter;

@Getter
public enum SituacaoSessao {
    CRIADA(1),
    EM_ANDAMENTO(2),
    FINALIZADA(3);

    private final Integer codigo;

    SituacaoSessao(Integer codigo) {
        this.codigo = codigo;
    }

    public static SituacaoSessao of(Integer codigo) {
        var opcaoVoto = Arrays.stream(values())
                .filter(element -> element.codigo.equals(codigo))
                .findFirst();

        if (opcaoVoto.isPresent()) {
            return opcaoVoto.get();
        }

        throw new ApplicationException(String.format("SituacaoSessao inválida %s", codigo));
    }
}
