package com.github.javachat.streems;

import java.util.function.Function;

@FunctionalInterface
public interface IterableProvider<E>
    extends Function<E, Iterable<E>>
{
}
