package com.example.desafiotecnicosicredi.constants;

import lombok.Getter;

@Getter
public enum I18Constants {
    NO_ITEM_FOUND("item.absent"),
    INVALID_FIELDS("invalid.fields");

    final String key;
    I18Constants(String key) {
        this.key = key;
    }
}
