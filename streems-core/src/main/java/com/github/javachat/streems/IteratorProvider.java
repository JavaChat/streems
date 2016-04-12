package com.github.javachat.streems;

import java.util.Iterator;
import java.util.function.Function;

@FunctionalInterface
public interface IteratorProvider<E>
    extends Function<E, Iterator<E>>
{
}
