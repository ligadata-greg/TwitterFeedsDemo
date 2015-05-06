#!/bin/bash

ipport="8998"

if [ "$1" != "debug" ]; then
	java -jar /root/Fatafat/bin/MetadataAPI-1.0 --config /root/Fatafat/input/Demo/metadata/config/MetadataAPIConfig_Cassandra.properties
else	
	java -Xdebug -Xrunjdwp:transport=dt_socket,address="$ipport",server=y -jar /root/Fatafat/bin/MetadataAPI-1.0 --config /root/Fatafat/input/application1/metadata/config/MetadataAPIConfig_Cassandra.properties
fi

