#!/usr/bin/sh
rm -fr /opt/apache-tomcat/webapps/ROOT.war
git pull
mvn clean install -Dmaven.test.skip=true
cp target/zslb-console-1.0-SNAPSHOT.war /opt/apache-tomcat/webapps/ROOT.war
