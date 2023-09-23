package com.example.desafiotecnicosicredi.enums;

import java.util.Arrays;

import com.example.desafiotecnicosicredi.exception.ApplicationException;

import lombok.Getter;

@Getter
public enum OpcaoVoto {
    SIM(1),
    NAO(2);

    private final Integer codigo;

    OpcaoVoto(Integer codigo) {
        this.codigo = codigo;
    }

    public static OpcaoVoto of(Integer codigo) {
        var opcaoVoto = Arrays.stream(values())
                .filter(element -> element.codigo.equals(codigo))
                .findFirst();

        if (opcaoVoto.isPresent()) {
            return opcaoVoto.get();
        }

        throw new ApplicationException(String.format("OpcaoVoto inválida %s", codigo));
    }
}
