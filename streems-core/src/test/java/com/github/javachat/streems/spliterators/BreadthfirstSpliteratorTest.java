package com.github.javachat.streems.spliterators;

import com.github.javachat.streems.trees.SimpleTree;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.Assertions.assertThat;

public final class BreadthfirstSpliteratorTest
{
    @Test
    public void preorderTest()
    {
        final SimpleTree tree = SimpleTree.sampleTree();
        final Spliterator<SimpleTree> spliterator
            = new BreadthFirstSpliterator<>(tree, SimpleTree::children);
        final Stream<SimpleTree> stream
            = StreamSupport.stream(spliterator, false);

        final List<String> list = stream.map(Object::toString)
            .collect(Collectors.toList());

        assertThat(list)
            .containsExactly("h", "d", "e", "g", "a", "b", "c", "f");
    }
}
