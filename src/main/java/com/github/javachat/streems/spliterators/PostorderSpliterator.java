package com.github.javachat.streems.spliterators;

import com.github.javachat.streems.NaryTreeSpliterator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Function;

public final class PostorderSpliterator<T>
    implements NaryTreeSpliterator<T>
{
    private final Function<T, Iterator<T>> fn;
    private final Deque<PostorderNode<T>> deque = new ArrayDeque<>();

    public PostorderSpliterator(final T root,
        final Function<T, Iterator<T>> fn)
    {
        this.fn = fn;
        deque.add(new PostorderNode<T>(root, fn));
    }

    @Override
    public boolean tryAdvance(final Consumer<? super T> action)
    {
        PostorderNode<T> node;
        Iterator<T> iterator;

        while (!deque.isEmpty()) {
            node = deque.getLast();
            iterator = node.childrenIterator;
            if (!iterator.hasNext()) {
                deque.removeLast();
                action.accept(node.root);
                return true;
            }
            deque.addLast(new PostorderNode<T>(iterator.next(), fn));
        }
        return false;
    }

    @Override
    public int characteristics()
    {
        return ORDERED | NONNULL;
    }

    private static class PostorderNode<E>
    {
        private final E root;
        private final Iterator<E> childrenIterator;

        private PostorderNode(final E root, final Function<E, Iterator<E>> fn)
        {
            this.root = root;
            childrenIterator = fn.apply(root);
        }
    }
}
