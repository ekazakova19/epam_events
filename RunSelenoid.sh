# The script deploy selenoid only for MAC OSs
# The script does not install docker. It is supposed that docker is installed on the machine

#!/usr/bin/env bash
 docker ps --filter "name=^selenoid$" --format '{{.Ports}}'
 if [ $? -eq 0 ]
  then
  echo "Selenoid is already running"
  exit 0
  else
    open --background -a Docker
    sleep 5
    curl -s https://aerokube.com/cm/bash | bash \
    && ./cm selenoid start --vnc
    if [ $? -eq 0 ]
    then
      echo "Selenoid started successfully" >&2
      exit 0
    else
     echo "Start selenoid failed" >&2
      exit 1
    fi
  fi
