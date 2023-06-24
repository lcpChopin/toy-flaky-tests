# toy-flaky-tests

Toy dataset of flaky tests in Java for evaluating the abilities of simple root cause finders

## Using Daikon

Make sure to replace `${PATH.TO.PACKAGE.CLASS}` with the correct test class.

```bash
# make the .decls-DynComp file
java -cp ./target/classes:./target/test-classes:$DAIKONDIR/daikon.jar daikon.DynComp --ppt-omit-pattern='org.junit|junit.framework' org.junit.runner.JUnitCore ${PATH.TO.PACKAGE.CLASS}

java -cp ./target/classes:./target/test-classes:$DAIKONDIR/daikon.jar daikon.Chicory --ppt-omit-pattern='org.junit|junit.framework' --comparability-file=JUnitCore.decls-DynComp org.junit.runner.JUnitCore ${PATH.TO.PACKAGE.CLASS}

java -cp $DAIKONDIR/daikon.jar daikon.Daikon JUnitCore.dtrace.gz
```

## Contributing / Development

```bash
# run a specific test
mvn test -Dtest=class#method
# run all tests
mvn test
# compile w/o running tests (that will likely fail)
mvn package -Dmaven.test.skip
```
