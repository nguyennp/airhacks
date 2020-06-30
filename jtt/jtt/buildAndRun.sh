#!/bin/sh
mvn clean package && docker build -t com.airhacks/jtt .
docker rm -f jtt || true && docker run -d -p 8080:8080 -p 4848:4848 --name jtt com.airhacks/jtt 
