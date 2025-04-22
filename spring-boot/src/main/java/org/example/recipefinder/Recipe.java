package org.example.recipefinder;

import org.springframework.data.annotation.Id;
import org.springframework.lang.Nullable;

public record Recipe(@Id @Nullable Long id, String name) {

    Recipe(String name) {
        this(null, name);
    }
}
