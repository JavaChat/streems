package com.github.javachat.streems.guava;

import com.github.javachat.streems.Traversal;
import com.google.common.collect.TreeTraverser;

import java.util.function.Function;

public final class GuavaTraversal<E>
{
    private final TreeTraverser<E> traverser;

    public GuavaTraversal(final TreeTraverser<E> traverser)
    {
        this.traverser = traverser;
    }

    public Function<E, Iterable<E>> forTraversal(final Traversal traversal)
    {
        switch (traversal) {
            case BREADTHFIRST:
                return this::breadthfirst;
            case POSTORDER:
                return this::postorder;
            case PREORDER:
                return this::preorder;
        }
        throw new IllegalStateException("How did I even get there??");
    }

    public Iterable<E> preorder(final E root)
    {
        return traverser.preOrderTraversal(root);
    }

    public Iterable<E> postorder(final E root)
    {
        return traverser.postOrderTraversal(root);
    }

    public Iterable<E> breadthfirst(final E root)
    {
        return traverser.breadthFirstTraversal(root);
    }
}
