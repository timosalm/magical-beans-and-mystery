package org.example;

import org.springframework.lang.Nullable;

public record Recipe(@Nullable Long id, String name) {

    public Recipe(String name) {
        this(null, name);
    }
}
