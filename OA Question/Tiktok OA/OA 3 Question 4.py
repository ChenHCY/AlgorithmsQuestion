# Tiktok OA 3 Question 4

# Enter your code here. Read input from STDIN. Print output to STDOUT
import sys
wokers, streets = 0, 0

streetList = []

for i, line in enumerate(sys.stdin):
    #remove the " " 
    l = line.split(" ")
    #get the range of works and streets
    wokersRange = int(l[0])
    streetsRange = int(l[1])
    
    if i == 0:
        wokers = wokersRange
        streets = streetsRange
        streetList = [0 for i in range(streetsRange + 1)]
    else:
        streetList[wokersRange] += 1
        streetList[streetsRange + 1] -= 1
    
res = 0
number = 0

for j in range(len(streetList) - 1):
    number += streetList[j]
    if number == 0:
        res += 1

print(res)
