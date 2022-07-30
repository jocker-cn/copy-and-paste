package io.github.support.function;

import java.util.Objects;

@FunctionalInterface
public interface SupperFunction<T> {

    T apply(Object... objects);

    default SupperFunction<T> andThen(SupperFunction<T> supperFunction) {
        Objects.requireNonNull(supperFunction);
        return objects -> supperFunction.apply(apply(objects));
    }
}
