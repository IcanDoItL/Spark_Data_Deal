#!/bin/bash
method=$1
path="/opt/hu/submit"
hadoop dfs -mkdir /user/hu/${method}
cd $path
hadoop dfs -put * /user/hu/${method}
