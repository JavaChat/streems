package com.github.javachat.streems.guava;

import com.github.javachat.streems.Traversal;
import com.google.common.collect.TreeTraverser;

import java.util.function.Function;

final class GuavaTraversal<E>
{
    private final TreeTraverser<E> traverser;

    GuavaTraversal(final TreeTraverser<E> traverser)
    {
        this.traverser = traverser;
    }

    Function<E, Iterable<E>> forTraversal(final Traversal traversal)
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

    private Iterable<E> preorder(final E root)
    {
        return traverser.preOrderTraversal(root);
    }

    private Iterable<E> postorder(final E root)
    {
        return traverser.postOrderTraversal(root);
    }

    private Iterable<E> breadthfirst(final E root)
    {
        return traverser.breadthFirstTraversal(root);
    }
}
