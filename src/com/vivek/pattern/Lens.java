package com.vivek.pattern;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public final class Lens<A, B> {

    private final Function<A, B> getter;
    private final BiFunction<A, B, A> setter;

    public Lens(final Function<A, B> getter, final BiFunction<A, B, A> setter) {
        this.getter = getter;
        this.setter = setter;
    }

    public static <A, B> Lens<A, B> of(Function<A, B> getter, BiFunction<A, B, A> setter) {
        return new Lens<>(getter, setter);
    }

    public B get(final A a) {
        return getter.apply(a);
    }

    public A set(final A a, final B b) {
        return setter.apply(a, b);
    }

    public A mod(final A a, final UnaryOperator<B> unaryOperator) {
        return set(a, unaryOperator.apply(get(a)));
    }

    public <C> Lens<C, B> compose(final Lens<C, A> that) {
        return new Lens<>(
                c -> get(that.get(c)),
                (c, b) -> that.mod(c, a -> set(a, b))
        );
    }

    public <C> Lens<A, C> andThen(final Lens<B, C> that) {
        return that.compose(this);
    }

}