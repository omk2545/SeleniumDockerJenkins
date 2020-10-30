#!/usr/bin/env bash
# Environment Variables
# HUB_HOST
# BROWSER
# MODULE
if [ "$1" == null ]; then
"$1"= 'search-module'
fi


source ~./bash_profile
echo " ********* Running the  $1 suite************** "
mvn clean install test -DsuiteXmlFile=$1.xml