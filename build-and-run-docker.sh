#!/bin/sh

if [ "$(sudo docker images | grep delta-rent-a-car)" ]; then
   sudo docker rmi -f delta-rent-a-car 
fi

sudo docker build -t delta-rent-a-car . 

if [ "$(sudo docker ps -a | grep delta-rent-a-car)" ]; then
   sudo docker rm -f delta-rent-a-car
fi

sudo docker run -d --name delta-rent-a-car -p 8888:8080 delta-rent-a-car
