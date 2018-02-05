#!/bin/bash
realFilePath=$1
evaluateFilePath=$2
outputFile=$3
method=$4
master=$5
/usr/bin/expect <<!
spawn  ssh root@192.168.10.101
expect {
      "password" {send "laijie\r"; exp_continue}
}
expect "#"
send "chmod 744 /opt/hu/hadoop.sh\r"
expect "#"
send "/opt/hu/hadoop.sh ${method}\r"
expect "#"
send "/opt/hadoop/spark/bin/spark-submit --class main.Main --executor-memory 6G --num-executors 100 --executor-cores 2 /opt/hu/app/F_measure.jar -ir ${realFilePath} -ie ${evaluateFilePath} -o ${outputFile} -m ${method} -master ${master}\r"
set timeout 2000
expect "#"
send "exit\r"
expect eof
!
