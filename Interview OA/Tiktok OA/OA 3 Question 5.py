# Tiktok OA 3 Question 5

#!/bin/python3

import math
import os
import random
import re
import sys

#
# Complete the 'get_shortest_time' function below.
#
# The function is expected to return a LONG_INTEGER.
# The function accepts following parameters:
#  1. STRING startNode
#  2. STRING endNode
#  3. 2D_STRING_ARRAY paths
#
import heapq
import collections

def get_shortest_time(startNode, endNode, paths):
    # Write your code here
    
    # Save all the path with value from graph
    pathGraph = collections.defaultdict(list)
    for i in paths:
        pathGraph[i[0]].append((i[1], int(i[2])))
    
    #declare the heap to used traverse
    heap = [(0, startNode)]
    visited = set() # used to save the visited node
    timeList = {} # used to save the time of all the nodes need used it
    timeList[startNode] = 0
    
    #used while loop to traverse all the path node
    while heap:
        currTime, currNode = heapq.heappop(heap)
        #if the currNode was not visted before
        if currNode not in visited:
            for nextNode, i in pathGraph.get(currNode, ()):
                #if the next node was visited before => jump and contiue it
                if nextNode in visited:
                    continue
                
                #declare a previous time value and newest time value
                prevTime = timeList.get(nextNode, None)
                newTime = currTime + i
                
                #Compare the currTime with previous time, and find the shorest time
                if prevTime is None or newTime < prevTime:
                    timeList[nextNode] = newTime 
                    heapq.heappush(heap, (newTime, nextNode))
            
            #change the currNode become visisted
            visited.add(currNode) 
            #if arrived the end node, print and output the shortest time
            if currNode == endNode:
                return currTime
                    
    return 0

