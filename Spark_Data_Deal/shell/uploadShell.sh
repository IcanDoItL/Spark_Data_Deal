#!/bin/bash
shellpath="/home/james/graduate/shell"
jarpath="/home/james/GraduceProject/F_measure/out/artifacts/F_measure"
cd $shellpath
/usr/bin/expect <<EOF
spawn scp hadoop.sh root@192.168.10.101:/opt/hu
expect {
      "password:" {send "laijie\r"; exp_continue}
      eof
}
cd $jarpath
spawn scp F_measure.jar root@192.168.10.101:/opt/hu/app
expect {
      "password:" {send "laijie\r"; exp_continue}
     eof
}
EOF
