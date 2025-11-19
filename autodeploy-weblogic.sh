#!/usr/bin/env bash

export JAVA_HOME=/Users/tiagopadua/dev/openlogic-openjdk-8u352-b08-mac-x64/jdk1.8.0_352.jdk/Contents/Home
export PATH=$JAVA_HOME/bin:$PATH
mvn spotless:apply clean install
rm /Volumes/LEXAR_1TB/Oracle/fmw/user_projects/domains/base_domain/autodeploy/*.war
cp target/*.war /Volumes/LEXAR_1TB/Oracle/fmw/user_projects/domains/base_domain/autodeploy
