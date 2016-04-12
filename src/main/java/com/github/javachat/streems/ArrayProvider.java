package com.github.javachat.streems;

import java.util.function.Function;

@FunctionalInterface
public interface ArrayProvider<E>
    extends Function<E, E[]>
{
}
