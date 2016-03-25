## What this is

This library aims to provide a
[`Stream`](http://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html)
interface over n-ary trees.

The idea was inspired by Guava's
[`TreeTraverser`](http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/collect/TreeTraverser.html),
which provides the same service, or nearly so...

Since Guava is aimed at Java 7 or less, its traversal methods return instances
of
[`Iterable`](http://docs.oracle.com/javase/8/docs/api/java/lang/Iterable.html)s,
not `Stream`s. This package intends to fill the gap.

## Goals

### Reuse Guava

Guava has it, we might as well reuse it. This package will therefore provide a
way to use Guava's `TreeTraverser`s to produce `Stream`s out of them.

### Provide an API

In a more generic case, if you happen to have classes with parent/child
relationship and you can obtain a `Stream` of your children from a parent, then
this API may be used as well.

Well... A `Stream`, or an array, or a `Collection`... Ultimately anyway,
everything will become a `Stream`.

## Projected usage

### Reusing a Guava `TreeTraverser`

```java
// Choose your traversal order... Here, PREORDER
final Stream<Foo> stream = Streems.fromGuava(foo, treeTraverser, Traversal.PREORDER);
```

### Using your own classes

Provided your class, named `Foo`, can provide a `Stream` of its children using a
method with prototype `Stream<Foo> children()`, then:

```java
final Stream<Foo> stream = Streems.from(foo, Foo::children, Traversal.PREORDER);
```

