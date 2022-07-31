package io.github.support.function;

import java.util.Objects;

@FunctionalInterface
public interface SuperFunction<T> {

    T apply(Object... objects);

    default SuperFunction<T> andThen(SuperFunction<T> supperFunction) {
        Objects.requireNonNull(supperFunction);
        return objects -> supperFunction.apply(apply(objects));
    }
}
