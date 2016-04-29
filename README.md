# Streems [![Build Status](https://travis-ci.org/JavaChat/streems.svg?branch=master)](https://travis-ci.org/JavaChat/streems)

## Read me first

This project is licensed under the Apache Software License, version 2. See the
LICENSE file at the root of the project for more details.

This project requires **Java 8**.

The current version is **0.2.0**:

```
dependencies {
    compile(group: "com.github.javachat", name: "streems", version: "0.1.0");
}
```

Another package exists which provides a way to reuse a `TreeTraverer` from Guava
(see below):


```
dependencies {
    compile(group: "com.github.javachat", name: "streems-guava", version: "0.1.0");
}
```

## What this is

This library provides a
[`Stream`](http://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html)
interface over n-ary trees.

The idea was inspired by Guava's
[`TreeTraverser`](http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/collect/TreeTraverser.html).
Since Guava is aimed at Java 7 or less, its traversal methods return instances
of
[`Iterable`](http://docs.oracle.com/javase/8/docs/api/java/lang/Iterable.html)s,
not `Stream`s. This package provides such a functionality.

### The different traversals of an n-ary tree

An example is provided by the javadoc of `TreeTraverser`; we will reproduce it
here:

```
For example, the tree

          h
        / | \
       /  e  \
      d       g
     /|\      |
    / | \     f
   a  b  c       

can be iterated over in preorder (hdabcegf), postorder (abcdefgh), or
breadth-first order (hdegabcf).
```

## Usage

You provide a class which gives access to its immediate children, _in order_,
using either of:

* a `Stream`,
* an `Iterator`,
* an `Iterable`,
* or an array.

You can then use this API to traverse instances of the class as an n-ary tree in
any order: preorder, postorder or breadth first.

For instance:

```
final Stream<MyNode> stream
    = Streems.traverse(myNode, MyNode::children, Traversal.PREORDER);
```

### Notes about the returned Streams...

The underlying
[`Spliterator`](http://docs.oracle.com/javase/8/docs/api/java/util/Spliterator.html)
will always have the same characteristics:
[`ORDERED`](http://docs.oracle.com/javase/8/docs/api/java/util/Spliterator.html#ORDERED)
 and
[`NONNULL`](http://docs.oracle.com/javase/8/docs/api/java/util/Spliterator.html#NONNULL):

* `ORDERED` is obvious... After all, you choose the traversal order and you
  expect nodes to be returned in that order;
* `NONNULL` because it is a requirement (you cannot attempt to get any children
  from a null node).

