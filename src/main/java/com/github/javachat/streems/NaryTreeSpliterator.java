package com.github.javachat.streems;

import java.util.Spliterator;

public interface NaryTreeSpliterator<T>
    extends Spliterator<T>
{
    @Override
    default Spliterator<T> trySplit()
    {
        return null;
    }

    @Override
    default long estimateSize()
    {
        return Long.MAX_VALUE;
    }
}
