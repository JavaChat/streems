package com.github.javachat.streems.trees;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class SimpleTree
{
    private final String value;
    private final List<SimpleTree> children = new ArrayList<>();

    public static SimpleTree sampleTree()
    {
        final SimpleTree root = new SimpleTree("h");

        SimpleTree node;

        node = root.add("d");
        node.add("a");
        node.add("b");
        node.add("c");

        root.add("e");

        node = root.add("g");
        node.add("f");

        return root;
    }

    private SimpleTree(final String value)
    {
        this.value = value;
    }

    public Iterator<SimpleTree> children()
    {
        return children.iterator();
    }

    private SimpleTree add(final String value)
    {
        final SimpleTree ret = new SimpleTree(value);
        children.add(ret);
        return ret;
    }

    @Override
    public String toString()
    {
        return value;
    }
}
