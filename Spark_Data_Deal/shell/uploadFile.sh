#!/bin/bash
filepath="/home/james/graduate/submit/"
cd $filepath
/usr/bin/expect <<EOF
spawn bash -c "scp *  root@192.168.10.101:/opt/hu/submit"
expect {
      "password:" {send "laijie\r"; exp_continue};
      eof
}
EOF
