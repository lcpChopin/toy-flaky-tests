#!/usr/bin/env bash

if [ "$1" = "" ] || [ "$2" = "" ]; then
    echo "Usage: ./diff-flaky-daikon.sh <pass|fail> <com.example.ExampleTest>"
elif [ "$1" = "pass" ]; then
    shift

    while ! java -cp ./target/classes:./target/test-classes:$DAIKONDIR/daikon.jar daikon.DynComp --ppt-omit-pattern='org.junit|junit.framework|junit.runner|com.sun.proxy.' org.junit.runner.JUnitCore $@
    do echo "Re-trying daikon.DynComp because the test failed..."; sleep 1; done;

    while ! java -cp ./target/classes:./target/test-classes:$DAIKONDIR/daikon.jar daikon.Chicory --ppt-omit-pattern='org.junit|junit.framework|junit.runner|com.sun.proxy.' --comparability-file=JUnitCore.decls-DynComp org.junit.runner.JUnitCore $@
    do echo "Re-trying daikon.Chicory because the test failed..."; sleep 1; done;

    java -cp $DAIKONDIR/daikon.jar daikon.Daikon JUnitCore.dtrace.gz >daikon-pass.log

    mv JUnitCore.inv.gz daikon-pass.inv.gz

    echo "Complete. Daikon text output is in daikon-pass.log, binary output is in daikon-pass.inv"
    
    gunzip daikon-pass.inv.gz
else
    shift

    while java -cp ./target/classes:./target/test-classes:$DAIKONDIR/daikon.jar daikon.DynComp --ppt-omit-pattern='org.junit|junit.framework|junit.runner|com.sun.proxy.' org.junit.runner.JUnitCore $@
    do echo "Re-trying daikon.DynComp because the test passed..."; sleep 1; done;

    while java -cp ./target/classes:./target/test-classes:$DAIKONDIR/daikon.jar daikon.Chicory --ppt-omit-pattern='org.junit|junit.framework|junit.runner|com.sun.proxy.' --comparability-file=JUnitCore.decls-DynComp org.junit.runner.JUnitCore $@
    do echo "Re-trying daikon.Chicory because the test passed..."; sleep 1; done;

    java -cp $DAIKONDIR/daikon.jar daikon.Daikon JUnitCore.dtrace.gz >daikon-fail.log

    mv JUnitCore.inv.gz daikon-fail.inv.gz

    echo "Complete. Daikon text output is in daikon-fail.log, binary output is in daikon-fail.inv"

    gunzip daikon-pass.inv.gz
fi;


