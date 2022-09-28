#!/usr/bin/env bash
cd /home/kmilo/apps/chatilo || stderr -1;

git pull;

cd /home/kmilo/apps/chatilo/chatilo-containers || stderr -1;

docker-compose down --rmi all;

docker-compose up -d;

exit 0;