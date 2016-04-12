## Read me first

This project is licensed under the Apache Software License, version 2. See the
LICENSE file at the root of the project for more details.

This project requires **Java 8**.

## What this is

This library provides a
[`Stream`](http://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html)
interface over n-ary trees.

The idea was inspired by Guava's
[`TreeTraverser`](http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/collect/TreeTraverser.html),
which provides the same service, or nearly so...

Since Guava is aimed at Java 7 or less, its traversal methods return instances
of
[`Iterable`](http://docs.oracle.com/javase/8/docs/api/java/lang/Iterable.html)s,
not `Stream`s. This package intends to fill the gap.

## Usage

You provide a class which gives access to its immediate children, _in order_,
using either of:

* an `Iterator`,
* an `Iterable`,
* or an array.

You can then use this API to traverse instances of the class as an n-ary tree in
any order: preorder, postorder or breadth first.

For instance:

```
final Stream<MyNode> stream
        = Streems.traverse(myNode, MyNode::children, Traversal.PREORDER)`;
```

