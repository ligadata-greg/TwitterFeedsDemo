#!/bin/bash

ipport="8998"

if [ "$1" != "debug" ]; then
	java -jar /home/cloudera/Desktop/Demo-twitter/bin/MetadataAPI-1.0 --config /home/cloudera/Desktop/Demo-twitter/input/application1/metadata/config/MetadataAPIConfig_Cassandra.properties
else	
	java -Xdebug -Xrunjdwp:transport=dt_socket,address="$ipport",server=y -jar /home/cloudera/Desktop/Demo-twitter/bin/MetadataAPI-1.0 --config /home/cloudera/Desktop/Demo-twitter/input/application1/metadata/config/MetadataAPIConfig_Cassandra.properties
fi

