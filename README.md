## sbt project compiled with Dotty

### Usage

This is a normal sbt project, you can compile code with `sbt compile` and run it
with `sbt run`, `sbt console` will start a Dotty REPL.

For more information on the sbt-dotty plugin, see the
[dotty-example-project](https://github.com/lampepfl/dotty-example-project/blob/master/README.md).


Try installing Metals, Bloop and Coursier.
Bloop can compile test and run too. 

## Notes:

New scala enums are cool. But sometimes the extensibility is a bit annoying. For example, in the case of `enum Foo(a: Int, val b: Int)` - `a` is private, and `b` is public - and may require the use of override val.

Scalatest has much nicer handling when it comes to matching lists. This is very apparant in scala3 where the `: _*` infix operator has been removed. This means you cannot decompose a list to pass as varargs to AssertContainsInOrder - which has signature `(Any, Any, Any*)` ... I couldn't figure out how to decompose into an `Any*`.

## TODO:

Try to implement a solution with ZIO

## 2024 - uiua project.
- `rustup update stable`
- `cargo install uiua`
- `uiua init`