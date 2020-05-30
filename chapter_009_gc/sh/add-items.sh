#!/bin/bash
# This script supplies input for adding the specified number of items 
# to the tracker started with command line UI after redirection symbol.
# Arguments:
# $1 - number of items
# Example of usage: 
#  ./../../../chapter_009_gc/sh/add-items.sh 1000 | java ru.job4j.tracker.StartUI
x=1
while [ $x -le $1 ]
do
    # Send 'Add new Item' command to tracker
    echo 0
    echo "name-$x"
    echo "description-$x"
    x=$(( $x + 1 ))
done
# Send 'Exit Program' command to tracker
echo 6
