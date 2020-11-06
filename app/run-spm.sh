#!/usr/bin/env bash

#REM set the class path,
#REM assumes the build was executed with maven copy-dependencies
export BASE_CP=base.app.spm.console/target/base.app.spm.console-1.3.0-SNAPSHOT.jar:base.app.spm.console/target/dependency/*;

#REM call the java VM, e.g,
java -cp $BASE_CP eapli.base.app.spm.console.SPMApp
