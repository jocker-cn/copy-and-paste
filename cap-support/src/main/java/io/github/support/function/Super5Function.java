package io.github.support.function;

@FunctionalInterface
public interface Super5Function<A, B, C, D, E> {

    E apply(A a, B b, C c, D d);
}
