#!/usr/bin/env bash
# Environment Variables
# HUB_HOST
# BROWSER
# MODULE


#set JAVA_HOME
export JAVA_HOME=$(/usr/libexec/java_home)
M2_HOME=/usr/local/Cellar/maven/3.6.3_1
export M2_HOME
PATH=$PATH:$JAVA_HOME/bin:$M2_HOME/bin
export PATH

echo $PATH

echo  "running test on  $1 "
echo " ********* Running the  $1 suite************** "
mvn clean test  -DsuiteXmlFile="$1"
