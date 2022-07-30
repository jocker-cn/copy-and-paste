package io.github.support.function;

@FunctionalInterface
public interface Super4Function<A,B,C,D> {

    D apply(A a, B b, C c);

}
