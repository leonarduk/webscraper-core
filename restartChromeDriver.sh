#!/bin/bash
PID=`ps -ef  | grep chromedriver | grep -v grep | awk  ' { print $2 } '`

if [[ $PID != '' ]]; then
echo "Already running - kill it and restart"
kill $PID
else
echo "Not Running - will start"
fi
nohup /home/stephen/workspace/luk/trunk/core/src/main/shell/chromedriver & >/dev/null

