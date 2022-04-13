#! /bin/bash

APP_NAME=service-gateway-server
SCRIPT_DIR=$(cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd)
PID=$(ps -ef | grep java | grep $APP_NAME.jar | awk '{print $2}')

if  [ ! -z "$PID" ] 
then
	echo "stopping [$APP_NAME]"
	kill -9 $PID
	sleep 10
fi

echo "starting [$APP_NAME]"
cd $SCRIPT_DIR
nohup java -Dspring.profiles.active=production -jar $SCRIPT_DIR/$APP_NAME.jar >> $SCRIPT_DIR/launch.log &