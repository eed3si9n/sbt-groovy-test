Demonstration for 

- http://stackoverflow.com/questions/32131595/how-to-get-sbt-to-pick-up-tests-written-in-groovy
- https://github.com/sbt/sbt/issues/2167

```scala
> test
[info] Start Compiling Test Groovy sources : /Users/xxx/sbt-2167-groovy/src/test/groovy 
[error] Test Foo.foo failed: expected:<1> but was:<2>, took 0.062 sec
[error] Failed: Total 1, Failed 1, Errors 0, Passed 0
[error] Failed tests:
[error]         Foo
[error] (test:test) sbt.TestsFailedException: Tests unsuccessful
[error] Total time: 1 s, completed Aug 23, 2015 5:05:01 AM
```

